package cn.com.sinosoft.ebusiness.service.spring;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.service.facade.InsuranceService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.sinatay.ReconciliationDetail;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public class DailyBalanceServiceSpringImpl extends InsuranceService {
	
	@Autowired
	private CoreBalanceService balanceService;
	
	@Override
	protected InsuranceVerifiable handleRequest(InsuranceVerifiable ins){
		TXInsurance txIns = (TXInsurance) ins;
		TranRequest tranReq = (TranRequest) txIns.getBusinessDatum().get(0);
		String billDate = tranReq.getBillDate();
		BigDecimal totalPrem = tranReq.getTotalPrem();
		List<ReconciliationDetail> detailList = tranReq.getDetailList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(billDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_YEAR, 1);
		String start = billDate;
		String end = sdf.format(cal.getTime());
		
		List resultList = balanceService.findCoreBalanceDate(txIns.getSource(),start,end);
		
		StringBuilder tradeMsg = new StringBuilder();
		boolean balanceFlag = true;
		
		if(detailList.size()>0&&resultList.size()>0){
			for(int i = detailList.size()-1 ; i >= 0 ; i--){
				ReconciliationDetail detail = detailList.get(i);
				for(int j = resultList.size()-1 ; j >= 0 ; j--){
					Object[] reulstDetail = (Object[])resultList.get(j);
					if(!detail.getPrtno().equals(reulstDetail[1])){
						continue;
					}else{
						//�ҵ�Ͷ����
						detailList.remove(i);
						resultList.remove(j);
					}
					if(detail.getPrem().compareTo((BigDecimal)reulstDetail[2])!=0){
						tradeMsg.append(String.format("Ͷ������%s�ı��Ѳ�һ��:���ı���%s,���˱���%s��",detail.getPrtno(),((BigDecimal)reulstDetail[2]).toString(),detail.getPrem().toString()));
						balanceFlag = false;
					}
					if(!detail.getAppFlag().equals(reulstDetail[3])){
						tradeMsg.append("Ͷ������").append(detail.getPrtno()).append("�ı���״̬��һ�¡�");
						balanceFlag = false;
					}
				}
			}
		}
		
		//��������Ͷ��������
		for(int i=0;i<detailList.size();i++){
			tradeMsg.append("���ĵļ�¼δ�ҵ�Ͷ������").append(detailList.get(i).getPrtno()).append("�Ķ������ݡ�");
			balanceFlag = false;
		}
		//����Ͷ��������
		for(int i=0;i<resultList.size();i++){
			Object[] reulstDetail = (Object[])resultList.get(i);
			tradeMsg.append("����Ͷ������").append(reulstDetail[1]).append("������δ���ڶ����������ҵ���");
			balanceFlag = false;
		}
		
		//��װTxins
		TranResponse tranRes = new TranResponse();
		if(!balanceFlag){
			tranRes.setFlag("0");
			tranRes.setDesc(tradeMsg.toString());
		}else{
			tranRes.setFlag("1");
			tranRes.setDesc(tradeMsg.append(start).append("���˳ɹ���").toString());
		}
		tranRes.setFlag(balanceFlag?"1":"0");
		txIns.getBusinessDatum().add(0,tranRes);
		return txIns;
	}

	@Override
	protected InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InsuranceVerifiable savaInfoAfterResponse(InsuranceVerifiable ins) {
		// TODO Auto-generated method stub
		return null;
	}

}
