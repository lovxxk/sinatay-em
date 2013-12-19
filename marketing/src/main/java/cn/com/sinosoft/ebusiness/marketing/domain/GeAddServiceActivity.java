package cn.com.sinosoft.ebusiness.marketing.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeAddServiceActivity
 */
@Entity
@Table(name = "GE_ADDSERVICE_ACTIVITY")
public class GeAddServiceActivity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性活动代码 */
	private String activityId;

	/** 属性活动名称 */
	private String activityName;
	
	/** 属性活动内容介绍 */
	private String activityContent;

	/** 属性地区 */
	private String deptID;

	/** 属性活动状态 */
	private String status;

	/** 属性活动起始时间 */
	private Date activityStartDate;

	/** 属性活动结束时间 */
	private Date activityEndDate;
	
	/** 属性是否有效 */
	private String validInd;
	
	/** 属性创建者机构代码 */
	private String createDeptId;
	
	/** 属性更新者机构代码 */
	private String updateDeptId;
	
	/** 属性创建时间 */
	private Date createDate;

	/** 属性标志位 */
	private String flag;
	private String province;
	
	
	/** 正在运行的节点id  */
	private String runintTaskId ;
	
	/**  工作流id */
	private String workFlowId ;

	@Column(name = "RUNINGTISKID" )
	public String getRunintTaskId() {
		return runintTaskId;
	}
	public void setRunintTaskId(String runintTaskId) {
		this.runintTaskId = runintTaskId;
	}
	
	@Column(name = "WORKFLOWID")
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	

	/** 属性geActivitiesProducts */
	private List<GeActivitiesProduct> geActivitiesProducts = new ArrayList<GeActivitiesProduct>(
			0);

	/** 属性geActivitiesRules */
	private List<GeActivitiesRule> geActivitiesRules = new ArrayList<GeActivitiesRule>(
			0);
	
	/** 属性geActivitiesPictures */
	private List<GeActivitiesPicture> geActivitiesPictures = new ArrayList<GeActivitiesPicture>(
			0);
	
	/** 属性geAddServiceProcess 主要用于轨迹查询*/
	private List<GeAddServiceProcess> geAddServiceProcesses = new  ArrayList<GeAddServiceProcess>(
			0); 
	
	//业务上使用 start
	//增值服务上传图片start
	private List<File> uploadPicture;//上传的图片文件
	private List<String> uploadPictureFileName;//上传的图片的名字
	private List<String> uploadPictureContentType;//上传的图片类型
	private List<String> uploadSerialNoList;//上传文件对应的序列号
	private String uploadImagePath;//要上传的图片的物理位置
	private String uploadSavePath;//要保存的图片
	//增值服务上传图片end
	private String deptName;//地区名称
	private boolean sameProductFlag;//同一产品标志位
	private java.util.Map<String,String> pictureSerialMap;//图片要修改的序列号 及对应的文件名
	private String premiumType;//业务上使用的
	private String peremiumValue;//业务上使用的
	//业务上使用 end
	/**
	 * 类GeAddServiceActivity的默认构造方法
	 */
	public GeAddServiceActivity() {
	}
	
	/** 属性geWorkflowActivities */
	private List<GeWorkflowActivity> geWorkflowActivities = new ArrayList<GeWorkflowActivity>(
			0);

	/**
	 * 属性活动代码的getter方法
	 */
	@Id
	@Column(name = "ACTIVITYID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getActivityId() {
		return this.activityId;
	}

	/**
	 * 属性活动代码的setter方法
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * 属性活动名称的getter方法
	 */

	@Column(name = "ACTIVITYNAME")
	public String getActivityName() {
		return this.activityName;
	}

	/**
	 * 属性活动名称的setter方法
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * 属性活动内容介绍的getter方法
	 */

	@Column(name = "ACTIVITYCONTENT")
	public String getActivityContent() {
		return this.activityContent;
	}

	/**
	 * 属性活动内容介绍的setter方法
	 */
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	/**
	 * 属性地区的getter方法
	 */

	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}

	/**
	 * 属性地区的setter方法
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * 属性活动状态的getter方法
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * 属性活动状态的setter方法
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 属性活动起始时间的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "ACTIVITYSTARTDATE")
	public Date getActivityStartDate() {
		return this.activityStartDate;
	}

	/**
	 * 属性活动起始时间的setter方法
	 */
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	/**
	 * 属性活动结束时间的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "ACTIVITYENDDATE")
	public Date getActivityEndDate() {
		return this.activityEndDate;
	}

	/**
	 * 属性活动结束时间的setter方法
	 */
	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	@Column(name = "VALIDIND")
	public String getValidInd() {
		return validInd;
	}

	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}
	@Column(name = "CREATEDEPTID")
	public String getCreateDeptId() {
		return createDeptId;
	}

	public void setCreateDeptId(String createDeptId) {
		this.createDeptId = createDeptId;
	}
	
	@Column(name = "UPDATEDEPTID")
	public String getUpdateDeptId() {
		return updateDeptId;
	}

	public void setUpdateDeptId(String updateDeptId) {
		this.updateDeptId = updateDeptId;
	}
	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType. TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 属性标志位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标志位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性标志位的getter方法
	 */
	
	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}

	/**
	 * 属性标志位的setter方法
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 属性geActivitiesProducts的getter方法
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geAddServiceActivity")
	public List<GeActivitiesProduct> getGeActivitiesProducts() {
		return this.geActivitiesProducts;
	}

	/**
	 * 属性geActivitiesProducts的setter方法
	 */
	public void setGeActivitiesProducts(
			List<GeActivitiesProduct> geActivitiesProducts) {
		this.geActivitiesProducts = geActivitiesProducts;
	}

	/**
	 * 属性geActivitiesRules的getter方法
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geAddServiceActivity")
	public List<GeActivitiesRule> getGeActivitiesRules() {
		return this.geActivitiesRules;
	}

	/**
	 * 属性geActivitiesRules的setter方法
	 */
	public void setGeActivitiesRules(List<GeActivitiesRule> geActivitiesRules) {
		this.geActivitiesRules = geActivitiesRules;
	}
	
	/**
	 * 属性geActivitiesPictures的getter方法
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geAddServiceActivity")
	public List<GeActivitiesPicture> getGeActivitiesPictures() {
		return this.geActivitiesPictures;
	}

	/**
	 * 属性geActivitiesPictures的setter方法
	 */
	public void setGeActivitiesPictures(
			List<GeActivitiesPicture> geActivitiesPictures) {
		this.geActivitiesPictures = geActivitiesPictures;
	}
	
	/**
	 * 属性geActivitiesPictures的getter方法
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geAddServiceActivity")
	public List<GeAddServiceProcess> getGeAddServiceProcesses() {
		return geAddServiceProcesses;
	}

	public void setGeAddServiceProcesses(
			List<GeAddServiceProcess> geAddServiceProcesses) {
		this.geAddServiceProcesses = geAddServiceProcesses;
	}
	
	//业务上使用 start
	//增值服务上传图片start
	@Transient
	public List<File> getUploadPicture() {
		return uploadPicture;
	}
	public void setUploadPicture(List<File> uploadPicture) {
		this.uploadPicture = uploadPicture;
	}
	@Transient
	public List<String> getUploadPictureFileName() {
		return uploadPictureFileName;
	}
	public void setUploadPictureFileName(List<String> uploadPictureFileName) {
		this.uploadPictureFileName = uploadPictureFileName;
	}
	@Transient
	public List<String> getUploadPictureContentType() {
		return uploadPictureContentType;
	}
	public void setUploadPictureContentType(List<String> uploadPictureContentType) {
		this.uploadPictureContentType = uploadPictureContentType;
	}
	@Transient
	public String getUploadImagePath() {
		return uploadImagePath;
	}
	public void setUploadImagePath(String uploadImagePath) {
		this.uploadImagePath = uploadImagePath;
	}
	@Transient
	public String getUploadSavePath() {
		return uploadSavePath;
	}

	public void setUploadSavePath(String uploadSavePath) {
		this.uploadSavePath = uploadSavePath;
	}
	@Transient
	public java.util.Map<String, String> getPictureSerialMap() {
		return pictureSerialMap;
	}

	public void setPictureSerialMap(java.util.Map<String, String> pictureSerialMap) {
		this.pictureSerialMap = pictureSerialMap;
	}
	@Transient
	public List<String> getUploadSerialNoList() {
		return uploadSerialNoList;
	}

	public void setUploadSerialNoList(List<String> uploadSerialNoList) {
		this.uploadSerialNoList = uploadSerialNoList;
	}
	//增值服务上传图片end
	//地域名称
	@Transient
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Transient
	public boolean isSameProductFlag() {
		return sameProductFlag;
	}
	public void setSameProductFlag(boolean sameProductFlag) {
		this.sameProductFlag = sameProductFlag;
	}
	@Transient
	public String getPremiumType() {
		return premiumType;
	}

	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}
	@Transient
	public String getPeremiumValue() {
		return peremiumValue;
	}

	public void setPeremiumValue(String peremiumValue) {
		this.peremiumValue = peremiumValue;
	}
	
	/**
	 * 属性geWorkflowActivities的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geAddserviceActivity")
	public List<GeWorkflowActivity> getGeWorkflowActivities() {
		return this.geWorkflowActivities;
	}

	/**
	 * 属性geWorkflowActivities的setter方法
	 */
	public void setGeWorkflowActivities(
			List<GeWorkflowActivity> geWorkflowActivities) {
		this.geWorkflowActivities = geWorkflowActivities;
	}
	//业务上使用 end
}
