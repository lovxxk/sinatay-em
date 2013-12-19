package cn.com.sinosoft.ebusiness.marketing.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * POJO��GeActivitiesRule
 */
@Entity
@Table(name = "GE_ACTIVITIES_RULE")
public class GeActivitiesRule implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Թ������ */
	private String ruleId;

	/** ������ֵ����-GE_AddService_Activity */
	private GeAddServiceActivity geAddServiceActivity;

	/** ������Ʒ���� */
	private String itemID;

	/** ���Ի��ʽ */
	private String activityPattern;

	/** ���Ի��ʽN��ֵ */
	private String nvalue;

	/** ���Ա�־λ */
	private String flag;
	
	/** ���Ա������� 1����  2С�� 3���� 4����֮��*/
	private String premiumType;
	
	/** ���Ա���ֵ  ����������������֮ǰʱ��@���������ֵ�ķ�Χ*/
	private String peremiumValue;
	
	/** ���Դ�����������*/
	private String discountType;
	
	/** �����ۿ�ID�����պ�������*/
	private String discountID;
	
	/** ���Ա���ֵ  �������ӵ�����*/
	private byte[] discountRemark;
	
	/** ����geActivitiesShoppingProducts */
	private List<GeActivitiesShoppingProduct> geActivitiesShoppingProducts = new ArrayList<GeActivitiesShoppingProduct>(
			0);
	
	//ҵ����ʹ�õ��ֶ�start
	private String itemName;//��Ʒ����
	private String deptID;//����ID  ����ȥ��������ȥ��ѯ
	private String proposalArea;//���ڴ���Ͷ��������
	private String riskCode;//���ִ���
	private String eid;//��Ʒ����
	private String proposalNo;//Ͷ������
	private String premiumRange1;//���ѷ�Χ1  ���ڱ��ѵ�ֵΪ����֮��
	private String premiumRange2;//���ѷ�Χ2 ���ڱ��ѵ�ֵΪ����֮��
	private String discountRemarkText ;//�������������ֶ�
	//ҵ����ʹ�õ��ֶ�end
	
	//�ձ��ʶ
	@Transient
	private List<String> kindCode ;
	
	//������ʶ
	@Transient
	private String connectFlag ;
	/**
	 * ��GeActivitiesRule��Ĭ�Ϲ��췽��
	 */
	public GeActivitiesRule() {
	}

	/**
	 * ���Թ�������getter����
	 */
	@Id
	@Column(name = "RULEID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getRuleId() {
		return this.ruleId;
	}

	/**
	 * ���Թ�������setter����
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * ������ֵ����-GE_AddService_Activity��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	public GeAddServiceActivity getGeAddServiceActivity() {
		return this.geAddServiceActivity;
	}

	/**
	 * ������ֵ����-GE_AddService_Activity��setter����
	 */
	public void setGeAddServiceActivity(
			GeAddServiceActivity geAddServiceActivity) {
		this.geAddServiceActivity = geAddServiceActivity;
	}

	/**
	 * ������Ʒ�����getter����
	 */

	@Column(name = "ITEMID")
	public String getItemID() {
		return this.itemID;
	}

	/**
	 * ������Ʒ�����setter����
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * ���Ի��ʽ��getter����
	 */

	@Column(name = "ACTIVITYPATTERN")
	public String getActivityPattern() {
		return this.activityPattern;
	}

	/**
	 * ���Ի��ʽ��setter����
	 */
	public void setActivityPattern(String activityPattern) {
		this.activityPattern = activityPattern;
	}

	/**
	 * ���Ի��ʽN��ֵ��getter����
	 */

	@Column(name = "NVALUE")
	public String getNvalue() {
		return this.nvalue;
	}

	/**
	 * ���Ի��ʽN��ֵ��setter����
	 */
	public void setNvalue(String nvalue) {
		this.nvalue = nvalue;
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
	@Column(name="PREMIUMTYPE")
	public String getPremiumType() {
		return premiumType;
	}

	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}
	
	@Column(name="PEREMIUMVALUE")
	public String getPeremiumValue() {
		return peremiumValue;
	}

	public void setPeremiumValue(String peremiumValue) {
		this.peremiumValue = peremiumValue;
	}
	
	@Column(name = "DISCOUNTTYPE")
	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	@Column(name = "DISCOUNTID")
	public String getDiscountID() {
		return discountID;
	}

	public void setDiscountID(String discountID) {
		this.discountID = discountID;
	}
	
	@Column(name = "DISCOUNTREMARK")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.BlobByteArrayType")
	public byte[] getDiscountRemark() {
		return discountRemark;
	}

	public void setDiscountRemark(byte[] discountRemark) {
		this.discountRemark = discountRemark;
	}
	
	/**
	 * ����geActivitiesShoppingProducts��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geActivitiesRule")
	public List<GeActivitiesShoppingProduct> getGeActivitiesShoppingProducts() {
		return this.geActivitiesShoppingProducts;
	}

	/**
	 * ����geActivitiesShoppingProducts��setter����
	 */
	public void setGeActivitiesShoppingProducts(
			List<GeActivitiesShoppingProduct> geActivitiesShoppingProducts) {
		this.geActivitiesShoppingProducts = geActivitiesShoppingProducts;
	}

	//ҵ����ʹ�õ��ֶ�start
	@Transient
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Transient
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	@Transient
	public String getProposalArea() {
		return proposalArea;
	}

	public void setProposalArea(String proposalArea) {
		this.proposalArea = proposalArea;
	}
	@Transient
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	@Transient
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	@Transient
	public String getPremiumRange1() {
		return premiumRange1;
	}

	public void setPremiumRange1(String premiumRange1) {
		this.premiumRange1 = premiumRange1;
	}
	@Transient
	public String getPremiumRange2() {
		return premiumRange2;
	}

	public void setPremiumRange2(String premiumRange2) {
		this.premiumRange2 = premiumRange2;
	}
	@Transient
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
	@Transient
	public String getDiscountRemarkText() {
		return discountRemarkText;
	}

	public void setDiscountRemarkText(String discountRemarkText) {
		this.discountRemarkText = discountRemarkText;
	}
	
	
	@Transient
	public List<String> getKindCode() {
		return kindCode;
	}

	public void setKindCode(List<String> kindCode) {
		this.kindCode = kindCode;
	}
	@Transient
	public String getConnectFlag() {
		return connectFlag;
	}

	public void setConnectFlag(String connectFlag) {
		this.connectFlag = connectFlag;
	}
	//ҵ����ʹ�õ��ֶ�start
	
}

