package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO类GeOccupation
 */
@Entity
@Table(name = "GE_OCCUPATION")
public class GeOccupation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性职业代码 */
	private String occupationCode;

	/** 属性职业类别名称 */
	private String occupationTypeName;

	/** 属性父职业代码 */
	private String parentOccupationCode;

	/** 属性职业等级 */
	private String occupationLevel;

	/** 属性备注 */
	private String remark;

	/**
	 * 类GeOccupation的默认构造方法
	 */
	public GeOccupation() {
	}

	/**
	 * 属性职业代码的getter方法
	 */
	@Id
	@Column(name = "OCCUPATIONCODE")
	public String getOccupationCode() {
		return this.occupationCode;
	}

	/**
	 * 属性职业代码的setter方法
	 */
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	/**
	 * 属性职业类别名称的getter方法
	 */

	@Column(name = "OCCUPATIONTYPENAME")
	public String getOccupationTypeName() {
		return this.occupationTypeName;
	}

	/**
	 * 属性职业类别名称的setter方法
	 */
	public void setOccupationTypeName(String occupationTypeName) {
		this.occupationTypeName = occupationTypeName;
	}

	/**
	 * 属性父职业代码的getter方法
	 */

	@Column(name = "PARENTOCCUPATIONCODE")
	public String getParentOccupationCode() {
		return this.parentOccupationCode;
	}

	/**
	 * 属性父职业代码的setter方法
	 */
	public void setParentOccupationCode(String parentOccupationCode) {
		this.parentOccupationCode = parentOccupationCode;
	}

	/**
	 * 属性职业等级的getter方法
	 */

	@Column(name = "OCCUPATIONLEVEL")
	public String getOccupationLevel() {
		return this.occupationLevel;
	}

	/**
	 * 属性职业等级的setter方法
	 */
	public void setOccupationLevel(String occupationLevel) {
		this.occupationLevel = occupationLevel;
	}

	/**
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
