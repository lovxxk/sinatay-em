package cn.com.sinosoft.ebusiness.service.user.personal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��ActivityInfo
 */
@Entity
@Table(name = "TRANSMISSION_TIMES")
public class TransmissionTimes {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;
	
	/** �����˻� */
	private String account;
	
	/** ��������*/
	private Date transactionDate = new Date();
	
	/** ���Է��ʹ��� */
	private Integer trans_times;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * �������Ƶ�getter����
	 */
	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	/**
	 * �������Ƶ�getter����
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * ����������getter����
	 */
	@Column(name = "TRANS_TIMES")
	public Integer getTrans_Times() {
		return trans_times;
	}

	/**
	 * ����������getter����
	 */
	public void setTrans_Times(Integer trans_times) {
		this.trans_times = trans_times;
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	@JSON(serialize=false)  
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	@JSON(serialize=false)  
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * ��������
	 */
	@Temporal(TemporalType.DATE)
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
