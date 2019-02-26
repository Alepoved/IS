package onMarket.integracion.ModuloUsuarios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.sql.DataSource;

import onMarket.integracion.AdminDB.SingleDataSource;
import onMarket.negocio.ModuloUsuarios.TUsuario;

public class DAOUsuarioImp implements DAOUsuario{

	@Override
	public Integer create(TUsuario tUsuario) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		Integer ret;
		char comillas = '"';
		String sql = "INSERT INTO usuarios (nombre,mail,password, es_admin,apellido,direccion,telefono,f_nacimiento,foto,n_tarjeta,dir_entrega,activo) VALUES (" + comillas
				+ tUsuario.getNombre() + comillas + ", " +comillas +tUsuario.getMail() +comillas+ ", "+comillas+tUsuario.getPassword()+comillas+", "+tUsuario.getEsAdmin()+", "+comillas+tUsuario.getApellido()+comillas+", "
				+comillas+tUsuario.getDireccion()+comillas+", "+comillas+ tUsuario.getTelefono()+comillas+", "+comillas+tUsuario.getFecha_nac().toString()+comillas+", "+comillas+tUsuario.getFoto()+comillas+", "+comillas+tUsuario.getN_tarjeta()
				+comillas+", "+comillas+tUsuario.getDireccionEntrega()+comillas+", "
				+ tUsuario.isActivo() + " )";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			ret = pst.executeUpdate();
			return ret;

		} catch (SQLException e) {
			 System.out.println("No se pudo añadir el usuario el mail ya esta dado de alta");
		     return null;
		}
	}

	@Override
	public Integer delete(Integer id) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement("DELETE FROM usuarios WHERE id = " + id)) {
			return pst.executeUpdate(); 
										
		} catch (SQLException e) {
			System.out.println("No se pudo eliminar ese usuario");
			return null;
		}
	}

	@Override
	public Integer update(TUsuario tUsuario) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas='"'; 
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"UPDATE usuarios SET activo = " + tUsuario.isActivo()
						+ ", nombre = " +comillas +tUsuario.getNombre()+comillas
						+ ", mail = " + comillas + tUsuario.getMail() + comillas
						+ ", password = " +comillas +tUsuario.getPassword()+comillas
						+ ", es_admin = " +tUsuario.getEsAdmin()
						+ ", apellido = " +comillas +tUsuario.getApellido()+comillas
						+ ", direccion = " + comillas + tUsuario.getDireccion() + comillas
						+ ", telefono = " +comillas +tUsuario.getTelefono()+comillas
						+ ", f_nacimiento = " + comillas + tUsuario.getFecha_nac() + comillas
						+ ", foto = " +comillas +tUsuario.getFoto()+comillas
						+ ", n_tarjeta = " + comillas + tUsuario.getN_tarjeta() + comillas
						+ ", dir_entrega = " +comillas +tUsuario.getDireccionEntrega()+comillas
						+ " WHERE id = " + tUsuario.getId()))
		{
			return pst.executeUpdate(); //devuelve el numero de filas modificadas
		}
		catch(SQLException e){
			 e.printStackTrace();
		     return null;
		}
	}



	@Override
	public TUsuario readByEmail(String email) {
		char comillas='"';
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		String sql = "SELECT * FROM usuarios WHERE mail = "+comillas + email + comillas;
		TUsuario datosUser = null;
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery()) {
				if(rs.first())
				{
						
					Integer id = rs.getInt("usuarios.id");
					Integer telefono = rs.getInt("usuarios.telefono");
					boolean activo = rs.getBoolean("usuarios.activo");
					boolean esAdmin = rs.getBoolean("usuarios.es_admin");
					Date fechaNac = rs.getDate("usuarios.f_nacimiento");
					String Email = rs.getString("usuarios.mail");
					String password = rs.getString("usuarios.password");
					String nombre = rs.getString("usuarios.nombre");
					String apellido = rs.getString("usuarios.apellido");
					String direccion = rs.getString("usuarios.direccion");
					String foto = rs.getString("usuarios.foto");
					String n_tarjeta = rs.getString("usuarios.n_tarjeta");
					String dir_Entrega = rs.getString("usuarios.dir_entrega");
					
					datosUser = new TUsuario(id,telefono,esAdmin,activo,fechaNac,
								Email,password,nombre,apellido,direccion,foto,n_tarjeta,dir_Entrega);
				}
			con.close();
			return datosUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<TUsuario> readByType(String es_Admin) {
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		String sql = "SELECT * FROM usuarios WHERE activo = 1 AND es_admin = " + es_Admin;
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery()) {
				ArrayList<TUsuario> res = new ArrayList<>();
				while (rs.next()) {
					Integer id = new Integer(rs.getInt("usuarios.id"));
					Integer telefono = new Integer(rs.getInt("usuarios.telefono"));
					boolean activo =rs.getBoolean("usuarios.activo");
					boolean esAdmin = rs.getBoolean("usuarios.es_admin");
					Date fechaNac = rs.getDate("usuarios.f_nacimiento");
					String Email = new String(rs.getString("usuarios.mail"));
					String password = new String(rs.getString("usuarios.password"));
					String nombre = new String(rs.getString("usuarios.nombre"));
					String apellido = new String(rs.getString("usuarios.apellido"));
					String direccion = new String(rs.getString("usuarios.direccion"));
					String foto = new String(rs.getString("usuarios.foto"));
					String n_tarjeta = new String(rs.getString("usuarios.n_tarjeta"));
					String dir_Entrega = new String(rs.getString("usuarios.dir_entrega"));
					
					TUsuario elemento = new TUsuario(id,telefono,esAdmin,activo,fechaNac,
							Email,password,nombre,apellido,direccion,foto,n_tarjeta,dir_Entrega);
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
