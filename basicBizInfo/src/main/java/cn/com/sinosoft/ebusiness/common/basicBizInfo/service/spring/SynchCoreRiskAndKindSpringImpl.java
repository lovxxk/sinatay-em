package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring;

import ins.framework.utils.FileUtils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.exolab.castor.mapping.Mapping;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindFromConfig;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRiskClassCodeAndRiskCode;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.SynchCoreRiskAndKindService;
import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.CastorMappingUnMarshall;
import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.LoadMappingFiles;

public class SynchCoreRiskAndKindSpringImpl implements SynchCoreRiskAndKindService {
	private String kindMappingConfigPath;
	
	private String kindConfigPath;
	
	private List<String> riskCodeList;
	

	private BizCommonService bizCommonService;
	/*
	public void init() throws Exception {
//		String kindMappingxml = getFilePath(kindMappingConfigPath);
//		String kindxml = getFilePath(kindConfigPath);
		URL kindMappingxml = getUrl(kindMappingConfigPath);  
		URL kindxml = getUrl(kindConfigPath);
		
		CastorMappingUnMarshall cmu = new CastorMappingUnMarshall();
		String requestxml = FileUtils.read(kindMappingxml.getFile());
		GeKindFromConfig geKindFromConfig = (GeKindFromConfig) cmu.unmarshallMessage(requestxml, kindMappingxml.getPath());
		
		//set riskCodeList
		riskCodeList = getSynchCoreRisk(geKindFromConfig.getSynchCoreRiskCodes());
		
		
//		Map<String,RiskAndKind> map = new HashMap<String, RiskAndKind>();
//		if(geKindFromConfig!=null&&geKindFromConfig.getGeKinds()!=null&&geKindFromConfig.getGeKinds().size()>0){
//			for(RiskAndKind riskAndKindTemp:geKindFromConfig.getGeKinds()){
//				if(riskAndKindTemp.getKindCode()==null){
//					BasicBizInfoCommonException.newInstanceCode("000");//在配置文件kind.xml里配置的固定的险别代码不能为空
//				}
//				if(map.get(riskAndKindTemp.getKindCode())==null){
//					map.put(riskAndKindTemp.getKindCode(), riskAndKindTemp);//key=kindCode
//				}
//			}
//		}
//		if(geKindFromConfig.getSynchCoreRiskCodes()!=null&&geKindFromConfig.getSynchCoreRiskCodes().size()>0){
//			bizCommonService.getCoreRiskAndKind(geKindFromConfig.getSynchCoreRiskCodes(),map);
//		}
	}
	*/
	public void init() throws Exception {
		Mapping kindMapping = LoadMappingFiles.loadMappingFile("config", "kindMapping.xml");
		String kindxml = getFilePath(kindConfigPath);
		CastorMappingUnMarshall cmu = new CastorMappingUnMarshall();
		String requestxml = FileUtils.read(kindxml);
		
		GeKindFromConfig geKindFromConfig = (GeKindFromConfig) cmu.unmarshallMessage(requestxml, kindMapping);
		riskCodeList = getSynchCoreRisk(geKindFromConfig.getSynchCoreRiskCodes());
	}
	/**
	 * 返回要同步的险别
	 * @return
	 * @throws Exception
	 */
	private List<String> getSynchCoreRisk(List<GeRiskClassCodeAndRiskCode> geRiskClassCodeAndRiskCodeList){
		if(geRiskClassCodeAndRiskCodeList!=null&&geRiskClassCodeAndRiskCodeList.size()>0){
			List<String> riskCodeList = new ArrayList<String>();
			for(GeRiskClassCodeAndRiskCode geRiskClassCodeAndRiskCodeTemp :geRiskClassCodeAndRiskCodeList){
				riskCodeList.add(geRiskClassCodeAndRiskCodeTemp.getRiskCode());
			}
			return riskCodeList;
		}else{
			return null;
		}
	}
	private String getFilePath(String exceptionConfigPath)
			throws UnsupportedEncodingException {
		//String filePathTemp = this.getClass().getClassLoader().getResource(exceptionConfigPath).getPath();
		String filePathTemp = SynchCoreRiskAndKindSpringImpl.class.getClassLoader().getResource("config/" +exceptionConfigPath).getPath();
		filePathTemp = java.net.URLDecoder.decode(filePathTemp, "utf-8");
		return filePathTemp;
	}
	public static URL getUrl(String fileMame){
		return SynchCoreRiskAndKindSpringImpl.class.getClassLoader().getResource("config/" + fileMame);
	}

	//get and set method
	public BizCommonService getBizCommonService() {
		return bizCommonService;
	}
	public void setBizCommonService(BizCommonService bizCommonService) {
		this.bizCommonService = bizCommonService;
	}
	public String getKindMappingConfigPath() {
		return kindMappingConfigPath;
	}

	public void setKindMappingConfigPath(String kindMappingConfigPath) {
		this.kindMappingConfigPath = kindMappingConfigPath;
	}

	public String getKindConfigPath() {
		return kindConfigPath;
	}

	public void setKindConfigPath(String kindConfigPath) {
		this.kindConfigPath = kindConfigPath;
	}
	public List<String> getRiskCodeList() {
		return riskCodeList;
	}
	public void setRiskCodeList(List<String> riskCodeList) {
		this.riskCodeList = riskCodeList;
	}
}
