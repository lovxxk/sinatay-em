package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO��GeArea
 */
@Entity
@Table(name = "GE_AREA")
public class GeArea implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �������ڵ������� */
	private String areaCode;

	/** ����areaname */
	private String areaname;

	/** ����upperareacode */
	private String upperareacode;

	/** ����������д */
	private String shortName;

	/** ����arealevel */
	private String arealevel;

	/** ���Ա�־λ */
	private String flag;

	/**
	 * ��GeArea��Ĭ�Ϲ��췽��
	 */
	public GeArea() {
	}

	/**
	 * �������ڵ��������getter����
	 */
	@Id
	@Column(name = "AREACODE")
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * �������ڵ��������setter����
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * ����areaname��getter����
	 */

	@Column(name = "AREANAME")
	public String getAreaname() {
		return this.areaname;
	}

	/**
	 * ����areaname��setter����
	 */
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	/**
	 * ����upperareacode��getter����
	 */

	@Column(name = "UPPERAREACODE")
	public String getUpperareacode() {
		return this.upperareacode;
	}

	/**
	 * ����upperareacode��setter����
	 */
	public void setUpperareacode(String upperareacode) {
		this.upperareacode = upperareacode;
	}

	/**
	 * ����������д��getter����
	 */

	@Column(name = "SHORTNAME")
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * ����������д��setter����
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * ����arealevel��getter����
	 */

	@Column(name = "AREALEVEL")
	public String getArealevel() {
		return this.arealevel;
	}

	/**
	 * ����arealevel��setter����
	 */
	public void setArealevel(String arealevel) {
		this.arealevel = arealevel;
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
	 * ����ʡ�ݺ���
	 */
	static public final String AREA_LEVEL_PROVINCE = "1";
	/**
	 * �����к���
	 */
	static public final String AREA_LEVEL_CITY = "2";
}
