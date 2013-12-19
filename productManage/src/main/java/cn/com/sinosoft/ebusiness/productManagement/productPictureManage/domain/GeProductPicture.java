package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类GeProductPicture
 */
@Entity
@Table(name = "GE_PRODUCT_PICTURE")
public class GeProductPicture implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性pictureid */
	private String pictureid;

	/** 属性geProductPictureDetail */
	private GeProductPictureDetail geProductPictureDetail;

	/** 属性pictureurl */
	private String pictureurl;

	/** 属性序号 */
	private String serialNo;
	
	//用于业务上使用的 start
	private String nooryes;//no 是没有图片 yes是有图片
	//用于业务上使用的 end


	/**
	 * 类GeProductPicture的默认构造方法
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
	 * 属性pictureid的getter方法
	 */
	@Id
	@Column(name = "PICTUREID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getPictureid() {
		return this.pictureid;
	}

	/**
	 * 属性pictureid的setter方法
	 */
	public void setPictureid(String pictureid) {
		this.pictureid = pictureid;
	}

	/**
	 * 属性geProductPictureDetail的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAILID")
	public GeProductPictureDetail getGeProductPictureDetail() {
		return this.geProductPictureDetail;
	}

	/**
	 * 属性geProductPictureDetail的setter方法
	 */
	public void setGeProductPictureDetail(
			GeProductPictureDetail geProductPictureDetail) {
		this.geProductPictureDetail = geProductPictureDetail;
	}

	/**
	 * 属性pictureurl的getter方法
	 */

	@Column(name = "PICTUREURL")
	public String getPictureurl() {
		return this.pictureurl;
	}

	/**
	 * 属性pictureurl的setter方法
	 */
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	/**
	 * 属性序号的getter方法
	 */

	@Column(name = "SERIALNO")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

}
