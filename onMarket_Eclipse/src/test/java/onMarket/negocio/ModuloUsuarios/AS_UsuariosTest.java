package onMarket.negocio.ModuloUsuarios;

import java.sql.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.integracion.ModuloUsuarios.DAOUsuarioImp;
import onMarket.negocio.Seguridad.AS_SeguridadImp;

public class AS_UsuariosTest extends TestCase{
	public AS_UsuariosTest( String testName )
    {
        super(testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AS_UsuariosTest.class );
    }



	public void testObtenUsuario(){
		
	    /*
	     * Test obtener un usuario con base de datos vacia
	     */
		PrepararBD prepara= new PrepararBD();
		AS_UsuarioImp usu = new AS_UsuarioImp();	
		prepara.borrarBD();
		assertTrue("Se están obteniendo elementos en una base de Datos vacía",usu.obtenUsuario("1@1")== null);
		  /*
	     * Test obtener un usuario con base datos llena
	     */
		prepara.borrarBD();
		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		AS_SeguridadImp seg = new AS_SeguridadImp();
		TUsuario nuevo = new TUsuario(123456789,false,true,fecha, "algo@algo.es","admin","usuario",
				 "Palotes","Direccion","foto","n_Tarjeta","dir_Entrega");
		seg.altaUsuario(nuevo);
		TUsuario usuarioencotrado=usu.obtenUsuario(nuevo.getMail());
		assertTrue("Se ha encontrado el usuario buscado",usuarioencotrado!=null);
		
		/*
		 *Test obtener un usuario que no existe en la base de datos 
		 */
		usuarioencotrado=usu.obtenUsuario("algo@nosemas");
		assertTrue("Se ha encontrado el usuario buscado",usuarioencotrado==null);
	}
	
	


	
	

public void testEditaUsuario(){
		
		PrepararBD prepara= new PrepararBD();
		AS_UsuarioImp as = new AS_UsuarioImp();
		AS_SeguridadImp seg = new AS_SeguridadImp();
		/*
		 * Test: Cambiar el nombre de un Usuario a uno ya  existente
		 */
		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		prepara.borrarBD();
		 TUsuario nuevo = new TUsuario(123456789,false,true,fecha, "algo@algo.es","admin","usuario",
				 "Palotes","Direccion","foto","n_Tarjeta","dir_Entrega");
		seg.altaUsuario(nuevo);
		DAOUsuarioImp daousu = new DAOUsuarioImp();
		TUsuario s3 =daousu.readByEmail("algo@algo.es");
		TUsuario s2 = new TUsuario(s3.getId(),66666666,false,true,fecha,"algo@algo.es","pass","Pepa", "Martinez", "direccion", "caca.jpg", "mitarjeta", "el garaje");
		assertTrue("Se ha actualizado correctamente",as.editaUsuario(s2)!= null);
		
		/*
		 * Test Cambiar algun campo a un usario no existente
		 */
		TUsuario s4 = new TUsuario(8,45454,false,true,fecha,"cosas@cosas.es","contr","Anonimous","null",null,null,null,null);
		assertTrue("No se ha actualizado, el usuario no existe",as.editaUsuario(s4)==null);
		
		/*
		 * Test cambiar campos a null ,campos que si se pueden poner a null
		 */
		TUsuario s5 = new TUsuario(8,45454,false,true,fecha,"algo@algo.es",null,null,null,null,null,null,null);
		assertTrue("No se ha actualizado, el usuario no existe",as.editaUsuario(s5)==null);
		
		/*
		 * Test cambiar campos a null ,campos que no se pueden poner a null
		 */
		nuevo = new TUsuario(null,false,true,null,null,null,null,
				 null,null,null,null,null);
		assertTrue("insertar un usuario con datos nulos 1 ",as.editaUsuario(nuevo) == null);
}


	public void testEliminaUsuario(){
		
		AS_UsuarioImp as = new AS_UsuarioImp();
		PrepararBD prepara= new PrepararBD();	
		//Test: Eliminar una Usuario que no existe
		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		prepara.borrarBD();
		TUsuario s2 = new TUsuario(66666666,true,true,fecha,"example@example.com","pass",
								   "Pepe", "Martinez", "direccion", "foto", "n_tarjeta", 
								   "dir_entrega");
		assertTrue("Eliminar un Usuario que no existe", as.eliminaUsuario(s2)== null);


	prepara.borrarBD();
	AS_UsuarioImp as2 = new AS_UsuarioImp();
	AS_SeguridadImp seg = new AS_SeguridadImp();
	TUsuario s1 = new TUsuario(66666666,true,true,fecha,"example@example.com","pass","Pepe", "Martinez", "direccion", "foto", "n_tarjeta", "dir_entrega");
	seg.altaUsuario(s1);
	//caso existente:
	/*
	 * Test Eliminar usuario activo
	 */
	assertTrue("Eliminamos un usuario existente",as2.eliminaUsuario(s1)!=null);
	// caso inactivo
	//Test: Eliminar un Usuario tanto existente como  inactivo
	assertTrue("Eliminar un Uusario ya eliminado", as2.eliminaUsuario(s1)== null);
	prepara.borrarBD();
	}
	
	


}
