package geneator;

import geneator.PerformanceWorkDto;

public interface PerformanceWorkDtoMapper {
    int deleteByPrimaryKey(Integer alarmId);

    int insert(PerformanceWorkDto record);

    int insertSelective(PerformanceWorkDto record);

    PerformanceWorkDto selectByPrimaryKey(Integer alarmId);

    int updateByPrimaryKeySelective(PerformanceWorkDto record);

    int updateByPrimaryKey(PerformanceWorkDto record);
}