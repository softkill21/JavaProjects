package tests;
import java.util.Scanner;
import clients.Client;
import clients.LlistaClients;
import java.io.*;
public class TestClients {
static Scanner teclat = new Scanner (System.in);

	public static void main(String[] args) throws IOException 
	{
		LlistaClients A = new LlistaClients(10);
		
		/*Prova afegegir client*/
		int opcio;
		menu();
		opcio=teclat.nextInt();
		while(opcio!=6) 
		{
			switch(opcio) 
			{
				case 1:if(Afegir(A)==0) 
						{
							System.out.println("El client s'ha ageit correctament");
						}
						else if(Afegir(A)==1) 
						{
							System.out.println("El client ja existeix");
						}
				break;
				case 2:Consultar(A);
				break;
				case 3:if(Eliminar(A)==0) 
						{
							System.out.println("S'ha eliminat correctament");
						}
						else if(Eliminar(A)==1) 
						{
							System.out.println("El client no existeix");
						}
				break;
				case 4:DadesFitxer(A);
				break;
				case 5:GuardarFitxer(A);
				break;
			}
			menu();
			opcio=teclat.nextInt();
		}
	}
	private static void GuardarFitxer(LlistaClients A) throws IOException
	{
		File f = new File("Store.txt");
		A.WriterFile(f);
	}
	private static void DadesFitxer(LlistaClients A) throws FileNotFoundException 
	{	
		File f=new File("Clients.txt");
		A.ReadFile(f);
	}
	private static void menu() 
	{
		System.out.println("1-Afegir client");
		System.out.println("2-Consultar client");
		System.out.println("3-Suprimir client");
		System.out.println("4-Llegir de fitxer");
		System.out.println("5-Guardar dades al  fitxer");
	}
	private static int Afegir(LlistaClients A) 
	{
		System.out.println("Entra el dni");
		String dni=teclat.next();
		System.out.println("Entra el correu");
		String correu=teclat.next();
		Client P=null;
		int code=0;
		try 
		{
			System.out.println("Entra el codi postal");
			code=teclat.nextInt();
		}
		catch(NegativeArraySizeException e) 
		{
			System.out.println("Error:codi postal te que ser positiu");
			System.out.println("Entra el codi postal");
			code=teclat.nextInt();
		}
		catch(NumberFormatException e)
		{
			System.out.println("Error:codi postal ha de ser numeric");
			System.out.println("Entra el codi postal");
			code=teclat.nextInt();
		}
		P= new Client(dni,correu,code);
		return A.AltaClient(P);
	}
	private static  void Consultar(LlistaClients A) 
	{
		System.out.println("Entra el dni");
		String dni=teclat.next();
		if(A.MostratClient(dni)!=null) 
		{
			System.out.println("El client es:");
			System.out.println(A.MostratClient(dni));
		}
		else 
		{
			System.out.println("El client no existeix");
		}
		
	}
	private static int Eliminar(LlistaClients A) 
	{
		System.out.println("Entra el dni");
		String dni=teclat.next();
		return A.BaixaClient(dni);
	}
}


