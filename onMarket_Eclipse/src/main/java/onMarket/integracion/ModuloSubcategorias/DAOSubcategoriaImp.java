package onMarket.integracion.ModuloSubcategorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import onMarket.integracion.AdminDB.SingleDataSource;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;

/**
 * Clae que implementa todos los metodos existentes en la interfaz de
 * DaoSubcategoria
 */
public class DAOSubcategoriaImp implements DAOSubcategoria {
	/**
	 * Constructora
	 */
	public DAOSubcategoriaImp() {
	}

	/**
	 * Metodo si implementar
	 */
	@Override
	public Integer create(TSubcategoria tSubcategoria) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		Integer ret;
		char comillas = '"';
		String sql = "INSERT INTO subcategorias (nombre, categoria, activo) VALUES (" + comillas
				+ tSubcategoria.getNombre() + comillas + ", " + tSubcategoria.getCategoria() + ", "
				+ tSubcategoria.isActivo() + " )";

		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			ret = pst.executeUpdate();
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo que se encarga de eliminar una subcategoria en funcion delid que
	 * recibe por parametro
	 */
	@Override
	public Integer delete(Integer id) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement("DELETE FROM subcategorias WHERE id = " + id)) {
			return pst.executeUpdate(); // devuelve el numero de filas
										// modificadas
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Modifica una subcategoria de nuestra base de datos
	 * 
	 * @param tSubcategoria
	 *            subcategoria con datos ya actualizados
	 * @return el numero de filas modificadas
	 */
	@Override
	public Integer update(TSubcategoria tSubcategoria) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"'; 
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"UPDATE subcategorias SET activo = " + tSubcategoria.isActivo()
						+ ", nombre = " +comillas +tSubcategoria.getNombre()+comillas
						+ ", categoria = " + comillas + tSubcategoria.getCategoria() + comillas
						+ "WHERE id = " + tSubcategoria.getId()))
		{
			return pst.executeUpdate(); //devuelve el numero de filas modificadas
		}
		catch(SQLException e){
			 e.printStackTrace();
		     return null;
		}
	}

	/**
	 * Metodo si implementar
	 */
	@Override
	public ArrayList<TSubcategoria> readAll() {
		return null;
	}

	/**
	 * Devuelve la subcategoria que tenga el nombre pasado por parametro
	 * 
	 * @param nombre
	 *            nombre de la subcategoria
	 * @return subcategoria con ese nombre
	 */
	@Override
	public TSubcategoria readByName(String nombre) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comilla = '"';
		TSubcategoria elemento = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con
						.prepareStatement("SELECT * FROM subcategorias WHERE nombre = " + comilla + nombre + comilla)) {
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Integer id = new Integer(rs.getInt("subcategorias.id"));
					Boolean activo = rs.getBoolean("subcategorias.activo");
					Integer categoria = new Integer(rs.getInt("subcategorias.categoria"));
					elemento = new TSubcategoria(id, nombre, activo, categoria);
				}
				con.close();
				return elemento;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metodo que devuelve un array de subcategorias cuyo id de categoria es el
	 * dado por parametro
	 */
	@Override
	public ArrayList<TSubcategoria> readByCategory(Integer id_categoria) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		String sql = "SELECT * FROM subcategorias WHERE activo = 1 AND categoria = " + id_categoria.toString();
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery()) {
				ArrayList<TSubcategoria> res = new ArrayList<>();
				while (rs.next()) {
					Integer id = new Integer(rs.getInt("subcategorias.id"));
					String nombre = new String(rs.getString("subcategorias.nombre"));
					Integer categoria = new Integer(rs.getInt("subcategorias.categoria"));
					Boolean activo = rs.getBoolean("subcategorias.activo");
					TSubcategoria elemento = new TSubcategoria(id, nombre, activo, categoria);
					res.add(elemento);
				}
				con.close();
				return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
