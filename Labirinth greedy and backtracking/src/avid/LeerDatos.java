package avid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Clase para leer los datos de un fichero.
  *@author Catalin Adelin Iovan
 * @author Younes Kabiri
 *
 */
public class LeerDatos {

	public int m;
	public int n;
	public int filaInicio;
	public int colInicio;
	public int filaFinal;
	public int colFinal;
	public String[][] a = new String[m][n];
	public String[][] b = new String[m][n];
	
	/**
	 * 
	 * Método principal para hacer la lectura.
	 */
	public LeerDatos() {
		try (

			Scanner input = new Scanner(new File("backUnoSimple.txt"))) {
			String text = input.nextLine();
			String[] strArray = text.split(",");

			m = Integer.parseInt(strArray[0]);
			n = Integer.parseInt(strArray[1]);
			filaInicio = Integer.parseInt(strArray[2]);
			colInicio = Integer.parseInt(strArray[3]);
			filaFinal = Integer.parseInt(strArray[4]);
			colFinal = Integer.parseInt(strArray[5]);

			a = new String[m][n];
			b = new String[m][n];
			while (input.hasNext()) {
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++)
						a[i][j] = b[i][j] = input.next();
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("El archivo de entrada no existe");
		}
	}

	/**
	 * Getters i Setter de la clase
	 * @return
	 */
	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}

	public int getFilaInicio() {
		return filaInicio;
	}

	public int getColInicio() {
		return colInicio;
	}

	public int getFilaFinal() {
		return filaFinal;
	}
	public int getColFinal() {
		return colFinal;
	}

	public String[][] getA() {
		return a;
	}
	public String[][] getB() {
		return b;
	}
	/**
	 * Método para imprimir el laberinto.
	 */
	public void imprimir_lista() {
		System.out.println("\n\n\n\t\t\tLa matriz ordenada de entrada es :");
		for (int i = 0; i < m; i++) {
			System.out.print("\n\t\t\t");
			for (int j = 0; j < n; j++)
				System.out.print(a[i][j]+" ");
		}
		System.out.println("\n");

	}
	
	/**
	 * Método para imprimir la cabecera con los datos.
	 */
	public void imprimir_cabecera() {
		System.out.println("\t\t*********************************************************");
		System.out.print("\t\t***  Tamaño fila: [" + m + " ]\t\t columna: [" + n + "]         ***\n\t\t***  Posición inicio fila [" + filaInicio
				+ "] \t inicio columna: [" + colInicio + "]  *** \n\t\t***  Posición fila final: [" + filaFinal + "] \t columna final: ["
				+ colFinal + "]   ***\n");
		System.out.println("\t\t*********************************************************");
	}
}
