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
	
	/** ���� ��� */
	private String serialNo;
	
	/** ���� ֧������ */
	private String payType;
	
	/** ���� ֧������ */
	private String payName;
	
	/** ���� ֧������ */
	private String payDesc;
	
	/** ���� ���п������� */
	private String accType;
	
	/** ���� ״̬(0-δ��ͨ  1 -��ͨ) */
	private String status;
	
	/** ���� ����Ա */
	private String operatorID;
	
	/** ���� ����ʱ�� */
	private Date createTime = new Date();

	/** ���� ����ʱ�� */
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
