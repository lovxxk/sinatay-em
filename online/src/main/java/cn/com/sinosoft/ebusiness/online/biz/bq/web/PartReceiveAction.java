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
 * 后台服务网点管理
 * 
 *  
 * 
 */
public class PartReceiveAction extends Struts2Action {
	
	
	private static Logger log = LoggerFactory.getLogger(PartReceiveAction.class);
	
	//返回标志
	private String flag;
	//返回信息
	private String desc;

	//保单号码
	private String contNo;
	
	//险种编码
	private String riskCode;
	//险种名称
	private String riskName;
	//账户价值
	private BigDecimal acountValue;
	//保单年度
	private int curYear;
	//已领取次数
	private int getCount;
	//申请领取金额
	private BigDecimal applyMoney;
	
	//实际到账金额
	private BigDecimal getMoney;
	//领取手续费
	private BigDecimal pdSxFee;
	//还款金额
	private BigDecimal loanBJ;
	//还款利息
	private BigDecimal loanLX;
	//领取后剩余账户价值
	private BigDecimal leavaAccBala;	
	
	//付费账户类型
	private String acountType;
	//付费账户信息列表
	private String[][] acountDetail;	
	//付费账户类型-账号名
	private String acountName;
	//付费账户类型-银行名称
	private String bankName;
	//付费账户类型-账号
	private String acountNo;	
	//是否全额退保
	private String tbFlag;	
	//手机
	private String phone;
	//验证码
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
		
		// 业务方法
		
		
		acountDetail = new String[3][2];
		
		acountDetail[0][0] = "王劲燕";
		acountDetail[0][1] = "200块";
		acountDetail[1][0] = "麻慰";
		acountDetail[1][1] = "100块";
		acountDetail[2][0] = "王宇";
		acountDetail[2][1] = "220块";
		
		contNo = "22222222";
		
		//super.getRequest().setAttribute("ContNo", "112233445566");
		//super.getRequest().setAttribute("businessAreaList", null);
		return SUCCESS;
	}	
	
	public String calculate() {
		
		log.info("保全领取试算开始  calculate()");

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
			getMoney = new BigDecimal((String)res.get("getMoney"));//实际到账金额
	        pdSxFee = new BigDecimal((String)res.get("pdSxFee"));//领取手续费
	        loanBJ = new BigDecimal((String)res.get("loanBJ"));//还款金额
	        loanLX = new BigDecimal((String)res.get("loanLX"));//还款利息
	        leavaAccBala = new BigDecimal((String)res.get("leavaAccBala"));//领取后剩余账户价值
		}        
        //付费账户信息
        PayAccInfo payAccInfo = partReceiveService.findPayAccInfoByContNo(contNo);
        acountName = payAccInfo.getAccName();
        bankName = payAccInfo.getBankType();
        acountNo = payAccInfo.getAccNo();
        
        log.info("保全领取试算结束  calculate()");
        
		return SUCCESS;
	}
	
	public String confirm() {

		log.info("保全领取确认开始  confirm()");
		
		Map<String,Object> res = partReceiveService.confirm(tbFlag, contNo, applyMoney.toString());
		flag = (String)res.get("flag");		
		desc = (String)res.get("desc");	
		        
		log.info("保全领取确认结束  confirm()");
		
		return SUCCESS;
	}
	
	
	/**
	 * 手机验证码
	 * @return
	 */
	public String sendPhoneCheckNo() {
		log.info("发送短信验证码开始 sendPhoneCheckNo()");
		GeUserPersonal customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		String phoneNum = customer.getMobilePhone();
		//查询当前手机发送验证码是否超过次数
		Integer count = geUserPersonalService.getTransmissionTimesByAccount(phoneNum);
		if (count > 50) {
				flag="1";
				desc="非常抱歉，今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。";
			return SUCCESS;
		}		
		// 用户手机号
		String checkNo = partReceiveService.getPhoneCheckNo(phone);
		//保存当前用户手机号码到数据库中
		geUserPersonalService.saveMobilePhoneCode(phoneNum, checkNo);
		//统计当前时间该手机号发送验证码的次数
		geUserPersonalService.saveTransmissionTimes(phoneNum, count + 1);
		log.info("发送短信验证码结束 sendPhoneCheckNo()");
		return SUCCESS;
	}
	

	
	
}
