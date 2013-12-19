package cn.com.sinosoft.util.mail;

import java.io.File;

import javax.activation.FileDataSource;

import org.apache.commons.lang.StringUtils;

import cn.com.sinosoft.util.io.FilenameUtils;

public class InlineFile {
	
	private String contentId;

	private String absoluteFileName;
	
	private File inlineFile;
	
	private FileDataSource source;
	
	public InlineFile(String absoluteFileName) {
		super();
		this.absoluteFileName = absoluteFileName;
	}
	
	
	public InlineFile() {
		super();
	}


	public InlineFile(String contentId, File inlineFile) {
		super();
		this.contentId = contentId;
		this.inlineFile = inlineFile;
	}


	public InlineFile(String contentId, FileDataSource source) {
		super();
		this.contentId = contentId;
		this.source = source;
	}


	public InlineFile(String contentId, String absoluteFileName) {
		super();
		this.contentId = contentId;
		this.absoluteFileName = absoluteFileName;
	}


	public String getContentId() {
		if (StringUtils.isBlank(contentId)) {
			if (getInlineFile() != null) {
				return getInlineFile().getName();
			} else if (StringUtils.isNotBlank(absoluteFileName)) {
				return FilenameUtils.getBaseName(absoluteFileName);
			} else {
				return "";
			}
		}
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public File getInlineFile() {
		if (inlineFile == null) {
			if (StringUtils.isNotBlank(absoluteFileName)) {
				return new File(absoluteFileName);
			}
		}
		return inlineFile;
	}

	public void setInlineFile(File inlineFile) {
		this.inlineFile = inlineFile;
	}

	public FileDataSource getSource() {
		if (source == null) {
			if (inlineFile != null) {
				return new FileDataSource(inlineFile);
			} else if (StringUtils.isNotBlank(absoluteFileName)) {
				return new FileDataSource(absoluteFileName);
			} else {
				return null;
			}
		}
		return source;
	}

	public void setSource(FileDataSource source) {
		this.source = source;
	}
	
	
}
