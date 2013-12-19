package cn.com.sinosoft.ebusiness.service.user.common.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeCustomerFavorite
 */
@Entity
@Table(name = "GE_CUSTOMER_FAVORITE")
public class GeCustomerFavorite implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ժ�������� */
	private GeCustomerFavoriteId id;

	/** �����û����� */
	private String userType;

	/** �����ղ�ʱ�� */
	private Date submitDate;
	
	/** ���Ա�ʶλ */
	private String flag;

	/**����**/
    private String productName;
    
    /**�Ƿ��������ж��Ƿ���ʾ���߹���**/
    private String isNetSale;
    
    /**productSection**/
    private String productSection;
    
    private String picture;
    @Transient
    private String businessArea;
    
    @Transient
    private String netSaleProductType;
	/**
	 * ��GeCustomerFavorite��Ĭ�Ϲ��췽��
	 */
	public GeCustomerFavorite() {
	}

	/**
	 * ���Ժ�������ŵ�getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userID", column = @Column(name = "USERID")),
			@AttributeOverride(name = "eid", column = @Column(name = "EID")) })
	public GeCustomerFavoriteId getId() {
		return this.id;
	}

	/**
	 * ���Ժ�������ŵ�setter����
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
	 * �����û����͵�getter����
	 */

	@Column(name = "USERTYPE")
	public String getUserType() {
		return this.userType;
	}

	/**
	 * �����û����͵�setter����
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * �����ղ�ʱ���getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMITDATE")
	public Date getSubmitDate() {
		return this.submitDate;
	}

	/**
	 * �����ղ�ʱ���setter����
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	/**
	 * ���Ա�ʶλ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�ʶλ��setter����
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
