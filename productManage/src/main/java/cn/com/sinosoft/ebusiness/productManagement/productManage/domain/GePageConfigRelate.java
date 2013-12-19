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
 * POJO��GePageConfigRelate
 */
@Entity
@Table(name = "GE_PAGECONFIGRELATE")
public class GePageConfigRelate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// �������̣�ҳ��--���ԣ���ҳ�棩�м��
	
	/** ������� */
	private String serialNo;

	/** ����Ԫ������ */
	private GePageElementConfig gePageElementConfig;

	/** ����ҳ������ */
	private GePageConfig gePageConfig;

	/** ����Ĭ��˳�� */
	private Integer defaultSeqIndex;

	/** �����Ƿ�����༭��Y����༭��N������༭�� */
	private String editable;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ���Ա�ע */
	private String remark;

	/** �����Ƿ���� */
	private String required;

	/**
	 * ��GePageConfigRelate��Ĭ�Ϲ��췽��
	 */
	public GePageConfigRelate() {
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
	 * ����Ĭ��˳���getter����
	 */

	@Column(name = "DEFAULTSEQINDEX")
	public Integer getDefaultSeqIndex() {
		return this.defaultSeqIndex;
	}
	/**
	 * ����Ĭ��˳���setter����
	 */
	public void setDefaultSeqIndex(Integer defaultSeqIndex) {
		this.defaultSeqIndex = defaultSeqIndex;
	}
	/**
	 * �����Ƿ�����༭��Y����༭��N������༭����getter����
	 */

	@Column(name = "EDITABLE")
	public String getEditable() {
		return this.editable;
	}
	/**
	 * �����Ƿ�����༭��Y����༭��N������༭����setter����
	 */
	public void setEditable(String editable) {
		this.editable = editable;
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
	 * �����Ƿ�����getter����
	 */

	@Column(name = "REQUIRED")
	public String getRequired() {
		return this.required;
	}
	/**
	 * �����Ƿ�����setter����
	 */
	public void setRequired(String required) {
		this.required = required;
	}

}
