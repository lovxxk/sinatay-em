package cn.com.sinosoft.portalModule.interfacePortal.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类InterfaceInfo
 */
@Entity
@Table(name = "GE_PORTAL_INTERFACE")
public class InterfaceInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	

	private String esbSvcName;
	
	/** 属性esbserviceversion */
	private String esbserviceversion;

	/** 属性esborisys */
	private String esborisys;

	/** 属性esbtranno */
	private String esbtranno;

	/** 属性esbroutebranchno */
	private String esbroutebranchno;

	/** 属性esbdestsys */
	private String esbroutedestsys;
	
	/** 属性交易代码 */
	private String transCode;

	/** 属性clientUser */
	private ClientUser clientUser;

	/** 属性交易名称 */
	private String transName;

	/** 属性交易类型 */
	private String transType;

	/** 属性业务数据域 */
	private String businessArea;

	/** 属性账户状态 */
	private String status;

	/** 属性是否记录日志 */
	private String isRecordMessage;

	/** 属性报文处理类型 */
	private String handleMessageType;

	/** 属性StubClass名称 */
	private String className;

	/** 属性请服务器地址 */
	private String requestURL;

	/** 属性namespaceURL */
	private String namespaceURL;

	/** 属性本地部分方法名 */
	private String localPart;

	/** 属性本地部分方法参数数量 */
	private Integer localPartParameterNumber;

	/** 属性加密方式 */
	private String encryptionMethod;

	/** 属性密钥 */
	private String encryptionKey;

	/** 属性创建时间 */
	private Date createDate;

	/** 属性更新时间 */
	private Date updateDate;

	/** 属性备注 */
	private String remark;

	/** 属性externalSysUserInterfaceInfos */
	private List<ExternalSysUserInterfaceInfo> externalSysUserInterfaceInfos = new ArrayList<ExternalSysUserInterfaceInfo>(
			0);

	/**
	 * 类InterfaceInfo的默认构造方法
	 */
	public InterfaceInfo() {
	}

	/**
	 * 属性交易代码的getter方法
	 */
	@Id
	@Column(name = "TRANSCODE")
	public String getTransCode() {
		return this.transCode;
	}
	/**
	 * 属性交易代码的setter方法
	 */
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	/**
	 * 属性clientUser的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTUSERID")
	public ClientUser getClientUser() {
		return this.clientUser;
	}
	/**
	 * 属性clientUser的setter方法
	 */
	public void setClientUser(ClientUser clientUser) {
		this.clientUser = clientUser;
	}
	/**
	 * 属性交易名称的getter方法
	 */

	@Column(name = "TRANSNAME")
	public String getTransName() {
		return this.transName;
	}
	/**
	 * 属性交易名称的setter方法
	 */
	public void setTransName(String transName) {
		this.transName = transName;
	}
	/**
	 * 属性交易类型的getter方法
	 */

	@Column(name = "TRANSTYPE")
	public String getTransType() {
		return this.transType;
	}
	/**
	 * 属性交易类型的setter方法
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	/**
	 * 属性业务数据域的getter方法
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}
	/**
	 * 属性业务数据域的setter方法
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	/**
	 * 属性账户状态的getter方法
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}
	/**
	 * 属性账户状态的setter方法
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 属性是否记录日志的getter方法
	 */

	@Column(name = "ISRECORDMESSAGE")
	public String getIsRecordMessage() {
		return this.isRecordMessage;
	}
	/**
	 * 属性是否记录日志的setter方法
	 */
	public void setIsRecordMessage(String isRecordMessage) {
		this.isRecordMessage = isRecordMessage;
	}
	/**
	 * 属性报文处理类型的getter方法
	 */

	@Column(name = "HANDLEMESSAGETYPE")
	public String getHandleMessageType() {
		return this.handleMessageType;
	}
	/**
	 * 属性报文处理类型的setter方法
	 */
	public void setHandleMessageType(String handleMessageType) {
		this.handleMessageType = handleMessageType;
	}
	/**
	 * 属性StubClass名称的getter方法
	 */

	@Column(name = "CLASSNAME")
	public String getClassName() {
		return this.className;
	}
	/**
	 * 属性StubClass名称的setter方法
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * 属性请服务器地址的getter方法
	 */

	@Column(name = "REQUESTURL")
	public String getRequestURL() {
		return this.requestURL;
	}
	/**
	 * 属性请服务器地址的setter方法
	 */
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	/**
	 * 属性namespaceURL的getter方法
	 */

	@Column(name = "NAMESPACEURI")
	public String getNamespaceURL() {
		return this.namespaceURL;
	}
	/**
	 * 属性namespaceURL的setter方法
	 */
	public void setNamespaceURL(String namespaceURL) {
		this.namespaceURL = namespaceURL;
	}
	/**
	 * 属性本地部分方法名的getter方法
	 */

	@Column(name = "LOCALPART")
	public String getLocalPart() {
		return this.localPart;
	}
	/**
	 * 属性本地部分方法名的setter方法
	 */
	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}
	/**
	 * 属性本地部分方法参数数量的getter方法
	 */

	@Column(name = "LOCALPARTPARAMETERNUMBER")
	public Integer getLocalPartParameterNumber() {
		return this.localPartParameterNumber;
	}
	/**
	 * 属性本地部分方法参数数量的setter方法
	 */
	public void setLocalPartParameterNumber(Integer localPartParameterNumber) {
		this.localPartParameterNumber = localPartParameterNumber;
	}
	/**
	 * 属性加密方式的getter方法
	 */

	@Column(name = "ENCRYPTIONMETHOD")
	public String getEncryptionMethod() {
		return this.encryptionMethod;
	}
	/**
	 * 属性加密方式的setter方法
	 */
	public void setEncryptionMethod(String encryptionMethod) {
		this.encryptionMethod = encryptionMethod;
	}
	/**
	 * 属性密钥的getter方法
	 */

	@Column(name = "ENCRYPTIONKEY")
	public String getEncryptionKey() {
		return this.encryptionKey;
	}
	/**
	 * 属性密钥的setter方法
	 */
	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}
	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}
	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	/**
	 * 属性externalSysUserInterfaceInfos的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "interfaceInfo")
	public List<ExternalSysUserInterfaceInfo> getExternalSysUserInterfaceInfos() {
		return this.externalSysUserInterfaceInfos;
	}
	/**
	 * 属性externalSysUserInterfaceInfos的setter方法
	 */
	public void setExternalSysUserInterfaceInfos(
			List<ExternalSysUserInterfaceInfo> externalSysUserInterfaceInfos) {
		this.externalSysUserInterfaceInfos = externalSysUserInterfaceInfos;
	}
	
	/**
	 * 属性esbserviceversion的getter方法
	 */

	@Column(name = "ESBSERVICEVERSION")
	public String getEsbserviceversion() {
		return this.esbserviceversion;
	}

	/**
	 * 属性esbserviceversion的setter方法
	 */
	public void setEsbserviceversion(String esbserviceversion) {
		this.esbserviceversion = esbserviceversion;
	}

	/**
	 * 属性esborisys的getter方法
	 */

	@Column(name = "ESBORISYS")
	public String getEsborisys() {
		return this.esborisys;
	}

	/**
	 * 属性esborisys的setter方法
	 */
	public void setEsborisys(String esborisys) {
		this.esborisys = esborisys;
	}

	/**
	 * 属性esbtranno的getter方法
	 */

	@Column(name = "ESBTRANNO")
	public String getEsbtranno() {
		return this.esbtranno;
	}

	/**
	 * 属性esbtranno的setter方法
	 */
	public void setEsbtranno(String esbtranno) {
		this.esbtranno = esbtranno;
	}

	/**
	 * 属性esbroutebranchno的getter方法
	 */

	@Column(name = "ESBROUTEBRANCHNO")
	public String getEsbroutebranchno() {
		return this.esbroutebranchno;
	}

	/**
	 * 属性esbroutebranchno的setter方法
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
