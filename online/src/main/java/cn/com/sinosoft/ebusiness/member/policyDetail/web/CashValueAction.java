package cn.com.sinosoft.ebusiness.member.policyDetail.web;

import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.infomanage.domain.CashValueInfo;
import cn.com.sinosoft.ebusiness.infomanage.domain.ValueInfo;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.CashValueCreater;
import cn.com.sinosoft.util.string.StringUtils;

public class CashValueAction extends Struts2Action {

	private static final long serialVersionUID = -4000863691461355866L;
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description:
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-17
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	// 日志信息
	private static Logger log = LoggerFactory.getLogger(CashValueAction.class);
	// 保单号
	private String policyNo;
	// 保单险种号
	private String polNo;
	// 该保单险种价值信息
	private CashValueInfo cashValueInfo;
	@Autowired
	private CashValueCreater cashValueCreater;
	@Autowired
	private WSClientHelper wsclientHelper;
	private String flag;
	private String desc;
	private String jsonCashValueInfoStr;

	/**
	 * 业务：获取现金价值json字符串转换为现金价值对象
	 * @return
	 * @throws Exception
	 */
	public String cashValue() throws Exception {
		// 判断是否为空
		if (StringUtils.isNotBlank(jsonCashValueInfoStr)) {

			log.info("系统调用CashValueAction类中cashValue方法查询现金价值");
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("valueInfos", ValueInfo.class);

			JSONObject jsonObject = JSONObject.fromObject(jsonCashValueInfoStr);
			cashValueInfo = (CashValueInfo) JSONObject.toBean(jsonObject,
					CashValueInfo.class, classMap);
			
		}else{
			
			log.info("在CashValueAction类inBnf方法中发生错误:错误原因-->1、现金价值(jsonCashValueInfoStr)参数为空！2、系统异常！");
			throw new RuntimeException("保险利益(bnfJsonStr)参数为空！");
			
		}
		return SUCCESS;

	}

	/**
	 * 业务：校验是否有该险种是否有价值信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String check() throws Exception {
		log.info("系统调用CashValueAction类中check方法验证该保单号是否有现金价值：policyNo-"
				+ policyNo);
		// 判断是否有保单号
		if (StringUtils.isNotBlank(policyNo)) {
			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String, Object> result;
			// 接收报文信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("policyNo", policyNo);
			docRequest = cashValueCreater.createXml(map);
			sRequest = wsclientHelper.doc2String(docRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
			result = cashValueCreater.Xml2Object(docResponse);
			
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");
			
			// 获得价值信息
			List<CashValueInfo> cashValueInfos = (List<CashValueInfo>) result
					.get("cashValueInfos");
			if (cashValueInfos.size() == 0 || cashValueInfos == null) {
				flag = "0";
				desc = "无可查看数据，具体参见保险合同 ！";
			} else {
				// 取出该险种的价值信息
				for (CashValueInfo cashInfo : cashValueInfos) {
					if (cashInfo.getPolNo().equals(polNo)) {
						cashValueInfo = cashInfo;
						cashValueInfo.setPolicyNo(policyNo);

						JSONObject jsonObject = JSONObject
								.fromObject(cashValueInfo);
						jsonCashValueInfoStr = jsonObject.toString();
					}
				}
				// 如果该保单险种中在价值信息一个没有，就提示错误信息
				if (cashValueInfo == null) {
					flag = "0";
					desc = "无可查看数据，具体参见保险合同 ！";
				}
			}
		} else {
			log.info("在CashValueAction类check方法中发生错误:错误原因-->1,保单号不能为空！2,系统异常！");
			throw new RuntimeException("保单号为空！");
		}
		return SUCCESS;

	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolNo() {
		return polNo;
	}

	public void setPolNo(String polNo) {
		this.polNo = polNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public CashValueInfo getCashValueInfo() {
		return cashValueInfo;
	}

	public void setCashValueInfo(CashValueInfo cashValueInfo) {
		this.cashValueInfo = cashValueInfo;
	}

	public String getJsonCashValueInfoStr() {
		return jsonCashValueInfoStr;
	}

	public void setJsonCashValueInfoStr(String jsonCashValueInfoStr) {
		this.jsonCashValueInfoStr = jsonCashValueInfoStr;
	}

}
