package com.iscas.sdas.dto;
/**
 * 表索引
 * @author Administrator
 *
 */
public class TableInfoDto {
    private String tableName;

    private String columnName;

    private String ordinalPosition;

    private String columnType;

    private String columnComment;

    private Integer uiUsedflag;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(String ordinalPosition) {
        this.ordinalPosition = ordinalPosition == null ? null : ordinalPosition.trim();
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment == null ? null : columnComment.trim();
    }

    public Integer getUiUsedflag() {
        return uiUsedflag;
    }

    public void setUiUsedflag(Integer uiUsedflag) {
        this.uiUsedflag = uiUsedflag;
    }
}