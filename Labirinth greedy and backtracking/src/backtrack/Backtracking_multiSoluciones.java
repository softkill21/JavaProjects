package backtrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Backtracking_multiSoluciones {

	public int counter = 0;
	public int historicoIndex=0;
	public int maxF, maxC;
	public int totalBucle = 0;
	public String inicio;
	public static int solucion = 0;
	public int filaFinal, colFinal;
	public static String[][] maze;
	public static String[][] maze_original;
	public static String[][][] soluciones;
	public int maxSoluciones=100;

	/**
	 * @return the solucion
	 */
	public static int getSolucion() {
		return solucion;
	}

	/**
	 * @param solucion the solucion to set
	 */
	public static void setSolucion(int solucion) {
		Backtracking_multiSoluciones.solucion = solucion;
	}

	public Backtracking_multiSoluciones(String[][] entrada, int m, int n) {
		
		soluciones=new String[maxSoluciones][m][n];
		maze_original=new String[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				maze_original[i][j]=entrada[i][j];
			}
		}
		
		maze = entrada;
		//maze_original = entrada;
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
		boolean envio = false;
		filaFinal = m;
		colFinal = n;

		counter++;
		String na = "NA,";
		//System.out.println(this.toString());
	

		totalBucle = totalPuntos(maze[x][y], totalBucle);

	//	System.out.println("Total bucle:" + totalBucle);
		//System.out.println("Valor inicio:" + inicio);


		
		while (totalBucle>0 && historicoIndex<100) {
			

			inicio = maze[x][y];
			

			/** Accept case - we found the exit **/
			if (maze[x][y] == maze[filaFinal][colFinal]) {
				copiarSolucion(maze,soluciones[historicoIndex]);
				mostrarSoluciones();
				solucion++;
				historicoIndex++;

				
				//return true;
			}
			/*una vez que esta seguro qeu se copia en el historico, 
			 * crear un nuevo metodo, de comprobar uno por uno entre el maze actual
			 * y el de las soluciones, cada posicion
			 * si no coinciden, es que la solucion es nueva y asi succesivamente
			 * hasta llegar al numero de soluciones propuesto
			 * 
			 * 
			 * comprobar siempre de que puedan existir
			 * y no tener un bucle infinito (block )
			 * 
			 */

			/** Reject case - we hit a wall or our path **/
			if (maze[x][y].equals(na) || maze[x][y] == "VIST" || maze[x][y] =="**") {

				return false;

			}

			/** Backtracking Step **/

			// Mark this location as part of our path
			maze[x][y] = "VIST";
	
			boolean result;	
			
			// Try to go Right
			if(y<maxC-1) {
			result = step(x, y+1,m,n);
			if (result) { return true;}
			}
			// Try to go Up
			if(x>0) {
			result = step(x-1, y,m,n);
			if (result) { return true;}
			}
			// Try to go Left
			if(y>0) {
			result = step(x, y-1,m,n);
			if (result) { return true;}		
			}
			
			if(x<maxF-1) {
			// Try to go Down
			result = step(x+1, y,m,n);
			if (result) { return true;}		
			}
			

			
			
			
			/** Deadend - this location can't be part of the solution **/

			// Unmark this location
			 maze[x][y] = "**";

			// Go back
			 
			

		}
		
		
		for(int i=0;i<historicoIndex;i++) {
			for(int j=0;j<soluciones[0].length;j++) {
				for(int k=0;k<soluciones[0][0].length;k++) {
					if(soluciones[i][j][k]==maze[j][k]) {
						return false;
						
					}
				}
			}
		}
		
		copiarSolucion(maze_original,maze);

		return false;
	}

	private void copiarSolucion(String[][] mazeVist, String[][] matriuSoluciones) {
		for(int i=0;i<mazeVist.length;i++) {
			for(int j=0;j<mazeVist[0].length;j++) {
				matriuSoluciones[i][j]=mazeVist[i][j];
			}
		}
		
		
		
	}
	
	private void mostrarSoluciones() {
		for(int i=0;i<historicoIndex;i++) {
			System.out.println("Soluciones"+i);

			System.out.println(toString(soluciones[i]));
			
		}
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
	
	public String toString(String alfa[][]) {
		String output = "";
		for (int x = 0; x < alfa.length; x++) {
			for (int y = 0; y < alfa[0].length; y++) {
				output += alfa[x][y] + "|";
			}
			output += "\n";
		}
		return output;
	}
	
	public boolean comprobarMatrices(String entrada[][], String matrixComprobar[][]) {
		boolean distinto=false;
		
		for(int i=0;i<entrada.length;i++) {
			for(int j=0;i<entrada[0].length;j++) {
				if(entrada[i][j]!=matrixComprobar[i][j])
					distinto=true;
			}
		}
		
		return distinto;
	}

}