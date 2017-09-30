package com.iscas.sdas.dao.cell;

import java.util.List;

import com.iscas.sdas.dto.cell.BaseIndex;
import com.iscas.sdas.dto.cell.BaseWeight;

public interface CellIndexDao {
	
	BaseIndex getOriginIndex(Integer index,String cellname);
	
	List<String> getRealTimeData(Integer index);
	
	BaseWeight getCellWeight(String cellname);

}
