package main;

//Imported the following java libraries in order to use their methods.
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;

//Imported the Customer and Restaurant class in order to user their methods.
import customer.Customer;
import restaurant.Restaurant;

public class Main {

	
	public static void main(String[] args) {
		
		//Created local variables for later use.
		String entireOrder = "";
		String invoiceOutput = "";
		String driverName = "";
		double orderTotalNum = 0.00;
		String orderTotalStr = "";
		
		//Will force the while loop inside of the createNewRestaurant method.
		String answer = "y";
		
		//Created local array list for the "Restaurant" class in order to pass them through the constructor.
		List <String> orderListArr = new ArrayList<>();
		List <Double> orderPriceArr = new ArrayList<>();
		List <Integer> orderAmtArr = new ArrayList<>();
		
		//Created a decimal formatter object, that will format the doubles to 2 decimal places.
		DecimalFormat dFormat = new DecimalFormat ("0.00");
			
	
		//Extracted all user prompts used to create customer class object into a method.
		Customer cust = createNewCustomer();
	
		//Extracted all user prompts used to create customer class object into a method.
		Restaurant rest = createNewRestaurant(answer, orderListArr, orderPriceArr, orderAmtArr);
		

		//Surrounded code in try-catch block to manage exceptions.
		try {
			
			//For loop that creates a string of what the customer ordered in the following format: (2 x Pepperoni Pizza (R90.00)).
			//Loops through "orderListArr", "orderAmtArr" and "orderPriceArr" and with each iteration adds what is currently at the index to a temp string ("orderLine").
			//Temp string then gets added to "entireOrder" variable with each iteration.
			for(int j = 0; j < orderListArr.size(); j++) {
				
				String orderLine = Integer.toString(orderAmtArr.get(j)) + " x ";
				orderLine += orderListArr.get(j) + " ";
				orderLine += "(R" + dFormat.format(orderPriceArr.get(j)) + ")";
		
				entireOrder += orderLine + "\n";
			}
			
			//Combined the previous two for loops.
			//This for loop, iterates through orderAmtArr and orderPriceArr and multiplies the numbers where index is at the time and adds it to the "orderTotalNum" variable.
			//"orderTotalNum" then gets casted to a string and the decimal format gets set to two decimal places.
			for(int i = 0; i < orderAmtArr.size(); i++) {
				orderTotalNum += orderAmtArr.get(i) * orderPriceArr.get(i);	
				orderTotalStr = "R" + dFormat.format(orderTotalNum);
				
			}
			
		}
		
		//If the "createNewRestaurant" method catches an exception, the following exception will be caught and it will return to stop the rest of the program from running.
		catch(IndexOutOfBoundsException e) {
			return;
		}
		
		//Code is entered into a try-catch block to manage exceptions.
		try {		
			//Finds the "driver.txt" file
			File drivers = new File("drivers.txt");		
			//Finds the "invoice.txt" file.
			File invoice = new File("invoice.txt");
			
			//Scanner that can read through the "drivers.txt" file.
			Scanner scan = new Scanner(drivers);
			
			//Formatter that can write to the "invoice.txt" file.
			Formatter format = new Formatter(invoice);
			
			//While loop that carries on until there are no more new lines to read in the "drivers.txt" file.
			while(scan.hasNextLine()) {
				
				//Variable that gets created with each loop so that current line of text being read can be manipulated.
				String curLine = scan.nextLine();
				
				
				//If statement that determines if the "curLine" contains the "town" attributes of both the "Customer" and "Restaurant" object.			
				if(curLine.contains(cust.getTown()) && curLine.contains(rest.getTown())) {
				
					//If true: 
					//The current line get's pushed into an array
					String [] currentLineArr = curLine.split(", ");
				
					//The variable "driverName" linked to the element at index 0 of the above array.
					driverName = currentLineArr[0];
					
					//Extracted the original code to a method called "fullInvoice".
					//The variable "invoiceOutput" gets used to created in order to store all the attributes that get written into the "invoice.txt" file.
					invoiceOutput = fullInvoice(entireOrder, driverName, orderTotalStr, cust, rest);
					
					//The formatter writes what is stored in the "invoiceOutput" variable.
					format.format(invoiceOutput);
					
					//Break statement that breaks out of the loop after the first instance of the if statement being true.
					break;
				}
				
				//If "curLine" does not contain the "town" attribute of both the "Customer" and "Restaurant" object.
				//It will write what "invoiceOutput" currently is below to the "invoice.txt" file and break out of the loop.
				else {
					invoiceOutput = "Sorry! Our drivers are too far away from you to be able to deliver to your location.";
					format.format(invoiceOutput);
					break;
				}
			
			}

			//Closes both the scanner and formatter.
			scan.close();
			format.close();
			
		}
		
		//If the file does not exist then if will print out the following.
		catch(FileNotFoundException e) {
			System.out.println("Error - file not found!"); 
		}
		
		//If there are a few fields that are empty that aren't supposed to be empty, it will print out the following.
		catch(NullPointerException e) {
			System.out.println("One or more of the fields are empty.");
			return;
		}
		
	}
	
	//Extracted method that returns a string that will get written to the "invoice.txt" file.
	private static String fullInvoice(String entireOrder, String driverName, String orderTotalStr, Customer cust, Restaurant rest) {
		
		String invoiceOutput = cust.toString() + "\n\n";
		invoiceOutput += "You have ordered the following from " + rest.getName() + " in " + rest.getTown() + ": \n\n"; 
		invoiceOutput += entireOrder + "\n\n";
		invoiceOutput += "Special instructions: " + rest.getSpecialInstruct() + "\n\n"; 
		invoiceOutput += "Total: " + orderTotalStr + "\n\n";
		invoiceOutput += driverName + " is nearest to the restaurant and so he will be delivering your order at: \n\n";
		invoiceOutput += cust.getStreetAdd() + "\n" + cust.getSuburb() + "\n\n";
		invoiceOutput += "If you need to contact the restaurant, their number is: " + rest.getContactNum() + ".";
		
		return invoiceOutput;
	}
	
	//Extracted method that creates a new restaurant class object, via user prompts.
	private static Restaurant createNewRestaurant(String answer, List<String> orderListArr, List<Double> orderPriceArr,
			List<Integer> orderAmtArr) {
		
		//Surrounded code in try-catch block to manage exceptions.
		try {
			
			//Created local variables that prompt the user to enter specific information.
			String restName = JOptionPane.showInputDialog("Please enter the restaurant name: ");
			String restContactNum = JOptionPane.showInputDialog("Please enter the restaurant's phone number: ");
			String restTown = JOptionPane.showInputDialog("Please enter the town the restaurant resides in (eg. 'Cape Town'): ");
			
			//Created a while loop, so that the user receives the same prompts until the user until the user enters "n".
			while(answer.equalsIgnoreCase("y")) {
				
				orderListArr.add(JOptionPane.showInputDialog("What would you like to order?"));
				orderAmtArr.add(Integer.parseInt(JOptionPane.showInputDialog("How many of this would you like to order?")));
				orderPriceArr.add(Double.parseDouble(JOptionPane.showInputDialog("Please enter the price of this item (eg. 90 or 89.95):")));
				answer = JOptionPane.showInputDialog("Would you like to order something else? (y/n)");
			}
			
			
			String specialInstruct = (JOptionPane.showInputDialog("Do you have any special instructions for this order?"));
			
			//Passed the above variables through the "Restaurant constructor in order to create a "Restaurant" object.
			return new Restaurant(restName, restContactNum, restTown, orderListArr, orderPriceArr, orderAmtArr, specialInstruct);
		
		}
		
		//If the user does not enter a number for the "orderAmtArr" and "orderPriceArr" prompts then the following exception will be caught.
		catch(NumberFormatException e) {
			
			//The following message will be printed out to the user null will be returned.
			System.out.println("Invalid input, please enter a number !");
			return null;
		}
		
	}
	
	//Extracted method that creates a new customer class object, via user prompts.
	private static Customer createNewCustomer() {
		
		//Created local variables that prompt the user to enter specific information.
		String custOrderNum =  JOptionPane.showInputDialog("Please enter your order number: ");
		String custName = JOptionPane.showInputDialog("Please enter your full name: ");
		String custEmail = JOptionPane.showInputDialog("Please enter your e-mail address: ");
		String custContactNum = JOptionPane.showInputDialog("Please enter your phone number: ");
		String custTown = JOptionPane.showInputDialog("Please enter your the town you reside in (eg. 'Cape Town'): ");
		String custStreetAdd = JOptionPane.showInputDialog("Please enter your street address (eg. '12 Romney street'): ");
		String custSuburb = JOptionPane.showInputDialog("Please enter your suburb (eg. 'Bellville'): ");
		
		//Used the variables above and passed it through the "Customer" class constructor to in order to create "Customer" object.
		return new Customer(custName, custContactNum, custTown, custOrderNum, custStreetAdd, custSuburb, custEmail);

	}

}

//I used the following resource to help me format to the correct decimal places:
//Youtube.com. 2013. Java: Rounding Numbers (Math.round(), DecimalFormat & printf). [online] Available at: <https://www.youtube.com/watch?v=nLDWeTz3Zgc> [Accessed 20 January 2022].
 