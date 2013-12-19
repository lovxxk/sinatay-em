package cn.com.sinosoft.ebusiness.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.tools.BT;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.PolicyCustomerCreater;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class PolicyNoCheckerInterceptor implements Interceptor {
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description:
	 * @Author: jack_xiao
	 * @CreateDate: 2013-10-9
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Autowired
	private WSClientHelper wsclientHelper;

	@Autowired
	private PolicyCustomerCreater policyCustomerCreater;

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("Action执行前插入 代码");
		// 执行目标方法 (调用下一个拦截器, 或执行Action)
		// String actionName = invocation.getAction().getClass().toString();
		// System.out.println("actionName:" + actionName);

		ActionContext ctx = invocation.getInvocationContext();
		GeUserPersonal customer = (GeUserPersonal) ctx.getSession().get(
				"geUserPersonal");
		if (BT.isBlank(customer.getUserName()) || BT.isBlank(customer.getSex())
				|| customer.getBirthday() == null
				|| BT.isBlank(customer.getIdentifyType())
				|| BT.isBlank(customer.getIdentifyNumber())) {
			throw new RuntimeException("5要素基本信息不足，不允许操作！");
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String policyNo = request.getParameter("policyNo");
		if (BT.isBlank(policyNo)) {
			throw new RuntimeException("保单号为空！");
		}
		// 根据保单号查询用户5要素
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ContNo", policyNo);
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

					// 18位转15位身份证
					String idNo = mapResult.get("AppntID");
					String idNoLocal = customer.getIdentifyNumber();
					if (idNo != null
							&& "0".equals(mapResult.get("AppntIDType"))
							&& idNo.length() == 15) {
						idNoLocal = transIDNo(idNoLocal);
					}

					// 判断返回保单5要素与客户是否一致
					if (customer.getUserName().equals(
							mapResult.get("AppntName"))
							&& customer.getSex().equals(
									mapResult.get("AppntSex"))
							&& birthDay.equals(mapResult.get("AppntBirth"))
							&& customer.getIdentifyType().equals(
									mapResult.get("AppntIDType"))
							&& idNoLocal.equals(idNo)) {

					} else {
						throw new RuntimeException("您不能对此保单进行操作");
					}
				} else {
					throw new RuntimeException("系统中无该保单号！");
				}
			} else {
				throw new RuntimeException("查询异常！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("网络异常！");
		}

		final String res = invocation.invoke();
		System.out.println("Action执行后插入 代码");
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
