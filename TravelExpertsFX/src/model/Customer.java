package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int customerId;
	
	private String custFirstName;
	
	private String custLastName;
	
	private String custAddress;
	
	private String custCity;
	
	private String custProv;
	
	private String custPostal;
	
	private String custCountry;
	
	private String custHomePhone;
	
	private String custBusPhone;
	
	private String custEmail;

	private int agentId;
	
	public Customer() {
	}

	public Customer(int customerId, String custFirstName, String custLastName, String custAddress, String custCity,
			String custProv, String custPostal, String custCountry, String custHomePhone, String custBusPhone,
			String custEmail, int agentId) {
		super();
		this.customerId = customerId;
		this.custFirstName = custFirstName;
		this.custLastName = custLastName;
		this.custAddress = custAddress;
		this.custCity = custCity;
		this.custProv = custProv;
		this.custPostal = custPostal;
		this.custCountry = custCountry;
		this.custHomePhone = custHomePhone;
		this.custBusPhone = custBusPhone;
		this.custEmail = custEmail;
		this.agentId = agentId;
	}
	
	 public Customer(Customer another) {
		this.customerId = another.customerId;
		this.custFirstName = another.custFirstName;
		this.custLastName = another.custLastName;
		this.custAddress = another.custAddress;
		this.custCity = another.custCity;
		this.custProv = another.custProv;
		this.custPostal = another.custPostal;
		this.custCountry = another.custCountry;
		this.custHomePhone = another.custHomePhone;
		this.custBusPhone = another.custBusPhone;
		this.custEmail = another.custEmail;
		this.agentId = another.agentId;
		  }
	 
	 public Customer Copy(Customer another) {
		this.customerId = another.customerId;
		this.custFirstName = another.custFirstName;
		this.custLastName = another.custLastName;
		this.custAddress = another.custAddress;
		this.custCity = another.custCity;
		this.custProv = another.custProv;
		this.custPostal = another.custPostal;
		this.custCountry = another.custCountry;
		this.custHomePhone = another.custHomePhone;
		this.custBusPhone = another.custBusPhone;
		this.custEmail = another.custEmail;
		this.agentId = another.agentId;
		return this;
		  }

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAgentId() {
		return this.agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustBusPhone() {
		return this.custBusPhone;
	}

	public void setCustBusPhone(String custBusPhone) {
		this.custBusPhone = custBusPhone;
	}

	public String getCustCity() {
		return this.custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	public String getCustCountry() {
		return this.custCountry;
	}

	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}

	public String getCustEmail() {
		return this.custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustFirstName() {
		return this.custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public String getCustHomePhone() {
		return this.custHomePhone;
	}

	public void setCustHomePhone(String custHomePhone) {
		this.custHomePhone = custHomePhone;
	}

	public String getCustLastName() {
		return this.custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	public String getCustPostal() {
		return this.custPostal;
	}

	public void setCustPostal(String custPostal) {
		this.custPostal = custPostal;
	}

	public String getCustProv() {
		return this.custProv;
	}

	public void setCustProv(String custProv) {
		this.custProv = custProv;
	}

	@Override
	public String toString() {
		String name  = custFirstName+ " " + custLastName  + ", ";
		//return custFirstName + " " + custLastName+ ", " + custHomePhone;
		return String.format("%-25s%s", name, custHomePhone);
		
		//return String.format("%.10s%.10s%.15s", "shortText", "shortText", "shortText");
		
	}
	
	

}