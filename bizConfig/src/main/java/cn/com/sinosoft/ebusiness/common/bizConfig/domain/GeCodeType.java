package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// default package
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * POJO类GeCodeType
 */
@Entity
@Table(name = "GE_CODE_TYPE")
public class GeCodeType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性代码类型 */
	private String codeType;

	/** 属性简体描述 */
	private String codeTypeCDesc;

	/** 属性英文描述 */
	private String codeTypeEDesc;

	/** 属性繁体描述 */
	private String codeTypeTDesc;

	/** 属性是否有效：1:有效，0：无效 */
	private String validInd;

	/** 属性类型标志 0:普通代码类型（允许进行基础代码设置）；1:特殊代码类型（不允许进行基础代码设置） */
	private String typeInd;

	/** 属性业务领域(1-集团；2-寿险；3-财险；4-养老险；9-其他) */
	private String businessArea;

	/** 属性标志位 */
	private String flag;

	/** 属性geCodes */
	private List<GeCode> geCodes = new ArrayList<GeCode>(0);

	/**
	 * 类GeCodeType的默认构造方法
	 */
	public GeCodeType() {
	}

	/**
	 * 属性代码类型的getter方法
	 */
	@Id
	@Column(name = "CODETYPE")
	public String getCodeType() {
		return this.codeType;
	}

	/**
	 * 属性代码类型的setter方法
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	/**
	 * 属性简体描述的getter方法
	 */

	@Column(name = "CODETYPECDESC")
	public String getCodeTypeCDesc() {
		return this.codeTypeCDesc;
	}

	/**
	 * 属性简体描述的setter方法
	 */
	public void setCodeTypeCDesc(String codeTypeCDesc) {
		this.codeTypeCDesc = codeTypeCDesc;
	}

	/**
	 * 属性英文描述的getter方法
	 */

	@Column(name = "CODETYPEEDESC")
	public String getCodeTypeEDesc() {
		return this.codeTypeEDesc;
	}

	/**
	 * 属性英文描述的setter方法
	 */
	public void setCodeTypeEDesc(String codeTypeEDesc) {
		this.codeTypeEDesc = codeTypeEDesc;
	}

	/**
	 * 属性繁体描述的getter方法
	 */

	@Column(name = "CODETYPETDESC")
	public String getCodeTypeTDesc() {
		return this.codeTypeTDesc;
	}

	/**
	 * 属性繁体描述的setter方法
	 */
	public void setCodeTypeTDesc(String codeTypeTDesc) {
		this.codeTypeTDesc = codeTypeTDesc;
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
	 * 属性类型标志 0:普通代码类型（允许进行基础代码设置）；1:特殊代码类型（不允许进行基础代码设置）的getter方法
	 */

	@Column(name = "TYPEIND")
	public String getTypeInd() {
		return this.typeInd;
	}

	/**
	 * 属性类型标志 0:普通代码类型（允许进行基础代码设置）；1:特殊代码类型（不允许进行基础代码设置）的setter方法
	 */
	public void setTypeInd(String typeInd) {
		this.typeInd = typeInd;
	}

	/**
	 * 属性业务领域(1-集团；2-寿险；3-财险；4-养老险；9-其他)的getter方法
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}

	/**
	 * 属性业务领域(1-集团；2-寿险；3-财险；4-养老险；9-其他)的setter方法
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
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
	 * 属性geCodes的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geCodeType")
	public List<GeCode> getGeCodes() {
		return this.geCodes;
	}

	/**
	 * 属性geCodes的setter方法
	 */
	public void setGeCodes(List<GeCode> geCodes) {
		this.geCodes = geCodes;
	}

}
