package onMarket.integracion.ModuloCategorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import onMarket.integracion.AdminDB.SingleDataSource;
import onMarket.integracion.ModuloSubcategorias.DAOSubcategoria;
import onMarket.integracion.factorias.FactoriaIntegracionImp;
import onMarket.negocio.ModuloCategorias.TCategoria;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;

public class DAOCategoriaImp implements DAOCategoria {
	
	/**
	 * Constructora
	 * */
	public DAOCategoriaImp(){}
	/**
	 * Metodo que se encarga de accerder a la base de datos y crear una nueva categoria si se cumplen las condiciones idoneas
	 * */
	public Integer create(TCategoria tCategoria) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		Integer ret;
		char comillas='"';
		String sql = "INSERT INTO categorias (nombre, activo) VALUES (" + comillas + tCategoria.getNombre() + comillas + 
				", " + tCategoria.getActivo() + " )";
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			ret = pst.executeUpdate();
			return ret; 

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * Metodo que se encarga de eliminar una categoria de la base de datos gracias al id que recibe por parametro
	 * Eliminara dicho elemento si existe.
	 * */
	public Integer delete(Integer id) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"DELETE FROM categorias WHERE id = "+id))
		{
			return pst.executeUpdate(); //devuelve el numero de filas modificadas
		}
		catch(SQLException e){
			 e.printStackTrace();
		     return null;
		}
	}
	/**
	 * Metodo que se encarga de actualizar una categoria con los datos que recibe por parametro de una categoria.
	 * Todo esto se realizara si dicho categoria existe en el sistema 
	 * */
	public Integer update(TCategoria tCategoria) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"'; 
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"UPDATE categorias SET activo = " + tCategoria.getActivo()
						+ ", nombre = " +comillas +tCategoria.getNombre()+comillas
						+ "WHERE id = " + tCategoria.getId()))
		{
			return pst.executeUpdate(); //devuelve el numero de filas modificadas
		}
		catch(SQLException e){
			 e.printStackTrace();
		     return null;
		}
	}
	
	/**
	 * Metodo que devuelve todas las categorias existentes en el sistema en un array de tipo TCategoria 
	 * */	
	public ArrayList<TCategoria> readAll() {
		DataSource ds= SingleDataSource.obtenerInstancia().getDS();
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						        "SELECT * FROM categorias WHERE activo = 1"))
		{
			try (ResultSet rs = pst.executeQuery()) {
				ArrayList<TCategoria> res= new ArrayList<>();
				while (rs.next()) {
					Integer id= new Integer(rs.getInt("categorias.id"));
					String nombre= new String(rs.getString("categorias.nombre"));
					Boolean activo= rs.getBoolean("categorias.activo");
					TCategoria elemento= new TCategoria(id,nombre,activo);
					res.add(elemento);
			     }
				con.close();
				return res;
			} 
		}catch (SQLException e) {
			 e.printStackTrace();
		     return null;
		    }
	}
	
	/**
	 * Metodo que devuelve una categoria si dado un nombre por parametro existe dicho elemento en el sistema.
	 * Si no es asi devolvera null
	 * */
	public TCategoria readByName(String nombre) {
		DataSource ds= SingleDataSource.obtenerInstancia().getDS();
		char comilla='"';
		TCategoria elemento=null;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"SELECT * FROM categorias WHERE nombre = "+comilla+nombre+comilla))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Integer id= new Integer(rs.getInt("categorias.id"));
					Boolean activo= rs.getBoolean("categorias.activo");
					elemento= new TCategoria(id,nombre,activo);
					FactoriaIntegracionImp fact = new FactoriaIntegracionImp();
					DAOSubcategoria daoSub = fact.generaDAOSubcategoria();
					ArrayList<TSubcategoria> subcate = daoSub.readByCategory(id);
					int i = 0;
					while (i < subcate.size()){
						elemento.setSubcategoria(subcate.get(i).getId());
						++i;
					}
			     }
				con.close();
				return elemento;
			}
			catch(NullPointerException o){
				o.printStackTrace();
				return null;
			}
		}catch (SQLException e) {
			 e.printStackTrace();
		     return null;
		    
		}
		catch (NullPointerException o){
			o.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo que devuelve una categoria si dado un id por parametro existe dicho elemento en el sistema.
	 * Si no es asi devolvera null
	 * */
	public TCategoria readById(Integer id) {
		DataSource ds= SingleDataSource.obtenerInstancia().getDS();
		char comilla='"';
		TCategoria elemento=null;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"SELECT * FROM categorias WHERE id = "+comilla+id+comilla))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					String nombre = rs.getString("categorias.nombre");
					Boolean activo= rs.getBoolean("categorias.activo");
					elemento= new TCategoria(id,nombre,activo);
					FactoriaIntegracionImp fact = new FactoriaIntegracionImp();
					DAOSubcategoria daoSub = fact.generaDAOSubcategoria();
					ArrayList<TSubcategoria> subcate = daoSub.readByCategory(id);
					int i = 0;
					while (i < subcate.size()){
						elemento.setSubcategoria(subcate.get(i).getId());
						++i;
					}
			     }
				con.close();
				return elemento;
			}
			catch(NullPointerException o){
				o.printStackTrace();
				return null;
			}
		}catch (SQLException e) {
			 e.printStackTrace();
		     return null;
		    
		}
		catch (NullPointerException o){
			o.printStackTrace();
			return null;
		}
	}
}