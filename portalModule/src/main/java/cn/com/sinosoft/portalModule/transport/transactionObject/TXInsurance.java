package cn.com.sinosoft.portalModule.transport.transactionObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.portalModule.transport.sinatay.SMS;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;

/**
 * 报文扩展域
 *  
 *
 */
public class TXInsurance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -926137260981217174L;
	
	/** 交易类型（交易号）*/
	protected String transType;

	/** 属性交易标示 */
	protected String transIdentify;
	
	/** 属性批量处理ID */
	protected String batchID;
	
	/** 交易流水号*/
	protected String transRefGUID;
	
	/** 子交易流水号*/
	protected String subTransRefGUID;
	
	/** 属性商家交易流水号 */
	protected String merchantTransRefGUID;
	
	/** 属性是发送方ID */
	protected String senderId;
	
	/** 属性是接收方ID */
	protected String receiverId;
	
	/** 属性请服务器地址 */
	protected String requestURL;
	
	/** 属性回调地址 */
	protected String callbackURL;
	
	/** 属性是签名串 */
	protected String sign;
	
	/** 交易日期 */
	protected String transExeDate;
	
	/** 交易时间 */
	protected String transExeTime;
	
	/** 子交易类型 */
	protected String transSubType;
	
	/** 柜员代码 */
	protected String tellerNo;
	
	/** 销售渠道 */
	protected String saleChannel;
	
	/** 销售方式 */
	protected String sellType;
	
	/** 信息来源 */
	protected String source;
	
	/** 业务扩展域 */
	private List<Object> businessDatum;
	
	/** 业务处理结果 */
	private List<BusinessResultDatum> businessResultDatum;
	
	/** 交易结果域 */
	private TransResult transResult;
	
	/** 业务扩展域 */
	private TransBusinessExtension businessExtension;
	
	private TranRequest tranRequest;
	
	/** 手机短信 */
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
