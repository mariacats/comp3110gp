package customer;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;

//NEEDS ADJUSTMENT
//NEEDS BUTTON TO RETURN TO MAIN MENU
//NEEDS CUSTOMER OBJECT SAVING AND LOADING
//ADD A TOTAL / MONTH CALCULATION THING
//ADD OPTION TO REMOVE/EDIT AN INCOME
public class Income extends JPanel{
	
	JFrame frame;
	SpringLayout layout;
	Customer customer;	//customer object
	int period, rowSize;
	float value;
	
	JButton addIncome;
	JTextField incText;
	
	//default constructor that has some preset values, must take a customer object as input
	public Income() {
		//this.customer = customer;
		period = 31;				//sets the default payment period to 31 days
		value = 1000;				//sets the default income value to 1000
		
	}
	
	/*overloaded constructor takes input
	Input: a customer object, an integer that denotes the period the income comes in (days), and value of income in dollars*/
	public Income(Customer customer, int period, float value ) {
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
	public void setValue(float value) {
		if (isValidValue(value)) {
			this.value = value;
		}
		
	}

	//private predicate method returns true if period is within 0 to 365, false otherwise
	private boolean isValidPeriod(int period) {
		return (period > 0 && period < 366); 
	}
	
	//private predicate method returns true if value is above 0 and below 2,147,483,648 (2,147,483,647 is largest int)
	private boolean isValidValue(float value) {
		return (value > 0 && value <= 2147483647);
	}
	
	//public access method to get period
	public int getPeriod()
	{
		return period;
	}
	
	//public access method to get value
	public float getValue()
	{
		return (float) value;
	}
	


}

