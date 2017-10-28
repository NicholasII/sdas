package com.iscas.sdas.dto.cell;
/**
 * 小组指标
 * @author Administrator
 *
 */
public class BaseGroupIndex extends Base{

	private String indeicator_code;

	private String cell_code;

	private String group2_code;

	public String getCell_code() {
		return cell_code;
	}

	public void setCell_code(String cell_code) {
		this.cell_code = cell_code;
	}

	public String getGroup2_code() {
		return group2_code;
	}

	public void setGroup2_code(String group2_code) {
		this.group2_code = group2_code;
	}

	public String getIndeicator_code() {
		return indeicator_code;
	}

	public void setIndeicator_code(String indeicator_code) {
		this.indeicator_code = indeicator_code;
	}

	

}
