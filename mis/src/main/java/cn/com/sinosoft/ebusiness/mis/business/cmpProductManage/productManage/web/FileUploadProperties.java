package cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.productManage.web;

import java.io.Serializable;

import org.springframework.stereotype.Service;

public class FileUploadProperties implements Serializable{
	private String quoteFilePrefix;
	private String questionFilePrefix;
	private String quotePath;
	private String questionPath;
	
	
	public String getQuoteFilePrefix() {
		return quoteFilePrefix;
	}
	public void setQuoteFilePrefix(String quoteFilePrefix) {
		this.quoteFilePrefix = quoteFilePrefix;
	}
	public String getQuestionFilePrefix() {
		return questionFilePrefix;
	}
	public void setQuestionFilePrefix(String questionFilePrefix) {
		this.questionFilePrefix = questionFilePrefix;
	}
	public String getQuotePath() {
		return quotePath;
	}
	public void setQuotePath(String quotePath) {
		this.quotePath = quotePath;
	}
	public String getQuestionPath() {
		return questionPath;
	}
	public void setQuestionPath(String questionPath) {
		this.questionPath = questionPath;
	}
	
	
	
}
