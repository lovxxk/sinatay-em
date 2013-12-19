package cn.com.sinosoft.ebusiness.cms.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * POJO类CmsTemplet
 */
@Entity
@Table(name = "CMS_TEMPLET")
public class CmsTemplet implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性TplID */
	private Integer tplID;

	/** 属性TplName */
	private String tplName;

	/** 属性TplExtend */
	private String tplExtend;

	/** 属性TplDesc */
	private String tplDesc;

	/** 属性TplPath */
	private String tplPath;

	/** 属性TplPro */
	private String tplPro;

	/** 属性TplChnlID */
	private String tplChnlID;

	/** 属性TplType */
	private String tplType;

	/** 属性TplStoreName */
	private String tplStoreName;

	/**
	 * 类CmsTemplet的默认构造方法
	 */
	public CmsTemplet() {
	}

	/**
	 * 属性TplID的getter方法
	 */
	@Id
	@Column(name = "TPLID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_TEMPLET_SEQ",allocationSize=1)
	public Integer getTplID() {
		return this.tplID;
	}

	/**
	 * 属性TplID的setter方法
	 */
	public void setTplID(Integer tplID) {
		this.tplID = tplID;
	}

	/**
	 * 属性TplName的getter方法
	 */

	@Column(name = "TPLNAME")
	public String getTplName() {
		return this.tplName;
	}

	/**
	 * 属性TplName的setter方法
	 */
	public void setTplName(String tplName) {
		this.tplName = tplName;
	}

	/**
	 * 属性TplExtend的getter方法
	 */

	@Column(name = "TPLEXTEND")
	public String getTplExtend() {
		return this.tplExtend;
	}

	/**
	 * 属性TplExtend的setter方法
	 */
	public void setTplExtend(String tplExtend) {
		this.tplExtend = tplExtend;
	}

	/**
	 * 属性TplDesc的getter方法
	 */

	@Column(name = "TPLDESC")
	public String getTplDesc() {
		return this.tplDesc;
	}

	/**
	 * 属性TplDesc的setter方法
	 */
	public void setTplDesc(String tplDesc) {
		this.tplDesc = tplDesc;
	}

	/**
	 * 属性TplPath的getter方法
	 */

	@Column(name = "TPLPATH")
	public String getTplPath() {
		return this.tplPath;
	}

	/**
	 * 属性TplPath的setter方法
	 */
	public void setTplPath(String tplPath) {
		this.tplPath = tplPath;
	}

	/**
	 * 属性TplPro的getter方法
	 */

	@Column(name = "TPLPRO")
	public String getTplPro() {
		return this.tplPro;
	}

	/**
	 * 属性TplPro的setter方法
	 */
	public void setTplPro(String tplPro) {
		this.tplPro = tplPro;
	}

	/**
	 * 属性TplChnlID的getter方法
	 */

	@Column(name = "TPLCHNLID")
	public String getTplChnlID() {
		return this.tplChnlID;
	}

	/**
	 * 属性TplChnlID的setter方法
	 */
	public void setTplChnlID(String tplChnlID) {
		this.tplChnlID = tplChnlID;
	}

	/**
	 * 属性TplType的getter方法
	 */

	@Column(name = "TPLTYPE")
	public String getTplType() {
		return this.tplType;
	}

	/**
	 * 属性TplType的setter方法
	 */
	public void setTplType(String tplType) {
		this.tplType = tplType;
	}

	/**
	 * 属性TplStoreName的getter方法
	 */

	@Column(name = "TPLSTORENAME")
	public String getTplStoreName() {
		return this.tplStoreName;
	}

	/**
	 * 属性TplStoreName的setter方法
	 */
	public void setTplStoreName(String tplStoreName) {
		this.tplStoreName = tplStoreName;
	}

}
