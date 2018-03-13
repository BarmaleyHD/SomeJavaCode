package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the triptypes database table.
 * 
 */

public class TripTypes implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	private String tripTypeId;

	private String TTName;

	public TripTypes() {
	}
	

	public TripTypes(String tripTypeId, String tTName) {
		super();
		this.tripTypeId = tripTypeId;
		TTName = tTName;
	}


	public String getTripTypeId() {
		return this.tripTypeId;
	}

	public void setTripTypeId(String tripTypeId) {
		this.tripTypeId = tripTypeId;
	}

	public String getTTName() {
		return this.TTName;
	}

	public void setTTName(String TTName) {
		this.TTName = TTName;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return TTName+"";
	}
	
	

}