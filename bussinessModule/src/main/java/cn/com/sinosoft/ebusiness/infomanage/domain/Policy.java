package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Policy implements Serializable{
	/**
	 * @ProjectName: online
	 * @Package:     cn.com.sinosoft.ebusiness.policy.domain
	 * @ClassName:   Policy
	 * @Description: 保单详细页面保单信息
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-09-03
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	private static final long serialVersionUID = 2256926557023327060L;
	//属性保单号
	private String policyNo;
	//主险账户价值信息
	private RiskAccount riskAccount;
	//保单渠道来源（MALL-网上商城 为空非网上商城）
	private String source;
	//销售机构名称
	private String comName;
	//销售机构地址
	private String comAddress;
	//保单状态
	private String state;
	//保单生效日期
	private String valiDate;
	//是否回访
	private String isVisit;
	//险种信息
	private List<Risk> risks;
	//主险险种信息
	private Risk mRisk;
	//投保人信息
    private Appnt appnt = new Appnt();
	//被保人信息
	private Insured insured = new Insured();
    //属性受益人 
	private List<Beneficiary> bnfs = new ArrayList<Beneficiary>();
	//属性保全变更记录
	private List<Edor> edors = new ArrayList<Edor>();
		
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public RiskAccount getRiskAccount() {
		return riskAccount;
	}
	public void setRiskAccount(RiskAccount riskAccount) {
		this.riskAccount = riskAccount;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsVisit() {
		return isVisit;
	}
	public void setIsVisit(String isVisit) {
		this.isVisit = isVisit;
	}
	public List<Risk> getRisks() {
		return risks;
	}
	public void setRisks(List<Risk> risks) {
		this.risks = risks;
	}
	
	public String getValiDate() {
		return valiDate;
	}
	public void setValiDate(String valiDate) {
		this.valiDate = valiDate;
	}
	public Appnt getAppnt() {
		return appnt;
	}
	public void setAppnt(Appnt appnt) {
		this.appnt = appnt;
	}
	public Insured getInsured() {
		return insured;
	}
	public void setInsured(Insured insured) {
		this.insured = insured;
	}
	public List<Beneficiary> getBnfs() {
		return bnfs;
	}
	public void setBnfs(List<Beneficiary> bnfs) {
		this.bnfs = bnfs;
	}
	public List<Edor> getEdors() {
		return edors;
	}
	public void setEdors(List<Edor> edors) {
		this.edors = edors;
	}
	public Risk getmRisk() {
		return mRisk;
	}
	public void setmRisk(Risk mRisk) {
		this.mRisk = mRisk;
	}
	
}
