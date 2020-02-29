package backtrack;
/**
* @author Catalin Adelin Iovan
* @author Younes Kabiri
*/
public class Backtracking_unaSolucion {

	public int counter = 0;
	public int historicoIndex = 0;
	public int maxF, maxC;
	public int totalBucle = 0;
	public String inicio;
	public static int solucion = 0;
	public int filaFinal, colFinal;
	public static String[][] maze;
	public static String[][] maze_original;
	public static String[][][] soluciones;

	/**
	 * @return the solucion
	 */
	public int getSolucion() {
		return solucion;
	}

	/**
	 * @param solucion the solucion to set
	 */
	public static void setSolucion(int solucion) {
		Backtracking_unaSolucion.solucion = solucion;
	}

	public Backtracking_unaSolucion(String[][] entrada, int m, int n) {

		maze_original = new String[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				maze_original[i][j] = entrada[i][j];
			}
		}

		maze = entrada;
		// maze_original = entrada;
		maxF = m;
		maxC = n;

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
				total = 1;
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

	// Get the start location (x,y) and try to solve the maze

	// Backtracking method
	public boolean step(int x, int y, int m, int n) {
		filaFinal = m;
		colFinal = n;

		counter++;
		String na = "NA,";
		System.out.println(this.toString());

		inicio = maze[x][y];

		totalBucle = totalPuntos(maze[x][y], totalBucle);

		System.out.println("Total bucle:" + totalBucle);
		System.out.println("Valor inicio:" + inicio);

		if (totalBucle > 0) {

			/** Accept case - we found the exit **/
			if (maze[x][y] == maze[filaFinal][colFinal]) {
				solucion++;
				return true;
			}

			/** Reject case - we hit a wall or our path **/
			if (maze[x][y].equals(na) || maze[x][y] == "VIST" || maze[x][y] == "**") {

				return false;

			}

			/** Backtracking Step **/

			// Mark this location as part of our path
			maze[x][y] = "VIST";

			boolean result;

			// Try to go Right
			if (y < maxC - 1) {
				result = step(x, y + 1, m, n);
				if (result) {
					return true;
				}
			}
			// Try to go Up
			if (x > 0) {
				result = step(x - 1, y, m, n);
				if (result) {
					return true;
				}
			}
			// Try to go Left
			if (y > 0) {
				result = step(x, y - 1, m, n);
				if (result) {
					return true;
				}
			}

			if (x < maxF - 1) {
				// Try to go Down
				result = step(x + 1, y, m, n);
				if (result)
					return true;
			}

			/** Deadend - this location can't be part of the solution **/

			// Unmark this location
			maze[x][y] = "**";
		}

		else return false;
		
		
		return false;
	}

	public String toString() {
		String output = "";
		for (int x = 0; x < maxF; x++) {
			for (int y = 0; y < maxC; y++) {
				output += maze[x][y] + "|";
			}
			output += "\n";
		}
		return output;
	}

}