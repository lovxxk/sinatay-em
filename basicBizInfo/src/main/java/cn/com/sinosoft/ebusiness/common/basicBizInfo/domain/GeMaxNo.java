package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * POJO类GeMaxNo
 */
@Entity
@Table(name = "GE_MAXNO")
public class GeMaxNo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIRST = "1";
	public static final String SECOND="2";

	public static final String FLAG_YES = "1";
	public static final String FLAG_NO = "0";


	/** 属性会员ID 对应登陆会员的ID */
	private GeMaxNoId id;

	/** 属性是否有效标志 */
	private String validInd;

	/** 属性备注 */
	private String remark;

	/** 属性标志位 */
	private String flag;

	/**
	 * 类GeMaxNo的默认构造方法
	 */
	public GeMaxNo() {
	}

	/**
	 * 属性会员ID 对应登陆会员的ID的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "groupNo", column = @Column(name = "GROUPNO")),
			@AttributeOverride(name = "maxNo", column = @Column(name = "MAXNO")) })
	public GeMaxNoId getId() {
		return this.id;
	}

	/**
	 * 属性会员ID 对应登陆会员的ID的setter方法
	 */
	public void setId(GeMaxNoId id) {
		this.id = id;
	}

	/**
	 * 属性是否有效标志的getter方法
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * 属性是否有效标志的setter方法
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	/**
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 属性标志位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标志位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
