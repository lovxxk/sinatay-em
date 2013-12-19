package cn.com.sinosoft.ruleEngine.service.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.sinosoft.ruleEngine.domain.ActivityInputBOM;
import cn.com.sinosoft.ruleEngine.domain.ActivityResultBOM;
import cn.com.sinosoft.ruleEngine.service.facade.ActivityRuleService;

public class ActivityRuleServiceSpringImpl implements ActivityRuleService {

	@Override
	public List<ActivityResultBOM> getActivityInfo(
			List<ActivityInputBOM> activityInputList) {
		DroolsRuleServiceSpringImpl droolsRuleServiceSpringImpl = new DroolsRuleServiceSpringImpl();
		List<ActivityResultBOM> activityResultList = new ArrayList<ActivityResultBOM>();
		try{
			if (activityInputList.size() > 0) {
				for (int i = 0; i < activityInputList.size(); i++) {
					//i
					ActivityInputBOM inputBOM = activityInputList.get(i);
					ActivityResultBOM resultXOM = new ActivityResultBOM();
					ActivityResultBOM returnBOM = (ActivityResultBOM) droolsRuleServiceSpringImpl
							.executeRules(
									"cn.com.sinosoft.ebusiness.ruleEngine.ActivityRuleFow",
									inputBOM, resultXOM,
									"/drools_activity.properties");
					
					String str1 = returnBOM.getActivityPattern();//3,1
					String str2 = returnBOM.getnValue();//50,0
					String str3 = returnBOM.getItemID();//8a80820d33925e9a013392623bff0001,8a80c4f733c54ad90133c54ed5b10001
					String str4 = returnBOM.getIntervalDate();//01-Nov-2011 00:00:00,31-Dec-2011 23:59:59
					String[] ap = null;
					String[] nv = null;
					String[] it = null;
					String[] ind = null;
					if (str1 != null && !str1.equals("") && str2 != null
							&& !str2.equals("") && str3 != null && !str3.equals("")
							&& str4 != null && !str4.equals("")) {
						ap = str1.split(",");
						nv = str2.split(",");
						it = str3.split(",");
						ind = str4.split(",");
					}

					if (it != null && it.length > 0) {
						for (int m = 0; m < it.length; m++) {
							if (ap != null && ap.length > 0 && nv != null && nv.length > 0) {
								/*
								for (int k = 0; k < ap.length; k++) {
									ActivityResultBOM arBOM = new ActivityResultBOM();
									SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									arBOM.setActivityPattern(ap[k]);
									arBOM.setItemID(it[m]);
									arBOM.setnValue(nv[k]);
									arBOM.setStartDate(sdf1.format(new Date(ind[0])));
									arBOM.setEndDate(sdf1.format(new Date(ind[1])));
									arBOM.setDeptID(inputBOM.getDeptID());
									arBOM.setRiskCode(inputBOM.getRiskCode());
									arBOM.setActivityDate(sdf1.format(inputBOM.getActivityDate()));
									arBOM.setProposalNo(inputBOM.getProposalNo());
									activityResultList.add(arBOM);
								}
								*/
								ActivityResultBOM arBOM = new ActivityResultBOM();
								SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								arBOM.setActivityPattern(ap[m]);
								arBOM.setItemID(it[m]);
								arBOM.setnValue(nv[m]);
								arBOM.setStartDate(sdf1.format(new Date(ind[0])));
								arBOM.setEndDate(sdf1.format(new Date(ind[1])));
								arBOM.setDeptID(inputBOM.getDeptID());
								arBOM.setRiskCode(inputBOM.getRiskCode());
								arBOM.setActivityDate(sdf1.format(inputBOM.getActivityDate()));
								arBOM.setProposalNo(inputBOM.getProposalNo());
								arBOM.setProposalArea(inputBOM.getProposalArea());
								activityResultList.add(arBOM);
							}
						}
					}
				}
			}
			return activityResultList;
		}catch(Exception e){
			return null;
		}
	}
}
