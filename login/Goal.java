package customer;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.lang.Math;
import java.io.*;

public class Goal {
	
	double goalAmount;
	int goalDate;
	Customer customer;
	

	
	
	public Goal() {
		goalDate = 31;
		goalAmount = 500;
		
	}
	
	public Goal(Customer customer, double goalAmount, int goalDate) {
		this.customer = customer;
		
		setGoalAmount(goalAmount);
		setGoalDate(goalDate);
	}
	
	
	public void setGoalAmount(double goalAmount) {
		if (isValidValue(goalAmount)) {
			this.goalAmount = (double) Math.round(goalAmount * 100.00) / (double) 100.00;
		}
	}
	
	public double getGoalAmount() {
		return this.goalAmount;
	}
	
	public void setGoalDate(int goalDate) {
		this.goalDate = goalDate;
	}
	
	public int getGoalDate() {
		return this.goalDate;
	}
	
	//private predicate method returns true if value is above 0 
	private boolean isValidValue(double value) {
		return (value > 0);
	}
	
	


}
