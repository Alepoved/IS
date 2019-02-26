package onMarket.negocio.ModuloProductos;

public class TProducto {

	private Integer id = -1;
	private String nombre = "";
	private String marca = "";
	private Double precio = -1.0;
	private String peso_vol = "";
	private Integer stock = -1;
	private String foto = "";
	private Boolean activo = false;
	private Integer subcategoria;
	
	/**
	 * Constructoras
	 * */
	public TProducto(String nombre, String marca){
		this.nombre = nombre;
		this.marca = marca;
	}
	
	public TProducto(Integer id, String nombre, Boolean activo, Integer subcategoria,
			String marca, Double precio, Integer stock, String peso_vol, String foto){
		this.id=id;
		this.nombre=nombre;
		this.marca = marca;
		this.precio = precio;
		this.peso_vol = peso_vol;
		this.stock = stock;
		this.foto = foto;
		this.activo=activo;
		this.subcategoria=subcategoria;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
			this.id = id;
	}
		
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {	
		this.marca = marca;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {	
		this.nombre = nombre;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
			this.precio = precio;
	}
	
	public String getPesoVol() {
		return peso_vol;
	}
	
	public void setPesoVol(String peso_vol) {	
		this.peso_vol = peso_vol;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {	
		this.foto = foto;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
			this.stock = stock;
	}
	
	public Boolean getEstado() {
		return activo;
	}
	
	public void setEstado(Boolean activo) {
		this.activo = activo;
	}
	
	public Integer getSubcategoria() {
		return subcategoria;
	}
	
	public void setSubcategoria(Integer subcategoria) {
		this.subcategoria = subcategoria;
	}
}
