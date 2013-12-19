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
 * POJO��GeDeptAttribute
 */
@Entity
@Table(name = "GE_DEPT_ATTRIBUTE")
public class GeDeptAttribute implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����deptattrid */
	private String deptattrid;

	/** ���Ի�����-GE_Department */
	private GeDepartment geDepartment;

	/** ���Ի���������Ϣ��-GE_Dept_Info */
	private GeDeptInfo geDeptInfo;

	/** ��������ֵ */
	private String attrValue;

	/**
	 * ��GeDeptAttribute��Ĭ�Ϲ��췽��
	 */
	public GeDeptAttribute() {
	}

	/**
	 * ����deptattrid��getter����
	 */
	@Id
	@Column(name = "DEPTATTRID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getDeptattrid() {
		return this.deptattrid;
	}

	/**
	 * ����deptattrid��setter����
	 */
	public void setDeptattrid(String deptattrid) {
		this.deptattrid = deptattrid;
	}

	/**
	 * ���Ի�����-GE_Department��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTID", nullable = false)
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
	 * ���Ի���������Ϣ��-GE_Dept_Info��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATTRID", nullable = false)
	public GeDeptInfo getGeDeptInfo() {
		return this.geDeptInfo;
	}

	/**
	 * ���Ի���������Ϣ��-GE_Dept_Info��setter����
	 */
	public void setGeDeptInfo(GeDeptInfo geDeptInfo) {
		this.geDeptInfo = geDeptInfo;
	}

	/**
	 * ��������ֵ��getter����
	 */

	@Column(name = "ATTRVALUE")
	public String getAttrValue() {
		return this.attrValue;
	}

	/**
	 * ��������ֵ��setter����
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

}
