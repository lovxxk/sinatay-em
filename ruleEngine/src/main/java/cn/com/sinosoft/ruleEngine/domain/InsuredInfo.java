package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class InsuredInfo implements Serializable{

	//年龄-年
	private int age = 18;
	//年龄-日
	private int age_day = 28;
	//与投保人关系  1:本人 2:父母
	private String insuredRelation = "";
	//证件类型  1:身份证 2：护照 3：军官证 4：驾驶证
	private String credType = "";

	public int getAge() {
		return age;
	}

	public void setAge(String age){
		if(!age.equals("")){
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
			age_day = this.age_day;
		}
	}
	
	public String getInsuredRelation() {
		return insuredRelation;
	}
	
	public void setInsuredRelation(String insuredRelation) {
		this.insuredRelation = insuredRelation;
	}
	
	public String getCredType() {
		return credType;
	}
	
	public void setCredType(String credType) {
		this.credType = credType;
	}
	
}
