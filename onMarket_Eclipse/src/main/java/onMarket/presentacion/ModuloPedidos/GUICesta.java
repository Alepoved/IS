package onMarket.presentacion.ModuloPedidos;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.GroupLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import onMarket.negocio.ModuloPedidos.BOCesta;
import onMarket.negocio.ModuloPedidos.EstadoPedido;
import onMarket.negocio.ModuloPedidos.TLineaPedido;
import onMarket.negocio.ModuloPedidos.TPedido;
import onMarket.negocio.ModuloProductos.TProducto;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.presentacion.admin.IGUI;

@SuppressWarnings("serial")
public class GUICesta extends JPanel implements IGUI{
	private JFrame parentWindow; //Se usa cuando necesitamos un nuevo Jdialog
	private ControladorPedidos control;
    private BOCesta cesta;
    private JPanel panelDer;
    private TUsuario usuario;	
	
    private JPanel panelIzq;
    private JButton botonAgregar;
    private JScrollPane scrollPane;
    private DialogoPago dialogo =null;
    
	public GUICesta(JFrame miFrame,ControladorPedidos cont,TUsuario user) 
	{
		
		setSize(new Dimension(650, 400));
		setPreferredSize(new Dimension(650, 400));
		setFont(new Font("Tahoma", Font.PLAIN, 10));
		this.parentWindow=miFrame;
		this.control=cont;
		control.setGui(this);
		this.usuario = user;
		cesta=new BOCesta();
		initComponents();
    }

    
   
    private void initComponents() {
        panelDer = new JPanel();
        setSize(new Dimension(650, 400));
        
        
        	
	        botonAgregar = new JButton("Realizar Pedido");
	        botonAgregar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		if(cesta.getLineasSize()>0){
	        			Double importe =0.0;
	        			for(int i=0;i<cesta.getLineasSize();i++){
	        				importe=importe+cesta.getLineaIesima(i).getPrecio_linea();
	        			}
	        			
	        			TPedido pedido= new TPedido(usuario.getId(),EstadoPedido.encurso,
	        										"",null,0,0,usuario.getTelefono().toString(),
	        										importe,true);
	        			cesta.setPedido(pedido);
	        			dialogo= new DialogoPago(parentWindow,true, control,
	        												 cesta.getPedido());
	        			dialogo.setVisible(true);
	        		}
	        		else{
	        			String mensaje = "Debes Agregar al Menos Un Producto";
	        			JOptionPane.showMessageDialog(parentWindow,mensaje,
	        									  "Pedido Vacío",
	        									  JOptionPane.ERROR_MESSAGE);
	        		 }
	        	}	
	        });
	
	        
	        GroupLayout gl_panelDer = new GroupLayout(panelDer);
	        gl_panelDer.setHorizontalGroup(
	        	gl_panelDer.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(gl_panelDer.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(botonAgregar, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
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
        
        

        
        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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
        
        
    }



	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
			case EventoPedidos.RES_PAGO_OK: {
                control.accion(EventoPedidos.CONFIRMA_PEDIDO, cesta);
				break;
			}

			case EventoPedidos.RES_PAGO_KO: {
				String mensaje =  "Has Introducido un dato Nó Válido";
    			JOptionPane.showMessageDialog(parentWindow,mensaje,
    									  "Error de Pago",
    									  JOptionPane.ERROR_MESSAGE);
				break;
			}

			
			case EventoPedidos.RES_CONFIRMA_OK: {
				String mensaje =  "Tu Pedido se Ha Realizado Correctamente. " + System.getProperty("line.separator")+
								  "Lo recibirás en la hora Y fecha Indicadas";
    			JOptionPane.showMessageDialog(parentWindow,mensaje,
    									  "PEDIDO CORRECTO !!",
    									  JOptionPane.INFORMATION_MESSAGE);
    			this.dialogo.setVisible(false);
    			for(int i=cesta.getLineasSize()-1;i>=0;i--){
    				this.panelIzq.remove(i);
    				this.panelIzq.updateUI();
    			}
    			cesta = new BOCesta();
				break;
			}

			
			case EventoPedidos.RES_CONFIRMA_KO: {
				String mensaje =  "No hemos podido realizar el Pedido"+
								  "El importe te será Devuelto en Breve";
				JOptionPane.showMessageDialog(parentWindow,mensaje,
								  "ERROR INTERNO",
								  JOptionPane.INFORMATION_MESSAGE);	
				this.dialogo.setVisible(false);
				break;
			}
			
			case EventoPedidos.RES_STOCK_KO: {
				String mensaje =  "No hay Existencias suficientes para Realizar tu pedido";
				JOptionPane.showMessageDialog(parentWindow,mensaje,
						  "Agotado !!",
						  JOptionPane.INFORMATION_MESSAGE);
				break;
			}


		}
		
	}



	public void addProducto(TProducto p) {
	  boolean repetido=this.repetido(p.getId());
	  if(!repetido){
			TLineaPedido linea = new TLineaPedido(0, p.getId(), 1, p.getPrecio());
			ComponenteFilaCesta fila = new ComponenteFilaCesta(linea, cesta.getLineasSize(), p, this.control, this);
			cesta.agregarLineaAt(cesta.getLineasSize(), linea);
			this.panelIzq.add(fila, cesta.getLineasSize());
			this.updateUI();
	  }
	  else{
		  String mensaje = "Este Producto Yá se encuentra en Su Cesta";
		  JOptionPane.showMessageDialog(this.parentWindow,mensaje,
								  "Producto Repetido",
								  JOptionPane.ERROR_MESSAGE);
	  }
	  
	}


	public void elimina(ComponenteFilaCesta aux, int posInCesta) {
		this.panelIzq.remove(posInCesta);
		this.cesta.borrarLineaIesima(posInCesta);
		this.panelIzq.updateUI();
		
	}                        

	private boolean repetido(int id) {
		boolean flag =false;
		for(int i=0; i<cesta.getLineasSize();i++){
			if (cesta.getLineaIesima(i).getId_producto()==id)
				flag=true;
		}
		return flag;
	}


    
	
}
