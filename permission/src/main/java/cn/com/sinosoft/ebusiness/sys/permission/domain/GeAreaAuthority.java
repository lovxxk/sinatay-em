package cn.com.sinosoft.ebusiness.sys.permission.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO��GeArea
 */
@Entity
@Table(name = "GE_AREA")
public class GeAreaAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Բ�Ʒ���ID(��������ID) */
	private String gid;

	/** ����gname */
	private String gname;

	/** ����pgid */
	private String pgid;

	/** ����TYPE */
	private String type;

	/** ����glevel */
	private String glevel;

	/** ����note */
	private String note;

	/**
	 * ��GeArea��Ĭ�Ϲ��췽��
	 */
	public GeAreaAuthority() {
	}

	/**
	 * ���Բ�Ʒ���ID(��������ID)��getter����
	 */
	@Id
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
	 * ����pgid��getter����
	 */

	@Column(name = "PGID")
	public String getPgid() {
		return this.pgid;
	}

	/**
	 * ����pgid��setter����
	 */
	public void setPgid(String pgid) {
		this.pgid = pgid;
	}

	/**
	 * ����TYPE��getter����
	 */

	@Column(name = "TYPE")
	public String getType() {
		return this.type;
	}

	/**
	 * ����TYPE��setter����
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * ����glevel��getter����
	 */

	@Column(name = "GLEVEL")
	public String getGlevel() {
		return this.glevel;
	}

	/**
	 * ����glevel��setter����
	 */
	public void setGlevel(String glevel) {
		this.glevel = glevel;
	}

	/**
	 * ����note��getter����
	 */

	@Column(name = "NOTE")
	public String getNote() {
		return this.note;
	}

	/**
	 * ����note��setter����
	 */
	public void setNote(String note) {
		this.note = note;
	}

}
