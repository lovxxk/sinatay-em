package cn.com.sinosoft.ebusiness.payment.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO��geWapPapconfirmResult
 */
@Entity
@Table(name = "GE_WAP_PAYCONFIRM_RESULT")
public class GeWapPayconfirmResult implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** ����orderno */
	private String orderno;

	/** ����proposalno */
	private String proposalno;

	/** ����sendresult */
	private String sendresult;

	/** ����sendcount */
	private BigDecimal sendcount;

	/** ����createdate */
	private Date createdate;

	/** ����modifydate */
	private Date modifydate;

	/** ���Ա�־λ */
	private String flag;

	/**
	 * ��geWapPapconfirmResult��Ĭ�Ϲ��췽��
	 */
	public GeWapPayconfirmResult(){}

	/**
	 * ����orderno��getter����
	 */
	@Id
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
	 * ����sendresult��getter����
	 */

	@Column(name = "SENDRESULT")
	public String getSendresult(){
		return this.sendresult;
	}

	/**
	 * ����sendresult��setter����
	 */
	public void setSendresult(String sendresult){
		this.sendresult = sendresult;
	}

	/**
	 * ����sendcount��getter����
	 */

	@Column(name = "SENDCOUNT")
	public BigDecimal getSendcount(){
		return this.sendcount;
	}

	/**
	 * ����sendcount��setter����
	 */
	public void setSendcount(BigDecimal sendcount){
		this.sendcount = sendcount;
	}

	/**
	 * ����createdate��getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreatedate(){
		return this.createdate;
	}

	/**
	 * ����createdate��setter����
	 */
	public void setCreatedate(Date createdate){
		this.createdate = createdate;
	}

	/**
	 * ����modifydate��getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFYDATE")
	public Date getModifydate(){
		return this.modifydate;
	}

	/**
	 * ����modifydate��setter����
	 */
	public void setModifydate(Date modifydate){
		this.modifydate = modifydate;
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
