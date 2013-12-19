package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProductRisk;
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

/**
 * POJO��GeRiskConfig
 */
@Entity
@Table(name = "GE_RISKCONFIG")
public class GeRiskConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// ��������
	/** �������ִ��� */
	private String riskCode;

	/** ������������ */
	private String riskName;

	/** �������ּ�� */
	private String riskSimpleName;

	/** ���Ժ������ִ��� */
	private String coreRiskCode;

	/** ������������ */
	private String saleChannel;

	/** ������������ */
	private String saleType;

	/** ������������ */
	private String riskType;

	/** ������������ */
	private String riskNature;

	/** ��������ϸ�� */
	private String riskTypeDetail;

	/** ���������ձ�־ */
	private String subRiskFlag;

	/** ����ҵ������ */
	private String businessArea;

	/** ���Բ���Ա��� */
	private String operatorID;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ����geDutyConfigs */
	private List<GeDutyConfig> geDutyConfigs = new ArrayList<GeDutyConfig>(0);

	/** ����geSaleProductRisks */
	private List<GeSaleProductRisk> geSaleProductRisks = new ArrayList<GeSaleProductRisk>(
			0);

	/** ����geProductRisks */
	private List<GeProductRisk> geProductRisks = new ArrayList<GeProductRisk>(0);

	/**
	 * ��GeRiskConfig��Ĭ�Ϲ��췽��
	 */
	public GeRiskConfig() {
	}

	/**
	 * �������ִ����getter����
	 */
	@Id
	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return this.riskCode;
	}
	/**
	 * �������ִ����setter����
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	/**
	 * �����������Ƶ�getter����
	 */

	@Column(name = "RISKNAME")
	public String getRiskName() {
		return this.riskName;
	}
	/**
	 * �����������Ƶ�setter����
	 */
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	/**
	 * �������ּ�Ƶ�getter����
	 */

	@Column(name = "RISKSIMPLENAME")
	public String getRiskSimpleName() {
		return this.riskSimpleName;
	}
	/**
	 * �������ּ�Ƶ�setter����
	 */
	public void setRiskSimpleName(String riskSimpleName) {
		this.riskSimpleName = riskSimpleName;
	}
	/**
	 * ���Ժ������ִ����getter����
	 */

	@Column(name = "CORERISKCODE")
	public String getCoreRiskCode() {
		return this.coreRiskCode;
	}
	/**
	 * ���Ժ������ִ����setter����
	 */
	public void setCoreRiskCode(String coreRiskCode) {
		this.coreRiskCode = coreRiskCode;
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
	 * �����������͵�getter����
	 */

	@Column(name = "SALETYPE")
	public String getSaleType() {
		return this.saleType;
	}
	/**
	 * �����������͵�setter����
	 */
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	/**
	 * �����������͵�getter����
	 */

	@Column(name = "RISKTYPE")
	public String getRiskType() {
		return this.riskType;
	}
	/**
	 * �����������͵�setter����
	 */
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	/**
	 * �����������ʵ�getter����
	 */

	@Column(name = "RISKNATURE")
	public String getRiskNature() {
		return this.riskNature;
	}
	/**
	 * �����������ʵ�setter����
	 */
	public void setRiskNature(String riskNature) {
		this.riskNature = riskNature;
	}
	/**
	 * ��������ϸ�ֵ�getter����
	 */

	@Column(name = "RISKTYPEDETAIL")
	public String getRiskTypeDetail() {
		return this.riskTypeDetail;
	}
	/**
	 * ��������ϸ�ֵ�setter����
	 */
	public void setRiskTypeDetail(String riskTypeDetail) {
		this.riskTypeDetail = riskTypeDetail;
	}
	/**
	 * ���������ձ�־��getter����
	 */

	@Column(name = "SUBRISKFLAG")
	public String getSubRiskFlag() {
		return this.subRiskFlag;
	}
	/**
	 * ���������ձ�־��setter����
	 */
	public void setSubRiskFlag(String subRiskFlag) {
		this.subRiskFlag = subRiskFlag;
	}
	/**
	 * ����ҵ�������getter����
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}
	/**
	 * ����ҵ�������setter����
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
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
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}
	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}
	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * ����geDutyConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRiskConfig")
	public List<GeDutyConfig> getGeDutyConfigs() {
		return this.geDutyConfigs;
	}
	/**
	 * ����geDutyConfigs��setter����
	 */
	public void setGeDutyConfigs(List<GeDutyConfig> geDutyConfigs) {
		this.geDutyConfigs = geDutyConfigs;
	}
	/**
	 * ����geSaleProductRisks��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRiskConfig")
	public List<GeSaleProductRisk> getGeSaleProductRisks() {
		return this.geSaleProductRisks;
	}
	/**
	 * ����geSaleProductRisks��setter����
	 */
	public void setGeSaleProductRisks(List<GeSaleProductRisk> geSaleProductRisks) {
		this.geSaleProductRisks = geSaleProductRisks;
	}
	/**
	 * ����geProductRisks��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRiskConfig")
	public List<GeProductRisk> getGeProductRisks() {
		return this.geProductRisks;
	}
	/**
	 * ����geProductRisks��setter����
	 */
	public void setGeProductRisks(List<GeProductRisk> geProductRisks) {
		this.geProductRisks = geProductRisks;
	}

}
