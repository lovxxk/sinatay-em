package cn.com.sinosoft.ebusiness.log.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 主操作
 *
 */
public class LogTraceDto implements Serializable{
	private static final long serialVersionUID = -7689325448327108677L;
	private String id;
	private String userId;
	private String ip;
	private String userType;
	private String operationType;
	private Date makeDate;
	private String operation;
	private String parentId;
	private String result;
	private String errorMessage;
	//历经步骤
	private List<LogTraceDto> children = new ArrayList<LogTraceDto>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public Date getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<LogTraceDto> getChildren() {
		return children;
	}
	public void setChildren(List<LogTraceDto> children) {
		this.children = children;
	}
}
