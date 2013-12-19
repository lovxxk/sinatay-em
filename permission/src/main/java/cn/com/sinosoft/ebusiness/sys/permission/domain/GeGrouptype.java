package cn.com.sinosoft.ebusiness.sys.permission.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeGrouptype
 */
@Entity
@Table(name = "GE_GROUPTYPE")
public class GeGrouptype implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����grouptypeid */
	private String grouptypeid;

	/** ����grouptypename */
	private String grouptypename;
	
	/** ����groupdeptid */
	private String grouptypedeptid;
	
	/**����grouptypedesc*/
	private String grouptypedesc;

	/**
	 * ��GeGrouptype��Ĭ�Ϲ��췽��
	 */
	public GeGrouptype() {
	}

	/**
	 * ����grouptypeid��getter����
	 */
	@Id
	@Column(name = "GROUPTYPEID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getGrouptypeid() {
		return this.grouptypeid;
	}

	/**
	 * ����grouptypeid��setter����
	 */
	public void setGrouptypeid(String grouptypeid) {
		this.grouptypeid = grouptypeid;
	}

	/**
	 * ����grouptypename��getter����
	 */

	@Column(name = "GROUPTYPENAME")
	public String getGrouptypename() {
		return this.grouptypename;
	}

	/**
	 * ����grouptypename��setter����
	 */
	public void setGrouptypename(String grouptypename) {
		this.grouptypename = grouptypename;
	}
	
	/**
	 * ����grouptypedeptid��getter����
	 */

	@Column(name = "GROUPTYPEDEPTID")
	public String getGrouptypedeptid() {
		return grouptypedeptid;
	}
	
	/**
	 * ����grouptypedeptid��setter����
	 */
	public void setGrouptypedeptid(String grouptypedeptid) {
		this.grouptypedeptid = grouptypedeptid;
	}
	
	/**
	 * ����grouptypedesc��getter����
	 */
	@Column(name = "GROUPTYPEDESC")
	public String getGrouptypedesc() {
		return grouptypedesc;
	}

	/**
	 * ����grouptypedesc��setter����
	 */
	public void setGrouptypedesc(String grouptypedesc) {
		this.grouptypedesc = grouptypedesc;
	}
}
