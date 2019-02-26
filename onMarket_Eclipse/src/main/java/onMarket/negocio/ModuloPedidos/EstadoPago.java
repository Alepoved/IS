package onMarket.negocio.ModuloPedidos;

public class EstadoPago {
		
		public static final int CORRECTO= 400; 
		public static final int ERROR_TITULAR= 401;
		public static final int ERROR_TARJETA= 402;
		public static final int ERROR_CVV= 403;
		public static final int ERROR_CADUCIDAD= 404;
		public static final int ERROR_IMPORTE= 405;
		public static final int ERROR_PAGO= 406;

		public String toString(int e){
			if (e == EstadoPago.CORRECTO) return "Pago Correcto";
			if (e == EstadoPago.ERROR_TITULAR) return "Error Campo Titular";
			if (e == EstadoPago.ERROR_TARJETA) return "Error Campo Tarjeta";
			if (e == EstadoPago.ERROR_CADUCIDAD) return "Error Campos Caducidad";
			if (e == EstadoPago.ERROR_IMPORTE) return "error Importe";
			if (e == EstadoPago.ERROR_PAGO) return "Error de Pago ";
			if (e == EstadoPago.ERROR_CVV) return "Error Campo CVV";
			return null;
		}

}
