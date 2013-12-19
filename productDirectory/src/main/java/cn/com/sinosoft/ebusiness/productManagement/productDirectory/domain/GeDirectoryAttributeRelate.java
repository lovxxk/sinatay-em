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
 * POJO类GeDirectoryAttributeRelate
 */
@Entity
@Table(name = "GE_DIRECTORY_ATTRIBUTE_RELATE")
public class GeDirectoryAttributeRelate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品目录基本信息表-GE_Directory */
	private GeDirectory geDirectory;

	/** 属性产品属性信息表-GE_Directory_Attribute_Info */
	private GeDirectoryAttributeInfo geDirectoryAttributeInfo;

	/**
	 * 类GeDirectoryAttributeRelate的默认构造方法
	 */
	public GeDirectoryAttributeRelate() {
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
	 * 属性产品目录基本信息表-GE_Directory的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EID", nullable = false)
	public GeDirectory getGeDirectory() {
		return this.geDirectory;
	}
	/**
	 * 属性产品目录基本信息表-GE_Directory的setter方法
	 */
	public void setGeDirectory(GeDirectory geDirectory) {
		this.geDirectory = geDirectory;
	}
	/**
	 * 属性产品属性信息表-GE_Directory_Attribute_Info的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATTRID", nullable = false)
	public GeDirectoryAttributeInfo getGeDirectoryAttributeInfo() {
		return this.geDirectoryAttributeInfo;
	}
	/**
	 * 属性产品属性信息表-GE_Directory_Attribute_Info的setter方法
	 */
	public void setGeDirectoryAttributeInfo(
			GeDirectoryAttributeInfo geDirectoryAttributeInfo) {
		this.geDirectoryAttributeInfo = geDirectoryAttributeInfo;
	}

}
