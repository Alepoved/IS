package onMarket.integracion.ModuloCategorias;

import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.integracion.factorias.FactoriaIntegracion;
import onMarket.negocio.ModuloCategorias.AS_CategoriaImp;
import onMarket.negocio.ModuloCategorias.TCategoria;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DAOCategoriaTest extends TestCase {
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DAOCategoriaTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DAOCategoriaTest.class );
    }

	
	
	/*
	 * Test: Crear una Categoria Existente
	 */
	public void testDaoCreate() {
		PrepararBD bd = new PrepararBD();
 		bd.borrarBD();
		bd.insertCat(1, "carne", true);
		bd.insertCat(2, "harina", true);
		AS_CategoriaImp test = new AS_CategoriaImp();
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = test.crearDAOCategorias(factoria);
		TCategoria cat = new TCategoria(1,"carne",false);
		cat.setActivo(true);
		//OJO: Este test produce una excepción de BD por Clave duplicada
		assertTrue("Esta categoria ya existe", daoCat.create(cat) == null );  
	}
	
	
	/*
	 * Test: Eliminar una Categoria no existente
	 */
	public void testDaoDelete() {
		int id = 3;
		PrepararBD bd = new PrepararBD();
		// Preparamos la base de datos para el caso sea el correcto
		bd.borrarBD();
		bd.insertCat(1, "carne", true);
		bd.insertCat(2, "harina", true);
		AS_CategoriaImp test = new AS_CategoriaImp();
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = test.crearDAOCategorias(factoria);
		assertTrue("No existe una categoria con ese nombre no se puede borrar", daoCat.delete(id) == 0);
	}

	
	/* 
	 * Test: actualizar un elemento no existente
	 */
	public void testDaoUpdate() {
		PrepararBD bd = new PrepararBD();
		// Preparamos la base de datos para el caso sea el correcto
		bd.borrarBD();
		bd.insertCat(1, "carne", true);
		bd.insertCat(2, "harina", true);
		AS_CategoriaImp as = new AS_CategoriaImp();
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = as.crearDAOCategorias(factoria);
		TCategoria cat = daoCat.readByName("galletas");
		assertTrue("Todo ok", cat == null);
	}

	/*
	 * Test: Ejecutar un readAll y no hay elementos
	 */
	public void testDaoReadAllEmpty() {
		PrepararBD bd = new PrepararBD();
		bd.borrarBD();
		AS_CategoriaImp test = new AS_CategoriaImp();
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = test.crearDAOCategorias(factoria);
		assertTrue("No existe una categoria con ese nombre no se puede borrar", daoCat.readAll().size() == 0);
	}
	
	 /*  
	  * Test: Si ponemos un elemento a inactivo desaparece del read all
	  */
	 public void tesDaoReadAllInactives(){
		 PrepararBD bd = new PrepararBD();
			bd.borrarBD();
			bd.insertCat(1, "carne", false);
			bd.insertCat(2, "harina", false);
			bd.insertCat(3, "galletas", true);
			AS_CategoriaImp test = new AS_CategoriaImp();
			FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
			DAOCategoria daoCat = test.crearDAOCategorias(factoria);
			ArrayList<TCategoria> list=new ArrayList<>();
			list=daoCat.readAll();
			assertTrue("No existe una categoria con ese nombre no se puede borrar",
						list.size() == 1 && list.get(0).getNombre().equals("galletas"));
	 }
	
	/* 
	 * Test: Ejecutar un ReadByName y no existe el nombre
	 */
	public void testDaoReadByName() {
		PrepararBD bd = new PrepararBD();
		// Preparamos la base de datos para el caso sea el correcto
		bd.borrarBD();
		bd.insertCat(1, "carne", true);
		bd.insertCat(2, "harina", true);
		AS_CategoriaImp as = new AS_CategoriaImp();
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = as.crearDAOCategorias(factoria);
		TCategoria cat = daoCat.readByName("galletas");
		assertTrue("No hay ninguna categoria", cat == null);
		bd.borrarBD();
	}
	
	/* 
	 * Test: Ejecutar un ReadByName y no existe el nombre
	 */
	public void testDaoReadById() {
		PrepararBD bd = new PrepararBD();
		// Preparamos la base de datos para el caso sea el correcto
		bd.borrarBD();
		bd.insertCat(1, "pescado", true);
		bd.insertCat(2, "yogures", true);
		AS_CategoriaImp as = new AS_CategoriaImp();
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = as.crearDAOCategorias(factoria);
		TCategoria cat = daoCat.readById(3);
		assertTrue("No hay ninguna categoria", cat == null);
		bd.borrarBD();
	}
	

}
