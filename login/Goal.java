package customer;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.lang.Math;
import java.io.*;

public class Goal {
	
	double goalAmount;
	int goalDate;
	
	
	double saving;
	double goal;
	double rem;
	double month;
	double income;
	double bill;
	long ReqTime;
	
	
	public Goal() {
		saving = 1000.0;
		goal = 350.40;
		rem = saving - goal;
	}
	
	public Goal(double goal, double month, double income, double bill) {
		setGoal(goal);
		setMonth(month);
		this.income = income;
		this.bill = bill;
		setSav(income, bill);
		setRem();
		setTimeRequired();
		
	}
	
	
	public void setGoalAmount(double goalAmount) {
		this.goalAmount = goalAmount;
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
	
	
	
	
	
	
	public void setSav(double income, double bill) {
		
		this.saving = income - bill;
	}
	
	public void setGoal(double goal) {
		this.goal = goal;
	}
	
	public void setRem() {
		this.rem = getSav() - getGoal();
	}
	
	public void setMonth(double month) {
		this.month = month;
	}
	
	public void setTimeRequired() {
		this.ReqTime = Math.round(goal/month);
		
	}
	
	public double getSav() {	
		return saving;
	}
	
	public double getGoal() {
		
		return goal;
	}
	
	public double getRem() {
		return rem;
		
	}
	
	public double getMonth() {
		return month;
	}
	
	public long getTimeRequired() {
		return ReqTime;
	}
	
	public void display() {
		System.out.println("It will take you atleast"+ReqTime+" months" );
	}

}
