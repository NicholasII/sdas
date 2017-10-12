package com.iscas.sdas.dto;
/**
 * 历史健康度信息；包括健康度和各类工单信息和报警
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
	
	
}
