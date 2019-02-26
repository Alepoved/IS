package onMarket.presentacion.ModuloSubcategorias;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import onMarket.negocio.ModuloCategorias.TCategoria;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.ModuloCategorias.GUICategorias;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class DialogoInputSubcat extends JDialog{
	private GUICategorias parentComponent;
    private JButton botonCancelar;
    private JButton botonAceptar;
    private JLabel labelInstruction;
    private JPanel jPanel1;
    private JTextField textFieldNombre;
    private JLabel lblEscogeUnaCategoria ;
    private JComboBox<Object> comboBox;
    private Boolean accept = false;
    private int tipoDG;
    TCategoria actualCat;
    
    public DialogoInputSubcat(GUICategorias parentC, boolean modal, int tipo, TCategoria actual) {
        super(parentC.getParentWindow(),modal);
        parentComponent= parentC;
        setResizable(false);
        setSize(new Dimension(350, 250));
        getContentPane().setSize(new Dimension(350, 250));
        getContentPane().setPreferredSize(new Dimension(350, 250));
        this.comboBox = new JComboBox<Object>();
        this.tipoDG=tipo;
        this.actualCat=actual;
        if(this.tipoDG == TipoDialogo.DG_EDITA_SUBCATEGORIA)
        setTitle("Editar - Subcategoría");
        if(this.tipoDG == TipoDialogo.DG_ALTA_SUBCATEGORIA)
            setTitle("Alta - Subcategoría");
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
        
        if(this.tipoDG == TipoDialogo.DG_EDITA_SUBCATEGORIA)
        	labelInstruction.setText("Inserta El Nuevo Nombre de Subcategoría : ");
        
        if(this.tipoDG == TipoDialogo.DG_ALTA_SUBCATEGORIA)
        	labelInstruction.setText("Dale Un Nombre a la Nueva Subcategoría : ");
        
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
        
        lblEscogeUnaCategoria = new JLabel("Escoge una Categoría : ");
        TCategoria aux= new TCategoria(this.actualCat.getId(),"-- Mantener Actual--",this.actualCat.getActivo());
        ArrayList<TCategoria> lista= parentComponent.getFilas();
        lista.remove(actualCat);
        lista.add(0, aux);
        Object[] listaToShow = lista.toArray();
        comboBox.setMaximumRowCount(200);
        comboBox.setModel(new DefaultComboBoxModel<Object>(listaToShow));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        					.addComponent(botonAceptar)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(botonCancelar)
        					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        					.addComponent(textFieldNombre, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        					.addComponent(labelInstruction, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
        					.addGap(98))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(comboBox, 0, 330, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(lblEscogeUnaCategoria, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        					.addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(32)
        			.addComponent(labelInstruction, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(37)
        			.addComponent(lblEscogeUnaCategoria, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
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
        //setVisible(true);
        pack();
    }                        

    
    
    public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public TCategoria getSelection(){
		ComboBoxModel<Object> aux = this.comboBox.getModel();
		TCategoria catElegida=(TCategoria) aux.getElementAt(this.comboBox.getSelectedIndex());
		return catElegida;
	}
	
	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}


	public Component getComboBox() {
		return comboBox;
	}

	

	public Component getLblEscogeUnaCategoria() {
		return lblEscogeUnaCategoria;
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
