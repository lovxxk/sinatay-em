package cn.com.sinosoft.portalModule.transport.sinatay;

/**
 * 身份证校验结果集DTO
 *
 */
public class IdCardCheckResult implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 公民身份号码 */
	private String gmsfhm;
	
	/** 公民身份号码校验结果 */
	private String result_gmsfhm;
	
	/** 姓名 */
	private String xm;
	
	/** 姓名校验结果 */
	private String result_xm;
	
	/** 相片 */
	private String xp;
	
	private String errormesage;
	
	private String errormesagecol;
	
	public String getErrormesage() {
		return errormesage;
	}

	public void setErrormesage(String errormesage) {
		this.errormesage = errormesage;
	}

	public String getErrormesagecol() {
		return errormesagecol;
	}

	public void setErrormesagecol(String errormesagecol) {
		this.errormesagecol = errormesagecol;
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
	 * 属性 result_gmsfhm 的 getter 方法
	 * @return the result_gmsfhm
	 */
	public String getResult_gmsfhm() {
		return result_gmsfhm;
	}

	/**
	 * 属性 result_gmsfhm 的 setter 方法
	 * @param result_gmsfhm the result_gmsfhm to set
	 */
	public void setResult_gmsfhm(String result_gmsfhm) {
		this.result_gmsfhm = result_gmsfhm;
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
	 * 属性 result_xm 的 getter 方法
	 * @return the result_xm
	 */
	public String getResult_xm() {
		return result_xm;
	}

	/**
	 * 属性 result_xm 的 setter 方法
	 * @param result_xm the result_xm to set
	 */
	public void setResult_xm(String result_xm) {
		this.result_xm = result_xm;
	}

	/**
	 * 属性 xp 的 getter 方法
	 * @return the xp
	 */
	public String getXp() {
		return xp;
	}

	/**
	 * 属性 xp 的 setter 方法
	 * @param xp the xp to set
	 */
	public void setXp(String xp) {
		this.xp = xp;
	}
	
}

