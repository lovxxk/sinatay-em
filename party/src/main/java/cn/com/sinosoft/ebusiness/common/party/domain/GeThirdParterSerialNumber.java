package cn.com.sinosoft.ebusiness.common.party.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


/**
 * POJO��GeThirdParterSerialNumber
 */
@Entity
@Table(name = "GE_THIRDPARTER_SERIALNUMBER")
public class GeThirdParterSerialNumber implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������ˮ�� */
	private String searialNo;

	/** ���Ե�������Ʒ������Ϣ��-GE_ThirdParter_Service */
	private GeThirdParterService geThirdParterService;

	/** ����Ͷ������ */
	private String proposalNo;
	private String userID;
	/** �����ձ����� */
	private String description;

	/** ���Բ���ʱ�� */
	private Date opertionDate;

	/** �����Ƿ���Ч��־ */
	private String validInd;
	
	/** ����Ͷ������ */
	private String proposalArea;
	
	/** �������� */
	private String count;

	/** ���Ա�־λ */
	private String flag;
	//ҵ����ʹ�õ�start
	private String proposalAreaName;
	//ҵ����ʹ�õ�end
	/**
	 * ��GeThirdParterSerialNumber��Ĭ�Ϲ��췽��
	 */
	public GeThirdParterSerialNumber() {
	}

	/**
	 * ������ˮ�ŵ�getter����
	 */
	@Id
	@Column(name = "SEARIALNO", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSearialNo() {
		return this.searialNo;
	}


	/**
	 * ������ˮ�ŵ�setter����
	 */
	public void setSearialNo(String searialNo) {
		this.searialNo = searialNo;
	}



	/**
	 * ���Ե�������Ʒ������Ϣ��-GE_ThirdParter_Service��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEMID")
	public GeThirdParterService getGeThirdParterService() {
		return this.geThirdParterService;
	}

	/**
	 * ���Ե�������Ʒ������Ϣ��-GE_ThirdParter_Service��setter����
	 */
	public void setGeThirdParterService(
			GeThirdParterService geThirdParterService) {
		this.geThirdParterService = geThirdParterService;
	}

	/**
	 * ����Ͷ�����ŵ�getter����
	 */

	@Column(name = "PROPOSALNO")
	public String getProposalNo() {
		return this.proposalNo;
	}

	/**
	 * ����Ͷ�����ŵ�setter����
	 */
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	/**
	 * �����û��ŵ�getter����
	 */

	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * �����û��ŵ�setter����
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * �����ձ�������getter����
	 */

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	/**
	 * �����ձ�������setter����
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ���Բ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERTIONDATE")
	public Date getOpertionDate() {
		return this.opertionDate;
	}

	/**
	 * ���Բ���ʱ���setter����
	 */
	public void setOpertionDate(Date opertionDate) {
		this.opertionDate = opertionDate;
	}
	/**
	 * �����Ƿ���Ч��־��getter����
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * �����Ƿ���Ч��־��setter����
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}

	@Column(name = "PROPOSALAREA")
	public String getProposalArea() {
		return proposalArea;
	}

	public void setProposalArea(String proposalArea) {
		this.proposalArea = proposalArea;
	}
	
	/**
	 * ����������getter����
	 */

	@Column(name = "COUNT")
	public String getCount() {
		return this.count;
	}

	/**
	 * ����������setter����
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * ���Ա�־λ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�ʶλ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	//ҵ����ʹ�õ�start
	@Transient
	public String getProposalAreaName() {
		return proposalAreaName;
	}

	public void setProposalAreaName(String proposalAreaName) {
		this.proposalAreaName = proposalAreaName;
	}
	//ҵ����ʹ�õ�end
}
