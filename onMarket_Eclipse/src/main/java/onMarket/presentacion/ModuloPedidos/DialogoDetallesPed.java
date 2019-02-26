package onMarket.presentacion.ModuloPedidos;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class DialogoDetallesPed extends JDialog {
	private JScrollPane scrollpanel;
	private JPanel panel;

	public DialogoDetallesPed(GUImisPedidos parentComponent, boolean b, ControladorPedidos control) {
		setPreferredSize(new Dimension(800, 700));
		setMinimumSize(new Dimension(650, 600));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setModal(b);
		this.scrollpanel = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollpanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollpanel, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		this.panel = new JPanel();
		scrollpanel.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		getContentPane().setLayout(groupLayout);
		
	}
	public JPanel getPanel(){
		return this.panel;
	}
	
}