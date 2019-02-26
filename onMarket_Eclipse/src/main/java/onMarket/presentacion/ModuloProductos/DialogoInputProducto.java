package onMarket.presentacion.ModuloProductos;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileNameExtensionFilter;

import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.ModuloSubcategorias.GUISubcategorias;

@SuppressWarnings("serial")
public class DialogoInputProducto extends JDialog {
	
	private GUISubcategorias parentComponent;
    private Boolean accept = false;
    private int tipoDG;
    private TSubcategoria actualSubcat;
    
    //COMPONENTES GRAFICAS
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JButton botonImagen;
	private JComboBox<Object> comboBox;
	private JPanel jPanel1;
	private JLabel labelEscogeSubcategoria;
	private JLabel labelFoto;
	private JLabel labelInstruction;
	private JLabel labelMarca;
	private JLabel labelNombre;
	private JLabel labelPesoVol;
	private JLabel labelPrecio;
	private JLabel labelStock;
	private JSpinner spinnerStock;
	private JTextField textFieldFoto;
	private JTextField textFieldMarca;
	private JTextField textFieldNombre;
	private JTextField textFieldPesoVol;
	private JTextField textFieldPrecio;
	
    
    public DialogoInputProducto(GUISubcategorias parentC, boolean modal, int tipo, TSubcategoria actual) {
    	
        super(parentC.getParentWindow(),modal);
        parentComponent= parentC;
        setResizable(false);
        setSize(new Dimension(400, 400));
        getContentPane().setSize(new Dimension(400, 400));
        getContentPane().setPreferredSize(new Dimension(400, 400));
        
        this.comboBox = new JComboBox<Object>();
        this.tipoDG = tipo;
        this.actualSubcat = actual;
        
        if (this.tipoDG == TipoDialogo.DG_EDITA_PRODUCTO)
        	setTitle("Editar - Producto");
        else if (this.tipoDG == TipoDialogo.DG_ALTA_PRODUCTO)
        	setTitle("Alta - Producto");
        else if (this.tipoDG == TipoDialogo.DG_REPONER_PRODUCTO)
        	setTitle("Reponer producto");
        
        initComponents();
        
    }

    private void initComponents() {

    	jPanel1 = new JPanel();
        labelInstruction = new JLabel();
        labelNombre = new JLabel();
        labelMarca = new JLabel();
        labelPrecio = new JLabel();
        labelPesoVol = new JLabel();
        labelStock = new JLabel();
        labelFoto = new JLabel();
        labelEscogeSubcategoria = new JLabel();
        textFieldFoto = new JTextField();
        textFieldFoto.setEnabled(false);
        textFieldNombre = new JTextField();
        textFieldMarca = new JTextField();
        textFieldPrecio = new JTextField();
        textFieldPesoVol = new JTextField();
        spinnerStock = new JSpinner();
        botonCancelar = new JButton();
        botonAceptar = new JButton();
        botonImagen = new JButton();
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        getContentPane().setSize(new Dimension(400, 400));
        
        if (this.tipoDG == TipoDialogo.DG_EDITA_PRODUCTO)
        	labelInstruction.setText("Inserta los nuevos datos del producto: ");
        else if (this.tipoDG == TipoDialogo.DG_ALTA_PRODUCTO)
        	 labelInstruction.setText("Introduzca los campos necesarios para crear un nuevo producto:");
        else if (this.tipoDG == TipoDialogo.DG_REPONER_PRODUCTO)
        	labelInstruction.setText("Aumente el stock del producto: ");

        labelNombre.setText("Nombre:");
        labelMarca.setText("Marca:");
        labelPrecio.setText("Precio:");
        labelPesoVol.setText("Peso / volumen:");
        labelStock.setText("Unidades:");
        labelFoto.setText("Imagen:");
        labelEscogeSubcategoria.setText("Escoge una subcategoría:");
        
        spinnerStock.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        
        TSubcategoria aux = new TSubcategoria(this.actualSubcat.getId(), "-- Mantener Actual --", this.actualSubcat.isActivo(),
        		this.actualSubcat.getCategoria());
        
        ArrayList<TSubcategoria> subs = parentComponent.getFilas();
        
        subs.remove(this.actualSubcat);
        subs.add(0, aux);
        
        Object[] listaToShow = subs.toArray();
      
        comboBox.setMaximumRowCount(200);
        comboBox.setModel(new DefaultComboBoxModel<Object>(listaToShow));

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

        botonImagen.setText("Seleccionar");
        botonImagen.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		botonImagenActionPerformed(evt);
        	}
        });
        
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(labelPesoVol)
                            .addComponent(labelStock)
                            .addComponent(labelPrecio)
                            .addComponent(labelMarca)
                            .addComponent(labelNombre)
                            .addComponent(labelFoto))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldPesoVol)
                            .addComponent(textFieldPrecio)
                            .addComponent(textFieldMarca)
                            .addComponent(textFieldNombre)
                            .addComponent(spinnerStock, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(textFieldFoto)))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(botonAceptar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCancelar))
                            .addComponent(botonImagen, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelInstruction)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelEscogeSubcategoria)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstruction)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMarca)
                    .addComponent(textFieldMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPrecio)
                    .addComponent(textFieldPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPesoVol)
                    .addComponent(textFieldPesoVol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStock)
                    .addComponent(spinnerStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFoto)
                    .addComponent(textFieldFoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonImagen)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEscogeSubcategoria))
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonAceptar))
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                       

	private void botonImagenActionPerformed(ActionEvent evt) 
	{
		
    	JFileChooser seleccionaFichero = new JFileChooser();
    	seleccionaFichero.setFileFilter(new FileNameExtensionFilter("archivo de imagen", "jpg", "png", "jpeg"));
    	int option = seleccionaFichero.showOpenDialog(this);
    	if (option == JFileChooser.APPROVE_OPTION)
    	{
    		botonImagen.setText("Cambiar imagen");
    		textFieldFoto.setText(seleccionaFichero.getSelectedFile().getName());
    	}
    	
	}
	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}
    
    public JButton getBotonImagen() {
		return botonImagen;
	}

	public JTextField getTextFieldMarca() {
		return textFieldMarca;
	}
    
    public JTextField getTextFieldPrecio(){
    	return textFieldPrecio;
    }
    
    public JTextField getTextFieldPesoVol(){
    	return textFieldPesoVol;
    }
    
    public JSpinner getSpinnerStock(){
    	return spinnerStock;
    }
    
    public JTextField getTextFieldFoto(){
    	return textFieldFoto;
    }

	public TSubcategoria getSelection(){
		ComboBoxModel<Object> aux = this.comboBox.getModel();
		TSubcategoria subElegida = (TSubcategoria) aux.getElementAt(this.comboBox.getSelectedIndex());
		return subElegida;
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

	public Component getLblEscogeSubcategoria() {
		return labelEscogeSubcategoria;
	}                     

    private void botonCancelarActionPerformed(ActionEvent evt) {
			this.setAccept(false);
			this.setVisible(false);		
	}
    
    private void botonAceptarActionPerformed(ActionEvent evt) {
    	this.setAccept(true);
		this.setVisible(false);	
	}
}
