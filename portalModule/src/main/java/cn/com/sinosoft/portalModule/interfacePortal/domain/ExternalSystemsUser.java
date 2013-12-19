package cn.com.sinosoft.portalModule.interfacePortal.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��ExternalSystemsUser
 */
@Entity
@Table(name = "GE_PORTAL_EXSYSUSER", uniqueConstraints = @UniqueConstraint(columnNames = {
		"LOGINNAME", "IPADDRESS"}))
public class ExternalSystemsUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	
	/** ����esbauthbranchno */
	private String esbauthbranchno;

	/** ����esbsignature */
	private String esbsignature;
	
	/** �����߼����� */
	private String id;

	/** ����externalSysInfo */
	private ExternalSysInfo externalSysInfo;

	/** ���Ե�¼�� */
	private String loginName;

	/** ���Ե�¼���� */
	private String password;

	/** �����û�IP��ַ */
	private String ipAddress;

	/** �����˻�״̬ */
	private String status;

	/** ���Դ���ʱ�� */
	private Date createDate;

	/** ���Ը���ʱ�� */
	private Date updateDate;

	/** ���Ա�ע */
	private String remark;

	/** ����externalSysUserInterfaceInfos */
	private List<ExternalSysUserInterfaceInfo> externalSysUserInterfaceInfos = new ArrayList<ExternalSysUserInterfaceInfo>(
			0);

	/**
	 * ��ExternalSystemsUser��Ĭ�Ϲ��췽��
	 */
	public ExternalSystemsUser() {
	}

	/**
	 * �����߼�������getter����
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getId() {
		return this.id;
	}
	/**
	 * �����߼�������setter����
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * ����externalSysInfo��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXTERNALSYSID", nullable = false)
	public ExternalSysInfo getExternalSysInfo() {
		return this.externalSysInfo;
	}
	/**
	 * ����externalSysInfo��setter����
	 */
	public void setExternalSysInfo(ExternalSysInfo externalSysInfo) {
		this.externalSysInfo = externalSysInfo;
	}
	/**
	 * ���Ե�¼����getter����
	 */

	@Column(name = "LOGINNAME")
	public String getLoginName() {
		return this.loginName;
	}
	/**
	 * ���Ե�¼����setter����
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * ���Ե�¼�����getter����
	 */

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}
	/**
	 * ���Ե�¼�����setter����
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * �����û�IP��ַ��getter����
	 */

	@Column(name = "IPADDRESS")
	public String getIpAddress() {
		return this.ipAddress;
	}
	/**
	 * �����û�IP��ַ��setter����
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * �����˻�״̬��getter����
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}
	/**
	 * �����˻�״̬��setter����
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}
	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	 * ����externalSysUserInterfaceInfos��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "externalSystemsUser")
	public List<ExternalSysUserInterfaceInfo> getExternalSysUserInterfaceInfos() {
		return this.externalSysUserInterfaceInfos;
	}
	/**
	 * ����externalSysUserInterfaceInfos��setter����
	 */
	public void setExternalSysUserInterfaceInfos(
			List<ExternalSysUserInterfaceInfo> externalSysUserInterfaceInfos) {
		this.externalSysUserInterfaceInfos = externalSysUserInterfaceInfos;
	}
	
	/**
	 * ����esbauthbranchno��getter����
	 */

	@Column(name = "ESBAUTHBRANCHNO")
	public String getEsbauthbranchno() {
		return this.esbauthbranchno;
	}

	/**
	 * ����esbauthbranchno��setter����
	 */
	public void setEsbauthbranchno(String esbauthbranchno) {
		this.esbauthbranchno = esbauthbranchno;
	}

	/**
	 * ����esbsignature��getter����
	 */

	@Column(name = "ESBSIGNATURE")
	public String getEsbsignature() {
		return this.esbsignature;
	}

	/**
	 * ����esbsignature��setter����
	 */
	public void setEsbsignature(String esbsignature) {
		this.esbsignature = esbsignature;
	}


}
