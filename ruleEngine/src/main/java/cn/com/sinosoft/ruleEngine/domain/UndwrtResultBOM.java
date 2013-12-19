package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class UndwrtResultBOM implements Serializable{

	//业务单号
	private String certiNo = "";
	//是否核保通过
	private boolean isUnderwritePass = true;
	//不通过原因
	private String reason = "";
	
	public String getCertiNo() {
		return certiNo;
	}
	
	public void setCertiNo(String certiNo) {
		this.certiNo = certiNo;
	}
	
	public boolean isUnderwritePass() {
		return isUnderwritePass;
	}
	
	public void setUnderwritePass(boolean isUnderwritePass) {
		this.isUnderwritePass = isUnderwritePass;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
