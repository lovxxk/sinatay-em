package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.ArrayList;
import java.util.List;

/**
 * ���֤У��DTO
 *
 */
public class IdCardChecks implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** ��ѯ��Ϣ code��ʾλ1: ������������˲� 2: ���������˲�ȶ� 3: ������������˲鼰ͬסַ��Ա����˲�  */
	private String code;
	
	private String no;

	/** ���֤���� */
	private String gmsfhm;
	
	/** ���� */
	private String xm;
	
	/** ҵ������(6λ��������������) */
	private String fsd;

	/** ҵ������ */
	private String ywlx;
	
	/** ����ڵ� */
	private List<IdCardCheckResult> items = new ArrayList<IdCardCheckResult>(0);

	/**
	 * ���� code �� getter ����
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * ���� code �� setter ����
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * ���� no �� getter ����
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * ���� no �� setter ����
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
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
	 * ���� fsd �� getter ����
	 * @return the fsd
	 */
	public String getFsd() {
		return fsd;
	}

	/**
	 * ���� fsd �� setter ����
	 * @param fsd the fsd to set
	 */
	public void setFsd(String fsd) {
		this.fsd = fsd;
	}

	/**
	 * ���� ywlx �� getter ����
	 * @return the ywlx
	 */
	public String getYwlx() {
		return ywlx;
	}

	/**
	 * ���� ywlx �� setter ����
	 * @param ywlx the ywlx to set
	 */
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	/**
	 * ���� items �� getter ����
	 * @return the items
	 */
	public List<IdCardCheckResult> getItems() {
		return items;
	}

	/**
	 * ���� items �� setter ����
	 * @param items the items to set
	 */
	public void setItems(List<IdCardCheckResult> items) {
		this.items = items;
	}
	
}
