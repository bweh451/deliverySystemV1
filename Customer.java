package customer;

//Imported the GeneralDetails class in order to use it's methods.
import generalDetails.GeneralDetails;

//Created a Customer class that extends the GeneralDetails class.
public class Customer extends GeneralDetails {
	
	//Created variables specific to the Customer class.
	private String orderNum;
	private String streetAdd;
	private String suburb;
	private String email;
	
	//Created a constructor method in order to pass through the both the variables form the GeneralDetails class and the Customer class.
	public  Customer (String name, String contactNum, String town, String orderNum, String streetAdd, String suburb, String email) {
		super(name, contactNum, town);
		this.orderNum = orderNum;
		this.streetAdd = streetAdd;
		this.suburb = suburb;
		this.email = email;
	}
	
	//Removed all setter methods.
	public String getOrderNum() {
		return orderNum;
	}


	public String getStreetAdd() {
		return streetAdd;
	}

	public String getSuburb() {
		return suburb;
	}
	
	public String getEmail() {
		return email;
	}
	
	//Created a to string method in order to display some of the attributes of the Customer class in a more attractive way.
	public String toString() {
		String output = "Order number: " + orderNum;
	    output += "\nCustomer: " + super.getName();
	    output += "\nEmail: " + email;
	    output += "\nPhone number: " + super.getContactNum();
	    output += "\nLocation: " + super.getTown();
	    
	    return output;
	}
}
