package onMarket.integracion.ModuloProductos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import onMarket.integracion.AdminDB.SingleDataSource;
import onMarket.negocio.ModuloProductos.TProducto;

public class DAOProductoImp implements DAOProducto{

	@Override
	public Integer create(TProducto tProducto) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		Integer ret;
		char comillas = '"';
		String sql = "INSERT INTO productos (subcategoria, marca, nombre, stock, precio, "
				+ "peso_vol, foto, activo) VALUES (" +
				+ tProducto.getSubcategoria() + ", " + comillas + tProducto.getMarca() + comillas + ", "
				+ comillas + tProducto.getNombre() + comillas + ", " + tProducto.getStock() + ", "
				+ tProducto.getPrecio() + ", " + comillas + tProducto.getPesoVol() + comillas + ", "
				+ comillas + tProducto.getFoto() + comillas + ", "
				+ tProducto.getEstado() + " )";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			ret = pst.executeUpdate();
			return ret;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public Integer update(TProducto tProducto) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		String sql = "UPDATE productos SET subcategoria = " + tProducto.getSubcategoria()
		+ ", marca = " + comillas + tProducto.getMarca() + comillas
		+ ", nombre = " + comillas + tProducto.getNombre() + comillas
		+ ", stock = " + tProducto.getStock()
		+ ", precio = " + tProducto.getPrecio()
		+ ", peso_vol = " + comillas + tProducto.getPesoVol() + comillas
		+ ", foto = " + comillas + tProducto.getFoto() + comillas
		+ ", activo = " + tProducto.getEstado()
		+ " WHERE id = " + tProducto.getId();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						sql))
		{
			return pst.executeUpdate();
		}
		catch(SQLException e){
			 e.printStackTrace();
		     return null;
		}
	}

	@Override
	public TProducto readByNameAndMarca(String nombre, String marca) {
		DataSource ds= SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		TProducto elemento = null;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"SELECT * FROM productos WHERE nombre = " + comillas + nombre + comillas
						+ " and marca = " + comillas + marca + comillas))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Integer id= new Integer(rs.getInt("productos.id"));
					Boolean activo= rs.getBoolean("productos.activo");
					Integer subcategoria = new Integer(rs.getInt("productos.subcategoria"));
					Double precio = new Double(rs.getDouble("productos.precio"));
					Integer stock = new Integer(rs.getInt("productos.stock"));
					String peso_vol = new String(rs.getString("productos.peso_vol"));
					String foto = new String(rs.getString("productos.foto"));
					elemento= new TProducto(id, nombre, activo, subcategoria , marca , precio, stock, peso_vol, foto);
			     }
				con.close();
				return elemento;
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		     return null;
		}
	}
	
	@Override
	public TProducto readById(Integer id) {
		DataSource ds= SingleDataSource.obtenerInstancia().getDS();
		char comillas='"';
		TProducto elemento = null;
		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"SELECT * FROM productos WHERE id = " + comillas + id + comillas))
		{
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Boolean activo= rs.getBoolean("productos.activo");
					Integer subcategoria = new Integer(rs.getInt("productos.subcategoria"));
					String marca = new String(rs.getString("productos.marca")) ;	
					String nombre = new String(rs.getString("productos.nombre"));
					Double precio = new Double(rs.getDouble("productos.precio"));
					Integer stock = new Integer(rs.getInt("productos.stock"));
					String peso_vol = new String(rs.getString("productos.peso_vol"));
					String foto = new String(rs.getString("productos.foto"));
					elemento= new TProducto(id, nombre, activo, subcategoria , marca , precio, stock, peso_vol, foto);
			     }
				con.close();
				return elemento;
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		     return null;
		    
		}
		
	}

	@Override
	public ArrayList<TProducto> readBySubcategoria(Integer idSub) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		String sql = "SELECT * FROM productos WHERE activo = 1 AND subcategoria = " + idSub.toString();
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery()) {
				ArrayList<TProducto> res = new ArrayList<>();
				while (rs.next()) {
					Integer id= new Integer(rs.getInt("productos.id"));
					Boolean activo= rs.getBoolean("productos.activo");
					Integer subcategoria = new Integer(rs.getInt("productos.subcategoria"));
					Double precio = new Double(rs.getDouble("productos.precio"));
					String marca = new String(rs.getString("productos.marca")) ;	
					String nombre = new String(rs.getString("productos.nombre"));
					Integer stock = new Integer(rs.getInt("productos.stock"));
					String peso_vol = new String(rs.getString("productos.peso_vol"));
					String foto = new String(rs.getString("productos.foto"));
					TProducto elemento = new TProducto(id, nombre, activo, subcategoria , marca , precio, stock, peso_vol, foto);
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


	@Override
	public Integer delete(Integer id) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement("DELETE FROM productos WHERE id = " + id)) {
			return pst.executeUpdate(); 
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
