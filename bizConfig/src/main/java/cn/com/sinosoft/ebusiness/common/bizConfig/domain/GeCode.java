package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// default package
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * POJO��GeCode
 */
@Entity
@Table(name = "GE_CODE")
public class GeCode implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����id */
	private GeCodeId id;

	/** ����geCodeType */
	private GeCodeType geCodeType;

	/** ������ʾ��� */
	private Integer orderNo;

	/** ���Դ��������� */
	private String codeCName;

	/** ���Դ���Ӣ���� */
	private String codeEName;

	/** ���Դ��뷱���� */
	private String codeTName;

	/** �����Ƿ���Ч��1:��Ч��0����Ч */
	private String validInd;

	/** ���Ա�־λ */
	private String flag;
	
	/**
	 * �������Ĵ���
	 */
	private String codeCoreRelation;
	/**
	 * ��GeCode��Ĭ�Ϲ��췽��
	 */
	public GeCode() {
	}

	/**
	 * ����id��getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeType", column = @Column(name = "CODETYPE")),
			@AttributeOverride(name = "codeCode", column = @Column(name = "CODECODE")) })
	public GeCodeId getId() {
		return this.id;
	}

	/**
	 * ����id��setter����
	 */
	public void setId(GeCodeId id) {
		this.id = id;
	}

	/**
	 * ����geCodeType��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODETYPE", nullable = false, insertable = false, updatable = false)
	public GeCodeType getGeCodeType() {
		return this.geCodeType;
	}

	/**
	 * ����geCodeType��setter����
	 */
	public void setGeCodeType(GeCodeType geCodeType) {
		this.geCodeType = geCodeType;
	}

	/**
	 * ������ʾ��ŵ�getter����
	 */

	@Column(name = "ORDERNO")
	public Integer getOrderNo() {
		return this.orderNo;
	}

	/**
	 * ������ʾ��ŵ�setter����
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * ���Դ�����������getter����
	 */

	@Column(name = "CODECNAME")
	public String getCodeCName() {
		return this.codeCName;
	}

	/**
	 * ���Դ�����������setter����
	 */
	public void setCodeCName(String codeCName) {
		this.codeCName = codeCName;
	}

	/**
	 * ���Դ���Ӣ������getter����
	 */

	@Column(name = "CODEENAME")
	public String getCodeEName() {
		return this.codeEName;
	}

	/**
	 * ���Դ���Ӣ������setter����
	 */
	public void setCodeEName(String codeEName) {
		this.codeEName = codeEName;
	}

	/**
	 * ���Դ��뷱������getter����
	 */

	@Column(name = "CODETNAME")
	public String getCodeTName() {
		return this.codeTName;
	}

	/**
	 * ���Դ��뷱������setter����
	 */
	public void setCodeTName(String codeTName) {
		this.codeTName = codeTName;
	}

	/**
	 * �����Ƿ���Ч��1:��Ч��0����Ч��getter����
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * �����Ƿ���Ч��1:��Ч��0����Ч��setter����
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	/**
	 * ���Ա�־λ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�־λ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	/**
	 * ���Թ������Ĵ����getter����
	 */
	@Column(name = "CODECORERELATION")
	public String getCodeCoreRelation() {
		return codeCoreRelation;
	}
	
	/**
	 * ���Թ������Ĵ����setter����
	 */
	public void setCodeCoreRelation(String codeCoreRelation) {
		this.codeCoreRelation = codeCoreRelation;
	}
	
}
