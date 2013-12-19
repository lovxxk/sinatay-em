package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.portalModule.transport.dhf100.QueryData;

/**
 * TranResponse节点DTO
 *
 */
public class TranResponse implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private QueryData queryData;
	
	/** 返回标志 CD25 返回标志 0：失败 1：成功 */
	private String flag;
	
	/** 返回信息 Flag为0：错误信息 */
	private String desc;
	
	private String detailArray;
	/**  */
	private LCCont lcCont;
	
	/** 影像文件数 */
	private Integer attachmentCount;
	
	/** 影像文件 */
	private List<Attachment> attachments = new ArrayList<Attachment>(0);

	/** 身份证校验 */
	private List<IdCardCheck> idCardChecks = new ArrayList<IdCardCheck>(0);

	private SMSResult smsResult = new SMSResult();
	
	private String outTradeNo;
	
	private String url;
	
	public QueryData getQueryData() {
		return queryData;
	}

	public void setQueryData(QueryData queryData) {
		this.queryData = queryData;
	}

	/**
	 * 属性 flag 的 getter 方法
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 属性 flag 的 setter 方法
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性 desc 的 getter 方法
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 属性 desc 的 setter 方法
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDetailArray() {
		return detailArray;
	}

	public void setDetailArray(String detailArray) {
		this.detailArray = detailArray;
	}

	/**
	 * 属性 lcCont 的 getter 方法
	 * @return the lcCont
	 */
	public LCCont getLcCont() {
		return lcCont;
	}

	/**
	 * 属性 lcCont 的 setter 方法
	 * @param lcCont the lcCont to set
	 */
	public void setLcCont(LCCont lcCont) {
		this.lcCont = lcCont;
	}

	/**
	 * 属性 attachmentCount 的 getter 方法
	 * @return the attachmentCount
	 */
	public Integer getAttachmentCount() {
		return attachmentCount;
	}

	/**
	 * 属性 attachmentCount 的 setter 方法
	 * @param attachmentCount the attachmentCount to set
	 */
	public void setAttachmentCount(Integer attachmentCount) {
		this.attachmentCount = attachmentCount;
	}

	/**
	 * 属性 attachments 的 getter 方法
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * 属性 attachments 的 setter 方法
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * 属性 idCardChecks 的 getter 方法
	 * @return the idCardChecks
	 */
	public List<IdCardCheck> getIdCardChecks() {
		return idCardChecks;
	}

	/**
	 * 属性 idCardChecks 的 setter 方法
	 * @param idCardChecks the idCardChecks to set
	 */
	public void setIdCardChecks(List<IdCardCheck> idCardChecks) {
		this.idCardChecks = idCardChecks;
	}

	public SMSResult getSmsResult() {
		return smsResult;
	}

	public void setSmsResult(SMSResult smsResult) {
		this.smsResult = smsResult;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
