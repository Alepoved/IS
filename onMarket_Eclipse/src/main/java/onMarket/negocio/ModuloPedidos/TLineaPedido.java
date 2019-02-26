/**
 * 
 */
package onMarket.negocio.ModuloPedidos;

public class TLineaPedido {
	private Integer id_linea = -1;
	private Integer id_pedido;
	private Integer id_producto;
	private Integer cantidad;
	private Double precio_linea;
	
	public TLineaPedido(Integer id_linea, Integer id_pedido, Integer id_producto, 
						Integer cantidad,Double precio_linea) {
		this.id_linea = id_linea;
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.precio_linea = precio_linea;
	}

	public TLineaPedido(Integer id_pedido, Integer id_producto, Integer cantidad, 
						Double precio_linea) {
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.precio_linea = precio_linea;
	}

	public Integer getId_linea() {
		return id_linea;
	}

	public void setId_linea(Integer id_linea) {
		this.id_linea = id_linea;
	}

	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio_linea() {
		return precio_linea;
	}

	public void setPrecio_linea(Double precio_linea) {
		this.precio_linea = precio_linea;
	}
	
	
}