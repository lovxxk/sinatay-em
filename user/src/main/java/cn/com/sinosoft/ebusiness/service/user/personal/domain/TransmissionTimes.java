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
 * POJO类ActivityInfo
 */
@Entity
@Table(name = "TRANSMISSION_TIMES")
public class TransmissionTimes {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;
	
	/** 属性账户 */
	private String account;
	
	/** 交易日期*/
	private Date transactionDate = new Date();
	
	/** 属性发送次数 */
	private Integer trans_times;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * 属性名称的getter方法
	 */
	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	/**
	 * 属性名称的getter方法
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 属性描述的getter方法
	 */
	@Column(name = "TRANS_TIMES")
	public Integer getTrans_Times() {
		return trans_times;
	}

	/**
	 * 属性描述的getter方法
	 */
	public void setTrans_Times(Integer trans_times) {
		this.trans_times = trans_times;
	}

	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	@JSON(serialize=false)  
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	@JSON(serialize=false)  
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 交易日期
	 */
	@Temporal(TemporalType.DATE)
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
