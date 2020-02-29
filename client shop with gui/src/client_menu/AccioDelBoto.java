package client_menu;

import java.awt.event.*;

import javax.swing.JButton;

import product.ProductList;

public class AccioDelBoto implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		ProductList p = new ProductList(35);
		if(b != null) 
		{
			new Frame_CercaProducte(p);
		}
	}

	


}
