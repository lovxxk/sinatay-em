package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeWebFlow
 */
@Entity
@Table(name = "GE_WEBFLOW")
public class GeWebFlow implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// �󶨲�Ʒ
	
	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ������������ */
	private GeFlowConfig geFlowConfig;

	/** ���Դ���״̬��'0'δ���ƣ�'1'�Ѷ��ƣ� */
	private String status;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ����geWebFlowPages */
	private List<GeWebFlowPage> geWebFlowPages = new ArrayList<GeWebFlowPage>(0);

	/**
	 * ��GeWebFlow��Ĭ�Ϲ��췽��
	 */
	public GeWebFlow() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}
	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * ���Բ�Ʒ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE")
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * ���Բ�Ʒ��setter����
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * �����������õ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLOWCODE")
	public GeFlowConfig getGeFlowConfig() {
		return this.geFlowConfig;
	}
	/**
	 * �����������õ�setter����
	 */
	public void setGeFlowConfig(GeFlowConfig geFlowConfig) {
		this.geFlowConfig = geFlowConfig;
	}
	/**
	 * ���Դ���״̬��'0'δ���ƣ�'1'�Ѷ��ƣ���getter����
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}
	/**
	 * ���Դ���״̬��'0'δ���ƣ�'1'�Ѷ��ƣ���setter����
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * ����geWebFlowPages��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geWebFlow")
	public List<GeWebFlowPage> getGeWebFlowPages() {
		return this.geWebFlowPages;
	}
	/**
	 * ����geWebFlowPages��setter����
	 */
	public void setGeWebFlowPages(List<GeWebFlowPage> geWebFlowPages) {
		this.geWebFlowPages = geWebFlowPages;
	}

}
