/** 
 * Aplicacions POO utilitzant classes JAVA.
 * Treballem amb BigIntegers. Construim una calculadora que els pugui manipular.
 * 
 */
import java.util.*;
import java.math.*;

public class GranCalculadora {
	
	public static void mostraMenu() {
		System.out.println("\n\n\nCalculadora per a grans enters");
		System.out.println("\n\t1- Sumar");
		System.out.println("\t2- Restar");
		System.out.println("\t3- Multiplicar");
		System.out.println("\t4- Dividir");
		System.out.println("\t0- Sortir");
		System.out.println("\n\t\t\tIndica opcio: ");
	}

	public static void main(String[] args) {
		Scanner teclat=new Scanner(System.in);
		BigInteger x, y, z;
		char opcio;

		mostraMenu();
		opcio=teclat.nextLine().charAt(0);
		while (opcio!='0') {
			System.out.println("Indica els dos valors a manipular");
			x = teclat.nextBigInteger();
			y = teclat.nextBigInteger();
			String sl =teclat.nextLine(); // netegem el buffer, llegim el salt de linia despres del segon BigInteger
			switch (opcio) {
			case '1': z=x.add(y); break;
			case '2': y=y.negate(); z=x.add(y); break;
			case '3': z=x.multiply(y); break;
			case '4': z=x.divide(y); break;
			default: z=BigInteger.ZERO;
			}
			System.out.println("El resultat de l'operacio es "+z);
			mostraMenu();
			opcio=teclat.nextLine().charAt(0);
		}
		
		teclat.close();
	}

}
