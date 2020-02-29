package avid;

/**
 * 
 * @author Catalin Adelin Iovan
 * @author Younes Kabiri
 *
 */
public class Avid_bestSolution {

	private static int filaInicio;
	private static int colInicio;
	private static int filaFinal;
	private static int colFinal;
	private static String a[][];
	private static String[] soluciones;
	private static int contadorSoluciones;
	private static int mugen;

	public Avid_bestSolution() {
	}

	public static int totalPuntos(String entrada, int total) {

		char plus = '+';
		char minus = '-';
		char multi = '*';
		char div = '/';

		char operation = entrada.charAt(0);

		int valor = Character.getNumericValue(entrada.charAt(1));

		if (operation == plus) {
			total = total + valor;
		}
		if (operation == div) {
			if (valor == 1) {
				total = total;
			} else
				total = total / valor;
		}
		if (operation == multi) {
			total = Math.multiplyExact(total, valor);
		}
		if (operation == minus) {
			total = total - valor;

		}

		return total;
	}

	public static String pozitie(int sus, int jos, int stanga, int dreapta, String sus2, String jos2, String stanga2,
			String dreapta2) {
		String pozitie = null;

		if (sus > jos && sus > stanga && sus > dreapta) {
			pozitie = sus2;
		}
		if (jos > sus && jos > stanga && jos > dreapta) {
			pozitie = jos2;
		}
		if (stanga > jos && stanga > sus && stanga > dreapta) {
			pozitie = stanga2;
		}
		if (dreapta > jos && dreapta > stanga && dreapta > sus) {
			pozitie = dreapta2;
		}

		return pozitie;

	}

	public static String pozitie2(int sus, int jos, int stanga, int dreapta, String sus2, String jos2, String stanga2,
			String dreapta2) {
		String pozitie2 = null;

		if (sus2 != "X,") {
			pozitie2 = sus2;
		}
		if (jos2 != "X,") {
			pozitie2 = jos2;
		}
		if (stanga2 != "X,") {
			pozitie2 = stanga2;
		}
		if (dreapta2 != "X,") {
			pozitie2 = dreapta2;
		}

		return pozitie2;

	}

	public static int totalTemporar(String entrada) {
		int total = 0;
		char plus = '+';
		char minus = '-';
		char multi = '*';
		char div = '/';
		char na = 'N';
		char x = 'X';

		char operation = entrada.charAt(0);

		int valor = Character.getNumericValue(entrada.charAt(1));

		if (operation == plus) {
			total = total + valor;
		}
		if (operation == div) {
			if (valor == 1) {
				total = total;
			} else
				total = total / valor;
		}
		if (operation == multi) {
			total = Math.multiplyExact(1, valor);
		}
		if (operation == minus) {
			total = total - valor;

		}
		if (operation == na || operation == x) {
			total = total - 10000000;
		}

		// NA OPERATION EXCEED AMMOUNT

		return total;
	}

	public static int solucionBackMulti(String entrada[][], int filaIniEntrada, int colIniEntrada, int filaInisal,
			int colInisal, int m, int n) {

		boolean trobat = false;
		int totalBucle = 0, fila, col;

		mugen = 0;
		String na = "NA,", vist = "X,";
		contadorSoluciones = 0;
		soluciones = new String[50];
		a = entrada;
		filaInicio = filaIniEntrada;
		colInicio = colIniEntrada;
		filaFinal = filaInisal;
		colFinal = colInisal;

		String inicio = (a[filaInicio][colInicio]);
		String sus = (a[filaInicio][colInicio]);
		String jos = (a[filaInicio][colInicio]);
		String stanga = (a[filaInicio][colInicio]);
		String dreapta = (a[filaInicio][colInicio]);
		fila = filaInicio;
		col = colInicio;

		while ((totalBucle >= 0) && (!trobat) && inicio != vist) {

			inicio = (a[fila][col]);
			soluciones[contadorSoluciones] = inicio;

			a[fila][col] = vist;
			totalBucle = totalPuntos(inicio, totalBucle);
			if (fila < m - 1)
				jos = (a[fila + 1][col]);
			else
				jos = na;
			if (fila > 0)
				sus = (a[fila - 1][col]);
			else
				sus = na;
			if (col > 0)
				stanga = (a[fila][col - 1]);
			else
				stanga = na;
			if (col < n - 1)
				dreapta = (a[fila][col + 1]);
			else
				dreapta = na;

			int temporarSUS = totalBucle + totalTemporar(sus);
			int temporarJOS = totalBucle + totalTemporar(jos);
			int temporarStanga = totalBucle + totalTemporar(stanga);
			int temporarDreapta = totalBucle + totalTemporar(dreapta);

			String inicio2 = pozitie(temporarSUS, temporarJOS, temporarStanga, temporarDreapta, sus, jos, stanga,
					dreapta);
			if (inicio2 == sus) {
				a[fila][col] = vist;
				fila = fila - 1;

			}
			if (inicio2 == jos) {
				a[fila][col] = vist;
				fila = fila + 1;

			}
			if (inicio2 == stanga) {
				a[fila][col] = vist;
				col = col - 1;

			}
			if (inicio2 == dreapta) {
				a[fila][col] = vist;
				col = col + 1;

			}

			System.out.println("\nValor elegido: " + inicio);
			System.out.println("Puntuacion: " + totalBucle);

			for (int i = 0; i < m; i++) {
				System.out.print("\n");
				for (int j = 0; j < n; j++)
					System.out.print(a[i][j] + "\t");
			}

			if (a[fila][col] == a[filaFinal][colFinal]) {
				trobat = true;
				mugen++;
				inicio = (a[fila][col]);
				soluciones[contadorSoluciones] = inicio;

				totalBucle = totalPuntos(inicio, totalBucle);
				System.out.println("\n" + a[fila][col]);
				System.out.println("\nEncontrado! \nPuntuacion total: " + totalBucle);
			}

			contadorSoluciones++;

		}
		if (trobat != true)
			System.out.println("Camino no encontrado");
/*
		for (int k = 0; k < soluciones.length; k++) {
			if (soluciones[k] != null)
				System.out.print("\t" + soluciones[k]);
		}*/

		return totalBucle;
	}

}
