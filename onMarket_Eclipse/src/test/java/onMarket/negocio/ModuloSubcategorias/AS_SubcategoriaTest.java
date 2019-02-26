package onMarket.negocio.ModuloSubcategorias;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.negocio.ModuloCategorias.AS_CategoriaImp;
import onMarket.negocio.ModuloCategorias.TCategoria;

public class AS_SubcategoriaTest extends TestCase {

	public AS_SubcategoriaTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AS_SubcategoriaTest.class );
    }

    public void testAltaSubcategoria(){
		PrepararBD prepara = new PrepararBD();
		AS_CategoriaImp asCat = new AS_CategoriaImp();
		AS_SubcategoriaImp asSub = new AS_SubcategoriaImp();
		
		/*
		 * Test: Dar de alta una Subcategoria que no existe
		 */
		prepara.borrarBD();
		TCategoria cat1 = new TCategoria("carne");
		Integer idcat1 = asCat.altaCategoria(cat1);
		TSubcategoria sub1= new TSubcategoria("Chorizo"); 
		sub1.setActivo(true);
		sub1.setCategoria(idcat1); 
		Integer id1 = asSub.altaSubcategoria(sub1);

		assertEquals("Dar de Alta una nueva Subcategoria", prepara.getNombreSubcat(id1), "Chorizo"); 
		
		/*
		 * Test: Dar de alta una Subcategoria existente activa
		 */
		prepara.borrarBD(); 
		prepara.insertCat(1, "carne", true); 
		prepara.insertSubcat(1, "Chorizo", true, 1); 
		TSubcategoria sub2 = new TSubcategoria("Chorizo");
		sub2.setActivo(true); 
		sub2.setCategoria(1); 
		
		assertEquals("Dar de Alta una nueva categoria existente y activa", asSub.altaSubcategoria(sub2), null );
		
		/*
		 * Test: Dar de alta una Subcategoria existente pero inactiva
		 */
		prepara.borrarBD(); 
		prepara.insertCat(1, "carne", true); 
		prepara.insertSubcat(1, "Chorizo", false, 1); 
		TSubcategoria sub3 = new TSubcategoria("Chorizo");
		sub3.setActivo(true);
		sub3.setCategoria(1);
		asSub.altaSubcategoria(sub3); 
		
		assertEquals("Dar de Alta una nueva Subcategoria existente y activa", prepara.getActivoSubcat(sub3.getNombre()), true); 
		
	}
	
	public void testObtenSubcategorias(){
		PrepararBD prepara= new PrepararBD();
		AS_SubcategoriaImp as = new AS_SubcategoriaImp();
		
		prepara.borrarBD();
		
		/*
		 * Test: Obtener las subccategorias de una tabla de subcategorias vacia
		 */
		prepara.insertCat(1, "lacteos", true);
		assertTrue("Se están obteniendo elementos en una base de Datos vacía",
					as.obtenSubcategorias(1).size()==0);
		
		/*
		 * Test: Obtener las subccategorias (activas) de una categoria con elementos
		 */
		prepara.insertSubcat(1, "yogures", true,1);
		prepara.insertSubcat(2, "helados", true,1);
		prepara.insertSubcat(3, "leche", false,1);
        ArrayList<TSubcategoria> obtenidos= new ArrayList<>(); 
        obtenidos=as.obtenSubcategorias(1);
        boolean flag= true;
        /*Si el tamaño es distinto de 2 ya no nos sirve*/
        if(obtenidos.size() != 2)
        	flag=false;
        
        /*si alguno de los dos  es el inactivo "leche" ya no nos sirve */
        if((obtenidos.get(0).getNombre() =="leche") ||
    			(obtenidos.get(1).getNombre() =="leche"))
    			flag=false;
        
        
        /*Si uno es "yogures" y el otro NO es "helados"  ya no nos vale*/
		if((obtenidos.get(0).getNombre()=="yogures") &&
			(obtenidos.get(1).getNombre()!="helados"))
			flag=false;
		
		/*Si uno es "helados" y el otro NO es "yogures"  ya no nos vale*/
		if((obtenidos.get(0).getNombre()=="helados") &&
				(obtenidos.get(1).getNombre()!="yogures"))
				flag=false;
		assertTrue("Se están obteniendo categorias activas e inactivas", flag);
		
		
		/*
		 * Test: Obtener las subcategorias de una Categoria que solo contiene inactivas
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "lacteos", false);
		prepara.insertSubcat(1, "yogures", false,1);
		prepara.insertSubcat(2, "helados", false,1);
		prepara.insertSubcat(3, "leche", false,1);
		assertTrue("No se deberian obtener Catrgorias Inactivas",as.obtenSubcategorias(1).size()==0);
		prepara.borrarBD();
	}

public void testEditaSubcategoria(){
		
		PrepararBD prepara= new PrepararBD();
		AS_SubcategoriaImp as = new AS_SubcategoriaImp();
		
		/*
		 * Test: Cambiar el nombre a una subcategoria
		 */
		prepara.borrarBD(); 
		prepara.insertCat(1, "pescado", true);
		prepara.insertSubcat(1, "merluza", true, 1);
		prepara.insertSubcat(2, "salmon", true, 1);
		TSubcategoria s1 = new TSubcategoria(1, "atun", true, 1);
		as.editaSubcategoria(s1);
		assertEquals("Cambiar el nombre a una subcategoria", prepara.getNombreSubcat(1), "atun");
		/*
		 * Test: Cambiar el nombre de una subcategoria existente
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "pescado", true);
		prepara.insertSubcat(1, "merluza", true, 1);
		prepara.insertSubcat(2, "salmon", true, 1);
		TSubcategoria s2 = new TSubcategoria(1, "salmon", true, 1);
		assertEquals("Cambiar el nombre de una subcategoria existente", as.editaSubcategoria(s2), null);
		/*
		 * Test: Cambiar el nombre a una subcategoria cuando esta la BD vacia
		 */
		
		prepara.borrarBD();
		TSubcategoria s3 = new TSubcategoria(1, "lubina", true, 1);
		assertTrue("Cambiar el nombre de una subcategoria cuando no hay nada para cambiar", as.editaSubcategoria(s3) == 0);
		/*
		 * Test: Cambiar de nombre y categoria a una subcategoria
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "pescado", true);
		prepara.insertCat(2, "carne", true);
		prepara.insertSubcat(1, "merluza", true, 1);
		prepara.insertSubcat(2, "salmon", true, 1);
		prepara.insertSubcat(3, "atun", true, 1);
		prepara.insertSubcat(4, "jamon", true, 2);
		
		TSubcategoria s4 = new TSubcategoria(4, "filete", true, 1);
		as.editaSubcategoria(s4);
		assertEquals("Cambiar de nombre y categoria a una subcategoria existente: se ha borrado de la antigua", as.obtenSubcategorias(2).size(), 0);
		assertEquals("Cambiar de nombre y categoria a una subcategoria existente: se ha insertado en la categoria",
				as.obtenSubcategorias(1).size(), 4);
		assertEquals("Cambiar de nombre y categoria a una subcategoria existente: es la misma que antes",
				as.obtenSubcategorias(1).get(3).getNombre(), s4.getNombre());

		/*
		 * Test: Cambiar de categoria a una subcategoria existente
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "frutas", true);
		prepara.insertCat(2, "verduras", true);
		prepara.insertSubcat(1, "limon", true, 1);
		prepara.insertSubcat(2, "pera", true, 1);
		prepara.insertSubcat(3, "manzana", true, 1);
		prepara.insertSubcat(4, "coliflor", false, 2);
		prepara.insertSubcat(5, "alcachofa", true, 2);
		
		TSubcategoria s5 = new TSubcategoria(5, "alcachofa", true, 1);
		as.editaSubcategoria(s5);
		assertEquals("Cambiar de categoria a una subcategoria existente: se ha borrado de la antigua", as.obtenSubcategorias(2).size(), 0);
		assertEquals("Cambiar de categoria a una subcategoria existente: se ha insertado en la categoria",
				as.obtenSubcategorias(1).size(), 4);
		assertEquals("Cambiar de categoria a una subcategoria existente: es la misma que antes",
				as.obtenSubcategorias(1).get(3).getNombre(), s5.getNombre());
		
		/*
		 * Test: Cambiar de categoria y nombre a una subcategoria cuyo nombre ya existia
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "frutas", true);
		prepara.insertCat(2, "verduras", true);
		prepara.insertSubcat(1, "limon", true, 1);
		prepara.insertSubcat(2, "pera", true, 1);
		prepara.insertSubcat(3, "coliflor", true, 2);
		prepara.insertSubcat(4, "alcachofa", true, 2);
		
		TSubcategoria s6 = new TSubcategoria(3, "alcachofa", true, 1);
		as.editaSubcategoria(s6);
		assertEquals("Cambiar de categoria y nombre a una subcategoria cuyo nombre ya existia: se ha borrado de la antigua", as.obtenSubcategorias(2).size(), 0);
		assertEquals("Cambiar de categoria y nombre a una subcategoria cuyo nombre ya existia: se ha insertado en la categoria",
				as.obtenSubcategorias(1).size(), 3);
		assertEquals("Cambiar de categoria y nombre a una subcategoria cuyo nombre ya existia: se ha cambiado el nombre",
				as.obtenSubcategorias(1).get(2).getNombre(), s6.getNombre());
		
		/*
		 * Test: Cambiar de categoria y nombre a una subcategoria cuyo nombre ya existia (en otra categoria)
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "frutas", true);
		prepara.insertCat(2, "verduras", true);
		prepara.insertSubcat(1, "limon", true, 1);
		prepara.insertSubcat(2, "pera", true, 1);
		prepara.insertSubcat(3, "coliflor", true, 2);
		prepara.insertSubcat(4, "alcachofa", true, 2);
		
		TSubcategoria s7 = new TSubcategoria(3, "pera", true, 1);
		as.editaSubcategoria(s7);
		assertEquals("Cambiar de categoria y nombre a una subcategoria cuyo nombre ya existia(en otra categoria): se ha borrado de la antigua", as.obtenSubcategorias(2).size(), 2);
		assertEquals("Cambiar de categoria y nombre a una subcategoria cuyo nombre ya existia(en otra categoria): se ha insertado en la categoria",
				as.obtenSubcategorias(1).size(), 2);
		assertEquals("Cambiar de categoria y nombre a una subcategoria cuyo nombre ya existia(en otra categoria): se ha cambiado el nombre",
				as.obtenSubcategorias(1).get(1).getNombre(), s7.getNombre());
		
		/*
		 * Test: Cambiar el nombre a una sub por uno de una subcategoria inactiva
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "frutas", true);
		prepara.insertCat(2, "verduras", true);
		prepara.insertSubcat(1, "limon", false, 1);
		prepara.insertSubcat(2, "melocoton", true, 1);
		
		TSubcategoria s8 = new TSubcategoria(2, "limon", true, 1);
		as.editaSubcategoria(s8);
		assertEquals("Cambiar el nombre a una sub por uno de una subcategoria inactiva: se ha borrado la antigua", as.obtenSubcategorias(1).size(), 1);
		assertEquals("Cambiar el nombre a una sub por uno de una subcategoria inactiva: se ha cambiado el nombre",
				as.obtenSubcategorias(1).get(0).getNombre(), s8.getNombre());
		
		/*
		 * Test: Cambiar de categoria y nombre a una subcategoria con el mismo nombre que otra subcategoria inactiva
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "frutas", true);
		prepara.insertCat(2, "verduras", true);
		prepara.insertSubcat(1, "limon", true, 1);
		prepara.insertSubcat(2, "pera", false, 1);
		prepara.insertSubcat(3, "coliflor", true, 2);
		prepara.insertSubcat(4, "alcachofa", false, 2);
		
		TSubcategoria s9 = new TSubcategoria(3, "pera", true, 1);
		as.editaSubcategoria(s9);
		assertEquals("Cambiar de categoria y nombre a una subcategoria con el mismo nombre que otra subcategoria inactiva: se ha borrado de la antigua", as.obtenSubcategorias(1).size(), 2);
		assertEquals("Cambiar de categoria y nombre a una subcategoria con el mismo nombre que otra subcategoria inactiva: se ha cambiado a activa",
				as.obtenSubcategorias(1).get(1).isActivo(), s7.isActivo());
	}

public void testEliminaSubcategoria(){
	
	AS_SubcategoriaImp as = new AS_SubcategoriaImp();
    PrepararBD prepara= new PrepararBD();	
	 //Test: Eliminar una Subcategoria que no existe
	prepara.borrarBD();
	prepara.insertCat(1, "carne", true);
	prepara.insertSubcat(1,"Buey",true,1);
	prepara.insertSubcat(2, "Vaca", true, 1);
	assertTrue("Eliminar una Subcategoria que no existe", as.eliminaSubcategoria("Cerdo")== null);

	//Test: Eliminar una categoria ya eliminada
	prepara.borrarBD();
	prepara.insertCat(1, "carne", false);
	prepara.insertSubcat(1,"Buey",true,1);
	prepara.borrarSubcategorias();
	assertTrue("Eliminar una Subcategoria ya eliminada", as.eliminaSubcategoria("Buey")== null);
	
	//Test: Eliminar una Subcategoria cuando esta la BD vacia
	prepara.borrarBD();
	assertTrue("Eliminar una Subcategoria cuando la BD esta vacia", as.eliminaSubcategoria("Buey")== null);
	prepara.borrarBD();
}
	
}
