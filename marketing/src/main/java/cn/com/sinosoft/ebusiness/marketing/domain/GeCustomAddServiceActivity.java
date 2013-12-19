package cn.com.sinosoft.ebusiness.marketing.domain;


public class GeCustomAddServiceActivity extends GeAddServiceActivity {
	private static final long serialVersionUID = 3740249605785140486L;
	private String eid;//产品代码
	private String productName;//产品名称
	private String activitypattern;//活动方式
	private String startDate;  //起始日期
	private String endDate;   //结束日期
	//set and get 
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getActivitypattern() {
		return activitypattern;
	}
	public void setActivitypattern(String activitypattern) {
		this.activitypattern = activitypattern;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
