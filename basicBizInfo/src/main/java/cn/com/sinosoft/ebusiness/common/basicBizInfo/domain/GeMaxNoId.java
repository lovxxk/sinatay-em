package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeMaxNoId
 */
@Embeddable
public class GeMaxNoId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String groupNo;

	/** �������� */
	private String maxNo;

	/**
	 * ��GeMaxNoId��Ĭ�Ϲ��췽��
	 */
	public GeMaxNoId() {
	}

	/**
	 * ������ŵ�getter����
	 */

	@Column(name = "GROUPNO")
	public String getGroupNo() {
		return this.groupNo;
	}

	/**
	 * ������ŵ�setter����
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	/**
	 * �������ŵ�getter����
	 */

	@Column(name = "MAXNO")
	public String getMaxNo() {
		return this.maxNo;
	}

	/**
	 * �������ŵ�setter����
	 */
	public void setMaxNo(String maxNo) {
		this.maxNo = maxNo;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeMaxNoId)) {
			return false;
		}
		GeMaxNoId castOther = (GeMaxNoId) other;

		return ((this.getGroupNo() == castOther.getGroupNo()) || (this
				.getGroupNo() != null && castOther.getGroupNo() != null && this
				.getGroupNo().equals(castOther.getGroupNo())))
				&& ((this.getMaxNo() == castOther.getMaxNo()) || (this
						.getMaxNo() != null && castOther.getMaxNo() != null && this
						.getMaxNo().equals(castOther.getMaxNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupNo() == null ? 0 : this.getGroupNo().hashCode());
		result = 37 * result
				+ (getMaxNo() == null ? 0 : this.getMaxNo().hashCode());
		return result;
	}

}
