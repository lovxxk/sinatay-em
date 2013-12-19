package cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain;
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
 * POJO类GeProductCorrelation
 */
@Entity
@Table(name = "GE_PRODUCT_CORRELATION")
public class GeProductCorrelation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性电子商务产品 */
	private GeDirectory geDirectoryByproduct;

	/** 属性电子商务产品推荐产品 */
	private GeDirectory geDirectoryByrecommendProduct;

	/** 属性记录两种产品关联度等级 比如用数字1-10表示 （1关联度最高，10关联度最低） */
	private Integer correclation;

	/**
	 * 类GeProductCorrelation的默认构造方法
	 */
	public GeProductCorrelation() {
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
	 * 属性电子商务产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	public GeDirectory getGeDirectoryByproduct() {
		return this.geDirectoryByproduct;
	}
	/**
	 * 属性电子商务产品的setter方法
	 */
	public void setGeDirectoryByproduct(GeDirectory geDirectoryByproduct) {
		this.geDirectoryByproduct = geDirectoryByproduct;
	}
	/**
	 * 属性电子商务产品推荐产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECOMMENDPRODUCT")
	public GeDirectory getGeDirectoryByrecommendProduct() {
		return this.geDirectoryByrecommendProduct;
	}
	/**
	 * 属性电子商务产品推荐产品的setter方法
	 */
	public void setGeDirectoryByrecommendProduct(
			GeDirectory geDirectoryByrecommendProduct) {
		this.geDirectoryByrecommendProduct = geDirectoryByrecommendProduct;
	}
	/**
	 * 属性记录两种产品关联度等级 比如用数字1-10表示 （1关联度最高，10关联度最低）的getter方法
	 */

	@Column(name = "CORRECLATION")
	public Integer getCorreclation() {
		return this.correclation;
	}
	/**
	 * 属性记录两种产品关联度等级 比如用数字1-10表示 （1关联度最高，10关联度最低）的setter方法
	 */
	public void setCorreclation(Integer correclation) {
		this.correclation = correclation;
	}

}
