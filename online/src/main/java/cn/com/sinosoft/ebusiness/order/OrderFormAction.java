package cn.com.sinosoft.ebusiness.order;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.OrderFormService;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteMainService;
import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.util.security.springSecurity.SpringSecurityUtils;

public class OrderFormAction extends Struts2Action {
	
	@Autowired
	public OrderFormService orderFormService;
	
	@Autowired
	public QuoteMainService quoteMainService;
	
	@Autowired
	public InsurancePolicyService insurancePolicyService;
	
	@Autowired
	private GeCodeService geCodeService;// 数据字典service
	
	public OrderForm orderForm;
	
	public QuoteMain quoteMain;
	
	//订单
	public List<OrderForm> orders;
	
	public String message;
	
	public InsurancePolicy insurancePolicy;
	
	public String flag;
	
	public long pageCount;
	
	public Integer status;
	
	public String orderSerialNo;
	/**
	 * 获取所有订单信息
	 * @return
	 */
	public String orders() {
		/*
		String flag = getRequest().getParameter("flag");
		if ("1".equals(flag)) {
			 message = "订单删除成功";
		} else if ("-1".equals(flag)){
			message = "操作无效";
		} else if ("0".equals(flag)) {
			message = "订单删除失败，只能删除'未支付'与'未核保'的订单信息";
		} else if (flag == null || flag == "") {
			message = "";
		}
		flag = "";
		*/
		return SUCCESS;
	}
	
	
	/**
	 * 获取所有订单信息
	 * @return
	 */
	public String ordersAjax() {
		LoginUserInfo loginUser = SpringSecurityUtils.getCurrentUser();
		
		if (loginUser != null) {
			GeUserPersonal userPersonal =  loginUser.getCustomer();
			
			Object obj = getRequest().getParameter("page");
			String orderSerialNo = getRequest().getParameter("orderSerialNo");
			pageNo = obj == null ? 0 : new Integer((String)obj);
			Page page = orderFormService.getOrderFormsByUserId(userPersonal.getUserID(), status, orderSerialNo, pageNo, 5);
			orders = page.getResult();
			pageCount = page.getTotalPageCount();
		}
		return SUCCESS;
	}
	public String deleteOrder() {
		String serialNo = getRequest().getParameter("serialNo");
		OrderForm orderForm = orderFormService.getOrderFormBySerialNo(serialNo);
		flag = "";
		try {
			if (orderForm == null) 
				flag = "-1";
			if (orderForm != null && !orderForm.getOrderStatus().equals(OrderStatus.UNPAID.getValue()) && !orderForm.getOrderStatus().equals(OrderStatus.INPUTED_QUOTE_INFO.getValue())) 
				flag = "0";
			if(orderForm != null && !"0".equals(flag)){
				orderFormService.deleteByOrderFormSerialNumber(orderForm.getOrderSerialNumber());
				flag = "1";
			}
		} catch (Exception e) {
			flag = "-1";
		}finally{
			super.render(flag,"application/json;charset=GBK");
		}
		return NONE;
	}
	/**
	 * 获取订单的详细信息
	 * @return
	 */
	public String orderDetail() {
		//订单主键
		String serialNo = getRequest().getParameter("id");
		//订单信息
		orderForm = orderFormService.getOrderFormForKey(serialNo);
		
//		quoteMain = quoteMainService.getQuoteMainByQuoteNo(serialNo);
		
//		insurancePolicy = insurancePolicyService.getInsurancePolicyByQuoteNo(quoteMain.getQuoteNo());
		
		message = "";
		//保单信息
//		insurancePolicy = orderForm.getInsurancePolicy();
//		//投保人信息
//		insuranceApplicant = insurancePolicy.getInsuranceApplicant();
//		//被保人
//		insureds = insurancePolicy.getInsureds();
//		//受益人
//		beneficiaries = insurancePolicy.getBeneficiaries();
//		//保险责任
//		insurancePolicyLiabilities = insurancePolicy.getInsurancePolicyLiabilities();
		
		/**获取关系描述*/
		List<GeCode> insRelaToAppList = geCodeService.findAllByGeCodeType("applicantAndInsured");
		super.getRequest().setAttribute("insRelaToAppList", insRelaToAppList);
		
		/**获取性别描述*/
		List<GeCode> sexList = geCodeService.findAllByGeCodeType("Sex");//被保人/受益人性别
		super.getRequest().setAttribute("sexList",sexList);
		
		/**获取证件类型*/
		List<GeCode> idTypeList = geCodeService.findAllByGeCodeType("IdentifyType");//被保人/受益人性别
		super.getRequest().setAttribute("idTypeList",idTypeList);
		
		return SUCCESS;
	}

	/**
	 * 保存保单生效日
	 */
	public String saveInitEffectStartDate() {
		String effect_start = getRequest().getParameter("effect_start");
		String serialNo = getRequest().getParameter("serialNo");
		orderForm = orderFormService.getOrderFormBySerialNo(serialNo);
		
		orderFormService.updateInsurancePolicyEffectDate(orderForm, effect_start);
		
		return SUCCESS;
	}

	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}

	public void setOrders(List<OrderForm> orders) {
		this.orders = orders;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
	}
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public List<OrderForm> getOrders() {
		return orders;
	}


	public Integer getOrderStatus() {
		return status;
	}


	public void setOrderStatus(Integer orderStatus) {
		this.status = orderStatus;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getOrderSerialNo() {
		return orderSerialNo;
	}


	public void setOrderSerialNo(String orderSerialNo) {
		this.orderSerialNo = orderSerialNo;
	}
	
}
