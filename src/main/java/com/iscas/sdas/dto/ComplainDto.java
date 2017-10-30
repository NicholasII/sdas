package com.iscas.sdas.dto;

import java.sql.Timestamp;

public class ComplainDto {
	
	private Timestamp time;
	private String cellname;
	private int countnum;
	private String daynum;
	private String starttime;
	private String endtime;
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
	public String getDaynum() {
		return daynum;
	}
	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	

}
