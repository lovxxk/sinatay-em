package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlowPage;
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
 * POJO��GePageConfig
 */
@Entity
@Table(name = "GE_PAGECONFIG")
public class GePageConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	//��ҳ�棨����ʾ��
	
	
	/** ����ҳ����� */
	private String pageCode;

	/** ����ҳ������ */
	private String pageName;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ���Ա�ע */
	private String remark;

	/** ����gePageConfigRelates */
	private List<GePageConfigRelate> gePageConfigRelates = new ArrayList<GePageConfigRelate>(
			0);

	/** ����geWebFlowPages */
	private List<GeWebFlowPage> geWebFlowPages = new ArrayList<GeWebFlowPage>(0);

	/** ����geSaleProWebFlowPages */
	private List<GeSaleProWebFlowPage> geSaleProWebFlowPages = new ArrayList<GeSaleProWebFlowPage>(
			0);

	/** ����geFlowConfigRelates */
	private List<GeFlowConfigRelate> geFlowConfigRelates = new ArrayList<GeFlowConfigRelate>(
			0);

	/**
	 * ��GePageConfig��Ĭ�Ϲ��췽��
	 */
	public GePageConfig() {
	}

	/**
	 * ����ҳ������getter����
	 */
	@Id
	@Column(name = "PAGECODE")
	public String getPageCode() {
		return this.pageCode;
	}
	/**
	 * ����ҳ������setter����
	 */
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	/**
	 * ����ҳ�����Ƶ�getter����
	 */

	@Column(name = "PAGENAME")
	public String getPageName() {
		return this.pageName;
	}
	/**
	 * ����ҳ�����Ƶ�setter����
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
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
	 * ����gePageConfigRelates��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageConfig")
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
	/**
	 * ����geWebFlowPages��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageConfig")
	public List<GeWebFlowPage> getGeWebFlowPages() {
		return this.geWebFlowPages;
	}
	/**
	 * ����geWebFlowPages��setter����
	 */
	public void setGeWebFlowPages(List<GeWebFlowPage> geWebFlowPages) {
		this.geWebFlowPages = geWebFlowPages;
	}
	/**
	 * ����geSaleProWebFlowPages��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageConfig")
	public List<GeSaleProWebFlowPage> getGeSaleProWebFlowPages() {
		return this.geSaleProWebFlowPages;
	}
	/**
	 * ����geSaleProWebFlowPages��setter����
	 */
	public void setGeSaleProWebFlowPages(
			List<GeSaleProWebFlowPage> geSaleProWebFlowPages) {
		this.geSaleProWebFlowPages = geSaleProWebFlowPages;
	}
	/**
	 * ����geFlowConfigRelates��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageConfig")
	public List<GeFlowConfigRelate> getGeFlowConfigRelates() {
		return this.geFlowConfigRelates;
	}
	/**
	 * ����geFlowConfigRelates��setter����
	 */
	public void setGeFlowConfigRelates(
			List<GeFlowConfigRelate> geFlowConfigRelates) {
		this.geFlowConfigRelates = geFlowConfigRelates;
	}

}
