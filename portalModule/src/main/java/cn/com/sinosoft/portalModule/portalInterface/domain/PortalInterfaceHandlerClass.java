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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��ӿڴ�����
 */
@Entity
@Table(name = "PORTAL_INTERFACE_HANDLERCLASS", uniqueConstraints = @UniqueConstraint(columnNames = "INTERFACESERIALNO"))
public class PortalInterfaceHandlerClass implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Դ��������� */
	private String className;

	/** ���Դ����� */
	private String methodName;

	/** ���Դ���������*/
	private String classDesc;
	
	/** ���Խӿ� */
	private PortalInterface portalInterface;
	
	/** ���Խӿ��˺���Ϣ */
	private List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters = new ArrayList<PortalInterfaceHandlerMethodParameter>(0);
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/**
	 * ��ӿڴ������Ĭ�Ϲ��췽��
	 */
	public PortalInterfaceHandlerClass() {
		
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
	 * ���Դ��������Ƶ�getter����
	 */
	@Column(name = "CLASSNAME")
	public String getClassName() {
		return className;
	}
	
	/**
	 * ���Դ��������Ƶ�setter����
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	/**
	 * ���Դ�������getter����
	 */
	@Column(name = "METHODNAME")
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * ���Դ�������setter����
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * ���Դ�����������getter����
	 */
	@Column(name = "CLASSDESC")
	public String getClassDesc() {
		return classDesc;
	}
	
	/**
	 * ���Դ�����������setter����
	 */
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
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
		if (getPortalInterface() != null && getPortalInterface().getPortalInterfaceSystem() == null) {
			getPortalInterface().setPortalInterfaceHandlerClass(this);
		}
	}
	
	/**
	 * ���Խӿڴ�����������getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterfaceHandlerClass")
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
			if (portalInterfaceHandlerMethodParameter.getPortalInterfaceHandlerClass() == null) {
				portalInterfaceHandlerMethodParameter.setPortalInterfaceHandlerClass(this);
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
