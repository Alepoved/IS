package onMarket.presentacion.GeneralUtils;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DialogoError extends JDialog{
	private JButton botonAceptar;
    private JLabel labelError;
    private JLabel labelMsj;
    private JPanel panel;
    private String mensaje = "Se ha Producido un Error";
    private Boolean accept = false;

	public DialogoError(java.awt.Frame parent, boolean modal, String msj) {
        super(parent, modal);
        setSize(new Dimension(400, 200));
        this.mensaje=msj;
        setMinimumSize(new Dimension(400, 200));
        initComponents();
        
    }

    
    private void initComponents() {

        panel = new JPanel();
        labelError = new JLabel();
        labelError.setForeground(new Color(220, 20, 60));
        labelError.setHorizontalAlignment(SwingConstants.CENTER);
        labelError.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelMsj = new JLabel();
        labelMsj.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelMsj.setHorizontalAlignment(SwingConstants.CENTER);
        botonAceptar = new JButton();
        botonAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setAccept(true);
        		setVisible(false);
        	}
        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        labelError.setText("¡ ERROR !");

        labelMsj.setText(this.mensaje);

        botonAceptar.setText("Aceptar");

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(160)
        					.addComponent(botonAceptar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(150)
        					.addComponent(labelError, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(25)
        					.addComponent(labelMsj, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
        			.addGap(10))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(30)
        			.addComponent(labelError, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        			.addGap(20)
        			.addComponent(labelMsj, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(botonAceptar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(15, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(panel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(panel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);

        pack();
    }
    
    public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}
    
    
    
    
}
