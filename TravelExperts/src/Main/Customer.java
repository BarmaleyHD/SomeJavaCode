package Main;

public class Customer {
	
	protected String cusName;
	protected String cusAddress;
	protected String cusCity;
	protected String cusProvince;
	
	public Customer(String name, String address, String city, String province) {
		this.cusName = name;
		this.cusAddress = address;
		this.cusCity = city;
		this.cusProvince = province;
	}
	
	public Customer(String name){
		this.cusName = name;
		this.cusAddress = "No address";
		this.cusCity = "No city";
		this.cusProvince = "No province";
	}
	
	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
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

	
}
