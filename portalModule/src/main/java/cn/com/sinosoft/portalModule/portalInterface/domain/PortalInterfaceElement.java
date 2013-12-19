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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.BooleanStatus;
import cn.com.sinosoft.portalModule.enumUtil.ElementType;
import cn.com.sinosoft.portalModule.enumUtil.NodeType;

/**
 * POJO类接口处理类
 */
@Entity
@Table(name = "PORTAL_INTERFACE_ELEMENT")
public class PortalInterfaceElement implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性元素名称 */
	private String elementName;

	/** 属性元素值 */
	private String elementValue;

	/** 属性元素类型 */
	private Integer elementType;
	
	/** 属性节点类型 */
	private Integer nodeType;
	
	/** 属性操作（0-不做，1-做）*/
	private Integer done;
	
	/** 属性前缀 */
	private String prefix;
	
	/** 属性命名空间 */
	private String namespaceURI;
	
	/** 属性元素模式 */
	private Integer lineNumber;
	
	/** 属性元素顺序 */
	private Integer elementOrder;
	
	/** 属性操作员 */
	private String operatorID;
	
	/** 属性接口 */
	private PortalInterface portalInterface;
	
	/** 属性处理类 */
	private PortalInterfaceElement portalInterfaceElement;
	
	/** 属性接口账号信息 */
	private List<PortalInterfaceElement> portalInterfaceElements = new ArrayList<PortalInterfaceElement>(0);
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * 类接口处理类的默认构造方法
	 */
	public PortalInterfaceElement() {
		
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
	 * 属性元素名称的getter方法
	 */
	@Column(name = "ELEMENTNAME")
	public String getElementName() {
		return elementName;
	}

	/**
	 * 属性元素名称的setter方法
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	/**
	 * 属性元素值的getter方法
	 */
	@Column(name = "ELEMENTVALUE")
	public String getElementValue() {
		return elementValue;
	}

	/**
	 * 属性元素类名称的setter方法
	 */
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

	/**
	 * 属性元素类型的getter方法
	 */

	@Column(name = "ELEMENTTYPE")
	public Integer getElementType() {
		return this.elementType;
	}

	/**
	 * 属性元素类型枚举类的getter方法
	 */
	@Transient
	public ElementType getEnumElementType() {
		if (getElementType() == null) {
			return null;
		}
		ElementType elementType = (ElementType) EnumDataUtils.getEnumDictionaryByValue(ElementType.class, getElementType());
		return elementType;
	}

	/**
	 * 属性元素类型核心值的getter方法
	 */
	@Transient
	public String getElementTypeByCoreValue() {
		if (getElementType() == null) {
			return "";
		}
		ElementType elementType = (ElementType) EnumDataUtils.getEnumDictionaryByValue(ElementType.class, getElementType());
		return elementType.getCoreValue();
	}

	/**
	 * 属性元素类型商家值的getter方法
	 */
	@Transient
	public String getElementTypeByMerchantValue() {
		if (getElementType() == null) {
			return "";
		}
		ElementType elementType = (ElementType) EnumDataUtils.getEnumDictionaryByValue(ElementType.class, getElementType());
		return elementType.getMerchantValue();
	}

	/**
	 * 属性元素类型的setter方法
	 */
	public void setElementType(Integer elementType) {
		this.elementType = elementType;
	}

	/**
	 * 属性元素类型赋值
	 */
	public void setEnumElementType(ElementType elementType) {
		if (elementType != null) {
			this.elementType = elementType.getValue();
		}
	}

	/**
	 * 属性元素类型赋值
	 */
	public void setElementTypeByCoreValue(String coreValue) {
		ElementType elementType = (ElementType) EnumDataUtils.getEnumDictionaryByCoreValue(ElementType.class, coreValue);
		if (elementType != null) {
			this.elementType = elementType.getValue();
		}
	}

	/**
	 * 属性商家元素类型赋值
	 */
	public void setElementTypeByMerchantValue(String merchantValue) {
		ElementType elementType = (ElementType) EnumDataUtils.getEnumDictionaryByMerchantValue(ElementType.class, merchantValue);
		if (elementType != null) {
			this.elementType = elementType.getValue();
		}
	}
	
	/**
	 * 属性节点类型的getter方法
	 */

	@Column(name = "NODETYPE")
	public Integer getNodeType() {
		return this.nodeType;
	}

	/**
	 * 属性节点类型枚举类的getter方法
	 */
	@Transient
	public NodeType getEnumNodeType() {
		if (getNodeType() == null) {
			return null;
		}
		NodeType nodeType = (NodeType) EnumDataUtils.getEnumDictionaryByValue(NodeType.class, getNodeType());
		return nodeType;
	}

	/**
	 * 属性节点类型核心值的getter方法
	 */
	@Transient
	public String getNodeTypeByCoreValue() {
		if (getNodeType() == null) {
			return "";
		}
		NodeType nodeType = (NodeType) EnumDataUtils.getEnumDictionaryByValue(NodeType.class, getNodeType());
		return nodeType.getCoreValue();
	}

	/**
	 * 属性节点类型商家值的getter方法
	 */
	@Transient
	public String getNodeTypeByMerchantValue() {
		if (getNodeType() == null) {
			return "";
		}
		NodeType nodeType = (NodeType) EnumDataUtils.getEnumDictionaryByValue(NodeType.class, getNodeType());
		return nodeType.getMerchantValue();
	}

	/**
	 * 属性节点类型的setter方法
	 */
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * 属性节点类型赋值
	 */
	public void setEnumNodeType(NodeType nodeType) {
		if (nodeType != null) {
			this.nodeType = nodeType.getValue();
		}
	}

	/**
	 * 属性加密类型赋值
	 */
	public void setNodeTypeByCoreValue(String coreValue) {
		NodeType nodeType = (NodeType) EnumDataUtils.getEnumDictionaryByCoreValue(NodeType.class, coreValue);
		if (nodeType != null) {
			this.nodeType = nodeType.getValue();
		}
	}

	/**
	 * 属性商家节点类型赋值
	 */
	public void setNodeTypeByMerchantValue(String merchantValue) {
		NodeType nodeType = (NodeType) EnumDataUtils.getEnumDictionaryByMerchantValue(NodeType.class, merchantValue);
		if (nodeType != null) {
			this.nodeType = nodeType.getValue();
		}
	}
	
	/**
	 * 属性操作（0-不做，1-做）的getter方法
	 */

	@Column(name = "DONE")
	public Integer getDone() {
		return this.done;
	}
	
	/**
	 * 属性操作（0-不做，1-做）枚举类的getter方法
	 */
	@Transient
	public BooleanStatus getEnumDone() {
		if (getDone() == null) {
			return null;
		}
		BooleanStatus  done = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDone());
		return done;
	}
	
	/**
	 * 属性操作（0-不做，1-做）核心值的getter方法
	 */
	@Transient
	public String getDoneByCoreValue() {
		if (getDone() == null) {
			return "";
		}
		BooleanStatus  done = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDone());
		return done.getCoreValue();
	}
	
	/**
	 * 属性操作（0-不做，1-做）商家值的getter方法
	 */
	@Transient
	public String getDoneByMerchantValue() {
		if (getDone() == null) {
			return "";
		}
		BooleanStatus  done = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDone());
		return done.getMerchantValue();
	}
	
	/**
	 * 属性操作（0-不做，1-做）的setter方法
	 */
	public void setDone(Integer done) {
		this.done = done;
	}
	
	/**
	 * 属性操作（0-不做，1-做）赋值
	 */
	public void setEnumDone(BooleanStatus  done) {
		if (done != null) {
			this.done = done.getValue();
		}
	}
	
	/**
	 * 属性核心操作（0-不做，1-做）赋值
	 */
	public void setDoneByCoreValue(String coreValue) {
		BooleanStatus  done = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (done != null) {
			this.done = done.getValue();
		}
	}
	
	/**
	 * 属性商家操作（0-不做，1-做）赋值
	 */
	public void setDoneByMerchantValue(String merchantValue) {
		BooleanStatus  done = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (done != null) {
			this.done = done.getValue();
		}
	}
	
	/**
	 * 属性前缀的getter方法
	 */
	@Column(name = "PREFIX")
	public String getPrefix() {
		return prefix;
	}

	/**
	 * 属性前缀的setter方法
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * 属性命名空间的getter方法
	 */
	@Column(name = "NAMESPACEURI")
	public String getNamespaceURI() {
		return namespaceURI;
	}
	
	/**
	 * 属性命名空间的setter方法
	 */
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}
	
	/**
	 * 属性行数的getter方法
	 */
	@Column(name = "LINENUMBER")
	public Integer getLineNumber() {
		return lineNumber;
	}
	
	/**
	 * 属性行数的setter方法
	 */
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	/**
	 * 属性元素顺序的getter方法
	 */
	@Column(name = "ELEMENTORDER")
	public Integer getElementOrder() {
		return elementOrder;
	}
	
	/**
	 * 属性元素顺序的setter方法
	 */
	public void setElementOrder(Integer elementOrder) {
		this.elementOrder = elementOrder;
	}
	
	/**
	 * 属性操作员的getter方法
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * 属性操作员的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
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
		if (getPortalInterface() != null && !getPortalInterface().getPortalInterfaceElements().contains(this)) {
			getPortalInterface().getPortalInterfaceElements().add(this);
		}
	}
	
	/**
	 * 属性父节点元素的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTSERIALNO")
	public PortalInterfaceElement getPortalInterfaceElement() {
		return portalInterfaceElement;
	}
	
	/**
	 * 属性父节点元素的setter方法
	 */
	public void setPortalInterfaceElement(
			PortalInterfaceElement portalInterfaceElement) {
		this.portalInterfaceElement = portalInterfaceElement;
		if (getPortalInterfaceElement() != null && !getPortalInterfaceElement().getPortalInterfaceElements().contains(this)) {
			getPortalInterfaceElement().getPortalInterfaceElements().add(this);
		}
	}
	
	/**
	 * 属性子元素节点的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterfaceElement")
	public List<PortalInterfaceElement> getPortalInterfaceElements() {
		return portalInterfaceElements;
	}
	
	/**
	 * 属性子元素节点的setter方法
	 */
	public void setPortalInterfaceElements(
			List<PortalInterfaceElement> portalInterfaceElements) {
		this.portalInterfaceElements = portalInterfaceElements;
	}

	/**
	 * 属性添加所有子元素节点
	 */
	public void addPortalInterfaceElements(List<PortalInterfaceElement> portalInterfaceElements) {
		
		for (PortalInterfaceElement portalInterfaceElement:portalInterfaceElements) {
			if (!getPortalInterfaceElements().contains(portalInterfaceElement)) {
				getPortalInterfaceElements().add(portalInterfaceElement);
			}
		}
		
		for (PortalInterfaceElement portalInterfaceElement:getPortalInterfaceElements()) {
			if (portalInterfaceElement.getPortalInterface() == null) {
				portalInterfaceElement.setPortalInterfaceElement(this);
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
