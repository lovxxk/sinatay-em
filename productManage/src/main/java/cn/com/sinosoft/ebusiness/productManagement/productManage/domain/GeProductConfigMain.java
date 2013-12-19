package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeProductConfigMain
 */
@Entity
@Table(name = "GE_PRODUCT_CONFIGMAIN")
public class GeProductConfigMain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ���Բ�Ʒ������Ϣ  */
	private String productInfo;

	/** ���Ե����ʾ� */
	private String question;

	/** ���Ա�������  */
	private String calculation;

	/** ���Է������� */
	private String legalNotices;

	/** ����Ͷ����֪ */
	private String inform;

	/** ����Ͷ������  */
	private String proposalNotices;

	/** ���Ա������� */
	private String policy;

	/**
	 * ��GeProductConfigMain��Ĭ�Ϲ��췽��
	 */
	public GeProductConfigMain() {
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
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
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
	 * ���Բ�Ʒ������Ϣ ��getter����
	 */

	@Column(name = "PRODUCTINFO")
	public String getProductInfo() {
		return this.productInfo;
	}
	/**
	 * ���Բ�Ʒ������Ϣ ��setter����
	 */
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	/**
	 * ���Ե����ʾ��getter����
	 */

	@Column(name = "QUESTION")
	public String getQuestion() {
		return this.question;
	}
	/**
	 * ���Ե����ʾ��setter����
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * ���Ա������� ��getter����
	 */

	@Column(name = "CALCULATION")
	public String getCalculation() {
		return this.calculation;
	}
	/**
	 * ���Ա������� ��setter����
	 */
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
	/**
	 * ���Է���������getter����
	 */

	@Column(name = "LEGALNOTICES")
	public String getLegalNotices() {
		return this.legalNotices;
	}
	/**
	 * ���Է���������setter����
	 */
	public void setLegalNotices(String legalNotices) {
		this.legalNotices = legalNotices;
	}
	/**
	 * ����Ͷ����֪��getter����
	 */

	@Column(name = "INFORM")
	public String getInform() {
		return this.inform;
	}
	/**
	 * ����Ͷ����֪��setter����
	 */
	public void setInform(String inform) {
		this.inform = inform;
	}
	/**
	 * ����Ͷ������ ��getter����
	 */

	@Column(name = "PROPOSALNOTICES")
	public String getProposalNotices() {
		return this.proposalNotices;
	}
	/**
	 * ����Ͷ������ ��setter����
	 */
	public void setProposalNotices(String proposalNotices) {
		this.proposalNotices = proposalNotices;
	}
	/**
	 * ���Ա������õ�getter����
	 */

	@Column(name = "POLICY")
	public String getPolicy() {
		return this.policy;
	}
	/**
	 * ���Ա������õ�setter����
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}

}
