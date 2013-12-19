package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeEnterpriselevelAuthorityId
 */
@Embeddable
public class GeEnterpriselevelAuthorityId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �����û��ȼ� */
	private String userLevel;

	/** ����Ȩ�ޱ�� */
	private String authorityID;

	/**
	 * ��GeEnterpriselevelAuthorityId��Ĭ�Ϲ��췽��
	 */
	public GeEnterpriselevelAuthorityId() {
	}

	/**
	 * �����û��ȼ���getter����
	 */

	@Column(name = "USERLEVEL")
	public String getUserLevel() {
		return this.userLevel;
	}

	/**
	 * �����û��ȼ���setter����
	 */
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * ����Ȩ�ޱ�ŵ�getter����
	 */

	@Column(name = "AUTHORITYID")
	public String getAuthorityID() {
		return this.authorityID;
	}

	/**
	 * ����Ȩ�ޱ�ŵ�setter����
	 */
	public void setAuthorityID(String authorityID) {
		this.authorityID = authorityID;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeEnterpriselevelAuthorityId)) {
			return false;
		}
		GeEnterpriselevelAuthorityId castOther = (GeEnterpriselevelAuthorityId) other;

		return ((this.getUserLevel() == castOther.getUserLevel()) || (this
				.getUserLevel() != null && castOther.getUserLevel() != null && this
				.getUserLevel().equals(castOther.getUserLevel())))
				&& ((this.getAuthorityID() == castOther.getAuthorityID()) || (this
						.getAuthorityID() != null
						&& castOther.getAuthorityID() != null && this
						.getAuthorityID().equals(castOther.getAuthorityID())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserLevel() == null ? 0 : this.getUserLevel().hashCode());
		result = 37
				* result
				+ (getAuthorityID() == null ? 0 : this.getAuthorityID()
						.hashCode());
		return result;
	}

}
