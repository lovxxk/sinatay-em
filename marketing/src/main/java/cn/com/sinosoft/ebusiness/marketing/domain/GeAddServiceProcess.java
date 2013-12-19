package cn.com.sinosoft.ebusiness.marketing.domain;


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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类   
 */
@Entity
@Table(name = "GE_ADDSERVICE_PROCESS")
public class GeAddServiceProcess implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性处理序号 */
	private String serialNo;

	/** 属性增值服务活动表-GeAddServiceActivity */
	private GeAddServiceActivity geAddServiceActivity;

	/** 属性当前处理机构 */
	private String handleDept;

	/** 属性操作人编号 */
	private String operatorID;

	/** 属性处理时间 */
	private Date handleDate;

	/** 属性处理后的状态 */
	private String handleStatus;

	/** 属性处理后机构 */
	private String newHandleDept;

	/** 属性处理意见 */
	private String options;

	/** 属性备注 */
	private String remark;

	/** 属性标识位 */
	private String flag;

	/**
	 * 类GeProposalIntentionProcess的默认构造方法
	 */
	public GeAddServiceProcess() {
	}

	/**
	 * 属性处理序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * 属性处理序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * 属性投保意向表-GeAddServiceActivity的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	@JsonIgnore
	public GeAddServiceActivity getGeAddServiceActivity() {
		return geAddServiceActivity;
	}

	/**
	 * 属性投保意向表-GeAddServiceActivity的setter方法
	 */
	public void setGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity) {
		this.geAddServiceActivity = geAddServiceActivity;
	}

	/**
	 * 属性当前处理机构的getter方法
	 */

	@Column(name = "HANDLEDEPT")
	public String getHandleDept() {
		return this.handleDept;
	}

	/**
	 * 属性当前处理机构的setter方法
	 */
	public void setHandleDept(String handleDept) {
		this.handleDept = handleDept;
	}

	/**
	 * 属性操作人编号的getter方法
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * 属性操作人编号的setter方法
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
	 * 属性处理后的状态的getter方法
	 */

	@Column(name = "HANDLESTATUS")
	public String getHandleStatus() {
		return this.handleStatus;
	}

	/**
	 * 属性处理后的状态的setter方法
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
	 * 属性标识位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标识位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
