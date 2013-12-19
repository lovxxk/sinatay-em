package cn.com.sinosoft.ebusiness.cms.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO类CmsWebinfo
 */
@Entity
@Table(name = "CMS_WEBINFO")
public class CmsWebinfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性ID */
	private Integer id;

	/** 属性PageType */
	private String pageType;

	/** 属性Area */
	private String area;

	/** 属性ChannelID */
	private String channelID;

	/** 属性Path */
	private String path;

	/** 属性CreateTime */
	private Date createTime;

	/** 属性LastModify */
	private Date lastModify;

	/**
	 * 类CmsWebinfo的默认构造方法
	 */
	public CmsWebinfo() {
	}

	/**
	 * 属性ID的getter方法
	 */
	@Id
	@Column(name = "ID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_WEBINFO_SEQ",allocationSize=1)
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
	 * 属性PageType的getter方法
	 */

	@Column(name = "PAGETYPE")
	public String getPageType() {
		return this.pageType;
	}

	/**
	 * 属性PageType的setter方法
	 */
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/**
	 * 属性Area的getter方法
	 */

	@Column(name = "AREA")
	public String getArea() {
		return this.area;
	}

	/**
	 * 属性Area的setter方法
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 属性ChannelID的getter方法
	 */

	@Column(name = "CHANNELID")
	public String getChannelID() {
		return this.channelID;
	}

	/**
	 * 属性ChannelID的setter方法
	 */
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	/**
	 * 属性Path的getter方法
	 */

	@Column(name = "PATH")
	public String getPath() {
		return this.path;
	}

	/**
	 * 属性Path的setter方法
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 属性CreateTime的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性CreateTime的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性LastModify的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "LASTMODIFY")
	public Date getLastModify() {
		return this.lastModify;
	}

	/**
	 * 属性LastModify的setter方法
	 */
	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

}
