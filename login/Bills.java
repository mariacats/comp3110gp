
public class Bills {
	
	Customer customer;	//customer object
	int duePeriod;
	double amountDue;
	
	//default constructor that has some preset values,  must take a customer object as input
	public Bills(Customer customer) {
		this.customer = customer;
		duePeriod = 31;				    //sets the default due period to 31 days
		amountDue = 500;				//sets the default income value to 500
		
	}
	
	/*overloaded constructor takes input
	Input: a customer object, an integer that denotes the period the bill must be paid in days, and value of the amount due in dollars*/
	public Bills(Customer customer, int duePeriod, double amountDue) {
		this.customer = customer;
		setduePeriod(duePeriod);
		setamountDue(amountDue);
	}


	//public access method to set the due period
	public void setduePeriod(int duePeriod) {
		if (isValidPeriod(duePeriod)) {
			this.duePeriod = duePeriod;
		}
	}	
	
	//public access method to set the value of the the bill
	private void setamountDue(double amountDue) {
		if (isValidValue(amountDue)) {
			this.amountDue =(double) Math.round(amountDue * 100.00) / (double) 100.00;
		}
		
	}


	//private predicate method returns true if due period is within 0 to 365, false otherwise
	private boolean isValidPeriod(int period) {
		return (period > 0 && period < 366); 
	}
	
	//private predicate method returns true if value is above 0 
	private boolean isValidValue(double value) {
		return (value > 0);
	}
	
	//public access method to get due period
	public int getPeriod()
	{
		return duePeriod;
	}
	
	//public access method to get amount due
	public float getValue()
	{
		return (float) amountDue;
	}
	


	
}
