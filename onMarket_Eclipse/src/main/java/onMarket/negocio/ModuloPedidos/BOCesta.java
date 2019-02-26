package onMarket.negocio.ModuloPedidos;

import java.util.ArrayList;

public class BOCesta {
	private TPedido pedido;
	private ArrayList<TLineaPedido> lineas;
	
	public BOCesta(){
		pedido= null;
		lineas= new ArrayList<TLineaPedido> ();
	}
	
	public BOCesta(TPedido p){
		this.pedido= p;
		lineas= new ArrayList<TLineaPedido> ();
	}

	public TPedido getPedido() {
		return pedido;
	}

	public void setPedido(TPedido pedido) {
		this.pedido = pedido;
	}

	public ArrayList<TLineaPedido> getLineas() {
		return lineas;
	}

	public Integer getLineasSize(){
		return this.lineas.size();
	}
	
	public TLineaPedido getLineaIesima(int index){
		return this.lineas.get(index);
	}
	
	public void borrarLineaIesima(int index){
		this.lineas.remove(index);
	}
	
	public void agregarLineaAt(int index, TLineaPedido elem ) {
		this.lineas.add(index, elem);
	}

	public void reemplazarLineaAt(int index, TLineaPedido elem ) {
		this.lineas.set(index, elem);
	}
}
