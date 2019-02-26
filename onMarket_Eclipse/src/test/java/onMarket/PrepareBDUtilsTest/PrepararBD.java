package onMarket.PrepareBDUtilsTest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;

import onMarket.integracion.AdminDB.SingleDataSource;

public class PrepararBD {
 public PrepararBD(){};
 
 	/*
	 * Funcion que borra todas las Categorias y subcategorías de la base de datos
	 * hay que realizar ambas cosas pues están ligadas por foreign key
	 */
	public void borrarBD(){
		borrarLineas();
		borrarProductos();
		borrarPedidos();
		borrarSubcategorias();
		borrarUsuarios();
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		
		
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"DELETE FROM categorias"))
		{
			if(borrarSubcategorias())
			  pst.executeUpdate();
		}
		catch(SQLException e){
			 e.printStackTrace();
		}
	}
	
	/*
	 * Función que elimina todas las subcategorías de la BD*/
	private boolean borrarPedidos() {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"DELETE FROM pedidos"))
		{
			return pst.executeUpdate() >= 0;
			
		}
		catch(SQLException e){
			 e.printStackTrace();
			 return false;
		}
	}

	private boolean borrarLineas() {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"DELETE FROM lineas_pedido"))
		{
			return pst.executeUpdate() >= 0;
			
		}
		catch(SQLException e){
			 e.printStackTrace();
			 return false;
		}		
	}
	public boolean borrarSubcategorias(){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"DELETE FROM subcategorias"))
		{
			return pst.executeUpdate() >= 0;
			
		}
		catch(SQLException e){
			 e.printStackTrace();
			 return false;
		}
	}
	
	/**
	 * Funcion que borra la tabla de productos
	 * @return true si se ha borrado bien, false en caso contrario
	 */
	public boolean borrarProductos(){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"DELETE FROM productos"))
		{
			return pst.executeUpdate() >= 0;
			
		}
		catch(SQLException e){
			 e.printStackTrace();
			 return false;
		}
	}
	 
	public boolean borrarUsuarios(){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"DELETE FROM usuarios WHERE id != 1"))
		{
			return pst.executeUpdate() >= 0;
			
		}
		catch(SQLException e){
			 e.printStackTrace();
			 return false;
		}
	}

	
	 /**
	 * Funcion que inserta una fila en la tabla de categorias
	 * @param id id de la categoria a insertar
	 * @param nombre nombre de la categoria a insertar
	 * @param activo estado de la categoria a insertar
	 */
	public void insertCat(Integer id, String nombre, Boolean activo){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql = "INSERT INTO categorias (id, nombre, activo) VALUES (" + comillas + id
				+ comillas + ", " + comillas + nombre + comillas + 
				", " + activo + " )";
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.executeUpdate();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Funcion que inserta una fila en la tabla de subcategorias
	 * @param id id de la categoria a insertar
	 * @param nombre nombre de la categoria a insertar
	 * @param activo estado de la categoria a insertar
	 * @param categoria categoria a la que queremos referenciar
	 */
	public void insertSubcat(Integer id, String nombre, Boolean activo, Integer categoria){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql = "INSERT INTO subcategorias (id, nombre, activo, categoria) VALUES (" + comillas + id
				+ comillas + ", " + comillas + nombre + comillas + 
				", " + activo + ", "+ categoria + " )";
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.executeUpdate();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Funcion que inserta una fila en la tabla de productos
	 * @param id
	 * @param nombre
	 * @param activo
	 * @param subcategoria
	 * @param marca
	 * @param precio
	 * @param stock
	 * @param peso_vol
	 * @param foto
	 */
	public void insertProd(Integer id, String nombre, Boolean activo, Integer subcategoria,
			String marca, Double precio, Integer stock, String peso_vol, String foto){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas = '"';
		String sql = "INSERT INTO productos (id, subcategoria, marca, nombre, stock, precio, "
				+ "peso_vol, foto, activo) VALUES (" + id + ", "
				+ subcategoria + ", " + comillas + marca + comillas + ", "
				+ comillas + nombre + comillas + ", " + stock + ", "
				+ precio + ", " + comillas + peso_vol + comillas + ", "
				+ comillas + foto + comillas + ", "
				+ activo + " )";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void insertUsuario(String nombre,String mail,String password,boolean es_admin,
			  String apellido,String direccion,Integer telefono,Date f_nacimiento,
			  String foto,String n_tarjeta,String dir_entrega,boolean activo)
	{
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas = '"';
		password = DigestUtils.sha256Hex(password);
		String sql = "INSERT INTO usuarios (nombre,mail,password, es_admin,apellido,direccion,telefono,f_nacimiento,foto,n_tarjeta,dir_entrega,activo) VALUES (" + comillas
				+ nombre + comillas + ", " +comillas +mail +comillas+ ", "+comillas+password+comillas+", "+es_admin+", "+comillas+apellido+comillas+", "
				+comillas+direccion+comillas+", "+comillas+ telefono+comillas+", "+comillas+f_nacimiento.toString()+comillas+", "+comillas+foto+comillas+", "+comillas+n_tarjeta
				+comillas+", "+comillas+dir_entrega+comillas+", "
				+ activo + " )";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Funcion que nos devuelve el estado de una categoria
	 * @param nombre nombre de la categoria de la que queremos saber su estado
	 * @param activo estado de la categoria
	 */
	public boolean getActivoCat(String nombre){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql2 = "SELECT activo FROM categorias WHERE nombre = " + comillas + nombre + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql2))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					return rs.getBoolean("categorias.activo");
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Funcion que nos devuelve el nombre de una categoria
	 * @param id id de la categoria de la que queremos saber su nombre
	 * @param nombre nombre de la categoria
	 */
	public String getNombreCat(Integer id){
		String nombre = "";
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql2 = "SELECT * FROM categorias WHERE id = " + comillas + id + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql2))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					nombre = new String(rs.getString("categorias.nombre"));
					return nombre;
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return nombre;
	}
	
	/**
	 * Funcion que nos devuelve el nombre de una subcategoria
	 * @param id id de la subcategoria de la que queremos saber su nombre
	 * @return nombre de la subcategoria
	 */
	public String getNombreSubcat(Integer id){
		String nombre = "";
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql2 = "SELECT * FROM subcategorias WHERE id = " + comillas + id + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql2))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					nombre = new String(rs.getString("subcategorias.nombre"));
					return nombre;
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return nombre;
	}
	/**
	 * Funcion que nos devuelve el estado de una Subcategoria
	 * @param nombre nombre de la Subcategoria de la que queremos saber su estado
	 * @param activo estado de la Subcategoria
	 */
	public boolean getActivoSubcat(String nombre){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql2 = "SELECT activo FROM subcategorias WHERE nombre = " + comillas + nombre + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql2))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					return rs.getBoolean("subcategorias.activo");
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return true;
	}

	/**
	 * Funcion que nos devuelve el nombre de un producto
	 * @param id identificador del producto del que queremos saber su nombre
	 * @return nombre del producto correspondiente
	 */
	public String getNombreProd(Integer id){
		String nombre = "";
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql = "SELECT * FROM productos WHERE id = " + comillas + id + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					nombre = new String(rs.getString("productos.nombre"));
					return nombre;
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return nombre;
	}
	public Integer getIdProd(String nombre, String marca){
		Integer id = null;
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql = "SELECT * FROM productos WHERE nombre = " + comillas + nombre + comillas + " AND marca = " + comillas + marca + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					id = new Integer(rs.getInt("productos.id"));
					return id;
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return id;
	}
	/**
	 * Funcion que nos devuelve el activo de un producto
	 * @param id identificador del producto
	 * @return true si esta activo, false en caso contrario
	 */
	public boolean getActivoProd(Integer id){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql = "SELECT activo FROM productos WHERE id = " + comillas + id + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					return rs.getBoolean("productos.activo");
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Funcion que nos devuelve el stock de un producto
	 * @param id identificador del producto
	 * @return el stock del producto
	 */
	public Integer getStockProd(Integer id){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql = "SELECT stock FROM productos WHERE id = " + comillas + id + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					Integer stock = new Integer(rs.getInt("productos.stock"));
					return stock;
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return -1;
	}
	public Integer getIdUsuario(String mail){
		Integer id = null;
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql = "SELECT * FROM usuarios WHERE mail = " + comillas + mail + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					id = new Integer(rs.getInt("usuarios.id"));
					return id;
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * Funcion que nos devuelve el id de categoria de una subcategoria
	 * @param nombre nombre de la subcategoria
	 * @return id de la categoria, -1 en caso de error
	 */
	public Integer getCategoriaSubcat(String nombre){
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql = "SELECT categoria FROM subcategorias WHERE nombre = " + comillas + nombre + comillas;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()){
					Integer categoria = new Integer(rs.getInt("subcategorias.categoria"));
					return categoria;
				}
				con.close();
			} 
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return -1;
	}
}
