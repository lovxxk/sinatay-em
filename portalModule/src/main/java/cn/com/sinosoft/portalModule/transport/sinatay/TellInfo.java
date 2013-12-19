package cn.com.sinosoft.portalModule.transport.sinatay;

/**
 * ��֪DTO
 *
 */
public class TellInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	/** ��֪��� */
	private String tellVersion;
	
	/** ��֪���� */
	private String tellCode;
	
	/** ��֪���� */
	private String tellContent;
	
	/** ��֪��ע */
	private String tellRemark;
	
	/**
	 * ���� tellVersion �� getter ����
	 * @return the tellVersion
	 */
	public String getTellVersion() {
		return tellVersion;
	}
	/**
	 * ���� tellVersion �� setter ����
	 * @param tellVersion the tellVersion to set
	 */
	public void setTellVersion(String tellVersion) {
		this.tellVersion = tellVersion;
	}
	/**
	 * ���� tellCode �� getter ����
	 * @return the tellCode
	 */
	public String getTellCode() {
		return tellCode;
	}
	/**
	 * ���� tellCode �� setter ����
	 * @param tellCode the tellCode to set
	 */
	public void setTellCode(String tellCode) {
		this.tellCode = tellCode;
	}
	/**
	 * ���� tellContent �� getter ����
	 * @return the tellContent
	 */
	public String getTellContent() {
		return tellContent;
	}
	/**
	 * ���� tellContent �� setter ����
	 * @param tellContent the tellContent to set
	 */
	public void setTellContent(String tellContent) {
		this.tellContent = tellContent;
	}
	/**
	 * ���� tellRemark �� getter ����
	 * @return the tellRemark
	 */
	public String getTellRemark() {
		return tellRemark;
	}
	/**
	 * ���� tellRemark �� setter ����
	 * @param tellRemark the tellRemark to set
	 */
	public void setTellRemark(String tellRemark) {
		this.tellRemark = tellRemark;
	}
	/**
	 * ���� serialversionuid �� getter ����
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
