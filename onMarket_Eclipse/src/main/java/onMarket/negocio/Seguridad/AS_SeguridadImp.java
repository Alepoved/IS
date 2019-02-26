package onMarket.negocio.Seguridad;

import onMarket.integracion.ModuloUsuarios.DAOUsuario;
import onMarket.integracion.factorias.FactoriaIntegracion;
import onMarket.negocio.ModuloUsuarios.TUsuario;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.commons.codec.digest.DigestUtils;

public class AS_SeguridadImp implements AppServSeguridad{

	@Override
	public TUsuario Login(String Usuario, String Contraseña) {
		FactoriaIntegracion fac = FactoriaIntegracion.obtenerInstancia();
		DAOUsuario daoUser = fac.generaDAOUsuario();
		TUsuario devolver = null;
		
		if(Usuario != null && Contraseña != null)
		{
			TUsuario datosUsuario = daoUser.readByEmail(Usuario);
			if(datosUsuario != null)
			{
				String claveEncriptada = encriptarClave(Contraseña);
				if(claveEncriptada.equals(datosUsuario.getPassword()))
				{
					if(datosUsuario.isActivo())
						devolver = datosUsuario; 
				}
			}
		}
		return devolver;
	}

	@Override
	public Integer altaUsuario(TUsuario copia) {
		
		Integer devolver = null;
		Integer resultado = new Integer(0);
		FactoriaIntegracion fac = FactoriaIntegracion.obtenerInstancia();
		fac.generaDAOUsuario();
		DAOUsuario daoUser = fac.generaDAOUsuario();
		String dirFoto = null;
		if(copia != null && copia.isActivo() && copia.getPassword() != null && copia.getMail() != null)
		{
			copia.setPassword(encriptarClave(copia.getPassword()));
			TUsuario datosUsuario = daoUser.readByEmail(copia.getMail());
			dirFoto = copia.getFoto();
			copia.setFoto(copia.getMail());
			if(datosUsuario == null) // no existe
			{
				resultado = daoUser.create(copia);
				if(resultado != null)
				{
					try 
					{
						File f = new File(dirFoto);
						if(f.exists() && !f.isDirectory()) 
						{ 
						    // cargar imagen
							String is = this.getClass().getClassLoader().getResource("").getPath();
							File des = new File(is + "\\fotosUser\\" + copia.getMail() + ".png");
							Files.copy(f.toPath(), des.toPath(), StandardCopyOption.REPLACE_EXISTING);
						
						}
					} catch (IOException e) {
					    // error al cargar la imagen del usuario
					}
					finally
					{
					// el usuario se ha creado correctamente,  si falla la copia de
					// la imagen se utilizara una por defecto por eso aun asi se devuelve correcto
						devolver = resultado;
					}
				}
			}
			else // el usuario existe SOLO se activara la cuenta si el email y la clave COINCIDEN (por seguridad) y ademas esta inactivo
			{
				if(copia.getPassword().equals(datosUsuario.getPassword()) && !datosUsuario.isActivo())
				{
					// si el email y la clave coinciden, se habilita la cuenta
					datosUsuario.setActivo(true);
					resultado = daoUser.update(datosUsuario);
					if(resultado != null)
					{
						// usuario reactivado
						devolver = resultado;
					}
				}
			}
		}
		return devolver;
	}
	private String encriptarClave(String clave)
	{
		return DigestUtils.sha256Hex(clave);
	}
}
