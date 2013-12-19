package cn.com.sinosoft.ebusiness.marketing.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO类GeActivitiesConfig
 */
@Entity
@Table(name = "GE_ACTIVITIES_CONFIG")
public class GeActivitiesConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性productid */
	private String productid;

	/** 属性产品名称 */
	private String productName;

	/** 属性图片数量 */
	private String pictureCount;
	
	/** 属性产品分类01车02非车03卡 对应gecodeType 表中的ProductType类型 */
	private String productType;
	
	/** 属性是否有效标志1：有效0：无效 */
	private String validInd;
	
	/**
	 * 类GeActivitiesConfig的默认构造方法
	 */
	public GeActivitiesConfig() {
	}

	/**
	 * 属性productid的getter方法
	 */
	@Id
	@Column(name = "PRODUCTID")
	public String getProductid() {
		return this.productid;
	}

	/**
	 * 属性productid的setter方法
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}

	/**
	 * 属性产品名称的getter方法
	 */

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return this.productName;
	}

	/**
	 * 属性产品名称的setter方法
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 属性图片数量的getter方法
	 */

	@Column(name = "PICTURECOUNT")
	public String getPictureCount() {
		return this.pictureCount;
	}

	/**
	 * 属性图片数量的setter方法
	 */
	public void setPictureCount(String pictureCount) {
		this.pictureCount = pictureCount;
	}
	
	/**
	 * 属性产品分类的setter方法
	 */
	@Column(name = "PRODUCTTYPE")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 属性是否有效的setter方法
	 */
	@Column(name = "VALIDIND")
	public String getValidInd() {
		return validInd;
	}

	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

}
