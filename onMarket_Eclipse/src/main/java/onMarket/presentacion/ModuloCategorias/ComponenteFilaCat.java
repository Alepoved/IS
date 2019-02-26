package onMarket.presentacion.ModuloCategorias;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import onMarket.negocio.ModuloCategorias.TCategoria;
import onMarket.presentacion.GeneralUtils.DialogoConfirma;
import onMarket.presentacion.GeneralUtils.DialogoError;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.ModuloSubcategorias.GUISubcategorias;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class ComponenteFilaCat extends JPanel {
	private GUICategorias parentComponent; 
	private ControladorCategorias control;
    private TCategoria tCategoria;
	private Box.Filler filler1;
    private JButton botonEditar;
    private JButton botonLista;
    private JButton botonEliminar;
    private JLabel labelActual;
    private String contentLabel;
    private JSeparator separator;

    
    public ComponenteFilaCat(TCategoria tCat,ControladorCategorias cont,GUICategorias miComponente )  {
    	setBorder(UIManager.getBorder("TextField.border"));
    	this.tCategoria=tCat;
    	this.control=cont;
    	this.parentComponent=miComponente;
    	this.contentLabel=tCat.getNombre();
        initComponents();
    }

    private void initComponents() {

        labelActual = new JLabel();
        botonEditar = new JButton();
        botonLista = new JButton();
        botonEliminar = new JButton();
        filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));

        labelActual.setText(this.contentLabel);
        
        botonLista.setText("Ir a Subcategorías");
        botonLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	GUISubcategorias guiSubcat= new GUISubcategorias(parentComponent, tCategoria);
            	if (parentComponent.getAdmin())
            		parentComponent.getParentDialog().setComponentInTab(guiSubcat,parentComponent.getPosInTabs()
                													,"Subcategorías");
            	else
            		parentComponent.getParentDialog().setComponentInTab(guiSubcat,parentComponent.getPosInTabs()
							,"Nuestros Productos - Subcategorías");
            	guiSubcat.setVisible(true);
            }
        });
        
        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(Color.BLACK);
        
        if (this.parentComponent.getAdmin()){
	
	        botonEditar.setText("Editar");
	        botonEditar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                DialogoInputCat dialogo= new DialogoInputCat(parentComponent.getParentWindow(),true,
	                											 TipoDialogo.DG_EDITA_CATEGORIA);
	                dialogo.setVisible(true);
	                if (dialogo.getAccept()){
	                	String nuevoNombre= dialogo.getTextFieldNombre().getText();
	                	String nuevoSinEspacio= nuevoNombre.trim();
	                	Pattern pat = Pattern.compile("[[A-ZñÑáéíóúÁÉÍÓÚ] +|[a-zñÑáéíóúÁÉÍÓÚ] +]+");
	   			     	Matcher mat = pat.matcher(nuevoSinEspacio);
	   			     	if (mat.matches()) {
	   			     		TCategoria aux= new TCategoria(tCategoria.getId(),nuevoSinEspacio,
	   			     										tCategoria.getActivo());
	   			     		control.accion(EventoCategorias.EDITA_CATEGORIA, aux); 
	   			     	} else {
	   			     		dialogo.setVisible(false);
	   			     		String msj="El Nombre Debe Ser Una Frase Formada Solo Por Letras";
	   			     		DialogoError dialogoE= new DialogoError(parentComponent.getParentWindow(),true,msj);
	   			     		dialogoE.setVisible(true);
	   			     		if(dialogoE.getAccept()){}
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
	                	control.accion(EventoCategorias.BAJA_CATEGORIA,tCategoria);   			     			
	                }
	            }
	        });
	
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(layout.createSequentialGroup()
	        					.addGap(82)
	        					.addComponent(filler1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(layout.createSequentialGroup()
	        					.addContainerGap()
	        					.addComponent(labelActual, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(botonEditar, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addComponent(botonLista, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(botonEliminar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
	        			.addGap(4))
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGap(5)
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
	        				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	        				.addGroup(layout.createSequentialGroup()
	        					.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
	        						.addComponent(labelActual, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
	        						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	        							.addComponent(botonEditar)
	        							.addComponent(botonLista)
	        							.addComponent(botonEliminar)))))
	        			.addContainerGap())
	        );
	        this.setLayout(layout);
        }
        else{
        	
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(layout.createSequentialGroup()
	        					.addGap(82)
	        					.addComponent(filler1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(layout.createSequentialGroup()
	        					.addContainerGap()
	        					.addComponent(labelActual, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addComponent(botonLista, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
	        			.addGap(4))
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGap(5)
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
	        				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	        				.addGroup(layout.createSequentialGroup()
	        					.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
	        						.addComponent(labelActual, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
	        						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	        							.addComponent(botonLista)))))
	        			.addContainerGap())
	        );
	        this.setLayout(layout);
       	}
    }                        

       
    @Override
    protected void paintComponent(Graphics g){
     super.paintComponent(g);
     
    }


}
