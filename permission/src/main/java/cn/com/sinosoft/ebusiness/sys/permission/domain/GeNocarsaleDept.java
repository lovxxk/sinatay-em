package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * POJO类GeNocarsaleDept
 */
@Entity
@Table(name = "GE_NOCARSALE_DEPT")
public class GeNocarsaleDept implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性机构编号 */
	private String deptID;

	/** 属性机构表-GE_Department */
	private GeDepartment geDepartment;

	/** 属性orgid */
	private String orgid;

	/**
	 * 类GeNocarsaleDept的默认构造方法
	 */
	public GeNocarsaleDept() {
	}

	/**
	 * 属性机构编号的getter方法
	 */
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "geDepartment"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}

	/**
	 * 属性机构编号的setter方法
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * 属性机构表-GE_Department的getter方法
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
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
	 * 属性orgid的getter方法
	 */

	@Column(name = "ORGID")
	public String getOrgid() {
		return this.orgid;
	}

	/**
	 * 属性orgid的setter方法
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}
