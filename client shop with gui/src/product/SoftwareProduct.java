package product;

import java.io.Serializable;
/**
 * SoftwareProduct class.
 * @author Adelin with Younes.
 *
 */
public class SoftwareProduct extends Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private SistemasOperatius sis;
	protected static int countSoft = 0;
	
	/**
	 * SoftwareProduct constructor.
	 * @param price : The price of configuration product.
	 * @param name  : The name of configuration product.
	 * @param stock : The stock value of configuration product.
	 * @param sis : The type of the Operative System  
	 */
	public SoftwareProduct(double price, String name, int stock, SistemasOperatius sis) {
		super(price, name, stock);
		this.sis = sis;
	}

	/**
	 * Enum class which have the differnts 
	 * @author Adelin and Younes.
	 *
	 */
	public enum SistemasOperatius {
		WINDOWS, LINUX, macOS,
	}

	/**
	 * Method to get the Operative System
	 * @return
	 */
	public SistemasOperatius getSo() {
		return sis;
	}

	/**
	 * Method to change the Operative System
	 * @param so : The new value.
	 */
	public void setSo(SistemasOperatius so) {
		this.sis = so;
	}

	/**
	 * Method to cloning a Product.
	 */
	@Override
	public Product copia() {
		SoftwareProduct s = new SoftwareProduct(super.price, super.name, super.stock, this.sis);
		s.setId(this.getId());
		return s;
	}

	/**
	 * Method toString.
	 */
	public String toString() {
		return "Id: " + this.id + "\tprecio: " + this.price + "\tnombre: " + this.name + "\t stock: " + this.stock
				+ "\tOS: " + this.sis;

	}

	/**
	 * Method to get Software index.
	 * @return
	 */
	public static int getCountSoft() {
		return countSoft;
	}

}
