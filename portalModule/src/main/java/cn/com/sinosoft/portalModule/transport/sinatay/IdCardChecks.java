package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.ArrayList;
import java.util.List;

/**
 * 身份证校验DTO
 *
 */
public class IdCardChecks implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 查询信息 code表示位1: 号码姓名多项核查 2: 号码姓名核查比对 3: 号码姓名多项核查及同住址成员多项核查  */
	private String code;
	
	private String no;

	/** 身份证号码 */
	private String gmsfhm;
	
	/** 姓名 */
	private String xm;
	
	/** 业务发生地(6位的行政区划编码) */
	private String fsd;

	/** 业务类型 */
	private String ywlx;
	
	/** 输出节点 */
	private List<IdCardCheckResult> items = new ArrayList<IdCardCheckResult>(0);

	/**
	 * 属性 code 的 getter 方法
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 属性 code 的 setter 方法
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 属性 no 的 getter 方法
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * 属性 no 的 setter 方法
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * 属性 gmsfhm 的 getter 方法
	 * @return the gmsfhm
	 */
	public String getGmsfhm() {
		return gmsfhm;
	}

	/**
	 * 属性 gmsfhm 的 setter 方法
	 * @param gmsfhm the gmsfhm to set
	 */
	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}

	/**
	 * 属性 xm 的 getter 方法
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}

	/**
	 * 属性 xm 的 setter 方法
	 * @param xm the xm to set
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}

	/**
	 * 属性 fsd 的 getter 方法
	 * @return the fsd
	 */
	public String getFsd() {
		return fsd;
	}

	/**
	 * 属性 fsd 的 setter 方法
	 * @param fsd the fsd to set
	 */
	public void setFsd(String fsd) {
		this.fsd = fsd;
	}

	/**
	 * 属性 ywlx 的 getter 方法
	 * @return the ywlx
	 */
	public String getYwlx() {
		return ywlx;
	}

	/**
	 * 属性 ywlx 的 setter 方法
	 * @param ywlx the ywlx to set
	 */
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	/**
	 * 属性 items 的 getter 方法
	 * @return the items
	 */
	public List<IdCardCheckResult> getItems() {
		return items;
	}

	/**
	 * 属性 items 的 setter 方法
	 * @param items the items to set
	 */
	public void setItems(List<IdCardCheckResult> items) {
		this.items = items;
	}
	
}
