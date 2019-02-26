/**
 * 
 */
package onMarket.negocio.ModuloPedidos;

import java.util.ArrayList;

import onMarket.negocio.ModuloUsuarios.TUsuario;

public interface AppServPedido {
  
  /*
   * Simula la realización de un pago
   * */
  public int pagoCorrecto(BOPago pago);
  
  /*
   * Reduce el Stock de los productos de un pedido usando un DAOProducto
   * debe hacerlo en un bucle,  si falla en alguno de ellos, debe deshacer el recorrido
   * y devolver false
   * */
  public boolean confirmaStock(BOCesta cesta);
  
  /*
   * Da de alta un pedido y sus Lineas de pedido basta con devolver el
   * Integer de inseción del DAO
   * */
  public Integer confirmaPedido(BOCesta cesta);
  
  /*
   * obtiene todos los pedidos activos de un usuario junto con la 
   * lista de ids de lineas de pedido que tiene como atributo 
   * */
  public ArrayList<TPedido> obtenerPedidos(TUsuario usuario);
  
  /*
   * obtiene todas las líneas específicas TLineaPedido de un pedido concreto
   * */
  public ArrayList<TLineaPedido> obtenerLineas(TPedido pedido);
  
  /*
   * Obtiene los nombres de los productos correspondientes a la lista de ids
   * de LineasPedido que tiene un pedido, se debe cuidar que ambas listas lleven
   *  el mismo orden.
   *  Se puede hacer con un DAOProductos o se puede hacer un join 
   * */
  public ArrayList<String> obtenerProductos(TPedido pedido);
  
}