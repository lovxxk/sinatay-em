package cn.com.sinosoft.ebusiness.marketing.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeActivitiesProduct
 */
@Entity
@Table(name = "GE_ACTIVITIES_PRODUCT")
public class GeActivitiesProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性产品流水ID */
	private String autoActivitiesProductId;

	/** 属性增值服务活动-GE_AddService_Activity */
	private GeAddServiceActivity geAddServiceActivity;

	/** 属性电子商务产品ID */
	private String eid;
	/** 属性折扣ID供寿险核心描述*/
	private String discountID;
	//业务上使用的start
	private String productName;//产品名称
	private String coreProductCode;//产品代码
	//业务上使用的end
	/**
	 * 类GeActivitiesProduct的默认构造方法
	 */
	public GeActivitiesProduct() {
	}

	/**
	 * 属性产品流水ID的getter方法
	 */
	@Id
	@Column(name = "AUTOACTIVITIESPRODUCTID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getAutoActivitiesProductId() {
		return this.autoActivitiesProductId;
	}

	/**
	 * 属性产品流水ID的setter方法
	 */
	public void setAutoActivitiesProductId(String autoActivitiesProductId) {
		this.autoActivitiesProductId = autoActivitiesProductId;
	}

	/**
	 * 属性增值服务活动-GE_AddService_Activity的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	public GeAddServiceActivity getGeAddServiceActivity() {
		return this.geAddServiceActivity;
	}

	/**
	 * 属性增值服务活动-GE_AddService_Activity的setter方法
	 */
	public void setGeAddServiceActivity(
			GeAddServiceActivity geAddServiceActivity) {
		this.geAddServiceActivity = geAddServiceActivity;
	}

	/**
	 * 属性电子商务产品ID的getter方法
	 */

	@Column(name = "EID")
	public String getEid() {
		return this.eid;
	}

	/**
	 * 属性电子商务产品ID的setter方法
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}

	//业务上使用的start
	@Transient
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Transient
	public String getCoreProductCode() {
		return coreProductCode;
	}

	public void setCoreProductCode(String coreProductCode) {
		this.coreProductCode = coreProductCode;
	}
	//业务上使用的end
	/**
	 * 属性寿险折扣ID
	 */
	@Column(name = "DISCOUNTID")
	public String getDiscountID() {
		return discountID;
	}

	public void setDiscountID(String discountID) {
		this.discountID = discountID;
	}
}
