package onMarket.integracion.AdminDB;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * Clase que implementa todos los metodos necesarios para la implementacion del 
 * acceso a la base de datos
 * */
public class SingleDataSource {
	/**
	 * Atributos privados
	 * */
	private static SingleDataSource instancia;
	private DataSource ds;
/**
 * Constructoras
 * */
	private SingleDataSource(){
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:mysql://localhost/onmarket");
		this.ds = cpds;
	}
/**
 * Metodo que permite instanciar una Single data Source
 * */	
	public static SingleDataSource obtenerInstancia(){ 
		if (instancia== null) 
			instancia = new SingleDataSource(); 
		return instancia;
	}
/**
 * metodo que devuelve el datasource
 * */
	public DataSource getDS(){
		return this.ds;
	}
}
