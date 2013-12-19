package cn.com.sinosoft.ebusiness.cms.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * POJO��CmsTemplet
 */
@Entity
@Table(name = "CMS_TEMPLET")
public class CmsTemplet implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����TplID */
	private Integer tplID;

	/** ����TplName */
	private String tplName;

	/** ����TplExtend */
	private String tplExtend;

	/** ����TplDesc */
	private String tplDesc;

	/** ����TplPath */
	private String tplPath;

	/** ����TplPro */
	private String tplPro;

	/** ����TplChnlID */
	private String tplChnlID;

	/** ����TplType */
	private String tplType;

	/** ����TplStoreName */
	private String tplStoreName;

	/**
	 * ��CmsTemplet��Ĭ�Ϲ��췽��
	 */
	public CmsTemplet() {
	}

	/**
	 * ����TplID��getter����
	 */
	@Id
	@Column(name = "TPLID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_TEMPLET_SEQ",allocationSize=1)
	public Integer getTplID() {
		return this.tplID;
	}

	/**
	 * ����TplID��setter����
	 */
	public void setTplID(Integer tplID) {
		this.tplID = tplID;
	}

	/**
	 * ����TplName��getter����
	 */

	@Column(name = "TPLNAME")
	public String getTplName() {
		return this.tplName;
	}

	/**
	 * ����TplName��setter����
	 */
	public void setTplName(String tplName) {
		this.tplName = tplName;
	}

	/**
	 * ����TplExtend��getter����
	 */

	@Column(name = "TPLEXTEND")
	public String getTplExtend() {
		return this.tplExtend;
	}

	/**
	 * ����TplExtend��setter����
	 */
	public void setTplExtend(String tplExtend) {
		this.tplExtend = tplExtend;
	}

	/**
	 * ����TplDesc��getter����
	 */

	@Column(name = "TPLDESC")
	public String getTplDesc() {
		return this.tplDesc;
	}

	/**
	 * ����TplDesc��setter����
	 */
	public void setTplDesc(String tplDesc) {
		this.tplDesc = tplDesc;
	}

	/**
	 * ����TplPath��getter����
	 */

	@Column(name = "TPLPATH")
	public String getTplPath() {
		return this.tplPath;
	}

	/**
	 * ����TplPath��setter����
	 */
	public void setTplPath(String tplPath) {
		this.tplPath = tplPath;
	}

	/**
	 * ����TplPro��getter����
	 */

	@Column(name = "TPLPRO")
	public String getTplPro() {
		return this.tplPro;
	}

	/**
	 * ����TplPro��setter����
	 */
	public void setTplPro(String tplPro) {
		this.tplPro = tplPro;
	}

	/**
	 * ����TplChnlID��getter����
	 */

	@Column(name = "TPLCHNLID")
	public String getTplChnlID() {
		return this.tplChnlID;
	}

	/**
	 * ����TplChnlID��setter����
	 */
	public void setTplChnlID(String tplChnlID) {
		this.tplChnlID = tplChnlID;
	}

	/**
	 * ����TplType��getter����
	 */

	@Column(name = "TPLTYPE")
	public String getTplType() {
		return this.tplType;
	}

	/**
	 * ����TplType��setter����
	 */
	public void setTplType(String tplType) {
		this.tplType = tplType;
	}

	/**
	 * ����TplStoreName��getter����
	 */

	@Column(name = "TPLSTORENAME")
	public String getTplStoreName() {
		return this.tplStoreName;
	}

	/**
	 * ����TplStoreName��setter����
	 */
	public void setTplStoreName(String tplStoreName) {
		this.tplStoreName = tplStoreName;
	}

}
