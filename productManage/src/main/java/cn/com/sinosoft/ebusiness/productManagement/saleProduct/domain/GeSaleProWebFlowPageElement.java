package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageElementConfig;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeSaleProWebFlowPageElement
 */
@Entity
@Table(name = "GE_SALE_PRO_WEBFLOWPAGEELEMENT")
public class GeSaleProWebFlowPageElement implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** 属性元素配置 */
	private GePageElementConfig gePageElementConfig;

	/** 属性 */
	private GeSaleProWebFlowPage geSaleProWebFlowPage;

	/** 属性处理状态（'0'未定制，'1'已定制） */
	private String status;

	/** 属性显示顺序 */
	private Integer seqIndex;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/**
	 * 类GeSaleProWebFlowPageElement的默认构造方法
	 */
	public GeSaleProWebFlowPageElement() {
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
	 * 属性geSaleProduct的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE")
	public GeSaleProduct getGeSaleProduct() {
		return this.geSaleProduct;
	}
	/**
	 * 属性geSaleProduct的setter方法
	 */
	public void setGeSaleProduct(GeSaleProduct geSaleProduct) {
		this.geSaleProduct = geSaleProduct;
	}
	/**
	 * 属性元素配置的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ELEMENTCODE")
	public GePageElementConfig getGePageElementConfig() {
		return this.gePageElementConfig;
	}
	/**
	 * 属性元素配置的setter方法
	 */
	public void setGePageElementConfig(GePageElementConfig gePageElementConfig) {
		this.gePageElementConfig = gePageElementConfig;
	}
	/**
	 * 属性的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLOWPAGESERIALNO")
	public GeSaleProWebFlowPage getGeSaleProWebFlowPage() {
		return this.geSaleProWebFlowPage;
	}
	/**
	 * 属性的setter方法
	 */
	public void setGeSaleProWebFlowPage(
			GeSaleProWebFlowPage geSaleProWebFlowPage) {
		this.geSaleProWebFlowPage = geSaleProWebFlowPage;
	}
	/**
	 * 属性处理状态（'0'未定制，'1'已定制）的getter方法
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}
	/**
	 * 属性处理状态（'0'未定制，'1'已定制）的setter方法
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 属性显示顺序的getter方法
	 */

	@Column(name = "SEQINDEX")
	public Integer getSeqIndex() {
		return this.seqIndex;
	}
	/**
	 * 属性显示顺序的setter方法
	 */
	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
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

}
