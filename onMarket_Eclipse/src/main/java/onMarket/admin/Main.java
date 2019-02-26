package onMarket.admin;

import java.awt.EventQueue;

import onMarket.presentacion.admin.PrincipalGUI;


public class Main {

	public static void main(String[] args) {
		// Inicializar presentacion    	
	    final PrincipalGUI frame = new PrincipalGUI();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
			
		
		
	}

}
