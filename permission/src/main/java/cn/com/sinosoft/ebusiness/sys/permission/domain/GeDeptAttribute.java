package cn.com.sinosoft.ebusiness.sys.permission.domain;

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
 * POJO类GeDeptAttribute
 */
@Entity
@Table(name = "GE_DEPT_ATTRIBUTE")
public class GeDeptAttribute implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性deptattrid */
	private String deptattrid;

	/** 属性机构表-GE_Department */
	private GeDepartment geDepartment;

	/** 属性机构属性信息表-GE_Dept_Info */
	private GeDeptInfo geDeptInfo;

	/** 属性属性值 */
	private String attrValue;

	/**
	 * 类GeDeptAttribute的默认构造方法
	 */
	public GeDeptAttribute() {
	}

	/**
	 * 属性deptattrid的getter方法
	 */
	@Id
	@Column(name = "DEPTATTRID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getDeptattrid() {
		return this.deptattrid;
	}

	/**
	 * 属性deptattrid的setter方法
	 */
	public void setDeptattrid(String deptattrid) {
		this.deptattrid = deptattrid;
	}

	/**
	 * 属性机构表-GE_Department的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTID", nullable = false)
	public GeDepartment getGeDepartment() {
		return this.geDepartment;
	}

	/**
	 * 属性机构表-GE_Department的setter方法
	 */
	public void setGeDepartment(GeDepartment geDepartment) {
		this.geDepartment = geDepartment;
	}

	/**
	 * 属性机构属性信息表-GE_Dept_Info的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATTRID", nullable = false)
	public GeDeptInfo getGeDeptInfo() {
		return this.geDeptInfo;
	}

	/**
	 * 属性机构属性信息表-GE_Dept_Info的setter方法
	 */
	public void setGeDeptInfo(GeDeptInfo geDeptInfo) {
		this.geDeptInfo = geDeptInfo;
	}

	/**
	 * 属性属性值的getter方法
	 */

	@Column(name = "ATTRVALUE")
	public String getAttrValue() {
		return this.attrValue;
	}

	/**
	 * 属性属性值的setter方法
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

}
