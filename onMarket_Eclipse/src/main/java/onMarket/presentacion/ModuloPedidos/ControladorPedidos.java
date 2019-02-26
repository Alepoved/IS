package onMarket.presentacion.ModuloPedidos;

import java.util.ArrayList;

import onMarket.negocio.ModuloPedidos.AS_PedidoImp;
import onMarket.negocio.ModuloPedidos.BOCesta;
import onMarket.negocio.ModuloPedidos.BOPago;
import onMarket.negocio.ModuloPedidos.EstadoPago;
import onMarket.negocio.ModuloPedidos.TLineaPedido;
import onMarket.negocio.ModuloPedidos.TPedido;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.presentacion.admin.IGUI;

public class ControladorPedidos {
   private AS_PedidoImp modelo;
   private IGUI gui;
   
   public ControladorPedidos(AS_PedidoImp modelo, IGUI gui ){
	   this.modelo=modelo;
	   this.gui=gui;
   }
   
   public void accion(int evento, Object datos){
		switch (evento){
			case EventoPedidos.MOSTRAR_PEDIDOS:
				{
					TUsuario usuario= (TUsuario) datos;
					ArrayList<TPedido> res=modelo.obtenerPedidos(usuario);
					if (res != null){
						gui.actualizar(EventoPedidos.RES_MOSTRAR_PEDIDOS_OK, res);
					}else{
						gui.actualizar(EventoPedidos.RES_MOSTRAR_PEDIDOS_KO, null);
						}
					break;
				}		
		       
			case EventoPedidos.PAGAR_PEDIDO:
				{
					BOPago pago= (BOPago) datos;
					int res=modelo.pagoCorrecto(pago);
					if(res== EstadoPago.CORRECTO)
						gui.actualizar(EventoPedidos.RES_PAGO_OK,res);
					else
						gui.actualizar(EventoPedidos.RES_PAGO_KO,res);
					break;
				}			
			
			case EventoPedidos.CONFIRMA_PEDIDO:
				{
					BOCesta cesta= (BOCesta) datos;
					if(modelo.confirmaStock(cesta)){
						Integer res = modelo.confirmaPedido(cesta);
						if(res!=null)
							gui.actualizar(EventoPedidos.RES_CONFIRMA_OK,res);
						 else
							gui.actualizar(EventoPedidos.RES_CONFIRMA_KO, res);
					}
					else{
						gui.actualizar(EventoPedidos.RES_STOCK_KO, null);
					}
					break;
				}	
				
			case EventoPedidos.VER_DETALLES:
				{
					TPedido pedido= (TPedido) datos;
					ArrayList<TLineaPedido> res=modelo.obtenerLineas(pedido);
					if(res!= null)
						gui.actualizar(EventoPedidos.RES_DETALLES_OK,res);
					else
						gui.actualizar(EventoPedidos.RES_DETALLES_KO,res);
					break;
				}	
		
			case EventoPedidos.VER_PRODUCTOS:
			{
				TPedido pedido= (TPedido) datos;
				ArrayList<String> res=modelo.obtenerProductos(pedido);
				if(res!= null)
					gui.actualizar(EventoPedidos.RES_PRODUCTOS_OK,res);
				else
					gui.actualizar(EventoPedidos.RES_PRODUCTOS_KO,res);
				break;
			}		
		}	
	}
		
    
   public void setGui(IGUI g){
			this.gui=g;
		}
}
