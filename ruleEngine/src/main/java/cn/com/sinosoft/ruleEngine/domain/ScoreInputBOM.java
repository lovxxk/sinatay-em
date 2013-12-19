package cn.com.sinosoft.ruleEngine.domain;

import java.util.Date;

public class ScoreInputBOM {
	//类型
	private String type;
	//获取途径
	private String getWay;
	//缴纳保费
	private String payment;
	//积分时间
	private Date scoreDate;
	//用户等级
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
