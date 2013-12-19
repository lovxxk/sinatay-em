package cn.com.sinosoft.ebusiness.infomanage.action;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.businessModule.bindPolicy.service.facade.BindPolicyService;
import cn.com.sinosoft.ebusiness.infomanage.domain.PolicyList;
import cn.com.sinosoft.ebusiness.member.policyDetail.web.PolicyDetailAction;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.tools.BT;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.CustomerPolicyCreater;
import cn.com.sinosoft.ebusiness.xmltransfer.PolicyCustomerCreater;
import cn.com.sinosoft.ebusiness.xmltransfer.PolicyListCreater;

public class PolicyListAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	

	
	public GeUserPersonal customer;
	@Autowired
	private BindPolicyService bindPolicyService;
	@Autowired
	private WSClientHelper wsclientHelper;
	@Autowired
	private PolicyListCreater policyListCreater;
	@Autowired
	private PolicyCustomerCreater policyCustomerCreater;
	@Autowired
	private CustomerPolicyCreater customerPolicyCreater;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 日志信息
	private static Logger log = LoggerFactory.getLogger(PolicyListAction.class);
	//删除绑定保单号
	private String delPolicySerialNumber;
	//需要绑定保单号
	private String policySerialNumber;
	
	//绑定返回结果
	private String flag = "N";
	//结果提示字符串
	private String result;
	//是否有保单号
	private String hasPolicy = "N";
	
	private List<PolicyList> listPolicyList = new ArrayList<PolicyList>();
	private List<PolicyList> listOtherPolicy = new ArrayList<PolicyList>();
	private List<String> bindOtherPolicy = new ArrayList<String>();
	
	/**
	 * 保单列表查询Method
	 * @return
	 */
	public String initPolicyList(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			return "login";
		}
		log.error("用户：" + customer.getUserAccount() + "查询保单列表.");
		log.error("开始查找保单列表：" + dateFormat(new Date()));
		policyList();
		log.error("结束查找保单列表：" + dateFormat(new Date()));
		if(listPolicyList.size() > 0){
			hasPolicy = "Y";
		}else{
			hasPolicy = "N";
		}
		
		return SUCCESS;
	}
	
	/**
	 * 绑定保单
	 * @return
	 */
	public String bindPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		//判断客户5要素是否为空
		if(customer == null || BT.isBlank(customer.getUserName())
			|| BT.isBlank(customer.getSex())
			|| customer.getBirthday() == null
			|| BT.isBlank(customer.getIdentifyType())
			|| BT.isBlank(customer.getIdentifyNumber())){
			flag = "N";
			result = "绑定失败，请确认您已填写基本资料！";
			return SUCCESS;
		}
		log.info("用户：" + customer.getUserAccount() + "正在绑定保单" + policySerialNumber + ".");
		//查询是否已被绑定
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		for(BindPolicy tBindPolicy : listBindPolicy){
			if(tBindPolicy.getPolicySerialNumber().equals(policySerialNumber)){
				flag = "N";
				result = "您已绑定该保单，请勿重复绑定！";
				log.info("用户：" + customer.getUserAccount() + "绑定保单" + policySerialNumber + "失败，重复绑定.");
				return SUCCESS;
			}
		}
		
		//根据保单号查询用户5要素
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ContNo", policySerialNumber);
		try{
			Document docRequest = policyCustomerCreater.createXml(map);
			String sRequest = wsclientHelper.doc2String(docRequest);
			String sResponse = wsclientHelper.submitGBK(sRequest);
			Document docResponse = wsclientHelper.string2Doc(sResponse);
			if(BT.isSuccess(docResponse)){
				Map<String,String> mapResult = policyCustomerCreater.Xml2Object(docResponse);
				
				if("Y".equals(mapResult.get("hasCustomer"))){
					//java.sql.Date birthDay = new java.sql.Date(customer.getBirthday().getTime());
					String birthDay = customer.getBirthday()==null?"":new java.sql.Date(customer.getBirthday().getTime())+"";
					
					//18位转15位身份证
					String idNo = mapResult.get("AppntID");
					String idNoLocal = customer.getIdentifyNumber();
					if(idNo != null && "0".equals(mapResult.get("AppntIDType")) && idNo.length() == 15){
						idNoLocal = transIDNo(idNoLocal);
					}
					
					//判断返回保单5要素与客户是否一致
					if(customer.getUserName().equals(mapResult.get("AppntName"))
						&& customer.getSex().equals(mapResult.get("AppntSex"))
						&& birthDay.equals(mapResult.get("AppntBirth"))
						&& customer.getIdentifyType().equals(mapResult.get("AppntIDType"))
						&& idNoLocal.equals(idNo)){
						
						BindPolicy bindPolicy = new BindPolicy();
						bindPolicy.setCustomerId(customer.getUserID());
						bindPolicy.setPolicySerialNumber(policySerialNumber);
						bindPolicyService.addBindPolicy(bindPolicy);
						
						policyList();
						
						//查询用户在核心的其他保单，保存在listOtherPolicy中
						listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
						findOtherPolicy(listBindPolicy);
						hasPolicy = "Y";
						flag = "Y";
						result = "绑定成功！";
						log.info("用户：" + customer.getUserAccount() + "绑定保单" + policySerialNumber + "成功.");
					}else{
						flag = "N";
						result = "绑定失败，请添加自己投保的保单！";
						log.info("用户：" + customer.getUserAccount() + "绑定保单" + policySerialNumber + "失败，非本人保单.");
					}
				}else{
					flag = "N";
					result = "绑定失败，系统中无该保单号！";
					log.info("用户：" + customer.getUserAccount() + "绑定保单" + policySerialNumber + "失败，无此保单.");
				}
			}else{
				flag = "N";
				result = BT.getDesc(docResponse);
				log.info("用户：" + customer.getUserAccount() + "绑定保单" + policySerialNumber + "失败，" + result +".");
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = "N";
			result = "网络异常！";
		}
		return SUCCESS;
	}
	
	/**
	 * 绑定其他保单
	 * @return
	 */
	public String bindOtherPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			return SUCCESS;
		}
		log.info("用户：" + customer.getUserAccount() + "正在批量绑定保单.");
		String customerID = customer.getUserID();
		BindPolicy bindPolicy = null;
		QueryRule queryRule;
		for(int i = 0; i< bindOtherPolicy.size(); i++){
			queryRule = QueryRule.getInstance();
			queryRule.addEqual("CUSTOMERID", customerID);
			queryRule.addEqual("POLICYSERIALNUMBER", bindOtherPolicy.get(i));
			List<BindPolicy> l = bindPolicyService.findPolicyByQueryRule(queryRule);
			if(l.size() == 0){
				bindPolicy = new BindPolicy();
				bindPolicy.setCustomerId(customerID);
				bindPolicy.setPolicySerialNumber(bindOtherPolicy.get(i));
				bindPolicyService.addBindPolicy(bindPolicy);
				log.info("用户：" + customer.getUserAccount() + "绑定保单" + bindPolicy.getPolicySerialNumber() + ".");
			}
			
		}
		policyList();
		
		return SUCCESS;
	}
	
	/**
	 * 删除绑定保单
	 */
	public String delBindPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		BindPolicy bindPolicy = null;
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		queryRule.addEqual("POLICYSERIALNUMBER", delPolicySerialNumber);
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		if(listBindPolicy.size() != 0){
			bindPolicy = listBindPolicy.get(0);
			bindPolicyService.deleteBindPolicy(bindPolicy);
			flag = "Y";
			result = "您已成功解除绑定！";
			log.info("用户：" + customer.getUserAccount() + "解绑保单" + delPolicySerialNumber + ".");
			return SUCCESS;
		}else{
			flag = "N";
			result = "该保单未被绑定！";
			return SUCCESS;
		}
	}
	
	/**
	 * 会员中心查找未绑定保单及绑定保单列表
	 * @return
	 */
	public String centerFindPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			listPolicyList = new ArrayList<PolicyList>();
			listOtherPolicy = new ArrayList<PolicyList>();
			return SUCCESS;
		}
		
		log.error("用户：" + customer.getUserAccount() + "查询保单列表.");
		log.error("开始查找保单列表：" + dateFormat(new Date()));
		policyList();
		log.error("结束查找保单列表：" + dateFormat(new Date()));
		if (listPolicyList.size() > 3) {
			List<PolicyList> tList = listPolicyList;
			listPolicyList = new ArrayList<PolicyList>();
			for (int i = 0; i < 3; i++) {
				listPolicyList.add(tList.get(i));
			}
		}
		
		log.info("用户：" + customer.getUserAccount() + "查询未绑定保单.");
		log.error("开始查找未绑定保单：" + dateFormat(new Date()));
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		
		findOtherPolicy(listBindPolicy);
		log.error("结束查找未绑定保单：" + dateFormat(new Date()));
		return SUCCESS;
	}
	
	/**
	 * 显示未绑定保单
	 */
	public String showUnbindPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			listOtherPolicy = new ArrayList<PolicyList>();
			return SUCCESS;
		}
		log.info("用户：" + customer.getUserAccount() + "查询未绑定保单.");
		log.error("开始查找未绑定保单：" + dateFormat(new Date()));
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		
		findOtherPolicy(listBindPolicy);
		log.error("结束查找未绑定保单：" + dateFormat(new Date()));
		return SUCCESS;
	}
	
	/**
	 * 会员中心绑定保单
	 */
	public String centerBindPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			return SUCCESS;
		}
		String customerID = customer.getUserID();
		BindPolicy bindPolicy = null;
		QueryRule queryRule;
		for(int i = 0; i< bindOtherPolicy.size(); i++){
			queryRule = QueryRule.getInstance();
			queryRule.addEqual("CUSTOMERID", customerID);
			queryRule.addEqual("POLICYSERIALNUMBER", bindOtherPolicy.get(i));
			List<BindPolicy> l = bindPolicyService.findPolicyByQueryRule(queryRule);
			if(l.size() == 0){
				bindPolicy = new BindPolicy();
				bindPolicy.setCustomerId(customerID);
				bindPolicy.setPolicySerialNumber(bindOtherPolicy.get(i));
				bindPolicyService.addBindPolicy(bindPolicy);
				log.info("用户：" + customer.getUserAccount() + "绑定保单" + bindPolicy.getPolicySerialNumber() + ".");
			}
			
		}
//		
//		queryRule = QueryRule.getInstance();
//		queryRule.addEqual("CUSTOMERID", customer.getUserID());
//		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
//		
//		findOtherPolicy(listBindPolicy);
		return SUCCESS;
	}
	
	/**
	 * 保单列表查询
	 */
	private void policyList() {
		
		// 取的下载电子保单的地址
		String sql = "select codecorerelation from ge_code where codetype = 'contfilepath'";
		List<Object> objectList = new ArrayList<Object>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, objectList.toArray());		
		String base_path = "";		
		if(list.size() != 0){
			base_path = list.get(0).get("codecorerelation").toString();
		}else{
			throw new RuntimeException("下载中心文件路劲未配置");
		}
//		base_path = "D:/file2";
		
		log.error("开始查找用户已绑定的保单号：" + dateFormat(new Date()));
		//查询保单绑定表中的保单号.
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		log.error("结束查找用户已绑定的保单号：" + dateFormat(new Date()));
		listPolicyList = new ArrayList<PolicyList>();
		
		//查询保单列表
		if(listBindPolicy.size() == 0){
			return;
		}
		log.info("用户：" + customer.getUserAccount() + "查询保单列表.");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("listBindPolicy", listBindPolicy);
		try{
			log.error("开始生成保单列表请求报文：" + dateFormat(new Date()));
			Document docRequest = policyListCreater.createXml(map);
			String sRequest = wsclientHelper.doc2String(docRequest);
			log.error("结束生成保单列表请求报文,开始发送请求：" + dateFormat(new Date()));
			String sResponse = wsclientHelper.submitGBK(sRequest);
			log.error("结束发送请求,开始解析报文：" + dateFormat(new Date()));
			Document docResponse = wsclientHelper.string2Doc(sResponse);
			if(BT.isSuccess(docResponse)){
				listPolicyList = policyListCreater.Xml2Object(docResponse);
				flag = "Y";
				//添加下载地址
				for (PolicyList pl : listPolicyList) {
					String fileName = pl.getPrNumber() + "sinatay.pdf";
					String filePath = base_path
							+ "/" + fileName;
					File file = new File(filePath);
					if (file.exists() || file.isFile()) {
						pl.setDownloadString(PolicyListAction
								.encryption(filePath));
					}
				}
			
				// 按生效日期+保单号倒序排列
				Collections.sort(listPolicyList, new Comparator<PolicyList>() {
					@Override
					public int compare(PolicyList o1, PolicyList o2) {
						// 生效日期+保单号
						String s1 = o1.getInceptionDate()
								+ o1.getPolicySerialNumber();
						String s2 = o2.getInceptionDate()
								+ o2.getPolicySerialNumber();
						return s2.compareTo(s1);
					}
				});
				
			}else{
				flag = "N";
				result = BT.getDesc(docResponse);
			}
			log.error("结束解析报文：" + dateFormat(new Date()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询用户除现有绑定保单外的其他保单
	 */
	private void findOtherPolicy(List<BindPolicy> listBindPolicy){
		log.error("开始生成查找用户核心保单报文：" + dateFormat(new Date()));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("AppntName", customer.getUserName());
		map.put("AppntSex", customer.getSex());
		String birthDay = customer.getBirthday()==null?"":new java.sql.Date(customer.getBirthday().getTime())+"";
		map.put("AppntBirthday", birthDay);
		map.put("AppntIDType", customer.getIdentifyType());
		map.put("AppntIDNo", customer.getIdentifyNumber());
		List<PolicyList> listAllPolicy = new ArrayList<PolicyList>();
		listOtherPolicy = new ArrayList<PolicyList>();
		try{
			Document docRequest = customerPolicyCreater.createXml(map);
			String sRequest = wsclientHelper.doc2String(docRequest);
			log.error("结束生成查找用户核心保单报文,开始发送报文：" + dateFormat(new Date()));
			String sResponse = wsclientHelper.submitGBK(sRequest);
			log.error("结束发送报文,开始解析报文：" + dateFormat(new Date()));
			Document docResponse = wsclientHelper.string2Doc(sResponse);
			if(BT.isSuccess(docResponse)){
				listAllPolicy = customerPolicyCreater.Xml2Object(docResponse);
				log.error("结束解析报文,开始比对本地已有保单：" + dateFormat(new Date()));
				for(int i = 0; i < listAllPolicy.size(); i++){
					int j = 0;
					for(; j < listBindPolicy.size(); j++){
						if(listBindPolicy.get(j).getPolicySerialNumber().equals(listAllPolicy.get(i).getPolicySerialNumber())){
							break;
						}
					}
					//未被绑定，则添加到listOtherPolicy中
					if(j == listBindPolicy.size()){
						listOtherPolicy.add(listAllPolicy.get(i));
					}
				}
				log.error("结束比对本地已有保单：" + dateFormat(new Date()));
				flag = "Y";
			}else{
				flag = "N";
				result = BT.getDesc(docResponse);
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = "N";
			result = "网络异常";
		}
	}
	
	private static String transIDNo(String IDNo) {

		String temStr = "";
		String temStr1 = "";
		String temStr2 = "";
		if ((IDNo.length() != 15) && (IDNo.length() != 18)) {
			return IDNo;
		}
		if (IDNo.length() == 18) {
			temStr1 = IDNo.substring(0, 6);
			temStr2 = IDNo.substring(8, 17);
			temStr = temStr1.concat(temStr2);
		}
		return temStr;
	}


	/**
	 * 加密字符串
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encryption(String string) {// ASCII转换为字符串
		// 转义符转换，注意转换后不能少于6个字符，所以前面加点固定的字符
		String s0 = "";
		try {
			s0 = URLEncoder.encode("中科软信泰项目组" + string, "GBK").replaceAll(
					"%", "`");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// 计算子字符串的长度
		int length = s0.length() / 4 + (s0.length() % 4 == 0 ? 0 : 1);
		// 分解成4个子字符串
		String s1 = s0.substring(0, length);
		String s2 = s0.substring(length, length * 2);
		String s3 = s0.substring(length * 2, length * 3);
		String s4 = s0.substring(length * 3);
		// s4补~字符
		switch (length - s4.length()) {
		case 1:
			s4 = s4 + "~";
			break;
		case 2:
			s4 = s4 + "~~";
			break;
		case 3:
			s4 = s4 + "~~~";
			break;
		default:
			break;
		}
		// 重新排列成新的字符串
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(s1.charAt(i));
			sb.append(s2.charAt(i));
			sb.append(s3.charAt(i));
			sb.append(s4.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 解密字符串
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decryption(String string)
			 {
		int length = string.length();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();
		for (int i = 0; i < length;) {
			sb1.append(string.charAt(i));
			i++;
			sb2.append(string.charAt(i));
			i++;
			sb3.append(string.charAt(i));
			i++;
			sb4.append(string.charAt(i));
			i++;
		}
		String s0 = "" + sb1 + sb2 + sb3 + sb4;
		// 消除最后的"~"字符,最多有3个，所以执行3遍
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		// 转义符转换
		s0 = s0.replaceAll("`", "%");
		try {
			s0 = URLDecoder.decode(s0, "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		s0 = s0.replaceFirst("中科软信泰项目组", "");
		return s0;
	}
	
	private String dateFormat(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}	

	public String getDelPolicySerialNumber() {
		return delPolicySerialNumber;
	}

	public void setDelPolicySerialNumber(String delPolicySerialNumber) {
		this.delPolicySerialNumber = delPolicySerialNumber;
	}

	public String getPolicySerialNumber() {
		return policySerialNumber;
	}

	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<PolicyList> getListPolicyList() {
		return listPolicyList;
	}

	public void setListPolicyList(List<PolicyList> listPolicyList) {
		this.listPolicyList = listPolicyList;
	}

	public List<PolicyList> getListOtherPolicy() {
		return listOtherPolicy;
	}

	public void setListOtherPolicy(List<PolicyList> listOtherPolicy) {
		this.listOtherPolicy = listOtherPolicy;
	}
	
	
	public String getHasPolicy() {
		return hasPolicy;
	}

	public void setHasPolicy(String hasPolicy) {
		this.hasPolicy = hasPolicy;
	}
	
	
	public List<String> getBindOtherPolicy() {
		return bindOtherPolicy;
	}

	public void setBindOtherPolicy(List<String> bindOtherPolicy) {
		this.bindOtherPolicy = bindOtherPolicy;
	}

	public static void main(String[] args){
		System.out.println(transIDNo("33010519691127002X"));
		/*
		WSClientHelper ws = new WSClientHelper();
		Document doc = ws.loadDoc("D:\\a.xml");
		List<Element> list = doc.selectNodes("//Datas/Data");
		for(int i = 0; i < list.size(); i++){
			Element e = list.get(i);
			Element a = (Element) e.selectSingleNode("Col01");
			System.out.println(a.getText());
		}
		System.out.println(doc.asXML());*/
	}
}
