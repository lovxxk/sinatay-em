package cn.com.sinosoft.ebusiness.marketing.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeWorkflowActivity
 */
@Entity
@Table(name = "GE_WORKFLOW_ACTIVITY")
public class GeWorkflowActivity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性ID */
	private String id;

	/** 属性geAddserviceActivity */
	private GeAddServiceActivity geAddserviceActivity;

	/** 属性city */
	private String city;

	/** 属性createdate */
	private Date createdate;

	/**
	 * 类GeWorkflowActivity的默认构造方法
	 */
	public GeWorkflowActivity() {
	}

	/**
	 * 属性ID的getter方法
	 */
	@Id
	@Column(name = "ID" ,unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getId() {
		return this.id;
	}

	/**
	 * 属性ID的setter方法
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 属性geAddserviceActivity的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	public GeAddServiceActivity getGeAddserviceActivity() {
		return this.geAddserviceActivity;
	}

	/**
	 * 属性geAddserviceActivity的setter方法
	 */
	public void setGeAddserviceActivity(
			GeAddServiceActivity geAddserviceActivity) {
		this.geAddserviceActivity = geAddserviceActivity;
	}

	/**
	 * 属性city的getter方法
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	/**
	 * 属性city的setter方法
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 属性createdate的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE")
	public Date getCreatedate() {
		return this.createdate;
	}

	/**
	 * 属性createdate的setter方法
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}
