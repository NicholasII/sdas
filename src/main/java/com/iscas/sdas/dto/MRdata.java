package com.iscas.sdas.dto;

import java.util.Date;
/**
 * MR指标信息
 * @author Administrator
 *
 */
public class MRdata {
    private String city;

    private Date time;

    private String company;

    private String aNetCity;

    private String aNetId;

    private String bNetCity;

    private String bNetId;

    private String cNetCity;

    private String cNetId;

    private String cgi;

    private String cellName;

    private String coverScene;

    private String usedBand;

    private Integer centerBand;

    private Integer mrsNum;

    private Integer mrsGreater105dbmNum;

    private Integer mrsGreater110dbmNum;

    private Integer mrsGreater105dbmCover;

    private Integer mrsGreater110dbmCover;

    private Integer mroNum;

    private Integer mroGreater105dbmNum;

    private Integer mroGreater110dbmNum;

    private Double mroGreater105dbmCover;

    private Double mroGreater110dbmCover;

    private Integer db6Inter3Num;

    private String db6Inter3Ratio;

    private Integer db6Inter3NumSame;

    private String db6Inter3RatioSame;

    private Integer db6Inter1NumSame;

    private String db6Inter1RatioSame;

    private String mro;

    private String mrs;

    private String hightCoverCellNum;

    private String cellhightCoverCellNum;

    private String sameHightCoverCellNum;

    private String uplinkPercentage;

    private String sinrAver;

    private String ueAver;

    private String mixLiveCoverage;

    private String liveCoverageSame;

    private String overlappingCoverage15;

    private String overlappingCoverage10;

    private String overlappingCoverage5;

    private String mrs20;

    private String mrs10;

    private String mro20;

    private String mro10;

    private String liveCoverage;

    private String liveCoverage16;

    private String liveCoverageHight;

    private String highCellCi1Count;

    private String uplinkQci1Num;

    private String uplinkQci1Packet;

    private String uplinkCellName;

    private String downlinkQci1Count;

    private String downlinkQci1Num;

    private String downlinkQci1;

    private String downlinkQci1Cellname;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getaNetCity() {
        return aNetCity;
    }

    public void setaNetCity(String aNetCity) {
        this.aNetCity = aNetCity == null ? null : aNetCity.trim();
    }

    public String getaNetId() {
        return aNetId;
    }

    public void setaNetId(String aNetId) {
        this.aNetId = aNetId == null ? null : aNetId.trim();
    }

    public String getbNetCity() {
        return bNetCity;
    }

    public void setbNetCity(String bNetCity) {
        this.bNetCity = bNetCity == null ? null : bNetCity.trim();
    }

    public String getbNetId() {
        return bNetId;
    }

    public void setbNetId(String bNetId) {
        this.bNetId = bNetId == null ? null : bNetId.trim();
    }

    public String getcNetCity() {
        return cNetCity;
    }

    public void setcNetCity(String cNetCity) {
        this.cNetCity = cNetCity == null ? null : cNetCity.trim();
    }

    public String getcNetId() {
        return cNetId;
    }

    public void setcNetId(String cNetId) {
        this.cNetId = cNetId == null ? null : cNetId.trim();
    }

    public String getCgi() {
        return cgi;
    }

    public void setCgi(String cgi) {
        this.cgi = cgi == null ? null : cgi.trim();
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName == null ? null : cellName.trim();
    }

    public String getCoverScene() {
        return coverScene;
    }

    public void setCoverScene(String coverScene) {
        this.coverScene = coverScene == null ? null : coverScene.trim();
    }

    public String getUsedBand() {
        return usedBand;
    }

    public void setUsedBand(String usedBand) {
        this.usedBand = usedBand == null ? null : usedBand.trim();
    }

    public Integer getCenterBand() {
        return centerBand;
    }

    public void setCenterBand(Integer centerBand) {
        this.centerBand = centerBand;
    }

    public Integer getMrsNum() {
        return mrsNum;
    }

    public void setMrsNum(Integer mrsNum) {
        this.mrsNum = mrsNum;
    }

    public Integer getMrsGreater105dbmNum() {
        return mrsGreater105dbmNum;
    }

    public void setMrsGreater105dbmNum(Integer mrsGreater105dbmNum) {
        this.mrsGreater105dbmNum = mrsGreater105dbmNum;
    }

    public Integer getMrsGreater110dbmNum() {
        return mrsGreater110dbmNum;
    }

    public void setMrsGreater110dbmNum(Integer mrsGreater110dbmNum) {
        this.mrsGreater110dbmNum = mrsGreater110dbmNum;
    }

    public Integer getMrsGreater105dbmCover() {
        return mrsGreater105dbmCover;
    }

    public void setMrsGreater105dbmCover(Integer mrsGreater105dbmCover) {
        this.mrsGreater105dbmCover = mrsGreater105dbmCover;
    }

    public Integer getMrsGreater110dbmCover() {
        return mrsGreater110dbmCover;
    }

    public void setMrsGreater110dbmCover(Integer mrsGreater110dbmCover) {
        this.mrsGreater110dbmCover = mrsGreater110dbmCover;
    }

    public Integer getMroNum() {
        return mroNum;
    }

    public void setMroNum(Integer mroNum) {
        this.mroNum = mroNum;
    }

    public Integer getMroGreater105dbmNum() {
        return mroGreater105dbmNum;
    }

    public void setMroGreater105dbmNum(Integer mroGreater105dbmNum) {
        this.mroGreater105dbmNum = mroGreater105dbmNum;
    }

    public Integer getMroGreater110dbmNum() {
        return mroGreater110dbmNum;
    }

    public void setMroGreater110dbmNum(Integer mroGreater110dbmNum) {
        this.mroGreater110dbmNum = mroGreater110dbmNum;
    }

    public Double getMroGreater105dbmCover() {
        return mroGreater105dbmCover;
    }

    public void setMroGreater105dbmCover(Double mroGreater105dbmCover) {
        this.mroGreater105dbmCover = mroGreater105dbmCover;
    }

    public Double getMroGreater110dbmCover() {
        return mroGreater110dbmCover;
    }

    public void setMroGreater110dbmCover(Double mroGreater110dbmCover) {
        this.mroGreater110dbmCover = mroGreater110dbmCover;
    }

    public Integer getDb6Inter3Num() {
        return db6Inter3Num;
    }

    public void setDb6Inter3Num(Integer db6Inter3Num) {
        this.db6Inter3Num = db6Inter3Num;
    }

    public String getDb6Inter3Ratio() {
        return db6Inter3Ratio;
    }

    public void setDb6Inter3Ratio(String db6Inter3Ratio) {
        this.db6Inter3Ratio = db6Inter3Ratio == null ? null : db6Inter3Ratio.trim();
    }

    public Integer getDb6Inter3NumSame() {
        return db6Inter3NumSame;
    }

    public void setDb6Inter3NumSame(Integer db6Inter3NumSame) {
        this.db6Inter3NumSame = db6Inter3NumSame;
    }

    public String getDb6Inter3RatioSame() {
        return db6Inter3RatioSame;
    }

    public void setDb6Inter3RatioSame(String db6Inter3RatioSame) {
        this.db6Inter3RatioSame = db6Inter3RatioSame == null ? null : db6Inter3RatioSame.trim();
    }

    public Integer getDb6Inter1NumSame() {
        return db6Inter1NumSame;
    }

    public void setDb6Inter1NumSame(Integer db6Inter1NumSame) {
        this.db6Inter1NumSame = db6Inter1NumSame;
    }

    public String getDb6Inter1RatioSame() {
        return db6Inter1RatioSame;
    }

    public void setDb6Inter1RatioSame(String db6Inter1RatioSame) {
        this.db6Inter1RatioSame = db6Inter1RatioSame == null ? null : db6Inter1RatioSame.trim();
    }

    public String getMro() {
        return mro;
    }

    public void setMro(String mro) {
        this.mro = mro == null ? null : mro.trim();
    }

    public String getMrs() {
        return mrs;
    }

    public void setMrs(String mrs) {
        this.mrs = mrs == null ? null : mrs.trim();
    }

    public String getHightCoverCellNum() {
        return hightCoverCellNum;
    }

    public void setHightCoverCellNum(String hightCoverCellNum) {
        this.hightCoverCellNum = hightCoverCellNum == null ? null : hightCoverCellNum.trim();
    }

    public String getCellhightCoverCellNum() {
        return cellhightCoverCellNum;
    }

    public void setCellhightCoverCellNum(String cellhightCoverCellNum) {
        this.cellhightCoverCellNum = cellhightCoverCellNum == null ? null : cellhightCoverCellNum.trim();
    }

    public String getSameHightCoverCellNum() {
        return sameHightCoverCellNum;
    }

    public void setSameHightCoverCellNum(String sameHightCoverCellNum) {
        this.sameHightCoverCellNum = sameHightCoverCellNum == null ? null : sameHightCoverCellNum.trim();
    }

    public String getUplinkPercentage() {
        return uplinkPercentage;
    }

    public void setUplinkPercentage(String uplinkPercentage) {
        this.uplinkPercentage = uplinkPercentage == null ? null : uplinkPercentage.trim();
    }

    public String getSinrAver() {
        return sinrAver;
    }

    public void setSinrAver(String sinrAver) {
        this.sinrAver = sinrAver == null ? null : sinrAver.trim();
    }

    public String getUeAver() {
        return ueAver;
    }

    public void setUeAver(String ueAver) {
        this.ueAver = ueAver == null ? null : ueAver.trim();
    }

    public String getMixLiveCoverage() {
        return mixLiveCoverage;
    }

    public void setMixLiveCoverage(String mixLiveCoverage) {
        this.mixLiveCoverage = mixLiveCoverage == null ? null : mixLiveCoverage.trim();
    }

    public String getLiveCoverageSame() {
        return liveCoverageSame;
    }

    public void setLiveCoverageSame(String liveCoverageSame) {
        this.liveCoverageSame = liveCoverageSame == null ? null : liveCoverageSame.trim();
    }

    public String getOverlappingCoverage15() {
        return overlappingCoverage15;
    }

    public void setOverlappingCoverage15(String overlappingCoverage15) {
        this.overlappingCoverage15 = overlappingCoverage15 == null ? null : overlappingCoverage15.trim();
    }

    public String getOverlappingCoverage10() {
        return overlappingCoverage10;
    }

    public void setOverlappingCoverage10(String overlappingCoverage10) {
        this.overlappingCoverage10 = overlappingCoverage10 == null ? null : overlappingCoverage10.trim();
    }

    public String getOverlappingCoverage5() {
        return overlappingCoverage5;
    }

    public void setOverlappingCoverage5(String overlappingCoverage5) {
        this.overlappingCoverage5 = overlappingCoverage5 == null ? null : overlappingCoverage5.trim();
    }

    public String getMrs20() {
        return mrs20;
    }

    public void setMrs20(String mrs20) {
        this.mrs20 = mrs20 == null ? null : mrs20.trim();
    }

    public String getMrs10() {
        return mrs10;
    }

    public void setMrs10(String mrs10) {
        this.mrs10 = mrs10 == null ? null : mrs10.trim();
    }

    public String getMro20() {
        return mro20;
    }

    public void setMro20(String mro20) {
        this.mro20 = mro20 == null ? null : mro20.trim();
    }

    public String getMro10() {
        return mro10;
    }

    public void setMro10(String mro10) {
        this.mro10 = mro10 == null ? null : mro10.trim();
    }

    public String getLiveCoverage() {
        return liveCoverage;
    }

    public void setLiveCoverage(String liveCoverage) {
        this.liveCoverage = liveCoverage == null ? null : liveCoverage.trim();
    }

    public String getLiveCoverage16() {
        return liveCoverage16;
    }

    public void setLiveCoverage16(String liveCoverage16) {
        this.liveCoverage16 = liveCoverage16 == null ? null : liveCoverage16.trim();
    }

    public String getLiveCoverageHight() {
        return liveCoverageHight;
    }

    public void setLiveCoverageHight(String liveCoverageHight) {
        this.liveCoverageHight = liveCoverageHight == null ? null : liveCoverageHight.trim();
    }

    public String getHighCellCi1Count() {
        return highCellCi1Count;
    }

    public void setHighCellCi1Count(String highCellCi1Count) {
        this.highCellCi1Count = highCellCi1Count == null ? null : highCellCi1Count.trim();
    }

    public String getUplinkQci1Num() {
        return uplinkQci1Num;
    }

    public void setUplinkQci1Num(String uplinkQci1Num) {
        this.uplinkQci1Num = uplinkQci1Num == null ? null : uplinkQci1Num.trim();
    }

    public String getUplinkQci1Packet() {
        return uplinkQci1Packet;
    }

    public void setUplinkQci1Packet(String uplinkQci1Packet) {
        this.uplinkQci1Packet = uplinkQci1Packet == null ? null : uplinkQci1Packet.trim();
    }

    public String getUplinkCellName() {
        return uplinkCellName;
    }

    public void setUplinkCellName(String uplinkCellName) {
        this.uplinkCellName = uplinkCellName == null ? null : uplinkCellName.trim();
    }

    public String getDownlinkQci1Count() {
        return downlinkQci1Count;
    }

    public void setDownlinkQci1Count(String downlinkQci1Count) {
        this.downlinkQci1Count = downlinkQci1Count == null ? null : downlinkQci1Count.trim();
    }

    public String getDownlinkQci1Num() {
        return downlinkQci1Num;
    }

    public void setDownlinkQci1Num(String downlinkQci1Num) {
        this.downlinkQci1Num = downlinkQci1Num == null ? null : downlinkQci1Num.trim();
    }

    public String getDownlinkQci1() {
        return downlinkQci1;
    }

    public void setDownlinkQci1(String downlinkQci1) {
        this.downlinkQci1 = downlinkQci1 == null ? null : downlinkQci1.trim();
    }

    public String getDownlinkQci1Cellname() {
        return downlinkQci1Cellname;
    }

    public void setDownlinkQci1Cellname(String downlinkQci1Cellname) {
        this.downlinkQci1Cellname = downlinkQci1Cellname == null ? null : downlinkQci1Cellname.trim();
    }
}