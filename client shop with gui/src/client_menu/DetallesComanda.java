package client_menu;

import java.awt.BorderLayout;


import javax.swing.*;
import order.Order;

import product.Product;




public class DetallesComanda extends JFrame {		//implementa Action para que escuche los eventos sobre los botones
		
		
		private static final long serialVersionUID = 1L;
		Order o;
		
		
		public DetallesComanda( Order o1) {

			o=o1.copy();
			Product []aux=o.getListP();
			
		setTitle("Mis Pedidos");
		setBounds(600,350,200,200); //POSICION, TAMAÑO
		
		JLabel etiqueta=new JLabel ("Detalles de la comanda:");
		Box cajaH=Box.createHorizontalBox();
		Box cajaV= Box.createVerticalBox();
		cajaV.add(Box.createVerticalStrut(10));
		cajaV.add(etiqueta);
		cajaV.add(cajaH);


		int i=0;
		Box cajaV2 = Box.createVerticalBox();
	
		while(i<aux.length&& aux[i]!=null) {
			JLabel item=new JLabel ("PORDUCT:"+(aux[i].getName()));
			cajaV2.add(item);
			i++;			
		}
		
		int i2=0;
		Box cajaV3 = Box.createVerticalBox();
	
		while(i2<aux.length&& aux[i2]!=null) {
			JLabel item=new JLabel ("PRICE:"+(aux[i2].getPrice()));
			cajaV3.add(item);
			i2++;			
		}
		
		cajaH.add(cajaV2);
		cajaH.add(Box.createHorizontalStrut(50));
		cajaH.add(cajaV3);
		add(cajaV,BorderLayout.WEST);

		JLabel priceT=new JLabel ("Price Total:"+o.getPriceTotal());
		cajaV.add(priceT);
		JLabel estate=new JLabel ("Estado: "+o.getEst());
		cajaV.add(estate);

		add(cajaV,BorderLayout.WEST);


		}

		
		

	
		

}
