package cn.com.sinosoft.portalModule.transport.sinatay;

/**
 * ���֤У������DTO
 *
 */
public class IdCardCheckResult implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	/** ������ݺ��� */
	private String gmsfhm;
	
	/** ������ݺ���У���� */
	private String result_gmsfhm;
	
	/** ���� */
	private String xm;
	
	/** ����У���� */
	private String result_xm;
	
	/** ��Ƭ */
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
	 * ���� gmsfhm �� getter ����
	 * @return the gmsfhm
	 */
	public String getGmsfhm() {
		return gmsfhm;
	}

	/**
	 * ���� gmsfhm �� setter ����
	 * @param gmsfhm the gmsfhm to set
	 */
	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}

	/**
	 * ���� result_gmsfhm �� getter ����
	 * @return the result_gmsfhm
	 */
	public String getResult_gmsfhm() {
		return result_gmsfhm;
	}

	/**
	 * ���� result_gmsfhm �� setter ����
	 * @param result_gmsfhm the result_gmsfhm to set
	 */
	public void setResult_gmsfhm(String result_gmsfhm) {
		this.result_gmsfhm = result_gmsfhm;
	}

	/**
	 * ���� xm �� getter ����
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}

	/**
	 * ���� xm �� setter ����
	 * @param xm the xm to set
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}

	/**
	 * ���� result_xm �� getter ����
	 * @return the result_xm
	 */
	public String getResult_xm() {
		return result_xm;
	}

	/**
	 * ���� result_xm �� setter ����
	 * @param result_xm the result_xm to set
	 */
	public void setResult_xm(String result_xm) {
		this.result_xm = result_xm;
	}

	/**
	 * ���� xp �� getter ����
	 * @return the xp
	 */
	public String getXp() {
		return xp;
	}

	/**
	 * ���� xp �� setter ����
	 * @param xp the xp to set
	 */
	public void setXp(String xp) {
		this.xp = xp;
	}
	
}

