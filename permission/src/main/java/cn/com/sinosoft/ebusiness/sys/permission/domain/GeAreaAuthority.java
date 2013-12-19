package cn.com.sinosoft.ebusiness.sys.permission.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO类GeArea
 */
@Entity
@Table(name = "GE_AREA")
public class GeAreaAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性产品组合ID(电子商务ID) */
	private String gid;

	/** 属性gname */
	private String gname;

	/** 属性pgid */
	private String pgid;

	/** 属性TYPE */
	private String type;

	/** 属性glevel */
	private String glevel;

	/** 属性note */
	private String note;

	/**
	 * 类GeArea的默认构造方法
	 */
	public GeAreaAuthority() {
	}

	/**
	 * 属性产品组合ID(电子商务ID)的getter方法
	 */
	@Id
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
	 * 属性pgid的getter方法
	 */

	@Column(name = "PGID")
	public String getPgid() {
		return this.pgid;
	}

	/**
	 * 属性pgid的setter方法
	 */
	public void setPgid(String pgid) {
		this.pgid = pgid;
	}

	/**
	 * 属性TYPE的getter方法
	 */

	@Column(name = "TYPE")
	public String getType() {
		return this.type;
	}

	/**
	 * 属性TYPE的setter方法
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 属性glevel的getter方法
	 */

	@Column(name = "GLEVEL")
	public String getGlevel() {
		return this.glevel;
	}

	/**
	 * 属性glevel的setter方法
	 */
	public void setGlevel(String glevel) {
		this.glevel = glevel;
	}

	/**
	 * 属性note的getter方法
	 */

	@Column(name = "NOTE")
	public String getNote() {
		return this.note;
	}

	/**
	 * 属性note的setter方法
	 */
	public void setNote(String note) {
		this.note = note;
	}

}
