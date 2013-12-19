package cn.com.sinosoft.ebusiness.cms.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 * POJO类CmsDocument
 */
@Entity
@Table(name = "CMS_DOCUMENT")
public class CmsDocument implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性DocID */
	private Integer docID;

	/** 属性DocChannel */
	private String docChannel;

	/** 属性DocVersion */
	private String docVersion;

	/** 属性DocOrder */
	private BigDecimal docOrder;

	/** 属性DocType */
	private String docType;

	/** 属性DocTitle */
	private String docTitle;

	/** 属性DocSource */
	private String docSource;

	/** 属性DocStatus */
	private String docStatus;

	/** 属性DocContent */
	private String docContent;

	/** 属性DocHtmlCon */
	private String docHtmlCon;

	/** 属性DocABS */
	private String docABS;

	/** 属性DocKeysWord */
	private String docKeysWord;

	/** 属性DocAuthor */
	private String docAuthor;

	/** 属性DocMakeDate */
	private Date docMakeDate;

	/** 属性DocPubDate */
	private Date docPubDate;

	/** 属性HitsCount */
	private String hitsCount;

	/** 属性Acctachpic */
	private String acctachpic;

	/** 属性DocLink */
	private String docLink;

	/** 属性Tempk */
	private String tempk;

	/** 属性DocRelTime */
	private String docRelTime;

	/**
	 * 类CmsDocument的默认构造方法
	 */
	public CmsDocument() {
	}

	/**
	 * 属性DocID的getter方法
	 */
	@Id
	@Column(name = "DOCID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_DOCUMENT_SEQ",allocationSize=1)
	public Integer getDocID() {
		return this.docID;
	}

	/**
	 * 属性DocID的setter方法
	 */
	public void setDocID(Integer docID) {
		this.docID = docID;
	}

	/**
	 * 属性DocChannel的getter方法
	 */

	@Column(name = "DOCCHANNEL")
	public String getDocChannel() {
		return this.docChannel;
	}

	/**
	 * 属性DocChannel的setter方法
	 */
	public void setDocChannel(String docChannel) {
		this.docChannel = docChannel;
	}

	/**
	 * 属性DocVersion的getter方法
	 */

	@Column(name = "DOCVERSION")
	public String getDocVersion() {
		return this.docVersion;
	}

	/**
	 * 属性DocVersion的setter方法
	 */
	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}

	/**
	 * 属性DocOrder的getter方法
	 */

	@Column(name = "DOCORDER")
	public BigDecimal getDocOrder() {
		return this.docOrder;
	}

	/**
	 * 属性DocOrder的setter方法
	 */
	public void setDocOrder(BigDecimal docOrder) {
		this.docOrder = docOrder;
	}

	/**
	 * 属性DocType的getter方法
	 */

	@Column(name = "DOCTYPE")
	public String getDocType() {
		return this.docType;
	}

	/**
	 * 属性DocType的setter方法
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}

	/**
	 * 属性DocTitle的getter方法
	 */

	@Column(name = "DOCTITLE")
	public String getDocTitle() {
		return this.docTitle;
	}

	/**
	 * 属性DocTitle的setter方法
	 */
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	/**
	 * 属性DocSource的getter方法
	 */

	@Column(name = "DOCSOURCE")
	public String getDocSource() {
		return this.docSource;
	}

	/**
	 * 属性DocSource的setter方法
	 */
	public void setDocSource(String docSource) {
		this.docSource = docSource;
	}

	/**
	 * 属性DocStatus的getter方法
	 */

	@Column(name = "DOCSTATUS")
	public String getDocStatus() {
		return this.docStatus;
	}

	/**
	 * 属性DocStatus的setter方法
	 */
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	/**
	 * 属性DocContent的getter方法
	 */

	@Column(name = "DOCCONTENT")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getDocContent() {
		return this.docContent;
	}

	/**
	 * 属性DocContent的setter方法
	 */
	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}

	/**
	 * 属性DocHtmlCon的getter方法
	 */

	@Column(name = "DOCHTMLCON")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getDocHtmlCon() {
		return this.docHtmlCon;
	}

	/**
	 * 属性DocHtmlCon的setter方法
	 */
	public void setDocHtmlCon(String docHtmlCon) {
		this.docHtmlCon = docHtmlCon;
	}

	/**
	 * 属性DocABS的getter方法
	 */

	@Column(name = "DOCABS")
	public String getDocABS() {
		return this.docABS;
	}

	/**
	 * 属性DocABS的setter方法
	 */
	public void setDocABS(String docABS) {
		this.docABS = docABS;
	}

	/**
	 * 属性DocKeysWord的getter方法
	 */

	@Column(name = "DOCKEYSWORD")
	public String getDocKeysWord() {
		return this.docKeysWord;
	}

	/**
	 * 属性DocKeysWord的setter方法
	 */
	public void setDocKeysWord(String docKeysWord) {
		this.docKeysWord = docKeysWord;
	}

	/**
	 * 属性DocAuthor的getter方法
	 */

	@Column(name = "DOCAUTHOR")
	public String getDocAuthor() {
		return this.docAuthor;
	}

	/**
	 * 属性DocAuthor的setter方法
	 */
	public void setDocAuthor(String docAuthor) {
		this.docAuthor = docAuthor;
	}

	/**
	 * 属性DocMakeDate的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DOCMAKEDATE")
	public Date getDocMakeDate() {
		return this.docMakeDate;
	}

	/**
	 * 属性DocMakeDate的setter方法
	 */
	public void setDocMakeDate(Date docMakeDate) {
		this.docMakeDate = docMakeDate;
	}

	/**
	 * 属性DocPubDate的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DOCPUBDATE")
	public Date getDocPubDate() {
		return this.docPubDate;
	}

	/**
	 * 属性DocPubDate的setter方法
	 */
	public void setDocPubDate(Date docPubDate) {
		this.docPubDate = docPubDate;
	}

	/**
	 * 属性HitsCount的getter方法
	 */

	@Column(name = "HITSCOUNT")
	public String getHitsCount() {
		return this.hitsCount;
	}

	/**
	 * 属性HitsCount的setter方法
	 */
	public void setHitsCount(String hitsCount) {
		this.hitsCount = hitsCount;
	}

	/**
	 * 属性Acctachpic的getter方法
	 */

	@Column(name = "ACCTACHPIC")
	public String getAcctachpic() {
		return this.acctachpic;
	}

	/**
	 * 属性Acctachpic的setter方法
	 */
	public void setAcctachpic(String acctachpic) {
		this.acctachpic = acctachpic;
	}

	/**
	 * 属性DocLink的getter方法
	 */

	@Column(name = "DOCLINK")
	public String getDocLink() {
		return this.docLink;
	}

	/**
	 * 属性DocLink的setter方法
	 */
	public void setDocLink(String docLink) {
		this.docLink = docLink;
	}

	/**
	 * 属性Tempk的getter方法
	 */

	@Column(name = "TEMPK")
	public String getTempk() {
		return this.tempk;
	}

	/**
	 * 属性Tempk的setter方法
	 */
	public void setTempk(String tempk) {
		this.tempk = tempk;
	}

	/**
	 * 属性DocRelTime的getter方法
	 */

	@Column(name = "DOCRELTIME")
	public String getDocRelTime() {
		return this.docRelTime;
	}

	/**
	 * 属性DocRelTime的setter方法
	 */
	public void setDocRelTime(String docRelTime) {
		this.docRelTime = docRelTime;
	}

}
