package cn.com.sinosoft.ebusiness.payment.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��geTransmsgLog
 */
@Entity
@Table(name = "GE_TRANSMSG_LOG")
public class GeTransmsgLog implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** ����id */
	private String id;

	/** ����sysflag */
	private String sysflag;

	/** ����transflag */
	private String transflag;

	/** ����orderno */
	private String orderno;

	/** ����proposalno */
	private String proposalno;

	/** ����reqmsg */
	private String reqmsg;

	/** ����resmsg */
	private String resmsg;

	/** ����reqtime */
	private Date reqtime;

	/** ����restime */
	private Date restime;

	/** ����resultinfo */
	private String resultinfo;

	/** ����resultcode */
	private String resultcode;

	/** ���Ա�־λ */
	private String flag;

	/**
	 * ��geTransmsgLog��Ĭ�Ϲ��췽��
	 */
	public GeTransmsgLog(){}

	/**
	 * ����id��getter����
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getId(){
		return this.id;
	}

	/**
	 * ����id��setter����
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * ����sysflag��getter����
	 */

	@Column(name = "SYSFLAG")
	public String getSysflag(){
		return this.sysflag;
	}

	/**
	 * ����sysflag��setter����
	 */
	public void setSysflag(String sysflag){
		this.sysflag = sysflag;
	}

	/**
	 * ����transflag��getter����
	 */

	@Column(name = "TRANSFLAG")
	public String getTransflag(){
		return this.transflag;
	}

	/**
	 * ����transflag��setter����
	 */
	public void setTransflag(String transflag){
		this.transflag = transflag;
	}

	/**
	 * ����orderno��getter����
	 */

	@Column(name = "ORDERNO")
	public String getOrderno(){
		return this.orderno;
	}

	/**
	 * ����orderno��setter����
	 */
	public void setOrderno(String orderno){
		this.orderno = orderno;
	}

	/**
	 * ����proposalno��getter����
	 */

	@Column(name = "PROPOSALNO")
	public String getProposalno(){
		return this.proposalno;
	}

	/**
	 * ����proposalno��setter����
	 */
	public void setProposalno(String proposalno){
		this.proposalno = proposalno;
	}

	/**
	 * ����reqmsg��getter����
	 */

	@Column(name = "REQMSG")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getReqmsg(){
		return this.reqmsg;
	}

	/**
	 * ����reqmsg��setter����
	 */
	public void setReqmsg(String reqmsg){
		this.reqmsg = reqmsg;
	}

	/**
	 * ����resmsg��getter����
	 */

	@Column(name = "RESMSG")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getResmsg(){
		return this.resmsg;
	}

	/**
	 * ����resmsg��setter����
	 */
	public void setResmsg(String resmsg){
		this.resmsg = resmsg;
	}

	/**
	 * ����reqtime��getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQTIME")
	public Date getReqtime(){
		return this.reqtime;
	}

	/**
	 * ����reqtime��setter����
	 */
	public void setReqtime(Date reqtime){
		this.reqtime = reqtime;
	}

	/**
	 * ����restime��getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESTIME")
	public Date getRestime(){
		return this.restime;
	}

	/**
	 * ����restime��setter����
	 */
	public void setRestime(Date restime){
		this.restime = restime;
	}

	/**
	 * ����resultinfo��getter����
	 */

	@Column(name = "RESULTINFO")
	public String getResultinfo(){
		return this.resultinfo;
	}

	/**
	 * ����resultinfo��setter����
	 */
	public void setResultinfo(String resultinfo){
		this.resultinfo = resultinfo;
	}

	/**
	 * ����resultcode��getter����
	 */

	@Column(name = "RESULTCODE")
	public String getResultcode(){
		return this.resultcode;
	}

	/**
	 * ����resultcode��setter����
	 */
	public void setResultcode(String resultcode){
		this.resultcode = resultcode;
	}

	/**
	 * ���Ա�־λ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag(){
		return this.flag;
	}

	/**
	 * ���Ա�־λ��setter����
	 */
	public void setFlag(String flag){
		this.flag = flag;
	}

}
