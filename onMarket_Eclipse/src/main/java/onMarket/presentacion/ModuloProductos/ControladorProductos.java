package onMarket.presentacion.ModuloProductos;

import java.util.ArrayList;

import onMarket.negocio.ModuloProductos.AS_ProductoImp;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.presentacion.admin.IGUI;

public class ControladorProductos {

	private AS_ProductoImp modelo ; 
	private IGUI gui; 
	
	public ControladorProductos(AS_ProductoImp modelo, IGUI gui){
		this.modelo=modelo;		
		this.gui=gui;
	}
	
	public void accion(int evento, TProducto datos){
		
		switch (evento){
			case EventoProducto.ALTA_PRODUCTO:
				{
					TProducto tProducto = datos;
					Integer resultado = modelo.altaProducto(tProducto);
					
					if (resultado != null){
						tProducto.setId(resultado);
						gui.actualizar(EventoProducto.RES_ALTA_PRODUCTO_OK, tProducto);
					}
					else
						gui.actualizar(EventoProducto.RES_ALTA_PRODUCTO_KO, null);
					break;
				}
			
			case EventoProducto.BAJA_PRODUCTO:
				{	
					Integer negocio = modelo.bajaProducto(datos.getId());
					Integer resultado = null;
					if (negocio != null){
						resultado = datos.getId();
						gui.actualizar(EventoProducto.RES_BAJA_PRODUCTO_OK, resultado);
					}
					else
						gui.actualizar(EventoProducto.RES_BAJA_PRODUCTO_KO, resultado);
					break;
				}
				
			case EventoProducto.MOSTRAR_PRODUCTOS:
				{  
					ArrayList<TProducto> resultado = modelo.obtenProductos(datos.getSubcategoria());
					if (resultado != null)
						gui.actualizar(EventoProducto.RES_MOSTRAR_PRODUCTOS_OK, resultado);
					else
						gui.actualizar(EventoProducto.RES_MOSTRAR_PRODUCTOS_KO, resultado);
					break;
				}
			
			case EventoProducto.EDITA_PRODUCTO:
				{
					Integer negocio = modelo.modificarProducto(datos);
					TProducto resultado = null;
					if (negocio != null){
						resultado = datos;
						gui.actualizar(EventoProducto.RES_EDITA_PRODUCTO_OK, resultado);
					}
					else
						gui.actualizar(EventoProducto.RES_EDITA_PRODUCTO_KO, resultado);
					break;
				}
				
			case EventoProducto.REPONER_PRODUCTO:
			{
				Integer negocio = modelo.reponerProducto(datos);
				TProducto resultado = null;
				if (negocio != null){
					resultado = datos;
					gui.actualizar(EventoProducto.RES_REPONER_PRODUCTO_OK, resultado);
				}
				else
					gui.actualizar(EventoProducto.RES_REPONER_PRODUCTO_KO, resultado);
				break;
			}	
	 	}
	}
	
	public void setGui(IGUI g){
		this.gui=g;
	}
}
