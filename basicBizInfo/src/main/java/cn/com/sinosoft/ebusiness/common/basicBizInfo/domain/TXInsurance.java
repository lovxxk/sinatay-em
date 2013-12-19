package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;

/**
 * 报文扩展域
 *
 */
public class TXInsurance implements Serializable{

	//交易流水号
	protected String transRefGUID;
	
	//交易类型（交易号）
	protected String transType;
	
	//交易日期
	protected String transExeDate;
	
	//交易时间
	protected String transExeTime;
	
	//子交易类型
	protected String transSubType;
	
	public String getTransRefGUID() {
		return transRefGUID;
	}

	public void setTransRefGUID(String transRefGUID) {
		this.transRefGUID = transRefGUID;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransExeDate() {
		return transExeDate;
	}

	public void setTransExeDate(String transExeDate) {
		this.transExeDate = transExeDate;
	}

	public String getTransExeTime() {
		return transExeTime;
	}

	public void setTransExeTime(String transExeTime) {
		this.transExeTime = transExeTime;
	}

	public String getTransSubType() {
		return transSubType;
	}

	public void setTransSubType(String transSubType) {
		this.transSubType = transSubType;
	}
}
