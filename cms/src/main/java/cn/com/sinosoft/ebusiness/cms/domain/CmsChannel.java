package cn.com.sinosoft.ebusiness.cms.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类CmsChannel
 */
@Entity
@Table(name = "CMS_CHANNEL")
public class CmsChannel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性ChannelID */
	private Integer channelID;

	/** 属性SiteID */
	private String siteID;

	/** 属性ChnlName */
	private String chnlName;

	/** 属性ChnlDesc */
	private String chnlDesc;

	/** 属性ParentID */
	private String parentID;

	/** 属性ChnlOrder */
	private BigDecimal chnlOrder;

	/** 属性ChnlDataPath */
	private String chnlDataPath;

	/** 属性ChnlType */
	private String chnlType;

	/** 属性ChnlMakeDate */
	private Date chnlMakeDate;

	/** 属性CrUser */
	private String crUser;

	/** 属性Status */
	private String status;

	/** 属性LinkUrl */
	private String linkUrl;

	/** 属性ChnlDesName */
	private String chnlDesName;

	/** 属性ChnlDataStatus */
	private BigDecimal chnlDataStatus;

	/** 属性ChannelPower */
	private String channelPower;

	/** 属性MirrorID */
	private String mirrorID;

	/**
	 * 类CmsChannel的默认构造方法
	 */
	public CmsChannel() {
	}

	/**
	 * 属性ChannelID的getter方法
	 */
	@Id
	@Column(name = "CHANNELID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_CHANNEL_SEQ",allocationSize=1)
	public Integer getChannelID() {
		return this.channelID;
	}

	/**
	 * 属性ChannelID的setter方法
	 */
	public void setChannelID(Integer channelID) {
		this.channelID = channelID;
	}

	/**
	 * 属性SiteID的getter方法
	 */

	@Column(name = "SITEID")
	public String getSiteID() {
		return this.siteID;
	}

	/**
	 * 属性SiteID的setter方法
	 */
	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	/**
	 * 属性ChnlName的getter方法
	 */

	@Column(name = "CHNLNAME")
	public String getChnlName() {
		return this.chnlName;
	}

	/**
	 * 属性ChnlName的setter方法
	 */
	public void setChnlName(String chnlName) {
		this.chnlName = chnlName;
	}

	/**
	 * 属性ChnlDesc的getter方法
	 */

	@Column(name = "CHNLDESC")
	public String getChnlDesc() {
		return this.chnlDesc;
	}

	/**
	 * 属性ChnlDesc的setter方法
	 */
	public void setChnlDesc(String chnlDesc) {
		this.chnlDesc = chnlDesc;
	}

	/**
	 * 属性ParentID的getter方法
	 */

	@Column(name = "PARENTID")
	public String getParentID() {
		return this.parentID;
	}

	/**
	 * 属性ParentID的setter方法
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	/**
	 * 属性ChnlOrder的getter方法
	 */

	@Column(name = "CHNLORDER")
	public BigDecimal getChnlOrder() {
		return this.chnlOrder;
	}

	/**
	 * 属性ChnlOrder的setter方法
	 */
	public void setChnlOrder(BigDecimal chnlOrder) {
		this.chnlOrder = chnlOrder;
	}

	/**
	 * 属性ChnlDataPath的getter方法
	 */

	@Column(name = "CHNLDATAPATH")
	public String getChnlDataPath() {
		return this.chnlDataPath;
	}

	/**
	 * 属性ChnlDataPath的setter方法
	 */
	public void setChnlDataPath(String chnlDataPath) {
		this.chnlDataPath = chnlDataPath;
	}

	/**
	 * 属性ChnlType的getter方法
	 */

	@Column(name = "CHNLTYPE")
	public String getChnlType() {
		return this.chnlType;
	}

	/**
	 * 属性ChnlType的setter方法
	 */
	public void setChnlType(String chnlType) {
		this.chnlType = chnlType;
	}

	/**
	 * 属性ChnlMakeDate的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CHNLMAKEDATE")
	public Date getChnlMakeDate() {
		return this.chnlMakeDate;
	}

	/**
	 * 属性ChnlMakeDate的setter方法
	 */
	public void setChnlMakeDate(Date chnlMakeDate) {
		this.chnlMakeDate = chnlMakeDate;
	}

	/**
	 * 属性CrUser的getter方法
	 */

	@Column(name = "CRUSER")
	public String getCrUser() {
		return this.crUser;
	}

	/**
	 * 属性CrUser的setter方法
	 */
	public void setCrUser(String crUser) {
		this.crUser = crUser;
	}

	/**
	 * 属性Status的getter方法
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * 属性Status的setter方法
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 属性LinkUrl的getter方法
	 */

	@Column(name = "LINKURL")
	public String getLinkUrl() {
		return this.linkUrl;
	}

	/**
	 * 属性LinkUrl的setter方法
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * 属性ChnlDesName的getter方法
	 */

	@Column(name = "CHNLDESNAME")
	public String getChnlDesName() {
		return this.chnlDesName;
	}

	/**
	 * 属性ChnlDesName的setter方法
	 */
	public void setChnlDesName(String chnlDesName) {
		this.chnlDesName = chnlDesName;
	}

	/**
	 * 属性ChnlDataStatus的getter方法
	 */

	@Column(name = "CHNLDATASTATUS")
	public BigDecimal getChnlDataStatus() {
		return this.chnlDataStatus;
	}

	/**
	 * 属性ChnlDataStatus的setter方法
	 */
	public void setChnlDataStatus(BigDecimal chnlDataStatus) {
		this.chnlDataStatus = chnlDataStatus;
	}

	/**
	 * 属性ChannelPower的getter方法
	 */

	@Column(name = "CHANNELPOWER")
	public String getChannelPower() {
		return this.channelPower;
	}

	/**
	 * 属性ChannelPower的setter方法
	 */
	public void setChannelPower(String channelPower) {
		this.channelPower = channelPower;
	}

	/**
	 * 属性MirrorID的getter方法
	 */

	@Column(name = "MIRRORID")
	public String getMirrorID() {
		return this.mirrorID;
	}

	/**
	 * 属性MirrorID的setter方法
	 */
	public void setMirrorID(String mirrorID) {
		this.mirrorID = mirrorID;
	}

}
