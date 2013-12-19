package cn.com.sinosoft.ebusiness.payment.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO��GeAddServiceActivity
 */
@Entity
@Table(name = "GE_MER_DEPT")
public class GeMerDept implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �����̻�ID */
	private String merID;

	/** �����̻����� */
	private String merName;

	/** ���Ի���ID */
	private String deptID;

	/** ���Ի���1 ���������2���������� */
	private String payFlag;
	/**
	 * ��GeAddServiceActivity��Ĭ�Ϲ��췽��
	 */
	public GeMerDept() {
	}

	@Id
	@Column(name = "MERID")
	public String getMerID() {
		return merID;
	}

	public void setMerID(String merID) {
		this.merID = merID;
	}

	@Column(name = "MERNAME")
	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}
	
	@Column(name = "DEPTID")
	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	
	@Column(name = "PAYFLAG")
	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
}
