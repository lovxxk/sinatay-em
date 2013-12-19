package cn.com.sinosoft.ebusiness.service.user.common.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * POJO类GeCustomerFavorite
 */
@Entity
@Table(name = "GE_CUSTOMER_FAVORITE")
public class GeCustomerFavorite implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性黑名单编号 */
	private GeCustomerFavoriteId id;

	/** 属性用户类型 */
	private String userType;

	/** 属性收藏时间 */
	private Date submitDate;
	
	/** 属性标识位 */
	private String flag;

	/**名称**/
    private String productName;
    
    /**是否网销，判断是否显示在线购买**/
    private String isNetSale;
    
    /**productSection**/
    private String productSection;
    
    private String picture;
    @Transient
    private String businessArea;
    
    @Transient
    private String netSaleProductType;
	/**
	 * 类GeCustomerFavorite的默认构造方法
	 */
	public GeCustomerFavorite() {
	}

	/**
	 * 属性黑名单编号的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userID", column = @Column(name = "USERID")),
			@AttributeOverride(name = "eid", column = @Column(name = "EID")) })
	public GeCustomerFavoriteId getId() {
		return this.id;
	}

	/**
	 * 属性黑名单编号的setter方法
	 */
	public void setId(GeCustomerFavoriteId id) {
		this.id = id;
	}

	@Transient
	public String getProductSection() {
		return productSection;
	}

	public void setProductSection(String productSection) {
		this.productSection = productSection;
	}

	@Transient
	public String getIsNetSale() {
		return isNetSale;
	}

	public void setIsNetSale(String isNetSale) {
		this.isNetSale = isNetSale;
	}

	/**
	 * 属性用户类型的getter方法
	 */

	@Column(name = "USERTYPE")
	public String getUserType() {
		return this.userType;
	}

	/**
	 * 属性用户类型的setter方法
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * 属性收藏时间的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMITDATE")
	public Date getSubmitDate() {
		return this.submitDate;
	}

	/**
	 * 属性收藏时间的setter方法
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	/**
	 * 属性标识位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标识位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	 @Transient
	public String getProductName() {
		return productName;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Transient
	public String getPicture() {
		return picture;
	}
	@Transient
	public String getBusinessArea() {
		return businessArea;
	}
	
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	
	@Transient
	public String getNetSaleProductType() {
		return netSaleProductType;
	}

	public void setNetSaleProductType(String netSaleProductType) {
		this.netSaleProductType = netSaleProductType;
	}
	
}
