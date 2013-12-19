package cn.com.sinosoft.ebusiness.sys.permission.domain;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * POJO��GeDeptGroupRole
 */
@Entity
@Table(name = "GE_DEPT_GROUP_ROLE")
public class GeDeptGroupRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ժ�������� */
	private GeDeptGroupRoleId id;

	/** �����û����-GE_Group */
	private GeGroup geGroup;

	/** ���Ժ�̨��ɫ��-GE_Role */
	private GeRole geRole;
	
	private String flag;

	/**
	 * ��GeDeptGroupRole��Ĭ�Ϲ��췽��
	 */
	public GeDeptGroupRole() {
	}

	/**
	 * ���Ժ�������ŵ�getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleID", column = @Column(name = "ROLEID")),
			@AttributeOverride(name = "groupID", column = @Column(name = "GROUPID")),
			@AttributeOverride(name = "deptID", column = @Column(name = "DEPTID")) })
	public GeDeptGroupRoleId getId() {
		return this.id;
	}

	/**
	 * ���Ժ�������ŵ�setter����
	 */
	public void setId(GeDeptGroupRoleId id) {
		this.id = id;
	}

	/**
	 * �����û����-GE_Group��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUPID", nullable = false, insertable = false, updatable = false)
	public GeGroup getGeGroup() {
		return this.geGroup;
	}

	/**
	 * �����û����-GE_Group��setter����
	 */
	public void setGeGroup(GeGroup geGroup) {
		this.geGroup = geGroup;
	}

	/**
	 * ���Ժ�̨��ɫ��-GE_Role��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLEID", nullable = false, insertable = false, updatable = false)
	public GeRole getGeRole() {
		return this.geRole;
	}

	/**
	 * ���Ժ�̨��ɫ��-GE_Role��setter����
	 */
	public void setGeRole(GeRole geRole) {
		this.geRole = geRole;
	}
	
	/**
	 * �����ӻ����Ƿ�ȫѡ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * �����ӻ����Ƿ�ȫѡ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
