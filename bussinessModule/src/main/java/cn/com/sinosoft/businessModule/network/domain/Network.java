package cn.com.sinosoft.businessModule.network.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类InsurancePolicy
 */
@Entity
@Table(name = "NETWORK")
public class Network implements java.io.Serializable,Comparable<Network>{
	/**
	 * @ProjectName:
	 * @Package:     
	 * @ClassName:   
	 * @Description: 
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-9-24
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */

	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;
	
	/** 属性电话 */
	private String phone;
	
	/** 属性网点地址 */
	private String address;
	
	/** 属性工作时间 */
	private String workTime;

	/** 属性服务类型*/
	private String type;
	
	/** 网点排序*/
	private String sort;
	
	/** 属性服务星级 */
	private String grade;
	
	/** 属性网点经度 */
	private String longItude;

	/** 属性网点纬度 */
	private String latItude;

	/** 属性管理机构代码 */
	private String manageCom;

	/** 属性管理机构名称 */
	private String manageName;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/** 属性操作员 */
	private String operator;
	
	/** 属性省 */
	private String province;
	
	/** 属性市 */
	
	private String city;
	/**
	 * 类Site的默认构造方法
	 */
	public Network() {
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = false, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	
	
	

	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 属性网点名称的getter方法
	 */

	/**
	 * 属性电话的getter方法
	 */
	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}
	/**
	 * 属性电话的setter方法
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 属性地址的getter方法
	 */
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}
	
	/**
	 * 属性地址的setter方法
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 属性地址的getter方法
	 */
	@Column(name = "WORKTIME")
	public String getWorkTime() {
		return workTime;
	}

	/**
	 * 属性地址的setter方法
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	/**
	 * 属性服务类型的getter方法
	 */
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	/**
	 * 属性服务类型的setter方法
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 属性服务星级的getter方法
	 */
	@Column(name = "GRADE")
	public String getGrade() {
		return grade;
	}
	/**
	 * 属性服务星级的getter方法
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * 属性经度的getter方法
	 */
	@Column(name = "LONGITUDE")
	public String getLongItude() {
		return longItude;
	}
	/**
	 * 属性经度的setter方法
	 */
	public void setLongItude(String longItude) {
		this.longItude = longItude;
	}
	/**
	 * 属性纬度的getter方法
	 */
	@Column(name = "LATITUDE")
	public String getLatItude() {
		return latItude;
	}
	/**
	 * 属性纬度的setter方法
	 */
	public void setLatItude(String latItude) {
		this.latItude = latItude;
	}
	/**
	 * 属性管理机构getter方法
	 */
	@Column(name = "MANAGECOM")
	public String getManageCom() {
		return manageCom;
	}
	/**
	 * 属性管理机构说setter方法
	 */
	public void setManageCom(String manageCom) {
		this.manageCom = manageCom;
	}
	/**
	 * 属性管理机构名称getter方法
	 */
	@Column(name = "MANAGENAME")
	public String getManageName() {
		return manageName;
	}
	/**
	 * 属性管理机构名称setter方法
	 */
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	/**
	 * 属性操作员名称getter方法
	 */
	@Column(name = "OPERATOR")
	public String getOperator() {
		return operator;
	}
	/**
	 * 属性操作员名称setter方法
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * 属性网点排序名称getter方法
	 */
	@Column(name = "SORT")
	public String getSort() {
		return sort;
	}
	/**
	 * 属性网点排序名称setter方法
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 属性省getter方法
	 */
	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 属性市getter方法
	 */
	
	public String getCity() {
		return city;
	}
	@Column(name = "CITY")
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int compareTo(Network o) {
		return o.getManageCom().compareTo(this.getManageCom());
	}
	
	
}
