package cn.com.sinosoft.ebusiness.common.party.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeThirdParterOrder
 */
@Entity
@Table(name = "GE_THIRDPARTER_ORDER")
public class GeThirdParterOrder implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Զ����� */
	private String orderNo;

	/** ���Ե��������������Ϣ��-GE_ThirdParter_Info */
	private GeThirdParterInfo geThirdParterInfo;

	/** �����˵��� */
	private String wayBillNo;

	/**
	 * ��GeThirdParterOrder��Ĭ�Ϲ��췽��
	 */
	public GeThirdParterOrder() {
	}

	/**
	 * ������ʾ��ŵ�getter����
	 */
	@Id
	@Column(name = "ORDERNO")
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * ������ʾ��ŵ�setter����
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * ���Ե��������������Ϣ��-GE_ThirdParter_Info��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THIRDPARTERID", nullable = false)
	public GeThirdParterInfo getGeThirdParterInfo() {
		return this.geThirdParterInfo;
	}

	/**
	 * ���Ե��������������Ϣ��-GE_ThirdParter_Info��setter����
	 */
	public void setGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo) {
		this.geThirdParterInfo = geThirdParterInfo;
	}

	/**
	 * �����˵��ŵ�getter����
	 */

	@Column(name = "WAYBILLNO")
	public String getWayBillNo() {
		return this.wayBillNo;
	}

	/**
	 * �����˵��ŵ�setter����
	 */
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}

}
