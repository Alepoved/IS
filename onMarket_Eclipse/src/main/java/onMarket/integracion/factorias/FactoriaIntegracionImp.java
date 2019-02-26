package onMarket.integracion.factorias;

import onMarket.integracion.ModuloCategorias.DAOCategoria;
import onMarket.integracion.ModuloCategorias.DAOCategoriaImp;
import onMarket.integracion.ModuloPedidos.DAOPedidos;
import onMarket.integracion.ModuloPedidos.DAOPedidosImp;
import onMarket.integracion.ModuloProductos.DAOProducto;
import onMarket.integracion.ModuloProductos.DAOProductoImp;
import onMarket.integracion.ModuloSubcategorias.DAOSubcategoria;
import onMarket.integracion.ModuloSubcategorias.DAOSubcategoriaImp;
import onMarket.integracion.ModuloUsuarios.DAOUsuario;
import onMarket.integracion.ModuloUsuarios.DAOUsuarioImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion
{
	/**
	 * Metodo que crea un nuevo DaoCategoria
	 * */
	@Override
	public DAOCategoria generaDAOCategoria() {
		return new DAOCategoriaImp();
	}
	/**
	 * Metodo que crea un nuevo DaoSubcategoria
	 * */
	@Override
	public DAOSubcategoria generaDAOSubcategoria() {
		return new DAOSubcategoriaImp();
	}
	
	@Override
	public DAOProducto generaDAOProducto() {
		return new DAOProductoImp();
	}
	
	@Override
	public DAOUsuario generaDAOUsuario(){
		return new DAOUsuarioImp();
	}
	
	@Override
	public DAOPedidos generaDAOPedidos() {
		return new DAOPedidosImp() ;
	}
	
	
	

}