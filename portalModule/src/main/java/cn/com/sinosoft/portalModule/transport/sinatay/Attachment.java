package cn.com.sinosoft.portalModule.transport.sinatay;

import java.io.Serializable;

/**
 * 影像通知DTO
 *
 */
public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 单证类型 */
	private String subType;
	
	/** 页码 */
	private String pageCode;
	
	/** 文件名称 */
	private String fileName;
	
	/** 文件路径 */
	private String picPath;

	/**
	 * 属性 subType 的 getter 方法
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}

	/**
	 * 属性 subType 的 setter 方法
	 * @param subType the subType to set
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}

	/**
	 * 属性 pageCode 的 getter 方法
	 * @return the pageCode
	 */
	public String getPageCode() {
		return pageCode;
	}

	/**
	 * 属性 pageCode 的 setter 方法
	 * @param pageCode the pageCode to set
	 */
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	/**
	 * 属性 fileName 的 getter 方法
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 属性 fileName 的 setter 方法
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 属性 picPath 的 getter 方法
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}

	/**
	 * 属性 picPath 的 setter 方法
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
}
