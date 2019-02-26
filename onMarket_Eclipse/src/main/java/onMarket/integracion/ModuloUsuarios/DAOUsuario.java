package onMarket.integracion.ModuloUsuarios;

import java.util.ArrayList;

import onMarket.negocio.ModuloUsuarios.TUsuario;

public interface DAOUsuario {
	/**
	 * Metodo que crea un Usuario
	 * */
	public Integer create(TUsuario tUsuario);
	/**
	 * Metodo que elimina un Usuario
	 * */	
	public Integer delete(Integer id);
	/**
	 * Metodo que actualiza un usuario
	 * */
	public Integer update(TUsuario tUsuario);
	/**
	 * Metodo que devuelve un array de todos los usuarios existentes que tengan el mail igual a email
	 * */
	public TUsuario readByEmail(String email);
	/**
	 * Metodo que devuelve una lista de usuarios del tipo especificado
	 * */
	public ArrayList<TUsuario> readByType(String es_Admin);
	
}
