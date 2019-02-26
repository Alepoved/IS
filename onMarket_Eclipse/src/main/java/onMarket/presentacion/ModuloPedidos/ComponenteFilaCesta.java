package onMarket.presentacion.ModuloPedidos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import onMarket.negocio.ModuloPedidos.TLineaPedido;
import onMarket.negocio.ModuloProductos.TProducto;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

@SuppressWarnings("serial")
public class ComponenteFilaCesta extends JPanel {
	private GUICesta parentComponent; 
	private TLineaPedido tLinea;
    private int posInCesta;
    private TProducto producto;
    private Integer stockOrigin;
    private ArrayList<Integer> cantidad;
    
    private JComboBox<Integer> comboStock;
    private JSeparator separator;
    private JButton botonQuitar;
    private JLabel labelNombre;
    private JLabel labelMarca;
    private JLabel labelPrecio;
    private JLabel labelEuro;
    private JSeparator separator_1;

    
    public ComponenteFilaCesta(TLineaPedido lin,int pos,TProducto prod,ControladorPedidos cont,GUICesta miComponente )  {
    	setMaximumSize(new Dimension(300, 100));
    	setBorder(UIManager.getBorder("ComboBox.border"));
    	this.parentComponent=miComponente;
    	this.tLinea=lin;
    	this.posInCesta=pos;
    	this.producto=new TProducto(prod.getId(),prod.getNombre(),prod.getEstado(),
    								prod.getSubcategoria(),prod.getMarca(),prod.getPrecio(),
    								prod.getStock(),prod.getPesoVol(),prod.getFoto());
    	this.stockOrigin=producto.getStock();
        initComponents();
    }

    private void initComponents() {
    	cantidad= new ArrayList<Integer>();
        for(int i=0; i<this.stockOrigin-(this.stockOrigin/8);i++){
        	this.cantidad.add(i,i+1);
        }
    	
        labelNombre = new JLabel();
        labelNombre.setText(this.producto.getNombre());
        
        labelMarca = new JLabel();
        labelMarca.setText(this.producto.getMarca());
        
        labelPrecio = new JLabel();
        labelPrecio.setText(this.producto.getPrecio().toString());
        
        comboStock = new JComboBox<Integer>();
        comboStock.setMaximumSize(new Dimension(100, 50));
        comboStock.setName("comboStock");
        comboStock.setModel(new DefaultComboBoxModel<Integer>());
        for(int cant=0;cant<this.cantidad.size();cant++){
        	comboStock.addItem(this.cantidad.get(cant));
        }
        comboStock.setSelectedIndex(0);
        comboStock.setMaximumRowCount(10);
        comboStock.setAutoscrolls(true);
        
        labelEuro = new JLabel("€");
        
        botonQuitar = new JButton("Descartar");
        botonQuitar.setFont(new Font("Tahoma", Font.BOLD, 11));
        botonQuitar.setForeground(Color.WHITE);
        ComponenteFilaCesta aux=this;
        botonQuitar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		parentComponent.elimina(aux,posInCesta);
        		
        	}	
        });
        botonQuitar.setBackground(new Color(204, 51, 51));
        
        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(Color.BLACK);
	        
	        separator_1 = new JSeparator();
	 
        	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(labelNombre, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(separator, GroupLayout.DEFAULT_SIZE, 5, Short.MAX_VALUE)
	        			.addGap(18)
	        			.addComponent(labelMarca, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(comboStock, 0, 62, Short.MAX_VALUE)
	        			.addGap(18)
	        			.addComponent(labelPrecio, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(labelEuro, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
	        			.addGap(18)
	        			.addComponent(botonQuitar, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
	        			.addContainerGap())
	        		.addComponent(separator_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
	        				.addComponent(comboStock, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	        				.addGroup(layout.createParallelGroup(Alignment.BASELINE, false)
	        					.addComponent(labelEuro, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	        					.addComponent(botonQuitar))
	        				.addComponent(labelPrecio, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
	        			.addGap(157))
	        );
	        this.setLayout(layout);
	        comboStock.addActionListener(new ActionListener() {
	        	   @Override
	        	   public void actionPerformed(ActionEvent e) {
	        	      Integer selected=(Integer)comboStock.getSelectedItem();
	        	      producto.setStock(stockOrigin-selected);
	        	      tLinea.setCantidad(selected);
	        	      Double nuevoPrecio=selected*producto.getPrecio();
	        	      tLinea.setPrecio_linea(nuevoPrecio);
	        	      labelPrecio.setText(nuevoPrecio.toString());
	        	      aux.updateUI();
	        	   }
	        	});
    }                        

       
    @Override
    protected void paintComponent(Graphics g){
     super.paintComponent(g);
     
    }
}
