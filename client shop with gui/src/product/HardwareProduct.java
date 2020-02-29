package product;

import java.io.Serializable;

/**
 * HardeareProduct class.
 * 
 * @author Adelin with Younes.
 *
 */
public class HardwareProduct extends Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private HardwareType hardware;
	protected static int countHard = 0;

	/**
	 * HardwareProduct Constructor.
	 * 
	 * @param price    : The price of configuration product.
	 * @param name     : The name of configuration product.
	 * @param stock    : The stock value of configuration product.
	 * @param hardware : The type of hardware which the object have.
	 */
	public HardwareProduct(double price, String name, int stock, HardwareType hardware) {
		super(price, name, stock);
		this.hardware = hardware;
	}

	/**
	 * The enum for indicate HardwareType.
	 * 
	 * @author Adelin and Younes
	 *
	 */
	public enum HardwareType {
		CPU, MB, HDD, RAM, GPU, PERIFERIC
	}

	/**
	 * Method to get HardwareType.
	 * 
	 * @return Will return HardwareType.
	 */
	public HardwareType getHardware() {
		return this.hardware;
	}

	/**
	 * Method to change the HardwareType.
	 * 
	 * @param ht : New value of HardwareType.
	 */
	public void setSo(HardwareType ht) {
		this.hardware = ht;
	}

	/**
	 * Method to cloning a Product which extends of Product class.
	 */
	@Override
	public Product copia() {
		HardwareProduct h = new HardwareProduct(super.price, super.name, super.stock, hardware);
		h.setId(this.id);
		return h;
	}

	/**
	 * Method toString.
	 */
	public String toString() {
		return "Id: " + this.id + "\tprecio: " + this.price + "\tnombre: " + this.name + "\t stock: " + this.stock
				+ "\tHardware: " + this.hardware;
	}

	public static int getCountHard() {
		return countHard;
	}

}
