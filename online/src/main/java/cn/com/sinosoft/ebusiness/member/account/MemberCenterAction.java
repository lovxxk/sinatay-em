package cn.com.sinosoft.ebusiness.member.account;

import ins.framework.web.Struts2Action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.OrderFormService;
import cn.com.sinosoft.ebusiness.infomanage.action.PolicyListAction;
import cn.com.sinosoft.ebusiness.infomanage.domain.PolicyList;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteMainService;
import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.util.security.springSecurity.SpringSecurityUtils;

public class MemberCenterAction extends Struts2Action {
	
	private static Logger log = LoggerFactory.getLogger(MemberCenterAction.class);

	@Autowired
	public GeUserPersonalService geUserPersonalService;
	
	@Autowired
	private OrderFormService orderFormService;

	@Autowired
	public QuoteMainService quoteMainService;
	
	public List<OrderForm> twoOrderForms;
	
	public List<QuoteMain> quoteMains;
	
	public List<PolicyList> policyList;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	//�����Ա������ҳ
	public String homePage() {
		log.error("�����Ա���ģ���ʼ���ص��ӱ�����ַ:" + dateFormat(new Date()));
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
		log.error("�����Ա���ģ����ص��ӱ�����ַ����:" + dateFormat(new Date()));
//		base_path = "D:/file2";	
		
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		String userId = user.getCustomer().getUserID();

		log.error("�����Ա���ģ���ʼ��ѯδ֧���Ķ�������:" + dateFormat(new Date()));
		//δ֧��
		Integer unPaidCount = orderFormService.getOrderFomrsByState(OrderStatus.UNPAID, userId);
		log.error("�����Ա���ģ���ѯδ֧���Ķ�����������:" + dateFormat(new Date()));
		
		log.error("�����Ա���ģ���ʼ��ѯ���ύ�Ķ�������:" + dateFormat(new Date()));
		//���ύ(/**���㵥���� 1���� 2��дͶ���� 3ȷ��Ͷ����*/)
		Integer quoteMainCount = orderFormService.getQuoteMainByState(userId);
		log.error("�����Ա���ģ���ѯ���ύ�Ķ�����������:" + dateFormat(new Date()));
		

		log.error("�����Ա���ģ���ʼ��ѯ����ʱ�������������������:" + dateFormat(new Date()));
		twoOrderForms = orderFormService.getTwoOrderForms(userId);
		log.error("�����Ա���ģ���ѯ����ʱ������������������ݽ���:" + dateFormat(new Date()));
		
		HttpServletRequest request = getRequest();
		request.setAttribute("unPaidCount", unPaidCount);
		request.setAttribute("quoteMainCount", quoteMainCount);
		/*
		log.error("�����Ա���ģ���ʼ��ѯ�û��󶨱�������:" + dateFormat(new Date()));
		//policyList = geUserPersonalService.findPolicyList(userId);
		log.error("�����Ա���ģ���ѯ�û��󶨱������ݽ���:" + dateFormat(new Date()));
		
		
		//������ص�ַ
		for (PolicyList pl : policyList) {
			String fileName = pl.getPrNumber() + "sinatay.pdf";
			String filePath = base_path
					+ "/" + fileName;
			File file = new File(filePath);
			if (file.exists() || file.isFile()) {
				pl.setDownloadString(PolicyListAction
						.encryption(filePath));
			}
		}*/

		log.error("�����Ա���ģ���ʼ���������֤:" + dateFormat(new Date()));
		//�����֤
		boolean bindPolicy = geUserPersonalService.checkBindPolicy(user.getCustomer().getUserID());
		if (!bindPolicy) {
			bindPolicy = geUserPersonalService.findPolicyByOrder(user.getCustomer().getUserID());
		}
		log.error("�����Ա���ģ����������֤����:" + dateFormat(new Date()));
		getRequest().setAttribute("bindPolicy", bindPolicy);
	

		//log.error("�����Ա���ģ�����������:" + dateFormat(new Date()));
		// ����Ч����+�����ŵ�������
		/*Collections.sort(policyList, new Comparator<PolicyList>() {
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
		log.error("�����Ա���ģ��������������:" + dateFormat(new Date()));
		
		if(policyList.size() > 3){
			List<PolicyList> tPolicyList = policyList;
			policyList = new ArrayList<PolicyList>();
			for(int i = 0; i < 3; i++){
				policyList.add(tPolicyList.get(i));
			}
		}*/
		
		return SUCCESS;
	}
	
	
	
	/**��ѯ�������*/
	public String searchClaimFolder() {
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		if (user != null)
			return SUCCESS;
		
		return ERROR;
	}

	public void setTwoOrderForms(List<OrderForm> twoOrderForms) {
		this.twoOrderForms = twoOrderForms;
	}

	public void setPolicyList(List<PolicyList> policyList) {
		this.policyList = policyList;
	}

	public String dateFormat(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}	
}
