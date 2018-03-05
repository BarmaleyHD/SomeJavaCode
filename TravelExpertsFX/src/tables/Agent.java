package tables;

public class Agent extends Person {
	
	private String phoneNumber;
	private double commission;
	
	public Agent(String name, String phoneNumber, double commission) {
		super(name);
		this.commission = commission;
		this.phoneNumber = phoneNumber;
	}
	
	public Agent(String name){
		super(name);
	}	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}	
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	public void setName(String name){
		super.setName(name);
	}
	
	public String getName(){
		return super.name;
	}
	@Override
	public void display() { // Display full info to console
		System.out.println("Agent name: " + super.name + 
				", Phone Number: " + phoneNumber + 
				", Commission: " + commission);
		
	}

}
