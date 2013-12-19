package cn.com.sinosoft.ebusiness.marketing.domain;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO类GeDiscountManage
 */
@Entity
@Table(name = "GE_DISCOUNT_MANAGE")
public class GeDiscountManage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性discountid */
	private String discountid;

	/** 属性电子商务产品ID */
	private String eid;

	/** 属性discountstartdate */
	private Date discountstartdate;

	/** 属性discountenddate */
	private Date discountenddate;

	/** 属性总折扣率 */
	private String discount;

	/** 属性activitysiteslogan */
	private String activitysiteslogan;

	/** 属性pictureone */
	private String pictureone;

	/** 属性picturetwo */
	private String picturetwo;

	/** 属性picturethree */
	private String picturethree;

	/** 属性picturefour */
	private String picturefour;

	/** 属性picturefive */
	private String picturefive;

	/** 属性picturesix */
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
	 * 类GeDiscountManage的默认构造方法
	 */
	public GeDiscountManage() {
	}

	/**
	 * 属性discountid的getter方法
	 */
	@Id
	@Column(name = "DISCOUNTID")
	public String getDiscountid() {
		return this.discountid;
	}

	/**
	 * 属性discountid的setter方法
	 */
	public void setDiscountid(String discountid) {
		this.discountid = discountid;
	}

	/**
	 * 属性电子商务产品ID的getter方法
	 */

	@Column(name = "EID")
	public String getEid() {
		return this.eid;
	}

	/**
	 * 属性电子商务产品ID的setter方法
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}

	/**
	 * 属性discountstartdate的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DISCOUNTSTARTDATE")
	public Date getDiscountstartdate() {
		return this.discountstartdate;
	}

	/**
	 * 属性discountstartdate的setter方法
	 */
	public void setDiscountstartdate(Date discountstartdate) {
		this.discountstartdate = discountstartdate;
	}

	/**
	 * 属性discountenddate的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DISCOUNTENDDATE")
	public Date getDiscountenddate() {
		return this.discountenddate;
	}

	/**
	 * 属性discountenddate的setter方法
	 */
	public void setDiscountenddate(Date discountenddate) {
		this.discountenddate = discountenddate;
	}

	/**
	 * 属性总折扣率的getter方法
	 */

	@Column(name = "DISCOUNT")
	public String getDiscount() {
		return this.discount;
	}

	/**
	 * 属性总折扣率的setter方法
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	/**
	 * 属性activitysiteslogan的getter方法
	 */

	@Column(name = "ACTIVITYSITESLOGAN")
	public String getActivitysiteslogan() {
		return this.activitysiteslogan;
	}

	/**
	 * 属性activitysiteslogan的setter方法
	 */
	public void setActivitysiteslogan(String activitysiteslogan) {
		this.activitysiteslogan = activitysiteslogan;
	}

	/**
	 * 属性pictureone的getter方法
	 */

	@Column(name = "PICTUREONE")
	public String getPictureone() {
		return this.pictureone;
	}

	/**
	 * 属性pictureone的setter方法
	 */
	public void setPictureone(String pictureone) {
		this.pictureone = pictureone;
	}

	/**
	 * 属性picturetwo的getter方法
	 */

	@Column(name = "PICTURETWO")
	public String getPicturetwo() {
		return this.picturetwo;
	}

	/**
	 * 属性picturetwo的setter方法
	 */
	public void setPicturetwo(String picturetwo) {
		this.picturetwo = picturetwo;
	}

	/**
	 * 属性picturethree的getter方法
	 */

	@Column(name = "PICTURETHREE")
	public String getPicturethree() {
		return this.picturethree;
	}

	/**
	 * 属性picturethree的setter方法
	 */
	public void setPicturethree(String picturethree) {
		this.picturethree = picturethree;
	}

	/**
	 * 属性picturefour的getter方法
	 */

	@Column(name = "PICTUREFOUR")
	public String getPicturefour() {
		return this.picturefour;
	}

	/**
	 * 属性picturefour的setter方法
	 */
	public void setPicturefour(String picturefour) {
		this.picturefour = picturefour;
	}

	/**
	 * 属性picturefive的getter方法
	 */

	@Column(name = "PICTUREFIVE")
	public String getPicturefive() {
		return this.picturefive;
	}

	/**
	 * 属性picturefive的setter方法
	 */
	public void setPicturefive(String picturefive) {
		this.picturefive = picturefive;
	}

	/**
	 * 属性picturesix的getter方法
	 */

	@Column(name = "PICTURESIX")
	public String getPicturesix() {
		return this.picturesix;
	}

	/**
	 * 属性picturesix的setter方法
	 */
	public void setPicturesix(String picturesix) {
		this.picturesix = picturesix;
	}

}
