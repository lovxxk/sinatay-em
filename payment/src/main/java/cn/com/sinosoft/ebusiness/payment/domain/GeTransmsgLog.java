package cn.com.sinosoft.ebusiness.payment.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * POJO类geTransmsgLog
 */
@Entity
@Table(name = "GE_TRANSMSG_LOG")
public class GeTransmsgLog implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 属性id */
	private String id;

	/** 属性sysflag */
	private String sysflag;

	/** 属性transflag */
	private String transflag;

	/** 属性orderno */
	private String orderno;

	/** 属性proposalno */
	private String proposalno;

	/** 属性reqmsg */
	private String reqmsg;

	/** 属性resmsg */
	private String resmsg;

	/** 属性reqtime */
	private Date reqtime;

	/** 属性restime */
	private Date restime;

	/** 属性resultinfo */
	private String resultinfo;

	/** 属性resultcode */
	private String resultcode;

	/** 属性标志位 */
	private String flag;

	/**
	 * 类geTransmsgLog的默认构造方法
	 */
	public GeTransmsgLog(){}

	/**
	 * 属性id的getter方法
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getId(){
		return this.id;
	}

	/**
	 * 属性id的setter方法
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * 属性sysflag的getter方法
	 */

	@Column(name = "SYSFLAG")
	public String getSysflag(){
		return this.sysflag;
	}

	/**
	 * 属性sysflag的setter方法
	 */
	public void setSysflag(String sysflag){
		this.sysflag = sysflag;
	}

	/**
	 * 属性transflag的getter方法
	 */

	@Column(name = "TRANSFLAG")
	public String getTransflag(){
		return this.transflag;
	}

	/**
	 * 属性transflag的setter方法
	 */
	public void setTransflag(String transflag){
		this.transflag = transflag;
	}

	/**
	 * 属性orderno的getter方法
	 */

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
	 * 属性reqmsg的getter方法
	 */

	@Column(name = "REQMSG")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getReqmsg(){
		return this.reqmsg;
	}

	/**
	 * 属性reqmsg的setter方法
	 */
	public void setReqmsg(String reqmsg){
		this.reqmsg = reqmsg;
	}

	/**
	 * 属性resmsg的getter方法
	 */

	@Column(name = "RESMSG")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getResmsg(){
		return this.resmsg;
	}

	/**
	 * 属性resmsg的setter方法
	 */
	public void setResmsg(String resmsg){
		this.resmsg = resmsg;
	}

	/**
	 * 属性reqtime的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQTIME")
	public Date getReqtime(){
		return this.reqtime;
	}

	/**
	 * 属性reqtime的setter方法
	 */
	public void setReqtime(Date reqtime){
		this.reqtime = reqtime;
	}

	/**
	 * 属性restime的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESTIME")
	public Date getRestime(){
		return this.restime;
	}

	/**
	 * 属性restime的setter方法
	 */
	public void setRestime(Date restime){
		this.restime = restime;
	}

	/**
	 * 属性resultinfo的getter方法
	 */

	@Column(name = "RESULTINFO")
	public String getResultinfo(){
		return this.resultinfo;
	}

	/**
	 * 属性resultinfo的setter方法
	 */
	public void setResultinfo(String resultinfo){
		this.resultinfo = resultinfo;
	}

	/**
	 * 属性resultcode的getter方法
	 */

	@Column(name = "RESULTCODE")
	public String getResultcode(){
		return this.resultcode;
	}

	/**
	 * 属性resultcode的setter方法
	 */
	public void setResultcode(String resultcode){
		this.resultcode = resultcode;
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
