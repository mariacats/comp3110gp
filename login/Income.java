
public class Income {
	
	Customer customer;	//customer object
	int period;
	double value;
	
	//default constructor that has some preset values, must take a customer object as input
	public Income(Customer customer) {
		this.customer = customer;
		period = 31;				//sets the default payment period to 31 days
		value = 1000;				//sets the default income value to 1000
		
	}
	
	/*overloaded constructor takes input
	Input: a customer object, an integer that denotes the period the income comes in (days), and value of income in dollars*/
	public Income(Customer customer, int period, double value ) {
		this.customer = customer;
		setPeriod(period);
		setValue(value);
	}


	//public access method to set the period
	public void setPeriod(int period) {
		if (isValidPeriod(period)) {
			this.period = period;
		}
	}	
	
	//public access method to set the value of the income
	public void setValue(double d) {
		if (isValidValue(d)) {
			this.value = (double) Math.round(d * 100.00) / (double) 100.00;
		}
		
	}

	//private predicate method returns true if period is within 0 to 365, false otherwise
	public boolean isValidPeriod(int period) {
		return (period > 0 && period < 366); 
	}
	
	//private predicate method returns true if value is above 0
	public boolean isValidValue(double value) {
		return (value > 0);
	}
	
	//public access method to get period
	public int getPeriod()
	{
		return period;
	}
	
	//public access method to get value
	public double getValue()
	{
		return value;
	}
	


}
