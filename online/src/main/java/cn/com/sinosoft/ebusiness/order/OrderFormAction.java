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
	private GeCodeService geCodeService;// �����ֵ�service
	
	public OrderForm orderForm;
	
	public QuoteMain quoteMain;
	
	//����
	public List<OrderForm> orders;
	
	public String message;
	
	public InsurancePolicy insurancePolicy;
	
	public String flag;
	
	public long pageCount;
	
	public Integer status;
	
	public String orderSerialNo;
	/**
	 * ��ȡ���ж�����Ϣ
	 * @return
	 */
	public String orders() {
		/*
		String flag = getRequest().getParameter("flag");
		if ("1".equals(flag)) {
			 message = "����ɾ���ɹ�";
		} else if ("-1".equals(flag)){
			message = "������Ч";
		} else if ("0".equals(flag)) {
			message = "����ɾ��ʧ�ܣ�ֻ��ɾ��'δ֧��'��'δ�˱�'�Ķ�����Ϣ";
		} else if (flag == null || flag == "") {
			message = "";
		}
		flag = "";
		*/
		return SUCCESS;
	}
	
	
	/**
	 * ��ȡ���ж�����Ϣ
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
	 * ��ȡ��������ϸ��Ϣ
	 * @return
	 */
	public String orderDetail() {
		//��������
		String serialNo = getRequest().getParameter("id");
		//������Ϣ
		orderForm = orderFormService.getOrderFormForKey(serialNo);
		
//		quoteMain = quoteMainService.getQuoteMainByQuoteNo(serialNo);
		
//		insurancePolicy = insurancePolicyService.getInsurancePolicyByQuoteNo(quoteMain.getQuoteNo());
		
		message = "";
		//������Ϣ
//		insurancePolicy = orderForm.getInsurancePolicy();
//		//Ͷ������Ϣ
//		insuranceApplicant = insurancePolicy.getInsuranceApplicant();
//		//������
//		insureds = insurancePolicy.getInsureds();
//		//������
//		beneficiaries = insurancePolicy.getBeneficiaries();
//		//��������
//		insurancePolicyLiabilities = insurancePolicy.getInsurancePolicyLiabilities();
		
		/**��ȡ��ϵ����*/
		List<GeCode> insRelaToAppList = geCodeService.findAllByGeCodeType("applicantAndInsured");
		super.getRequest().setAttribute("insRelaToAppList", insRelaToAppList);
		
		/**��ȡ�Ա�����*/
		List<GeCode> sexList = geCodeService.findAllByGeCodeType("Sex");//������/�������Ա�
		super.getRequest().setAttribute("sexList",sexList);
		
		/**��ȡ֤������*/
		List<GeCode> idTypeList = geCodeService.findAllByGeCodeType("IdentifyType");//������/�������Ա�
		super.getRequest().setAttribute("idTypeList",idTypeList);
		
		return SUCCESS;
	}

	/**
	 * ���汣����Ч��
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
