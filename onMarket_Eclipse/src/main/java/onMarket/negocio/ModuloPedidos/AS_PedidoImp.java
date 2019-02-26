package onMarket.negocio.ModuloPedidos;

import java.util.ArrayList;

import onMarket.integracion.ModuloPedidos.DAOPedidos;
import onMarket.integracion.ModuloProductos.DAOProducto;
import onMarket.integracion.factorias.FactoriaIntegracion;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.negocio.ModuloUsuarios.TUsuario;

public class AS_PedidoImp implements AppServPedido {

	public AS_PedidoImp(){}
	
	@Override
	public int pagoCorrecto(BOPago pago) {
		if(pago != null){
			if(pago.pagar() == EstadoPago.CORRECTO)
				return EstadoPago.CORRECTO;				
			else
				return pago.pagar(); 
			
		} return EstadoPago.ERROR_PAGO;
	}
	
  @Override
  public boolean confirmaStock(BOCesta cesta) {
	 boolean flag= true;	
	  if(cesta != null){ 
		  int i =0;
		  ArrayList<Integer> stockOriginal = new ArrayList<Integer>();
		  FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		  DAOProducto daoProd = factoria.generaDAOProducto();
		  // Reducción del Stock
		  while(flag && i < cesta.getLineasSize()){
			  TLineaPedido lineaActual=cesta.getLineaIesima(i);
			  TProducto producto=daoProd.readById(lineaActual.getId_producto());
			  if(producto!=null){
				  stockOriginal.add(i,producto.getStock());
				  if(producto.getStock()>=lineaActual.getCantidad()){  
						producto.setStock(producto.getStock()-lineaActual.getCantidad());
						Integer correcto= daoProd.update(producto);
						if(correcto == null) flag=false;
				  } else flag=false;
			   }else flag=false;
			  i++;
		  }
		
		  // Si hay algun problema, Se Reestablece el Stock original al menos hasta i
		  if (flag==false){
			for(int j=0; j<i ; j++){
				TLineaPedido lineaCurrent=cesta.getLineaIesima(j);
				TProducto prod=daoProd.readById(lineaCurrent.getId_producto());
				if(prod==null) return flag;
				else{
					prod.setStock(stockOriginal.get(j));
				    daoProd.update(prod);
					}
			 }	
		  }		
	  } else flag=false;	
      return flag;
   }

	@Override
	public Integer confirmaPedido(BOCesta cesta) {
		Integer dev = null;
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOPedidos daoPedidos = factoria.generaDAOPedidos();
	   if(cesta!=null){	
		if (cesta.getPedido() != null){
			if(cesta.getLineas().size() > 0){
				 DAOProducto daoProd = factoria.generaDAOProducto();
				  for(int i=0; i<cesta.getLineasSize(); i++){
					  TProducto producto=daoProd.readById(cesta.getLineaIesima(i).getId_producto());
					  if (producto == null) return null;
				  }
				if(cesta.getPedido().getDir_entrega() != null){
					if (cesta.getPedido().getEstado() != null){
						if (cesta.getPedido().getFecha_entrega() != null){
							if (9 <= cesta.getPedido().getHorario_ini() && cesta.getPedido().getHorario_ini() <= 21){
								if (9 <= cesta.getPedido().getHorario_fin() && cesta.getPedido().getHorario_fin() <= 21){
									if (cesta.getPedido().getId_usuario() >= 1){
										if (cesta.getPedido().getTelefono() != null && cesta.getPedido().getTelefono().length() == 9){
											if (cesta.getPedido().getTotal_pagar() > 0){
												dev = daoPedidos.create(cesta);
											} 
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return dev;
	  } 
	 return null;	
	}

	@Override
	public ArrayList<TPedido> obtenerPedidos(TUsuario usuario){
		ArrayList<TPedido> res=null;
		if(usuario == null) return null;
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOPedidos daoPedidos = factoria.generaDAOPedidos();
		if (usuario.getId() >= 1){
			if (usuario.isActivo()){
			  res =daoPedidos.readByUser(usuario.getId());
			  if(res == null) 
				  return null;
			  else
				  return res;
			}
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<TLineaPedido> obtenerLineas(TPedido pedido) {
		if(pedido != null){
			if(pedido.getId_pedido() != null){
				FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
				DAOPedidos daoPed= factoria.generaDAOPedidos();
				TPedido aux= daoPed.readById(pedido.getId_pedido());
				
				if(aux != null && aux.getId_usuario().intValue()==pedido.getId_usuario().intValue()){
					if(aux.getActivo()){
						ArrayList<TLineaPedido> lineas = new ArrayList<>(); 
						lineas = daoPed.readAllLines(pedido);
						if(lineas == null){
							return null; 
						}
						else return lineas; 
					}else return null; 
				}else return null; 
			}else return null; 
		}else return null; 
	}

	@Override
	public ArrayList<String> obtenerProductos(TPedido pedido) {
		if(pedido!=null){
		  if(pedido.getId_pedido() != null){
			FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
			DAOPedidos daoPed= factoria.generaDAOPedidos();
			TPedido aux= daoPed.readById(pedido.getId_pedido());
			if(aux != null && aux.getId_usuario().intValue()==
							  pedido.getId_usuario().intValue()){
			  if(aux.getActivo()){
			 	  ArrayList<String> nomProductos= new ArrayList<String>();
				  ArrayList<TLineaPedido> lineas= this.obtenerLineas(aux);
				  DAOProducto daoProd = factoria.generaDAOProducto();
				  for(int i=0; i<lineas.size(); i++){
					  TProducto producto=daoProd.readById(lineas.get(i).getId_producto());
					  if (producto == null) return null;
					  nomProductos.add(i,producto.getNombre()+" - "+producto.getMarca());
				  } 
				  return nomProductos;
			  }return null;
			} return null;
		  }return null;
		} return null;
	}

}