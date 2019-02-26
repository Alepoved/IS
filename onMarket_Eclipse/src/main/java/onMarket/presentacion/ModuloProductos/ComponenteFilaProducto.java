package onMarket.presentacion.ModuloProductos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
import onMarket.presentacion.GeneralUtils.DialogoConfirma;
import onMarket.presentacion.GeneralUtils.DialogoError;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.ModuloSubcategorias.GUISubcategorias;

@SuppressWarnings("serial")
public class ComponenteFilaProducto extends JPanel {

	private GUISubcategorias parentComponent; 
	private ControladorProductos control;
    private TSubcategoria tSubcategoria; 
    private TProducto tProducto;
    //ELEMENTOS GRAFICOS
    private JSeparator separator;
    private JPanel jPanel1;
    private JButton botonCesta;
    private JButton botonEditar;
    private JButton botonEliminar;
    private JButton botonReponer;
    private JTextField labelMarca;
    private String contentMarca;
    private JLabel labelFoto;
    private JTextField labelNombre;
    private String contentNombre;
    private JTextField labelPesoVol;
    private String contentPesoVol;
    private JTextField labelPrecio;
    private String contentPrecio;
    private JTextField labelStock;
    private String contentStock;
    
    public ComponenteFilaProducto(TSubcategoria sub ,TProducto producto, ControladorProductos ctrl 
    							  ,GUISubcategorias padreC)  
    {
    	setBorder(UIManager.getBorder("TextField.border"));
        this.parentComponent=padreC;
    	this.contentNombre = producto.getNombre();
    	this.contentMarca = producto.getMarca();
    	this.contentPrecio = producto.getPrecio().toString();
    	this.contentPesoVol = producto.getPesoVol();
    	this.contentStock = producto.getStock().toString();
        this.control = ctrl;
        this.tSubcategoria = sub;
        this.tProducto = producto;
        initComponents();
    }

    private void initComponents() {
    	
    	jPanel1 = new JPanel();
    	labelFoto = new JLabel();
    	labelNombre = new JTextField();
        labelMarca = new JTextField();
        labelPrecio = new JTextField();
        labelPesoVol = new JTextField();
        labelStock = new JTextField();
        botonEditar = new JButton();
        botonEliminar = new JButton();
        botonCesta = new JButton();
        botonReponer = new JButton();

        labelNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        labelMarca.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        labelPrecio.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        labelPesoVol.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        labelStock.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        labelNombre.setText(contentNombre);
        labelMarca.setText(contentMarca);
        labelPrecio.setText(contentPrecio);
        labelPesoVol.setText(contentPesoVol);
        labelStock.setText(contentStock);
        
        try{   
        	ImageIcon icon = null;
        	if (tProducto.getFoto().equals(""))
        		icon = new ImageIcon(getClass().getClassLoader().getResource("ImagenesProducto/NotFound.jpg"));
        	else
        		icon = new ImageIcon(getClass().getClassLoader().getResource("ImagenesProducto/" + tProducto.getFoto()));
        	Image imagen = icon.getImage();
        	imagen = imagen.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
        	icon = new ImageIcon(imagen);
        	labelFoto = new JLabel(icon);
        }
        catch(Exception e){
        	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("ImagenesProducto/NotFound.jpg"));
        	Image imagen = icon.getImage();
        	imagen = imagen.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
        	icon = new ImageIcon(imagen);
        	labelFoto = new JLabel(icon);
        }
        
        if (Integer.parseInt(contentStock) < 10)
        	labelStock.setForeground(Color.RED);
        
        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(Color.BLACK);

        labelNombre.setEditable(false);
        labelNombre.setBorder(null);

        labelMarca.setEditable(false);
        labelMarca.setBorder(null);

        labelPrecio.setEditable(false);
        labelPrecio.setBorder(null);

        labelPesoVol.setEditable(false);
        labelPesoVol.setBorder(null);

        labelStock.setEditable(false);
        labelStock.setBorder(null);
        
      
        botonCesta.setText("Añadir a cesta");
        botonCesta.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		if(tProducto.getStock() > 0){
        			parentComponent.prodEnCesta(1, tProducto);
        		}else {
        			String mensaje = "No Hay Suficientes Existencias El Producto"
        					         +" no se añade a Tu Cesta";
         			JOptionPane.showMessageDialog(parentComponent.getParentWindow(),mensaje,
         										  "Producto Agotado",
         										  JOptionPane.INFORMATION_MESSAGE);
        		  }
        	}
        });
        
        if (this.parentComponent.getAdmin()){
        	
	        botonEditar.setText("Editar");
	        botonEditar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	 DialogoInputProducto dialogo= new DialogoInputProducto(parentComponent, true,
	            			 											TipoDialogo.DG_EDITA_PRODUCTO, tSubcategoria);
	            	 
	                 dialogo.getTextFieldNombre().setText(tProducto.getNombre());
	                 dialogo.getTextFieldMarca().setText(tProducto.getMarca());
	                 dialogo.getTextFieldPrecio().setText(tProducto.getPrecio().toString());
	                 dialogo.getTextFieldPesoVol().setText(tProducto.getPesoVol());
	                 dialogo.getSpinnerStock().setValue(tProducto.getStock());
	                 dialogo.getTextFieldFoto().setText(tProducto.getFoto());
	            	 dialogo.setVisible(true);
	            	 
	                 if (dialogo.getAccept()){
	                	 
	                 	String nuevoNombre= dialogo.getTextFieldNombre().getText();
	                 	String nuevaMarca = dialogo.getTextFieldMarca().getText();
	                 	String nuevoPesoVol = dialogo.getTextFieldPesoVol().getText();
	                 	try{
	                 	Double nuevoPrecio = Double.parseDouble(dialogo.getTextFieldPrecio().getText());
	                 	Integer nuevaSubcat = new Integer(dialogo.getSelection().getId());
	                 	Integer nuevoStock = (Integer) dialogo.getSpinnerStock().getValue();
	                 	String nuevaFoto = dialogo.getTextFieldFoto().getText();
						Pattern pat = Pattern.compile("[[A-ZñÑáéíóúÁÉÍÓÚ] +|[a-zñÑáéíóúÁÉÍÓÚ] +]+");
						Matcher mat1 = pat.matcher(nuevoNombre);
						Matcher mat2 = pat.matcher(nuevaMarca);
						
				     	if (mat1.matches() && mat2.matches() && nuevoStock > 0 && nuevoPrecio > 0
								&& nuevoPesoVol.length() <= 4) {
				     		
				     		if(tProducto.getNombre().equals(nuevoNombre) && 
				     				tProducto.getSubcategoria().intValue() == nuevaSubcat.intValue()
				     				&& tProducto.getMarca().equals(nuevaMarca) &&
				     				tProducto.getPesoVol().equals(nuevoPesoVol) &&
				     				tProducto.getPrecio().equals(nuevoPrecio) &&
				     				tProducto.getStock().equals(nuevoStock) &&
				     				tProducto.getFoto().equals(nuevaFoto)){}
				     		
				     		else{
				     			
				     			TProducto aux= new TProducto(tProducto.getId(), nuevoNombre,
				     											 	 tSubcategoria.isActivo(), nuevaSubcat, 
				     											 	 nuevaMarca, nuevoPrecio, nuevoStock, 
				     											 	 nuevoPesoVol, nuevaFoto);	
				     			
				     			control.accion(EventoProducto.EDITA_PRODUCTO, aux);
				     			
				     			if (tProducto.getSubcategoria().intValue() != nuevaSubcat.intValue()){
				     				dialogo.setVisible(false);
				     				 parentComponent.getParentDialog().setComponentInTab(parentComponent,parentComponent.getPosInTabs()
 											 ,"Subcategorías");
				     			}
				     		}
				     	}
				     	else if (nuevoStock <= 0){
							
							dialogo.setVisible(false);
							String msj = "El numero de unidades debe ser mayor que 0";
							DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
							dialogoE.setVisible(true);
							if (dialogoE.getAccept()) {
							}
						}
						else if (nuevoPrecio <= 0){
							
							dialogo.setVisible(false);
							String msj = "El precio del articulo debe ser mayor que 0";
							DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
							dialogoE.setVisible(true);
							if (dialogoE.getAccept()) {
							}
						}
						else if (nuevoPesoVol.length() > 4){
							
							dialogo.setVisible(false);
							String msj = "El campo de peso no debe sobrepasar los 4 caracteres";
							DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
							dialogoE.setVisible(true);
							if (dialogoE.getAccept()) {
							}
						}
						else{
							
							dialogo.setVisible(false);
							String msj = "Nombre y marca deben ser una frase formada solo por letras";
							DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
							dialogoE.setVisible(true);
							if (dialogoE.getAccept()) {
							}
						}
				     	}
	                 	catch(NumberFormatException e){
	                 		dialogo.setVisible(false);
							String msj = "El precio debe ser un numero";
							DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
							dialogoE.setVisible(true);
							if (dialogoE.getAccept()) {
							}
	                 	}
	                 	
	                 }       
	            }
	        });
	
	        botonEliminar.setText("Eliminar");
	        botonEliminar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                DialogoConfirma dialogo= new DialogoConfirma(parentComponent.getParentWindow(),true);
	                dialogo.setVisible(true);
	                if (dialogo.getAccept()){
	                	control.accion(EventoProducto.BAJA_PRODUCTO ,tProducto);   			     			
	                }
	            }
	        });
	
	        botonReponer.setText("Reponer");
	        botonReponer.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent evt){
	        		DialogoInputProducto dialogo= new DialogoInputProducto(parentComponent, true,
								TipoDialogo.DG_REPONER_PRODUCTO, tSubcategoria);

					dialogo.getTextFieldNombre().setText(tProducto.getNombre());
					dialogo.getTextFieldNombre().setEnabled(false);
					dialogo.getTextFieldMarca().setText(tProducto.getMarca());
					dialogo.getTextFieldMarca().setEnabled(false);
					dialogo.getTextFieldPrecio().setText(tProducto.getPrecio().toString());
					dialogo.getTextFieldPrecio().setEnabled(false);
					dialogo.getTextFieldPesoVol().setText(tProducto.getPesoVol());
					dialogo.getTextFieldPesoVol().setEnabled(false);
					dialogo.getSpinnerStock().setValue(0);
					dialogo.getComboBox().setEnabled(false);
					dialogo.getTextFieldFoto().setText(tProducto.getFoto());
					dialogo.getTextFieldFoto().setEnabled(false);
					dialogo.getBotonImagen().setEnabled(false);
					dialogo.setVisible(true);
					
					if (dialogo.getAccept()){
					
						Integer nuevoStock = (Integer) dialogo.getSpinnerStock().getValue();
					
						if (nuevoStock > 0) {
					
							if(tProducto.getStock().equals(nuevoStock)){}
							
							else{
							
								TProducto aux= new TProducto(tProducto.getId(), tProducto.getNombre(),
														 	 tSubcategoria.isActivo(), tProducto.getSubcategoria(), 
														 	 tProducto.getMarca(), tProducto.getPrecio(), nuevoStock, 
														 	 tProducto.getPesoVol(), tProducto.getFoto());	
								
								control.accion(EventoProducto.REPONER_PRODUCTO, aux);
							}
						}
						else{
						
							dialogo.setVisible(false);
							String msj = "El numero de unidades debe ser mayor que 0";
							DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
							dialogoE.setVisible(true);
							if (dialogoE.getAccept()) {
							}
						}
					}       
				}
	        });
	        
	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(botonEditar)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(botonReponer)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(botonEliminar)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                
	                .addContainerGap(32, Short.MAX_VALUE))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addComponent(separator, GroupLayout.Alignment.TRAILING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(botonEditar)
	                    .addComponent(botonReponer)
	                    .addComponent(botonEliminar)
	                    )
	                .addContainerGap(22, Short.MAX_VALUE))
	        );

	        GroupLayout layout = new GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(19, 19, 19)
	                .addComponent(labelFoto, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelPrecio, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelPesoVol, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelStock, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(labelFoto, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 0, Short.MAX_VALUE))
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(labelPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(labelPesoVol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(labelStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
        }
        
        else{
	
        	GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(botonCesta, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(32, Short.MAX_VALUE))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addComponent(separator, GroupLayout.Alignment.TRAILING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(botonCesta))
	                .addContainerGap(22, Short.MAX_VALUE))
	        );

	        GroupLayout layout = new GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(19, 19, 19)
	                .addComponent(labelFoto, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelPrecio, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelPesoVol, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelStock, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(labelFoto, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 0, Short.MAX_VALUE))
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(labelPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(labelPesoVol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(labelStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
        }
    }                        

       
    @Override
    protected void paintComponent(Graphics g){
     super.paintComponent(g);
     
    }

}
