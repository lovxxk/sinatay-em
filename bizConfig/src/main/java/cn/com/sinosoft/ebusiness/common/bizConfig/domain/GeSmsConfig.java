package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// default package
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GesmsConfig
 */
@Entity
@Table(name = "GE_SMS_CONFIG")
public class GeSmsConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Զ���ID */
	private String smsId;

	/** �������ù��� */
	private String functionID;

	/** ���Զ������� */
	private String smsName;

	/** ���Զ������� */
	private String smsContent;

	/** ���Դ����� */
	private String creater;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** �����޸��� */
	private String updater;

	/** �����޸�ʱ�� */
	private Date updateTime;

	/** �����Ƿ���Ч��־ */
	private String validInd;

	/** ���Ա�ע */
	private String remark;
	@Transient
	/**�������ù�������*/
	private String sendSmsName;
	/**
	 * ��GesmsConfig��Ĭ�Ϲ��췽��
	 */
	public GeSmsConfig() {
	}

	/**
	 * ���Զ���ID��getter����
	 */
	@Id
	@Column(name = "SMSID", unique = true,nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSmsId() {
		return this.smsId;
	}

	/**
	 * ���Զ���ID��setter����
	 */
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	/**
	 * �������ù��ܵ�getter����
	 */

	@Column(name = "FUNCTIONID")
	public String getFunctionID() {
		return this.functionID;
	}

	/**
	 * �������ù��ܵ�setter����
	 */
	public void setFunctionID(String functionID) {
		this.functionID = functionID;
	}

	/**
	 * ���Զ������Ƶ�getter����
	 */

	@Column(name = "SMSNAME")
	public String getSmsName() {
		return this.smsName;
	}

	/**
	 * ���Զ������Ƶ�setter����
	 */
	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}

	/**
	 * ���Զ������ݵ�getter����
	 */

	@Column(name = "SMSCONTENT")
	public String getSmsContent() {
		return this.smsContent;
	}

	/**
	 * ���Զ������ݵ�setter����
	 */
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	/**
	 * ���Դ����ߵ�getter����
	 */

	@Column(name = "CREATER")
	public String getCreater() {
		return this.creater;
	}

	/**
	 * ���Դ����ߵ�setter����
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * �����޸��ߵ�getter����
	 */

	@Column(name = "UPDATER")
	public String getUpdater() {
		return this.updater;
	}

	/**
	 * �����޸��ߵ�setter����
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	/**
	 * �����޸�ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * �����޸�ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * �����Ƿ���Ч��־��getter����
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * �����Ƿ���Ч��־��setter����
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	/**
	 * ���Ա�ע��getter����
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���Ա�ע��setter����
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
