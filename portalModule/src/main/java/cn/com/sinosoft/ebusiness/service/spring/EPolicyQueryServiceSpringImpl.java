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
		//�ж��Ƿ���Ȩ��
		TXInsurance txIns = (TXInsurance) ins;
		TranRequest tranReq = (TranRequest) txIns.getBusinessDatum().get(0);
		TranResponse tranRes = new TranResponse();
		txIns.getBusinessDatum().set(0, tranRes);
		
		String proposalContNo = tranReq.getProposalContNo();
		
		//����Ͷ�����Ų鱣���Աȶ����š�
		Map<String,String> params = new HashMap<String,String>();
		params.put("applicationNumber",proposalContNo);
		InsurancePolicy insPol = insPolService.findInsurancePolicyByQueryMap(params);
		
		//�ñ���������
		if(insPol == null){
			tranRes.setFlag("0");
			tranRes.setDesc("�ñ��������ڣ�");
			return txIns;
		}
		
		String orderId = tranReq.getOrderId();
		
		//ȡ�ñ�����������Ķ�������,������null
		OrderForm orderForm = insPol.getOrderForm();
		
		if(!orderId.equals(orderForm.getMerchantOrderNumber())){
			tranRes.setFlag("0");
			tranRes.setDesc("�����ź͸ñ��������Ķ����Ų�ƥ�䣡");
		}
		
		//ȡ�õ��ӱ���·��ǰ׺��Ӧ����null
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
