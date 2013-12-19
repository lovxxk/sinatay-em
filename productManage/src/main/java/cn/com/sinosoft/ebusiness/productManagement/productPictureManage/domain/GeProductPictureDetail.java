package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeProductPictureDetail
 */
@Entity
@Table(name = "GE_PRODUCT_PICTURE_DETAIL")
public class GeProductPictureDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性detailid */
	private String detailid;

	/** 属性picturename */
	private String picturename;

	/** 属性picturedesc */
	private String picturedesc;

	/** 属性标志位 */
	private String flag;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性geProductPictures */
	private List<GeProductPicture> geProductPictures = new ArrayList<GeProductPicture>(
			0);
	//业务上使用 start
	//苹果上传图片start
	private List<File> uploadPicture;//上传的图片文件
	private List<String> uploadPictureFileName;//上传的图片的名字
	private List<String> uploadPictureContentType;//上传的图片类型
	private List<String> uploadSerialNoList;//上传文件对应的序列号
	private String uploadImagePath;//要上传的图片的物理位置
	private String uploadSavePath;//要保存的图片
	//苹果上传图片end
	//业务上使用 end
	/**
	 * 类GeProductPictureDetail的默认构造方法
	 */
	public GeProductPictureDetail() {
	}

	/**
	 * 属性detailid的getter方法
	 */
	@Id
	@Column(name = "DETAILID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getDetailid() {
		return this.detailid;
	}

	/**
	 * 属性detailid的setter方法
	 */
	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	/**
	 * 属性picturename的getter方法
	 */

	@Column(name = "PICTURENAME")
	public String getPicturename() {
		return this.picturename;
	}

	/**
	 * 属性picturename的setter方法
	 */
	public void setPicturename(String picturename) {
		this.picturename = picturename;
	}

	/**
	 * 属性picturedesc的getter方法
	 */

	@Column(name = "PICTUREDESC")
	public String getPicturedesc() {
		return this.picturedesc;
	}

	/**
	 * 属性picturedesc的setter方法
	 */
	public void setPicturedesc(String picturedesc) {
		this.picturedesc = picturedesc;
	}

	/**
	 * 属性标志位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标志位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性创建时间的getter方法
	 */
	//@Temporal(TemporalType.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性geProductPictures的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductPictureDetail")
	public List<GeProductPicture> getGeProductPictures() {
		return this.geProductPictures;
	}

	/**
	 * 属性geProductPictures的setter方法
	 */
	public void setGeProductPictures(List<GeProductPicture> geProductPictures) {
		this.geProductPictures = geProductPictures;
	}
	@Transient
	public List<File> getUploadPicture() {
		return uploadPicture;
	}

	public void setUploadPicture(List<File> uploadPicture) {
		this.uploadPicture = uploadPicture;
	}
	@Transient
	public List<String> getUploadPictureFileName() {
		return uploadPictureFileName;
	}

	public void setUploadPictureFileName(List<String> uploadPictureFileName) {
		this.uploadPictureFileName = uploadPictureFileName;
	}
	@Transient
	public List<String> getUploadPictureContentType() {
		return uploadPictureContentType;
	}

	public void setUploadPictureContentType(List<String> uploadPictureContentType) {
		this.uploadPictureContentType = uploadPictureContentType;
	}
	@Transient
	public List<String> getUploadSerialNoList() {
		return uploadSerialNoList;
	}

	public void setUploadSerialNoList(List<String> uploadSerialNoList) {
		this.uploadSerialNoList = uploadSerialNoList;
	}
	@Transient
	public String getUploadImagePath() {
		return uploadImagePath;
	}

	public void setUploadImagePath(String uploadImagePath) {
		this.uploadImagePath = uploadImagePath;
	}
	@Transient
	public String getUploadSavePath() {
		return uploadSavePath;
	}

	public void setUploadSavePath(String uploadSavePath) {
		this.uploadSavePath = uploadSavePath;
	}

}
