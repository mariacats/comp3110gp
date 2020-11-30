package customer;

import javax.swing.*; 
import java.awt.*;  
import java.awt.event.*;

public class Bill {
	
	JFrame frame;
	SpringLayout layout;
	JButton addBill;
	JTextField billText;
	
	
	Customer customer;	//customer object
	int duePeriod, rowSize;
	double amountDue;
	
	//default constructor that has some preset values,  must take a customer object as input
	public Bill() {
		
		//this.customer = customer;
		duePeriod = 31;				    //sets the default due period to 31 days
		amountDue = 500;				//sets the default income value to 500
		
	}
	
	/*overloaded constructor takes input
	Input: a customer object, an integer that denotes the period the bill must be paid in days, and value of the amount due in dollars*/
	public Bill(Customer customer, int duePeriod, double amountDue) {
		this.customer = customer;
		setDuePeriod(duePeriod);
		setAmountDue(amountDue);
	}
	
	//public access method to set the due period
	public void setDuePeriod(int duePeriod) {
		if (isValidPeriod(duePeriod)) {
			this.duePeriod = duePeriod;
		}
	}	
	
	//public access method to set the value of the the bill
	public void setAmountDue(double amountDue) {
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
	public double getValue()
	{
		return (double) amountDue;
	}
	


	
}

