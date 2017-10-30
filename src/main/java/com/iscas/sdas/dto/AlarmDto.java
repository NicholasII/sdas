package com.iscas.sdas.dto;

public class AlarmDto {

	private String cell_code;
	
	private String yyyyMMdd;
	
	private int count;
	
	private String app_hour;
	
	private int app_result;
	private String daynum;
	private String starttime;
	private String endtime;
	

	public String getCell_code() {
		return cell_code;
	}

	public void setCell_code(String cell_code) {
		this.cell_code = cell_code;
	}

	public String getYyyyMMdd() {
		return yyyyMMdd;
	}

	public void setYyyyMMdd(String yyyyMMdd) {
		this.yyyyMMdd = yyyyMMdd;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getApp_hour() {
		return app_hour;
	}

	public void setApp_hour(String app_hour) {
		this.app_hour = app_hour;
	}

	public int getApp_result() {
		return app_result;
	}

	public void setApp_result(int app_result) {
		this.app_result = app_result;
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
