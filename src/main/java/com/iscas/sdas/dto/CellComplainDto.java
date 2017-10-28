package com.iscas.sdas.dto;

import java.util.Date;

import com.iscas.sdas.common.PageDto;
/**
 * 投诉信息dto
 * @author dongqun
 * 2017年10月13日上午11:14:30
 */
public class CellComplainDto{

	private Date record_time;
	private String phone_number;
	private String live_cellname1,live_cellname2,live_cellname3;
	private String complaint_detailinfo;
	private String servicerequesttype;
	public Date getRecord_time() {
		return record_time;
	}
	public void setRecord_time(Date record_time) {
		this.record_time = record_time;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getLive_cellname1() {
		return live_cellname1;
	}
	public void setLive_cellname1(String live_cellname1) {
		this.live_cellname1 = live_cellname1;
	}
	public String getLive_cellname2() {
		return live_cellname2;
	}
	public void setLive_cellname2(String live_cellname2) {
		this.live_cellname2 = live_cellname2;
	}
	public String getLive_cellname3() {
		return live_cellname3;
	}
	public void setLive_cellname3(String live_cellname3) {
		this.live_cellname3 = live_cellname3;
	}
	public String getComplaint_detailinfo() {
		return complaint_detailinfo;
	}
	public void setComplaint_detailinfo(String complaint_detailinfo) {
		this.complaint_detailinfo = complaint_detailinfo;
	}
	public String getServicerequesttype() {
		return servicerequesttype;
	}
	public void setServicerequesttype(String servicerequesttype) {
		this.servicerequesttype = servicerequesttype;
	}
}
