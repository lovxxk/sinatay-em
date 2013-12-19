package cn.com.sinosoft.ebusiness.cms.util.pojo;

public class Document {
	private String docName;//文章标题
	private String docContent;//文章内容
	private String pubTime;//文章撰写时间
	private String author;//作者
	private String docID;//文章ID
	private String keyWord;//关键词
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocContent() {
		return docContent;
	}
	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}
	public String getPubTime() {
		return pubTime;
	}
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDocID() {
		return docID;
	}
	public void setDocID(String docID) {
		this.docID = docID;
	}
	
	
}
