package cn.com.sinosoft.ebusiness.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.ebusiness.member.policyDetail.web.PolicyDetailAction;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.tools.BT;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.PolicyCustomerCreater;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ContCheckerInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(ContCheckerInterceptor.class);
	
	@Autowired
	private WSClientHelper wsclientHelper;

	@Autowired
	private PolicyCustomerCreater policyCustomerCreater;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ctx = invocation.getInvocationContext();
		GeUserPersonal customer = (GeUserPersonal) ctx.getSession().get(
				"geUserPersonal");
		if(null == customer){
			throw new RuntimeException("��½��Ϣ�ѹ��ڣ�");
		}
		
		if (BT.isBlank(customer.getUserName()) || BT.isBlank(customer.getSex())
				|| customer.getBirthday() == null
				|| BT.isBlank(customer.getIdentifyType())
				|| BT.isBlank(customer.getIdentifyNumber())) {
			throw new RuntimeException("5Ҫ�ػ�����Ϣ���㣬�����������");
		}
		
		String contNo = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String actionName = invocation.getAction().getClass().getSimpleName();
		// ���ݲ�ͬ��action��ȡ�ı����ŵķ���Ҳ��ͬ
		if("AppntBaseInfoChangeAction".equals(actionName)){
			contNo = request.getParameter("policySerialNumber");
		} else if("InsuredBaseInfoChangeAction".equals(actionName)) {
			contNo = request.getParameter("policySerialNumber");
		} else {
			contNo = request.getParameter("contNo");
		}
		
		if(BT.isBlank(contNo)){
			throw new RuntimeException("������Ϊ�գ�");
		}
		// ���ݱ����Ų�ѯ�û�5Ҫ��
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ContNo", contNo);
		try {
			Document docRequest = policyCustomerCreater.createXml(map);
			String sRequest = wsclientHelper.doc2String(docRequest);
			String sResponse = wsclientHelper.submitGBK(sRequest);
			Document docResponse = wsclientHelper.string2Doc(sResponse);
			if (BT.isSuccess(docResponse)) {
				Map<String, String> mapResult = policyCustomerCreater
						.Xml2Object(docResponse);
				if ("Y".equals(mapResult.get("hasCustomer"))) {
					String birthDay = customer.getBirthday() == null ? ""
							: new java.sql.Date(customer.getBirthday()
									.getTime()) + "";
					// 18λת15λ���֤
					String idNo = mapResult.get("AppntID");
					String idNoLocal = customer.getIdentifyNumber();
					if (idNo != null && "0".equals(mapResult.get("AppntIDType"))
							&& idNo.length() == 15) {
						idNoLocal = transIDNo(idNoLocal);
					}
					// �жϷ��ر���5Ҫ����ͻ��Ƿ�һ��
					if (customer.getUserName().equals(
							mapResult.get("AppntName"))
							&& customer.getSex().equals(
									mapResult.get("AppntSex"))
							&& birthDay.equals(mapResult.get("AppntBirth"))
							&& customer.getIdentifyType().equals(
									mapResult.get("AppntIDType"))
							&& idNoLocal.equals(idNo)) {
						// ��������£�ʲô������
					} else {
						log.info("�ͻ���" + customer.getUserID() + " ����Ҫ���뱣����"
								+ contNo + "��ƥ�䣡");
						throw new RuntimeException("5Ҫ�ز�ƥ�䣬���ܶԴ˱������в�����");
					}
				} else {
					throw new RuntimeException("ϵͳ���޸ñ����ţ�");
				}
			} else {
				throw new RuntimeException("��ѯ�쳣��");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�����쳣��");
		}
		// ����ִ�з��ʵ�action
		final String res = invocation.invoke();

		return res;
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

}
