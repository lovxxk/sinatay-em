package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;

/**
 * ������չ��
 *
 */
public class TXInsurance implements Serializable{

	//������ˮ��
	protected String transRefGUID;
	
	//�������ͣ����׺ţ�
	protected String transType;
	
	//��������
	protected String transExeDate;
	
	//����ʱ��
	protected String transExeTime;
	
	//�ӽ�������
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
