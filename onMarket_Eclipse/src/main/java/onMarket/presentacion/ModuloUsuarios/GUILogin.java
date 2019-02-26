package onMarket.presentacion.ModuloUsuarios;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.admin.AfterLoginGUI;
import onMarket.presentacion.admin.IGUI;

import java.awt.Color;

@SuppressWarnings("serial")

/*
 * Componente con el Contenido de la ventana principal
 * En nuestro caso Siempre será LogIn 
 * */
public class GUILogin  extends JPanel implements IGUI{
    
  private JFrame parentFrame;	
  private JButton jButton1;
  private JButton jButton2;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JTextField textEmail;
  private JTextField textPassword;
  private ControladorUsuarios ctrl;
  private boolean primerClick = false; 
  
	public GUILogin(JFrame p, ControladorUsuarios ctrl) {
		setBackground(new Color(0, 102, 255));
		parentFrame=p;
		this.ctrl = ctrl;
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel1.setBackground(Color.BLACK);
        jLabel1.setForeground(Color.WHITE);
        jLabel2 = new JLabel();
        jLabel2.setForeground(Color.WHITE);
        jLabel3 = new JLabel();
        jLabel3.setForeground(Color.WHITE);
        textEmail = new JTextField();
        textEmail.setForeground(Color.GRAY);
        textPassword = new JPasswordField();
        textPassword.setForeground(Color.GRAY);
        jButton1 = new JButton();
        jButton2 = new JButton();


        jLabel1.setFont(new Font("Cooper Black", 2, 24)); // NOI18N
        jLabel1.setText("OnMarket");

        jLabel2.setText("Correo electronico:");
        jLabel3.setText("Contraseña:");

        textEmail.setText("correo Electrónico");
        textEmail.setToolTipText("");
        textEmail.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEmailActionPerformed(evt);
            }
        });
        textEmail.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	if(!primerClick && e.getButton() == MouseEvent.BUTTON1)
            	{
	            	textEmail.setText("");
	            	primerClick = true;
            	}
            }
        });
        textPassword.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPasswordActionPerformed(evt);
            }
        });

        jButton1.setText("LogIn");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            	String errores = "";
    	    	if(textEmail.getText().isEmpty())
    	    		errores +="El campo email esta vacio" + System.getProperty("line.separator");
    	    	if(textPassword.getText().isEmpty())	
    	    		errores +="El campo contraseña esta vacio" + System.getProperty("line.separator");
    	    	
    	    	if(errores.isEmpty())
    	    	{
	    	    	TUsuario datosUsuario = new TUsuario();
	            	datosUsuario.setMail(textEmail.getText());
	            	datosUsuario.setPassword(textPassword.getText());
	            	ctrl.accion(EventoUsuario.LOGIN_USUARIO, datosUsuario);
    	    	}
    	    	else
    	    		mensaje(errores,JOptionPane.ERROR_MESSAGE);
            }
        });

        jButton2.setText("Regístrate");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	/*
            	 * Este botón no crea un AfterLogin... debe crear un nuevo diálogo 
            	 * para el registro de usuario nuevo cuya GUI es parte del Modulo Usuarios
            	 * dentro del Modulo sois libres de hacer un dialogo entero o
            	 * hacer un afterRegister (en Admin) vacío que luego agrega un componente
            	 * GUIRegistro del Modulo de Usuarios 
            	 * */
                jButton2ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(textEmail, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textPassword, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2,GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1,GroupLayout.PREFERRED_SIZE, 187,GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1,GroupLayout.PREFERRED_SIZE, 22,GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textEmail,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textPassword,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(67, 67, 67)
                .addComponent(jButton2)
                .addContainerGap())
        );

    
    }                        

    private void textEmailActionPerformed(ActionEvent evt) {}                                         

    private void textPasswordActionPerformed(ActionEvent evt) {}                                           

    private void jButton2ActionPerformed(ActionEvent evt) {                                         
    	DialogoInputUs formAlta = new DialogoInputUs(this.parentFrame, true ,TipoDialogo.DG_ALTA_USUARIO);
    	boolean salir = false;
    	formAlta.setVisible(true);
    	while(formAlta.getAccept() && !salir)
    	{
	    	String errores = "";
	    	String nombre = formAlta.getTextFieldNom().getText();
	    	String apellido = formAlta.getTextFieldAp().getText();
	    	Integer dia = formAlta.getDd();
	    	Integer mes = formAlta.getMm();
	    	Integer year = formAlta.getYy();
	    	String telefono = formAlta.getTextFieldTlfono().getText();
	    	String email = formAlta.getTextFieldMail().getText();
	    	String clave = formAlta.getTextFieldPsswrd().getText();
	    	String direccion = formAlta.getTextFieldDir().getText();

	    	String foto = formAlta.getFoto();
	    	String numTarjeta = formAlta.getTextFieldNTarj().getText();
	    	String direccionEntrega = formAlta.getTextFieldDirEntr().getText();
	    	if(nombre.isEmpty())
	    		errores +="El campo nombre esta vacio" + System.getProperty("line.separator");
	    	if(apellido.isEmpty())	
	    		errores +="El campo apellido esta vacio" + System.getProperty("line.separator");
	    	if(telefono.isEmpty())
	    		errores +="El campo telefono esta vacio" + System.getProperty("line.separator");
	    	if(email.isEmpty())
	    		errores +="El campo email esta vacio" + System.getProperty("line.separator");
	    	if(clave.isEmpty())
	    		errores +="El campo contraseña esta vacio" + System.getProperty("line.separator");
	    	if(direccion.isEmpty())
	    		errores +="El campo direccion esta vacio" + System.getProperty("line.separator");
	    	if(foto.isEmpty())
	    		errores +="No se ha seleccionado ninguna imagen" + System.getProperty("line.separator");
	    	if(numTarjeta.isEmpty())
	    		errores +="El campo numero de tarjeta esta vacio" + System.getProperty("line.separator");
	    	if(direccionEntrega.isEmpty())
	    		errores +="El campo direccion de entrega esta vacio" + System.getProperty("line.separator");
	    	
	    	if(errores.isEmpty())
	    	{
	    	    @SuppressWarnings("deprecation")
	    	    Date ds = new Date(year-1900, mes,dia);
	    		TUsuario datosUsuario = new TUsuario(new Integer(telefono),false, true, ds , email,clave, nombre ,apellido ,direccion, foto, numTarjeta,direccionEntrega);
	    		ctrl.accion(EventoUsuario.ALTA_USUARIO, datosUsuario);
	    		salir = true;
	    	}
	    	else
	    	{
	    		mensaje(errores,JOptionPane.ERROR_MESSAGE);
	    		formAlta.setVisible(true);
	    	}
    	}
    }
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento)
		{
		case EventoUsuario.RES_LOGIN_USUARIO_OK:
    		
			textEmail.setText("correo Electronico");
			textPassword.setText("");
			this.primerClick = false;
			
			AfterLoginGUI secondWindow= new AfterLoginGUI(this.parentFrame,true,(TUsuario)datos);
			secondWindow.setVisible(true);
		break;
		case EventoUsuario.RES_LOGIN_USUARIO_KO:

			mensaje("El correo electronico o la contraseña son incorrectos",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		break;
		case EventoUsuario.RES_ALTA_USUARIO_OK:
    		
			mensaje("La cuenta se ha creado correctamente", JOptionPane.INFORMATION_MESSAGE);

		break;
		case EventoUsuario.RES_ALTA_USUARIO_KO:

			mensaje("Se ha producido un error al crear su cuenta",JOptionPane.ERROR_MESSAGE);
    		
		break;
		}
	}                                        
	private void mensaje(String s, int tipo)
	{
		   JOptionPane.showMessageDialog(this.parentFrame, s, "OnMarket", tipo);
	}
}
