package onMarket.presentacion.ModuloUsuarios;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.presentacion.GeneralUtils.DialogoError;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.admin.AfterLoginGUI;
import onMarket.presentacion.admin.IGUI;


@SuppressWarnings("serial")
public class GUIUsuarios extends JPanel implements IGUI {

	private JFrame parentWindow; //Se usa cuando necesitamos un nuevo Jdialog
	private AfterLoginGUI parentDialog; // nos permite acceder a cambiar el contenido de una pestaña 
	private int posInTabs; // nos permite saber en que pestaña se ubica esta misma componente
	private ControladorUsuarios control;
    private JPanel panelDer;
    private JButton botonAgregar;
    private JScrollPane scrollPane;
    private JPanel panelIzq;
    private TUsuario usuario;
    
   //atributos gui usuario Mi Perfil
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelDirEntrega;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelMail;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelTarjeta;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JSeparator jSeparator1;
    
   
	
	
	public GUIUsuarios(JFrame miFrame,AfterLoginGUI miDialog,int indexTab,ControladorUsuarios cont,TUsuario usuario){
		setSize(new Dimension(650, 400));
		setPreferredSize(new Dimension(650, 400));
		setFont(new Font("Tahoma", Font.PLAIN, 10));
		this.control=cont;
		this.parentWindow=miFrame;
		this.parentDialog=miDialog;
		this.posInTabs=indexTab;
		this.usuario=usuario;
		initComponents();
    }

   
    private void initComponents() {
    	
    	control.setGui(this);
        panelDer = new JPanel();
        setSize(new Dimension(650, 400));

        //Boton Editar Perfil Usuario
        botonAgregar = new JButton("Editar Perfil");
        botonAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		DialogoInputUs dialogo= new DialogoInputUs(parentWindow,true,TipoDialogo.DG_EDITA_USUARIO);
                boolean cambio = false;
                
                dialogo.getTextFieldNom().setText(usuario.getNombre());
                dialogo.getTextFieldAp().setText(usuario.getApellido());
                dialogo.getTextFieldTlfono().setText(usuario.getTelefono().toString());
                dialogo.getTextFieldMail().setText(usuario.getMail());
                dialogo.getTextFieldDir().setText(usuario.getDireccion());
                dialogo.getTextFieldNTarj().setText(usuario.getN_tarjeta());
                dialogo.getTextFieldDirEntr().setText(usuario.getDireccionEntrega());
                if(usuario.getFecha_nac() != null)
                	dialogo.setDate(usuario.getFecha_nac());
                else
                	dialogo.setDate(new java.util.Date());
                
                
                dialogo.setVisible(true);
                if (dialogo.getAccept()){
               	
                	String direccion=dialogo.getTextFieldDir().getText();
                	String direccionEntrega=dialogo.getTextFieldDirEntr().getText();
                	String nTarjeta=dialogo.getTextFieldNTarj().getText();
                 	Integer telefono = Integer.parseInt(dialogo.getTextFieldTlfono().getText());
                 	String foto = dialogo.getFoto();
                 	String contraseña = dialogo.getTextFieldPsswrd().getText();
                 	
                 	TUsuario modificacion= new TUsuario(usuario.getId(),usuario.getTelefono(),usuario.getEsAdmin(),true,usuario.getFecha_nac(),usuario.getMail(),usuario.getPassword(),usuario.getNombre(),usuario.getApellido(),usuario.getDireccionEntrega(),usuario.getFoto(),usuario.getN_tarjeta(),usuario.getDireccionEntrega());
                 	
                 	if(usuario.getFecha_nac() == null)
                 		modificacion.setFecha_nac(new Date(new java.util.Date().getTime()));
                 	
                 	if(!telefono.equals(usuario.getTelefono()))
                 	{
                 		modificacion.setTelefono(telefono);
                 		cambio = true;
                 	}
                 	if(!contraseña.isEmpty())
                 	{
                 		modificacion.setPassword(contraseña);
                 		cambio = true;
                 	}
                 	if(!direccion.equals(usuario.getDireccion()))
                 	{
                 		modificacion.setDireccion(direccion);
                 		cambio = true;
                 	}
                 	if(!foto.isEmpty())
                 	{
                 		modificacion.setFoto(foto);
                 		cambio = true;
                 	}
                 	if(!nTarjeta.equals(usuario.getN_tarjeta()))
                 	{
                 		modificacion.setNTarjeta(nTarjeta);
                 		cambio = true;
                 	}
                 	if(!direccionEntrega.equals(usuario.getDireccionEntrega()))
                 	{
                 		modificacion.setDireccionEntrega(direccionEntrega);
                 		cambio = true;
                 	}
                 	if(cambio)
                 		control.accion(EventoUsuario.EDITA_USUARIO, modificacion);
                 	else
                 		mensaje("Ninguna modificacion detectada, no se aplica ningun cambio",JOptionPane.INFORMATION_MESSAGE);
                 		
                }
             
        	}
        });
        
        //Boton Eliminar Perfil Usuario
        JButton btnEliminarPerfil = new JButton("Eliminar Perfil");
        btnEliminarPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		DialogoInputUs dialogo2= new DialogoInputUs(parentWindow,true,TipoDialogo.DG_ELIMINA_USUARIO);
        		dialogo2.setVisible(true);
        		TUsuario datos = usuario; 
        		if(dialogo2.getAccept()==true){
				control.accion(EventoUsuario.BAJA_USUARIO, datos);
        		}
        	}
        });

        
        GroupLayout gl_panelDer = new GroupLayout(panelDer);
        gl_panelDer.setHorizontalGroup(
        	gl_panelDer.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panelDer.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panelDer.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btnEliminarPerfil, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
        				.addComponent(botonAgregar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
        			.addGap(4))
        );
        gl_panelDer.setVerticalGroup(
        	gl_panelDer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelDer.createSequentialGroup()
        			.addGap(75)
        			.addComponent(botonAgregar)
        			.addGap(18)
        			.addComponent(btnEliminarPerfil)
        			.addContainerGap(261, Short.MAX_VALUE))
        );
        panelDer.setLayout(gl_panelDer);
        
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(panelDer, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(panelDer, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        		.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        
        panelIzq = new JPanel();
        scrollPane.setViewportView(panelIzq);
        panelIzq.setLayout(new GridLayout(0, 1, 0, 0));
        
       //Inicializacion Pestaña Mi Perfil
        
        setLayout(layout);
     
        
        jLabelMail = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelApellido = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jLabelFechaNacimiento = new javax.swing.JLabel();
        jLabelTarjeta = new javax.swing.JLabel();
        jLabelDirEntrega = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
  
        jLabelMail.setText("Mail: "+usuario.getMail());
        jLabelPassword.setText("Password: *****");

        jLabelNombre.setFont(new java.awt.Font("Times New Roman", 3, 24)); 
        jLabelNombre.setText("Nombre: "+usuario.getNombre());

        jLabelApellido.setFont(new java.awt.Font("Times New Roman", 3, 24)); 
        jLabelApellido.setText("Apellido: "+usuario.getApellido());

        jLabelDireccion.setText("Direccion: "+usuario.getDireccion());

        jLabelTelefono.setText("Telefono: "+Integer.toString(usuario.getTelefono()));

        jLabelFechaNacimiento.setText("Fecha Nacimiento: "+usuario.getFecha_nac());

        jLabelTarjeta.setText("nº Tarjeta: "+usuario.getN_tarjeta());

        jLabelDirEntrega.setText("Direccion Entrega: "+usuario.getDireccionEntrega());
        try{  
        	jLabelImagen = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("fotosUser/"+usuario.getFoto()+".png")));
        }
        catch(RuntimeException e){
        	jLabelImagen = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("fotosUser/User1.png")));
        }
        javax.swing.GroupLayout layout1 = new javax.swing.GroupLayout(panelIzq);
        panelIzq.setLayout(layout1);
        layout1.setHorizontalGroup(
            layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout1.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(jLabelNombre)
                        .addGap(263, 263, 263)
                        .addComponent(jLabelApellido))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addGroup(layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFechaNacimiento)
                            .addComponent(jLabelMail)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelDireccion)
                            .addComponent(jLabelTelefono)
                            .addComponent(jLabelTarjeta)
                            .addComponent(jLabelDirEntrega))))
                .addGap(2, 2, 2))
        );
        layout1.setVerticalGroup(
            layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout1.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelApellido))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(jLabelMail)
                        .addGap(13, 13, 13)
                        .addComponent(jLabelPassword)
                        .addGap(13, 13, 13)
                        .addComponent(jLabelDireccion)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelTelefono)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelFechaNacimiento)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelTarjeta)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDirEntrega))
                    .addGroup(layout1.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(606, 606, 606))
        );
        parentWindow.pack();

       control.accion(EventoUsuario.MOSTRAR_PERFIL,usuario);
    }

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento)
		{   
		
		/*
		 * Respuestas en caso Baja Usuario
		 * */
		case EventoUsuario.RES_BAJA_USUARIO_OK: 
		{ 
			mensaje("Se ha eliminado la cuenta correctamente",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
			break;
		}			
		
	case EventoUsuario.RES_BAJA_USUARIO_KO:
		{
			String msj="Error Interno - No se ha podido Eliminar";
	     	DialogoError dialogoE= new DialogoError(parentWindow,true,msj);
	     	dialogoE.setVisible(true);
	     	if(dialogoE.getAccept()){}
			break;
		}
	case EventoUsuario.RES_MOSTRAR_PERFIL_OK: 
	{
		mensaje("Bienvenido "+usuario.getNombre()+System.lineSeparator()+"Has iniciado sesion correctamente",JOptionPane.INFORMATION_MESSAGE);
		break;
	}
	case EventoUsuario.RES_MOSTRAR_PERFIL_KO: 
		{ 	
			String msj="Error Interno - No se ha podido Obtener los datos";
	     	DialogoError dialogoE= new DialogoError(parentWindow,true,msj);
	     	dialogoE.setVisible(true);
	     	if(dialogoE.getAccept()){}
			System.exit(0);
	     	break;
			
		}
	case EventoUsuario.RES_EDITA_USUARIO_OK: 
	{
		mensaje("Usuario editado correctamente, recuerda que debes cerrar todas las ventanas y volver a ejecutar la aplicacion",JOptionPane.INFORMATION_MESSAGE);
		this.usuario = (TUsuario)datos;
		break;
	}
	
	case EventoUsuario.RES_EDITA_USUARIO_KO:
	{
			String msj="Error Interno - No se ha podido Editar";
	     	DialogoError dialogoE= new DialogoError(parentWindow,true,msj);
	     	dialogoE.setVisible(true);
	     	if(dialogoE.getAccept()){}
			break;
		} 
	  }	
	}


public JFrame getParentWindow() {
	return parentWindow;
}

public AfterLoginGUI getParentDialog() {
	return parentDialog;
}

public int getPosInTabs() {
	return posInTabs;
}
private void mensaje(String s, int tipo)
{
	  JOptionPane.showMessageDialog(this.parentWindow, s, "OnMarket", tipo);
}
}
