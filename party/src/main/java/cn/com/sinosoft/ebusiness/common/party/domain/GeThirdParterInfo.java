package cn.com.sinosoft.ebusiness.common.party.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeThirdParterInfo
 */
@Entity
@Table(name = "GE_THIRDPARTER_INFO")
public class GeThirdParterInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ե�������˾ID */
	private String thirdParterID;

	/** ���Թ�˾���� */
	private String thirdParterName;

	/** ���Թ�˾�绰 */
	private String companyPhone;

	/** ���Ե�ַ */
	private String address;

	/** ���Ե������� */
	private String email;

	/** ����url */
	private String url;

	/** ���Դ���ʱ�� */
	private Date createDate;
	
	/** ���Թ�˾���� */
	private String companyType;
	
	/** ����ҵ������*/
	private String businessArea;
	
	/** ���Ի������*/
	private String deptID;

	/** ���Դ����˴���*/
	private String createCode;
	
	/**һ��Ϊ���һ�θ����˴���*/
	private String updateCode;
	
	/** ���Ա�ʶλ */
	private String flag;

	/** ����geThirdParterServices */
	private List<GeThirdParterService> geThirdParterServices = new ArrayList<GeThirdParterService>(
			0);

	/** ����geThirdParterContacts */
	private List<GeThirdParterContact> geThirdParterContacts = new ArrayList<GeThirdParterContact>(
			0);
	
	/** ����geThirdParterOrders */
	private List<GeThirdParterOrder> geThirdParterOrders = new ArrayList<GeThirdParterOrder>(
			0);

	//ҵ����ʹ�õ��ֶ�start
	private String deptName;
	//ҵ����ʹ�õ��ֶ�end
	/**
	 * ��GeThirdParterInfo��Ĭ�Ϲ��췽��
	 */
	public GeThirdParterInfo() {
	}

	/**
	 * ���Ե�������˾ID��getter����
	 */
	@Id
//	@Column(name = "THIRDPARTERID")
	@Column(name = "THIRDPARTERID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getThirdParterID() {
		return this.thirdParterID;
	}

	/**
	 * ���Ե�������˾ID��setter����
	 */
	public void setThirdParterID(String thirdParterID) {
		this.thirdParterID = thirdParterID;
	}

	/**
	 * ���Թ�˾���Ƶ�getter����
	 */

	@Column(name = "THIRDPARTERNAME")
	public String getThirdParterName() {
		return this.thirdParterName;
	}

	/**
	 * ���Թ�˾���Ƶ�setter����
	 */
	public void setThirdParterName(String thirdParterName) {
		this.thirdParterName = thirdParterName;
	}

	/**
	 * ���Թ�˾�绰��getter����
	 */

	@Column(name = "COMPANYPHONE")
	public String getCompanyPhone() {
		return this.companyPhone;
	}

	/**
	 * ���Թ�˾�绰��setter����
	 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
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
	 * ����url��getter����
	 */

	@Column(name = "URL")
	public String getUrl() {
		return this.url;
	}

	/**
	 * ����url��setter����
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * ���Թ�˾���͵�getter����
	 */
	@Column(name = "COMPANYTYPE")
	public String getCompanyType() {
		return companyType;
	}
	
	/**
	 * ���Թ�˾���͵�setter����
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	/**
	 * ����ҵ�������getter����
	 */
	
	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return businessArea;
	}
	
	/**
	 * ����ҵ�������setter����
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	
	/**
	 * ���Ի�����ŵ�getter����
	 */
	
	@Column(name = "DEPTID")
	public String getDeptID() {
		return deptID;
	}

	/**
	 * ���Ի�����ŵ�setter����
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	
	
	@Column(name="CREATECODE")
	public String getCreateCode() {
		return createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}

	@Column(name="UPDATECODE")
	public String getUpdateCode() {
		return updateCode;
	}

	public void setUpdateCode(String updateCode) {
		this.updateCode = updateCode;
	}
	
	/**
	 * ���Ա�ʶλ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�ʶλ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * ����geThirdParterServices��getter����
	 *  
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "geThirdParterInfo")
	public List<GeThirdParterService> getGeThirdParterServices() {
		return this.geThirdParterServices;
	}

	/**
	 * ����geThirdParterServices��setter����
	 */
	public void setGeThirdParterServices(
			List<GeThirdParterService> geThirdParterServices) {
		this.geThirdParterServices = geThirdParterServices;
	}

	/**
	 * ����geThirdParterContacts��getter����
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geThirdParterInfo")
	public List<GeThirdParterContact> getGeThirdParterContacts() {
		return this.geThirdParterContacts;
	}

	/**
	 * ����geThirdParterContacts��setter����
	 */
	public void setGeThirdParterContacts(
			List<GeThirdParterContact> geThirdParterContacts) {
		this.geThirdParterContacts = geThirdParterContacts;
	}
	/**
	 * ����geThirdParterOrders��getter����
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geThirdParterInfo")
	public List<GeThirdParterOrder> getGeThirdParterOrders() {
		return this.geThirdParterOrders;
	}

	/**
	 * ����geThirdParterOrders��setter����
	 */
	public void setGeThirdParterOrders(
			List<GeThirdParterOrder> geThirdParterOrders) {
		this.geThirdParterOrders = geThirdParterOrders;
	}
	//ҵ����ʹ�õķ���start
	@Transient
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	//ҵ����ʹ�õķ���start
}
