package cn.com.sinosoft.ebusiness.marketing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeActivitiesPicture
 */
@Entity
@Table(name = "GE_ACTIVITIES_PICTURE")
public class GeActivitiesPicture implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����ͼƬ�Զ����ɵ�id */
	private String autoPictureId;

	/** ������ֵ����-GE_AddService_Activity */
	private GeAddServiceActivity geAddServiceActivity;

	/** �������к� */
	private String serialNo;

	/** ����ͼƬURL */
	private String pictureUrl;
	/**����ͼƬ����URL*/
	private String jumpUrl;
	//����ҵ����ʹ�õ� start
	private String nooryes;//no ��û��ͼƬ yes����ͼƬ
	//����ҵ����ʹ�õ� end
	/**
	 * ��GeActivitiesPicture��Ĭ�Ϲ��췽��
	 */
	public GeActivitiesPicture() {
	}

	/**
	 * ����ͼƬ�Զ����ɵ�id��getter����
	 */
	@Id
	@Column(name = "AUTOPICTUREID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getAutoPictureId() {
		return this.autoPictureId;
	}

	/**
	 * ����ͼƬ�Զ����ɵ�id��setter����
	 */
	public void setAutoPictureId(String autoPictureId) {
		this.autoPictureId = autoPictureId;
	}

	/**
	 * ������ֵ����-GE_AddService_Activity��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	@JsonIgnore
	public GeAddServiceActivity getGeAddServiceActivity() {
		return this.geAddServiceActivity;
	}

	/**
	 * ������ֵ����-GE_AddService_Activity��setter����
	 */
	public void setGeAddServiceActivity(
			GeAddServiceActivity geAddServiceActivity) {
		this.geAddServiceActivity = geAddServiceActivity;
	}

	/**
	 * �������кŵ�getter����
	 */

	@Column(name = "SERIALNO")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * �������кŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * ����ͼƬURL��getter����
	 */

	@Column(name = "PICTUREURL")
	public String getPictureUrl() {
		return this.pictureUrl;
	}

	/**
	 * ����ͼƬURL��setter����
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	//ҵ����ʹ�õ�start
	@Transient
	public String getNooryes() {
		return nooryes;
	}

	public void setNooryes(String nooryes) {
		this.nooryes = nooryes;
	}
	//ҵ����ʹ�õ�end
	/**
	 * ����ͼƬ����URL
	 */
	@Column(name = "JUMPURL")
	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	
}
