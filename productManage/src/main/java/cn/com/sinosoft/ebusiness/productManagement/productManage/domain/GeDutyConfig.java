package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProductDuty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeDutyConfig
 */
@Entity
@Table(name = "GE_DUTYCONFIG")
public class GeDutyConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// ��������
	
	/** ������� */
	private String serialNo;

	/** ���Ա������� */
	private GeRiskConfig geRiskConfig;

	/** ���Ժ������δ��� */
	private String dutyCode;

	/** ������������ */
	private String dutyName;

	/** �����������μ�� */
	private String simpleName;

	/** ����ҵ������ */
	private String businessArea;

	/** ���Բ���Ա��� */
	private String operatorID;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ���Բ�Ʒ���� */
	private List<GeProductDuty> geProductDuties = new ArrayList<GeProductDuty>(
			0);

	/** ����geSaleProductDuties */
	private List<GeSaleProductDuty> geSaleProductDuties = new ArrayList<GeSaleProductDuty>(
			0);

	/**
	 * ��GeDutyConfig��Ĭ�Ϲ��췽��
	 */
	public GeDutyConfig() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}
	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * ���Ա������ֵ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RISKCODE")
	public GeRiskConfig getGeRiskConfig() {
		return this.geRiskConfig;
	}
	/**
	 * ���Ա������ֵ�setter����
	 */
	public void setGeRiskConfig(GeRiskConfig geRiskConfig) {
		this.geRiskConfig = geRiskConfig;
	}
	/**
	 * �������δ����getter����
	 */

	@Column(name = "DUTYCODE")
	public String getDutyCode() {
		return this.dutyCode;
	}
	/**
	 * �������δ����setter����
	 */
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	/**
	 * �����������Ƶ�getter����
	 */

	@Column(name = "DUTYNAME")
	public String getDutyName() {
		return this.dutyName;
	}
	/**
	 * �����������Ƶ�setter����
	 */
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	/**
	 * �����������μ�Ƶ�getter����
	 */

	@Column(name = "SIMPLENAME")
	public String getSimpleName() {
		return this.simpleName;
	}
	/**
	 * �����������μ�Ƶ�setter����
	 */
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
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
	 * ���Բ�Ʒ���ε�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDutyConfig")
	public List<GeProductDuty> getGeProductDuties() {
		return this.geProductDuties;
	}
	/**
	 * ���Բ�Ʒ���ε�setter����
	 */
	public void setGeProductDuties(List<GeProductDuty> geProductDuties) {
		this.geProductDuties = geProductDuties;
	}
	/**
	 * ����geSaleProductDuties��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDutyConfig")
	public List<GeSaleProductDuty> getGeSaleProductDuties() {
		return this.geSaleProductDuties;
	}
	/**
	 * ����geSaleProductDuties��setter����
	 */
	public void setGeSaleProductDuties(
			List<GeSaleProductDuty> geSaleProductDuties) {
		this.geSaleProductDuties = geSaleProductDuties;
	}

}
