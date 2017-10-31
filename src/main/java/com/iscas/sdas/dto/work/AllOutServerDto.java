package com.iscas.sdas.dto.work;

import java.util.Date;

public class AllOutServerDto {
    private String netType;

    private String alarmId;

    private Date startTime;

    private Date recoveryTime;

    private String faultType;

    private String orderState;

    private String vipType;

    private String netName;

    private String cellName;

    private String stationName;

    private String retireOrder;

    private String manumantGroup;

    private String eventLog;

    private String physicalSiteId;

    private String physicalSiteName;

    private String deviceMaintainCompany;

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType == null ? null : netType.trim();
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId == null ? null : alarmId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getRecoveryTime() {
        return recoveryTime;
    }

    public void setRecoveryTime(Date recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType == null ? null : faultType.trim();
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType == null ? null : vipType.trim();
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName == null ? null : netName.trim();
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName == null ? null : cellName.trim();
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public String getRetireOrder() {
        return retireOrder;
    }

    public void setRetireOrder(String retireOrder) {
        this.retireOrder = retireOrder == null ? null : retireOrder.trim();
    }

    public String getManumantGroup() {
        return manumantGroup;
    }

    public void setManumantGroup(String manumantGroup) {
        this.manumantGroup = manumantGroup == null ? null : manumantGroup.trim();
    }

    public String getEventLog() {
        return eventLog;
    }

    public void setEventLog(String eventLog) {
        this.eventLog = eventLog == null ? null : eventLog.trim();
    }

    public String getPhysicalSiteId() {
        return physicalSiteId;
    }

    public void setPhysicalSiteId(String physicalSiteId) {
        this.physicalSiteId = physicalSiteId == null ? null : physicalSiteId.trim();
    }

    public String getPhysicalSiteName() {
        return physicalSiteName;
    }

    public void setPhysicalSiteName(String physicalSiteName) {
        this.physicalSiteName = physicalSiteName == null ? null : physicalSiteName.trim();
    }

    public String getDeviceMaintainCompany() {
        return deviceMaintainCompany;
    }

    public void setDeviceMaintainCompany(String deviceMaintainCompany) {
        this.deviceMaintainCompany = deviceMaintainCompany == null ? null : deviceMaintainCompany.trim();
    }
}