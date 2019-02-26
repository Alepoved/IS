package onMarket.negocio.ModuloUsuarios;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import onMarket.integracion.ModuloUsuarios.DAOUsuario;
import onMarket.integracion.factorias.FactoriaIntegracion;


public class AS_UsuarioImp implements AppServUsuario{
	
	@Override
	public Integer eliminaUsuario(TUsuario tUsuario) {
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOUsuario daoUs = factoria.generaDAOUsuario();
		TUsuario us = daoUs.readByEmail(tUsuario.getMail()); 
		if (us != null) {
			if (us.isActivo()) {
				us.setActivo(false);
				return daoUs.update(us);
			}
		}
		return null;
	}
	
	@Override
	public TUsuario obtenUsuario(String email) {
		if(email!=null){
			FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
			DAOUsuario daoUsuario = factoria.generaDAOUsuario();
			return daoUsuario.readByEmail(email);
		}
		else{
			return null;
		}
	}

	@Override
	public Integer editaUsuario(TUsuario tUsuario) {
		FactoriaIntegracion factoria = FactoriaIntegracion.obtenerInstancia();
		DAOUsuario daoUsuario = factoria.generaDAOUsuario();
		TUsuario us = daoUsuario.readByEmail(tUsuario.getMail());
		if (us != null){
			if (us.isActivo()){
				if(tUsuario.getTelefono()!=null){
					if(tUsuario.getFecha_nac()!=null){
						if(tUsuario.getPassword()!=null){
							if(tUsuario.getNombre()!=null){
								if(tUsuario.getApellido()!=null){
									if(tUsuario.getDireccion()!=null){
										if(tUsuario.getFoto()!=null){
											if(tUsuario.getN_tarjeta()!=null){
												if(tUsuario.getDireccionEntrega()!=null)
												{
													tUsuario.setId(us.getId());
													tUsuario.setMail(us.getMail());	
													try 
													{
														File f = new File(tUsuario.getFoto());
														if(f.exists() && !f.isDirectory()) 
														{ 
														    // cargar imagen
															String is = this.getClass().getClassLoader().getResource("").getPath();
															File des = new File(is + "\\fotosUser\\" + tUsuario.getMail() + ".png");
															Files.copy(f.toPath(), des.toPath(), StandardCopyOption.REPLACE_EXISTING);
														
														}
													} catch (IOException e) {
													   System.out.println("Error al cargar la foto del usuario");
													}
													tUsuario.setFoto(tUsuario.getMail());
													return daoUsuario.update(tUsuario);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				return null;
			}
			return null;
			} 
		return null;	
		}	
	}
