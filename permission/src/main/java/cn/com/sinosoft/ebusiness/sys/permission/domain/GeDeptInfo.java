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

	/** 属性属性ID */
	private String attrID;

	/** 属性属性名称 */
	private String attrName;

	/** 属性属性描述 */
	private String attrDescription;

	/** 属性geDeptAttributes */
	private List<GeDeptAttribute> geDeptAttributes = new ArrayList<GeDeptAttribute>(
			0);

	/**
	 * 类GeDeptInfo的默认构造方法
	 */
	public GeDeptInfo() {
	}

	/**
	 * 属性属性ID的getter方法
	 */
	@Id
	@Column(name = "ATTRID")
	public String getAttrID() {
		return this.attrID;
	}

	/**
	 * 属性属性ID的setter方法
	 */
	public void setAttrID(String attrID) {
		this.attrID = attrID;
	}

	/**
	 * 属性属性名称的getter方法
	 */

	@Column(name = "ATTRNAME")
	public String getAttrName() {
		return this.attrName;
	}

	/**
	 * 属性属性名称的setter方法
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	/**
	 * 属性属性描述的getter方法
	 */

	@Column(name = "ATTRDESCRIPTION")
	public String getAttrDescription() {
		return this.attrDescription;
	}

	/**
	 * 属性属性描述的setter方法
	 */
	public void setAttrDescription(String attrDescription) {
		this.attrDescription = attrDescription;
	}

	/**
	 * 属性geDeptAttributes的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDeptInfo")
	public List<GeDeptAttribute> getGeDeptAttributes() {
		return this.geDeptAttributes;
	}

	/**
	 * 属性geDeptAttributes的setter方法
	 */
	public void setGeDeptAttributes(List<GeDeptAttribute> geDeptAttributes) {
		this.geDeptAttributes = geDeptAttributes;
	}

}
