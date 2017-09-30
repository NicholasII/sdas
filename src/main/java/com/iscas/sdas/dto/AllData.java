package com.iscas.sdas.dto;
/**
 * 小区内所有类型的指标：包括mr 硬采 软才 网管等
 * @author Administrator
 *
 */
public class AllData {

	private int id;
	
	private String type;
	
	private String dataname;
	
	private String datavalue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDataname() {
		return dataname;
	}

	public void setDataname(String dataname) {
		this.dataname = dataname;
	}

	public String getDatavalue() {
		return datavalue;
	}

	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}
	
	
}
