package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * POJO��GeEnterpriselevelAuthority
 */
@Entity
@Table(name = "GE_ENTERPRISELEVEL_AUTHORITY")
public class GeEnterpriselevelAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ժ�������� */
	private GeEnterpriselevelAuthorityId id;

	/**
	 * ��GeEnterpriselevelAuthority��Ĭ�Ϲ��췽��
	 */
	public GeEnterpriselevelAuthority() {
	}

	/**
	 * ���Ժ�������ŵ�getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userLevel", column = @Column(name = "USERLEVEL")),
			@AttributeOverride(name = "authorityID", column = @Column(name = "AUTHORITYID")) })
	public GeEnterpriselevelAuthorityId getId() {
		return this.id;
	}

	/**
	 * ���Ժ�������ŵ�setter����
	 */
	public void setId(GeEnterpriselevelAuthorityId id) {
		this.id = id;
	}

}
