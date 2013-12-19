package cn.com.sinosoft.portalModule.interfacePortal.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO��InterfaceInfo
 */
@Entity
@Table(name = "GE_PORTAL_INTERFACE")
public class InterfaceInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	

	private String esbSvcName;
	
	/** ����esbserviceversion */
	private String esbserviceversion;

	/** ����esborisys */
	private String esborisys;

	/** ����esbtranno */
	private String esbtranno;

	/** ����esbroutebranchno */
	private String esbroutebranchno;

	/** ����esbdestsys */
	private String esbroutedestsys;
	
	/** ���Խ��״��� */
	private String transCode;

	/** ����clientUser */
	private ClientUser clientUser;

	/** ���Խ������� */
	private String transName;

	/** ���Խ������� */
	private String transType;

	/** ����ҵ�������� */
	private String businessArea;

	/** �����˻�״̬ */
	private String status;

	/** �����Ƿ��¼��־ */
	private String isRecordMessage;

	/** ���Ա��Ĵ������� */
	private String handleMessageType;

	/** ����StubClass���� */
	private String className;

	/** �������������ַ */
	private String requestURL;

	/** ����namespaceURL */
	private String namespaceURL;

	/** ���Ա��ز��ַ����� */
	private String localPart;

	/** ���Ա��ز��ַ����������� */
	private Integer localPartParameterNumber;

	/** ���Լ��ܷ�ʽ */
	private String encryptionMethod;

	/** ������Կ */
	private String encryptionKey;

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
	 * ��InterfaceInfo��Ĭ�Ϲ��췽��
	 */
	public InterfaceInfo() {
	}

	/**
	 * ���Խ��״����getter����
	 */
	@Id
	@Column(name = "TRANSCODE")
	public String getTransCode() {
		return this.transCode;
	}
	/**
	 * ���Խ��״����setter����
	 */
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	/**
	 * ����clientUser��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTUSERID")
	public ClientUser getClientUser() {
		return this.clientUser;
	}
	/**
	 * ����clientUser��setter����
	 */
	public void setClientUser(ClientUser clientUser) {
		this.clientUser = clientUser;
	}
	/**
	 * ���Խ������Ƶ�getter����
	 */

	@Column(name = "TRANSNAME")
	public String getTransName() {
		return this.transName;
	}
	/**
	 * ���Խ������Ƶ�setter����
	 */
	public void setTransName(String transName) {
		this.transName = transName;
	}
	/**
	 * ���Խ������͵�getter����
	 */

	@Column(name = "TRANSTYPE")
	public String getTransType() {
		return this.transType;
	}
	/**
	 * ���Խ������͵�setter����
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	/**
	 * ����ҵ���������getter����
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}
	/**
	 * ����ҵ���������setter����
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
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
	 * �����Ƿ��¼��־��getter����
	 */

	@Column(name = "ISRECORDMESSAGE")
	public String getIsRecordMessage() {
		return this.isRecordMessage;
	}
	/**
	 * �����Ƿ��¼��־��setter����
	 */
	public void setIsRecordMessage(String isRecordMessage) {
		this.isRecordMessage = isRecordMessage;
	}
	/**
	 * ���Ա��Ĵ������͵�getter����
	 */

	@Column(name = "HANDLEMESSAGETYPE")
	public String getHandleMessageType() {
		return this.handleMessageType;
	}
	/**
	 * ���Ա��Ĵ������͵�setter����
	 */
	public void setHandleMessageType(String handleMessageType) {
		this.handleMessageType = handleMessageType;
	}
	/**
	 * ����StubClass���Ƶ�getter����
	 */

	@Column(name = "CLASSNAME")
	public String getClassName() {
		return this.className;
	}
	/**
	 * ����StubClass���Ƶ�setter����
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * �������������ַ��getter����
	 */

	@Column(name = "REQUESTURL")
	public String getRequestURL() {
		return this.requestURL;
	}
	/**
	 * �������������ַ��setter����
	 */
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	/**
	 * ����namespaceURL��getter����
	 */

	@Column(name = "NAMESPACEURI")
	public String getNamespaceURL() {
		return this.namespaceURL;
	}
	/**
	 * ����namespaceURL��setter����
	 */
	public void setNamespaceURL(String namespaceURL) {
		this.namespaceURL = namespaceURL;
	}
	/**
	 * ���Ա��ز��ַ�������getter����
	 */

	@Column(name = "LOCALPART")
	public String getLocalPart() {
		return this.localPart;
	}
	/**
	 * ���Ա��ز��ַ�������setter����
	 */
	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}
	/**
	 * ���Ա��ز��ַ�������������getter����
	 */

	@Column(name = "LOCALPARTPARAMETERNUMBER")
	public Integer getLocalPartParameterNumber() {
		return this.localPartParameterNumber;
	}
	/**
	 * ���Ա��ز��ַ�������������setter����
	 */
	public void setLocalPartParameterNumber(Integer localPartParameterNumber) {
		this.localPartParameterNumber = localPartParameterNumber;
	}
	/**
	 * ���Լ��ܷ�ʽ��getter����
	 */

	@Column(name = "ENCRYPTIONMETHOD")
	public String getEncryptionMethod() {
		return this.encryptionMethod;
	}
	/**
	 * ���Լ��ܷ�ʽ��setter����
	 */
	public void setEncryptionMethod(String encryptionMethod) {
		this.encryptionMethod = encryptionMethod;
	}
	/**
	 * ������Կ��getter����
	 */

	@Column(name = "ENCRYPTIONKEY")
	public String getEncryptionKey() {
		return this.encryptionKey;
	}
	/**
	 * ������Կ��setter����
	 */
	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "interfaceInfo")
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
	 * ����esbserviceversion��getter����
	 */

	@Column(name = "ESBSERVICEVERSION")
	public String getEsbserviceversion() {
		return this.esbserviceversion;
	}

	/**
	 * ����esbserviceversion��setter����
	 */
	public void setEsbserviceversion(String esbserviceversion) {
		this.esbserviceversion = esbserviceversion;
	}

	/**
	 * ����esborisys��getter����
	 */

	@Column(name = "ESBORISYS")
	public String getEsborisys() {
		return this.esborisys;
	}

	/**
	 * ����esborisys��setter����
	 */
	public void setEsborisys(String esborisys) {
		this.esborisys = esborisys;
	}

	/**
	 * ����esbtranno��getter����
	 */

	@Column(name = "ESBTRANNO")
	public String getEsbtranno() {
		return this.esbtranno;
	}

	/**
	 * ����esbtranno��setter����
	 */
	public void setEsbtranno(String esbtranno) {
		this.esbtranno = esbtranno;
	}

	/**
	 * ����esbroutebranchno��getter����
	 */

	@Column(name = "ESBROUTEBRANCHNO")
	public String getEsbroutebranchno() {
		return this.esbroutebranchno;
	}

	/**
	 * ����esbroutebranchno��setter����
	 */
	public void setEsbroutebranchno(String esbroutebranchno) {
		this.esbroutebranchno = esbroutebranchno;
	}

	@Column(name = "ESBROUTEDESTSYS")
	public String getEsbroutedestsys() {
		return esbroutedestsys;
	}

	public void setEsbroutedestsys(String esbroutedestsys) {
		this.esbroutedestsys = esbroutedestsys;
	}

	@Column(name = "ESBSVCNAME")
	public String getEsbSvcName() {
		return esbSvcName;
	}

	public void setEsbSvcName(String esbSvcName) {
		this.esbSvcName = esbSvcName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
