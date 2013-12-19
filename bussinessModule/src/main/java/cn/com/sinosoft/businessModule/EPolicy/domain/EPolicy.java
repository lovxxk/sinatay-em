package cn.com.sinosoft.businessModule.EPolicy.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��Epolicy
 */
@Entity
@Table(name = "EPOLICY")
public class EPolicy implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Էֹ�˾�� */
	private String comCode;

	/** ���Ա����� */
	private String policyNo;

	/** ���Զ����� */
	private String orderNo;

	/** ������Ч��� 1-��Ч��2-���ϴ�CM */
	private Integer vaildFlag;

	/** ����PDF·�� */
	private String path;

	/** ����PDF����·��*/
	private String absolutePath;
	
	/** ���Զ�Ӧ�ϴ�CM���ItemId */
	private String itemId;

	/** �����ϴ�ʱ�䣬ȡ����д���current Time */
	private Date daTime;

	/** �����ϴ�CMʱ�䣬ȡ�ϴ�CM������current Time */
	private Date cmTime;

	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/** �����Ƿ����ع� */
	private Integer isDownload = 0;

	/** �������ش��� */
	private Integer downloadCount = 0;

	/** �������һ������ʱ�� */
	private Date lastDownloadTime = new Date();;

	/**
	 * ��EPolicy��Ĭ�Ϲ��췽��
	 */
	public EPolicy() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * ���Էֹ�˾�ŵ�getter����
	 */

	@Column(name = "COM_CODE")
	public String getComCode() {
		return this.comCode;
	}

	/**
	 * ���Էֹ�˾�ŵ�setter����
	 */
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	/**
	 * ���Ա����ŵ�getter����
	 */

	@Column(name = "POLICY_NO")
	public String getPolicyNo() {
		return this.policyNo;
	}

	/**
	 * ���Ա����ŵ�setter����
	 */
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	/**
	 * ���Զ����ŵ�getter����
	 */

	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * ���Զ����ŵ�setter����
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * ������Ч��� 1-��Ч��2-���ϴ�CM��getter����
	 */

	@Column(name = "VAILD_FLAG")
	public Integer getVaildFlag() {
		return this.vaildFlag;
	}

	/**
	 * ������Ч��� 1-��Ч��2-���ϴ�CM��setter����
	 */
	public void setVaildFlag(Integer vaildFlag) {
		this.vaildFlag = vaildFlag;
	}

	/**
	 * ����PDF·����getter����
	 */
	@Column(name = "PATH")
	public String getPath() {
		return this.path;
	}

	/**
	 * ����PDF·����setter����
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * ����PDF����·����getter����
	 */
	@Column(name = "ABSOLUTEPATH")
	public String getAbsolutePath() {
		return absolutePath;
	}
	
	/**
	 * ����PDF����·����setter����
	 */
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	/**
	 * ���Զ�Ӧ�ϴ�CM���ItemId��getter����
	 */

	@Column(name = "ITEMID")
	public String getItemId() {
		return this.itemId;
	}

	/**
	 * ���Զ�Ӧ�ϴ�CM���ItemId��setter����
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * �����ϴ�ʱ�䣬ȡ����д���current Time��getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATIME")
	public Date getDaTime() {
		return this.daTime;
	}

	/**
	 * �����ϴ�ʱ�䣬ȡ����д���current Time��setter����
	 */
	public void setDaTime(Date daTime) {
		this.daTime = daTime;
	}

	/**
	 * �����ϴ�CMʱ�䣬ȡ�ϴ�CM������current Time��getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CMTIME")
	public Date getCmTime() {
		return this.cmTime;
	}

	/**
	 * �����ϴ�CMʱ�䣬ȡ�ϴ�CM������current Time��setter����
	 */
	public void setCmTime(Date cmTime) {
		this.cmTime = cmTime;
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * �����Ƿ����ع���getter����
	 */

	@Column(name = "ISDOWNLOAD")
	public Integer getIsDownload() {
		return this.isDownload;
	}

	/**
	 * �����Ƿ����ع���setter����
	 */
	public void setIsDownload(Integer isDownload) {
		this.isDownload = isDownload;
	}

	/**
	 * �������ش�����getter����
	 */

	@Column(name = "DOWNLOADCOUNT")
	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	/**
	 * �������ش�����setter����
	 */
	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	/**
	 * �������һ������ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTDOWNLOADTIME")
	public Date getLastDownloadTime() {
		return this.lastDownloadTime;
	}

	/**
	 * �������һ������ʱ���setter����
	 */
	public void setLastDownloadTime(Date lastDownloadTime) {
		this.lastDownloadTime = lastDownloadTime;
	}

}
