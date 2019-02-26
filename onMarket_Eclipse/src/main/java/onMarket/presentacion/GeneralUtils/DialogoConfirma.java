package onMarket.presentacion.GeneralUtils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;

@SuppressWarnings("serial")
public class DialogoConfirma extends JDialog {
	private JButton botonCancelar;
    private JButton botonAceptar;
    private JLabel labelInstruction;
    private JPanel jPanel1;
    private Boolean accept = false;
    
    
    public DialogoConfirma(Frame parent, boolean modal) {
        super(parent, modal);
        setResizable(false);
        setSize(new Dimension(339, 242));
        getContentPane().setSize(new Dimension(300, 200));
        getContentPane().setPreferredSize(new Dimension(300, 200));
        setTitle("Confirmación");
        initComponents();
        
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        labelInstruction = new JLabel();
        labelInstruction.setHorizontalAlignment(SwingConstants.CENTER);
        labelInstruction.setText("Seguro que desea Eliminar El Elemento ?");
        botonCancelar = new JButton();
        botonAceptar = new JButton();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        getContentPane().setSize(new Dimension(350, 250));
       
        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }

			
        });

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }

			
        });
        
        JLabel lblatencin = new JLabel("¡ATENCIÓN!");
        lblatencin.setForeground(new Color(218, 165, 32));
        lblatencin.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblatencin.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(40)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(botonAceptar)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(botonCancelar))
        						.addComponent(labelInstruction, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
        					.addGap(40))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(lblatencin, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        					.addGap(100))))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblatencin, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(labelInstruction, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
        			.addGap(48)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(botonCancelar)
        				.addComponent(botonAceptar))
        			.addGap(84))
        );
        jPanel1.setLayout(jPanel1Layout);

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(52, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        //setVisible(true);
        pack();
    }                        

	
	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

    private void botonCancelarActionPerformed(ActionEvent evt) {
			this.setAccept(false);
			this.setVisible(false);		
			}
    
    private void botonAceptarActionPerformed(ActionEvent evt) {
    	this.setAccept(true);
		this.setVisible(false);	
	}
}
