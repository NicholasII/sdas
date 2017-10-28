package com.iscas.sdas.dto.cell;
/**
 * 小区指标
 * @author Administrator
 *
 */
public class BaseIndex extends Base{

	private String indeicator_name;
	
	private String cell_code;
	
	
	public String getIndeicator_name() {
		return indeicator_name;
	}

	public void setIndeicator_name(String indeicator_name) {
		this.indeicator_name = indeicator_name;
	}

	public String getCell_code() {
		return cell_code;
	}

	public void setCell_code(String cell_code) {
		this.cell_code = cell_code;
	}
	
}
