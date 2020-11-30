package customer;

import java.awt.*; 
import java.awt.event.*;

import javax.swing.*;
public class MainMenu extends JPanel{
	
	IncomeGUI income;
	BillGUI bill;
	GoalGUI goal;
	
	private Customer user;
	
	SpringLayout layout;
	JFrame frame;
	Database db;
	private String name, address, phoneNumber;
	private double balance;
	private JTextField nText, aText, pnText, balText; //incText, billText, gText;
	//private JPasswordField ccText;
	private JButton editButton, incButton, billButton, gButton;
	private int id;
	
	MainMenu(JFrame frame, Database db, int id){
		this.db = db;
		this.id = id;
		
		//user = current Customer
		this.user = this.db.customerList.get(this.id);
		
		loadFrame(frame);
	}
	
	
	
	//placeholder until i create a better menu/return system
	public void loadFrame(JFrame frame){
		this.frame = frame;
		//resize frame to fit contents
		frame.setSize(325, 375);
		
		//labels for textfields
		String[] labels = {"Name: ", "Address: ", "Phone Number: ", "Balance: "};
		
		this.removeAll();
		layout = new SpringLayout();
		setLayout(layout);
		
		
		//create label and textfield instances and add them to panel
		for(int i=0; i<labels.length; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			add(l);
			switch(i) {
				case 0: nText = new JTextField(10);
						l.setLabelFor(nText);
						nText.setEditable(false);
						nText.setText(this.user.getName());
						add(nText);
						break;

				case 1: aText = new JTextField(10);
						l.setLabelFor(aText);
						aText.setEditable(false);
						aText.setText(this.user.getAddress());
						add(aText);
						break;
	
				case 2: pnText = new JTextField(10);
						l.setLabelFor(pnText);
						pnText.setEditable(false);
						pnText.setText(this.user.getPhoneNumber());
						add(pnText);
						break;

				case 3: balText = new JTextField(10);
						l.setLabelFor(balText);
						balText.setEditable(false);
						balText.setText(""+this.user.getBalance());
						add(balText);
				
				default: break;

			}
		}
		
		//add button to finalize/collect data entered
		editButton = new JButton("Edit Information");
		add(new JLabel(""));
		add(editButton);
		
		//add income button
		incButton = new JButton("View Income");
		add(new JLabel(""));
		add(incButton);

		//add goal button
		billButton = new JButton("View Bills");
		add(new JLabel(""));
		add(billButton);
		
		//add goal button
		gButton = new JButton("View Goals");
		add(new JLabel(""));
		add(gButton);

		
		//format labels, textfields, button
		SpringUtilities.makeCompactGrid(this,
                 labels.length+4, 2, //rows, cols
                 6, 6,        //initX, initY
                 6, 6);       //xPad, yPad
		
		//button to edit/save information
		editButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if button clicked said "Edit Information", allow textfields to be edited and change Button text to "Save"
				if(editButton.getText().compareTo("Edit Information") == 0) {
					nText.setEditable(true);
					aText.setEditable(true);
					pnText.setEditable(true);
					balText.setEditable(true);
					editButton.setText("Save");
				}
				//if button clicked said "Save", make textfields uneditable, change Button text to "Edit...", 
				//and call updateInformation() to save info to Customer
				else {
					setName(nText.getText());
					setAddress(aText.getText());
					setPhoneNumber(pnText.getText());
					if(validTextToDouble() == false)
						return;
					setBalance(Double.valueOf(balText.getText()));
					nText.setEditable(false);
					aText.setEditable(false);
					pnText.setEditable(false);
					balText.setEditable(false);
					editButton.setText("Edit Information");
					updateInformation();
				}
			}
		});
		
		//if View Income button is pressed
		incButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openIncome();
			}
		});
		
		//if View Bills button is pressed
		billButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBill();
			}
		});
		
		//if View Goals button is pressed
		gButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openGoal();
			}
		});
		
		
		//add CustomerInfo to frame
		frame.add(this);
		frame.repaint();
		frame.validate();
		
		
	}
	
	/* Function to close Login panel and open IncomeGUI panel
	 * No input
	 * Returns nothing
	 */
	public void openIncome() {
		frame.remove(this);
		income = new IncomeGUI(this, frame, user);
	}
	
	/* Function to close Login panel and open BillGUI panel
	 * No input
	 * Returns nothing
	 */
	public void openBill() {
		frame.remove(this);
		bill = new BillGUI(this, frame, user);
	}
	
	/* Function to close Login panel and open GoalGUI panel
	 * No input
	 * Returns nothing
	 */
	public void openGoal() {
		frame.remove(this);
		goal = new GoalGUI(this, frame, user);
	}
	
	
	public boolean validTextToDouble() {
		try {
			Double.valueOf(balText.getText());
        } catch (NumberFormatException exception) {
        	JOptionPane.showMessageDialog(this, "Please enter a valid balance number.");
        	balText.setText("");
        	return false;
        }
		return true;
	}
	
	// Function to save the input information in the Database for the Customer
	public void updateInformation() {
		this.db.customerList.get(this.id).setName(getName());
		this.db.customerList.get(this.id).setAddress(getAddress());
		this.db.customerList.get(this.id).setPhoneNumber(getPhoneNumber());
		this.db.customerList.get(this.id).setBalance(getBalance());
	}
	
	//setter function for name
	public void setName(String s) {
		this.name = s;
	}
	
	//getter function for name
	public String getName() {
		return this.name;
	}
	
	//setter function for address
	public void setAddress(String s) {
		this.address = s;
	}
	
	//getter function for address
	public String getAddress() {
		return this.address;
	}
	
	//setter function for phoneNumber
	public void setPhoneNumber(String s) {
		this.phoneNumber = s;
	}
		
	//getter function for confirmPassword
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	//setter function for balance
	public void setBalance(double f) {
		this.balance = f;
	}
			
	//getter function for balance
	public double getBalance() {
		return this.balance;
	}

}