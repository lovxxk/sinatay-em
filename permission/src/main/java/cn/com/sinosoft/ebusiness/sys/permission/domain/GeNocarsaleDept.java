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
 * POJO��GeNocarsaleDept
 */
@Entity
@Table(name = "GE_NOCARSALE_DEPT")
public class GeNocarsaleDept implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ի������ */
	private String deptID;

	/** ���Ի�����-GE_Department */
	private GeDepartment geDepartment;

	/** ����orgid */
	private String orgid;

	/**
	 * ��GeNocarsaleDept��Ĭ�Ϲ��췽��
	 */
	public GeNocarsaleDept() {
	}

	/**
	 * ���Ի�����ŵ�getter����
	 */
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "geDepartment"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}

	/**
	 * ���Ի�����ŵ�setter����
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * ���Ի�����-GE_Department��getter����
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public GeDepartment getGeDepartment() {
		return this.geDepartment;
	}

	/**
	 * ���Ի�����-GE_Department��setter����
	 */
	public void setGeDepartment(GeDepartment geDepartment) {
		this.geDepartment = geDepartment;
	}

	/**
	 * ����orgid��getter����
	 */

	@Column(name = "ORGID")
	public String getOrgid() {
		return this.orgid;
	}

	/**
	 * ����orgid��setter����
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}
