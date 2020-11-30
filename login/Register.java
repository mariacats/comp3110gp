package customer;

import java.awt.*;  
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;

public class Register extends JPanel{
	
	SpringLayout layout;
	Database db;
	JFrame frame;
	Login login;
	Customer customer;
	private String username, password, confirmPassword, email;
	private JTextField uText, eText;
	private JPasswordField pText, cpText;
	private JButton registerButton;
	Pattern pattern;
	Matcher matcher;
	
	Register(JFrame frame, Database db){
		
		this.frame = frame;
		this.db = db;
		
		//labels for textfields
		String[] labels = {"Username: ", "Password: ", "Confirm Password: ", "Email: "};
				
		layout = new SpringLayout();
		setLayout(layout);
		
		//create label and textfield instances and add them to panel
		for(int i=0; i<labels.length; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			add(l);
			switch(i) {
				case 0: uText = new JTextField(10);
						l.setLabelFor(uText);
						add(uText);
						break;

				case 1: pText = new JPasswordField(10);
						l.setLabelFor(pText);
						add(pText);
						break;
	
				case 2: cpText = new JPasswordField(10);
						l.setLabelFor(cpText);
						add(cpText);
						break;

				case 3: eText = new JTextField(10);
						l.setLabelFor(eText);
						add(eText);
						break;
				
				default: break;

			}
		}
		
		//add button to register the user as a customer
		registerButton = new JButton("Register");
		add(new JLabel(""));
		add(registerButton);
		
		//format labels, textfields, button
		SpringUtilities.makeCompactGrid(this,
                 labels.length+1, 2, //rows, cols
                 6, 6,        //initX, initY
                 6, 6);       //xPad, yPad
		
		//when button is clicked, call startRegistration() function which attempts to create a new customer
		registerButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startRegistration();
			}
		});
		
		//add Register to frame
		frame.add(this);
		frame.repaint();
		frame.validate();
		
		
	}
	
	// Function to validate user entry data and add them as a customer
	public void startRegistration() {
		//check if username is valid
		if(validateUsername(uText.getText()) == false) {
			uText.setText("");
			return;
		}
		
		//check if password is valid
		if(validatePassword(String.valueOf(pText.getPassword())) == false) {
			pText.setText("");
			cpText.setText("");
			return;
		}
		
		//check if passwords match
		if(String.valueOf(pText.getPassword()).compareTo(String.valueOf(cpText.getPassword())) != 0){
			JOptionPane.showMessageDialog(this, "Your passwords must match.");
			pText.setText("");
			cpText.setText("");
			return;
		}
		
		//check if email is valid
		if (validateEmail(eText.getText()) == false) {
			JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
			eText.setText("");
			return;
		}
		
		//now that we know information is valid, save it and call addCustomerToDatabase() function to create a new customer
		setUsername(uText.getText());
		setPassword(String.valueOf(pText.getPassword()));
		setConfirmPassword(String.valueOf(cpText.getPassword()));
		setEmail(eText.getText());
		addCustomerToDatabase();
		
		//call function to return to login screen
		startLogin();
	}
	
	//setter function for username
	public void setUsername(String s) {
		this.username = s;
	}
	
	//getter function for username
	public String getUsername() {
		return this.username;
	}
	
	//setter function for password
	public void setPassword(String s) {
		this.password = s;
	}
	
	//getter function for password
	public String getPassword() {
		return this.password;
	}
	
	//setter function for confirmPassword
	public void setConfirmPassword(String s) {
		this.confirmPassword = s;
	}
		
	//getter function for confirmPassword
	public String getConfirmPassword() {
		return this.confirmPassword;
	}
	
	//setter function for email
	public void setEmail(String s) {
		this.email = s;
	}
			
	//getter function for email
	public String getEmail() {
		return this.email;
	}
	
	/* Function to check if username is valid
	 * Takes String as input
	 * Returns true if username passes valid parameters, otherwise returns false
	 */
	public boolean validateUsername(String u) {
		if(u.length()<4 || u.length()>25) {
			JOptionPane.showMessageDialog(this, "Your username must be between 4-25 characters.");
			return false;
		}
		this.pattern = Pattern.compile("^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
		this.matcher = this.pattern.matcher(u);
		if(this.matcher.find() == false) {
			JOptionPane.showMessageDialog(this, "Your username can only contain: A-Z, 0-9, with '_' and '.' as separators.");
			return false;
		}
		return true;

	}
	
	/* Function to check if password is valid
	 * Takes String as input
	 * Returns true if password passes valid parameters, otherwise returns false
	 */
	public boolean validatePassword(String u) {
		if(u.length()<4 || u.length()>25) {
			JOptionPane.showMessageDialog(this, "Your password must be between 4-25 characters.");
			return false;
		}
		this.pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{7,}$");
		this.matcher = this.pattern.matcher(u);
		if(this.matcher.find() == false) {
			JOptionPane.showMessageDialog(this, "Your password must contain 7 characters, one uppercase letter, one digit, and one special character.");
			return false;
		}
		return true;

	}
	
	/* Function to check for valid email address
	 * Takes no input
	 * Returns true if email matches pattern, otherwise returns false
	 */
	public boolean validateEmail(String e) {
		this.pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		this.matcher = this.pattern.matcher(e);
		return this.matcher.find();
	}
	
	// Function to create a new Customer and add it to the Database
	public void addCustomerToDatabase() {
		customer = new Customer(getUsername(), getPassword(), getEmail());
		db.addCustomer(customer);
	}
	
	// Function to remove Register panel and call Login panel with the JFrame and Database
	public void startLogin() {
		frame.remove(this);
		login = new Login(frame, db);
	}
	
}