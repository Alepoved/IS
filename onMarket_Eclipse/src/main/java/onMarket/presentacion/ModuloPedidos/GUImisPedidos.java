package onMarket.presentacion.ModuloPedidos;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFrame;
import onMarket.negocio.ModuloPedidos.EstadoPedido;
import onMarket.negocio.ModuloPedidos.TLineaPedido;
import onMarket.negocio.ModuloPedidos.TPedido;
import onMarket.negocio.ModuloUsuarios.TUsuario;
import onMarket.presentacion.GeneralUtils.DialogoError;
import onMarket.presentacion.admin.AfterLoginGUI;
import onMarket.presentacion.admin.IGUI;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GUImisPedidos extends JPanel implements IGUI {

	private ArrayList<TPedido> filas;
	private ControladorPedidos ctrl;
	private JFrame miFrame;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private TUsuario user;
	private DialogoDetallesPed dialogo;
	

	public GUImisPedidos(JFrame miFrame, AfterLoginGUI miDialog, int indexTab, ControladorPedidos control,
			TUsuario datosUser) {
		control.setGui(this);
		setPreferredSize(new Dimension(650, 400));
		setMinimumSize(new Dimension(650, 400));
		setMaximumSize(new Dimension(650, 400));

		this.filas = new ArrayList<>();
		this.ctrl = control;
		this.miFrame = miFrame;
		this.user = datosUser;

		scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(0, 0, 0));
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 13));

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE).addGap(18)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addGap(16)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE).addContainerGap()));

		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.accion(EventoPedidos.MOSTRAR_PEDIDOS, datosUser);
			}
		});

		JTextArea txtrSiHasRealizado = new JTextArea();
		txtrSiHasRealizado.setFont(new Font("Arial", Font.PLAIN, 16));
		txtrSiHasRealizado.setSelectedTextColor(new Color(255, 0, 0));
		txtrSiHasRealizado.setDisabledTextColor(new Color(220, 20, 60));
		txtrSiHasRealizado.setBackground(SystemColor.scrollbar);
		txtrSiHasRealizado.setEnabled(false);
		txtrSiHasRealizado.setText("Si has realizado un \r\npedido recientemente \ny no aparece, \n¡Puede ACTUALIZAR!");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtrSiHasRealizado, GroupLayout.PREFERRED_SIZE, 175,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup().addGap(54)
								.addComponent(btnNewButton).addContainerGap(56, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addGap(35)
						.addComponent(txtrSiHasRealizado, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton)
						.addContainerGap(203, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

		control.accion(EventoPedidos.MOSTRAR_PEDIDOS, datosUser);
	}

	@Override
	public void actualizar(int evento, Object datos) {

		switch (evento) {
		case EventoPedidos.RES_MOSTRAR_PEDIDOS_KO: {
			String msj = "Error Interno - No se ha podido Obtener los datos";
			DialogoError dialogoE = new DialogoError(miFrame, true, msj);
			dialogoE.setVisible(true);
			if (dialogoE.getAccept()) {
			}
			System.exit(0);
			break;

		}
		case EventoPedidos.RES_MOSTRAR_PEDIDOS_OK: {
			@SuppressWarnings("unchecked")
			ArrayList<TPedido> castDatos = (ArrayList<TPedido>) datos;

			if (panel_1.getComponentCount() > 0) {
				for (int i = panel_1.getComponentCount() - 1; i >= 0; i--) {
					filas.remove(i);
					panel_1.remove(i);

				}
				panel_1.updateUI();
			}
			for (int i = 0; i < castDatos.size(); i++) {
				TPedido aux = castDatos.get(i);
				ComponenteFilaPedido componente = new ComponenteFilaPedido(this.ctrl, this, aux);
				filas.add(i, aux);
				panel_1.add(componente, i);
				panel_1.updateUI();
			}
			break;
		}
		case EventoPedidos.RES_DETALLES_KO: {
			String msj = "Error Interno - No se ha podido Obtener los datos";
			DialogoError dialogoE = new DialogoError(miFrame, true, msj);
			dialogoE.setVisible(true);
			if (dialogoE.getAccept()) {
			}
			System.exit(0);
			break;
		}
		case EventoPedidos.RES_DETALLES_OK:
		{
			this.dialogo = new DialogoDetallesPed(this, true, this.ctrl);
			this.dialogo.setTitle("Detalles del pedido");
			@SuppressWarnings("unchecked")
			ArrayList<TLineaPedido> castDatos = (ArrayList<TLineaPedido>) datos;
			ArrayList<Integer> listaid = new ArrayList<Integer>();
			for (int i = 0; i < castDatos.size(); i++){
				listaid.add(castDatos.get(i).getId_linea());
			}
			Integer idPedido = castDatos.get(0).getId_pedido();
			for (int i = 0; i < castDatos.size(); i++){
				TLineaPedido aux = castDatos.get(i);
				ComponenteFilaDetalles fila = new ComponenteFilaDetalles("", aux);
				dialogo.getPanel().add(fila, i);
			}
			ctrl.accion(EventoPedidos.VER_PRODUCTOS, new TPedido(idPedido, user.getId(), EstadoPedido.encurso, "", null, 0, 0, "999999999", null, true, listaid));
			dialogo.setVisible(true);
			break;
		}
		case EventoPedidos.RES_PRODUCTOS_KO: {
			String msj = "Error Interno - No se ha podido Obtener los datos";
			DialogoError dialogoE = new DialogoError(miFrame, true, msj);
			dialogoE.setVisible(true);
			if (dialogoE.getAccept()) {
			}
			break;
		}
		case EventoPedidos.RES_PRODUCTOS_OK:
		{
			@SuppressWarnings("unchecked")
			ArrayList<String> castDatos = (ArrayList<String>) datos;
			for(int i = 0; i < castDatos.size(); i++){
				String aux = castDatos.get(i);
				ComponenteFilaDetalles aux2 = (ComponenteFilaDetalles) dialogo.getPanel().getComponent(i);
				aux2.setNombreYMarca(aux);
			} break;
		}
		
		}
	}
}
