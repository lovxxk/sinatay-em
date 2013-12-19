package cn.com.sinosoft.ebusiness.claim.action;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.businessModule.bindPolicy.service.facade.BindPolicyService;
import cn.com.sinosoft.ebusiness.claim.domain.ClaimDetail;
import cn.com.sinosoft.ebusiness.claim.domain.ClaimPayDetail;
import cn.com.sinosoft.ebusiness.claim.domain.ClaimProcessList;
import cn.com.sinosoft.ebusiness.infomanage.action.PolicyListAction;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.tools.BT;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.ClaimDetailCreater;
import cn.com.sinosoft.ebusiness.xmltransfer.ClaimListCreater;

/**
 * 理赔进度查询Action
 * @author zhuxuyang
 * 2013.09.10
 */
public class ClaimProcessAction extends Struts2Action {
	private static final long serialVersionUID = 1L;
	
	public GeUserPersonal customer;
	
	@Autowired
	private BindPolicyService bindPolicyService;
	@Autowired
	private WSClientHelper wsclientHelper;
	@Autowired
	private ClaimListCreater claimListCreater;
	@Autowired
	private ClaimDetailCreater claimDetailCreater;
	
	// 日志信息
	private static Logger log = LoggerFactory.getLogger(ClaimProcessAction.class);

	String hasPolicy;
	String hasClaims;
	List<ClaimProcessList> listClaimProcessList;
	ClaimDetail claimDetail;
	
	/**
	 * 理赔进度查询初始化
	 * @return
	 */
	public String initClaimProcess(){
		customer = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		if(customer == null){
			getRequest().setAttribute("returnUrl", getRequest().getRequestURI());
			return "login";
		}
		
		log.info("用户：" + customer.getUserAccount() + "查询理赔列表.");
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		if(listBindPolicy.size() == 0){
			hasPolicy = "N";
		}else{
			hasPolicy = "Y";
		}
		listClaimProcessList = findClaimProcessList(listBindPolicy);
		if(listClaimProcessList.size() == 0){
			hasClaims = "N";
		}else{
			hasClaims = "Y";
		}
		return SUCCESS;
	}
	
	/**
	 * 理赔详情查询
	 * @return
	 */
	public String claimDetail(){
		log.info("查询" + claimDetail.getClaimNumber() + "理赔详情.");
		findClaimDetail();
		
		return SUCCESS;
	}
	
	/**
	 * 通过绑定保单列表查询理赔进度列表
	 * xxxxxxx
	 * @param listBindPolicy
	 * @return
	 */
	private List<ClaimProcessList> findClaimProcessList(List<BindPolicy> listBindPolicy){
		List<ClaimProcessList> list = new ArrayList<ClaimProcessList>();
		if(listBindPolicy.size() == 0){
			return list;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("listBindPolicy", listBindPolicy);
		try{
			Document docRequest = claimListCreater.createXml(map);
			String sRequest = wsclientHelper.doc2String(docRequest);
			String sResponse = wsclientHelper.submitGBK(sRequest);
			Document docResponse = wsclientHelper.string2Doc(sResponse);
			if(BT.isSuccess(docResponse)){
				list = claimListCreater.Xml2Object(docResponse);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 通过赔案号查询理赔详情
	 */
	private void findClaimDetail(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("CLMNo", claimDetail.getClaimNumber());
		try{
			Document docRequest = claimDetailCreater.createXml(map);
			String sRequest = wsclientHelper.doc2String(docRequest);
			String sResponse = wsclientHelper.submitGBK(sRequest);
			Document docResponse = wsclientHelper.string2Doc(sResponse);
			if(BT.isSuccess(docResponse)){
				claimDetail = claimDetailCreater.Xml2Object(docResponse);
			}else{
				claimDetail.setListClaimPayDetail(new ArrayList<ClaimPayDetail>());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<ClaimProcessList> getListClaimProcessList() {
		return listClaimProcessList;
	}

	public void setListClaimProcessList(List<ClaimProcessList> listClaimProcessList) {
		this.listClaimProcessList = listClaimProcessList;
	}

	public ClaimDetail getClaimDetail() {
		return claimDetail;
	}

	public void setClaimDetail(ClaimDetail claimDetail) {
		this.claimDetail = claimDetail;
	}

	public static void main(String[] args){
		BigDecimal money = new BigDecimal(0);
		System.out.println(money.compareTo(new BigDecimal(0)));
	}

	public String getHasPolicy() {
		return hasPolicy;
	}

	public void setHasPolicy(String hasPolicy) {
		this.hasPolicy = hasPolicy;
	}

	public String getHasClaims() {
		return hasClaims;
	}

	public void setHasClaims(String hasClaims) {
		this.hasClaims = hasClaims;
	}
}
