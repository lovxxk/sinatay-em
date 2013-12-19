package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class InsuredInfo implements Serializable{

	//����-��
	private int age = 18;
	//����-��
	private int age_day = 28;
	//��Ͷ���˹�ϵ  1:���� 2:��ĸ
	private String insuredRelation = "";
	//֤������  1:���֤ 2������ 3������֤ 4����ʻ֤
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
