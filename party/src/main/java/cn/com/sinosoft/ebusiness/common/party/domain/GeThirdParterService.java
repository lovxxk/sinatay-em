package cn.com.sinosoft.ebusiness.common.party.domain;
// default package
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * POJO类GeThirdParterService
 */
@Entity
@Table(name = "GE_THIRDPARTER_SERVICE")
public class GeThirdParterService implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性项目ID */
	private String itemID;

	/** 属性第三方合作伙伴信息表-GE_ThirdParter_Info */
	private GeThirdParterInfo geThirdParterInfo;

	/** 属性商品名称 */
	private String itemName;

	/** 属性商品简介 */
	private String itemContent;

	/** 属性商品图片 */
	private byte[] itemPicture;

	/** 属性创建时间 */
	private Date createDate;

	/** 属性标识位 */
	private String flag;
	
	/** 属性图片URL*/
	private String pictureUrl;
	
	/** 属性商品总数量*/
	private String totalNumber;
	
	/** 属性已送出的商品数*/
	private String send;
	
	/** 属性geThirdParterSerialNumbers */
	private List<GeThirdParterSerialNumber> geThirdParterSerialNumbers = new ArrayList<GeThirdParterSerialNumber>(
			0);
	//以下为业务使用字段非持久化使用的字段 增值服务需求
	
	private String activityPattern;
	
	private String nValue;
	
	private String startDate;
	
	private String endDate;
	
	private String deptID;
	
	private String riskCode;
	
	private String proposalNo;
	
	private String proposalArea;//投保地区
	
	private String surplus;//剩余量
	private java.util.Map<String,List<GeThirdParterSerialNumber>> map;//各个分公司的汇总
	//以下为业务使用字段的图片存储的实现
	/**属性文件*/
	private File  attrPicture;
	
	/**属性mis的绝对路径*/
	private String imageAbsolutePath;
	
	/**属性online的绝对路径*/
	private String imageOnlineAbsolutePath;
	
	/**属性文件名*/
	private String attrPictureFileName;
	
	/**属性保存文件路径URL*/
	private String imagePath;
	
	/** 属性库存余量*/
	private String inventory;

	/**
	 * 类GeThirdParterService的默认构造方法
	 */
	public GeThirdParterService() {
	}

	/**
	 * 属性项目ID的getter方法
	 */
	@Id
	@Column(name = "ITEMID" ,unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getItemID() {
		return this.itemID;
	}

	/**
	 * 属性项目ID的setter方法
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * 属性第三方合作伙伴信息表-GE_ThirdParter_Info的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THIRDPARTERID")
	public GeThirdParterInfo getGeThirdParterInfo() {
		return this.geThirdParterInfo;
	}

	/**
	 * 属性第三方合作伙伴信息表-GE_ThirdParter_Info的setter方法
	 */
	public void setGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo) {
		this.geThirdParterInfo = geThirdParterInfo;
	}

	/**
	 * 属性商品名称的getter方法
	 */

	@Column(name = "ITEMNAME")
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 属性商品名称的setter方法
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 属性商品简介的getter方法
	 */

	@Column(name = "ITEMCONTENT")
	public String getItemContent() {
		return this.itemContent;
	}

	/**
	 * 属性商品简介的setter方法
	 */
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	/**
	 * 属性商品图片的getter方法
	 */

	@Column(name = "ITEMPICTURE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.BlobByteArrayType")
	public byte[] getItemPicture() {
		return this.itemPicture;
	}

	/**
	 * 属性商品图片的setter方法
	 */
	public void setItemPicture(byte[] itemPicture) {
		this.itemPicture = itemPicture;
	}

	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 属性标识位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标识位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	/**
	 * 属性图片URL的getter方法
	 */
	@Column(name = "PICTUREURL")
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * 属性图片URL的setter方法
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	/**
	 * 属性商品总数量的getter方法
	 */
	@Column(name = "TOTALNUMBER")
	public String getTotalNumber() {
		return totalNumber;
	}

	/**
	 * 属性商品总数量的setter方法
	 */
	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

	/**
	 * 属性已送出商品的getter方法
	 */
	@Column(name = "SEND")
	public String getSend() {
		return send;
	}
	
	public void setSend(String send) {
		this.send = send;
	}
	/**
	 * 属性geThirdParterSerialNumbers的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geThirdParterService")
	@JsonIgnore
	public List<GeThirdParterSerialNumber> getGeThirdParterSerialNumbers() {
		return this.geThirdParterSerialNumbers;
	}

	/**
	 * 属性geThirdParterSerialNumbers的setter方法
	 */
	public void setGeThirdParterSerialNumbers(
			List<GeThirdParterSerialNumber> geThirdParterSerialNumbers) {
		this.geThirdParterSerialNumbers = geThirdParterSerialNumbers;
	}
	//set get method 业务使用的字段，非持久化使用
	@Transient
	public String getActivityPattern() {
		return activityPattern;
	}

	public void setActivityPattern(String activityPattern) {
		this.activityPattern = activityPattern;
	}
	@Transient
	public String getnValue() {
		return nValue;
	}

	public void setnValue(String nValue) {
		this.nValue = nValue;
	}
	@Transient
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@Transient
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Transient
	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	@Transient
	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	@Transient
	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	
	@Transient
	public File getAttrPicture() {
		return attrPicture;
	}

	public void setAttrPicture(File attrPicture) {
		this.attrPicture = attrPicture;
	}
	@Transient
	public String getAttrPictureFileName() {
		return attrPictureFileName;
	}

	public void setAttrPictureFileName(String attrPictureFileName) {
		this.attrPictureFileName = attrPictureFileName;
	}
	@Transient
	public String getImageAbsolutePath() {
		return imageAbsolutePath;
	}

	public void setImageAbsolutePath(String imageAbsolutePath) {
		this.imageAbsolutePath = imageAbsolutePath;
	}
	@Transient
	public String getImageOnlineAbsolutePath() {
		return imageOnlineAbsolutePath;
	}

	public void setImageOnlineAbsolutePath(String imageOnlineAbsolutePath) {
		this.imageOnlineAbsolutePath = imageOnlineAbsolutePath;
	}
	@Transient
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Transient
	public String getProposalArea() {
		return proposalArea;
	}

	public void setProposalArea(String proposalArea) {
		this.proposalArea = proposalArea;
	}
	@Transient
	public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}
	@Transient
	public java.util.Map<String, List<GeThirdParterSerialNumber>> getMap() {
		return map;
	}
	public void setMap(java.util.Map<String, List<GeThirdParterSerialNumber>> map) {
		this.map = map;
	}
}

