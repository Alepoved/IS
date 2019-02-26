package onMarket.negocio.ModuloProductos;

import java.util.ArrayList;

import onMarket.integracion.ModuloProductos.DAOProducto;
import onMarket.integracion.factorias.FactoriaIntegracion;

public class AS_ProductoImp implements AppServProducto {

	@Override
	public Integer altaProducto(TProducto tProducto) {
		if (tProducto != null && tProducto.getNombre() != null && tProducto.getMarca() != null
				&& tProducto.getEstado() != null && tProducto.getStock() != null
				&& tProducto.getSubcategoria() != null && tProducto.getPrecio() != null
				&& tProducto.getPesoVol() != null && tProducto.getFoto() != null){
			FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
			DAOProducto daoProd = factoria.generaDAOProducto(); 
			TProducto prod = daoProd.readByNameAndMarca(tProducto.getNombre(), tProducto.getMarca());
			TProducto res = null;
			if (prod != null) {
				if (prod.getEstado())
					return null;
				else{
					tProducto.setEstado(true);
					if (daoProd.update(tProducto)!= null) {
						res = daoProd.readById(tProducto.getId());
						if (res != null)
							return res.getId();
					}
					return null;
				}
			}
			else {
				if (daoProd.create(tProducto) != null) {
					res = daoProd.readByNameAndMarca(tProducto.getNombre(), tProducto.getMarca());
					if (res != null)
						return res.getId();
				}
				return null;
			}
		}
		return null;
	}

	@Override
	public Integer bajaProducto(Integer id) {
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOProducto daoProd = factoria.generaDAOProducto();
		TProducto prod = daoProd.readById(id);
		if (prod != null){
			if (prod.getEstado()){
				prod.setEstado(false);
				return daoProd.update(prod);
			}
		}
		return null;
	}
	
	@Override
	public ArrayList<TProducto> obtenProductos(Integer id) {
		if (id != null){
			FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
			DAOProducto daoProd = factoria.generaDAOProducto();
			return daoProd.readBySubcategoria(id);
		}
		return null;
	}

	@Override
	public Integer modificarProducto(TProducto tProducto) {
		if (tProducto != null && tProducto.getEstado() != null
				&& tProducto.getSubcategoria() != null && tProducto.getPrecio() != null
				&& tProducto.getStock() != null){
			FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
			DAOProducto daoProd = factoria.generaDAOProducto();
			TProducto prod = daoProd.readById(tProducto.getId());
			TProducto resultado = null;
			if (prod != null){
				if (prod.getEstado()){
					resultado = daoProd.readByNameAndMarca(tProducto.getNombre(), tProducto.getMarca());
					if (resultado == null)
						return daoProd.update(tProducto);
					else{
						if ((int)tProducto.getId() == (int)resultado.getId())
							return daoProd.update(tProducto);
						else{
							if ((int) tProducto.getSubcategoria() != (int) resultado.getSubcategoria()
									|| !resultado.getEstado() && tProducto.getEstado()){
								daoProd.delete(resultado.getId());
								return daoProd.update(tProducto);
							}	
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Integer reponerProducto(TProducto tProducto) {
		if(tProducto != null && tProducto.getStock() != null){
			FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
			int stock;
			DAOProducto daoProd = factoria.generaDAOProducto();
			TProducto prod = daoProd.readById(tProducto.getId());
			if (prod != null){
				if (prod.getEstado()){
					stock = prod.getStock();
					tProducto.setStock(tProducto.getStock() + stock);
					return daoProd.update(tProducto);
				}
				else
					return null;
			}
			else
				return null;
			}
		else return null;
	}	

}
