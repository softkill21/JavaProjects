package order;

import java.io.Serializable;

public class Date  implements Serializable {

	private static final long serialVersionUID = 1L;
		private int day;
	    private int month;
	    private int year;

	
	    //Constructor con par�metros
	    public Date(int day, int month, int year) {
	        this.day= day;
	        this.month= month;
	        this.year = year;
	    }

	    //setters y getters
	    public void setDay(int d) {
	        day = d;
	    }
	    public void setMontn(int m) {
	        month = m;
	    }
	    public void setYear(int a) {
	        year = a;
	    }
	    public int getDay() {
	        return day;
	    }
	    public int getMonth() {
	        return month;
	    }
	    public int getYear() {
	        return year;
	    }

		@Override
		public String toString() {
			return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
		}
}
