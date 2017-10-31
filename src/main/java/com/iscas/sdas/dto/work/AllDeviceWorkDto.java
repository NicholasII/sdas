package com.iscas.sdas.dto.work;

import java.util.Date;

public class AllDeviceWorkDto {
    private String orderId;

    private String orderState;

    private String orderTitle;

    private String orderCreatPerson;

    private String orderCreatPersonPart;

    private Date buildBillTime;

    private String netAlarmId;

    private String netAlarmName;

    private Date netRemoveTime;

    private String netAlarmSource;

    private String netAlarmSerialnum;

    private String preTreatedIdentification;

    private String automaticDispatchIdent;

    private String partAcceptIdent;

    private String submitMaintenanceFlag;

    private String deviceMaintenanceCompany;

    private String lineMaintenanceCompany;

    private String alarmLogicType;

    private String alarmLogicChildType;

    private String alarmType;

    private String cellName;

    private String alarmObject;

    private String area;

    private String alarmRelationIdent;

    private Integer childAlarmCount;

    private String netAlarmLevel;

    private String distributeLeafletsMode;

    private String faultDeviceCompany;

    private String orderAcceptTime;

    private String orderAcceptLong;

    private String t1ProcessLong;

    private String t2ProcessLong;

    private String netTypeClassOne;

    private String netTypeClassTwo;

    private String netTypeClassThree;

    private Date faultOccusTime;

    private String faultFindMode;

    private String faultFindTime;

    private String faultOccusCity;

    private String faultHandleCity;

    private String faultCriticalFlag;

    private String t1AcceptTime;

    private String t1TranstObject;

    private String t1TranstSuggestion;

    private String t1TranstPersion;

    private String t1TranstPersonPart;

    private String t1TranstOperTime;

    private String t2AcceptPerson;

    private String t2AcceptTime;

    private String transtReason;

    private String requirementCompleteTime;

    private String taskAcceptTime;

    private String taskAssignPerson;

    private String taskAssignPersonPart;

    private String taskAssignOpteratTime;

    private String workRecoverTime;

    private String influenceLong;

    private String returnReason;

    private String taskRebackPerson;

    private String taskRebackPersonPart;

    private String taskRebackTime;

    private String confirmationOption;

    private String confirmationPerson;

    private String confirmationPersonPart;

    private String taskConfirmTime;

    private String assignReplyFaultName;

    private String assignReplyHandleTime;

    private String assignReplyHandleMethod;

    private String assignReplySolveTime;

    private String applyReportFaultResult;

    private String applyReportFaultType;

    private String applySettlementMeasure;

    private String applyBusinessFlag;

    private String applyPerson;

    private String applyPersonPart;

    private String applyTime;

    private String applyFaultEliminateTime;

    private String warmTime;

    private String processTime;

    private String powerStartTime;

    private String powerEndTime;

    private String oilEngineNum;

    private String denyFlag;

    private String confirmPerson;

    private String confirmPersonPart;

    private String confirmTime;

    private String denyValidFlag;

    private String applyExtensionFlag;

    private String applyExtensionResion;

    private String expectedResolveTime;

    private String applyExtensionPerson;

    private String applyExtensionPersionPart;

    private String approvalOpinions;

    private String extensionPerson;

    private String stageReplyResponse;

    private String stageReplyTime;

    private String stageReplyPerson;

    private String stageReplyPersonPart;

    private String customerFlag;

    private String customerType;

    private String businessScope;

    private String customeGroupname;

    private String customeGroupnum;

    private String businessSupportLevel;

    private String businessEventImpact;

    private String clientDeviceType;

    private String clientDeviceCompany;

    private String clientDeviceModel;

    private String workOrderTimeout;

    private String assignFlag;

    private String mainOrderFlag;

    private String announceFlag;

    private String announcementNum;

    private String orderFilingTime;

    private String assignReplyPerson;

    private String assignReplyPersonPart;

    private String assignReplyOperTime;

    private String pretreatmentAdvice;

    private String intelligentFlag;

    private String assignOrderMode;

    private String netLevel;

    private String dispatchingRule;

    private String probleRuleOrder;

    private String assignResourceOrder;

    private Integer performanceAlarmChildNum;

    private String stationCompany;

    private String affiliatedComputerRoom;

    private String physicalDeviceType;

    private String orderAutomaticResponsibility;

    private String autoStrantResult;

    private String netClass1;

    private String netClass2;

    private String month;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle == null ? null : orderTitle.trim();
    }

    public String getOrderCreatPerson() {
        return orderCreatPerson;
    }

    public void setOrderCreatPerson(String orderCreatPerson) {
        this.orderCreatPerson = orderCreatPerson == null ? null : orderCreatPerson.trim();
    }

    public String getOrderCreatPersonPart() {
        return orderCreatPersonPart;
    }

    public void setOrderCreatPersonPart(String orderCreatPersonPart) {
        this.orderCreatPersonPart = orderCreatPersonPart == null ? null : orderCreatPersonPart.trim();
    }

    public Date getBuildBillTime() {
        return buildBillTime;
    }

    public void setBuildBillTime(Date buildBillTime) {
        this.buildBillTime = buildBillTime;
    }

    public String getNetAlarmId() {
        return netAlarmId;
    }

    public void setNetAlarmId(String netAlarmId) {
        this.netAlarmId = netAlarmId == null ? null : netAlarmId.trim();
    }

    public String getNetAlarmName() {
        return netAlarmName;
    }

    public void setNetAlarmName(String netAlarmName) {
        this.netAlarmName = netAlarmName == null ? null : netAlarmName.trim();
    }

    public Date getNetRemoveTime() {
        return netRemoveTime;
    }

    public void setNetRemoveTime(Date netRemoveTime) {
        this.netRemoveTime = netRemoveTime;
    }

    public String getNetAlarmSource() {
        return netAlarmSource;
    }

    public void setNetAlarmSource(String netAlarmSource) {
        this.netAlarmSource = netAlarmSource == null ? null : netAlarmSource.trim();
    }

    public String getNetAlarmSerialnum() {
        return netAlarmSerialnum;
    }

    public void setNetAlarmSerialnum(String netAlarmSerialnum) {
        this.netAlarmSerialnum = netAlarmSerialnum == null ? null : netAlarmSerialnum.trim();
    }

    public String getPreTreatedIdentification() {
        return preTreatedIdentification;
    }

    public void setPreTreatedIdentification(String preTreatedIdentification) {
        this.preTreatedIdentification = preTreatedIdentification == null ? null : preTreatedIdentification.trim();
    }

    public String getAutomaticDispatchIdent() {
        return automaticDispatchIdent;
    }

    public void setAutomaticDispatchIdent(String automaticDispatchIdent) {
        this.automaticDispatchIdent = automaticDispatchIdent == null ? null : automaticDispatchIdent.trim();
    }

    public String getPartAcceptIdent() {
        return partAcceptIdent;
    }

    public void setPartAcceptIdent(String partAcceptIdent) {
        this.partAcceptIdent = partAcceptIdent == null ? null : partAcceptIdent.trim();
    }

    public String getSubmitMaintenanceFlag() {
        return submitMaintenanceFlag;
    }

    public void setSubmitMaintenanceFlag(String submitMaintenanceFlag) {
        this.submitMaintenanceFlag = submitMaintenanceFlag == null ? null : submitMaintenanceFlag.trim();
    }

    public String getDeviceMaintenanceCompany() {
        return deviceMaintenanceCompany;
    }

    public void setDeviceMaintenanceCompany(String deviceMaintenanceCompany) {
        this.deviceMaintenanceCompany = deviceMaintenanceCompany == null ? null : deviceMaintenanceCompany.trim();
    }

    public String getLineMaintenanceCompany() {
        return lineMaintenanceCompany;
    }

    public void setLineMaintenanceCompany(String lineMaintenanceCompany) {
        this.lineMaintenanceCompany = lineMaintenanceCompany == null ? null : lineMaintenanceCompany.trim();
    }

    public String getAlarmLogicType() {
        return alarmLogicType;
    }

    public void setAlarmLogicType(String alarmLogicType) {
        this.alarmLogicType = alarmLogicType == null ? null : alarmLogicType.trim();
    }

    public String getAlarmLogicChildType() {
        return alarmLogicChildType;
    }

    public void setAlarmLogicChildType(String alarmLogicChildType) {
        this.alarmLogicChildType = alarmLogicChildType == null ? null : alarmLogicChildType.trim();
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType == null ? null : alarmType.trim();
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName == null ? null : cellName.trim();
    }

    public String getAlarmObject() {
        return alarmObject;
    }

    public void setAlarmObject(String alarmObject) {
        this.alarmObject = alarmObject == null ? null : alarmObject.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAlarmRelationIdent() {
        return alarmRelationIdent;
    }

    public void setAlarmRelationIdent(String alarmRelationIdent) {
        this.alarmRelationIdent = alarmRelationIdent == null ? null : alarmRelationIdent.trim();
    }

    public Integer getChildAlarmCount() {
        return childAlarmCount;
    }

    public void setChildAlarmCount(Integer childAlarmCount) {
        this.childAlarmCount = childAlarmCount;
    }

    public String getNetAlarmLevel() {
        return netAlarmLevel;
    }

    public void setNetAlarmLevel(String netAlarmLevel) {
        this.netAlarmLevel = netAlarmLevel == null ? null : netAlarmLevel.trim();
    }

    public String getDistributeLeafletsMode() {
        return distributeLeafletsMode;
    }

    public void setDistributeLeafletsMode(String distributeLeafletsMode) {
        this.distributeLeafletsMode = distributeLeafletsMode == null ? null : distributeLeafletsMode.trim();
    }

    public String getFaultDeviceCompany() {
        return faultDeviceCompany;
    }

    public void setFaultDeviceCompany(String faultDeviceCompany) {
        this.faultDeviceCompany = faultDeviceCompany == null ? null : faultDeviceCompany.trim();
    }

    public String getOrderAcceptTime() {
        return orderAcceptTime;
    }

    public void setOrderAcceptTime(String orderAcceptTime) {
        this.orderAcceptTime = orderAcceptTime == null ? null : orderAcceptTime.trim();
    }

    public String getOrderAcceptLong() {
        return orderAcceptLong;
    }

    public void setOrderAcceptLong(String orderAcceptLong) {
        this.orderAcceptLong = orderAcceptLong == null ? null : orderAcceptLong.trim();
    }

    public String getT1ProcessLong() {
        return t1ProcessLong;
    }

    public void setT1ProcessLong(String t1ProcessLong) {
        this.t1ProcessLong = t1ProcessLong == null ? null : t1ProcessLong.trim();
    }

    public String getT2ProcessLong() {
        return t2ProcessLong;
    }

    public void setT2ProcessLong(String t2ProcessLong) {
        this.t2ProcessLong = t2ProcessLong == null ? null : t2ProcessLong.trim();
    }

    public String getNetTypeClassOne() {
        return netTypeClassOne;
    }

    public void setNetTypeClassOne(String netTypeClassOne) {
        this.netTypeClassOne = netTypeClassOne == null ? null : netTypeClassOne.trim();
    }

    public String getNetTypeClassTwo() {
        return netTypeClassTwo;
    }

    public void setNetTypeClassTwo(String netTypeClassTwo) {
        this.netTypeClassTwo = netTypeClassTwo == null ? null : netTypeClassTwo.trim();
    }

    public String getNetTypeClassThree() {
        return netTypeClassThree;
    }

    public void setNetTypeClassThree(String netTypeClassThree) {
        this.netTypeClassThree = netTypeClassThree == null ? null : netTypeClassThree.trim();
    }

    public Date getFaultOccusTime() {
        return faultOccusTime;
    }

    public void setFaultOccusTime(Date faultOccusTime) {
        this.faultOccusTime = faultOccusTime;
    }

    public String getFaultFindMode() {
        return faultFindMode;
    }

    public void setFaultFindMode(String faultFindMode) {
        this.faultFindMode = faultFindMode == null ? null : faultFindMode.trim();
    }

    public String getFaultFindTime() {
        return faultFindTime;
    }

    public void setFaultFindTime(String faultFindTime) {
        this.faultFindTime = faultFindTime == null ? null : faultFindTime.trim();
    }

    public String getFaultOccusCity() {
        return faultOccusCity;
    }

    public void setFaultOccusCity(String faultOccusCity) {
        this.faultOccusCity = faultOccusCity == null ? null : faultOccusCity.trim();
    }

    public String getFaultHandleCity() {
        return faultHandleCity;
    }

    public void setFaultHandleCity(String faultHandleCity) {
        this.faultHandleCity = faultHandleCity == null ? null : faultHandleCity.trim();
    }

    public String getFaultCriticalFlag() {
        return faultCriticalFlag;
    }

    public void setFaultCriticalFlag(String faultCriticalFlag) {
        this.faultCriticalFlag = faultCriticalFlag == null ? null : faultCriticalFlag.trim();
    }

    public String getT1AcceptTime() {
        return t1AcceptTime;
    }

    public void setT1AcceptTime(String t1AcceptTime) {
        this.t1AcceptTime = t1AcceptTime == null ? null : t1AcceptTime.trim();
    }

    public String getT1TranstObject() {
        return t1TranstObject;
    }

    public void setT1TranstObject(String t1TranstObject) {
        this.t1TranstObject = t1TranstObject == null ? null : t1TranstObject.trim();
    }

    public String getT1TranstSuggestion() {
        return t1TranstSuggestion;
    }

    public void setT1TranstSuggestion(String t1TranstSuggestion) {
        this.t1TranstSuggestion = t1TranstSuggestion == null ? null : t1TranstSuggestion.trim();
    }

    public String getT1TranstPersion() {
        return t1TranstPersion;
    }

    public void setT1TranstPersion(String t1TranstPersion) {
        this.t1TranstPersion = t1TranstPersion == null ? null : t1TranstPersion.trim();
    }

    public String getT1TranstPersonPart() {
        return t1TranstPersonPart;
    }

    public void setT1TranstPersonPart(String t1TranstPersonPart) {
        this.t1TranstPersonPart = t1TranstPersonPart == null ? null : t1TranstPersonPart.trim();
    }

    public String getT1TranstOperTime() {
        return t1TranstOperTime;
    }

    public void setT1TranstOperTime(String t1TranstOperTime) {
        this.t1TranstOperTime = t1TranstOperTime == null ? null : t1TranstOperTime.trim();
    }

    public String getT2AcceptPerson() {
        return t2AcceptPerson;
    }

    public void setT2AcceptPerson(String t2AcceptPerson) {
        this.t2AcceptPerson = t2AcceptPerson == null ? null : t2AcceptPerson.trim();
    }

    public String getT2AcceptTime() {
        return t2AcceptTime;
    }

    public void setT2AcceptTime(String t2AcceptTime) {
        this.t2AcceptTime = t2AcceptTime == null ? null : t2AcceptTime.trim();
    }

    public String getTranstReason() {
        return transtReason;
    }

    public void setTranstReason(String transtReason) {
        this.transtReason = transtReason == null ? null : transtReason.trim();
    }

    public String getRequirementCompleteTime() {
        return requirementCompleteTime;
    }

    public void setRequirementCompleteTime(String requirementCompleteTime) {
        this.requirementCompleteTime = requirementCompleteTime == null ? null : requirementCompleteTime.trim();
    }

    public String getTaskAcceptTime() {
        return taskAcceptTime;
    }

    public void setTaskAcceptTime(String taskAcceptTime) {
        this.taskAcceptTime = taskAcceptTime == null ? null : taskAcceptTime.trim();
    }

    public String getTaskAssignPerson() {
        return taskAssignPerson;
    }

    public void setTaskAssignPerson(String taskAssignPerson) {
        this.taskAssignPerson = taskAssignPerson == null ? null : taskAssignPerson.trim();
    }

    public String getTaskAssignPersonPart() {
        return taskAssignPersonPart;
    }

    public void setTaskAssignPersonPart(String taskAssignPersonPart) {
        this.taskAssignPersonPart = taskAssignPersonPart == null ? null : taskAssignPersonPart.trim();
    }

    public String getTaskAssignOpteratTime() {
        return taskAssignOpteratTime;
    }

    public void setTaskAssignOpteratTime(String taskAssignOpteratTime) {
        this.taskAssignOpteratTime = taskAssignOpteratTime == null ? null : taskAssignOpteratTime.trim();
    }

    public String getWorkRecoverTime() {
        return workRecoverTime;
    }

    public void setWorkRecoverTime(String workRecoverTime) {
        this.workRecoverTime = workRecoverTime == null ? null : workRecoverTime.trim();
    }

    public String getInfluenceLong() {
        return influenceLong;
    }

    public void setInfluenceLong(String influenceLong) {
        this.influenceLong = influenceLong == null ? null : influenceLong.trim();
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason == null ? null : returnReason.trim();
    }

    public String getTaskRebackPerson() {
        return taskRebackPerson;
    }

    public void setTaskRebackPerson(String taskRebackPerson) {
        this.taskRebackPerson = taskRebackPerson == null ? null : taskRebackPerson.trim();
    }

    public String getTaskRebackPersonPart() {
        return taskRebackPersonPart;
    }

    public void setTaskRebackPersonPart(String taskRebackPersonPart) {
        this.taskRebackPersonPart = taskRebackPersonPart == null ? null : taskRebackPersonPart.trim();
    }

    public String getTaskRebackTime() {
        return taskRebackTime;
    }

    public void setTaskRebackTime(String taskRebackTime) {
        this.taskRebackTime = taskRebackTime == null ? null : taskRebackTime.trim();
    }

    public String getConfirmationOption() {
        return confirmationOption;
    }

    public void setConfirmationOption(String confirmationOption) {
        this.confirmationOption = confirmationOption == null ? null : confirmationOption.trim();
    }

    public String getConfirmationPerson() {
        return confirmationPerson;
    }

    public void setConfirmationPerson(String confirmationPerson) {
        this.confirmationPerson = confirmationPerson == null ? null : confirmationPerson.trim();
    }

    public String getConfirmationPersonPart() {
        return confirmationPersonPart;
    }

    public void setConfirmationPersonPart(String confirmationPersonPart) {
        this.confirmationPersonPart = confirmationPersonPart == null ? null : confirmationPersonPart.trim();
    }

    public String getTaskConfirmTime() {
        return taskConfirmTime;
    }

    public void setTaskConfirmTime(String taskConfirmTime) {
        this.taskConfirmTime = taskConfirmTime == null ? null : taskConfirmTime.trim();
    }

    public String getAssignReplyFaultName() {
        return assignReplyFaultName;
    }

    public void setAssignReplyFaultName(String assignReplyFaultName) {
        this.assignReplyFaultName = assignReplyFaultName == null ? null : assignReplyFaultName.trim();
    }

    public String getAssignReplyHandleTime() {
        return assignReplyHandleTime;
    }

    public void setAssignReplyHandleTime(String assignReplyHandleTime) {
        this.assignReplyHandleTime = assignReplyHandleTime == null ? null : assignReplyHandleTime.trim();
    }

    public String getAssignReplyHandleMethod() {
        return assignReplyHandleMethod;
    }

    public void setAssignReplyHandleMethod(String assignReplyHandleMethod) {
        this.assignReplyHandleMethod = assignReplyHandleMethod == null ? null : assignReplyHandleMethod.trim();
    }

    public String getAssignReplySolveTime() {
        return assignReplySolveTime;
    }

    public void setAssignReplySolveTime(String assignReplySolveTime) {
        this.assignReplySolveTime = assignReplySolveTime == null ? null : assignReplySolveTime.trim();
    }

    public String getApplyReportFaultResult() {
        return applyReportFaultResult;
    }

    public void setApplyReportFaultResult(String applyReportFaultResult) {
        this.applyReportFaultResult = applyReportFaultResult == null ? null : applyReportFaultResult.trim();
    }

    public String getApplyReportFaultType() {
        return applyReportFaultType;
    }

    public void setApplyReportFaultType(String applyReportFaultType) {
        this.applyReportFaultType = applyReportFaultType == null ? null : applyReportFaultType.trim();
    }

    public String getApplySettlementMeasure() {
        return applySettlementMeasure;
    }

    public void setApplySettlementMeasure(String applySettlementMeasure) {
        this.applySettlementMeasure = applySettlementMeasure == null ? null : applySettlementMeasure.trim();
    }

    public String getApplyBusinessFlag() {
        return applyBusinessFlag;
    }

    public void setApplyBusinessFlag(String applyBusinessFlag) {
        this.applyBusinessFlag = applyBusinessFlag == null ? null : applyBusinessFlag.trim();
    }

    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson == null ? null : applyPerson.trim();
    }

    public String getApplyPersonPart() {
        return applyPersonPart;
    }

    public void setApplyPersonPart(String applyPersonPart) {
        this.applyPersonPart = applyPersonPart == null ? null : applyPersonPart.trim();
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime == null ? null : applyTime.trim();
    }

    public String getApplyFaultEliminateTime() {
        return applyFaultEliminateTime;
    }

    public void setApplyFaultEliminateTime(String applyFaultEliminateTime) {
        this.applyFaultEliminateTime = applyFaultEliminateTime == null ? null : applyFaultEliminateTime.trim();
    }

    public String getWarmTime() {
        return warmTime;
    }

    public void setWarmTime(String warmTime) {
        this.warmTime = warmTime == null ? null : warmTime.trim();
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime == null ? null : processTime.trim();
    }

    public String getPowerStartTime() {
        return powerStartTime;
    }

    public void setPowerStartTime(String powerStartTime) {
        this.powerStartTime = powerStartTime == null ? null : powerStartTime.trim();
    }

    public String getPowerEndTime() {
        return powerEndTime;
    }

    public void setPowerEndTime(String powerEndTime) {
        this.powerEndTime = powerEndTime == null ? null : powerEndTime.trim();
    }

    public String getOilEngineNum() {
        return oilEngineNum;
    }

    public void setOilEngineNum(String oilEngineNum) {
        this.oilEngineNum = oilEngineNum == null ? null : oilEngineNum.trim();
    }

    public String getDenyFlag() {
        return denyFlag;
    }

    public void setDenyFlag(String denyFlag) {
        this.denyFlag = denyFlag == null ? null : denyFlag.trim();
    }

    public String getConfirmPerson() {
        return confirmPerson;
    }

    public void setConfirmPerson(String confirmPerson) {
        this.confirmPerson = confirmPerson == null ? null : confirmPerson.trim();
    }

    public String getConfirmPersonPart() {
        return confirmPersonPart;
    }

    public void setConfirmPersonPart(String confirmPersonPart) {
        this.confirmPersonPart = confirmPersonPart == null ? null : confirmPersonPart.trim();
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime == null ? null : confirmTime.trim();
    }

    public String getDenyValidFlag() {
        return denyValidFlag;
    }

    public void setDenyValidFlag(String denyValidFlag) {
        this.denyValidFlag = denyValidFlag == null ? null : denyValidFlag.trim();
    }

    public String getApplyExtensionFlag() {
        return applyExtensionFlag;
    }

    public void setApplyExtensionFlag(String applyExtensionFlag) {
        this.applyExtensionFlag = applyExtensionFlag == null ? null : applyExtensionFlag.trim();
    }

    public String getApplyExtensionResion() {
        return applyExtensionResion;
    }

    public void setApplyExtensionResion(String applyExtensionResion) {
        this.applyExtensionResion = applyExtensionResion == null ? null : applyExtensionResion.trim();
    }

    public String getExpectedResolveTime() {
        return expectedResolveTime;
    }

    public void setExpectedResolveTime(String expectedResolveTime) {
        this.expectedResolveTime = expectedResolveTime == null ? null : expectedResolveTime.trim();
    }

    public String getApplyExtensionPerson() {
        return applyExtensionPerson;
    }

    public void setApplyExtensionPerson(String applyExtensionPerson) {
        this.applyExtensionPerson = applyExtensionPerson == null ? null : applyExtensionPerson.trim();
    }

    public String getApplyExtensionPersionPart() {
        return applyExtensionPersionPart;
    }

    public void setApplyExtensionPersionPart(String applyExtensionPersionPart) {
        this.applyExtensionPersionPart = applyExtensionPersionPart == null ? null : applyExtensionPersionPart.trim();
    }

    public String getApprovalOpinions() {
        return approvalOpinions;
    }

    public void setApprovalOpinions(String approvalOpinions) {
        this.approvalOpinions = approvalOpinions == null ? null : approvalOpinions.trim();
    }

    public String getExtensionPerson() {
        return extensionPerson;
    }

    public void setExtensionPerson(String extensionPerson) {
        this.extensionPerson = extensionPerson == null ? null : extensionPerson.trim();
    }

    public String getStageReplyResponse() {
        return stageReplyResponse;
    }

    public void setStageReplyResponse(String stageReplyResponse) {
        this.stageReplyResponse = stageReplyResponse == null ? null : stageReplyResponse.trim();
    }

    public String getStageReplyTime() {
        return stageReplyTime;
    }

    public void setStageReplyTime(String stageReplyTime) {
        this.stageReplyTime = stageReplyTime == null ? null : stageReplyTime.trim();
    }

    public String getStageReplyPerson() {
        return stageReplyPerson;
    }

    public void setStageReplyPerson(String stageReplyPerson) {
        this.stageReplyPerson = stageReplyPerson == null ? null : stageReplyPerson.trim();
    }

    public String getStageReplyPersonPart() {
        return stageReplyPersonPart;
    }

    public void setStageReplyPersonPart(String stageReplyPersonPart) {
        this.stageReplyPersonPart = stageReplyPersonPart == null ? null : stageReplyPersonPart.trim();
    }

    public String getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(String customerFlag) {
        this.customerFlag = customerFlag == null ? null : customerFlag.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    public String getCustomeGroupname() {
        return customeGroupname;
    }

    public void setCustomeGroupname(String customeGroupname) {
        this.customeGroupname = customeGroupname == null ? null : customeGroupname.trim();
    }

    public String getCustomeGroupnum() {
        return customeGroupnum;
    }

    public void setCustomeGroupnum(String customeGroupnum) {
        this.customeGroupnum = customeGroupnum == null ? null : customeGroupnum.trim();
    }

    public String getBusinessSupportLevel() {
        return businessSupportLevel;
    }

    public void setBusinessSupportLevel(String businessSupportLevel) {
        this.businessSupportLevel = businessSupportLevel == null ? null : businessSupportLevel.trim();
    }

    public String getBusinessEventImpact() {
        return businessEventImpact;
    }

    public void setBusinessEventImpact(String businessEventImpact) {
        this.businessEventImpact = businessEventImpact == null ? null : businessEventImpact.trim();
    }

    public String getClientDeviceType() {
        return clientDeviceType;
    }

    public void setClientDeviceType(String clientDeviceType) {
        this.clientDeviceType = clientDeviceType == null ? null : clientDeviceType.trim();
    }

    public String getClientDeviceCompany() {
        return clientDeviceCompany;
    }

    public void setClientDeviceCompany(String clientDeviceCompany) {
        this.clientDeviceCompany = clientDeviceCompany == null ? null : clientDeviceCompany.trim();
    }

    public String getClientDeviceModel() {
        return clientDeviceModel;
    }

    public void setClientDeviceModel(String clientDeviceModel) {
        this.clientDeviceModel = clientDeviceModel == null ? null : clientDeviceModel.trim();
    }

    public String getWorkOrderTimeout() {
        return workOrderTimeout;
    }

    public void setWorkOrderTimeout(String workOrderTimeout) {
        this.workOrderTimeout = workOrderTimeout == null ? null : workOrderTimeout.trim();
    }

    public String getAssignFlag() {
        return assignFlag;
    }

    public void setAssignFlag(String assignFlag) {
        this.assignFlag = assignFlag == null ? null : assignFlag.trim();
    }

    public String getMainOrderFlag() {
        return mainOrderFlag;
    }

    public void setMainOrderFlag(String mainOrderFlag) {
        this.mainOrderFlag = mainOrderFlag == null ? null : mainOrderFlag.trim();
    }

    public String getAnnounceFlag() {
        return announceFlag;
    }

    public void setAnnounceFlag(String announceFlag) {
        this.announceFlag = announceFlag == null ? null : announceFlag.trim();
    }

    public String getAnnouncementNum() {
        return announcementNum;
    }

    public void setAnnouncementNum(String announcementNum) {
        this.announcementNum = announcementNum == null ? null : announcementNum.trim();
    }

    public String getOrderFilingTime() {
        return orderFilingTime;
    }

    public void setOrderFilingTime(String orderFilingTime) {
        this.orderFilingTime = orderFilingTime == null ? null : orderFilingTime.trim();
    }

    public String getAssignReplyPerson() {
        return assignReplyPerson;
    }

    public void setAssignReplyPerson(String assignReplyPerson) {
        this.assignReplyPerson = assignReplyPerson == null ? null : assignReplyPerson.trim();
    }

    public String getAssignReplyPersonPart() {
        return assignReplyPersonPart;
    }

    public void setAssignReplyPersonPart(String assignReplyPersonPart) {
        this.assignReplyPersonPart = assignReplyPersonPart == null ? null : assignReplyPersonPart.trim();
    }

    public String getAssignReplyOperTime() {
        return assignReplyOperTime;
    }

    public void setAssignReplyOperTime(String assignReplyOperTime) {
        this.assignReplyOperTime = assignReplyOperTime == null ? null : assignReplyOperTime.trim();
    }

    public String getPretreatmentAdvice() {
        return pretreatmentAdvice;
    }

    public void setPretreatmentAdvice(String pretreatmentAdvice) {
        this.pretreatmentAdvice = pretreatmentAdvice == null ? null : pretreatmentAdvice.trim();
    }

    public String getIntelligentFlag() {
        return intelligentFlag;
    }

    public void setIntelligentFlag(String intelligentFlag) {
        this.intelligentFlag = intelligentFlag == null ? null : intelligentFlag.trim();
    }

    public String getAssignOrderMode() {
        return assignOrderMode;
    }

    public void setAssignOrderMode(String assignOrderMode) {
        this.assignOrderMode = assignOrderMode == null ? null : assignOrderMode.trim();
    }

    public String getNetLevel() {
        return netLevel;
    }

    public void setNetLevel(String netLevel) {
        this.netLevel = netLevel == null ? null : netLevel.trim();
    }

    public String getDispatchingRule() {
        return dispatchingRule;
    }

    public void setDispatchingRule(String dispatchingRule) {
        this.dispatchingRule = dispatchingRule == null ? null : dispatchingRule.trim();
    }

    public String getProbleRuleOrder() {
        return probleRuleOrder;
    }

    public void setProbleRuleOrder(String probleRuleOrder) {
        this.probleRuleOrder = probleRuleOrder == null ? null : probleRuleOrder.trim();
    }

    public String getAssignResourceOrder() {
        return assignResourceOrder;
    }

    public void setAssignResourceOrder(String assignResourceOrder) {
        this.assignResourceOrder = assignResourceOrder == null ? null : assignResourceOrder.trim();
    }

    public Integer getPerformanceAlarmChildNum() {
        return performanceAlarmChildNum;
    }

    public void setPerformanceAlarmChildNum(Integer performanceAlarmChildNum) {
        this.performanceAlarmChildNum = performanceAlarmChildNum;
    }

    public String getStationCompany() {
        return stationCompany;
    }

    public void setStationCompany(String stationCompany) {
        this.stationCompany = stationCompany == null ? null : stationCompany.trim();
    }

    public String getAffiliatedComputerRoom() {
        return affiliatedComputerRoom;
    }

    public void setAffiliatedComputerRoom(String affiliatedComputerRoom) {
        this.affiliatedComputerRoom = affiliatedComputerRoom == null ? null : affiliatedComputerRoom.trim();
    }

    public String getPhysicalDeviceType() {
        return physicalDeviceType;
    }

    public void setPhysicalDeviceType(String physicalDeviceType) {
        this.physicalDeviceType = physicalDeviceType == null ? null : physicalDeviceType.trim();
    }

    public String getOrderAutomaticResponsibility() {
        return orderAutomaticResponsibility;
    }

    public void setOrderAutomaticResponsibility(String orderAutomaticResponsibility) {
        this.orderAutomaticResponsibility = orderAutomaticResponsibility == null ? null : orderAutomaticResponsibility.trim();
    }

    public String getAutoStrantResult() {
        return autoStrantResult;
    }

    public void setAutoStrantResult(String autoStrantResult) {
        this.autoStrantResult = autoStrantResult == null ? null : autoStrantResult.trim();
    }

    public String getNetClass1() {
        return netClass1;
    }

    public void setNetClass1(String netClass1) {
        this.netClass1 = netClass1 == null ? null : netClass1.trim();
    }

    public String getNetClass2() {
        return netClass2;
    }

    public void setNetClass2(String netClass2) {
        this.netClass2 = netClass2 == null ? null : netClass2.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }
}