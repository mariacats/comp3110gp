package customer;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

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
	double amountDue;
	
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
				
				
				//Get input from user, check if input is valid. If valid, set input.
				double value;
				
				while(true) {
					String input = JOptionPane.showInputDialog(frame, "What is your Goal? (amount you would like to save)", null);
					if(isDouble(input)) {
						value = Double.parseDouble(input);
						break;
					}
					else
						JOptionPane.showMessageDialog(frame, "Please enter a valid value.");
				}
				
				int days;
				
				while(true) {
					String input = JOptionPane.showInputDialog(frame, "In how many days would you like to accomplish this goal?", null);
					if(isInteger(input)) {
						days = Integer.parseInt(input);
						break;
					}
					else
						JOptionPane.showMessageDialog(frame, "Please enter a valid value.");
				}

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
					int remove;
					while(true) {
						String input = JOptionPane.showInputDialog(frame, "Enter the number of the Goal you wish to remove: ", null);
						if(isInteger(input)) {
							remove = Integer.parseInt(input);
							if(remove > 0 && remove <= goalList.size()){
								break;
							}
						}
						JOptionPane.showMessageDialog(frame, "Please enter a valid value.");
							
					}
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
	
	/* Function to check if string being parsed is an Integer
	 * Takes String as input
	 * Returns true if it is an int, false otherwise
	 */
	private boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	/* Function to check if string being parsed is an Double
	 * Takes String as input
	 * Returns true if it is an double, false otherwise
	 */
	private boolean isDouble(String s) {
	    try { 
	        Double.parseDouble(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	/* Function to remove the goal from the list
	 * Takes location of goal in list to be removed
	 * Returns nothing. Remove the goal and decrement size.
	 */
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

