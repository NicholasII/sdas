package com.iscas.sdas.dto.work;

import java.sql.Timestamp;

public class CapacityWorkDto {
	
	private Timestamp occurrence_time;
	private Integer alarm_id;
	private String cellid;
	private String boutique_level;
	private String limit_times;
	private String belong_area;
	private String reasons;
	private String monitor_content;
	private String monitor_value;
	private String alerm_level;
	private Timestamp complete_time;
	private Integer questionflag;
	private String daynum;
	private String starttime;
	private String endtime;
	public Timestamp getOccurrence_time() {
		return occurrence_time;
	}
	public void setOccurrence_time(Timestamp occurrence_time) {
		this.occurrence_time = occurrence_time;
	}
	public String getCellid() {
		return cellid;
	}
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
	public String getBoutique_level() {
		return boutique_level;
	}
	public void setBoutique_level(String boutique_level) {
		this.boutique_level = boutique_level;
	}
	public String getLimit_times() {
		return limit_times;
	}
	public void setLimit_times(String limit_times) {
		this.limit_times = limit_times;
	}
	public String getBelong_area() {
		return belong_area;
	}
	public void setBelong_area(String belong_area) {
		this.belong_area = belong_area;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public String getMonitor_content() {
		return monitor_content;
	}
	public void setMonitor_content(String monitor_content) {
		this.monitor_content = monitor_content;
	}
	public String getMonitor_value() {
		return monitor_value;
	}
	public void setMonitor_value(String monitor_value) {
		this.monitor_value = monitor_value;
	}
	public String getAlerm_level() {
		return alerm_level;
	}
	public void setAlerm_level(String alerm_level) {
		this.alerm_level = alerm_level;
	}
	public Timestamp getComplete_time() {
		return complete_time;
	}
	public void setComplete_time(Timestamp complete_time) {
		this.complete_time = complete_time;
	}
	public Integer getAlarm_id() {
		return alarm_id;
	}
	public void setAlarm_id(Integer alarm_id) {
		this.alarm_id = alarm_id;
	}
	
	public Integer getQuestionflag() {
		return questionflag;
	}
	public void setQuestionflag(Integer questionflag) {
		this.questionflag = questionflag;
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
