package cn.com.sinosoft.ebusiness.marketing.domain;


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
 * POJO类GeActivitiesShoppingProduct
 */
@Entity
@Table(name = "GE_ACTIVITIES_SHOPPINGPRODUCT")
public class GeActivitiesShoppingProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性流水ID */
	private String autoShoppingId;

	/** 属性增值服务活动规则-Ge_Activities_Rule */
	private GeActivitiesRule geActivitiesRule;

	/** 属性产品ID */
	private String eid;

	//业务上使用的start
	private String productName;
	//业务上使用的end
	/**
	 * 类GeActivitiesShoppingProduct的默认构造方法
	 */
	public GeActivitiesShoppingProduct() {
	}

	/**
	 * 属性流水ID的getter方法
	 */
	@Id
	@Column(name = "AUTOSHOPPINGID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getAutoShoppingId() {
		return this.autoShoppingId;
	}

	/**
	 * 属性流水ID的setter方法
	 */
	public void setAutoShoppingId(String autoShoppingId) {
		this.autoShoppingId = autoShoppingId;
	}

	/**
	 * 属性增值服务活动规则-Ge_Activities_Rule的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RULEID")
	public GeActivitiesRule getGeActivitiesRule() {
		return this.geActivitiesRule;
	}

	/**
	 * 属性增值服务活动规则-Ge_Activities_Rule的setter方法
	 */
	public void setGeActivitiesRule(GeActivitiesRule geActivitiesRule) {
		this.geActivitiesRule = geActivitiesRule;
	}

	/**
	 * 属性产品ID的getter方法
	 */

	@Column(name = "EID")
	public String getEid() {
		return this.eid;
	}

	/**
	 * 属性产品ID的setter方法
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}
	//业务上使用的 start
	@Transient
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	//业务上使用的 end 
}
