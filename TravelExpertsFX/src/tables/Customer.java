package tables;

public class Customer extends Person{
	
	protected String cusAddress;
	protected String cusCity;
	protected String cusProvince;
	
	// Constructor with full info
	public Customer(String name, String address, String city, String province) {
		super(name);
		this.cusAddress = address;
		this.cusCity = city;
		this.cusProvince = province;
	}
	
	// Constructor with name and default values
	public Customer(String name){
		super(name);
		this.cusAddress = "No address";
		this.cusCity = "No city";
		this.cusProvince = "No province";
	}
	
	public String getCusName() {
		return super.getName();
	}

	public void setCusName(String cusName) {
		super.setName(cusName);
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusCity() {
		return cusCity;
	}

	public void setCusCity(String cusCity) {
		this.cusCity = cusCity;
	}

	public String getCusProvince() {
		return cusProvince;
	}

	public void setCusProvince(String cusProvince) {
		this.cusProvince = cusProvince;
	}

	@Override
	public void display() { // Display full info to console
		System.out.println("Customer name: " + super.name + 
							", Address: " + cusAddress + 
							", City: " + cusCity + 
							", Province: " + cusProvince);		
		
	}

	
}
