package product;

import java.io.Serializable;
import java.util.Objects;

/**
 * Product class which extend of Product.
 * 
 * @author Adelin with Younes
 *
 */
public abstract class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	protected static int cont = 0;
	protected int id;
	protected double price;
	protected String name;
	protected int stock;

	/**
	 * Product constructor.
	 * 
	 * @param price : The name of configuration product.
	 * @param name  : The name of configuration product.
	 * @param stock : The stock value of configuration product.
	 */
	public Product(double price, String name, int stock) {
		this.id = cont;
		cont++;
		this.price = price;
		this.name = name;
		this.stock = stock;
	}

	/**
	 * Method to get the product price.
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Method to get the product name.
	 * 
	 * @return
	 */
	public String getName() {
		return name;

	}

	/**
	 * Method to get the product stock.
	 * 
	 * @return
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Method to change the price.
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Method to change the stock.
	 * 
	 * @param stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * Method to subtract the stock value.
	 */
	public void substStock() {
		this.stock--;

	}

	/**
	 * Method to change the product name.
	 * 
	 * @param name : New value name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the product id.
	 * 
	 * @return Will return the value of the id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method to change the product id.
	 * 
	 * @param id : New value to change.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method to compare two objects even if different classes.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		// modifico per saber si instancia producte encara que sigui de una subclasse
		if (!(this instanceof Product)) {
			return false;
		}

		final Product other = (Product) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		return true;
	}

	/**
	 * Method to cloning a Product.
	 * 
	 * @return
	 */
	public abstract Product copia();

	/**
	 * Method toString.
	 * 
	 * @return will return String which have all information about the product.
	 */
	public String toString() {
		return "Id: " + this.id + "\tprecio: " + this.price + "\tnombre: " + this.name + "\t stock: " + this.stock;
	}

}
