package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.File;

/**
 * 
 * ������Ƕ�ļ���Ϣ����
 *
 */

public class InlineFile {
	private String inlineFileName;
	private File inlineFile;
	public File getInlineFile() {
		return inlineFile;
	}
	public void setInlineFile(File inlineFile) {
		this.inlineFile = inlineFile;
	}
	public String getInlineFileName() {
		return inlineFileName;
	}
	public void setInlineFileName(String inlineFileName) {
		this.inlineFileName = inlineFileName;
	}
}
