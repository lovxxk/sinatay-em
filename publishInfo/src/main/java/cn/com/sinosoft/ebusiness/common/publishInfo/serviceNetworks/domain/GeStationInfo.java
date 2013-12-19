package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO��GeStationInfo
 */
@Entity
@Table(name = "GE_STATION_INFO")
public class GeStationInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����obid */
	private String obid;

	/** ���������ֹ�˾���� */
	private String corpName;

	/** ����ʡ��� */
	private String province;

	/** ���Գ��� */
	private String city;

	/** ���Գ����� */
	private String town;

	/** ���Է����������� */
	private String unitName;

	/** ���Ե�ַ */
	private String address;

	/** ������������ */
	private String zipCode;

	/** ����zone */
	private String zone;

	/** ���Ե绰 */
	private String telePhone;

	/** ���Դ��� */
	private String fax;

	/** ���Ե������� */
	private String email;

	/** ������Ҫ */
	private String principal;

	/** ���Է���ʱ�� */
	private String businessTime;

	/** ���Է���Χ */
	private String businessRange;

	/** ���Ա�ע */
	private String remark;

	/** ����TYPE */
	private String type;

	/** ����ʡ���� */
	private String provinceName;

	/** ���������� */
	private String cityName;

	/** ���������� */
	private String townName;
	
	/** ���Ի�������*/
	private String deptid;
	
	/** ����*/
	private String longitude;
	
	/** γ��*/
	private String latitude;

	/** �Ǽ�*/
	private String starLevel;
	
	/** ��������*/
	private String tos;
	
	
	
	/**
	 * �����Ǽ���getter����
	 */
	@Column(name = "STARLEVEL")
	public String getStarLevel() {
		return starLevel;
	}
	/**
	 * �����Ǽ���setter����
	 */
	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}
	/**
	 * ���Է������͵�getter����
	 */
	@Column(name = "TOS")
	public String getTos() {
		return tos;
	}
	/**
	 * ���Է������͵�setter����
	 */
	public void setTos(String tos) {
		this.tos = tos;
	}
	/**
	 * ����γ�ȵ�getter����
	 */
	@Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}
	/**
	 * ����γ�ȵ�setter����
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * ���Ծ��ȵ�getter����
	 */
	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	/**
	 * ���Ծ��ȵ�setter����
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
	 * ��GeStationInfo��Ĭ�Ϲ��췽��
	 */
	public GeStationInfo() {
	}

	/**
	 * ����obid��getter����
	 */
	@Id
	@Column(name = "OBID")
	public String getObid() {
		return this.obid;
	}

	/**
	 * ����obid��setter����
	 */
	public void setObid(String obid) {
		this.obid = obid;
	}

	/**
	 * ���������ֹ�˾���Ƶ�getter����
	 */

	@Column(name = "CORP_NAME")
	public String getCorpName() {
		return this.corpName;
	}

	/**
	 * ���������ֹ�˾���Ƶ�setter����
	 */
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	/**
	 * ����ʡ��ŵ�getter����
	 */

	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}

	/**
	 * ����ʡ��ŵ�setter����
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * ���Գ��е�getter����
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	/**
	 * ���Գ��е�setter����
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * ���Գ����ŵ�getter����
	 */

	@Column(name = "TOWN")
	public String getTown() {
		return this.town;
	}

	/**
	 * ���Գ����ŵ�setter����
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * ���Է����������Ƶ�getter����
	 */

	@Column(name = "UNIT_NAME")
	public String getUnitName() {
		return this.unitName;
	}

	/**
	 * ���Է����������Ƶ�setter����
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * ���Ե�ַ��getter����
	 */

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	/**
	 * ���Ե�ַ��setter����
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * �������������getter����
	 */

	@Column(name = "ZIPCODE")
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * �������������setter����
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * ����zone��getter����
	 */

	@Column(name = "ZONE")
	public String getZone() {
		return this.zone;
	}

	/**
	 * ����zone��setter����
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * ���Ե绰��getter����
	 */

	@Column(name = "TELEPHONE")
	public String getTelePhone() {
		return this.telePhone;
	}

	/**
	 * ���Ե绰��setter����
	 */
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	/**
	 * ���Դ����getter����
	 */

	@Column(name = "FAX")
	public String getFax() {
		return this.fax;
	}

	/**
	 * ���Դ����setter����
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * ���Ե��������getter����
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * ���Ե��������setter����
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * ������Ҫ��getter����
	 */

	@Column(name = "PRINCIPAL")
	public String getPrincipal() {
		return this.principal;
	}

	/**
	 * ������Ҫ��setter����
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * ���Է���ʱ���getter����
	 */

	@Column(name = "BUSINESS_TIME")
	public String getBusinessTime() {
		return this.businessTime;
	}

	/**
	 * ���Է���ʱ���setter����
	 */
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	/**
	 * ���Է���Χ��getter����
	 */

	@Column(name = "BUSINESS_RANGE")
	public String getBusinessRange() {
		return this.businessRange;
	}

	/**
	 * ���Է���Χ��setter����
	 */
	public void setBusinessRange(String businessRange) {
		this.businessRange = businessRange;
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
	 * ����TYPE��getter����
	 */

	@Column(name = "TYPE")
	public String getType() {
		return this.type;
	}

	/**
	 * ����TYPE��setter����
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * ����ʡ���Ƶ�getter����
	 */

	@Column(name = "PROVINCENAME")
	public String getProvinceName() {
		return this.provinceName;
	}

	/**
	 * ����ʡ���Ƶ�setter����
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * ���������Ƶ�getter����
	 */

	@Column(name = "CITYNAME")
	public String getCityName() {
		return this.cityName;
	}

	/**
	 * ���������Ƶ�setter����
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * ���������Ƶ�getter����
	 */

	@Column(name = "TOWNNAME")
	public String getTownName() {
		return this.townName;
	}

	/**
	 * ���������Ƶ�setter����
	 */
	public void setTownName(String townName) {
		this.townName = townName;
	}

}
