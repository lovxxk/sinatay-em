package cn.com.sinosoft.ebusiness.payment.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO类GeAddServiceActivity
 */
@Entity
@Table(name = "GE_MER_DEPT")
public class GeMerDept implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性商户ID */
	private String merID;

	/** 属性商户名称 */
	private String merName;

	/** 属性机构ID */
	private String deptID;

	/** 属性机构1 代表广银联2代码上银联 */
	private String payFlag;
	/**
	 * 类GeAddServiceActivity的默认构造方法
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
