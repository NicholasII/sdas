package com.iscas.sdas.dto;
/**
 * 时间、历史健康度、工单、投诉、预警名称和个数
 * @author dongqun
 * 2017年10月10日下午5:01:12
 */
public class TotalHealthInfoDto {

	private String time;
	private double ratio;
	private int perworks;
	private int deviceworks;
	private int osworks;
	private int complaints;
	private String alarm_name;
	private int alarm_counts;
	private Integer result_fault;//app_result
	private Integer result_warnning;//app_result
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	public int getPerworks() {
		return perworks;
	}
	public void setPerworks(int perworks) {
		this.perworks = perworks;
	}
	public int getDeviceworks() {
		return deviceworks;
	}
	public void setDeviceworks(int deviceworks) {
		this.deviceworks = deviceworks;
	}
	public int getOsworks() {
		return osworks;
	}
	public void setOsworks(int osworks) {
		this.osworks = osworks;
	}
	public int getComplaints() {
		return complaints;
	}
	public void setComplaints(int complaints) {
		this.complaints = complaints;
	}
	public String getAlarm_name() {
		return alarm_name;
	}
	public void setAlarm_name(String alarm_name) {
		this.alarm_name = alarm_name;
	}
	public int getAlarm_counts() {
		return alarm_counts;
	}
	public void setAlarm_counts(int alarm_counts) {
		this.alarm_counts = alarm_counts;
	}
	public Integer getResult_fault() {
		return result_fault;
	}
	public void setResult_fault(Integer result_fault) {
		this.result_fault = result_fault;
	}
	public Integer getResult_warnning() {
		return result_warnning;
	}
	public void setResult_warnning(Integer result_warnning) {
		this.result_warnning = result_warnning;
	}
}
