package cn.com.sinosoft.portalModule.interfacePortal.domain;
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
 * POJO��ExternalSysUserInterfaceInfo
 */
@Entity
@Table(name = "GE_PORTAL_EXSYSUSER_INTERFACE")
public class ExternalSysUserInterfaceInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �����߼����� */
	private String id;

	/** ����externalSystemsUser */
	private ExternalSystemsUser externalSystemsUser;

	/** ����interfaceInfo */
	private InterfaceInfo interfaceInfo;

	/**
	 * ��ExternalSysUserInterfaceInfo��Ĭ�Ϲ��췽��
	 */
	public ExternalSysUserInterfaceInfo() {
	}

	/**
	 * �����߼�������getter����
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getId() {
		return this.id;
	}
	/**
	 * �����߼�������setter����
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * ����externalSystemsUser��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXSYSUSERID", nullable = false)
	public ExternalSystemsUser getExternalSystemsUser() {
		return this.externalSystemsUser;
	}
	/**
	 * ����externalSystemsUser��setter����
	 */
	public void setExternalSystemsUser(ExternalSystemsUser externalSystemsUser) {
		this.externalSystemsUser = externalSystemsUser;
	}
	/**
	 * ����interfaceInfo��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSCODE", nullable = false)
	public InterfaceInfo getInterfaceInfo() {
		return this.interfaceInfo;
	}
	/**
	 * ����interfaceInfo��setter����
	 */
	public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

}
