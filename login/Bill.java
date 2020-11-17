package customer;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;

//NEEDS ADJUSTMENT
//NEEDS BUTTON TO RETURN TO MAIN MENU
//NEEDS CUSTOMER OBJECT SAVING AND LOADING
//ADD A TOTAL / MONTH CALCULATION THING
//ADD OPTION TO REMOVE/COMPLETE/EDIT A BILL
public class Bill extends JPanel{
	
	JFrame frame;
	SpringLayout layout;
	JButton addBill;
	JTextField billText;
	
	Customer customer;	//customer object
	int duePeriod, rowSize;
	float amountDue;
	
	//default constructor that has some preset values,  must take a customer object as input
	public Bill(JFrame frame) {
		setupGUI(frame);
		
		
		//this.customer = customer;
		duePeriod = 31;				    //sets the default due period to 31 days
		amountDue = 500;				//sets the default income value to 500
		
	}
	
	/*overloaded constructor takes input
	Input: a customer object, an integer that denotes the period the bill must be paid in days, and value of the amount due in dollars*/
	public Bill(Customer customer, int duePeriod, float amountDue) {
		this.customer = customer;
		setDuePeriod(duePeriod);
		setAmountDue(amountDue);
	}

	public void setupGUI(JFrame frame) {

		this.rowSize = 1; // size = frame size by rows. Currently there is only the button, so size = 1 row

		// setup frame with layout
		this.frame = frame;
		layout = new SpringLayout();
		setLayout(layout);

		// add goal button
		addBill = new JButton("Create a new Bill");
		add(new JLabel(""));
		add(addBill);

		// format labels, textfields, button
		SpringUtilities.makeCompactGrid(this, 
				this.rowSize, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		// if View Income button is pressed
		addBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create popup window that asks "What is the income amount?" and then "How
				// often (days) do you recieve this income?"

				// *********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the amount due for your bill?", null));
				int days = Integer.parseInt(
						JOptionPane.showInputDialog(frame, "When, in days, is your bill due?", null));

				setAmountDue(value);
				setDuePeriod(days);

				// then add a thing to the GUI that says "Income 1: $X every Y days."
				newBill();

			}
		});
		
		//add Income to the frame
		frame.setSize(325, 75);
		frame.add(this);
		frame.repaint();
		frame.validate();	
	}
	
	public void newBill() {
		this.removeAll();
		layout = new SpringLayout();
		setLayout(layout);
		
		//increment the frame size to add a new row to place the newly created Income.
		rowSize++;
		
		
		JLabel l = new JLabel("Bill "+(rowSize-1)+":", JLabel.TRAILING);
		add(l);
		
		String newText = "$"+getValue()+" is due in "+getPeriod()+" days.";
		JTextField field = new JTextField(20);
		field.setText(newText);
		l.setLabelFor(field);
		field.setEditable(false);
		add(field);
		
		//*******below is super tedious and inefficient, needs to be reworked*******
		//BUT.. works for now
		//add income button
		addBill = new JButton("Create a new Bill");
		add(new JLabel(""));
		add(addBill);
		
		//add income button listener
		addBill.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create popup window that asks "What is the income amount?" and then "How often (days) do you recieve this income?"
				
				//*********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the income amount?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "How often(days) do you recieve this income?", null));
				
				setAmountDue(value);
				setDuePeriod(days);
				
				//then add a thing to the GUI that says "Income 1: $X every Y days."
				newBill();
				
			}
		});
		
		
		
		
		SpringUtilities.makeCompactGrid(this,
				             this.rowSize, 2, //rows, cols
				             6, 6,        //initX, initY
				             6, 6);       //xPad, yPad
		
		//resize frame based on # of incomes added
		this.frame.setSize(325, 75*this.rowSize);
		this.revalidate();
		this.repaint();
	}
	
	
	//public access method to set the due period
	public void setDuePeriod(int duePeriod) {
		if (isValidPeriod(duePeriod)) {
			this.duePeriod = duePeriod;
		}
	}	
	
	//public access method to set the value of the the bill
	private void setAmountDue(float amountDue) {
		if (isValidValue(amountDue)) {
			this.amountDue = amountDue;
		}
		
	}

	//private predicate method returns true if due period is within 0 to 365, false otherwise
	private boolean isValidPeriod(int period) {
		return (period > 0 && period < 366); 
	}
	
	//private predicate method returns true if value is above 0 and below 2,147,483,648 (2,147,483,647 is largest int)
	private boolean isValidValue(float value) {
		return (value > 0 && value <= 2147483647);
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

