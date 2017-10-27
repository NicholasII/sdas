package com.iscas.sdas.dto.cell;

import java.util.List;

public class CellHealthTableDto {

	private String yyyyMMdd;
	private List<MomentDto> moments;
	/*private double range_00, range_01, range_02, range_03, range_04, range_05, range_06, range_07, range_08, range_09,
			range_10, range_11, range_12, range_13, range_14, range_15, range_16, range_17, range_18, range_19,
			range_20, range_21, range_22, range_23;*/
	public String getYyyyMMdd() {
		return yyyyMMdd;
	}
	public void setYyyyMMdd(String yyyyMMdd) {
		this.yyyyMMdd = yyyyMMdd;
	}
	public List<MomentDto> getMoments() {
		return moments;
	}
	public void setMoments(List<MomentDto> moments) {
		this.moments = moments;
	}
	
}
