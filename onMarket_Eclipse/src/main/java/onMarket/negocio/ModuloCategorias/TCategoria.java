package onMarket.negocio.ModuloCategorias;

import java.util.ArrayList;

/**
 * Clase que implementa todos los metodos necesarios para el uso de una clase TCategoria
 * */
public class TCategoria {
	/**
	 * Atributos que contiene la clase:
	 * contiene el id de una categoria
	 * contiene el nombre de una categoria
	 * contiene si la categoria esta activa o no
	 * */
	private Integer id =-1;
	private String nombre;
	private Boolean activo = false;
	private ArrayList<Integer> subcategorias = new ArrayList<Integer>();

	/**
	 * Constructoras
	 * */
	public TCategoria(String nombre){
		this.nombre=nombre;
	}

	public TCategoria(Integer id, String nombre, Boolean activo){
		this.id=id;
		this.nombre=nombre;
		this.setActivo(activo);
	}
	/**
	 * Metodo que devuelve el id de una categoria
	 * */
	public Integer getId() {
		return id;
	}
	/**
	 * Metodo que modifica el id de una categoria
	 * */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Metodo que devuelve el nombre de la categoria
	 * */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo que prmite modificar el nombre de una categoria
	 * */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo que devuelve el estado de activo o no de una categoria
	 * */
	public Boolean getActivo() {
		return activo;
	}
	/**
	 * Metodo  que permite modificar el estado de una categoria 
	 * */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Devuelve la lista de subcategorias de la categoria
	 * @return lista de subcategorias
	 */
	public ArrayList<Integer> getSubcategorias(){
		return this.subcategorias;
	}
	
	/**
	 * Inserta una subcategoria en la lista
	 * @param sub subcategoria que queremos insertar
	 */
	public void setSubcategoria(Integer sub){
		this.subcategorias.add(sub);
	}

	@Override
	public String toString() {
		String aux= new String(this.getNombre());
		return aux;
	}
   
	
		
}
