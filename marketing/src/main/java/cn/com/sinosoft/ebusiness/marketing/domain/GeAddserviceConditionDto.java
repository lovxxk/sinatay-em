package cn.com.sinosoft.ebusiness.marketing.domain;

import java.util.List;

public class GeAddserviceConditionDto {
	private String systemFlowId;//ϵͳ���̺�  01�������ڱ�������Ͷ������   02���ղ�Ʒ�������̺�
	private boolean isPictureFlag;//�Ƿ�ҪͼƬ
	private List<String> wantedActivityPatterns;//��Ҫ���ֻ��ʽ������
	private String userId;
	
	//set and get
	public String getSystemFlowId() {
		return systemFlowId;
	}
	public void setSystemFlowId(String systemFlowId) {
		this.systemFlowId = systemFlowId;
	}
	public List<String> getWantedActivityPatterns() {
		return wantedActivityPatterns;
	}
	public void setWantedActivityPatterns(List<String> wantedActivityPatterns) {
		this.wantedActivityPatterns = wantedActivityPatterns;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isPictureFlag() {
		return isPictureFlag;
	}
	public void setPictureFlag(boolean isPictureFlag) {
		this.isPictureFlag = isPictureFlag;
	}
}
