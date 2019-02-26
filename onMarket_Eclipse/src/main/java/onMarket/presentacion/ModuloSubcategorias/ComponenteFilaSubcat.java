package onMarket.presentacion.ModuloSubcategorias;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import onMarket.negocio.ModuloCategorias.TCategoria;
import onMarket.negocio.ModuloSubcategorias.TSubcategoria;
import onMarket.presentacion.GeneralUtils.DialogoConfirma;
import onMarket.presentacion.GeneralUtils.DialogoError;
import onMarket.presentacion.GeneralUtils.TipoDialogo;
import onMarket.presentacion.ModuloCategorias.GUICategorias;
import onMarket.presentacion.ModuloProductos.GUIProductos;

@SuppressWarnings("serial")
public class ComponenteFilaSubcat extends JPanel  {
	private GUICategorias parentComponent;
	private GUISubcategorias subs;
	private ControladorSubcategorias control;
    private TCategoria tCategoria; 
    private TSubcategoria tSubcategoria;
	private Box.Filler filler1;
    private JButton botonEditar;
    private JButton botonLista;
    private JButton botonEliminar;
    private JLabel labelActual;
    private String contentLabel;
    private JSeparator separator;

    
    public ComponenteFilaSubcat(TCategoria tCat,TSubcategoria subcat,ControladorSubcategorias cont,GUICategorias padreC,
    		GUISubcategorias subs)  
    {
    	setBorder(UIManager.getBorder("TextField.border"));
        this.parentComponent=padreC;
        this.subs = subs;
    	this.contentLabel=subcat.getNombre();
        this.control=cont;
    	this.tCategoria=tCat;
    	this.tSubcategoria=subcat;
        initComponents();
    }

    private void initComponents() {

        labelActual = new JLabel();
        botonEditar = new JButton();
        botonLista = new JButton();
        botonEliminar = new JButton();
        filler1 = new Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));

        labelActual.setText(this.contentLabel);


        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(Color.BLACK);
        
        botonLista.setText("Ir a Productos");
        botonLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	GUIProductos guiProd = new GUIProductos(subs, tSubcategoria);
                parentComponent.getParentDialog().setComponentInTab(guiProd,
                		parentComponent.getPosInTabs(),"Productos");
            	guiProd.setVisible(true);
            }
        });
        
        if (this.parentComponent.getAdmin()){
	        botonEditar.setText("Editar");
	        botonEditar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	 DialogoInputSubcat dialogo= new DialogoInputSubcat(parentComponent,true,
	            			 											TipoDialogo.DG_EDITA_SUBCATEGORIA,tCategoria);
	                 dialogo.getTextFieldNombre().setText(tSubcategoria.getNombre());
	            	 dialogo.setVisible(true);
	                 if (dialogo.getAccept()){
	                 	String nuevoNombre= dialogo.getTextFieldNombre().getText();
	                 	Integer nuevaCat=new Integer(dialogo.getSelection().getId());
	                 	String nuevoSinEspacio= nuevoNombre.trim();
	                 	Pattern pat = Pattern.compile("[[A-ZñÑáéíóúÁÉÍÓÚ] +|[a-zñÑáéíóúÁÉÍÓÚ] +]+");
				     	Matcher mat = pat.matcher(nuevoSinEspacio);
				     	if (mat.matches()) {
				     		if(tSubcategoria.getNombre().equals(nuevoNombre) && 
				     		   tSubcategoria.getCategoria().intValue() == nuevaCat.intValue()){}
				     		else{
				     			TSubcategoria aux= new TSubcategoria(tSubcategoria.getId(),nuevoSinEspacio,
				     											 	 tCategoria.getActivo(),nuevaCat);	
				     			control.accion(EventoSubcategoria.EDITA_SUBCATEGORIA, aux);
				     			if (tSubcategoria.getCategoria().intValue() != nuevaCat.intValue()){
				     				dialogo.setVisible(false);
				     				 parentComponent.getParentDialog().setComponentInTab(parentComponent,parentComponent.getPosInTabs()
 											 ,"Categorías");
				     			}
				     		}
				     		if (nuevaCat.intValue() != tCategoria.getId().intValue()){
				     		}
				     	}
				     	else {
				     		dialogo.setVisible(false);
				     		String msj="El nombre debe ser una frase formada solo por letras";
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
	                	control.accion(EventoSubcategoria.BAJA_SUBCATEGORIA,tSubcategoria);   			     			
	                }
	            }
	        });
	        
	    
	
	        GroupLayout layout = new GroupLayout(this);
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(layout.createSequentialGroup()
	        					.addGap(82)
	        					.addComponent(filler1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(layout.createSequentialGroup()
	        					.addContainerGap()
	        					.addComponent(labelActual, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(botonEditar, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addComponent(botonLista, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addComponent(botonEliminar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
	        					.addGap(18)))
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
        	GroupLayout layout = new GroupLayout(this);
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(layout.createSequentialGroup()
	        					.addGap(82)
	        					.addComponent(filler1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(layout.createSequentialGroup()
	        					.addContainerGap()
	        					.addComponent(labelActual, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addComponent(botonLista, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
	        					.addGap(18)))
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

	public TCategoria gettCategoria() {
		return tCategoria;
	}

	public void settCategoria(TCategoria tCategoria) {
		this.tCategoria = tCategoria;
	}

	public TSubcategoria gettSubcategoria() {
		return tSubcategoria;
	}

	public void settSubcategoria(TSubcategoria tSubcategoria) {
		this.tSubcategoria = tSubcategoria;
	}

    
}
