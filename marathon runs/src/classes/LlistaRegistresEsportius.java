package classes;

public class LlistaRegistresEsportius {

	private int numeroExercicis;
	private Exercici[] llista;
	private int codigo;

	private double peso;
	public int length;

	/**
	 * Crea la lista de los exercicios
	 * 
	 * @param dim    tamany de la lista
	 * @param peso   peso de la persona
	 * @param llista lista de Exercici
	 */
	public LlistaRegistresEsportius(int dim, double peso, int codigo) {
		numeroExercicis = 0;
		llista = new Exercici[dim];
		this.peso=peso;
	}

	/**
	 * Metodo get del numero de exercicis
	 * 
	 * @return numeroExercicis
	 */

	public int getNumeroExercicis() {
		return numeroExercicis;
	}

	/**
	 * Metodo set del Numero de exercicis
	 */

	public void setNumeroExercicis(int numeroExercicis) {
		this.numeroExercicis = numeroExercicis;
	}

	/**
	 * Metdo get de la lista de exercici
	 * 
	 * @return llista
	 */

	public Exercici[] getLlista() {
		return llista;
	}

	/**
	 * Metodo set de la lista de exercici
	 */

	public void setLlista(Exercici[] llista) {
		this.llista = llista;
	}

	/**
	 * Metodo get del peso del candidato
	 * 
	 * @return peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Metodo set del peso de la persona
	 */

	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * Metodo get del codigo del exercici
	 * 
	 * @return codigo
	 */

	public int getCodigo() {
		return codigo;
	}

	/**
	 * Metodo set del codigo del exercici, un entero
	 */

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Metodo copia de lista de registres
	 */

	public LlistaRegistresEsportius copia() {
		return new LlistaRegistresEsportius(this.numeroExercicis, this.peso, this.codigo);
	}

	/**
	 * Metodo toString de la lista de exercicis
	 */
	public String toString() {
		return ("Numero elements:" + this.numeroExercicis + "Codigo lista:" + this.codigo + "Peso persona:" + this.peso
				+ "Lista:" + this.llista);
	}

	/**
	 * Metodo de anadir nuevo exercici
	 * 
	 * @param e
	 * @return comprobar
	 */
	public boolean afegirExercici(Exercici e) {
		boolean comprobar = false;
		if (numeroExercicis < llista.length) {
			if (!existeExercici(e)) {
				int pos = numeroExercicis;
				
				comprobar = true;
				llista[pos] = e.copia();
				numeroExercicis++;
			}

		} else
			comprobar = false;
		return comprobar;

	}

	/**
	 * Metodo que comprueba si un elemento existe antes de anadirlo
	 * 
	 * @param e
	 * @return encontrado
	 */
	public boolean existeExercici(Exercici e) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < numeroExercicis) {
			if (llista[i].esEsteExercici(e.getNom())) {
				encontrado = true;
			} else
				i++;
		}
		return encontrado;
	}
	
	public boolean existeExercicinom(String nom) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < numeroExercicis) {
			if (llista[i].getNom().equals(nom)) {
				encontrado = true;
			} else
				i++;
		}
		return encontrado;
	}

	/**
	 * Metodo extra que nos muestra los elementos de la lista
	 * 
	 */

	public Exercici mostrarElementos(int i) {
		if (i < numeroExercicis && llista[i] != null)
			return (llista[i].copia());
		return null;
	}

	/**
	 * Metodo que nos permite borrar un cantidad de elementos de nuestra lista de
	 * exercicios
	 * 
	 * @param num
	 */

	public void borrarExercici(int num) {
			int i=0;
			int j=numeroExercicis;
			if(num<=llista.length) {
		for (int posicion = 0; posicion < num; posicion++) {
			llista[posicion] = null;
			j--;
		}

		for (int numfinal = num; numfinal <numeroExercicis; numfinal++) {
			
			llista[i] = llista[numfinal];
			//llista[numfinal]=null;
			i++;
		}

		for (int pos2 = numeroExercicis-num; pos2 < llista.length; pos2++) {
			llista[pos2] = null;

		}
		numeroExercicis=j;

	}
	}

	/**
	 * Metodo que nos busca si un nombre de cursa que introducimos existe en nuestra
	 * lista
	 * 
	 * @param nom
	 */

	public Exercici buscarNom(String nom) {
		boolean trobat = false;
		int i = 0;

		while (i < numeroExercicis && !trobat && llista[i] != null) {
			if (llista[i].getNom().equals(nom) ) {
				trobat = true;
				return (llista[i].copia());
			} else
				trobat = false;
			i++;
		}
		return null;

	}

	/**
	 * Metodo que nos busca el maximo de calorias que se ha gastado en algun
	 * exercicio de la lista
	 * 
	 * @return a
	 * @param peso
	 */

	public Exercici buscarTopCalorias(double peso) {
		double max = 0;
		int i = 0;
		Exercici a = new Exercici();

		while (i < numeroExercicis && llista[i] != null) {
			if (llista[i].calories(peso) > max)  {
				max = llista[i].calories(peso);
				a = llista[i].copia();
			}
			i++;
		}
		return a;

	}

	/**
	 * Metodo de buscar los exercicios con valor superior a 4km/h
	 * 
	 * @return comprobar
	 * 
	 */

	public boolean buscarMitjana() {
		boolean comprobar = false;
		int i = 0;

		while (i < numeroExercicis && !comprobar && llista[i] != null) {
			if (llista[i].velocitatMedia() > 4) {
				comprobar = true;
			} else
				comprobar = false;

			i++;
		}
		return comprobar;
	}

	/**
	 * Metodo que nos confirma que exercicios de nuestra lista han sido
	 * satisfactorios
	 * 
	 * @return l
	 */

	public String[] exerciciSatisfactori() {

		int i = 0, j = 0;
		String l[] = new String[numeroExercicis];
		if (numeroExercicis > 0) {
			while (i < numeroExercicis && llista[i] != null) {
				if (llista[i].satisfactorio()) {
					l[j] = llista[i].getNom();
					j++;
				}
				i++;
			}
		}

		return l;
	}

	/**
	 * Metodo que pasado un nombre de un objeto nos permite comprobar si es
	 * equivalente con algun otro de la lista
	 * 
	 * @param nom
	 * @return equivalents
	 */

	public Exercici[] exerciciEquivalent(String nom) {
		int i = 0;
		int j = 0;

		Exercici[] equivalents = new Exercici[numeroExercicis];

		boolean trobat = false;
		Exercici a = new Exercici();
		Exercici exe = null;

		while (i < numeroExercicis && !trobat && llista[i] != null) {
			{
				if (llista[i].getNom().equals(nom)) {
					trobat = true;
					a = llista[i];

				}
				i++;
			}

			j= 0;
			while (j < numeroExercicis && llista[j] != null) {
				if (a.compararEx(llista[j]) == true) {
					equivalents[i] = llista[j];
				}
				j++;
			}

		}

		return equivalents;

	}

}