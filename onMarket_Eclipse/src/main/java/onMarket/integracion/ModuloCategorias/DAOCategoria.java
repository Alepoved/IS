package onMarket.integracion.ModuloCategorias;

import java.util.ArrayList;

import onMarket.negocio.ModuloCategorias.TCategoria;
/**
 * Interfaz que implementa los metodos necesarios para trabajar con la clase DaoCategoria
 * */
public interface DAOCategoria {
	
	/**
	 * Metodo que perite crear una categoria
	 * */
	public Integer create(TCategoria tCategoria);
	/**
	 * Metodo que permite eleminar una categoria
	 * */
	public Integer delete(Integer id);
	/**
	 * Metodo que permite actualizar una categoria
	 * */
	public Integer update(TCategoria tCategoria);
	/**
	 * Metodo que devuelve todas las catgorias existentes en la base de datos
	 * */
	public ArrayList<TCategoria> readAll();
	/**
	 * Metodo que devuelve una categoria cuyo nombre sea el dado por el parametro nombre
	 * */
	public TCategoria readByName(String nombre);
	
	/**
	 * Metodo que devuelve una categoria cuyo id sea el dado por el parametro id
	 * */
	public TCategoria readById(Integer id);
}