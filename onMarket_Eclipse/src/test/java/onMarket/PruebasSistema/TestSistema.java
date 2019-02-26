package onMarket.PruebasSistema;

import java.sql.Date;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.negocio.ModuloCategorias.AS_CategoriaImp;
import onMarket.negocio.ModuloCategorias.TCategoria;
import onMarket.negocio.ModuloPedidos.AS_PedidoImp;
import onMarket.negocio.ModuloPedidos.BOCesta;
import onMarket.negocio.ModuloPedidos.BOPago;
import onMarket.negocio.ModuloPedidos.EstadoPago;
import onMarket.negocio.ModuloPedidos.EstadoPedido;
import onMarket.negocio.ModuloPedidos.TLineaPedido;
import onMarket.negocio.ModuloPedidos.TPedido;
import onMarket.negocio.ModuloProductos.AS_ProductoImp;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.negocio.ModuloSubcategorias.AS_SubcategoriaImp;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
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

public class TestSistema extends TestCase{
	public TestSistema(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TestSistema.class);
	}
	
	public void testSistema(){
		PrepararBD prepara = new PrepararBD();
		prepara.borrarBD();
		@SuppressWarnings("deprecation")
		Date fecha = new Date(117, 5, 23);
		AS_PedidoImp asPed = new AS_PedidoImp();
		AS_ProductoImp asProd = new AS_ProductoImp();
		AS_UsuarioImp asUsu = new AS_UsuarioImp();
		AS_CategoriaImp asCat = new AS_CategoriaImp();
		AS_SubcategoriaImp asSub = new AS_SubcategoriaImp();
		AS_SeguridadImp asSeg = new AS_SeguridadImp();
		
	// Primero un Admin que interactúa con productos
		
		Integer resulFinal=1;
		
		//login de usuario
		
		TUsuario admin=asSeg.Login("admin@onmarket.es", "admin");
		if (admin == null)
			resulFinal=null;	
		else{
			// Interaccion con Categorías
			
			Integer idCat = asCat.altaCategoria(new TCategoria(1, "carne", true));
			if (idCat == null || idCat <=0 )
				resulFinal=null;
			Integer editada= asCat.editaCategoria(new TCategoria(idCat, "pollo", true));
			if (editada == null || editada <=0 )
				resulFinal=null;
			Integer eliminada= asCat.eliminaCategoria("pollo");
			if (eliminada == null || eliminada <=0 )
				resulFinal=null;
			Integer idCat2 = asCat.altaCategoria(new TCategoria(1, "carne", true));
			if (idCat2 == null || idCat2 <=0 )
				resulFinal=null;
			Integer idCat3 = asCat.altaCategoria(new TCategoria(2, "horno", true));
			if (idCat3 == null || idCat3 <=0 )
				resulFinal=null;
			
			//Interaccion con subcategorías
			
			Integer idSub = asSub.altaSubcategoria(new TSubcategoria(1, "vacuno", true, idCat2));
			if (idSub == null || idSub <=0 )
				resulFinal=null;
			Integer idSub2 = asSub.altaSubcategoria(new TSubcategoria(2,"pescado",true,idCat3));
			if (idSub2 == null || idSub2 <=0 )
				resulFinal=null;
			Integer editadaSub= asSub.editaSubcategoria(new TSubcategoria(idSub2,"pollo",true,idCat3));  
			if (editadaSub == null || editadaSub <=0 )
				resulFinal=null;
			Integer editadaSub2= asSub.editaSubcategoria(new TSubcategoria(idSub2,"pollo",true,idCat2));  
			if (editadaSub2 == null || editadaSub2 <=0 )
				resulFinal=null;
			Integer eliminadaSub= asSub.eliminaSubcategoria("pollo");  
			if (eliminadaSub == null || eliminadaSub <=0 )
				resulFinal=null;
			Integer idSub3 = asSub.altaSubcategoria(new TSubcategoria(2,"pescado",true,idCat2));
			if (idSub3 == null || idSub3 <=0 )
				resulFinal=null;
			
			//Interaccion con Productos
			
			Integer idProd = asProd.altaProducto(new TProducto(1, "entrecot", true, idSub,
												 "super", 15.0, 100, "0.5", "foto1"));
			if (idProd == null || idProd <=0 )
				resulFinal=null;
			Integer idProd2 = asProd.altaProducto(new TProducto(2, "lomo", true, idSub,
					 							  "cerdi", 5.0, 100, "0.9", "foto2"));
			if (idProd2 == null || idProd2 <=0 )
				resulFinal=null;
			Integer idProd3 = asProd.altaProducto(new TProducto(3, "Chuletas", true, idSub3,
					  							  "ternerita", 8.0, 100, "0.6", "foto3"));
			if (idProd3 == null || idProd3 <=0 )
				resulFinal=null;
			Integer editado = asProd.modificarProducto(new TProducto(idProd3, "Chuletas",true,
														idSub,"ternerita", 8.0, 100, "0.6", "foto3"));
			if (editado == null || editado <=0 )
				resulFinal=null;
			Integer eliminado = asProd.bajaProducto(idProd3);
			if (eliminado == null || eliminado <= 0)
				resulFinal = null;
			Integer idProd4 = asProd.altaProducto(new TProducto(idProd3, "Chuletas", true, idSub,
					  							  "ternerita", 8.0, 100, "0.6", "foto3"));	
			if (idProd4 == null || idProd4 <= 0)
				resulFinal = null;
			
			
			//Ahora un usuario se registra
			
			TUsuario user = new TUsuario(676318957, false, true, new Date(15101996), "paquito@ucm.es", "prueba", "Paco",
					"Sanchez", "calle ninguna", "foto", "1234567812345678", "calle ninguna");
			Integer alta = asSeg.altaUsuario(user);
			if (alta == null || alta <= 0)
				resulFinal = null;
			Integer idUser= asUsu.obtenUsuario("paquito@ucm.es").getId();
			if(idUser == null) 
				resulFinal=null;
			user.setId(idUser);
			user = asSeg.Login(user.getMail(), "prueba");
			if (user == null)
				resulFinal = null;
			
			//Edicion de  usuarios
			
			Integer editadoUsu = asUsu.editaUsuario(new TUsuario(user.getId(), 678436548, false, true, user.getFecha_nac(), user.getMail(), "prueba2", 
					user.getNombre(), user.getApellido(), "Calle cambiada", user.getFoto(), user.getN_tarjeta(), user.getDireccionEntrega()));
			if (editadoUsu == null || editadoUsu <= 0)
				resulFinal = null;
			Integer eliminadoUsu = asUsu.eliminaUsuario(asUsu.obtenUsuario(user.getMail()));
			if (eliminadoUsu == null || eliminadoUsu <= 0)
				resulFinal = null;
			TUsuario usu2 = new TUsuario(657123456, false, true, new Date(16102006), "usu2@ucm.es", "prueba", "Usuario",
					"Dos", "calle ninguna", "foto", "1234123412341234", "calle ninguna");
			alta = asSeg.altaUsuario(usu2);
			if (alta == null || alta <= 0)
				resulFinal = null;
			idUser= asUsu.obtenUsuario("usu2@ucm.es").getId();
			usu2.setId(idUser);
			if(idUser == null) 
				resulFinal=null;
			usu2 = asSeg.Login(usu2.getMail(), "prueba");
			if (usu2 == null)
				resulFinal = null;
			
			//Interaccion con Pedidos
			//Es necesario crear una cesta con su pedido y lineas de pedido, y un pago, a mano porque en pruebas no hay interaccion con usuario
			
			BOCesta cesta = new BOCesta();
			cesta.setPedido(new TPedido(usu2.getId(), EstadoPedido.encurso, "Calle de prueba", fecha, 12, 20, "916374567", 20.0, true));
			cesta.agregarLineaAt(0, new TLineaPedido(1, idProd, 3, 45.0));
			cesta.agregarLineaAt(1, new TLineaPedido(1, idProd2, 1, 5.0));
			
			BOPago pago = null;
			pago = new BOPago("Manolo", "1234567891234567", "090", 11, 2018, 50.0);
			int correcto = asPed.pagoCorrecto(pago);
			if (correcto != EstadoPago.CORRECTO)
				resulFinal = null;
			boolean reducido = asPed.confirmaStock(cesta);
			if (!reducido)
				resulFinal = null;
			Integer pedido = asPed.confirmaPedido(cesta);
			if (pedido == null || pedido <= 0)
				resulFinal = null;
			ArrayList<TPedido> lista = asPed.obtenerPedidos(usu2);
			if (lista == null || lista.size() <= 0)
				resulFinal = null;
			ArrayList<TLineaPedido> listaLineas = null;
			for (int i = 0; i < lista.size(); i++){
				listaLineas = asPed.obtenerLineas(lista.get(i));
				if (listaLineas == null || listaLineas.size() <= 0)
					resulFinal = null;
			}
			ArrayList<String> listaProductos = null;
			for (int i = 0; i < lista.size(); i++){
				listaProductos = asPed.obtenerProductos(lista.get(i));
				if (listaProductos == null || listaProductos.size() <= 0)
					resulFinal = null;
			}
			
	   }
		assertTrue("Toda La interaccón se Hace Correctamente",resulFinal !=null); 
		prepara.borrarBD();
	}
}
