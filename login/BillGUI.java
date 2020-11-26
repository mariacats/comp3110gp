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
public class BillGUI extends JPanel{
	
	JFrame frame;
	SpringLayout layout;
	JButton addBill;
	JTextField billText;
	List<Bill> billList;
	Bill bill;
	MainMenu menu;
	JButton returnButton, removeButton;
	
	Customer customer;	//customer object
	int duePeriod, size;
	float amountDue;
	
	Customer user;
	
	//Overloaded constructor that takes JFrame as input
	public BillGUI(MainMenu menu, JFrame frame, Customer user) {
		
		this.menu = menu;
		this.frame = frame;
		this.user = user;
		
		billList = this.user.getBillList();
		this.size = this.billList.size();
		this.frame.add(this);
		
		setupGUI();	
	
	}
	
	public void setupGUI() {
		
		//Clear and remake GUI
		this.removeAll();
		layout = new SpringLayout();
		setLayout(layout);
		
		
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
		
		// Add remove button
		removeButton = new JButton("Remove a Bill");
		add(new JLabel(""));
		add(removeButton);
		
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
				size++;
				setupGUI();
				
			}
		});
		
		
		// if Remove button is pressed
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (size == 0) {
					JOptionPane.showMessageDialog(null, "There are no Bill to remove!");
				} else {
					int remove = Integer.parseInt(JOptionPane.showInputDialog(frame,
							"Enter the number of the Bill you wish to remove: ", null));
					removeBill(remove);
					setupGUI();
				}
			}
		});
		
		//if Return button is pressed
		returnButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenu();
			}
		});
		
		
		
		
		SpringUtilities.makeCompactGrid(this,
				             this.size+3, 2, //rows, cols
				             6, 6,        //initX, initY
				             6, 6);       //xPad, yPad
		
		//resize frame based on # of bills added (+2 is the 2 buttons being added to the frame)
		this.frame.setSize(325, 55*(this.size+3));
		this.revalidate();
		this.repaint();
	}
	
	private void removeBill(int remove) {
		billList.remove(remove-1);
		this.size--;
	}
	
	
	/* Returns to the Main Menu
	 * Takes no input
	 * Returns nothing
	 */
	private void returnToMenu() {
		this.frame.remove(this);
		menu.loadFrame(this.frame);
	}

	
}

