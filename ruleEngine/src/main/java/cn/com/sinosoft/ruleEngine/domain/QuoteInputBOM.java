package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class QuoteInputBOM implements Serializable {
	//产品代码
	private String productCode = "";
	//责任代码
	private String dutyCode="";
	//责任保额
	private String amount = "";
	//年龄
	private String age;
	//保险期限 如：*天，*周，*个月，*年
	private String insuredTime = "";
	//被保险人个数
	private int persons = 0;
	//性别
	private String sex = "";
	//是否会员
	private String isMember = "";
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getDutyCode() {
		return dutyCode;
	}
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getInsuredTime() {
		return insuredTime;
	}
	public void setInsuredTime(String insuredTime) {
		this.insuredTime = insuredTime;
	}
	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIsMember() {
		return isMember;
	}
	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}

}
