package onMarket.integracion.ModuloSubcategorias;

import java.util.ArrayList;

import onMarket.negocio.ModuloSubcategorias.TSubcategoria;

/**
 * Interfaz que contiene todos los metodos que se vana a usar para la gestion de los DaoSubcategorias
 * */
public interface DAOSubcategoria {
	/**
	 * Metodo que crea una Subcategoria
	 * */
	public Integer create(TSubcategoria tSubcategoria);
	/**
	 * Metodo que elimina una subcategoria
	 * */	
	public Integer delete(Integer id);
	/**
	 * Metodo que actualiza una subcategoria
	 * */
	public Integer update(TSubcategoria tSubcategoria);
	/**
	 * Metodo que devuelve todas las subcateogrias existentes
	 * */
	public ArrayList<TSubcategoria> readAll();
	/**
	 * Metodo que devuelve un array de todas las subcategorias existentes dentro de una categoria
	 * */
	public ArrayList<TSubcategoria> readByCategory(Integer id_categoria);
	/**
	 * Metodo que devuelve una subcategoria con el nombre dado por parametro
	 * */
	public TSubcategoria readByName(String nombre);
}
