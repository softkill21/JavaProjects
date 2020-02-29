package order;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import product.*;


public class OrderList implements Serializable{
	private static final long serialVersionUID = 1L;
	private Order[] orderList;
	private static int numO;
	protected static int countConf=0;
	protected static int countSoft=0;
	protected static int countHard=0;

	/**
	 * Constructor de la clase OrderList
	 * @param dim: Numero de comandas máximo que es posible agregar.
	 */
	public OrderList(int dim) {
		this.orderList=new Order [dim];
		numO=0;
	}

/**
 * Getter de la Lista de comandas 	
 * @return Lista de comandas
 */
	public Order[] getOrderList() {
		return orderList;
	}
	
	

/**
 * Setter de la Lista de comandas.
 * @param orderList: Lista de comandas por la cual se modifica la actual
 */
	public void setOrderList(Order[] orderList) {
		this.orderList = orderList;
	}


/**
 *Getter del numO (numero de comandas actual)
 * @return valor del numO
 */
	public int getNumO() {
		return numO;
	}

	

/**
 * Reescriptura para el método toString de la clase
 */
	public String toString() {
	String s = "\nOrders List: \n\n";
     	for (int i=0; i<numO; i++)
     		s+= orderList[i]+"\n";
     	return s;
     
	}


/**
 * Método que añade una comanda a la lista
 * @param o: comanda a añadir
 * @return Booleano para saber si se ha añadido la comanda o no
 */
	public boolean addOrder (Order o) {
	
		boolean added=false;
		try {
			if(numO>=orderList.length) {
				Order[] aux=new Order[numO*2];
				for(int i=0; i<numO;i++) {
					aux[i]=orderList[i];
				}
				orderList=aux;
				}
			
				orderList[numO]=o.copy();
				Product []listProduct=orderList[numO].getListP();
				int i=0;
		/*for(int x=0;x<listProduct.length&&listProduct[x]!=null;x++) {			//INFORMACION,DECREMENTO A MEDIDA QUE SE VAN HACIENDO COMANDAS DEL STOCK
			System.out.println("Stocks: "+listProduct[x].getStock());
		}*/
	
				while( i<listProduct.length&& listProduct[i]!=null){
					if(listProduct[i] instanceof Configuration) {
						countConf++;
				
						Product[] listConfiguration=((Configuration)listProduct[i]).getLlista();
			
						for(int x=0;x<listConfiguration.length&&listConfiguration[x]!=null;x++) {			//DECREMENTO DEL STOCK DEL LISTADO DE PRODUCTOS DE CONFIGURACION
				listConfiguration[x].setStock((listConfiguration[x].getStock())-1);
				if(listConfiguration[x]instanceof HardwareProduct) {
					countHard++;
				}
				else {
					countSoft++;
				}
						}
			
					}
					if(listProduct[i]instanceof HardwareProduct) {
						countHard++;
					}
					else {
						countSoft++;
					}
			
					listProduct[i].setStock((listProduct[i].getStock())-1);								//DECREMENTO DE LOS STOCKS DEL LISTADO DE PRODUCTOS DE LA COMANDA
					i++;
		}
		/*for(int x=0;x<listProduct.length&&listProduct[x]!=null;x++) {
			System.out.println("MODIFICADO:"+listProduct[x].getStock());
		}*/
		

				added=true;
		
				numO++;	
			
		}
		catch (java.lang.NullPointerException e) {
		
		}
	
	
		return(added);
	
	}



public static int getCountConf() {
	return countConf;
}

public static void setCountConf(int countConf) {
	OrderList.countConf = countConf;
}

public static int getCountSoft() {
	return countSoft;
}

public static void setCountSoft(int countSoft) {
	OrderList.countSoft = countSoft;
}

public static int getCountHard() {
	return countHard;
}

public static void setCountHard(int countHard) {
	OrderList.countHard = countHard;
}



/**Método que permite añadir una comanda a la lista
 * 
 * @param o: comanda a añadir
 */
	public void addOrderData(Order o){
		if (numO >= orderList.length) {
			Order[] auxiliar = new Order[numO * 2];
            for (int i = 0; i < numO; i++) {
                auxiliar[i] = orderList[i];
            }
            orderList = auxiliar;
		}
            orderList[numO]=o;
			numO++;

	}


	
/**
 * Método que busca una comanda por su id.
 * @param id: id de la comanda a buscar
 * @return Comanda a la cual pertenece dicho id o null (si no existe)
 */
	public Order orderId (String id) {
	
		Order aux=null; 	//Objecte null
		boolean encontrada=false;
		int i=0;
		try {
			while( i<numO &&!encontrada) {
	
				if(id.equals(orderList[i].getId())==true) {
					aux=orderList[i].copy();
					encontrada=true;
				}
				else {
					i++;
				}
			}
		}
	
		catch (java.lang.NullPointerException e) {
			System.out.println("No existe ninguna comanda");

		}
	



	return (aux);
	}

	
	
/**
 * Método que enseña las comandas de un cliente mediante su DNI
 * @param dni: documento de identidad del cliente
 * @return Listado de comandas que le pertenece
 */
	public Order[] ordersDni (String dni) {
	
		Order [] aux=new Order [numO]; 	//Objecte null
		int x=0;
		try {
			for(int i=0; i<numO;i++) {
		
				if(dni.equals(orderList[i].getClient().getDni())==true) {
			
					aux[x]=orderList[i].copy();
					x++;
				}
		
			}
		}
		catch(java.lang.NullPointerException e){
		System.out.println("No existe ninguna comanda");
		}
		
		return (aux);
	}


/**
 * Método que permite eliminar una comanda en concreto mediante su id.
 * @param id: id de la comanda
 * @return Booleano para saber si esta ha sido eliminada.
 */
	public boolean deleteOrder (String id) {
		int i=0,j;
		boolean delete=false;
		try {
			while(i<numO) {
	
				if(id.equals(orderList[i].getId())==true) {
					delete=true;	
				
					j = i;
						while (j < numO - 1) {
							orderList[j] = orderList[j+1];
							j++;
						} 
						Product []listProduct=orderList[i].getListP();
						int y=0;
	        		/*for(int x=0;x<listProduct.length&&listProduct[x]!=null;x++) {			//INFORMACION,DECREMENTO A MEDIDA QUE SE VAN HACIENDO COMANDAS DEL STOCK
	        			System.out.println("Stocks: "+listProduct[x].getStock());
	        		}*/
	        	
						while( y<listProduct.length&& listProduct[y]!=null){
							if(listProduct[y] instanceof HardwareProduct) {
								countHard--;
								listProduct[y].setStock((listProduct[y].getStock()+1));
							}
							if(listProduct[y] instanceof SoftwareProduct) {
								countSoft--;
								listProduct[y].setStock((listProduct[y].getStock()+1));
							}
							if(listProduct[y] instanceof Configuration) {
								countConf--;
								listProduct[y].setStock((listProduct[y].getStock()+1));
					
								Product[] listConfiguration=((Configuration)listProduct[y]).getLlista();
	        			
								for(int x=0;x<listConfiguration.length&&listConfiguration[x]!=null;x++) {			//DECREMENTO DEL STOCK DEL LISTADO DE PRODUCTOS DE CONFIGURACION
									listConfiguration[x].setStock((listConfiguration[x].getStock())+1);
	        			
								}
	        			
							}
							y++;
						}
	                
						numO--;
          		            
				}
				else { 
					i++;
				}
			}
		}
		catch(java.lang.NullPointerException e){
			System.out.println("No existe ninguna comanda.");
		}
	

		return (delete);
	}





/**
 * Método que permite saber si una comanda existe o no
 * @param id: id de la comanda
 * @return Booleano que indica si existe o no
 */
	public boolean existOrder(String id) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < orderList.length) {
			if (id.equals(orderList[i].getId())) {
			encontrado = true;
			i++;
			}
		}
		return encontrado;
	}

	
	

/**
 * Método que devuelve la comanda de determinada posición de la lista 
 * @param i: posición de la comanda
 * @return Comanda que pertenece a dicha posición
 */
	public Order seeOrder(int i) {
		if (i < numO && orderList[i] != null)
			return (orderList[i].copy());
		return null;
	}

	
	
/**
 * Método que hace una copia del listado de comandas	
 * @return Listado de comandas
 */
	public Order [] copyOrder (){
		Order [] copy= new Order[numO];
		for(int i=0;i<numO;i++) {
			copy [i]= orderList[i];
			
		}
		return (copy);
	}



public String  maxProducts() {
	int max=0;
	String maxP="";
		
	if(max<OrderList.getCountConf()){
		max=OrderList.getCountConf();
		maxP="Configuration con un total de ";
	}
	if(max<OrderList.getCountHard()) {
		max=OrderList.getCountHard();
		maxP="HardwareProduct con un total de ";

	}
	if(max<OrderList.getCountSoft()) {
		max=OrderList.getCountSoft();
		maxP="SoftwareProduct con un total de ";

	}
	
	return 	(maxP+max+" comandas");
	
}

/**
 * Método que escribe en fichero el listado de comandas
 * @param orderL: lista de comandas dónde se escribe el anterior.
 * @throws IOException
 */
	public  void writeData(Order []orderL) throws IOException  {
		ObjectOutputStream outputFile;
		try {
			outputFile = new ObjectOutputStream(new FileOutputStream("order.dat"));
			for (int i=0; i<numO; i++) {
				outputFile.writeObject(orderL[i]);
			}
			outputFile.close();
		}

		catch (IOException e) {
			System.out.println("Error al guardar/escribir en el archivo.");
		}
	}	
	
	
/**
 * Método que lee el fichero creado con el listado comandas
 * @param orderL: Lista a la cual se le asigna lo que lee
 * @throws IOException
 */
	public void readData(Order[] orderL)throws IOException {
		ObjectInputStream inputFile;
		int i=0;
		boolean finalFitxer=false;
	
		try {
			inputFile = new ObjectInputStream(new FileInputStream("order.dat"));
	
			try{
				while(!finalFitxer) {
					orderL[i]=(Order)inputFile.readObject();
					i++;
				}
			}
			catch (EOFException e) {
				finalFitxer=true;
		
			}
			inputFile.close();
	
		}
		catch (IOException e) {
			System.out.println("Error en l'arxiu d'entrada.");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Error, no es troba la classe Vehicle."+e);
		}
		catch (ClassCastException e){
			System.out.println("Error, el format de l'arxiu no és correcte per la definició actual de la classe Vehicle."+e);
		}

		}
		

	}




