package onMarket.negocio.ModuloSubcategorias;
/**
 * Clase que contiene todos los metodos necesarios para la implementacion de la clase y sus atributos
 * */
public class TSubcategoria {
	/**
	 * Atributos que contiene la clase:
	 * contiene el id de una subcategoria
	 * contiene el nombre de una subcategoria
	 * contiene si la subcategoria esta activa o no
	 * contien el id de la categoria a la que pertenece
	 * */
	private Integer id = -1;
	private String nombre;
	private Boolean activo = false;
	private Integer categoria;
	/**
	 * Constructoras
	 * */
	public TSubcategoria(String nombre){
		this.nombre=nombre;
	}

	public TSubcategoria(Integer id, String nombre, Boolean activo, Integer categoria ){
		this.id=id;
		this.nombre=nombre;
		this.activo=activo;
		this.categoria=categoria;
	}
	/**
	 * metodo que devuelve el id de una subcategoria
	 * */	
	public Integer getId() {
		return id;
	}
	/**
	 *metodo que permite editar el id
	 * */
	public void setId(Integer id) {
			this.id = id;
	}
	/**
	 *metodo que devuelve el nombre de la subcategoria
	 * */	
	public String getNombre() {
		return nombre;
	}
	/**
	 *metodo que permite modificar el nombre de la subcategoria
	 * */	
	public void setNombre(String nombre) {	
		this.nombre = nombre;
	}
	
	/**
	 *metodo que devuelve el el estado de la subcategoria si esta activo o no
	 * */
	public Boolean isActivo() {
			return activo;
		}
	/**
	 *metodo que permite modificar el estado de la subcategoria
	 * */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	/**
	 *metodo que devuelve cual es el id de la categoria a la que esta ligada la subcategoria
	 * */
	public Integer getCategoria() {
		return categoria;
	}
	/**
	 *metodo que permite editar cual es el id de la categoria a la que esta ligada la subcategoria
	 * */
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		String aux= new String(this.getNombre());
		return aux;
	}
}
