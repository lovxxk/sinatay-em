package cn.com.sinosoft.portalModule.transport.sinatay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * LCCont�ڵ�DTO
 * 
 */
public class LCCont implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Ͷ�����
	 */
	private String proposalContNo;

	/**
	 * Ͷ������
	 */
	private Date polApplyDate;

	/**
	 * �˻�����
	 */
	private String accName;

	/**
	 * ���б���
	 */
	private String accBankCode;

	/**
	 * ����ʡ����
	 */
	private String accProvince;

	/**
	 * �����б���
	 */
	private String accCity;

	/**
	 * ���п�������
	 */
	private String accType;

	/**
	 * �����˻�
	 */
	private String bankAccNo;

	/**
	 * ���������˻�
	 */
	private String secBankCode;

	/**
	 * �������б���
	 */
	private String secBankAccNo;

	/**
	 * �����˻�����
	 */
	private String secAccName;

	/**
	 * ��������ʡ����
	 */
	private String secAccProvince;

	/**
	 * ���������б���
	 */
	private String secAccCity;

	/**
	 * �������п�������
	 */
	private String secAccType;

	/**
	 * �ɷ���ʽ
	 */
	private String payMode;

	/**
	 * ���ڽɷ���ʽ
	 */
	private String exPayMode;

	/**
	 * �������ͷ�ʽ
	 */
	private String getPolMode;

	/**
	 * �������
	 */
	private String manageCom;

	/**
	 * ��������
	 */
	private String password;

	/**
	 * �ر�Լ��
	 */
	private String specContent;

	/**
	 * ����ӡˢ��
	 */
	private String prtNo;

	/**
	 * ��Ʊӡˢ����
	 */
	private String tempFeeNo;

	/**
	 * �����˱���
	 */
	private String agentCode;

	/**
	 * ���������
	 */
	private String agentGroup;

	/**
	 * ����������
	 */
	private String agentName;

	/**
	 * �����������
	 */
	private String agentCom;

	/**
	 * �����������
	 */
	private String agentComName;

	/**
	 * ��ͬ���鴦��ʽ
	 */
	private String disputedFlag;

	/**
	 * �ٲ�ίԱ������
	 */
	private String acName;

	/**
	 * �ܱ���
	 */
	private BigDecimal prem;

	/**
	 * ԭ������ˮ��
	 */
	private String oldTransrNo;

	/**
	 * �Ƿ�ɷѳɹ�
	 */
	private String isPaySucc;

	/**
	 * �ɷѽ����Ϣ
	 */
	private String payMessage;

	/** �������� */
	private String smsMessage;

	/**
	 * Ͷ���˽ڵ�
	 */
	private LCAppnt lcAppnt;

	/**
	 * ��������
	 */
	private Integer lcInsuredCount;

	/**
	 * ������
	 */
	private List<LCInsured> lcInsureds = new ArrayList<LCInsured>(0);

	/**
	 * Flag
	 */
	private String flag;

	/**
	 * ������
	 */
	private String contNo;

	/**
	 * ����״̬
	 */
	private String contState;

	/**
	 * ��Ч����
	 */
	private Date cvaliDate;

	/**
	 * �������
	 */
	private BigDecimal payRemain;

	/**
	 * ����ʵ��Ӧ������
	 */
	private BigDecimal payPrem;

	/**
	 * ת��״̬
	 */
	private String transState;

	/**
	 * ת��״̬������
	 */
	private Date transDate;

	/**
	 * �ɴ�
	 */
	private Integer payCount;

	/**
	 * ҵ��Ա�绰
	 */
	private String agentMobile;

	/**
	 * ��ְ����
	 */
	private String agentOWDate;

	/**
	 * ������Ա����
	 */
	private String newAgentName;

	/**
	 * ������Ա����
	 */
	private String supervisorName;

	/**
	 * ������Ա����
	 */
	private String supervisorCode;

	private String paySerialNo;

	private String orderId;

	private String carNo;

	private String startDate;

	private String startTime;

	private String endDate;

	private String endTime;

	private String startPlace;

	private String acrossFlag;

	private String comAddress;

	private String isVisit;

	private String riskName;

	private String sourceFlag;

	private String wnFlag;

	private String countBala;

	// ������ȡ���
	private String applyMoney;
	
	//ʵ�ʵ��˽��
	private String getMoney;
	
	//��ȡ������
	private String pdSxFee;
	
	//����������
	private String loanBJ;
	
	//������Ϣ
	private String loanLX;
	
	//��ȡ��ʣ���˻���ֵ
	private String leavaAccBala;
	
	// �˱�ԭ�� 
	private String reason;
	
	//��ҵ��Ա��ϵ
	private String relaToAgent;

	public String getProposalContNo() {
		return proposalContNo;
	}

	public void setProposalContNo(String proposalContNo) {
		this.proposalContNo = proposalContNo;
	}

	public Date getPolApplyDate() {
		return polApplyDate;
	}

	public void setPolApplyDate(Date polApplyDate) {
		this.polApplyDate = polApplyDate;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccBankCode() {
		return accBankCode;
	}

	public void setAccBankCode(String accBankCode) {
		this.accBankCode = accBankCode;
	}

	public String getAccProvince() {
		return accProvince;
	}

	public void setAccProvince(String accProvince) {
		this.accProvince = accProvince;
	}

	public String getAccCity() {
		return accCity;
	}

	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getSecBankCode() {
		return secBankCode;
	}

	public void setSecBankCode(String secBankCode) {
		this.secBankCode = secBankCode;
	}

	public String getSecBankAccNo() {
		return secBankAccNo;
	}

	public void setSecBankAccNo(String secBankAccNo) {
		this.secBankAccNo = secBankAccNo;
	}

	public String getSecAccName() {
		return secAccName;
	}

	public void setSecAccName(String secAccName) {
		this.secAccName = secAccName;
	}

	public String getSecAccProvince() {
		return secAccProvince;
	}

	public void setSecAccProvince(String secAccProvince) {
		this.secAccProvince = secAccProvince;
	}

	public String getSecAccCity() {
		return secAccCity;
	}

	public void setSecAccCity(String secAccCity) {
		this.secAccCity = secAccCity;
	}

	public String getSecAccType() {
		return secAccType;
	}

	public void setSecAccType(String secAccType) {
		this.secAccType = secAccType;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getExPayMode() {
		return exPayMode;
	}

	public void setExPayMode(String exPayMode) {
		this.exPayMode = exPayMode;
	}

	public String getGetPolMode() {
		return getPolMode;
	}

	public void setGetPolMode(String getPolMode) {
		this.getPolMode = getPolMode;
	}

	public String getManageCom() {
		return manageCom;
	}

	public void setManageCom(String manageCom) {
		this.manageCom = manageCom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpecContent() {
		return specContent;
	}

	public void setSpecContent(String specContent) {
		this.specContent = specContent;
	}

	public String getPrtNo() {
		return prtNo;
	}

	public void setPrtNo(String prtNo) {
		this.prtNo = prtNo;
	}

	public String getTempFeeNo() {
		return tempFeeNo;
	}

	public void setTempFeeNo(String tempFeeNo) {
		this.tempFeeNo = tempFeeNo;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentGroup() {
		return agentGroup;
	}

	public void setAgentGroup(String agentGroup) {
		this.agentGroup = agentGroup;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentCom() {
		return agentCom;
	}

	public void setAgentCom(String agentCom) {
		this.agentCom = agentCom;
	}

	public String getAgentComName() {
		return agentComName;
	}

	public void setAgentComName(String agentComName) {
		this.agentComName = agentComName;
	}

	public String getDisputedFlag() {
		return disputedFlag;
	}

	public void setDisputedFlag(String disputedFlag) {
		this.disputedFlag = disputedFlag;
	}

	public String getAcName() {
		return acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

	public BigDecimal getPrem() {
		return prem;
	}

	public void setPrem(BigDecimal prem) {
		this.prem = prem;
	}

	public String getOldTransrNo() {
		return oldTransrNo;
	}

	public void setOldTransrNo(String oldTransrNo) {
		this.oldTransrNo = oldTransrNo;
	}

	public String getIsPaySucc() {
		return isPaySucc;
	}

	public void setIsPaySucc(String isPaySucc) {
		this.isPaySucc = isPaySucc;
	}

	public String getPayMessage() {
		return payMessage;
	}

	public void setPayMessage(String payMessage) {
		this.payMessage = payMessage;
	}

	public String getSmsMessage() {
		return smsMessage;
	}

	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}

	public LCAppnt getLcAppnt() {
		return lcAppnt;
	}

	public void setLcAppnt(LCAppnt lcAppnt) {
		this.lcAppnt = lcAppnt;
	}

	public Integer getLcInsuredCount() {
		return lcInsuredCount;
	}

	public void setLcInsuredCount(Integer lcInsuredCount) {
		this.lcInsuredCount = lcInsuredCount;
	}

	public List<LCInsured> getLcInsureds() {
		return lcInsureds;
	}

	public void setLcInsureds(List<LCInsured> lcInsureds) {
		this.lcInsureds = lcInsureds;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getContState() {
		return contState;
	}

	public void setContState(String contState) {
		this.contState = contState;
	}

	public Date getCvaliDate() {
		return cvaliDate;
	}

	public void setCvaliDate(Date cvaliDate) {
		this.cvaliDate = cvaliDate;
	}

	public BigDecimal getPayRemain() {
		return payRemain;
	}

	public void setPayRemain(BigDecimal payRemain) {
		this.payRemain = payRemain;
	}

	public BigDecimal getPayPrem() {
		return payPrem;
	}

	public void setPayPrem(BigDecimal payPrem) {
		this.payPrem = payPrem;
	}

	public String getTransState() {
		return transState;
	}

	public void setTransState(String transState) {
		this.transState = transState;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public Integer getPayCount() {
		return payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}

	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	public String getAgentOWDate() {
		return agentOWDate;
	}

	public void setAgentOWDate(String agentOWDate) {
		this.agentOWDate = agentOWDate;
	}

	public String getNewAgentName() {
		return newAgentName;
	}

	public void setNewAgentName(String newAgentName) {
		this.newAgentName = newAgentName;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getSupervisorCode() {
		return supervisorCode;
	}

	public void setSupervisorCode(String supervisorCode) {
		this.supervisorCode = supervisorCode;
	}

	public String getPaySerialNo() {
		return paySerialNo;
	}

	public void setPaySerialNo(String paySerialNo) {
		this.paySerialNo = paySerialNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getAcrossFlag() {
		return acrossFlag;
	}

	public void setAcrossFlag(String acrossFlag) {
		this.acrossFlag = acrossFlag;
	}

	public String getComAddress() {
		return comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}

	public String getIsVisit() {
		return isVisit;
	}

	public void setIsVisit(String isVisit) {
		this.isVisit = isVisit;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getSourceFlag() {
		return sourceFlag;
	}

	public void setSourceFlag(String sourceFlag) {
		this.sourceFlag = sourceFlag;
	}

	public String getWnFlag() {
		return wnFlag;
	}

	public void setWnFlag(String wnFlag) {
		this.wnFlag = wnFlag;
	}

	public String getCountBala() {
		return countBala;
	}

	public void setCountBala(String countBala) {
		this.countBala = countBala;
	}

	public String getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(String applyMoney) {
		this.applyMoney = applyMoney;
	}

	public String getGetMoney() {
		return getMoney;
	}

	public void setGetMoney(String getMoney) {
		this.getMoney = getMoney;
	}

	public String getPdSxFee() {
		return pdSxFee;
	}

	public void setPdSxFee(String pdSxFee) {
		this.pdSxFee = pdSxFee;
	}

	public String getLoanBJ() {
		return loanBJ;
	}

	public void setLoanBJ(String loanBJ) {
		this.loanBJ = loanBJ;
	}

	public String getLoanLX() {
		return loanLX;
	}

	public void setLoanLX(String loanLX) {
		this.loanLX = loanLX;
	}

	public String getLeavaAccBala() {
		return leavaAccBala;
	}

	public void setLeavaAccBala(String leavaAccBala) {
		this.leavaAccBala = leavaAccBala;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRelaToAgent() {
		return relaToAgent;
	}

	public void setRelaToAgent(String relaToAgent) {
		this.relaToAgent = relaToAgent;
	}
}
