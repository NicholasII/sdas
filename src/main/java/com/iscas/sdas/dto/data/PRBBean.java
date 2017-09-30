package com.iscas.sdas.dto.data;

import java.sql.Timestamp;

public class PRBBean {

	private Timestamp timestamp;
	private String rrc;
	private String upprb;
	private String downprb;
	private String switchs;
	public String getSwitchs() {
		return switchs;
	}
	public void setSwitchs(String switchs) {
		this.switchs = switchs;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getRrc() {
		return rrc;
	}
	public void setRrc(String rrc) {
		this.rrc = rrc;
	}
	public String getUpprb() {
		return upprb;
	}
	public void setUpprb(String upprb) {
		this.upprb = upprb;
	}
	public String getDownprb() {
		return downprb;
	}
	public void setDownprb(String downprb) {
		this.downprb = downprb;
	}
	
	
}
