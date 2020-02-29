package avid;
/**
 * Clase para mostrar los errors.
 * @author Adelin y Younes
 *
 */
public class Error  extends Exception{
	/**
	 * Constructor de la clase Error.
	 * @param valor : Contexto de excepción
	 */
	public Error(String valor) {
		super(valor);
	}
}
