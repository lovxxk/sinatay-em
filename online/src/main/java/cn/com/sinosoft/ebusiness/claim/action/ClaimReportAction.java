/**
 * 
 */
package cn.com.sinosoft.ebusiness.claim.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.hospital.service.facade.HospitalService;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.ClaimReportCreater;
import cn.com.sinosoft.util.encode.JsonBinder;

import ins.framework.web.Struts2Action;

/**
 * 理赔报案Action
 * @author zhuxuyang
 * 2013.09.05
 */
public class ClaimReportAction extends Struts2Action {
	private static final long serialVersionUID = 1L;
	
	private String reportName;
	private String reportPhone;
	private String reportRelation;
	private String accidentName;
	private String accidentSex;
	private String accidentBirth;
	private String accidentBirthYear;
	private String accidentBirthMonth;
	private String accidentBirthDay;
	private String accidentIdType;
	private String accidentId;
	private String accidentDate;
	private String accidentDateYear;
	private String accidentDateMonth;
	private String accidentDateDay;
	private String province;
	private String city;
	private String county;
	private String other;
	private String accidentReason;
	private String accidentStatus;
	private String accidentDo;
	private String hospital;
	private String diagnose;
	private List<String> claimType;
	
	@Autowired
	private WSClientHelper wsclientHelper;
	@Autowired
	private ClaimReportCreater claimReportCreater;
	@Autowired
	private HospitalService hospitalService;
	
	private String flag;
	private String desc;
	
	public String reportClaim(){
		String sClaimType = "";
		Document docRequest;
		Document docResponse;
		String sRequest;
		String sResponse;
		Map<String,String> result;
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(accidentBirthMonth.length() == 1){
			accidentBirthMonth = "0" + accidentBirthMonth;
		}
		if(accidentBirthDay.length() == 1){
			accidentBirthDay = "0" + accidentBirthDay;
		}
		if(accidentDateMonth.length() == 1){
			accidentDateMonth = "0" + accidentDateMonth;
		}
		if(accidentDateDay.length() == 1){
			accidentDateDay = "0" + accidentDateDay;
		}
		accidentBirth = accidentBirthYear + accidentBirthMonth + accidentBirthDay;
		accidentDate = accidentDateYear + accidentDateMonth + accidentDateDay;
		
		if(claimType != null){
			for(int i = 0; i< (claimType.size()-1); i++){
				sClaimType += (claimType.get(i)+"-");
			}
			sClaimType += claimType.get(claimType.size()-1);
			
			map.put("ReportName", reportName);
			map.put("ReportPhone", reportPhone);
			map.put("ReportRelation", reportRelation);
			map.put("AccidentName", accidentName);
			map.put("AccidentSex", accidentSex);
			map.put("AccidentBirth", accidentBirth);
			map.put("AccidentIdType", accidentIdType);
			map.put("AccidentId", accidentId);
			map.put("AccidentDate", accidentDate);
			map.put("Province", province);
			map.put("City", city);
			map.put("County", county);
			map.put("Other", other);
			map.put("AccidentReason", accidentReason);
			map.put("AccidentStatus", accidentStatus);
			map.put("AccidentDo", accidentDo);
			if("1".equals(accidentReason)){
				map.put("AccidentResult", "T07");
			}else{
				map.put("AccidentResult", "R69");
			}
			map.put("Hospital", hospital);
			map.put("Diagnose", diagnose);
			map.put("ClaimType", sClaimType);
			
			docRequest = claimReportCreater.createXml(map);
			try {
				sRequest = wsclientHelper.doc2String(docRequest);
				sResponse = wsclientHelper.submitGBK(sRequest);
				docResponse = wsclientHelper.string2Doc(sResponse);

				result = claimReportCreater.Xml2Object(docResponse);

				flag = result.get("flag");
				desc = result.get("desc");
			} catch (Exception e) {
				flag = "0";
				desc = "网络异常！";
				e.printStackTrace();
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查询医院
	 * @return
	 */
	public String loadHospitals(){
		String keyword = super.getRequest().getParameter("keyword");
		List hospitals = hospitalService.findHospitalInfoByLikeName(keyword);
		super.render(JsonBinder.buildNonNullBinder().toJson(hospitals), "application/json;charset=GBK");
		return NONE;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public void setReportPhone(String reportPhone) {
		this.reportPhone = reportPhone;
	}

	public void setAccidentName(String accidentName) {
		this.accidentName = accidentName;
	}

	public void setAccidentSex(String accidentSex) {
		this.accidentSex = accidentSex;
	}

	public void setAccidentIdType(String accidentIdType) {
		this.accidentIdType = accidentIdType;
	}
	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public void setAccidentReason(String accidentReason) {
		this.accidentReason = accidentReason;
	}

	public void setAccidentStatus(String accidentStatus) {
		this.accidentStatus = accidentStatus;
	}

	public void setAccidentDo(String accidentDo) {
		this.accidentDo = accidentDo;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public void setClaimType(List<String> claimType) {
		this.claimType = claimType;
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

	public void setReportRelation(String reportRelation) {
		this.reportRelation = reportRelation;
	}

	public void setAccidentBirthYear(String accidentBirthYear) {
		this.accidentBirthYear = accidentBirthYear;
	}

	public void setAccidentBirthMonth(String accidentBirthMonth) {
		this.accidentBirthMonth = accidentBirthMonth;
	}

	public void setAccidentBirthDay(String accidentBirthDay) {
		this.accidentBirthDay = accidentBirthDay;
	}

	public void setAccidentDateYear(String accidentDateYear) {
		this.accidentDateYear = accidentDateYear;
	}

	public void setAccidentDateMonth(String accidentDateMonth) {
		this.accidentDateMonth = accidentDateMonth;
	}

	public void setAccidentDateDay(String accidentDateDay) {
		this.accidentDateDay = accidentDateDay;
	}
}
