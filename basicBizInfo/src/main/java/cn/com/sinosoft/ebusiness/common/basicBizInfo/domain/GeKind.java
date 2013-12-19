package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * POJO��GeKind
 */
@Entity
@Table(name = "GE_KIND")
public class GeKind implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����ID */
	private GeKindId id;

	/** ����combinekindcode */
	private String combinekindcode;

	/** �����ձ��������� */
	private String kindCName;

	/** �����ձ�Ӣ������ */
	private String kindEName;

	/** ����kindtname */
	private String kindtname;

	/** ����kindflag */
	private String kindflag;

	/** ���Դ������� */
	private String codeType;

	/** ����valuerange */
	private String valuerange;

	/** ������ʾ˳����� */
	private Integer orderNo;

	/** ����nodeductflag */
	private String nodeductflag;
	
	/** ������Ч��־ */
	private String validInd;
	
	/** �ײ�������ʾ����*/
	private String isComboFlag;

	/** ���Ա�־ */
	private String flag;
	
	/** ��д */
	private String abbreviation;

	/** �ձ����� */
	private String kindDescription;
	
	public String getKindDescription() {
		return kindDescription;
	}
	public void setKindDescription(String kindDescription) {
		this.kindDescription = kindDescription;
	}
	/**
	 * ��GeKind��Ĭ�Ϲ��췽��
	 */
	public GeKind() {
	}

	private List<GeKindRelate> geKindRelateList = new ArrayList<GeKindRelate>(0);
	
	/**
	 * ����ID��getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "riskCode", column = @Column(name = "RISKCODE")),
			@AttributeOverride(name = "kindCode", column = @Column(name = "KINDCODE")) })
	public GeKindId getId() {
		return this.id;
	}

	/**
	 * ����ID��setter����
	 */
	public void setId(GeKindId id) {
		this.id = id;
	}

	/**
	 * ����combinekindcode��getter����
	 */

	@Column(name = "COMBINEKINDCODE")
	public String getCombinekindcode() {
		return this.combinekindcode;
	}

	/**
	 * ����combinekindcode��setter����
	 */
	public void setCombinekindcode(String combinekindcode) {
		this.combinekindcode = combinekindcode;
	}

	/**
	 * �����ձ��������Ƶ�getter����
	 */

	@Column(name = "KINDCNAME")
	public String getKindCName() {
		return this.kindCName;
	}

	/**
	 * �����ձ��������Ƶ�setter����
	 */
	public void setKindCName(String kindCName) {
		this.kindCName = kindCName;
	}

	/**
	 * �����ձ�Ӣ�����Ƶ�getter����
	 */

	@Column(name = "KINDENAME")
	public String getKindEName() {
		return this.kindEName;
	}

	/**
	 * �����ձ�Ӣ�����Ƶ�setter����
	 */
	public void setKindEName(String kindEName) {
		this.kindEName = kindEName;
	}

	/**
	 * ����kindtname��getter����
	 */

	@Column(name = "KINDTNAME")
	public String getKindtname() {
		return this.kindtname;
	}

	/**
	 * ����kindtname��setter����
	 */
	public void setKindtname(String kindtname) {
		this.kindtname = kindtname;
	}

	/**
	 * ����kindflag��getter����
	 */

	@Column(name = "KINDFLAG")
	public String getKindflag() {
		return this.kindflag;
	}

	/**
	 * ����kindflag��setter����
	 */
	public void setKindflag(String kindflag) {
		this.kindflag = kindflag;
	}

	/**
	 * ���Դ������͵�getter����
	 */

	@Column(name = "CODETYPE")
	public String getCodeType() {
		return this.codeType;
	}

	/**
	 * ���Դ������͵�setter����
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	/**
	 * ����valuerange��getter����
	 */

	@Column(name = "VALUERANGE")
	public String getValuerange() {
		return this.valuerange;
	}

	/**
	 * ����valuerange��setter����
	 */
	public void setValuerange(String valuerange) {
		this.valuerange = valuerange;
	}

	/**
	 * ������ʾ˳����ŵ�getter����
	 */

	@Column(name = "ORDERNO")
	public Integer getOrderNo() {
		return this.orderNo;
	}

	/**
	 * ������ʾ˳����ŵ�setter����
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * ����nodeductflag��getter����
	 */

	@Column(name = "NODEDUCTFLAG")
	public String getNodeductflag() {
		return this.nodeductflag;
	}

	/**
	 * ����nodeductflag��setter����
	 */
	public void setNodeductflag(String nodeductflag) {
		this.nodeductflag = nodeductflag;
	}

	
	/**
	 * ������Ч��־��getter����
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * ������Ч��־��setter����
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}
	
	/**
	 * �����ײ�������ʾ���õ�getter����
	 */
	@Column(name = "ISCOMBOFLAG")
	public String getIsComboFlag() {
		return isComboFlag;
	}
	/**
	 * �����ײ�������ʾ���õ�setter����
	 */
	public void setIsComboFlag(String isComboFlag) {
		this.isComboFlag = isComboFlag;
	}
	
	/**
	 * ���Ա�־��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�־��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "ABBREVIATION")
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geKind")
	public List<GeKindRelate> getGeKindRelateList() {
		return geKindRelateList;
	}
	
	public void setGeKindRelateList(List<GeKindRelate> geKindRelateList) {
		this.geKindRelateList = geKindRelateList;
	}
}
