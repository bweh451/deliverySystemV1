package restaurant;

//Imported the following java library in order to use it's methods.
import java.util.*;

//Imported the GeneralDetails class in order to use it's methods.
import generalDetails.GeneralDetails;

//Created a Restaurant class that extends the GeneralDetails class.
public class Restaurant extends GeneralDetails {
	
	//Created variables specific to the Restaurant class.
	private List <String> orderListArr = new ArrayList <>();
	private List <Double> orderPriceArr = new ArrayList <>();
	private List <Integer> orderAmtArr = new ArrayList <>();
	private String specialInstruct;
	
	//Created a constructor method in order to pass through the both the variables form the GeneralDetails class and the Restaurant class.
	public Restaurant(String name, String contactNum, String town, List <String> orderListArr, List <Double> orderPriceArr, 
			List <Integer> orderAmtArr, String specialInstruct) {
		
		super(name, contactNum, town);
		this.orderListArr = orderListArr;
		this.orderPriceArr = orderPriceArr;
		this.orderAmtArr = orderAmtArr;
		this.specialInstruct = specialInstruct;
		
	}
	
	//Removed all setter methods.
	public List <String> getOrderListArr() {
		return orderListArr;
	}


	public List <Double> getOrderPriceArr() {
		return orderPriceArr;
	}


	public List <Integer> getOrderAmtArr() {
		return orderAmtArr;
	}


	public String getSpecialInstruct() {
		return specialInstruct;
	}


}
