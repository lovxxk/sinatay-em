package cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeDirectory
 */
@Entity
@Table(name = "GE_DIRECTORY")
public class GeDirectory implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ե��������ƷID */
	private String eid;

	/** �����Ƿ����� */
	private String isNetSale;

	/** ���Բ�Ʒ���� */
	private String productType;

	/** ���Բ�Ʒ����/���ִ��� */
	private String coreProductCode;

	/** ���Բ�Ʒ���� */
	private String productName;

	/** ���Բ�Ʒ��� */
	private String coreProductSimpleName;

	/** ���Ա����ڼ� */
	private String insurancePeriod;

	/** ���Ա����ڼ����� */
	private String insurancePeriodType;

	/** ����������� */
	private String lowestAge;

	/** ����������� */
	private String highestAge;

	/** ������������ */
	private String saleChannel;

	/** ����ҵ�������� */
	private String businessArea;

	/** ���Ա������ */
	private String itemType;

	/** ������������ */
	private String riskLimit;

	/** ���Գб���ʽ */
	private String acceptInsurance;

	/** ���Բ�Ʒ���¼� */
	private String isProductShelf;

	/** ����СͼƬone */
	private String smallPictureOne;

	/** ����СͼƬtwo */
	private String smallPictureTwo;

	/** ������ͼƬone */
	private String middlePictureOne;

	/** ������ͼƬtwo */
	private String middlePictureTwo;

	/** ���Դ�ͼƬone */
	private String bigPictureOne;

	/** ���Դ�ͼƬTwo */
	private String bigPictureTwo;

	/** ���Բ�Ʒ��� */
	private String productSummary;

	/** ���Բ�Ʒ��ɫ */
	private String productFeature;

	/** ���Բ�Ʒ�������� */
	private String productArticleDesc;

	/** ���Ա������� */
	private String premiumRange;

	/** ���Ա��ս������ */
	private String insuranceAmountDesc;

	/** ���Ա����ڼ����� */
	private String insurancePeriodDesc;

	/** ���Խɷѷ�ʽ */
	private String payType;

	/** ����Ͷ����Χ */
	private String insureRange;

	/** ����Ͷ������ */
	private String insureAge;

	/** ���Գб����� */
	private String underwritingObject;

	/** ����������Ⱥ */
	private String applyPeople;

	/** ���Թ�����֪ */
	private String purchaseNotes;

	/** ���Ա������� */
	private String securityInterest;

	/** ���Բ�Ʒ�Ƽ����� */
	private String productRecommend;

	/** ���Է������� */
	private Date publishDate;

	/** ����ͣ������ */
	private Date stopDate;

	/** ���Բ���Ա��� */
	private String operatorID;

	/** ���Դ���ʱ�� */
	private Date createDate;

	/** ���Ը���ʱ�� */
	private Date updateDate;

	/** ����extra1 */
	private String extra1;

	/** ����extra2 */
	private String extra2;

	/** ����extra3 */
	private String extra3;

	/** ����extra4 */
	private String extra4;

	/** ����extra5 */
	private String extra5;

	/** ����premium */
	private BigDecimal premium;

	/** ���Բ�Ʒҵ������ 01��02�ǳ�03�� */
	private String netSaleProductType;

	/** ���Բ�Ʒ��Ŀ 01���� 02��ҵ */
	private String productSection;

	/** ���Բ�Ʒ������� */
	private String productQuoteCode;
	
	/** �ǳ־û����Գ�Ա��˾ */
	private String companyName;

	/** ����geProductCorrelationsForproduct */
	private List<GeProductCorrelation> geProductCorrelationsForproduct = new ArrayList<GeProductCorrelation>(
			0);

	/** ����geDirectoryAttributeRelates */
	private List<GeDirectoryAttributeRelate> geDirectoryAttributeRelates = new ArrayList<GeDirectoryAttributeRelate>(
			0);

	/** ����geProductCorrelationsForrecommendProduct */
	private List<GeProductCorrelation> geProductCorrelationsForrecommendProduct = new ArrayList<GeProductCorrelation>(
			0);

	/**
	 * ��GeDirectory��Ĭ�Ϲ��췽��
	 */
	public GeDirectory() {
	}

	/**
	 * ���Ե��������ƷID��getter����
	 */
	@Id
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
	 * �����Ƿ�������getter����
	 */

	@Column(name = "ISNETSALE")
	public String getIsNetSale() {
		return this.isNetSale;
	}
	/**
	 * �����Ƿ�������setter����
	 */
	public void setIsNetSale(String isNetSale) {
		this.isNetSale = isNetSale;
	}
	/**
	 * ���Բ�Ʒ���͵�getter����
	 */

	@Column(name = "PRODUCTTYPE")
	public String getProductType() {
		return this.productType;
	}
	/**
	 * ���Բ�Ʒ���͵�setter����
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * ���Բ�Ʒ����/���ִ����getter����
	 */

	@Column(name = "COREPRODUCTCODE")
	public String getCoreProductCode() {
		return this.coreProductCode;
	}
	/**
	 * ���Բ�Ʒ����/���ִ����setter����
	 */
	public void setCoreProductCode(String coreProductCode) {
		this.coreProductCode = coreProductCode;
	}
	/**
	 * ���Բ�Ʒ���Ƶ�getter����
	 */

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return this.productName;
	}
	/**
	 * ���Բ�Ʒ���Ƶ�setter����
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * ���Բ�Ʒ��Ƶ�getter����
	 */

	@Column(name = "COREPRODUCTSIMPLENAME")
	public String getCoreProductSimpleName() {
		return this.coreProductSimpleName;
	}
	/**
	 * ���Բ�Ʒ��Ƶ�setter����
	 */
	public void setCoreProductSimpleName(String coreProductSimpleName) {
		this.coreProductSimpleName = coreProductSimpleName;
	}
	/**
	 * ���Ա����ڼ��getter����
	 */

	@Column(name = "INSURANCEPERIOD")
	public String getInsurancePeriod() {
		return this.insurancePeriod;
	}
	/**
	 * ���Ա����ڼ��setter����
	 */
	public void setInsurancePeriod(String insurancePeriod) {
		this.insurancePeriod = insurancePeriod;
	}
	/**
	 * ���Ա����ڼ����͵�getter����
	 */

	@Column(name = "INSURANCEPERIODTYPE")
	public String getInsurancePeriodType() {
		return this.insurancePeriodType;
	}
	/**
	 * ���Ա����ڼ����͵�setter����
	 */
	public void setInsurancePeriodType(String insurancePeriodType) {
		this.insurancePeriodType = insurancePeriodType;
	}
	/**
	 * ������������getter����
	 */

	@Column(name = "LOWESTAGE")
	public String getLowestAge() {
		return this.lowestAge;
	}
	/**
	 * ������������setter����
	 */
	public void setLowestAge(String lowestAge) {
		this.lowestAge = lowestAge;
	}
	/**
	 * ������������getter����
	 */

	@Column(name = "HIGHESTAGE")
	public String getHighestAge() {
		return this.highestAge;
	}
	/**
	 * ������������setter����
	 */
	public void setHighestAge(String highestAge) {
		this.highestAge = highestAge;
	}
	/**
	 * ��������������getter����
	 */

	@Column(name = "SALECHANNEL")
	public String getSaleChannel() {
		return this.saleChannel;
	}
	/**
	 * ��������������setter����
	 */
	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}
	/**
	 * ����ҵ���������getter����
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}
	/**
	 * ����ҵ���������setter����
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	/**
	 * ���Ա�����͵�getter����
	 */

	@Column(name = "ITEMTYPE")
	public String getItemType() {
		return this.itemType;
	}
	/**
	 * ���Ա�����͵�setter����
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * �����������޵�getter����
	 */

	@Column(name = "RISKLIMIT")
	public String getRiskLimit() {
		return this.riskLimit;
	}
	/**
	 * �����������޵�setter����
	 */
	public void setRiskLimit(String riskLimit) {
		this.riskLimit = riskLimit;
	}
	/**
	 * ���Գб���ʽ��getter����
	 */

	@Column(name = "ACCEPTINSURANCE")
	public String getAcceptInsurance() {
		return this.acceptInsurance;
	}
	/**
	 * ���Գб���ʽ��setter����
	 */
	public void setAcceptInsurance(String acceptInsurance) {
		this.acceptInsurance = acceptInsurance;
	}
	/**
	 * ���Բ�Ʒ���¼ܵ�getter����
	 */

	@Column(name = "ISPRODUCTSHELF")
	public String getIsProductShelf() {
		return this.isProductShelf;
	}
	/**
	 * ���Բ�Ʒ���¼ܵ�setter����
	 */
	public void setIsProductShelf(String isProductShelf) {
		this.isProductShelf = isProductShelf;
	}
	/**
	 * ����СͼƬone��getter����
	 */

	@Column(name = "SMALLPICTUREONE")
	public String getSmallPictureOne() {
		return this.smallPictureOne;
	}
	/**
	 * ����СͼƬone��setter����
	 */
	public void setSmallPictureOne(String smallPictureOne) {
		this.smallPictureOne = smallPictureOne;
	}
	/**
	 * ����СͼƬtwo��getter����
	 */

	@Column(name = "SMALLPICTURETWO")
	public String getSmallPictureTwo() {
		return this.smallPictureTwo;
	}
	/**
	 * ����СͼƬtwo��setter����
	 */
	public void setSmallPictureTwo(String smallPictureTwo) {
		this.smallPictureTwo = smallPictureTwo;
	}
	/**
	 * ������ͼƬone��getter����
	 */

	@Column(name = "MIDDLEPICTUREONE")
	public String getMiddlePictureOne() {
		return this.middlePictureOne;
	}
	/**
	 * ������ͼƬone��setter����
	 */
	public void setMiddlePictureOne(String middlePictureOne) {
		this.middlePictureOne = middlePictureOne;
	}
	/**
	 * ������ͼƬtwo��getter����
	 */

	@Column(name = "MIDDLEPICTURETWO")
	public String getMiddlePictureTwo() {
		return this.middlePictureTwo;
	}
	/**
	 * ������ͼƬtwo��setter����
	 */
	public void setMiddlePictureTwo(String middlePictureTwo) {
		this.middlePictureTwo = middlePictureTwo;
	}
	/**
	 * ���Դ�ͼƬone��getter����
	 */

	@Column(name = "BIGPICTUREONE")
	public String getBigPictureOne() {
		return this.bigPictureOne;
	}
	/**
	 * ���Դ�ͼƬone��setter����
	 */
	public void setBigPictureOne(String bigPictureOne) {
		this.bigPictureOne = bigPictureOne;
	}
	/**
	 * ���Դ�ͼƬTwo��getter����
	 */

	@Column(name = "BIGPICTURETWO")
	public String getBigPictureTwo() {
		return this.bigPictureTwo;
	}
	/**
	 * ���Դ�ͼƬTwo��setter����
	 */
	public void setBigPictureTwo(String bigPictureTwo) {
		this.bigPictureTwo = bigPictureTwo;
	}
	/**
	 * ���Բ�Ʒ����getter����
	 */

	@Column(name = "PRODUCTSUMMARY")
	public String getProductSummary() {
		return this.productSummary;
	}
	/**
	 * ���Բ�Ʒ����setter����
	 */
	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}
	/**
	 * ���Բ�Ʒ��ɫ��getter����
	 */

	@Column(name = "PRODUCTFEATURE")
	public String getProductFeature() {
		return this.productFeature;
	}
	/**
	 * ���Բ�Ʒ��ɫ��setter����
	 */
	public void setProductFeature(String productFeature) {
		this.productFeature = productFeature;
	}
	/**
	 * ���Բ�Ʒ����������getter����
	 */

	@Column(name = "PRODUCTARTICLEDESC")
	public String getProductArticleDesc() {
		return this.productArticleDesc;
	}
	/**
	 * ���Բ�Ʒ����������setter����
	 */
	public void setProductArticleDesc(String productArticleDesc) {
		this.productArticleDesc = productArticleDesc;
	}
	/**
	 * ���Ա��������getter����
	 */

	@Column(name = "PREMIUMRANGE")
	public String getPremiumRange() {
		return this.premiumRange;
	}
	/**
	 * ���Ա��������setter����
	 */
	public void setPremiumRange(String premiumRange) {
		this.premiumRange = premiumRange;
	}
	/**
	 * ���Ա��ս��������getter����
	 */

	@Column(name = "INSURANCEAMOUNTDESC")
	public String getInsuranceAmountDesc() {
		return this.insuranceAmountDesc;
	}
	/**
	 * ���Ա��ս��������setter����
	 */
	public void setInsuranceAmountDesc(String insuranceAmountDesc) {
		this.insuranceAmountDesc = insuranceAmountDesc;
	}
	/**
	 * ���Ա����ڼ�������getter����
	 */

	@Column(name = "INSURANCEPERIODDESC")
	public String getInsurancePeriodDesc() {
		return this.insurancePeriodDesc;
	}
	/**
	 * ���Ա����ڼ�������setter����
	 */
	public void setInsurancePeriodDesc(String insurancePeriodDesc) {
		this.insurancePeriodDesc = insurancePeriodDesc;
	}
	/**
	 * ���Խɷѷ�ʽ��getter����
	 */

	@Column(name = "PAYTYPE")
	public String getPayType() {
		return this.payType;
	}
	/**
	 * ���Խɷѷ�ʽ��setter����
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * ����Ͷ����Χ��getter����
	 */

	@Column(name = "INSURERANGE")
	public String getInsureRange() {
		return this.insureRange;
	}
	/**
	 * ����Ͷ����Χ��setter����
	 */
	public void setInsureRange(String insureRange) {
		this.insureRange = insureRange;
	}
	/**
	 * ����Ͷ�������getter����
	 */

	@Column(name = "INSUREAGE")
	public String getInsureAge() {
		return this.insureAge;
	}
	/**
	 * ����Ͷ�������setter����
	 */
	public void setInsureAge(String insureAge) {
		this.insureAge = insureAge;
	}
	/**
	 * ���Գб������getter����
	 */

	@Column(name = "UNDERWRITINGOBJECT")
	public String getUnderwritingObject() {
		return this.underwritingObject;
	}
	/**
	 * ���Գб������setter����
	 */
	public void setUnderwritingObject(String underwritingObject) {
		this.underwritingObject = underwritingObject;
	}
	/**
	 * ����������Ⱥ��getter����
	 */

	@Column(name = "APPLYPEOPLE")
	public String getApplyPeople() {
		return this.applyPeople;
	}
	/**
	 * ����������Ⱥ��setter����
	 */
	public void setApplyPeople(String applyPeople) {
		this.applyPeople = applyPeople;
	}
	/**
	 * ���Թ�����֪��getter����
	 */

	@Column(name = "PURCHASENOTES")
	public String getPurchaseNotes() {
		return this.purchaseNotes;
	}
	/**
	 * ���Թ�����֪��setter����
	 */
	public void setPurchaseNotes(String purchaseNotes) {
		this.purchaseNotes = purchaseNotes;
	}
	/**
	 * ���Ա��������getter����
	 */

	@Column(name = "SECURITYINTEREST")
	public String getSecurityInterest() {
		return this.securityInterest;
	}
	/**
	 * ���Ա��������setter����
	 */
	public void setSecurityInterest(String securityInterest) {
		this.securityInterest = securityInterest;
	}
	/**
	 * ���Բ�Ʒ�Ƽ����ֵ�getter����
	 */

	@Column(name = "PRODUCTRECOMMEND")
	public String getProductRecommend() {
		return this.productRecommend;
	}
	/**
	 * ���Բ�Ʒ�Ƽ����ֵ�setter����
	 */
	public void setProductRecommend(String productRecommend) {
		this.productRecommend = productRecommend;
	}
	/**
	 * ���Է������ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PUBLISHDATE")
	public Date getPublishDate() {
		return this.publishDate;
	}
	/**
	 * ���Է������ڵ�setter����
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	/**
	 * ����ͣ�����ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "STOPDATE")
	public Date getStopDate() {
		return this.stopDate;
	}
	/**
	 * ����ͣ�����ڵ�setter����
	 */
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	/**
	 * ���Բ���Ա��ŵ�getter����
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}
	/**
	 * ���Բ���Ա��ŵ�setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATEDATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}
	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * ����extra1��getter����
	 */

	@Column(name = "EXTRA1")
	public String getExtra1() {
		return this.extra1;
	}
	/**
	 * ����extra1��setter����
	 */
	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}
	/**
	 * ����extra2��getter����
	 */

	@Column(name = "EXTRA2")
	public String getExtra2() {
		return this.extra2;
	}
	/**
	 * ����extra2��setter����
	 */
	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}
	/**
	 * ����extra3��getter����
	 */

	@Column(name = "EXTRA3")
	public String getExtra3() {
		return this.extra3;
	}
	/**
	 * ����extra3��setter����
	 */
	public void setExtra3(String extra3) {
		this.extra3 = extra3;
	}
	/**
	 * ����extra4��getter����
	 */

	@Column(name = "EXTRA4")
	public String getExtra4() {
		return this.extra4;
	}
	/**
	 * ����extra4��setter����
	 */
	public void setExtra4(String extra4) {
		this.extra4 = extra4;
	}
	/**
	 * ����extra5��getter����
	 */

	@Column(name = "EXTRA5")
	public String getExtra5() {
		return this.extra5;
	}
	/**
	 * ����extra5��setter����
	 */
	public void setExtra5(String extra5) {
		this.extra5 = extra5;
	}
	/**
	 * ����premium��getter����
	 */

	@Column(name = "PREMIUM")
	public BigDecimal getPremium() {
		return this.premium;
	}
	/**
	 * ����premium��setter����
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	/**
	 * ���Բ�Ʒҵ������ 01��02�ǳ�03����getter����
	 */

	@Column(name = "NETSALEPRODUCTTYPE")
	public String getNetSaleProductType() {
		return this.netSaleProductType;
	}
	/**
	 * ���Բ�Ʒҵ������ 01��02�ǳ�03����setter����
	 */
	public void setNetSaleProductType(String netSaleProductType) {
		this.netSaleProductType = netSaleProductType;
	}
	/**
	 * ���Բ�Ʒ��Ŀ 01���� 02��ҵ��getter����
	 */

	@Column(name = "PRODUCTSECTION")
	public String getProductSection() {
		return this.productSection;
	}
	/**
	 * ���Բ�Ʒ��Ŀ 01���� 02��ҵ��setter����
	 */
	public void setProductSection(String productSection) {
		this.productSection = productSection;
	}
	/**
	 * ���Բ�Ʒ��������getter����
	 */

	@Column(name = "PRODUCTQUOTECODE")
	public String getProductQuoteCode() {
		return this.productQuoteCode;
	}
	/**
	 * ���Բ�Ʒ��������setter����
	 */
	public void setProductQuoteCode(String productQuoteCode) {
		this.productQuoteCode = productQuoteCode;
	}
	/**
	 * ����geProductCorrelationsForproduct��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectoryByproduct")
	public List<GeProductCorrelation> getGeProductCorrelationsForproduct() {
		return this.geProductCorrelationsForproduct;
	}
	/**
	 * ����geProductCorrelationsForproduct��setter����
	 */
	public void setGeProductCorrelationsForproduct(
			List<GeProductCorrelation> geProductCorrelationsForproduct) {
		this.geProductCorrelationsForproduct = geProductCorrelationsForproduct;
	}
	/**
	 * ����geDirectoryAttributeRelates��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectory")
	public List<GeDirectoryAttributeRelate> getGeDirectoryAttributeRelates() {
		return this.geDirectoryAttributeRelates;
	}
	/**
	 * ����geDirectoryAttributeRelates��setter����
	 */
	public void setGeDirectoryAttributeRelates(
			List<GeDirectoryAttributeRelate> geDirectoryAttributeRelates) {
		this.geDirectoryAttributeRelates = geDirectoryAttributeRelates;
	}
	/**
	 * ����geProductCorrelationsForrecommendProduct��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDirectoryByrecommendProduct")
	public List<GeProductCorrelation> getGeProductCorrelationsForrecommendProduct() {
		return this.geProductCorrelationsForrecommendProduct;
	}
	/**
	 * ����geProductCorrelationsForrecommendProduct��setter����
	 */
	public void setGeProductCorrelationsForrecommendProduct(
			List<GeProductCorrelation> geProductCorrelationsForrecommendProduct) {
		this.geProductCorrelationsForrecommendProduct = geProductCorrelationsForrecommendProduct;
	}

	/**
	 * 	�ǳ־û����Գ�Ա��˾��getter����
	 */
	@Transient
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 	�ǳ־û����Գ�Ա��˾��setter����
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
