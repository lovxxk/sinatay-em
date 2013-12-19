package cn.com.sinosoft.ebusiness.cms.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��CmsWebinfo
 */
@Entity
@Table(name = "CMS_WEBINFO")
public class CmsWebinfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����ID */
	private Integer id;

	/** ����PageType */
	private String pageType;

	/** ����Area */
	private String area;

	/** ����ChannelID */
	private String channelID;

	/** ����Path */
	private String path;

	/** ����CreateTime */
	private Date createTime;

	/** ����LastModify */
	private Date lastModify;

	/**
	 * ��CmsWebinfo��Ĭ�Ϲ��췽��
	 */
	public CmsWebinfo() {
	}

	/**
	 * ����ID��getter����
	 */
	@Id
	@Column(name = "ID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_WEBINFO_SEQ",allocationSize=1)
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����ID��setter����
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ����PageType��getter����
	 */

	@Column(name = "PAGETYPE")
	public String getPageType() {
		return this.pageType;
	}

	/**
	 * ����PageType��setter����
	 */
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/**
	 * ����Area��getter����
	 */

	@Column(name = "AREA")
	public String getArea() {
		return this.area;
	}

	/**
	 * ����Area��setter����
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * ����ChannelID��getter����
	 */

	@Column(name = "CHANNELID")
	public String getChannelID() {
		return this.channelID;
	}

	/**
	 * ����ChannelID��setter����
	 */
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	/**
	 * ����Path��getter����
	 */

	@Column(name = "PATH")
	public String getPath() {
		return this.path;
	}

	/**
	 * ����Path��setter����
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * ����CreateTime��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ����CreateTime��setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ����LastModify��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "LASTMODIFY")
	public Date getLastModify() {
		return this.lastModify;
	}

	/**
	 * ����LastModify��setter����
	 */
	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

}
