package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring;


import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.exception.BusinessException;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import cn.com.sinosoft.businessModule.network.domain.Network;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeArea;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKind;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindId;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindRelate;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindRelateId;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeMaxNo;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeMaxNoId;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRisk;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRiskClassCodeAndRiskCode;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.IInsuranceExtension;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.RiskAndKind;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.RiskClauseKindData;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.RiskInputCoreDto;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.TXInsuranceRequest;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.TXInsuranceRequestExtension;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeId;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeType;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeSmsConfigService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BasicBizInfoCommonException;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.GePortalInterfaceExtraService;
import cn.com.sinosoft.portalModule.interfacePortal.webService.service.WebService;

import com.wisdom.mtwebservice.client.MTWebServiceIFService;

/**
 * ����Service�ӿڵ�ʵ����,�ṩ���뷭��,��ȡ���ŵ�ͨ�ù���
 *
 */
public class BizCommonServiceSpringImpl extends
		GenericDaoHibernate<GeRisk, String> implements BizCommonService {
	
	//private static final Log logger = LogFactory.getLog(BizCommonServiceSpringImpl.class);
	Logger logger = LoggerFactory.getLogger(BizCommonServiceSpringImpl.class);
	private GeSmsConfigService geSmsConfigService; 
	private WebService webService;
	private GePortalInterfaceExtraService gePortalInterfaceExtraService;
	
	/*
	private static final String onlineAbsolutePath = getProjectLocalPath() + "/online_war.ear/online.war/WEB-INF/classes/config/phoneConfig.properties";
	
	
	//*****���ض��Žӿ���Ϣ������phoneConfig.properties
	private static String ipHost;
	private static String user_id;
	private static String userName;
	private static String pwd;
	
	private static boolean loadPhoneMessage(){
		System.out.println(">>>>>>>>>>>>>>>>>>##############��ȡ���������ļ�·��Ϊ��"+onlineAbsolutePath);
		
		Properties p = new Properties();
		InputStream in = null;
		try {
		   in = new BufferedInputStream(new FileInputStream(onlineAbsolutePath));
		   p.load(in);
		   
		   ipHost = p.getProperty("ip") +":"+ p.getProperty("host");
		   user_id = p.getProperty("user_id");
		   userName = p.getProperty("userName");
		   pwd = p.getProperty("pwd");
		   
		   return true;
		}catch (Exception e1) {
		   e1.printStackTrace();
		   throw BasicBizInfoCommonException.newInstanceMsg("��ȡ���������ļ�����!",e1);
		}
	}
	*/
	
	public GePortalInterfaceExtraService getGePortalInterfaceExtraService() {
		return gePortalInterfaceExtraService;
	}
	public void setGePortalInterfaceExtraService(
			GePortalInterfaceExtraService gePortalInterfaceExtraService) {
		this.gePortalInterfaceExtraService = gePortalInterfaceExtraService;
	}
	//*****���ض��Žӿ���Ϣ��ʵʱ��ȡ���ݿ�
	private String urlString;
	private String namespaceURI;
	private String localPart;
	private String user_id;
	private String userName;
	private String pwd;
	
	private boolean loadPhoneMessage(){
		try {
			Map<String, String> map = gePortalInterfaceExtraService.findParamFromType("SMFromLife");
			urlString = map.get("url");
			namespaceURI = map.get("namespaceURI");
			localPart = map.get("localPart");
			user_id = map.get("user_id");
			userName = map.get("userName");
			pwd = map.get("pwd");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * ��ʽ������
	 */
//	private static final NumberFormat nf = new DecimalFormat("0000000000");
	private static final NumberFormat nf = new DecimalFormat("000000");
	/**
	 * ��ʼ����ʵ��
	 */
	private static CacheService cacheManager = CacheManager.getInstance("code");
	
	/**
	 * ���ݴ�������,�����ô�������(Ӧ�û������)
	 * @param codeType ��������
	 * @param codeCode ����
	 * @return ��������
	 */
	public String findCodeName(String codeType, String codeCode) {
		String codeName = "";
		GeCode code = null;
		String key = cacheManager.generateCacheKey(codeType,codeCode);
		Object cache = cacheManager.getCache(key);
		if(cache!=null){
			code = (GeCode)cache;
		}else{
			GeCodeId id = new GeCodeId();
			id.setCodeType(codeType);
			id.setCodeCode(codeCode);
			code = super.get(GeCode.class, id);
			cacheManager.putCache(key, code);
		}
		if(code!=null&&code.getCodeCName()!=null){
			codeName = code.getCodeCName();
		}
		return codeName;
	}
	/**
	 * ���ݴ�������,��ø�����ȫ�����뼯��
	 * @param codeType ��������
	 * @return ���뼯��
	 */
	public List<GeCode> findCodeList(String codeType) {
		GeCodeType geCodeType = super.get(GeCodeType.class, codeType);
		if(geCodeType!=null){
			return geCodeType.getGeCodes();
		}else{
			throw new BusinessException();
		}
	}
	
	
	public void addGeRisk(GeRisk geRisk){
		super.save(geRisk);
	}
	
	/**
	 * ��ȡ��ǰҵ������к�
	 * 
	 * @param typeCode ҵ�����
	 * @param date     ҵ������
	 * @param clazz    ����ʱ����,Ϊ���ø÷���Ҫ����Ķ�Ӧ��hibernateʵ����
	 * 01 --��������
	 * 02 --�������㵥��
	 * 03 --��ҵID��ˮ��
	 * 04 --�������㵥��
	 * 05 --���ռӹ����
	 * @return (�����к�����ʱ,��������,������ʱ,����ȱʧ����С��)
	 */
	@SuppressWarnings("unchecked")
	public synchronized String takeSerialNo(String typeCode, Date dateTemp,Class<?> clazz) {
		
//		synchronized(cacheManager){
//			
//		}
		if (typeCode == null || "".equals(typeCode)) {
			throw new BusinessException();
		}
//		if (dateTemp == null ) {
//			throw new BusinessException();
//		}
		String returnMaxNo = null;// Ҫ���ص����
		String date = dealWithDate(dateTemp);
		String groupNo = typeCode + date;// ���,���������������  �ζ�Ӧֵ
		logger.debug("��ȡ���Ϊ" + groupNo + " ������");
		while(true){
			
			boolean hasGroupNo = super.getCount("from GeMaxNo where id.groupNo=?",groupNo) == 0;// ��ѯ,��groupNo�Ƿ��м�¼��
			
			if (hasGroupNo) {// û�������ŵļ�¼,insertһ��                            
				
					returnMaxNo = groupNo + nf.format(Integer.parseInt(GeMaxNo.FIRST));  //��������
					GeMaxNo maxNo = new GeMaxNo();
					maxNo.setFlag(GeMaxNo.FLAG_YES);
					maxNo.setValidInd(GeMaxNo.FLAG_YES);
					
					GeMaxNoId id = new GeMaxNoId();
					id.setGroupNo(groupNo);
					id.setMaxNo(GeMaxNo.SECOND);
					
					maxNo.setId(id);
					super.save(maxNo);
				
			} else {// �������ŵļ�¼,��ô�ж�,�Ƿ���Flag Ϊ GeMaxNo.FLAG_NO�ļ�¼,�������
				
				
					boolean hasFlagNo = super.getCount("from GeMaxNo where id.groupNo=? and flag=?", groupNo,GeMaxNo.FLAG_NO) == 0;
					if (hasFlagNo) {// ���û�� Flag Ϊ GeMaxNo.FLAG_NO�ļ�¼ ��ô Ŀǰȫ���������ļ�¼��û����Ч�����ţ���ô��������������
						
						
						// �����ݿ��в��,��ǰgroupNo������,Ȼ�󷵻ظ�����,Ȼ�󽫸�����ɾ��,����һ����������1�ļ�¼
						QueryRule queryRule = QueryRule.getInstance();
						queryRule.addEqual("id.groupNo", groupNo).addEqual("flag", GeMaxNo.FLAG_YES);
						
						GeMaxNo maxNoTemp = super.findUnique(GeMaxNo.class,queryRule);
						
						returnMaxNo = groupNo + nf.format(Integer.parseInt(maxNoTemp.getId().getMaxNo()));
						
						GeMaxNoId id = new GeMaxNoId();
						id.setGroupNo(groupNo);
						id.setMaxNo(String.valueOf(Integer.parseInt(maxNoTemp.getId().getMaxNo()) + 1));
						super.delete(maxNoTemp);
						
						GeMaxNo maxNo = new GeMaxNo();
						maxNo.setFlag(GeMaxNo.FLAG_YES);
						maxNo.setValidInd(GeMaxNo.FLAG_YES);
						maxNo.setId(id);
						super.save(maxNo);
						
					} else {// �����Flag,����Ч������
						
						
							// ΪGeMaxNo.FLAG_NO�ļ�¼,��ôȡ,��Щ��¼��,������С��һ��,���ظ�����,Ȼ�󽫸�����ɾ��
							QueryRule queryRule = QueryRule.getInstance();
							queryRule.addEqual("id.groupNo", groupNo)
							.addEqual("flag", GeMaxNo.FLAG_NO)
							.addAscOrder("id.maxNo");
							List<GeMaxNo> maxNoList = super.find(GeMaxNo.class,queryRule);
							
							if (maxNoList != null && maxNoList.size() > 0) {
								GeMaxNo maxNo = maxNoList.get(0);
								returnMaxNo = groupNo + nf.format(Integer.parseInt(maxNo.getId().getMaxNo()));
								super.delete(maxNo);
							}
						
						
					}
			}
			
			if (findMaxNoBySql(clazz,returnMaxNo)){
				break;
			}
		}

		return returnMaxNo;
	}
	
	/**
	 * ��֤�����Ƿ��Ѿ�����Ŀ�����
	 * @param clazz ����ʱ����,������hibernate�־���
	 * @param maxNo ���ɺõ�,����֤������
	 * @return ��֤ͨ������true.��ͨ������false
	 */
	private boolean findMaxNoBySql(Class<?> clazz,String maxNo){
		String tableName = ((Table)clazz.getAnnotation(Table.class)).name();
		String field = "";
		for(Method m : clazz.getDeclaredMethods()){
			if(m.getAnnotation(Id.class)!=null){
				field = (m.getAnnotation(Column.class).name());
				break;
			}
		}
		String sql = "select count("+field+") from "+tableName+" where "+ field + "=?";
		@SuppressWarnings("unchecked")
		List<BigDecimal> list = super.findBySql(sql, maxNo);
		return Integer.parseInt(list.get(0).toString())==0;
	}
	
	/**
	 * ��ҵ�����ɾ����Ӧ�����к�ʱ()��Ҫ���ø÷���,��ɾ�������к��ͻ��������
	 * @param serialNo Ҫɾ��������
	 */
	public void revertMaxNo(String serialNo){
		if(serialNo.length()<7){
			throw new BusinessException();
		}
		String groupNo = serialNo.substring(0, serialNo.length()-6);
		String maxNo = serialNo.substring(serialNo.length()-6);
		GeMaxNo geMaxNo = new GeMaxNo();
		geMaxNo.setFlag(GeMaxNo.FLAG_NO);
		geMaxNo.setValidInd(GeMaxNo.FLAG_YES);
		GeMaxNoId id = new GeMaxNoId();
		id.setGroupNo(groupNo);
		String x  = String.valueOf(Integer.parseInt(maxNo));
		id.setMaxNo(x);
		geMaxNo.setId(id);
		super.save(geMaxNo);
		
	}
	/**
	 * ע��ݵ�����һ��Ҫ��ȷ���� 
	 * ��Ҫ��ȷ��ʱ����
	 * @return
	 */
	private String  dealWithDate(Date date){
		if(date!=null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			return simpleDateFormat.format(date);
		}else{
			return "";
		}
		
	}
	/**
	 * ���ʡ���б�
	 * @return ʡ���б���
	 */
	@SuppressWarnings("unchecked")
	public List<GeArea> findAllProvince() {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("arealevel", GeArea.AREA_LEVEL_PROVINCE);
		return super.find(GeArea.class, rule);
	}
	/**
	 * ����ʡ�ݴ����ø�ʡ���³����б���
	 * @param upperareacode ʡ�ݴ���
	 * @return ���м���
	 */
	@SuppressWarnings("unchecked")
	public List<GeArea> findCityListByProvince(String upperareacode) {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("arealevel", GeArea.AREA_LEVEL_CITY);
		if(StringUtils.isNotBlank(upperareacode))
			rule.addEqual("upperareacode", upperareacode);
		return super.find(GeArea.class, rule);
	}
	
	/**
	 * ���ݵ��������õ�������
	 * @param areacode ��������
	 * @return ��������or NULL
	 */
	public GeArea findAreaByAreacode(String areacode){
		return super.get(GeArea.class,areacode);
	}
	
	
	/**
	 * �������ִ��������ֶ���
	 * @param riskCode ���ִ���
	 * @return ���ֶ���
	 */
	public GeRisk findGeRiskByCode(String riskCode) {
		Assert.hasText(riskCode, "�봫�����ִ���");
		GeRisk risk = null;
		String key = cacheManager.generateCacheKey("GeRisk",riskCode);
		Object cache = cacheManager.getCache(key);
		if(cache!=null){
			risk = (GeRisk)cache;
		}else{
			risk = super.get(GeRisk.class, riskCode);
			cacheManager.putCache("GeRisk",riskCode);
		}
		return risk;
	}

	/**
	 * ��ѯ��������
	 * @param queryRule
	 * @return
	 * QueryRule queryRule = QueryRule.getInstance();
	 * queryRule.addEqual("riskType", "11");//������
	 */
	@SuppressWarnings("unchecked")
	public List<GeRisk> findAllRiskCode(QueryRule queryRule) {
		//cacheManager.getCacheManagerInfo(arg0);
		if(queryRule ==null){
			queryRule =  QueryRule.getInstance();
		}
		return super.find(GeRisk.class, queryRule);
	}
	
	/**
	 * ��ѯ���֣���������ѯ ��ҳ��ѯ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeRiskList(QueryRule queryRule,int pageNo,int pageSize){
		if(queryRule ==null){
			queryRule =  QueryRule.getInstance();
		}
		return super.find(queryRule, pageNo, pageSize);
	}
	
	public List<GeRisk> findGeRiskListWithEid(GeRisk geRisk){
		StringBuilder sb = new StringBuilder();
		sb.append("select       direc.eid,          ");
		sb.append("             risk.riskcode,      ");
		sb.append("             direc.productname   ");
		sb.append("from         ge_directory direc, ");
		sb.append("             ge_risk risk        ");
		sb.append("where        direc.coreproductcode = risk.riskcode   ");
		sb.append("and          direc.businessarea = '3'                ");//����Ϊ��ҵ������д��Ϊ3
		sb.append("and          direc.isnetsale = '01'                  ");//Ϊ������Ʒ
		sb.append("and          direc.isproductshelf = '01'             ");//Ϊ���ϼܲ�Ʒ
		sb.append("and          risk.businessarea='3'                   ");//���ֵ�ҵ������Ϊ3
		//
		StringBuilder condition = new StringBuilder();
		if(geRisk!=null){
			if(geRisk.getRiskCName()!=null&&!geRisk.getRiskCName().equals("")){
				String riskCName =geRisk.getRiskCName(); 
				condition.append("and risk.riskcname like '%"+riskCName+"%'   ");
			}
			if(geRisk.getRiskCode()!=null&&!geRisk.getRiskCode().equals("")){
				String riskCode = geRisk.getRiskCode();
				condition.append("and risk.riskCode = '"+riskCode+"'   ");
			}
		}
		if(condition.toString()!=null&&!condition.toString().equals("")){
			sb.append(condition.toString());
		}
		//
		
		
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		List list = sqlQuery.list();
		if(list!=null&&list.size()>0){
			List<GeRisk> geRiskList= new ArrayList<GeRisk>();
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[])list.get(i);
				GeRisk geRiskTemp = new GeRisk();
				geRiskTemp.setEid(objs[0]==null?"":(String)objs[0]);//eid
				geRiskTemp.setRiskCode(objs[1]==null?"":(String)objs[1]);//���ִ���
				geRiskTemp.setRiskCName(objs[2]==null?"":(String)objs[2]);//��Ʒ����
				geRiskList.add(geRiskTemp);
			}
			return geRiskList;
		}
		return null;
	}
	
	public Page findGeRiskListWithEid(GeRisk geRisk,int pageNo,int pageSize){
		long start=0l;//
		long end = 0l;
		long totalSize=0l;//�ܼ�¼��
		totalSize = findGeRiskListWithEidCount(geRisk);
		start = (pageNo-1)*pageSize+1;
		end =   pageNo*pageSize;
		List list =findGeRiskListWithEidList(geRisk,start,end);
		if(list!=null&&list.size()>0){
			return new Page(start,totalSize,pageSize,list);
		}
		return null;
	}
	
	private List findGeRiskListWithEidList(GeRisk geRisk,long start,long end){
		
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append("SELECT * FROM (                                   ");

		sb.append("select A.*,ROWNUM RN                              ");
		sb.append("FROM                                              ");
		sb.append("(select                                           ");
		sb.append("direc.eid,                                        ");
		sb.append("risk.riskcode,                                    ");
		sb.append("risk.riskcname,                                   ");
		sb.append("direc.businessarea,                               ");
		sb.append("risk.validind,                                    ");
		sb.append("risk.operatorid,                                  ");
		sb.append("risk.createdate                                  ");
		sb.append("from ge_directory direc ,ge_risk risk             ");
		sb.append("where direc.coreproductcode = risk.riskcode       ");
		sb.append("and direc.businessarea='3'                        ");//���յ�ҵ������Ϊ3����
		sb.append("and direc.isnetsale ='01'                         ");//�Ƿ�Ϊ����
		sb.append("and direc.isproductshelf='01'                     ");//�ϼ�
		StringBuilder condition = new StringBuilder();
		if(geRisk!=null){
			if(geRisk.getBusinessArea()!=null&&!geRisk.getBusinessArea().equals("")){
				String businessArea = geRisk.getBusinessArea();
				condition.append("and risk.businessarea='"+businessArea+"'    ");
			}
			if(geRisk.getRiskCName()!=null&&!geRisk.getRiskCName().equals("")){
				String riskCName =geRisk.getRiskCName(); 
				condition.append("and risk.riskcname like '%"+riskCName+"%'   ");
			}
			if(geRisk.getRiskCode()!=null&&!geRisk.getRiskCode().equals("")){
				String riskCode = geRisk.getRiskCode();
				condition.append("and risk.riskCode = '"+riskCode+"'   ");
			}
		}
		if(condition.toString()!=null&&!condition.toString().equals("")){
			sb.append(condition.toString());
		}
		sb.append(") A                                               ");
		sb.append("WHERE ROWNUM<=?                                   ");
		sb.append(")                                                 ");

		sb.append("WHERE RN>=?                                       ");
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		sqlQuery.setLong(0, end);
		sqlQuery.setLong(1, start);
		List list = sqlQuery.list();
		if(list!=null&&list.size()>0){
			List<GeRisk> geRiskList= new ArrayList<GeRisk>();
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[])list.get(i);
				GeRisk geRiskTemp = new GeRisk();
				geRiskTemp.setEid(objs[0]==null?"":(String)objs[0]);
				geRiskTemp.setRiskCode(objs[1]==null?"":(String)objs[1]);
				geRiskTemp.setRiskCName(objs[2]==null?"":(String)objs[2]);
				geRiskTemp.setBusinessArea(objs[3]==null?"":(String)objs[3]);
				geRiskTemp.setValidInd(objs[4]==null?"":(String)objs[4]);
				geRiskTemp.setOperatorID(objs[4]==null?"":(String)objs[4]);
				geRiskTemp.setCreateDate(objs[6]==null?null:(Date)objs[6]);
				geRiskList.add(geRiskTemp);
			}
			return geRiskList;
		}
		return null;
	}
	private long findGeRiskListWithEidCount(GeRisk geRisk){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(*)           ");
		sb.append("from ge_directory direc ,ge_risk risk           ");
		sb.append("where direc.coreproductcode = risk.riskcode     ");
		sb.append("and direc.businessarea='3'                      ");
		sb.append("and direc.isnetsale ='01'                       ");
		sb.append("and direc.isproductshelf='01'                   ");
		
		StringBuilder condition = new StringBuilder();
		if(geRisk!=null){
			if(geRisk.getBusinessArea()!=null&&!geRisk.getBusinessArea().equals("")){
				String businessArea = geRisk.getBusinessArea();
				condition.append("and risk.businessarea='"+businessArea+"'    ");
			}
			if(geRisk.getRiskCName()!=null&&!geRisk.getRiskCName().equals("")){
				String riskCName =geRisk.getRiskCName(); 
				condition.append("and risk.riskcname like '%"+riskCName+"%'   ");
			}
			if(geRisk.getRiskCode()!=null&&!geRisk.getRiskCode().equals("")){
				String riskCode = geRisk.getRiskCode();
				condition.append("and risk.riskCode = '"+riskCode+"'   ");
			}
		}
		if(condition.toString()!=null&&!condition.toString().equals("")){
			sb.append(condition.toString());
		}
		
		
		
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		List list = sqlQuery.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				BigDecimal bigDecimal = (BigDecimal)list.get(i);
				return bigDecimal.longValue();
			}
		}
		
		return 0l;
	}
	
	public List<String> findEid(String riskCode){
		List<String> eids = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select geDirectory.eid                ");//��ƷID
		sb.append("from   GeDirectory geDirectory        ");//��ƷĿ¼
		sb.append("where  geDirectory.isNetSale='01'     ");//01��������
		sb.append("and    geDirectory.businessArea='3'   ");//03����ҵ������
		sb.append("and    geDirectory.coreProductCode=?  ");//������ 
		Query query = getSession().createQuery(sb.toString());
		query.setString(0, riskCode);
		List list= query.list();
		if(list!=null&&list.size()>0){
			
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[])list.get(i);
				eids.add((String)obj[0]);//eid
			}
		}
		return eids;
	}
	
	//�����������
	public String findEidName(String riskCode){
		List<String> eids = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("select geDirectory.productName        ");//��ƷID
		sb.append("from   GeDirectory geDirectory        ");//��ƷĿ¼
		sb.append("where  geDirectory.isNetSale='01'     ");//01��������
		sb.append("and    geDirectory.businessArea='3'   ");//03����ҵ������
		sb.append("and    geDirectory.coreProductCode=?  ");//������ 
		Query query = getSession().createQuery(sb.toString());
		query.setString(0, riskCode);
		List list= query.list();
		if(list!=null&&list.size()>0){
			return  (String) list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<GeKind> findKindByRiskCode(String riskCode){
		Assert.hasText(riskCode, "���ִ��벻����Ϊ��");
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.riskCode", riskCode);
		return super.find(GeKind.class,queryRule);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GeKind> findValidateKind(List<String> kindCodes){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIn("id.kindCode", kindCodes);
		queryRule.addEqual("validInd", "1");
		return super.find(GeKind.class,queryRule);
	}
	
	/**
	 * ��ѯ������Ч�ձ�
	 * @return
	 */
	public List<GeKind> findInvalidateKind(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("validInd", "0");
		return super.find(GeKind.class,queryRule);
	}
	
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�������ձ��б�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GeKind> findValidateMainKind(boolean isFreeCombo){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("kindflag", "01");
		queryRule.addEqual("validInd", "1");
		if (isFreeCombo) {
			List<String> comboflags = new ArrayList<String>();
			comboflags.add("1");
			comboflags.add("0");
			queryRule.addIn("isComboFlag", comboflags);			
		} else {
			queryRule.addEqual("isComboFlag", "1");
		}
		queryRule.addAscOrder("orderNo");
		return super.find(GeKind.class, queryRule);
	}
	
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�������ձ��б�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GeKind> findValidateMainKind(boolean isFreeCombo,String riskCode){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("kindflag", "01");
		queryRule.addEqual("validInd", "1");
		queryRule.addEqual("id.riskCode", riskCode);
		if (isFreeCombo) {
			List<String> comboflags = new ArrayList<String>();
			comboflags.add("1");
			comboflags.add("0");
			queryRule.addIn("isComboFlag", comboflags);			
		} else {
			queryRule.addEqual("isComboFlag", "1");
		}
		queryRule.addAscOrder("orderNo");
		return super.find(GeKind.class, queryRule);
	}
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�ĸ������ձ��б�
	 * @return
	 */
	public List<GeKind> findValidateAttachKind(boolean isFreeCombo){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("kindflag", "02");
		queryRule.addEqual("validInd", "1");
		if (isFreeCombo) {
			List<String> comboflags = new ArrayList<String>();
			comboflags.add("1");
			comboflags.add("0");
			queryRule.addIn("isComboFlag", comboflags);			
		} else {
			queryRule.addEqual("isComboFlag", "1");
		}
		queryRule.addAscOrder("orderNo");
		return super.find(GeKind.class, queryRule);
	}
	
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�ĸ������ձ��б�
	 * @return
	 */
	public List<GeKind> findValidateAttachKind(boolean isFreeCombo,String riskCode){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("kindflag", "02");
		queryRule.addEqual("validInd", "1");
		queryRule.addEqual("id.riskCode", riskCode);
		if (isFreeCombo) {
			List<String> comboflags = new ArrayList<String>();
			comboflags.add("1");
			comboflags.add("0");
			queryRule.addIn("isComboFlag", comboflags);			
		} else {
			queryRule.addEqual("isComboFlag", "1");
		}
		queryRule.addAscOrder("orderNo");
		return super.find(GeKind.class, queryRule);
	}
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�Ĳ��������ձ��б�
	 * @return
	 */
	public List<GeKind> findValidateDeductKind(boolean isFreeCombo){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("nodeductflag", "1");
		queryRule.addEqual("validInd", "1");
		if (isFreeCombo) {
			List<String> comboflags = new ArrayList<String>();
			comboflags.add("1");
			comboflags.add("2");
			queryRule.addIn("isComboFlag", comboflags);			
		} else {
			queryRule.addEqual("isComboFlag", "1");
		}
		queryRule.addAscOrder("orderNo");
		return super.find(GeKind.class,queryRule);
	}
	
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�Ĳ��������ձ��б�
	 * @return
	 */
	public List<GeKind> findValidateDeductKind(boolean isFreeCombo,String riskCode){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("nodeductflag", "1");
		queryRule.addEqual("validInd", "1");
		queryRule.addEqual("id.riskCode", riskCode);
		if (isFreeCombo) {
			List<String> comboflags = new ArrayList<String>();
			comboflags.add("1");
			comboflags.add("2");
			queryRule.addIn("isComboFlag", comboflags);			
		} else {
			queryRule.addEqual("isComboFlag", "1");
		}
		queryRule.addAscOrder("kindflag");
		queryRule.addAscOrder("orderNo");
		return super.find(GeKind.class,queryRule);
	}
	
	@SuppressWarnings("unchecked")
	public List<GeKind> findKindByMain(String riskCode, String kindCode) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.riskCode", riskCode);
		queryRule.addEqual("id.kindCode", kindCode);
		List<GeKindRelate> list =  super.find(GeKindRelate.class, queryRule);
		ArrayList<String> kindCodeList = new ArrayList<String>();
		for(Iterator<GeKindRelate> iter = list.iterator();iter.hasNext();){
			kindCodeList.add(iter.next().getId().getRelateKindCode());
		}
		return findValidateKind(kindCodeList);
	}
	 
	/**
	 * �õ� ���ĵ������ձ�ӿ�
	 */
	public void getCoreRiskAndKind(List<GeRiskClassCodeAndRiskCode> riskCodeList ,java.util.Map<String,RiskAndKind> kindMap){
		//��ȡ���ǵ����ݿ���ȥ��ѯ0510 0507 �������֣�����ѯ���ˣ���ô��ͬ�����ģ���û�в�ѯ����ͬ������
		if(riskCodeList!=null&&riskCodeList.size()>0){//
			List<String> coreRiskList = new ArrayList<String>();
			java.util.Map<String,String> riskClassCodeAndRiskCodeMap = new HashMap<String, String>();
			
			for(GeRiskClassCodeAndRiskCode geRiskClassCodeAndRiskCodeTemp: riskCodeList){
				GeRisk geRisk  = super.get(geRiskClassCodeAndRiskCodeTemp.getRiskCode());
//				GeRisk geRisk = null;
				if(geRisk==null){//�����������ݿ��ﲻ���ڣ���Ҫͬ����������
					String riskClassCodeTemp = geRiskClassCodeAndRiskCodeTemp.getRiskClassCode();
					String riskCodeTemp = 	geRiskClassCodeAndRiskCodeTemp.getRiskCode();
					coreRiskList.add(riskCodeTemp);
					riskClassCodeAndRiskCodeMap.put(riskCodeTemp, riskClassCodeTemp);
				}
			}
			//
			if(coreRiskList.size()>0){//ͬ����������
				List<RiskInputCoreDto> riskInputCoreDtoList = new ArrayList<RiskInputCoreDto>();
				for(int i=0;i<coreRiskList.size();i++){
					//���ú��ĵķ���
					RiskInputCoreDto riskInputCoreDto = new RiskInputCoreDto();
					riskInputCoreDto.setRequestType("1");//1-����
					riskInputCoreDto.setRiskCode((String)coreRiskList.get(i));//���ִ��룺����������������
					riskInputCoreDto.setRiskClassCode(riskClassCodeAndRiskCodeMap.get((String)coreRiskList.get(i)));//�������05
					//���÷�ҳ
					IInsuranceExtension iInsuranceExtension = new IInsuranceExtension();
					iInsuranceExtension.setPageFlag("1");//1-Ϊ����ҳ
					iInsuranceExtension.setOrderField("1");//1-������
					//����������
					TXInsuranceRequest tXInsuranceRequest = new TXInsuranceRequest();
					tXInsuranceRequest.setIInsuranceExtension(iInsuranceExtension);//���÷�ҳ��
					//���ڲ��Ե�
					tXInsuranceRequest.setTransRefGUID("111");//������ˮ��
					tXInsuranceRequest.setTransType("transType");//�������ͣ����׺ţ�
					tXInsuranceRequest.setTransExeDate("2011-01-01");//��������
					tXInsuranceRequest.setTransExeTime("05:05:05");//����ʱ��
					tXInsuranceRequest.setTransSubType("01");//�ӽ�������
					riskInputCoreDto.settXInsuranceRequest(tXInsuranceRequest);//������������
					//������չ����
					TXInsuranceRequestExtension tXInsuranceRequestExtension = new TXInsuranceRequestExtension();//������չ��
					tXInsuranceRequestExtension.setOperator("ecom_user");//�û���
					tXInsuranceRequestExtension.setOperatorKey("123456");//����
					riskInputCoreDto.settXInsuranceRequestExtension(tXInsuranceRequestExtension);//������������չ��
					//add List
					riskInputCoreDtoList.add(riskInputCoreDto);
					//end******************************************************************
				}
				
				//���ú���
				List<RiskClauseKindData> riskClauseKindDataList = synchCoreRiskAndKind(riskInputCoreDtoList);
				//����ʹ��
//				List<RiskClauseKindData> riskClauseKindDataList = new ArrayList<RiskClauseKindData>();
//				//����
//				RiskClauseKindData RiskClauseKindData = new RiskClauseKindData();
//				RiskClauseKindData.setRiskCode("1112");
//				RiskClauseKindData.setRiskName("1112");
//				RiskClauseKindData.setClassCode("11");
//				RiskClauseKindData.setClauseCode("11");
//				RiskClauseKindData.setClauseName("11");
//				RiskClauseKindData.setKindCode("test01");
//				RiskClauseKindData.setKindName("test01");
//				RiskClauseKindData.setLsSum("1");
//				RiskClauseKindData.setRelateKindCode("aaa");
//				RiskClauseKindData.setKindFlag("01");//01����
//				RiskClauseKindData.setNoDeductFlag("1");
//				riskClauseKindDataList.add(RiskClauseKindData);
//				//
//				RiskClauseKindData RiskClauseKindData2 = new RiskClauseKindData();
//				RiskClauseKindData2.setRiskCode("1112");
//				RiskClauseKindData2.setRiskName("1112");
//				RiskClauseKindData2.setClassCode("11");
//				RiskClauseKindData2.setClauseCode("11");
//				RiskClauseKindData2.setClauseName("11");
//				RiskClauseKindData2.setKindCode("test02");
//				RiskClauseKindData2.setKindName("test02");
//				RiskClauseKindData2.setLsSum("1");
//				RiskClauseKindData2.setRelateKindCode("test01");
//				RiskClauseKindData2.setKindFlag("02");;//������
//				RiskClauseKindData2.setNoDeductFlag("1");
//				
//				riskClauseKindDataList.add(RiskClauseKindData2);
				//������ĵĽ����
				if(riskClauseKindDataList!=null&&riskClauseKindDataList.size()>0){
					List<GeRisk> geRiskReturnList = new ArrayList<GeRisk>();//����
					List<GeKind> geKindReturnList = new ArrayList<GeKind>();//�ձ�
					List<GeKindRelate> geKindRelateList = new ArrayList<GeKindRelate>();//ά�����еĸ�����
					setListValue(geRiskReturnList,geKindReturnList,geKindRelateList,riskClauseKindDataList,kindMap);//����������List����ֵ
					//save �����ձ�
					super.saveAll(geRiskReturnList);//��������
					super.saveAll(geKindReturnList);//�����ձ�
					super.saveAll(geKindRelateList);//��������ձ����
				}
			}
		}//end if(riskCodeList!=null&&riskCodeList.size()>0){
	}
	
	/**
	 * ͬ�����ĵ������ձ�ӿ�
	 * @param riskInputCoreDto
	 * @return
	 * @throws AxisFault 
	 */
	public List<RiskClauseKindData> synchCoreRiskAndKind(List<RiskInputCoreDto> riskInputCoreDtoList){
		List<RiskClauseKindData> RiskClauseKindDataList = new ArrayList<RiskClauseKindData>(); //���ڴ�ŷ��ؽ�������ݵ�
		return RiskClauseKindDataList;
	}
	
	private void setListValue(List<GeRisk> geRiskList,
			List<GeKind> geKindList,
			List<GeKindRelate> geKindRelateList,
			List<RiskClauseKindData> riskClauseKindDataList,java.util.Map<String,RiskAndKind> kindMap){
		
		java.util.Map<String,List<GeRisk>> mapTemp = new HashMap<String, List<GeRisk>>();
		java.util.Set<String> set = new HashSet<String>();
		for(RiskClauseKindData riskClauseKindDataTemp :riskClauseKindDataList){
			//set GeRisk �ù��˵��ظ���
			set.add(riskClauseKindDataTemp.getRiskCode());
			if(mapTemp.get(riskClauseKindDataTemp.getRiskCode())==null){//��һ�����
				GeRisk geRisk = new GeRisk();
				geRisk.setRiskCode(riskClauseKindDataTemp.getRiskCode());
				geRisk.setRiskCName(riskClauseKindDataTemp.getRiskName());
//				geRisk.setRiskEName(riskEName)//����û�и�����
//				geRisk.setRiskTName(riskTName);//����û�и�����
				geRisk.setBusinessArea("3");//1-���ţ�2-���գ�3-���գ�4-�����գ�9-����
				geRisk.setRiskType("11");//01  ��ͳ��  02  �ֺ� 03  Ͷ�� 04  ���� 05  ���� 11 ���� 12 �Ҳ� 13 ���� 14 ����
//				geRisk.setInsuAccFlag(riskClauseKindDataTemp);����ר�� ����û�и�����//������ 0-���˻� 1-���˻�
				geRisk.setValidInd("1");//�����Ƿ���Ч��־:1��Ч 0��Ч
//				geRisk.setOperatorID("");//���ò���Ա�滮
				geRisk.setCreateDate(new Date());
				
				List<GeRisk> geRisks = new ArrayList<GeRisk>();
				geRisks.add(geRisk);
				mapTemp.put(riskClauseKindDataTemp.getRiskCode(), geRisks);
			}else{//�ǵ�һ�����
				GeRisk geRisk = new GeRisk();
				geRisk.setRiskCode(riskClauseKindDataTemp.getRiskCode());
				geRisk.setRiskCName(riskClauseKindDataTemp.getRiskName());
//				geRisk.setRiskEName(riskEName)//����û�и�����
//				geRisk.setRiskTName(riskTName);//����û�и�����
				geRisk.setBusinessArea("3");//1-���ţ�2-���գ�3-���գ�4-�����գ�9-����
				geRisk.setRiskType("11");//01  ��ͳ��  02  �ֺ� 03  Ͷ�� 04  ���� 05  ���� 11 ���� 12 �Ҳ� 13 ���� 14 ����
//				geRisk.setInsuAccFlag(riskClauseKindDataTemp);����ר�� ����û�и�����//������ 0-���˻� 1-���˻�
				geRisk.setValidInd("1");//�����Ƿ���Ч��־:1��Ч 0��Ч
//				geRisk.setOperatorID("");//���ò���Ա�滮
				geRisk.setCreateDate(new Date());
				
				mapTemp.get(riskClauseKindDataTemp.getRiskCode()).add(geRisk);
			}
			//set GeKind
			GeKindId id = new GeKindId();
			id.setRiskCode(riskClauseKindDataTemp.getRiskCode());
			id.setKindCode(riskClauseKindDataTemp.getKindCode());
			GeKind geKind = new GeKind();
			geKind.setId(id);
//			geKind.setCombinekindcode(combinekindcode);//�ϲ����룬����û�и��ֶ�
			geKind.setKindCName(riskClauseKindDataTemp.getKindName());
//			geKind.setKindEName(kindEName)//����û�и��ֶ�
//			geKind.setKindtname("");//����û�и��ֶ�
			geKind.setKindflag(riskClauseKindDataTemp.getKindFlag());//�ձ��־ 01-����  02-������          �������յñ�������ձ�
			//codeTypeȡֵ��� ,ȡֵ��ΧValueRange   �������ļ���ȡ
			if(kindMap!=null&&kindMap.get(riskClauseKindDataTemp.getKindCode())!=null){
				RiskAndKind riskAndKind =  kindMap.get(riskClauseKindDataTemp.getRiskCode()+riskClauseKindDataTemp.getKindCode());
				geKind.setCodeType(riskAndKind.getCodeType());//ȡkind.xml�������ļ��������
				geKind.setValuerange(riskAndKind.getValueRange());//ȡkind.xml�������ļ��������
			}
//			geKind.setOrderNo(orderNo);//�Լ�����
			geKind.setNodeductflag(riskClauseKindDataTemp.getNoDeductFlag());//���������־
			geKind.setValidInd("1");//�Լ����� �Ƿ���Ч��־��1��Ч 0��Ч
//			geKind.setFlag("");//�Լ����� 
			geKindList.add(geKind);
			
			
			//set  GeKindRelate
			if(riskClauseKindDataTemp.getKindFlag()!=null&&riskClauseKindDataTemp.getKindFlag().equals("02")){//������
				GeKindRelateId geKindRelateId = new GeKindRelateId();
				geKindRelateId.setRiskCode(riskClauseKindDataTemp.getRiskCode());
				geKindRelateId.setKindCode(riskClauseKindDataTemp.getKindCode());
				geKindRelateId.setRelateKindCode(riskClauseKindDataTemp.getRelateKindCode());
				
				GeKindRelate geKindRelate = new GeKindRelate();
				geKindRelate.setId(geKindRelateId);
				geKindRelate.setGeKind(geKind);
				geKindRelateList.add(geKindRelate);
			}
		}//end for(RiskClauseKindData riskClauseKindDataTemp :riskClauseKindDataList) 
		
		//����List<GeRisk> geRiskList  ���˵��ظ���
		if(set!=null&&set.size()>0){
			Iterator iterator = set.iterator();
			while(iterator.hasNext()){
				String riskCode = iterator.next().toString();
				List<GeRisk> geRiskListTemp = (List<GeRisk>)mapTemp.get(riskCode);
				GeRisk geRiskTemp = (GeRisk)geRiskListTemp.get(0);
				geRiskList.add(geRiskTemp);
			}
		}//end  if(set!=null&&set.size()>0){ 
	}
	
	
	//�������صĽӿ�
	//public boolean sendMessage(String mobile,String content){
	public boolean sendMessage(String functionId,String[] params,String mobile){
		String result = "";
		String content ="";
		//�������ŵ����� �ǵ��ö���ģ��ӿ�
		try {
			//���ö���ģ��
			content = geSmsConfigService.getSmsContent(functionId, params);//service
			//content ="this is test message ";///  ���ڲ���
			if(content==null||content.equals("")||mobile==null||mobile.equals("")){//
				logger.info("��������Ϊ�գ����ֻ�����Ϊ��");
				result = "2";
			}else{
				
//				SmsServiceStub.SmsMessage msg = null;
//				SmsServiceStub.SmsMessages msgs = new SmsServiceStub.SmsMessages();
//				
//				
//				msgs.setNeedUseTemplateFlag("false");//�����Ƿ���õĽӿ�ģ�� true:ʹ�� false:��ʹ��
//				msgs.setTemplatId("CLAIM_15");//Ҫʹ�õĽӿ�ģ���id
//				msgs.setTaskId("");//��ȷ����ֵ��ÿ��ͨ���ӿڷ���ʱΨһ
//				msgs.setTaskValue("��ע");//taskid��Ӧ�ı�ע�ֶ�
//				msgs.setOrganizationId("0");//���ø������ŵ�OrganizationId������ͬ�������ж��Ÿ�ʽ�е�OrganizationԪ��
//				msgs.setExtension("false");//�Ƿ����ø������ŵ���չβ��
//				msgs.setExtensionValue("������չβ��");//�������ŵ���չβ��
//				msgs.setServiceType("sgfs");//���ø������ŵ�ServiceType������ͬ�������ж��Ÿ�ʽ�е�ServiceTypeԪ��
//				
//				String[] date = getTime().split("@");
//				//��ʼ���� ��������
//				msgs.setStartDate(date[0]);//���ø������ŵ�StartDate������ͬ�������ж��Ÿ�ʽ�е�StartDateԪ��
//				msgs.setStartTime("00:00");//���ø������ŵ�StartTime������ͬ�������ж��Ÿ�ʽ�е�StartTimeԪ��
//				msgs.setEndDate(date[1]);//���ø������ŵ�EndDate������ͬ�������ж��Ÿ�ʽ�е�EndDateԪ��
//				msgs.setEndTime("23:59");//���ø������ŵ�EndTime������ͬ�������ж��Ÿ�ʽ�е�EndTimeԪ��
//				//��¼��־
//				logger.info("������ʼ����="+msgs.getStartDate()+" "+msgs.getStartTime());
//				logger.info("���Ž�������="+msgs.getEndDate()+" "+msgs.getEndTime());
//				//
//				msg = new SmsServiceStub.SmsMessage();//����SmsMessage���󣬶���ͬ�������ж��Ÿ�ʽ�е�MessageԪ��
//				msg.setReceiver(mobile);//���ø������ŵ�Receiver������ͬ�������ж��Ÿ�ʽ�е�ReceiverԪ��
//				msg.setOrgCode("0");//���ø������ŵķ��ͻ���orgCode������ͬ�������ж��Ÿ�ʽ�е�orgCodeԪ��
//				msg.setContents(content);
//				SmsServiceStub.SmsMessage []messages = new SmsServiceStub.SmsMessage []{msg};
//				msgs.setMessages(messages);
//				
//				//������
//				SmsServiceStub.TXInsuranceRequest tx_request = new SmsServiceStub.TXInsuranceRequest();
//				tx_request.setTransType("Test TransType");
//				msgs.setTXInsuranceRequest(tx_request);
//				
//				//������չ��
//				SmsServiceStub.TXInsuranceRequestExtension tx_requestExtension = new SmsServiceStub.TXInsuranceRequestExtension();
//				//tx_requestExtension.setOperator("admin");//userName;
//				//tx_requestExtension.setOperatorKey("Administrator");//password
//				msgs.setTXInsuranceRequestExtension(tx_requestExtension);
//				
//				//ֱ��   ����
////				SmsServiceStub smsServiceStub = new SmsServiceStub("http://10.2.128.4:7001/wssmsif2/services/SmsService");
////				SmsServiceStub.SendSMS sendSms = new SmsServiceStub.SendSMS();	
////				sendSms.setMsgs(msgs);	
////				SmsServiceStub.Response  re =  smsServiceStub.sendSMS(sendSms).get_return();
////				System.out.println(re.getMessage());
//				
//				
//				//�Ż�ͨ
//				SmsServiceStub.SendSMSResponse  sendSMSResponse  = (SmsServiceStub.SendSMSResponse)webService.sendADBObject(msgs,"setMsgs","EC07002");
//				if(sendSMSResponse==null){
//					logger.info("mobilePhone:"+mobile+"���ŷ���ʧ��  ���ص�sendSMSResponse Ϊ��  ��������:"+content);
//				}else{
//					
//					SmsServiceStub.Response  re = sendSMSResponse.get_return();
//					if(re != null&&re.getStatus()!=null&&re.getStatus().equals("OK")){
//						logger.info("mobilePhone:"+mobile+"���ŷ��ͳɹ�"+re.getMessage()+"��������:"+content);
//						result= "1";//�ɹ�
//					}else{
//						logger.info("mobilePhone:"+mobile+"���ŷ���ʧ��"+re.getMessage()+"��������:"+content);
//						result = "2";//���ʧ��
//					}
//				}
				
			}
		} catch (Exception e) {
			result = "2";//���ʧ��
			//���ŷ���webservice�ӿڵ�BizCommonServiceSpringImpl.java���sendMessage��������smsServiceStubʱ����
			logger.info("mobilePhone:"+mobile+"���ŷ���ʧ��  AxisFault e:"+e.getMessage()+"��������:"+content);
			throw BasicBizInfoCommonException.newInstanceCode("001");
		}finally{
			if(result==null||result.equals("")){
				return false;
			}
			if(result.equals("2")){
				return false;
			}else{//1
				return true;
			}
		}
	} 
	//
	private String getTime(){
		//��ǰʱ�������
		Date startDate =new Date();
		Date endDate = new Date(startDate.getTime()+1*24*60*60*1000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(startDate)+"@"+simpleDateFormat.format(endDate);
	}
	
	public Map<String,List<GeKindRelate>> getKindRelateByRiskCode(String riskCode){
		Map<String,List<GeKindRelate>> kindRelateds = new HashMap<String,List<GeKindRelate>>();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.riskCode", riskCode);
		List<GeKindRelate> kindRelateList =  super.find(GeKindRelate.class, queryRule);
		List<GeKindRelate> mainKindCodeList = null;
		for(GeKindRelate kindRelate:kindRelateList){
			String kindCode = kindRelate.getGeKind().getId().getKindCode();
			if(kindRelateds.containsKey(kindCode)){
				mainKindCodeList = kindRelateds.get(kindCode);
			}else{
				mainKindCodeList = new ArrayList<GeKindRelate>();
				kindRelateds.put(kindCode, mainKindCodeList);
			}
			mainKindCodeList.add(kindRelate);
		}
		return kindRelateds;
	}
	
	public boolean sendSMFromLife(List<String> phones,String functionId,String[] params,String beginTime,String endTime){
		URL url = null;

		try
		{
			logger.debug("******************************�������շ��Ͷ��ŷ�������������sendSMFromLife���ֻ��ţ�" + StringUtils.join(phones, ",") + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("��ȡ���ն��ŷ��������ļ��ɹ����ֻ��ţ�" + StringUtils.join(phones, ",") + "��url====" + urlString + "��user_id====" + user_id + "��userName====" + userName + "��pwd====" + pwd);
			//���WebServiceURL
			url = new URL(urlString);
			//���WebService�ӿ�ʵ��
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//���Ͷ���
			String content = geSmsConfigService.getSmsContent(functionId, params);
			logger.debug("��ȡ���ŷ������ݳɹ����������ݣ�" + content);
			logger.debug("****************************************�������սӿڷ��Ͷ��ſ�ʼ**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",beginTime,endTime, "","", 0, "");
			logger.debug("****************************************�������սӿڷ��Ͷ��Ž����������" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("�ֻ��ţ�"+StringUtils.join(phones, ",")+"���ŷ��ͳɹ�,�������ݣ�"+content);
				return true;
			}else {
				logger.info("�ֻ��ţ�"+StringUtils.join(phones, ",")+"���ŷ���ʧ��,���ͽ����"+result+",�������ݣ�"+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + StringUtils.join(phones, ",") + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿڲ���ȷ��������"+ urlString +"���ֻ��ţ�" + StringUtils.join(phones, ",") + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿڲ���ȷ:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿ��޷����ʻ�����ֻ��ţ�" + StringUtils.join(phones, ",") + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿ��޷����ʻ����!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + StringUtils.join(phones, ",") + "����ջ��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Ͷ����쳣:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(String phone,String functionId,String[] params,String beginTime,String endTime){
		URL url = null;

		try
		{
			logger.debug("******************************�������շ��Ͷ��ŷ�������������sendSMFromLife���ֻ��ţ�" + phone + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("��ȡ���ն��ŷ��������ļ��ɹ����ֻ��ţ�" + phone + "��url====" + urlString + "��user_id====" + user_id + "��userName====" + userName + "��pwd====" + pwd);
			//���WebServiceURL
			url = new URL(urlString);
			//���WebService�ӿ�ʵ��
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//���Ͷ���
			String content = geSmsConfigService.getSmsContent(functionId, params);
			logger.debug("��ȡ���ŷ������ݳɹ����������ݣ�" + content);
			List<String> phones = new ArrayList<String>();
			phones.add(phone);
			logger.debug("****************************************�������սӿڷ��Ͷ��ſ�ʼ**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",beginTime,endTime, "","", 0, "");
			logger.debug("****************************************�������սӿڷ��Ͷ��Ž����������" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("�ֻ��ţ�"+phone+"���ŷ��ͳɹ�,�������ݣ�"+content);
				return true;
			}else {
				logger.info("�ֻ��ţ�"+phone+"���ŷ���ʧ��,���ͽ����"+result+",�������ݣ�"+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + phone + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿڲ���ȷ��������"+ urlString +"���ֻ��ţ�" + phone + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿڲ���ȷ:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿ��޷����ʻ�����ֻ��ţ�" + phone + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿ��޷����ʻ����!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + phone + "����ջ��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Ͷ����쳣:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(List<String> phones,String functionId,String[] params){
		URL url = null;

		try
		{
			logger.debug("******************************�������շ��Ͷ��ŷ�������������sendSMFromLife���ֻ��ţ�" + StringUtils.join(phones, ",") + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("��ȡ���ն��ŷ��������ļ��ɹ����ֻ��ţ�" + StringUtils.join(phones, ",") + "��url====" + urlString + "��user_id====" + user_id + "��userName====" + userName + "��pwd====" + pwd);
			//���WebServiceURL
			url = new URL(urlString);
			//���WebService�ӿ�ʵ��
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//���Ͷ���
			String content = geSmsConfigService.getSmsContent(functionId, params);
			logger.debug("��ȡ���ŷ������ݳɹ����������ݣ�" + content);
			String[] times = getLifeTime().split("@");
			logger.debug("****************************************�������սӿڷ��Ͷ��ſ�ʼ**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",times[0],times[1], "","", 0, "");
			logger.debug("****************************************�������սӿڷ��Ͷ��Ž����������" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("�ֻ��ţ�"+StringUtils.join(phones, ",")+"���ŷ��ͳɹ�,�������ݣ�"+content);
				return true;
			}else {
				logger.info("�ֻ��ţ�"+StringUtils.join(phones, ",")+"���ŷ���ʧ��,���ͽ����"+result+",�������ݣ�"+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + StringUtils.join(phones, ",") + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿڲ���ȷ��������"+ urlString +"���ֻ��ţ�" + StringUtils.join(phones, ",") + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿڲ���ȷ:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿ��޷����ʻ�����ֻ��ţ�" + StringUtils.join(phones, ",") + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿ��޷����ʻ����!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + StringUtils.join(phones, ",") + "����ջ��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Ͷ����쳣:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(String phone,String functionId,String[] params){
		
		URL url = null;

		try
		{
			logger.debug("******************************�������շ��Ͷ��ŷ�������������sendSMFromLife���ֻ��ţ�" + phone + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("��ȡ���ն��ŷ��������ļ��ɹ����ֻ��ţ�" + phone + "��url====" + urlString + "��user_id====" + user_id + "��userName====" + userName + "��pwd====" + pwd);
			//���WebServiceURL
			url = new URL(urlString);
			//���WebService�ӿ�ʵ��
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//���Ͷ���
			String content = geSmsConfigService.getSmsContent(functionId, params);
			logger.debug("��ȡ���ŷ������ݳɹ����������ݣ�" + content);
			List<String> phones = new ArrayList<String>();
			phones.add(phone);
			String[] times = getLifeTime().split("@");
			logger.debug("****************************************�������սӿڷ��Ͷ��ſ�ʼ**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",times[0],times[1], "","", 0, "");
			logger.debug("****************************************�������սӿڷ��Ͷ��Ž����������" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("�ֻ��ţ�"+phone+"���ŷ��ͳɹ�,�������ݣ�"+content);
				return true;
			}else {
				logger.info("�ֻ��ţ�"+phone+"���ŷ���ʧ��,���ͽ����"+result+",�������ݣ�"+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + phone + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿڲ���ȷ��������"+ urlString +"���ֻ��ţ�" + phone + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿڲ���ȷ:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿ��޷����ʻ�����ֻ��ţ�" + phone + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿ��޷����ʻ����!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + phone + "����ջ��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Ͷ����쳣:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(List<String> phones,String content){
		URL url = null;

		try
		{
			logger.debug("******************************�������շ��Ͷ��ŷ�������������sendSMFromLife���ֻ��ţ�" + StringUtils.join(phones, ",") + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("��ȡ���ն��ŷ��������ļ��ɹ����ֻ��ţ�" + StringUtils.join(phones, ",") + "��url====" + urlString + "��user_id====" + user_id + "��userName====" + userName + "��pwd====" + pwd);
			//���WebServiceURL
			url = new URL(urlString);
			//���WebService�ӿ�ʵ��
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//���Ͷ���
			String[] times = getLifeTime().split("@");
			logger.debug("****************************************�������սӿڷ��Ͷ��ſ�ʼ**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",times[0],times[1], "","", 0, "");
			logger.debug("****************************************�������սӿڷ��Ͷ��Ž����������" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("�ֻ��ţ�"+StringUtils.join(phones, ",")+"���ŷ��ͳɹ�,�������ݣ�"+content);
				return true;
			}else {
				logger.info("�ֻ��ţ�"+StringUtils.join(phones, ",")+"���ŷ���ʧ��,���ͽ����"+result+",�������ݣ�"+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + StringUtils.join(phones, ",") + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿڲ���ȷ��������"+ urlString +"���ֻ��ţ�" + StringUtils.join(phones, ",") + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿڲ���ȷ:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿ��޷����ʻ�����ֻ��ţ�" + StringUtils.join(phones, ",") + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿ��޷����ʻ����!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + StringUtils.join(phones, ",") + "����ջ��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Ͷ����쳣:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(String phone,String content){
		URL url = null;

		try
		{
			logger.debug("******************************�������շ��Ͷ��ŷ�������������sendSMFromLife���ֻ��ţ�" + phone + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("��ȡ���ն��ŷ��������ļ��ɹ����ֻ��ţ�" + phone + "��url====" + urlString + "��user_id====" + user_id + "��userName====" + userName + "��pwd====" + pwd);
			//���WebServiceURL
			url = new URL(urlString);
			//���WebService�ӿ�ʵ��
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//���Ͷ���
			List<String> phones = new ArrayList<String>();
			phones.add(phone);
			String[] times = getLifeTime().split("@");
			logger.debug("****************************************�������սӿڷ��Ͷ��ſ�ʼ**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",times[0],times[1], "","", 0, "");
			logger.debug("****************************************�������սӿڷ��Ͷ��Ž����������" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("�ֻ��ţ�"+phone+"���ŷ��ͳɹ�,�������ݣ�"+content);
				return true;
			}else {
				logger.info("�ֻ��ţ�"+phone+"���ŷ���ʧ��,���ͽ����"+result+",�������ݣ�"+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + phone + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿڲ���ȷ��������"+ urlString +"���ֻ��ţ�" + phone + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿڲ���ȷ:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣�����Žӿ��޷����ʻ�����ֻ��ţ�" + phone + "���쳣��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Žӿ��޷����ʻ����!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("���սӿڷ��� ���ų����쳣���ֻ��ţ�" + phone + "����ջ��Ϣ��" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("���Ͷ����쳣:"+e.getMessage(), e);
		}
	}
	
	public String getLifeTime(){
		//��ǰʱ���1��
		Date startDate =new Date();
		Date endDate = new Date(startDate.getTime()+1*24*60*60*1000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		return simpleDateFormat.format(startDate)+"@"+simpleDateFormat.format(endDate);
	}
	
	public GeSmsConfigService getGeSmsConfigService() {
		return geSmsConfigService;
	}
	public void setGeSmsConfigService(GeSmsConfigService geSmsConfigService) {
		this.geSmsConfigService = geSmsConfigService;
	}
	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	 /**
	 * ��ȡ��Ŀ����·��
	 * 
	 * @return ��Ŀ·��
	 * @throws Exception
	 *             δ�ҵ�·��
	 */
	public static String getProjectLocalPath() {
		try {
			String path = BizCommonServiceSpringImpl.class.getResource("").getFile();//�˴� ComplaintAction.classΪ��ȡ��ǰ�࣬����������action
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				String temp = "";
				if (StringUtils.isNotBlank(path) && path.length() >= 5)
					temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
			}
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  ��������ձ�ʵ��
	 */
	public String addKind(GeKind geKind) {
		//0�������д������ձ� �����ڲ���
		//1����������
		GeKindId geKindId = geKind.getId();
		GeKind geKindTemp = super.get(GeKind.class, geKindId);
		if(geKindTemp!=null){
			return "0";
		}else{
			String sql = " select nvl(max(orderno),0)+1 from ge_kind where riskcode = ? ";
			Query query = getSession().createSQLQuery(sql);
			query.setString(0,geKind.getId().getRiskCode());
			geKind.setOrderNo(Integer.parseInt(query.uniqueResult().toString()));
			super.save(geKind);
			return "1";
		}
	}
	/**
	 * ��ѯ���ִ��룬��������ѯ ��ҳ��ѯ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 
	 */
	public Page findGeKindList(QueryRule queryRule,int pageNo,int pageSize){
		if(queryRule ==null){
			queryRule =  QueryRule.getInstance();
		}
		return super.find(GeKind.class,queryRule, pageNo, pageSize);
	}
	
	
	/**
	 * �������ִ������ձ�����ѯ
	 */
	public GeKind findGeKindByCode(String riskCode, String kindCode) {
			//��������װ���ִ������ձ����
			GeKindId geKindID = new GeKindId();
			geKindID.setKindCode(kindCode);
			geKindID.setRiskCode(riskCode);
		    GeKind geKind = super.get(GeKind.class,geKindID );
			    if(null !=geKind.getGeKindRelateList()&& geKind.getGeKindRelateList().size()>0 ){
				    List<GeKindRelate>  tempList = geKind.getGeKindRelateList();
					for(GeKindRelate ge : tempList){
						GeKindId geKindIDTEMP = new GeKindId();
						geKindIDTEMP.setKindCode(ge.getId().getRelateKindCode());
						geKindIDTEMP.setRiskCode(ge.getId().getRiskCode());
						GeKind gee = super.get(GeKind.class,geKindIDTEMP);
					    if(null != gee ){
						   ge.setKindRelateCName(gee.getKindCName());
					    }
					}
			    }
		return geKind;
	}
	/**
	 * �޸�ǰ׼��
	 * �������ִ������ձ�����ѯ
	 * 
	 */
	public GeKind prepareUpdateGeKind(String riskCode,String kindCode){
		//��������װ���ִ������ձ����
		GeKindId geKindID = new GeKindId();
		geKindID.setKindCode(kindCode);
		geKindID.setRiskCode(riskCode);
	    GeKind geKind = super.get(GeKind.class,geKindID );
	  return geKind;
	}
	
	/**
	 * �޸��ձ���Ϣ
	 */
	public String updateGeEnKind(GeKind geKind){
		//0�������д������ձ� �����ڲ���
		//1����������
		    super.deleteByPK(GeKind.class,geKind.getId());
		    super.getSession().flush();
		    super.save(geKind);
		    super.update(geKind);
			return "1";
	}		
	/**
	 * �޸��ձ���Ϣ
	 */
	public void updateGeEnKindOrderNo(GeKind geKind){
		    super.update(geKind);
	}		
	
	public String updateGeRisk(GeRisk geRisk) {
		
		   super.update(geRisk);
		  
		   return "1";
		  
		
	}
	
	public void deleteGeRiskListById(String riskCode) {
		String[] riskCodes = riskCode.split(",");
		for(String code:riskCodes){
			String deleteSql = "delete ge_risk g where g.riskCode = ?";
			Query   query = getSession().createSQLQuery(deleteSql);
			query.setString(0, code); 
		
			query.executeUpdate();
		}
		
	}

	
	public String saveGeRisk(GeRisk geRisk) {
		
		GeRisk tempGeRisk = this.findGeRiskByCode(geRisk.getRiskCode());
		   if(tempGeRisk!=null){
			   return "2";
		   }
		   super.save(geRisk);
		   return "1";
	}
	//�Լ���������ѯ��������ʱû�ã���
	public List<GeRisk> findGeRiskListBy(String riskCode,String riskCName) {
		List<GeRisk> geRiskaList=new ArrayList<GeRisk>();
		String selectSql="";
		Query  query = getSession().createSQLQuery(selectSql);
		if("".equals(riskCName)&&!("".equals(riskCode))){
			selectSql = "select *from ge_risk g where g.riskCode = ?";
			query.setString(0, riskCode); 
		}else if(!("".equals(riskCName))&&("".equals(riskCode))){
			selectSql = "select *from ge_risk g where g.riskCName = ?";
			query.setString(0, riskCode); 
		}else{
			selectSql = "select *from ge_risk g where g.riskCode = ? and g.riskCName=?";
			query.setString(0, riskCode); 
			query.setString(1, riskCName); 
		}
		geRiskaList=query.list();
		return geRiskaList;
	}

	public Page getGeCardProductList(GeRisk geRisk, int pageNo, int pageSize) {
		QueryRule queryRule = QueryRule.getInstance();
		if(geRisk.getRiskCName()!=null&&!"".equals(geRisk.getRiskCName())){
		   queryRule.addLike("riskCName", "%"+geRisk.getRiskCName()+"%");
		}
		if(geRisk.getBusinessArea()!=null&&!"".equals(geRisk.getBusinessArea())){
		   queryRule.addEqual("businessArea",geRisk.getBusinessArea());
		}
		Page page = super.find(queryRule, pageNo, pageSize);
		
		return page;
	}


    
	/**
      * ɾ�������ձ���Ϣ
      */
	public void deleteByList(List<GeKind> list) {
		
		super.deleteAll(list);
	}
	/**
	 * ɾ��һ���ձ���Ϣ
	 */
	public void deleteById(String kindCode, String riskCode) {
		//��������װ���ִ������ձ����
		GeKindId geKindID = new GeKindId();
		geKindID.setKindCode(kindCode);
		geKindID.setRiskCode(riskCode);
		super.deleteByPK(GeKind.class,geKindID);
	}
	
	/**
	 * 
	 * @param kindCode
	 * @return ��������
	 *  У����ձ��Ƿ����ײ�����ʹ��
	 */
	public String checkKindCodeUnique(String kindCode,String riskCode){
		String comboFlag = "" ;
		String str = "select k.combocode, k.kindcode, c.riskcode from ge_combo_kind k left join ge_combo c on k.combocode = c.combocode where k.combocode is not null and k.kindcode= ? and c.riskcode= ? ";
		Query query = (Query)getSession().createSQLQuery(str);
		query.setString(0,kindCode);
		query.setString(1,riskCode);
		//BigDecimal size = (BigDecimal)query.uniqueResult();
		List tempList = query.list();
		if(null==tempList||tempList.size()==0){
			comboFlag ="1";
		}else if(tempList.size()>0){
			comboFlag="0";//����ɾ��
		}
		return comboFlag;
	}
	
	/**
	 * 
	 * @param gekind
	 * @return ��������
	 * У��orderno�Ƿ��ظ�
	 */
	public String checkOrderNo(GeKind gekind){
		String hql = "select count(*) from GeKind g where g.id.riskCode = '"+gekind.getId().getRiskCode()+"' and  g.orderNo="+gekind.getOrderNo()+" and g.kindflag='"+gekind.getKindflag()+"' ";
		Query query = (Query)getSession().createQuery(hql);//"+gekind.getId().getRiskCode()+"  "+gekind.getOrderNo()+"
	//	query.setProperties(gekind);
		List tempList = (List)query.list();
		if((Long)tempList.get(0)>0){
			return "0";
		}
		return "1";
	}
	
	/**
	 *  ������ո����չ�ϵ
	 */
	public String addKindRelate(GeKindRelate geKindRelate) {
		//0�������д��������ո����ղ����ظ� �����ڲ���
		//1����������
		GeKindRelateId geKindRelateId = geKindRelate.getId();
		GeKindRelate geKindTemp = super.get(GeKindRelate.class, geKindRelateId);
		if(geKindTemp!=null){
			return "0";
		}else{
			super.save(geKindRelate);
			return "1";
		}
	}
	
	/**
	 * ��ѯ�ձ��ϵ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findKindRelateList(QueryRule queryRule,int pageNo,int pageSize){
		if(queryRule ==null){
			queryRule =  QueryRule.getInstance();
		}
		return super.find(GeKindRelate.class,queryRule, pageNo, pageSize);
	}
	
	
	/**
	 *   �������ִ������ձ�����ѯ �ձ��ϵ
	 */
	public GeKindRelate viewKindRelate(GeKindRelate geKindRelate) {
			//��������װ���ִ������ձ����
		GeKindRelateId ID = new GeKindRelateId();
		ID.setKindCode(geKindRelate.getId().getKindCode());
		ID.setRelateKindCode(geKindRelate.getId().getRelateKindCode());
		ID.setRiskCode(geKindRelate.getId().getRiskCode());
		GeKindRelate geKindRela = super.get(GeKindRelate.class,ID);
		return geKindRela;
	}
	/**
	 * �ձ��ϵά���޸�
	 * @param geKindRelate
	 * @return
	 */
	public String modifyKindRelate(GeKindRelate geKindRelate) {
		//��ȥ�����Լ� ���ж��Ƿ��������ظ�
		super.update(geKindRelate);
	   return "1";
	}
	
	/**
	 * У���ձ��ϵ�Ƿ��ظ�
	 * @return
	 */
	public Map<String,Object> checkRelate(GeKindRelateId relateId){
		List<GeKindRelate> tempList = new ArrayList<GeKindRelate>();
		Map<String,Object> tempMap = new HashMap<String,Object>();
		String kindAddCodes[] = relateId.getRelateKindCode().split("-");
		if(!"".equals(kindAddCodes)){
			for(int i=0;i<kindAddCodes.length;i++){ 
				relateId.getRiskCode();
				relateId.getKindCode();
				relateId.setRelateKindCode(kindAddCodes[i]);
				GeKindRelate geKindTemp = super.get(GeKindRelate.class, relateId);
				if(null != geKindTemp){
					tempList.add(geKindTemp);
				}
			}
	    }
		tempMap.put("tempList", tempList);
	 return  tempMap;
	}
	
	/**
	 * У���Ƿ���ڸ����� �������������Ӹ�����
	 * @return
	 */
	public String checkAddFlag(GeKind ge){
		String flag = "1";
		String sql = " select * from ge_kind  k where k.riskCode=? and k.kindflag=? and k.validind=? ";
	    Query query = super.getSession().createSQLQuery(sql);
	    query.setString(0, ge.getId().getRiskCode());
	    query.setString(1, "02");
	    query.setString(2, "1");
		List<GeKind> tempList = query.list();
		if(tempList.size()<1){
			flag= "2";
		}
		return flag;
	}
	
	/**
	 *  У���Ƿ�����޸��ձ�����
	 */
	@SuppressWarnings("unchecked")
	public String checkFlag(GeKindRelate geRelate,String flag){
		QueryRule query = QueryRule.getInstance();
		StringBuffer  sb = new StringBuffer();
		sb.append(" riskCode='"+geRelate.getId().getRiskCode()+"' ");
		//��Ϊ�������ѯ�����ձ� ���������ѯ��ϵ���� �����Ƿ����
		if("01".equals(flag)){
			sb.append(" and kindCode='"+geRelate.getId().getKindCode()+"' ");
		}else{
			sb.append(" and relateKindCode='"+geRelate.getId().getKindCode()+"' ");
		}
		query.addSql(sb.toString());
		List<GeKindRelate> tempList = super.find(GeKindRelate.class, query);
		if(tempList.size()>0){
			return "2" ;
		}else{
			return "1";
		}
	}
	
	/**
	 * 
	 * @param riskCode
	 * @param relateKindCode
	 * @return ���븽���ղ�ѯ������
	 */
	public List<GeKindRelate> findRelateKindCode(String riskCode, String relateKindCode){
		 String sql = " select kindcode from ge_kind_relate k where k.riskCode=? and k.relateKindCode =? ";
		 Query query = super.getSession().createSQLQuery(sql);
		 query.setString(0,riskCode);
		 query.setString(1, relateKindCode);
		 List<GeKindRelate> relateList = query.list();
		return relateList;
	}
	
	/**
	 *  �жϸø������Ƿ������չ�ϵ 
	 * @param riskCode
	 * @param kindCode
	 * @param kindFlag
	 * @return
	 */
	public Map<String,Object> vaildateRelateCode(String riskCode,String kindCode){
		List tempList = new ArrayList();
		Map<String,Object> tempMap = new HashMap<String,Object>();
		String sql = " select * from ge_kind_relate k where k.riskcode = ? and k.relatekindcode= ? " ;
		Query query = super.getSession().createSQLQuery(sql);
		query.setString(0,riskCode);
		query.setString(1,kindCode);
		tempList  = query.list();
		List temst  = new ArrayList() ;
		if(tempList.size()>0 || tempList !=null){
			for(int i=0;i<tempList.size();i++){
				Object[] obj = (Object[])tempList.get(i);
				String code = obj[1].toString();  //���ձ����
				temst.add(code);
			}
		 tempMap.put("tempList",temst);
		}
		return tempMap;
	}


	
}
