package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class UndwrtResultBOM implements Serializable{

	//ҵ�񵥺�
	private String certiNo = "";
	//�Ƿ�˱�ͨ��
	private boolean isUnderwritePass = true;
	//��ͨ��ԭ��
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
