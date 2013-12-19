package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类GeEnterpriselevelAuthorityId
 */
@Embeddable
public class GeEnterpriselevelAuthorityId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性用户等级 */
	private String userLevel;

	/** 属性权限编号 */
	private String authorityID;

	/**
	 * 类GeEnterpriselevelAuthorityId的默认构造方法
	 */
	public GeEnterpriselevelAuthorityId() {
	}

	/**
	 * 属性用户等级的getter方法
	 */

	@Column(name = "USERLEVEL")
	public String getUserLevel() {
		return this.userLevel;
	}

	/**
	 * 属性用户等级的setter方法
	 */
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * 属性权限编号的getter方法
	 */

	@Column(name = "AUTHORITYID")
	public String getAuthorityID() {
		return this.authorityID;
	}

	/**
	 * 属性权限编号的setter方法
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
