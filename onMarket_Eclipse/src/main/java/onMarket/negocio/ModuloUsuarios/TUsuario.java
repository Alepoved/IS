package onMarket.negocio.ModuloUsuarios;

import java.sql.Date;

public class TUsuario {
	private Integer id=-1;
	private Integer telefono;
	private boolean es_Admin;
	private boolean Activo=false;
	private Date fechaNacimiento;
	private String Email;
	private String Password;
	private String Nombre;
	private String Apellido;
	private String Direccion;
	private String foto;
	private String n_Tarjeta;
	private String dir_Entrega;
	
	
	public TUsuario(Integer id,Integer telefono,boolean es_Admin,boolean Activo,Date fechaNacimiento,
			String Email,String Password,String Nombre,String Apellido,String Direccion,String foto,String n_Tarjeta,String dir_Entrega){
		this.Activo=Activo;
		this.Apellido=Apellido;
		this.dir_Entrega= dir_Entrega;
		this.Direccion=Direccion;
		this.Email=Email;
		this.es_Admin=es_Admin;
		this.fechaNacimiento=fechaNacimiento;
		this.foto=foto;
		this.id=id;
		this.n_Tarjeta=n_Tarjeta;
		this.Nombre=Nombre;
		this.Password=Password;
		this.telefono=telefono;
	}

	public TUsuario(Integer telefono,boolean es_Admin,boolean Activo,Date fechaNacimiento,
			String Email,String Password,String Nombre,String Apellido,String Direccion,String foto,String n_Tarjeta,String dir_Entrega){
		this.Activo=Activo;
		this.Apellido=Apellido;
		this.dir_Entrega= dir_Entrega;
		this.Direccion=Direccion;
		this.Email=Email;
		this.es_Admin=es_Admin;
		this.fechaNacimiento=fechaNacimiento;
		this.foto=foto;
		this.n_Tarjeta=n_Tarjeta;
		this.Nombre=Nombre;
		this.Password=Password;
		this.telefono=telefono;
	}

	public TUsuario() {
		this.es_Admin=false;
	}
	
	public TUsuario(String nombre) {
		this.Nombre=nombre;
	}


	public boolean isActivo() {
		return this.Activo;
	}


	public String getNombre() {
		return this.Nombre;
	}


	public String getMail() {
		return this.Email;
	}


	public String getPassword() {
		return this.Password;
	}


	public boolean getEsAdmin() {
		return this.es_Admin;
	}


	public String getApellido() {
		return this.Apellido;
	}


	public String getDireccion() {
		return this.Direccion;
	}


	public Integer getTelefono() {
		return this.telefono;
	}


	public Date getFecha_nac() {
		return this.fechaNacimiento;
	}


	public String getFoto() {
		return this.foto;
	}


	public String getN_tarjeta() {
		return this.n_Tarjeta;
	}


	public String getDireccionEntrega() {
		return this.dir_Entrega;
	}


	public Integer getId() {
		return this.id;
	}


	public void setActivo(boolean activo) {
		this.Activo=activo;	
	}


	public void setApellido(String apellido) {
		this.Apellido=apellido;
	}


	public void setDireccion(String direccion) {
		this.Direccion=direccion;
	}


	public void setDireccionEntrega(String direccionEntrega) {
		this.dir_Entrega=direccionEntrega;
	}


	public void setFecha_nac(Date fecha_nac) {
		this.fechaNacimiento=fecha_nac;
	}


	public void setFoto(String foto) {
		this.foto=foto;
	}


	public void setMail(String mail) {
		this.Email=mail;
	}


	public void setNombre(String nombre) {
		this.Nombre=nombre;
	}


	public void setPassword(String password) {
		this.Password=password;
	}


	public void setTelefono(Integer telefono) {
		this.telefono=telefono;
	}


	public void setId(Integer id) {
		this.id=id;
		
	}
	
	public void setNTarjeta(String nTarjeta) {
		this.n_Tarjeta=nTarjeta;
		
	}
	
	
}
