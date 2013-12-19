package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类GeMaxNoId
 */
@Embeddable
public class GeMaxNoId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性组号 */
	private String groupNo;

	/** 属性最大号 */
	private String maxNo;

	/**
	 * 类GeMaxNoId的默认构造方法
	 */
	public GeMaxNoId() {
	}

	/**
	 * 属性组号的getter方法
	 */

	@Column(name = "GROUPNO")
	public String getGroupNo() {
		return this.groupNo;
	}

	/**
	 * 属性组号的setter方法
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	/**
	 * 属性最大号的getter方法
	 */

	@Column(name = "MAXNO")
	public String getMaxNo() {
		return this.maxNo;
	}

	/**
	 * 属性最大号的setter方法
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
