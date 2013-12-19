package cn.com.sinosoft.ebusiness.marketing.domain;

import java.util.List;

public class GeProductAndPattern {
	private String productName;
	private List<String> activityPatternList;
	//set and get 
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<String> getActivityPatternList() {
		return activityPatternList;
	}
	public void setActivityPatternList(List<String> activityPatternList) {
		this.activityPatternList = activityPatternList;
	}
}
