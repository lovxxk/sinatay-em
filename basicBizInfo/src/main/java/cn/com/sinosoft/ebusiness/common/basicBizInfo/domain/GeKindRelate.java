package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * POJO��GeKindRelate
 */
@Entity
@Table(name = "GE_KIND_RELATE")
public class GeKindRelate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����ID */
	private GeKindRelateId id;

	/** ����geKind */
	private GeKind geKind;

	/** ���Ա�־ */
	private String flag;
	/** ҵ������ */
	private String businessArea;
	
	@Transient
    private String kindRelateCName;
	/**
	 * ��GeKindRelate��Ĭ�Ϲ��췽��
	 */
	public GeKindRelate() {
	}
	
	@Transient
	public String getKindRelateCName() {
		return kindRelateCName;
	}

	public void setKindRelateCName(String kindRelateCName) {
		this.kindRelateCName = kindRelateCName;
	}

	/**
	 * ����ID��getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "riskCode", column = @Column(name = "RISKCODE")),
			@AttributeOverride(name = "kindCode", column = @Column(name = "KINDCODE")),
			@AttributeOverride(name = "relateKindCode", column = @Column(name = "RELATEKINDCODE")) })
	public GeKindRelateId getId() {
		return this.id;
	}

	/**
	 * ����ID��setter����
	 */
	public void setId(GeKindRelateId id) {
		this.id = id;
	}

	/**
	 * ����geKind��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "RISKCODE", referencedColumnName = "RISKCODE", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "KINDCODE", referencedColumnName = "KINDCODE", nullable = false, insertable = false, updatable = false) })
	public GeKind getGeKind() {
		return this.geKind;
	}

	/**
	 * ����geKind��setter����
	 */
	public void setGeKind(GeKind geKind) {
		this.geKind = geKind;
	}

	/**
	 * ���Ա�־��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�־��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}


}
