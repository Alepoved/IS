package onMarket.negocio.ModuloSubcategorias;

import java.util.ArrayList;

import onMarket.integracion.ModuloProductos.DAOProducto;
import onMarket.integracion.ModuloSubcategorias.DAOSubcategoria;
import onMarket.integracion.factorias.FactoriaIntegracion;
import onMarket.negocio.ModuloProductos.AS_ProductoImp;
import onMarket.negocio.ModuloProductos.TProducto;
/**
 * Clase que implementan todos los metodos necesarios para trabajar con la As de subcategoria
 * */
public class AS_SubcategoriaImp implements AppServSubcategoria{
	/**
	 * Devuelve el ID de la nueva subcategoria creada o de la actualizada si existe y esta
	 * inactiva
	 * */
	@Override
	public Integer altaSubcategoria(TSubcategoria tSubcategoria) {
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOSubcategoria daoSub = this.CrearDAOSubcategoria(factoria); 
		TSubcategoria sub = daoSub.readByName(tSubcategoria.getNombre());
		TSubcategoria res = null;

		if (sub != null) {
			if (sub.isActivo()) {
				return null;
			} else {
				tSubcategoria.setActivo(true);
				tSubcategoria.setId(sub.getId());
				if (daoSub.update(tSubcategoria) != null) {
					res = daoSub.readByName(tSubcategoria.getNombre());
					if (res != null) {
						return res.getId();
					} else
						return null;

				} else
					return null;
			}
		} else {

			if (daoSub.create(tSubcategoria) != null) {
				res = daoSub.readByName(tSubcategoria.getNombre());
				if (res != null) {
					return res.getId();
				} else
					return null;
			} else
				return null;
		}
	}
	/**
	 * Metodo que devuelve todas las subcategorias que tienen como categoria un id que se recibe por parametro
	 * */
	@Override
	public ArrayList <TSubcategoria> obtenSubcategorias(Integer id) {
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOSubcategoria daoSubcat = this.CrearDAOSubcategoria(factoria);
		return daoSubcat.readByCategory(id);
	}
	/**
	 * Elimina la subcategoria con ese nombre
	 * @param nombre nombre de la subcategoria que queremos eliminar
	 * @return numero de filas modificadas de la base de datos
	 * */
	@Override
	public Integer eliminaSubcategoria(String nombre) {
		
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOSubcategoria daoSubcat = this.CrearDAOSubcategoria(factoria);
		TSubcategoria subcat = daoSubcat.readByName(nombre); // comprobamos si exite dicha categoria
		if (subcat != null) {
			if (subcat.isActivo()) {
				AS_ProductoImp appServProd = new AS_ProductoImp();
				ArrayList<TProducto> prods = appServProd.obtenProductos(subcat.getId());
				int i = 0;
				while (i < prods.size()) {
					appServProd.bajaProducto(prods.get(i).getId());
					++i;
				}
				// Por ultimo borramos la subcategoria
				subcat.setActivo(false);
				return daoSubcat.update(subcat);// Si existe, miramos si esta activa,en cuyo caso modificaremos dicho atributo
			}
		}
		return null; // devolvemos error en caso de que no exista o no este activo
	}
	
	
	/**
	 * Modifica los datos de una subcategoria
	 * @param tSubcategoria subcategoria con los datos nuevos a modificar y el mismo id del anterior
	 * @return numero de filas modificadas de la base de datos, null en caso de que no se haya podido actualizar
	 */
	@Override
	public Integer editaSubcategoria(TSubcategoria tSubcategoria) {
		
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOSubcategoria daoSubcat = this.CrearDAOSubcategoria(factoria);
		TSubcategoria subcat = daoSubcat.readByName(tSubcategoria.getNombre());
		//Si la subcat es nula, es que no existe en la BD y por tanto podemos cambiarle el nombre
		if (subcat == null){
			//CASO1: queremos editar solo el nombre
			//CASO2: queremos editar el nombre y la categoria
			//Se resuelven simplemente actualizando
			return daoSubcat.update(tSubcategoria);
		}
		//Mientras que si es distinta de nula, tenemos una subcategoria con el mismo nombre que el que le pasamos
		else{
			if ((int)subcat.getCategoria() != (int)tSubcategoria.getCategoria()){
				//CASO3: queremos editar la categoria de una subcategoria
				if ((int)subcat.getId() == (int)tSubcategoria.getId()){
					AS_ProductoImp appServProd = new AS_ProductoImp();
					DAOProducto daoProd = factoria.generaDAOProducto();
					ArrayList<TProducto> prods = appServProd.obtenProductos(subcat.getId());
					ArrayList<TProducto> nuevos = prods;
					int i = 0;
					while (i < prods.size()) {
						daoProd.delete(prods.get(i).getId());
						++i;
					}
					
					daoSubcat.delete(subcat.getId());
					Integer resultado = daoSubcat.create(tSubcategoria);
					if (resultado != null) {
						TSubcategoria res = daoSubcat.readByName(tSubcategoria.getNombre());
						if (res != null) {
							Integer num =  res.getId();
							i = 0;
							while (i < nuevos.size()){
								nuevos.get(i).setSubcategoria(num);
								daoProd.create(nuevos.get(i));
								++i;
							}
						} else
							return null;
					}
					return resultado;
				}
				//CASO4: queremos editar la categoria de una subcategoria que ya existe en otro sitio
				else{
					daoSubcat.delete(subcat.getId());
					return daoSubcat.update(tSubcategoria);
				}
			}
			//CASO5: tenemos una con ese mismo nombre pero esta inactiva
			else if(!subcat.isActivo() && tSubcategoria.isActivo()){
				daoSubcat.delete(subcat.getId());
				return daoSubcat.update(tSubcategoria);
			}
			//CASO6: otros casos
			return null;
		}

	}
	/**
	 * Metodo que crea un DaoSubcategoria 
	 * */
	@Override
	public DAOSubcategoria CrearDAOSubcategoria(FactoriaIntegracion factoria) {
		return factoria.generaDAOSubcategoria();
	}


}
