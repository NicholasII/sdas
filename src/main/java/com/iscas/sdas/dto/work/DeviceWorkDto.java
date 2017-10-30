package com.iscas.sdas.dto.work;

import java.sql.Timestamp;

public class DeviceWorkDto {

	private String order_title;
	private Timestamp build_bill_time;
	private String net_alarm_name;
	private Timestamp net_remove_time;
	private String net_alarm_source;
	private String cell_name;
	private String fault_occus_time;
	private String transt_reason;
	
	private String daynum;
	private String starttime;
	private String endtime;
	
	public String getOrder_title() {
		return order_title;
	}
	public void setOrder_title(String order_title) {
		this.order_title = order_title;
	}
	public Timestamp getBuild_bill_time() {
		return build_bill_time;
	}
	public void setBuild_bill_time(Timestamp build_bill_time) {
		this.build_bill_time = build_bill_time;
	}
	public String getNet_alarm_name() {
		return net_alarm_name;
	}
	public void setNet_alarm_name(String net_alarm_name) {
		this.net_alarm_name = net_alarm_name;
	}
	public Timestamp getNet_remove_time() {
		return net_remove_time;
	}
	public void setNet_remove_time(Timestamp net_remove_time) {
		this.net_remove_time = net_remove_time;
	}
	public String getNet_alarm_source() {
		return net_alarm_source;
	}
	public void setNet_alarm_source(String net_alarm_source) {
		this.net_alarm_source = net_alarm_source;
	}
	public String getCell_name() {
		return cell_name;
	}
	public void setCell_name(String cell_name) {
		this.cell_name = cell_name;
	}
	public String getFault_occus_time() {
		return fault_occus_time;
	}
	public void setFault_occus_time(String fault_occus_time) {
		this.fault_occus_time = fault_occus_time;
	}
	public String getTranst_reason() {
		return transt_reason;
	}
	public void setTranst_reason(String transt_reason) {
		this.transt_reason = transt_reason;
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
