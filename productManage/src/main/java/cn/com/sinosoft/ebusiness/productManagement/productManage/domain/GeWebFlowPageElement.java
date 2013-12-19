package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeWebFlowPageElement
 */
@Entity
@Table(name = "GE_WEBFLOWPAGEELEMENT")
public class GeWebFlowPageElement implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// ���ݲ�Ʒ��GePageElementConfig
	
	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ����Ԫ������ */
	private GePageElementConfig gePageElementConfig;

	/** ��������ҳ�� */
	private GeWebFlowPage geWebFlowPage;

	/** ���Դ���״̬��'0'δ���ƣ�'1'�Ѷ��ƣ� */
	private String status;

	/** ������ʾ˳�� */
	private Integer seqIndex;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/**
	 * ��GeWebFlowPageElement��Ĭ�Ϲ��췽��
	 */
	public GeWebFlowPageElement() {
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
	 * ����Ԫ�����õ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ELEMENTCODE")
	public GePageElementConfig getGePageElementConfig() {
		return this.gePageElementConfig;
	}
	/**
	 * ����Ԫ�����õ�setter����
	 */
	public void setGePageElementConfig(GePageElementConfig gePageElementConfig) {
		this.gePageElementConfig = gePageElementConfig;
	}
	/**
	 * ��������ҳ���getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLOWPAGESERIALNO")
	public GeWebFlowPage getGeWebFlowPage() {
		return this.geWebFlowPage;
	}
	/**
	 * ��������ҳ���setter����
	 */
	public void setGeWebFlowPage(GeWebFlowPage geWebFlowPage) {
		this.geWebFlowPage = geWebFlowPage;
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

}
