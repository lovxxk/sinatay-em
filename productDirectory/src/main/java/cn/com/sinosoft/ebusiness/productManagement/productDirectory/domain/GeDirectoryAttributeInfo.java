package cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类GeDirectoryAttributeInfo
 */
@Entity
@Table(name = "GE_DIRECTORY_ATTRIBUTE_INFO")
public class GeDirectoryAttributeInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性属性ID */
	private String attrID;

	/** 属性产品属性信息表-GE_Directory_Attribute_Info */
	private GeDirectoryAttributeInfo geDirectoryAttributeInfo;

	/** 属性属性名称 */
	private String attrName;

	/** 属性显示顺序 */
	private Integer seqIndex;

	/** 属性属性描述 */
	private String attrDescription;

	/** 属性属性宣传信息 */
	private String attrNoteInfo;

	/** 属性小图片one */
	private String attrSmallPictureOne;

	/** 属性小图片two */
	private String attrSmallPictureTwo;

	/** 属性中图片one */
	private String attrMiddlePictureOne;

	/** 属性中图片two */
	private String attrMiddlePictureTwo;

	/** 属性大图片one */
	private String attrBigPictureOne;

	/** 属性大图片Two */
	private String attrBigPictureTwo;

	/** 属性geDirectoryAttributeRelates */
	private List<GeDirectoryAttributeRelate> geDirectoryAttributeRelates = new ArrayList<GeDirectoryAttributeRelate>(
			0);

	/** 属性geDirectoryAttributeInfos */
	private List<GeDirectoryAttributeInfo> geDirectoryAttributeInfos = new ArrayList<GeDirectoryAttributeInfo>(
			0);

	/**
	 * 类GeDirectoryAttributeInfo的默认构造方法
	 */
	public GeDirectoryAttributeInfo() {
	}

	/**
	 * 属性属性ID的getter方法
	 */
	@Id
	@Column(name = "ATTRID")
	public String getAttrID() {
		return this.attrID;
	}
	/**
	 * 属性属性ID的setter方法
	 */
	public void setAttrID(String attrID) {
		this.attrID = attrID;
	}
	/**
	 * 属性产品属性信息表-GE_Directory_Attribute_Info的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTATTRID")
	public GeDirectoryAttributeInfo getGeDirectoryAttributeInfo() {
		return this.geDirectoryAttributeInfo;
	}
	/**
	 * 属性产品属性信息表-GE_Directory_Attribute_Info的setter方法
	 */
	public void setGeDirectoryAttributeInfo(
			GeDirectoryAttributeInfo geDirectoryAttributeInfo) {
		this.geDirectoryAttributeInfo = geDirectoryAttributeInfo;
	}
	/**
	 * 属性属性名称的getter方法
	 */

	@Column(name = "ATTRNAME")
	public String getAttrName() {
		return this.attrName;
	}
	/**
	 * 属性属性名称的setter方法
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	/**
	 * 属性显示顺序的getter方法
	 */

	@Column(name = "SEQINDEX")
	public Integer getSeqIndex() {
		return this.seqIndex;
	}
	/**
	 * 属性显示顺序的setter方法
	 */
	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
	}
	/**
	 * 属性属性描述的getter方法
	 */

	@Column(name = "ATTRDESCRIPTION")
	public String getAttrDescription() {
		return this.attrDescription;
	}
	/**
	 * 属性属性描述的setter方法
	 */
	public void setAttrDescription(String attrDescription) {
		this.attrDescription = attrDescription;
	}
	/**
	 * 属性属性宣传信息的getter方法
	 */

	@Column(name = "ATTRNOTEINFO")
	public String getAttrNoteInfo() {
		return this.attrNoteInfo;
	}
	/**
	 * 属性属性宣传信息的setter方法
	 */
	public void setAttrNoteInfo(String attrNoteInfo) {
		this.attrNoteInfo = attrNoteInfo;
	}
	/**
	 * 属性小图片one的getter方法
	 */

	@Column(name = "ATTRSMALLPICTUREONE")
	public String getAttrSmallPictureOne() {
		return this.attrSmallPictureOne;
	}
	/**
	 * 属性小图片one的setter方法
	 */
	public void setAttrSmallPictureOne(String attrSmallPictureOne) {
		this.attrSmallPictureOne = attrSmallPictureOne;
	}
	/**
	 * 属性小图片two的getter方法
	 */

	@Column(name = "ATTRSMALLPICTURETWO")
	public String getAttrSmallPictureTwo() {
		return this.attrSmallPictureTwo;
	}
	/**
	 * 属性小图片two的setter方法
	 */
	public void setAttrSmallPictureTwo(String attrSmallPictureTwo) {
		this.attrSmallPictureTwo = attrSmallPictureTwo;
	}
	/**
	 * 属性中图片one的getter方法
	 */

	@Column(name = "ATTRMIDDLEPICTUREONE")
	public String getAttrMiddlePictureOne() {
		return this.attrMiddlePictureOne;
	}
	/**
	 * 属性中图片one的setter方法
	 */
	public void setAttrMiddlePictureOne(String attrMiddlePictureOne) {
		this.attrMiddlePictureOne = attrMiddlePictureOne;
	}
	/**
	 * 属性中图片two的getter方法
	 */

	@Column(name = "ATTRMIDDLEPICTURETWO")
	public String getAttrMiddlePictureTwo() {
		return this.attrMiddlePictureTwo;
	}
	/**
	 * 属性中图片two的setter方法
	 */
	public void setAttrMiddlePictureTwo(String attrMiddlePictureTwo) {
		this.attrMiddlePictureTwo = attrMiddlePictureTwo;
	}
	/**
	 * 属性大图片one的getter方法
	 */

	@Column(name = "ATTRBIGPICTUREONE")
	public String getAttrBigPictureOne() {
		return this.attrBigPictureOne;
	}
	/**
	 * 属性大图片one的setter方法
	 */
	public void setAttrBigPictureOne(String attrBigPictureOne) {
		this.attrBigPictureOne = attrBigPictureOne;
	}
	/**
	 * 属性大图片Two的getter方法
	 */

	@Column(name = "ATTRBIGPICTURETWO")
	public String getAttrBigPictureTwo() {
		return this.attrBigPictureTwo;
	}
	/**
	 * 属性大图片Two的setter方法
	 */
	public void setAttrBigPictureTwo(String attrBigPictureTwo) {
		this.attrBigPictureTwo = attrBigPictureTwo;
	}
	/**
	 * 属性geDirectoryAttributeRelates的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectoryAttributeInfo")
	public List<GeDirectoryAttributeRelate> getGeDirectoryAttributeRelates() {
		return this.geDirectoryAttributeRelates;
	}
	/**
	 * 属性geDirectoryAttributeRelates的setter方法
	 */
	public void setGeDirectoryAttributeRelates(
			List<GeDirectoryAttributeRelate> geDirectoryAttributeRelates) {
		this.geDirectoryAttributeRelates = geDirectoryAttributeRelates;
	}
	/**
	 * 属性geDirectoryAttributeInfos的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectoryAttributeInfo")
	public List<GeDirectoryAttributeInfo> getGeDirectoryAttributeInfos() {
		return this.geDirectoryAttributeInfos;
	}
	/**
	 * 属性geDirectoryAttributeInfos的setter方法
	 */
	public void setGeDirectoryAttributeInfos(
			List<GeDirectoryAttributeInfo> geDirectoryAttributeInfos) {
		this.geDirectoryAttributeInfos = geDirectoryAttributeInfos;
	}

}
