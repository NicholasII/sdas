package com.iscas.sdas.dto;

public class StationInfoDto {
    private String stationCode;

    private String stationName;

    private String stationNodeid;

    private String stationLatitude;

    private String stationLongitude;

    private String stationInfo;

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public String getStationNodeid() {
        return stationNodeid;
    }

    public void setStationNodeid(String stationNodeid) {
        this.stationNodeid = stationNodeid == null ? null : stationNodeid.trim();
    }

    public String getStationLatitude() {
        return stationLatitude;
    }

    public void setStationLatitude(String stationLatitude) {
        this.stationLatitude = stationLatitude == null ? null : stationLatitude.trim();
    }

    public String getStationLongitude() {
        return stationLongitude;
    }

    public void setStationLongitude(String stationLongitude) {
        this.stationLongitude = stationLongitude == null ? null : stationLongitude.trim();
    }

    public String getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(String stationInfo) {
        this.stationInfo = stationInfo == null ? null : stationInfo.trim();
    }
}