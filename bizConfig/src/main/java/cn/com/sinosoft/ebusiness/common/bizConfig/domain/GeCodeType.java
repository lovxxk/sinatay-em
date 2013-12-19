package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// default package
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * POJO��GeCodeType
 */
@Entity
@Table(name = "GE_CODE_TYPE")
public class GeCodeType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Դ������� */
	private String codeType;

	/** ���Լ������� */
	private String codeTypeCDesc;

	/** ����Ӣ������ */
	private String codeTypeEDesc;

	/** ���Է������� */
	private String codeTypeTDesc;

	/** �����Ƿ���Ч��1:��Ч��0����Ч */
	private String validInd;

	/** �������ͱ�־ 0:��ͨ�������ͣ�������л����������ã���1:����������ͣ���������л����������ã� */
	private String typeInd;

	/** ����ҵ������(1-���ţ�2-���գ�3-���գ�4-�����գ�9-����) */
	private String businessArea;

	/** ���Ա�־λ */
	private String flag;

	/** ����geCodes */
	private List<GeCode> geCodes = new ArrayList<GeCode>(0);

	/**
	 * ��GeCodeType��Ĭ�Ϲ��췽��
	 */
	public GeCodeType() {
	}

	/**
	 * ���Դ������͵�getter����
	 */
	@Id
	@Column(name = "CODETYPE")
	public String getCodeType() {
		return this.codeType;
	}

	/**
	 * ���Դ������͵�setter����
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	/**
	 * ���Լ���������getter����
	 */

	@Column(name = "CODETYPECDESC")
	public String getCodeTypeCDesc() {
		return this.codeTypeCDesc;
	}

	/**
	 * ���Լ���������setter����
	 */
	public void setCodeTypeCDesc(String codeTypeCDesc) {
		this.codeTypeCDesc = codeTypeCDesc;
	}

	/**
	 * ����Ӣ��������getter����
	 */

	@Column(name = "CODETYPEEDESC")
	public String getCodeTypeEDesc() {
		return this.codeTypeEDesc;
	}

	/**
	 * ����Ӣ��������setter����
	 */
	public void setCodeTypeEDesc(String codeTypeEDesc) {
		this.codeTypeEDesc = codeTypeEDesc;
	}

	/**
	 * ���Է���������getter����
	 */

	@Column(name = "CODETYPETDESC")
	public String getCodeTypeTDesc() {
		return this.codeTypeTDesc;
	}

	/**
	 * ���Է���������setter����
	 */
	public void setCodeTypeTDesc(String codeTypeTDesc) {
		this.codeTypeTDesc = codeTypeTDesc;
	}

	/**
	 * �����Ƿ���Ч��1:��Ч��0����Ч��getter����
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * �����Ƿ���Ч��1:��Ч��0����Ч��setter����
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	/**
	 * �������ͱ�־ 0:��ͨ�������ͣ�������л����������ã���1:����������ͣ���������л����������ã���getter����
	 */

	@Column(name = "TYPEIND")
	public String getTypeInd() {
		return this.typeInd;
	}

	/**
	 * �������ͱ�־ 0:��ͨ�������ͣ�������л����������ã���1:����������ͣ���������л����������ã���setter����
	 */
	public void setTypeInd(String typeInd) {
		this.typeInd = typeInd;
	}

	/**
	 * ����ҵ������(1-���ţ�2-���գ�3-���գ�4-�����գ�9-����)��getter����
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}

	/**
	 * ����ҵ������(1-���ţ�2-���գ�3-���գ�4-�����գ�9-����)��setter����
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
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

	/**
	 * ����geCodes��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geCodeType")
	public List<GeCode> getGeCodes() {
		return this.geCodes;
	}

	/**
	 * ����geCodes��setter����
	 */
	public void setGeCodes(List<GeCode> geCodes) {
		this.geCodes = geCodes;
	}

}
