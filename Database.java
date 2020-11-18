package customer;

import java.util.ArrayList;
import java.util.List;

public class Database {
	
	public List<Customer> customerList;
	
	Database(){
		customerList = new ArrayList<Customer>();
	}

	
	public void addCustomer(Customer c) {
		customerList.add(c);
	}
	
	/* Search if their exists a duplicate username.
	 * Takes String username as input.
	 * Searches the username List for a duplicate. If one is found, return true. Else, return false.
	 */
	public boolean isDuplicateUsername(String username) {
		for(int i=0; i<customerList.size(); i++) {
			if(username.compareTo(customerList.get(i).getUsername()) == 0)
				return true;
		}
		return false;
	}
	
	/* Search if their exists a duplicate password.
	 * Takes String password as input.
	 * Searches the password List for a duplicate. If one is found, return true. Else, return false.
	 */
	public boolean isDuplicateEmail(String email) {
		for(int i=0; i<customerList.size(); i++) {
			if(email.compareTo(customerList.get(i).getEmail()) == 0)
				return true;
		}
		return false;
	}
	
	/* Search if the username exists in our database
	 * Takes String username as input.
	 * Searches if the username exists. If it does, return it's position. If nothing is found, return -1.
	 */
	public int findUsername(String username) {
		System.out.println("User entered username: "+username);
		System.out.println("Customer List size: "+customerList.size());
		for(int i=0; i<customerList.size(); i++) {
			System.out.println("Comparing... "+username+" and "+customerList.get(i).getUsername());
			if(username.compareTo(customerList.get(i).getUsername()) == 0) {
				System.out.println("Match found. Returning... "+i);
				return i;
			}
		}
		return -1;
	}
	
	/* Check if the user entered password matches with the associated username
	 * Takes String password and int location as input
	 * Returns true if password matches, false if it doesn't
	 */
	public boolean checkPassword(String password, int location) {
		if(password.compareTo(customerList.get(location).getPassword()) == 0)
			return true;
		return false;
	}

}
