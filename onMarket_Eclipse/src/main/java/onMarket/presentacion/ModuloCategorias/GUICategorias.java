package onMarket.presentacion.ModuloCategorias;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Dimension;

import onMarket.negocio.ModuloCategorias.TCategoria;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.presentacion.GeneralUtils.DialogoError;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.admin.AfterLoginGUI;
import onMarket.presentacion.admin.IGUI;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class GUICategorias extends JPanel implements IGUI {
	private JFrame parentWindow; //Se usa cuando necesitamos un nuevo Jdialog
	private AfterLoginGUI parentDialog; // nos permite acceder a cambiar el contenido de una pestaña 
	private int posInTabs; // nos permite saber en que pestaña se ubica esta misma componente
	private ControladorCategorias control;
    private ArrayList<TCategoria> filas;
    private JPanel panelDer;
    private JButton botonAgregar;
    private JScrollPane scrollPane;
    private JPanel panelIzq;
    private TUsuario usuario;
	
	
	public GUICategorias(JFrame miFrame,AfterLoginGUI miDialog,int indexTab,ControladorCategorias cont,
			TUsuario user){
		setSize(new Dimension(650, 400));
		setPreferredSize(new Dimension(650, 400));
		setFont(new Font("Tahoma", Font.PLAIN, 10));
		this.filas= new ArrayList<TCategoria>();
		this.control=cont;
		this.parentWindow=miFrame;
		this.parentDialog=miDialog;
		this.posInTabs=indexTab;
		this.usuario = user;
		initComponents();
    }

    private void initComponents() {
    	control.setGui(this);
        panelDer = new JPanel();
        setSize(new Dimension(650, 400));
        
        if(this.usuario.getEsAdmin()){
        	
	        botonAgregar = new JButton("Nueva Categoria");
	        botonAgregar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		DialogoInputCat dialogo= new DialogoInputCat(parentWindow,true,TipoDialogo.DG_ALTA_CATEGORIA);
	                dialogo.setVisible(true);
	                if (dialogo.getAccept()){
	                	String nombre= dialogo.getTextFieldNombre().getText();
	                	String nuevoSinEspacio= nombre.trim();
	                	Pattern pat = Pattern.compile("[[A-ZñÑáéíóúÁÉÍÓÚ] +|[a-zñÑáéíóúÁÉÍÓÚ] +]+");
	   			     	Matcher mat = pat.matcher(nuevoSinEspacio);
	   			     	if (mat.matches()) {
	   			     		TCategoria aux= new TCategoria(nuevoSinEspacio);
	   			     		aux.setActivo(true);
	   			     		control.accion(EventoCategorias.ALTA_CATEGORIA, aux); 
	   			     	} else {
	   			     		dialogo.setVisible(false);
	   			     		String msj="El Nombre Debe Ser Una Frase Formada Solo Por Letras";
	   			     		DialogoError dialogoE= new DialogoError(parentWindow,true,msj);
	   			     		dialogoE.setVisible(true);
	   			     		if(dialogoE.getAccept()){}
	   			     	  }	
	                }
	             
	        	}
	        });
	
	        
	        GroupLayout gl_panelDer = new GroupLayout(panelDer);
	        gl_panelDer.setHorizontalGroup(
	        	gl_panelDer.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(gl_panelDer.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(botonAgregar, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
	        			.addGap(4))
	        );
	        gl_panelDer.setVerticalGroup(
	        	gl_panelDer.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panelDer.createSequentialGroup()
	        			.addGap(75)
	        			.addComponent(botonAgregar)
	        			.addContainerGap(302, Short.MAX_VALUE))
	        );
	        panelDer.setLayout(gl_panelDer);
        }
        
        else{
        	
        	JLabel info = new JLabel();
        	info.setText("Haga click en una de las categorias de nuestro supermercado");
        	 GroupLayout gl_panelDer = new GroupLayout(panelDer);
  	        gl_panelDer.setHorizontalGroup(
  	        	gl_panelDer.createParallelGroup(Alignment.TRAILING)
  	        		.addGroup(gl_panelDer.createSequentialGroup()
  	        			.addContainerGap()
  	        			.addComponent(info, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
  	        			.addGap(4))
  	        );
  	        
  	        gl_panelDer.setVerticalGroup(
  	            	gl_panelDer.createParallelGroup(Alignment.LEADING)
  	            		.addGroup(gl_panelDer.createSequentialGroup()
  	            			.addGap(75)
  	            			.addComponent(info)
  	            			.addContainerGap(302, Short.MAX_VALUE))
  	            );
  	        
  	        panelDer.setLayout(gl_panelDer);
        }
        
        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(panelDer, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(panelDer, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        		.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        
        panelIzq = new JPanel();
        scrollPane.setViewportView(panelIzq);
        panelIzq.setLayout(new GridLayout(0, 1, 0, 0));
        
        
        
        setLayout(layout);
        parentWindow.pack();
        control.accion(EventoCategorias.MOSTRAR_CATEGORIAS,null);
    }
    
    

	@SuppressWarnings("unchecked")
	public ArrayList<TCategoria> getFilas() {
		ArrayList<TCategoria> res= (ArrayList<TCategoria>) filas.clone();
		return res;
	}

	public void setFilas(ArrayList<TCategoria> filas) {
		this.filas = filas;
	}

	
	public JFrame getParentWindow() {
		return parentWindow;
	}

	public AfterLoginGUI getParentDialog() {
		return parentDialog;
	}

	public int getPosInTabs() {
		return posInTabs;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento){   
		/*
		 * Respuestas en caso Alta Categoria
		 * */
		case EventoCategorias.RES_ALTA_CATEGORIA_OK: 
		{ 
			TCategoria castDatos= (TCategoria) datos;
			ComponenteFilaCat componente= new ComponenteFilaCat(castDatos, control,this);
    		filas.add(castDatos);
    		panelIzq.add(componente);
    		panelIzq.updateUI();
			break;
		}
		
		case EventoCategorias.RES_ALTA_CATEGORIA_KO: 
		{ 		
			String msj="Error Interno - Debes Asegurarte de Que La Categoria Introducida no Existe";
	     	DialogoError dialogoE= new DialogoError(parentWindow,true,msj);
	     	dialogoE.setVisible(true);
	     	if(dialogoE.getAccept()){}
			break;
		}
		
		/*
		 * Respuestas en caso Baja Categoria
		 * */
		case EventoCategorias.RES_BAJA_CATEGORIA_OK: 
		{ 
			Integer castDatos= (Integer) datos; //debe ser el id del borrado
			int index=-1;
			for(int i=0;i<filas.size();i++){
			 	if (filas.get(i).getId().intValue() == castDatos.intValue() ){
			 		index=i;
			 	}
			}	
			if(index >=0 ){
				filas.remove(index);	 
				panelIzq.remove(index);
			 }
			panelIzq.updateUI();
			break;
		}			
		
		case EventoCategorias.RES_BAJA_CATEGORIA_KO:
		{
			String msj="Error Interno - No se ha podido Eliminar";
	     	DialogoError dialogoE= new DialogoError(parentWindow,true,msj);
	     	dialogoE.setVisible(true);
	     	if(dialogoE.getAccept()){}
			break;
		}
		
		/*
		 * Respuestas en caso Mostrar Categorias
		 * */
		case EventoCategorias.RES_MOSTRAR_CATEGORIAS_OK: 
			{ 
				@SuppressWarnings("unchecked")
				ArrayList<TCategoria> castDatos= (ArrayList<TCategoria>) datos;
				for(int i=0; i< castDatos.size();i++){
					TCategoria aux= castDatos.get(i);
					ComponenteFilaCat componente= new ComponenteFilaCat(aux,control,this);
	        		filas.add(i,aux);
	        		panelIzq.add(componente, i);
	        		panelIzq.updateUI();
				}
				
				break;
			}
			
		case EventoCategorias.RES_MOSTRAR_CATEGORIAS_KO: 
			{ 	
				String msj="Error Interno - No se ha podido Obtener los datos";
		     	DialogoError dialogoE= new DialogoError(parentWindow,true,msj);
		     	dialogoE.setVisible(true);
		     	if(dialogoE.getAccept()){}
				System.exit(0);
		     	break;
				
			}
			
		/*
		 * Respuestas en caso Editar Nombre Categoria
		 * */		
		case EventoCategorias.RES_EDITA_CATEGORIA_OK: 
		{ 
			TCategoria castDatos= (TCategoria) datos; //debe ser el id del borrado
			int index=-1;
			for(int i=0;i<filas.size();i++){
			 	if (filas.get(i).getId() == castDatos.getId() ){
			 		index=i;
			 	}
			 	if(index >=0 ){
				 filas.remove(index);
				 filas.add(index,castDatos);
				 panelIzq.remove(index);
				 ComponenteFilaCat nuevoComponente = new ComponenteFilaCat(castDatos,control,this);
				 panelIzq.add(nuevoComponente, index);
			 	}
			 	panelIzq.updateUI();
			 }
			break;
		}
		
		case EventoCategorias.RES_EDITA_CATEGORIA_KO:
		{
			String msj="Error Interno - No se ha podido Editar";
	     	DialogoError dialogoE= new DialogoError(parentWindow,true,msj);
	     	dialogoE.setVisible(true);
	     	if(dialogoE.getAccept()){}
			break;
		} 
	  }	
	}

	public boolean getAdmin(){
		return this.usuario.getEsAdmin();
	}

	public void prodEnCesta(int posInTab, TProducto p) {
		this.parentDialog.prodEnCesta(posInTab,p);
		
	}
		
}                        
