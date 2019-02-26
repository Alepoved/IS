package onMarket.presentacion.ModuloProductos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import onMarket.negocio.ModuloProductos.AS_ProductoImp;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
import onMarket.presentacion.GeneralUtils.DialogoError;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.ModuloSubcategorias.GUISubcategorias;
import onMarket.presentacion.admin.IGUI;

@SuppressWarnings("serial")
public class GUIProductos extends JPanel implements IGUI {
	
	private GUISubcategorias parentComponent;
	private ControladorProductos control;
	private ArrayList<TProducto> filas;
	
	private JPanel panelDer;
	private JButton botonAgregar;
	private JScrollPane scrollPane;
	private JPanel panelIzq;
	private TSubcategoria miSubcategoria;
	private JButton botonAtras;
	
	private JPanel panelPpal;
    private JLabel labelMarca;
    private JLabel labelNombre;
    private JLabel labelPesoVol;
    private JLabel labelPrecio;
    private JLabel labelStock;
    private JPanel panelCabecera;
	
	public GUIProductos(GUISubcategorias parent, TSubcategoria subcat) {
		this.parentComponent = parent;
		this.miSubcategoria = subcat;
		setSize(new Dimension(680, 400));
		setPreferredSize(new Dimension(793, 400));
		setFont(new Font("Tahoma", Font.PLAIN, 10));
		AS_ProductoImp appServ = new AS_ProductoImp();
		this.control = new ControladorProductos(appServ, this);
		this.filas = new ArrayList<TProducto>();
		initComponents();
	}
	
	private void initComponents() {
		
		panelDer = new JPanel();
		
		setSize(new Dimension(650, 400));
		

		botonAtras = new JButton("Ir Atrás");
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parentComponent.getAdmin())
					parentComponent.getParentDialog().setComponentInTab(parentComponent,parentComponent.getPosInTabs()
					 											 ,"Subcategorías");
				else
					parentComponent.getParentDialog().setComponentInTab(parentComponent,parentComponent.getPosInTabs()
							 ,"Nuestros Productos - Subcategorías");
			}
		});
		
		if (this.parentComponent.getAdmin()){
			
			botonAgregar = new JButton("Nuevo Producto");
			botonAgregar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					DialogoInputProducto dialogo = new DialogoInputProducto(parentComponent, true,
																		TipoDialogo.DG_ALTA_PRODUCTO ,miSubcategoria);
			
					dialogo.getComboBox().setVisible(false);
					dialogo.getLblEscogeSubcategoria().setVisible(false);
					dialogo.setVisible(true);
					
					if (dialogo.getAccept()) {
						
						String nombre = dialogo.getTextFieldNombre().getText();
						String marca = dialogo.getTextFieldMarca().getText();
						String pesoVol = dialogo.getTextFieldPesoVol().getText();
						Double precio = Double.parseDouble(dialogo.getTextFieldPrecio().getText());
						Integer stock = (Integer) dialogo.getSpinnerStock().getValue();
						String foto = dialogo.getTextFieldFoto().getText();
						Pattern pat = Pattern.compile("[[A-ZñÑáéíóúÁÉÍÓÚ] +|[a-zñÑáéíóúÁÉÍÓÚ] +]+");
						Matcher mat1 = pat.matcher(nombre);
						Matcher mat2 = pat.matcher(marca);
						
						if (mat1.matches() && mat2.matches() && stock > 0 && precio > 0
								&& pesoVol.length() <= 4) {
							
							TProducto aux = new TProducto(nombre, marca);
							aux.setEstado(true);
							aux.setPesoVol(pesoVol); aux.setPrecio(precio);
							aux.setStock(stock); aux.setFoto(foto);
							aux.setSubcategoria(miSubcategoria.getId());
							control.accion(EventoProducto.ALTA_PRODUCTO, aux);
						}
						else if (stock <= 0){
							
							dialogo.setVisible(false);
							String msj = "El numero de unidades debe ser mayor que 0";
							DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
							dialogoE.setVisible(true);
							if (dialogoE.getAccept()) {
							}
						}
						else if (precio <= 0){
							
							dialogo.setVisible(false);
							String msj = "El precio del articulo debe ser mayor que 0";
							DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
							dialogoE.setVisible(true);
							if (dialogoE.getAccept()) {
							}
						}
						else if (pesoVol.length() > 4){
							
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
	
				}
			});
			
			GroupLayout gl_panelDer = new GroupLayout(panelDer);
			gl_panelDer.setHorizontalGroup(
				gl_panelDer.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelDer.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelDer.createParallelGroup(Alignment.LEADING)
							.addComponent(botonAgregar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addComponent(botonAtras, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
						.addGap(4))
			);
			gl_panelDer.setVerticalGroup(
				gl_panelDer.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDer.createSequentialGroup()
						.addGap(75)
						.addComponent(botonAgregar)
						.addGap(78)
						.addComponent(botonAtras)
						.addContainerGap(201, Short.MAX_VALUE))
			);
			panelDer.setLayout(gl_panelDer);
		}
		
		else{
			
			GroupLayout gl_panelDer = new GroupLayout(panelDer);
			gl_panelDer.setHorizontalGroup(
				gl_panelDer.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelDer.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelDer.createParallelGroup(Alignment.LEADING)
							.addComponent(botonAtras, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
						.addGap(4))
			);
			gl_panelDer.setVerticalGroup(
				gl_panelDer.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDer.createSequentialGroup()
						.addGap(75)
						.addComponent(botonAtras)
						.addContainerGap(201, Short.MAX_VALUE))
			);
			panelDer.setLayout(gl_panelDer);
		}

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelPpal = new JPanel();
	        panelCabecera = new javax.swing.JPanel();
	        labelNombre = new javax.swing.JLabel();
	        labelMarca = new javax.swing.JLabel();
	        labelPrecio = new javax.swing.JLabel();
	        labelPesoVol = new javax.swing.JLabel();
	        labelStock = new javax.swing.JLabel();

	        labelNombre.setFont(new java.awt.Font("Century Gothic", 1, 11));
	        labelNombre.setText("Nombre");

	        labelMarca.setFont(new java.awt.Font("Century Gothic", 1, 11));
	        labelMarca.setText("Marca");

	        labelPrecio.setFont(new java.awt.Font("Century Gothic", 1, 11));
	        labelPrecio.setText("Precio");

	        labelPesoVol.setFont(new java.awt.Font("Century Gothic", Font.BOLD, 11));
	        labelPesoVol.setText("kg/L");

	        labelStock.setFont(new java.awt.Font("Century Gothic", 1, 11));
	        labelStock.setText("Unidades");

	        javax.swing.GroupLayout panelCabeceraLayout = new javax.swing.GroupLayout(panelCabecera);
	        panelCabeceraLayout.setHorizontalGroup(
	        	panelCabeceraLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(panelCabeceraLayout.createSequentialGroup()
	        			.addGap(88)
	        			.addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(labelPrecio, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(labelPesoVol, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(labelStock)
	        			.addContainerGap(1290, Short.MAX_VALUE))
	        );
	        panelCabeceraLayout.setVerticalGroup(
	        	panelCabeceraLayout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(panelCabeceraLayout.createSequentialGroup()
	        			.addGap(0, 19, Short.MAX_VALUE)
	        			.addGroup(panelCabeceraLayout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(labelNombre)
	        				.addComponent(labelMarca)
	        				.addComponent(labelStock)
	        				.addComponent(labelPesoVol)
	        				.addComponent(labelPrecio)))
	        );
	        panelCabecera.setLayout(panelCabeceraLayout);
	    
		GroupLayout layout2 = new GroupLayout(this);
		layout2.setHorizontalGroup(
			layout2.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelDer, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		layout2.setVerticalGroup(
			layout2.createParallelGroup(Alignment.TRAILING)
				.addComponent(panelDer, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
		);

		panelIzq = new JPanel();
		scrollPane.setViewportView(panelPpal);
		panelIzq.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelPpal.setLayout(new BorderLayout());
		panelPpal.add(panelCabecera, BorderLayout.NORTH);
		panelPpal.add(panelIzq, BorderLayout.CENTER);

		setLayout(layout2);
		parentComponent.getParentWindow().pack();
		TProducto aux = new TProducto("aux", "auxMarca");
		aux.setSubcategoria(this.miSubcategoria.getId());
		
		control.accion(EventoProducto.MOSTRAR_PRODUCTOS, aux);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		
		switch (evento) {
		/*
		 * Respuestas en caso Alta Producto
		 */
		case EventoProducto.RES_ALTA_PRODUCTO_OK:{
			
			TProducto castDatos = (TProducto) datos;
			
			ComponenteFilaProducto componente = new ComponenteFilaProducto(miSubcategoria, castDatos, control,
																	   parentComponent);
			filas.add(castDatos);
			panelIzq.add(componente);
			panelIzq.updateUI();
			break;
		}

		case EventoProducto.RES_ALTA_PRODUCTO_KO: {
			
			String msj = "ERROR INTERNO - Debes asegurarte de que el producto introducido no existe";
			DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
			dialogoE.setVisible(true);
			if (dialogoE.getAccept()) {
			}

			break;
		}

		/*
		 * Respuestas en caso Baja Producto
		 */
		case EventoProducto.RES_BAJA_PRODUCTO_OK:{
			Integer castDatos = (Integer) datos; // debe ser el id del borrado
			int index = -1;
			for (int i = 0; i < filas.size(); i++) {
				if (filas.get(i).getId().intValue() == castDatos.intValue()) {
					index = i;
				}
			}
			if (index >= 0) {
				filas.remove(index);
				panelIzq.remove(index);
			}
			panelIzq.updateUI();
			break;
		}

		case EventoProducto.RES_BAJA_PRODUCTO_KO: {
			String msj = "ERROR INTERNO - No se ha podido eliminar";
			DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
			dialogoE.setVisible(true);
			if (dialogoE.getAccept()) {
			}
			break;
		}

		/*
		 * Respuestas en caso Mostrar Productos
		 */
		case EventoProducto.RES_MOSTRAR_PRODUCTOS_OK: {
			
			@SuppressWarnings("unchecked")
			ArrayList<TProducto> castDatos = (ArrayList<TProducto>) datos;
			for (int i = 0; i < castDatos.size(); i++) {
				TProducto aux = castDatos.get(i);
				ComponenteFilaProducto componente = new ComponenteFilaProducto(this.miSubcategoria, aux,this.control,
																			parentComponent);
				filas.add(i, aux);
				panelIzq.add(componente, i);
				panelIzq.updateUI();

			}

			break;
		}

		case EventoProducto.RES_MOSTRAR_PRODUCTOS_KO: {
			String msj = "ERROR INTERNO - No se han podido obtener los datos";
			DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
			dialogoE.setVisible(true);
			if (dialogoE.getAccept()) {
			}
			this.setVisible(false);
			break;

		}

		/*
		 * Respuestas en caso Editar Producto
		 */
		case EventoProducto.RES_EDITA_PRODUCTO_OK: {
			
			TProducto castDatos= (TProducto) datos;
			int index=-1;
			for(int i=0;i<filas.size();i++){
			 	if (filas.get(i).getId() == castDatos.getId() ){
			 		index=i;
			 	}
			 	if(index >=0 ){
				 filas.remove(index);
				 filas.add(index,castDatos);
				 panelIzq.remove(index);
				 ComponenteFilaProducto nuevoComponente=new ComponenteFilaProducto(miSubcategoria, castDatos,control,
						 											           parentComponent);
				 panelIzq.add(nuevoComponente, index);
			 	}
			 	panelIzq.updateUI();
			 }
			
			//En el caso de que se haya cambiado la categoria con exito mostramos un mensaje informativo de ello
 			String mensaje = "Se ha editado el producto correctamente";
 			JOptionPane.showMessageDialog(parentComponent.getParentWindow(),mensaje,
 										  "Cambio de subcategoria", JOptionPane.INFORMATION_MESSAGE);
 			break;
		}

		case EventoProducto.RES_EDITA_PRODUCTO_KO: {
			String msj="ERROR INTERNO - No se ha podido editar- Intenta borrarlo y dar de alta uno nuevo";
	     	DialogoError dialogoE= new DialogoError(parentComponent.getParentWindow(),true,msj);
	     	dialogoE.setVisible(true);
	     	if(dialogoE.getAccept()){}
	     	
			break;
		}
		
		case EventoProducto.RES_REPONER_PRODUCTO_OK:{
			
			TProducto castDatos= (TProducto) datos;
			int index=-1;
			for(int i=0;i<filas.size();i++){
			 	if (filas.get(i).getId() == castDatos.getId()){
			 		index=i;
			 	}
			 	if(index >=0 ){
			 		filas.remove(index);
			 		filas.add(index,castDatos);
			 		panelIzq.remove(index);
			 		ComponenteFilaProducto nuevoComponente=new ComponenteFilaProducto(miSubcategoria, castDatos,control,
						 											           parentComponent);
			 		panelIzq.add(nuevoComponente, index);
			 	}
			 	panelIzq.updateUI();
			 }
			
			//En el caso de que se haya cambiado la categoria con exito mostramos un mensaje informativo de ello
 			String mensaje = "Se ha editado el producto correctamente";
 			JOptionPane.showMessageDialog(parentComponent.getParentWindow(),mensaje,
 										  "Cambio de subcategoria", JOptionPane.INFORMATION_MESSAGE);
 			break;
		}
		case EventoProducto.RES_REPONER_PRODUCTO_KO:{
			String msj="ERROR INTERNO - No se ha podido reponer el articulo";
	     	DialogoError dialogoE= new DialogoError(parentComponent.getParentWindow(),true,msj);
	     	dialogoE.setVisible(true);
	     	if(dialogoE.getAccept()){}
	     	
			break;
		}
		
		
		}
	}
	
	public boolean getAdmin(){
		return this.parentComponent.getAdmin();
	}

}
