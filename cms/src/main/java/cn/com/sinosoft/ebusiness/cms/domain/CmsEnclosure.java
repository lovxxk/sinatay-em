package cn.com.sinosoft.ebusiness.cms.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * POJO��CmsEnclosure
 */
@Entity
@Table(name = "CMS_ENCLOSURE")
public class CmsEnclosure implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����AttID */
	private Integer attID;

	/** ����AttName */
	private String attName;

	/** ����AttDocID */
	private String attDocID;

	/** ����AttDesc */
	private String attDesc;

	/** ����AttPath */
	private String attPath;

	/** ����AttType */
	private String attType;

	/**
	 * ��CmsEnclosure��Ĭ�Ϲ��췽��
	 */
	public CmsEnclosure() {
	}

	/**
	 * ����AttID��getter����
	 */
	@Id
	@Column(name = "ATTID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_ENCLOSURE_SEQ",allocationSize=1)
	public Integer getAttID() {
		return this.attID;
	}

	/**
	 * ����AttID��setter����
	 */
	public void setAttID(Integer attID) {
		this.attID = attID;
	}

	/**
	 * ����AttName��getter����
	 */

	@Column(name = "ATTNAME")
	public String getAttName() {
		return this.attName;
	}

	/**
	 * ����AttName��setter����
	 */
	public void setAttName(String attName) {
		this.attName = attName;
	}

	/**
	 * ����AttDocID��getter����
	 */

	@Column(name = "ATTDOCID")
	public String getAttDocID() {
		return this.attDocID;
	}

	/**
	 * ����AttDocID��setter����
	 */
	public void setAttDocID(String attDocID) {
		this.attDocID = attDocID;
	}

	/**
	 * ����AttDesc��getter����
	 */

	@Column(name = "ATTDESC")
	public String getAttDesc() {
		return this.attDesc;
	}

	/**
	 * ����AttDesc��setter����
	 */
	public void setAttDesc(String attDesc) {
		this.attDesc = attDesc;
	}

	/**
	 * ����AttPath��getter����
	 */

	@Column(name = "ATTPATH")
	public String getAttPath() {
		return this.attPath;
	}

	/**
	 * ����AttPath��setter����
	 */
	public void setAttPath(String attPath) {
		this.attPath = attPath;
	}

	/**
	 * ����AttType��getter����
	 */

	@Column(name = "ATTTYPE")
	public String getAttType() {
		return this.attType;
	}

	/**
	 * ����AttType��setter����
	 */
	public void setAttType(String attType) {
		this.attType = attType;
	}

}
