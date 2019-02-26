package onMarket.negocio.ModuloSubcategorias;

import java.util.ArrayList;

import onMarket.integracion.ModuloSubcategorias.DAOSubcategoria;
import onMarket.integracion.factorias.FactoriaIntegracion;
/**
 * Interfaz que contiene todos los metodos necesarios para trabajar con la clase As de subcategorias
 * */
public interface AppServSubcategoria {
	/**
	 * Metodo que permite crear una nueva subcategoria
	 * */
	public Integer altaSubcategoria(TSubcategoria tSubcategoria);
	/**
	 * Metodo que devuelve todas las subcategorias que contiene una categoria con el id dado por parametro
	 * */
	public ArrayList <TSubcategoria> obtenSubcategorias(Integer id);
	/**
	 * Metodo que permite eliminar una subcategoria con el id dado por parametro
	 * */
	public Integer eliminaSubcategoria(String nombre);
	/**
	 * Metodo que permite editar una subcategoria
	 * */
	public Integer editaSubcategoria(TSubcategoria tSubcategoria);
	/**
	 * Metodo que permite crear un DaoSubcateogoria
	 * */
	public DAOSubcategoria CrearDAOSubcategoria(FactoriaIntegracion factoria);
	
}
