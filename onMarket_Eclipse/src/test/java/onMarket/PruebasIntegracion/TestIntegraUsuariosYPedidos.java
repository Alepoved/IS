package onMarket.PruebasIntegracion;

import java.sql.Date;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.negocio.ModuloPedidos.AS_PedidoImp;
import onMarket.negocio.ModuloPedidos.BOCesta;
import onMarket.negocio.ModuloPedidos.BOPago;
import onMarket.negocio.ModuloPedidos.EstadoPago;
import onMarket.negocio.ModuloPedidos.EstadoPedido;
import onMarket.negocio.ModuloPedidos.TLineaPedido;
import onMarket.negocio.ModuloPedidos.TPedido;
import onMarket.negocio.ModuloUsuarios.AS_UsuarioImp;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.negocio.Seguridad.AS_SeguridadImp;
/*
 * Dado de que En Las pruebas unitarias de cada módulo se prueban todos los casos
 * en los que pueden fallar y No Fallar los métodos
 * Unicamente hemos desarrollado pruebas del sistema sobre casos
 * hipotéticamente correctos, aún así si algún método interno fallase
 * el test es capaz de reconocerlo y no lo Pasa
 * */
public class TestIntegraUsuariosYPedidos extends TestCase{
	public TestIntegraUsuariosYPedidos(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TestIntegraUsuariosYPedidos.class);
	}
	
	public void testConfirmarPedidoConUsuarioReal(){
		@SuppressWarnings("deprecation")
		Date fecha = new Date(117, 5, 25);
		AS_PedidoImp asPed = new AS_PedidoImp();
		PrepararBD prepara = new PrepararBD();
		prepara.borrarBD();
		prepara.insertCat(1, "Horno", true);
		prepara.insertCat(2, "Lacteos", true);
		prepara.insertSubcat(1, "Panes", true, 1);
		prepara.insertSubcat(2, "Leches", true, 2);
		prepara.insertProd(1, "pan", true, 1, "nestle", 1.0, 100, "0.1", "sinFoto");
		prepara.insertProd(2, "leche", true, 2, "central lechera asturiana", 2.0, 100,
						   "0.5", "sinFoto");
		Integer id1 = prepara.getIdProd("pan", "nestle");
		Integer id2 = prepara.getIdProd("leche", "central lechera asturiana");
		
		//Damos de alta un usuario
		Integer resulFinal=1;
		TUsuario user = new TUsuario(676318957, false, true, new Date(15101996), "paquito@ucm.es", "paquito", "Paco",
				"Sanchez", "calle ninguna", "foto", "1234567812345678", "calle ninguna"); 
		
		AS_SeguridadImp asSeg = new AS_SeguridadImp();
		AS_UsuarioImp asUser = new AS_UsuarioImp();
		Integer alta=asSeg.altaUsuario(user);	
		if(alta == null) 
			resulFinal=null;

		Integer idUser= asUser.obtenUsuario("paquito@ucm.es").getId();
		if(idUser == null) 
			resulFinal=null;
		user.setId(idUser);
		
		user = asSeg.Login(user.getMail(), "paquito");
		if(user == null) 
			resulFinal=null;
		
		// Agragamos Pedido y lineas a la Cesta con los productos creados a mano
		BOCesta cesta = new BOCesta();
		cesta.setPedido(new TPedido(user.getId(), EstadoPedido.encurso, "Universidad", fecha, 12, 20, "916374567", 20.0, true));
		cesta.agregarLineaAt(0, new TLineaPedido(1, id1, 2, 2.0));
		cesta.agregarLineaAt(1, new TLineaPedido(1, id2, 6, 12.0));
		
		//Realizamos el pago el pago
		BOPago pago = null;
		pago = new BOPago("Manolo", "1234567891234567", "090", 11, 2018, 50.0);
		if(asPed.pagoCorrecto(pago) !=EstadoPago.CORRECTO)
			resulFinal=null;
		
		//Comprobamos La actualización del Stock con las cantidades Solicitadas por el Pedido	
		if(!asPed.confirmaStock(cesta))
			resulFinal=null;
		
		// Agregamos el pedido al  sistema y comprobamos
		  Integer pedRealizado=asPed.confirmaPedido(cesta);
		  if( pedRealizado == null || pedRealizado <=0)
			  resulFinal=null;
		  assertTrue("El pedido del Usuario Se Realiza Correctamente",resulFinal!=null);
		}
	
	/*
	 * Encadenamiento de pruebas:
	 * Falla cuando el código en raras ocasiones no se ejecuta en forma secuencial
	 * después de ello, si se hace un reRun las veces que quiera funciona bien 
	 * */
	public void testObtenerPedidosYLineasDeUsuarioReal(){
		//Usamos el usuario creado  y pedidos por el metodo anterior
		AS_PedidoImp asPed = new AS_PedidoImp();
		PrepararBD prepara = new PrepararBD();
		Integer resulFinal=1;
		AS_SeguridadImp asSeg = new AS_SeguridadImp();
		AS_UsuarioImp asUser = new AS_UsuarioImp();
		
		//realizamos el login
		Integer idUser= asUser.obtenUsuario("paquito@ucm.es").getId();
		if(idUser == null) 
			resulFinal=null;
		
		TUsuario user = asSeg.Login("paquito@ucm.es","paquito");
		if(user == null) 
			resulFinal=null;
		
		//obtenemos los pedidos de un usuario
		ArrayList<TPedido> listaPed = asPed.obtenerPedidos(user); 	
		if(user == null) 
			resulFinal=null;
		else{
			// pedimos los detalles de cada pedido (lineas de pedido)
			ArrayList<TLineaPedido> actual;
			for (int i = 0; i < listaPed.size(); i++){			 
				actual=asPed.obtenerLineas(listaPed.get(i));
				if(actual == null || actual.size()==0)
					resulFinal=null;
			};
		}
		assertTrue("Se Han leido los pedidos de un usuario y el detalle de cada uno Correctamente ",
				    resulFinal !=null);
		prepara.borrarBD();
	}
	
}
