package onMarket.presentacion.ModuloPedidos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import onMarket.negocio.ModuloPedidos.TLineaPedido;


@SuppressWarnings("serial")
public class ComponenteFilaDetalles extends JPanel {
    private TLineaPedido tLinea;
    private JSeparator separator;
    private JLabel labelNombre;
    private JLabel labelCantidad;
    private JLabel labelPrecio;
    private JLabel labelEuro;
    private JLabel labelMarca;
    
    public ComponenteFilaDetalles(String nombreProducto, TLineaPedido aux)  {
    	setMaximumSize(new Dimension(300, 100));
    	setBorder(UIManager.getBorder("TextField.border"));
    	this.tLinea=aux;
        initComponents();
    }

    private void initComponents() {
            	
        labelNombre = new JLabel();
        labelNombre.setForeground(Color.DARK_GRAY);
        labelNombre.setText("Producto: ");
        
        labelCantidad = new JLabel();
        labelCantidad.setText("Cantidad: " + this.tLinea.getCantidad());
        
        labelPrecio = new JLabel();
        labelPrecio.setText("Precio " + this.tLinea.getPrecio_linea().toString());
        
        
        labelEuro = new JLabel("€");
        
        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(Color.BLACK);
	        
	    labelMarca = new JLabel("");
	    labelMarca.setForeground(Color.DARK_GRAY);
	    labelMarca.setText("Marca:");
        	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(labelNombre)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(separator, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(labelCantidad, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(labelPrecio)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(labelEuro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
	        				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	        					.addComponent(labelCantidad, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	        					.addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
	        				.addComponent(labelPrecio, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(labelEuro, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
	        			.addGap(23))
	        );
	        this.setLayout(layout);
	        this.setVisible(true);
    }         
    public void setNombreYMarca(String nombreYMarca){
    	String[] partes = nombreYMarca.split("-", 2);
    	String nombre = partes[0];
    	String marca = partes[1];
		this.labelNombre.setText(this.labelNombre.getText() + nombre);
		this.labelMarca.setText(this.labelMarca.getText() + marca);
	}

       
    @Override
    protected void paintComponent(Graphics g){
     super.paintComponent(g);
     
    }

}