package cn.com.sinosoft.portalModule.portalInterface.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类接口处理类
 */
@Entity
@Table(name = "PORTAL_INTERFACE_METHODPARAM")
public class PortalInterfaceHandlerMethodParameter implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性参数名称 */
	private String parameterName;

	/** 属性参数类名称 */
	private String parameterClassName;

	/** 属性参数类型 */
	private String parameterType;
	
	/** 属性参数类型名称 */
	private String parameterTypeName;
	
	/** 属性参数值 */
	private String parameterValue;
	
	/** 属性参数默认值 */
	private String defaultValue;
	
	/** 属性加密值 */
	private String encodedValue;
	
	/** 属性输入参数值名称 */
	private Object inputObjectValue;
	
	/** 属性参数模式 */
	private Integer parameterMode;
	
	/** 属性参数顺序 */
	private Integer parameterOrder;
	
	/** 属性接口 */
	private PortalInterface portalInterface;
	
	/** 属性处理类 */
	private PortalInterfaceHandlerClass portalInterfaceHandlerClass;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * 类接口处理类的默认构造方法
	 */
	public PortalInterfaceHandlerMethodParameter() {
		
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}
	
	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * 属性参数名称的getter方法
	 */
	@Column(name = "PARAMETERNAME")
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * 属性参数名称的setter方法
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	/**
	 * 属性参数类名称的getter方法
	 */
	@Column(name = "PARAMETERCLASSNAME")
	public String getParameterClassName() {
		return parameterClassName;
	}

	/**
	 * 属性参数类名称的setter方法
	 */
	public void setParameterClassName(String parameterClassName) {
		this.parameterClassName = parameterClassName;
	}

	/**
	 * 属性参数类型的getter方法
	 */
	@Column(name = "PARAMETERTYPE")
	public String getParameterType() {
		return parameterType;
	}
	
	/**
	 * 属性参数类型的setter方法
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	
	/**
	 * 属性参数类型名称的getter方法
	 */
	@Column(name = "PARAMETERTYPENAME")
	public String getParameterTypeName() {
		return parameterTypeName;
	}
	
	/**
	 * 属性参数类型名称的setter方法
	 */
	public void setParameterTypeName(String parameterTypeName) {
		this.parameterTypeName = parameterTypeName;
	}
	
	/**
	 * 属性参数值的getter方法
	 */
	@Column(name = "PARAMETERVALUE")
	public String getParameterValue() {
		return parameterValue;
	}
	
	/**
	 * 属性参数值的setter方法
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	/**
	 * 属性参数默认值的getter方法
	 */
	@Column(name = "DEFAULTVALUE")
	public String getDefaultValue() {
		return defaultValue;
	}
	
	/**
	 * 属性参数默认值的setter方法
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	/**
	 * 属性加密值的getter方法
	 */
	@Column(name = "ENCODEDVALUE")
	public String getEncodedValue() {
		return encodedValue;
	}
	
	/**
	 * 属性加密值的setter方法
	 */
	public void setEncodedValue(String encodedValue) {
		this.encodedValue = encodedValue;
	}
	
	/**
	 * 属性输入参数对象的getter方法
	 */
	@Transient
	public Object getInputObjectValue() {
		return inputObjectValue;
	}
	
	/**
	 * 属性输入参数对象的setter方法
	 */
	public void setInputObjectValue(Object inputObjectValue) {
		this.inputObjectValue = inputObjectValue;
	}

	/**
	 * 属性参数模式的getter方法
	 */
	@Column(name = "PARAMETERMODE")
	public Integer getParameterMode() {
		return parameterMode;
	}
	
	/**
	 * 属性参数模式的setter方法
	 */
	public void setParameterMode(Integer parameterMode) {
		this.parameterMode = parameterMode;
	}
	
	/**
	 * 属性参数顺序的getter方法
	 */
	@Column(name = "PARAMETERORDER")
	public Integer getParameterOrder() {
		return parameterOrder;
	}
	
	/**
	 * 属性参数顺序的setter方法
	 */
	public void setParameterOrder(Integer parameterOrder) {
		this.parameterOrder = parameterOrder;
	}
	
	/**
	 * 属性接口的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "INTERFACESERIALNO", unique = true, nullable = false)
	public PortalInterface getPortalInterface() {
		return portalInterface;
	}
	
	/**
	 * 属性接口的getter方法
	 */
	public void setPortalInterface(PortalInterface portalInterface) {
		this.portalInterface = portalInterface;
	}
	
	/**
	 * 属性接口的getter方法
	 */
	public void addPortalInterface(PortalInterface portalInterface) {
		this.portalInterface = portalInterface;
		if (getPortalInterface() != null && !getPortalInterface().getPortalInterfaceHandlerMethodParameters().contains(this)) {
			getPortalInterface().getPortalInterfaceHandlerMethodParameters().add(this);
		}
	}
	
	/**
	 * 属性接口处理类的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASSSERIALNO")
	public PortalInterfaceHandlerClass getPortalInterfaceHandlerClass() {
		return portalInterfaceHandlerClass;
	}
	
	/**
	 * 属性接口处理类的setter方法
	 */
	public void setPortalInterfaceHandlerClass(
			PortalInterfaceHandlerClass portalInterfaceHandlerClass) {
		this.portalInterfaceHandlerClass = portalInterfaceHandlerClass;
	}
	
	/**
	 * 属性接口处理类的setter方法
	 */
	public void addPortalInterfaceHandlerClass(
			PortalInterfaceHandlerClass portalInterfaceHandlerClass) {
		this.portalInterfaceHandlerClass = portalInterfaceHandlerClass;
		if (getPortalInterfaceHandlerClass() != null && !getPortalInterfaceHandlerClass().getPortalInterfaceHandlerMethodParameters().contains(this)) {
			getPortalInterfaceHandlerClass().getPortalInterfaceHandlerMethodParameters().add(this);
		}
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
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
