package cn.com.sinosoft.ebusiness.common.party.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * POJO类GeThirdParterOrder
 */
@Entity
@Table(name = "GE_THIRDPARTER_ORDER")
public class GeThirdParterOrder implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性订单号 */
	private String orderNo;

	/** 属性第三方合作伙伴信息表-GE_ThirdParter_Info */
	private GeThirdParterInfo geThirdParterInfo;

	/** 属性运单号 */
	private String wayBillNo;

	/**
	 * 类GeThirdParterOrder的默认构造方法
	 */
	public GeThirdParterOrder() {
	}

	/**
	 * 属性显示序号的getter方法
	 */
	@Id
	@Column(name = "ORDERNO")
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 属性显示序号的setter方法
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 属性第三方合作伙伴信息表-GE_ThirdParter_Info的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THIRDPARTERID", nullable = false)
	public GeThirdParterInfo getGeThirdParterInfo() {
		return this.geThirdParterInfo;
	}

	/**
	 * 属性第三方合作伙伴信息表-GE_ThirdParter_Info的setter方法
	 */
	public void setGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo) {
		this.geThirdParterInfo = geThirdParterInfo;
	}

	/**
	 * 属性运单号的getter方法
	 */

	@Column(name = "WAYBILLNO")
	public String getWayBillNo() {
		return this.wayBillNo;
	}

	/**
	 * 属性运单号的setter方法
	 */
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}

}
