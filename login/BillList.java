package customer;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

//NEEDS ADJUSTMENT
//NEEDS BUTTON TO RETURN TO MAIN MENU
//NEEDS CUSTOMER OBJECT SAVING AND LOADING
//ADD A TOTAL / MONTH CALCULATION THING
//ADD OPTION TO REMOVE/COMPLETE/EDIT A BILL
public class BillList extends JPanel{
	
	JFrame frame;
	SpringLayout layout;
	JButton addBill;
	JTextField billText;
	List<Bill> billList;
	Bill bill;
	CustomerInfo ci;
	JButton returnButton;
	
	Customer customer;	//customer object
	int duePeriod, size;
	float amountDue;
	
	//default constructor that has some preset values,  must take a customer object as input
	public BillList(JFrame frame) {
		billList = new ArrayList<Bill>();
		this.size = 0;
		setupGUI(frame);
		
		
		//this.customer = customer;
		//duePeriod = 31;				    //sets the default due period to 31 days
		//amountDue = 500;				//sets the default income value to 500
		
	}

	public void setupGUI(JFrame frame) {

		// setup frame with layout
		this.frame = frame;
		layout = new SpringLayout();
		setLayout(layout);

		// add addBill button
		addBill = new JButton("Create a new Bill");
		add(new JLabel(""));
		add(addBill);
		
		//add return button
		returnButton = new JButton("Return");
		add(new JLabel(""));
		add(returnButton);

		// format labels, textfields, button
		SpringUtilities.makeCompactGrid(this, 
				this.size+2, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		// if Add Bill button is pressed
		addBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bill = new Bill();
				
				// *********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the amount due for your bill?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "When, in days, is your bill due?", null));

				bill.setAmountDue(value);
				bill.setDuePeriod(days);
				
				billList.add(bill);

				// then add a thing to the GUI that says "Income 1: $X every Y days."
				newBill();

			}
		});
		
		//if Return button is pressed
		returnButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenu();
			}
		});
		
		//add Bill to the frame
		frame.setSize(325, 110);
		frame.add(this);
		frame.repaint();
		frame.validate();	
	}
	
	public void newBill() {
		this.removeAll();
		layout = new SpringLayout();
		setLayout(layout);
		
		//increment the frame size to add a new row to place the newly created Income.
		this.size++;
		
		for(int i=0; i < billList.size(); i++) {
			JLabel l = new JLabel("Bill "+(i+1)+":", JLabel.TRAILING);
			add(l);
			
			JTextField field = new JTextField(20);
			field.setText("$"+billList.get(i).getValue()+"0 is due in "+billList.get(i).getPeriod()+" days.");
			l.setLabelFor(field);
			field.setEditable(false);
			add(field);
		}
		
		//*******below is super tedious and inefficient, needs to be reworked*******
		//BUT.. works for now
		//add addBill button
		addBill = new JButton("Create a new Bill");
		add(new JLabel(""));
		add(addBill);
		
		//add return button
		returnButton = new JButton("Return");
		add(new JLabel(""));
		add(returnButton);
		
		
		
		//add income button listener
		addBill.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bill = new Bill();
				
				// *********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the amount due for your bill?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "When, in days, is your bill due?", null));

				bill.setAmountDue(value);
				bill.setDuePeriod(days);
				
				billList.add(bill);

				// then add a thing to the GUI that says "Income 1: $X every Y days."
				newBill();
				
			}
		});
		
		//if Return button is pressed
		returnButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenu();
			}
		});
		
		
		
		
		SpringUtilities.makeCompactGrid(this,
				             this.size+2, 2, //rows, cols
				             6, 6,        //initX, initY
				             6, 6);       //xPad, yPad
		
		//resize frame based on # of bills added
		this.frame.setSize(325, 55*(this.size+2));
		this.revalidate();
		this.repaint();
	}
	
	private void returnToMenu() {
		frame.remove(this);
		ci = new CustomerInfo(frame);
	}

	
}

