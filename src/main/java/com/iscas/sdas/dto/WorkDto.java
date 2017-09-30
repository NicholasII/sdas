package com.iscas.sdas.dto;

import java.sql.Timestamp;

public class WorkDto {
	
	private Timestamp occurrence_time;
	
	//private String monitor_content;

	public Timestamp getOccurrence_time() {
		return occurrence_time;
	}

	public void setOccurrence_time(Timestamp occurrence_time) {
		this.occurrence_time = occurrence_time;
	}

	/*public String getMonitor_content() {
		return monitor_content;
	}

	public void setMonitor_content(String monitor_content) {
		this.monitor_content = monitor_content;
	}*/
	
	

}
