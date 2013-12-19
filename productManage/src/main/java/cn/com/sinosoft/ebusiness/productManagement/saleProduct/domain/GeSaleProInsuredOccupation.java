package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
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
 * POJO��GeSaleProInsuredOccupation
 */
@Entity
@Table(name = "GE_SALE_PRO_INSURED_OCCUPATION")
public class GeSaleProInsuredOccupation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���� */
	private GeSaleProInsuredConfig geSaleProInsuredConfig;

	/** ����ְҵ���� */
	private String occupationCode;

	/**
	 * ��GeSaleProInsuredOccupation��Ĭ�Ϲ��췽��
	 */
	public GeSaleProInsuredOccupation() {
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
	 * ���Ե�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSUREDSERIALNO")
	public GeSaleProInsuredConfig getGeSaleProInsuredConfig() {
		return this.geSaleProInsuredConfig;
	}
	/**
	 * ���Ե�setter����
	 */
	public void setGeSaleProInsuredConfig(
			GeSaleProInsuredConfig geSaleProInsuredConfig) {
		this.geSaleProInsuredConfig = geSaleProInsuredConfig;
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
