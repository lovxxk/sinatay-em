package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * POJO类GeKind
 */
@Entity
@Table(name = "GE_KIND")
public class GeKind implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性ID */
	private GeKindId id;

	/** 属性combinekindcode */
	private String combinekindcode;

	/** 属性险别中文名称 */
	private String kindCName;

	/** 属性险别英文名称 */
	private String kindEName;

	/** 属性kindtname */
	private String kindtname;

	/** 属性kindflag */
	private String kindflag;

	/** 属性代码类型 */
	private String codeType;

	/** 属性valuerange */
	private String valuerange;

	/** 属性显示顺序序号 */
	private Integer orderNo;

	/** 属性nodeductflag */
	private String nodeductflag;
	
	/** 属性有效标志 */
	private String validInd;
	
	/** 套餐配置显示配置*/
	private String isComboFlag;

	/** 属性标志 */
	private String flag;
	
	/** 缩写 */
	private String abbreviation;

	/** 险别描述 */
	private String kindDescription;
	
	public String getKindDescription() {
		return kindDescription;
	}
	public void setKindDescription(String kindDescription) {
		this.kindDescription = kindDescription;
	}
	/**
	 * 类GeKind的默认构造方法
	 */
	public GeKind() {
	}

	private List<GeKindRelate> geKindRelateList = new ArrayList<GeKindRelate>(0);
	
	/**
	 * 属性ID的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "riskCode", column = @Column(name = "RISKCODE")),
			@AttributeOverride(name = "kindCode", column = @Column(name = "KINDCODE")) })
	public GeKindId getId() {
		return this.id;
	}

	/**
	 * 属性ID的setter方法
	 */
	public void setId(GeKindId id) {
		this.id = id;
	}

	/**
	 * 属性combinekindcode的getter方法
	 */

	@Column(name = "COMBINEKINDCODE")
	public String getCombinekindcode() {
		return this.combinekindcode;
	}

	/**
	 * 属性combinekindcode的setter方法
	 */
	public void setCombinekindcode(String combinekindcode) {
		this.combinekindcode = combinekindcode;
	}

	/**
	 * 属性险别中文名称的getter方法
	 */

	@Column(name = "KINDCNAME")
	public String getKindCName() {
		return this.kindCName;
	}

	/**
	 * 属性险别中文名称的setter方法
	 */
	public void setKindCName(String kindCName) {
		this.kindCName = kindCName;
	}

	/**
	 * 属性险别英文名称的getter方法
	 */

	@Column(name = "KINDENAME")
	public String getKindEName() {
		return this.kindEName;
	}

	/**
	 * 属性险别英文名称的setter方法
	 */
	public void setKindEName(String kindEName) {
		this.kindEName = kindEName;
	}

	/**
	 * 属性kindtname的getter方法
	 */

	@Column(name = "KINDTNAME")
	public String getKindtname() {
		return this.kindtname;
	}

	/**
	 * 属性kindtname的setter方法
	 */
	public void setKindtname(String kindtname) {
		this.kindtname = kindtname;
	}

	/**
	 * 属性kindflag的getter方法
	 */

	@Column(name = "KINDFLAG")
	public String getKindflag() {
		return this.kindflag;
	}

	/**
	 * 属性kindflag的setter方法
	 */
	public void setKindflag(String kindflag) {
		this.kindflag = kindflag;
	}

	/**
	 * 属性代码类型的getter方法
	 */

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
	 * 属性valuerange的getter方法
	 */

	@Column(name = "VALUERANGE")
	public String getValuerange() {
		return this.valuerange;
	}

	/**
	 * 属性valuerange的setter方法
	 */
	public void setValuerange(String valuerange) {
		this.valuerange = valuerange;
	}

	/**
	 * 属性显示顺序序号的getter方法
	 */

	@Column(name = "ORDERNO")
	public Integer getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 属性显示顺序序号的setter方法
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 属性nodeductflag的getter方法
	 */

	@Column(name = "NODEDUCTFLAG")
	public String getNodeductflag() {
		return this.nodeductflag;
	}

	/**
	 * 属性nodeductflag的setter方法
	 */
	public void setNodeductflag(String nodeductflag) {
		this.nodeductflag = nodeductflag;
	}

	
	/**
	 * 属性有效标志的getter方法
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * 属性有效标志的setter方法
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}
	
	/**
	 * 属性套餐配置显示配置的getter方法
	 */
	@Column(name = "ISCOMBOFLAG")
	public String getIsComboFlag() {
		return isComboFlag;
	}
	/**
	 * 属性套餐配置显示配置的setter方法
	 */
	public void setIsComboFlag(String isComboFlag) {
		this.isComboFlag = isComboFlag;
	}
	
	/**
	 * 属性标志的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标志的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "ABBREVIATION")
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geKind")
	public List<GeKindRelate> getGeKindRelateList() {
		return geKindRelateList;
	}
	
	public void setGeKindRelateList(List<GeKindRelate> geKindRelateList) {
		this.geKindRelateList = geKindRelateList;
	}
}
