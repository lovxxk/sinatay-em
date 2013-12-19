package cn.com.sinosoft.businessModule.hospitalManage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��HospitalManage
 */
@Entity
@Table(name = "HOSPITALMANAGE")
public class HospitalManage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ���� */
	private String serialNo;
	/** ��֧ */
	private String province;
	/** ���� */
	private String city;
	/** ҽԺ���� */
	private String hosName;
	/** ҽԺ��ַ */
	private String hosAddr;
	
	/**
	 * ��HospitalManage��Ĭ�Ϲ��췽��
	 */
	public HospitalManage() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = false, nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * ������֧��getter����
	 */
	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * ���Ի�����getter����
	 */
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * ����ҽԺ���Ƶ�getter����
	 */
	@Column(name = "HOSNAME")
	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	/**
	 * ����ҽԺ��ַ��getter����
	 */
	@Column(name = "HOSADDR")
	public String getHosAddr() {
		return hosAddr;
	}

	public void setHosAddr(String hosAddr) {
		this.hosAddr = hosAddr;
	}
}
