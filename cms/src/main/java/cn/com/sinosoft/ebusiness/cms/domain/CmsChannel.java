package cn.com.sinosoft.ebusiness.cms.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��CmsChannel
 */
@Entity
@Table(name = "CMS_CHANNEL")
public class CmsChannel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����ChannelID */
	private Integer channelID;

	/** ����SiteID */
	private String siteID;

	/** ����ChnlName */
	private String chnlName;

	/** ����ChnlDesc */
	private String chnlDesc;

	/** ����ParentID */
	private String parentID;

	/** ����ChnlOrder */
	private BigDecimal chnlOrder;

	/** ����ChnlDataPath */
	private String chnlDataPath;

	/** ����ChnlType */
	private String chnlType;

	/** ����ChnlMakeDate */
	private Date chnlMakeDate;

	/** ����CrUser */
	private String crUser;

	/** ����Status */
	private String status;

	/** ����LinkUrl */
	private String linkUrl;

	/** ����ChnlDesName */
	private String chnlDesName;

	/** ����ChnlDataStatus */
	private BigDecimal chnlDataStatus;

	/** ����ChannelPower */
	private String channelPower;

	/** ����MirrorID */
	private String mirrorID;

	/**
	 * ��CmsChannel��Ĭ�Ϲ��췽��
	 */
	public CmsChannel() {
	}

	/**
	 * ����ChannelID��getter����
	 */
	@Id
	@Column(name = "CHANNELID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_CHANNEL_SEQ",allocationSize=1)
	public Integer getChannelID() {
		return this.channelID;
	}

	/**
	 * ����ChannelID��setter����
	 */
	public void setChannelID(Integer channelID) {
		this.channelID = channelID;
	}

	/**
	 * ����SiteID��getter����
	 */

	@Column(name = "SITEID")
	public String getSiteID() {
		return this.siteID;
	}

	/**
	 * ����SiteID��setter����
	 */
	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	/**
	 * ����ChnlName��getter����
	 */

	@Column(name = "CHNLNAME")
	public String getChnlName() {
		return this.chnlName;
	}

	/**
	 * ����ChnlName��setter����
	 */
	public void setChnlName(String chnlName) {
		this.chnlName = chnlName;
	}

	/**
	 * ����ChnlDesc��getter����
	 */

	@Column(name = "CHNLDESC")
	public String getChnlDesc() {
		return this.chnlDesc;
	}

	/**
	 * ����ChnlDesc��setter����
	 */
	public void setChnlDesc(String chnlDesc) {
		this.chnlDesc = chnlDesc;
	}

	/**
	 * ����ParentID��getter����
	 */

	@Column(name = "PARENTID")
	public String getParentID() {
		return this.parentID;
	}

	/**
	 * ����ParentID��setter����
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	/**
	 * ����ChnlOrder��getter����
	 */

	@Column(name = "CHNLORDER")
	public BigDecimal getChnlOrder() {
		return this.chnlOrder;
	}

	/**
	 * ����ChnlOrder��setter����
	 */
	public void setChnlOrder(BigDecimal chnlOrder) {
		this.chnlOrder = chnlOrder;
	}

	/**
	 * ����ChnlDataPath��getter����
	 */

	@Column(name = "CHNLDATAPATH")
	public String getChnlDataPath() {
		return this.chnlDataPath;
	}

	/**
	 * ����ChnlDataPath��setter����
	 */
	public void setChnlDataPath(String chnlDataPath) {
		this.chnlDataPath = chnlDataPath;
	}

	/**
	 * ����ChnlType��getter����
	 */

	@Column(name = "CHNLTYPE")
	public String getChnlType() {
		return this.chnlType;
	}

	/**
	 * ����ChnlType��setter����
	 */
	public void setChnlType(String chnlType) {
		this.chnlType = chnlType;
	}

	/**
	 * ����ChnlMakeDate��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CHNLMAKEDATE")
	public Date getChnlMakeDate() {
		return this.chnlMakeDate;
	}

	/**
	 * ����ChnlMakeDate��setter����
	 */
	public void setChnlMakeDate(Date chnlMakeDate) {
		this.chnlMakeDate = chnlMakeDate;
	}

	/**
	 * ����CrUser��getter����
	 */

	@Column(name = "CRUSER")
	public String getCrUser() {
		return this.crUser;
	}

	/**
	 * ����CrUser��setter����
	 */
	public void setCrUser(String crUser) {
		this.crUser = crUser;
	}

	/**
	 * ����Status��getter����
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * ����Status��setter����
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * ����LinkUrl��getter����
	 */

	@Column(name = "LINKURL")
	public String getLinkUrl() {
		return this.linkUrl;
	}

	/**
	 * ����LinkUrl��setter����
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * ����ChnlDesName��getter����
	 */

	@Column(name = "CHNLDESNAME")
	public String getChnlDesName() {
		return this.chnlDesName;
	}

	/**
	 * ����ChnlDesName��setter����
	 */
	public void setChnlDesName(String chnlDesName) {
		this.chnlDesName = chnlDesName;
	}

	/**
	 * ����ChnlDataStatus��getter����
	 */

	@Column(name = "CHNLDATASTATUS")
	public BigDecimal getChnlDataStatus() {
		return this.chnlDataStatus;
	}

	/**
	 * ����ChnlDataStatus��setter����
	 */
	public void setChnlDataStatus(BigDecimal chnlDataStatus) {
		this.chnlDataStatus = chnlDataStatus;
	}

	/**
	 * ����ChannelPower��getter����
	 */

	@Column(name = "CHANNELPOWER")
	public String getChannelPower() {
		return this.channelPower;
	}

	/**
	 * ����ChannelPower��setter����
	 */
	public void setChannelPower(String channelPower) {
		this.channelPower = channelPower;
	}

	/**
	 * ����MirrorID��getter����
	 */

	@Column(name = "MIRRORID")
	public String getMirrorID() {
		return this.mirrorID;
	}

	/**
	 * ����MirrorID��setter����
	 */
	public void setMirrorID(String mirrorID) {
		this.mirrorID = mirrorID;
	}

}
