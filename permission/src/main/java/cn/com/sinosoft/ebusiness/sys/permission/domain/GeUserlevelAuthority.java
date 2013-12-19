package cn.com.sinosoft.ebusiness.sys.permission.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeUserlevelAuthority
 */
@Entity
@Table(name = "GE_USERLEVEL_AUTHORITY")
public class GeUserlevelAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����id */
	private GeUserlevelAuthorityId id;

	/** ����geUserAuthority */
	private GeUserAuthority geUserAuthority;

	/**
	 * ��GeUserlevelAuthority��Ĭ�Ϲ��췽��
	 */
	public GeUserlevelAuthority() {
	}

	/**
	 * ����id��getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userlevel", column = @Column(name = "USERLEVEL")),
			@AttributeOverride(name = "authorityid", column = @Column(name = "AUTHORITYID")) })
	public GeUserlevelAuthorityId getId() {
		return this.id;
	}

	/**
	 * ����id��setter����
	 */
	public void setId(GeUserlevelAuthorityId id) {
		this.id = id;
	}

	/**
	 * ����geUserAuthority��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHORITYID", nullable = false, insertable = false, updatable = false)
	public GeUserAuthority getGeUserAuthority() {
		return this.geUserAuthority;
	}

	/**
	 * ����geUserAuthority��setter����
	 */
	public void setGeUserAuthority(GeUserAuthority geUserAuthority) {
		this.geUserAuthority = geUserAuthority;
	}

}
