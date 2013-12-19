package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeProductConfigMain
 */
@Entity
@Table(name = "GE_PRODUCT_CONFIGMAIN")
public class GeProductConfigMain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性产品基本信息  */
	private String productInfo;

	/** 属性调查问卷 */
	private String question;

	/** 属性保费试算  */
	private String calculation;

	/** 属性法律声明 */
	private String legalNotices;

	/** 属性投保告知 */
	private String inform;

	/** 属性投保声明  */
	private String proposalNotices;

	/** 属性保单配置 */
	private String policy;

	/**
	 * 类GeProductConfigMain的默认构造方法
	 */
	public GeProductConfigMain() {
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
	 * 属性产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * 属性产品的setter方法
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * 属性产品基本信息 的getter方法
	 */

	@Column(name = "PRODUCTINFO")
	public String getProductInfo() {
		return this.productInfo;
	}
	/**
	 * 属性产品基本信息 的setter方法
	 */
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	/**
	 * 属性调查问卷的getter方法
	 */

	@Column(name = "QUESTION")
	public String getQuestion() {
		return this.question;
	}
	/**
	 * 属性调查问卷的setter方法
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 属性保费试算 的getter方法
	 */

	@Column(name = "CALCULATION")
	public String getCalculation() {
		return this.calculation;
	}
	/**
	 * 属性保费试算 的setter方法
	 */
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
	/**
	 * 属性法律声明的getter方法
	 */

	@Column(name = "LEGALNOTICES")
	public String getLegalNotices() {
		return this.legalNotices;
	}
	/**
	 * 属性法律声明的setter方法
	 */
	public void setLegalNotices(String legalNotices) {
		this.legalNotices = legalNotices;
	}
	/**
	 * 属性投保告知的getter方法
	 */

	@Column(name = "INFORM")
	public String getInform() {
		return this.inform;
	}
	/**
	 * 属性投保告知的setter方法
	 */
	public void setInform(String inform) {
		this.inform = inform;
	}
	/**
	 * 属性投保声明 的getter方法
	 */

	@Column(name = "PROPOSALNOTICES")
	public String getProposalNotices() {
		return this.proposalNotices;
	}
	/**
	 * 属性投保声明 的setter方法
	 */
	public void setProposalNotices(String proposalNotices) {
		this.proposalNotices = proposalNotices;
	}
	/**
	 * 属性保单配置的getter方法
	 */

	@Column(name = "POLICY")
	public String getPolicy() {
		return this.policy;
	}
	/**
	 * 属性保单配置的setter方法
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}

}
