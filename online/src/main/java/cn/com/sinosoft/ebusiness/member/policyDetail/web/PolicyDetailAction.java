package cn.com.sinosoft.ebusiness.member.policyDetail.web;

import ins.framework.web.Struts2Action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.infomanage.domain.Edor;
import cn.com.sinosoft.ebusiness.infomanage.domain.Policy;
import cn.com.sinosoft.ebusiness.infomanage.domain.Risk;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.EdorCreater;
import cn.com.sinosoft.ebusiness.xmltransfer.PolicyDetailCreater;
import cn.com.sinosoft.util.string.StringUtils;

public class PolicyDetailAction extends Struts2Action implements Serializable {
	/**
	 * @ProjectName:online
	 * @Package: cn.com.sinosoft.ebusiness.infomanage.action
	 * @ClassName: PolicyDetailAction
	 * @Description: 保单详细页面显示action
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-5
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	private static final long serialVersionUID = -6811177498600452905L;
	// 日志信息
	private static Logger log = LoggerFactory
			.getLogger(PolicyDetailAction.class);
	// 保单号
	private String policyNo;
	// 保单详细信息
	private Policy policy;
	// 保全变更信息
	private List<Edor> edors = new ArrayList<Edor>();;
	// 页面显示部分保全信息
	// 险种信息字符串
	private String risksStr;
	// 保单业务处理类
	@Autowired
	private PolicyDetailCreater policyDetailCreater;
	@Autowired
	private WSClientHelper wsclientHelper;
	@Autowired
	private EdorCreater edorCreater;
	private String flag;
	private String desc;
	private String userMobile;
	private GeUserPersonal customer;

	private boolean receiveFlag;

	/**
	 * 是否显示险种列表
	 */
	private String showSelect = "0";

	@SuppressWarnings("unchecked")
	public String viewMyPolicyDetail() throws Exception {
		if (StringUtils.isNotBlank(policyNo)) {

			// 设置用户移动电话
			customer = (GeUserPersonal) super.getSession().getAttribute(
					"geUserPersonal");
			log.info("用户名：" + customer.getUserName() + "查看保单详情,保单号为："
					+ policyNo);
			userMobile = customer.getMobilePhone();

			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String, Object> result;
			// 接收报文信息
			Map<String, Object> map = new HashMap<String, Object>();

			// 保单详情信息
			map.put("policyNo", policyNo);
			// 第一笔报文请求

			docRequest = policyDetailCreater.createXml(map);
			log.info("保单详情第一笔请求报文docRequest......:" + docRequest);
			System.out.println("保单详情第一笔请求报文docRequest......:" + docRequest);

			sRequest = wsclientHelper.doc2String(docRequest);
			log.info("保单详情第一笔请求报文sRequest......:" + sRequest);
			System.out.println("保单详情第一笔请求报文sRequest......:" + sRequest);

			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);

			result = policyDetailCreater.Xml2Object(docResponse);
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");

			if (flag.equals("1")) {
				policy = (Policy) result.get("policy");

				// 增加生效时间的判断
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				if (sdf.format(new Date()).compareTo(policy.getValiDate()) < 0) {
					receiveFlag = false;
				} else {
					receiveFlag = true;
				}

				// 获取保全变更记录
				// 第二笔报文请求
				docRequest = edorCreater.createXml(map);
				log.info("保单详情第二笔请求报文docRequest......:" + docRequest);
				System.out.println("保单详情第二笔请求报文docRequest......:" + docRequest);

				sRequest = wsclientHelper.doc2String(docRequest);
				log.info("保单详情第二笔请求报文sRequest......:" + sRequest);
				System.out.println("保单详情第二笔请求报文sRequest......:" + sRequest);

				sResponse = wsclientHelper.submitGBK(sRequest);
				docResponse = wsclientHelper.string2Doc(sResponse);
				result = edorCreater.Xml2Object(docResponse);

				flag = (String) result.get("flag");
				desc = (String) result.get("desc");

				if (flag.equals("1")) {
					policy.setEdors((List<Edor>) result.get("edors"));
					// 获得保全变更信息
					edors = policy.getEdors();
					// 实现排序
					Collections.sort(edors);
					// 保存险种json 主要用户弹出框显示
					// 如果有相同险种，不需要增加 ,取出集合中重复的某个字段元素,显示的只是有效的
					List<Risk> selectRisk = removeListRepeatingObject(policy
							.getRisks());
					if (selectRisk.size() == 0 || selectRisk == null) {
						showSelect = "0";
					} else {
						showSelect = "1";
					}
					JSONArray jsonArray = JSONArray.fromObject(selectRisk);
					risksStr = jsonArray.toString();
				}
			}else{
				log.info("在PolicyDetailAction类viewMyPolicyDetail方法中发生错误:错误原因-->1,保单号不能为空！2,系统异常！");
				throw new RuntimeException("保单号为空！");
			}
		}
		return SUCCESS;
	}

	/**
	 * 移出险种集合中重复的险种
	 * 
	 * @param risks
	 * @return
	 */
	private List<Risk> removeListRepeatingObject(List<Risk> risks) {
		Map<String, Risk> risksMap = new HashMap<String, Risk>();
		List<Risk> riskList = new ArrayList<Risk>();
		for (Risk risk : risks) {
			if (risk.getState().equals("有效")) {
				risksMap.put(risk.getRiskCode(), risk);
			}
		}
		for (Entry<String, Risk> entry : risksMap.entrySet()) {
			riskList.add(entry.getValue());
		}
		return riskList;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getRisksStr() {
		return risksStr;
	}

	public void setRisksStr(String risksStr) {
		this.risksStr = risksStr;
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

	public List<Edor> getEdors() {
		return edors;
	}

	public void setEdors(List<Edor> edors) {
		this.edors = edors;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public String getShowSelect() {
		return showSelect;
	}

	public void setShowSelect(String showSelect) {
		this.showSelect = showSelect;
	}

	public boolean isReceiveFlag() {
		return receiveFlag;
	}

	public void setReceiveFlag(boolean receiveFlag) {
		this.receiveFlag = receiveFlag;
	}

}
