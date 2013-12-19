package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeProductPicture
 */
@Entity
@Table(name = "GE_PRODUCT_PICTURE")
public class GeProductPicture implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����pictureid */
	private String pictureid;

	/** ����geProductPictureDetail */
	private GeProductPictureDetail geProductPictureDetail;

	/** ����pictureurl */
	private String pictureurl;

	/** ������� */
	private String serialNo;
	
	//����ҵ����ʹ�õ� start
	private String nooryes;//no ��û��ͼƬ yes����ͼƬ
	//����ҵ����ʹ�õ� end


	/**
	 * ��GeProductPicture��Ĭ�Ϲ��췽��
	 */
	public GeProductPicture() {
	}
	@Transient
	public String getNooryes() {
		return nooryes;
	}

	public void setNooryes(String nooryes) {
		this.nooryes = nooryes;
	}

	/**
	 * ����pictureid��getter����
	 */
	@Id
	@Column(name = "PICTUREID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getPictureid() {
		return this.pictureid;
	}

	/**
	 * ����pictureid��setter����
	 */
	public void setPictureid(String pictureid) {
		this.pictureid = pictureid;
	}

	/**
	 * ����geProductPictureDetail��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAILID")
	public GeProductPictureDetail getGeProductPictureDetail() {
		return this.geProductPictureDetail;
	}

	/**
	 * ����geProductPictureDetail��setter����
	 */
	public void setGeProductPictureDetail(
			GeProductPictureDetail geProductPictureDetail) {
		this.geProductPictureDetail = geProductPictureDetail;
	}

	/**
	 * ����pictureurl��getter����
	 */

	@Column(name = "PICTUREURL")
	public String getPictureurl() {
		return this.pictureurl;
	}

	/**
	 * ����pictureurl��setter����
	 */
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	/**
	 * ������ŵ�getter����
	 */

	@Column(name = "SERIALNO")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

}
