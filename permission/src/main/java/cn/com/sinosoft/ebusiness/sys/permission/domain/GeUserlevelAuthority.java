package cn.com.sinosoft.ebusiness.sys.permission.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * POJO类GeUserlevelAuthority
 */
@Entity
@Table(name = "GE_USERLEVEL_AUTHORITY")
public class GeUserlevelAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性id */
	private GeUserlevelAuthorityId id;

	/** 属性geUserAuthority */
	private GeUserAuthority geUserAuthority;

	/**
	 * 类GeUserlevelAuthority的默认构造方法
	 */
	public GeUserlevelAuthority() {
	}

	/**
	 * 属性id的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userlevel", column = @Column(name = "USERLEVEL")),
			@AttributeOverride(name = "authorityid", column = @Column(name = "AUTHORITYID")) })
	public GeUserlevelAuthorityId getId() {
		return this.id;
	}

	/**
	 * 属性id的setter方法
	 */
	public void setId(GeUserlevelAuthorityId id) {
		this.id = id;
	}

	/**
	 * 属性geUserAuthority的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHORITYID", nullable = false, insertable = false, updatable = false)
	public GeUserAuthority getGeUserAuthority() {
		return this.geUserAuthority;
	}

	/**
	 * 属性geUserAuthority的setter方法
	 */
	public void setGeUserAuthority(GeUserAuthority geUserAuthority) {
		this.geUserAuthority = geUserAuthority;
	}

}
