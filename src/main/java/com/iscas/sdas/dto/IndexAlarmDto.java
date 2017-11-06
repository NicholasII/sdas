package com.iscas.sdas.dto;

import com.iscas.sdas.common.BaseDto;

public class IndexAlarmDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cell_code;
	
	private String yyyyMM;
	
	private String yyyyMMdd;
	
	private int count;
	
	private String app_hour;
	
	private int app_result;
	
	private int app_code;
	
	private int app_type;
	
	private String type;

	private String daynum;
	private String starttime;
	private String endtime;
	private String create_time;
	
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

	public String getYyyyMM() {
		return yyyyMM;
	}

	public void setYyyyMM(String yyyyMM) {
		this.yyyyMM = yyyyMM;
	}

	public int getApp_code() {
		return app_code;
	}

	public void setApp_code(int app_code) {
		this.app_code = app_code;
	}

	public int getApp_type() {
		return app_type;
	}

	public void setApp_type(int app_type) {
		this.app_type = app_type;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
