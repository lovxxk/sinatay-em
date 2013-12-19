package cn.com.sinosoft.portalModule.transport.transactionObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.util.string.StringUtils;


public class BusinessResult implements Serializable {

	/**
	 * ҵ������
	 */
	private static final long serialVersionUID = 1906904929280660056L;
	
	/**
	 * �������
	 */
	private String resultCode;
	
	/***
	 * ���״̬
	 */
	private String resultStatus;
	
	/***
	 * �������
	 */
	private String resultInfoDesc;
	
	/***
	 * ��ϸ�����Ϣ
	 */
	private List<ResultDetailInfo> resultDetailInfos = new ArrayList<ResultDetailInfo>(0);

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getResultInfoDesc() {
		return resultInfoDesc;
	}

	public void setResultInfoDesc(String resultInfoDesc) {
		this.resultInfoDesc = resultInfoDesc;
	}

	public List<ResultDetailInfo> getResultDetailInfos() {
		return resultDetailInfos;
	}

	public void setResultDetailInfos(List<ResultDetailInfo> resultDetailInfos) {
		this.resultDetailInfos = resultDetailInfos;
	}
	
	
	public String getResultMessage() {
		return getResultInfoDesc();
	}
	
	public String getResultDetailMessage() {
		StringBuffer resultMessage = new StringBuffer();
		resultMessage.append(getResultInfoDesc());
		List<ResultDetailInfo> resultDetailInfos = getResultDetailInfos();
		StringBuffer resultDetailMessage = new StringBuffer();
		for (int i = 0; i < resultDetailInfos.size(); i++) {
			ResultDetailInfo resultDetailInfo = resultDetailInfos.get(i);
			if (StringUtils.isNotBlank(resultDetailInfo.getInfoCode()) && StringUtils.isNotBlank(resultDetailInfo.getInfoMessage())) {
				resultDetailMessage.append(resultDetailInfo.getInfoCode() + ": " + resultDetailInfo.getInfoMessage());
			} else {
				if (StringUtils.isNotBlank(resultDetailInfo.getInfoCode())) {
					resultDetailMessage.append(resultDetailInfo.getInfoCode());
				}
				if (StringUtils.isNotBlank(resultDetailInfo.getInfoMessage())) {
					resultDetailMessage.append(resultDetailInfo.getInfoMessage());
				}
				
			}
		}
		if (resultDetailMessage.length() > 0) {
			resultMessage.append(" - " + resultDetailMessage);
		}
		return resultMessage.toString();
	}
}
