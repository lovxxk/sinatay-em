package cn.com.sinosoft.ebusiness.sys.permission.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeUserlevelAuthorityId
 */
@Embeddable
public class GeUserlevelAuthorityId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����userlevel */
	private String userlevel;

	/** ����authorityid */
	private String authorityid;

	/**
	 * ��GeUserlevelAuthorityId��Ĭ�Ϲ��췽��
	 */
	public GeUserlevelAuthorityId() {
	}

	/**
	 * ����userlevel��getter����
	 */

	@Column(name = "USERLEVEL")
	public String getUserlevel() {
		return this.userlevel;
	}

	/**
	 * ����userlevel��setter����
	 */
	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	/**
	 * ����authorityid��getter����
	 */

	@Column(name = "AUTHORITYID")
	public String getAuthorityid() {
		return this.authorityid;
	}

	/**
	 * ����authorityid��setter����
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
