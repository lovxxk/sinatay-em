package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// default package
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GesmsConfig
 */
@Entity
@Table(name = "GE_SMS_CONFIG")
public class GeSmsConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性短信ID */
	private String smsId;

	/** 属性适用功能 */
	private String functionID;

	/** 属性短信名称 */
	private String smsName;

	/** 属性短信内容 */
	private String smsContent;

	/** 属性创建者 */
	private String creater;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性修改者 */
	private String updater;

	/** 属性修改时间 */
	private Date updateTime;

	/** 属性是否有效标志 */
	private String validInd;

	/** 属性备注 */
	private String remark;
	@Transient
	/**短信适用功能名称*/
	private String sendSmsName;
	/**
	 * 类GesmsConfig的默认构造方法
	 */
	public GeSmsConfig() {
	}

	/**
	 * 属性短信ID的getter方法
	 */
	@Id
	@Column(name = "SMSID", unique = true,nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSmsId() {
		return this.smsId;
	}

	/**
	 * 属性短信ID的setter方法
	 */
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	/**
	 * 属性适用功能的getter方法
	 */

	@Column(name = "FUNCTIONID")
	public String getFunctionID() {
		return this.functionID;
	}

	/**
	 * 属性适用功能的setter方法
	 */
	public void setFunctionID(String functionID) {
		this.functionID = functionID;
	}

	/**
	 * 属性短信名称的getter方法
	 */

	@Column(name = "SMSNAME")
	public String getSmsName() {
		return this.smsName;
	}

	/**
	 * 属性短信名称的setter方法
	 */
	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}

	/**
	 * 属性短信内容的getter方法
	 */

	@Column(name = "SMSCONTENT")
	public String getSmsContent() {
		return this.smsContent;
	}

	/**
	 * 属性短信内容的setter方法
	 */
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	/**
	 * 属性创建者的getter方法
	 */

	@Column(name = "CREATER")
	public String getCreater() {
		return this.creater;
	}

	/**
	 * 属性创建者的setter方法
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}

	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性修改者的getter方法
	 */

	@Column(name = "UPDATER")
	public String getUpdater() {
		return this.updater;
	}

	/**
	 * 属性修改者的setter方法
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	/**
	 * 属性修改时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性修改时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 属性是否有效标志的getter方法
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * 属性是否有效标志的setter方法
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	/**
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getSendSmsName() {
		return sendSmsName;
	}

	public void setSendSmsName(String sendSmsName) {
		this.sendSmsName = sendSmsName;
	}
	
}
