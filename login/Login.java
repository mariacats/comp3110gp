package customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Login extends JPanel{

	SpringLayout layout;
	Register reg;
	JFrame frame;
	Database db;
	private String username, password;
	private int id;
	//private boolean confirmation;
	private JTextField uText;
	private JPasswordField pText;
	private JButton loginButton;
	private JButton registerButton;
	CustomerInfo ci;

	Login(JFrame frame, Database db){
		
		this.frame = frame;
		this.db = db;
		
		//labels for textfields
		String[] labels = {"Username: ", "Password: "};

		//declare display layout
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

			default: break;

			}
		}

		//button to login
		loginButton = new JButton("Login");
		add(new JLabel(""));
		add(loginButton);
		
		//button to register
		registerButton = new JButton("Register");
		add(new JLabel(""));
		add(registerButton);

		//format labels, textfields, button
		SpringUtilities.makeCompactGrid(this,
				labels.length+2, 2, //rows, cols
				6, 6,        //initX, initY
				6, 6);       //xPad, yPad

		//when loginButton is clicked, call setter functions to set variables with textfield data, then call validateLogin() to check if entry is valid
		loginButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUsername(uText.getText());
				setPassword(String.valueOf(pText.getPassword()));
				validateLogin();
			}
		});
		
		//when register button is clicked, call startRegistrtation() function which opens register panel
		registerButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startRegistration();
			}
		});
		
		//add login panel to frame and make it visible, then update frame
		frame.add(this);
		frame.setVisible(true);
		frame.repaint();
		frame.validate();
		
	}
	
	/* Function to close Login panel and open Register panel
	 * No input
	 * Returns nothing
	 */
	public void startRegistration() {
		frame.remove(this);
		reg = new Register(frame, db);
	}
	
	/* Function to locate username in database, check if password matches, then log the user in
	 * 
	 */
	public void validateLogin() {
		
		//x is the username location in Database customerList
		int x = db.findUsername(getUsername());
		
		//No username match found. Display error message and reset textfields
		if(x < 0) {
			JOptionPane.showMessageDialog(this, "Username does not exist.");
			uText.setText("");
			pText.setText("");
			return;
		}
		
		//Username and password do not match. Display error message and reset textfields
		if(db.checkPassword(getPassword(), x) == false) {
			JOptionPane.showMessageDialog(this, "Username and password do not match.");
			uText.setText("");
			pText.setText("");
			return;
		}
		
		//id = position of customer in customerList
		this.id = x;
		loginSuccess();
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
	
	//Function to remove Login panel and call CustomerInfo panel
	public void loginSuccess() {
		frame.remove(this);
		ci = new CustomerInfo(frame, this.db, this.id);
	}
	
}
