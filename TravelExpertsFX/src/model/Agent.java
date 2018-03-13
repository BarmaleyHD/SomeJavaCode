package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the agents database table.
 * 
 */
@Entity
@Table(name="agents")
@NamedQuery(name="Agent.findAll", query="SELECT a FROM Agent a")
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int agentId;

	private String agtFirstName;
	
	private String agtMiddleInitial;
	
	private String agtLastName;

	private String agtBusPhone;

	private String agtEmail;	

	private String agtPosition;
	
	private int agencyId;

	public Agent() {
	}

    
	public Agent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName, String agtBusPhone,
			String agtEmail, String agtPosition, int agencyId) {
		super();
		this.agentId = agentId;
		this.agtFirstName = agtFirstName;
		this.agtMiddleInitial = agtMiddleInitial;
		this.agtLastName = agtLastName;
		this.agtBusPhone = agtBusPhone;
		this.agtEmail = agtEmail;
		this.agtPosition = agtPosition;
		this.agencyId = agencyId;
	}
	
	public int getAgentId() {
		return this.agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getAgencyId() {
		return this.agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgtBusPhone() {
		return this.agtBusPhone;
	}

	public void setAgtBusPhone(String agtBusPhone) {
		this.agtBusPhone = agtBusPhone;
	}

	public String getAgtEmail() {
		return this.agtEmail;
	}

	public void setAgtEmail(String agtEmail) {
		this.agtEmail = agtEmail;
	}

	public String getAgtFirstName() {
		return this.agtFirstName;
	}

	public void setAgtFirstName(String agtFirstName) {
		this.agtFirstName = agtFirstName;
	}

	public String getAgtLastName() {
		return this.agtLastName;
	}

	public void setAgtLastName(String agtLastName) {
		this.agtLastName = agtLastName;
	}

	public String getAgtMiddleInitial() {
		return this.agtMiddleInitial;
	}

	public void setAgtMiddleInitial(String agtMiddleInitial) {
		this.agtMiddleInitial = agtMiddleInitial;
	}

	public String getAgtPosition() {
		return this.agtPosition;
	}

	public void setAgtPosition(String agtPosition) {
		this.agtPosition = agtPosition;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return agtFirstName + "";
	}
	
	

}