package onMarket.PruebasIntegracion;

import java.sql.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import onMarket.PrepareBDUtilsTest.PrepararBD;
import onMarket.negocio.ModuloCategorias.AS_CategoriaImp;
import onMarket.negocio.ModuloCategorias.TCategoria;
import onMarket.negocio.ModuloProductos.AS_ProductoImp;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.negocio.ModuloSubcategorias.AS_SubcategoriaImp;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.negocio.Seguridad.AS_SeguridadImp;
/*
 * Dado de que En Las pruebas unitarias de cada módulo se prueban todos los casos
 * en los que pueden fallar y No Fallar los métodos
 * Unicamente hemos desarrollado pruebas del sistema sobre casos
 * hipotéticamente correctos, aún así si algún método interno fallase
 * el test es capaz de reconocerlo y no lo Pasa
 * */

public class TestIntegraUsuariosProductos extends TestCase {
	
		public TestIntegraUsuariosProductos( String testName )
	    {
	        super(testName );
	    }

	    /**
	     * @return the suite of tests being tested
	     */
	    public static Test suite()
	    {
	        return new TestSuite( TestIntegraUsuariosProductos.class );
	    }

	    public void testVariosModulos(){
	    	//Dado que todos los modulos se relacionan en la capa de presentacion las prueba de integracion de todos los modulos se
	    	//tienen que hacer asi
	    	PrepararBD prepara= new PrepararBD();
	    	prepara.borrarBD();
	    	AS_SeguridadImp seg = new AS_SeguridadImp();
	    	AS_CategoriaImp cat = new AS_CategoriaImp();
	    	AS_SubcategoriaImp sub = new AS_SubcategoriaImp();
	    	AS_ProductoImp pro = new AS_ProductoImp();
	    	prepara.borrarBD();
	    	@SuppressWarnings("deprecation")
			Date fecha = new Date(93,0,21);
	    	//Creamos un usuario admin
	    	TUsuario admin= new TUsuario(123456789,false,true,fecha, "kike@kike","admin","usuario",
	    			 "Palotes","Direccion","foto","n_Tarjeta","dir_Entrega");
	    	assertTrue("Usuario admin creado correctamente",seg.altaUsuario(admin)!=null);
	    	
	    	//creamos con ese usuario una categoria
	    	
	    	TCategoria categoria = new TCategoria("carne");
	    	assertTrue("Usuario admin crea correctamente una Categoria",cat.altaCategoria(categoria)!=null);
	    	
	    	//dentro de esa categoria creamos una subcategoria
	    	
	    	Integer idcat1 = cat.altaCategoria(categoria);
	    	TSubcategoria sub1= new TSubcategoria("Chorizo"); 
	    	sub1.setActivo(true);
	    	sub1.setCategoria(idcat1); 
	    	Integer id1 = sub.altaSubcategoria(sub1);

	    	assertEquals("Dar de Alta una nueva Subcategoria", prepara.getNombreSubcat(id1), "Chorizo"); 
	    	
	    	
	    	//Y por ultimo dentro de esa subcategoria creamos un producto
	    	
	    	TProducto prod1 = new TProducto("Pan de molde", "Bimbo");
	    	prod1.setEstado(true);
	    	prod1.setFoto("foto");
	    	prod1.setPesoVol("300");
	    	prod1.setPrecio(2.0);
	    	prod1.setStock(50);
	    	prod1.setSubcategoria(id1);
	    	Integer id2 = pro.altaProducto(prod1); 
	    	assertEquals("Dar de alta un producto", prepara.getNombreProd(id2), "Pan de molde");
	    	//Eliminamos el Producto
	    	assertEquals("Dar de baja un producto existente y activo", pro.bajaProducto(prod1.getId()),null);
	    	//Eliminamos la Subcategoria
	    	assertTrue("Dar de baja una subcategoria activa",sub.eliminaSubcategoria("Chorizo") == 1);
	    	//Eliminamos la Categoria
	    	assertTrue("Dar de baja la categoria creada entes ",cat.eliminaCategoria("carne")==1);
	    	prepara.borrarBD();
	    }	
	    
	    
	    
}
