package classes;

/**
 * 
 * @author Catalin Adelin Iovan Esta clase se encarga de definir una serie de
 *         metodos para cada ejercicio 
 */
public class Exercici {

	/** LISTADO DE ATRIBUTOS: */
	private String nomExercici;
	private int alturaMax, alturaMin, duracio;
	private double distancia;
	static private int humbral = 14;

	/**
	 * CONSTRUCTOR DE LA CLASE :
	 * 
	 * @param nomExercici_t : Nombre de la cursa
	 * @param distancia_t   : Distancia del ejercicio.
	 * @param duracio_t     : Duración del ejercicio.
	 * @param maxAltitut_t  : Altura maxima de la cursa.
	 * @param minAltitut_t  : Altura minima de la cursa.
	 * 
	 */
	public Exercici(String nomExercici_t, int duracio_t, double distancia_t, int alturaMax_t, int alturaMin_t) {
		nomExercici = nomExercici_t;
		distancia = distancia_t;
		alturaMax = alturaMax_t;
		alturaMin = alturaMin_t;
		duracio = duracio_t;
	}

	public Exercici() {
		nomExercici = "";
		distancia = 0;
		alturaMax = 0;
		alturaMin = 0;
		duracio = 0;
	}

	/**
	 * METODOS GETTERS DE LOS ATRIBUTOS DE LA CLASE :
	 */
	/**
	 * GET Nombre de la cursa
	 * 
	 * @return nomExercici
	 */
	public String getNom() {
		return nomExercici;
	}

	/**
	 * GET Altura maxima
	 * 
	 * @return alturaMax
	 */
	public int getMaxAltitut() {
		return alturaMax;
	}

	/**
	 * GET Altura minima
	 * 
	 * @return alturaMin
	 */
	public int getMinAltitut() {
		return alturaMin;
	}

	/**
	 * GET Duracion
	 * 
	 * @return duracio
	 */
	public int getDuracio() {
		return duracio;
	}

	/**
	 * GET Distancia
	 * 
	 * @return distancia
	 */
	public double getDistancia() {
		return distancia;
	}

	/**
	 * GET Satisfactori valor
	 * 
	 * @return humbral
	 */
	public static int getHumbral() {
		return humbral;
	}

	/**
	 * SET nomExercici
	 * 
	 * @param nomExercici: Nuevo nombre del identificador.
	 */
	public void setNom(String nomExercici) {
		this.nomExercici = nomExercici;
	}

	/**
	 * METODO CÓPIA : Crea una cópia del objeto.
	 * 
	 * @return Retorna un objeto de la clase.
	 */
	public Exercici copia() {
		return new Exercici(this.nomExercici, this.duracio, this.distancia, this.alturaMax, this.alturaMin);
	}

	/**
	 * METODOS TOSTRING:
	 * 
	 * @return Retorna un String con todos los atributos del objeto.
	 */
	public String toString() {
		return "Nom exercici:" + this.nomExercici + ", " + "duracio:" + this.duracio + ", " + " Distancia:"
				+ this.distancia + ", " + "Altura max:" + this.alturaMax + " Altura min:" + this.alturaMin;
	}

	/**
	 * METODOS VELOCIDAD MEDIA: Calcula la velocidad media del ejercicio.
	 * 
	 * @return velocitat
	 */
	public double velocitatMedia() {

		double velocitat = 0;
		double duracioHora = 0;

		duracioHora = this.duracio / 60.0;
		velocitat = this.distancia / duracioHora;

		return velocitat;
	}

	/**
	 * METODOS PROMEDIO PASOS: Calcula el promedio de pasos por minuto del ejercicio
	 * y devuelve un valor entero.
	 * 
	 * @return pasos.
	 */
	public int mitjanaPasos() {
		double pasos = 0.0;
		pasos = (this.distancia * 1000) / 0.8;
		pasos = pasos / this.duracio;
		pasos = Math.round(pasos);

		return (int) pasos;
	}

	/**
	 * MÉTODO CALORÍAS: Calcula las calorías quemadas según el peso del corredor.
	 * 
	 * @param pes : Peso del corredor.
	 * @return calories: El numero de calorias quemadas.
	 */

	public double calories(double pes) {

		double calories = 0;
		double velocitat = 0;

		velocitat = velocitatMedia();

		if (velocitat <= 6.0) {
			calories = pes * this.distancia * 0.73;
		} else if (velocitat > 6.0 && velocitat <= 12.0) {
			calories = pes * this.distancia * 1.03;
		} else {
			calories = pes * this.distancia * 1.25;
		}

		return calories;
	}

	/**
	 * MÉTODO RITMO: Calcula el rimo de la cursa.
	 * 
	 * @return ritme: Valor del ritmo calculado.
	 */

	public double ritme() {

		double ritme = 0;
		ritme = (double) this.duracio / this.distancia;
		return ritme;
	}

	/**
	 * MÉTODO SATISFACTORIO: Comprueba si un ejercicio ha estado satisfactorio.
	 * 
	 * @return satisfactorio: true o false depende de si ha sido satisfactorio el
	 *         ejercicio.
	 */

	public boolean satisfactorio() {

		boolean satisfactorio = false;
		double ritme = 0.0;

		ritme = ritme();

		if (ritme < humbral) {
			satisfactorio = true;
		} else {
			satisfactorio = false;
		}

		return satisfactorio;
	}

	/**
	 * MÉTODO COMPARA EQUIVALENCIA: Compara dos ejercicios si son equivalentes.
	 * 
	 * @param exe2: Pasamos un Objeto de Clase sesion
	 * @return equivalent: retorna un boleano true o false según si los ejercicios
	 *         son equivalentes.
	 */

	public boolean compararEx(Exercici exe2) {

		boolean equivalent = false;
		int difAlt1 = 0;
		int difAlt2 = 0;

		difAlt1 = this.alturaMax - this.alturaMin;
		difAlt2 = exe2.getMaxAltitut() - exe2.getMinAltitut();

		if ((this.distancia == exe2.getDistancia()) && (difAlt1 == difAlt2)) {
			equivalent = true;
		}
		return equivalent;
	}

	/**
	 * MÉTODO COMPARA MAYOR DIFERENCIA DE ALTITUD: Compara dos ejercicios según su
	 * diferencia de altitud.
	 * 
	 * @param exe2: Pasamos un Objeto de Clase sesion
	 * @return mayor: retorna el objeto con difrencia de altitud mayor, en caso de
	 *         ser iguales devuelve NULL.
	 */

	public Exercici mayorEx(Exercici exe2) {

		int difExe1 = 0;
		int difExe2 = 0;
		Exercici mayor;

		difExe1 = this.alturaMax - this.alturaMin;
		difExe2 = exe2.getMaxAltitut() - exe2.getMinAltitut();

		if (difExe1 > difExe2) {
			mayor = new Exercici(this.nomExercici, this.duracio, this.distancia, this.alturaMax, this.alturaMin);

		} else if (difExe1 < difExe2) {

			mayor = exe2.copia();
		} else {
			mayor = null;
		}

		return mayor;
	}

	/**
	 * MÉTODO LLIDAR MINIMO: Nos permite cambiar el valor del llindar minimo.
	 * 
	 * @param llindar: pasamos el nuevo valor del llindar y nos comprueba que esté
	 *                 dentro del rango [5-20].
	 */

	public void llindarMin(int llindar) {

		if (llindar <= 20 && llindar >= 5) {
			humbral = llindar;
		}

	}

	public boolean esEsteExercici(String n) {
		return (nomExercici.equals(n));

	}

}
