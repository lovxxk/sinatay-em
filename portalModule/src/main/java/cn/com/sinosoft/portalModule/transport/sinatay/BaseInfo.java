package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.Date;

public class BaseInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ��������
	 */
	private Date transrDate;
	/**
	 * ����ʱ��
	 */
	private String TransrTime;
	/**
	 * ��Ա����
	 */
	private String tellerNo;
	/**
	 * ������ˮ��
	 */
	private String transrNo;
	/**
	 * ��������
	 */
	private String saleChannel;
	/**
	 * ���۷�ʽ
	 */
	private String sellType;
	/**
	 * �����־
	 */
	private String functionFlag;
	/**
	 * ��Ϣ��Դ
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
