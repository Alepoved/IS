package onMarket.presentacion.ModuloPedidos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import onMarket.negocio.ModuloPedidos.TPedido;
import java.awt.Font;

@SuppressWarnings("serial")
public class ComponenteFilaPedido extends JPanel{
	private ControladorPedidos control;
    private JSeparator separator;
    private JLabel labelId;
    private JLabel labelEstado;
    private JLabel labelPrecio;
    private JLabel labelFecha;
    private JLabel labelDir;
    private JLabel labelHorario;
    private JButton btnNewButton;
    private TPedido ped; 

    public ComponenteFilaPedido(ControladorPedidos cont, GUImisPedidos misPedidos, TPedido pedido)  {
    	setMaximumSize(new Dimension(300, 100));
    	setBorder(UIManager.getBorder("TextField.border"));
    	this.control=cont;
    	this.ped = pedido; 
        initComponents();
    }

    private void initComponents() {    	
        labelId = new JLabel();
        labelId.setText("Id: " + this.ped.getId_pedido());
        
        labelEstado = new JLabel();
        labelEstado.setText("Estado: " + ped.getEstado()); 
        
        labelPrecio = new JLabel();
        labelPrecio.setText("Importe: " + ped.getTotal_pagar() + " €");
        
        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(Color.BLACK);
	        
	        labelFecha = new JLabel("Fecha Entrega: " + ped.getFecha_entrega());
	        
	        labelDir = new JLabel("Dir. de Entrega: " +  ped.getDir_entrega());
	        
	        labelHorario = new JLabel("Horario de Entrega: " + ped.getHorario_ini() + ":00 - " + ped.getHorario_fin() + ":00 " );
	        
	        btnNewButton = new JButton("Ver Detalles !");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		control.accion(EventoPedidos.VER_DETALLES, ped);
	        	}
	        });
	        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnNewButton.setForeground(Color.WHITE);
	        btnNewButton.setBackground(new Color(0, 153, 51));
	 
        	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(layout.createSequentialGroup()
	        					.addComponent(labelId, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
	        					.addGap(10)
	        					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(labelEstado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        					.addGap(18))
	        				.addGroup(layout.createSequentialGroup()
	        					.addComponent(labelDir, GroupLayout.PREFERRED_SIZE, 139, Short.MAX_VALUE)
	        					.addGap(23)))
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(layout.createSequentialGroup()
	        					.addComponent(labelFecha, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
	        					.addGap(18)
	        					.addComponent(labelPrecio, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
	        					.addGap(50))
	        				.addGroup(layout.createSequentialGroup()
	        					.addComponent(labelHorario, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addComponent(btnNewButton)))
	        			.addGap(29))
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
	        				.addGroup(layout.createParallelGroup(Alignment.BASELINE, false)
	        					.addComponent(labelPrecio, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	        					.addComponent(labelFecha, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
	        					.addComponent(labelEstado, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
	        				.addComponent(labelId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        				.addGroup(layout.createSequentialGroup()
	        					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	        					.addGap(5)))
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(labelDir, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(labelHorario, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(btnNewButton))
	        			.addGap(21))
	        );
	        this.setLayout(layout);
	        this.setVisible(true); 
    }                        

       
    @Override
    protected void paintComponent(Graphics g){
     super.paintComponent(g);
     
    }

    
}
