package cn.com.sinosoft.businessModule.hospitalManage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类HospitalManage
 */
@Entity
@Table(name = "HOSPITALMANAGE")
public class HospitalManage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String serialNo;
	/** 中支 */
	private String province;
	/** 机构 */
	private String city;
	/** 医院名称 */
	private String hosName;
	/** 医院地址 */
	private String hosAddr;
	
	/**
	 * 类HospitalManage的默认构造方法
	 */
	public HospitalManage() {
	}

	/**
	 * 属性序号的getter方法
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
	 * 属性中支的getter方法
	 */
	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 属性机构的getter方法
	 */
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 属性医院名称的getter方法
	 */
	@Column(name = "HOSNAME")
	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	/**
	 * 属性医院地址的getter方法
	 */
	@Column(name = "HOSADDR")
	public String getHosAddr() {
		return hosAddr;
	}

	public void setHosAddr(String hosAddr) {
		this.hosAddr = hosAddr;
	}
}
