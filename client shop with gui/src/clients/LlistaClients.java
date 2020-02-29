package clients;
import java.util.*;
import java.io.*;

public class LlistaClients implements Serializable {

	private static final long serialVersionUID = 1L;
	private Client llistaClients[];
	private int numClients;
	private static Scanner read;
	public LlistaClients(int n) 
	{
		llistaClients=new Client[n];
		numClients=0;
	}
	public int ExisteixClient(String dni) 
	{
		int i=0;
		boolean existeix=false;
		int pos=-1;
		while(i<numClients&&!existeix) {
			if(llistaClients[i].getDni().equals(dni)) {
				existeix=true;
			}
			else {
				i++;
			}
		}
		if(existeix) {
			pos=i;
		}
		return pos;
	}
	public int AltaClient(Client c) 
	{
		int pos=-1;
		if(numClients<llistaClients.length) 
		{
			if(ExisteixClient(c.getDni())==-1) 
			{
				llistaClients[numClients]=c.copia();
				numClients++;
				pos=0;
			}
			else 
			{
				pos=1;
			}
		}
		return pos;
	}
	public int BaixaClient(String dni) 
	{
		int result=-1;
		int pos=ExisteixClient(dni);
		if(pos!=-1) 
		{
			for(int i=pos+1;i<numClients;i++) 
			{
				llistaClients[i-1]=llistaClients[i];
			}
			numClients--;
			result=0;
		}
		else 
		{
			result=1;
		}
		return result;
	}
	public Client MostratClient(String dni) 
	{
		int p=ExisteixClient(dni);
		Client c = null;
		if(p!=-1) 
		{
			c=llistaClients[p].copia();
		}
		return c;
	}

	public Client[] Mostrartots() {
		return this.llistaClients;
	}
	public void ReadFile(File fclients) throws FileNotFoundException{
		read=new Scanner(fclients);
		String dni;
		String correu;
		int code;
		read.useDelimiter(",");
		read.useLocale(Locale.ENGLISH);
		Client f;
		while(read.hasNext()) 
		{
			dni=read.next();
			correu=read.next();
			code=Integer.parseInt(read.next());
			f= new Client(dni,correu,code);
			AltaClient(f);
		}
		read.close();
		
	}
	public void WriterFile(File fclients) throws IOException 
	{
		try 
		{
			int i=0;
			BufferedWriter f; 
			f= new BufferedWriter(new FileWriter(fclients));
			while(i<this.numClients) 
			{
				f.write(this.llistaClients[i].getDni()+",");
				f.write(this.llistaClients[i].getEmail()+",");
				f.write(this.llistaClients[i].getCode_postal()+",");
				f.newLine();
				i++;
			}
			f.close();
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("El fitxer no existeix");
		}
	}
	public int getNumClients() 
	{
		return this.numClients;
	}
}
