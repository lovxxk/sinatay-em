package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class QuoteInputBOM implements Serializable {
	//��Ʒ����
	private String productCode = "";
	//���δ���
	private String dutyCode="";
	//���α���
	private String amount = "";
	//����
	private String age;
	//�������� �磺*�죬*�ܣ�*���£�*��
	private String insuredTime = "";
	//�������˸���
	private int persons = 0;
	//�Ա�
	private String sex = "";
	//�Ƿ��Ա
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
