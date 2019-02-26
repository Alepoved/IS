package onMarket.integracion.ModuloPedidos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import onMarket.integracion.AdminDB.SingleDataSource;
import onMarket.negocio.ModuloPedidos.BOCesta;
import onMarket.negocio.ModuloPedidos.EstadoPedido;
import onMarket.negocio.ModuloPedidos.TLineaPedido;
import onMarket.negocio.ModuloPedidos.TPedido;

/*
 * Dany Team:
 * DAO Ataca directamente a la BD. Todas las comprobaciones de Nulidad y Control 
 * de parámetros se realizan en AS. Por tanto es responsabilidad del programador
 * asegurar que los datos que llegan a esta clase Sean Aceptables para los 
 * Métodos. Aún así se comprueba la nulidad básica. Mientras que la BD controla 
 * la nulidad de los campos.
 * */
public class DAOPedidosImp implements DAOPedidos {

	public DAOPedidosImp() {
	}

	@Override
	public Integer create(BOCesta cesta) {
		if (cesta == null)
			return null;
		if (cesta.getPedido() == null)
			return null;
		if (cesta.getLineasSize() <= 0)
			return null;
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		Integer retFinal;
		Integer id = 0;
		@SuppressWarnings("unused")
		Integer ret;
		char comillas = '"';
		int i = 0;
		String sqlLineas = "";
		String sqlPedido = "INSERT INTO pedidos (usuario, estado, "
				+ "dir_entrega, fecha_entrega, hora_entrega_ini, hora_entrega_fin, "
				+ "telefono, total, activo) VALUES (" + +cesta.getPedido().getId_usuario() + ", " + comillas
				+ cesta.getPedido().getEstado() + comillas + ", " + comillas + cesta.getPedido().getDir_entrega()
				+ comillas + ", " + comillas + cesta.getPedido().getFecha_entrega() + comillas + ", "
				+ cesta.getPedido().getHorario_ini() + ", " + cesta.getPedido().getHorario_fin() + ", " + comillas
				+ cesta.getPedido().getTelefono() + comillas + ", " + cesta.getPedido().getTotal_pagar() + ", "
				+ cesta.getPedido().getActivo() + " )";
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sqlPedido)) {
			retFinal = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		sqlLineas = "SELECT MAX(id) FROM pedidos WHERE usuario = " + cesta.getPedido().getId_usuario();
		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sqlLineas)) {
			try(ResultSet rs = pst.executeQuery()){
				if (rs.next()){
					id = new Integer(rs.getInt("MAX(id)"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		while (i < cesta.getLineasSize()) {
			sqlLineas = "INSERT INTO lineas_pedido (pedido, producto, cantidad, precio) VALUES ("
					+ id + ", " + cesta.getLineaIesima(i).getId_producto() + ", "
					+ cesta.getLineaIesima(i).getCantidad() + ", " + cesta.getLineaIesima(i).getPrecio_linea() + " )";
			try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sqlLineas)) {
				ret = pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			i++;
		}
		return retFinal;

	}

	@Override
	public ArrayList<TPedido> readByUser(Integer usuario) {
		if (usuario == null)
			return null;
		if (usuario <= 0)
			return null;
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		ArrayList<TPedido> res = new ArrayList<>();
		ArrayList<Integer> lista = new ArrayList<Integer>();
		Integer id = 0;
		// Lectura de pedidos
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement("SELECT * FROM pedidos WHERE usuario = " + usuario + " AND activo = 1")) {
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					id = new Integer(rs.getInt("pedidos.id"));
					Integer usu = new Integer(rs.getInt("pedidos.usuario"));
					String est = new String(rs.getString("pedidos.estado"));
					EstadoPedido estado;
					switch (est.toLowerCase().replaceAll("\\s","")) {
					case "encurso":
						estado = EstadoPedido.encurso;
						break; 
					case "cancelado":
						estado = EstadoPedido.cancelado;
						break; 
					case "enviado":
						estado = EstadoPedido.enviado;
						break;
					case "entregado":
						estado = EstadoPedido.entregado;
						break; 
					default:
						estado = null;
					}
					String dir = new String(rs.getString("pedidos.dir_entrega"));
					Date fecha = rs.getDate("pedidos.fecha_entrega");
					Integer hora_ini = new Integer(rs.getInt("pedidos.hora_entrega_ini"));
					Integer hora_fin = new Integer(rs.getInt("pedidos.hora_entrega_fin"));
					String telf = new String(rs.getString("pedidos.telefono"));
					Double total = new Double(rs.getDouble("pedidos.total"));
					Boolean activo = new Boolean(rs.getBoolean("pedidos.activo"));
					TPedido elemento = new TPedido(id, usu, estado, dir, fecha, hora_ini, hora_fin, telf, total, activo,
							lista);
					res.add(elemento);
				}
				if (res.size() == 0)
					return res;
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		// Lectura de las lineas de cada pedido
		for (int i = 0; i < res.size(); i++) {
			int idPedido = res.get(i).getId_pedido();
			try (Connection con2 = ds.getConnection();
					PreparedStatement pst2 = con2
							.prepareStatement("SELECT * FROM lineas_pedido WHERE pedido = " + idPedido)) {
				try (ResultSet rs2 = pst2.executeQuery()) {
					int it = 0;
					while (rs2.next()) {
						int idCogido = new Integer(rs2.getInt("lineas_Pedido.id"));
						res.get(i).addLineas_pedidoAt(it, idCogido);
						it++;
					}
					con2.close();
					it = 0;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		return res;

	}

	@Override
	public ArrayList<TLineaPedido> readAllLines(TPedido pedido) {
		if (pedido == null)
			return null;
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();

		try (Connection con = ds.getConnection();
				PreparedStatement pst = con
						.prepareStatement("SELECT * FROM lineas_pedido WHERE pedido = " + pedido.getId_pedido())) {
			try (ResultSet rs = pst.executeQuery()) {
				ArrayList<TLineaPedido> res = new ArrayList<>();
				int cont = 0; 
				while (rs.next()) {
					Integer id = new Integer(rs.getInt("lineas_pedido.id"));
					Integer ped = new Integer(rs.getInt("lineas_pedido.pedido"));
					Integer prod = new Integer(rs.getInt("lineas_pedido.producto"));
					Integer cant = new Integer(rs.getInt("lineas_pedido.cantidad"));
					Double prec = new Double(rs.getDouble("lineas_pedido.precio"));
					TLineaPedido elemento = new TLineaPedido(id, ped, prod, cant, prec);
					res.add(cont,elemento);
					cont++; 
				}
				con.close();
				if(res.size() == 0) return null; 
				else return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer update(TPedido pedido) {
		if (pedido == null)
			return null;
		if(pedido.getId_pedido()==null)
			return null;
		
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas = '"';
		String sql = "UPDATE pedidos SET usuario = " + pedido.getId_usuario() + ", estado = " + comillas
				+ pedido.getEstado().toString() + comillas + ", dir_entrega = " + comillas + pedido.getDir_entrega()
				+ comillas + ", fecha_entrega = " + comillas + pedido.getFecha_entrega().toString() + comillas
				+ ", hora_entrega_ini = " + pedido.getHorario_ini() + ", hora_entrega_fin = " + pedido.getHorario_fin()
				+ ", telefono = " + comillas + pedido.getTelefono() + comillas + ", total = " + pedido.getTotal_pagar()
				+ ", activo = " + pedido.getActivo() + " WHERE id = " + pedido.getId_pedido(); 

		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer delete(TPedido pedido) {
		if (pedido == null)
			return null;
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		ArrayList<TLineaPedido> lp = readAllLines(pedido);
		Integer in2 = 0;
		
		if(lp == null) return null; 

		if (lp.size() > 0) {

			try (Connection con = ds.getConnection();
					PreparedStatement pst = con.prepareStatement(
							"DELETE FROM lineas_pedido WHERE  lineas_pedido.pedido = " + pedido.getId_pedido())) {
				in2 = pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

		}else return null; 

		try (Connection con = ds.getConnection();
				PreparedStatement pst = con
						.prepareStatement("DELETE FROM pedidos WHERE  pedidos.id = " + pedido.getId_pedido())) {
			in2 += pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return in2;
	}

	@Override
	public TPedido readById(Integer id) {
		if (id == null)
			return null;
		if (id <= 0)
			return null;
		DataSource ds = SingleDataSource.obtenerInstancia().getDS();
		char comillas = '"';
		TPedido ret = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con
						.prepareStatement("SELECT * FROM pedidos WHERE id = " + comillas + id + comillas)) {
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Integer idRes = new Integer(rs.getInt("pedidos.id"));
					Integer usu = new Integer(rs.getInt("pedidos.usuario"));
					String est = new String(rs.getString("pedidos.estado"));
					EstadoPedido estado;
					switch (est.toLowerCase().replaceAll("\\s","")) {
					case "encurso":
						estado = EstadoPedido.encurso;
						break;
					case "cancelado":
						estado = EstadoPedido.cancelado;
						break;
					case "enviado":
						estado = EstadoPedido.enviado;
						break;
					case "entregado":
						estado = EstadoPedido.entregado;
						break;
					default:
						estado = null;
					}
					String dir = new String(rs.getString("pedidos.dir_entrega"));
					Date fecha = rs.getDate("pedidos.fecha_entrega");
					Integer hora_ini = new Integer(rs.getInt("pedidos.hora_entrega_ini"));
					Integer hora_fin = new Integer(rs.getInt("pedidos.hora_entrega_fin"));
					String telf = new String(rs.getString("pedidos.telefono"));
					Double total = new Double(rs.getDouble("pedidos.total"));
					Boolean activo = new Boolean(rs.getBoolean("pedidos.activo"));
					TPedido pedido = new TPedido(idRes, usu, estado, dir, fecha, hora_ini, hora_fin, telf, total, activo,
							new ArrayList<Integer>());
					ret = pedido;
				}
				con.close();
				return ret;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}

	}

}