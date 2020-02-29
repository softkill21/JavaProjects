package product;

import product.Configuration;
import product.HardwareProduct;
import product.Product;
import product.SoftwareProduct;
import product.HardwareProduct.HardwareType;
import product.SoftwareProduct.SistemasOperatius;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.StringTokenizer;

import admin_shop.Error;

/**
 * ProductList class, this class contains a list of all products.
 * 
 * @author Adelin with Younes
 *
 */
public class ProductList implements Serializable {

	private static final long serialVersionUID = 1L;
	private Product[] productList;
	private int nElem;
	private int mida;

	/**
	 * ProductList Constructor.
	 * 
	 * @param mida : The length of the list.
	 */
	public ProductList(int mida) {
		this.mida = mida;
		nElem = 0;
		productList = new Product[mida];

	}

	/**
	 * Method to get the list of products.
	 * 
	 * @return Will return the product list.
	 */
	public Product[] getLlista() {
		Product[] list = new Product[nElem];
		int i = 0;
		while (i < nElem) {
			list[i] = this.productList[i];
			i++;
		}
		return list;
	}

	/**
	 * Method to change the values of the list.
	 * 
	 * @param llista
	 */
	public void setLlista(Product[] llista) {
		this.productList = llista;
	}

	/**
	 * Method to get the index of the list.
	 * 
	 * @return Will return a value number.
	 */
	public int getnElem() {
		return nElem;
	}

	/**
	 * Method to change index value.
	 * 
	 * @param nElem : New value.
	 */
	public void setnElem(int nElem) {
		this.nElem = nElem;
	}

	/**
	 * Method to add a Hardware product.
	 * 
	 * @param h : The new HardwareProduct.
	 * @return Will return a boolean variable to indicate if the product was added
	 *         of not.
	 */
	public boolean addHardwareProduct(HardwareProduct h) {
		int pos = 0;
		int stock = 0;
		int comprobar = 0;
		boolean b = false;
		try {

			if (isEmpty()) {
				if (!existProduct(h.getId())) {
					productList[nElem] = h.copia();
					nElem++;
					comprobar = 1;
					HardwareProduct.countHard++;
					b = true;
				} else {
					pos = positionOfProduct(h.getId());
					if (pos != -1) {
						stock = this.productList[pos].getStock() + h.getStock();
						productList[pos].setStock(stock);
						HardwareProduct.countHard++;
						comprobar = -2;
					}
				}
			} else {
				b = false;
				comprobar = -1;
			}

			if (comprobar == -1)
				throw new Error("Error...No hi ha espai en la llista.");
			if (comprobar == -2)
				throw new Error("Aquest producte ja existeix en el sistema, hem modificate el seu stock.");
		} catch (Error e) {
			System.out.println(e.toString());
		}

		return b;
	}

	/**
	 * Method to add a Software product.
	 * 
	 * @param s : The new product to add.
	 * @return Will return a boolean variable to indicate if the product was added
	 *         of not.
	 */
	public boolean addSoftwareProduct(SoftwareProduct s) {
		int pos = 0;
		int stock = 0;
		int comprobar = 0;
		boolean b = false;
		try {
			if (isEmpty()) {
				if (!existProduct(s.getId())) {
					productList[nElem] = s.copia();
					nElem++;
					b = true;
				} else {
					pos = positionOfProduct(s.getId());
					if (pos != -1) {
						stock = this.productList[pos].getStock() + s.getStock();
						productList[pos].setStock(stock);
						comprobar = -2;
					}
				}

			} else {
				comprobar = -1;
				b = false;
			}

			if (comprobar == -1)
				throw new Error("Error...No hi ha espai en la llista.");
			if (comprobar == -2)
				throw new Error("Aquest producte ja existeix en el sistema, hem modificate el seu stock.");
		} catch (Error e) {
			System.out.println(e.toString());
		}

		return b;
	}

	/**
	 * Method to add a Configuration product.
	 * 
	 * @param c : The new product to add.
	 * @return Will return a boolean variable to indicate if the product was added
	 *         of not.
	 */
	public int addConfigurationProduct(Configuration c) {
		int pos = 0;
		int stock = 0;
		int comprobar = 0;

		if (isEmpty()) {
			if (!existProduct(c.getId())) {
				productList[nElem] = c.copia();
				nElem++;
				comprobar = 1;
				Configuration.countConf++;
			} else {
				pos = positionOfProduct(c.getId());
				if (pos != -1) {
					stock = this.productList[pos].getStock() + c.getStock();
					productList[pos].setStock(stock);
					comprobar = 1;
					Configuration.countConf++;
				}
			}

		} else
			comprobar = -1;
		return comprobar;
	}

	/**
	 * Method to delete a Hardware product.
	 * 
	 * @param h : Hardware product which we want to delete.
	 * @return Will return 1 if we have deleted it and -1 if not.
	 */
	public int deleteHardwareProduct(HardwareProduct h) {
		int deleted = 0;
		int pos_deleted = 0;
		if (existProduct(h.getId())) {
			for (int i = 0; i < nElem; i++) {
				if (productList[i].getId() == h.getId()) {
					productList[i] = null;
					pos_deleted = i;
					deleted = 1;
					this.nElem--;
					HardwareProduct.countHard--;
				}
			}

			if (deleted == 1) {
				while (pos_deleted < nElem) {
					productList[pos_deleted] = productList[pos_deleted + 1];
					pos_deleted++;
				}
			}
		} else
			deleted = -1;

		return deleted;
	}

	/**
	 * Method to delete a Software product.
	 * 
	 * @param s : Software product which we want to delete.
	 * @return Will return 1 if we have deleted it and -1 if not.
	 */
	public int deleteSoftwareProduct(SoftwareProduct s) {
		int deleted = 0;
		int pos_deleted = 0;
		if (existProduct(s.getId())) {
			for (int i = 0; i < nElem; i++) {
				if (productList[i].getId() == s.getId()) {
					productList[i] = null;
					pos_deleted = i;
					deleted = 1;
					this.nElem--;
					SoftwareProduct.countSoft--;
				}
			}

			if (deleted == 1) {
				while (pos_deleted < nElem) {
					productList[pos_deleted] = productList[pos_deleted + 1];
					pos_deleted++;
				}
			}
		} else
			deleted = -1;

		return deleted;
	}

	/**
	 * Method to delete a Configuration product.
	 * 
	 * @param c : The Configuration product which we want to delete.
	 * @return Will return -1 if we didn't deleted it and 1 in the opposite case.
	 */
	public int deleteConfigurationProduct(Configuration c) {
		int deleted = 0;
		int pos_deleted = 0;
		if (existProduct(c.getId())) {
			for (int i = 0; i < nElem; i++) {
				if (productList[i].getId() == c.getId()) {
					productList[i] = null;
					pos_deleted = i;
					deleted = 1;
					this.nElem--;
					Configuration.countConf--;
				}
			}

			if (deleted == 1) {
				while (pos_deleted < nElem) {
					productList[pos_deleted] = productList[pos_deleted + 1];
					pos_deleted++;
				}
			}
		} else
			deleted = -1;

		return deleted;
	}

	/**
	 * Method to check if the list is empty.
	 * 
	 * @return Will return true if we have space and false in the opposite case.
	 */
	public boolean isEmpty() {
		boolean empty = false;
		if (this.nElem >= 0 && nElem < this.mida)
			empty = true;

		return empty;
	}

	/**
	 * Method which check if the product exist by her id.
	 * 
	 * @param id : Id of product.
	 * @return Will return true if exist and false if not.
	 */
	public boolean existProduct(int id) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < nElem) {
			if (productList[i].getId() == id)
				encontrado = true;
			i++;
		}
		return encontrado;
	}

	/**
	 * Method to get the position of product by her Id.
	 * 
	 * @param id : The id of product which we want to get her position.
	 * @return Will return the position of the product in the list, and -1 if the
	 *         product doesn't exist.
	 */
	public int positionOfProduct(int id) {
		int i = 0;
		int pos = -1;
		boolean find = false;

		while (!find && i < nElem) {
			if (id == this.productList[i].getId()) {
				find = true;
				pos = i;
			}
			i++;
		}
		return pos;
	}

	/**
	 * Method to change the stock of a product, n=0 if we want to subtract stock and
	 * n=1 if we want to add.
	 * 
	 * @param id    : The id of product which we want to change.
	 * @param sotck : New stock value.
	 * @param n     : Variable which are going to indicate us if we are going to add
	 *              or subtract.
	 * @return Will return a boolean variable to indicate the if the sock was
	 *         changed of not.
	 */
	public boolean changeStock(int id, int sotck, int n) {
		boolean change = false;
		int s = 0;
		int pos = positionOfProduct(id);
		if (pos != -1) {
			if (n == 0) {

				s = this.productList[pos].getStock() - sotck;
				if (s < 0) {
					this.productList[pos].setStock(0);
					change = true;
				} else {
					this.productList[pos].setStock(s);
					change = true;
				}

			} else if (n == 1) {

				s = this.productList[pos].getStock() + sotck;
				this.productList[pos].setStock(s);
				change = true;
			}
		}

		return change;
	}

	/**
	 * Method to get one product by her id.
	 * 
	 * @param id : The id of product.
	 * @return Will return the owner id, and null if the product doesn't exist.
	 */
	public Product productById(int id) {
		Product p = null;
		int i = 0;
		boolean find = false;

		while (i < nElem && !find) {
			if (this.productList[i].getId() == id) {
				p = productList[i].copia();
				find = true;
			}
			i++;
		}
		return p;
	}

	/**
	 * Method to get all product with stock >= 1.
	 * 
	 * @return Will return a list of products.
	 */
	public Product[] stockMoreOne() {
		Product[] list = new Product[nElem];
		int i = 0;
		while (i < nElem) {
			if (this.productList[i].getStock() >= 1)
				list[i] = this.productList[i];
			i++;
		}
		return list;
	}

	public Product mostrarProduct(int i) {
		if (i < nElem && productList[i] != null)
			return (productList[i].copia());
		return null;
	}

	/**
	 * Method to get all products which name is the equal to the given name.
	 * 
	 * @param name : Name to search.
	 * @return Will return all products with the same name.
	 */
	public Product[] productListHardConf(String name) {
		Product[] list = new Product[nElem];
		Product[] list2 = new Product[nElem];
		int cont = 0;
		int i = 0;
		if (nElem > 0) {
			while (i < nElem) {
				if (productList[i] instanceof HardwareProduct) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}
				if (productList[i] instanceof Configuration) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}
				if (productList[i] instanceof SoftwareProduct) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}

				i++;
			}
		}
		return list2;
	}

	/**
	 * Method to get products which name is equal to the given.
	 * 
	 * @param c : Name of product.
	 * @return Will return a list of products.
	 */
	public Product[] ListProductsOfConfiguration(String c) {
		HardwareProduct har = null;
		SoftwareProduct sof = null;
		int j = 0;
		Product[] products = new Product[mida];
		for (int i = 0; i < nElem; i++) {
			if (productList[i] instanceof HardwareProduct) {
				har = (HardwareProduct) productList[i].copia();
				if (har.getHardware().name().equals(c)) {
					products[j] = har;
					j++;
				}
			} else if (productList[i] instanceof SoftwareProduct) {
				sof = (SoftwareProduct) productList[i].copia();
				if (sof.getSo().name().equals(c)) {
					products[j] = sof;
					j++;
				}
			}
		}
		return products;
	}

	/**
	 * Method to get all Software Product which name is equal to the given.
	 * 
	 * @param name : Name of Software product.
	 * @return will return a list of Software Product.
	 */
	public Product[] productListSoft(String name) {
		Product[] list = new Product[nElem];
		Product[] list2 = new Product[nElem];
		int cont = 0;
		int i = 0;
		if (nElem > 0) {
			while (i < nElem) {
				if (productList[i] instanceof SoftwareProduct) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}

				i++;
			}
		}
		return list2;
	}

	/**
	 * Method to get all Software Product and Hardware Product which name is equal
	 * to the given.
	 * 
	 * @param name : Name of product.
	 * @return Will return a list of products.
	 */
	public Product[] productListSoftHard(String name) {
		Product[] list = new Product[nElem];
		Product[] list2 = new Product[nElem];
		int cont = 0;
		int i = 0;
		if (nElem > 0) {
			while (i < nElem) {
				if (productList[i] instanceof SoftwareProduct) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}

				if (productList[i] instanceof HardwareProduct) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}

				i++;
			}
		}
		return list2;
	}

	/**
	 * Method to get all Software Product and Configuration Product which name is
	 * equal to the given.
	 * 
	 * @param name : Name of product.
	 * @return Will return a list of products.
	 */
	public Product[] productListSoftConf(String name) {
		Product[] list = new Product[nElem];
		Product[] list2 = new Product[nElem];
		int cont = 0;
		int i = 0;
		if (nElem > 0) {
			while (i < nElem) {
				if (productList[i] instanceof SoftwareProduct) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}

				if (productList[i] instanceof Configuration) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}

				i++;
			}
		}
		return list2;
	}

	/**
	 * Method to get all HardwareProduct which name is the equal to the given name.
	 * 
	 * @param name to search.
	 * @return Will return a list of products.
	 */
	public Product[] productListHard(String name) {
		Product[] list = new Product[nElem];
		Product[] list2 = new Product[nElem];
		int cont = 0;
		int i = 0;
		if (nElem > 0) {
			while (i < nElem) {
				if (productList[i] instanceof HardwareProduct) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}
				}

				i++;
			}
		}
		return list2;
	}

	/**
	 * Method to get all products which name is the equal to the given name.
	 * 
	 * @param name : Name to search.
	 * @return Will return all products with the same name.
	 */
	public Product[] productListSoftHardConf(String name) {
		Product[] list = new Product[nElem];
		Product[] list2 = new Product[nElem];
		int cont = 0;
		int i = 0;
		if (nElem > 0) {
			while (i < nElem) {
				if (productList[i] instanceof HardwareProduct) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}
				if (productList[i] instanceof Configuration) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}
				if (productList[i] instanceof SoftwareProduct) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						list2[cont] = list[i];
						cont++;
					}

				}

				i++;
			}
		}
		return list2;
	}

	/**
	 * Method to get all configuration product which stock is >0.
	 * 
	 * @param name : Name to search.
	 * @return Will return a list of products.
	 */
	public Product[] productListConf(String name) {
		Product[] list = new Product[nElem];
		Product[] list2 = new Product[nElem];
		int cont = 0;
		int i = 0;
		if (nElem > 0) {
			while (i < nElem) {
				if (productList[i] instanceof Configuration) {
					list[i] = this.productList[i];
					if (list[i].getName().contains(name)) {
						if (list[i].getStock() > 0)
							list2[cont] = list[i];
						cont++;
					}
				}
				i++;
			}
		}
		return list2;
	}

	/**
	 * Method to read the products file, with this method we can read hardware and
	 * software products.
	 * 
	 * @param s : The file path.
	 * @return Will return a list of products.
	 */
	public Product[] readProductsFile(String s) {
		int nElem = 0;
		Product[] list = new Product[100];
		SoftwareProduct soft = null;
		HardwareProduct hard = null;
		try {

			BufferedReader f = new BufferedReader(new FileReader(s));
			String frase;
			double price;
			String name;
			int stock;
			String type = "";

			frase = f.readLine();
			while (frase != null) {
				StringTokenizer st = new StringTokenizer(frase, "*");
				price = Double.parseDouble(st.nextToken());
				name = st.nextToken();
				stock = Integer.parseInt(st.nextToken());
				type = st.nextToken();

				if (type.equals("WINDOWS") || type.equals("LINUX") || type.equals("macOS")) {

					if (type.equals("WINDOWS")) {
						SistemasOperatius Windows = SistemasOperatius.WINDOWS;
						soft = new SoftwareProduct(price, name, stock, Windows);
						list[nElem] = soft;
						nElem++;

					} else if (type.equals("LINUX")) {
						SistemasOperatius Linux = SistemasOperatius.LINUX;
						soft = new SoftwareProduct(price, name, stock, Linux);
						list[nElem] = soft;
						nElem++;
					} else {
						SistemasOperatius Mac = SistemasOperatius.macOS;
						soft = new SoftwareProduct(price, name, stock, Mac);
						list[nElem] = soft;
						nElem++;
					}

				} else {
					switch (type) {
					case "CPU":
						HardwareType CPU = HardwareType.CPU;
						hard = new HardwareProduct(price, name, stock, CPU);
						list[nElem] = hard;
						nElem++;
						break;
					case "MB":
						HardwareType MB = HardwareType.MB;
						hard = new HardwareProduct(price, name, stock, MB);
						list[nElem] = hard;
						nElem++;
						break;
					case "HDD":
						HardwareType HDD = HardwareType.HDD;
						hard = new HardwareProduct(price, name, stock, HDD);
						list[nElem] = hard;
						nElem++;
						break;
					case "RAM":
						HardwareType RAM = HardwareType.RAM;
						hard = new HardwareProduct(price, name, stock, RAM);
						list[nElem] = hard;
						nElem++;
						break;
					case "GPU":
						HardwareType GPU = HardwareType.GPU;
						hard = new HardwareProduct(price, name, stock, GPU);
						list[nElem] = hard;
						nElem++;
						break;
					case "PERIFERIC":
						HardwareType PRIFÈRIC = HardwareType.PERIFERIC;
						hard = new HardwareProduct(price, name, stock, PRIFÈRIC);
						list[nElem] = hard;
						nElem++;
						break;

					}
				}

				frase = f.readLine();
			}
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("L'arxiu d'entrada no existeix");
		} catch (IOException e) {
			System.out.println("S'ha produit un error en els arxius");
		}
		return list;
	}

	/**
	 * Method which organize the file methods, depends of the product type, we will
	 * call one method or another.
	 * 
	 * @param list               : List of products where we have to save.
	 * @param path_hard          : File path where we are going to save Hardware
	 *                           products.
	 * @param path_soft          : File path where we are going to save Software
	 *                           products.
	 * @param path_configuration : File path where we are going to save
	 *                           Configuration products.
	 */
	public void Write(Product[] list, String path_hard, String path_soft, String path_configuration) {

		HardwareProduct[] list_hardware = new HardwareProduct[list.length];
		SoftwareProduct[] list_software = new SoftwareProduct[list.length];
		Configuration[] list_configuration = new Configuration[list.length];
		int i = 0, j = 0, k = 0;
		for (int k2 = 0; k2 < list.length && list[k2] != null; k2++) {

			if (list[k2] instanceof HardwareProduct) {
				list_hardware[i] = (HardwareProduct) list[k2];
				i++;
			} else if (list[k2] instanceof SoftwareProduct) {
				list_software[j] = (SoftwareProduct) list[k2];
				j++;
			} else if (list[k2] instanceof Configuration) {
				list_configuration[k] = (Configuration) list[k2];
				k++;
			}
		}
		if (i > 0) {
			WriteHardware(list_hardware, path_hard);
		}
		if (j > 0) {
			WriteSoftWare(list_software, path_soft);
		}
		if (k > 0) {
			WriteConfigurationProduct(list_configuration, path_configuration);
		}
	}

	/**
	 * Method to save Software products in File.
	 * 
	 * @param p         : List of products where we have to save.
	 * @param path_hard : File path where we are going to save products.
	 */
	public void WriteHardware(Product[] p, String path_hard) {
		try {
			BufferedWriter g = new BufferedWriter(new FileWriter(path_hard));
			String frase = "";

			for (int i = 0; i < p.length && p[i] != null; i++) {

				frase = frase + p[i].getPrice() + "*";
				frase = frase + p[i].getName() + "*";
				frase = frase + p[i].getStock() + "*";

				if (p[i] instanceof HardwareProduct) {
					HardwareProduct hard = (HardwareProduct) p[i];
					frase = frase + hard.getHardware();
					g.write(frase);
				}
				frase = "";
				g.newLine();
			}
			g.close();
		} catch (FileNotFoundException e) {
			System.out.println("L'arxiu d'entrada no existeix");
		} catch (IOException e) {
			System.out.println("S'ha produit un error en els arxius");
		}
	}

	/**
	 * Method to save Software products in File.
	 * 
	 * @param p         : List of products where we have to save.
	 * @param path_soft : File path where we are going to save products.
	 */
	public void WriteSoftWare(Product[] p, String path_soft) {
		try {
			BufferedWriter g = new BufferedWriter(new FileWriter(path_soft));
			String frase = "";

			for (int i = 0; i < p.length && p[i] != null; i++) {

				frase = frase + p[i].getPrice() + "*";
				frase = frase + p[i].getName() + "*";
				frase = frase + p[i].getStock() + "*";

				if (p[i] instanceof SoftwareProduct) {
					SoftwareProduct soft = (SoftwareProduct) p[i];
					frase = frase + soft.getSo();
					g.write(frase);
				}
				frase = "";
				g.newLine();
			}
			g.close();
		} catch (FileNotFoundException e) {
			System.out.println("L'arxiu d'entrada no existeix");
		} catch (IOException e) {
			System.out.println("S'ha produit un error en els arxius");
		}
	}

	/**
	 * Method to save configuration products in File.
	 * 
	 * @param product            : List of products where we have to save.
	 * @param path_configuration : File path where we are going to save products.
	 */
	public void WriteConfigurationProduct(Product[] product, String path_configuration) {
		HardwareProduct obj_hard = null;
		SoftwareProduct obj_soft = null;

		try {
			BufferedWriter g = new BufferedWriter(new FileWriter(path_configuration));
			String frase = "";
			for (int i = 0; i < product.length && product[i] != null; i++) {
				frase = frase + product[i].getPrice() + "*";
				frase = frase + product[i].getName() + "*";
				frase = frase + product[i].getStock() + "*";
				Configuration conf = (Configuration) product[i];
				Product[] lista_obj = conf.getLlista();
				if (lista_obj != null) {
					for (int j = 0; j < conf.getnElem(); j++) {
						if (lista_obj[j] instanceof HardwareProduct) {
							obj_hard = (HardwareProduct) lista_obj[j];
							frase = frase + obj_hard.getPrice() + "*";
							frase = frase + obj_hard.getName() + "*";
							frase = frase + obj_hard.getStock() + "*";
							frase = frase + obj_hard.getHardware() + "*";
						} else if (lista_obj[j] instanceof SoftwareProduct) {
							obj_soft = (SoftwareProduct) lista_obj[j];
							frase = frase + obj_soft.getPrice() + "*";
							frase = frase + obj_soft.getName() + "*";
							frase = frase + obj_soft.getStock() + "*";
							frase = frase + obj_soft.getSo() + "*";
						}
					}
				}
				g.write(frase);
				frase = "";
				g.newLine();
			}
			g.close();
		} catch (FileNotFoundException e) {
			System.out.println("L'arxiu d'entrada no existeix");
		} catch (IOException e) {
			System.out.println("S'ha produit un error en els arxius");
		}
	}

	/**
	 * Method to read a configuration file, in each line we have one product.
	 * 
	 * @param path_configuration_file : Path where we have the Configuration file.
	 * @return Will return a list of product which we have read.
	 */
	public Product[] readConfigurationProductFile(String path_configuration_file) {
		int nElem = 0;
		Product[] list = new Product[100];
		Configuration config = null;
		HardwareProduct obj_hard = null;
		SoftwareProduct obj_soft = null;
		try {
			BufferedReader f = new BufferedReader(new FileReader(path_configuration_file));
			String frase;
			double price = 0;
			String name;
			int stock = 0;
			String type = "";
			frase = f.readLine();
			while (frase != null) {
				StringTokenizer st = new StringTokenizer(frase, "*");
				price = Double.parseDouble(st.nextToken());
				name = st.nextToken();
				stock = Integer.parseInt(st.nextToken());
				config = new Configuration(price, name, stock); // New create object of configuration type

				while (st.hasMoreTokens()) {

					price = Double.parseDouble(st.nextToken());
					name = st.nextToken();
					stock = Integer.parseInt(st.nextToken());
					type = st.nextToken();
					if (type.equals("WINDOWS") || type.equals("LINUX") || type.equals("macOS")) {
						if (type.equals("WINDOWS")) {
							SistemasOperatius Windows = SistemasOperatius.WINDOWS;
							obj_soft = new SoftwareProduct(price, name, stock, Windows);
							config.addProduct(obj_soft);
						} else if (type.equals("LINUX")) {
							SistemasOperatius Linux = SistemasOperatius.LINUX;
							obj_soft = new SoftwareProduct(price, name, stock, Linux);
							config.addProduct(obj_soft);
						} else {
							SistemasOperatius Mac = SistemasOperatius.macOS;
							obj_soft = new SoftwareProduct(price, name, stock, Mac);
							config.addProduct(obj_soft);
						}

					} else {
						switch (type) {
						case "CPU":
							HardwareType CPU = HardwareType.CPU;
							obj_hard = new HardwareProduct(price, name, stock, CPU);
							config.addProduct(obj_hard);
							break;
						case "MB":
							HardwareType MB = HardwareType.MB;
							obj_hard = new HardwareProduct(price, name, stock, MB);
							config.addProduct(obj_hard);
							break;
						case "HDD":
							HardwareType HDD = HardwareType.HDD;
							obj_hard = new HardwareProduct(price, name, stock, HDD);
							config.addProduct(obj_hard);
							break;
						case "RAM":
							HardwareType RAM = HardwareType.RAM;
							obj_hard = new HardwareProduct(price, name, stock, RAM);
							config.addProduct(obj_hard);
							break;
						case "GPU":
							HardwareType GPU = HardwareType.GPU;
							obj_hard = new HardwareProduct(price, name, stock, GPU);
							config.addProduct(obj_hard);
							break;
						case "PERIFERIC":
							HardwareType PRIFÈRIC = HardwareType.PERIFERIC;
							obj_hard = new HardwareProduct(price, name, stock, PRIFÈRIC);
							config.addProduct(obj_hard);
							break;
						}
					}
				}
				list[nElem] = config;
				nElem++;
				frase = f.readLine();
			}
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("L'arxiu d'entrada no existeix");
		} catch (IOException e) {
			System.out.println("S'ha produit un error en els arxius");
		}
		return list;
	}
}
