package onMarket.presentacion.ModuloUsuarios;


import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JCalendar;

import onMarket.presentacion.GeneralUtils.TipoDialogo;

@SuppressWarnings("serial")
public class DialogoInputUs extends JDialog {
	private JButton botonCancelar;
    private JButton botonAceptar;
    private JPanel jPanel1;
    //Nombre
    private JLabel labelInstruction1;
    private JTextField textFieldNom;
    //Apellido
    private JLabel labelInstruction2;
    private JTextField textFieldAp;
    //FechaNac
    private JLabel labelInstruction3;
    private JCalendar calendario;
    //Telefono
    private JLabel labelInstruction4;
    private JTextField textFieldTlfono;
    //Mail   
    private JLabel labelInstruction5;
    private JTextField textFieldMail;
    //Password
    private JLabel labelInstruction6;
    private JPasswordField textFieldPsswrd;
    //Direccion
    private JLabel labelInstruction7;
    private JTextField textFieldDir;
    //Foto
    private JButton textFieldFoto;
    //Ntarj
    private JLabel labelInstruction9;
    private JTextField textFieldNTarj;
    //DirEntrega
    private JLabel labelInstruction10;
    private JTextField textFieldDirEntr;
    private Boolean accept = false;
    private int tipoDG;
    private boolean seleccionado = false;
    private String stringFoto;
    
    public DialogoInputUs(Frame parentU,boolean modal,int tipo){
    	super(parentU, modal);
        setResizable(false);
        setSize(new Dimension(350, 250));
        getContentPane().setSize(new Dimension(350, 250));
        getContentPane().setPreferredSize(new Dimension(800,450));
        this.tipoDG=tipo;
        if(this.tipoDG == TipoDialogo.DG_EDITA_USUARIO){
            setTitle("Editar - Perfil");
       		initComponents();
       }
       if(this.tipoDG == TipoDialogo.DG_ALTA_USUARIO){
            setTitle("Alta - Usuario");
            initComponents();
       }
       if(this.tipoDG == TipoDialogo.DG_ELIMINA_USUARIO){
    	   setTitle("Baja - Usuario");
    	   ventana();
       }
    }

    private void ventana() {
    	jPanel1 = new JPanel();
    	labelInstruction1 = new JLabel();
    	labelInstruction1.setText("¿Esta seguro de darte de baja? ");
        botonCancelar = new JButton();
        botonAceptar = new JButton();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setSize(new Dimension(300, 200));
        getContentPane().setPreferredSize(new Dimension(250,150));
        
        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }	
        });

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }	
        });
        
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
            	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
            		.addGroup(jPanel1Layout.createSequentialGroup()
            			.addContainerGap()
            			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    					.addComponent(labelInstruction1, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    					.addContainerGap())
                				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                						.addComponent(botonAceptar)
                						.addPreferredGap(ComponentPlacement.UNRELATED)
                						.addComponent(botonCancelar)
                						.addContainerGap())
            					))       		
                );
        
        jPanel1Layout.setVerticalGroup(
            	jPanel1Layout.createParallelGroup(Alignment.CENTER)
            		.addGroup(jPanel1Layout.createSequentialGroup()
            			.addGap(32)
            			.addComponent(labelInstruction1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
            			.addPreferredGap(ComponentPlacement.UNRELATED)
            			.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
            			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
            				.addComponent(botonCancelar)
            				.addComponent(botonAceptar))
            			.addContainerGap())
        		 );
        
        GroupLayout layout = new GroupLayout(getContentPane());
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);
        pack(); 
	}    
    
    private void initComponents() {
    	
    	jPanel1 = new JPanel();
        labelInstruction1 = new JLabel();
        textFieldNom = new JTextField();
        labelInstruction2 = new JLabel();
        textFieldAp = new JTextField();
        labelInstruction3 = new JLabel();
        calendario = new JCalendar();
        labelInstruction4 = new JLabel();
        textFieldTlfono = new JTextField();
        labelInstruction5 = new JLabel();
        textFieldMail = new JTextField();
        labelInstruction6 = new JLabel();
        textFieldPsswrd = new JPasswordField();
        labelInstruction7 = new JLabel();
        textFieldDir = new JTextField();
        textFieldFoto = new JButton();
        labelInstruction9 = new JLabel();
        textFieldNTarj = new JTextField();
        labelInstruction10 = new JLabel();
        textFieldDirEntr = new JTextField();
        botonCancelar = new JButton();
        botonAceptar = new JButton();
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        getContentPane().setSize(new Dimension(350, 250));
        
        if(this.tipoDG == TipoDialogo.DG_EDITA_USUARIO){
        	labelInstruction1.setEnabled(false);
        	labelInstruction1.setText("Nombre : ");
            textFieldNom.setEnabled(false);
            labelInstruction2.setEnabled(false);
            labelInstruction2.setText("Apellido : ");
            textFieldAp.setEnabled(false);
            labelInstruction3.setEnabled(false);
            labelInstruction3.setText("Fecha de Nacimiento : ");
            calendario.setEnabled(false);
        	labelInstruction4.setText("Introduce El Nuevo Telefono : ");
        	labelInstruction5.setEnabled(false);
        	labelInstruction5.setText("Email : ");
        	textFieldMail.setEnabled(false);
        	labelInstruction6.setText("Introduce La Nueva Contraseña : ");
        	labelInstruction7.setText("Introduce La Nueva Direccion : ");
        	textFieldFoto.setText("Seleccionar Foto");
        	labelInstruction9.setText("Introduce El Nuevo Numero de Tarjeta : ");
        	labelInstruction10.setText("Introduce La Nueva Direccion de Entrega : ");
        }
        if(this.tipoDG == TipoDialogo.DG_ALTA_USUARIO){
        	labelInstruction1.setText("Introduce tu Nombre : ");
            labelInstruction2.setText("Introduce tu Apellido : ");
            labelInstruction3.setText("Introduce tu Fecha de Nacimiento : ");
        	labelInstruction4.setText("Introduce tu Numero de Telefono : ");
        	labelInstruction5.setText("Introduce tu Email : ");
        	labelInstruction6.setText("Introduce tu Contraseña : ");
        	labelInstruction7.setText("Introduce tu Direccion : ");
        	textFieldFoto.setText("Seleccionar Foto : ");
        	labelInstruction9.setText("Introduce tu Numero de Tarjeta : ");
        	labelInstruction10.setText("Introduce tu Direccion de Entrega : ");
        }
        textFieldNom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldNomActionPerformed(evt);
            }
        });
        textFieldAp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldApActionPerformed(evt);
            }
        });
        textFieldTlfono.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldTlfonoActionPerformed(evt);
            }
        });
        textFieldMail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldMailActionPerformed(evt);
            }
        });
        textFieldPsswrd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldPsswrdActionPerformed(evt);
            }
        });
        textFieldDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldDirActionPerformed(evt);
            }
        });
        textFieldFoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldFotoActionPerformed(evt);
            }
        });
        textFieldNTarj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldNTarjActionPerformed(evt);
            }
        });
        textFieldDirEntr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldDirEntrActionPerformed(evt);
            }
        });
        
        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }	
        });

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }	
        });
        
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);

        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        					.addComponent(botonAceptar)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(botonCancelar)
        					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            					.addComponent(labelInstruction1, GroupLayout.DEFAULT_SIZE,50, Short.MAX_VALUE)
            					.addComponent(textFieldNom, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        						.addComponent(labelInstruction2, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            					.addComponent(textFieldAp, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            					.addContainerGap())
        				.addGroup(jPanel1Layout.createSequentialGroup()
            					.addComponent(labelInstruction3, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            					)
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            					.addComponent(calendario, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
            					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            					.addComponent(labelInstruction4, GroupLayout.DEFAULT_SIZE,50, Short.MAX_VALUE)
            					.addComponent(textFieldTlfono, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            					.addComponent(labelInstruction5, GroupLayout.DEFAULT_SIZE,50, Short.MAX_VALUE)
            					.addComponent(textFieldMail, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            					.addComponent(labelInstruction6, GroupLayout.DEFAULT_SIZE,50, Short.MAX_VALUE)
            					.addComponent(textFieldPsswrd, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            					.addComponent(labelInstruction7, GroupLayout.DEFAULT_SIZE,50, Short.MAX_VALUE)
            					.addComponent(textFieldDir, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            					.addContainerGap())       				
        				.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
            					.addComponent(textFieldFoto, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
            					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            					.addComponent(labelInstruction9, GroupLayout.DEFAULT_SIZE,50, Short.MAX_VALUE)
            					.addComponent(textFieldNTarj, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            					.addComponent(labelInstruction10, GroupLayout.DEFAULT_SIZE,50, Short.MAX_VALUE)
            					.addComponent(textFieldDirEntr, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            					.addContainerGap())
        					))       		
        );
        
        jPanel1Layout.setVerticalGroup(
            	jPanel1Layout.createParallelGroup(Alignment.LEADING)
            		.addGroup(jPanel1Layout.createSequentialGroup()
            			.addGap(32)
            			.addGroup(jPanel1Layout.createParallelGroup()
                    			.addComponent(labelInstruction1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    			.addComponent(textFieldNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            					)
            			.addGroup(jPanel1Layout.createParallelGroup()
                    			.addComponent(labelInstruction2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    			.addComponent(textFieldAp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            					)
            			.addComponent(labelInstruction3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
            			.addPreferredGap(ComponentPlacement.UNRELATED)
            			.addComponent(calendario, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
            			.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
            			.addGroup(jPanel1Layout.createParallelGroup()
                    			.addComponent(labelInstruction4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    			.addComponent(textFieldTlfono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            					)
            			.addGroup(jPanel1Layout.createParallelGroup()
                    			.addComponent(labelInstruction5, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    			.addComponent(textFieldMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            					)
            			.addGroup(jPanel1Layout.createParallelGroup()
                    			.addComponent(labelInstruction6, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    			.addComponent(textFieldPsswrd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            					)
            			.addGroup(jPanel1Layout.createParallelGroup()
                    			.addComponent(labelInstruction7, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    			.addComponent(textFieldDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            					)
            			.addComponent(textFieldFoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            			.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
            			.addGroup(jPanel1Layout.createParallelGroup()
                    			.addComponent(labelInstruction9, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    			.addComponent(textFieldNTarj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            					)
            			.addGroup(jPanel1Layout.createParallelGroup()
                    			.addComponent(labelInstruction10, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    			.addComponent(textFieldDirEntr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            					)
            			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
            				.addComponent(botonCancelar)
            				.addComponent(botonAceptar))
            			.addContainerGap())
        );
        jPanel1.setLayout(jPanel1Layout);
        
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
        pack();                        
    }
    
    public JTextField getTextFieldNom() {
		return textFieldNom;
	}
    public JTextField getTextFieldAp() {
		return textFieldAp;
	}

    public int getDd() {
		return calendario.getDayChooser().getDay();
	}
    public int getMm() {
		return calendario.getMonthChooser().getMonth();
	}
     public int getYy() {
    	return calendario.getYearChooser().getYear();
	}
     public void setDate(Date d) {
     	calendario.setDate(d);
 	}
    public JTextField getTextFieldTlfono() {
		return textFieldTlfono;
	}
    public JTextField getTextFieldMail() {
		return textFieldMail;
	}
    public JTextField getTextFieldPsswrd() {
		return textFieldPsswrd;
	}
    public JTextField getTextFieldDir() {
		return textFieldDir;
	}
    public JButton getTextFieldFoto() {
		return textFieldFoto;
	}
    public JTextField getTextFieldNTarj() {
		return textFieldNTarj;
	}
    public JTextField getTextFieldDirEntr() {
		return textFieldDirEntr;
	}
	
    public Boolean getAccept() {
		return accept;
	}
	
	public void setAccept(Boolean accept) {
		this.accept = accept;
	}
	public String getFoto() {
		if(this.seleccionado)
			return stringFoto;
		else
			return "";
	}
	private void textFieldNomActionPerformed(ActionEvent evt) {}
	private void textFieldApActionPerformed(ActionEvent evt) {}
	private void textFieldTlfonoActionPerformed(ActionEvent evt) {}
	private void textFieldMailActionPerformed(ActionEvent evt) {}
	private void textFieldPsswrdActionPerformed(ActionEvent evt) {}
	private void textFieldDirActionPerformed(ActionEvent evt) {}	
	private void textFieldFotoActionPerformed(ActionEvent evt) 
	{
		
    	JFileChooser seleccionaFichero = new JFileChooser();
    	int option = seleccionaFichero.showOpenDialog(this);
    	if (option == JFileChooser.APPROVE_OPTION)
    	{
    		textFieldFoto.setText("La foto ya ha sido seleccionada");
    		this.seleccionado = true;
    		this.stringFoto = seleccionaFichero.getSelectedFile().getAbsolutePath();
    	}
	}
	private void textFieldNTarjActionPerformed(ActionEvent evt) {}
	private void textFieldDirEntrActionPerformed(ActionEvent evt) {}
	
    private void botonCancelarActionPerformed(ActionEvent evt) {
			this.setAccept(false);
			this.setVisible(false);		
	}
    
    private void botonAceptarActionPerformed(ActionEvent evt) {
    	this.setAccept(true);
		this.setVisible(false);	
	}
}


