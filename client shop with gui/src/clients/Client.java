package clients;

import java.io.Serializable;

public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Dni;
	private String Email;
	private int Code_postal;
	
	public Client(String Dni,String Email,int Code_postal) 
	{
		this.Dni=Dni;
		this.Email=Email;
		this.Code_postal=Code_postal;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getCode_postal() {
		return Code_postal;
	}

	public void setCode_postal(int code_postal) {
		Code_postal = code_postal;
	}

	public String toString() {
		return "Clients [Dni=" + Dni + ", Email=" + Email + ", Code_postal=" + Code_postal + "]";
	}
	public Client copia() 
	{
		Client c = new Client(Dni,Email,Code_postal);
		return c;
	}
	
}
