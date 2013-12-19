package cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * POJO��GeDirectoryAttributeInfo
 */
@Entity
@Table(name = "GE_DIRECTORY_ATTRIBUTE_INFO")
public class GeDirectoryAttributeInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ��������ID */
	private String attrID;

	/** ���Բ�Ʒ������Ϣ��-GE_Directory_Attribute_Info */
	private GeDirectoryAttributeInfo geDirectoryAttributeInfo;

	/** ������������ */
	private String attrName;

	/** ������ʾ˳�� */
	private Integer seqIndex;

	/** ������������ */
	private String attrDescription;

	/** ��������������Ϣ */
	private String attrNoteInfo;

	/** ����СͼƬone */
	private String attrSmallPictureOne;

	/** ����СͼƬtwo */
	private String attrSmallPictureTwo;

	/** ������ͼƬone */
	private String attrMiddlePictureOne;

	/** ������ͼƬtwo */
	private String attrMiddlePictureTwo;

	/** ���Դ�ͼƬone */
	private String attrBigPictureOne;

	/** ���Դ�ͼƬTwo */
	private String attrBigPictureTwo;

	/** ����geDirectoryAttributeRelates */
	private List<GeDirectoryAttributeRelate> geDirectoryAttributeRelates = new ArrayList<GeDirectoryAttributeRelate>(
			0);

	/** ����geDirectoryAttributeInfos */
	private List<GeDirectoryAttributeInfo> geDirectoryAttributeInfos = new ArrayList<GeDirectoryAttributeInfo>(
			0);

	/**
	 * ��GeDirectoryAttributeInfo��Ĭ�Ϲ��췽��
	 */
	public GeDirectoryAttributeInfo() {
	}

	/**
	 * ��������ID��getter����
	 */
	@Id
	@Column(name = "ATTRID")
	public String getAttrID() {
		return this.attrID;
	}
	/**
	 * ��������ID��setter����
	 */
	public void setAttrID(String attrID) {
		this.attrID = attrID;
	}
	/**
	 * ���Բ�Ʒ������Ϣ��-GE_Directory_Attribute_Info��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTATTRID")
	public GeDirectoryAttributeInfo getGeDirectoryAttributeInfo() {
		return this.geDirectoryAttributeInfo;
	}
	/**
	 * ���Բ�Ʒ������Ϣ��-GE_Directory_Attribute_Info��setter����
	 */
	public void setGeDirectoryAttributeInfo(
			GeDirectoryAttributeInfo geDirectoryAttributeInfo) {
		this.geDirectoryAttributeInfo = geDirectoryAttributeInfo;
	}
	/**
	 * �����������Ƶ�getter����
	 */

	@Column(name = "ATTRNAME")
	public String getAttrName() {
		return this.attrName;
	}
	/**
	 * �����������Ƶ�setter����
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	/**
	 * ������ʾ˳���getter����
	 */

	@Column(name = "SEQINDEX")
	public Integer getSeqIndex() {
		return this.seqIndex;
	}
	/**
	 * ������ʾ˳���setter����
	 */
	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
	}
	/**
	 * ��������������getter����
	 */

	@Column(name = "ATTRDESCRIPTION")
	public String getAttrDescription() {
		return this.attrDescription;
	}
	/**
	 * ��������������setter����
	 */
	public void setAttrDescription(String attrDescription) {
		this.attrDescription = attrDescription;
	}
	/**
	 * ��������������Ϣ��getter����
	 */

	@Column(name = "ATTRNOTEINFO")
	public String getAttrNoteInfo() {
		return this.attrNoteInfo;
	}
	/**
	 * ��������������Ϣ��setter����
	 */
	public void setAttrNoteInfo(String attrNoteInfo) {
		this.attrNoteInfo = attrNoteInfo;
	}
	/**
	 * ����СͼƬone��getter����
	 */

	@Column(name = "ATTRSMALLPICTUREONE")
	public String getAttrSmallPictureOne() {
		return this.attrSmallPictureOne;
	}
	/**
	 * ����СͼƬone��setter����
	 */
	public void setAttrSmallPictureOne(String attrSmallPictureOne) {
		this.attrSmallPictureOne = attrSmallPictureOne;
	}
	/**
	 * ����СͼƬtwo��getter����
	 */

	@Column(name = "ATTRSMALLPICTURETWO")
	public String getAttrSmallPictureTwo() {
		return this.attrSmallPictureTwo;
	}
	/**
	 * ����СͼƬtwo��setter����
	 */
	public void setAttrSmallPictureTwo(String attrSmallPictureTwo) {
		this.attrSmallPictureTwo = attrSmallPictureTwo;
	}
	/**
	 * ������ͼƬone��getter����
	 */

	@Column(name = "ATTRMIDDLEPICTUREONE")
	public String getAttrMiddlePictureOne() {
		return this.attrMiddlePictureOne;
	}
	/**
	 * ������ͼƬone��setter����
	 */
	public void setAttrMiddlePictureOne(String attrMiddlePictureOne) {
		this.attrMiddlePictureOne = attrMiddlePictureOne;
	}
	/**
	 * ������ͼƬtwo��getter����
	 */

	@Column(name = "ATTRMIDDLEPICTURETWO")
	public String getAttrMiddlePictureTwo() {
		return this.attrMiddlePictureTwo;
	}
	/**
	 * ������ͼƬtwo��setter����
	 */
	public void setAttrMiddlePictureTwo(String attrMiddlePictureTwo) {
		this.attrMiddlePictureTwo = attrMiddlePictureTwo;
	}
	/**
	 * ���Դ�ͼƬone��getter����
	 */

	@Column(name = "ATTRBIGPICTUREONE")
	public String getAttrBigPictureOne() {
		return this.attrBigPictureOne;
	}
	/**
	 * ���Դ�ͼƬone��setter����
	 */
	public void setAttrBigPictureOne(String attrBigPictureOne) {
		this.attrBigPictureOne = attrBigPictureOne;
	}
	/**
	 * ���Դ�ͼƬTwo��getter����
	 */

	@Column(name = "ATTRBIGPICTURETWO")
	public String getAttrBigPictureTwo() {
		return this.attrBigPictureTwo;
	}
	/**
	 * ���Դ�ͼƬTwo��setter����
	 */
	public void setAttrBigPictureTwo(String attrBigPictureTwo) {
		this.attrBigPictureTwo = attrBigPictureTwo;
	}
	/**
	 * ����geDirectoryAttributeRelates��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectoryAttributeInfo")
	public List<GeDirectoryAttributeRelate> getGeDirectoryAttributeRelates() {
		return this.geDirectoryAttributeRelates;
	}
	/**
	 * ����geDirectoryAttributeRelates��setter����
	 */
	public void setGeDirectoryAttributeRelates(
			List<GeDirectoryAttributeRelate> geDirectoryAttributeRelates) {
		this.geDirectoryAttributeRelates = geDirectoryAttributeRelates;
	}
	/**
	 * ����geDirectoryAttributeInfos��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectoryAttributeInfo")
	public List<GeDirectoryAttributeInfo> getGeDirectoryAttributeInfos() {
		return this.geDirectoryAttributeInfos;
	}
	/**
	 * ����geDirectoryAttributeInfos��setter����
	 */
	public void setGeDirectoryAttributeInfos(
			List<GeDirectoryAttributeInfo> geDirectoryAttributeInfos) {
		this.geDirectoryAttributeInfos = geDirectoryAttributeInfos;
	}

}
