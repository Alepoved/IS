package onMarket.negocio.ModuloPedidos;


import java.sql.Date;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.negocio.ModuloUsuarios.TUsuario;

public class AS_PedidosTest extends TestCase {

	public AS_PedidosTest(String testName) {

		super(testName);

	}

	public static Test suite() {
		return new TestSuite(AS_PedidosTest.class);
	}

	public void testPagoCorrecto() {
		BOPago pago = new BOPago("Pepito Perez", "0000000000000000", "123", new Integer(12), new Integer(2018), 17.50); 
		AS_PedidoImp as = new AS_PedidoImp();
		
		//Pago correcto
		assertTrue("No hay estado de pago", as.pagoCorrecto(pago) == EstadoPago.CORRECTO);
		
		//Si el pago es nulo
		pago = null; 
		assertTrue("El pago no puede ser nulo", as.pagoCorrecto(pago) == EstadoPago.ERROR_PAGO);
		
		//si falla el titular
		pago = new BOPago("Pepito Perez;;;Lopez", "0000000000000000", "123", new Integer(12), new Integer(2018), 17.50);
		assertTrue("El pago no puede ser nulo", as.pagoCorrecto(pago) == EstadoPago.ERROR_TITULAR);
		
		//si falla el N tarjeta
		pago = new BOPago("Pepito Perez Lopez", "12", "123", new Integer(12), new Integer(2018), 17.50);
		assertTrue("El pago no puede ser nulo", as.pagoCorrecto(pago) == EstadoPago.ERROR_TARJETA);
		
		//Si falla el cvv
		pago = new BOPago("Pepito Perez Lopez", "0000000000000000", "1", new Integer(12), new Integer(2018), 17.50);
		assertTrue("El pago no puede ser nulo", as.pagoCorrecto(pago) == EstadoPago.ERROR_CVV);
		
		//si falla el mes de caducidad
		pago = new BOPago("Pepito Perez Lopez", "0000000000000000", "123", new Integer(255), new Integer(2018), 17.50);
		assertTrue("El pago no puede ser nulo", as.pagoCorrecto(pago) == EstadoPago.ERROR_CADUCIDAD);
		
		//si falla el año de caducidad
		pago = new BOPago("Pepito Perez Lopez", "0000000000000000", "123", new Integer(2), new Integer(2000), 17.50);
		assertTrue("El pago no puede ser nulo", as.pagoCorrecto(pago) == EstadoPago.ERROR_CADUCIDAD);
		
		// si el importe no es válido
		pago = new BOPago("Pepito Perez Lopez", "0000000000000000", "123", new Integer(10), new Integer(2018), 0.0);
		assertTrue("El pago no puede ser nulo", as.pagoCorrecto(pago) == EstadoPago.ERROR_IMPORTE);
	}

	public void testConfirmaStock() {
		PrepararBD prepara = new PrepararBD();
		prepara.borrarBD();
		prepara.insertCat(1, "carne", true);
		prepara.insertSubcat(1, "vacuno", true, 1);
		prepara.insertProd(1, "entrecot", true, 1, "super", 15.0, 100, "0.5", "asddsd");
		prepara.insertProd(2, "filetes", true, 1, "provenzal", 5.0,50, "50g", "mifoto");
		prepara.insertUsuario("Paco", "paquito@ucm.es", "prueba", false, "Sanchez", 
							  "calle ninguna", 676318957, new Date(15101996), "foto", 
							  "1234567812345678", "calle ninguna", true);
		Integer idUsuario = prepara.getIdUsuario("paquito@ucm.es");
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(117, 05, 30);
		AS_PedidoImp as = new AS_PedidoImp();
		TPedido ped = null;
									//     ped|prod|cantidad|precio
		TLineaPedido linea = new TLineaPedido(1, 1, 30, 5.0*30);
		TLineaPedido linea2 = new TLineaPedido(1, 2, 40, 5.0*40);
		
		ped = new TPedido(idUsuario, EstadoPedido.encurso, "Universidad", fecha, 12, 20,
				  "916374567", 20.0, true);
		// Actualizar el Stock con Cesta nula
		BOCesta cesta=null;
		assertTrue("No se puede actualizar Stock de cesta Vacía", !as.confirmaStock(cesta));
		
		//Actualizar el Stock con algún producto null 
		cesta = new BOCesta();
		cesta.setPedido(ped);
		linea2.setId_producto(null);
		cesta.getLineas().add(0,linea);
		cesta.getLineas().add(1,linea2);
		assertTrue("No se puede Actualizar Stock de un producto Nulo", !as.confirmaStock(cesta));
		
		//Actualizar Stock Cundo se pide más productos(85) de los que existen(50)
		boolean stockOriginal=true;
		linea2.setId_producto(2);;
		linea2.setCantidad(85);
		  // aseguramos que las los productos quedan con su stock original en la bd
		if(prepara.getStockProd(1) != 100 || prepara.getStockProd(2) != 50 ) 
			stockOriginal=false;
			
		assertTrue("No Hay Suficientes Productos en la BD",!as.confirmaStock(cesta) && stockOriginal);
		
		
		// Actualizar el Stock de una cesta correcta
		Boolean actualizacionCorrecta=true;
		linea2.setCantidad(40);
		   //Aseguramos que El stock de los productos se actualiza correctamente
		Boolean res=as.confirmaStock(cesta);
		if(prepara.getStockProd(1) != 100-linea.getCantidad() || 
		   prepara.getStockProd(2) != 50-linea2.getCantidad() ) 
				actualizacionCorrecta=false;
		assertTrue("Stock correcto", res && actualizacionCorrecta );
	}

	/*
	 * trabajamos con el mismo pedido y lineas comprobados en el método anterior
	 * que nó accede a los pedidos de la BD
	 * */
	public void testConfirmaPedido() {
		PrepararBD prepara = new PrepararBD();
		@SuppressWarnings("deprecation")
		Date fecha = new Date(117, 05, 30);
		AS_PedidoImp as = new AS_PedidoImp();
		TPedido ped = null;
		TLineaPedido linea = new TLineaPedido(1, 1, 30,15.0*30);
		TLineaPedido linea2 = new TLineaPedido(1, 2, 40, 5.0*40);
		
		// confirmar pedido con cesta nula
		BOCesta cesta=null;
		assertTrue("La cesta no puede ser Nula", as.confirmaPedido(cesta)==null);
		
		// confirmar pedido con pedido nulo
		cesta= new BOCesta();
		cesta.setPedido(ped);
		assertTrue("El pedido no puede ser nulo", as.confirmaPedido(cesta) == null);
		
		// confirmar un pedido sin lineas de pedido
		Integer idUsuario = prepara.getIdUsuario("paquito@ucm.es");
		ped = new TPedido(idUsuario, EstadoPedido.encurso, "Universidad", fecha, 12, 20, 
						  "916374567", 20.0, true);
		cesta.setPedido(ped);
		assertTrue("El pedido debe tener alguna linea de pedido", as.confirmaPedido(cesta) == null);
		
		// confirmar un pedido con alguno de sus campos no validos
		cesta.agregarLineaAt(0, linea);
		cesta.agregarLineaAt(1, linea2);
		ped.setDir_entrega(null);
		assertTrue("El pedido debe tener una direccion de entrega no nula", as.confirmaPedido(cesta) == null);
		
		cesta.getPedido().setDir_entrega("Universidad");
		cesta.getPedido().setEstado(null);
		assertTrue("El estado debe ser correcto", as.confirmaPedido(cesta) == null);
		
		cesta.getPedido().setEstado(EstadoPedido.encurso);
		cesta.getPedido().setFecha_entrega(null);
		assertTrue("La fecha de entrega debe ser valida y distinta de nula", as.confirmaPedido(cesta) == null);
		
		cesta.getPedido().setFecha_entrega(fecha);
		cesta.getPedido().setHorario_ini(8);
		assertTrue("La hora minima de entrega debe ser mayor igual que 9 y menor igual que 21", as.confirmaPedido(cesta) == null);
		
		cesta.getPedido().setHorario_ini(12);
		cesta.getPedido().setHorario_fin(24);
		assertTrue("La hora maxima de entrega debe ser mayor igual que 9 y menor igual que 21", as.confirmaPedido(cesta) == null);
		
		cesta.getPedido().setHorario_fin(20);
		cesta.getPedido().setId_usuario(-1);
		assertTrue("El id de usuario debe ser valido", as.confirmaPedido(cesta) == null);
		
		cesta.getPedido().setId_usuario(idUsuario);
		cesta.getPedido().setTelefono(null);
		assertTrue("El telefono no puede ser nulo", as.confirmaPedido(cesta) == null);
		
		cesta.getPedido().setTelefono("919388392298382");
		assertTrue("El telefono debe tener 9 digitos", as.confirmaPedido(cesta) == null);
		
		cesta.getPedido().setTelefono("916374567");
		cesta.getPedido().setTotal_pagar(0.0);
		assertTrue("El importe del pedido debe ser mayor que 0", as.confirmaPedido(cesta) == null);
		
		// Confirmar un pedido correcto
		cesta.getPedido().setTotal_pagar(20.0);
		assertTrue("El pedido se ha creado correctamente", as.confirmaPedido(cesta) >= 0);
	}

	public void testObtenerPedidos() {
		PrepararBD prepara = new PrepararBD();
		AS_PedidoImp as = new AS_PedidoImp();
		// Pedidos de un usuario nulo
		TUsuario usuario = null;
		assertTrue("El usuario no puede ser nulo", as.obtenerPedidos(usuario) == null);

		//Pedidos de un Usuario con id nó válido
		usuario = new TUsuario(676318957,false,true,new Date(15101996),"paquito@ucm.es",
				   "prueba","Paco","Sanchez", "calle ninguna",
				   "foto","1234567812345678","calle ninguna");
		usuario.setId(0);
		assertTrue("El id del usuario debe ser coherente", as.obtenerPedidos(usuario) == null);
		
		//Pedidos de un usuario inactivo
		usuario.setId(prepara.getIdUsuario("paquito@ucm.es"));
		usuario.setActivo(false);
		assertTrue("El usuario debe Estar Activo", as.obtenerPedidos(usuario) == null);
		
		//Pedidos de un usuario correcto
		//trabaja con el id de usuario creado en el 1er método
		usuario.setActivo(true); 
		assertTrue("Lista de pedidos obtenida satisfactoriamente", as.obtenerPedidos(usuario) != null);
	}


	public void testObtenerLineas() {
		PrepararBD prepara = new PrepararBD();
		Date d = new Date(0);  
		prepara.borrarBD();
		prepara.insertCat(1, "carne", true);
		prepara.insertSubcat(1, "vacuno", true, 1);
		prepara.insertProd(1, "entrecot", true, 1, "super", 15.0, 100, "0.5", "asddsd");
		prepara.insertProd(2, "filetes", true, 1, "provenzal", 5.0,50, "50g", "mifoto");
		
		prepara.insertUsuario("Paco", "paquito@ucm.es", "12345", false, "Sanchez", "Calle ninguna", 123456789, 
				new Date(15101996), "foto", "1234567812345678" , "calle ninguna", true); 
				
		TUsuario usuario = new TUsuario(123456789,false,true,new Date(15101996),"paquito@ucm.es",
				   "prueba","Paco","Sanchez", "calle ninguna",
				   "foto","1234567812345678","calle ninguna");
		usuario.setId(prepara.getIdUsuario("paquito@ucm.es"));
		
		AS_PedidoImp as = new AS_PedidoImp();		
		
		//Si el pedido es nulo
		TPedido pedido = null; 
		assertTrue("No puede haber un pedido nulo", as.obtenerLineas(pedido) == null);
		
		//Si el pedido no tiene lineas de pedido 
		pedido= new TPedido(usuario.getId(), EstadoPedido.encurso, "Universidad", d, 12, 20, 
				  "916374567", 20.0, true);
		pedido.setId_pedido(1);
		assertTrue("No puede haber un pedido sin lineas de pedido", as.obtenerLineas(pedido) == null);
		
		ArrayList<TPedido> pedidos = as.obtenerPedidos(usuario);
		
		//Si el pedido no existe al menos con el mismo usuario
		TPedido actual=null;
		for(int i=0; i<pedidos.size();i++){
			 actual=pedidos.get(i);
		}
		//actual.setId_pedido(pedido.getId_pedido()+1);
		assertTrue("El pedido no existe en la bd", as.obtenerLineas(actual) == null);
		
		//Si el id del pedido es nulo	
		pedido.setId_pedido(null); 
		assertTrue("No puede haber un pedido con id nulo", as.obtenerLineas(pedido) == null);		
		
		TLineaPedido l1 = new TLineaPedido(0, pedido.getId_pedido(), 1, 10, 50.0); 
		TLineaPedido l2 = new TLineaPedido(1, pedido.getId_pedido(), 2, 3, 60.0); 
		
		pedido.addLineas_pedidoAt(0, l1.getId_linea()); 
		pedido.addLineas_pedidoAt(1, l2.getId_linea()); 
		
		BOCesta cesta1 = new BOCesta(pedido); 
		cesta1.agregarLineaAt(0, l1);
		cesta1.agregarLineaAt(1, l2); 
		as.confirmaPedido(cesta1); 
		
		pedidos = as.obtenerPedidos(usuario);

		
		//si el pedido esta inactivo 
		TPedido aux = new TPedido(usuario.getId(), EstadoPedido.cancelado, "fuera",d, 12, 20, 
				  "916374444", 20.0, false);
		Integer pedidoReferenciado=pedidos.get(pedidos.size()-1).getId_pedido() + 1;
		TLineaPedido linea = new TLineaPedido(pedidoReferenciado, 1, 30,15.0*30);
		TLineaPedido linea2 = new TLineaPedido(pedidoReferenciado, 2, 40, 5.0*40);
		BOCesta cesta=new BOCesta();
		cesta.setPedido(aux);
		cesta.agregarLineaAt(0,linea);
		cesta.agregarLineaAt(1,linea2);
		as.confirmaPedido(cesta);
		assertTrue("No se pueden obtener las lineas de un pedido inactivo", as.obtenerLineas(aux) == null);
		
		//obtener las lineas de un pedido correcto
		aux.setActivo(true); 
		for (int i = 0; i < pedidos.size(); i++){
			for (int j = 0; j < pedidos.get(i).getLineas_pedidoSize(); j++){
				assertTrue("Lineas obtenidas correctamente", pedidos.get(i).getLinea_pedidos_iesima(j) != null && pedidos.get(i).getLineas_pedidoSize() > 0);
		
			}
		}

	}
	
	public void testObtenerProductos() {
		PrepararBD prepara = new PrepararBD();
		Date d = new Date(0);  
		TUsuario usuario = new TUsuario(676318957,false,true,d,"paquito@ucm.es",
				   "prueba","Paco","Sanchez", "calle ninguna",
				   "foto","1234567812345678","calle ninguna");
		usuario.setId(prepara.getIdUsuario("paquito@ucm.es"));
		AS_PedidoImp as = new AS_PedidoImp();
		ArrayList<TPedido> pedidos = as.obtenerPedidos(usuario);
		
		// leer productos de pedido correcto 
		//y de pedido inactivo (creado en el metodo anterior)
		TPedido actual=null;
		for(int i=0; i<pedidos.size();i++){
			actual=pedidos.get(i);
			ArrayList<String> res=as.obtenerProductos(pedidos.get(i));
			if(pedidos.get(i).getActivo())
				assertTrue("Leyendo productos de un pedido correcto", res!=null);
			else
				assertTrue("Leyendo productos de un pedido inactivo ", res==null);
			
		}
		
		//Leer productos de un pedido que no existe al menos con el mismo usuario
		actual.setId_pedido(actual.getId_pedido()+1);
		assertTrue("No se puede leer productos de un pedido inexistente",
					as.obtenerProductos(actual) == null);
		actual.setId_pedido(actual.getId_pedido()-1);
		
		//Leer productos de un pedido nulo
		TPedido pedidoCorrecto=pedidos.get(0);
		pedidoCorrecto=null;
		assertTrue("No se puede leer productos de un pedido nulo",
				    as.obtenerProductos(pedidoCorrecto) == null);
		
		//Leer productos de un pedido con Id nulo
		pedidoCorrecto=pedidos.get(0);
		pedidoCorrecto.setId_pedido(null);
		assertTrue("no se puede leer productos de un pedido con id Nulo",
					as.obtenerProductos(actual) == null);
	    
		prepara.borrarBD();
	}

}

