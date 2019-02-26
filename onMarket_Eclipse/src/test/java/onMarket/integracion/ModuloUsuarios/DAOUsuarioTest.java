package onMarket.integracion.ModuloUsuarios;

import java.sql.Date;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.integracion.factorias.FactoriaIntegracion;
import onMarket.negocio.ModuloUsuarios.TUsuario;

public class DAOUsuarioTest extends TestCase{
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
	public DAOUsuarioTest( String testName )
    {
        super( testName );
    }
	  /**
     * @return the suite of tests being tested
     */
   
	public static Test suite()
    {
        return new TestSuite( DAOUsuarioTest.class );
    }
	
	public void testDaoCreate() {
		PrepararBD bd = new PrepararBD();
 		bd.borrarBD();
 		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		bd.insertUsuario("Pepe", "example@example.com", "pass", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);
		bd.insertUsuario("Pepa", "example2@example.com", "pass2", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOUsuario daoUsu = factoria.generaDAOUsuario() ;
		TUsuario usu = new TUsuario(66666666,true,true,fecha,"example@example.com","pass","Pepe", "Martinez", "direccion", "foto", "n_tarjeta", "dir_entrega");
		usu.setActivo(true);
		//si devuelve un null el usuario ya existe y esta dado de alta
		assertTrue("Este usuario ya existe", daoUsu.create(usu) == null );
		//creamos un usuario
				TUsuario usuariocreado = new TUsuario(66666667,true,true,fecha,"exampleC@example.com","pass","Pepe", "Martinez", "direccion", "foto", "n_tarjeta", "dir_entrega");
				daoUsu.create(usuariocreado);
				assertTrue("EL usuario ha sido creado correctamente",usuariocreado!=null);
	}
	
	public void testDaoDelete() {//Eliminamos un usuario no existente
		int id = 3;
		PrepararBD bd = new PrepararBD();
		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		bd.borrarBD();
		bd.insertUsuario("Pepe", "example@example.com", "pass", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);
		bd.insertUsuario("Pepa", "example2@example.com", "pass2", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOUsuario daoUsu = factoria.generaDAOUsuario();
		assertTrue("Se intenta borrar un usuario que no existe en la BD", daoUsu.delete(id) == 0);
		//borramos un usuario existente
				TUsuario usuariocreado = new TUsuario(66666667,true,true,fecha,"exampleC@example.com","pass","Pepe", "Martinez", "direccion", "foto", "n_tarjeta", "dir_entrega");
				daoUsu.create(usuariocreado);
				assertTrue("Se borra un usuario",daoUsu.delete(66666667)!=null);
	}
		
	
	/* 
	 * Test: actualizar un elemento 
	 */
	public void testDaoUpdate() {
		PrepararBD bd = new PrepararBD();
		// Preparamos la base de datos para el caso sea el correcto
		bd.borrarBD();
		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		bd.insertUsuario("Pepe", "example@example.com", "pass", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);
		bd.insertUsuario("Pepa", "example2@example.com", "pass2", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOUsuario daoUsu = factoria.generaDAOUsuario();
		TUsuario usu = daoUsu.readByEmail("example@example.com");
		usu.setMail("nuevoMail@nuevo");
		assertTrue("Se intenta borrar un usuario que no existe en la BD", daoUsu.update(usu) != null);
	}
	
	public void testDaoReadByMail() {
		PrepararBD bd = new PrepararBD();
		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		// Preparamos la base de datos para el caso sea el correcto
		bd.borrarBD();
		bd.insertUsuario("Pepe", "example@example.com", "pass", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", false);
		bd.insertUsuario("Pepa", "example2@example.com", "pass2", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", false);
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOUsuario daoUsu = factoria.generaDAOUsuario();
		
		// buscar un usuario no existente
		TUsuario usu = daoUsu.readByEmail("example3@example.com");
		assertTrue("No hay ninguna usuario con ese mail", usu == null);
		
		// buscar un usuario exixtente
		usu = daoUsu.readByEmail("example2@example.com");
		assertTrue("Usuario leido correctamente", usu != null);
		bd.borrarBD();
	}
	public void testDaoReadByType() {
		PrepararBD bd = new PrepararBD();
		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		// Preparamos la base de datos para el caso sea el correcto
		bd.borrarBD();
		bd.insertUsuario("Pepe", "example@example.com", "pass", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);
		bd.insertUsuario("Pepa", "example2@example.com", "pass2", true, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOUsuario daoUsu = factoria.generaDAOUsuario();

		// buscar los usuarios que no sean administradores
		ArrayList<TUsuario> usu = daoUsu.readByType("0");
		assertTrue("No hay ningun usuario que no sea administrador", usu.isEmpty()==true);		
		// buscar los usuarios que sean administradores
		bd.borrarBD();
		bd.insertUsuario("Pepe", "example@example.com", "pass", false, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);
		bd.insertUsuario("Pepa", "example2@example.com", "pass2", false, "Martinez", "casa", 66666, fecha, "foto", "n_tarjeta", "dir_entrega", true);

		// buscar los usuarios que no sean administradores
		usu = daoUsu.readByType("0");
		assertTrue("Hay al menos un usuario que no sea administrador", usu.isEmpty()==false);
		bd.borrarBD();
	}
}
