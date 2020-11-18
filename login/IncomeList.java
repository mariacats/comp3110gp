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
//ADD OPTION TO REMOVE/EDIT AN INCOME
public class IncomeList extends JPanel{
	
	JFrame frame;
	SpringLayout layout;
	Customer customer;	//customer object
	int period, size;
	float value;
	
	JButton addIncome;
	JTextField incText;
	
	List<Income> incomeList;
	Income income;
	
	
	//Overloaded constructor that takes JFrame as input
	public IncomeList(JFrame frame) {
		
		incomeList = new ArrayList<Income>();
		
		this.size = 0;
		setupGUI(frame);
		//this.customer = customer;
		//period = 31;				//sets the default payment period to 31 days
		//value = 1000;				//sets the default income value to 1000
		
	}
	
	public void setupGUI(JFrame frame) {
				
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
		               this.size, 2, //rows, cols
		               6, 6,        //initX, initY
		               6, 6);       //xPad, yPad
		
		//if View Income button is pressed
		addIncome.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				income = new Income();
				
				//*********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the income amount?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "How often(days) do you recieve this income?", null));
				
				income.setValue(value);
				income.setPeriod(days);
				
				incomeList.add(income);
				
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
		this.size++;
		
		
		for(int i=0; i < incomeList.size(); i++) {
			JLabel l = new JLabel("Income "+(i+1)+":", JLabel.TRAILING);
			add(l);
			
			JTextField field = new JTextField(20);
			field.setText("$"+incomeList.get(i).getValue()+" is earned every "+incomeList.get(i).getPeriod()+" days.");
			l.setLabelFor(field);
			field.setEditable(false);
			add(field);
		}
		
		//*******below is super tedious and inefficient, needs to be reworked*******
		//BUT.. works for now
		//add income button
		addIncome = new JButton("Create a new Income");
		add(new JLabel(""));
		add(addIncome);
		
		//add income button listener
		addIncome.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				income = new Income();
				
				//*********this doesn't check for text input, fix*********
				int value = Integer.parseInt(JOptionPane.showInputDialog(frame, "What is the income amount?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "How often(days) do you recieve this income?", null));
				
				income.setValue(value);
				income.setPeriod(days);
				
				incomeList.add(income);
				
				//then add a thing to the GUI that says "Income 1: $X every Y days."
				newIncome();
				
			}
		});
		
		
		
		
		SpringUtilities.makeCompactGrid(this,
				             this.size+1, 2, //rows, cols
				             6, 6,        //initX, initY
				             6, 6);       //xPad, yPad
		
		//resize frame based on # of incomes added
		this.frame.setSize(325, 75*(this.size+1));
		this.revalidate();
		this.repaint();
	}


}

