package admin_shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFrame;
import client_menu.Comanda;
import client_menu.DetallesComanda;
import client_menu.Frame_CercaProducte;
import client_menu.MenuPrincipal;
import client_menu.posardni;
import clients.Client;
import clients.LlistaClients;
import order.Date;
import order.Order;
import order.OrderList;
import order.Order.Estate;
import product.Configuration;
import product.HardwareProduct;
import product.HardwareProduct.HardwareType;
import product.Product;
import product.ProductList;
import product.SoftwareProduct;
import product.SoftwareProduct.SistemasOperatius;

public class Admin {

	static Scanner entrada = new Scanner(System.in);

	// Menu option which the clients should use
	public static void mostraMenu() {
		System.out.println("\n Opciones del menu:");
		System.out.println("\t1.  Afegir un producte de software.");
		System.out.println("\t2.  Afegir un producte de hardware.");
		System.out.println("\t3.  Afegir una configuracion de producte.");
		System.out.println("\t4.  Donar de alta un client");
		System.out.println("\t5.  Donar de baixa un client");
		System.out.println(
				"\t6.  Treure un llistat de tots els productes que tenen alguna comanda, mostrant les dades del client que l’han fet.");
		System.out.println(
				"\t7.  Modificar l’estoc (a l’alça o a la baixa) de qualsevol dels productes que s’han donat d’alta a	partir del seu identificador");
		System.out.println("\t8.  Treure un llistat de tots el productes que tenen estoc>=1, indicant el seu estoc.");
		System.out.println("\t9.  Treure un llistat de tots els productes que formen part d’alguna configuració.");
		System.out.println("\t10. Mostrar el producte del qual s’han fet més comandes i indicar el número d’aquestes.");
		System.out.println("\t11. Consultar tots els elements de qualsevol llista que tingueu definida.");
		System.out.println("\t97. Grafic-> Introduzca dni.");
		System.out.println("\t98. Grafic-> Comanda.");
		System.out.println("\t99. Grafic for product.");
		System.out.println("\t100. Grafic for main menu.");
		System.out.println("\t101. Grafic for details of the command.");
		System.out.println("\t12. Sortir");
		System.out.print("\n\t\t\tIndica opcio:\n");
	}

	// Method to ask for product information, if n=1 the method will ask for
	// Hardware Product, if n=2 the will ask for Software Product, and if n=3 we
	// will ask for Configuration.
	public static Product getProduct(int n) {
		boolean aux = false;
		double price = 0;
		int stock = 0, so = 0, ho = 0;
		String name = "";
		Configuration confi = null;

		System.out.println("\n\n\tIndica el nom de producte: ");
		name = entrada.next();

		while (!aux) {
			try {
				System.out.println("\n\n\tIndica preu:");
				price = entrada.nextDouble();
				if (price <= 0)
					throw new Error("Preu no pot ser negartiu o 0");
				aux = true;
			} catch (InputMismatchException e) {
				System.out.println("Preu ha de ser numeric. Torna-hi ");
				entrada.next();
			} catch (Error e) {
				System.out.println(e.toString());
			}
		}

		aux = false;
		while (!aux) {
			try {
				System.out.print("\n\n\tIndica STOCK:\t");
				stock = entrada.nextInt();

				if (stock <= 0)
					throw new Error("Stock no pot ser negartiu o 0.");
				aux = true;
			} catch (InputMismatchException e) {
				System.out.println("Stock ha de ser numeric. Torna-hi ");
				entrada.next();
			} catch (Error e) {
				System.out.println(e.toString());
			}
		}

		if (n == 1) {// Hardware product
			aux = false;
			while (!aux) {
				try {
					System.out.print("\n\n\tIndica hardware, entre  CPU(0),MB(1),HDD(2),RAM(3),GPU(4),PERIFERIC(5):");
					ho = entrada.nextInt();

					if (ho < 0 || ho > 5)
						throw new Error("Opció fora de rang.");
					aux = true;
				} catch (InputMismatchException e) {
					System.out.println("Opció ha de ser numerica. Torna-hi ");
					entrada.next();
				} catch (Error e) {
					System.out.println(e.toString());
				}
			}

			HardwareType hardType = HardwareType.values()[ho];
			HardwareProduct hard = new HardwareProduct(price, name, stock, hardType);
			return hard;

		} else if (n == 2) { // Software product
			aux = false;
			while (!aux) {
				try {
					System.out.print("\n\n\tIndica SO, entre  WINDOWS(0), LINUX(1),  macOS(2):");
					so = entrada.nextInt();

					if (so < 0 || so > 2)
						throw new Error("Opció fora de rang.");
					aux = true;
				} catch (InputMismatchException e) {
					System.out.println("Opció ha de ser numerica. Torna-hi ");
					entrada.next();
				} catch (Error e) {
					System.out.println(e.toString());
				}
			}

			SistemasOperatius OperativeSystem = SistemasOperatius.values()[so];
			SoftwareProduct soft = new SoftwareProduct(price, name, stock, OperativeSystem);
			return soft;
		} else if (n == 3) {
			confi = new Configuration(price, name, stock);
			return confi;
		} else
			return null;

	}

	// Option 1 where the user can add a Software product.
	public static void opcio1(ProductList listaP) {
		boolean comprobar;
		SoftwareProduct s = (SoftwareProduct) getProduct(2);
		comprobar = listaP.addSoftwareProduct(s);

		if (comprobar) {
			System.out.println("L'element s'ha afegit correctament a la llista");
		} else
			System.out.println("L'element no s'ha pogut afegir a la llista");

	}

	// Option 2 where the user can add a Hardware product.
	public static void opcio2(ProductList listaP) {
		boolean comprobar;
		HardwareProduct h = (HardwareProduct) getProduct(1);
		comprobar = listaP.addHardwareProduct(h);

		if (comprobar) {
			System.out.println("L'elemento s'ha afegit correctament a la llista");
		} else
			System.out.println("L'elemento no s'ha pogutn  afegit.");
	}

	// Option 3 where the user can add a Configuration product.
	public static void opcio3(ProductList listaP) {
		int op = 0, compHard = 0, compSoft = 0;
		boolean components = false, aux = false;
		Configuration c = (Configuration) getProduct(3);
		HardwareProduct h = null;
		SoftwareProduct s = null;
		if (c != null) {

			while (!components) {
				System.out.println("\n\n\t\t--------------");
				System.out.println(
						"\n\t\t Nº components Hardware: " + compHard + "\t\t Nº components Software: " + compSoft);
				System.out.println("\n\t\t" + c.components());
				System.out.println("\n\t\t--------------");
				aux = false;
				while (!aux) {
					try {
						System.out.print("\n\n\tIndica producte, entre  Hardware[1], Software[2]");
						op = entrada.nextInt();

						if (op <= 0 || op > 2) {
							throw new Error("Opció fora de rang.");
						} else {
							if (op == 1) {
								h = (HardwareProduct) getProduct(1);
								if (h != null) {
									if (c.addProduct(h) == 1)
										compHard++;
								}
							} else if (op == 2) {
								s = (SoftwareProduct) getProduct(2);
								if (s != null) {
									if (c.addProduct(s) == 1)
										compSoft++;
								}
							}
							aux = true;
						}

					} catch (InputMismatchException e) {
						System.out.println("Opció ha de ser numerica. Torna-hi ");
						entrada.next();
					} catch (Error e) {
						System.out.println(e.toString());
					}
				}

				if (compHard >= 6 && compSoft >= 1) {
					if (c.chekProduct()) {
						components = true;
					} else {
						System.out.println(
								"Encara falta algun component...Ha de tendre com a mínim un component de cada tipus.");
					}
				}
			}
			if (listaP.addConfigurationProduct(c) == 1) {
				System.out.println("S'ha afegir la configuració correctament!");
			} else {
				System.out.println("No s'ha pogut afegir la configuració...Ha de tendre com a mínim un component!");
			}
		}
	}

	// Option 4 where the user can add a client.
	public static void opcio4(LlistaClients listaC) {
		int comprobar = 0;
		String email = "", dni = "";
		int codiPostal = 0;
		boolean aux = false;
		System.out.println("Introdueix Dni del client: ");
		dni = entrada.next();
		while (!aux) {
			comprobar = listaC.ExisteixClient(dni);
			if (comprobar == -1) {
				aux = true;
			} else {
				System.out.println("El client ja existeix.");
				System.out.println("\n\nIntrodueix Dni del client: ");
				dni = entrada.next();
			}
		}

		System.out.println("Introdueix email: ");
		email = entrada.next();

		while (aux) {
			try {
				System.out.println("\n\n\tIndica Codi Postal: ");
				codiPostal = entrada.nextInt();
				if (codiPostal <= 0)
					throw new Error("Stock no pot ser negartiu o 0.");
				aux = false;
			} catch (InputMismatchException e) {
				System.out.println("Stock ha de ser numeric. Torna-hi ");
				entrada.next();
			} catch (Error e) {
				System.out.println(e.toString());
			}
		}
		if (comprobar == -1) {
			Client client = new Client(dni, email, codiPostal);
			comprobar = listaC.AltaClient(client);
			if (comprobar == 0) {
				System.out.println("El cliente s'ha afegit correctament a la llista");
			} else
				System.out.println("El cliente no s'ha pogut afegir.");
		}

	}

	// Option 5 where the user can delete a client.
	public static void opcio5(LlistaClients listaC) {
		boolean acabar = false;
		int op = 0;
		int comprobar;

		while (!acabar) {
			System.out.println("Introdueix DNI del client: ");
			String dni = entrada.next();
			comprobar = listaC.ExisteixClient(dni);
			if (comprobar == -1) {

				try {
					System.out.println("El client no existeix.");
					System.out.println("\nVols tornar-hi? si[1]  no[2].");
					op = entrada.nextInt();
					if (op <= 0)
						throw new Error("Opció no pot ser negartiva o 0.");
					else if (op < 1 || op > 2)
						throw new Error("Opció fuera de rang.");
					else if (op == 2)
						acabar = true;
				} catch (InputMismatchException e) {
					System.out.println("Stock ha de ser numeric. Torna-hi ");
					entrada.next();
				} catch (Error e) {
					System.out.println(e.toString());
				}

			} else {
				comprobar = listaC.BaixaClient(dni);
				if (comprobar == 0) {
					System.out.println("El cliente se ha borrado correctamente.");
					acabar = true;
				} else
					System.out.println("El cliente no se ha pogut borrar de la llista.");
			}
		}

	}

	//Option 6 where the user can see all product with her commands and the information about the client who did the purchase.
	public static void opcio6(OrderList listaOL) {
		System.out.print("\n\n\tIndica el id de la comanda:\t");
		String id = entrada.next();
		try {
			Product[] aux = listaOL.orderId(id).getListP();
			System.out.println("\n\tProducts are:\n");
			for (int i = 0; i < listaOL.orderId(id).getListP().length && aux[i] != null; i++) {
				System.out.println("\t\t" + aux[i]);
			}
			System.out.println("Customer data is as follows:" + listaOL.orderId(id).getClient());

		}catch (java.lang.NullPointerException e) {

		}
	}

	// Option 7 where the user can change the product stock.
	public static void opcio7(ProductList listaP) {
		boolean comprobar, aux = false;
		int id = 0, stock = 0, opc = 0;
		while (!aux) {
			try {
				System.out.println("\n\nIndica Id: ");
				id = entrada.nextInt();
				if (id <= 0)
					throw new Error("Id no pot ser negartiu o 0.");
				aux = true;
			} catch (InputMismatchException e) {
				System.out.println("Id ha de ser numeric. Torna-hi ");
				entrada.next();
			} catch (Error e) {
				System.out.println(e.toString());
			}
		}

		aux = false;
		while (!aux) {
			try {
				System.out.println("\n\nIndica quantitat stock: ");
				stock = entrada.nextInt();
				if (stock <= 0)
					throw new Error("stock no pot ser negartiu o 0.");
				aux = true;
			} catch (InputMismatchException e) {
				System.out.println("stock ha de ser numeric. Torna-hi ");
				entrada.next();
			} catch (Error e) {
				System.out.println(e.toString());
			}
		}

		aux = false;
		while (!aux) {
			try {
				System.out.println("\n\nIndica si vols sumar[1] o restar[0]: ");
				opc = entrada.nextInt();
				if (opc < 0 || opc > 1)
					throw new Error("Opció fora de rang.");
				aux = true;
			} catch (InputMismatchException e) {
				System.out.println("Opció ha de ser numeric. Torna-hi ");
				entrada.next();
			} catch (Error e) {
				System.out.println(e.toString());
			}
		}

		comprobar = listaP.changeStock(id, stock, opc);

		if (comprobar == true) {
			System.out.println("El stock s'ha modificat correctament");
		} else
			System.out.println("El stock no ha pogut modificar");

	}

	//Option 8 where the user can see all product which her stock is >0.
	public static void opcio8(ProductList listaP) {
		int i = 0;
		Product[] List_prod = listaP.stockMoreOne();
		if (List_prod.length == 0) {
			System.out.println("Vacio, ningun producto que satisfazca la condicion");
		} else {
			System.out.println(
					"\n\t+--------------------------------------------------------------------------------------+");
			while (i < List_prod.length && List_prod[i] != null) {
				System.out.println("\t+  " + List_prod[i]);
				i++;
			}
			System.out.println(
					"\t+--------------------------------------------------------------------------------------+");
		}
	}
	// Option 9 where the user can see the configuration product which have one of this components.
	private static void opcio9(ProductList listaP) {
		String nom_configu = "";
		int i = 0;
		Product[] list = null;
		String[] configur = { "CPU", "MB", "HDD", "RAM", "PERIFERIC", "WINDOWS", "LINUX", "macOS" };

		System.out.println("\n\n\n--- Configuracions amb les quals disposem:---------++ ");
		while (i < configur.length) {
			System.out.println("\t++---  " + configur[i]);
			i++;
		}
		System.out.println("\t++------------------------------------++\n\n");

		System.out.println("Introdueix el nom de la configuració a buscar: ");
		nom_configu = entrada.next();

		System.out.println();
		list = listaP.ListProductsOfConfiguration(nom_configu);
		i = 0;
		System.out.println("\t\t Nombre" + "\t\t    Preu" + "\tStock");
		System.out.println("\t\t--------" + "           -------" + "\t-------");
		while (i < list.length && list[i] != null) {
			System.out.println("\t++---  " + list[i].getName() + "\t" + list[i].getPrice() + "\t" + list[i].getStock()
					+ "  ----------++");
			i++;
		}
	}

	private static void opcio10(OrderList listaOL) {
		System.out.println("The products with more commands have been: " + listaOL.maxProducts());

	}

	public static void opcio11(ProductList listaP, LlistaClients listaC, OrderList listaO) {
		if (listaP.getnElem() > 0) {
			System.out.println(
					"\n\t+--------------------------------------------------------------------------------------+");
			System.out.println("\t Productes: ");
			for (int i = 0; i < listaP.getnElem(); i++) {
				Product p = listaP.mostrarProduct(i);
				System.out.println("\t+  " + p);
			}
			System.out.println(
					"\t+--------------------------------------------------------------------------------------+");

		} else {
			System.out.println("\n\t No hi ha cap producte!");
		}

		if (listaC.getNumClients() > 0) {
			System.out.println(
					"\n\t+------------------------------------------------------------------------------------------------------------+");
			System.out.println("\t Clients: ");
			for (int i = 0; i < listaC.getNumClients(); i++) {

				Client c;
				c = listaC.Mostrartots()[i];
				System.out.println(c.toString());
			}
		} else {
			System.out.println("\n\t No hi ha cap clien!");
		}

		if (listaO.getNumO() > 0) {
			System.out.println(
					"\n\t+-----------------------------------------------------------------------------------------------------------+");
			System.out.println("\t Comanda: ");
			Order[] aux = listaO.getOrderList();
			for (int i = 0; i < listaO.getNumO(); i++) {
				if (aux[i] != null) {
					System.out.println(aux[i]);
				}
			}
		} else {
			System.out.println("\n\t No hi ha cap comanda!");
		}
	}

	public static void opcio12(ProductList listaP, String path_hard, String path_soft, String path_configuration,
			LlistaClients c) {

		boolean op = false;
		int opcio = 0;
		// int i = 0;
		// Product[] lista = listaP.getLlista();

		while (!op) {
			try {
				System.out.println("\n\n\t\tVols desa el canvis? si[1] no[2]");
				opcio = entrada.nextInt();
				if (opcio <= 0)
					throw new Error("Opció no pot ser negartiu o 0.");
				else if (opcio > 2)
					throw new Error("Opció fora de rang.");
				else if (opcio == 1 || opcio == 2)
					op = true;
			} catch (Error e) {
				System.out.println(e.toString());
			} catch (InputMismatchException e) {
				System.out.println(e.toString());
				entrada.next();
			}
		}

		if (opcio == 1) {
			listaP.Write(listaP.getLlista(), path_hard, path_soft, path_configuration);
		}
		/* Guardar los clientes en un fitxero de texto */
		File store = new File("src/Store.txt");
		try {
			c.WriterFile(store);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void opcio97(LlistaClients listaCl) {
		new posardni(listaCl, "PosarDNI");
	}

	public static void opcio98(String titol, OrderList llistO, ProductList listaP) {
		new Comanda(titol, llistO, listaP);
	}

	public static void opcio99(ProductList listaP) {
		new Frame_CercaProducte(listaP);
	}

	public static void opcio100(ProductList listaPL,LlistaClients listaCL) throws IOException {

		System.out.println("Introduce el DNI: ");
		String dni = entrada.next();

		Client client = listaCL.MostratClient(dni);
		// System.out.println(client);
		Order o2 = new Order(listaPL.getLlista(), Estate.CANCELED, client, new Date(13, 10, 2017));

		Order[] listaOL1 = new Order[100];
		OrderList orderList1 = new OrderList(100);

		orderList1.readData(listaOL1);

		int y1 = 0;
		while (y1 < listaOL1.length && listaOL1 != null) {
			orderList1.addOrderData(listaOL1[y1]);
			y1++;

		}

		MenuPrincipal menu = new MenuPrincipal(orderList1, o2, dni);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);

		new MenuPrincipal(orderList1, o2, dni);

	}

	public static void opcio101() throws IOException {
		Order[] listaOL = new Order[100];
		OrderList orderList = new OrderList(100);

		orderList.readData(listaOL);
		int y = 0;
		while (y < listaOL.length && listaOL != null) {
			orderList.addOrderData(listaOL[y]);
			y++;

		}

		DetallesComanda detallesC = new DetallesComanda(listaOL[2]);
		detallesC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		detallesC.setVisible(true);
		new DetallesComanda(listaOL[2]);
	}
	

	public static void main(String[] args) throws IOException {
		boolean end = false;
		int opcio = 0, i = 0;
		ProductList listaPL = new ProductList(100);
		LlistaClients listaCL = new LlistaClients(100);
		OrderList listaO = new OrderList(100);
		Order[] listaOL = new Order[100];

		// Direction of products files
		String s1 = "src/products_fila_hardward.txt";
		String s2 = "src/products_fila_software.txt";
		String s3 = "src/products_fila_configurations.txt";
		File s4 = new File("src/Clients.txt");
		String titol = "Random";

		// Get orders file and save them into orderList
		OrderList orderList = new OrderList(100);
		orderList.readData(listaOL);

		int y = 0;
		while (y < listaOL.length && listaOL != null) {
			orderList.addOrderData(listaOL[y]);
			y++;

		}

		// Get products of Hardware file and save them into ProductList
		Product[] list = listaPL.readProductsFile(s1);
		i = 0;
		while (i < list.length && list[i] != null) {
			HardwareProduct hardProduct = (HardwareProduct) list[i];
			listaPL.addHardwareProduct(hardProduct);
			i++;
		}

		// Get products of Software file and save them into ProductList
		list = listaPL.readProductsFile(s2);
		i = 0;
		while (i < list.length && list[i] != null) {
			SoftwareProduct softProduct = (SoftwareProduct) list[i];
			listaPL.addSoftwareProduct(softProduct);
			i++;
		}

		list = listaPL.readConfigurationProductFile(s3);
		i = 0;
		while (i < list.length && list[i] != null) {
			Configuration configurationProduct = (Configuration) list[i];
			listaPL.addConfigurationProduct(configurationProduct);
			i++;
		}
		try {
			listaCL.ReadFile(s4);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		while (!end) {
			try {
				mostraMenu();
				opcio = entrada.nextInt();
				if (opcio <= 0)
					throw new Error("Opció no pot ser negartiu o 0.");
			} catch (Error e) {
				System.out.println(e.toString());
			} catch (InputMismatchException e) {
				System.out.println(e.toString());
				entrada.next();
			}

			switch (opcio) {
			case 1:
				opcio1(listaPL);
				break;
			case 2:
				opcio2(listaPL);
				break;
			case 3:
				opcio3(listaPL);
				break;
			case 4:
				opcio4(listaCL);
				break;
			case 5:
				opcio5(listaCL);
				break;
			case 6:
				opcio6(orderList);
				break;
			case 7:
				opcio7(listaPL);
				break;
			case 8:
				opcio8(listaPL);
				break;
			case 9:
				opcio9(listaPL);
				break;
			case 10:
				opcio10(orderList);
				break;
			case 11:
				opcio11(listaPL, listaCL, orderList);
				break;
			case 12:
				opcio12(listaPL, s1, s2, s3, listaCL);
				end = true;
				break;
			case 97:
				opcio97(listaCL);
				break;
			case 98:
				opcio98(titol, listaO, listaPL);
				break;
			case 99:
				opcio99(listaPL);
				break;
			case 100:
				opcio100(listaPL,listaCL);
				break;
			case 101:
				opcio101();
				break;
			default:
				System.out.println("Error...Opció incorrecte!");
				break;
			}
		}
	}

}
