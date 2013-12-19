package cn.com.sinosoft.ebusiness.common.party.domain;
// default package
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeThirdParterService
 */
@Entity
@Table(name = "GE_THIRDPARTER_SERVICE")
public class GeThirdParterService implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������ĿID */
	private String itemID;

	/** ���Ե��������������Ϣ��-GE_ThirdParter_Info */
	private GeThirdParterInfo geThirdParterInfo;

	/** ������Ʒ���� */
	private String itemName;

	/** ������Ʒ��� */
	private String itemContent;

	/** ������ƷͼƬ */
	private byte[] itemPicture;

	/** ���Դ���ʱ�� */
	private Date createDate;

	/** ���Ա�ʶλ */
	private String flag;
	
	/** ����ͼƬURL*/
	private String pictureUrl;
	
	/** ������Ʒ������*/
	private String totalNumber;
	
	/** �������ͳ�����Ʒ��*/
	private String send;
	
	/** ����geThirdParterSerialNumbers */
	private List<GeThirdParterSerialNumber> geThirdParterSerialNumbers = new ArrayList<GeThirdParterSerialNumber>(
			0);
	//����Ϊҵ��ʹ���ֶηǳ־û�ʹ�õ��ֶ� ��ֵ��������
	
	private String activityPattern;
	
	private String nValue;
	
	private String startDate;
	
	private String endDate;
	
	private String deptID;
	
	private String riskCode;
	
	private String proposalNo;
	
	private String proposalArea;//Ͷ������
	
	private String surplus;//ʣ����
	private java.util.Map<String,List<GeThirdParterSerialNumber>> map;//�����ֹ�˾�Ļ���
	//����Ϊҵ��ʹ���ֶε�ͼƬ�洢��ʵ��
	/**�����ļ�*/
	private File  attrPicture;
	
	/**����mis�ľ���·��*/
	private String imageAbsolutePath;
	
	/**����online�ľ���·��*/
	private String imageOnlineAbsolutePath;
	
	/**�����ļ���*/
	private String attrPictureFileName;
	
	/**���Ա����ļ�·��URL*/
	private String imagePath;
	
	/** ���Կ������*/
	private String inventory;

	/**
	 * ��GeThirdParterService��Ĭ�Ϲ��췽��
	 */
	public GeThirdParterService() {
	}

	/**
	 * ������ĿID��getter����
	 */
	@Id
	@Column(name = "ITEMID" ,unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getItemID() {
		return this.itemID;
	}

	/**
	 * ������ĿID��setter����
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * ���Ե��������������Ϣ��-GE_ThirdParter_Info��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THIRDPARTERID")
	public GeThirdParterInfo getGeThirdParterInfo() {
		return this.geThirdParterInfo;
	}

	/**
	 * ���Ե��������������Ϣ��-GE_ThirdParter_Info��setter����
	 */
	public void setGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo) {
		this.geThirdParterInfo = geThirdParterInfo;
	}

	/**
	 * ������Ʒ���Ƶ�getter����
	 */

	@Column(name = "ITEMNAME")
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * ������Ʒ���Ƶ�setter����
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * ������Ʒ����getter����
	 */

	@Column(name = "ITEMCONTENT")
	public String getItemContent() {
		return this.itemContent;
	}

	/**
	 * ������Ʒ����setter����
	 */
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	/**
	 * ������ƷͼƬ��getter����
	 */

	@Column(name = "ITEMPICTURE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.BlobByteArrayType")
	public byte[] getItemPicture() {
		return this.itemPicture;
	}

	/**
	 * ������ƷͼƬ��setter����
	 */
	public void setItemPicture(byte[] itemPicture) {
		this.itemPicture = itemPicture;
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * ���Ա�ʶλ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�ʶλ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	/**
	 * ����ͼƬURL��getter����
	 */
	@Column(name = "PICTUREURL")
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * ����ͼƬURL��setter����
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	/**
	 * ������Ʒ��������getter����
	 */
	@Column(name = "TOTALNUMBER")
	public String getTotalNumber() {
		return totalNumber;
	}

	/**
	 * ������Ʒ��������setter����
	 */
	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

	/**
	 * �������ͳ���Ʒ��getter����
	 */
	@Column(name = "SEND")
	public String getSend() {
		return send;
	}
	
	public void setSend(String send) {
		this.send = send;
	}
	/**
	 * ����geThirdParterSerialNumbers��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geThirdParterService")
	@JsonIgnore
	public List<GeThirdParterSerialNumber> getGeThirdParterSerialNumbers() {
		return this.geThirdParterSerialNumbers;
	}

	/**
	 * ����geThirdParterSerialNumbers��setter����
	 */
	public void setGeThirdParterSerialNumbers(
			List<GeThirdParterSerialNumber> geThirdParterSerialNumbers) {
		this.geThirdParterSerialNumbers = geThirdParterSerialNumbers;
	}
	//set get method ҵ��ʹ�õ��ֶΣ��ǳ־û�ʹ��
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

