package cn.com.sinosoft.portalModule.portalInterface.domain;

import java.io.Serializable;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PORTAL_INTERFACE_RULEFACTOR")
public class PortalInterfaceRuleFactor implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 属性  序号 */
	private String serialNo;
	
	/** 属性 因子代码 */
	private String factorCode;

	/** 属性 因子名称 */
	private String factorName;

	/** 属性 因子类型 */
	private Integer factorType;

	/** 属性 等级 */
	private Integer factorLevel;

	/** 属性 允许值类型 */
	private Integer allowValuesType;

	/** 属性 报文存储类型 */
	private Integer saveMessageType;
	
	/** 属性 介绍 */
	private String factorDesc;

	/** 属性 外部系统代码（方便查询） */
	private String systemCode;

	/** 属性 交易代码（方便查询） */
	private String transCode;

	/** 属性 请求报文映射文件路径 */
	private String reqCastorMappingPath;

	/** 属性 应答报文映射文件路径 */
	private String resCastorMappingPath;

	/** 属性 请求报文映射文件 */
	private String reqCastorMapping;

	/** 属性 应答报文映射文件 */
	private String resCastorMapping;

	/** 属性 状态(0-未开通  1 -开通) */
	private String status;
	
	/** 属性 外界系统对象 */
	private PortalInterfaceSystem portalInterfaceSystem;

	/** 属性 接口对象 */
	private PortalInterface portalInterface;
	
	/** 属性 接口规则对象 */
	private PortalInterfaceRuleFactor portalInterfaceRuleFactor;
	
	private List<PortalInterfaceRuleFactor> portalInterfaceRuleFactors = new ArrayList<PortalInterfaceRuleFactor>(0);
	
	private List<PortalInterfaceRuleFactorValue> portalInterfaceRuleFactorValues = new ArrayList<PortalInterfaceRuleFactorValue>(0);
	
	/** 属性 操作员 */
	private String operatorID;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * @return the serialNo
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	
	/**
	 * @return the factorCode
	 */
	@Column(name = "FACTORCODE")
	public String getFactorCode() {
		return factorCode;
	}

	/**
	 * @param factorCode the factorCode to set
	 */
	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	/**
	 * @return the factorName
	 */
	@Column(name = "FACTORNAME")
	public String getFactorName() {
		return factorName;
	}

	/**
	 * @param factorName the factorName to set
	 */
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	/**
	 * @return the factorType
	 */
	@Column(name = "FACTORTYPE")
	public Integer getFactorType() {
		return factorType;
	}

	/**
	 * @param factorType the factorType to set
	 */
	public void setFactorType(Integer factorType) {
		this.factorType = factorType;
	}

	/**
	 * @return the factorLevel
	 */
	@Column(name = "FACTORLEVEL")
	public Integer getFactorLevel() {
		return factorLevel;
	}

	/**
	 * @param factorLevel the factorLevel to set
	 */
	public void setFactorLevel(Integer factorLevel) {
		this.factorLevel = factorLevel;
	}

	/**
	 * @return the allowValuesType
	 */
	@Column(name = "ALLOWVALUESTYPE")
	public Integer getAllowValuesType() {
		return allowValuesType;
	}

	/**
	 * @param allowValuesType the allowValuesType to set
	 */
	public void setAllowValuesType(Integer allowValuesType) {
		this.allowValuesType = allowValuesType;
	}

	/**
	 * @return the saveMessageType
	 */
	@Column(name = "SAVEMESSAGETYPE")
	public Integer getSaveMessageType() {
		return saveMessageType;
	}

	/**
	 * @param saveMessageType the saveMessageType to set
	 */
	public void setSaveMessageType(Integer saveMessageType) {
		this.saveMessageType = saveMessageType;
	}

	/**
	 * @return the factorDesc
	 */
	@Column(name = "FACTORDESC")
	public String getFactorDesc() {
		return factorDesc;
	}

	/**
	 * @param factorDesc the factorDesc to set
	 */
	public void setFactorDesc(String factorDesc) {
		this.factorDesc = factorDesc;
	}

	/**
	 * @return the systemCode
	 */
	@Column(name = "SYSTEMCODE")
	public String getSystemCode() {
		return systemCode;
	}

	/**
	 * @param systemCode the systemCode to set
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * @return the transCode
	 */
	@Column(name = "TRANSCODE")
	public String getTransCode() {
		return transCode;
	}

	/**
	 * @param transCode the transCode to set
	 */
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	/**
	 * @return the reqCastorMappingPath
	 */
	@Column(name = "REQCASTORMAPPINGPATH")
	public String getReqCastorMappingPath() {
		return reqCastorMappingPath;
	}

	/**
	 * @param reqCastorMappingPath the reqCastorMappingPath to set
	 */
	public void setReqCastorMappingPath(String reqCastorMappingPath) {
		this.reqCastorMappingPath = reqCastorMappingPath;
	}

	/**
	 * @return the resCastorMappingPath
	 */
	@Column(name = "RESCASTORMAPPINGPATH")
	public String getResCastorMappingPath() {
		return resCastorMappingPath;
	}

	/**
	 * @param resCastorMappingPath the resCastorMappingPath to set
	 */
	public void setResCastorMappingPath(String resCastorMappingPath) {
		this.resCastorMappingPath = resCastorMappingPath;
	}

	/**
	 * @return the reqCastorMapping
	 */
	@Column(name = "REQCASTORMAPPING")
	public String getReqCastorMapping() {
		return reqCastorMapping;
	}

	/**
	 * @param reqCastorMapping the reqCastorMapping to set
	 */
	public void setReqCastorMapping(String reqCastorMapping) {
		this.reqCastorMapping = reqCastorMapping;
	}

	/**
	 * @return the resCastorMapping
	 */
	@Column(name = "RESCASTORMAPPING")
	public String getResCastorMapping() {
		return resCastorMapping;
	}

	/**
	 * @param resCastorMapping the resCastorMapping to set
	 */
	public void setResCastorMapping(String resCastorMapping) {
		this.resCastorMapping = resCastorMapping;
	}

	/**
	 * @return the status
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the portalInterfaceSystem
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "SYSTEMSERIALNO")
	public PortalInterfaceSystem getPortalInterfaceSystem() {
		return portalInterfaceSystem;
	}

	/**
	 * @param portalInterfaceSystem the portalInterfaceSystem to set
	 */
	public void setPortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		this.portalInterfaceSystem = portalInterfaceSystem;
	}

	/**
	 * @return the portalInterface
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "INTERFACESERIALNO")
	public PortalInterface getPortalInterface() {
		return portalInterface;
	}

	/**
	 * @param portalInterface the portalInterface to set
	 */
	public void setPortalInterface(PortalInterface portalInterface) {
		this.portalInterface = portalInterface;
	}

	/**
	 * @return the portalInterfaceRulesFactor
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTSERIALNO")
	public PortalInterfaceRuleFactor getPortalInterfaceRuleFactor() {
		return portalInterfaceRuleFactor;
	}

	public void setPortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor) {
		this.portalInterfaceRuleFactor = portalInterfaceRuleFactor;
	}
	
	/**
	 * @return the portalInterfaceRulesFactors
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch =FetchType.EAGER , mappedBy = "portalInterfaceRuleFactor")
	@Fetch(FetchMode.SUBSELECT)
	public List<PortalInterfaceRuleFactor> getPortalInterfaceRuleFactors() {
		return portalInterfaceRuleFactors;
	}
	
	/**
	 * @param portalInterfaceRuleFactors the portalInterfaceRuleFactors to set
	 */
	public void setPortalInterfaceRuleFactors(List<PortalInterfaceRuleFactor> portalInterfaceRuleFactors) {
		this.portalInterfaceRuleFactors = portalInterfaceRuleFactors;
	}

	/**
	 * @return the portalInterfaceRuleFactorValues
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalInterfaceRuleFactor")
	@Fetch(FetchMode.SUBSELECT)
	public List<PortalInterfaceRuleFactorValue> getPortalInterfaceRuleFactorValues() {
		return portalInterfaceRuleFactorValues;
	}

	/**
	 * @param portalInterfaceRuleFactorValues the portalInterfaceRuleFactorValues to set
	 */
	public void setPortalInterfaceRuleFactorValues(List<PortalInterfaceRuleFactorValue> portalInterfaceRuleFactorValues) {
		this.portalInterfaceRuleFactorValues = portalInterfaceRuleFactorValues;
	}

	/**
	 * @return the operatorID
	 */
	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return operatorID;
	}

	/**
	 * @param operatorID the operatorID to set
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * @return the createTime
	 */
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
