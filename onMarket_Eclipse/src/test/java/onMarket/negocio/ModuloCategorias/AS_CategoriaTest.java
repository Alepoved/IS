package onMarket.negocio.ModuloCategorias;
import onMarket.PrepareBDUtilsTest.PrepararBD;



import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AS_CategoriaTest extends TestCase{
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AS_CategoriaTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AS_CategoriaTest.class );
    }
    


	public void testAltacategoria(){
		PrepararBD prepara = new PrepararBD();
		AS_CategoriaImp as = new AS_CategoriaImp();
		
		/*
		 * Test: Dar de alta una categoria que no existe
		 */
		prepara.borrarBD();
		TCategoria cat1 = new TCategoria("carne");
		Integer id1 = as.altaCategoria(cat1); 

		assertEquals("Dar de Alta una nueva categoria", prepara.getNombreCat(id1), "carne");
		
		/*
		 * Test: Dar de alta una categoria existente activa
		 */
		prepara.borrarBD(); 
		prepara.insertCat(1, "carne", true); 
		TCategoria cat2 = new TCategoria("carne"); 
		
		assertEquals("Dar de Alta una nueva categoria existente y activa", as.altaCategoria(cat2), null );
		
		/*
		 * Test: Dar de alta una categoria existente pero inactiva
		 */
		prepara.borrarBD(); 
		prepara.insertCat(1, "carne", false); 
		TCategoria cat3 = new TCategoria("carne"); 
		as.altaCategoria(cat3);
		
		assertEquals("Dar de Alta una nueva categoria existente y activa", prepara.getActivoCat(cat3.getNombre()), true);
		
	}

	

	public void testEliminaCategoria(){
		
		AS_CategoriaImp as = new AS_CategoriaImp();
	    PrepararBD prepara= new PrepararBD();	
		
		/*
		 * Test: Eliminar una categoria
		 * */
		prepara.borrarBD(); //borramos todas las filas de la base de datos
		prepara.insertCat(1, "aceite", true); //insertamos una nueva fila
		as.eliminaCategoria("aceite"); //y ahora eliminamos dicha fila
		assertEquals("Eliminar una categoria existente", prepara.getActivoCat("aceite"), false);
		
		
		/*
		 * Test: Eliminar una categoria que no existe
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "carne", true);
		assertEquals("Eliminar una categoria que no existe", as.eliminaCategoria("pescado"), null);
		
		
		/*
		 * Test: Eliminar una categoria ya eliminada
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "pescado", true);
		prepara.insertCat(2, "carne", false);
		assertEquals("Eliminar una categoria ya eliminada", as.eliminaCategoria("carne"), null);
		
		/*
		 * Test: Eliminar una categoria cuando esta la BD vacia
		 */
		prepara.borrarBD();
		assertEquals("Eliminar una categoria cuando la BD esta vacia", as.eliminaCategoria("congelados"), null);
	}
	
	

	public void testEditaCategoria(){
		
		PrepararBD prepara= new PrepararBD();
		AS_CategoriaImp as = new AS_CategoriaImp();
		
		/*
		 * Test: Cambiar el nombre a una categoria
		 */
		prepara.borrarBD(); 
		prepara.insertCat(1, "pescado", true);
		TCategoria cat2 = new TCategoria(1, "jamon", true);
		as.editaCategoria(cat2);
		assertEquals("Cambiar el nombre a una categoria", prepara.getNombreCat(1), "jamon");
		
		/*
		 * Test: Cambiar el nombre de una categoria existente
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "pescado", true);
		prepara.insertCat(2, "carne", true);
		TCategoria cat3 = new TCategoria(1, "carne", true);
		assertEquals("Cambiar el nombre de una categoria existente", as.editaCategoria(cat3), null);
		
		/*
		 * Test: Cambiar el nombre a una categoria cuando esta la BD vacia
		 */
		prepara.borrarBD();
		TCategoria cat4 = new TCategoria(1, "carne", true);
		assertTrue("Cambiar el nombre de una categoria cuando no hay nada para cambiar", as.editaCategoria(cat4) == null);
	}
	
	public void testObtenCategorias(){
		PrepararBD prepara= new PrepararBD();
		AS_CategoriaImp as = new AS_CategoriaImp();
		
		prepara.borrarBD();
		/*
		 * Test: Obtener las categorias de una BD vacia
		 */
		assertTrue("Se están obteniendo elementos en una base de Datos vacía",
					as.obtenCategorias().size()==0);
		
		/*
		 * Test: Obtener las categorias (activas) de la BD con elementos
		 */
		prepara.insertCat(1, "pescados", true);
		prepara.insertCat(2, "carnes", true);
		prepara.insertCat(3, "harinas", false);
        ArrayList<TCategoria> obtenidos= new ArrayList<>(); 
        obtenidos=as.obtenCategorias();
        boolean flag= true;
        /*Si el tamaño es distinto de 2 ya no nos sirve*/
        if(obtenidos.size() != 2)
        	flag=false;
        
        /*si alguno de los dos  es el inactivo "harinas" */
        if((obtenidos.get(0).getNombre() =="harinas") ||
    			(obtenidos.get(1).getNombre() =="harinas"))
    			flag=false;
        
        
        /*Si uno es "carnes" y el otro NO es "pescados"  ya no nos vale*/
		if((obtenidos.get(0).getNombre()=="carnes") &&
			(obtenidos.get(1).getNombre()!="pescados"))
			flag=false;
		
		/*Si uno es "pescados" y el otro NO es "carnes"  ya no nos vale*/
		if((obtenidos.get(0).getNombre()=="pescados") &&
				(obtenidos.get(1).getNombre()!="carnes"))
				flag=false;
		assertTrue("Se están obteniendo categorias activas e inactivas", flag);
		
		
		/*
		 * Test: Obtener las categorias de una BD que solo contiene inactivas
		 */
		prepara.borrarBD();
		prepara.insertCat(1, "pescados", false);
		prepara.insertCat(2, "carnes", false);
		prepara.insertCat(3, "harinas", false);
		assertTrue("No se deberian obtener Catrgorias Inactivas",
					as.obtenCategorias().size()==0);
		prepara.borrarBD();
	}
	
	
}
