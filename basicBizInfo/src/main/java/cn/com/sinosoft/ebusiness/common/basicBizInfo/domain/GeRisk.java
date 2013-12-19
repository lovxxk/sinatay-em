package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * POJO类GeRisk
 */
@Entity
@Table(name = "GE_RISK")
public class GeRisk implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性险种编码 */
	private String riskCode;

	/** 属性中文名称 */
	private String riskCName;

	/** 属性英文名称 */
	private String riskEName;

	/** 属性繁体名称 */
	private String riskTName;

	/** 属性业务领域 */
	private String businessArea;

	/** 属性险种类型 */
	private String riskType;

	/** 属性保险帐户标记 */
	private String insuAccFlag;

	/** 属性是否有效标志 */
	private String validInd;

	/** 属性操作人编号 */
	private String operatorID;

	/** 属性动作时间 */
	private Date createDate;

	/** 属性标识位 */
	private String flag;
	//业务上使用的字段start
	private String eid;
	//业务上使用的字段end
	/**
	 * 类GeRisk的默认构造方法
	 */
	public GeRisk() {
	}

	/**
	 * 属性险种编码的getter方法
	 */
	@Id
	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return this.riskCode;
	}

	/**
	 * 属性险种编码的setter方法
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	/**
	 * 属性中文名称的getter方法
	 */

	@Column(name = "RISKCNAME")
	public String getRiskCName() {
		return this.riskCName;
	}

	/**
	 * 属性中文名称的setter方法
	 */
	public void setRiskCName(String riskCName) {
		this.riskCName = riskCName;
	}

	/**
	 * 属性英文名称的getter方法
	 */

	@Column(name = "RISKENAME")
	public String getRiskEName() {
		return this.riskEName;
	}

	/**
	 * 属性英文名称的setter方法
	 */
	public void setRiskEName(String riskEName) {
		this.riskEName = riskEName;
	}

	/**
	 * 属性繁体名称的getter方法
	 */

	@Column(name = "RISKTNAME")
	public String getRiskTName() {
		return this.riskTName;
	}

	/**
	 * 属性繁体名称的setter方法
	 */
	public void setRiskTName(String riskTName) {
		this.riskTName = riskTName;
	}

	/**
	 * 属性业务领域的getter方法
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}

	/**
	 * 属性业务领域的setter方法
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	/**
	 * 属性险种类型的getter方法
	 */

	@Column(name = "RISKTYPE")
	public String getRiskType() {
		return this.riskType;
	}

	/**
	 * 属性险种类型的setter方法
	 */
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	/**
	 * 属性保险帐户标记的getter方法
	 */

	@Column(name = "INSUACCFLAG")
	public String getInsuAccFlag() {
		return this.insuAccFlag;
	}

	/**
	 * 属性保险帐户标记的setter方法
	 */
	public void setInsuAccFlag(String insuAccFlag) {
		this.insuAccFlag = insuAccFlag;
	}

	/**
	 * 属性是否有效标志的getter方法
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * 属性是否有效标志的setter方法
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	/**
	 * 属性操作人编号的getter方法
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * 属性操作人编号的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	
	/**
	 * 属性动作时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 属性动作时间的setter方法
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 属性标识位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标识位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	//业务上使用的字段start
	@Transient
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
	//业务上使用的字段end
	
}
