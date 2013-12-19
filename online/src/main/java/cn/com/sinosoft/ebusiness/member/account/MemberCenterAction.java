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
	
	
	//进入会员中心首页
	public String homePage() {
		log.error("进入会员中心，开始下载电子保单地址:" + dateFormat(new Date()));
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
		log.error("进入会员中心，下载电子保单地址结束:" + dateFormat(new Date()));
//		base_path = "D:/file2";	
		
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		String userId = user.getCustomer().getUserID();

		log.error("进入会员中心，开始查询未支付的订单数量:" + dateFormat(new Date()));
		//未支付
		Integer unPaidCount = orderFormService.getOrderFomrsByState(OrderStatus.UNPAID, userId);
		log.error("进入会员中心，查询未支付的订单数量结束:" + dateFormat(new Date()));
		
		log.error("进入会员中心，开始查询待提交的订单数量:" + dateFormat(new Date()));
		//待提交(/**试算单步骤 1试算 2填写投保单 3确认投保单*/)
		Integer quoteMainCount = orderFormService.getQuoteMainByState(userId);
		log.error("进入会员中心，查询待提交的订单数量结束:" + dateFormat(new Date()));
		

		log.error("进入会员中心，开始查询更新时间最近的两条订单数据:" + dateFormat(new Date()));
		twoOrderForms = orderFormService.getTwoOrderForms(userId);
		log.error("进入会员中心，查询更新时间最近的两条订单数据结束:" + dateFormat(new Date()));
		
		HttpServletRequest request = getRequest();
		request.setAttribute("unPaidCount", unPaidCount);
		request.setAttribute("quoteMainCount", quoteMainCount);
		/*
		log.error("进入会员中心，开始查询用户绑定保单数据:" + dateFormat(new Date()));
		//policyList = geUserPersonalService.findPolicyList(userId);
		log.error("进入会员中心，查询用户绑定保单数据结束:" + dateFormat(new Date()));
		
		
		//添加下载地址
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

		log.error("进入会员中心，开始进行身份验证:" + dateFormat(new Date()));
		//身份验证
		boolean bindPolicy = geUserPersonalService.checkBindPolicy(user.getCustomer().getUserID());
		if (!bindPolicy) {
			bindPolicy = geUserPersonalService.findPolicyByOrder(user.getCustomer().getUserID());
		}
		log.error("进入会员中心，进行身份验证结束:" + dateFormat(new Date()));
		getRequest().setAttribute("bindPolicy", bindPolicy);
	

		//log.error("进入会员中心，保单号排序:" + dateFormat(new Date()));
		// 按生效日期+保单号倒序排列
		/*Collections.sort(policyList, new Comparator<PolicyList>() {
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
		log.error("进入会员中心，保单号排序结束:" + dateFormat(new Date()));
		
		if(policyList.size() > 3){
			List<PolicyList> tPolicyList = policyList;
			policyList = new ArrayList<PolicyList>();
			for(int i = 0; i < 3; i++){
				policyList.add(tPolicyList.get(i));
			}
		}*/
		
		return SUCCESS;
	}
	
	
	
	/**查询理赔进度*/
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
