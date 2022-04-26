package model;

public class Interrupt {

	int idinterrupt;
	String province;
	String district;
	String date;
	Double interruptTime;
	Double restoreTime;
	
	public Interrupt() {
	}

	public Interrupt(int idinterrupt, String province, String district, String date, Double interruptTime, Double restoreTime) {
		super();
		this.idinterrupt = idinterrupt;
		this.province = province;
		this.district = district;
		this.date = date;
		this.interruptTime = interruptTime;
		this.restoreTime = restoreTime;
	}

	public int getIdinterrupt() {
		return idinterrupt;
	}

	public void setIdinterrupt(int idinterrupt) {
		this.idinterrupt = idinterrupt;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getInterruptTime() {
		return interruptTime;
	}

	public void setInterruptTime(Double interruptTime) {
		this.interruptTime = interruptTime;
	}

	public Double getRestoreTime() {
		return restoreTime;
	}

	public void setRestoreTime(Double restoreTime) {
		this.restoreTime = restoreTime;
	}
	
	
	
}
