package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.Date;

public class UniversalRate{
	private String riskCode;
	/* �����˻����� */
	private String insuAccno;
	/* �������� */
	private Date balaDate;
	/* ����Ӧ������ */
	private Date srateDate;
	/* ����ʵ�ʹ������� */
	private Date arateDate;
	/* �������� */
	private Date startDate;
	/* ��ֹ���� */
	private Date endDate;
	/* ���� */
	private String rate;
	/* �ۺ���������� */
	private String amountRate;
	/* ������ */
	private String fiscalYear;

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getInsuAccno() {
		return insuAccno;
	}

	public void setInsuAccno(String insuAccno) {
		this.insuAccno = insuAccno;
	}

	public Date getBalaDate() {
		return balaDate;
	}

	public void setBalaDate(Date balaDate) {
		this.balaDate = balaDate;
	}

	public Date getSrateDate() {
		return srateDate;
	}

	public void setSrateDate(Date srateDate) {
		this.srateDate = srateDate;
	}

	public Date getArateDate() {
		return arateDate;
	}

	public void setArateDate(Date arateDate) {
		this.arateDate = arateDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAmountRate() {
		return amountRate;
	}

	public void setAmountRate(String amountRate) {
		this.amountRate = amountRate;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
}
