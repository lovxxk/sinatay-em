package cn.com.sinosoft.ebusiness.sys.exception;

import cn.com.sinosoft.ebusiness.sys.exception.userException.ExceptionKey;

/**
 * 
 * 用户异常基类
 * 
 */
public abstract class UserException extends RuntimeException {

	private Throwable cause = null;
	private final String exceptionKind = "01";
	private String userExceptionCode = "";
	private String subUserExceptionCode = "";
	private String concreteExceptionCode = "";
	private String identifyFlag = "";
	private String msg = "";
	private ExceptionGrade grade = ExceptionGrade.UNSERIOUS;
	private String exceptionCode = "";
	private boolean showExceptionCode = true;
	private boolean showExceptionDesc = false;

	public UserException(String userExceptionCode, String subUserExceptionCode,
			String concreteExceptionCode, String msg, Throwable cause,
			ExceptionGrade grade) {
		if (msg != null) {
			this.msg = msg;
		}
		if (grade != null) {
			this.grade = grade;
		}
		if (userExceptionCode != null) {
			this.userExceptionCode = userExceptionCode;

		}
		if (subUserExceptionCode != null) {
			this.subUserExceptionCode = subUserExceptionCode;
		}
		if (concreteExceptionCode != null) {
			this.concreteExceptionCode = concreteExceptionCode;
		}
		if (!("").equals(this.concreteExceptionCode)
				&& !("").equals(this.subUserExceptionCode)
				&& !("").equals(this.userExceptionCode)) {
			XmlConcreteException xmlConcreteException = ExceptionConfig
					.getXmlConcreteException(exceptionKind, userExceptionCode,
							subUserExceptionCode, concreteExceptionCode);
			if (xmlConcreteException != null) {
				this.msg = xmlConcreteException.getExceptionDesc();
				this.grade = xmlConcreteException.getGrade();
				this.exceptionCode = subUserExceptionCode+"_"+ xmlConcreteException.getExceptionCode();
				this.showExceptionCode = xmlConcreteException.isShowExceptionCode();
				this.showExceptionDesc = xmlConcreteException.isShowExceptionDesc();
			}
		}else if(this.msg.contains("@Code:")){
			//处理非配置异常(处理核心异常)
			this.concreteExceptionCode = this.msg.split("@Message:")[0].split("@Code:")[1];
			if(this.msg.contains("@IdentifyFlag:")){
				this.identifyFlag = this.msg.split("@IdentifyFlag:")[1];
			}
			this.exceptionCode = subUserExceptionCode+"_"+ this.concreteExceptionCode;
		}
		
		if(this.identifyFlag==null||"".equals(this.identifyFlag)){
			String key = ExceptionKey.exceptionKeyMap.get();
			this.identifyFlag = key;
		}

		this.cause = cause;
	}
	
	public String showMessage(){
		StringBuffer message = new StringBuffer("");
		if(showExceptionDesc){
			if(!"".equals(msg)){
				message.append(msg);
			}else{
				message.append(cause.getMessage());
			}
		}
		if(showExceptionCode){
			if(!showExceptionDesc){
				message.append("").append(exceptionCode);
			}else{
				message.append("(").append("").append(exceptionCode).append(")");
			}
		}
		return message.toString();
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public String getUserExceptionCode() {
		return userExceptionCode;
	}

	public void setUserExceptionCode(String userExceptionCode) {
		this.userExceptionCode = userExceptionCode;
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	public void setSubUserExceptionCode(String subUserExceptionCode) {
		this.subUserExceptionCode = subUserExceptionCode;
	}

	public String getConcreteExceptionCode() {
		return concreteExceptionCode;
	}

	public void setConcreteExceptionCode(String concreteExceptionCode) {
		this.concreteExceptionCode = concreteExceptionCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ExceptionGrade getGrade() {
		return grade;
	}

	public void setGrade(ExceptionGrade grade) {
		this.grade = grade;
	}

	public String getExceptionKind() {
		return exceptionKind;
	}
	
	public String getIdentifyFlag() {
		return identifyFlag;
	}
	

	private static final long serialVersionUID = 1L;
	
}
