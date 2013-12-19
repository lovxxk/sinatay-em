package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeProductInformBook
 */
@Entity
@Table(name = "GE_PRODUCT_INFORMBOOK")
public class GeProductInformBook implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性告知顺序 */
	private String informOrder;

	/** 属性告知内容 */
	private String informContent;

	/** 属性告知选项 */
	private String informOption;

	/**
	 * 类GeProductInformBook的默认构造方法
	 */
	public GeProductInformBook() {
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
	 * 属性产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * 属性产品的setter方法
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * 属性告知顺序的getter方法
	 */

	@Column(name = "INFORMORDER")
	public String getInformOrder() {
		return this.informOrder;
	}
	/**
	 * 属性告知顺序的setter方法
	 */
	public void setInformOrder(String informOrder) {
		this.informOrder = informOrder;
	}
	/**
	 * 属性告知内容的getter方法
	 */

	@Column(name = "INFORMCONTENT")
	public String getInformContent() {
		return this.informContent;
	}
	/**
	 * 属性告知内容的setter方法
	 */
	public void setInformContent(String informContent) {
		this.informContent = informContent;
	}
	/**
	 * 属性告知选项的getter方法
	 */

	@Column(name = "INFORMOPTION")
	public String getInformOption() {
		return this.informOption;
	}
	/**
	 * 属性告知选项的setter方法
	 */
	public void setInformOption(String informOption) {
		this.informOption = informOption;
	}

}
