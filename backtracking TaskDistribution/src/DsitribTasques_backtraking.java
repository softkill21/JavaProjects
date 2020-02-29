
public class DsitribTasques_backtraking {
	static int[][] costos= { {11, 17, 8, 16, 20},
							{9, 7, 12, 6, 15},
							{13, 16, 15, 12, 16},
							{21, 24, 17, 28, 26},
							{14, 10, 12, 11, 15}};
							
	/*
	static int[][] costos= { {94, 1, 54, 68},
							{74, 10, 88, 82},
							{62, 88, 8, 76},
							{11, 74, 81, 21}};
							*/
	
	/*static int[][] costos= { {16, 20, 18},
							{11, 15, 17},
							{17, 1, 20}};*/
	static int[] solucio, solMin;
	static int costMin, nElem;
	
	public static void main(String[] args) {
		nElem=costos.length;
		solucio=new int[nElem]; // a cada treballador quina tasca li assignem
		for (int i=0; i<nElem; i++)
			costMin+=costos[i][i];
		assignacio(0);
		mostrarSolucio();
	}

	private static void assignacio(int treb) {
		int costActual;
		
		solucio[treb]=0;
		while (solucio[treb]<nElem) {
			if (esFactible(treb)) {
				if (treb<nElem-1)
					assignacio(treb+1);
				else {
					costActual=sumaSolucio();
					if (costActual<costMin) {
						guardaSolucioActual();
						costMin=costActual;
					}
				}
			}
			solucio[treb]++;
		}
	}
	
	private static boolean esFactible(int treb) {
		// hem de comprovar que el contingut de la posició treb sigui diferent a les anteriors
		// les anteriors ja estan comprovades en crides anteriors
		boolean iguals=false;
		int i=0;
		
		while (!iguals && i<treb)
			if (solucio[i]==solucio[treb]) iguals=true;
			else i++;
		return !iguals;
		//TODO falta comprovar que amb l'assignacio que portem no ens passem del costMin que tenim fins al moment, del contrari no cal continuar
	}

	private static void guardaSolucioActual() {
		solMin=new int[nElem];
		for (int i=0; i<nElem; i++)
			solMin[i]=solucio[i];
	}

	private static int sumaSolucio() {
		int s=0;
		for (int i=0; i<solucio.length; i++)
			s=s+costos[i][solucio[i]];
		return s;
	}

	private static void mostrarSolucio() {
		for (int i=0; i<solucio.length; i++) {
			System.out.println("Treballador "+(i+1)+" se li assigna la tasca: "+(solMin[i]+1));
		}
	}
	
	
}
