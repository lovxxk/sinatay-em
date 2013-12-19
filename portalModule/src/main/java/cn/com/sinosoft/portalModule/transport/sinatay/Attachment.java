package cn.com.sinosoft.portalModule.transport.sinatay;

import java.io.Serializable;

/**
 * Ӱ��֪ͨDTO
 *
 */
public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ��֤���� */
	private String subType;
	
	/** ҳ�� */
	private String pageCode;
	
	/** �ļ����� */
	private String fileName;
	
	/** �ļ�·�� */
	private String picPath;

	/**
	 * ���� subType �� getter ����
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}

	/**
	 * ���� subType �� setter ����
	 * @param subType the subType to set
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}

	/**
	 * ���� pageCode �� getter ����
	 * @return the pageCode
	 */
	public String getPageCode() {
		return pageCode;
	}

	/**
	 * ���� pageCode �� setter ����
	 * @param pageCode the pageCode to set
	 */
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	/**
	 * ���� fileName �� getter ����
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * ���� fileName �� setter ����
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * ���� picPath �� getter ����
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}

	/**
	 * ���� picPath �� setter ����
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
}
