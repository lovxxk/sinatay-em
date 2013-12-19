package cn.com.sinosoft.ebusiness.marketing.domain;

import java.util.List;

public class GeAddserviceConditionDto {
	private String systemFlowId;//系统流程号  01代表深圳北京车险投保流程   02寿险产品打折流程号
	private boolean isPictureFlag;//是否要图片
	private List<String> wantedActivityPatterns;//想要哪种活动方式的数据
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
