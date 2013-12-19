package cn.com.sinosoft.ebusiness.online.biz.bq.web;

import ins.framework.web.Struts2Action;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.member.policyDetail.web.PolicyDetailAction;
import cn.com.sinosoft.ebusiness.service.policyService.domain.PayAccInfo;
import cn.com.sinosoft.ebusiness.service.policyService.service.facade.PartReceiveService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;



/**
 * ��̨�����������
 * 
 *  
 * 
 */
public class PartReceiveAction extends Struts2Action {
	
	
	private static Logger log = LoggerFactory.getLogger(PartReceiveAction.class);
	
	//���ر�־
	private String flag;
	//������Ϣ
	private String desc;

	//��������
	private String contNo;
	
	//���ֱ���
	private String riskCode;
	//��������
	private String riskName;
	//�˻���ֵ
	private BigDecimal acountValue;
	//�������
	private int curYear;
	//����ȡ����
	private int getCount;
	//������ȡ���
	private BigDecimal applyMoney;
	
	//ʵ�ʵ��˽��
	private BigDecimal getMoney;
	//��ȡ������
	private BigDecimal pdSxFee;
	//������
	private BigDecimal loanBJ;
	//������Ϣ
	private BigDecimal loanLX;
	//��ȡ��ʣ���˻���ֵ
	private BigDecimal leavaAccBala;	
	
	//�����˻�����
	private String acountType;
	//�����˻���Ϣ�б�
	private String[][] acountDetail;	
	//�����˻�����-�˺���
	private String acountName;
	//�����˻�����-��������
	private String bankName;
	//�����˻�����-�˺�
	private String acountNo;	
	//�Ƿ�ȫ���˱�
	private String tbFlag;	
	//�ֻ�
	private String phone;
	//��֤��
	private String checkNo;
	
	
	@Autowired
	PartReceiveService partReceiveService;
	@Autowired
	public GeUserPersonalService geUserPersonalService;
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getTbFlag() {
		return tbFlag;
	}

	public void setTbFlag(String tbFlag) {
		this.tbFlag = tbFlag;
	}

	public String getAcountName() {
		return acountName;
	}

	public void setAcountName(String acountName) {
		this.acountName = acountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAcountNo() {
		return acountNo;
	}

	public void setAcountNo(String acountNo) {
		this.acountNo = acountNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public BigDecimal getAcountValue() {
		return acountValue;
	}

	public void setAcountValue(BigDecimal acountValue) {
		this.acountValue = acountValue;
	}

	public int getCurYear() {
		return curYear;
	}

	public void setCurYear(int curYear) {
		this.curYear = curYear;
	}

	public int getGetCount() {
		return getCount;
	}

	public void setGetCount(int getCount) {
		this.getCount = getCount;
	}

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}

	public BigDecimal getGetMoney() {
		return getMoney;
	}

	public void setGetMoney(BigDecimal getMoney) {
		this.getMoney = getMoney;
	}

	public BigDecimal getPdSxFee() {
		return pdSxFee;
	}

	public void setPdSxFee(BigDecimal pdSxFee) {
		this.pdSxFee = pdSxFee;
	}

	public BigDecimal getLoanBJ() {
		return loanBJ;
	}

	public void setLoanBJ(BigDecimal loanBJ) {
		this.loanBJ = loanBJ;
	}

	public BigDecimal getLoanLX() {
		return loanLX;
	}

	public void setLoanLX(BigDecimal loanLX) {
		this.loanLX = loanLX;
	}

	public BigDecimal getLeavaAccBala() {
		return leavaAccBala;
	}

	public void setLeavaAccBala(BigDecimal leavaAccBala) {
		this.leavaAccBala = leavaAccBala;
	}

	public String getAcountType() {
		return acountType;
	}

	public void setAcountType(String acountType) {
		this.acountType = acountType;
	}

	public String[][] getAcountDetail() {
		return acountDetail;
	}

	public void setAcountDetail(String[][] acountDetail) {
		this.acountDetail = acountDetail;
	}

	public String init() throws Exception {
		System.out.println("init()");
		System.out.println(contNo);	
		
		// ҵ�񷽷�
		
		
		acountDetail = new String[3][2];
		
		acountDetail[0][0] = "������";
		acountDetail[0][1] = "200��";
		acountDetail[1][0] = "��ο";
		acountDetail[1][1] = "100��";
		acountDetail[2][0] = "����";
		acountDetail[2][1] = "220��";
		
		contNo = "22222222";
		
		//super.getRequest().setAttribute("ContNo", "112233445566");
		//super.getRequest().setAttribute("businessAreaList", null);
		return SUCCESS;
	}	
	
	public String calculate() {
		
		log.info("��ȫ��ȡ���㿪ʼ  calculate()");

		Map<String,Object> res = partReceiveService.calculate(tbFlag, contNo, applyMoney.toString());
		flag = (String)res.get("flag");		
		desc = (String)res.get("desc");	
		if(!"1".equals(flag)){
			return SUCCESS;
		}
		if("1".equals(tbFlag)){
			String str = (String)res.get("detailArray");
			String[] str1 = str.substring(0,str.length()-1).split(";");
			
			acountDetail = new String[str1.length][2];			
			for (int i = 0; i < str1.length; i++) {
				acountDetail[i][0] = str1[i].split(":")[0];
				acountDetail[i][1] = str1[i].split(":")[1];
			}			
		}else{
			getMoney = new BigDecimal((String)res.get("getMoney"));//ʵ�ʵ��˽��
	        pdSxFee = new BigDecimal((String)res.get("pdSxFee"));//��ȡ������
	        loanBJ = new BigDecimal((String)res.get("loanBJ"));//������
	        loanLX = new BigDecimal((String)res.get("loanLX"));//������Ϣ
	        leavaAccBala = new BigDecimal((String)res.get("leavaAccBala"));//��ȡ��ʣ���˻���ֵ
		}        
        //�����˻���Ϣ
        PayAccInfo payAccInfo = partReceiveService.findPayAccInfoByContNo(contNo);
        acountName = payAccInfo.getAccName();
        bankName = payAccInfo.getBankType();
        acountNo = payAccInfo.getAccNo();
        
        log.info("��ȫ��ȡ�������  calculate()");
        
		return SUCCESS;
	}
	
	public String confirm() {

		log.info("��ȫ��ȡȷ�Ͽ�ʼ  confirm()");
		
		Map<String,Object> res = partReceiveService.confirm(tbFlag, contNo, applyMoney.toString());
		flag = (String)res.get("flag");		
		desc = (String)res.get("desc");	
		        
		log.info("��ȫ��ȡȷ�Ͻ���  confirm()");
		
		return SUCCESS;
	}
	
	
	/**
	 * �ֻ���֤��
	 * @return
	 */
	public String sendPhoneCheckNo() {
		log.info("���Ͷ�����֤�뿪ʼ sendPhoneCheckNo()");
		GeUserPersonal customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		String phoneNum = customer.getMobilePhone();
		//��ѯ��ǰ�ֻ�������֤���Ƿ񳬹�����
		Integer count = geUserPersonalService.getTransmissionTimesByAccount(phoneNum);
		if (count > 50) {
				flag="1";
				desc="�ǳ���Ǹ�����������������վ��������֤�뷢�����ۼƳ���50�Σ������������ԡ�";
			return SUCCESS;
		}		
		// �û��ֻ���
		String checkNo = partReceiveService.getPhoneCheckNo(phone);
		//���浱ǰ�û��ֻ����뵽���ݿ���
		geUserPersonalService.saveMobilePhoneCode(phoneNum, checkNo);
		//ͳ�Ƶ�ǰʱ����ֻ��ŷ�����֤��Ĵ���
		geUserPersonalService.saveTransmissionTimes(phoneNum, count + 1);
		log.info("���Ͷ�����֤����� sendPhoneCheckNo()");
		return SUCCESS;
	}
	

	
	
}
