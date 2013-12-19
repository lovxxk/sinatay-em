package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * POJO��GeRisk
 */
@Entity
@Table(name = "GE_RISK")
public class GeRisk implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �������ֱ��� */
	private String riskCode;

	/** ������������ */
	private String riskCName;

	/** ����Ӣ������ */
	private String riskEName;

	/** ���Է������� */
	private String riskTName;

	/** ����ҵ������ */
	private String businessArea;

	/** ������������ */
	private String riskType;

	/** ���Ա����ʻ���� */
	private String insuAccFlag;

	/** �����Ƿ���Ч��־ */
	private String validInd;

	/** ���Բ����˱�� */
	private String operatorID;

	/** ���Զ���ʱ�� */
	private Date createDate;

	/** ���Ա�ʶλ */
	private String flag;
	//ҵ����ʹ�õ��ֶ�start
	private String eid;
	//ҵ����ʹ�õ��ֶ�end
	/**
	 * ��GeRisk��Ĭ�Ϲ��췽��
	 */
	public GeRisk() {
	}

	/**
	 * �������ֱ����getter����
	 */
	@Id
	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return this.riskCode;
	}

	/**
	 * �������ֱ����setter����
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	/**
	 * �����������Ƶ�getter����
	 */

	@Column(name = "RISKCNAME")
	public String getRiskCName() {
		return this.riskCName;
	}

	/**
	 * �����������Ƶ�setter����
	 */
	public void setRiskCName(String riskCName) {
		this.riskCName = riskCName;
	}

	/**
	 * ����Ӣ�����Ƶ�getter����
	 */

	@Column(name = "RISKENAME")
	public String getRiskEName() {
		return this.riskEName;
	}

	/**
	 * ����Ӣ�����Ƶ�setter����
	 */
	public void setRiskEName(String riskEName) {
		this.riskEName = riskEName;
	}

	/**
	 * ���Է������Ƶ�getter����
	 */

	@Column(name = "RISKTNAME")
	public String getRiskTName() {
		return this.riskTName;
	}

	/**
	 * ���Է������Ƶ�setter����
	 */
	public void setRiskTName(String riskTName) {
		this.riskTName = riskTName;
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
	 * ���Ա����ʻ���ǵ�getter����
	 */

	@Column(name = "INSUACCFLAG")
	public String getInsuAccFlag() {
		return this.insuAccFlag;
	}

	/**
	 * ���Ա����ʻ���ǵ�setter����
	 */
	public void setInsuAccFlag(String insuAccFlag) {
		this.insuAccFlag = insuAccFlag;
	}

	/**
	 * �����Ƿ���Ч��־��getter����
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * �����Ƿ���Ч��־��setter����
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	/**
	 * ���Բ����˱�ŵ�getter����
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * ���Բ����˱�ŵ�setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	
	/**
	 * ���Զ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * ���Զ���ʱ���setter����
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	//ҵ����ʹ�õ��ֶ�start
	@Transient
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
	//ҵ����ʹ�õ��ֶ�end
	
}
