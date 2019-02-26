package onMarket.presentacion.ModuloPedidos;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import onMarket.negocio.ModuloPedidos.BOPago;
import onMarket.negocio.ModuloPedidos.EstadoPago;
import onMarket.negocio.ModuloPedidos.TPedido;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class DialogoPago extends JDialog{
	private JButton botonCancelar;
    private JButton botonAceptar;
    private JLabel labelInstruction;
    private Boolean accept = false;
    private JTextField textField_titular;
    private JTextField textField_tarjeta;
    private JTextField textField_cvv;
    private JTextField textField_dir;
    private JPanel jPanel1;
    private JLabel lblTitular;
    private JLabel lblNTarjeta;
    private JLabel lblCvv;
    private JLabel lblMesCad;
    private JLabel lblAoCad;
    private JLabel lblTotal;
    private JLabel lblDireccionDeEntrega;
    private JDateChooser dateChooser;
    private JLabel lblElijaFechaEntrega;
    private BOPago pago; 
    private ControladorPedidos ctrl; 
    private TPedido ped; 
    private JFrame parent;
    private JSpinner Horario_ini;
    private JSpinner Horario_fin;
    private JYearChooser yearChooser;
    private JMonthChooser monthChooser;
    private Integer hora_ini;
    private Integer hora_fin;
    private Date fecha_entrega;
    private String dirEntrega;

    public DialogoPago(JFrame parent, boolean modal, ControladorPedidos ctrl, TPedido ped) { 
        super(parent,modal);
    	this.ctrl = ctrl;
    	this.ped = ped; 
    	this.parent = parent;
        setPreferredSize(new Dimension(600, 650));
        setResizable(false);
        setSize(new Dimension(800, 700));
        getContentPane().setSize(new Dimension(350, 250));
        getContentPane().setPreferredSize(new Dimension(350, 250));

        initComponents();
        
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jPanel1.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelInstruction = new JLabel();
        labelInstruction.setText("Introduzca Datos de pago: ");
        labelInstruction.setHorizontalTextPosition(SwingConstants.LEFT);
        labelInstruction.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 16));
        botonCancelar = new JButton();
        botonAceptar = new JButton();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        getContentPane().setSize(new Dimension(350, 250));
        
        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }

			
        });

        botonAceptar.setText("Pagar");
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }

			
        });
        
        lblTitular = new JLabel("Titular:");
        lblTitular.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        lblNTarjeta = new JLabel("Nº tarjeta:");
        lblNTarjeta.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        lblCvv = new JLabel("CVV:");
        lblCvv.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        textField_titular = new JTextField();
        textField_titular.setColumns(10);
        
        textField_tarjeta = new JTextField();
        textField_tarjeta.setColumns(10);
        
        textField_cvv = new JTextField();
        textField_cvv.setColumns(10);
        
        lblMesCad = new JLabel("Mes cad.");
        lblMesCad.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        lblAoCad = new JLabel("Año cad.");
        lblAoCad.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        lblTotal = new JLabel("TOTAL:");
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        lblDireccionDeEntrega = new JLabel("Direccion de entrega:");
        lblDireccionDeEntrega.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        textField_dir = new JTextField();
        textField_dir.setColumns(10);
        
        lblElijaFechaEntrega = new JLabel("Elija fecha entrega");
        lblElijaFechaEntrega.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        dateChooser = new JDateChooser();
        
        monthChooser = new JMonthChooser();
        
        yearChooser = new JYearChooser();
        
        Horario_ini = new JSpinner();
        Horario_ini.setModel(new SpinnerNumberModel(12, 9, 21, 1));
        
        Horario_fin = new JSpinner();
        Horario_fin.setModel(new SpinnerNumberModel(12, 9, 21, 1));
        
        JLabel lblHorarioDeEntrega = new JLabel("Horario de entrega entre las");
        
        JLabel lblYLas = new JLabel("y las");
        
        JLabel LblImporte = new JLabel("" + ped.getTotal_pagar());

        GroupLayout gl_jPanel1 = new GroupLayout(jPanel1);
        gl_jPanel1.setHorizontalGroup(
        	gl_jPanel1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel1.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        						.addComponent(labelInstruction, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
        						.addGroup(gl_jPanel1.createSequentialGroup()
        							.addComponent(lblMesCad)
        							.addGap(106)
        							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        								.addComponent(yearChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblAoCad)))
        						.addGroup(gl_jPanel1.createSequentialGroup()
        							.addComponent(lblHorarioDeEntrega)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(Horario_ini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(lblYLas)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(Horario_fin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(30)
        							.addComponent(lblTotal)
        							.addGap(15)
        							.addComponent(LblImporte, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
        						.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING, false)
        							.addComponent(dateChooser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(lblElijaFechaEntrega, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        					.addContainerGap())
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(textField_dir, 419, 419, 419)
        					.addGap(160))
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(lblTitular, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(274, Short.MAX_VALUE))
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(textField_titular, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(157, Short.MAX_VALUE))
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(lblNTarjeta)
        					.addContainerGap(490, Short.MAX_VALUE))
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(lblCvv)
        					.addContainerGap(540, Short.MAX_VALUE))
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(textField_tarjeta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(433, Short.MAX_VALUE))
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(textField_cvv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(433, Short.MAX_VALUE))
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(lblDireccionDeEntrega)
        					.addContainerGap(404, Short.MAX_VALUE))
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(monthChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(449, Short.MAX_VALUE))
        				.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
        					.addComponent(botonAceptar)
        					.addGap(18)
        					.addComponent(botonCancelar)
        					.addContainerGap())))
        );
        gl_jPanel1.setVerticalGroup(
        	gl_jPanel1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel1.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(labelInstruction, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblTitular)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textField_titular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblNTarjeta)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textField_tarjeta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(lblCvv)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textField_cvv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(12)
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblMesCad)
        				.addComponent(lblAoCad))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addComponent(monthChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(33)
        					.addComponent(lblDireccionDeEntrega))
        				.addComponent(yearChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textField_dir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(lblElijaFechaEntrega)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(29)
        					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblHorarioDeEntrega)
        						.addComponent(Horario_ini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblTotal)
        						.addComponent(Horario_fin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblYLas)
        						.addComponent(LblImporte, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
        					.addContainerGap())
        				.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
        					.addGap(150)
        					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
        						.addComponent(botonCancelar)
        						.addComponent(botonAceptar))
        					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1.setLayout(gl_jPanel1);

        GroupLayout layout = new GroupLayout(getContentPane());
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 250, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);
        //setVisible(true);
        pack();
    }                        

    
	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

    private void botonCancelarActionPerformed(ActionEvent evt) {
			this.setAccept(false);
			this.setVisible(false);		
			}
    
    @SuppressWarnings({ "static-access", "deprecation" })
	private void botonAceptarActionPerformed(ActionEvent evt) {
    	int flag = EstadoPago.CORRECTO;
  	  if(textField_titular.getText() == null){
  		  flag = EstadoPago.ERROR_TITULAR;
  		String mensaje = "Es necesario rellenarlo";
		  JOptionPane.showMessageDialog(this.parent,mensaje,
								  "Titular",
								  JOptionPane.ERROR_MESSAGE);
  	  }
	  
  	  String titular=textField_titular.getText();
  	  String nuevoSinEspacio= titular.trim();
  	  Pattern pat = Pattern.compile("[[A-ZñÑáéíóúÁÉÍÓÚ] +|[a-zñÑáéíóúÁÉÍÓÚ] +]+");
  	  Matcher mat = pat.matcher(nuevoSinEspacio);
  	  if (!mat.matches()){
  		flag=EstadoPago.ERROR_TITULAR;
  	    String mensaje = "Solo se admiten letras";
	    JOptionPane.showMessageDialog(this.parent,mensaje,
							  "Titular",
							  JOptionPane.ERROR_MESSAGE);
  	  }
  	  if(textField_tarjeta.getText() == null){
  		  flag = EstadoPago.ERROR_TARJETA;
  		  String mensaje = "Debe rellenar el campo de tarjeta";
		  JOptionPane.showMessageDialog(this.parent,mensaje,
								  "Tarjeta",
								  JOptionPane.ERROR_MESSAGE);
  	  }
  	  String tarjeta = textField_tarjeta.getText();
  	  String tarjetaSinEspacio= tarjeta.replaceAll("\\s","");
  	  Pattern pat1 = Pattern.compile("[0-9]{16}");
  	  Matcher mat1 = pat1.matcher(tarjetaSinEspacio);
  	  if (!mat1.matches()){
  		  flag=EstadoPago.ERROR_TARJETA;
  	      String mensaje = "La tarjeta solo puede tener numeros del 0 al 9, y como maximo 16 digitos";
	      JOptionPane.showMessageDialog(this.parent,mensaje,
							  "Tarjeta",
							  JOptionPane.ERROR_MESSAGE);
  	  }
	  if(textField_cvv.getText() ==null){
  		  flag = EstadoPago.ERROR_CVV;
  		  String mensaje = "Debe rellenar el campo de cvv";
		  JOptionPane.showMessageDialog(this.parent,mensaje,
								  "CVV",
								  JOptionPane.ERROR_MESSAGE);
  	  }
  	  String cvv=textField_cvv.getText();
  	  String cvvSinEspacio= cvv.replaceAll("\\s","");
  	  Pattern pat2 = Pattern.compile("[0-9]{3}");
  	  Matcher mat2 = pat2.matcher(cvvSinEspacio);
  	  if (!mat2.matches()) {
  		  flag = EstadoPago.ERROR_CVV;
  		  String mensaje = "El campo CVV solo debe tener numeros del 0 al 9 y como maximo 3 digitos";
		  JOptionPane.showMessageDialog(this.parent,mensaje,
								  "CVV",
								  JOptionPane.ERROR_MESSAGE);
  	  }
  	  
  	  if(monthChooser.getMonth()<1 || monthChooser.getMonth()>12 ) {
  		flag = EstadoPago.ERROR_CADUCIDAD;
  		String mensaje = "Debe elegir un mes valido";
		  JOptionPane.showMessageDialog(this.parent,mensaje,
								  "Mes de caducidad",
								  JOptionPane.ERROR_MESSAGE);
  	  }
  		 
  	 
  	  Calendar fecha = new GregorianCalendar();
  	  int anio = fecha.get(Calendar.YEAR);
  	  if(yearChooser.getYear()<anio || yearChooser.getYear()>anio+10 ) {
  		flag = EstadoPago.ERROR_CADUCIDAD;
  		String mensaje = "El año de caducidad debe ser correcto";
		  JOptionPane.showMessageDialog(this.parent,mensaje,
								  "Año de caducidad",
								  JOptionPane.ERROR_MESSAGE);
  	  }
  	  
  	  
  	  if(ped.getTotal_pagar()==null ||ped.getTotal_pagar() <=0) 
  	  {
  		flag = EstadoPago.ERROR_IMPORTE;
  		String mensaje = "El importe no es valido";
		  JOptionPane.showMessageDialog(this.parent,mensaje,
								  "Importe",
								  JOptionPane.ERROR_MESSAGE);
  	  }
  	  Calendar obtenida = new GregorianCalendar();
  	  obtenida.setTime(this.dateChooser.getDate());
  	  if (obtenida.before(fecha.getInstance())){
  		flag = EstadoPago.ERROR_PAGO;
  		String mensaje = "La fecha no es valida";
		  JOptionPane.showMessageDialog(this.parent,mensaje,
								  "Fecha entrega",
								  JOptionPane.ERROR_MESSAGE);
  	  }
  	  else{
  	  int dia = dateChooser.getCalendar().getTime().getDate();
  	  int mes = dateChooser.getCalendar().getTime().getMonth();
  	  int año = dateChooser.getCalendar().getTime().getYear();
  	  this.fecha_entrega = new Date(año, mes, dia);
  	  }
  	  this.hora_ini = (Integer) this.Horario_ini.getValue();
  	  this.hora_fin = (Integer) this.Horario_fin.getValue();
  	  if (this.textField_dir.getText() != null && this.textField_dir.getText().length() > 5){
  	  this.dirEntrega = this.textField_dir.getText();
  	  }
  	  else{
  		flag = EstadoPago.ERROR_PAGO;
  		String mensaje = "La direccion no es valida";
		  JOptionPane.showMessageDialog(this.parent,mensaje,
								  "Direccion de entrega",
								  JOptionPane.ERROR_MESSAGE);
  	  }
  	  if (flag == EstadoPago.CORRECTO){
  		ped.setDir_entrega(this.dirEntrega);
  		ped.setHorario_fin(hora_fin);
  		ped.setHorario_ini(hora_ini);
  		ped.setFecha_entrega(this.fecha_entrega);
  	  	pago = new BOPago(textField_titular.getText(), textField_tarjeta.getText(), textField_cvv.getText(),  monthChooser.getMonth(),
			yearChooser.getYear(), ped.getTotal_pagar());  	  
    	this.ctrl.accion(EventoPedidos.PAGAR_PEDIDO, pago); 
    	this.setAccept(true);
		this.setVisible(false);	
  	  }
	}

    public JTextField getTextFieldNombre() {
		return textField_titular;
	}
}
