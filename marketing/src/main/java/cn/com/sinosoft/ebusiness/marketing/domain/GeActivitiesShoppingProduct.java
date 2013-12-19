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
 * POJO��GeActivitiesShoppingProduct
 */
@Entity
@Table(name = "GE_ACTIVITIES_SHOPPINGPRODUCT")
public class GeActivitiesShoppingProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������ˮID */
	private String autoShoppingId;

	/** ������ֵ��������-Ge_Activities_Rule */
	private GeActivitiesRule geActivitiesRule;

	/** ���Բ�ƷID */
	private String eid;

	//ҵ����ʹ�õ�start
	private String productName;
	//ҵ����ʹ�õ�end
	/**
	 * ��GeActivitiesShoppingProduct��Ĭ�Ϲ��췽��
	 */
	public GeActivitiesShoppingProduct() {
	}

	/**
	 * ������ˮID��getter����
	 */
	@Id
	@Column(name = "AUTOSHOPPINGID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getAutoShoppingId() {
		return this.autoShoppingId;
	}

	/**
	 * ������ˮID��setter����
	 */
	public void setAutoShoppingId(String autoShoppingId) {
		this.autoShoppingId = autoShoppingId;
	}

	/**
	 * ������ֵ��������-Ge_Activities_Rule��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RULEID")
	public GeActivitiesRule getGeActivitiesRule() {
		return this.geActivitiesRule;
	}

	/**
	 * ������ֵ��������-Ge_Activities_Rule��setter����
	 */
	public void setGeActivitiesRule(GeActivitiesRule geActivitiesRule) {
		this.geActivitiesRule = geActivitiesRule;
	}

	/**
	 * ���Բ�ƷID��getter����
	 */

	@Column(name = "EID")
	public String getEid() {
		return this.eid;
	}

	/**
	 * ���Բ�ƷID��setter����
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}
	//ҵ����ʹ�õ� start
	@Transient
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	//ҵ����ʹ�õ� end 
}
