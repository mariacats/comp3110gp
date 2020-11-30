package customer;

import javax.swing.*; 
import java.awt.*;  
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class IncomeGUI extends JPanel{
	
	JFrame frame;
	SpringLayout layout;
	Customer customer;	//customer object
	int period, size;
	float value;
	
	JButton addIncome;
	JTextField incText;
	
	JButton returnButton, removeButton;
	
	List<Income> incomeList;
	Income income;
	MainMenu menu;
	
	Customer user;
	
	
	//Overloaded constructor that takes JFrame as input
	public IncomeGUI(MainMenu menu, JFrame frame, Customer user) {
		
		this.menu = menu;
		this.frame = frame;
		this.user = user;
		
		this.incomeList = this.user.getIncomeList();
		this.size = this.incomeList.size();
		this.frame.add(this);
		
		setupGUI();

	}
	
	
	public void setupGUI() {
		//Clear and remake GUI
		this.removeAll();
		layout = new SpringLayout();
		setLayout(layout);
		
		
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
		
		// Add remove button
		removeButton = new JButton("Remove an Income");
		add(new JLabel(""));
		add(removeButton);
		
		//add "Return" button
		returnButton = new JButton("Return");
		add(new JLabel(""));
		add(returnButton);
				
		
		//add addIncome button listener
		addIncome.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get the data inputs and assign them to the Income object
				income = new Income();
				
				
				//Get input from user, check if input is valid. If valid, set input.
				double value;
				
				while(true) {
					String input = JOptionPane.showInputDialog(frame, "What is the income amount?", null);
					if(isDouble(input)) {
						value = Double.parseDouble(input);
						break;
					}
					else
						JOptionPane.showMessageDialog(frame, "Please enter a valid value.");
				}
				
				int days;
				
				while(true) {
					String input = JOptionPane.showInputDialog(frame, "How often(days) do you receive this income?", null);
					if(isInteger(input)) {
						days = Integer.parseInt(input);
						break;
					}
					else
						JOptionPane.showMessageDialog(frame, "Please enter a valid value.");
				}
				
				income.setValue(value);
				income.setPeriod(days);
				
				//Add Income object to the List
				incomeList.add(income);
				
				//Call function to add income to GUI
				size++;
				setupGUI();
				
			}
		});
		
		
		// if Remove button is pressed
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (size == 0) {
					JOptionPane.showMessageDialog(null, "There are no Incomes to remove!");
				} 
				else {
					int remove;
					while(true) {
						String input = JOptionPane.showInputDialog(frame, "Enter the number of the Income you wish to remove: ", null);
						if(isInteger(input)) {
							remove = Integer.parseInt(input);
							if(remove > 0 && remove <= incomeList.size()){
								break;
							}
						}
						JOptionPane.showMessageDialog(frame, "Please enter a valid value.");
							
					}
					removeIncome(remove);
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
		
		//resize frame based on # of incomes added
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
	
	/* Function to remove the income from the list
	 * Takes location of income in list to be removed
	 * Returns nothing. Remove the income and decrement size.
	 */
	private void removeIncome(int remove) {
		this.incomeList.remove(remove-1);
		this.size--;
	}
	
	/* Function to return to main menu
	 * Takes no input
	 * Returns nothing.
	 */
	private void returnToMenu() {
		this.frame.remove(this);
		this.menu.loadFrame(this.frame);
	}


}

