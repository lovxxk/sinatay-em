package cn.com.sinosoft.ebusiness.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.service.facade.InsuranceService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public class EPolicyQueryServiceSpringImpl extends InsuranceService{

	@Autowired
	private InsurancePolicyService insPolService;
	
	@Autowired
	private GeCodeService codeService;
	
	@Override
	protected InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InsuranceVerifiable handleRequest(InsuranceVerifiable ins) {
		//判断是否有权限
		TXInsurance txIns = (TXInsurance) ins;
		TranRequest tranReq = (TranRequest) txIns.getBusinessDatum().get(0);
		TranResponse tranRes = new TranResponse();
		txIns.getBusinessDatum().set(0, tranRes);
		
		String proposalContNo = tranReq.getProposalContNo();
		
		//根据投保单号查保单对比订单号。
		Map<String,String> params = new HashMap<String,String>();
		params.put("applicationNumber",proposalContNo);
		InsurancePolicy insPol = insPolService.findInsurancePolicyByQueryMap(params);
		
		//该保单不存在
		if(insPol == null){
			tranRes.setFlag("0");
			tranRes.setDesc("该保单不存在！");
			return txIns;
		}
		
		String orderId = tranReq.getOrderId();
		
		//取得保单对象关联的订单对象,不处理null
		OrderForm orderForm = insPol.getOrderForm();
		
		if(!orderId.equals(orderForm.getMerchantOrderNumber())){
			tranRes.setFlag("0");
			tranRes.setDesc("订单号和该保单所属的订单号不匹配！");
		}
		
		//取得电子保单路径前缀不应处理null
		String epolicyUrl = codeService.getCodeCoreRelation("","epolicyurl");
		tranRes.setUrl(epolicyUrl+"?orderId=" + orderId + "&amp;proposalContNo=" + proposalContNo);
		return txIns;
	}

	@Override
	protected InsuranceVerifiable savaInfoBeforeResponse(InsuranceVerifiable ins) {
		// TODO Auto-generated method stub
		return null;
	}

}
