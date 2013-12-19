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
 * POJO��InsurancePolicy
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

	/** ������� */
	private String serialNo;
	
	/** ���Ե绰 */
	private String phone;
	
	/** ���������ַ */
	private String address;
	
	/** ���Թ���ʱ�� */
	private String workTime;

	/** ���Է�������*/
	private String type;
	
	/** ��������*/
	private String sort;
	
	/** ���Է����Ǽ� */
	private String grade;
	
	/** �������㾭�� */
	private String longItude;

	/** ��������γ�� */
	private String latItude;

	/** ���Թ���������� */
	private String manageCom;

	/** ���Թ���������� */
	private String manageName;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/** ���Բ���Ա */
	private String operator;
	
	/** ����ʡ */
	private String province;
	
	/** ������ */
	
	private String city;
	/**
	 * ��Site��Ĭ�Ϲ��췽��
	 */
	public Network() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = false, nullable = false, length = 50)
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
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * �����������Ƶ�getter����
	 */

	/**
	 * ���Ե绰��getter����
	 */
	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}
	/**
	 * ���Ե绰��setter����
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * ���Ե�ַ��getter����
	 */
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}
	
	/**
	 * ���Ե�ַ��setter����
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * ���Ե�ַ��getter����
	 */
	@Column(name = "WORKTIME")
	public String getWorkTime() {
		return workTime;
	}

	/**
	 * ���Ե�ַ��setter����
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	/**
	 * ���Է������͵�getter����
	 */
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	/**
	 * ���Է������͵�setter����
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * ���Է����Ǽ���getter����
	 */
	@Column(name = "GRADE")
	public String getGrade() {
		return grade;
	}
	/**
	 * ���Է����Ǽ���getter����
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * ���Ծ��ȵ�getter����
	 */
	@Column(name = "LONGITUDE")
	public String getLongItude() {
		return longItude;
	}
	/**
	 * ���Ծ��ȵ�setter����
	 */
	public void setLongItude(String longItude) {
		this.longItude = longItude;
	}
	/**
	 * ����γ�ȵ�getter����
	 */
	@Column(name = "LATITUDE")
	public String getLatItude() {
		return latItude;
	}
	/**
	 * ����γ�ȵ�setter����
	 */
	public void setLatItude(String latItude) {
		this.latItude = latItude;
	}
	/**
	 * ���Թ������getter����
	 */
	@Column(name = "MANAGECOM")
	public String getManageCom() {
		return manageCom;
	}
	/**
	 * ���Թ������˵setter����
	 */
	public void setManageCom(String manageCom) {
		this.manageCom = manageCom;
	}
	/**
	 * ���Թ����������getter����
	 */
	@Column(name = "MANAGENAME")
	public String getManageName() {
		return manageName;
	}
	/**
	 * ���Թ����������setter����
	 */
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	/**
	 * ���Բ���Ա����getter����
	 */
	@Column(name = "OPERATOR")
	public String getOperator() {
		return operator;
	}
	/**
	 * ���Բ���Ա����setter����
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * ����������������getter����
	 */
	@Column(name = "SORT")
	public String getSort() {
		return sort;
	}
	/**
	 * ����������������setter����
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * ����ʡgetter����
	 */
	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * ������getter����
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
