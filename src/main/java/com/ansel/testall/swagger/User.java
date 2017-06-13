package com.ansel.testall.swagger;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hp.openplatform.common.util.JsonHelper;

public class User {
	private String userId;

	private String password;

	private String userName;

	private String contactEmail;

	private String contactPhone;

	@JsonSerialize(using = JsonHelper.DatetimeSerializer.class)
	@JsonDeserialize(using = JsonHelper.DatetimeDeserializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registDate;

	private String registDateStr;

	private String userType;

	private String userStatus;

	private String nickname;

	private String userDescription;

	private Integer isDel;

	private String isSuperAdmin;

	private String partnerId;

	private String imageName;

	private List<Platform> platform;// 合作伙伴对象

	private String partnerEnName;// 合作伙伴英文名

	private String partnerChName;// 合作伙伴中文名

	private String partnerRole;// 合作伙伴角色

	private String partnerDescription;// 合作伙伴描述

	private List<SerServiceAudit> serServiceAudit;// 服务审批对象

	private String approvalStatus;// 审批状态

	private String auditType;// 审批类型

	private String auditTypeFlag;// 审批类型标识

	private String approvalStatusFlag;// 审批状态

	private String firstAuditor;

	private String secondAuditor;

	private String thirdAuditor;

	private String firstAuditOpinion;

	private String secondAuditOpinion;

	private String thirdAuditOpinion;

	@JsonSerialize(using = JsonHelper.DatetimeSerializer.class)
	@JsonDeserialize(using = JsonHelper.DatetimeDeserializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date firstCurrentTime;

	@JsonSerialize(using = JsonHelper.DatetimeSerializer.class)
	@JsonDeserialize(using = JsonHelper.DatetimeDeserializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date secondCurrentTime;

	@JsonSerialize(using = JsonHelper.DatetimeSerializer.class)
	@JsonDeserialize(using = JsonHelper.DatetimeDeserializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date thirdCurrentTime;
	
	
    private String createUser;
	@JsonSerialize(using = JsonHelper.DatetimeSerializer.class)
	@JsonDeserialize(using = JsonHelper.DatetimeDeserializer.class)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String updateUser;
	@JsonSerialize(using = JsonHelper.DatetimeSerializer.class)
	@JsonDeserialize(using = JsonHelper.DatetimeDeserializer.class)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail == null ? null : contactEmail.trim();
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone == null ? null : contactPhone.trim();
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType == null ? null : userType.trim();
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus == null ? null : userStatus.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription == null ? null : userDescription.trim();
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(String isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin == null ? null : isSuperAdmin.trim();
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId == null ? null : partnerId.trim();
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName == null ? null : imageName.trim();
	}

	public List<Platform> getPlatform() {
		return platform;
	}

	public void setPlatform(List<Platform> platform) {
		this.platform = platform;
	}

	public String getPartnerEnName() {
		return partnerEnName;
	}

	public void setPartnerEnName(String partnerEnName) {
		this.partnerEnName = partnerEnName;
	}

	public String getPartnerChName() {
		return partnerChName;
	}

	public void setPartnerChName(String partnerChName) {
		this.partnerChName = partnerChName;
	}

	public String getPartnerRole() {
		return partnerRole;
	}

	public void setPartnerRole(String partnerRole) {
		this.partnerRole = partnerRole;
	}

	public String getPartnerDescription() {
		return partnerDescription;
	}

	public void setPartnerDescription(String partnerDescription) {
		this.partnerDescription = partnerDescription;
	}

	public List<SerServiceAudit> getSerServiceAudit() {
		return serServiceAudit;
	}

	public void setSerServiceAudit(List<SerServiceAudit> serServiceAudit) {
		this.serServiceAudit = serServiceAudit;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getFirstAuditor() {
		return firstAuditor;
	}

	public void setFirstAuditor(String firstAuditor) {
		this.firstAuditor = firstAuditor;
	}

	public String getSecondAuditor() {
		return secondAuditor;
	}

	public void setSecondAuditor(String secondAuditor) {
		this.secondAuditor = secondAuditor;
	}

	public String getThirdAuditor() {
		return thirdAuditor;
	}

	public void setThirdAuditor(String thirdAuditor) {
		this.thirdAuditor = thirdAuditor;
	}

	public String getFirstAuditOpinion() {
		return firstAuditOpinion;
	}

	public void setFirstAuditOpinion(String firstAuditOpinion) {
		this.firstAuditOpinion = firstAuditOpinion;
	}

	public String getSecondAuditOpinion() {
		return secondAuditOpinion;
	}

	public void setSecondAuditOpinion(String secondAuditOpinion) {
		this.secondAuditOpinion = secondAuditOpinion;
	}

	public String getThirdAuditOpinion() {
		return thirdAuditOpinion;
	}

	public void setThirdAuditOpinion(String thirdAuditOpinion) {
		this.thirdAuditOpinion = thirdAuditOpinion;
	}

	public Date getFirstCurrentTime() {
		return firstCurrentTime;
	}

	public void setFirstCurrentTime(Date firstCurrentTime) {
		this.firstCurrentTime = firstCurrentTime;
	}

	public Date getSecondCurrentTime() {
		return secondCurrentTime;
	}

	public void setSecondCurrentTime(Date secondCurrentTime) {
		this.secondCurrentTime = secondCurrentTime;
	}

	public Date getThirdCurrentTime() {
		return thirdCurrentTime;
	}

	public void setThirdCurrentTime(Date thirdCurrentTime) {
		this.thirdCurrentTime = thirdCurrentTime;
	}

	public String getRegistDateStr() {
		return registDateStr;
	}

	public void setRegistDateStr(String registDateStr) {
		this.registDateStr = registDateStr;
	}

	public String getAuditTypeFlag() {
		return auditTypeFlag;
	}

	public void setAuditTypeFlag(String auditTypeFlag) {
		this.auditTypeFlag = auditTypeFlag;
	}

	public String getApprovalStatusFlag() {
		return approvalStatusFlag;
	}

	public void setApprovalStatusFlag(String approvalStatusFlag) {
		this.approvalStatusFlag = approvalStatusFlag;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}