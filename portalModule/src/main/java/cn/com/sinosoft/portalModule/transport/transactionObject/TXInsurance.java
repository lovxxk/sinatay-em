package cn.com.sinosoft.portalModule.transport.transactionObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.portalModule.transport.sinatay.SMS;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;

/**
 * ������չ��
 *  
 *
 */
public class TXInsurance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -926137260981217174L;
	
	/** �������ͣ����׺ţ�*/
	protected String transType;

	/** ���Խ��ױ�ʾ */
	protected String transIdentify;
	
	/** ������������ID */
	protected String batchID;
	
	/** ������ˮ��*/
	protected String transRefGUID;
	
	/** �ӽ�����ˮ��*/
	protected String subTransRefGUID;
	
	/** �����̼ҽ�����ˮ�� */
	protected String merchantTransRefGUID;
	
	/** �����Ƿ��ͷ�ID */
	protected String senderId;
	
	/** �����ǽ��շ�ID */
	protected String receiverId;
	
	/** �������������ַ */
	protected String requestURL;
	
	/** ���Իص���ַ */
	protected String callbackURL;
	
	/** ������ǩ���� */
	protected String sign;
	
	/** �������� */
	protected String transExeDate;
	
	/** ����ʱ�� */
	protected String transExeTime;
	
	/** �ӽ������� */
	protected String transSubType;
	
	/** ��Ա���� */
	protected String tellerNo;
	
	/** �������� */
	protected String saleChannel;
	
	/** ���۷�ʽ */
	protected String sellType;
	
	/** ��Ϣ��Դ */
	protected String source;
	
	/** ҵ����չ�� */
	private List<Object> businessDatum;
	
	/** ҵ������ */
	private List<BusinessResultDatum> businessResultDatum;
	
	/** ���׽���� */
	private TransResult transResult;
	
	/** ҵ����չ�� */
	private TransBusinessExtension businessExtension;
	
	private TranRequest tranRequest;
	
	/** �ֻ����� */
	private List<SMS> messages = new ArrayList<SMS>(0);

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransIdentify() {
		return transIdentify;
	}

	public void setTransIdentify(String transIdentify) {
		this.transIdentify = transIdentify;
	}

	public String getBatchID() {
		return batchID;
	}

	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}

	public String getTransRefGUID() {
		return transRefGUID;
	}

	public void setTransRefGUID(String transRefGUID) {
		this.transRefGUID = transRefGUID;
	}

	public String getSubTransRefGUID() {
		return subTransRefGUID;
	}

	public void setSubTransRefGUID(String subTransRefGUID) {
		this.subTransRefGUID = subTransRefGUID;
	}

	public String getMerchantTransRefGUID() {
		return merchantTransRefGUID;
	}

	public void setMerchantTransRefGUID(String merchantTransRefGUID) {
		this.merchantTransRefGUID = merchantTransRefGUID;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getCallbackURL() {
		return callbackURL;
	}

	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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

	public List<Object> getBusinessDatum() {
		return businessDatum;
	}

	public void setBusinessDatum(List<Object> businessDatum) {
		this.businessDatum = businessDatum;
	}

	public List<BusinessResultDatum> getBusinessResultDatum() {
		return businessResultDatum;
	}

	public void setBusinessResultDatum(List<BusinessResultDatum> businessResultDatum) {
		this.businessResultDatum = businessResultDatum;
	}

	public TransResult getTransResult() {
		return transResult;
	}

	public void setTransResult(TransResult transResult) {
		this.transResult = transResult;
	}

	public TransBusinessExtension getBusinessExtension() {
		return businessExtension;
	}

	public void setBusinessExtension(TransBusinessExtension businessExtension) {
		this.businessExtension = businessExtension;
	}

	public String getTellerNo() {
		return tellerNo;
	}

	public void setTellerNo(String tellerNo) {
		this.tellerNo = tellerNo;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<SMS> getMessages() {
		return messages;
	}

	public void setMessages(List<SMS> messages) {
		this.messages = messages;
	}

	public TranRequest getTranRequest() {
		return tranRequest;
	}

	public void setTranRequest(TranRequest tranRequest) {
		this.tranRequest = tranRequest;
	}

}
