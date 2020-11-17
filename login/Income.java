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
	public Income(JFrame frame) {
		setupGUI(frame);
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
	
	public void setupGUI(JFrame frame) {
		
		this.rowSize = 1; //size = frame size by rows. Currently there is only the button, so size = 1 (row).
		
		//setup frame with layout
		this.frame = frame;
		layout = new SpringLayout();
		setLayout(layout);
		
		//add goal button
		addIncome = new JButton("Create a new Income");
		add(new JLabel(""));
		add(addIncome);
		
		
		//format labels, textfields, button
		SpringUtilities.makeCompactGrid(this,
		               this.rowSize, 2, //rows, cols
		               6, 6,        //initX, initY
		               6, 6);       //xPad, yPad
		
		//if View Income button is pressed
		addIncome.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create popup window that asks "What is the income amount?" and then "How often (days) do you recieve this income?"
				
				//*********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the income amount?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "How often(days) do you recieve this income?", null));
				
				setValue(value);
				setPeriod(days);
				
				//then add a thing to the GUI that says "Income 1: $X every Y days."
				newIncome();
				
			}
		});
		
		//add Income to the frame
		frame.setSize(325, 75);
		frame.add(this);
		frame.repaint();
		frame.validate();
				
		
		
		
	}

	public void newIncome() {
		this.removeAll();
		layout = new SpringLayout();
		setLayout(layout);
		
		//increment the frame size to add a new row to place the newly created Income.
		rowSize++;
		
		
		JLabel l = new JLabel("Income "+(rowSize-1)+":", JLabel.TRAILING);
		add(l);
		
		String newText = "$"+getValue()+" every "+getPeriod()+" days.";
		JTextField field = new JTextField(20);
		field.setText(newText);
		l.setLabelFor(field);
		field.setEditable(false);
		add(field);
		
		//*******below is super tedious and inefficient, needs to be reworked*******
		//BUT.. works for now
		//add income button
		addIncome = new JButton("Create a new Income");
		add(new JLabel(""));
		add(addIncome);
		
		//add income button listener
		addIncome.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create popup window that asks "What is the income amount?" and then "How often (days) do you recieve this income?"
				
				//*********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the income amount?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "How often(days) do you recieve this income?", null));
				
				setValue(value);
				setPeriod(days);
				
				//then add a thing to the GUI that says "Income 1: $X every Y days."
				newIncome();
				
			}
		});
		
		
		
		
		SpringUtilities.makeCompactGrid(this,
				             this.rowSize, 2, //rows, cols
				             6, 6,        //initX, initY
				             6, 6);       //xPad, yPad
		
		//resize frame based on # of incomes added
		this.frame.setSize(325, 75+this.rowSize);
		this.revalidate();
		this.repaint();
	}
	
	
	
	//public access method to set the period
	public void setPeriod(int period) {
		if (isValidPeriod(period)) {
			this.period = period;
		}
	}	
	
	//public access method to set the value of the income
	private void setValue(float value) {
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

