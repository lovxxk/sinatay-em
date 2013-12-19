// default package
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。
package cn.com.sinosoft.ebusiness.sys.permission.domain;


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
 * POJO类GeDepartment
 */
@Entity
@Table(name = "GE_DEPARTMENT")
public class GeDepartment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性机构编号 */
	private String deptid;

	/** 属性机构名称 */
	private String deptname;

	/** 属性机构层级 */
	private String deptlevel;

	/** 属性机构类型 */
	private String depttype;

	/** 属性业务领域 */
	private String businessarea;

	/** 属性父权限编号 */
	private String parentid;

	/** 属性联系地址 */
	private String contactAddress;

	/** 属性办公电话 */
	private String officePhone;

	/** 属性邮政编码 */
	private String zipCode;

	/** 属性产品组合ID(电子商务ID) */
	private String gid;

	/** 属性gname */
	private String gname;
	
	private List<GeDeptAttribute> geDeptAttributes = new ArrayList<GeDeptAttribute>(
			0);

	/**
	 * 类GeDepartment的默认构造方法
	 */
	public GeDepartment() {
	}

	/**
	 * 属性机构编号的getter方法
	 */
	@Id
	@Column(name = "DEPTID")
	public String getDeptid() {
		return this.deptid;
	}

	/**
	 * 属性机构编号的setter方法
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	/**
	 * 属性机构名称的getter方法
	 */

	@Column(name = "DEPTNAME")
	public String getDeptname() {
		return this.deptname;
	}

	/**
	 * 属性机构名称的setter方法
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/**
	 * 属性机构层级的getter方法
	 */

	@Column(name = "DEPTLEVEL")
	public String getDeptlevel() {
		return this.deptlevel;
	}

	/**
	 * 属性机构层级的setter方法
	 */
	public void setDeptlevel(String deptlevel) {
		this.deptlevel = deptlevel;
	}

	/**
	 * 属性机构类型的getter方法
	 */

	@Column(name = "DEPTTYPE")
	public String getDepttype() {
		return this.depttype;
	}

	/**
	 * 属性机构类型的setter方法
	 */
	public void setDepttype(String depttype) {
		this.depttype = depttype;
	}

	/**
	 * 属性业务领域的getter方法
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessarea() {
		return this.businessarea;
	}

	/**
	 * 属性业务领域的setter方法
	 */
	public void setBusinessarea(String businessarea) {
		this.businessarea = businessarea;
	}

	/**
	 * 属性父权限编号的getter方法
	 */

	@Column(name = "PARENTID")
	public String getParentid() {
		return this.parentid;
	}

	/**
	 * 属性父权限编号的setter方法
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	/**
	 * 属性联系地址的getter方法
	 */

	@Column(name = "CONTACTADDRESS")
	public String getContactAddress() {
		return this.contactAddress;
	}

	/**
	 * 属性联系地址的setter方法
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * 属性办公电话的getter方法
	 */

	@Column(name = "OFFICEPHONE")
	public String getOfficePhone() {
		return this.officePhone;
	}

	/**
	 * 属性办公电话的setter方法
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	/**
	 * 属性邮政编码的getter方法
	 */

	@Column(name = "ZIPCODE")
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * 属性邮政编码的setter方法
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 属性产品组合ID(电子商务ID)的getter方法
	 */

	@Column(name = "GID")
	public String getGid() {
		return this.gid;
	}

	/**
	 * 属性产品组合ID(电子商务ID)的setter方法
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * 属性gname的getter方法
	 */

	@Column(name = "GNAME")
	public String getGname() {
		return this.gname;
	}

	/**
	 * 属性gname的setter方法
	 */
	public void setGname(String gname) {
		this.gname = gname;
	}
	/**
	 * 属性geDeptAttributes的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDepartment")
	public List<GeDeptAttribute> getGeDeptAttributes() {
		return this.geDeptAttributes;
	}

	/**
	 * 属性geDeptAttributes的setter方法
	 */
	public void setGeDeptAttributes(List<GeDeptAttribute> geDeptAttributes) {
		this.geDeptAttributes = geDeptAttributes;
	}
}
