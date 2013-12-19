package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;

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
 * POJO类GeWorkflowProduct
 */
@Entity
@Table(name = "GE_WORKFLOW_PRODUCT")
public class GeWorkflowProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性id */
	private String id;

	/** 属性geProductMain */
	private GeProductMain geProductMain;

	/** 属性city */
	private String city;

	/** 属性createdate */
	private Date createdate;

	/**
	 * 类GeWorkflowProduct的默认构造方法
	 */
	public GeWorkflowProduct() {
	}

	/**
	 * 属性id的getter方法
	 */
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getId() {
		return this.id;
	}

	/**
	 * 属性id的setter方法
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 属性geProductMain的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTCODE")
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}

	/**
	 * 属性geProductMain的setter方法
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
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
	@Temporal(TemporalType.TIMESTAMP)
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
