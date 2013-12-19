package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UndwrtInputBOM implements Serializable{

	//ҵ�񵥺�
	private String certiNo = "";
	//��Ʒ����
	private String productCode = "";
	//���ִ���
	private String riskCode = "";
	//����-��
	private int age = 0;
	//����-��
	private int age_day = 0;
	//�������� �磺*�죬*�ܣ�*���£�*��
	private String insuredTime = "";
	//��������_ʱ���_��
	private int insuredTime_day = 0;
	//�������˸���
	private int persons = 0;
	//����Ч��������
	private int days = 0;
	//����������Ϣ
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
