package onMarket.presentacion.admin;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import onMarket.negocio.ModuloUsuarios.AS_UsuarioImp;
import onMarket.negocio.Seguridad.AS_SeguridadImp;
import onMarket.presentacion.ModuloUsuarios.ControladorUsuarios;
import onMarket.presentacion.ModuloUsuarios.GUILogin;

import java.awt.Point;
import javax.swing.GroupLayout.Alignment;


/*
 * Ventana Principal de la app, siempre se crea vacía y se le agrega 
 * el componente Login implementado en onMarket.presentacion.ModuloUsuarios
 * De esta manera serán siempre los componentes de las distintas capas quienes
 * implementan la interfaz IGUI con el método "actualizar()"
 * */

@SuppressWarnings("serial")
public class PrincipalGUI extends JFrame {
    
	private GUILogin componente;
    
	public PrincipalGUI() {
        initComponents();
    }

	private void initComponents() {

		AS_SeguridadImp appServiceSeg= new AS_SeguridadImp ();
		AS_UsuarioImp appServiceUsu= new AS_UsuarioImp();
		
		final ControladorUsuarios ctrl = new ControladorUsuarios(appServiceUsu, null , appServiceSeg);
        componente = new GUILogin(this, ctrl);
        ctrl.setGui(componente);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int alto= super.getToolkit().getScreenSize().height/2;
        int ancho=super.getToolkit().getScreenSize().width/2;
        setLocation(new Point((ancho/2)+100,alto/2));
		setTitle("OnMarket");
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(componente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(0))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(componente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(0))
        );
        getContentPane().setLayout(layout);

        pack();
    }   
}
