package cn.com.sinosoft.ebusiness.cms.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * POJO类CmsChannelTemplet
 */
@Entity
@Table(name = "CMS_CHANNEL_TEMPLET")
public class CmsChannelTemplet implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性ID */
	private Integer id;

	/** 属性chlid */
	private String chlid;

	/** 属性tid */
	private String tid;

	/** 属性attr */
	private String attr;

	/**
	 * 类CmsChannelTemplet的默认构造方法
	 */
	public CmsChannelTemplet() {
	}

	/**
	 * 属性ID的getter方法
	 */
	@Id
	@Column(name = "ID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_CHANNEL_TEMPLET_SEQ",allocationSize=1)
	public Integer getId() {
		return this.id;
	}

	/**
	 * 属性ID的setter方法
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 属性chlid的getter方法
	 */

	@Column(name = "CHLID")
	public String getChlid() {
		return this.chlid;
	}

	/**
	 * 属性chlid的setter方法
	 */
	public void setChlid(String chlid) {
		this.chlid = chlid;
	}

	/**
	 * 属性tid的getter方法
	 */

	@Column(name = "TID")
	public String getTid() {
		return this.tid;
	}

	/**
	 * 属性tid的setter方法
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * 属性attr的getter方法
	 */

	@Column(name = "ATTR")
	public String getAttr() {
		return this.attr;
	}

	/**
	 * 属性attr的setter方法
	 */
	public void setAttr(String attr) {
		this.attr = attr;
	}

}
