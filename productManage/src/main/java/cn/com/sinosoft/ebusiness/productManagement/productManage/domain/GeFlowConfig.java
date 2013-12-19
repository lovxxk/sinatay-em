package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlow;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO��GeFlowConfig
 */
@Entity
@Table(name = "GE_FLOWCONFIG")
public class GeFlowConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// ������
	
	/** �������̴��� */
	private String flowCode;

	/** ������������ */
	private String flowName;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ���Ա�ע */
	private String remark;

	/** ����geFlowConfigRelates */
	private List<GeFlowConfigRelate> geFlowConfigRelates = new ArrayList<GeFlowConfigRelate>(
			0);

	/** ����geWebFlows */
	private List<GeWebFlow> geWebFlows = new ArrayList<GeWebFlow>(0);

	/** ����geSaleProWebFlows */
	private List<GeSaleProWebFlow> geSaleProWebFlows = new ArrayList<GeSaleProWebFlow>(
			0);

	/**
	 * ��GeFlowConfig��Ĭ�Ϲ��췽��
	 */
	public GeFlowConfig() {
	}

	/**
	 * �������̴����getter����
	 */
	@Id
	@Column(name = "FLOWCODE")
	public String getFlowCode() {
		return this.flowCode;
	}
	/**
	 * �������̴����setter����
	 */
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
	/**
	 * �����������Ƶ�getter����
	 */

	@Column(name = "FLOWNAME")
	public String getFlowName() {
		return this.flowName;
	}
	/**
	 * �����������Ƶ�setter����
	 */
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}
	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}
	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	 * ����geFlowConfigRelates��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geFlowConfig")
	public List<GeFlowConfigRelate> getGeFlowConfigRelates() {
		return this.geFlowConfigRelates;
	}
	/**
	 * ����geFlowConfigRelates��setter����
	 */
	public void setGeFlowConfigRelates(
			List<GeFlowConfigRelate> geFlowConfigRelates) {
		this.geFlowConfigRelates = geFlowConfigRelates;
	}
	/**
	 * ����geWebFlows��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geFlowConfig")
	public List<GeWebFlow> getGeWebFlows() {
		return this.geWebFlows;
	}
	/**
	 * ����geWebFlows��setter����
	 */
	public void setGeWebFlows(List<GeWebFlow> geWebFlows) {
		this.geWebFlows = geWebFlows;
	}
	/**
	 * ����geSaleProWebFlows��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geFlowConfig")
	public List<GeSaleProWebFlow> getGeSaleProWebFlows() {
		return this.geSaleProWebFlows;
	}
	/**
	 * ����geSaleProWebFlows��setter����
	 */
	public void setGeSaleProWebFlows(List<GeSaleProWebFlow> geSaleProWebFlows) {
		this.geSaleProWebFlows = geSaleProWebFlows;
	}

}
