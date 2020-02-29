package client_menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


import order.Order;
import order.OrderList;


public class MenuPrincipal extends JFrame implements ActionListener{		//implementa Action para que escuche los eventos sobre los botones
	
	
	private static final long serialVersionUID = 1L;
	JButton boton1=new JButton("Realitzar Comanda");
	JButton boton2=new JButton("Veure Comandes");
	OrderList llOrder;
	Order o;
	String dniClient;
	
	public MenuPrincipal(OrderList orderL, Order o2,String dni) {
	
		
		dniClient=dni;
		llOrder=orderL;
		o=o2.copy();
	setTitle("MENU PRINCIPAL");
	setBounds(600,350,200,200); //POSICION, TAMAÑO
	
	JLabel etiqueta=new JLabel ("Selecciona opció:");
	Box cajaV= Box.createVerticalBox();
	cajaV.add(etiqueta);
	add(cajaV,BorderLayout.WEST);
	
	cajaV.add(boton1);
	
	cajaV.add(Box.createVerticalStrut(10));
	cajaV.add(boton2);
	add(cajaV,BorderLayout.CENTER);
	
	boton1.addActionListener(this);		//Todas las acciones sobre este boton serán escuchadas
	boton2.addActionListener(this);	
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e){ //e:evento que escucha
	
		if(e.getSource()==boton1) {			//getSource: devuelve un Objeto( en este caso es de tipo boton)
			System.out.println("Order added? "+llOrder.addOrder(o));
			
			Order []axu=llOrder.getOrderList();
			for(int x=0; x<llOrder.getNumO();x++) {
				if(axu[x]!=null) {
				System.out.println("XXXX: "+axu[x]);
				}
			}
		
			}
		
		
		if(e.getSource()==boton2) {
			
			Order []axu=llOrder.getOrderList();
			for(int x=0; x<llOrder.getNumO();x++) {
				if(axu[x]!=null) {
				System.out.println("Order's: "+axu[x]);
				}
			}
		
				
				
			
		}
		}
	}

