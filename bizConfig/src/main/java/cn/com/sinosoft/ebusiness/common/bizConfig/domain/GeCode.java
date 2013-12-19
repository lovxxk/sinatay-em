package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// default package
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * POJO类GeCode
 */
@Entity
@Table(name = "GE_CODE")
public class GeCode implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性id */
	private GeCodeId id;

	/** 属性geCodeType */
	private GeCodeType geCodeType;

	/** 属性显示序号 */
	private Integer orderNo;

	/** 属性代码中文名 */
	private String codeCName;

	/** 属性代码英文名 */
	private String codeEName;

	/** 属性代码繁体名 */
	private String codeTName;

	/** 属性是否有效：1:有效，0：无效 */
	private String validInd;

	/** 属性标志位 */
	private String flag;
	
	/**
	 * 关联核心代码
	 */
	private String codeCoreRelation;
	/**
	 * 类GeCode的默认构造方法
	 */
	public GeCode() {
	}

	/**
	 * 属性id的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeType", column = @Column(name = "CODETYPE")),
			@AttributeOverride(name = "codeCode", column = @Column(name = "CODECODE")) })
	public GeCodeId getId() {
		return this.id;
	}

	/**
	 * 属性id的setter方法
	 */
	public void setId(GeCodeId id) {
		this.id = id;
	}

	/**
	 * 属性geCodeType的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODETYPE", nullable = false, insertable = false, updatable = false)
	public GeCodeType getGeCodeType() {
		return this.geCodeType;
	}

	/**
	 * 属性geCodeType的setter方法
	 */
	public void setGeCodeType(GeCodeType geCodeType) {
		this.geCodeType = geCodeType;
	}

	/**
	 * 属性显示序号的getter方法
	 */

	@Column(name = "ORDERNO")
	public Integer getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 属性显示序号的setter方法
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 属性代码中文名的getter方法
	 */

	@Column(name = "CODECNAME")
	public String getCodeCName() {
		return this.codeCName;
	}

	/**
	 * 属性代码中文名的setter方法
	 */
	public void setCodeCName(String codeCName) {
		this.codeCName = codeCName;
	}

	/**
	 * 属性代码英文名的getter方法
	 */

	@Column(name = "CODEENAME")
	public String getCodeEName() {
		return this.codeEName;
	}

	/**
	 * 属性代码英文名的setter方法
	 */
	public void setCodeEName(String codeEName) {
		this.codeEName = codeEName;
	}

	/**
	 * 属性代码繁体名的getter方法
	 */

	@Column(name = "CODETNAME")
	public String getCodeTName() {
		return this.codeTName;
	}

	/**
	 * 属性代码繁体名的setter方法
	 */
	public void setCodeTName(String codeTName) {
		this.codeTName = codeTName;
	}

	/**
	 * 属性是否有效：1:有效，0：无效的getter方法
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * 属性是否有效：1:有效，0：无效的setter方法
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	/**
	 * 属性标志位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标志位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	/**
	 * 属性关联核心代码的getter方法
	 */
	@Column(name = "CODECORERELATION")
	public String getCodeCoreRelation() {
		return codeCoreRelation;
	}
	
	/**
	 * 属性关联核心代码的setter方法
	 */
	public void setCodeCoreRelation(String codeCoreRelation) {
		this.codeCoreRelation = codeCoreRelation;
	}
	
}
