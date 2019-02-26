package onMarket.integracion.ModuloPedidos;


import java.sql.Date;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.integracion.factorias.FactoriaIntegracion;
import onMarket.negocio.ModuloPedidos.BOCesta;
import onMarket.negocio.ModuloPedidos.EstadoPedido;
import onMarket.negocio.ModuloPedidos.TLineaPedido;
import onMarket.negocio.ModuloPedidos.TPedido;

/*
 * Se decide hacer pruebas encadenadas, de tal forma que si un método pasa su test
 * el siguiente puede usar los métodos de DAO probados anteriormente
 * Es decir se prepara la BD una sola vez, para reducir la carga.
 * */
public class DAOPedidosTest extends TestCase {

	public DAOPedidosTest(String testName){
		super(testName); 
	}
	
	public static Test suite(){
	
		return new TestSuite(DAOPedidosTest.class); 
		
	}	
	
	
	public void testDaoCreate() {
		@SuppressWarnings("deprecation")
		Date fecha = new Date(117, 05, 30);
		PrepararBD bd = new PrepararBD();
 		bd.borrarBD();
 		bd.insertCat(1, "carne", true);
		bd.insertSubcat(1, "vacuno", true, 1);
		bd.insertProd(1, "entrecot", true, 1, "super", 15.0, 100, "0.5", "asddsd");
 		
		// crear pedido de una cesta vacía
		BOCesta cesta = new BOCesta();
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOPedidos daoPedidos = factoria.generaDAOPedidos();
		TPedido ped = new TPedido(1, EstadoPedido.encurso, "Universidad", fecha, 12, 20, 
								  "916374567", 20.0, true);
		TLineaPedido linea = new TLineaPedido(1, 1, 1, 15.0);
		cesta = null;
		assertTrue("La cesta no puede ser nula", daoPedidos.create(cesta) == null );
		
		// crear un pedido nulo
		ped = null;
		cesta = new BOCesta();
		cesta.setPedido(ped);
		cesta.agregarLineaAt(0, linea);		
		assertTrue("El pedido no puede ser nulo", daoPedidos.create(cesta) == null );
		
		// crear un pedido sin lineas de pedido
		cesta = new BOCesta();
		ped = new TPedido(1, EstadoPedido.encurso, "Universidad", fecha, 12, 20, "916374567", 20.0, true);
		cesta.setPedido(ped);
		assertTrue("Un pedido debe tener al menos una linea de pedido", daoPedidos.create(cesta) == null );
		
		// crear pedidos correctos
		cesta.agregarLineaAt(0, linea);
		assertTrue("Pedido creado correctamente", daoPedidos.create(cesta) >= 1);
	}

	public void testDaoReadByUser(){
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOPedidos daoPedidos = factoria.generaDAOPedidos();
		Integer usuario = null;
		assertTrue("El id de usuario no puede ser nulo", daoPedidos.readByUser(usuario) == null );
		
		usuario = -1;
		assertTrue("El id de usuario debe ser mayor a 0", daoPedidos.readByUser(usuario) == null );
	    
		usuario = 1;
		assertTrue("Pedidos Leídos correctamente", daoPedidos.readByUser(usuario) != null );
	}
	

	public void testDaoreadById(){
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOPedidos daoPedidos = factoria.generaDAOPedidos();
		Integer usuario = null;
		// Caso de lectura de pedido con id nulo
		Integer id=null;
		TPedido pedido=daoPedidos.readById(id);
		assertTrue("Un pedido no puede tener id Nulo", pedido == null );
		
		// Caso de lectura de pedido con id <=0 
		id=0;
		pedido=daoPedidos.readById(id);
		assertTrue("El id de Pedido debe ser simpre >= que 1", pedido== null );
		
		//caso correcto
		usuario=1;
		ArrayList<TPedido> pedidos=daoPedidos.readByUser(usuario);
		for(int i=0; i<pedidos.size();i++){
		   id=pedidos.get(i).getId_pedido();
		   pedido=daoPedidos.readById(id);
		   assertTrue("Pedido"+i+" Correcto", pedido!= null );
		}
		
	}
	

	public void testDaoReadAllLines(){
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		  
		DAOPedidos daoPedidos = factoria.generaDAOPedidos(); 
		
		TPedido pedido = new TPedido(1, 1, EstadoPedido.encurso, "Calle prueba", new Date(0000), 13, 17, "123456789", 16.0, true, null);
		assertTrue("El pedido tiene que tener lineas", daoPedidos.readAllLines(pedido) == null);
		
		//Pedido con lineas de pedido
		ArrayList<TPedido> pedidos = daoPedidos.readByUser(1);  
		
		for (int i = 0; i < pedidos.size(); i++){			
			assertTrue("No puede haber un pedido sin lineas de pedido", daoPedidos.readAllLines(pedidos.get(i)) != null); 
		}			
		
		//Si el pedido es nulo 
		pedido = null; 
		assertTrue("El pedido no puede ser nulo", daoPedidos.readAllLines(pedido) == null); 
			
	}
	
	
	public void testDaoupdate(){
		@SuppressWarnings("deprecation")
		Date fecha = new Date(117, 05, 30);
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOPedidos daoPedidos = factoria.generaDAOPedidos();
		
		//Actualizar el pedido con id <=0
		TPedido pedido = new TPedido(1, EstadoPedido.enviado, "facultad", fecha, 
									 10, 25, "665989999", 20.0, true);
		pedido.setId_pedido(0);
		Integer res=daoPedidos.update(pedido);
		assertTrue("El Id del Pedido no puede Ser Cero",res == 0);
		
		res=daoPedidos.update(pedido);
		pedido.setId_pedido(-1);
		assertTrue("El Id del Pedido no puede Ser Menor que cero",res == 0);
		
		//Actualizar un pedido nulo
		pedido=null;
		res=daoPedidos.update(pedido);
		assertTrue("El pedido no puede ser nulo", res == null);
		
		// Caso Correcto de actualizaciones:
		int id;
		int usuario=1;
		ArrayList<TPedido> pedidos=daoPedidos.readByUser(usuario);
		for(int i=0; i<pedidos.size();i++){
		   id=pedidos.get(i).getId_pedido();
		   TPedido pedidoNuevo = new TPedido(id,1, EstadoPedido.enviado, "otro "+i, fecha, 
					10+i, 25+i, "665989999", 20.0+i, true,new ArrayList<Integer>());
		   res=daoPedidos.update(pedidoNuevo);
		   assertTrue("El id de Pedido es simpre >= que 1", res != 0 );
	    }
     }
	
	public void testDaodelete(){
		Date d = new Date(0); 
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		ArrayList<Integer> linea = new ArrayList<>(); 
		 
		TPedido pedido = new TPedido(new Integer(31), new Integer(1), EstadoPedido.enviado, "Calle esto es una prueba",
				d, new Integer(3), new Integer(15), "676318957", 15.0, true, linea); 
		DAOPedidos daoPedidos = factoria.generaDAOPedidos(); 
		
		//Si el pedido a eliminar no tiene lineas de pedido 
		assertTrue("No se puede eliminar un pedido sin  lineas de pedido", daoPedidos.delete(pedido) == null);
				
		//Si el pedido a eliminar es nulo
		pedido = null; 		
		assertTrue("No puede haber un pedido nulo", daoPedidos.delete(pedido) == null);
		
		//Se elimina correctamente
		ArrayList<TPedido> ped = daoPedidos.readByUser(1);
		
		for(int i = 0; i < ped.size(); i++){
			assertTrue("No puede haber un pedido nulo", daoPedidos.delete(ped.get(i)) != null);
		}
		
		// finalmemte dejamos la BD vacía
		PrepararBD bd = new PrepararBD();
 		bd.borrarBD();
	}
	
}

