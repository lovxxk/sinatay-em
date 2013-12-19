package cn.com.sinosoft.ebusiness.sys.permission.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeGrouptype
 */
@Entity
@Table(name = "GE_GROUPTYPE")
public class GeGrouptype implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性grouptypeid */
	private String grouptypeid;

	/** 属性grouptypename */
	private String grouptypename;
	
	/** 属性groupdeptid */
	private String grouptypedeptid;
	
	/**属性grouptypedesc*/
	private String grouptypedesc;

	/**
	 * 类GeGrouptype的默认构造方法
	 */
	public GeGrouptype() {
	}

	/**
	 * 属性grouptypeid的getter方法
	 */
	@Id
	@Column(name = "GROUPTYPEID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getGrouptypeid() {
		return this.grouptypeid;
	}

	/**
	 * 属性grouptypeid的setter方法
	 */
	public void setGrouptypeid(String grouptypeid) {
		this.grouptypeid = grouptypeid;
	}

	/**
	 * 属性grouptypename的getter方法
	 */

	@Column(name = "GROUPTYPENAME")
	public String getGrouptypename() {
		return this.grouptypename;
	}

	/**
	 * 属性grouptypename的setter方法
	 */
	public void setGrouptypename(String grouptypename) {
		this.grouptypename = grouptypename;
	}
	
	/**
	 * 属性grouptypedeptid的getter方法
	 */

	@Column(name = "GROUPTYPEDEPTID")
	public String getGrouptypedeptid() {
		return grouptypedeptid;
	}
	
	/**
	 * 属性grouptypedeptid的setter方法
	 */
	public void setGrouptypedeptid(String grouptypedeptid) {
		this.grouptypedeptid = grouptypedeptid;
	}
	
	/**
	 * 属性grouptypedesc的getter方法
	 */
	@Column(name = "GROUPTYPEDESC")
	public String getGrouptypedesc() {
		return grouptypedesc;
	}

	/**
	 * 属性grouptypedesc的setter方法
	 */
	public void setGrouptypedesc(String grouptypedesc) {
		this.grouptypedesc = grouptypedesc;
	}
}
