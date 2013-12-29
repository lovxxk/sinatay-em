package cn.com.sinosoft.portalModule.portalInterface.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PORTAL_INTERFACE_PAYMENT")
public class PortalInterfacePayment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 属性 序号 */
	private String serialNo;
	
	/** 属性 支付类型 */
	private String payType;
	
	/** 属性 支付名称 */
	private String payName;
	
	/** 属性 支付描述 */
	private String payDesc;
	
	/** 属性 银行卡折类型 */
	private String accType;
	
	/** 属性 状态(0-未开通  1 -开通) */
	private String status;
	
	/** 属性 操作员 */
	private String operatorID;
	
	/** 属性 创建时间 */
	private Date createTime = new Date();

	/** 属性 更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * @return the serialNo
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * @return the payType
	 */
	@Column(name = "PAYTYPE")
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return the payName
	 */
	@Column(name = "PAYNAME")
	public String getPayName() {
		return payName;
	}

	/**
	 * @param payName the payName to set
	 */
	public void setPayName(String payName) {
		this.payName = payName;
	}

	/**
	 * @return the payDesc
	 */
	@Column(name = "PAYDESC")
	public String getPayDesc() {
		return payDesc;
	}

	/**
	 * @param payDesc the payDesc to set
	 */
	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

	/**
	 * @return the accType
	 */
	@Column(name = "ACCTYPE")
	public String getAccType() {
		return accType;
	}

	/**
	 * @param accType the accType to set
	 */
	public void setAccType(String accType) {
		this.accType = accType;
	}

	/**
	 * @return the status
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the operatorID
	 */
	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return operatorID;
	}

	/**
	 * @param operatorID the operatorID to set
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * @return the createTime
	 */
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
