package cn.com.sinosoft.ruleEngine.domain;

import java.util.Date;

public class ScoreInputBOM {
	//����
	private String type;
	//��ȡ;��
	private String getWay;
	//���ɱ���
	private String payment;
	//����ʱ��
	private Date scoreDate;
	//�û��ȼ�
	private String level;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getGetWay() {
		return getWay;
	}

	public void setGetWay(String getWay) {
		this.getWay = getWay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Date getScoreDate() {
		return scoreDate;
	}

	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}

}
