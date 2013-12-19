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
 * POJO��   
 */
@Entity
@Table(name = "GE_ADDSERVICE_PROCESS")
public class GeAddServiceProcess implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Դ������ */
	private String serialNo;

	/** ������ֵ������-GeAddServiceActivity */
	private GeAddServiceActivity geAddServiceActivity;

	/** ���Ե�ǰ������� */
	private String handleDept;

	/** ���Բ����˱�� */
	private String operatorID;

	/** ���Դ���ʱ�� */
	private Date handleDate;

	/** ���Դ�����״̬ */
	private String handleStatus;

	/** ���Դ������� */
	private String newHandleDept;

	/** ���Դ������ */
	private String options;

	/** ���Ա�ע */
	private String remark;

	/** ���Ա�ʶλ */
	private String flag;

	/**
	 * ��GeProposalIntentionProcess��Ĭ�Ϲ��췽��
	 */
	public GeAddServiceProcess() {
	}

	/**
	 * ���Դ�����ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * ���Դ�����ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * ����Ͷ�������-GeAddServiceActivity��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	@JsonIgnore
	public GeAddServiceActivity getGeAddServiceActivity() {
		return geAddServiceActivity;
	}

	/**
	 * ����Ͷ�������-GeAddServiceActivity��setter����
	 */
	public void setGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity) {
		this.geAddServiceActivity = geAddServiceActivity;
	}

	/**
	 * ���Ե�ǰ���������getter����
	 */

	@Column(name = "HANDLEDEPT")
	public String getHandleDept() {
		return this.handleDept;
	}

	/**
	 * ���Ե�ǰ���������setter����
	 */
	public void setHandleDept(String handleDept) {
		this.handleDept = handleDept;
	}

	/**
	 * ���Բ����˱�ŵ�getter����
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * ���Բ����˱�ŵ�setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HANDLEDATE")
	public Date getHandleDate() {
		return this.handleDate;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	/**
	 * ���Դ�����״̬��getter����
	 */

	@Column(name = "HANDLESTATUS")
	public String getHandleStatus() {
		return this.handleStatus;
	}

	/**
	 * ���Դ�����״̬��setter����
	 */
	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	/**
	 * ���Դ���������getter����
	 */

	@Column(name = "NEWHANDLEDEPT")
	public String getNewHandleDept() {
		return this.newHandleDept;
	}

	/**
	 * ���Դ���������setter����
	 */
	public void setNewHandleDept(String newHandleDept) {
		this.newHandleDept = newHandleDept;
	}

	/**
	 * ���Դ��������getter����
	 */

	@Column(name = "OPTIONS")
	public String getOptions() {
		return this.options;
	}

	/**
	 * ���Դ��������setter����
	 */
	public void setOptions(String options) {
		this.options = options;
	}

	/**
	 * ���Ա�ע��getter����
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���Ա�ע��setter����
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ���Ա�ʶλ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�ʶλ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
