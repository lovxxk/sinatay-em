<%@page import="cn.com.sinosoft.ebusiness.payment.web.PaymentAction"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus"%>
<%@page import="cn.com.sinosoft.businessModule.order.domain.OrderForm"%>
<%@page import="cn.com.sinosoft.ebusiness.ali.util.AlipayNotify"%>
<%
/* *
 ���ܣ�֧�����������첽֪ͨҳ��
 �汾��3.3
 ���ڣ�2012-08-17
 ˵����
 ���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 �ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���

 //***********ҳ�湦��˵��***********
 ������ҳ���ļ�ʱ�������ĸ�ҳ���ļ������κ�HTML���뼰�ո�
 ��ҳ�治���ڱ������Բ��ԣ��뵽�������������ԡ���ȷ���ⲿ���Է��ʸ�ҳ�档
 ��ҳ����Թ�����ʹ��д�ı�����logResult���ú�����com.alipay.util�ļ��е�AlipayNotify.java���ļ���
 ���û���յ���ҳ�淵�ص� success ��Ϣ��֧��������24Сʱ�ڰ�һ����ʱ������ط�֪ͨ
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%
	Logger log = LoggerFactory.getLogger(PaymentAction.class);
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
		//valueStr = new String(valueStr.getBytes("GBK"), "gbk");
		params.put(name, valueStr);
	}
	//��ȡ֧������֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)//
	//�̻�������
	String out_trade_no = request.getParameter("out_trade_no");
	
	String body = request.getParameter("body");
	
	String subject = request.getParameter("subject");
	
	//֧�������׺�	String trade_no = request.getParameter("trade_no");

	//����״̬
	String trade_status = request.getParameter("trade_status");

	//��ȡ֧������֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���Ͻ����ο�)//
	boolean verify_result = AlipayNotify.verify(params);
// 	OrderForm orderForm = orderFormService.findOrderFormByOrderFormSerialNumber(out_trade_no);
	log.error("�첽֪ͨ��֤������start" + verify_result);
	if(verify_result){//��֤�ɹ�
		//////////////////////////////////////////////////////////////////////////////////////////
		//������������̻���ҵ���߼��������

		//�������������ҵ���߼�����д�������´�������ο�������
		
		if(trade_status.equals("TRADE_FINISHED")){
// 			orderForm.setOrderStatus(OrderStatus.PAID.getValue());
			//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				
			//ע�⣺
			//���ֽ���״ֻ̬����������³���
			//1����ͨ����ͨ��ʱ���ˣ���Ҹ���ɹ���
			//2����ͨ�˸߼���ʱ���ˣ��Ӹñʽ��׳ɹ�ʱ�����𣬹���ǩԼʱ�Ŀ��˿�ʱ�ޣ��磺���������ڿ��˿һ�����ڿ��˿�ȣ���
		} else if (trade_status.equals("TRADE_SUCCESS")){
// 			orderForm.setOrderStatus(OrderStatus.PAID.getValue());
			//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				
			//ע�⣺
			//���ֽ���״ֻ̬��һ������³��֡�����ͨ�˸߼���ʱ���ˣ���Ҹ���ɹ���
		}

		//�������������ҵ���߼�����д�������ϴ�������ο�������
			
		out.println("success");	//�벻Ҫ�޸Ļ�ɾ��

		//////////////////////////////////////////////////////////////////////////////////////////
	}else{//��֤ʧ��
// 		orderForm.setOrderStatus(OrderStatus.PAYMENTFAILURE.getValue());
		out.println("fail");
	}
// 	orderForm.setUpdateTime(new Date());
// 	orderFormService.updateOrderForm(orderForm);
%>
