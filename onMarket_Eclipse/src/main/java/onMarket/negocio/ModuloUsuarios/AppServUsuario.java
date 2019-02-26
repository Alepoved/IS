package onMarket.negocio.ModuloUsuarios;

public interface AppServUsuario {
	/**
	 * Metodo que permite eliminar un usuario
	 * */
	public Integer eliminaUsuario(TUsuario tUsuario);
	/**
	 * Metodo que devuelve todos los usuarios con el nombre o el email dado por parametro
	 * */
	public TUsuario obtenUsuario(String nombre);//No se si es nombre o email MIRAR
	/**
	 * Metodo que permite editar un Usuario
	 * */
	public Integer editaUsuario(TUsuario tUsuario);
}
