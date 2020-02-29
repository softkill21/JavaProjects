package client_menu;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import client_menu.Frame_CercaProducte;

import order.Order;

import product.Configuration;
import product.HardwareProduct;

import product.Product;
import product.ProductList;
import product.SoftwareProduct;


public class Frame_CercaProducte extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductList lista;
	Order []orderL;
	private JFrame frame, frame2,frame3;
	private JTextField nomProducte;
	private JLabel nomP, tipusP, filtrar, preuP;
	String tipusP2;
	private JCheckBox soft, hard, conf, cproduct;
	private JRadioButton tots, stock;
	private JButton cercar,cercar2,componentes; 
	ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
	  ArrayList<Double> values = new ArrayList<>();
	public double preciototal = 0;
	public int contadorId=9999;
	Product p[] = null;
	

	public Frame_CercaProducte(ProductList listaP) {

	
		lista = listaP;
		frame = new JFrame("Cerca producte");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		JPanel controls = new JPanel(new GridLayout(10, 2));
		nomP = new JLabel("Nom producte:");
		nomProducte = new JTextField(20);
		tipusP = new JLabel("Tipus producte");
		soft = new JCheckBox("Software");
		hard = new JCheckBox("Hardware");
		conf = new JCheckBox("Configuration");
		filtrar = new JLabel("Filtrar");
		tots = new JRadioButton("Veure tots");
		stock = new JRadioButton("Solo con stock");
		cercar = new JButton("Cercar");
		cercar.addActionListener(this);
		controls.add(nomP);
		controls.add(nomProducte);
		controls.add(tipusP);
		controls.add(soft);
		controls.add(hard);
		controls.add(conf);
		controls.add(filtrar);
		controls.add(tots);
		controls.add(stock);
		controls.add(cercar);

		Container c = frame.getContentPane();
		c.add(controls, BorderLayout.NORTH);

		frame.pack();
		frame.setVisible(true);

	}
	
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String msg = "";
		msg = nomProducte.getText();
		

	
		if (soft.isSelected() && (!hard.isSelected() && (!conf.isSelected())) && (tots.isSelected()|| stock.isSelected())) {
			p = lista.productListSoft(msg);
		}
		
		if (soft.isSelected() && (hard.isSelected() && (!conf.isSelected())) && (tots.isSelected()|| stock.isSelected())) {
			p = lista.productListSoftHard(msg);
		}
		
		if (soft.isSelected() && (!hard.isSelected() && (conf.isSelected())) && (tots.isSelected()|| stock.isSelected())) {
			p = lista.productListSoftConf(msg);
		}
		
		if (!soft.isSelected() && (hard.isSelected() && (!conf.isSelected())) && (tots.isSelected()|| stock.isSelected())) {
			p = lista.productListHard(msg);
		}
		if (!soft.isSelected() && (hard.isSelected() && (conf.isSelected())) && (tots.isSelected()|| stock.isSelected())) {
			p = lista.productListHardConf(msg);
		}
		
		if (soft.isSelected() && (hard.isSelected() && (conf.isSelected())) && (tots.isSelected()|| stock.isSelected())) {
			p = lista.productListSoftHardConf(msg);
		}
		
		if (!soft.isSelected() && (!hard.isSelected() && (conf.isSelected())) && (tots.isSelected()|| stock.isSelected())) {
			p = lista.productListConf(msg);
		}
		
		
				
	

		// msg+="-----------------\n";
		// for(int j=0;j<p.length;j++)
		//JOptionPane.showMessageDialog(this, "\n" + p);
		int input = JOptionPane.showOptionDialog(null, "Quiere seguir adelante?", "The title",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (input == JOptionPane.OK_OPTION) {

			
			frame.setVisible(false);
			frame2 = new JFrame("Listar producte");
			frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame2.pack();
			frame2.setVisible(true);

			JPanel controls2 = new JPanel(new GridLayout(p.length, p.length));

			for (int j = 0; j < p.length; j++) {
				if (p[j] != null) {
					if (p[j] instanceof HardwareProduct) {
						tipusP2 = ("Hardware");
					}
					if (p[j] instanceof SoftwareProduct) {
						tipusP2 = ("Software");
					}
					if (p[j] instanceof Configuration) {
						tipusP2 = ("Configuration");
						componentes = new JButton("componentes(PARA VER COMPONENTES DE UN CONFIGURATION SOLO)");
						controls2.add(componentes);
						
						
					}
					
					cproduct = new JCheckBox("Nom:" + p[j].getName() + "       Preu:" + p[j].getPrice()
							+ "       Stock:" + p[j].getStock() + "   Tipus:" + tipusP2);
					
					checkboxes.add(cproduct);
					values.add((double) p[j].getPrice());
					
					controls2.add(cproduct);
					
					
				}
			}
			
			
			cercar2 = new JButton("Realizar comanda");
			controls2.add(cercar2);


			Container c2 = frame2.getContentPane();
			c2.add(controls2, BorderLayout.NORTH);
			

			frame2.pack();
			frame2.setVisible(true);
		}
		
		if(componentes.isDisplayable())
			componentes.addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	JPanel control3=new JPanel();
	        	
	        	for (int i=0;i<checkboxes.size();i++){
					if (checkboxes.get(i).isSelected()) {
						preuP = new JLabel();
						preuP.setText("<html><p style=\"width:1000px\"><br>"+p[i].toString()+"<br></p></html>");
						
						control3.add(preuP);
					}
					
				}
	        	
	       
				frame3 = new JFrame("Componentes");
				frame3.pack();
				frame3.setVisible(true);
				
				
				
				Container c3 = frame3.getContentPane();
				c3.add(control3, BorderLayout.NORTH);

				frame3.pack();
				frame3.setVisible(true);
	        }
	    });
	

		
		cercar2.addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        
	        	
	        
	     
	           	for (int i=0;i<checkboxes.size();i++){
					if (checkboxes.get(i).isSelected()) {
							preciototal += values.get(i);
					}
					
				}
	        	
	        //	orderL[contadorId].setIdInt(contadorId);
	        //	orderL[contadorId].setPriceTotal(preciototal);
	        	//OrderList.storeData(orderL);
	        //	contadorId++;
	        	
	        	
	        	frame2.setVisible(false);
				frame3 = new JFrame("Compra realizada");
				frame3.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame3.pack();
				frame3.setVisible(true);
				
				JPanel control3=new JPanel();
				preuP = new JLabel("Precio total:"+preciototal);
				control3.add(preuP);
				Container c3 = frame3.getContentPane();
				c3.add(control3, BorderLayout.NORTH);

				frame3.pack();
				frame3.setVisible(true);
	        }
	    });
			
		
	}
	

}