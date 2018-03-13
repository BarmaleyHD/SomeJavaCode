package model;

public class Province {
	
	private String prov;
	private String province;


	public Province(String prov, String province) {
		this.prov = prov;
		this.province = province;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return province+"";
	}
	
	

}
