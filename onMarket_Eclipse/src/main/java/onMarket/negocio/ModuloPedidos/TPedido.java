/**
 * 
 */
package onMarket.negocio.ModuloPedidos;

import java.sql.Date;
import java.util.ArrayList;

public class TPedido {
	private Integer id_pedido = -1;
	private Integer id_usuario;
	private EstadoPedido estado;
	private String dir_entrega;
	private Date fecha_entrega;
	private Integer horario_ini;
	private Integer horario_fin;
	private String telefono;
	private Double total_pagar;
	private Boolean activo;
	private ArrayList<Integer> lineas_pedido =new ArrayList<Integer>();
	
	public TPedido(Integer id_pedido, Integer id_usuario, EstadoPedido estado, String dir_entrega, Date fecha_entrega,
			Integer horario_ini, Integer horario_fin, String telefono, Double total_pagar, Boolean activo,
			ArrayList<Integer> lineas_pedido) {
		this.id_pedido = id_pedido;
		this.id_usuario = id_usuario;
		this.estado = estado;
		this.dir_entrega = dir_entrega;
		this.fecha_entrega = fecha_entrega;
		this.horario_ini = horario_ini;
		this.horario_fin = horario_fin;
		this.telefono = telefono;
		this.total_pagar = total_pagar;
		this.activo = activo;
		this.lineas_pedido = lineas_pedido;
	}

	public TPedido(Integer id_usuario, EstadoPedido estado, String dir_entrega, Date fecha_entrega,
			Integer horario_ini, Integer horario_fin, String telefono, Double total_pagar, Boolean activo
			) {
		this.id_usuario = id_usuario;
		this.estado = estado;
		this.dir_entrega = dir_entrega;
		this.fecha_entrega = fecha_entrega;
		this.horario_ini = horario_ini;
		this.horario_fin = horario_fin;
		this.telefono = telefono;
		this.total_pagar = total_pagar;
		this.activo = activo;
	}
	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public String getDir_entrega() {
		return dir_entrega;
	}

	public void setDir_entrega(String dir_entrega) {
		this.dir_entrega = dir_entrega;
	}

	public Date getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public Integer getHorario_ini() {
		return horario_ini;
	}

	public void setHorario_ini(Integer horario_ini) {
		this.horario_ini = horario_ini;
	}

	public Integer getHorario_fin() {
		return horario_fin;
	}

	public void setHorario_fin(Integer horario_fin) {
		this.horario_fin = horario_fin;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Double getTotal_pagar() {
		return total_pagar;
	}

	public void setTotal_pagar(Double total_pagar) {
		this.total_pagar = total_pagar;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public ArrayList<Integer> getLineas_pedido() {
		return lineas_pedido;
	}

	public Integer getLineas_pedidoSize(){
		return this.lineas_pedido.size();
	}
	
	public Integer getLinea_pedidos_iesima(int index){
		return this.lineas_pedido.get(index);
	}
	
	public void deleteLinea_pedidos_iesima(int index){
		this.lineas_pedido.remove(index);
	}
	
	public void addLineas_pedidoAt(int index, Integer elem ) {
		this.lineas_pedido.add(index, elem);
	}

}