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
	
	//Overloaded constructor that takes JFrame as input
	public BillList(JFrame frame) {
		
		billList = new ArrayList<Bill>();
		this.size = 0;
		setupGUI(frame);	
	
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
		
		//add Return button
		returnButton = new JButton("Return");
		add(new JLabel(""));
		add(returnButton);

		//creates the GUI Layout - adding buttons/textfields/etc.
		SpringUtilities.makeCompactGrid(this, 
				this.size+2, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		// if "Create new Bill" button is pressed
		addBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Retrieve the data inputs and add them to the Bill object
				bill = new Bill();
				
				// *********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the amount due for your bill?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "When, in days, is your bill due?", null));

				bill.setAmountDue(value);
				bill.setDuePeriod(days);
				
				//Add Bill object to the List
				billList.add(bill);

				// Remake GUI with new Bill
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
		
		//Clear and remake GUI
		this.removeAll();
		layout = new SpringLayout();
		setLayout(layout);
		
		//increment the frame size to add a new row to place the newly created Income.
		this.size++;
		
		//Loop to add add Bill objects in the List to the GUI
		for(int i=0; i < billList.size(); i++) {
			JLabel l = new JLabel("Bill "+(i+1)+":", JLabel.TRAILING);
			add(l);
			
			JTextField field = new JTextField(20);
			field.setText("$"+billList.get(i).getValue()+"0 is due in "+billList.get(i).getPeriod()+" days.");
			l.setLabelFor(field);
			field.setEditable(false);
			add(field);
		}
		
		//Add addBill button
		addBill = new JButton("Create a new Bill");
		add(new JLabel(""));
		add(addBill);
		
		//add return button
		returnButton = new JButton("Return");
		add(new JLabel(""));
		add(returnButton);
		
		
		
		//add addBill button listener
		addBill.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Retrieve the data inputs and add them to the Bill object
				bill = new Bill();
				
				// *********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the amount due for your bill?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "When, in days, is your bill due?", null));

				bill.setAmountDue(value);
				bill.setDuePeriod(days);
				
				//Add Bill object to the List
				billList.add(bill);

				// Remake GUI with new Bill
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
	
	/* Returns to the Main Menu
	 * Takes no input
	 * Returns nothing
	 */
	private void returnToMenu() {
		frame.remove(this);
		ci = new CustomerInfo(frame);
	}

	
}

