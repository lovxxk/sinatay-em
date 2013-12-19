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
 * POJO��GeWebFlowPage
 */
@Entity
@Table(name = "GE_WEBFLOWPAGE")
public class GeWebFlowPage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// ���ݲ�Ʒ��gePageConfig
	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ����ҳ���������� */
	private GeWebFlow geWebFlow;

	/** ����ҳ������ */
	private GePageConfig gePageConfig;

	/** ���Դ���״̬��'0'δ���ƣ�'1'�Ѷ��ƣ� */
	private String status;

	/** ������ʾ˳�� */
	private Integer seqIndex;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ������ʾ״̬ */
	private String isShow;

	/** ����geWebFlowPageElements */
	private List<GeWebFlowPageElement> geWebFlowPageElements = new ArrayList<GeWebFlowPageElement>(
			0);

	/**
	 * ��GeWebFlowPage��Ĭ�Ϲ��췽��
	 */
	public GeWebFlowPage() {
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
	 * ����ҳ���������õ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WEBFLOWSERCIALNO")
	public GeWebFlow getGeWebFlow() {
		return this.geWebFlow;
	}
	/**
	 * ����ҳ���������õ�setter����
	 */
	public void setGeWebFlow(GeWebFlow geWebFlow) {
		this.geWebFlow = geWebFlow;
	}
	/**
	 * ����ҳ�����õ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAGECODE")
	public GePageConfig getGePageConfig() {
		return this.gePageConfig;
	}
	/**
	 * ����ҳ�����õ�setter����
	 */
	public void setGePageConfig(GePageConfig gePageConfig) {
		this.gePageConfig = gePageConfig;
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
	 * ������ʾ˳���getter����
	 */

	@Column(name = "SEQINDEX")
	public Integer getSeqIndex() {
		return this.seqIndex;
	}
	/**
	 * ������ʾ˳���setter����
	 */
	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
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
	 * ������ʾ״̬��getter����
	 */

	@Column(name = "ISSHOW")
	public String getIsShow() {
		return this.isShow;
	}
	/**
	 * ������ʾ״̬��setter����
	 */
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	/**
	 * ����geWebFlowPageElements��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geWebFlowPage")
	public List<GeWebFlowPageElement> getGeWebFlowPageElements() {
		return this.geWebFlowPageElements;
	}
	/**
	 * ����geWebFlowPageElements��setter����
	 */
	public void setGeWebFlowPageElements(
			List<GeWebFlowPageElement> geWebFlowPageElements) {
		this.geWebFlowPageElements = geWebFlowPageElements;
	}

}
