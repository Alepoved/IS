package onMarket.integracion.ModuloPedidos;

import java.util.ArrayList;

import onMarket.negocio.ModuloPedidos.BOCesta;
import onMarket.negocio.ModuloPedidos.TLineaPedido;
import onMarket.negocio.ModuloPedidos.TPedido;

public interface DAOPedidos {
	public Integer create(BOCesta cesta);

	public ArrayList<TPedido> readByUser(Integer usuario);
	
	public ArrayList<TLineaPedido> readAllLines(TPedido pedido);
	
	public TPedido readById(Integer id);
	
	public Integer update(TPedido pedido);
	
	public Integer delete(TPedido pedido);
}