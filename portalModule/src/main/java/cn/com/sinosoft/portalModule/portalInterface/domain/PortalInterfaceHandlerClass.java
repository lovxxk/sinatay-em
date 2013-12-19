package cn.com.sinosoft.portalModule.portalInterface.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类接口处理类
 */
@Entity
@Table(name = "PORTAL_INTERFACE_HANDLERCLASS", uniqueConstraints = @UniqueConstraint(columnNames = "INTERFACESERIALNO"))
public class PortalInterfaceHandlerClass implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性处理类名称 */
	private String className;

	/** 属性处理方法 */
	private String methodName;

	/** 属性处理类描述*/
	private String classDesc;
	
	/** 属性接口 */
	private PortalInterface portalInterface;
	
	/** 属性接口账号信息 */
	private List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters = new ArrayList<PortalInterfaceHandlerMethodParameter>(0);
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * 类接口处理类的默认构造方法
	 */
	public PortalInterfaceHandlerClass() {
		
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
	 * 属性处理类名称的getter方法
	 */
	@Column(name = "CLASSNAME")
	public String getClassName() {
		return className;
	}
	
	/**
	 * 属性处理类名称的setter方法
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	/**
	 * 属性处理方法的getter方法
	 */
	@Column(name = "METHODNAME")
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * 属性处理方法的setter方法
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 属性处理类描述的getter方法
	 */
	@Column(name = "CLASSDESC")
	public String getClassDesc() {
		return classDesc;
	}
	
	/**
	 * 属性处理类描述的setter方法
	 */
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
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
		if (getPortalInterface() != null && getPortalInterface().getPortalInterfaceSystem() == null) {
			getPortalInterface().setPortalInterfaceHandlerClass(this);
		}
	}
	
	/**
	 * 属性接口处理方法参数的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterfaceHandlerClass")
	public List<PortalInterfaceHandlerMethodParameter> getPortalInterfaceHandlerMethodParameters() {
		return portalInterfaceHandlerMethodParameters;
	}
	
	/**
	 * 属性接口处理方法参数的setter方法
	 */
	public void setPortalInterfaceHandlerMethodParameters(
			List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters) {
		this.portalInterfaceHandlerMethodParameters = portalInterfaceHandlerMethodParameters;
	}
	
	/**
	 * 属性添加所有接口处理方法参数
	 */
	public void addPortalInterfaceHandlerMethodParameters(List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters) {
		
		for (PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter:portalInterfaceHandlerMethodParameters) {
			if (!getPortalInterfaceHandlerMethodParameters().contains(portalInterfaceHandlerMethodParameter)) {
				getPortalInterfaceHandlerMethodParameters().add(portalInterfaceHandlerMethodParameter);
			}
		}
		
		for (PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter:getPortalInterfaceHandlerMethodParameters()) {
			if (portalInterfaceHandlerMethodParameter.getPortalInterfaceHandlerClass() == null) {
				portalInterfaceHandlerMethodParameter.setPortalInterfaceHandlerClass(this);
			}
			
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
