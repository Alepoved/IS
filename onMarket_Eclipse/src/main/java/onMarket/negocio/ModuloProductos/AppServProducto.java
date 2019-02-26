package onMarket.negocio.ModuloProductos;

import java.util.ArrayList;

public interface AppServProducto {

	/**
	 * Permite dar de alta un nuevo producto
	 * @param tProducto producto con los datos nuevos ya introducidos
	 * @return codigo del producto que acabamos de crear
	 */
	public Integer altaProducto(TProducto tProducto);
	
	/**
	 * Coge todos los productos existentes en la base de datos
	 * @param id identificador de la subcategoria de la que queremos los productos
	 * @return un array con todos los productos existentes
	 */
	public ArrayList<TProducto> obtenProductos(Integer id);
	
	/**
	 * Elimina un producto de entre todos los existentes
	 * @param id codigo del producto que queremos eliminar
	 * @return numero de filas modificadas de la base de datos
	 */
	public Integer bajaProducto(Integer id);
	
	/**
	 * Permite editar un producto con los datos proporcionados
	 * @param tProducto producto con los datos modificados
	 * @return numero de filas modificadas de la base de datos
	 */
	public Integer modificarProducto(TProducto tProducto);
	
	/**
	 * Metodo que permite aumentar el stock de un producto cuando lo necesite
	 * @param tProducto producto con los datos modificados
	 * @return numero de filas modificadas de la base de datos
	 */
	public Integer reponerProducto(TProducto tProducto);	
}
