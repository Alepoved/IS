package onMarket.integracion.ModuloProductos;

import junit.framework.TestCase;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.integracion.factorias.FactoriaIntegracion;
import onMarket.negocio.ModuloProductos.TProducto;

public class DAOProductosTest extends TestCase{

	
	public void testDaoCreate() {
		
		/*
		 * Insertar un producto que ya existe
		 */
		PrepararBD bd = new PrepararBD();
 		bd.borrarBD();
		bd.insertCat(1, "Artesanal", true);
		bd.insertSubcat(1, "Pan", true, 1);
		bd.insertSubcat(2, "Pasta", true, 1);
		bd.insertProd(2, "Macarrones", true, 2, "Gallo", 3.0, 120, "500", "foto");
		
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOProducto daoProd = factoria.generaDAOProducto();
		TProducto prod = new TProducto("Macarrones", "Gallo");
		prod.setSubcategoria(2);
		
		assertTrue("Esta categoria ya existe", daoProd.create(prod) == null );  
	}
	
	
	public void testDaoUpdate() {
		/* 
		 * Test: reponer un elemento no existente
		 */
		PrepararBD bd = new PrepararBD();
		TProducto pro;
		bd.borrarBD();
		bd.insertCat(1, "Cocina", true);
		bd.insertSubcat(1, "Basicos", true, 1);
		bd.insertProd(1, "huevo", true, 1, "Hacendado", 2.0, 100, "peso", "foto");
		bd.insertProd(2, "leche", true, 1, "Hacendado", 2.0, 100, "peso", "foto");
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOProducto daoPro = factoria.generaDAOProducto();
		pro = daoPro.readById(8);
		assertTrue("Todo ok", pro == null);
	}
	
	
	public void testDaoReadById() {
		
		/* 
		 * Test: Ejecutar un ReadById y no existe el nombre
		 */
		PrepararBD bd = new PrepararBD();
		bd.borrarBD();
		bd.insertCat(1, "Cocina", true);
		bd.insertSubcat(1, "Basicos", true, 1);
		bd.insertProd(1, "huevo", true, 1, "Hacendado", 2.0, 100, "peso", "foto");
		bd.insertProd(2, "leche", true, 1, "Hacendado", 2.0, 100, "peso", "foto");
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOProducto daoPro = factoria.generaDAOProducto();
		TProducto pro = daoPro.readById(3);
		assertTrue("No hay ningun producto", pro == null);
		
		/*
		 * Buscar un producto existente
		 */
		bd.borrarBD();
		bd.insertCat(1, "Artesanal", true);
		bd.insertSubcat(1, "Pan", true, 1);
		bd.insertSubcat(2, "Pasta", true, 1);
		bd.insertProd(2, "Macarrones", true, 2, "Gallo", 3.0, 120, "500", "foto");
	
		TProducto prod = new TProducto(2, "Macarrones", true, 2, "Gallo", 3.0, 120, "500", "foto");
		assertTrue("Esta categoria ya existe", daoPro.readById(2).getNombre().equalsIgnoreCase(prod.getNombre()));  
	}
	

	
	public void testDaoReadByNameAndMarca() {
		/* 
		 * Test: Ejecutar un ReadByNameAndMarca y no existe el nombre
		 */
		PrepararBD bd = new PrepararBD();
		bd.borrarBD();
		bd.insertCat(1, "Cocina", true);
		bd.insertSubcat(1, "Basicos", true, 1);
		bd.insertProd(1, "huevo", true, 1, "Hacendado", 2.0, 100, "peso", "foto");
		bd.insertProd(2, "leche", true, 1, "Hacendado", 2.0, 100, "peso", "foto");
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOProducto daoPro = factoria.generaDAOProducto();
		TProducto pro = daoPro.readByNameAndMarca("patatas", "Lays");
		assertTrue("No hay ningun producto", pro == null);
	}

	
}
