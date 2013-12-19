package cn.com.sinosoft.ebusiness.service.user.common.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeCustomerFavoriteId
 */
@Embeddable
public class GeCustomerFavoriteId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �����û��� */
	private String userID;

	/** ���Ե��������ƷID */
	private String eid;

	/**
	 * ��GeCustomerFavoriteId��Ĭ�Ϲ��췽��
	 */
	public GeCustomerFavoriteId() {
	}

	/**
	 * �����û��ŵ�getter����
	 */

	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * �����û��ŵ�setter����
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * ���Ե��������ƷID��getter����
	 */

	@Column(name = "EID")
	public String getEid() {
		return this.eid;
	}

	/**
	 * ���Ե��������ƷID��setter����
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
