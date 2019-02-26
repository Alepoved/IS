package onMarket.negocio.Seguridad;

import onMarket.negocio.ModuloUsuarios.TUsuario;

public interface AppServSeguridad {
	/**
	 * metodo que inicio sesion de un usuario
	 * */
	public TUsuario Login(String Usuario,String Contraseña);
	/**
	 * metodo que da de alta un nuevo usuario en la aplicicacion
	 * */
	public Integer altaUsuario(TUsuario tUsuario);
	
}
