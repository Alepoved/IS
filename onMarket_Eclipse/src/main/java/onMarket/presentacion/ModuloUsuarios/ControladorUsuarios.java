package onMarket.presentacion.ModuloUsuarios;

import org.apache.commons.codec.digest.DigestUtils;

import onMarket.negocio.ModuloUsuarios.AS_UsuarioImp;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.negocio.Seguridad.AS_SeguridadImp;
import onMarket.presentacion.admin.IGUI;

public class ControladorUsuarios {
	private AS_UsuarioImp modelo;
	private IGUI gui;
	private AS_SeguridadImp modSeg;
	
	public ControladorUsuarios(AS_UsuarioImp modelo, IGUI gui,AS_SeguridadImp modSeg){
		this.modelo=modelo;		
		this.gui=gui;
		this.modSeg=modSeg;
	}
	public void accion(int evento,TUsuario datos){
		switch(evento){
		case EventoUsuario.ALTA_USUARIO:{
			TUsuario tUsuario=datos;
			Integer res= modSeg.altaUsuario(tUsuario);
			if (res != null){
				tUsuario.setId(res);
				tUsuario.setPassword(DigestUtils.sha256Hex(tUsuario.getPassword()));
				gui.actualizar(EventoUsuario.RES_ALTA_USUARIO_OK, tUsuario);
			}else{
				gui.actualizar(EventoUsuario.RES_ALTA_USUARIO_KO, null);
				}
			break;
			}
		case EventoUsuario.BAJA_USUARIO:{
			Integer negocio=modelo.eliminaUsuario(datos);
			Integer res= null;
			if (negocio!= null){
				res= datos.getId();
				gui.actualizar(EventoUsuario.RES_BAJA_USUARIO_OK, res);
			}else
				gui.actualizar(EventoUsuario.RES_BAJA_USUARIO_KO, res);
			break;
			}
		case EventoUsuario.MOSTRAR_PERFIL:{ 
			TUsuario res=modelo.obtenUsuario(datos.getMail());
			if (res!= null)
				gui.actualizar(EventoUsuario.RES_MOSTRAR_PERFIL_OK, res);
			else
				gui.actualizar(EventoUsuario.RES_MOSTRAR_PERFIL_KO, res);
			break;
			} 
		case EventoUsuario.EDITA_USUARIO:{ 
			Integer negocio = modelo.editaUsuario(datos);
			TUsuario res =null;
			if (negocio!= null){
				res=datos;
				res.setPassword(DigestUtils.sha256Hex(datos.getPassword()));
				gui.actualizar(EventoUsuario.RES_EDITA_USUARIO_OK, res);
			}else
				gui.actualizar(EventoUsuario.RES_EDITA_USUARIO_KO, res);
			break;
			}
		case EventoUsuario.LOGIN_USUARIO:{ 
			TUsuario res = null;
			res = this.modSeg.Login(datos.getMail(), datos.getPassword());
			if (res != null){
				res.setPassword(DigestUtils.sha256Hex(datos.getPassword()));
				gui.actualizar(EventoUsuario.RES_LOGIN_USUARIO_OK, res);}
			else
				gui.actualizar(EventoUsuario.RES_LOGIN_USUARIO_KO, res);
			break;
			}
		}
	}
	public void setGui(IGUI g){
		this.gui=g;
	}
}
