package cn.com.sinosoft.ebusiness.sys.permission.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类GeUserlevelAuthorityId
 */
@Embeddable
public class GeUserlevelAuthorityId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性userlevel */
	private String userlevel;

	/** 属性authorityid */
	private String authorityid;

	/**
	 * 类GeUserlevelAuthorityId的默认构造方法
	 */
	public GeUserlevelAuthorityId() {
	}

	/**
	 * 属性userlevel的getter方法
	 */

	@Column(name = "USERLEVEL")
	public String getUserlevel() {
		return this.userlevel;
	}

	/**
	 * 属性userlevel的setter方法
	 */
	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	/**
	 * 属性authorityid的getter方法
	 */

	@Column(name = "AUTHORITYID")
	public String getAuthorityid() {
		return this.authorityid;
	}

	/**
	 * 属性authorityid的setter方法
	 */
	public void setAuthorityid(String authorityid) {
		this.authorityid = authorityid;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeUserlevelAuthorityId)) {
			return false;
		}
		GeUserlevelAuthorityId castOther = (GeUserlevelAuthorityId) other;

		return ((this.getUserlevel() == castOther.getUserlevel()) || (this
				.getUserlevel() != null && castOther.getUserlevel() != null && this
				.getUserlevel().equals(castOther.getUserlevel())))
				&& ((this.getAuthorityid() == castOther.getAuthorityid()) || (this
						.getAuthorityid() != null
						&& castOther.getAuthorityid() != null && this
						.getAuthorityid().equals(castOther.getAuthorityid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserlevel() == null ? 0 : this.getUserlevel().hashCode());
		result = 37
				* result
				+ (getAuthorityid() == null ? 0 : this.getAuthorityid()
						.hashCode());
		return result;
	}

}
