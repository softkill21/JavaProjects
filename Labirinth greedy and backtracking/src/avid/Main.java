package avid;

/**
 * @author Catalin Adelin Iovan
 * @author Younes Kabiri
 */
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import backtrack.Backtracking_multiSoluciones;
import backtrack.Backtracking_unaSolucion;

public class Main {

	private static Scanner entrada;

	public static void main(String[] args) throws FileNotFoundException {
		LeerDatos datos = new LeerDatos();
		Backtracking_multiSoluciones back_multi;
		Backtracking_unaSolucion back_unic;
		long tini, tfin;

		entrada = new Scanner(System.in);
		boolean salir = false;
		int opcion = 0;

		datos.imprimir_cabecera();
		datos.imprimir_lista();

		while (!salir) {

			try {
				menu();
				opcion = entrada.nextInt();
				if (opcion < 0 || opcion > 3)
					throw new Error("Opción fuera de rando");
				if (opcion >= 0 && opcion <= 3) {
					if (opcion == 0)
						salir = true;
					else {
						switch (opcion) {
						case 1:
							tini = System.nanoTime();

							Avid_bestSolution.solucionBackMulti(datos.a, datos.filaInicio, datos.colInicio,
									datos.filaFinal, datos.colFinal, datos.m, datos.n);

							tfin = System.nanoTime();

							System.out.println("Temps " + (tfin - tini) + " nanosegundos");
							break;
						case 2:
							tini = System.nanoTime();
							back_unic = new Backtracking_unaSolucion(datos.a, datos.m, datos.n);
							back_unic.step(datos.filaInicio, datos.colInicio, datos.filaFinal, datos.colFinal);
							tfin = System.nanoTime();

							System.out.println("Temps " + (tfin - tini) + " nanosegundos");
							break;
						case 3:
							tini = System.nanoTime();
							back_multi = new Backtracking_multiSoluciones(datos.a, datos.m, datos.n);
							back_multi.step(datos.filaInicio, datos.colInicio, datos.filaFinal, datos.colFinal);

							System.out.println("Total llamadas: " + back_multi.counter);
							System.out.println("Total soluciónes: " + back_multi.getSolucion());
							tfin = System.nanoTime();

							System.out.println("Temps " + (tfin - tini) + " nanosegundos");
							break;
						default:
							break;
						}
					}
				}
			} catch (Error e) {
				System.out.println(e.toString());
			} catch (InputMismatchException e) {
				System.out.println(e.toString());
				entrada.next();

			}
		}
	}

	public static void menu() {
		System.out.println("\n\n************************************");
		System.out.println("*** 1. Avid                   ******");
		System.out.println("*** Backtracking              ******");
		System.out.println("***       2. Una solución   ******");
		System.out.println("***       3. Todas soluciónes   ****");
		System.out.println("*** 0. Salir");
		System.out.println("************************************");
		System.out.println("\nOpción: ");
	}
}
