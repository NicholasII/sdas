package com.iscas.sdas.common;

import java.util.List;

/**
 * 分页dto
 * @author dongqun
 * 2017年11月1日下午2:22:40
 * @param <T>
 */
public class PageDto<T> {
	
	private long total; //总记录数
    private List<T> rows; // 当前页记录List 

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
