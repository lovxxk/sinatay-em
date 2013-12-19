package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;

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
 * POJO��GeWorkflowProduct
 */
@Entity
@Table(name = "GE_WORKFLOW_PRODUCT")
public class GeWorkflowProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����id */
	private String id;

	/** ����geProductMain */
	private GeProductMain geProductMain;

	/** ����city */
	private String city;

	/** ����createdate */
	private Date createdate;

	/**
	 * ��GeWorkflowProduct��Ĭ�Ϲ��췽��
	 */
	public GeWorkflowProduct() {
	}

	/**
	 * ����id��getter����
	 */
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getId() {
		return this.id;
	}

	/**
	 * ����id��setter����
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ����geProductMain��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTCODE")
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}

	/**
	 * ����geProductMain��setter����
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
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
	@Temporal(TemporalType.TIMESTAMP)
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
