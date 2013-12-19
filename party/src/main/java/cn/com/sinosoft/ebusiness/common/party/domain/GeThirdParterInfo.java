package cn.com.sinosoft.ebusiness.common.party.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类GeThirdParterInfo
 */
@Entity
@Table(name = "GE_THIRDPARTER_INFO")
public class GeThirdParterInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性第三方公司ID */
	private String thirdParterID;

	/** 属性公司名称 */
	private String thirdParterName;

	/** 属性公司电话 */
	private String companyPhone;

	/** 属性地址 */
	private String address;

	/** 属性电子邮箱 */
	private String email;

	/** 属性url */
	private String url;

	/** 属性创建时间 */
	private Date createDate;
	
	/** 属性公司类型 */
	private String companyType;
	
	/** 属性业务领域*/
	private String businessArea;
	
	/** 属性机构编号*/
	private String deptID;

	/** 属性创建人代码*/
	private String createCode;
	
	/**一般为最后一次更新人代码*/
	private String updateCode;
	
	/** 属性标识位 */
	private String flag;

	/** 属性geThirdParterServices */
	private List<GeThirdParterService> geThirdParterServices = new ArrayList<GeThirdParterService>(
			0);

	/** 属性geThirdParterContacts */
	private List<GeThirdParterContact> geThirdParterContacts = new ArrayList<GeThirdParterContact>(
			0);
	
	/** 属性geThirdParterOrders */
	private List<GeThirdParterOrder> geThirdParterOrders = new ArrayList<GeThirdParterOrder>(
			0);

	//业务上使用的字段start
	private String deptName;
	//业务上使用的字段end
	/**
	 * 类GeThirdParterInfo的默认构造方法
	 */
	public GeThirdParterInfo() {
	}

	/**
	 * 属性第三方公司ID的getter方法
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
	 * 属性第三方公司ID的setter方法
	 */
	public void setThirdParterID(String thirdParterID) {
		this.thirdParterID = thirdParterID;
	}

	/**
	 * 属性公司名称的getter方法
	 */

	@Column(name = "THIRDPARTERNAME")
	public String getThirdParterName() {
		return this.thirdParterName;
	}

	/**
	 * 属性公司名称的setter方法
	 */
	public void setThirdParterName(String thirdParterName) {
		this.thirdParterName = thirdParterName;
	}

	/**
	 * 属性公司电话的getter方法
	 */

	@Column(name = "COMPANYPHONE")
	public String getCompanyPhone() {
		return this.companyPhone;
	}

	/**
	 * 属性公司电话的setter方法
	 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
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
	 * 属性url的getter方法
	 */

	@Column(name = "URL")
	public String getUrl() {
		return this.url;
	}

	/**
	 * 属性url的setter方法
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 属性公司类型的getter方法
	 */
	@Column(name = "COMPANYTYPE")
	public String getCompanyType() {
		return companyType;
	}
	
	/**
	 * 属性公司类型的setter方法
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	/**
	 * 属性业务领域的getter方法
	 */
	
	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return businessArea;
	}
	
	/**
	 * 属性业务领域的setter方法
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	
	/**
	 * 属性机构编号的getter方法
	 */
	
	@Column(name = "DEPTID")
	public String getDeptID() {
		return deptID;
	}

	/**
	 * 属性机构编号的setter方法
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
	 * 属性标识位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标识位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性geThirdParterServices的getter方法
	 *  
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "geThirdParterInfo")
	public List<GeThirdParterService> getGeThirdParterServices() {
		return this.geThirdParterServices;
	}

	/**
	 * 属性geThirdParterServices的setter方法
	 */
	public void setGeThirdParterServices(
			List<GeThirdParterService> geThirdParterServices) {
		this.geThirdParterServices = geThirdParterServices;
	}

	/**
	 * 属性geThirdParterContacts的getter方法
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geThirdParterInfo")
	public List<GeThirdParterContact> getGeThirdParterContacts() {
		return this.geThirdParterContacts;
	}

	/**
	 * 属性geThirdParterContacts的setter方法
	 */
	public void setGeThirdParterContacts(
			List<GeThirdParterContact> geThirdParterContacts) {
		this.geThirdParterContacts = geThirdParterContacts;
	}
	/**
	 * 属性geThirdParterOrders的getter方法
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geThirdParterInfo")
	public List<GeThirdParterOrder> getGeThirdParterOrders() {
		return this.geThirdParterOrders;
	}

	/**
	 * 属性geThirdParterOrders的setter方法
	 */
	public void setGeThirdParterOrders(
			List<GeThirdParterOrder> geThirdParterOrders) {
		this.geThirdParterOrders = geThirdParterOrders;
	}
	//业务上使用的方法start
	@Transient
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	//业务上使用的方法start
}
