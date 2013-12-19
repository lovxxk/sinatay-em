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
 * POJO类GeActivitiesPicture
 */
@Entity
@Table(name = "GE_ACTIVITIES_PICTURE")
public class GeActivitiesPicture implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性图片自动生成的id */
	private String autoPictureId;

	/** 属性增值服务活动-GE_AddService_Activity */
	private GeAddServiceActivity geAddServiceActivity;

	/** 属性序列号 */
	private String serialNo;

	/** 属性图片URL */
	private String pictureUrl;
	/**属性图片调整URL*/
	private String jumpUrl;
	//用于业务上使用的 start
	private String nooryes;//no 是没有图片 yes是有图片
	//用于业务上使用的 end
	/**
	 * 类GeActivitiesPicture的默认构造方法
	 */
	public GeActivitiesPicture() {
	}

	/**
	 * 属性图片自动生成的id的getter方法
	 */
	@Id
	@Column(name = "AUTOPICTUREID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getAutoPictureId() {
		return this.autoPictureId;
	}

	/**
	 * 属性图片自动生成的id的setter方法
	 */
	public void setAutoPictureId(String autoPictureId) {
		this.autoPictureId = autoPictureId;
	}

	/**
	 * 属性增值服务活动-GE_AddService_Activity的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	@JsonIgnore
	public GeAddServiceActivity getGeAddServiceActivity() {
		return this.geAddServiceActivity;
	}

	/**
	 * 属性增值服务活动-GE_AddService_Activity的setter方法
	 */
	public void setGeAddServiceActivity(
			GeAddServiceActivity geAddServiceActivity) {
		this.geAddServiceActivity = geAddServiceActivity;
	}

	/**
	 * 属性序列号的getter方法
	 */

	@Column(name = "SERIALNO")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * 属性序列号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * 属性图片URL的getter方法
	 */

	@Column(name = "PICTUREURL")
	public String getPictureUrl() {
		return this.pictureUrl;
	}

	/**
	 * 属性图片URL的setter方法
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	//业务上使用的start
	@Transient
	public String getNooryes() {
		return nooryes;
	}

	public void setNooryes(String nooryes) {
		this.nooryes = nooryes;
	}
	//业务上使用的end
	/**
	 * 属性图片调整URL
	 */
	@Column(name = "JUMPURL")
	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	
}
