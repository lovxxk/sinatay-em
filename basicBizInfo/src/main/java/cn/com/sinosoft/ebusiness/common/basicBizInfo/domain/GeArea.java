package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO类GeArea
 */
@Entity
@Table(name = "GE_AREA")
public class GeArea implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性所在地区代码 */
	private String areaCode;

	/** 属性areaname */
	private String areaname;

	/** 属性upperareacode */
	private String upperareacode;

	/** 属性名称缩写 */
	private String shortName;

	/** 属性arealevel */
	private String arealevel;

	/** 属性标志位 */
	private String flag;

	/**
	 * 类GeArea的默认构造方法
	 */
	public GeArea() {
	}

	/**
	 * 属性所在地区代码的getter方法
	 */
	@Id
	@Column(name = "AREACODE")
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * 属性所在地区代码的setter方法
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 属性areaname的getter方法
	 */

	@Column(name = "AREANAME")
	public String getAreaname() {
		return this.areaname;
	}

	/**
	 * 属性areaname的setter方法
	 */
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	/**
	 * 属性upperareacode的getter方法
	 */

	@Column(name = "UPPERAREACODE")
	public String getUpperareacode() {
		return this.upperareacode;
	}

	/**
	 * 属性upperareacode的setter方法
	 */
	public void setUpperareacode(String upperareacode) {
		this.upperareacode = upperareacode;
	}

	/**
	 * 属性名称缩写的getter方法
	 */

	@Column(name = "SHORTNAME")
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * 属性名称缩写的setter方法
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 属性arealevel的getter方法
	 */

	@Column(name = "AREALEVEL")
	public String getArealevel() {
		return this.arealevel;
	}

	/**
	 * 属性arealevel的setter方法
	 */
	public void setArealevel(String arealevel) {
		this.arealevel = arealevel;
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
	 * 代表省份含义
	 */
	static public final String AREA_LEVEL_PROVINCE = "1";
	/**
	 * 代表市含义
	 */
	static public final String AREA_LEVEL_CITY = "2";
}
