package cn.com.sinosoft.portalModule.transport.sinatay;

/**
 * 告知DTO
 *
 */
public class TellInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 告知版别 */
	private String tellVersion;
	
	/** 告知编码 */
	private String tellCode;
	
	/** 告知内容 */
	private String tellContent;
	
	/** 告知备注 */
	private String tellRemark;
	
	/**
	 * 属性 tellVersion 的 getter 方法
	 * @return the tellVersion
	 */
	public String getTellVersion() {
		return tellVersion;
	}
	/**
	 * 属性 tellVersion 的 setter 方法
	 * @param tellVersion the tellVersion to set
	 */
	public void setTellVersion(String tellVersion) {
		this.tellVersion = tellVersion;
	}
	/**
	 * 属性 tellCode 的 getter 方法
	 * @return the tellCode
	 */
	public String getTellCode() {
		return tellCode;
	}
	/**
	 * 属性 tellCode 的 setter 方法
	 * @param tellCode the tellCode to set
	 */
	public void setTellCode(String tellCode) {
		this.tellCode = tellCode;
	}
	/**
	 * 属性 tellContent 的 getter 方法
	 * @return the tellContent
	 */
	public String getTellContent() {
		return tellContent;
	}
	/**
	 * 属性 tellContent 的 setter 方法
	 * @param tellContent the tellContent to set
	 */
	public void setTellContent(String tellContent) {
		this.tellContent = tellContent;
	}
	/**
	 * 属性 tellRemark 的 getter 方法
	 * @return the tellRemark
	 */
	public String getTellRemark() {
		return tellRemark;
	}
	/**
	 * 属性 tellRemark 的 setter 方法
	 * @param tellRemark the tellRemark to set
	 */
	public void setTellRemark(String tellRemark) {
		this.tellRemark = tellRemark;
	}
	/**
	 * 属性 serialversionuid 的 getter 方法
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
