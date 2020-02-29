package client_menu;
import java.awt.*;

import javax.swing.*;


import order.OrderList;

import product.ProductList;

public class Comanda extends JFrame {
	private JLabel text,com;
	private JButton ver;
	private static final long serialVersionUID = 1L;
	public Comanda(String titol,OrderList llistO, ProductList l) 
	{
		super(titol);
		int i=0;
		setTitle("Mis Pedidos");
		setBounds(600,350,200,200);
		text = new JLabel("Mis Pedidos");
		Box caixa = Box.createVerticalBox();
		Box cbotons = Box.createVerticalBox();
		caixa.add(text);
		add(caixa,BorderLayout.WEST);
		add(cbotons,BorderLayout.CENTER);
		AccioDelBoto accioboto = new AccioDelBoto();
		while(i<llistO.getNumO()) 
		{
			com = new JLabel(llistO.getOrderList()[i].getId());
			caixa.add(com);
			ver = new JButton("Ver");
			cbotons.add(ver);
			ver.addActionListener(accioboto);
			i++;
		}
		setVisible(true);
	}
	

}
