package classes;

import java.io.*;
import java.util.Scanner;

public class Main_Practica2 {

	/**
	 * Entrada por pantalla para peso y para el id del concursate
	 */
	static Scanner entrada = new Scanner(System.in);
	

	/**
	 * Metodo del menu que vamos a mostrar por pantalla para elegir las opciones
	 */
	public static void mostraMenu() {
		System.out.println("\n\nOpcions del menu:");
		System.out.println("\t0. Mostrar la lista de los exercicis");
		System.out.println("\t1. Afegir un nuevo exercicio a la lista de registros esportivos");
		System.out.println("\t2. Esborrar una cantidad de exercicios");
		System.out.println("\t3. Busca cursa por su nombre");
		System.out.println("\t4. Consultar cursa por el maximo de calorias quemadas");
		System.out.println("\t5. Comprobar si el usuari a fet almenys un exercici con mitjana superior a 4km/h");
		System.out.println("\t6. Consultas todos los exercicios satisfactorios");
		System.out.println("\t7. Consultas todos los exercicios equivalentes a uno pasado");
		System.out.println("\t8. Sortir");
		System.out.print("\n\t\t\tIndica opcio:\n");
	}
	
	/**
	 * Metodo inicial 0, para mostrar todos los elementos que tenemos en la lista
	 * @param listaEx
	 */

	public static void opcio0(LlistaRegistresEsportius listaEx) {

		for (int i = 0; i < listaEx.getNumeroExercicis(); i++) {
			
			Exercici p;
			p = listaEx.mostrarElementos(i);
			System.out.println(p);
		}

	}
	/**
	 * Aqui llamamos al metodo de anadir 
	 * @param listaEx
	 */
	public static void opcio1(LlistaRegistresEsportius listaEx) {
		boolean comprobar;
		// 1. Afegir un nuevo exercicio a la lista de exercicios
		// System.out.print("\n\n\tIndica nombre ruta:\t");
		System.out.print("\n\n\tIndica nombre ruta:\t");
		String nomExercici = entrada.next();

		System.out.print("\n\n\tIndica distancia:\t");
		int distancia = entrada.nextInt();
		if (distancia < 0) {
			while (distancia < 0) {
				System.out.println("Vuelve a introducir una distancia");
				distancia = entrada.nextInt();
			}
		}

		System.out.print("\n\n\tIndica duracio:\t");
		int duracio = entrada.nextInt();
		if (duracio < 0) {
			while (duracio < 0) {
				System.out.println("Vuelve a introducir una duracion");
				duracio = entrada.nextInt();
			}
		}

		System.out.print("\n\n\tIndica alturaMax:\t");
		int alturaMax = entrada.nextInt();
		if (alturaMax < 0) {
			while (alturaMax < 0) {
				System.out.println("Vuelve a introducir una altura Maxima");
				alturaMax = entrada.nextInt();
			}
		}

		System.out.print("\n\n\tIndica alturaMin:\t");
		int alturaMin = entrada.nextInt();
		if (alturaMin < 0) {
			while (alturaMin < 0) {
				System.out.println("Vuelve a introducir una altura minima");
				alturaMin = entrada.nextInt();
			}
		}

		Exercici ex = new Exercici(nomExercici, distancia, duracio, alturaMax, alturaMin);

		comprobar = listaEx.afegirExercici(ex);

		if (comprobar == true) {
			System.out.println("El elemento se ha anadido correctamente a la lista");
		} else
			System.out.println("El elemento no se ha podido anadir a la lista");
	}
	/**
	 * Aqui llamamos al metodo de borrar
	 * @param listaEx
	 */
	public static void opcio2(LlistaRegistresEsportius listaEx) {
		
		int i=0;
		System.out.print("\n\n\tIndica cantidad de elementos para borrar:\t");
		int cantidad = entrada.nextInt();
	
		
		
		listaEx.borrarExercici(cantidad);
		
		

	}
	/**
	 * Aqui llamamos al metodo de buscar por el parametre nom para devolver un objeto
	 * @param listaEx
	 */
	public static void opcio3(LlistaRegistresEsportius listaEx) {
		System.out.print("\n\n\tIndica nombre de la cursa:\t");
		boolean opcio3=false;
		
		String nomExercici = entrada.next();
		
		opcio3=listaEx.existeExercicinom(nomExercici);
		
		if(opcio3==true) {
		listaEx.buscarNom(nomExercici);
		
		Exercici p;
		p = listaEx.buscarNom(nomExercici);
		
		System.out.println(p);
		}
		else
			System.out.println("Exercici con ese nombre no encontrado");
	}
	/**
	 * Metodo para buscar el maximo de calorias quemadas en toda la lista de exercicios
	 * @param listaEx
	 */

	public static void opcio4(LlistaRegistresEsportius listaEx) {

		Exercici p;
		p = listaEx.buscarTopCalorias(listaEx.getPeso());
		System.out.print(
				"Nombre cursa: " + p.getNom() + "\tDistancia: " + p.getDistancia() + "\tDuracio: " + p.getDuracio());

	}

	
	/**
	 * Llamamos al metodo para comprobar si algun exercicio de nuestra lista ha superado 4km/h
	 * @param listaEx
	 */
	public static void opcio5(LlistaRegistresEsportius listaEx) {

		boolean p;
		p = listaEx.buscarMitjana();
		if (p == true) {
			System.out.println("El usuari ha fet almenos un exercici superior a 4km/h");
		} else

			System.out.println("El usuari no ha fet almenos un exercici superior a 4km/h");
	}
	
	/**
	 * Llamada al metood de buscar si dos exercicios han sido satisfactorios
	 * @param listaEx
	 */

	public static void opcio6(LlistaRegistresEsportius listaEx) {

		int i = 0;
		String[] listaConNoms = listaEx.exerciciSatisfactori();

		if (listaConNoms.length == 0)
			System.out.println("\tNo hi ha cap element que satisface esta condicion.");
		else {
			while (i < listaConNoms.length && listaConNoms[i] != null) {
				System.out.println("Exercici satisfactori:" + listaConNoms[i].toString());
				i++;
			}
		}
	}

	/**
	 * Devolvemos una lista de objetos en caso de que han habido elementos equivalentes al nombre que hemos introducido
	 * @param listaEx
	 */
	public static void opcio7(LlistaRegistresEsportius listaEx) {
		int i = 0;

		System.out.println("Introduzca el nom para comprobar erquivalents");
		String nom = entrada.next();
		Exercici[] exer = listaEx.exerciciEquivalent(nom);

		if (exer.length == 0) {
			System.out.println("Vacio, cap exercici equivalent");
		} else

			System.out.println(exer.toString());

	}
	/**
	 * Inicio programa principal
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		
		boolean  bpeso=false;
		 double peso=0;
		 
		while(!bpeso) {
			System.out.println("Introduzca el peso del concursante\n");
			 peso = entrada.nextDouble();
			if(peso<0) {
				System.out.println("Peso incorecto, tiene que ser un valor positivo\n");
				peso = entrada.nextDouble();
			}
			else
				bpeso=true;
		}
		
		int idConcursante=0;
		bpeso=false;
		while(!bpeso) {
			System.out.println("Introduzca el id del concursante\n");
			idConcursante = entrada.nextInt();
			if(idConcursante<0) {
				System.out.println("Peso incorecto, tiene que ser un valor positivo\n");
				idConcursante = entrada.nextInt();
			}
			else
				bpeso=true;
		}
		
		
		
		System.out.println("Introduzca el numero de lineas para leer entre 0 y 11");
		int numLinies = entrada.nextInt();
		if (numLinies < 0 || numLinies > 11) {
			while (numLinies < 0 || numLinies > 11) {
				System.out.println("Vuelve a introducir el numero de lineas");
				numLinies = entrada.nextInt();
			}
		}
		String[] linies = llegirLiniesFitxer(numLinies);

		var separador = ",";
		String nomExercici = "";
		int maxAltitut = 0, minAltitut = 0, duracio = 0;
		double distancia = 0.0;

		LlistaRegistresEsportius listaRE = new LlistaRegistresEsportius(15, peso, idConcursante);

		for (int i = 0; i < numLinies; i++) {

			String linia = linies[i];
			var lin = linia.split(separador);
			nomExercici = lin[0];
			distancia = Double.parseDouble(lin[1]);
			duracio = Integer.valueOf(lin[2]);
			maxAltitut = Integer.valueOf(lin[3]);
			minAltitut = Integer.valueOf(lin[4]);

			Exercici Exe = new Exercici(nomExercici, duracio, distancia, maxAltitut, minAltitut);
			listaRE.afegirExercici(Exe);
		}

		mostraMenu();
		int opcion = entrada.nextInt();
		while (opcion != 8) {
			switch (opcion) {
			case 0:
				opcio0(listaRE);
				break;
			case 1:
				opcio1(listaRE);
				break;
			case 2:
				opcio2(listaRE);
				break;
			case 3:
				opcio3(listaRE);
				break;
			case 4:
				opcio4(listaRE);
				break;
			case 5:
				opcio5(listaRE);
				break;
			case 6:
				opcio6(listaRE);
				break;
			case 7:
				opcio7(listaRE);
				break;
			}

			mostraMenu();
			opcion = entrada.nextInt();
			;
		}
	}
	/**
	 * Metodo para leer los datos del fichero
	 * @param numLin
	 * @return
	 * @throws FileNotFoundException
	 */
	private static String[] llegirLiniesFitxer(int numLin) throws FileNotFoundException {
		String[] result;
		if (numLin < 0)
			numLin = 0;
		result = new String[numLin];
		Scanner f = new Scanner(new File("RunningTarragona.csv"));
		for (int i = 0; i < numLin; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		return result;
	}

}
