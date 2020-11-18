package customer;

import java.awt.*; 

import javax.swing.*;

public class Launch {

	public static void main(String[] args) {
		//ideally would load a pre-existing database
		Database db = new Database();
		
		JFrame frame = new JFrame();
		frame.setSize(300, 175);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Login login = new Login(frame, db);

	}

}
