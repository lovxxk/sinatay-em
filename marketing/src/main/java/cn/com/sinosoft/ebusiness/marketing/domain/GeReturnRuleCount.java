package cn.com.sinosoft.ebusiness.marketing.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
/**
 * POJO��GeReturnRuleCount
 */
@Entity
@Table(name = "GE_RETURNRULE_COUNT")
public class GeReturnRuleCount implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Թ������ */
	private String ruleId;

	/** ���Ի��ʼʱ�� */
	private Date activityStartDate;

	/** ���Ի����ʱ�� */
	private Date activityEndDate;

	/** ���Ե��� */
	private String deptID;

	/** �������ֱ��� */
	private String riskCode;

	/** ������Ʒ���� */
	private String itemID;

	/** ���Ի��ʽ */
	private String activityPattern;

	/** ������Ʒ������ */
	private String itemCount;

	/**
	 * ��GeReturnRuleCount��Ĭ�Ϲ��췽��
	 */
	public GeReturnRuleCount() {
	}

	/**
	 * ���Թ�������getter����
	 */
	@Id
	@Column(name = "RULEID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getRuleId() {
		return this.ruleId;
	}

	/**
	 * ���Թ�������setter����
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * ���Ի��ʼʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITYSTARTDATE")
	public Date getActivityStartDate() {
		return this.activityStartDate;
	}

	/**
	 * ���Ի��ʼʱ���setter����
	 */
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	/**
	 * ���Ի����ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITYENDDATE")
	public Date getActivityEndDate() {
		return this.activityEndDate;
	}

	/**
	 * ���Ի����ʱ���setter����
	 */
	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	/**
	 * ���Ե�����getter����
	 */

	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}

	/**
	 * ���Ե�����setter����
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * �������ֱ����getter����
	 */

	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return this.riskCode;
	}

	/**
	 * �������ֱ����setter����
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	/**
	 * ������Ʒ�����getter����
	 */

	@Column(name = "ITEMID")
	public String getItemID() {
		return this.itemID;
	}

	/**
	 * ������Ʒ�����setter����
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * ���Ի��ʽ��getter����
	 */

	@Column(name = "ACTIVITYPATTERN")
	public String getActivityPattern() {
		return this.activityPattern;
	}

	/**
	 * ���Ի��ʽ��setter����
	 */
	public void setActivityPattern(String activityPattern) {
		this.activityPattern = activityPattern;
	}

	/**
	 * ������Ʒ��������getter����
	 */

	@Column(name = "ITEMCOUNT")
	public String getItemCount() {
		return this.itemCount;
	}

	/**
	 * ������Ʒ��������setter����
	 */
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}

}
