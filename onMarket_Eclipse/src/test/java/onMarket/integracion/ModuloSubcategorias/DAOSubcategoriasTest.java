package onMarket.integracion.ModuloSubcategorias;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.integracion.factorias.FactoriaIntegracion;
import onMarket.negocio.ModuloSubcategorias.AS_SubcategoriaImp;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;


/*Propuestas de Test a realizar:
 * Crear una Sucategoria Existente
 * Eliminar una Subcategoria no existente
 * Añadir una Subcategoria mas cuando el array esta lleno
 * Que pasa si ejecutamos un readAll y no hay elementos?
 * Que pasa si ejecutamos un ReadByName y no exite el nombre? 
 * Que pasa si ejecutamos un ReadByCategory y no existe?
 * Si actualizamos el campo categoria cambia de categoria en el read All?
 * */

public class DAOSubcategoriasTest extends TestCase{
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    
	public DAOSubcategoriasTest( String testName )
    {
        super( testName );
    }

	
    /**
     * @return the suite of tests being tested
     */
   
	public static Test suite()
    {
        return new TestSuite( DAOSubcategoriasTest.class );
    }
	
	/*
	 * Crear una subcategoria existente
	 */
	public void testDaoCreate() {
		PrepararBD bd = new PrepararBD();
 		bd.borrarBD();
		bd.insertCat(1, "carne", true);
		bd.insertSubcat(1, "chorizo", true, 1); 
		AS_SubcategoriaImp test = new AS_SubcategoriaImp();
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOSubcategoria daoSub = test.CrearDAOSubcategoria(factoria); 
		TSubcategoria sub = new TSubcategoria(1,"chorizo", false, 1);
		sub.setActivo(true);
		//OJO: Este test produce una excepción de BD por Clave duplicada
		assertTrue("Esta categoria ya existe", daoSub.create(sub) == null );  
	}
   
	/**
	 * Test del update de una subcategoria
	 */
	public void testDaoUpdate() {
		
		PrepararBD bd = new PrepararBD();
		
		/*
		 * Test: Actualizar una Subcategoria no existente
		 */
		bd.borrarBD();
		bd.insertCat(1, "carne", true);
		bd.insertSubcat(1, "cerdo", true, 1);
		
		AS_SubcategoriaImp as = new AS_SubcategoriaImp();
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOSubcategoria daoSubcat = as.CrearDAOSubcategoria(factoria);
		
		TSubcategoria subcat = new TSubcategoria(2, "galletas", true, 1);
		assertTrue("Actualizar una subcategoria que no existe", daoSubcat.update(subcat) == 0);
		
		bd.borrarBD();
	}


	 	
	
}
