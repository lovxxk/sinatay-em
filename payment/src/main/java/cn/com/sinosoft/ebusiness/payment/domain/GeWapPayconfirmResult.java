package cn.com.sinosoft.ebusiness.payment.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO类geWapPapconfirmResult
 */
@Entity
@Table(name = "GE_WAP_PAYCONFIRM_RESULT")
public class GeWapPayconfirmResult implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 属性orderno */
	private String orderno;

	/** 属性proposalno */
	private String proposalno;

	/** 属性sendresult */
	private String sendresult;

	/** 属性sendcount */
	private BigDecimal sendcount;

	/** 属性createdate */
	private Date createdate;

	/** 属性modifydate */
	private Date modifydate;

	/** 属性标志位 */
	private String flag;

	/**
	 * 类geWapPapconfirmResult的默认构造方法
	 */
	public GeWapPayconfirmResult(){}

	/**
	 * 属性orderno的getter方法
	 */
	@Id
	@Column(name = "ORDERNO")
	public String getOrderno(){
		return this.orderno;
	}

	/**
	 * 属性orderno的setter方法
	 */
	public void setOrderno(String orderno){
		this.orderno = orderno;
	}

	/**
	 * 属性proposalno的getter方法
	 */

	@Column(name = "PROPOSALNO")
	public String getProposalno(){
		return this.proposalno;
	}

	/**
	 * 属性proposalno的setter方法
	 */
	public void setProposalno(String proposalno){
		this.proposalno = proposalno;
	}

	/**
	 * 属性sendresult的getter方法
	 */

	@Column(name = "SENDRESULT")
	public String getSendresult(){
		return this.sendresult;
	}

	/**
	 * 属性sendresult的setter方法
	 */
	public void setSendresult(String sendresult){
		this.sendresult = sendresult;
	}

	/**
	 * 属性sendcount的getter方法
	 */

	@Column(name = "SENDCOUNT")
	public BigDecimal getSendcount(){
		return this.sendcount;
	}

	/**
	 * 属性sendcount的setter方法
	 */
	public void setSendcount(BigDecimal sendcount){
		this.sendcount = sendcount;
	}

	/**
	 * 属性createdate的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreatedate(){
		return this.createdate;
	}

	/**
	 * 属性createdate的setter方法
	 */
	public void setCreatedate(Date createdate){
		this.createdate = createdate;
	}

	/**
	 * 属性modifydate的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFYDATE")
	public Date getModifydate(){
		return this.modifydate;
	}

	/**
	 * 属性modifydate的setter方法
	 */
	public void setModifydate(Date modifydate){
		this.modifydate = modifydate;
	}

	/**
	 * 属性标志位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag(){
		return this.flag;
	}

	/**
	 * 属性标志位的setter方法
	 */
	public void setFlag(String flag){
		this.flag = flag;
	}

}
