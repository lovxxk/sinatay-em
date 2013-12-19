package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO类GeStationInfo
 */
@Entity
@Table(name = "GE_STATION_INFO")
public class GeStationInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性obid */
	private String obid;

	/** 属性所属分公司名称 */
	private String corpName;

	/** 属性省编号 */
	private String province;

	/** 属性城市 */
	private String city;

	/** 属性城镇编号 */
	private String town;

	/** 属性服务网点名称 */
	private String unitName;

	/** 属性地址 */
	private String address;

	/** 属性邮政编码 */
	private String zipCode;

	/** 属性zone */
	private String zone;

	/** 属性电话 */
	private String telePhone;

	/** 属性传真 */
	private String fax;

	/** 属性电子邮箱 */
	private String email;

	/** 属性主要 */
	private String principal;

	/** 属性服务时间 */
	private String businessTime;

	/** 属性服务范围 */
	private String businessRange;

	/** 属性备注 */
	private String remark;

	/** 属性TYPE */
	private String type;

	/** 属性省名称 */
	private String provinceName;

	/** 属性市名称 */
	private String cityName;

	/** 属性镇名称 */
	private String townName;
	
	/** 属性机构编码*/
	private String deptid;
	
	/** 经度*/
	private String longitude;
	
	/** 纬度*/
	private String latitude;

	/** 星级*/
	private String starLevel;
	
	/** 服务类型*/
	private String tos;
	
	
	
	/**
	 * 属性星级的getter方法
	 */
	@Column(name = "STARLEVEL")
	public String getStarLevel() {
		return starLevel;
	}
	/**
	 * 属性星级的setter方法
	 */
	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}
	/**
	 * 属性服务类型的getter方法
	 */
	@Column(name = "TOS")
	public String getTos() {
		return tos;
	}
	/**
	 * 属性服务类型的setter方法
	 */
	public void setTos(String tos) {
		this.tos = tos;
	}
	/**
	 * 属性纬度的getter方法
	 */
	@Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}
	/**
	 * 属性纬度的setter方法
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 属性经度的getter方法
	 */
	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	/**
	 * 属性经度的setter方法
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	@Column(name = "DEPTID")
	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	/**
	 * 类GeStationInfo的默认构造方法
	 */
	public GeStationInfo() {
	}

	/**
	 * 属性obid的getter方法
	 */
	@Id
	@Column(name = "OBID")
	public String getObid() {
		return this.obid;
	}

	/**
	 * 属性obid的setter方法
	 */
	public void setObid(String obid) {
		this.obid = obid;
	}

	/**
	 * 属性所属分公司名称的getter方法
	 */

	@Column(name = "CORP_NAME")
	public String getCorpName() {
		return this.corpName;
	}

	/**
	 * 属性所属分公司名称的setter方法
	 */
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	/**
	 * 属性省编号的getter方法
	 */

	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}

	/**
	 * 属性省编号的setter方法
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 属性城市的getter方法
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	/**
	 * 属性城市的setter方法
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 属性城镇编号的getter方法
	 */

	@Column(name = "TOWN")
	public String getTown() {
		return this.town;
	}

	/**
	 * 属性城镇编号的setter方法
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * 属性服务网点名称的getter方法
	 */

	@Column(name = "UNIT_NAME")
	public String getUnitName() {
		return this.unitName;
	}

	/**
	 * 属性服务网点名称的setter方法
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 属性地址的getter方法
	 */

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	/**
	 * 属性地址的setter方法
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 属性邮政编码的getter方法
	 */

	@Column(name = "ZIPCODE")
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * 属性邮政编码的setter方法
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 属性zone的getter方法
	 */

	@Column(name = "ZONE")
	public String getZone() {
		return this.zone;
	}

	/**
	 * 属性zone的setter方法
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * 属性电话的getter方法
	 */

	@Column(name = "TELEPHONE")
	public String getTelePhone() {
		return this.telePhone;
	}

	/**
	 * 属性电话的setter方法
	 */
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	/**
	 * 属性传真的getter方法
	 */

	@Column(name = "FAX")
	public String getFax() {
		return this.fax;
	}

	/**
	 * 属性传真的setter方法
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * 属性电子邮箱的getter方法
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * 属性电子邮箱的setter方法
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 属性主要的getter方法
	 */

	@Column(name = "PRINCIPAL")
	public String getPrincipal() {
		return this.principal;
	}

	/**
	 * 属性主要的setter方法
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * 属性服务时间的getter方法
	 */

	@Column(name = "BUSINESS_TIME")
	public String getBusinessTime() {
		return this.businessTime;
	}

	/**
	 * 属性服务时间的setter方法
	 */
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	/**
	 * 属性服务范围的getter方法
	 */

	@Column(name = "BUSINESS_RANGE")
	public String getBusinessRange() {
		return this.businessRange;
	}

	/**
	 * 属性服务范围的setter方法
	 */
	public void setBusinessRange(String businessRange) {
		this.businessRange = businessRange;
	}

	/**
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 属性TYPE的getter方法
	 */

	@Column(name = "TYPE")
	public String getType() {
		return this.type;
	}

	/**
	 * 属性TYPE的setter方法
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 属性省名称的getter方法
	 */

	@Column(name = "PROVINCENAME")
	public String getProvinceName() {
		return this.provinceName;
	}

	/**
	 * 属性省名称的setter方法
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * 属性市名称的getter方法
	 */

	@Column(name = "CITYNAME")
	public String getCityName() {
		return this.cityName;
	}

	/**
	 * 属性市名称的setter方法
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 属性镇名称的getter方法
	 */

	@Column(name = "TOWNNAME")
	public String getTownName() {
		return this.townName;
	}

	/**
	 * 属性镇名称的setter方法
	 */
	public void setTownName(String townName) {
		this.townName = townName;
	}

}
