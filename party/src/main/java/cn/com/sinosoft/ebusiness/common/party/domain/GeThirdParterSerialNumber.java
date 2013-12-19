package cn.com.sinosoft.ebusiness.common.party.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。
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
 * POJO类GeThirdParterSerialNumber
 */
@Entity
@Table(name = "GE_THIRDPARTER_SERIALNUMBER")
public class GeThirdParterSerialNumber implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性流水号 */
	private String searialNo;

	/** 属性第三方产品服务信息表-GE_ThirdParter_Service */
	private GeThirdParterService geThirdParterService;

	/** 属性投保单号 */
	private String proposalNo;
	private String userID;
	/** 属性险别描述 */
	private String description;

	/** 属性操作时间 */
	private Date opertionDate;

	/** 属性是否有效标志 */
	private String validInd;
	
	/** 属性投保地区 */
	private String proposalArea;
	
	/** 属性数量 */
	private String count;

	/** 属性标志位 */
	private String flag;
	//业务上使用的start
	private String proposalAreaName;
	//业务上使用的end
	/**
	 * 类GeThirdParterSerialNumber的默认构造方法
	 */
	public GeThirdParterSerialNumber() {
	}

	/**
	 * 属性流水号的getter方法
	 */
	@Id
	@Column(name = "SEARIALNO", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSearialNo() {
		return this.searialNo;
	}


	/**
	 * 属性流水号的setter方法
	 */
	public void setSearialNo(String searialNo) {
		this.searialNo = searialNo;
	}



	/**
	 * 属性第三方产品服务信息表-GE_ThirdParter_Service的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEMID")
	public GeThirdParterService getGeThirdParterService() {
		return this.geThirdParterService;
	}

	/**
	 * 属性第三方产品服务信息表-GE_ThirdParter_Service的setter方法
	 */
	public void setGeThirdParterService(
			GeThirdParterService geThirdParterService) {
		this.geThirdParterService = geThirdParterService;
	}

	/**
	 * 属性投保单号的getter方法
	 */

	@Column(name = "PROPOSALNO")
	public String getProposalNo() {
		return this.proposalNo;
	}

	/**
	 * 属性投保单号的setter方法
	 */
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	/**
	 * 属性用户号的getter方法
	 */

	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * 属性用户号的setter方法
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * 属性险别描述的getter方法
	 */

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	/**
	 * 属性险别描述的setter方法
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 属性操作时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERTIONDATE")
	public Date getOpertionDate() {
		return this.opertionDate;
	}

	/**
	 * 属性操作时间的setter方法
	 */
	public void setOpertionDate(Date opertionDate) {
		this.opertionDate = opertionDate;
	}
	/**
	 * 属性是否有效标志的getter方法
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * 属性是否有效标志的setter方法
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
	 * 属性数量的getter方法
	 */

	@Column(name = "COUNT")
	public String getCount() {
		return this.count;
	}

	/**
	 * 属性数量的setter方法
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * 属性标志位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标识位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	//业务上使用的start
	@Transient
	public String getProposalAreaName() {
		return proposalAreaName;
	}

	public void setProposalAreaName(String proposalAreaName) {
		this.proposalAreaName = proposalAreaName;
	}
	//业务上使用的end
}
