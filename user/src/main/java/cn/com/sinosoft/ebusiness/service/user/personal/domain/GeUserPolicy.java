package cn.com.sinosoft.ebusiness.service.user.personal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeUserPolicy
 */
@Entity
@Table(name = "GE_USER_POLICY")
public class GeUserPolicy implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Դ������ */
	private String serialNo;

	/** �����û��� */
	private String userID;

	/** �����û����� */
	private String userType;

	/** ���Ա����� */
	private String policyNo;

	/** ����ҵ������ */
	private String businessArea;

	/** ���԰����� */
	private String bindType;

	/** ����bindpersontype */
	private String bindPersonType;

	/**
	 * ��GeUserPolicy��Ĭ�Ϲ��췽��
	 */
	public GeUserPolicy() {
	}

	/**
	 * ���Դ�����ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO")
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * ���Դ�����ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * �����û��ŵ�getter����
	 */

	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * �����û��ŵ�setter����
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * �����û����͵�getter����
	 */

	@Column(name = "USERTYPE")
	public String getUserType() {
		return this.userType;
	}

	/**
	 * �����û����͵�setter����
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * ���Ա����ŵ�getter����
	 */

	@Column(name = "POLICYNO")
	public String getPolicyNo() {
		return this.policyNo;
	}

	/**
	 * ���Ա����ŵ�setter����
	 */
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	/**
	 * ����ҵ�������getter����
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}

	/**
	 * ����ҵ�������setter����
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	/**
	 * ���԰����͵�getter����
	 */

	@Column(name = "BINDTYPE")
	public String getBindType() {
		return this.bindType;
	}

	/**
	 * ���԰����͵�setter����
	 */
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}

	/**
	 * ����bindPersonType��getter����
	 */

	@Column(name = "BINDPERSONTYPE")
	public String getBindPersonType() {
		return this.bindPersonType;
	}

	/**
	 * ����bindpersontype��setter����
	 */
	public void setBindPersonType(String bindPersonType) {
		this.bindPersonType = bindPersonType;
	}

}
