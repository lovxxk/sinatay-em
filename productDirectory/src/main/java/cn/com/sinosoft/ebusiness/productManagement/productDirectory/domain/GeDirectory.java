package cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * POJO类GeDirectory
 */
@Entity
@Table(name = "GE_DIRECTORY")
public class GeDirectory implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性电子商务产品ID */
	private String eid;

	/** 属性是否网销 */
	private String isNetSale;

	/** 属性产品类型 */
	private String productType;

	/** 属性产品代码/险种代码 */
	private String coreProductCode;

	/** 属性产品名称 */
	private String productName;

	/** 属性产品简称 */
	private String coreProductSimpleName;

	/** 属性保险期间 */
	private String insurancePeriod;

	/** 属性保险期间类型 */
	private String insurancePeriodType;

	/** 属性最低年龄 */
	private String lowestAge;

	/** 属性最高年龄 */
	private String highestAge;

	/** 属性销售渠道 */
	private String saleChannel;

	/** 属性业务数据域 */
	private String businessArea;

	/** 属性标的类型 */
	private String itemType;

	/** 属性险种期限 */
	private String riskLimit;

	/** 属性承保方式 */
	private String acceptInsurance;

	/** 属性产品上下架 */
	private String isProductShelf;

	/** 属性小图片one */
	private String smallPictureOne;

	/** 属性小图片two */
	private String smallPictureTwo;

	/** 属性中图片one */
	private String middlePictureOne;

	/** 属性中图片two */
	private String middlePictureTwo;

	/** 属性大图片one */
	private String bigPictureOne;

	/** 属性大图片Two */
	private String bigPictureTwo;

	/** 属性产品简介 */
	private String productSummary;

	/** 属性产品特色 */
	private String productFeature;

	/** 属性产品条款描述 */
	private String productArticleDesc;

	/** 属性保费区间 */
	private String premiumRange;

	/** 属性保险金额描述 */
	private String insuranceAmountDesc;

	/** 属性保险期间描述 */
	private String insurancePeriodDesc;

	/** 属性缴费方式 */
	private String payType;

	/** 属性投保范围 */
	private String insureRange;

	/** 属性投保年龄 */
	private String insureAge;

	/** 属性承保对象 */
	private String underwritingObject;

	/** 属性适用人群 */
	private String applyPeople;

	/** 属性购买须知 */
	private String purchaseNotes;

	/** 属性保障利益 */
	private String securityInterest;

	/** 属性产品推荐评分 */
	private String productRecommend;

	/** 属性发布日期 */
	private Date publishDate;

	/** 属性停售日期 */
	private Date stopDate;

	/** 属性操作员编号 */
	private String operatorID;

	/** 属性创建时间 */
	private Date createDate;

	/** 属性更新时间 */
	private Date updateDate;

	/** 属性extra1 */
	private String extra1;

	/** 属性extra2 */
	private String extra2;

	/** 属性extra3 */
	private String extra3;

	/** 属性extra4 */
	private String extra4;

	/** 属性extra5 */
	private String extra5;

	/** 属性premium */
	private BigDecimal premium;

	/** 属性产品业务类型 01车02非车03卡 */
	private String netSaleProductType;

	/** 属性产品栏目 01个人 02企业 */
	private String productSection;

	/** 属性产品试算代码 */
	private String productQuoteCode;
	
	/** 非持久化属性成员公司 */
	private String companyName;

	/** 属性geProductCorrelationsForproduct */
	private List<GeProductCorrelation> geProductCorrelationsForproduct = new ArrayList<GeProductCorrelation>(
			0);

	/** 属性geDirectoryAttributeRelates */
	private List<GeDirectoryAttributeRelate> geDirectoryAttributeRelates = new ArrayList<GeDirectoryAttributeRelate>(
			0);

	/** 属性geProductCorrelationsForrecommendProduct */
	private List<GeProductCorrelation> geProductCorrelationsForrecommendProduct = new ArrayList<GeProductCorrelation>(
			0);

	/**
	 * 类GeDirectory的默认构造方法
	 */
	public GeDirectory() {
	}

	/**
	 * 属性电子商务产品ID的getter方法
	 */
	@Id
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
	 * 属性是否网销的getter方法
	 */

	@Column(name = "ISNETSALE")
	public String getIsNetSale() {
		return this.isNetSale;
	}
	/**
	 * 属性是否网销的setter方法
	 */
	public void setIsNetSale(String isNetSale) {
		this.isNetSale = isNetSale;
	}
	/**
	 * 属性产品类型的getter方法
	 */

	@Column(name = "PRODUCTTYPE")
	public String getProductType() {
		return this.productType;
	}
	/**
	 * 属性产品类型的setter方法
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 属性产品代码/险种代码的getter方法
	 */

	@Column(name = "COREPRODUCTCODE")
	public String getCoreProductCode() {
		return this.coreProductCode;
	}
	/**
	 * 属性产品代码/险种代码的setter方法
	 */
	public void setCoreProductCode(String coreProductCode) {
		this.coreProductCode = coreProductCode;
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
	 * 属性产品简称的getter方法
	 */

	@Column(name = "COREPRODUCTSIMPLENAME")
	public String getCoreProductSimpleName() {
		return this.coreProductSimpleName;
	}
	/**
	 * 属性产品简称的setter方法
	 */
	public void setCoreProductSimpleName(String coreProductSimpleName) {
		this.coreProductSimpleName = coreProductSimpleName;
	}
	/**
	 * 属性保险期间的getter方法
	 */

	@Column(name = "INSURANCEPERIOD")
	public String getInsurancePeriod() {
		return this.insurancePeriod;
	}
	/**
	 * 属性保险期间的setter方法
	 */
	public void setInsurancePeriod(String insurancePeriod) {
		this.insurancePeriod = insurancePeriod;
	}
	/**
	 * 属性保险期间类型的getter方法
	 */

	@Column(name = "INSURANCEPERIODTYPE")
	public String getInsurancePeriodType() {
		return this.insurancePeriodType;
	}
	/**
	 * 属性保险期间类型的setter方法
	 */
	public void setInsurancePeriodType(String insurancePeriodType) {
		this.insurancePeriodType = insurancePeriodType;
	}
	/**
	 * 属性最低年龄的getter方法
	 */

	@Column(name = "LOWESTAGE")
	public String getLowestAge() {
		return this.lowestAge;
	}
	/**
	 * 属性最低年龄的setter方法
	 */
	public void setLowestAge(String lowestAge) {
		this.lowestAge = lowestAge;
	}
	/**
	 * 属性最高年龄的getter方法
	 */

	@Column(name = "HIGHESTAGE")
	public String getHighestAge() {
		return this.highestAge;
	}
	/**
	 * 属性最高年龄的setter方法
	 */
	public void setHighestAge(String highestAge) {
		this.highestAge = highestAge;
	}
	/**
	 * 属性销售渠道的getter方法
	 */

	@Column(name = "SALECHANNEL")
	public String getSaleChannel() {
		return this.saleChannel;
	}
	/**
	 * 属性销售渠道的setter方法
	 */
	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}
	/**
	 * 属性业务数据域的getter方法
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}
	/**
	 * 属性业务数据域的setter方法
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	/**
	 * 属性标的类型的getter方法
	 */

	@Column(name = "ITEMTYPE")
	public String getItemType() {
		return this.itemType;
	}
	/**
	 * 属性标的类型的setter方法
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * 属性险种期限的getter方法
	 */

	@Column(name = "RISKLIMIT")
	public String getRiskLimit() {
		return this.riskLimit;
	}
	/**
	 * 属性险种期限的setter方法
	 */
	public void setRiskLimit(String riskLimit) {
		this.riskLimit = riskLimit;
	}
	/**
	 * 属性承保方式的getter方法
	 */

	@Column(name = "ACCEPTINSURANCE")
	public String getAcceptInsurance() {
		return this.acceptInsurance;
	}
	/**
	 * 属性承保方式的setter方法
	 */
	public void setAcceptInsurance(String acceptInsurance) {
		this.acceptInsurance = acceptInsurance;
	}
	/**
	 * 属性产品上下架的getter方法
	 */

	@Column(name = "ISPRODUCTSHELF")
	public String getIsProductShelf() {
		return this.isProductShelf;
	}
	/**
	 * 属性产品上下架的setter方法
	 */
	public void setIsProductShelf(String isProductShelf) {
		this.isProductShelf = isProductShelf;
	}
	/**
	 * 属性小图片one的getter方法
	 */

	@Column(name = "SMALLPICTUREONE")
	public String getSmallPictureOne() {
		return this.smallPictureOne;
	}
	/**
	 * 属性小图片one的setter方法
	 */
	public void setSmallPictureOne(String smallPictureOne) {
		this.smallPictureOne = smallPictureOne;
	}
	/**
	 * 属性小图片two的getter方法
	 */

	@Column(name = "SMALLPICTURETWO")
	public String getSmallPictureTwo() {
		return this.smallPictureTwo;
	}
	/**
	 * 属性小图片two的setter方法
	 */
	public void setSmallPictureTwo(String smallPictureTwo) {
		this.smallPictureTwo = smallPictureTwo;
	}
	/**
	 * 属性中图片one的getter方法
	 */

	@Column(name = "MIDDLEPICTUREONE")
	public String getMiddlePictureOne() {
		return this.middlePictureOne;
	}
	/**
	 * 属性中图片one的setter方法
	 */
	public void setMiddlePictureOne(String middlePictureOne) {
		this.middlePictureOne = middlePictureOne;
	}
	/**
	 * 属性中图片two的getter方法
	 */

	@Column(name = "MIDDLEPICTURETWO")
	public String getMiddlePictureTwo() {
		return this.middlePictureTwo;
	}
	/**
	 * 属性中图片two的setter方法
	 */
	public void setMiddlePictureTwo(String middlePictureTwo) {
		this.middlePictureTwo = middlePictureTwo;
	}
	/**
	 * 属性大图片one的getter方法
	 */

	@Column(name = "BIGPICTUREONE")
	public String getBigPictureOne() {
		return this.bigPictureOne;
	}
	/**
	 * 属性大图片one的setter方法
	 */
	public void setBigPictureOne(String bigPictureOne) {
		this.bigPictureOne = bigPictureOne;
	}
	/**
	 * 属性大图片Two的getter方法
	 */

	@Column(name = "BIGPICTURETWO")
	public String getBigPictureTwo() {
		return this.bigPictureTwo;
	}
	/**
	 * 属性大图片Two的setter方法
	 */
	public void setBigPictureTwo(String bigPictureTwo) {
		this.bigPictureTwo = bigPictureTwo;
	}
	/**
	 * 属性产品简介的getter方法
	 */

	@Column(name = "PRODUCTSUMMARY")
	public String getProductSummary() {
		return this.productSummary;
	}
	/**
	 * 属性产品简介的setter方法
	 */
	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}
	/**
	 * 属性产品特色的getter方法
	 */

	@Column(name = "PRODUCTFEATURE")
	public String getProductFeature() {
		return this.productFeature;
	}
	/**
	 * 属性产品特色的setter方法
	 */
	public void setProductFeature(String productFeature) {
		this.productFeature = productFeature;
	}
	/**
	 * 属性产品条款描述的getter方法
	 */

	@Column(name = "PRODUCTARTICLEDESC")
	public String getProductArticleDesc() {
		return this.productArticleDesc;
	}
	/**
	 * 属性产品条款描述的setter方法
	 */
	public void setProductArticleDesc(String productArticleDesc) {
		this.productArticleDesc = productArticleDesc;
	}
	/**
	 * 属性保费区间的getter方法
	 */

	@Column(name = "PREMIUMRANGE")
	public String getPremiumRange() {
		return this.premiumRange;
	}
	/**
	 * 属性保费区间的setter方法
	 */
	public void setPremiumRange(String premiumRange) {
		this.premiumRange = premiumRange;
	}
	/**
	 * 属性保险金额描述的getter方法
	 */

	@Column(name = "INSURANCEAMOUNTDESC")
	public String getInsuranceAmountDesc() {
		return this.insuranceAmountDesc;
	}
	/**
	 * 属性保险金额描述的setter方法
	 */
	public void setInsuranceAmountDesc(String insuranceAmountDesc) {
		this.insuranceAmountDesc = insuranceAmountDesc;
	}
	/**
	 * 属性保险期间描述的getter方法
	 */

	@Column(name = "INSURANCEPERIODDESC")
	public String getInsurancePeriodDesc() {
		return this.insurancePeriodDesc;
	}
	/**
	 * 属性保险期间描述的setter方法
	 */
	public void setInsurancePeriodDesc(String insurancePeriodDesc) {
		this.insurancePeriodDesc = insurancePeriodDesc;
	}
	/**
	 * 属性缴费方式的getter方法
	 */

	@Column(name = "PAYTYPE")
	public String getPayType() {
		return this.payType;
	}
	/**
	 * 属性缴费方式的setter方法
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 属性投保范围的getter方法
	 */

	@Column(name = "INSURERANGE")
	public String getInsureRange() {
		return this.insureRange;
	}
	/**
	 * 属性投保范围的setter方法
	 */
	public void setInsureRange(String insureRange) {
		this.insureRange = insureRange;
	}
	/**
	 * 属性投保年龄的getter方法
	 */

	@Column(name = "INSUREAGE")
	public String getInsureAge() {
		return this.insureAge;
	}
	/**
	 * 属性投保年龄的setter方法
	 */
	public void setInsureAge(String insureAge) {
		this.insureAge = insureAge;
	}
	/**
	 * 属性承保对象的getter方法
	 */

	@Column(name = "UNDERWRITINGOBJECT")
	public String getUnderwritingObject() {
		return this.underwritingObject;
	}
	/**
	 * 属性承保对象的setter方法
	 */
	public void setUnderwritingObject(String underwritingObject) {
		this.underwritingObject = underwritingObject;
	}
	/**
	 * 属性适用人群的getter方法
	 */

	@Column(name = "APPLYPEOPLE")
	public String getApplyPeople() {
		return this.applyPeople;
	}
	/**
	 * 属性适用人群的setter方法
	 */
	public void setApplyPeople(String applyPeople) {
		this.applyPeople = applyPeople;
	}
	/**
	 * 属性购买须知的getter方法
	 */

	@Column(name = "PURCHASENOTES")
	public String getPurchaseNotes() {
		return this.purchaseNotes;
	}
	/**
	 * 属性购买须知的setter方法
	 */
	public void setPurchaseNotes(String purchaseNotes) {
		this.purchaseNotes = purchaseNotes;
	}
	/**
	 * 属性保障利益的getter方法
	 */

	@Column(name = "SECURITYINTEREST")
	public String getSecurityInterest() {
		return this.securityInterest;
	}
	/**
	 * 属性保障利益的setter方法
	 */
	public void setSecurityInterest(String securityInterest) {
		this.securityInterest = securityInterest;
	}
	/**
	 * 属性产品推荐评分的getter方法
	 */

	@Column(name = "PRODUCTRECOMMEND")
	public String getProductRecommend() {
		return this.productRecommend;
	}
	/**
	 * 属性产品推荐评分的setter方法
	 */
	public void setProductRecommend(String productRecommend) {
		this.productRecommend = productRecommend;
	}
	/**
	 * 属性发布日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PUBLISHDATE")
	public Date getPublishDate() {
		return this.publishDate;
	}
	/**
	 * 属性发布日期的setter方法
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	/**
	 * 属性停售日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "STOPDATE")
	public Date getStopDate() {
		return this.stopDate;
	}
	/**
	 * 属性停售日期的setter方法
	 */
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	/**
	 * 属性操作员编号的getter方法
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}
	/**
	 * 属性操作员编号的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATEDATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}
	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 属性extra1的getter方法
	 */

	@Column(name = "EXTRA1")
	public String getExtra1() {
		return this.extra1;
	}
	/**
	 * 属性extra1的setter方法
	 */
	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}
	/**
	 * 属性extra2的getter方法
	 */

	@Column(name = "EXTRA2")
	public String getExtra2() {
		return this.extra2;
	}
	/**
	 * 属性extra2的setter方法
	 */
	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}
	/**
	 * 属性extra3的getter方法
	 */

	@Column(name = "EXTRA3")
	public String getExtra3() {
		return this.extra3;
	}
	/**
	 * 属性extra3的setter方法
	 */
	public void setExtra3(String extra3) {
		this.extra3 = extra3;
	}
	/**
	 * 属性extra4的getter方法
	 */

	@Column(name = "EXTRA4")
	public String getExtra4() {
		return this.extra4;
	}
	/**
	 * 属性extra4的setter方法
	 */
	public void setExtra4(String extra4) {
		this.extra4 = extra4;
	}
	/**
	 * 属性extra5的getter方法
	 */

	@Column(name = "EXTRA5")
	public String getExtra5() {
		return this.extra5;
	}
	/**
	 * 属性extra5的setter方法
	 */
	public void setExtra5(String extra5) {
		this.extra5 = extra5;
	}
	/**
	 * 属性premium的getter方法
	 */

	@Column(name = "PREMIUM")
	public BigDecimal getPremium() {
		return this.premium;
	}
	/**
	 * 属性premium的setter方法
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	/**
	 * 属性产品业务类型 01车02非车03卡的getter方法
	 */

	@Column(name = "NETSALEPRODUCTTYPE")
	public String getNetSaleProductType() {
		return this.netSaleProductType;
	}
	/**
	 * 属性产品业务类型 01车02非车03卡的setter方法
	 */
	public void setNetSaleProductType(String netSaleProductType) {
		this.netSaleProductType = netSaleProductType;
	}
	/**
	 * 属性产品栏目 01个人 02企业的getter方法
	 */

	@Column(name = "PRODUCTSECTION")
	public String getProductSection() {
		return this.productSection;
	}
	/**
	 * 属性产品栏目 01个人 02企业的setter方法
	 */
	public void setProductSection(String productSection) {
		this.productSection = productSection;
	}
	/**
	 * 属性产品试算代码的getter方法
	 */

	@Column(name = "PRODUCTQUOTECODE")
	public String getProductQuoteCode() {
		return this.productQuoteCode;
	}
	/**
	 * 属性产品试算代码的setter方法
	 */
	public void setProductQuoteCode(String productQuoteCode) {
		this.productQuoteCode = productQuoteCode;
	}
	/**
	 * 属性geProductCorrelationsForproduct的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectoryByproduct")
	public List<GeProductCorrelation> getGeProductCorrelationsForproduct() {
		return this.geProductCorrelationsForproduct;
	}
	/**
	 * 属性geProductCorrelationsForproduct的setter方法
	 */
	public void setGeProductCorrelationsForproduct(
			List<GeProductCorrelation> geProductCorrelationsForproduct) {
		this.geProductCorrelationsForproduct = geProductCorrelationsForproduct;
	}
	/**
	 * 属性geDirectoryAttributeRelates的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectory")
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
	 * 属性geProductCorrelationsForrecommendProduct的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectoryByrecommendProduct")
	public List<GeProductCorrelation> getGeProductCorrelationsForrecommendProduct() {
		return this.geProductCorrelationsForrecommendProduct;
	}
	/**
	 * 属性geProductCorrelationsForrecommendProduct的setter方法
	 */
	public void setGeProductCorrelationsForrecommendProduct(
			List<GeProductCorrelation> geProductCorrelationsForrecommendProduct) {
		this.geProductCorrelationsForrecommendProduct = geProductCorrelationsForrecommendProduct;
	}

	/**
	 * 	非持久化属性成员公司的getter方法
	 */
	@Transient
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 	非持久化属性成员公司的setter方法
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
