package generalDetails;

//Created a "GeneralDetails" class with variables that are used by both the Customer and the Restaurant class.
public class GeneralDetails {
	
	private String name;
	private String contactNum;
	private String town;
	
	//Created a constructor method for the above variables.
	public GeneralDetails(String name, String contactNum, String town) {
		this.name = name;
		this.contactNum = contactNum;
		this.town = town;
	}
	
	//Removed all setter methods.
	public String getName() {
		return name;
	}

	public String getContactNum() {
		return contactNum;
	}

	public String getTown() {
		return town;
	}

}
