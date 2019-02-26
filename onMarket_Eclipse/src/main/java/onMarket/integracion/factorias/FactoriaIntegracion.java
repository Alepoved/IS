package onMarket.integracion.factorias;

import onMarket.integracion.ModuloCategorias.DAOCategoria;
import onMarket.integracion.ModuloPedidos.DAOPedidos;
import onMarket.integracion.ModuloProductos.DAOProducto;
import onMarket.integracion.ModuloSubcategorias.DAOSubcategoria;
import onMarket.integracion.ModuloUsuarios.DAOUsuario;

public abstract class FactoriaIntegracion{
	/**
	 * Atributo privado con el que trabajamos para iniciar la factoria
	 * */
	private static FactoriaIntegracion instancia;
	
	/**
	 * Metodo que se encarga de inicializar el atributo en caso de que no este iniciaizado
	 * */
	public static FactoriaIntegracion obtenerInstancia(){ 
		if (instancia== null) 
			instancia = new FactoriaIntegracionImp(); 
		return instancia;
	}
	
	/**
	 * Metodo abstracto que nos permite crear un DaoCategoria
	 * */
	public abstract DAOCategoria generaDAOCategoria();
	
	/**
	 * Metodo abstracto que nos permite crear una Subcategoria
	 * */	
	public abstract DAOSubcategoria generaDAOSubcategoria();
	
	/**
	 * Metodo que nos permite crear un daoProducto
	 * @return daoProducto
	 */
	public abstract DAOProducto generaDAOProducto();
	/**
	 * Metodo que nos permite crear un daoUsuario
	 * 
	 * */
	public abstract DAOUsuario generaDAOUsuario();
	
	/**
	 * Metodo que nos permite crear un daoPedidos
	 * 
	 * */
	public abstract DAOPedidos generaDAOPedidos();
}
