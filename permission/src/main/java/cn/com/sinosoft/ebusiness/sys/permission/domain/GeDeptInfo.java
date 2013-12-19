package cn.com.sinosoft.ebusiness.sys.permission.domain;
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
 * GeDeptInfo
 */
@Entity
@Table(name = "GE_DEPT_INFO")
public class GeDeptInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ��������ID */
	private String attrID;

	/** ������������ */
	private String attrName;

	/** ������������ */
	private String attrDescription;

	/** ����geDeptAttributes */
	private List<GeDeptAttribute> geDeptAttributes = new ArrayList<GeDeptAttribute>(
			0);

	/**
	 * ��GeDeptInfo��Ĭ�Ϲ��췽��
	 */
	public GeDeptInfo() {
	}

	/**
	 * ��������ID��getter����
	 */
	@Id
	@Column(name = "ATTRID")
	public String getAttrID() {
		return this.attrID;
	}

	/**
	 * ��������ID��setter����
	 */
	public void setAttrID(String attrID) {
		this.attrID = attrID;
	}

	/**
	 * �����������Ƶ�getter����
	 */

	@Column(name = "ATTRNAME")
	public String getAttrName() {
		return this.attrName;
	}

	/**
	 * �����������Ƶ�setter����
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	/**
	 * ��������������getter����
	 */

	@Column(name = "ATTRDESCRIPTION")
	public String getAttrDescription() {
		return this.attrDescription;
	}

	/**
	 * ��������������setter����
	 */
	public void setAttrDescription(String attrDescription) {
		this.attrDescription = attrDescription;
	}

	/**
	 * ����geDeptAttributes��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDeptInfo")
	public List<GeDeptAttribute> getGeDeptAttributes() {
		return this.geDeptAttributes;
	}

	/**
	 * ����geDeptAttributes��setter����
	 */
	public void setGeDeptAttributes(List<GeDeptAttribute> geDeptAttributes) {
		this.geDeptAttributes = geDeptAttributes;
	}

}
