package cn.com.sinosoft.ebusiness.marketing.domain;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO��GeDiscountManage
 */
@Entity
@Table(name = "GE_DISCOUNT_MANAGE")
public class GeDiscountManage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����discountid */
	private String discountid;

	/** ���Ե��������ƷID */
	private String eid;

	/** ����discountstartdate */
	private Date discountstartdate;

	/** ����discountenddate */
	private Date discountenddate;

	/** �������ۿ��� */
	private String discount;

	/** ����activitysiteslogan */
	private String activitysiteslogan;

	/** ����pictureone */
	private String pictureone;

	/** ����picturetwo */
	private String picturetwo;

	/** ����picturethree */
	private String picturethree;

	/** ����picturefour */
	private String picturefour;

	/** ����picturefive */
	private String picturefive;

	/** ����picturesix */
	private String picturesix;
	
	private String discountname;
	
	private String discountcontent;

	@Column(name = "DISCOUNTNAME")
	public String getDiscountname() {
		return discountname;
	}

	public void setDiscountname(String discountname) {
		this.discountname = discountname;
	}
	
	@Column(name = "DISCOUNTCONTENT")
	public String getDiscountcontent() {
		return discountcontent;
	}

	public void setDiscountcontent(String discountcontent) {
		this.discountcontent = discountcontent;
	}

	/**
	 * ��GeDiscountManage��Ĭ�Ϲ��췽��
	 */
	public GeDiscountManage() {
	}

	/**
	 * ����discountid��getter����
	 */
	@Id
	@Column(name = "DISCOUNTID")
	public String getDiscountid() {
		return this.discountid;
	}

	/**
	 * ����discountid��setter����
	 */
	public void setDiscountid(String discountid) {
		this.discountid = discountid;
	}

	/**
	 * ���Ե��������ƷID��getter����
	 */

	@Column(name = "EID")
	public String getEid() {
		return this.eid;
	}

	/**
	 * ���Ե��������ƷID��setter����
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}

	/**
	 * ����discountstartdate��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DISCOUNTSTARTDATE")
	public Date getDiscountstartdate() {
		return this.discountstartdate;
	}

	/**
	 * ����discountstartdate��setter����
	 */
	public void setDiscountstartdate(Date discountstartdate) {
		this.discountstartdate = discountstartdate;
	}

	/**
	 * ����discountenddate��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DISCOUNTENDDATE")
	public Date getDiscountenddate() {
		return this.discountenddate;
	}

	/**
	 * ����discountenddate��setter����
	 */
	public void setDiscountenddate(Date discountenddate) {
		this.discountenddate = discountenddate;
	}

	/**
	 * �������ۿ��ʵ�getter����
	 */

	@Column(name = "DISCOUNT")
	public String getDiscount() {
		return this.discount;
	}

	/**
	 * �������ۿ��ʵ�setter����
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	/**
	 * ����activitysiteslogan��getter����
	 */

	@Column(name = "ACTIVITYSITESLOGAN")
	public String getActivitysiteslogan() {
		return this.activitysiteslogan;
	}

	/**
	 * ����activitysiteslogan��setter����
	 */
	public void setActivitysiteslogan(String activitysiteslogan) {
		this.activitysiteslogan = activitysiteslogan;
	}

	/**
	 * ����pictureone��getter����
	 */

	@Column(name = "PICTUREONE")
	public String getPictureone() {
		return this.pictureone;
	}

	/**
	 * ����pictureone��setter����
	 */
	public void setPictureone(String pictureone) {
		this.pictureone = pictureone;
	}

	/**
	 * ����picturetwo��getter����
	 */

	@Column(name = "PICTURETWO")
	public String getPicturetwo() {
		return this.picturetwo;
	}

	/**
	 * ����picturetwo��setter����
	 */
	public void setPicturetwo(String picturetwo) {
		this.picturetwo = picturetwo;
	}

	/**
	 * ����picturethree��getter����
	 */

	@Column(name = "PICTURETHREE")
	public String getPicturethree() {
		return this.picturethree;
	}

	/**
	 * ����picturethree��setter����
	 */
	public void setPicturethree(String picturethree) {
		this.picturethree = picturethree;
	}

	/**
	 * ����picturefour��getter����
	 */

	@Column(name = "PICTUREFOUR")
	public String getPicturefour() {
		return this.picturefour;
	}

	/**
	 * ����picturefour��setter����
	 */
	public void setPicturefour(String picturefour) {
		this.picturefour = picturefour;
	}

	/**
	 * ����picturefive��getter����
	 */

	@Column(name = "PICTUREFIVE")
	public String getPicturefive() {
		return this.picturefive;
	}

	/**
	 * ����picturefive��setter����
	 */
	public void setPicturefive(String picturefive) {
		this.picturefive = picturefive;
	}

	/**
	 * ����picturesix��getter����
	 */

	@Column(name = "PICTURESIX")
	public String getPicturesix() {
		return this.picturesix;
	}

	/**
	 * ����picturesix��setter����
	 */
	public void setPicturesix(String picturesix) {
		this.picturesix = picturesix;
	}

}
