package cn.com.sinosoft.businessModule.EPolicy.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类Epolicy
 */
@Entity
@Table(name = "EPOLICY")
public class EPolicy implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性分公司号 */
	private String comCode;

	/** 属性保单号 */
	private String policyNo;

	/** 属性订单号 */
	private String orderNo;

	/** 属性有效标记 1-有效，2-已上传CM */
	private Integer vaildFlag;

	/** 属性PDF路径 */
	private String path;

	/** 属性PDF绝度路径*/
	private String absolutePath;
	
	/** 属性对应上传CM后的ItemId */
	private String itemId;

	/** 属性上传时间，取数据写入的current Time */
	private Date daTime;

	/** 属性上传CM时间，取上传CM操作的current Time */
	private Date cmTime;

	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/** 属性是否下载过 */
	private Integer isDownload = 0;

	/** 属性下载次数 */
	private Integer downloadCount = 0;

	/** 属性最后一次下载时间 */
	private Date lastDownloadTime = new Date();;

	/**
	 * 类EPolicy的默认构造方法
	 */
	public EPolicy() {
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * 属性分公司号的getter方法
	 */

	@Column(name = "COM_CODE")
	public String getComCode() {
		return this.comCode;
	}

	/**
	 * 属性分公司号的setter方法
	 */
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	/**
	 * 属性保单号的getter方法
	 */

	@Column(name = "POLICY_NO")
	public String getPolicyNo() {
		return this.policyNo;
	}

	/**
	 * 属性保单号的setter方法
	 */
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	/**
	 * 属性订单号的getter方法
	 */

	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 属性订单号的setter方法
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 属性有效标记 1-有效，2-已上传CM的getter方法
	 */

	@Column(name = "VAILD_FLAG")
	public Integer getVaildFlag() {
		return this.vaildFlag;
	}

	/**
	 * 属性有效标记 1-有效，2-已上传CM的setter方法
	 */
	public void setVaildFlag(Integer vaildFlag) {
		this.vaildFlag = vaildFlag;
	}

	/**
	 * 属性PDF路径的getter方法
	 */
	@Column(name = "PATH")
	public String getPath() {
		return this.path;
	}

	/**
	 * 属性PDF路径的setter方法
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * 属性PDF绝对路径的getter方法
	 */
	@Column(name = "ABSOLUTEPATH")
	public String getAbsolutePath() {
		return absolutePath;
	}
	
	/**
	 * 属性PDF绝对路径的setter方法
	 */
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	/**
	 * 属性对应上传CM后的ItemId的getter方法
	 */

	@Column(name = "ITEMID")
	public String getItemId() {
		return this.itemId;
	}

	/**
	 * 属性对应上传CM后的ItemId的setter方法
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 属性上传时间，取数据写入的current Time的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATIME")
	public Date getDaTime() {
		return this.daTime;
	}

	/**
	 * 属性上传时间，取数据写入的current Time的setter方法
	 */
	public void setDaTime(Date daTime) {
		this.daTime = daTime;
	}

	/**
	 * 属性上传CM时间，取上传CM操作的current Time的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CMTIME")
	public Date getCmTime() {
		return this.cmTime;
	}

	/**
	 * 属性上传CM时间，取上传CM操作的current Time的setter方法
	 */
	public void setCmTime(Date cmTime) {
		this.cmTime = cmTime;
	}

	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 属性是否下载过的getter方法
	 */

	@Column(name = "ISDOWNLOAD")
	public Integer getIsDownload() {
		return this.isDownload;
	}

	/**
	 * 属性是否下载过的setter方法
	 */
	public void setIsDownload(Integer isDownload) {
		this.isDownload = isDownload;
	}

	/**
	 * 属性下载次数的getter方法
	 */

	@Column(name = "DOWNLOADCOUNT")
	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	/**
	 * 属性下载次数的setter方法
	 */
	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	/**
	 * 属性最后一次下载时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTDOWNLOADTIME")
	public Date getLastDownloadTime() {
		return this.lastDownloadTime;
	}

	/**
	 * 属性最后一次下载时间的setter方法
	 */
	public void setLastDownloadTime(Date lastDownloadTime) {
		this.lastDownloadTime = lastDownloadTime;
	}

}
