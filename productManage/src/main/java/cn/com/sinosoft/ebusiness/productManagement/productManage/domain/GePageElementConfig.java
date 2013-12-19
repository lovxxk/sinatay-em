package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlowPageElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO��GePageElementConfig
 */
@Entity
@Table(name = "GE_PAGEELEMENTCONFIG")
public class GePageElementConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// ����
	/** ����Ԫ�ش��� */
	private String elementCode;

	/** ����Ԫ������ */
	private String elementName;

	/** ���Դ���URL */
	private String handleURL;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ���Ա�ע */
	private String remark;

	/** ����Ĭ��˳�� */
	private Integer defaultSeqIndex;

	/** ����geSaleProWebFlowPageElements */
	private List<GeSaleProWebFlowPageElement> geSaleProWebFlowPageElements = new ArrayList<GeSaleProWebFlowPageElement>(
			0);

	/** ����geWebFlowPageElements */
	private List<GeWebFlowPageElement> geWebFlowPageElements = new ArrayList<GeWebFlowPageElement>(
			0);

	/** ����gePageConfigRelates */
	private List<GePageConfigRelate> gePageConfigRelates = new ArrayList<GePageConfigRelate>(
			0);

	/**
	 * ��GePageElementConfig��Ĭ�Ϲ��췽��
	 */
	public GePageElementConfig() {
	}

	/**
	 * ����Ԫ�ش����getter����
	 */
	@Id
	@Column(name = "ELEMENTCODE")
	public String getElementCode() {
		return this.elementCode;
	}
	/**
	 * ����Ԫ�ش����setter����
	 */
	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}
	/**
	 * ����Ԫ�����Ƶ�getter����
	 */

	@Column(name = "ELEMENTNAME")
	public String getElementName() {
		return this.elementName;
	}
	/**
	 * ����Ԫ�����Ƶ�setter����
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	/**
	 * ���Դ���URL��getter����
	 */

	@Column(name = "HANDLEURL")
	public String getHandleURL() {
		return this.handleURL;
	}
	/**
	 * ���Դ���URL��setter����
	 */
	public void setHandleURL(String handleURL) {
		this.handleURL = handleURL;
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
	 * ����Ĭ��˳���getter����
	 */

	@Column(name = "DEFAULTSEQINDEX")
	public Integer getDefaultSeqIndex() {
		return this.defaultSeqIndex;
	}
	/**
	 * ����Ĭ��˳���setter����
	 */
	public void setDefaultSeqIndex(Integer defaultSeqIndex) {
		this.defaultSeqIndex = defaultSeqIndex;
	}
	/**
	 * ����geSaleProWebFlowPageElements��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageElementConfig")
	public List<GeSaleProWebFlowPageElement> getGeSaleProWebFlowPageElements() {
		return this.geSaleProWebFlowPageElements;
	}
	/**
	 * ����geSaleProWebFlowPageElements��setter����
	 */
	public void setGeSaleProWebFlowPageElements(
			List<GeSaleProWebFlowPageElement> geSaleProWebFlowPageElements) {
		this.geSaleProWebFlowPageElements = geSaleProWebFlowPageElements;
	}
	/**
	 * ����geWebFlowPageElements��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageElementConfig")
	public List<GeWebFlowPageElement> getGeWebFlowPageElements() {
		return this.geWebFlowPageElements;
	}
	/**
	 * ����geWebFlowPageElements��setter����
	 */
	public void setGeWebFlowPageElements(
			List<GeWebFlowPageElement> geWebFlowPageElements) {
		this.geWebFlowPageElements = geWebFlowPageElements;
	}
	/**
	 * ����gePageConfigRelates��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageElementConfig")
	public List<GePageConfigRelate> getGePageConfigRelates() {
		return this.gePageConfigRelates;
	}
	/**
	 * ����gePageConfigRelates��setter����
	 */
	public void setGePageConfigRelates(
			List<GePageConfigRelate> gePageConfigRelates) {
		this.gePageConfigRelates = gePageConfigRelates;
	}

}
