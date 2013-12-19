package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * POJO��GeMaxNo
 */
@Entity
@Table(name = "GE_MAXNO")
public class GeMaxNo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIRST = "1";
	public static final String SECOND="2";

	public static final String FLAG_YES = "1";
	public static final String FLAG_NO = "0";


	/** ���Ի�ԱID ��Ӧ��½��Ա��ID */
	private GeMaxNoId id;

	/** �����Ƿ���Ч��־ */
	private String validInd;

	/** ���Ա�ע */
	private String remark;

	/** ���Ա�־λ */
	private String flag;

	/**
	 * ��GeMaxNo��Ĭ�Ϲ��췽��
	 */
	public GeMaxNo() {
	}

	/**
	 * ���Ի�ԱID ��Ӧ��½��Ա��ID��getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "groupNo", column = @Column(name = "GROUPNO")),
			@AttributeOverride(name = "maxNo", column = @Column(name = "MAXNO")) })
	public GeMaxNoId getId() {
		return this.id;
	}

	/**
	 * ���Ի�ԱID ��Ӧ��½��Ա��ID��setter����
	 */
	public void setId(GeMaxNoId id) {
		this.id = id;
	}

	/**
	 * �����Ƿ���Ч��־��getter����
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * �����Ƿ���Ч��־��setter����
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	/**
	 * ���Ա�ע��getter����
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���Ա�ע��setter����
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ���Ա�־λ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�־λ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
