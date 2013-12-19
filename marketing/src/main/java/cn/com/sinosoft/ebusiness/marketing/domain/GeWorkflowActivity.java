package cn.com.sinosoft.ebusiness.marketing.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeWorkflowActivity
 */
@Entity
@Table(name = "GE_WORKFLOW_ACTIVITY")
public class GeWorkflowActivity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����ID */
	private String id;

	/** ����geAddserviceActivity */
	private GeAddServiceActivity geAddserviceActivity;

	/** ����city */
	private String city;

	/** ����createdate */
	private Date createdate;

	/**
	 * ��GeWorkflowActivity��Ĭ�Ϲ��췽��
	 */
	public GeWorkflowActivity() {
	}

	/**
	 * ����ID��getter����
	 */
	@Id
	@Column(name = "ID" ,unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getId() {
		return this.id;
	}

	/**
	 * ����ID��setter����
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ����geAddserviceActivity��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	public GeAddServiceActivity getGeAddserviceActivity() {
		return this.geAddserviceActivity;
	}

	/**
	 * ����geAddserviceActivity��setter����
	 */
	public void setGeAddserviceActivity(
			GeAddServiceActivity geAddserviceActivity) {
		this.geAddserviceActivity = geAddserviceActivity;
	}

	/**
	 * ����city��getter����
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	/**
	 * ����city��setter����
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * ����createdate��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE")
	public Date getCreatedate() {
		return this.createdate;
	}

	/**
	 * ����createdate��setter����
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}
