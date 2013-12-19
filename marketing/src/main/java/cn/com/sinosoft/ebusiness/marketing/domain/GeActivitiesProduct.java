package cn.com.sinosoft.ebusiness.marketing.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeActivitiesProduct
 */
@Entity
@Table(name = "GE_ACTIVITIES_PRODUCT")
public class GeActivitiesProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Բ�Ʒ��ˮID */
	private String autoActivitiesProductId;

	/** ������ֵ����-GE_AddService_Activity */
	private GeAddServiceActivity geAddServiceActivity;

	/** ���Ե��������ƷID */
	private String eid;
	/** �����ۿ�ID�����պ�������*/
	private String discountID;
	//ҵ����ʹ�õ�start
	private String productName;//��Ʒ����
	private String coreProductCode;//��Ʒ����
	//ҵ����ʹ�õ�end
	/**
	 * ��GeActivitiesProduct��Ĭ�Ϲ��췽��
	 */
	public GeActivitiesProduct() {
	}

	/**
	 * ���Բ�Ʒ��ˮID��getter����
	 */
	@Id
	@Column(name = "AUTOACTIVITIESPRODUCTID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getAutoActivitiesProductId() {
		return this.autoActivitiesProductId;
	}

	/**
	 * ���Բ�Ʒ��ˮID��setter����
	 */
	public void setAutoActivitiesProductId(String autoActivitiesProductId) {
		this.autoActivitiesProductId = autoActivitiesProductId;
	}

	/**
	 * ������ֵ����-GE_AddService_Activity��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	public GeAddServiceActivity getGeAddServiceActivity() {
		return this.geAddServiceActivity;
	}

	/**
	 * ������ֵ����-GE_AddService_Activity��setter����
	 */
	public void setGeAddServiceActivity(
			GeAddServiceActivity geAddServiceActivity) {
		this.geAddServiceActivity = geAddServiceActivity;
	}

	/**
	 * ���Ե��������ƷID��getter����
	 */

	@Column(name = "EID")
	public String getEid() {
		return this.eid;
	}

	/**
	 * ���Ե��������ƷID��setter����
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}

	//ҵ����ʹ�õ�start
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
	//ҵ����ʹ�õ�end
	/**
	 * ���������ۿ�ID
	 */
	@Column(name = "DISCOUNTID")
	public String getDiscountID() {
		return discountID;
	}

	public void setDiscountID(String discountID) {
		this.discountID = discountID;
	}
}
