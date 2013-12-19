package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UndwrtInputBOM implements Serializable{

	//业务单号
	private String certiNo = "";
	//产品代码
	private String productCode = "";
	//险种代码
	private String riskCode = "";
	//年龄-年
	private int age = 0;
	//年龄-日
	private int age_day = 0;
	//保险期限 如：*天，*周，*个月，*年
	private String insuredTime = "";
	//保险期限_时间段_天
	private int insuredTime_day = 0;
	//被保险人个数
	private int persons = 0;
	//距生效日期天数
	private int days = 0;
	//被保险人信息
	List<InsuredInfo> insuredInfoList = new ArrayList<InsuredInfo>();

	public String getCertiNo() {
		return certiNo;
	}
	
	public void setCertiNo(String certiNo) {
		this.certiNo = certiNo;
	}

	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(String age){
		if(!"".equals(age)){
			this.age = Integer.parseInt(age);
		}else{
			this.age = 0;
		}
	}
	
	public int getAge_day() {
		return age_day;
	}

	public void setAge_day(String ageDay){
		if(!"".equals(ageDay)){
			age_day = Integer.parseInt(ageDay);
		}else{
			age_day = 0;
		}
	}
	
	public String getInsuredTime() {
		return insuredTime;
	}
	
	public void setInsuredTime(String insuredTime) {
		this.insuredTime = insuredTime;
	}
	
	public int getInsuredTime_day() {
		return insuredTime_day;
	}

	public void setInsuredTime_day(String insuredTimeDay) {
		if(!"".equals(insuredTimeDay)){
			insuredTime_day = Integer.parseInt(insuredTimeDay);
		}else{
			insuredTime_day = 0;
		}
	}
	
	public int getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		if(!"".equals(persons)){
			this.persons = Integer.parseInt(persons);
		}else{
			this.persons = 1;
		}
	}
	
	public int getDays() {
		return days;
	}

	public void setDays(String days) {
		if(!"".equals(days)){
			this.days = Integer.parseInt(days);
		}else{
			this.days = 0;
		}
	}
	
	public List<InsuredInfo> getInsuredInfoList() {
		return insuredInfoList;
	}
	
	public void setInsuredInfoList(List<InsuredInfo> insuredInfoList) {
		this.insuredInfoList = insuredInfoList;
	}

	public String getRiskCode() {
		return riskCode;
	}
	
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	
}
