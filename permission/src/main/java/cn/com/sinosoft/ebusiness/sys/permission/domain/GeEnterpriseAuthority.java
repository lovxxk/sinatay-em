package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO��GeEnterpriseAuthority
 */
@Entity
@Table(name = "GE_ENTERPRISE_AUTHORITY")
public class GeEnterpriseAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����Ȩ�ޱ�� */
	private String authorityID;

	/** ����Ȩ������ */
	private String authorityName;

	/** ����Ȩ������ */
	private String authorityDesp;

	/** ����Ȩ�����ӵ�ַ */
	private String authorityLink;

	/** ����Ȩ�޲㼶 */
	private String authorityLevel;

	/** ���Ը�Ȩ�ޱ�� */
	private String parentID;

	/** ����authorityorder */
	private String authorityorder;

	/** ����Ȩ������ */
	private String authorityType;

	/**
	 * ��GeEnterpriseAuthority��Ĭ�Ϲ��췽��
	 */
	public GeEnterpriseAuthority() {
	}

	/**
	 * ����Ȩ�ޱ�ŵ�getter����
	 */
	@Id
	@Column(name = "AUTHORITYID")
	public String getAuthorityID() {
		return this.authorityID;
	}

	/**
	 * ����Ȩ�ޱ�ŵ�setter����
	 */
	public void setAuthorityID(String authorityID) {
		this.authorityID = authorityID;
	}

	/**
	 * ����Ȩ�����Ƶ�getter����
	 */

	@Column(name = "AUTHORITYNAME")
	public String getAuthorityName() {
		return this.authorityName;
	}

	/**
	 * ����Ȩ�����Ƶ�setter����
	 */
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	/**
	 * ����Ȩ��������getter����
	 */

	@Column(name = "AUTHORITYDESP")
	public String getAuthorityDesp() {
		return this.authorityDesp;
	}

	/**
	 * ����Ȩ��������setter����
	 */
	public void setAuthorityDesp(String authorityDesp) {
		this.authorityDesp = authorityDesp;
	}

	/**
	 * ����Ȩ�����ӵ�ַ��getter����
	 */

	@Column(name = "AUTHORITYLINK")
	public String getAuthorityLink() {
		return this.authorityLink;
	}

	/**
	 * ����Ȩ�����ӵ�ַ��setter����
	 */
	public void setAuthorityLink(String authorityLink) {
		this.authorityLink = authorityLink;
	}

	/**
	 * ����Ȩ�޲㼶��getter����
	 */

	@Column(name = "AUTHORITYLEVEL")
	public String getAuthorityLevel() {
		return this.authorityLevel;
	}

	/**
	 * ����Ȩ�޲㼶��setter����
	 */
	public void setAuthorityLevel(String authorityLevel) {
		this.authorityLevel = authorityLevel;
	}

	/**
	 * ���Ը�Ȩ�ޱ�ŵ�getter����
	 */

	@Column(name = "PARENTID")
	public String getParentID() {
		return this.parentID;
	}

	/**
	 * ���Ը�Ȩ�ޱ�ŵ�setter����
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	/**
	 * ����authorityorder��getter����
	 */

	@Column(name = "AUTHORITYORDER")
	public String getAuthorityorder() {
		return this.authorityorder;
	}

	/**
	 * ����authorityorder��setter����
	 */
	public void setAuthorityorder(String authorityorder) {
		this.authorityorder = authorityorder;
	}

	/**
	 * ����Ȩ�����͵�getter����
	 */

	@Column(name = "AUTHORITYTYPE")
	public String getAuthorityType() {
		return this.authorityType;
	}

	/**
	 * ����Ȩ�����͵�setter����
	 */
	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

}
