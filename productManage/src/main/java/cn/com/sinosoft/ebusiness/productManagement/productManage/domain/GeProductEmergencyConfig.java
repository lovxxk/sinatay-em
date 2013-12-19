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
 * POJO��GeProductEmergencyConfig
 */
@Entity
@Table(name = "GE_PRODUCT_EMERGENCY_CONFIG")
public class GeProductEmergencyConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ���Խ�����ϵ������ */
	private String emerName;

	/** ���Խ�����ϵ���ֻ� */
	private String mobile;

	/** ���Խ�����ϵ��Email */
	private String emerEmail;

	/**
	 * ��GeProductEmergencyConfig��Ĭ�Ϲ��췽��
	 */
	public GeProductEmergencyConfig() {
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
	 * ���Խ�����ϵ��������getter����
	 */

	@Column(name = "EMERNAME")
	public String getEmerName() {
		return this.emerName;
	}
	/**
	 * ���Խ�����ϵ��������setter����
	 */
	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}
	/**
	 * ���Խ�����ϵ���ֻ���getter����
	 */

	@Column(name = "MOBILE")
	public String getMobile() {
		return this.mobile;
	}
	/**
	 * ���Խ�����ϵ���ֻ���setter����
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * ���Խ�����ϵ��Email��getter����
	 */

	@Column(name = "EMEREMAIL")
	public String getEmerEmail() {
		return this.emerEmail;
	}
	/**
	 * ���Խ�����ϵ��Email��setter����
	 */
	public void setEmerEmail(String emerEmail) {
		this.emerEmail = emerEmail;
	}

}
