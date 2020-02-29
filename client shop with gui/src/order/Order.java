package order;

import java.io.Serializable;
import java.util.Arrays;
import clients.Client;
import product.Product;

public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public enum Estate {SENT,CANCELED,ORDERED}

	private Product [] listP= new Product [100];
	private double priceTotal;
	private Estate est;
	private Client client;
	private String id;
	@SuppressWarnings("unused")
	private int idInt;
	private static int count=0;
	private Date date;
	
	
	
public Order (Product [] listP,Estate x, Client c, Date d) {
	this.priceTotal=0;
	for(int i=0;i<listP.length;i++) {
		this.listP[i]=listP[i];
			
	}
		
	for(int i=0;i<listP.length&& listP[i]!=null;i++) {
		this.priceTotal=this.priceTotal+listP[i].getPrice();
	}
		this.est=x;
		this.client=c;
		this.id=c.getDni()+Order.count;
		this.date=d;
		count++;
	}

public Order (double priceT,String id) {
	
	this.priceTotal=priceT;
	this.id=id;
	count++;
}



//G&S////////////////////////////////////////

public Product[] getListP() {
	return listP;
}



public void setListP(Product[] listP) {
	this.listP = listP;
}



public double getPriceTotal() {
	return priceTotal;
}



public void setPriceTotal(double priceTotal) {
	this.priceTotal = priceTotal;
}



public Client getClient() {
	return client;
}



public void setClient(Client client) {
	this.client = client;
}



public Estate getEst() {
	return est;
}



public void setEst(Estate est) {
	this.est = est;
}



public String getId() {
	return id;
}



public void setId(String id) {
	this.id = id;
}

public void setIdInt(int id) {
	this.idInt = id;
}


public static int getCount() {
	return count;
}




public Date getDate() {
	return date;
}



public void setDate(Date date) {
	this.date = date;
}


//COPY/////////////////////////////////////////////////////

public Order copy() {
	
	Order copy= new Order (this.listP,this.est,this.client,this.date);
	copy.setId(this.id);
		
return(copy);

}


//TOSTRING///////////////////////////////////////////////
@Override
public String toString() {
	return "Order [listP=" + Arrays.toString(listP) + ", priceTotal=" + priceTotal + ", est=" + est + ", client="
			+ client + ", id=" + id + ", date=" + date + "]";
}



}
