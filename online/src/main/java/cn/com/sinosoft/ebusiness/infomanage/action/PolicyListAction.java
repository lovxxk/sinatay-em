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
	
	// ��־��Ϣ
	private static Logger log = LoggerFactory.getLogger(PolicyListAction.class);
	//ɾ���󶨱�����
	private String delPolicySerialNumber;
	//��Ҫ�󶨱�����
	private String policySerialNumber;
	
	//�󶨷��ؽ��
	private String flag = "N";
	//�����ʾ�ַ���
	private String result;
	//�Ƿ��б�����
	private String hasPolicy = "N";
	
	private List<PolicyList> listPolicyList = new ArrayList<PolicyList>();
	private List<PolicyList> listOtherPolicy = new ArrayList<PolicyList>();
	private List<String> bindOtherPolicy = new ArrayList<String>();
	
	/**
	 * �����б��ѯMethod
	 * @return
	 */
	public String initPolicyList(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			return "login";
		}
		log.error("�û���" + customer.getUserAccount() + "��ѯ�����б�.");
		log.error("��ʼ���ұ����б�" + dateFormat(new Date()));
		policyList();
		log.error("�������ұ����б�" + dateFormat(new Date()));
		if(listPolicyList.size() > 0){
			hasPolicy = "Y";
		}else{
			hasPolicy = "N";
		}
		
		return SUCCESS;
	}
	
	/**
	 * �󶨱���
	 * @return
	 */
	public String bindPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		//�жϿͻ�5Ҫ���Ƿ�Ϊ��
		if(customer == null || BT.isBlank(customer.getUserName())
			|| BT.isBlank(customer.getSex())
			|| customer.getBirthday() == null
			|| BT.isBlank(customer.getIdentifyType())
			|| BT.isBlank(customer.getIdentifyNumber())){
			flag = "N";
			result = "��ʧ�ܣ���ȷ��������д�������ϣ�";
			return SUCCESS;
		}
		log.info("�û���" + customer.getUserAccount() + "���ڰ󶨱���" + policySerialNumber + ".");
		//��ѯ�Ƿ��ѱ���
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		for(BindPolicy tBindPolicy : listBindPolicy){
			if(tBindPolicy.getPolicySerialNumber().equals(policySerialNumber)){
				flag = "N";
				result = "���Ѱ󶨸ñ����������ظ��󶨣�";
				log.info("�û���" + customer.getUserAccount() + "�󶨱���" + policySerialNumber + "ʧ�ܣ��ظ���.");
				return SUCCESS;
			}
		}
		
		//���ݱ����Ų�ѯ�û�5Ҫ��
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
					
					//18λת15λ���֤
					String idNo = mapResult.get("AppntID");
					String idNoLocal = customer.getIdentifyNumber();
					if(idNo != null && "0".equals(mapResult.get("AppntIDType")) && idNo.length() == 15){
						idNoLocal = transIDNo(idNoLocal);
					}
					
					//�жϷ��ر���5Ҫ����ͻ��Ƿ�һ��
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
						
						//��ѯ�û��ں��ĵ�����������������listOtherPolicy��
						listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
						findOtherPolicy(listBindPolicy);
						hasPolicy = "Y";
						flag = "Y";
						result = "�󶨳ɹ���";
						log.info("�û���" + customer.getUserAccount() + "�󶨱���" + policySerialNumber + "�ɹ�.");
					}else{
						flag = "N";
						result = "��ʧ�ܣ�������Լ�Ͷ���ı�����";
						log.info("�û���" + customer.getUserAccount() + "�󶨱���" + policySerialNumber + "ʧ�ܣ��Ǳ��˱���.");
					}
				}else{
					flag = "N";
					result = "��ʧ�ܣ�ϵͳ���޸ñ����ţ�";
					log.info("�û���" + customer.getUserAccount() + "�󶨱���" + policySerialNumber + "ʧ�ܣ��޴˱���.");
				}
			}else{
				flag = "N";
				result = BT.getDesc(docResponse);
				log.info("�û���" + customer.getUserAccount() + "�󶨱���" + policySerialNumber + "ʧ�ܣ�" + result +".");
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = "N";
			result = "�����쳣��";
		}
		return SUCCESS;
	}
	
	/**
	 * ����������
	 * @return
	 */
	public String bindOtherPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			return SUCCESS;
		}
		log.info("�û���" + customer.getUserAccount() + "���������󶨱���.");
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
				log.info("�û���" + customer.getUserAccount() + "�󶨱���" + bindPolicy.getPolicySerialNumber() + ".");
			}
			
		}
		policyList();
		
		return SUCCESS;
	}
	
	/**
	 * ɾ���󶨱���
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
			result = "���ѳɹ�����󶨣�";
			log.info("�û���" + customer.getUserAccount() + "��󱣵�" + delPolicySerialNumber + ".");
			return SUCCESS;
		}else{
			flag = "N";
			result = "�ñ���δ���󶨣�";
			return SUCCESS;
		}
	}
	
	/**
	 * ��Ա���Ĳ���δ�󶨱������󶨱����б�
	 * @return
	 */
	public String centerFindPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			listPolicyList = new ArrayList<PolicyList>();
			listOtherPolicy = new ArrayList<PolicyList>();
			return SUCCESS;
		}
		
		log.error("�û���" + customer.getUserAccount() + "��ѯ�����б�.");
		log.error("��ʼ���ұ����б�" + dateFormat(new Date()));
		policyList();
		log.error("�������ұ����б�" + dateFormat(new Date()));
		if (listPolicyList.size() > 3) {
			List<PolicyList> tList = listPolicyList;
			listPolicyList = new ArrayList<PolicyList>();
			for (int i = 0; i < 3; i++) {
				listPolicyList.add(tList.get(i));
			}
		}
		
		log.info("�û���" + customer.getUserAccount() + "��ѯδ�󶨱���.");
		log.error("��ʼ����δ�󶨱�����" + dateFormat(new Date()));
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		
		findOtherPolicy(listBindPolicy);
		log.error("��������δ�󶨱�����" + dateFormat(new Date()));
		return SUCCESS;
	}
	
	/**
	 * ��ʾδ�󶨱���
	 */
	public String showUnbindPolicy(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			listOtherPolicy = new ArrayList<PolicyList>();
			return SUCCESS;
		}
		log.info("�û���" + customer.getUserAccount() + "��ѯδ�󶨱���.");
		log.error("��ʼ����δ�󶨱�����" + dateFormat(new Date()));
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		
		findOtherPolicy(listBindPolicy);
		log.error("��������δ�󶨱�����" + dateFormat(new Date()));
		return SUCCESS;
	}
	
	/**
	 * ��Ա���İ󶨱���
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
				log.info("�û���" + customer.getUserAccount() + "�󶨱���" + bindPolicy.getPolicySerialNumber() + ".");
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
	 * �����б��ѯ
	 */
	private void policyList() {
		
		// ȡ�����ص��ӱ����ĵ�ַ
		String sql = "select codecorerelation from ge_code where codetype = 'contfilepath'";
		List<Object> objectList = new ArrayList<Object>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, objectList.toArray());		
		String base_path = "";		
		if(list.size() != 0){
			base_path = list.get(0).get("codecorerelation").toString();
		}else{
			throw new RuntimeException("���������ļ�·��δ����");
		}
//		base_path = "D:/file2";
		
		log.error("��ʼ�����û��Ѱ󶨵ı����ţ�" + dateFormat(new Date()));
		//��ѯ�����󶨱��еı�����.
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		log.error("���������û��Ѱ󶨵ı����ţ�" + dateFormat(new Date()));
		listPolicyList = new ArrayList<PolicyList>();
		
		//��ѯ�����б�
		if(listBindPolicy.size() == 0){
			return;
		}
		log.info("�û���" + customer.getUserAccount() + "��ѯ�����б�.");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("listBindPolicy", listBindPolicy);
		try{
			log.error("��ʼ���ɱ����б������ģ�" + dateFormat(new Date()));
			Document docRequest = policyListCreater.createXml(map);
			String sRequest = wsclientHelper.doc2String(docRequest);
			log.error("�������ɱ����б�������,��ʼ��������" + dateFormat(new Date()));
			String sResponse = wsclientHelper.submitGBK(sRequest);
			log.error("������������,��ʼ�������ģ�" + dateFormat(new Date()));
			Document docResponse = wsclientHelper.string2Doc(sResponse);
			if(BT.isSuccess(docResponse)){
				listPolicyList = policyListCreater.Xml2Object(docResponse);
				flag = "Y";
				//������ص�ַ
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
			
				// ����Ч����+�����ŵ�������
				Collections.sort(listPolicyList, new Comparator<PolicyList>() {
					@Override
					public int compare(PolicyList o1, PolicyList o2) {
						// ��Ч����+������
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
			log.error("�����������ģ�" + dateFormat(new Date()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ��ѯ�û������а󶨱��������������
	 */
	private void findOtherPolicy(List<BindPolicy> listBindPolicy){
		log.error("��ʼ���ɲ����û����ı������ģ�" + dateFormat(new Date()));
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
			log.error("�������ɲ����û����ı�������,��ʼ���ͱ��ģ�" + dateFormat(new Date()));
			String sResponse = wsclientHelper.submitGBK(sRequest);
			log.error("�������ͱ���,��ʼ�������ģ�" + dateFormat(new Date()));
			Document docResponse = wsclientHelper.string2Doc(sResponse);
			if(BT.isSuccess(docResponse)){
				listAllPolicy = customerPolicyCreater.Xml2Object(docResponse);
				log.error("������������,��ʼ�ȶԱ������б�����" + dateFormat(new Date()));
				for(int i = 0; i < listAllPolicy.size(); i++){
					int j = 0;
					for(; j < listBindPolicy.size(); j++){
						if(listBindPolicy.get(j).getPolicySerialNumber().equals(listAllPolicy.get(i).getPolicySerialNumber())){
							break;
						}
					}
					//δ���󶨣�����ӵ�listOtherPolicy��
					if(j == listBindPolicy.size()){
						listOtherPolicy.add(listAllPolicy.get(i));
					}
				}
				log.error("�����ȶԱ������б�����" + dateFormat(new Date()));
				flag = "Y";
			}else{
				flag = "N";
				result = BT.getDesc(docResponse);
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = "N";
			result = "�����쳣";
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
	 * �����ַ���
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encryption(String string) {// ASCIIת��Ϊ�ַ���
		// ת���ת����ע��ת����������6���ַ�������ǰ��ӵ�̶����ַ�
		String s0 = "";
		try {
			s0 = URLEncoder.encode("�п�����̩��Ŀ��" + string, "GBK").replaceAll(
					"%", "`");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// �������ַ����ĳ���
		int length = s0.length() / 4 + (s0.length() % 4 == 0 ? 0 : 1);
		// �ֽ��4�����ַ���
		String s1 = s0.substring(0, length);
		String s2 = s0.substring(length, length * 2);
		String s3 = s0.substring(length * 2, length * 3);
		String s4 = s0.substring(length * 3);
		// s4��~�ַ�
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
		// �������г��µ��ַ���
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
	 * �����ַ���
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
		// ��������"~"�ַ�,�����3��������ִ��3��
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		// ת���ת��
		s0 = s0.replaceAll("`", "%");
		try {
			s0 = URLDecoder.decode(s0, "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		s0 = s0.replaceFirst("�п�����̩��Ŀ��", "");
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
