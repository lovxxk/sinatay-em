package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.Date;

public class BaseInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 交易日期
	 */
	private Date transrDate;
	/**
	 * 交易时间
	 */
	private String TransrTime;
	/**
	 * 柜员代码
	 */
	private String tellerNo;
	/**
	 * 交易流水号
	 */
	private String transrNo;
	/**
	 * 销售渠道
	 */
	private String saleChannel;
	/**
	 * 销售方式
	 */
	private String sellType;
	/**
	 * 处理标志
	 */
	private String functionFlag;
	/**
	 * 信息来源
	 */
	private String source;

	public Date getTransrDate() {
		return transrDate;
	}

	public void setTransrDate(Date transrDate) {
		this.transrDate = transrDate;
	}

	public String getTransrTime() {
		return TransrTime;
	}

	public void setTransrTime(String transrTime) {
		TransrTime = transrTime;
	}

	public String getTellerNo() {
		return tellerNo;
	}

	public void setTellerNo(String tellerNo) {
		this.tellerNo = tellerNo;
	}

	public String getTransrNo() {
		return transrNo;
	}

	public void setTransrNo(String transrNo) {
		this.transrNo = transrNo;
	}

	public String getSaleChannel() {
		return saleChannel;
	}

	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}

	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	public String getFunctionFlag() {
		return functionFlag;
	}

	public void setFunctionFlag(String functionFlag) {
		this.functionFlag = functionFlag;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
