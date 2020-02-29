package tests;

import java.io.File;
import java.util.Scanner;

import clients.LlistaClients;
import clients.Client;
import order.Date;
import order.Order;
import order.Order.Estate;
import order.OrderList;
import product.ProductList;
import product.SoftwareProduct;
import product.SoftwareProduct.SistemasOperatius;
import product.Configuration;
import product.HardwareProduct;
import product.HardwareProduct.HardwareType;
import product.Product;

public class TestOrders {

	static Scanner teclat = new Scanner (System.in);
	
	private static HardwareType HDD;
	private static SistemasOperatius Linux;
	private static SistemasOperatius Mac;
	

	public static void main(String[] args) throws Exception {
		
		
		//RECUPERACION LISTADO CLIENTES
		File clients=new File("C:/Users/maygd/Desktop/ULTIMA_VERSION/src/Clients.txt");
		LlistaClients llistaCl= new LlistaClients (100);
		llistaCl.ReadFile(clients);
		
		Client []mostrarClientes=llistaCl.Mostrartots();
		
		for(int i=0; i<llistaCl.getNumClients() && llistaCl!=null;i++) {
			System.out.println(mostrarClientes[i]);
		}
		
		//RECUPERACION LISTADO PRODUCTOS
		Configuration c1 = new Configuration(0,"c1", 22);
		Configuration c2 = new Configuration(0,"c2", 10);

		ProductList llistaProd=new ProductList (100);
		ProductList llistaProd1=new ProductList (100);
		ProductList llistaProd2=new ProductList (100);

		Product[] list = llistaProd.readProductsFile("C:/Users/maygd/Desktop/ULTIMA_VERSION/src/products_fila_hardward.txt");

		for(int i=0; i<list.length && list[i] != null;i++) {
			HardwareProduct hardProduct = (HardwareProduct) list[i];
			llistaProd.addHardwareProduct(hardProduct);
			llistaProd1.addHardwareProduct(hardProduct);
			c1.addProduct(hardProduct);
			c2.addProduct(hardProduct);

		//	System.out.println(list[i]);
		}
		
		list=llistaProd.readProductsFile("C:/Users/maygd/Desktop/ULTIMA_VERSION/src/products_fila_software.txt");
		
		for(int i=0; i<list.length && list[i] != null;i++) {
			SoftwareProduct softProduct = (SoftwareProduct) list[i];
			llistaProd.addSoftwareProduct(softProduct);
			llistaProd1.addSoftwareProduct(softProduct);
			llistaProd2.addSoftwareProduct(softProduct);

		//	System.out.println(list[i]);
		}
		
	/*	list=llistaProd.readProductsFile("C:/Users/maygd/Desktop/ULTIMA_VERSION/src/products_fila_configurations.txt");
		
		for(int i=0; i<list.length && list[i] != null;i++) {
			Configuration configuration = (Configuration) list[i];
			llistaProd.addConfigurationProduct(configuration);
			
			System.out.println(list[i]);
		}
		*/
		
		//RELLENO DE LAS CONFIGURACIONES
		
		SoftwareProduct soft1 = new SoftwareProduct(980.0, "XB4", 20, Linux);
		SoftwareProduct soft2 = new SoftwareProduct(430.0, "Xs", 90, Mac);
		
		HardwareProduct h5 = new HardwareProduct(300, "SRG", 12, HDD);


		
		c1.addProduct(h5);
		c1.addProduct(soft1);
		c1.setPrice(c1.totalPriceProduct());

	
		c2.addProduct(h5);
		c2.addProduct(soft2);
		c2.setPrice(c2.totalPriceProduct());

		
	/*	Product []x =c1.getLlista();
		for(int i=0; i<x.length && x[i] != null;i++) {
			
			System.out.println(x[i]);
		}
		*/
		
		llistaProd.addConfigurationProduct(c1);
		llistaProd1.addConfigurationProduct(c1);

		llistaProd.addConfigurationProduct(c2);
		
	/*	Product [] c=llistaProd.getLlista();
		for(int i=0; i<llistaProd.getnElem();i++) {
			System.out.println("LISTA: "+c[i]);				//LISTA DE ELEMENTOS ANADIDOS AL LIST.PROD

		}*/
		
		
		

		
		Date d= new Date(31,12,2019);
		Date d2=new Date (12,12,2019);
		Date d3=new Date (29,12,2019);

		Order o1= new Order (llistaProd.getLlista(),Estate.SENT,mostrarClientes[0],d);
		Order o2= new Order (llistaProd1.getLlista(),Estate.CANCELED,mostrarClientes[1],d2);
		Order o3= new Order (llistaProd2.getLlista(),Estate.ORDERED,mostrarClientes[0],d3);
		
		

		OrderList orderL= new OrderList (20);
		
		orderL.addOrder(o1);
		orderL.addOrder(o2);
		orderL.addOrder(o3);
		
		Order[] orders=orderL.getOrderList();
		for(int i=0; i<orderL.getNumO();i++) {
			System.out.println("LISTA ORDENES: "+orders[i]+"\n");}
			
		
		// LISTADO DE COMANDAS DE UN DNI ESPECIFICO
		
		System.out.println("Introduce el DNI del cual desea ver comandas:");
		String dni=teclat.next();
		
				Order [] aux=orderL.ordersDni(dni);
				System.out.println("LISTA DE COMANDAS DE ID ESPECIFICO: ");
				for(int i=0; i<aux.length;i++) {
					if(aux[i]!=null) {
						System.out.println(aux[i]);
							}

					}
				
		//COMANDA ESPECIFICA ID ESPECIFICO
				
				System.out.println("Introduce el ID de la comanda:");
				String id=teclat.next();
				
			System.out.println("Existe la comanda: SI o NO?");
			if(orderL.orderId(id)==null) {
				System.out.println("NOT FOUND");
			}
			else {
				System.out.println("LA COMANDA ES: ");
					System.out.println(orderL.orderId(id));
					System.out.println("PRECIO TOTAL DE LA COMANDA: "+(orderL.orderId(id).getPriceTotal()));

			}

		
		
		//BORRAR COMANDA ID ESPECIFICO
			
			System.out.println("Introduce el ID de la comanda a borrar:");
			String idBorrar=teclat.next();
			
			if(orderL.deleteOrder(idBorrar)==false) {
				System.out.println("No se ha eliminado la comanda por que no existe o fue eliminada anteriormente.");
				
			}
			else {
				System.out.println("Se ha eliminado la comanda.");
				
			}
			orderL.deleteOrder(idBorrar);
			orderL.deleteOrder(idBorrar);
			//System.out.println(orderL.orderId(idBorrar));
			
			
			//GUARDAR (ESCRIBIR) EN UN ARCHIVO
					System.out.println("GUARDANDO ....");
					Order[] orderWrite = new Order [orderL.getNumO()];
					orderWrite=orderL.copyOrder();
					orderL.writeData(orderWrite);
					for(int x=0; x<orderWrite.length&&orderWrite[x]!=null;x++) {
						System.out.println("guardado: "+orderWrite[x]);
					}
					
					
			//RECUPERAR (LEER) EN UN ARCHIVO

					Order[] orderNew = new Order[orderL.getNumO()];
					OrderList  orderList=new OrderList (100);
					orderList.readData(orderNew);

					int y=0;
					while (y<orderNew.length &&orderNew!=null) {
						orderList.addOrderData(orderNew[y]);
						y++;
						
					}
					
					for(int x=0; x<orderNew.length&&orderNew[x]!=null;x++) {
						System.out.println("recuperado: "+orderNew[x]);
						
					}
					
					
					//COMPROBACION MAX
					System.out.println("The products with more commands have been: "+orderList.maxProducts());				

	
	}

					
				
		
	}


