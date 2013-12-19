package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO��GeOccupation
 */
@Entity
@Table(name = "GE_OCCUPATION")
public class GeOccupation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����ְҵ���� */
	private String occupationCode;

	/** ����ְҵ������� */
	private String occupationTypeName;

	/** ���Ը�ְҵ���� */
	private String parentOccupationCode;

	/** ����ְҵ�ȼ� */
	private String occupationLevel;

	/** ���Ա�ע */
	private String remark;

	/**
	 * ��GeOccupation��Ĭ�Ϲ��췽��
	 */
	public GeOccupation() {
	}

	/**
	 * ����ְҵ�����getter����
	 */
	@Id
	@Column(name = "OCCUPATIONCODE")
	public String getOccupationCode() {
		return this.occupationCode;
	}

	/**
	 * ����ְҵ�����setter����
	 */
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	/**
	 * ����ְҵ������Ƶ�getter����
	 */

	@Column(name = "OCCUPATIONTYPENAME")
	public String getOccupationTypeName() {
		return this.occupationTypeName;
	}

	/**
	 * ����ְҵ������Ƶ�setter����
	 */
	public void setOccupationTypeName(String occupationTypeName) {
		this.occupationTypeName = occupationTypeName;
	}

	/**
	 * ���Ը�ְҵ�����getter����
	 */

	@Column(name = "PARENTOCCUPATIONCODE")
	public String getParentOccupationCode() {
		return this.parentOccupationCode;
	}

	/**
	 * ���Ը�ְҵ�����setter����
	 */
	public void setParentOccupationCode(String parentOccupationCode) {
		this.parentOccupationCode = parentOccupationCode;
	}

	/**
	 * ����ְҵ�ȼ���getter����
	 */

	@Column(name = "OCCUPATIONLEVEL")
	public String getOccupationLevel() {
		return this.occupationLevel;
	}

	/**
	 * ����ְҵ�ȼ���setter����
	 */
	public void setOccupationLevel(String occupationLevel) {
		this.occupationLevel = occupationLevel;
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

}
