package onMarket.integracion.ModuloProductos;

import java.util.ArrayList;

import onMarket.negocio.ModuloProductos.TProducto;

public interface DAOProducto {
	
	/**
	 * Metodo que permite crear un producto
	 * @param tProducto datos del producto a dar de alta
	 * @return id del producto creado
	 */
	public Integer create(TProducto tProducto);

	/**
	 * Metodo que borra de la BD un producto
	 * @param id identificador del producto a eliminar
	 * @return numero de filas editadas de la base de datos
	 */
	public Integer delete(Integer id);

	/**
	 * Metodo que permite actualizar un producto
	 * @param tProducto datos ya actualizados del producto
	 * @return numero de filas modificadas de la base de datos
	 */
	public Integer update(TProducto tProducto);

	/**
	 * Metodo que devuelve un producto
	 * @param id identificador del producto que queremos
	 * @return tProducto con todos sus datos
	 */
	public TProducto readById(Integer id);

	/**
	 * Funcion que devuelve un producto con dichos nombre y marca
	 * @param nombre nombre del producto del que queremos informacion
	 * @param marca marca del producto del que queremos informacion
	 * @return tProducto con todos sus datos, null si se produce un error
	 */
	public TProducto readByNameAndMarca(String nombre, String marca);
	
	/**
	 * Metodo que devuelve todos los productos de una subcategoria
	 * @param id identificador de la subcategoria
	 * @return lista de productos pertenecientes a la subcategoria
	 */
	public ArrayList<TProducto> readBySubcategoria(Integer id);
}
