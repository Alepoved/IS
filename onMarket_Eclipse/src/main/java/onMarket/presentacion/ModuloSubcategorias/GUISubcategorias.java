package onMarket.presentacion.ModuloSubcategorias;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import onMarket.negocio.ModuloCategorias.TCategoria;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.negocio.ModuloSubcategorias.AS_SubcategoriaImp;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
import onMarket.presentacion.GeneralUtils.DialogoError;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.ModuloCategorias.GUICategorias;
import onMarket.presentacion.admin.AfterLoginGUI;
import onMarket.presentacion.admin.IGUI;


@SuppressWarnings("serial")
public class GUISubcategorias extends JPanel implements IGUI {
	private GUICategorias parentComponent;
	private ControladorSubcategorias control;
	private ArrayList<TSubcategoria> filas;
	private JPanel panelDer;
	private JButton botonAgregar;
	private JScrollPane scrollPane;
	private JPanel panelIzq;
	private TCategoria miCategoria;
	private JButton btnNewButton;

	public GUISubcategorias(GUICategorias parent,TCategoria cat) {
		this.parentComponent=parent;
		this.miCategoria = cat;
		setSize(new Dimension(680, 400));
		setPreferredSize(new Dimension(680, 400));
		setFont(new Font("Tahoma", Font.PLAIN, 10));
		AS_SubcategoriaImp AppServ = new AS_SubcategoriaImp();
		this.control = new ControladorSubcategorias(AppServ, this);
		this.filas = new ArrayList<TSubcategoria>();
		initComponents();
	}

	private void initComponents() {
		panelDer = new JPanel();

		setSize(new Dimension(650, 400));

		btnNewButton = new JButton("Ir Atrás");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parentComponent.getAdmin())
					parentComponent.getParentDialog().setComponentInTab(parentComponent,parentComponent.getPosInTabs()
							,"Categorías");
				else
					parentComponent.getParentDialog().setComponentInTab(parentComponent,parentComponent.getPosInTabs()
							,"Nuestros Productos - Categorías");
			}
		});
		
		if (this.parentComponent.getAdmin()){
			
			botonAgregar = new JButton("Nueva Subcategoria");
			botonAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DialogoInputSubcat dialogo = new DialogoInputSubcat(parentComponent, true,
																		TipoDialogo.DG_ALTA_SUBCATEGORIA,miCategoria);
					dialogo.getComboBox().setVisible(false);
					dialogo.getLblEscogeUnaCategoria().setVisible(false);
					dialogo.setVisible(true);
					
					if (dialogo.getAccept()) {
						String nombre = dialogo.getTextFieldNombre().getText();
						String nuevoSinEspacio = nombre.trim();
						Pattern pat = Pattern.compile("[[A-ZñÑáéíóúÁÉÍÓÚ] +|[a-zñÑáéíóúÁÉÍÓÚ] +]+");
						Matcher mat = pat.matcher(nuevoSinEspacio);
						
						if (mat.matches()) {
							TSubcategoria aux = new TSubcategoria(nuevoSinEspacio);
							aux.setActivo(true);
							aux.setCategoria(miCategoria.getId());
							control.accion(EventoSubcategoria.ALTA_SUBCATEGORIA, aux);
						} else {
							dialogo.setVisible(false);
							String msj = "El Nombre Debe Ser Una Frase Formada Solo Por Letras";
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
							.addComponent(botonAgregar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
						.addGap(4))
			);
			gl_panelDer.setVerticalGroup(
				gl_panelDer.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDer.createSequentialGroup()
						.addGap(75)
						.addComponent(botonAgregar)
						.addGap(78)
						.addComponent(btnNewButton)
						.addContainerGap(201, Short.MAX_VALUE))
			);
			panelDer.setLayout(gl_panelDer);
		}
		else{
			
			JLabel info = new JLabel();
			info.setText("Haga click en una de las subcategorías o vuelva a categorias si lo prefiere");
			
			GroupLayout gl_panelDer = new GroupLayout(panelDer);
			gl_panelDer.setHorizontalGroup(
				gl_panelDer.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelDer.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelDer.createParallelGroup(Alignment.LEADING)
							.addComponent(info,  Alignment.TRAILING,  GroupLayout.DEFAULT_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
						.addGap(4))
			);
			gl_panelDer.setVerticalGroup(
				gl_panelDer.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDer.createSequentialGroup()
						.addGap(75)
						.addComponent(info)
						.addGap(78)
						.addComponent(btnNewButton)
						.addContainerGap(201, Short.MAX_VALUE))
			);
			panelDer.setLayout(gl_panelDer);
		}

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		GroupLayout layout = new GroupLayout(this);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelDer, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panelDer, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));

		panelIzq = new JPanel();
		scrollPane.setViewportView(panelIzq);
		panelIzq.setLayout(new GridLayout(0, 1, 0, 0));

		setLayout(layout);
		parentComponent.getParentWindow().pack();
		TSubcategoria aux = new TSubcategoria("aux");
		aux.setCategoria(this.miCategoria.getId());
		control.accion(EventoSubcategoria.MOSTRAR_SUBCATEGORIAS, aux);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		/*
		 * Respuestas en caso Alta Categoria
		 */
		case EventoSubcategoria.RES_ALTA_SUBCATEGORIA_OK: {
			TSubcategoria castDatos = (TSubcategoria) datos;
			ComponenteFilaSubcat componente = new ComponenteFilaSubcat(miCategoria, castDatos, control,
																	   parentComponent, this);
			filas.add(castDatos);
			panelIzq.add(componente);
			panelIzq.updateUI();
			break;
		}

		case EventoSubcategoria.RES_ALTA_SUBCATEGORIA_KO: {
			String msj = "Error Interno - Debes Asegurarte de Que La Subcategoria Introducida no Existe";
			DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
			dialogoE.setVisible(true);
			if (dialogoE.getAccept()) {
			}

			break;
		}

		/*
		 * Respuestas en caso Baja Categoria
		 */
		case EventoSubcategoria.RES_BAJA_SUBCATEGORIA_OK: {
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

		case EventoSubcategoria.RES_BAJA_SUBCATEGORIA_KO: {
			String msj = "Error Interno - No se ha podido Eliminar";
			DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
			dialogoE.setVisible(true);
			if (dialogoE.getAccept()) {
			}
			break;
		}

		/*
		 * Respuestas en caso Mostrar Categorias
		 */
		case EventoSubcategoria.RES_MOSTRAR_SUBCATEGORIAS_OK: {
			@SuppressWarnings("unchecked")
			ArrayList<TSubcategoria> castDatos = (ArrayList<TSubcategoria>) datos;
			for (int i = 0; i < castDatos.size(); i++) {
				TSubcategoria aux = castDatos.get(i);
				ComponenteFilaSubcat componente = new ComponenteFilaSubcat(this.miCategoria, aux,this.control,
																			parentComponent, this);
				filas.add(i, aux);
				panelIzq.add(componente, i);
				panelIzq.updateUI();

			}

			break;
		}

		case EventoSubcategoria.RES_MOSTRAR_SUBCATEGORIAS_KO: {
			String msj = "Error Interno - No se ha podido Obtener los datos";
			DialogoError dialogoE = new DialogoError(parentComponent.getParentWindow(),true,msj);
			dialogoE.setVisible(true);
			if (dialogoE.getAccept()) {
			}
			this.setVisible(false);
			break;

		}

		/*
		 * Respuestas en caso Editar Nombre Categoria
		 */
		case EventoSubcategoria.RES_EDITA_SUBCATEGORIA_OK: {
			TSubcategoria castDatos= (TSubcategoria) datos;
			int index=-1;
			for(int i=0;i<filas.size();i++){
			 	if (filas.get(i).getId() == castDatos.getId() ){
			 		index=i;
			 	}
			 	if(index >=0 ){
				 filas.remove(index);
				 filas.add(index,castDatos);
				 panelIzq.remove(index);
				 ComponenteFilaSubcat nuevoComponente=new ComponenteFilaSubcat(miCategoria, castDatos,control,
						 											           parentComponent, this);
				 panelIzq.add(nuevoComponente, index);
			 	}
			 	panelIzq.updateUI();
			 }
			//En el caso de que se haya cambiado la categoria con exito mostramos un mensaje informativo de ello
 			String mensaje = "Se ha editado la categoria correctamente";
 			JOptionPane.showMessageDialog(parentComponent.getParentWindow(),mensaje,
 										  "Cambio de categoria", JOptionPane.INFORMATION_MESSAGE);
 			break;
		}

		case EventoSubcategoria.RES_EDITA_SUBCATEGORIA_KO: {
			String msj="Error Interno - No se ha podido Editar- Intenta Borrarla y Dar de Alta una Nueva";
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
	
	public JFrame getParentWindow() {
		return this.parentComponent.getParentWindow();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TSubcategoria> getFilas() {
		ArrayList<TSubcategoria> res= (ArrayList<TSubcategoria>) filas.clone();
		return res;
	}
	
	public AfterLoginGUI getParentDialog() {
		return this.parentComponent.getParentDialog();
	}
	
	public int getPosInTabs() {
		return this.parentComponent.getPosInTabs();
	}

	public void prodEnCesta(int posInTab, TProducto p){
		parentComponent.prodEnCesta(posInTab, p);
	}
}
