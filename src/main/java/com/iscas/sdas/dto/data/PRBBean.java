package com.iscas.sdas.dto.data;

import java.sql.Timestamp;

public class PRBBean {

	private Timestamp timestamp;
	private String rrc;
	private String upprb;
	private String downprb;
	private String switchs;
	private String uecounts;
	private String pucch;
	private String cce;
	private String puschprb;
	private String uprealprb;
	private String yymon;
	private String yysucces;
	private String yyrrc;
	private String yywire;
	private Integer daynum;
	private String starttime;
	private String endtime;
	private String cellname;
	private String monitor_value;
	private String reasons;
	private String lasttime;
	
	public String getSwitchs() {
		return switchs;
	}
	public void setSwitchs(String switchs) {
		this.switchs = switchs;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getRrc() {
		return rrc;
	}
	public void setRrc(String rrc) {
		this.rrc = rrc;
	}
	public String getUpprb() {
		return upprb;
	}
	public void setUpprb(String upprb) {
		this.upprb = upprb;
	}
	public String getDownprb() {
		return downprb;
	}
	public void setDownprb(String downprb) {
		this.downprb = downprb;
	}
	public String getUecounts() {
		return uecounts;
	}
	public void setUecounts(String uecounts) {
		this.uecounts = uecounts;
	}
	public String getPucch() {
		return pucch;
	}
	public void setPucch(String pucch) {
		this.pucch = pucch;
	}
	public String getCce() {
		return cce;
	}
	public void setCce(String cce) {
		this.cce = cce;
	}
	public String getPuschprb() {
		return puschprb;
	}
	public void setPuschprb(String puschprb) {
		this.puschprb = puschprb;
	}
	public String getUprealprb() {
		return uprealprb;
	}
	public void setUprealprb(String uprealprb) {
		this.uprealprb = uprealprb;
	}
	public String getYyrrc() {
		return yyrrc;
	}
	public void setYyrrc(String yyrrc) {
		this.yyrrc = yyrrc;
	}
	public String getYywire() {
		return yywire;
	}
	public void setYywire(String yywire) {
		this.yywire = yywire;
	}
	public String getYymon() {
		return yymon;
	}
	public void setYymon(String yymon) {
		this.yymon = yymon;
	}
	public String getYysucces() {
		return yysucces;
	}
	public void setYysucces(String yysucces) {
		this.yysucces = yysucces;
	}
	public Integer getDaynum() {
		return daynum;
	}
	public void setDaynum(Integer daynum) {
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
	public String getMonitor_value() {
		return monitor_value;
	}
	public void setMonitor_value(String monitor_value) {
		this.monitor_value = monitor_value;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public String getCellname() {
		return cellname;
	}
	public void setCellname(String cellname) {
		this.cellname = cellname;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	
}
