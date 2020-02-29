package client_menu;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import clients.LlistaClients;
public class posardni extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public posardni(LlistaClients dnic,String titol) 
	{
		super(titol);
		String dni = JOptionPane.showInputDialog("Introdueix DNI");
		
		while (dni == null || dni.equals("")||dnic.ExisteixClient(dni)==-1) 
		{
			JOptionPane.showMessageDialog(null, "ES Necessita un DNi Correcte!", "ERROR", JOptionPane.ERROR_MESSAGE);
			dni = JOptionPane.showInputDialog("Introdueix el DNI");
		}
		JOptionPane.showMessageDialog(null, "Hola "+dni+"!", "Benvingut/da", JOptionPane.INFORMATION_MESSAGE);
		
	}

}
