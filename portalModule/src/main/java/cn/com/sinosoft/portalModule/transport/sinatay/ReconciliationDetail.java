package cn.com.sinosoft.portalModule.transport.sinatay;

import java.math.BigDecimal;

public class ReconciliationDetail {

	private String transDate;
	
	private String prtno;
	
	private BigDecimal prem;
	
	private String appFlag;

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getPrtno() {
		return prtno;
	}

	public void setPrtno(String prtno) {
		this.prtno = prtno;
	}

	public BigDecimal getPrem() {
		return prem;
	}

	public void setPrem(BigDecimal prem) {
		this.prem = prem;
	}

	public String getAppFlag() {
		return appFlag;
	}

	public void setAppFlag(String appFlag) {
		this.appFlag = appFlag;
	}
	
	
}
