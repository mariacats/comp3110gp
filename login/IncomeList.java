package customer;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

//NEEDS ADJUSTMENT
//NEEDS CUSTOMER OBJECT SAVING AND LOADING
//ADD A TOTAL / MONTH CALCULATION THING
//ADD OPTION TO REMOVE/EDIT AN INCOME
public class IncomeList extends JPanel{
	
	JFrame frame;
	SpringLayout layout;
	Customer customer;	//customer object
	int period, size;
	float value;
	
	JButton addIncome;
	JTextField incText;
	
	JButton returnButton;
	
	List<Income> incomeList;
	Income income;
	CustomerInfo ci;
	
	
	//Overloaded constructor that takes JFrame as input
	public IncomeList(JFrame frame) {
		
		//create new list of incomes
		incomeList = new ArrayList<Income>();
		this.size = 0;
		setupGUI(frame);

	}
	
	/* Function to setup the GUI
	 * Takes the main JFrame as input
	 * Returns nothing.
	 */
	public void setupGUI(JFrame frame) {
				
		//setup frame with layout
		this.frame = frame;
		layout = new SpringLayout();
		setLayout(layout);
		
		//add addIncome button
		addIncome = new JButton("Create a new Income");
		add(new JLabel(""));
		add(addIncome);
		
		//add return button
		returnButton = new JButton("Return");
		add(new JLabel(""));
		add(returnButton);
		
		
		//creates the GUI Layout - adding buttons/textfields/etc.
		SpringUtilities.makeCompactGrid(this,
		               this.size+2, 2, //rows, cols
		               6, 6,        //initX, initY
		               6, 6);       //xPad, yPad
		
		//if "Add New Income" button is pressed
		addIncome.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get the data inputs and assign them to the Income object
				income = new Income();
				
				//*********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the income amount?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "How often(days) do you recieve this income?", null));
				
				income.setValue(value);
				income.setPeriod(days);
				
				//Add Income object to the List
				incomeList.add(income);
				
				//Call function to add income to GUI
				newIncome();
				
			}
		});
		
		//if Return button is pressed
		returnButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenu();
			}
		});
		
		//add Income to the frame
		frame.setSize(325, 110);
		frame.add(this);
		frame.repaint();
		frame.validate();

	}

	public void newIncome() {
		//Clear and remake GUI
		this.removeAll();
		layout = new SpringLayout();
		setLayout(layout);
		
		//increment the frame size to add a new row to place the newly created Income.
		this.size++;
		
		//Loop to add add Income objects in the List to the GUI
		for(int i=0; i < incomeList.size(); i++) {
			JLabel l = new JLabel("Income "+(i+1)+":", JLabel.TRAILING);
			add(l);
			
			JTextField field = new JTextField(20);
			field.setText("$"+incomeList.get(i).getValue()+"0 is earned every "+incomeList.get(i).getPeriod()+" days.");
			l.setLabelFor(field);
			field.setEditable(false);
			add(field);
		}
		
		//add "Create a new Income" button
		addIncome = new JButton("Create a new Income");
		add(new JLabel(""));
		add(addIncome);
		
		//add "Return" button
		returnButton = new JButton("Return");
		add(new JLabel(""));
		add(returnButton);
				
		
		//add addIncome button listener
		addIncome.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get the data inputs and assign them to the Income object
				income = new Income();
				
				//*********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the income amount?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "How often(days) do you recieve this income?", null));
				
				income.setValue(value);
				income.setPeriod(days);
				
				//Add Income object to the List
				incomeList.add(income);
				
				//Call function to add income to GUI
				newIncome();
				
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
		
		//resize frame based on # of incomes added
		this.frame.setSize(325, 55*(this.size+2));
		this.revalidate();
		this.repaint();
	}
	
	/* Function to return to main menu
	 * Takes no input
	 * Returns nothing.
	 */
	private void returnToMenu() {
		frame.remove(this);
		ci = new CustomerInfo(frame);
	}


}

