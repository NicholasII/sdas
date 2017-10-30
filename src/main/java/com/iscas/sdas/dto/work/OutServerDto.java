package com.iscas.sdas.dto.work;

import java.sql.Timestamp;

public class OutServerDto {

	private Timestamp start_time;
	private Timestamp recovery_time;
	private String fault_type;
	private String vip_type;
	private String cell_name;
	private String station_name;
	private String daynum;
	private String starttime;
	private String endtime;
	
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getRecovery_time() {
		return recovery_time;
	}
	public void setRecovery_time(Timestamp recovery_time) {
		this.recovery_time = recovery_time;
	}
	public String getFault_type() {
		return fault_type;
	}
	public void setFault_type(String fault_type) {
		this.fault_type = fault_type;
	}
	public String getVip_type() {
		return vip_type;
	}
	public void setVip_type(String vip_type) {
		this.vip_type = vip_type;
	}
	public String getCell_name() {
		return cell_name;
	}
	public void setCell_name(String cell_name) {
		this.cell_name = cell_name;
	}
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
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
