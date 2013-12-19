package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * POJO类GeEnterpriselevelAuthority
 */
@Entity
@Table(name = "GE_ENTERPRISELEVEL_AUTHORITY")
public class GeEnterpriselevelAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性黑名单编号 */
	private GeEnterpriselevelAuthorityId id;

	/**
	 * 类GeEnterpriselevelAuthority的默认构造方法
	 */
	public GeEnterpriselevelAuthority() {
	}

	/**
	 * 属性黑名单编号的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userLevel", column = @Column(name = "USERLEVEL")),
			@AttributeOverride(name = "authorityID", column = @Column(name = "AUTHORITYID")) })
	public GeEnterpriselevelAuthorityId getId() {
		return this.id;
	}

	/**
	 * 属性黑名单编号的setter方法
	 */
	public void setId(GeEnterpriselevelAuthorityId id) {
		this.id = id;
	}

}
