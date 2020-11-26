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
public class GoalGUI extends JPanel{
	
	JFrame frame;
	SpringLayout layout;
	JButton addGoal;
	JTextField goalText;
	List<Goal> goalList;
	Goal goal;
	MainMenu menu;
	JButton returnButton, removeButton;
	
	Customer customer;	//customer object
	int duePeriod, size;
	float amountDue;
	
	Customer user;
	
	//Overloaded constructor that takes JFrame as input
	public GoalGUI(MainMenu menu, JFrame frame, Customer user) {
		
		this.menu = menu;
		this.frame = frame;
		this.user = user;

		goalList = this.user.getGoalList();
		this.size = this.goalList.size();
		this.frame.add(this);
		
		setupGUI();	
	
	}

	
	public void setupGUI() {
		
		//Clear and remake GUI
		this.removeAll();
		layout = new SpringLayout();
		setLayout(layout);
		
		//Loop to add add Goal objects in the List to the GUI
		for(int i=0; i < goalList.size(); i++) {
			JLabel l = new JLabel("Goal "+(i+1)+":", JLabel.TRAILING);
			add(l);
			
			JTextField field = new JTextField(20);
			field.setText("Save up $"+goalList.get(i).getGoalAmount()+" in "+goalList.get(i).getGoalDate()+" days.");
			l.setLabelFor(field);
			field.setEditable(false);
			add(field);
		}
		
		//Add addGoal button
		addGoal = new JButton("Create a new Goal");
		add(new JLabel(""));
		add(addGoal);
		
		//Add remove button
		removeButton = new JButton("Remove a Goal");
		add(new JLabel(""));
		add(removeButton);
		
		//add return button
		returnButton = new JButton("Return");
		add(new JLabel(""));
		add(returnButton);
		
		
		
		//add addGoal button listener
		addGoal.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Retrieve the data inputs and add them to the Goal object
				goal = new Goal();
				
				
				// *********this doesn't check for text input, fix*********
				double value = Double.parseDouble(JOptionPane.showInputDialog(frame, "What is the amount due for your goal?", null));
				int days = Integer.parseInt(JOptionPane.showInputDialog(frame, "When, in days, is your goal due?", null));

				goal.setGoalAmount(value);
				goal.setGoalDate(days);
				
				//Add Goal object to the List
				goalList.add(goal);

				// Remake GUI with new Goal
				size++;
				setupGUI();
				
			}
		});
		
		// if Remove button is pressed
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(size == 0) {
					JOptionPane.showMessageDialog(null, "There are no Goals to remove!");
				}
				else {
					int remove = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the number of the Goal you wish to remove: ", null));
					removeGoal(remove);
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
		
		//resize frame based on # of goals added
		this.frame.setSize(325, 55*(this.size+3));
		this.revalidate();
		this.repaint();
	}
	
	private void removeGoal(int remove) {
		goalList.remove(remove-1);
		this.size--;
	}
	
	/* Returns to the Main Menu
	 * Takes no input
	 * Returns nothing
	 */
	private void returnToMenu() {
		frame.remove(this);
		menu.loadFrame(this.frame);
	}

	
}

