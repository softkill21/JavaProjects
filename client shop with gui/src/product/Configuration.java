package product;

import java.io.Serializable;

import admin_shop.Error;
/**
 * Configuration class which extend of Product. 
 * @author Adelin with Younes.
 *
 */
public class Configuration extends Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private Product[] components;
	private int nElem;
	private int mida;
	protected static int countConf = 0;

	/**
	 * The Configuration constructor.
	 * 
	 * @param price : The price of configuration product.
	 * @param name  : The name of configuration product.
	 * @param stock : The stock value of configuration product.
	 */
	public Configuration(double price, String name, int stock) {
		super(price, name, stock);
		nElem = 0;
		this.mida = 100;
		components = new Product[mida];
	}

	/**
	 * Method to get the list of configuration products.
	 * 
	 * @return Will return a list of Products.
	 */
	public Product[] getLlista() {
		return components;
	}

	/**
	 * Method to get all components of the list.
	 * 
	 * @return Will return a String which have the name of components of each
	 *         product.
	 */
	public String components() {
		String s = "";
		int i = 0;
		while (i < this.nElem && this.components[i] != null) {
			if (components[i] instanceof HardwareProduct) {
				HardwareProduct h = (HardwareProduct) components[i];
				s = s + "\t" + h.getHardware();
			} else if (components[i] instanceof SoftwareProduct) {
				SoftwareProduct sof = (SoftwareProduct) components[i];
				s = s + "\t" + sof.getSo();
			}
			i++;
		}
		return s;
	}

	/**
	 * Method for set the list Products.
	 * 
	 * @param llista : The new list.
	 */
	public void setLlista(Product[] llista) {
		this.components = llista;
	}

	/**
	 * Method for add a product to the configuration list.
	 * 
	 * @param e : New Product to add.
	 * @return Will return a number to indicate if the product was added of not.
	 */
	public int addProduct(Product pror) {

		int comprobar = 0;
		try {
			if (isEmpty()) {
				if (!existProduct(pror)) {
					components[nElem] = pror.copia();
					comprobar = 1;
					nElem++;
				} else
					comprobar = 0;

			} else
				comprobar = -1;

			if (comprobar == -1)
				throw new Error("Error...No hi ha espai en la llista de configuracions.");
			else if (comprobar == 0)
				throw new Error("Error...El producte ja existeix.");
		} catch (Error e) {
			System.out.println(e.toString());
		}

		return comprobar;

	}

	/**
	 * Method to delete a Product.
	 * 
	 * @param nomP : New product to add.
	 * @return Will return boolean variable to indicate if the product was added of
	 *         not.
	 */
	public boolean deleteProduct(Product nomP) {
		int borrado = 0;
		int pos_eliminat = 0;
		boolean b = false;
		try {
			if (existProduct(nomP)) {
				for (int i = 0; i < nElem; i++) {
					if (components[i].getId() == nomP.getId()) {
						components[i] = null;
						pos_eliminat = i;
						borrado = 1;
						this.nElem--;
					}
				}
				if (borrado == 1) {
					while (pos_eliminat < nElem) {
						components[pos_eliminat] = components[pos_eliminat + 1];
						pos_eliminat++;
					}
					b = true;
				}
			} else {
				b = false;
				borrado = -1;
			}

			if (borrado == -1)
				throw new Error("Error...El producte no ja existeix.");
		} catch (Error e) {
			System.out.println(e.toString());
		}
		return b;
	}

	/**
	 * Method to cloning a Product.
	 */
	public Configuration copia() {
		Configuration conf = new Configuration(super.price, super.name, super.stock);
		conf.setLlista(this.getLlista());
		conf.mida = this.mida;
		conf.nElem = this.nElem;
		return conf;
	}

	/**
	 * Method to check if the list is empty of not.
	 * 
	 * @return Will return boolean variable to indicate if we have more space for
	 *         add more products.
	 */
	public boolean isEmpty() {
		boolean empty = false;
		if (this.nElem >= 0 && nElem < this.mida)
			empty = true;

		return empty;
	}

	/**
	 * Method to check if exist some product.
	 * 
	 * @param e : Product object which we want no check.
	 * @return Will return boolean variable to indicate if the product exist of not.
	 */
	public boolean existProduct(Product e) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < nElem) {
			if (components[i].getId() == e.getId())
				encontrado = true;
			i++;
		}
		return encontrado;
	}

	/**
	 * Method which check if the configuration product have the minimum of components.
	 * @return Will return a boolean variable to indicate if the configuration product have the minimum components of not. 
	 */
	public boolean chekProduct() {
		int[] array = new int[7];
		/*
		 * array[0]=CPU, array[1]=MB, array[2]=HDD, array[3]=RAM, array[4]=GPU,
		 * array[5]=PRIFÈRIC array[6]=WINDOWS/LINUX/macOS,
		 */
		boolean checked = true;
		int i = 0;
		if (nElem > 0) {
			while (i < nElem) {
				if (this.components[i] instanceof HardwareProduct) {

					HardwareProduct hard = (HardwareProduct) this.components[i];
					Enum<?> s = hard.getHardware();
					switch (s.name()) {
					case "CPU":
						array[0] = array[0] + 1;
						break;
					case "MB":
						array[1] = array[1] + 1;
						break;
					case "HDD":
						array[2] = array[2] + 1;
						break;
					case "RAM":
						array[3] = array[3] + 1;
						break;
					case "GPU":
						array[4] = array[4] + 1;
						break;
					case "PERIFERIC":
						array[5] = array[5] + 1;
						break;
					}

				} else if (this.components[i] instanceof SoftwareProduct) {
					SoftwareProduct soft = (SoftwareProduct) this.components[i];
					String so = soft.getSo().name();

					if (so.equalsIgnoreCase("WINDOWS") || so.equalsIgnoreCase("LINUX")
							|| so.equalsIgnoreCase("macOS")) {
						array[6] = array[6] + 1;
					}
				}

				i++;
			}

			for (int j = 0; j < array.length - 1; j++) {
				if (array[j] < 1) {
					checked = false;
				}
			}
		}
		return checked;
	}

	/**
	 * Method which calculate the total price of list products.
	 * @return Will return the total price.
	 */
	public double totalPriceProduct() {
		double price = 0;
		price = price + this.getPrice();

		int i = 0;
		if (nElem > 0) {
			while (i < nElem) {
				if (this.components[i] instanceof HardwareProduct) {
					HardwareProduct hard = (HardwareProduct) this.components[i];
					price = price + hard.getPrice();
				} else if (this.components[i] instanceof SoftwareProduct) {
					SoftwareProduct soft = (SoftwareProduct) this.components[i];
					price = price + soft.getPrice();
				}

				i++;
			}
		}
		return (price * 0.9);
	}

	/**
	 * Method toString.
	 */
	public String toString() {
		String elements = "  Elements: [" + this.nElem + "]";
		for (int i = 0; i < nElem; i++) {
			elements = elements + "\n\t\t" + this.components[i].getPrice() + "\t\t" + this.components[i].getStock()
					+ "\t\t" + this.components[i].getName();
		}
		return "Id: " + this.id + "\tprecio: " + this.price + "\tnombre: " + this.name + "\t stock: " + this.stock
				+ " [ " + elements + " ]\n";
	}

	/**
	 * Method to get the list index value.
	 * 
	 * @return
	 */
	public int getnElem() {
		return nElem;
	}

	/**
	 * Method to change the list index value.
	 * 
	 * @param nElem : New value.
	 */
	public void setnElem(int nElem) {
		this.nElem = nElem;
	}

	public static int getCountConf() {
		return countConf;
	}
}
