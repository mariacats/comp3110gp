import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
/*
 * initialize using ReadWrite() or ReadWrite(String path)
 * Get file contents as a string using read()
 * write to the file using write(String x)
 */

public class ReadWrite {
	public Path file;
	private String Username;
	private String Password;
	private String Name;
	private String Address;
	private String Phone;
	private String Card;
	//sets it to save automatically to the location of the java file (theoretically)
	ReadWrite()
	{
		Path file2 = FileSystems.getDefault().getPath(done(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "storage.txt"));
		try(BufferedReader reader = Files.newBufferedReader(file2))
		{
			
			File myObj = new File(file2.toString());
			myObj.createNewFile();
			file = file2;
		}
		catch (IOException e) {
			System.out.println(file2);
			file = file2;
		}
	}
	//Saves everything to the file defined by x
	ReadWrite(String x, boolean getData)
	{
		Path file2 = FileSystems.getDefault().getPath(x);
		try(BufferedReader reader = Files.newBufferedReader(file2))
		{
			
			File myObj = new File(file2.toString());
			myObj.createNewFile();
			file = file2;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(file2);
			file = file2;
			//e.printStackTrace();
		}
		if(getData)
			readData();
	}
	//constructor sets  all data at once
	//everything except path can be set to null
	ReadWrite(String x, String u, String p, String n, String a, String ph, String c)
	{
		if(u == null)
			u = "";
		else if(u.contains(" "))
		{
			System.out.println("Cannot contain a space");
			u = u.replaceAll(" ", "");
		}
		if(p == null)
			p = "";
		else if(p.contains(" "))
		{
			System.out.println("Cannot contain a space");
			p = p.replaceAll(" ", "");
		}
		if(n == null)
			n = "";
		else if(n.contains(" "))
		{
			System.out.println("Cannot contain a space");
			n = n.replaceAll(" ", "");
		}
		if(a == null)
			a = "";
		else if(a.contains(":"))
		{
			System.out.println("Cannot contain a colon");
			a = a.replaceAll(":", "");
		}
		if(ph == null)
			ph = "";
		else if(ph.contains(" "))
		{
			System.out.println("Cannot contain a space");
			ph = ph.replaceAll(" ", "");
		}
		if(c == null)
			c = "";
		else if(c.contains(" "))
		{
			System.out.println("Cannot contain a space");
			c = c.replaceAll(" ", "");
		}
			
		Username = u;
		Password = p;
		Name = n;
		Address = a;
		Phone = ph;
		Card = c;
		Path file2 = FileSystems.getDefault().getPath(x);
		try(BufferedReader reader = Files.newBufferedReader(file2))
		{
			
			File myObj = new File(file2.toString());
			myObj.createNewFile();
			file = file2;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(file2);
			file = file2;
			//e.printStackTrace();
		}
	}
	 
	 //writes to the file, deleting everything that came before
	 public void write(String y)
	 {
		 try (BufferedWriter writer = Files.newBufferedWriter(file))
		 {
			 writer.write(y);
			 writer.close();
		 } catch (IOException x) {
		      System.err.format("IOException: %s%n", x);
		      System.out.println("l");
		    }
	 }
	//saves the data in a readable format
	 public void saveData()
		{
			write("Username: " + Username +
				  "\nPassword: " + Password +
			  	  "\nName: " + Name + 
				  "\nAddress: " + Address + 
				  "\nPhone: " + Phone + 
				  "\nCard: " + Card);
		}
		//reads the data from the file
	 public void readData()
		{
			String x = read();
			
			int start = 0;
			int end = 0;
			start = x.indexOf("Username: ") + 10;
			end  = x.indexOf("\nPassword: ");
			Username = x.substring(start, end);
			start = end + 11;
			end  = x.indexOf("\nName: ");
			Password = x.substring(start, end);
			start = end + 7;
			end  = x.indexOf("\nAddress: ");
			Name = x.substring(start, end);
			start = end + 10;
			end  = x.indexOf("\nPhone: ");
			Address = x.substring(start, end);
			start = end + 8;
			end  = x.indexOf("\nCard: ");
			Phone = x.substring(start, end);
			start = end + 7;
			end  = x.length();
			Card = x.substring(start, end);
			System.out.println(Username);
		}
	 //Returns everything in the file in a string
	 public String read(){
		 StringBuilder everything = new StringBuilder();
		  String line = "";
	   
	    File files = new File(file + "");

	    try (BufferedReader reader = Files.newBufferedReader(file)){//, StandardCharsets.UTF_8)) {
	     
	    	FileReader fileReader = new FileReader(files);
	        BufferedReader br = new BufferedReader(fileReader);
	        String x = "";
	          while((x = br.readLine()) != null)
	          {line = line + x + "\n";}
	          
	          if(line.length() >= 1)
	        	  line = line.substring(0, line.length() - 1);
	          
	        	everything.append(line);
	        	br.close();
	    } catch (IOException x) {
	      System.err.format("IOException: %s%n", x);
	      System.out.println("l");
	    }
	    
	    
	 	
	    return everything.toString();
	  }
	 //helps with the formating for the default path
	 public String done(String x)
	  {

	    x= x.substring(0, x.indexOf("/bin/")) + "/src" + x.substring(x.indexOf("/bin/") + 4, x.length());
	    while(x.contains("/"))
	    {
	      x= x.substring(0, x.indexOf("/")) + "\\" + x.substring(x.indexOf("/") + 1, x.length());
	    }
	    while(x.contains("%20"))
	    {
	      x= x.substring(0, x.indexOf("%20")) + " " + x.substring(x.indexOf("%20") + 3, x.length());
	    }
	    x= x.substring(1, x.length());
	    x.trim();
	    return x;
	  }
	//gets and sets
		public String GetUsername()
		{
			return Username;
		}
		public void SetUsername(String u)
		{
			Username = u;
		}
		public String GetPassword()
		{
			return Password;
		}
		public void SetPassword(String p)
		{
			Password = p;
		}
		public String GetName ()
		{
			return Name;
		}
		public void SetName(String n)
		{
			Name = n;
		}
		public String GetAddress()
		{
			return Address;
		}
		public void SetAddress(String a)
		{
			Address = a;
		}
		public String GetPhone()
		{
			return Phone;
		}
		public void SetPhone(String ph)
		{
			Phone = ph;
		}
		public String GetCard()
		{
			return Card;
		}
		public void SetCard(String c)
		{
			Card = c;
		}
}
