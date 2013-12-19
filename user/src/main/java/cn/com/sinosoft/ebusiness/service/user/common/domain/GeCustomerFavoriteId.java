package cn.com.sinosoft.ebusiness.service.user.common.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类GeCustomerFavoriteId
 */
@Embeddable
public class GeCustomerFavoriteId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性用户号 */
	private String userID;

	/** 属性电子商务产品ID */
	private String eid;

	/**
	 * 类GeCustomerFavoriteId的默认构造方法
	 */
	public GeCustomerFavoriteId() {
	}

	/**
	 * 属性用户号的getter方法
	 */

	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * 属性用户号的setter方法
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * 属性电子商务产品ID的getter方法
	 */

	@Column(name = "EID")
	public String getEid() {
		return this.eid;
	}

	/**
	 * 属性电子商务产品ID的setter方法
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeCustomerFavoriteId)) {
			return false;
		}
		GeCustomerFavoriteId castOther = (GeCustomerFavoriteId) other;

		return ((this.getUserID() == castOther.getUserID()) || (this
				.getUserID() != null && castOther.getUserID() != null && this
				.getUserID().equals(castOther.getUserID())))
				&& ((this.getEid() == castOther.getEid()) || (this.getEid() != null
						&& castOther.getEid() != null && this.getEid().equals(
						castOther.getEid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserID() == null ? 0 : this.getUserID().hashCode());
		result = 37 * result
				+ (getEid() == null ? 0 : this.getEid().hashCode());
		return result;
	}

}
