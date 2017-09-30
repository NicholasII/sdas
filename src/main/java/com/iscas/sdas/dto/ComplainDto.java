package com.iscas.sdas.dto;

import java.sql.Timestamp;

public class ComplainDto {
	
	private Timestamp time;
	private String cellname;
	private int countnum;

	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getCellname() {
		return cellname;
	}
	public void setCellname(String cellname) {
		this.cellname = cellname;
	}
	public int getCountnum() {
		return countnum;
	}
	public void setCountnum(int countnum) {
		this.countnum = countnum;
	}
	
	

}
