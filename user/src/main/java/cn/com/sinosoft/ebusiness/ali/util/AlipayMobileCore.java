package cn.com.sinosoft.ebusiness.ali.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.com.sinosoft.ebusiness.ali.config.AlipayConfig;
import cn.com.sinosoft.ebusiness.ali.util.AlipayCore;
import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;

public class AlipayMobileCore {

	public static String getTokenReqData(String subject, String out_trade_no, String total_fee ){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("direct_trade_create_req");
		root.addElement("subject").setText(subject);
		root.addElement("out_trade_no").setText(out_trade_no);
		root.addElement("total_fee").setText(total_fee);
		
		
		PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
		Properties properties = PropertyFileUtils.getProperties();
		String sinatayUrl = properties.getProperty("sinatayUrl");
		String notify_url = sinatayUrl + AlipayConfig.mobile_notify_url;
		String return_url = sinatayUrl + AlipayConfig.mobile_call_back_url;
		
		root.addElement("seller_account_name").setText(AlipayConfig.mobile_seller_email);
		root.addElement("call_back_url").setText(return_url);
		root.addElement("notify_url").setText(notify_url);
		return document.asXML().split("\n")[1];
	}
	
	public static String[] parseTokenRes(String result_token){
		System.out.println("手机支付-授权接口返回参数："+result_token);
		String[] result = new String[2];
		try{
			Map<String, String> parameter = readParameterToMap(result_token);
			if (parameter.get("res_data") != null ){
				//授权接口 返回成功
				String sign = parameter.get("sign");
				parameter.remove("sign");
				String res_data = URLDecoder.decode(parameter.get("res_data"),"UTF-8");
				System.out.println("手机-支付宝-返回授权码："+res_data);
				parameter.put("res_data",res_data);
				String mysign = AlipayCore.buildMysignForMobile(parameter);
				System.out.println("验证返回参数的签名--sign:"+sign+"||mysign:"+mysign+"||sign.equals(mysign):"+sign.equals(mysign));
				if (sign.equals(mysign)){
					Document data = DocumentHelper.parseText(res_data);
					result[1] = data.getRootElement().elementText("request_token");
					result[0] = "1";
				} else {
					result[0] = "0";
					result[1] = "验证返回参数签名不一致！";
				}
			}else if (parameter.get("res_error") != null ){
				String res_error = URLDecoder.decode(parameter.get("res_error"),"UTF-8");
				System.out.println("手机-支付宝-返回错误："+res_error);
				Document error = DocumentHelper.parseText(res_error);
				result[0] = "0";
				result[1] = error.getRootElement().elementText("detail");
			}else{
				result[0] = "0";
				result[1] = "参数有误！";
			}
		}catch (Exception e) {
			result[0] = "-1";
			result[1] = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static Map<String, String> readParameterToMap(String result_token){
		/*
		 * 正确
		 * partner=2088101000137799&req_id=1282889689836&res_data=<?xml version="1.0" encoding="utf-8"?><direct_trade_create_res><request_token>20100830e8085e3e0868a466b822350ede5886e8</request_token></direct_trade_create_res>&sec_id=MD5&service=alipay.wap.trade.create.direct&v=2.0&sign=72a64fb63f0b54f96b10cefb69319e8a
		 * 
		 * 错误
		 * partner=208810100013779&req_id=1282889689836&res_error=<?xml version="1.0" encoding="utf-8"?><err><code>0005</code><sub_code>0005</sub_code><msg>partner illegal</msg><detail>合作伙伴没有开通接口访问权限</detail></err>&sec_id=0001&service=alipay.wap.trade.create.direct&v=2.0
		 */
		if (result_token.indexOf("\n") == 0) {
			result_token = result_token.substring(1);
		}
		Map<String, String> parameter = new HashMap<String, String>();
		String[] result = result_token.split("&");
		for (String str : result) {
			String[] para = str.split("=");
			parameter.put(para[0], para[1]);
		}
		return parameter;
	}
	
	public static String create_wap_pay(String token) {
		String req_data = "<auth_and_execute_req><request_token>"+token+"</request_token></auth_and_execute_req>";
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("service", AlipayConfig.mobile_pay_service);
		parameter.put("format", AlipayConfig.mobile_format);
		parameter.put("v", AlipayConfig.mobile_v);
		parameter.put("partner", AlipayConfig.mobile_partner);
		parameter.put("sec_id", AlipayConfig.sign_type);
		parameter.put("req_data", req_data);
		//生成签名结果
        String mysign = AlipayCore.buildMysignForMobile(parameter);
        //签名结果与签名方式加入请求提交参数组中
        parameter.put("sign", mysign);
        String strButtonName = "确认";
        return buildForm(parameter, AlipayConfig.mobile_https_alipay_url, "get", strButtonName);
	}
	
	/**
     * 构造提交表单HTML数据
     * @param sParaTemp 请求参数数组
     * @param gateway 网关地址
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildForm(Map<String, String> sParaTemp, String gateway, String strMethod,
                                   String strButtonName) {
        //待请求参数数组
        List<String> keys = new ArrayList<String>(sParaTemp.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + gateway
                      + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sParaTemp.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
	
	public static void main(String[] args) throws Exception {
		String str = "\nres_data=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22utf-8%22%3F%3E%3Cdirect_trade_create_res%3E%3Crequest_token%3E20130328b85feea832d5a31b8b9dcbfeae1cf281%3C%2Frequest_token%3E%3C%2Fdirect_trade_create_res%3E&service=alipay.wap.trade.create.direct&sec_id=MD5&partner=2088201564809153&req_id=1364452602444&sign=89e2a6c19efa290b4c80efcf679c9320&v=2.0";
//		String str = "partner=2088101568349711&req_id=1364261544684&res_error=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22utf-8%22%3F%3E%3Cerr%3E%3Ccode%3E0005%3C%2Fcode%3E%3Csub_code%3E0005%3C%2Fsub_code%3E%3Cmsg%3Epartner+illegal%3C%2Fmsg%3E%3Cdetail%3E%E5%90%88%E4%BD%9C%E4%BC%99%E4%BC%B4%E6%B2%A1%E6%9C%89%E5%BC%80%E9%80%9A%E6%8E%A5%E5%8F%A3%E8%AE%BF%E9%97%AE%E6%9D%83%E9%99%90%3C%2Fdetail%3E%3C%2Ferr%3E&sec_id=MD5&service=alipay.wap.trade.create.direct&v=2.0";
		String[] result = parseTokenRes(str);
		System.out.println(result[0]);
		System.out.println(result[1]);
		String sHtmlText = AlipayMobileCore.create_wap_pay(result[1]);
		System.out.println(sHtmlText);
	}
}