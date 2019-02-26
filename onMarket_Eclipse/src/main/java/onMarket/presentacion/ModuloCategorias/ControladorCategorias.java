package onMarket.presentacion.ModuloCategorias;

import java.util.ArrayList;
import onMarket.negocio.ModuloCategorias.AS_CategoriaImp;
import onMarket.negocio.ModuloCategorias.TCategoria;
import onMarket.presentacion.admin.IGUI;

public class ControladorCategorias {
	private AS_CategoriaImp modelo ; 
	private IGUI gui; 
	
	public ControladorCategorias(AS_CategoriaImp modelo, IGUI gui){
		this.modelo=modelo;		
		this.gui=gui;
	}
	
	public void accion(int evento, TCategoria datos){
	switch (evento){
		case EventoCategorias.ALTA_CATEGORIA:
			{
				TCategoria tCategoria= datos;
				Integer res= modelo.altaCategoria(tCategoria); 
				if (res != null){
					tCategoria.setId(res);
					gui.actualizar(EventoCategorias.RES_ALTA_CATEGORIA_OK, tCategoria);
				}else{
					gui.actualizar(EventoCategorias.RES_ALTA_CATEGORIA_KO, null);
					}
				break;
			}
		
		case EventoCategorias.BAJA_CATEGORIA:
			{
				Integer negocio=modelo.eliminaCategoria(datos.getNombre());
				Integer res= null;
				if (negocio!= null){
					res= datos.getId();
					gui.actualizar(EventoCategorias.RES_BAJA_CATEGORIA_OK, res);
				}else
					gui.actualizar(EventoCategorias.RES_BAJA_CATEGORIA_KO, res);
				break;
			}
			
		case EventoCategorias.MOSTRAR_CATEGORIAS:
			{
				ArrayList<TCategoria> res=modelo.obtenCategorias();
				if (res!= null)
					gui.actualizar(EventoCategorias.RES_MOSTRAR_CATEGORIAS_OK, res);
				else
					gui.actualizar(EventoCategorias.RES_MOSTRAR_CATEGORIAS_KO, res);
				break;
			}
		
		case EventoCategorias.EDITA_CATEGORIA:
			{
				Integer negocio=modelo.editaCategoria(datos); 
				TCategoria res= null;
				if (negocio!= null){
					res=datos;
					gui.actualizar(EventoCategorias.RES_EDITA_CATEGORIA_OK, res);
				}else
					gui.actualizar(EventoCategorias.RES_EDITA_CATEGORIA_KO, res);
				break;
			}
				
	 	}
	
	}
	
	public void setGui(IGUI g){
		this.gui=g;
	}
}
