package cn.com.sinosoft.ebusiness.cms.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * POJO��CmsChannelTemplet
 */
@Entity
@Table(name = "CMS_CHANNEL_TEMPLET")
public class CmsChannelTemplet implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����ID */
	private Integer id;

	/** ����chlid */
	private String chlid;

	/** ����tid */
	private String tid;

	/** ����attr */
	private String attr;

	/**
	 * ��CmsChannelTemplet��Ĭ�Ϲ��췽��
	 */
	public CmsChannelTemplet() {
	}

	/**
	 * ����ID��getter����
	 */
	@Id
	@Column(name = "ID" ,unique = true, nullable = false)
	@GeneratedValue(generator = "SEQGenerator",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQGenerator",sequenceName = "CMS_CHANNEL_TEMPLET_SEQ",allocationSize=1)
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����ID��setter����
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ����chlid��getter����
	 */

	@Column(name = "CHLID")
	public String getChlid() {
		return this.chlid;
	}

	/**
	 * ����chlid��setter����
	 */
	public void setChlid(String chlid) {
		this.chlid = chlid;
	}

	/**
	 * ����tid��getter����
	 */

	@Column(name = "TID")
	public String getTid() {
		return this.tid;
	}

	/**
	 * ����tid��setter����
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * ����attr��getter����
	 */

	@Column(name = "ATTR")
	public String getAttr() {
		return this.attr;
	}

	/**
	 * ����attr��setter����
	 */
	public void setAttr(String attr) {
		this.attr = attr;
	}

}
