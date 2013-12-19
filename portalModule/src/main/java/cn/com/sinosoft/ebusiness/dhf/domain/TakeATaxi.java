package cn.com.sinosoft.ebusiness.dhf.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;

@Entity
@Table(name = "TAKEATAXI", uniqueConstraints = @UniqueConstraint(columnNames = "POLICYSERIALNO"))
public class TakeATaxi  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 属性序号 */
	private String serialNo;
	private String carNo;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String startPlace;
	private String acrossFlag;
	/** 属性保单 */
	private InsurancePolicy insurancePolicy;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	@Column(name = "CARNO")
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "STARTDATE")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "STARTTIME")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "ENDDATE")
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "ENDTIME")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "STARTPLACE")
	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	@Column(name = "ACROSSFLAG")
	public String getAcrossFlag() {
		return acrossFlag;
	}

	public void setAcrossFlag(String acrossFlag) {
		this.acrossFlag = acrossFlag;
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	
	/**
	 * 属性保单的getter方法
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POLICYSERIALNO", unique = true, nullable = false)
	public InsurancePolicy getInsurancePolicy() {
		return this.insurancePolicy;
	}

	/**
	 * 属性保单的setter方法
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}
	
	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
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
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
