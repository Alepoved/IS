package onMarket.presentacion.ModuloSubcategorias;

import java.util.ArrayList;
import onMarket.negocio.ModuloSubcategorias.AS_SubcategoriaImp;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
import onMarket.presentacion.admin.IGUI;

public class ControladorSubcategorias {
	private AS_SubcategoriaImp modelo ; 
	private IGUI gui; 
	
	public ControladorSubcategorias(AS_SubcategoriaImp modelo, IGUI gui){
		this.modelo=modelo;		
		this.gui=gui;
	}
	
	public void accion(int evento, TSubcategoria datos){
	switch (evento){
		case EventoSubcategoria.ALTA_SUBCATEGORIA:
			{
				TSubcategoria tSubcategoria= datos;
				Integer res= modelo.altaSubcategoria(tSubcategoria); 
				if (res != null){
					tSubcategoria.setId(res);
					gui.actualizar(EventoSubcategoria.RES_ALTA_SUBCATEGORIA_OK, tSubcategoria);
				}else{
					gui.actualizar(EventoSubcategoria.RES_ALTA_SUBCATEGORIA_KO, null);
					}
				break;
			}
		
		case EventoSubcategoria.BAJA_SUBCATEGORIA:
			{	
				Integer negocio=modelo.eliminaSubcategoria(datos.getNombre());
				Integer res= null;
				if (negocio!= null){
					res= datos.getId();
					gui.actualizar(EventoSubcategoria.RES_BAJA_SUBCATEGORIA_OK, res);
				}else
					gui.actualizar(EventoSubcategoria.RES_BAJA_SUBCATEGORIA_KO, res);
				break;
			}
			
		case EventoSubcategoria.MOSTRAR_SUBCATEGORIAS:
			{  
				ArrayList<TSubcategoria> res=modelo.obtenSubcategorias(datos.getCategoria());
				if (res!= null)
					gui.actualizar(EventoSubcategoria.RES_MOSTRAR_SUBCATEGORIAS_OK, res);
				else
					gui.actualizar(EventoSubcategoria.RES_MOSTRAR_SUBCATEGORIAS_KO, res);
				
				break;
			}
		
		case EventoSubcategoria.EDITA_SUBCATEGORIA:
			{
				Integer negocio = modelo.editaSubcategoria(datos); 
				TSubcategoria res= null;
				if (negocio != null){
					res = datos;
					gui.actualizar(EventoSubcategoria.RES_EDITA_SUBCATEGORIA_OK, res);
					
				}
				else
					gui.actualizar(EventoSubcategoria.RES_EDITA_SUBCATEGORIA_KO, res);
				
				break;
			}
				
	 	}
	
	}
	
	public void setGui(IGUI g){
		this.gui=g;
	}
}
