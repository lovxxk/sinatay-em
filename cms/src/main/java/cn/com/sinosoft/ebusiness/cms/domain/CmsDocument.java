package cn.com.sinosoft.ebusiness.cms.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��CmsDocument
 */
@Entity
@Table(name = "CMS_DOCUMENT")
public class CmsDocument implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����DocID */
	private Integer docID;

	/** ����DocChannel */
	private String docChannel;

	/** ����DocVersion */
	private String docVersion;

	/** ����DocOrder */
	private BigDecimal docOrder;

	/** ����DocType */
	private String docType;

	/** ����DocTitle */
	private String docTitle;

	/** ����DocSource */
	private String docSource;

	/** ����DocStatus */
	private String docStatus;

	/** ����DocContent */
	private String docContent;

	/** ����DocHtmlCon */
	private String docHtmlCon;

	/** ����DocABS */
	private String docABS;

	/** ����DocKeysWord */
	private String docKeysWord;

	/** ����DocAuthor */
	private String docAuthor;

	/** ����DocMakeDate */
	private Date docMakeDate;

	/** ����DocPubDate */
	private Date docPubDate;

	/** ����HitsCount */
	private String hitsCount;

	/** ����Acctachpic */
	private String acctachpic;

	/** ����DocLink */
	private String docLink;

	/** ����Tempk */
	private String tempk;

	/** ����DocRelTime */
	private String docRelTime;

	/**
	 * ��CmsDocument��Ĭ�Ϲ��췽��
	 */
	public CmsDocument() {
	}

	/**
	 * ����DocID��getter����
	 */
	@Id
	@Column(name = "DOCID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_DOCUMENT_SEQ",allocationSize=1)
	public Integer getDocID() {
		return this.docID;
	}

	/**
	 * ����DocID��setter����
	 */
	public void setDocID(Integer docID) {
		this.docID = docID;
	}

	/**
	 * ����DocChannel��getter����
	 */

	@Column(name = "DOCCHANNEL")
	public String getDocChannel() {
		return this.docChannel;
	}

	/**
	 * ����DocChannel��setter����
	 */
	public void setDocChannel(String docChannel) {
		this.docChannel = docChannel;
	}

	/**
	 * ����DocVersion��getter����
	 */

	@Column(name = "DOCVERSION")
	public String getDocVersion() {
		return this.docVersion;
	}

	/**
	 * ����DocVersion��setter����
	 */
	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}

	/**
	 * ����DocOrder��getter����
	 */

	@Column(name = "DOCORDER")
	public BigDecimal getDocOrder() {
		return this.docOrder;
	}

	/**
	 * ����DocOrder��setter����
	 */
	public void setDocOrder(BigDecimal docOrder) {
		this.docOrder = docOrder;
	}

	/**
	 * ����DocType��getter����
	 */

	@Column(name = "DOCTYPE")
	public String getDocType() {
		return this.docType;
	}

	/**
	 * ����DocType��setter����
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}

	/**
	 * ����DocTitle��getter����
	 */

	@Column(name = "DOCTITLE")
	public String getDocTitle() {
		return this.docTitle;
	}

	/**
	 * ����DocTitle��setter����
	 */
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	/**
	 * ����DocSource��getter����
	 */

	@Column(name = "DOCSOURCE")
	public String getDocSource() {
		return this.docSource;
	}

	/**
	 * ����DocSource��setter����
	 */
	public void setDocSource(String docSource) {
		this.docSource = docSource;
	}

	/**
	 * ����DocStatus��getter����
	 */

	@Column(name = "DOCSTATUS")
	public String getDocStatus() {
		return this.docStatus;
	}

	/**
	 * ����DocStatus��setter����
	 */
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	/**
	 * ����DocContent��getter����
	 */

	@Column(name = "DOCCONTENT")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getDocContent() {
		return this.docContent;
	}

	/**
	 * ����DocContent��setter����
	 */
	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}

	/**
	 * ����DocHtmlCon��getter����
	 */

	@Column(name = "DOCHTMLCON")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getDocHtmlCon() {
		return this.docHtmlCon;
	}

	/**
	 * ����DocHtmlCon��setter����
	 */
	public void setDocHtmlCon(String docHtmlCon) {
		this.docHtmlCon = docHtmlCon;
	}

	/**
	 * ����DocABS��getter����
	 */

	@Column(name = "DOCABS")
	public String getDocABS() {
		return this.docABS;
	}

	/**
	 * ����DocABS��setter����
	 */
	public void setDocABS(String docABS) {
		this.docABS = docABS;
	}

	/**
	 * ����DocKeysWord��getter����
	 */

	@Column(name = "DOCKEYSWORD")
	public String getDocKeysWord() {
		return this.docKeysWord;
	}

	/**
	 * ����DocKeysWord��setter����
	 */
	public void setDocKeysWord(String docKeysWord) {
		this.docKeysWord = docKeysWord;
	}

	/**
	 * ����DocAuthor��getter����
	 */

	@Column(name = "DOCAUTHOR")
	public String getDocAuthor() {
		return this.docAuthor;
	}

	/**
	 * ����DocAuthor��setter����
	 */
	public void setDocAuthor(String docAuthor) {
		this.docAuthor = docAuthor;
	}

	/**
	 * ����DocMakeDate��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DOCMAKEDATE")
	public Date getDocMakeDate() {
		return this.docMakeDate;
	}

	/**
	 * ����DocMakeDate��setter����
	 */
	public void setDocMakeDate(Date docMakeDate) {
		this.docMakeDate = docMakeDate;
	}

	/**
	 * ����DocPubDate��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DOCPUBDATE")
	public Date getDocPubDate() {
		return this.docPubDate;
	}

	/**
	 * ����DocPubDate��setter����
	 */
	public void setDocPubDate(Date docPubDate) {
		this.docPubDate = docPubDate;
	}

	/**
	 * ����HitsCount��getter����
	 */

	@Column(name = "HITSCOUNT")
	public String getHitsCount() {
		return this.hitsCount;
	}

	/**
	 * ����HitsCount��setter����
	 */
	public void setHitsCount(String hitsCount) {
		this.hitsCount = hitsCount;
	}

	/**
	 * ����Acctachpic��getter����
	 */

	@Column(name = "ACCTACHPIC")
	public String getAcctachpic() {
		return this.acctachpic;
	}

	/**
	 * ����Acctachpic��setter����
	 */
	public void setAcctachpic(String acctachpic) {
		this.acctachpic = acctachpic;
	}

	/**
	 * ����DocLink��getter����
	 */

	@Column(name = "DOCLINK")
	public String getDocLink() {
		return this.docLink;
	}

	/**
	 * ����DocLink��setter����
	 */
	public void setDocLink(String docLink) {
		this.docLink = docLink;
	}

	/**
	 * ����Tempk��getter����
	 */

	@Column(name = "TEMPK")
	public String getTempk() {
		return this.tempk;
	}

	/**
	 * ����Tempk��setter����
	 */
	public void setTempk(String tempk) {
		this.tempk = tempk;
	}

	/**
	 * ����DocRelTime��getter����
	 */

	@Column(name = "DOCRELTIME")
	public String getDocRelTime() {
		return this.docRelTime;
	}

	/**
	 * ����DocRelTime��setter����
	 */
	public void setDocRelTime(String docRelTime) {
		this.docRelTime = docRelTime;
	}

}
