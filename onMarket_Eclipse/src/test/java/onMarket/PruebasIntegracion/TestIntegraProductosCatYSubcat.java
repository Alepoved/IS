package onMarket.PruebasIntegracion;

import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.negocio.ModuloCategorias.AS_CategoriaImp;
import onMarket.negocio.ModuloProductos.AS_ProductoImp;
import onMarket.negocio.ModuloSubcategorias.AS_SubcategoriaImp;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
import junit.framework.TestCase;
/*
 * Dado de que En Las pruebas unitarias de cada módulo se prueban todos los casos
 * en los que pueden fallar y No Fallar los métodos
 * Unicamente hemos desarrollado pruebas del sistema sobre casos
 * hipotéticamente correctos, aún así si algún método interno fallase
 * el test es capaz de reconocerlo y no lo Pasa
 * */
public class TestIntegraProductosCatYSubcat extends TestCase{
	
	public void testBorradoItems(){
		PrepararBD preparar = new PrepararBD();
		AS_ProductoImp asP = new AS_ProductoImp();
		AS_SubcategoriaImp asSub= new AS_SubcategoriaImp();
		AS_CategoriaImp asCat= new AS_CategoriaImp();
		
		/*
		* Test: Borramos una subcategoria con productos  en ella
		*/

		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		preparar.insertProd(1, "Espaguetis", true, 2, "Gallo", 1.70, 35, "1kg", "foto");
		preparar.insertProd(2, "Macarrones", true, 2, "Gallo", 1.40, 35, "1kg", "foto");
		preparar.insertProd(3, "Raviolis", true, 2, "Gallo", 1.70, 35, "1kg", "foto");
		assertEquals("Borramos una subcategoria con productos en ella: la subcategoria "
				+ "2 tiene 3 productos", asP.obtenProductos(2).size(), 3);
		asSub.eliminaSubcategoria("Pasta");
		assertEquals("Borramos una subcategoria con productos en ella: la subcategoria "
				+ "2 tiene 0 productos dentro", asP.obtenProductos(2).size(), 0);
		assertEquals("Borramos una subcategoria con productos dentro: la categoria 1 "
				+ "solo tiene 1 subcategoria en ella", asSub.obtenSubcategorias(1).size(), 1);
		
		/*
		* Test: Borramos una categoria con subcategorias y productos en ella
		*/

		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertCat(2, "El horno", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		preparar.insertProd(1, "Espaguetis", true, 2, "Gallo", 1.70, 35, "1kg", "foto");
		preparar.insertProd(2, "Macarrones", true, 2, "Gallo", 1.40, 35, "1kg", "foto");
		preparar.insertProd(3, "Raviolis", true, 2, "Gallo", 1.70, 35, "1kg", "foto");
		assertEquals("Borramos una categoria con subcategorias y productos en ella: "
				+ "hay 2 categorias", asCat.obtenCategorias().size(), 2);
		assertEquals("Borramos una categoria con subcategorias y productos en ella: "
				+ "1 tiene 2 subcategorias", asSub.obtenSubcategorias(1).size(), 2);
		asCat.eliminaCategoria("Artesanal");
		assertEquals("Borramos una subcategoria con productos dentro: la subcategoria "
				+ "hay solo 1 categoria", asCat.obtenCategorias().size(), 1);
		assertEquals("Borramos una subcategoria con productos dentro: la subcategoria "
				+ "subcategoria 1 de 1 tiene 0 productos", asP.obtenProductos(1).size(), 0);
		assertEquals("Borramos una subcategoria con productos dentro: la subcategoria "
				+ "subcategoria 2 de 1 tiene 0 productos", asP.obtenProductos(2).size(), 0);
	}
	
	public void testUpdateItems(){
		
		PrepararBD preparar = new PrepararBD();
		AS_SubcategoriaImp asSub= new AS_SubcategoriaImp();
		
		/*
		* Test: Cambiamos una subcategoria de categoria a una que este activa
		*/
		
		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertCat(2, "Congelados", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		preparar.insertSubcat(3, "Platos preparados", true, 2);
		preparar.insertProd(1, "Espaguetis", true, 2, "Gallo", 1.70, 45, "1kg", "foto");
		preparar.insertProd(2, "Macarrones", true, 2, "Gallo", 1.40, 95, "1kg", "foto");
		preparar.insertProd(3, "Raviolis", true, 2, "Gallo", 1.70, 105, "1kg", "foto");
		preparar.insertProd(4, "Tallarines", true, 2, "Gallo", 1.50, 125, "1kg", "foto");
		preparar.insertProd(5, "Lasaña", true, 3, "Gallo", 1.30, 85, "1kg", "foto");
		preparar.insertProd(6, "Canelones", true, 3, "Gallo", 1.70, 40, "1kg", "foto");
		assertEquals("Cambiamos una subcategoria de categoria a una que este activa: "
				+ "categoria 1 tiene 2 subcategorias", asSub.obtenSubcategorias(1).size(), 2);
		assertEquals("Cambiamos una subcategoria de categoria a una que este activa: "
				+ "categoria 2 tiene 1 subcategorias", asSub.obtenSubcategorias(2).size(), 1);
		TSubcategoria catEdita = new TSubcategoria(2, "Pasta", true, 2);
		asSub.editaSubcategoria(catEdita);
		assertEquals("Cambiamos una subcategoria de categoria a una que este activa: "
				+ "comprobamos que la subcategoria 2 ha cambiado de categoria", preparar.getCategoriaSubcat("Pasta"), (Integer)2);
		assertEquals("Cambiamos una subcategoria de categoria a una que este activa: "
				+ "categoria 1 tiene 1 subcategorias", asSub.obtenSubcategorias(1).size(), 1);
		assertEquals("Cambiamos una subcategoria de categoria a una que este activa: "
				+ "categoria 2 tiene 2 subcategorias", asSub.obtenSubcategorias(2).size(), 2);
		preparar.borrarBD();
	}
}