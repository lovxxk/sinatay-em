// default package
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�
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
 * POJO��GeDepartment
 */
@Entity
@Table(name = "GE_DEPARTMENT")
public class GeDepartment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ի������ */
	private String deptid;

	/** ���Ի������� */
	private String deptname;

	/** ���Ի����㼶 */
	private String deptlevel;

	/** ���Ի������� */
	private String depttype;

	/** ����ҵ������ */
	private String businessarea;

	/** ���Ը�Ȩ�ޱ�� */
	private String parentid;

	/** ������ϵ��ַ */
	private String contactAddress;

	/** ���԰칫�绰 */
	private String officePhone;

	/** ������������ */
	private String zipCode;

	/** ���Բ�Ʒ���ID(��������ID) */
	private String gid;

	/** ����gname */
	private String gname;
	
	private List<GeDeptAttribute> geDeptAttributes = new ArrayList<GeDeptAttribute>(
			0);

	/**
	 * ��GeDepartment��Ĭ�Ϲ��췽��
	 */
	public GeDepartment() {
	}

	/**
	 * ���Ի�����ŵ�getter����
	 */
	@Id
	@Column(name = "DEPTID")
	public String getDeptid() {
		return this.deptid;
	}

	/**
	 * ���Ի�����ŵ�setter����
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	/**
	 * ���Ի������Ƶ�getter����
	 */

	@Column(name = "DEPTNAME")
	public String getDeptname() {
		return this.deptname;
	}

	/**
	 * ���Ի������Ƶ�setter����
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/**
	 * ���Ի����㼶��getter����
	 */

	@Column(name = "DEPTLEVEL")
	public String getDeptlevel() {
		return this.deptlevel;
	}

	/**
	 * ���Ի����㼶��setter����
	 */
	public void setDeptlevel(String deptlevel) {
		this.deptlevel = deptlevel;
	}

	/**
	 * ���Ի������͵�getter����
	 */

	@Column(name = "DEPTTYPE")
	public String getDepttype() {
		return this.depttype;
	}

	/**
	 * ���Ի������͵�setter����
	 */
	public void setDepttype(String depttype) {
		this.depttype = depttype;
	}

	/**
	 * ����ҵ�������getter����
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessarea() {
		return this.businessarea;
	}

	/**
	 * ����ҵ�������setter����
	 */
	public void setBusinessarea(String businessarea) {
		this.businessarea = businessarea;
	}

	/**
	 * ���Ը�Ȩ�ޱ�ŵ�getter����
	 */

	@Column(name = "PARENTID")
	public String getParentid() {
		return this.parentid;
	}

	/**
	 * ���Ը�Ȩ�ޱ�ŵ�setter����
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	/**
	 * ������ϵ��ַ��getter����
	 */

	@Column(name = "CONTACTADDRESS")
	public String getContactAddress() {
		return this.contactAddress;
	}

	/**
	 * ������ϵ��ַ��setter����
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * ���԰칫�绰��getter����
	 */

	@Column(name = "OFFICEPHONE")
	public String getOfficePhone() {
		return this.officePhone;
	}

	/**
	 * ���԰칫�绰��setter����
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	/**
	 * �������������getter����
	 */

	@Column(name = "ZIPCODE")
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * �������������setter����
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * ���Բ�Ʒ���ID(��������ID)��getter����
	 */

	@Column(name = "GID")
	public String getGid() {
		return this.gid;
	}

	/**
	 * ���Բ�Ʒ���ID(��������ID)��setter����
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * ����gname��getter����
	 */

	@Column(name = "GNAME")
	public String getGname() {
		return this.gname;
	}

	/**
	 * ����gname��setter����
	 */
	public void setGname(String gname) {
		this.gname = gname;
	}
	/**
	 * ����geDeptAttributes��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDepartment")
	public List<GeDeptAttribute> getGeDeptAttributes() {
		return this.geDeptAttributes;
	}

	/**
	 * ����geDeptAttributes��setter����
	 */
	public void setGeDeptAttributes(List<GeDeptAttribute> geDeptAttributes) {
		this.geDeptAttributes = geDeptAttributes;
	}
}
