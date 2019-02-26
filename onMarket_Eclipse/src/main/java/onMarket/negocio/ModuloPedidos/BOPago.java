package onMarket.negocio.ModuloPedidos;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOPago {
  private String titular;
  private String n_tarjeta;
  private String cvv;
  private Integer mes_caducidad;
  private Integer anio_caducidad;
  private Double importe;
  
  public BOPago(String titular, String n_tarjeta, String cvv, Integer mes_caducidad, 
			    Integer anio_caducidad,Double importe) {
	this.titular = titular;
	this.n_tarjeta = n_tarjeta;
	this.cvv = cvv;
	this.mes_caducidad = mes_caducidad;
	this.anio_caducidad = anio_caducidad;
	this.importe = importe;
  }
  
  public int pagar(){
	  /*
	   * Comprobar que un pago se realiza correctamente
	   * en nuestro caso simulamos, solamente comprobando unos campos correctos
	   * */
	  int flag= EstadoPago.CORRECTO;
	  if(this.titular==null) flag= EstadoPago.ERROR_TITULAR;
	  
	  String titular=this.titular;
	  String nuevoSinEspacio= titular.trim();
	  Pattern pat = Pattern.compile("[[A-ZñÑáéíóúÁÉÍÓÚ] +|[a-zñÑáéíóúÁÉÍÓÚ] +]+");
	  Matcher mat = pat.matcher(nuevoSinEspacio);
	  if (!mat.matches()) 
		  flag=EstadoPago.ERROR_TITULAR;
	  
	  if(this.n_tarjeta==null) flag= EstadoPago.ERROR_TARJETA;
	  String tarjeta=this.n_tarjeta;
	  String tarjetaSinEspacio= tarjeta.replaceAll("\\s","");
	  Pattern pat1 = Pattern.compile("[0-9]{16}");
	  Matcher mat1 = pat1.matcher(tarjetaSinEspacio);
	  if (!mat1.matches()) 
		  flag=EstadoPago.ERROR_TARJETA;
	  
	  if(this.cvv==null) flag= EstadoPago.ERROR_CVV;
	  String cvv=this.cvv;
	  String cvvSinEspacio= cvv.replaceAll("\\s","");
	  Pattern pat2 = Pattern.compile("[0-9]{3}");
	  Matcher mat2 = pat2.matcher(cvvSinEspacio);
	  if (!mat2.matches()) 
		  flag=EstadoPago.ERROR_CVV;
	  
	  if(this.mes_caducidad==null) flag= EstadoPago.ERROR_CADUCIDAD;
	  if(this.mes_caducidad<1 || this.mes_caducidad>12 ) 
		  flag= EstadoPago.ERROR_CADUCIDAD;
	 
	  if(this.anio_caducidad==null) flag= EstadoPago.ERROR_CADUCIDAD;
	  Calendar fecha = new GregorianCalendar();
	  int anio = fecha.get(Calendar.YEAR);
	  if(this.anio_caducidad<anio || this.anio_caducidad>anio+10 ) 
		  flag= EstadoPago.ERROR_CADUCIDAD;
	  
	  if(this.importe==null || this.importe <=0) 
		  flag= EstadoPago.ERROR_IMPORTE;
	  
	  return flag;
	  
	  
  }
  
  
}
