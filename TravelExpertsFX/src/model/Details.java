package model;


import model.TripTypes;

public class Details {
	
	private TripTypes triptype;
	private int noofCust;
	private String deptCity;
	
	//constructor
	public Details(TripTypes triptype, int noofCust, String deptCity) {
		//super();
		this.triptype = triptype;
		this.noofCust = noofCust;
		this.deptCity = deptCity;
	}
	
	
/*	public Details(Triptype tripType2, int parseInt, String text) {
		// TODO Auto-generated constructor stub
	}*/


	//getter and setters
	public TripTypes getTriptype() {
		return triptype;
	}
	public void setTriptype(TripTypes triptype) {
		this.triptype = triptype;
	}

	public int getNoofCust() {
		return noofCust;
	}
	public void setNoofCust(int noofCust) {
		this.noofCust = noofCust;
	}

	public String getDeptCity() {
		return deptCity;
	}
	public void setDeptCity(String deptCity) {
		this.deptCity = deptCity;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return triptype + "," + noofCust + "," + deptCity;
	}
	
	

}
