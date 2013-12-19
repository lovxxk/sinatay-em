package cn.com.sinosoft.ebusiness.marketing.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeAddServiceActivity
 */
@Entity
@Table(name = "GE_ADDSERVICE_ACTIVITY")
public class GeAddServiceActivity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ի���� */
	private String activityId;

	/** ���Ի���� */
	private String activityName;
	
	/** ���Ի���ݽ��� */
	private String activityContent;

	/** ���Ե��� */
	private String deptID;

	/** ���Ի״̬ */
	private String status;

	/** ���Ի��ʼʱ�� */
	private Date activityStartDate;

	/** ���Ի����ʱ�� */
	private Date activityEndDate;
	
	/** �����Ƿ���Ч */
	private String validInd;
	
	/** ���Դ����߻������� */
	private String createDeptId;
	
	/** ���Ը����߻������� */
	private String updateDeptId;
	
	/** ���Դ���ʱ�� */
	private Date createDate;

	/** ���Ա�־λ */
	private String flag;
	private String province;
	
	
	/** �������еĽڵ�id  */
	private String runintTaskId ;
	
	/**  ������id */
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
	

	/** ����geActivitiesProducts */
	private List<GeActivitiesProduct> geActivitiesProducts = new ArrayList<GeActivitiesProduct>(
			0);

	/** ����geActivitiesRules */
	private List<GeActivitiesRule> geActivitiesRules = new ArrayList<GeActivitiesRule>(
			0);
	
	/** ����geActivitiesPictures */
	private List<GeActivitiesPicture> geActivitiesPictures = new ArrayList<GeActivitiesPicture>(
			0);
	
	/** ����geAddServiceProcess ��Ҫ���ڹ켣��ѯ*/
	private List<GeAddServiceProcess> geAddServiceProcesses = new  ArrayList<GeAddServiceProcess>(
			0); 
	
	//ҵ����ʹ�� start
	//��ֵ�����ϴ�ͼƬstart
	private List<File> uploadPicture;//�ϴ���ͼƬ�ļ�
	private List<String> uploadPictureFileName;//�ϴ���ͼƬ������
	private List<String> uploadPictureContentType;//�ϴ���ͼƬ����
	private List<String> uploadSerialNoList;//�ϴ��ļ���Ӧ�����к�
	private String uploadImagePath;//Ҫ�ϴ���ͼƬ������λ��
	private String uploadSavePath;//Ҫ�����ͼƬ
	//��ֵ�����ϴ�ͼƬend
	private String deptName;//��������
	private boolean sameProductFlag;//ͬһ��Ʒ��־λ
	private java.util.Map<String,String> pictureSerialMap;//ͼƬҪ�޸ĵ����к� ����Ӧ���ļ���
	private String premiumType;//ҵ����ʹ�õ�
	private String peremiumValue;//ҵ����ʹ�õ�
	//ҵ����ʹ�� end
	/**
	 * ��GeAddServiceActivity��Ĭ�Ϲ��췽��
	 */
	public GeAddServiceActivity() {
	}
	
	/** ����geWorkflowActivities */
	private List<GeWorkflowActivity> geWorkflowActivities = new ArrayList<GeWorkflowActivity>(
			0);

	/**
	 * ���Ի�����getter����
	 */
	@Id
	@Column(name = "ACTIVITYID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getActivityId() {
		return this.activityId;
	}

	/**
	 * ���Ի�����setter����
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * ���Ի���Ƶ�getter����
	 */

	@Column(name = "ACTIVITYNAME")
	public String getActivityName() {
		return this.activityName;
	}

	/**
	 * ���Ի���Ƶ�setter����
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * ���Ի���ݽ��ܵ�getter����
	 */

	@Column(name = "ACTIVITYCONTENT")
	public String getActivityContent() {
		return this.activityContent;
	}

	/**
	 * ���Ի���ݽ��ܵ�setter����
	 */
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	/**
	 * ���Ե�����getter����
	 */

	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}

	/**
	 * ���Ե�����setter����
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * ���Ի״̬��getter����
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * ���Ի״̬��setter����
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * ���Ի��ʼʱ���getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "ACTIVITYSTARTDATE")
	public Date getActivityStartDate() {
		return this.activityStartDate;
	}

	/**
	 * ���Ի��ʼʱ���setter����
	 */
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	/**
	 * ���Ի����ʱ���getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "ACTIVITYENDDATE")
	public Date getActivityEndDate() {
		return this.activityEndDate;
	}

	/**
	 * ���Ի����ʱ���setter����
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
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType. TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * ���Ա�־λ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�־λ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * ���Ա�־λ��getter����
	 */
	
	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}

	/**
	 * ���Ա�־λ��setter����
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * ����geActivitiesProducts��getter����
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geAddServiceActivity")
	public List<GeActivitiesProduct> getGeActivitiesProducts() {
		return this.geActivitiesProducts;
	}

	/**
	 * ����geActivitiesProducts��setter����
	 */
	public void setGeActivitiesProducts(
			List<GeActivitiesProduct> geActivitiesProducts) {
		this.geActivitiesProducts = geActivitiesProducts;
	}

	/**
	 * ����geActivitiesRules��getter����
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geAddServiceActivity")
	public List<GeActivitiesRule> getGeActivitiesRules() {
		return this.geActivitiesRules;
	}

	/**
	 * ����geActivitiesRules��setter����
	 */
	public void setGeActivitiesRules(List<GeActivitiesRule> geActivitiesRules) {
		this.geActivitiesRules = geActivitiesRules;
	}
	
	/**
	 * ����geActivitiesPictures��getter����
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geAddServiceActivity")
	public List<GeActivitiesPicture> getGeActivitiesPictures() {
		return this.geActivitiesPictures;
	}

	/**
	 * ����geActivitiesPictures��setter����
	 */
	public void setGeActivitiesPictures(
			List<GeActivitiesPicture> geActivitiesPictures) {
		this.geActivitiesPictures = geActivitiesPictures;
	}
	
	/**
	 * ����geActivitiesPictures��getter����
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
	
	//ҵ����ʹ�� start
	//��ֵ�����ϴ�ͼƬstart
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
	//��ֵ�����ϴ�ͼƬend
	//��������
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
	 * ����geWorkflowActivities��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geAddserviceActivity")
	public List<GeWorkflowActivity> getGeWorkflowActivities() {
		return this.geWorkflowActivities;
	}

	/**
	 * ����geWorkflowActivities��setter����
	 */
	public void setGeWorkflowActivities(
			List<GeWorkflowActivity> geWorkflowActivities) {
		this.geWorkflowActivities = geWorkflowActivities;
	}
	//ҵ����ʹ�� end
}
