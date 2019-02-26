package onMarket.negocio.ModuloProductos;

import junit.framework.TestCase;
import onMarket.PrepareBDUtilsTest.PrepararBD;

public class AS_ProductoTest extends TestCase {

	public void testAltaProducto(){

		PrepararBD preparar = new PrepararBD();
		AS_ProductoImp as = new AS_ProductoImp();
		
		/*
		* Test: Damos de alta un producto no existente
		*/

		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		TProducto prod1 = new TProducto("Pan de molde", "Bimbo");
		prod1.setEstado(true); prod1.setFoto("foto");
		prod1.setPesoVol("300"); prod1.setPrecio(2.0);
		prod1.setStock(50); prod1.setSubcategoria(1);
		Integer id1 = as.altaProducto(prod1); 
		assertEquals("Dar de alta un producto no existente", preparar.getNombreProd(id1), "Pan de molde");

		/*
		 * Test: Dar de alta un producto existente y activo
		 */

		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		preparar.insertProd(2, "Macarrones", true, 2, "Gallo", 3.0, 120, "500", "foto");
		TProducto prod2 = new TProducto(3, "Macarrones", true, 2, "Gallo", 4.0, 100, "600", "foto");
		assertEquals("Dar de alta un producto nuevo que está existente y activo", as.altaProducto(prod2), null);

		/*
		 * Test: Dar de alta un producto existente pero inactivo
		 */

		preparar.borrarBD(); 
		preparar.insertCat(1, "Despensa", true);
		preparar.insertSubcat(1, "Aceite", true, 1);
		preparar.insertSubcat(2, "Productos de la tierra", true, 1);
		preparar.insertProd(3, "Patatas", false, 2, "Alipende", 2.0, 300, "5kg", "foto"); 
		TProducto prod3 = new TProducto("Patatas", "Alipende");
		prod3.setEstado(true); prod3.setFoto("foto");
		prod3.setPesoVol("1kg"); prod3.setPrecio(2.0);
		prod3.setStock(100); prod3.setSubcategoria(2);
		Integer id3 = as.altaProducto(prod3);
		assertEquals("Dar de alta un producto existente pero inactivo", preparar.getActivoProd(id3), true);
	
		/*
		 * Test: Dar de alta un producto null
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		TProducto prod4 = null;
		assertEquals("Dar de alta un producto null", as.altaProducto(prod4), null);
		
		/*
		 * Test: Dar de alta un producto con nombre null
		 */
		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		TProducto prod5 = new TProducto(null, "Bimbo");
		preparar.insertProd(2, null, true, 2, "Gallo", 3.0, 120, "500", "foto");
		prod5.setEstado(true); prod5.setFoto("foto");
		prod5.setPesoVol("300"); prod5.setPrecio(2.0);
		prod5.setStock(50); prod5.setSubcategoria(1);
		assertEquals("Dar de alta un producto con nombre null", as.altaProducto(prod5), null);

		/*
		 * Test: Dar de alta un producto con marca null
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		TProducto prod6 = new TProducto("Pan", null);
		prod6.setEstado(true); prod6.setFoto("foto");
		prod6.setPesoVol("300"); prod6.setPrecio(2.0);
		prod6.setStock(50); prod6.setSubcategoria(1);
		assertEquals(" Dar de alta un producto con marca null", as.altaProducto(prod6), null);
		
		/*
		 * Test: Dar de alta un producto con nombre y marca null
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		TProducto prod7 = new TProducto(null, null);
		prod7.setEstado(true); prod7.setFoto("foto");
		prod7.setPesoVol("300"); prod7.setPrecio(2.0);
		prod7.setStock(50); prod7.setSubcategoria(1);
		assertEquals("Dar de alta un producto con nombre y marca null", as.altaProducto(prod7), null);
		
		/*
		 * Test: Dar de alta un producto con varios campos a null
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		TProducto prod8 = new TProducto("Mayonesa", "Hellmans");
		prod8.setEstado(true); prod8.setFoto("foto");
		prod8.setPesoVol("300"); prod8.setPrecio(null);
		prod8.setStock(50); prod8.setSubcategoria(null);
		assertEquals("Dar de alta un producto con varios campos a null", as.altaProducto(prod8), null);
		
		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		TProducto prod9 = new TProducto("Mayonesa", "Hellmans");
		prod9.setEstado(null); prod9.setFoto("foto");
		prod9.setPesoVol(null); prod9.setPrecio(3.8);
		prod9.setStock(50); prod9.setSubcategoria(1);
		assertEquals("Dar de alta un producto con varios campos a null", as.altaProducto(prod9), null);
		
		preparar.borrarBD();
		preparar.insertCat(1, "Artesanal", true);
		preparar.insertSubcat(1, "Pan", true, 1);
		preparar.insertSubcat(2, "Pasta", true, 1);
		TProducto prod10 = new TProducto("Mayonesa", "Hellmans");
		prod10.setEstado(true); prod10.setFoto(null);
		prod10.setPesoVol("5gr"); prod10.setPrecio(3.8);
		prod10.setStock(null); prod10.setSubcategoria(1);
		assertEquals(" Dar de alta un producto con varios campos a null", as.altaProducto(prod9), null);
	}

public void testModificarProducto(){
		
		PrepararBD preparar = new PrepararBD();
		AS_ProductoImp as = new AS_ProductoImp();
		
		/*
		 * Test: Cambiar varios datos a un producto
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		TProducto prod1 = new TProducto(1, "caprichos", true, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		as.modificarProducto(prod1);
		assertEquals("Cambiar varios datos a un producto", preparar.getNombreProd(1), "caprichos");
		
		/*
		 * Test: Cambiar el nombre de un producto existente (sin cambiar la marca)
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", true, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		TProducto prod2 = new TProducto(1, "caprichos", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		assertTrue("Cambiar el nombre de un producto existente (sin cambiar la marca)", as.modificarProducto(prod2) == 1);
		assertEquals("Cambiar el nombre de un producto existente (sin cambiar la marca)", preparar.getNombreProd(1), "caprichos");
		
		/*
		 * Test: Cambiar el nombre y la marca de un producto existente
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", true, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		TProducto prod3 = new TProducto(1, "caprichos", true, 1, "Hacendado", 6.0, 80, "200", "imagen.png");
		assertEquals("Cambiar el nombre y la marca de un producto existente", as.modificarProducto(prod3), null);
		
		/*
		 * Test: Cambiar varios datos a un producto cuando la BD esta vacia
		 */
		
		preparar.borrarBD();
		TProducto prod4 = new TProducto(1, "caprichos", true, 1, "Hacendado", 6.0, 80, "200", "imagen.png");
		assertTrue("Cambiar varios datos a un producto cuando la BD esta vacia", as.modificarProducto(prod4) == null);
		
		/*
		 * Test: Cambiar varios datos (incluida la subcategoria) a un producto
		 */

		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", true, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 2, "Carretilla", 9.0, 180, "300", "imagen3.png");
		
		TProducto prod5 = new TProducto(3, "san jacobo", true, 1, "El Rojo", 3.0, 50, "500", "imagen3.png");
		
		as.modificarProducto(prod5);
		assertEquals("Cambiar varios datos (incluida la subcategoria) a un producto: se ha borrado de la antigua", as.obtenProductos(2).size(), 0);
		assertEquals("Cambiar varios datos (incluida la subcategoria) a un producto: se ha insertado en la subcategoria",
				as.obtenProductos(1).size(), 3);
		assertEquals("Cambiar varios datos (incluida la subcategoria) a un producto: es la misma que antes",
				as.obtenProductos(1).get(2).getNombre(), prod5.getNombre());

		/*
		 * Test: Cambiar de subcategoria a un producto existente
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.50, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", true, 1, "Hacendado", 7.50, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", false, 2, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", true, 2, "Tarradellas", 2.0, 220, "100", "imagen4.png");
		
		TProducto prod6 = new TProducto(4, "pizza", true, 1, "Tarradellas", 1.0, 220, "100", "imagen4.png");
		
		as.modificarProducto(prod6);
		assertEquals("Cambiar de subcategoria a un producto existente: se ha borrado de la antigua", as.obtenProductos(2).size(), 0);
		assertEquals("Cambiar de subcategoria a un producto existente: se ha insertado en la subcategoria",
				as.obtenProductos(1).size(), 3);
		assertEquals("Cambiar de subcategoria a un producto existente: es la misma que antes",
				as.obtenProductos(1).get(2).getNombre(), prod6.getNombre());
		
		/*
		 * Test: Cambiar de subcategoria, marca y nombre a un producto cuyo nombre ya existia
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.25, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", true, 1, "Hacendado", 7.00, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 2, "Carretilla", 9.00, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", true, 2, "Tarradellas", 2.00, 220, "100", "imagen4.png");
		
		TProducto prod7 = new TProducto(3, "pizza", true, 1, "Tarradellas", 9.00, 180, "300", "imagen3.png");
		
		as.modificarProducto(prod7);
	
		assertEquals("Cambiar de subcategoria, marca y nombre a un producto cuyo nombre ya existia: se ha borrado de la antigua", as.obtenProductos(2).size(), 0);
		assertEquals("Cambiar de subcategoria, marca y nombre a un producto cuyo nombre ya existia: se ha insertado en la subcategoria",
				as.obtenProductos(1).size(), 3);
		assertEquals("Cambiar de subcategoria, marca y nombre a un producto cuyo nombre ya existia: se ha cambiado el nombre",
				as.obtenProductos(1).get(2).getNombre(), prod7.getNombre());
		
		/*
		 * Test: Cambiar de subcategoria, nombre y marca a un producto cuyo nombre y marca ya existian (en otra subcategoria)
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.00, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", true, 1, "Hacendado", 7.00, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 2, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", true, 2, "Tarradellas", 2.0, 220, "100", "imagen4.png");
		
		TProducto prod8 = new TProducto(3, "caprichos", true, 1, "Hacendado", 9.0, 180, "300", "imagen3.png");
		
		as.modificarProducto(prod8);
		
		assertEquals("Cambiar de subcategoria, nombre y marca a un producto cuyo nombre y marca ya existian (en otra subcategoria)", as.modificarProducto(prod8), null);
		
		/*
		 * Test: Cambiar el nombre y marca a un producto por uno de un producto inactivo
		 */
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", true, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 2, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", false, 2, "Tarradellas", 2.00, 220, "100", "imagen4.png");
		
		TProducto prod9 = new TProducto(3, "pizza", true, 2, "Tarradellas", 9.0, 180, "300", "imagen3.png");
		
		as.modificarProducto(prod9);
		
		assertEquals("Cambiar el nombre y marca a un producto por uno de un producto inactivo: se ha borrado la antigua", as.obtenProductos(2).size(), 1);
		assertEquals("Cambiar el nombre y marca a un producto por uno de un producto inactivo: se ha cambiado el nombre",
				as.obtenProductos(2).get(0).getNombre(), prod9.getNombre());
		
		/*
		 * Test: Cambiar de subcategoria, nombre y marca a un producto con el mismo nombre y marca que otro inactivo
		 */
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", false, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 2, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", false, 2, "Tarradellas", 2.0, 220, "100", "imagen4.png");
		
		TProducto prod10 = new TProducto(3, "caprichos", true, 1, "Hacendado", 9.0, 180, "300", "imagen3.png");
		
		as.modificarProducto(prod10);
		assertEquals("Cambiar de subcategoria, nombre y marca a un producto con el mismo nombre y marca que otro inactivo: se ha borrado de la antigua", as.obtenProductos(1).size(), 2);
		assertEquals("Cambiar de subcategoria, nombre y marca a un producto con el mismo nombre y marca que otro inactivo: se ha cambiado a activa",
				as.obtenProductos(1).get(1).getEstado(), prod10.getEstado());
		assertEquals("Cambiar de subcategoria, nombre y marca a un producto con el mismo nombre y marca que otro inactivo: se ha cambiado de nombre y marca",
				as.obtenProductos(1).get(1).getNombre(), prod10.getNombre());
		
		/*
		 * Test: Intentar modificar un producto null
		 */
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", false, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 2, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", false, 2, "Tarradellas", 2.0, 220, "100", "imagen4.png");
		
		TProducto prod11 = null;
		assertEquals("Intentar modificar un producto null", as.modificarProducto(prod11), null);
		
		/*
		 * Test: Intentar modificar un producto con el id a null
		 */
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", false, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 2, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", false, 2, "Tarradellas", 2.0, 220, "100", "imagen4.png");
		
		TProducto prod12 =  new TProducto(null, "caprichos", true, 1, "Hacendado", 9.0, 180, "300", "imagen3.png");
		assertEquals("Intentar modificar un producto con el id a null", as.modificarProducto(prod12), null);
		
		/*
		 * Test: Intentar modificar un producto con varios campos a null
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", false, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 2, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", false, 2, "Tarradellas", 2.0, 220, "100", "imagen4.png");
		
		TProducto prod13 =  new TProducto(3, null, null, 1, "Carretilla", null, 123, "200", null);
		assertEquals("Intentar modificar un producto con el id a null", as.modificarProducto(prod13), null);
		
		TProducto prod14 =  new TProducto(3, "pizza", true, null, "Carretilla", 3.0, null, "200", "foto");
		assertEquals("Intentar modificar un producto con el id a null", as.modificarProducto(prod14), null);
		
		TProducto prod15 =  new TProducto(3, null, true, 1, "Carretilla", 5.0, 123, "200", null);
		assertEquals("Intentar modificar un producto con el id a null", as.modificarProducto(prod15), (Integer)1);
		
		TProducto prod16 =  new TProducto(null, "ldjfal", false, 1, null, null, 123, "200", null);
		assertEquals("Intentar modificar un producto con el id a null", as.modificarProducto(prod16), null);
	}

	public void testReponerProducto(){
		
		PrepararBD preparar = new PrepararBD();
		AS_ProductoImp as = new AS_ProductoImp();
		
		/*
		 * Test: Reponer un producto que exixte y esta activo
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		TProducto prod1 = new TProducto(1, "lasaña", true, 1, "Hacendado", 6.0, 80, "200", "imagen2.png");
		as.reponerProducto(prod1);
		assertEquals("Reponer Producto", preparar.getStockProd(1), (Integer) 160);
		
		/*
		 * Test: Reponer un producto que no existe
		 */
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertProd(2, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		TProducto prod2 = new TProducto(3, "Macarrones", true, 1, "Hacendado", 6.0, 80, "200", "imagen2.png");
		assertEquals("Reponer Producto", as.reponerProducto(prod2), null);	
		
		/*
		 * Test: Reponer un producto que existe pero no esta activo
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertProd(1, "lasaña", false, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		TProducto prod3 = new TProducto(1, "lasaña", false, 1, "Hacendado", 6.0, 80, "200", "imagen2.png");
		assertEquals("Reponer Producto", as.reponerProducto(prod3), null);
	}

	public void testBajaProducto(){
		PrepararBD preparar = new PrepararBD();
		AS_ProductoImp as = new AS_ProductoImp();
		
		/*
		 * Test: Dar de baja un producto existente y activo
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "Lácteos", true);
		preparar.insertSubcat(1, "Leche", true, 1);
		preparar.insertSubcat(2, "Yogurt", true, 1);
		preparar.insertProd(2, "Vitalinea", true, 2, "Danone", 1.0, 100, "500", "foto");
		assertEquals("Dar de baja un producto existente y activo", as.bajaProducto(2), (Integer)1);
	
		
		/*
		 * Test: Dar de baja un producto existente e inactivo
		 *
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "Lácteos", true);
		preparar.insertSubcat(1, "Leche", true, 1);
		preparar.insertSubcat(2, "Yogurt", true, 1);
		preparar.insertProd(3, "Vitalinea", false, 2, "Danone", 1.0, 300, "500", "foto"); 
		assertEquals("Dar de baja un producto existente e inactivo", as.bajaProducto(3), null);
		
	
		/*
		 * Test: Dar de baja un producto no existente
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "Lácteos", true);
		preparar.insertSubcat(1, "Leche", true, 1);
		preparar.insertSubcat(2, "Yogurt", true, 1);
		preparar.insertProd(3, "Vitalinea", false, 2, "Danone", 1.0, 300, "500", "foto"); 
		assertEquals("Dar de baja un productono existente", as.bajaProducto(4), null);
		
		/*
		 * Test: Dar de baja un producto null
		 */
		
		preparar.borrarBD();
		preparar.insertCat(1, "Lácteos", true);
		preparar.insertSubcat(1, "Leche", true, 1);
		preparar.insertSubcat(2, "Yogurt", true, 1);
		preparar.insertProd(2, "Vitalinea", true, 2, "Danone", 1.0, 100, "500", "foto");
		assertEquals("Dar de baja un producto null", as.bajaProducto(null), null);
	}
	
	public void testObtenProductos(){
		PrepararBD preparar = new PrepararBD();
		AS_ProductoImp as = new AS_ProductoImp();
	
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", false, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 1, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", false, 1, "Tarradellas", 2.0, 220, "100", "imagen4.png");
		assertEquals("Obtener productos de una subcategoria", as.obtenProductos(1).size(), 2);
		
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", false, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 1, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", false, 1, "Tarradellas", 2.0, 220, "100", "imagen4.png");
		assertEquals("Obtener productos de una subcategoria no existente", as.obtenProductos(2).size(), 0);
		
	
		preparar.borrarBD();
		preparar.insertCat(1, "precocinados", true);
		preparar.insertSubcat(1, "congelados", true, 1);
		preparar.insertSubcat(2, "no congelados", true, 1);
		preparar.insertProd(1, "lasaña", true, 1, "La Cocinera", 6.0, 80, "200", "imagen.png");
		preparar.insertProd(2, "caprichos", false, 1, "Hacendado", 7.0, 80, "200", "imagen2.png");
		preparar.insertProd(3, "albondigas", true, 1, "Carretilla", 9.0, 180, "300", "imagen3.png");
		preparar.insertProd(4, "pizza", false, 1, "Tarradellas", 2.0, 220, "100", "imagen4.png");
		assertEquals("Obtener productos de una subcategoria nula", as.obtenProductos(null), null);
	}		
}
