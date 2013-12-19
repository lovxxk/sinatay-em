package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeProductPictureDetail
 */
@Entity
@Table(name = "GE_PRODUCT_PICTURE_DETAIL")
public class GeProductPictureDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����detailid */
	private String detailid;

	/** ����picturename */
	private String picturename;

	/** ����picturedesc */
	private String picturedesc;

	/** ���Ա�־λ */
	private String flag;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ����geProductPictures */
	private List<GeProductPicture> geProductPictures = new ArrayList<GeProductPicture>(
			0);
	//ҵ����ʹ�� start
	//ƻ���ϴ�ͼƬstart
	private List<File> uploadPicture;//�ϴ���ͼƬ�ļ�
	private List<String> uploadPictureFileName;//�ϴ���ͼƬ������
	private List<String> uploadPictureContentType;//�ϴ���ͼƬ����
	private List<String> uploadSerialNoList;//�ϴ��ļ���Ӧ�����к�
	private String uploadImagePath;//Ҫ�ϴ���ͼƬ������λ��
	private String uploadSavePath;//Ҫ�����ͼƬ
	//ƻ���ϴ�ͼƬend
	//ҵ����ʹ�� end
	/**
	 * ��GeProductPictureDetail��Ĭ�Ϲ��췽��
	 */
	public GeProductPictureDetail() {
	}

	/**
	 * ����detailid��getter����
	 */
	@Id
	@Column(name = "DETAILID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getDetailid() {
		return this.detailid;
	}

	/**
	 * ����detailid��setter����
	 */
	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	/**
	 * ����picturename��getter����
	 */

	@Column(name = "PICTURENAME")
	public String getPicturename() {
		return this.picturename;
	}

	/**
	 * ����picturename��setter����
	 */
	public void setPicturename(String picturename) {
		this.picturename = picturename;
	}

	/**
	 * ����picturedesc��getter����
	 */

	@Column(name = "PICTUREDESC")
	public String getPicturedesc() {
		return this.picturedesc;
	}

	/**
	 * ����picturedesc��setter����
	 */
	public void setPicturedesc(String picturedesc) {
		this.picturedesc = picturedesc;
	}

	/**
	 * ���Ա�־λ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�־λ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	//@Temporal(TemporalType.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ����geProductPictures��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductPictureDetail")
	public List<GeProductPicture> getGeProductPictures() {
		return this.geProductPictures;
	}

	/**
	 * ����geProductPictures��setter����
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
