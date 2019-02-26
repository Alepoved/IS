package onMarket.negocio.ModuloCategorias;

import java.util.ArrayList;

import onMarket.integracion.ModuloCategorias.DAOCategoria;
import onMarket.integracion.factorias.FactoriaIntegracion;
/**
 * Interfaz que implementa todos los metodos necesarios para el uso de la AppServCategoria
 * */
public interface AppServCategoria {
	/**
	 * Metodo que permite dar de alta una ueva categoria
	 * */
	public Integer altaCategoria(TCategoria tCategoria);
	/**
	 * Metodo que deuelve un array de las categorias existentes
	 * */	
	public ArrayList<TCategoria> obtenCategorias();
	/**
	 * Metodo que elimina una categoria daod el parametro nombre que contiene dicha categoria
	 * */	
	public Integer eliminaCategoria(String nombre);
	/**
	 * Metodo que permite editar una categoria que recibe por parametro
	 * */
	public Integer editaCategoria(TCategoria tCategoria);
	/**
	 * Metodo que permite crear un DaoCategoria
	 * */	
	public DAOCategoria crearDAOCategorias(FactoriaIntegracion factoria);
}