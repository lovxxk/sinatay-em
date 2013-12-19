package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类GeKindRelate
 */
@Entity
@Table(name = "GE_KIND_RELATE")
public class GeKindRelate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性ID */
	private GeKindRelateId id;

	/** 属性geKind */
	private GeKind geKind;

	/** 属性标志 */
	private String flag;
	/** 业务领域 */
	private String businessArea;
	
	@Transient
    private String kindRelateCName;
	/**
	 * 类GeKindRelate的默认构造方法
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
	 * 属性ID的getter方法
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
	 * 属性ID的setter方法
	 */
	public void setId(GeKindRelateId id) {
		this.id = id;
	}

	/**
	 * 属性geKind的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "RISKCODE", referencedColumnName = "RISKCODE", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "KINDCODE", referencedColumnName = "KINDCODE", nullable = false, insertable = false, updatable = false) })
	public GeKind getGeKind() {
		return this.geKind;
	}

	/**
	 * 属性geKind的setter方法
	 */
	public void setGeKind(GeKind geKind) {
		this.geKind = geKind;
	}

	/**
	 * 属性标志的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标志的setter方法
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
