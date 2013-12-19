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
 * POJO��GeProInsuredOccupation
 */
@Entity
@Table(name = "GE_PRO_INSURED_OCCUPATION")
public class GeProInsuredOccupation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Ա��������� */
	private GeProductInsuredConfig geProductInsuredConfig;

	/** ����ְҵ���� */
	private String occupationCode;

	/**
	 * ��GeProInsuredOccupation��Ĭ�Ϲ��췽��
	 */
	public GeProInsuredOccupation() {
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
	 * ���Ա��������õ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSUREDSERIALNO")
	public GeProductInsuredConfig getGeProductInsuredConfig() {
		return this.geProductInsuredConfig;
	}
	/**
	 * ���Ա��������õ�setter����
	 */
	public void setGeProductInsuredConfig(
			GeProductInsuredConfig geProductInsuredConfig) {
		this.geProductInsuredConfig = geProductInsuredConfig;
	}
	/**
	 * ����ְҵ�����getter����
	 */

	@Column(name = "OCCUPATIONCODE")
	public String getOccupationCode() {
		return this.occupationCode;
	}
	/**
	 * ����ְҵ�����setter����
	 */
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

}
