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

import cn.com.sinosoft.ebusiness.infomanage.domain.InsuranceBenefitInfo;
import cn.com.sinosoft.ebusiness.infomanage.domain.ValueInfo;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.InsuranceBenefitCreater;
import cn.com.sinosoft.util.string.StringUtils;

public class InsuranceBenefitAction extends Struts2Action {
	private static final long serialVersionUID = 9003091484838238174L;
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description: 保险利益
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-17
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	// 日志信息
	private static Logger log = LoggerFactory
			.getLogger(InsuranceBenefitAction.class);
	// 保单号
	private String policyNo;
	// 保单险种号
	private String polNo;
	// 该保单险种信息
	private InsuranceBenefitInfo insuranceBenefitInfo;
	// 险种信息报文创建类
	@Autowired
	private InsuranceBenefitCreater insuranceBenefitCreater;
	// 接口辅组类
	@Autowired
	private WSClientHelper wsclientHelper;
	// 报文返回成功标志
	private String flag;
	// 返回报文描述
	private String desc;
	// 保险利益json字符串
	private String bnfJsonStr;

	// 返回保险利益信息
	public String inBnf() throws Exception {

		log.info("调用inBnf方法查询保险利益");
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("valueInfos", ValueInfo.class);

		// 判断是否为空
		if (StringUtils.isNotBlank(bnfJsonStr)) {
			JSONObject jsonObject = JSONObject.fromObject(bnfJsonStr);

			insuranceBenefitInfo = (InsuranceBenefitInfo) JSONObject.toBean(
					jsonObject, InsuranceBenefitInfo.class, classMap);
		} else {
			log.info("在InsuranceBenefitAction类inBnf方法中发生错误:错误原因-->1、保险利益(bnfJsonStr)参数为空！2、系统异常！");
			throw new RuntimeException("保险利益(bnfJsonStr)参数为空！");
		}
		return SUCCESS;
	}

	/**
	 * 校验是否有改险种是否有价值信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String check() throws Exception {
		log.info("系统调用InsuranceBenefitAction类中check方法验证该保单号是否有保险利益：policyNo-"
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
			docRequest = insuranceBenefitCreater.createXml(map);
			sRequest = wsclientHelper.doc2String(docRequest);

			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
			result = insuranceBenefitCreater.Xml2Object(docResponse);
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");

			// 获得价值形象
			List<InsuranceBenefitInfo> insuranceBenefitInfos = (List<InsuranceBenefitInfo>) result
					.get("insuranceBenefitInfos");
			if (insuranceBenefitInfos.size() == 0
					|| insuranceBenefitInfos == null) {
				flag = "0";
				desc = "无可查看数据，具体参见保险合同 ！";
			} else {
				// 取出该险种的价值信息
				for (InsuranceBenefitInfo tTnsuranceBenefitInfo : insuranceBenefitInfos) {
					if (tTnsuranceBenefitInfo.getPolNo().equals(polNo)) {

						insuranceBenefitInfo = tTnsuranceBenefitInfo;
						insuranceBenefitInfo.setPolicyNo(policyNo);

						JSONObject jsonObject = JSONObject
								.fromObject(insuranceBenefitInfo);
						bnfJsonStr = jsonObject.toString();
					}
				}

				// 如果该保单险种中在价值信息一个没有，就提示错误信息
				if (insuranceBenefitInfo == null) {
					flag = "0";
					desc = "无可查看数据，具体参见保险合同 ！";
				}
			}
		}else{
			log.info("在InsuranceBenefitAction类check方法中发生错误:错误原因-->1,保单号不能为空！2,系统异常！");
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

	public InsuranceBenefitInfo getInsuranceBenefitInfo() {
		return insuranceBenefitInfo;
	}

	public void setInsuranceBenefitInfo(
			InsuranceBenefitInfo insuranceBenefitInfo) {
		this.insuranceBenefitInfo = insuranceBenefitInfo;
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

	public String getBnfJsonStr() {
		return bnfJsonStr;
	}

	public void setBnfJsonStr(String bnfJsonStr) {
		this.bnfJsonStr = bnfJsonStr;
	}

}
