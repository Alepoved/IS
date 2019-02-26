package onMarket.presentacion.ModuloCategorias;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import onMarket.presentacion.GeneralUtils.TipoDialogo;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class DialogoInputCat extends JDialog {
	
    private JButton botonCancelar;
    private JButton botonAceptar;
    private JLabel labelInstruction;
    private JPanel jPanel1;
    private JTextField textFieldNombre;
    private Boolean accept = false;
    private int tipoDG;
    
    public DialogoInputCat(Frame parent, boolean modal, int tipo) {
        super(parent, modal);
        setResizable(false);
        setSize(new Dimension(350, 250));
        getContentPane().setSize(new Dimension(350, 250));
        getContentPane().setPreferredSize(new Dimension(350, 250));
        this.tipoDG=tipo;
        if(this.tipoDG == TipoDialogo.DG_EDITA_CATEGORIA)
        setTitle("Editar - Categoría");
        if(this.tipoDG == TipoDialogo.DG_ALTA_CATEGORIA)
            setTitle("Alta - Categoría");
        initComponents();
        
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        labelInstruction = new JLabel();
        textFieldNombre = new JTextField();
        botonCancelar = new JButton();
        botonAceptar = new JButton();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        getContentPane().setSize(new Dimension(350, 250));
        
        if(this.tipoDG == TipoDialogo.DG_EDITA_CATEGORIA)
        	labelInstruction.setText("Inserta El Nuevo Nombre de Categoría : ");
        
        if(this.tipoDG == TipoDialogo.DG_ALTA_CATEGORIA)
        	labelInstruction.setText("Dale Un Nombre a la Nueva Categoría : ");
        
        textFieldNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textFieldNombreActionPerformed(evt);
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
        					.addComponent(textFieldNombre, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(labelInstruction, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        					.addGap(98))))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(32)
        			.addComponent(labelInstruction, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
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

    
    
    public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	
	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

	private void textFieldNombreActionPerformed(ActionEvent evt) {}                                           

    private void botonCancelarActionPerformed(ActionEvent evt) {
			this.setAccept(false);
			this.setVisible(false);		
			}
    
    private void botonAceptarActionPerformed(ActionEvent evt) {
    	this.setAccept(true);
		this.setVisible(false);	
	}
}
