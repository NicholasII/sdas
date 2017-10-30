package com.iscas.sdas.common;

import java.util.List;

public class PageDto<T> {
	
	//public static final int DEFAULT_PAGE_SIZE = 10;  	  
    //protected int page; // 当前页, 默认为第1页  
    //protected int pageSize = DEFAULT_PAGE_SIZE; // 每页记录数  
    //protected long total; // 总记录数, 默认为-1, 表示需要查询  
    //protected String pages; // 总页数, 默认为-1, 表示需要计算   
	private long total;
    private List<T> rows; // 当前页记录List形式  

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

	  
    /*public Map<String, Object> params = new HashMap<String, Object>();//设置页面传递的查询参数  
  
    public Map<String, Object> getParams() {  
        return params;  
    }  
  
    public void setParams(Map<String, Object> params) {  
        this.params = params;  
    }*/  
  
  
    /*@Override  
    public String toString() {  
        StringBuilder builder = new StringBuilder().append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize)  
                .append(", totalRecord=").append(totalRecord < 0 ? "null" : totalRecord).append(", totalPage=")  
                .append(totalPage < 0 ? "null" : totalPage).append(", curPageObjects=").append(results == null ? "null" : results.size()).append("]");  
        return builder.toString();  
    }*/
}
