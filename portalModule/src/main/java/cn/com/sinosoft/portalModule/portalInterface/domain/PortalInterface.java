package cn.com.sinosoft.portalModule.portalInterface.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.BooleanStatus;
import cn.com.sinosoft.portalModule.enumUtil.EncapsulationType;
import cn.com.sinosoft.portalModule.enumUtil.EncryptionType;
import cn.com.sinosoft.portalModule.enumUtil.InterfaceStatus;
import cn.com.sinosoft.portalModule.enumUtil.InterfaceType;
import cn.com.sinosoft.portalModule.enumUtil.RecordMessageType;
import cn.com.sinosoft.portalModule.enumUtil.ReturnType;

/**
 * POJO��AppSystem
 */
@Entity
@Table(name = "PORTAL_INTERFACE", uniqueConstraints = @UniqueConstraint(columnNames = "SYSTEMSERIALNO"))
public class PortalInterface implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Խ��״��� */
	private String transCode;

	/** ���Խ������� */
	private String transName;

	/** �������ݴ������� */
	private String transPortType;

	/** ���Խӿ����� */
	private Integer interfaceType;

	/** ���Խӿ�״̬ */
	private Integer status = InterfaceStatus.ENABLED.getValue();

	/** �����Ƿ��ͷ�ID */
	private String senderId;

	/** �����ǽ��շ�ID */
	private String receiverId;

	/** �������������ַ */
	private String requestURL;

	/** ����������뼯 */
	private String requestEcoding = "GBK";

	/** ���Իص���ַ */
	private String callbackURL;

	/** ����ǰ׺ */
	private String prefix;

	/** ���������ռ� */
	private String namespaceURI;

	/** ���Ա��ز��ַ����� */
	private String localPart;

	/** �����ж� */
	private String action;

	/** �������ô�URL���Ӷ����� */
	private Integer doInput;

	/** �������ý�����д��URL���� */
	private Integer doOutput;

	/** �������ӳ�ʱʱ��  Ĭ��1���� */
	private Integer connectTimeOut = 1 * 60 * 1000;

	/** ���Զ���ʱ Ĭ��1���� */
	private Integer readTimeOut = 1 * 60 * 1000;

	/** ���Ա��ķ�װ���� */
	private Integer encapsulationType;

	/** ���Ա��ķ������� */
	private Integer returnType;

	/** ���Ա��ı��뼯 */
	private String messageEncoding = "GBK";

	/** ���Լ�¼�������� */
	private Integer recordMessageType;

	/** ���Լ������� */
	private Integer encryptionType;

	/** ���Ա��Ĵ������� */
	private String handleMessageType;

	/** ���Բ���Ա */
	private String operatorID;

	/** ���Խӿڽ���ϵͳ */
	private PortalInterfaceSystem portalInterfaceSystem;

	/** ���Խӿڴ����� */
	private PortalInterfaceHandlerClass portalInterfaceHandlerClass;

	/** ���Խӿ��˺���Ϣ */
	private List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters = new ArrayList<PortalInterfaceHandlerMethodParameter>(0);
	
	/** ���Խӿ��˺���Ϣ */
	private List<PortalInterfaceAccount> portalInterfaceAccounts = new ArrayList<PortalInterfaceAccount>(
			0);

	/** ���Խӿ��˺���Ϣ */
	private List<PortalInterfaceElement> portalInterfaceElements = new ArrayList<PortalInterfaceElement>(
			0);

	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/**
	 * ��PortalInterfaceSystem��Ĭ�Ϲ��췽��
	 */
	public PortalInterface() {

	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * ���Խ��״����getter����
	 */
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
	 * �������ݴ������͵�getter����
	 */
	@Column(name = "TRANSPORTTYPE")
	public String getTransPortType() {
		return transPortType;
	}

	/**
	 * �������ݴ������͵�setter����
	 */
	public void setTransPortType(String transPortType) {
		this.transPortType = transPortType;
	}

	/**
	 * ���Խӿ����͵�getter����
	 */

	@Column(name = "INTERFACETYPE")
	public Integer getInterfaceType() {
		return this.interfaceType;
	}

	/**
	 * ���Խӿ�����ö�����getter����
	 */
	@Transient
	public InterfaceType getEnumInterfaceType() {
		if (getInterfaceType() == null) {
			return null;
		}
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceType.class,
						getInterfaceType());
		return interfaceType;
	}

	/**
	 * ���Խӿ����ͺ���ֵ��getter����
	 */
	@Transient
	public String getInterfaceTypeByCoreValue() {
		if (getInterfaceType() == null) {
			return "";
		}
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceType.class,
						getInterfaceType());
		return interfaceType.getCoreValue();
	}

	/**
	 * ���Խӿ������̼�ֵ��getter����
	 */
	@Transient
	public String getInterfaceTypeByMerchantValue() {
		if (getInterfaceType() == null) {
			return "";
		}
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceType.class,
						getInterfaceType());
		return interfaceType.getMerchantValue();
	}

	/**
	 * ���Խӿ����͵�setter����
	 */
	public void setInterfaceType(Integer InterfaceType) {
		this.interfaceType = InterfaceType;
	}

	/**
	 * ���Խӿ����͸�ֵ
	 */
	public void setEnumInterfaceType(InterfaceType interfaceType) {
		if (interfaceType != null) {
			this.interfaceType = interfaceType.getValue();
		}
	}

	/**
	 * ���Ժ��Ľӿ����͸�ֵ
	 */
	public void setInterfaceTypeByCoreValue(String coreValue) {
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByCoreValue(InterfaceType.class, coreValue);
		if (interfaceType != null) {
			this.interfaceType = interfaceType.getValue();
		}
	}

	/**
	 * �����̼ҽӿ����͸�ֵ
	 */
	public void setInterfaceTypeByMerchantValue(String merchantValue) {
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByMerchantValue(InterfaceType.class,
						merchantValue);
		if (interfaceType != null) {
			this.interfaceType = interfaceType.getValue();
		}
	}

	/**
	 * ���Խӿ�״̬��getter����
	 */

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * ���Խӿ�״̬ö�����getter����
	 */
	@Transient
	public InterfaceStatus getEnumStatus() {
		if (getStatus() == null) {
			return null;
		}
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceStatus.class, getStatus());
		return status;
	}

	/**
	 * ���Խӿ�״̬����ֵ��getter����
	 */
	@Transient
	public String getStatusByCoreValue() {
		if (getStatus() == null) {
			return "";
		}
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceStatus.class, getStatus());
		return status.getCoreValue();
	}

	/**
	 * ���Խӿ�״̬�̼�ֵ��getter����
	 */
	@Transient
	public String getStatusByMerchantValue() {
		if (getStatus() == null) {
			return "";
		}
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceStatus.class, getStatus());
		return status.getMerchantValue();
	}

	/**
	 * ���Խӿ�״̬��setter����
	 */
	public void setStatus(Integer Status) {
		this.status = Status;
	}

	/**
	 * ���Խӿ�״̬��ֵ
	 */
	public void setEnumStatus(InterfaceStatus status) {
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * ���Ժ��Ľӿ�״̬��ֵ
	 */
	public void setStatusByCoreValue(String coreValue) {
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByCoreValue(InterfaceStatus.class, coreValue);
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * �����̼ҽӿ�״̬��ֵ
	 */
	public void setStatusByMerchantValue(String merchantValue) {
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByMerchantValue(InterfaceStatus.class,
						merchantValue);
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * ���Է��ͷ�ID��getter����
	 */
	@Column(name = "SENDERID")
	public String getSenderId() {
		return senderId;
	}

	/**
	 * ���Է��ͷ�ID��setter����
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * ���Խ��շ�ID��getter����
	 */
	@Column(name = "RECEIVERID")
	public String getReceiverId() {
		return receiverId;
	}

	/**
	 * ���Խ��շ�ID��setter����
	 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * ���������ַ��getter����
	 */
	@Column(name = "REQUESTURL")
	public String getRequestURL() {
		return requestURL;
	}

	/**
	 * ���������ַ��setter����
	 */
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	/**
	 * ����������뼯��getter����
	 */
	@Column(name = "REQUESTECODING")
	public String getRequestEcoding() {
		return requestEcoding;
	}

	/**
	 * ����������뼯��setter����
	 */
	public void setRequestEcoding(String requestEcoding) {
		this.requestEcoding = requestEcoding;
	}

	/**
	 * ���Իص���ַ��getter����
	 */
	@Column(name = "CALLBACKURL")
	public String getCallbackURL() {
		return callbackURL;
	}

	/**
	 * ���Իص���ַ��setter����
	 */
	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}

	/**
	 * ����ǰ׺��getter����
	 */
	@Column(name = "PREFIX")
	public String getPrefix() {
		return prefix;
	}

	/**
	 * ����ǰ׺��setter����
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * ���������ռ��getter����
	 */
	@Column(name = "NAMESPACEURI")
	public String getNamespaceURI() {
		return namespaceURI;
	}

	/**
	 * ���������ռ��setter����
	 */
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}

	/**
	 * ���Ա��ز��ַ�������getter����
	 */
	@Column(name = "LOCALPART")
	public String getLocalPart() {
		return localPart;
	}

	/**
	 * ���Ա��ز��ַ�������setter����
	 */
	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}

	/**
	 * �����ж���getter����
	 */
	@Column(name = "ACTION")
	public String getAction() {
		return action;
	}

	/**
	 * �����ж���setter����
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * �������ô�URL���Ӷ����ݵ�getter����
	 */

	@Column(name = "DOINPUT")
	public Integer getDoInput() {
		return this.doInput;
	}

	/**
	 * �������ô�URL���Ӷ�����ö�����getter����
	 */
	@Transient
	public BooleanStatus getEnumDoInput() {
		if (getDoInput() == null) {
			return null;
		}
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getDoInput());
		return doInput;
	}

	/**
	 * �������ô�URL���Ӷ����ݺ���ֵ��getter����
	 */
	@Transient
	public String getDoInputByCoreValue() {
		if (getDoInput() == null) {
			return "";
		}
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getDoInput());
		return doInput.getCoreValue();
	}

	/**
	 * �������ô�URL���Ӷ������̼�ֵ��getter����
	 */
	@Transient
	public String getDoInputByMerchantValue() {
		if (getDoInput() == null) {
			return "";
		}
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getDoInput());
		return doInput.getMerchantValue();
	}

	/**
	 * �������ô�URL���Ӷ����ݵ�setter����
	 */
	public void setDoInput(Integer DoInput) {
		this.doInput = DoInput;
	}

	/**
	 * �������ô�URL���Ӷ����ݸ�ֵ
	 */
	public void setEnumDoInput(BooleanStatus doInput) {
		if (doInput != null) {
			this.doInput = doInput.getValue();
		}
	}

	/**
	 * ���Ժ������ô�URL���Ӷ����ݸ�ֵ
	 */
	public void setDoInputByCoreValue(String coreValue) {
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (doInput != null) {
			this.doInput = doInput.getValue();
		}
	}

	/**
	 * �����̼����ô�URL���Ӷ����ݸ�ֵ
	 */
	public void setDoInputByMerchantValue(String merchantValue) {
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByMerchantValue(BooleanStatus.class,
						merchantValue);
		if (doInput != null) {
			this.doInput = doInput.getValue();
		}
	}

	/**
	 * �������ý�����д��URL���ӵ�getter����
	 */

	@Column(name = "DOOUTPUT")
	public Integer getdoOutput() {
		return this.doOutput;
	}

	/**
	 * �������ý�����д��URL����ö�����getter����
	 */
	@Transient
	public BooleanStatus getEnumdoOutput() {
		if (getdoOutput() == null) {
			return null;
		}
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getdoOutput());
		return doOutput;
	}

	/**
	 * �������ý�����д��URL���Ӻ���ֵ��getter����
	 */
	@Transient
	public String getdoOutputByCoreValue() {
		if (getdoOutput() == null) {
			return "";
		}
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getdoOutput());
		return doOutput.getCoreValue();
	}

	/**
	 * �������ý�����д��URL�����̼�ֵ��getter����
	 */
	@Transient
	public String getdoOutputByMerchantValue() {
		if (getdoOutput() == null) {
			return "";
		}
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getdoOutput());
		return doOutput.getMerchantValue();
	}

	/**
	 * �������ý�����д��URL���ӵ�setter����
	 */
	public void setdoOutput(Integer doOutput) {
		this.doOutput = doOutput;
	}

	/**
	 * �������ý�����д��URL���Ӹ�ֵ
	 */
	public void setEnumdoOutput(BooleanStatus doOutput) {
		if (doOutput != null) {
			this.doOutput = doOutput.getValue();
		}
	}

	/**
	 * ���Ժ������ý�����д��URL���Ӹ�ֵ
	 */
	public void setdoOutputByCoreValue(String coreValue) {
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (doOutput != null) {
			this.doOutput = doOutput.getValue();
		}
	}

	/**
	 * �����̼����ý�����д��URL���Ӹ�ֵ
	 */
	public void setdoOutputByMerchantValue(String merchantValue) {
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByMerchantValue(BooleanStatus.class,
						merchantValue);
		if (doOutput != null) {
			this.doOutput = doOutput.getValue();
		}
	}

	/**
	 * �������ӳ�ʱʱ��getter����
	 */
	@Column(name = "CONNECTTIMEOUT")
	public Integer getConnectTimeOut() {
		return connectTimeOut;
	}

	/**
	 * �������ӳ�ʱʱ��setter����
	 */
	public void setConnectTimeOut(Integer connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}

	/**
	 * ���Զ���ʱgetter����
	 */
	@Column(name = "READTIMEOUT")
	public Integer getReadTimeOut() {
		return readTimeOut;
	}

	/**
	 * ���Զ���ʱsetter����
	 */
	public void setReadTimeOut(Integer readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	/**
	 * ���Է�װ���͵�getter����
	 */

	@Column(name = "ENCAPSULATIONTYPE")
	public Integer getEncapsulationType() {
		return this.encapsulationType;
	}

	/**
	 * ���Է�װ����ö�����getter����
	 */
	@Transient
	public EncapsulationType getEnumEncapsulationType() {
		if (getEncapsulationType() == null) {
			return null;
		}
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByValue(EncapsulationType.class,
						getEncapsulationType());
		return encapsulationType;
	}

	/**
	 * ���Է�װ���ͺ���ֵ��getter����
	 */
	@Transient
	public String getEncapsulationTypeByCoreValue() {
		if (getEncapsulationType() == null) {
			return "";
		}
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByValue(EncapsulationType.class,
						getEncapsulationType());
		return encapsulationType.getCoreValue();
	}

	/**
	 * ���Է�װ�����̼�ֵ��getter����
	 */
	@Transient
	public String getEncapsulationTypeByMerchantValue() {
		if (getEncapsulationType() == null) {
			return "";
		}
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByValue(EncapsulationType.class,
						getEncapsulationType());
		return encapsulationType.getMerchantValue();
	}

	/**
	 * ���Է�װ���͵�setter����
	 */
	public void setEncapsulationType(Integer encapsulationType) {
		this.encapsulationType = encapsulationType;
	}

	/**
	 * ���Է�װ���͸�ֵ
	 */
	public void setEnumEncapsulationType(EncapsulationType encapsulationType) {
		if (encapsulationType != null) {
			this.encapsulationType = encapsulationType.getValue();
		}
	}

	/**
	 * ���Ժ��ķ�װ���͸�ֵ
	 */
	public void setEncapsulationTypeByCoreValue(String coreValue) {
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByCoreValue(EncapsulationType.class,
						coreValue);
		if (encapsulationType != null) {
			this.encapsulationType = encapsulationType.getValue();
		}
	}

	/**
	 * �����̼ҷ�װ���͸�ֵ
	 */
	public void setEncapsulationTypeByMerchantValue(String merchantValue) {
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByMerchantValue(EncapsulationType.class,
						merchantValue);
		if (encapsulationType != null) {
			this.encapsulationType = encapsulationType.getValue();
		}
	}

	/**
	 * ���Է������͵�getter����
	 */

	@Column(name = "RETURNTYPE")
	public Integer getReturnType() {
		return this.returnType;
	}

	/**
	 * ���Է�������ö�����getter����
	 */
	@Transient
	public ReturnType getEnumReturnType() {
		if (getReturnType() == null) {
			return null;
		}
		ReturnType returnType = (ReturnType) EnumDataUtils.getEnumDictionaryByValue(ReturnType.class, getReturnType());
		return returnType;
	}

	/**
	 * ���Է������ͺ���ֵ��getter����
	 */
	@Transient
	public String getReturnTypeByCoreValue() {
		if (getReturnType() == null) {
			return "";
		}
		ReturnType ReturnType = (ReturnType) EnumDataUtils.getEnumDictionaryByValue(ReturnType.class, getReturnType());
		return ReturnType.getCoreValue();
	}

	/**
	 * ���Է��������̼�ֵ��getter����
	 */
	@Transient
	public String getReturnTypeByMerchantValue() {
		if (getReturnType() == null) {
			return "";
		}
		ReturnType returnType = (ReturnType) EnumDataUtils.getEnumDictionaryByValue(ReturnType.class, getReturnType());
		return returnType.getMerchantValue();
	}

	/**
	 * ���Է������͵�setter����
	 */
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	/**
	 * ���Է������͸�ֵ
	 */
	public void setEnumReturnType(ReturnType returnType) {
		if (returnType != null) {
			this.returnType = returnType.getValue();
		}
	}

	/**
	 * ���Ժ��ķ������͸�ֵ
	 */
	public void setReturnTypeByCoreValue(String coreValue) {
		ReturnType returnType = (ReturnType) EnumDataUtils.getEnumDictionaryByCoreValue(ReturnType.class, coreValue);
		if (returnType != null) {
			this.returnType = returnType.getValue();
		}
	}

	/**
	 * �����̼ҷ������͸�ֵ
	 */
	public void setReturnTypeByMerchantValue(String merchantValue) {
		ReturnType returnType = (ReturnType) EnumDataUtils.getEnumDictionaryByMerchantValue(ReturnType.class, merchantValue);
		if (returnType != null) {
			this.returnType = returnType.getValue();
		}
	}

	/**
	 * ���Ա��ı��뼯getter����
	 */
	@Column(name = "MESSAGEENCODING")
	public String getMessageEncoding() {
		return messageEncoding;
	}

	/**
	 * ���Ա��ı��뼯setter����
	 */
	public void setMessageEncoding(String messageEncoding) {
		this.messageEncoding = messageEncoding;
	}

	/**
	 * ���Լ�¼�������͵�getter����
	 */

	@Column(name = "RECORDMESSAGETYPE")
	public Integer getRecordMessageType() {
		return this.recordMessageType;
	}

	/**
	 * ���Լ�¼��������ö�����getter����
	 */
	@Transient
	public RecordMessageType getEnumRecordMessageType() {
		if (getRecordMessageType() == null) {
			return null;
		}
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByValue(RecordMessageType.class, getRecordMessageType());
		return recordMessageType;
	}

	/**
	 * ���Լ�¼�������ͺ���ֵ��getter����
	 */
	@Transient
	public String getRecordMessageTypeByCoreValue() {
		if (getRecordMessageType() == null) {
			return "";
		}
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByValue(RecordMessageType.class, getRecordMessageType());
		return recordMessageType.getCoreValue();
	}

	/**
	 * ���Լ�¼���������̼�ֵ��getter����
	 */
	@Transient
	public String getRecordMessageTypeByMerchantValue() {
		if (getRecordMessageType() == null) {
			return "";
		}
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByValue(RecordMessageType.class, getRecordMessageType());
		return recordMessageType.getMerchantValue();
	}

	/**
	 * ���Լ�¼�������͵�setter����
	 */
	public void setRecordMessageType(Integer recordMessageType) {
		this.recordMessageType = recordMessageType;
	}

	/**
	 * ���Լ�¼�������͸�ֵ
	 */
	public void setEnumRecordMessageType(RecordMessageType recordMessageType) {
		if (recordMessageType != null) {
			this.recordMessageType = recordMessageType.getValue();
		}
	}

	/**
	 * ���Ժ��ļ�¼�������͸�ֵ
	 */
	public void setRecordMessageTypeByCoreValue(String coreValue) {
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByCoreValue(RecordMessageType.class, coreValue);
		if (recordMessageType != null) {
			this.recordMessageType = recordMessageType.getValue();
		}
	}

	/**
	 * �����̼Ҽ�¼�������͸�ֵ
	 */
	public void setRecordMessageTypeByMerchantValue(String merchantValue) {
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByMerchantValue(RecordMessageType.class, merchantValue);
		if (recordMessageType != null) {
			this.recordMessageType = recordMessageType.getValue();
		}
	}

	/**
	 * ���Լ�¼�������͵�getter����
	 */

	@Column(name = "ENCRYPTIONTYPE")
	public Integer getEncryptionType() {
		return this.encryptionType;
	}

	/**
	 * ���Լ�¼��������ö�����getter����
	 */
	@Transient
	public EncryptionType getEnumEncryptionType() {
		if (getEncryptionType() == null) {
			return null;
		}
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByValue(EncryptionType.class, getEncryptionType());
		return encryptionType;
	}

	/**
	 * ���Լ�¼�������ͺ���ֵ��getter����
	 */
	@Transient
	public String getEncryptionTypeByCoreValue() {
		if (getEncryptionType() == null) {
			return "";
		}
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByValue(EncryptionType.class, getEncryptionType());
		return encryptionType.getCoreValue();
	}

	/**
	 * ���Լ�¼���������̼�ֵ��getter����
	 */
	@Transient
	public String getEncryptionTypeByMerchantValue() {
		if (getEncryptionType() == null) {
			return "";
		}
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByValue(EncryptionType.class, getEncryptionType());
		return encryptionType.getMerchantValue();
	}

	/**
	 * ���Լ�¼�������͵�setter����
	 */
	public void setEncryptionType(Integer encryptionType) {
		this.encryptionType = encryptionType;
	}

	/**
	 * ���Լ�¼�������͸�ֵ
	 */
	public void setEnumEncryptionType(EncryptionType encryptionType) {
		if (encryptionType != null) {
			this.encryptionType = encryptionType.getValue();
		}
	}

	/**
	 * ���Լ������͸�ֵ
	 */
	public void setEncryptionTypeByCoreValue(String coreValue) {
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByCoreValue(EncryptionType.class, coreValue);
		if (encryptionType != null) {
			this.encryptionType = encryptionType.getValue();
		}
	}

	/**
	 * �����̼Ҽ�¼�������͸�ֵ
	 */
	public void setEncryptionTypeByMerchantValue(String merchantValue) {
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByMerchantValue(EncryptionType.class, merchantValue);
		if (encryptionType != null) {
			this.encryptionType = encryptionType.getValue();
		}
	}

	/**
	 * ���Ա��Ĵ�������getter����
	 */
	@Column(name = "HANDLEMESSAGETYPE")
	public String getHandleMessageType() {
		return handleMessageType;
	}

	/**
	 * ���Ա��Ĵ�������setter����
	 */
	public void setHandleMessageType(String handleMessageType) {
		this.handleMessageType = handleMessageType;
	}

	/**
	 * ���Բ���Ա��getter����
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * ���Բ���Ա��setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * ���Խӿڽ���ϵͳ��getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "SYSTEMSERIALNO", unique = true, nullable = true)
	public PortalInterfaceSystem getPortalInterfaceSystem() {
		return portalInterfaceSystem;
	}

	/**
	 * ���Խӿڽ���ϵͳ��setter����
	 */
	public void setPortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		this.portalInterfaceSystem = portalInterfaceSystem;
		if (getPortalInterfaceSystem() != null && getPortalInterfaceSystem().getPortalInterface() == null) {
			getPortalInterfaceSystem().setPortalInterface(this);
		}
	}

	/**
	 * ���Խӿڴ������getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterface")
	public PortalInterfaceHandlerClass getPortalInterfaceHandlerClass() {
		return portalInterfaceHandlerClass;
	}

	/**
	 * ���Խӿڴ������setter����
	 */
	public void setPortalInterfaceHandlerClass(PortalInterfaceHandlerClass portalInterfaceHandlerClass) {
		this.portalInterfaceHandlerClass = portalInterfaceHandlerClass;
		if (getPortalInterfaceHandlerClass() != null && getPortalInterfaceHandlerClass().getPortalInterface() == null) {
			getPortalInterfaceHandlerClass().setPortalInterface(this);
		}
	}
	
	/**
	 * ���Խӿڴ�����������getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalInterfaceHandlerClass")
	public List<PortalInterfaceHandlerMethodParameter> getPortalInterfaceHandlerMethodParameters() {
		return portalInterfaceHandlerMethodParameters;
	}
	
	/**
	 * ���Խӿڴ�����������setter����
	 */
	public void setPortalInterfaceHandlerMethodParameters(
			List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters) {
		this.portalInterfaceHandlerMethodParameters = portalInterfaceHandlerMethodParameters;
	}
	
	/**
	 * ����������нӿڴ���������
	 */
	public void addPortalInterfaceHandlerMethodParameters(List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters) {
		
		for (PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter:portalInterfaceHandlerMethodParameters) {
			if (!getPortalInterfaceHandlerMethodParameters().contains(portalInterfaceHandlerMethodParameter)) {
				getPortalInterfaceHandlerMethodParameters().add(portalInterfaceHandlerMethodParameter);
			}
		}
		
		for (PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter:getPortalInterfaceHandlerMethodParameters()) {
			if (portalInterfaceHandlerMethodParameter.getPortalInterface() == null) {
				portalInterfaceHandlerMethodParameter.setPortalInterface(this);
			}
			
		}
	}
	
	/**
	 * ���Խӿ��˺���Ϣ��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalInterface")
	@Fetch(FetchMode.SUBSELECT)
	public List<PortalInterfaceAccount> getPortalInterfaceAccounts() {
		return portalInterfaceAccounts;
	}

	/**
	 * ���Խӿ��˺���Ϣ��setter����
	 */
	public void setPortalInterfaceAccounts(
			List<PortalInterfaceAccount> portalInterfaceAccounts) {
		this.portalInterfaceAccounts = portalInterfaceAccounts;
	}
	
	/**
	 * ����������нӿ��˺���Ϣ
	 */
	public void addPortalInterfaceAccounts(List<PortalInterfaceAccount> portalInterfaceAccounts) {
		
		for (PortalInterfaceAccount portalInterfaceAccount:portalInterfaceAccounts) {
			if (!getPortalInterfaceAccounts().contains(portalInterfaceAccount)) {
				getPortalInterfaceAccounts().add(portalInterfaceAccount);
			}
		}
		
		for (PortalInterfaceAccount portalInterfaceAccount:getPortalInterfaceAccounts()) {
			if (portalInterfaceAccount.getPortalInterface() == null) {
				portalInterfaceAccount.setPortalInterface(this);
			}
			
		}
	}
	
	/**
	 * ����Ԫ�ؽڵ��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalInterface")
	@Fetch(FetchMode.SUBSELECT)
	public List<PortalInterfaceElement> getPortalInterfaceElements() {
		return portalInterfaceElements;
	}

	/**
	 * ����Ԫ�ؽڵ��setter����
	 */
	public void setPortalInterfaceElements(
			List<PortalInterfaceElement> portalInterfaceElements) {
		this.portalInterfaceElements = portalInterfaceElements;
	}
	
	/**
	 * �����������Ԫ�ؽڵ�
	 */
	public void addPortalInterfaceElements(List<PortalInterfaceElement> portalInterfaceElements) {
		
		for (PortalInterfaceElement portalInterfaceElement:portalInterfaceElements) {
			if (!getPortalInterfaceElements().contains(portalInterfaceElement)) {
				getPortalInterfaceElements().add(portalInterfaceElement);
			}
		}
		
		for (PortalInterfaceElement portalInterfaceElement:getPortalInterfaceElements()) {
			if (portalInterfaceElement.getPortalInterface() == null) {
				portalInterfaceElement.setPortalInterface(this);
			}
			
		}
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
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
