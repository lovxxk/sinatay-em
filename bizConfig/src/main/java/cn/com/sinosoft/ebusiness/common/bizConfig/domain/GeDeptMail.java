package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// default package
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeDeptMail
 */
@Entity
@Table(name = "GE_DEPT_MAIL")
public class GeDeptMail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	// �ֻ�
	private String mobile;
	
	
	
	/** ���Ի�����������ID */
	private String deptMailID;

//	/** ���Ի�����-GE_Department */
//	private GeDepartment geDepartment;
	/** ���Ի������ */
	private String deptID;

	/** �������ù���ID */
	private String functionID;

	/** �������� */
	private String mail;

	/** ���Դ����� */
	private String creater;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** �����޸��� */
	private String updater;

	/** �����޸�ʱ�� */
	private Date updateTime;

	/** ���Ա�ע */
	private String remark;

	/** �����Ƿ���Ч��־ */
	private String validInd;
	@Transient
	/** ��������ʡ */
	private String provinceCode;
	@Transient
	/** ���������� */
	private String cityCode;
	@Transient
	/**��������*/
	private String departNmae;
	@Transient
	/**���䷢�ͻ�������*/
	private String sendMailName;
	/**
	 * ��GeDeptMail��Ĭ�Ϲ��췽��
	 */
	public GeDeptMail() {
	}
	
	/**
	 * ���Ի�����������ID��getter����
	 */
	@Id
	@Column(name = "DEPTMAILID", unique = true,nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getDeptMailID() {
		return this.deptMailID;
	}

	/**
	 * ���Ի�����������ID��setter����
	 */
	public void setDeptMailID(String deptMailID) {
		this.deptMailID = deptMailID;
	}

//	/**
//	 * ���Ի�����-GE_Department��getter����
//	 */
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "DEPTID", nullable = false)
//	public GeDepartment getGeDepartment() {
//		return this.geDepartment;
//	}
//
//	/**
//	 * ���Ի�����-GE_Department��setter����
//	 */
//	public void setGeDepartment(GeDepartment geDepartment) {
//		this.geDepartment = geDepartment;
//	}
	/**
	 * ���Ի�����ŵ�getter����
	 */

	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}

	/**
	 * ���Ի�����ŵ�setter����
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	/**
	 * �������ù���ID��getter����
	 */

	@Column(name = "FUNCTIONID")
	public String getFunctionID() {
		return this.functionID;
	}

	/**
	 * �������ù���ID��setter����
	 */
	public void setFunctionID(String functionID) {
		this.functionID = functionID;
	}

	/**
	 * ���������getter����
	 */

	@Column(name = "MAIL")
	public String getMail() {
		return this.mail;
	}

	/**
	 * ���������setter����
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
	@Transient
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	@Transient
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	@Transient
	public String getDepartNmae() {
		return departNmae;
	}

	public void setDepartNmae(String departNmae) {
		this.departNmae = departNmae;
	}
	@Transient
	public String getSendMailName() {
		return sendMailName;
	}

	public void setSendMailName(String sendMailName) {
		this.sendMailName = sendMailName;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
