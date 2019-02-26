package onMarket.presentacion.admin;

import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import onMarket.negocio.ModuloCategorias.AS_CategoriaImp;
import onMarket.negocio.ModuloPedidos.AS_PedidoImp;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.presentacion.ModuloCategorias.ControladorCategorias;
import onMarket.presentacion.ModuloCategorias.GUICategorias;
import onMarket.presentacion.ModuloPedidos.ControladorPedidos;
import onMarket.presentacion.ModuloPedidos.GUICesta;
import onMarket.presentacion.ModuloPedidos.GUImisPedidos;
import onMarket.presentacion.ModuloUsuarios.ControladorUsuarios;
import onMarket.presentacion.ModuloUsuarios.GUIUsuarios;
import onMarket.negocio.ModuloUsuarios.AS_UsuarioImp;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.negocio.Seguridad.AS_SeguridadImp;


/*
 * Ventana (Dialogo) Secundaria "única" de la app, siempre se crea vacía y se le agregan 
 * Componentes específicos de cada módulo dentro de sus pestañas
 * De esta manera serán siempre los componentes de las distintas capas quienes
 * implementan la interfaz IGUI con el método "actualizar()"
 * 
 * */

@SuppressWarnings("serial")
public class AfterLoginGUI extends JDialog {
    private GUICategorias tabCategorias;
    private GUICesta tabCesta;
    private JTabbedPane jTabbedPane1;
    private TUsuario datosUsuario;
	private GUIUsuarios tabUsuarios;
	private GUImisPedidos tabMisPedidos; 
	
    
	public AfterLoginGUI(JFrame parent, boolean modal, TUsuario usuario) {
        super(parent, modal);
        this.datosUsuario = usuario;
        setTitle("OnMarket - Bienvenido \"" + usuario.getNombre() + "\"");
        
        initComponents();
    }

    
    private void initComponents() {
    	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jTabbedPane1 = new JTabbedPane();
        
		
        AS_CategoriaImp appServiceCat= new AS_CategoriaImp ();
	    final ControladorCategorias control = new ControladorCategorias(appServiceCat,null);
        tabCategorias = new GUICategorias((JFrame) this.getParent(),this,0,control,
        								   this.datosUsuario); 
        
        AS_PedidoImp appServicePed= new AS_PedidoImp();
	    final ControladorPedidos controlPed = new ControladorPedidos(appServicePed,null);
	    final ControladorPedidos controlCesta = new ControladorPedidos(appServicePed, null);
	    tabCesta = new GUICesta((JFrame) this.getParent(),
	    						controlCesta, datosUsuario);
	    
	    tabMisPedidos = new GUImisPedidos((JFrame)this.getParent(), this, 2, controlPed, datosUsuario); 
	    
        
        //always the last
	    AS_UsuarioImp appServiceUsu= new AS_UsuarioImp ();
	    AS_SeguridadImp appServiceSeg= new AS_SeguridadImp();
	    final ControladorUsuarios controlUsu = new ControladorUsuarios(appServiceUsu,
	    																null,appServiceSeg);
	    
	    
        
        jTabbedPane1.add(tabCategorias, 0);
        
        if(!datosUsuario.getEsAdmin()){
        	jTabbedPane1.setTitleAt(0, "Nuestros Productos - Categorias");
        	
        	jTabbedPane1.add(tabCesta, 1);
        	jTabbedPane1.setTitleAt(1, "Mi Cesta");
        	jTabbedPane1.add(tabMisPedidos, 2); 
        	jTabbedPane1.setTitleAt(2, "Mis Pedidos");
        	
        	//always the last
        	tabUsuarios = new GUIUsuarios((JFrame) this.getParent(),this,3,controlUsu,
					datosUsuario);
        	jTabbedPane1.add(tabUsuarios, 3);
        	jTabbedPane1.setTitleAt(3,"Mi Perfil");
        	
        }
        else
        	jTabbedPane1.setTitleAt(0,"Categorias");
        	
        
        
        int alto= super.getToolkit().getScreenSize().height-100;
        int ancho=super.getToolkit().getScreenSize().width-50;
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1,GroupLayout.DEFAULT_SIZE,ancho, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1,GroupLayout.PREFERRED_SIZE,alto, Short.MAX_VALUE)
        );

        pack();
    }                        

    public void setComponentInTab(Component componente,int index,String tittle){
    		jTabbedPane1.setComponentAt(index, componente);
    		jTabbedPane1.setTitleAt(index,tittle);	
        
    }


	public void prodEnCesta(int posInTab, TProducto p) {
		if (posInTab==1)
			this.tabCesta.addProducto(p);
			this.jTabbedPane1.setSelectedIndex(posInTab);
		
	}    
}
