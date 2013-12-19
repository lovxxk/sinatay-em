package cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeDirectoryAttributeRelate
 */
@Entity
@Table(name = "GE_DIRECTORY_ATTRIBUTE_RELATE")
public class GeDirectoryAttributeRelate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�ƷĿ¼������Ϣ��-GE_Directory */
	private GeDirectory geDirectory;

	/** ���Բ�Ʒ������Ϣ��-GE_Directory_Attribute_Info */
	private GeDirectoryAttributeInfo geDirectoryAttributeInfo;

	/**
	 * ��GeDirectoryAttributeRelate��Ĭ�Ϲ��췽��
	 */
	public GeDirectoryAttributeRelate() {
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
	 * ���Բ�ƷĿ¼������Ϣ��-GE_Directory��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EID", nullable = false)
	public GeDirectory getGeDirectory() {
		return this.geDirectory;
	}
	/**
	 * ���Բ�ƷĿ¼������Ϣ��-GE_Directory��setter����
	 */
	public void setGeDirectory(GeDirectory geDirectory) {
		this.geDirectory = geDirectory;
	}
	/**
	 * ���Բ�Ʒ������Ϣ��-GE_Directory_Attribute_Info��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATTRID", nullable = false)
	public GeDirectoryAttributeInfo getGeDirectoryAttributeInfo() {
		return this.geDirectoryAttributeInfo;
	}
	/**
	 * ���Բ�Ʒ������Ϣ��-GE_Directory_Attribute_Info��setter����
	 */
	public void setGeDirectoryAttributeInfo(
			GeDirectoryAttributeInfo geDirectoryAttributeInfo) {
		this.geDirectoryAttributeInfo = geDirectoryAttributeInfo;
	}

}
