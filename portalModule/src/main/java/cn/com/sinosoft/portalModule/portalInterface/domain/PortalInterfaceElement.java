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
 * POJO��ӿڴ�����
 */
@Entity
@Table(name = "PORTAL_INTERFACE_ELEMENT")
public class PortalInterfaceElement implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����Ԫ������ */
	private String elementName;

	/** ����Ԫ��ֵ */
	private String elementValue;

	/** ����Ԫ������ */
	private Integer elementType;
	
	/** ���Խڵ����� */
	private Integer nodeType;
	
	/** ���Բ�����0-������1-����*/
	private Integer done;
	
	/** ����ǰ׺ */
	private String prefix;
	
	/** ���������ռ� */
	private String namespaceURI;
	
	/** ����Ԫ��ģʽ */
	private Integer lineNumber;
	
	/** ����Ԫ��˳�� */
	private Integer elementOrder;
	
	/** ���Բ���Ա */
	private String operatorID;
	
	/** ���Խӿ� */
	private PortalInterface portalInterface;
	
	/** ���Դ����� */
	private PortalInterfaceElement portalInterfaceElement;
	
	/** ���Խӿ��˺���Ϣ */
	private List<PortalInterfaceElement> portalInterfaceElements = new ArrayList<PortalInterfaceElement>(0);
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/**
	 * ��ӿڴ������Ĭ�Ϲ��췽��
	 */
	public PortalInterfaceElement() {
		
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
	 * ����Ԫ�����Ƶ�getter����
	 */
	@Column(name = "ELEMENTNAME")
	public String getElementName() {
		return elementName;
	}

	/**
	 * ����Ԫ�����Ƶ�setter����
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	/**
	 * ����Ԫ��ֵ��getter����
	 */
	@Column(name = "ELEMENTVALUE")
	public String getElementValue() {
		return elementValue;
	}

	/**
	 * ����Ԫ�������Ƶ�setter����
	 */
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

	/**
	 * ����Ԫ�����͵�getter����
	 */

	@Column(name = "ELEMENTTYPE")
	public Integer getElementType() {
		return this.elementType;
	}

	/**
	 * ����Ԫ������ö�����getter����
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
	 * ����Ԫ�����ͺ���ֵ��getter����
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
	 * ����Ԫ�������̼�ֵ��getter����
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
	 * ����Ԫ�����͵�setter����
	 */
	public void setElementType(Integer elementType) {
		this.elementType = elementType;
	}

	/**
	 * ����Ԫ�����͸�ֵ
	 */
	public void setEnumElementType(ElementType elementType) {
		if (elementType != null) {
			this.elementType = elementType.getValue();
		}
	}

	/**
	 * ����Ԫ�����͸�ֵ
	 */
	public void setElementTypeByCoreValue(String coreValue) {
		ElementType elementType = (ElementType) EnumDataUtils.getEnumDictionaryByCoreValue(ElementType.class, coreValue);
		if (elementType != null) {
			this.elementType = elementType.getValue();
		}
	}

	/**
	 * �����̼�Ԫ�����͸�ֵ
	 */
	public void setElementTypeByMerchantValue(String merchantValue) {
		ElementType elementType = (ElementType) EnumDataUtils.getEnumDictionaryByMerchantValue(ElementType.class, merchantValue);
		if (elementType != null) {
			this.elementType = elementType.getValue();
		}
	}
	
	/**
	 * ���Խڵ����͵�getter����
	 */

	@Column(name = "NODETYPE")
	public Integer getNodeType() {
		return this.nodeType;
	}

	/**
	 * ���Խڵ�����ö�����getter����
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
	 * ���Խڵ����ͺ���ֵ��getter����
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
	 * ���Խڵ������̼�ֵ��getter����
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
	 * ���Խڵ����͵�setter����
	 */
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * ���Խڵ����͸�ֵ
	 */
	public void setEnumNodeType(NodeType nodeType) {
		if (nodeType != null) {
			this.nodeType = nodeType.getValue();
		}
	}

	/**
	 * ���Լ������͸�ֵ
	 */
	public void setNodeTypeByCoreValue(String coreValue) {
		NodeType nodeType = (NodeType) EnumDataUtils.getEnumDictionaryByCoreValue(NodeType.class, coreValue);
		if (nodeType != null) {
			this.nodeType = nodeType.getValue();
		}
	}

	/**
	 * �����̼ҽڵ����͸�ֵ
	 */
	public void setNodeTypeByMerchantValue(String merchantValue) {
		NodeType nodeType = (NodeType) EnumDataUtils.getEnumDictionaryByMerchantValue(NodeType.class, merchantValue);
		if (nodeType != null) {
			this.nodeType = nodeType.getValue();
		}
	}
	
	/**
	 * ���Բ�����0-������1-������getter����
	 */

	@Column(name = "DONE")
	public Integer getDone() {
		return this.done;
	}
	
	/**
	 * ���Բ�����0-������1-����ö�����getter����
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
	 * ���Բ�����0-������1-��������ֵ��getter����
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
	 * ���Բ�����0-������1-�����̼�ֵ��getter����
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
	 * ���Բ�����0-������1-������setter����
	 */
	public void setDone(Integer done) {
		this.done = done;
	}
	
	/**
	 * ���Բ�����0-������1-������ֵ
	 */
	public void setEnumDone(BooleanStatus  done) {
		if (done != null) {
			this.done = done.getValue();
		}
	}
	
	/**
	 * ���Ժ��Ĳ�����0-������1-������ֵ
	 */
	public void setDoneByCoreValue(String coreValue) {
		BooleanStatus  done = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (done != null) {
			this.done = done.getValue();
		}
	}
	
	/**
	 * �����̼Ҳ�����0-������1-������ֵ
	 */
	public void setDoneByMerchantValue(String merchantValue) {
		BooleanStatus  done = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (done != null) {
			this.done = done.getValue();
		}
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
	 * ����������getter����
	 */
	@Column(name = "LINENUMBER")
	public Integer getLineNumber() {
		return lineNumber;
	}
	
	/**
	 * ����������setter����
	 */
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	/**
	 * ����Ԫ��˳���getter����
	 */
	@Column(name = "ELEMENTORDER")
	public Integer getElementOrder() {
		return elementOrder;
	}
	
	/**
	 * ����Ԫ��˳���setter����
	 */
	public void setElementOrder(Integer elementOrder) {
		this.elementOrder = elementOrder;
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
		if (getPortalInterface() != null && !getPortalInterface().getPortalInterfaceElements().contains(this)) {
			getPortalInterface().getPortalInterfaceElements().add(this);
		}
	}
	
	/**
	 * ���Ը��ڵ�Ԫ�ص�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTSERIALNO")
	public PortalInterfaceElement getPortalInterfaceElement() {
		return portalInterfaceElement;
	}
	
	/**
	 * ���Ը��ڵ�Ԫ�ص�setter����
	 */
	public void setPortalInterfaceElement(
			PortalInterfaceElement portalInterfaceElement) {
		this.portalInterfaceElement = portalInterfaceElement;
		if (getPortalInterfaceElement() != null && !getPortalInterfaceElement().getPortalInterfaceElements().contains(this)) {
			getPortalInterfaceElement().getPortalInterfaceElements().add(this);
		}
	}
	
	/**
	 * ������Ԫ�ؽڵ��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterfaceElement")
	public List<PortalInterfaceElement> getPortalInterfaceElements() {
		return portalInterfaceElements;
	}
	
	/**
	 * ������Ԫ�ؽڵ��setter����
	 */
	public void setPortalInterfaceElements(
			List<PortalInterfaceElement> portalInterfaceElements) {
		this.portalInterfaceElements = portalInterfaceElements;
	}

	/**
	 * �������������Ԫ�ؽڵ�
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
