package com.powersi.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CaseCenter implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "案情类型")
    private String caseType;

    @ApiModelProperty(value = "案情阶段（1：未立案 2.法院审理阶段 3.执行阶段或已结案）")
    private String caseStage;

    @ApiModelProperty(value = "标的价值")
    private String remarkValue;

    private Date updateTime;

    private Date createTime;

    @ApiModelProperty(value = "服务类型")
    private Long serviceType;

    @ApiModelProperty(value = "用户诉求类型")
    private Byte userDemandType;

    @ApiModelProperty(value = "状态：0、未发布；1、已发布；2、进行中；3、已完成；4、已取消")
    private Byte status;

    @ApiModelProperty(value = "来源：0、运营端；1、IM端；2、用户端；")
    private Byte origin;

    @ApiModelProperty(value = "提交人id")
    private Long submitUserid;

    @ApiModelProperty(value = "IDai_lawyer_session")
    private Long caseSessionId;

    @ApiModelProperty(value = "案源定价")
    private BigDecimal price;

    @ApiModelProperty(value = "律所ID集合")
    private String lawFirmIds;

    @ApiModelProperty(value = "跟进人ID集合")
    private String followerIds;

    @ApiModelProperty(value = "是否被标记：0=否；1=是")
    private Integer hasMark;

    private String phoneUuid;

    @ApiModelProperty(value = "求助意向  0暂无/1一般/2强烈")
    private Byte helpIntention;

    @ApiModelProperty(value = "是否找过律师 1是/0否")
    private Byte foundLawyerFlag;

    @ApiModelProperty(value = "未解决原因")
    private String unresolvedReason;

    @ApiModelProperty(value = "当事人所在地(原告)")
    private String partyLocation;

    @ApiModelProperty(value = "起诉人所在地(被告)")
    private String prosecutorLocation;

    @ApiModelProperty(value = "是否有法务 1是/0否")
    private Byte hasLegalAffairsFlag;

    @ApiModelProperty(value = "案源等级")
    private String caseLevel;

    @ApiModelProperty(value = "案源等级对应值:82=A++;81=A+;80=A;72=B++;71=B+;70=B;62=C++;61=C+;6052=D++;5150=D;40=E;0= ;")
    private Integer caseLevelValue;

    @ApiModelProperty(value = "这字段暂时没用到了")
    private Integer caseFollowStatus;

    @ApiModelProperty(value = "案情来源")
    private String caseSource;

    @ApiModelProperty(value = "是否需要外呼标志  0:不需要  1:需要")
    private Integer callOutFlag;

    @ApiModelProperty(value = "0-都不显示，1-全部展示，2-仅在运营平台显示，3-仅在小法优律显示，4-仅在小法名律显示，5-不在运营平台显示，6-不在小法优律显示，7-不在小法名律显示")
    private Integer disable;

    @ApiModelProperty(value = "案源流转状态,1:待分配跟进人-2:跟进中-3:对接律所/律师-4:成单,5:无效案源")
    private Integer caseCirculateStatus;

    @ApiModelProperty(value = "原始案情描述")
    private String originalCaseDesc;

    @ApiModelProperty(value = "案情描述")
    private String caseDesc;

    @ApiModelProperty(value = "标的价值备注")
    private String remarkTarget;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "文件地址：多个用英文逗号隔开")
    private String filesUrl;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseStage() {
        return caseStage;
    }

    public void setCaseStage(String caseStage) {
        this.caseStage = caseStage;
    }

    public String getRemarkValue() {
        return remarkValue;
    }

    public void setRemarkValue(String remarkValue) {
        this.remarkValue = remarkValue;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getServiceType() {
        return serviceType;
    }

    public void setServiceType(Long serviceType) {
        this.serviceType = serviceType;
    }

    public Byte getUserDemandType() {
        return userDemandType;
    }

    public void setUserDemandType(Byte userDemandType) {
        this.userDemandType = userDemandType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getOrigin() {
        return origin;
    }

    public void setOrigin(Byte origin) {
        this.origin = origin;
    }

    public Long getSubmitUserid() {
        return submitUserid;
    }

    public void setSubmitUserid(Long submitUserid) {
        this.submitUserid = submitUserid;
    }

    public Long getCaseSessionId() {
        return caseSessionId;
    }

    public void setCaseSessionId(Long caseSessionId) {
        this.caseSessionId = caseSessionId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLawFirmIds() {
        return lawFirmIds;
    }

    public void setLawFirmIds(String lawFirmIds) {
        this.lawFirmIds = lawFirmIds;
    }

    public String getFollowerIds() {
        return followerIds;
    }

    public void setFollowerIds(String followerIds) {
        this.followerIds = followerIds;
    }

    public Integer getHasMark() {
        return hasMark;
    }

    public void setHasMark(Integer hasMark) {
        this.hasMark = hasMark;
    }

    public String getPhoneUuid() {
        return phoneUuid;
    }

    public void setPhoneUuid(String phoneUuid) {
        this.phoneUuid = phoneUuid;
    }

    public Byte getHelpIntention() {
        return helpIntention;
    }

    public void setHelpIntention(Byte helpIntention) {
        this.helpIntention = helpIntention;
    }

    public Byte getFoundLawyerFlag() {
        return foundLawyerFlag;
    }

    public void setFoundLawyerFlag(Byte foundLawyerFlag) {
        this.foundLawyerFlag = foundLawyerFlag;
    }

    public String getUnresolvedReason() {
        return unresolvedReason;
    }

    public void setUnresolvedReason(String unresolvedReason) {
        this.unresolvedReason = unresolvedReason;
    }

    public String getPartyLocation() {
        return partyLocation;
    }

    public void setPartyLocation(String partyLocation) {
        this.partyLocation = partyLocation;
    }

    public String getProsecutorLocation() {
        return prosecutorLocation;
    }

    public void setProsecutorLocation(String prosecutorLocation) {
        this.prosecutorLocation = prosecutorLocation;
    }

    public Byte getHasLegalAffairsFlag() {
        return hasLegalAffairsFlag;
    }

    public void setHasLegalAffairsFlag(Byte hasLegalAffairsFlag) {
        this.hasLegalAffairsFlag = hasLegalAffairsFlag;
    }

    public String getCaseLevel() {
        return caseLevel;
    }

    public void setCaseLevel(String caseLevel) {
        this.caseLevel = caseLevel;
    }

    public Integer getCaseLevelValue() {
        return caseLevelValue;
    }

    public void setCaseLevelValue(Integer caseLevelValue) {
        this.caseLevelValue = caseLevelValue;
    }

    public Integer getCaseFollowStatus() {
        return caseFollowStatus;
    }

    public void setCaseFollowStatus(Integer caseFollowStatus) {
        this.caseFollowStatus = caseFollowStatus;
    }

    public String getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(String caseSource) {
        this.caseSource = caseSource;
    }

    public Integer getCallOutFlag() {
        return callOutFlag;
    }

    public void setCallOutFlag(Integer callOutFlag) {
        this.callOutFlag = callOutFlag;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public Integer getCaseCirculateStatus() {
        return caseCirculateStatus;
    }

    public void setCaseCirculateStatus(Integer caseCirculateStatus) {
        this.caseCirculateStatus = caseCirculateStatus;
    }

    public String getOriginalCaseDesc() {
        return originalCaseDesc;
    }

    public void setOriginalCaseDesc(String originalCaseDesc) {
        this.originalCaseDesc = originalCaseDesc;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getRemarkTarget() {
        return remarkTarget;
    }

    public void setRemarkTarget(String remarkTarget) {
        this.remarkTarget = remarkTarget;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFilesUrl() {
        return filesUrl;
    }

    public void setFilesUrl(String filesUrl) {
        this.filesUrl = filesUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", phone=").append(phone);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", caseType=").append(caseType);
        sb.append(", caseStage=").append(caseStage);
        sb.append(", remarkValue=").append(remarkValue);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", userDemandType=").append(userDemandType);
        sb.append(", status=").append(status);
        sb.append(", origin=").append(origin);
        sb.append(", submitUserid=").append(submitUserid);
        sb.append(", caseSessionId=").append(caseSessionId);
        sb.append(", price=").append(price);
        sb.append(", lawFirmIds=").append(lawFirmIds);
        sb.append(", followerIds=").append(followerIds);
        sb.append(", hasMark=").append(hasMark);
        sb.append(", phoneUuid=").append(phoneUuid);
        sb.append(", helpIntention=").append(helpIntention);
        sb.append(", foundLawyerFlag=").append(foundLawyerFlag);
        sb.append(", unresolvedReason=").append(unresolvedReason);
        sb.append(", partyLocation=").append(partyLocation);
        sb.append(", prosecutorLocation=").append(prosecutorLocation);
        sb.append(", hasLegalAffairsFlag=").append(hasLegalAffairsFlag);
        sb.append(", caseLevel=").append(caseLevel);
        sb.append(", caseLevelValue=").append(caseLevelValue);
        sb.append(", caseFollowStatus=").append(caseFollowStatus);
        sb.append(", caseSource=").append(caseSource);
        sb.append(", callOutFlag=").append(callOutFlag);
        sb.append(", disable=").append(disable);
        sb.append(", caseCirculateStatus=").append(caseCirculateStatus);
        sb.append(", originalCaseDesc=").append(originalCaseDesc);
        sb.append(", caseDesc=").append(caseDesc);
        sb.append(", remarkTarget=").append(remarkTarget);
        sb.append(", remark=").append(remark);
        sb.append(", filesUrl=").append(filesUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}