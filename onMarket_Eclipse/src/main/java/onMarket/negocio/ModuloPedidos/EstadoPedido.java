
package onMarket.negocio.ModuloPedidos;

public enum EstadoPedido {
	encurso,
	enviado,
	cancelado,
	entregado;
	
	public String toString(EstadoPedido e){
		if (e == encurso) return "encurso";
		if (e == enviado) return "enviado";
		if (e == cancelado) return "cancelado";
		if (e == entregado) return "entregado";
		return null;
	}
}