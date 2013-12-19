package cn.com.sinosoft.ebusiness.cms.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * POJO类CmsEnclosure
 */
@Entity
@Table(name = "CMS_ENCLOSURE")
public class CmsEnclosure implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性AttID */
	private Integer attID;

	/** 属性AttName */
	private String attName;

	/** 属性AttDocID */
	private String attDocID;

	/** 属性AttDesc */
	private String attDesc;

	/** 属性AttPath */
	private String attPath;

	/** 属性AttType */
	private String attType;

	/**
	 * 类CmsEnclosure的默认构造方法
	 */
	public CmsEnclosure() {
	}

	/**
	 * 属性AttID的getter方法
	 */
	@Id
	@Column(name = "ATTID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_ENCLOSURE_SEQ",allocationSize=1)
	public Integer getAttID() {
		return this.attID;
	}

	/**
	 * 属性AttID的setter方法
	 */
	public void setAttID(Integer attID) {
		this.attID = attID;
	}

	/**
	 * 属性AttName的getter方法
	 */

	@Column(name = "ATTNAME")
	public String getAttName() {
		return this.attName;
	}

	/**
	 * 属性AttName的setter方法
	 */
	public void setAttName(String attName) {
		this.attName = attName;
	}

	/**
	 * 属性AttDocID的getter方法
	 */

	@Column(name = "ATTDOCID")
	public String getAttDocID() {
		return this.attDocID;
	}

	/**
	 * 属性AttDocID的setter方法
	 */
	public void setAttDocID(String attDocID) {
		this.attDocID = attDocID;
	}

	/**
	 * 属性AttDesc的getter方法
	 */

	@Column(name = "ATTDESC")
	public String getAttDesc() {
		return this.attDesc;
	}

	/**
	 * 属性AttDesc的setter方法
	 */
	public void setAttDesc(String attDesc) {
		this.attDesc = attDesc;
	}

	/**
	 * 属性AttPath的getter方法
	 */

	@Column(name = "ATTPATH")
	public String getAttPath() {
		return this.attPath;
	}

	/**
	 * 属性AttPath的setter方法
	 */
	public void setAttPath(String attPath) {
		this.attPath = attPath;
	}

	/**
	 * 属性AttType的getter方法
	 */

	@Column(name = "ATTTYPE")
	public String getAttType() {
		return this.attType;
	}

	/**
	 * 属性AttType的setter方法
	 */
	public void setAttType(String attType) {
		this.attType = attType;
	}

}
