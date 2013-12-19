package cn.com.sinosoft.ebusiness.marketing.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO��GeActivitiesConfig
 */
@Entity
@Table(name = "GE_ACTIVITIES_CONFIG")
public class GeActivitiesConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����productid */
	private String productid;

	/** ���Բ�Ʒ���� */
	private String productName;

	/** ����ͼƬ���� */
	private String pictureCount;
	
	/** ���Բ�Ʒ����01��02�ǳ�03�� ��ӦgecodeType ���е�ProductType���� */
	private String productType;
	
	/** �����Ƿ���Ч��־1����Ч0����Ч */
	private String validInd;
	
	/**
	 * ��GeActivitiesConfig��Ĭ�Ϲ��췽��
	 */
	public GeActivitiesConfig() {
	}

	/**
	 * ����productid��getter����
	 */
	@Id
	@Column(name = "PRODUCTID")
	public String getProductid() {
		return this.productid;
	}

	/**
	 * ����productid��setter����
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}

	/**
	 * ���Բ�Ʒ���Ƶ�getter����
	 */

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return this.productName;
	}

	/**
	 * ���Բ�Ʒ���Ƶ�setter����
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * ����ͼƬ������getter����
	 */

	@Column(name = "PICTURECOUNT")
	public String getPictureCount() {
		return this.pictureCount;
	}

	/**
	 * ����ͼƬ������setter����
	 */
	public void setPictureCount(String pictureCount) {
		this.pictureCount = pictureCount;
	}
	
	/**
	 * ���Բ�Ʒ�����setter����
	 */
	@Column(name = "PRODUCTTYPE")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * �����Ƿ���Ч��setter����
	 */
	@Column(name = "VALIDIND")
	public String getValidInd() {
		return validInd;
	}

	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

}
