package onMarket.negocio.ModuloCategorias;

import java.util.ArrayList;

import onMarket.integracion.ModuloCategorias.DAOCategoria;
import onMarket.integracion.factorias.FactoriaIntegracion;
import onMarket.negocio.ModuloSubcategorias.AS_SubcategoriaImp;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
/**
 * Clase que implementa todos los metodos existentes de la interfaz AppServCategoria
 * */
public class AS_CategoriaImp implements AppServCategoria {
	/**
	 * Constructora
	 * */
	public AS_CategoriaImp() {

	}

	/**
	 * Devuelve el identificador de la nueva categoria creada si no existe
	 * o de la categoria actualizada si estaba inactiva
	 */
	public Integer altaCategoria(TCategoria tCategoria) {
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = this.crearDAOCategorias(factoria);
		TCategoria cat = daoCat.readByName(tCategoria.getNombre());
		TCategoria res = null;

		if (cat != null) {
			if (cat.getActivo()) {
				return null;
			} else {
				cat.setActivo(true);
				if (daoCat.update(cat) != null) {
					res = daoCat.readByName(cat.getNombre());
					if (res != null) {
						return res.getId();
					} else
						return null;

				} else
					return null;
			}
		} else {

			if (daoCat.create(tCategoria) != null) {
				res = daoCat.readByName(tCategoria.getNombre());
				if (res != null) {
					return res.getId();
				} else
					return null;
			} else
				return null;
		}

	}
	
	/**
	 * Metodo que permite eliminar una categoria dado su nombre si existe en el sistema
	 * */
	public Integer eliminaCategoria(String nombre) {

		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = this.crearDAOCategorias(factoria);
		TCategoria cat = daoCat.readByName(nombre); // comprobamos si exite dicha categoria
		if (cat != null) {
			if (cat.getActivo()) {
				AS_SubcategoriaImp appServSubcat = new AS_SubcategoriaImp();
				ArrayList<TSubcategoria> subs = appServSubcat.obtenSubcategorias(cat.getId());
				int i = 0;
				while (i < subs.size()) {
					appServSubcat.eliminaSubcategoria(subs.get(i).getNombre());
					++i;
				}
				// Por ultimo borramos la categoria
				cat.setActivo(false);
				return daoCat.update(cat);
			}
		}
		return null;

	}

	/**
	 * Metodo que permite editar una categoria que se recibe por parametro
	 * */
	public Integer editaCategoria(TCategoria tCategoria) {

		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = this.crearDAOCategorias(factoria);
		TCategoria cat = daoCat.readById(tCategoria.getId());
		if (cat != null){
			if (cat.getActivo()){
				cat = daoCat.readByName(tCategoria.getNombre());
				if (cat == null){ //si es nulo es que no existe en la BD luego podemos modificar ese nombre
					return daoCat.update(tCategoria);
				}
				return null;
			}
		}
		return null;

	}
	/**
	 * Metodo que permite crear un DaoCategoria
	 * */
	public DAOCategoria crearDAOCategorias(FactoriaIntegracion factoria) {
		return factoria.generaDAOCategoria();
	}
	
	/**
	 * Metodo que devuelve una arrayKist que contiene todas las categorias existentes en el sistema
	 * */
	public ArrayList<TCategoria> obtenCategorias() {

		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOCategoria daoCat = this.crearDAOCategorias(factoria);
		return daoCat.readAll();

	}

}