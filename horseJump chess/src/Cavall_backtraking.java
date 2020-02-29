
public class Cavall_backtraking {
	static int[] movFila= {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] movCol= {2, 1, -1, -2, -2, -1, 1, 2};
	static int numSolucions=0;
	static boolean acabat=false;
	
	public static void main(String[] args) {
		int[][] solucio=new int[8][8];
		int fIni=0;
		int cIni=4;
		cavall(fIni, cIni, solucio, 1);
	}

	private static void cavall(int f, int c, int[][] solucio, int pas) {
		int nf, nc;
		int pos;
		solucio[f][c]=pas;
		if (pas<64) {
			pos=0;
			while ((pos<8)&&!acabat) {
				nf=f+movFila[pos];
				nc=c+movCol[pos];
				if (esFactible(solucio, nf, nc)) {	
					cavall(nf, nc,solucio, pas+1);
					//desfem la solucio quan tirem endarrera
					solucio[nf][nc]=0;
				}
				pos++;
			}
		} else {
			mostrarSolucio(solucio);
			if (numSolucions == 10 ) acabat=true;
		}
	}

	private static boolean esFactible(int[][] solucio, int f, int c) {
		// sera factible si la casella existeix i hi ha un 0, encara no ha estat visitada
		if (f>=0 && f<solucio.length && c>=0 && c<solucio.length)
			return (solucio[f][c]==0);
		return false;
	}

	private static void mostrarSolucio( int[][] tauler) {
		System.out.println("Solucio "+ numSolucions);
		numSolucions++;
		for (int i=0; i<tauler.length; i++) {
			for (int j=0; j<tauler.length; j++) {
				if ((tauler[i][j]<10)) System.out.print(" ");
				System.out.print(tauler[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	
}
