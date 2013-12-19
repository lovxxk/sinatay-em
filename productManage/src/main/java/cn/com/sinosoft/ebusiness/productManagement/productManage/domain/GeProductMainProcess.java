package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeProductMainProcess
 */
@Entity
@Table(name = "GE_PRODUCTMAIN_PROCESS")
public class GeProductMainProcess implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性处理机构 */
	private String handleDept;

	/** 属性操作员编号 */
	private String operatorID;

	/** 属性处理时间 */
	private Date handleDate;

	/** 属性处理状态 */
	private String handleStatus;

	/** 属性处理后机构 */
	private String newHandleDept;

	/** 属性处理意见 */
	private String options;

	/** 属性备注 */
	private String remark;

	/** 属性标志位 */
	private String flag;

	/**
	 * 类GeProductMainProcess的默认构造方法
	 */
	public GeProductMainProcess() {
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
	 * 属性产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * 属性产品的setter方法
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * 属性处理机构的getter方法
	 */

	@Column(name = "HANDLEDEPT")
	public String getHandleDept() {
		return this.handleDept;
	}
	/**
	 * 属性处理机构的setter方法
	 */
	public void setHandleDept(String handleDept) {
		this.handleDept = handleDept;
	}
	/**
	 * 属性操作员编号的getter方法
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}
	/**
	 * 属性操作员编号的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	/**
	 * 属性处理时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HANDLEDATE")
	public Date getHandleDate() {
		return this.handleDate;
	}
	/**
	 * 属性处理时间的setter方法
	 */
	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}
	/**
	 * 属性处理状态的getter方法
	 */

	@Column(name = "HANDLESTATUS")
	public String getHandleStatus() {
		return this.handleStatus;
	}
	/**
	 * 属性处理状态的setter方法
	 */
	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
	/**
	 * 属性处理后机构的getter方法
	 */

	@Column(name = "NEWHANDLEDEPT")
	public String getNewHandleDept() {
		return this.newHandleDept;
	}
	/**
	 * 属性处理后机构的setter方法
	 */
	public void setNewHandleDept(String newHandleDept) {
		this.newHandleDept = newHandleDept;
	}
	/**
	 * 属性处理意见的getter方法
	 */

	@Column(name = "OPTIONS")
	public String getOptions() {
		return this.options;
	}
	/**
	 * 属性处理意见的setter方法
	 */
	public void setOptions(String options) {
		this.options = options;
	}
	/**
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}
	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 属性标志位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}
	/**
	 * 属性标志位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
