package onMarket.negocio.ModuloSeguridad;

import java.sql.Date;

import junit.framework.TestCase;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.negocio.Seguridad.AS_SeguridadImp;

public class AS_SeguridadTest extends TestCase {

	
	public void testLogin()
	{
		PrepararBD db = new PrepararBD();
		AS_SeguridadImp aSeg = new AS_SeguridadImp();
		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		db.borrarBD();
		assertTrue("login con usuario erroneo",aSeg.Login("noexiste","no existe") == null);
		assertTrue("login con usuario null",aSeg.Login(null, "clave") == null);
		assertTrue("login con contraseña null",aSeg.Login("email", null) == null);
		assertTrue("login con usuario y contraseña null",aSeg.Login(null, null) == null);
		
		TUsuario nuevo = new TUsuario(2,123456789,false,true,fecha,
		"algo@algo.es","admin","usuario","Palotes","Direccion","foto","n_Tarjeta","dir_Entrega");
		aSeg.altaUsuario(nuevo);
		
		assertTrue("login usuario correcto, contraseña incorrecta",aSeg.Login("algo@algo.es", "1234") == null);
		assertTrue("login usuario incorrecto",aSeg.Login("algo@1234algo.es", "admin") == null);
		assertTrue("login correcto",aSeg.Login("algo@algo.es", "admin") != null);
		
		db.borrarBD();
		
		nuevo = new TUsuario(2,123456789,false,false,fecha,
		"algo@algo.es","admin","usuario","Palotes","Direccion","foto","n_Tarjeta","dir_Entrega");
		aSeg.altaUsuario(nuevo);
		assertTrue("login con cuenta desactivada",aSeg.Login("algo@algo.es", "admin") == null);
		
	}
	
	public void testAlta()
	{
		
		PrepararBD db = new PrepararBD();
		AS_SeguridadImp aSeg = new AS_SeguridadImp();
		@SuppressWarnings("deprecation")
		Date fecha = new Date(93,0,21);
		db.borrarBD();
		
		// crear usuario desactivado
		TUsuario nuevo = new TUsuario(2,123456789,false,false,fecha,"algo@algo.es","admin",
									  "usuario","Palotes","Direccion","foto","n_Tarjeta",
									  "dir_Entrega");
		assertTrue("No es posible crear un usuario inactivo",aSeg.altaUsuario(nuevo) == null);
		
		 // crear usuario activo
		nuevo = new TUsuario(2,123456789,false,true,fecha, "algo@algo.es","admin","usuario",
							 "Palotes","Direccion","foto","n_Tarjeta","dir_Entrega");
				assertTrue("Usuario creado correctamente",aSeg.altaUsuario(nuevo) != null);
		
		// Activar un usuario desactivado
		// primero creamos un usuario inactivo (no se puede mediante AS)		
		db.insertUsuario("kike","kike@kike","admin",false,"apellidoKike","direccion kik",
						 623326623,fecha,"foto","n_tarjeta","dir_entrega", false);		
	
		nuevo = new TUsuario(123456789,false,true,fecha, "kike@kike","admin","usuario",
				 			 "Palotes","Direccion","foto","n_Tarjeta","dir_Entrega");
		assertTrue("usuario activado correctamente",aSeg.altaUsuario(nuevo) != null);
		
		
		// crear un usuario existente y activo
		nuevo = new TUsuario(123456789,false,true,fecha,"kike@kike","admin","usuario",
							 "Palotes","nada","foto","n_Tarjeta","dir_Entrega");
		assertTrue("insertar un usuario existente con distinta contraseña",aSeg.altaUsuario(nuevo) == null);
		
		nuevo = new TUsuario(null,false,true,null,null,null,null,
				 null,null,null,null,null);
		assertTrue("insertar un usuario con datos nulos 1 ",aSeg.altaUsuario(nuevo) == null);
		
		nuevo = new TUsuario(123456789,false,true,null,null,null,null,
				 null,null,null,null,null);
		assertTrue("insertar un usuario con datos nulos 2",aSeg.altaUsuario(nuevo) == null);
		
		Date d = null;
		nuevo = new TUsuario(null,false,true,d,null,null,null,
				 null,null,null,null,null);
		assertTrue("insertar un usuario con datos nulos 3",aSeg.altaUsuario(nuevo) == null);
		
		nuevo = new TUsuario(null,false,true,null,"admin@onmarket.es",null,null,
				 null,null,null,null,null);
		assertTrue("insertar un usuario con datos nulos 4",aSeg.altaUsuario(nuevo) == null);
		
		nuevo = new TUsuario(null,false,true,null,null,"password",null,
				 null,null,null,null,null);
		assertTrue("insertar un usuario con datos nulos 5",aSeg.altaUsuario(nuevo) == null);
		
		nuevo = new TUsuario(null,false,true,null,null,null,"nombre",
				 null,null,null,null,null);
		assertTrue("insertar un usuario con datos nulos 6",aSeg.altaUsuario(nuevo) == null);
		
		nuevo = new TUsuario(null,false,true,null,null,null,null,
				 "apellido",null,null,null,null);
		assertTrue("insertar un usuario con datos nulos 7",aSeg.altaUsuario(nuevo) == null);

		nuevo = new TUsuario(null,false,true,null,null,null,null,
				 null,"direccion",null,null,null);
		assertTrue("insertar un usuario con datos nulos 8",aSeg.altaUsuario(nuevo) == null);
		
		nuevo = new TUsuario(null,false,true,null,null,null,null,
				 null,null,"foto",null,null);
		assertTrue("insertar un usuario con datos nulos 9",aSeg.altaUsuario(nuevo) == null);
		
		nuevo = new TUsuario(null,false,true,null,null,null,null,
				 null,null,null,"n tarjeta",null);
		assertTrue("insertar un usuario con datos nulos 10",aSeg.altaUsuario(nuevo) == null);
		
		nuevo = new TUsuario(null,false,true,null,null,null,null,
				 null,null,null,null,"dir entrega");
		assertTrue("insertar un usuario con datos nulos 11",aSeg.altaUsuario(nuevo) == null);
		db.borrarBD();
	}
}
