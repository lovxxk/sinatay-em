package cn.com.sinosoft.portalModule.portalInterface.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��ӿڴ�����
 */
@Entity
@Table(name = "PORTAL_INTERFACE_METHODPARAM")
public class PortalInterfaceHandlerMethodParameter implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ������� */
	private String parameterName;

	/** ���Բ��������� */
	private String parameterClassName;

	/** ���Բ������� */
	private String parameterType;
	
	/** ���Բ����������� */
	private String parameterTypeName;
	
	/** ���Բ���ֵ */
	private String parameterValue;
	
	/** ���Բ���Ĭ��ֵ */
	private String defaultValue;
	
	/** ���Լ���ֵ */
	private String encodedValue;
	
	/** �����������ֵ���� */
	private Object inputObjectValue;
	
	/** ���Բ���ģʽ */
	private Integer parameterMode;
	
	/** ���Բ���˳�� */
	private Integer parameterOrder;
	
	/** ���Խӿ� */
	private PortalInterface portalInterface;
	
	/** ���Դ����� */
	private PortalInterfaceHandlerClass portalInterfaceHandlerClass;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/**
	 * ��ӿڴ������Ĭ�Ϲ��췽��
	 */
	public PortalInterfaceHandlerMethodParameter() {
		
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
	 * ���Բ������Ƶ�getter����
	 */
	@Column(name = "PARAMETERNAME")
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * ���Բ������Ƶ�setter����
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	/**
	 * ���Բ��������Ƶ�getter����
	 */
	@Column(name = "PARAMETERCLASSNAME")
	public String getParameterClassName() {
		return parameterClassName;
	}

	/**
	 * ���Բ��������Ƶ�setter����
	 */
	public void setParameterClassName(String parameterClassName) {
		this.parameterClassName = parameterClassName;
	}

	/**
	 * ���Բ������͵�getter����
	 */
	@Column(name = "PARAMETERTYPE")
	public String getParameterType() {
		return parameterType;
	}
	
	/**
	 * ���Բ������͵�setter����
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	
	/**
	 * ���Բ����������Ƶ�getter����
	 */
	@Column(name = "PARAMETERTYPENAME")
	public String getParameterTypeName() {
		return parameterTypeName;
	}
	
	/**
	 * ���Բ����������Ƶ�setter����
	 */
	public void setParameterTypeName(String parameterTypeName) {
		this.parameterTypeName = parameterTypeName;
	}
	
	/**
	 * ���Բ���ֵ��getter����
	 */
	@Column(name = "PARAMETERVALUE")
	public String getParameterValue() {
		return parameterValue;
	}
	
	/**
	 * ���Բ���ֵ��setter����
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	/**
	 * ���Բ���Ĭ��ֵ��getter����
	 */
	@Column(name = "DEFAULTVALUE")
	public String getDefaultValue() {
		return defaultValue;
	}
	
	/**
	 * ���Բ���Ĭ��ֵ��setter����
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	/**
	 * ���Լ���ֵ��getter����
	 */
	@Column(name = "ENCODEDVALUE")
	public String getEncodedValue() {
		return encodedValue;
	}
	
	/**
	 * ���Լ���ֵ��setter����
	 */
	public void setEncodedValue(String encodedValue) {
		this.encodedValue = encodedValue;
	}
	
	/**
	 * ����������������getter����
	 */
	@Transient
	public Object getInputObjectValue() {
		return inputObjectValue;
	}
	
	/**
	 * ����������������setter����
	 */
	public void setInputObjectValue(Object inputObjectValue) {
		this.inputObjectValue = inputObjectValue;
	}

	/**
	 * ���Բ���ģʽ��getter����
	 */
	@Column(name = "PARAMETERMODE")
	public Integer getParameterMode() {
		return parameterMode;
	}
	
	/**
	 * ���Բ���ģʽ��setter����
	 */
	public void setParameterMode(Integer parameterMode) {
		this.parameterMode = parameterMode;
	}
	
	/**
	 * ���Բ���˳���getter����
	 */
	@Column(name = "PARAMETERORDER")
	public Integer getParameterOrder() {
		return parameterOrder;
	}
	
	/**
	 * ���Բ���˳���setter����
	 */
	public void setParameterOrder(Integer parameterOrder) {
		this.parameterOrder = parameterOrder;
	}
	
	/**
	 * ���Խӿڵ�getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "INTERFACESERIALNO", unique = true, nullable = false)
	public PortalInterface getPortalInterface() {
		return portalInterface;
	}
	
	/**
	 * ���Խӿڵ�getter����
	 */
	public void setPortalInterface(PortalInterface portalInterface) {
		this.portalInterface = portalInterface;
	}
	
	/**
	 * ���Խӿڵ�getter����
	 */
	public void addPortalInterface(PortalInterface portalInterface) {
		this.portalInterface = portalInterface;
		if (getPortalInterface() != null && !getPortalInterface().getPortalInterfaceHandlerMethodParameters().contains(this)) {
			getPortalInterface().getPortalInterfaceHandlerMethodParameters().add(this);
		}
	}
	
	/**
	 * ���Խӿڴ������getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASSSERIALNO")
	public PortalInterfaceHandlerClass getPortalInterfaceHandlerClass() {
		return portalInterfaceHandlerClass;
	}
	
	/**
	 * ���Խӿڴ������setter����
	 */
	public void setPortalInterfaceHandlerClass(
			PortalInterfaceHandlerClass portalInterfaceHandlerClass) {
		this.portalInterfaceHandlerClass = portalInterfaceHandlerClass;
	}
	
	/**
	 * ���Խӿڴ������setter����
	 */
	public void addPortalInterfaceHandlerClass(
			PortalInterfaceHandlerClass portalInterfaceHandlerClass) {
		this.portalInterfaceHandlerClass = portalInterfaceHandlerClass;
		if (getPortalInterfaceHandlerClass() != null && !getPortalInterfaceHandlerClass().getPortalInterfaceHandlerMethodParameters().contains(this)) {
			getPortalInterfaceHandlerClass().getPortalInterfaceHandlerMethodParameters().add(this);
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
