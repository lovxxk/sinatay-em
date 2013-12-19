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
 * 公用Service接口的实现类,提供代码翻译,获取最大号等通用功能
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
	
	
	//*****加载短信接口信息，读的phoneConfig.properties
	private static String ipHost;
	private static String user_id;
	private static String userName;
	private static String pwd;
	
	private static boolean loadPhoneMessage(){
		System.out.println(">>>>>>>>>>>>>>>>>>##############获取短信配置文件路径为："+onlineAbsolutePath);
		
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
		   throw BasicBizInfoCommonException.newInstanceMsg("获取短信配置文件错误!",e1);
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
	//*****加载短信接口信息，实时读取数据库
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
	 * 格式化数字
	 */
//	private static final NumberFormat nf = new DecimalFormat("0000000000");
	private static final NumberFormat nf = new DecimalFormat("000000");
	/**
	 * 初始缓存实例
	 */
	private static CacheService cacheManager = CacheManager.getInstance("code");
	
	/**
	 * 根据代码类型,代码获得代码名称(应用缓存机制)
	 * @param codeType 代码类型
	 * @param codeCode 代码
	 * @return 代码名称
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
	 * 根据代码类型,获得该类型全部代码集合
	 * @param codeType 代码类型
	 * @return 代码集合
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
	 * 获取当前业务的序列号
	 * 
	 * @param typeCode 业务代码
	 * @param date     业务日期
	 * @param clazz    调用时传入,为调用该方法要插入的对应的hibernate实体类
	 * 01 --代表订单号
	 * 02 --代表试算单号
	 * 03 --企业ID流水号
	 * 04 --寿险试算单号
	 * 05 --寿险加购标记
	 * @return (当序列号连续时,返回最大号,不连续时,返回缺失的最小号)
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
		String returnMaxNo = null;// 要返回的序号
		String date = dealWithDate(dateTemp);
		String groupNo = typeCode + date;// 组号,最大号码表中主键字  段对应值
		logger.debug("获取组号为" + groupNo + " 的最大号");
		while(true){
			
			boolean hasGroupNo = super.getCount("from GeMaxNo where id.groupNo=?",groupNo) == 0;// 查询,该groupNo是否有记录、
			
			if (hasGroupNo) {// 没有这个组号的记录,insert一条                            
				
					returnMaxNo = groupNo + nf.format(Integer.parseInt(GeMaxNo.FIRST));  //处理最大号
					GeMaxNo maxNo = new GeMaxNo();
					maxNo.setFlag(GeMaxNo.FLAG_YES);
					maxNo.setValidInd(GeMaxNo.FLAG_YES);
					
					GeMaxNoId id = new GeMaxNoId();
					id.setGroupNo(groupNo);
					id.setMaxNo(GeMaxNo.SECOND);
					
					maxNo.setId(id);
					super.save(maxNo);
				
			} else {// 有这个组号的记录,那么判断,是否有Flag 为 GeMaxNo.FLAG_NO的记录,这个最大号
				
				
					boolean hasFlagNo = super.getCount("from GeMaxNo where id.groupNo=? and flag=?", groupNo,GeMaxNo.FLAG_NO) == 0;
					if (hasFlagNo) {// 如果没有 Flag 为 GeMaxNo.FLAG_NO的记录 那么 目前全部是连续的记录，没有无效的最大号，那么都是连续的最大号
						
						
						// 从数据库中查出,当前groupNo的最大号,然后返回该最大号,然后将该数据删除,增加一条最大号增加1的记录
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
						
					} else {// 如果有Flag,有无效的最大号
						
						
							// 为GeMaxNo.FLAG_NO的记录,那么取,这些记录中,最大号最小的一个,返回该最大号,然后将该数据删除
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
	 * 验证最大号是否已经存在目标表中
	 * @param clazz 掉用时候传入,必须是hibernate持久类
	 * @param maxNo 生成好的,待验证的最大号
	 * @return 验证通过返回true.不通过返回false
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
	 * 当业务表中删除对应的序列号时()需要调用该方法,将删除的序列号送回最大号码表
	 * @param serialNo 要删除的最大号
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
	 * 注意递的日期一定要精确到日 
	 * 不要精确到时分秒
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
	 * 获得省份列表
	 * @return 省份列表集合
	 */
	@SuppressWarnings("unchecked")
	public List<GeArea> findAllProvince() {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("arealevel", GeArea.AREA_LEVEL_PROVINCE);
		return super.find(GeArea.class, rule);
	}
	/**
	 * 根据省份代码获得该省份下城市列表集合
	 * @param upperareacode 省份代码
	 * @return 城市集合
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
	 * 根据地区代码获得地区对象
	 * @param areacode 地区代码
	 * @return 地区对象or NULL
	 */
	public GeArea findAreaByAreacode(String areacode){
		return super.get(GeArea.class,areacode);
	}
	
	
	/**
	 * 根据险种代码获得险种对象
	 * @param riskCode 险种代码
	 * @return 险种对象
	 */
	public GeRisk findGeRiskByCode(String riskCode) {
		Assert.hasText(riskCode, "请传入险种代码");
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
	 * 查询所有险种
	 * @param queryRule
	 * @return
	 * QueryRule queryRule = QueryRule.getInstance();
	 * queryRule.addEqual("riskType", "11");//代表车险
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
	 * 查询险种，按条件查询 分页查询
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
		sb.append("and          direc.businessarea = '3'                ");//车险为的业务领域写死为3
		sb.append("and          direc.isnetsale = '01'                  ");//为网销产品
		sb.append("and          direc.isproductshelf = '01'             ");//为已上架产品
		sb.append("and          risk.businessarea='3'                   ");//险种的业务领域为3
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
				geRiskTemp.setRiskCode(objs[1]==null?"":(String)objs[1]);//险种代码
				geRiskTemp.setRiskCName(objs[2]==null?"":(String)objs[2]);//产品名称
				geRiskList.add(geRiskTemp);
			}
			return geRiskList;
		}
		return null;
	}
	
	public Page findGeRiskListWithEid(GeRisk geRisk,int pageNo,int pageSize){
		long start=0l;//
		long end = 0l;
		long totalSize=0l;//总记录数
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
		sb.append("and direc.businessarea='3'                        ");//车险的业务领域为3财险
		sb.append("and direc.isnetsale ='01'                         ");//是否为网销
		sb.append("and direc.isproductshelf='01'                     ");//上架
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
		sb.append("select geDirectory.eid                ");//产品ID
		sb.append("from   GeDirectory geDirectory        ");//产品目录
		sb.append("where  geDirectory.isNetSale='01'     ");//01代表网销
		sb.append("and    geDirectory.businessArea='3'   ");//03代表业务领域
		sb.append("and    geDirectory.coreProductCode=?  ");//代表车险 
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
	
	//翻译代码名称
	public String findEidName(String riskCode){
		List<String> eids = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("select geDirectory.productName        ");//产品ID
		sb.append("from   GeDirectory geDirectory        ");//产品目录
		sb.append("where  geDirectory.isNetSale='01'     ");//01代表网销
		sb.append("and    geDirectory.businessArea='3'   ");//03代表业务领域
		sb.append("and    geDirectory.coreProductCode=?  ");//代表车险 
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
		Assert.hasText(riskCode, "险种代码不允许为空");
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
	 * 查询所有无效险别
	 * @return
	 */
	public List<GeKind> findInvalidateKind(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("validInd", "0");
		return super.find(GeKind.class,queryRule);
	}
	
	/**
	 * 
	 * 查询用于套餐展示的主险险别列表
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
	 * 查询用于套餐展示的主险险别列表
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
	 * 查询用于套餐展示的附加险险别列表
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
	 * 查询用于套餐展示的附加险险别列表
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
	 * 查询用于套餐展示的不计免赔险别列表
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
	 * 查询用于套餐展示的不计免赔险别列表
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
	 * 得到 核心的险种险别接口
	 */
	public void getCoreRiskAndKind(List<GeRiskClassCodeAndRiskCode> riskCodeList ,java.util.Map<String,RiskAndKind> kindMap){
		//先取我们的数据库里去查询0510 0507 两个险种，若查询出了，那么不同步核心，若没有查询，则同步核心
		if(riskCodeList!=null&&riskCodeList.size()>0){//
			List<String> coreRiskList = new ArrayList<String>();
			java.util.Map<String,String> riskClassCodeAndRiskCodeMap = new HashMap<String, String>();
			
			for(GeRiskClassCodeAndRiskCode geRiskClassCodeAndRiskCodeTemp: riskCodeList){
				GeRisk geRisk  = super.get(geRiskClassCodeAndRiskCodeTemp.getRiskCode());
//				GeRisk geRisk = null;
				if(geRisk==null){//电子商务数据库里不存在，需要同步核心数据
					String riskClassCodeTemp = geRiskClassCodeAndRiskCodeTemp.getRiskClassCode();
					String riskCodeTemp = 	geRiskClassCodeAndRiskCodeTemp.getRiskCode();
					coreRiskList.add(riskCodeTemp);
					riskClassCodeAndRiskCodeMap.put(riskCodeTemp, riskClassCodeTemp);
				}
			}
			//
			if(coreRiskList.size()>0){//同步核心数据
				List<RiskInputCoreDto> riskInputCoreDtoList = new ArrayList<RiskInputCoreDto>();
				for(int i=0;i<coreRiskList.size();i++){
					//调用核心的方法
					RiskInputCoreDto riskInputCoreDto = new RiskInputCoreDto();
					riskInputCoreDto.setRequestType("1");//1-网销
					riskInputCoreDto.setRiskCode((String)coreRiskList.get(i));//险种代码：电销机动车辆保险
					riskInputCoreDto.setRiskClassCode(riskClassCodeAndRiskCodeMap.get((String)coreRiskList.get(i)));//险类代码05
					//设置分页
					IInsuranceExtension iInsuranceExtension = new IInsuranceExtension();
					iInsuranceExtension.setPageFlag("1");//1-为不分页
					iInsuranceExtension.setOrderField("1");//1-不排序
					//设置请求报文
					TXInsuranceRequest tXInsuranceRequest = new TXInsuranceRequest();
					tXInsuranceRequest.setIInsuranceExtension(iInsuranceExtension);//设置分页的
					//用于测试的
					tXInsuranceRequest.setTransRefGUID("111");//交易流水号
					tXInsuranceRequest.setTransType("transType");//交易类型（交易号）
					tXInsuranceRequest.setTransExeDate("2011-01-01");//交易日期
					tXInsuranceRequest.setTransExeTime("05:05:05");//交易时间
					tXInsuranceRequest.setTransSubType("01");//子交易类型
					riskInputCoreDto.settXInsuranceRequest(tXInsuranceRequest);//设置请求报文域
					//设置扩展报文
					TXInsuranceRequestExtension tXInsuranceRequestExtension = new TXInsuranceRequestExtension();//报文扩展域
					tXInsuranceRequestExtension.setOperator("ecom_user");//用户名
					tXInsuranceRequestExtension.setOperatorKey("123456");//密码
					riskInputCoreDto.settXInsuranceRequestExtension(tXInsuranceRequestExtension);//设置请求报文扩展域
					//add List
					riskInputCoreDtoList.add(riskInputCoreDto);
					//end******************************************************************
				}
				
				//调用核心
				List<RiskClauseKindData> riskClauseKindDataList = synchCoreRiskAndKind(riskInputCoreDtoList);
				//测试使用
//				List<RiskClauseKindData> riskClauseKindDataList = new ArrayList<RiskClauseKindData>();
//				//主险
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
//				RiskClauseKindData.setKindFlag("01");//01主险
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
//				RiskClauseKindData2.setKindFlag("02");;//付加险
//				RiskClauseKindData2.setNoDeductFlag("1");
//				
//				riskClauseKindDataList.add(RiskClauseKindData2);
				//处理核心的结果集
				if(riskClauseKindDataList!=null&&riskClauseKindDataList.size()>0){
					List<GeRisk> geRiskReturnList = new ArrayList<GeRisk>();//险种
					List<GeKind> geKindReturnList = new ArrayList<GeKind>();//险别
					List<GeKindRelate> geKindRelateList = new ArrayList<GeKindRelate>();//维护所有的附加险
					setListValue(geRiskReturnList,geKindReturnList,geKindRelateList,riskClauseKindDataList,kindMap);//给以上两个List设置值
					//save 险种险别
					super.saveAll(geRiskReturnList);//保存险种
					super.saveAll(geKindReturnList);//保存险别
					super.saveAll(geKindRelateList);//保存关连险别代码
				}
			}
		}//end if(riskCodeList!=null&&riskCodeList.size()>0){
	}
	
	/**
	 * 同步核心的险种险别接口
	 * @param riskInputCoreDto
	 * @return
	 * @throws AxisFault 
	 */
	public List<RiskClauseKindData> synchCoreRiskAndKind(List<RiskInputCoreDto> riskInputCoreDtoList){
		List<RiskClauseKindData> RiskClauseKindDataList = new ArrayList<RiskClauseKindData>(); //用于存放返回结果的数据的
		return RiskClauseKindDataList;
	}
	
	private void setListValue(List<GeRisk> geRiskList,
			List<GeKind> geKindList,
			List<GeKindRelate> geKindRelateList,
			List<RiskClauseKindData> riskClauseKindDataList,java.util.Map<String,RiskAndKind> kindMap){
		
		java.util.Map<String,List<GeRisk>> mapTemp = new HashMap<String, List<GeRisk>>();
		java.util.Set<String> set = new HashSet<String>();
		for(RiskClauseKindData riskClauseKindDataTemp :riskClauseKindDataList){
			//set GeRisk 得过滤掉重复的
			set.add(riskClauseKindDataTemp.getRiskCode());
			if(mapTemp.get(riskClauseKindDataTemp.getRiskCode())==null){//第一次添加
				GeRisk geRisk = new GeRisk();
				geRisk.setRiskCode(riskClauseKindDataTemp.getRiskCode());
				geRisk.setRiskCName(riskClauseKindDataTemp.getRiskName());
//				geRisk.setRiskEName(riskEName)//核心没有该属性
//				geRisk.setRiskTName(riskTName);//核心没有该属性
				geRisk.setBusinessArea("3");//1-集团；2-寿险；3-财险；4-养老险；9-其他
				geRisk.setRiskType("11");//01  传统险  02  分红 03  投连 04  万能 05  其他 11 车险 12 家财 13 意外 14 其它
//				geRisk.setInsuAccFlag(riskClauseKindDataTemp);寿险专用 核心没有该属性//寿险有 0-有账户 1-无账户
				geRisk.setValidInd("1");//设置是否有效标志:1有效 0无效
//				geRisk.setOperatorID("");//设置操作员规划
				geRisk.setCreateDate(new Date());
				
				List<GeRisk> geRisks = new ArrayList<GeRisk>();
				geRisks.add(geRisk);
				mapTemp.put(riskClauseKindDataTemp.getRiskCode(), geRisks);
			}else{//非第一次添加
				GeRisk geRisk = new GeRisk();
				geRisk.setRiskCode(riskClauseKindDataTemp.getRiskCode());
				geRisk.setRiskCName(riskClauseKindDataTemp.getRiskName());
//				geRisk.setRiskEName(riskEName)//核心没有该属性
//				geRisk.setRiskTName(riskTName);//核心没有该属性
				geRisk.setBusinessArea("3");//1-集团；2-寿险；3-财险；4-养老险；9-其他
				geRisk.setRiskType("11");//01  传统险  02  分红 03  投连 04  万能 05  其他 11 车险 12 家财 13 意外 14 其它
//				geRisk.setInsuAccFlag(riskClauseKindDataTemp);寿险专用 核心没有该属性//寿险有 0-有账户 1-无账户
				geRisk.setValidInd("1");//设置是否有效标志:1有效 0无效
//				geRisk.setOperatorID("");//设置操作员规划
				geRisk.setCreateDate(new Date());
				
				mapTemp.get(riskClauseKindDataTemp.getRiskCode()).add(geRisk);
			}
			//set GeKind
			GeKindId id = new GeKindId();
			id.setRiskCode(riskClauseKindDataTemp.getRiskCode());
			id.setKindCode(riskClauseKindDataTemp.getKindCode());
			GeKind geKind = new GeKind();
			geKind.setId(id);
//			geKind.setCombinekindcode(combinekindcode);//合并代码，核心没有该字段
			geKind.setKindCName(riskClauseKindDataTemp.getKindName());
//			geKind.setKindEName(kindEName)//核心没有该字段
//			geKind.setKindtname("");//核心没有该字段
			geKind.setKindflag(riskClauseKindDataTemp.getKindFlag());//险别标志 01-主险  02-附加险          若是主险得保存关联险别
			//codeType取值类别 ,取值范围ValueRange   从配置文件里取
			if(kindMap!=null&&kindMap.get(riskClauseKindDataTemp.getKindCode())!=null){
				RiskAndKind riskAndKind =  kindMap.get(riskClauseKindDataTemp.getRiskCode()+riskClauseKindDataTemp.getKindCode());
				geKind.setCodeType(riskAndKind.getCodeType());//取kind.xml的配制文件里的内容
				geKind.setValuerange(riskAndKind.getValueRange());//取kind.xml的配置文件里的内容
			}
//			geKind.setOrderNo(orderNo);//自己设置
			geKind.setNodeductflag(riskClauseKindDataTemp.getNoDeductFlag());//不计免赔标志
			geKind.setValidInd("1");//自己设置 是否有效标志：1有效 0无效
//			geKind.setFlag("");//自己设置 
			geKindList.add(geKind);
			
			
			//set  GeKindRelate
			if(riskClauseKindDataTemp.getKindFlag()!=null&&riskClauseKindDataTemp.getKindFlag().equals("02")){//附加险
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
		
		//设置List<GeRisk> geRiskList  过滤掉重复的
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
	
	
	//短信网关的接口
	//public boolean sendMessage(String mobile,String content){
	public boolean sendMessage(String functionId,String[] params,String mobile){
		String result = "";
		String content ="";
		//产生短信的内容 是调用短信模板接口
		try {
			//调用短信模板
			content = geSmsConfigService.getSmsContent(functionId, params);//service
			//content ="this is test message ";///  用于测试
			if(content==null||content.equals("")||mobile==null||mobile.equals("")){//
				logger.info("短信内容为空，或手机号码为空");
				result = "2";
			}else{
				
//				SmsServiceStub.SmsMessage msg = null;
//				SmsServiceStub.SmsMessages msgs = new SmsServiceStub.SmsMessages();
//				
//				
//				msgs.setNeedUseTemplateFlag("false");//设置是否调用的接口模板 true:使用 false:不使用
//				msgs.setTemplatId("CLAIM_15");//要使用的接口模板的id
//				msgs.setTaskId("");//请确保此值在每次通过接口发送时唯一
//				msgs.setTaskValue("备注");//taskid对应的备注字段
//				msgs.setOrganizationId("0");//设置该批短信的OrganizationId，定义同上文下行短信格式中的Organization元素
//				msgs.setExtension("false");//是否设置该批短信的扩展尾号
//				msgs.setExtensionValue("短信扩展尾号");//该批短信的扩展尾号
//				msgs.setServiceType("sgfs");//设置该批短信的ServiceType，定义同上文下行短信格式中的ServiceType元素
//				
//				String[] date = getTime().split("@");
//				//起始日期 结束日期
//				msgs.setStartDate(date[0]);//设置该批短信的StartDate，定义同上文下行短信格式中的StartDate元素
//				msgs.setStartTime("00:00");//设置该批短信的StartTime，定义同上文下行短信格式中的StartTime元素
//				msgs.setEndDate(date[1]);//设置该批短信的EndDate，定义同上文下行短信格式中的EndDate元素
//				msgs.setEndTime("23:59");//设置该批短信的EndTime，定义同上文下行短信格式中的EndTime元素
//				//记录日志
//				logger.info("短信起始日期="+msgs.getStartDate()+" "+msgs.getStartTime());
//				logger.info("短信结束日期="+msgs.getEndDate()+" "+msgs.getEndTime());
//				//
//				msg = new SmsServiceStub.SmsMessage();//创建SmsMessage对象，定义同上文下行短信格式中的Message元素
//				msg.setReceiver(mobile);//设置该条短信的Receiver，定义同上文下行短信格式中的Receiver元素
//				msg.setOrgCode("0");//设置该条短信的发送机构orgCode，定义同上文下行短信格式中的orgCode元素
//				msg.setContents(content);
//				SmsServiceStub.SmsMessage []messages = new SmsServiceStub.SmsMessage []{msg};
//				msgs.setMessages(messages);
//				
//				//请求域
//				SmsServiceStub.TXInsuranceRequest tx_request = new SmsServiceStub.TXInsuranceRequest();
//				tx_request.setTransType("Test TransType");
//				msgs.setTXInsuranceRequest(tx_request);
//				
//				//请求扩展域
//				SmsServiceStub.TXInsuranceRequestExtension tx_requestExtension = new SmsServiceStub.TXInsuranceRequestExtension();
//				//tx_requestExtension.setOperator("admin");//userName;
//				//tx_requestExtension.setOperatorKey("Administrator");//password
//				msgs.setTXInsuranceRequestExtension(tx_requestExtension);
//				
//				//直连   测试
////				SmsServiceStub smsServiceStub = new SmsServiceStub("http://10.2.128.4:7001/wssmsif2/services/SmsService");
////				SmsServiceStub.SendSMS sendSms = new SmsServiceStub.SendSMS();	
////				sendSms.setMsgs(msgs);	
////				SmsServiceStub.Response  re =  smsServiceStub.sendSMS(sendSms).get_return();
////				System.out.println(re.getMessage());
//				
//				
//				//门户通
//				SmsServiceStub.SendSMSResponse  sendSMSResponse  = (SmsServiceStub.SendSMSResponse)webService.sendADBObject(msgs,"setMsgs","EC07002");
//				if(sendSMSResponse==null){
//					logger.info("mobilePhone:"+mobile+"短信发送失败  返回的sendSMSResponse 为空  短信内容:"+content);
//				}else{
//					
//					SmsServiceStub.Response  re = sendSMSResponse.get_return();
//					if(re != null&&re.getStatus()!=null&&re.getStatus().equals("OK")){
//						logger.info("mobilePhone:"+mobile+"短信发送成功"+re.getMessage()+"短信内容:"+content);
//						result= "1";//成功
//					}else{
//						logger.info("mobilePhone:"+mobile+"短信发送失败"+re.getMessage()+"短信内容:"+content);
//						result = "2";//结果失败
//					}
//				}
				
			}
		} catch (Exception e) {
			result = "2";//结果失败
			//短信发送webservice接口的BizCommonServiceSpringImpl.java类的sendMessage方法建立smsServiceStub时出错
			logger.info("mobilePhone:"+mobile+"短信发送失败  AxisFault e:"+e.getMessage()+"短信内容:"+content);
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
		//当前时间加两天
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
			logger.debug("******************************进入寿险发送短信方法，方法名：sendSMFromLife，手机号：" + StringUtils.join(phones, ",") + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("获取寿险短信发送配置文件成功，手机号：" + StringUtils.join(phones, ",") + "，url====" + urlString + "，user_id====" + user_id + "，userName====" + userName + "，pwd====" + pwd);
			//获得WebServiceURL
			url = new URL(urlString);
			//获得WebService接口实例
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//发送短信
			String content = geSmsConfigService.getSmsContent(functionId, params);
			logger.debug("获取短信发送内容成功，短信内容：" + content);
			logger.debug("****************************************调用寿险接口发送短信开始**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",beginTime,endTime, "","", 0, "");
			logger.debug("****************************************调用寿险接口发送短信结束，结果：" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("手机号："+StringUtils.join(phones, ",")+"短信发送成功,短信内容："+content);
				return true;
			}else {
				logger.info("手机号："+StringUtils.join(phones, ",")+"短信发送失败,发送结果："+result+",短信内容："+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + StringUtils.join(phones, ",") + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口不正确》》》》"+ urlString +"，手机号：" + StringUtils.join(phones, ",") + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口不正确:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口无法访问或错误，手机号：" + StringUtils.join(phones, ",") + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口无法访问或错误!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + StringUtils.join(phones, ",") + "，堆栈信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("发送短信异常:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(String phone,String functionId,String[] params,String beginTime,String endTime){
		URL url = null;

		try
		{
			logger.debug("******************************进入寿险发送短信方法，方法名：sendSMFromLife，手机号：" + phone + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("获取寿险短信发送配置文件成功，手机号：" + phone + "，url====" + urlString + "，user_id====" + user_id + "，userName====" + userName + "，pwd====" + pwd);
			//获得WebServiceURL
			url = new URL(urlString);
			//获得WebService接口实例
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//发送短信
			String content = geSmsConfigService.getSmsContent(functionId, params);
			logger.debug("获取短信发送内容成功，短信内容：" + content);
			List<String> phones = new ArrayList<String>();
			phones.add(phone);
			logger.debug("****************************************调用寿险接口发送短信开始**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",beginTime,endTime, "","", 0, "");
			logger.debug("****************************************调用寿险接口发送短信结束，结果：" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("手机号："+phone+"短信发送成功,短信内容："+content);
				return true;
			}else {
				logger.info("手机号："+phone+"短信发送失败,发送结果："+result+",短信内容："+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + phone + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口不正确》》》》"+ urlString +"，手机号：" + phone + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口不正确:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口无法访问或错误，手机号：" + phone + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口无法访问或错误!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + phone + "，堆栈信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("发送短信异常:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(List<String> phones,String functionId,String[] params){
		URL url = null;

		try
		{
			logger.debug("******************************进入寿险发送短信方法，方法名：sendSMFromLife，手机号：" + StringUtils.join(phones, ",") + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("获取寿险短信发送配置文件成功，手机号：" + StringUtils.join(phones, ",") + "，url====" + urlString + "，user_id====" + user_id + "，userName====" + userName + "，pwd====" + pwd);
			//获得WebServiceURL
			url = new URL(urlString);
			//获得WebService接口实例
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//发送短信
			String content = geSmsConfigService.getSmsContent(functionId, params);
			logger.debug("获取短信发送内容成功，短信内容：" + content);
			String[] times = getLifeTime().split("@");
			logger.debug("****************************************调用寿险接口发送短信开始**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",times[0],times[1], "","", 0, "");
			logger.debug("****************************************调用寿险接口发送短信结束，结果：" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("手机号："+StringUtils.join(phones, ",")+"短信发送成功,短信内容："+content);
				return true;
			}else {
				logger.info("手机号："+StringUtils.join(phones, ",")+"短信发送失败,发送结果："+result+",短信内容："+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + StringUtils.join(phones, ",") + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口不正确》》》》"+ urlString +"，手机号：" + StringUtils.join(phones, ",") + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口不正确:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口无法访问或错误，手机号：" + StringUtils.join(phones, ",") + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口无法访问或错误!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + StringUtils.join(phones, ",") + "，堆栈信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("发送短信异常:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(String phone,String functionId,String[] params){
		
		URL url = null;

		try
		{
			logger.debug("******************************进入寿险发送短信方法，方法名：sendSMFromLife，手机号：" + phone + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("获取寿险短信发送配置文件成功，手机号：" + phone + "，url====" + urlString + "，user_id====" + user_id + "，userName====" + userName + "，pwd====" + pwd);
			//获得WebServiceURL
			url = new URL(urlString);
			//获得WebService接口实例
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//发送短信
			String content = geSmsConfigService.getSmsContent(functionId, params);
			logger.debug("获取短信发送内容成功，短信内容：" + content);
			List<String> phones = new ArrayList<String>();
			phones.add(phone);
			String[] times = getLifeTime().split("@");
			logger.debug("****************************************调用寿险接口发送短信开始**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",times[0],times[1], "","", 0, "");
			logger.debug("****************************************调用寿险接口发送短信结束，结果：" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("手机号："+phone+"短信发送成功,短信内容："+content);
				return true;
			}else {
				logger.info("手机号："+phone+"短信发送失败,发送结果："+result+",短信内容："+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + phone + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口不正确》》》》"+ urlString +"，手机号：" + phone + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口不正确:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口无法访问或错误，手机号：" + phone + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口无法访问或错误!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + phone + "，堆栈信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("发送短信异常:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(List<String> phones,String content){
		URL url = null;

		try
		{
			logger.debug("******************************进入寿险发送短信方法，方法名：sendSMFromLife，手机号：" + StringUtils.join(phones, ",") + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("获取寿险短信发送配置文件成功，手机号：" + StringUtils.join(phones, ",") + "，url====" + urlString + "，user_id====" + user_id + "，userName====" + userName + "，pwd====" + pwd);
			//获得WebServiceURL
			url = new URL(urlString);
			//获得WebService接口实例
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//发送短信
			String[] times = getLifeTime().split("@");
			logger.debug("****************************************调用寿险接口发送短信开始**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",times[0],times[1], "","", 0, "");
			logger.debug("****************************************调用寿险接口发送短信结束，结果：" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("手机号："+StringUtils.join(phones, ",")+"短信发送成功,短信内容："+content);
				return true;
			}else {
				logger.info("手机号："+StringUtils.join(phones, ",")+"短信发送失败,发送结果："+result+",短信内容："+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + StringUtils.join(phones, ",") + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口不正确》》》》"+ urlString +"，手机号：" + StringUtils.join(phones, ",") + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口不正确:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口无法访问或错误，手机号：" + StringUtils.join(phones, ",") + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口无法访问或错误!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + StringUtils.join(phones, ",") + "，堆栈信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("发送短信异常:"+e.getMessage(), e);
		}
	}
	
	public boolean sendSMFromLife(String phone,String content){
		URL url = null;

		try
		{
			logger.debug("******************************进入寿险发送短信方法，方法名：sendSMFromLife，手机号：" + phone + "******************************");
			if(!loadPhoneMessage()) return false;
			logger.debug("获取寿险短信发送配置文件成功，手机号：" + phone + "，url====" + urlString + "，user_id====" + user_id + "，userName====" + userName + "，pwd====" + pwd);
			//获得WebServiceURL
			url = new URL(urlString);
			//获得WebService接口实例
			MTWebServiceIFService service = new MTWebServiceIFService(url, new QName(namespaceURI, localPart));
			//发送短信
			List<String> phones = new ArrayList<String>();
			phones.add(phone);
			String[] times = getLifeTime().split("@");
			logger.debug("****************************************调用寿险接口发送短信开始**************************************");
			String result = service.getMTWebServiceIFPort().sendSM(phones, content,user_id , userName, pwd, "serviceid",times[0],times[1], "","", 0, "");
			logger.debug("****************************************调用寿险接口发送短信结束，结果：" + result + "*********************");
			if ("100".equals(result)) {
				logger.info("手机号："+phone+"短信发送成功,短信内容："+content);
				return true;
			}else {
				logger.info("手机号："+phone+"短信发送失败,发送结果："+result+",短信内容："+content);
				return false;
			}
		} catch (BasicBizInfoCommonException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + phone + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg(e.getMsg(), e);
		}catch (MalformedURLException e){
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口不正确》》》》"+ urlString +"，手机号：" + phone + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口不正确:"+urlString, e);
		}catch (WebServiceException e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常：短信接口无法访问或错误，手机号：" + phone + "，异常信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("短信接口无法访问或错误!", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("寿险接口发送 短信出现异常，手机号：" + phone + "，堆栈信息：" + e.getMessage());
			throw BasicBizInfoCommonException.newInstanceMsg("发送短信异常:"+e.getMessage(), e);
		}
	}
	
	public String getLifeTime(){
		//当前时间加1天
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
	 * 获取项目物理路径
	 * 
	 * @return 项目路径
	 * @throws Exception
	 *             未找到路径
	 */
	public static String getProjectLocalPath() {
		try {
			String path = BizCommonServiceSpringImpl.class.getResource("").getFile();//此处 ComplaintAction.class为获取当前类，及方法所在action
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
	 *  添加险种险别实现
	 */
	public String addKind(GeKind geKind) {
		//0代表已有此险种险别 不能在插入
		//1代表可以添加
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
	 * 查询险种代码，按条件查询 分页查询
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
	 * 根据险种代码与险别代码查询
	 */
	public GeKind findGeKindByCode(String riskCode, String kindCode) {
			//用主键封装险种代码与险别代码
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
	 * 修改前准备
	 * 根据险种代码与险别代码查询
	 * 
	 */
	public GeKind prepareUpdateGeKind(String riskCode,String kindCode){
		//用主键封装险种代码与险别代码
		GeKindId geKindID = new GeKindId();
		geKindID.setKindCode(kindCode);
		geKindID.setRiskCode(riskCode);
	    GeKind geKind = super.get(GeKind.class,geKindID );
	  return geKind;
	}
	
	/**
	 * 修改险别信息
	 */
	public String updateGeEnKind(GeKind geKind){
		//0代表已有此险种险别 不能在插入
		//1代表可以添加
		    super.deleteByPK(GeKind.class,geKind.getId());
		    super.getSession().flush();
		    super.save(geKind);
		    super.update(geKind);
			return "1";
	}		
	/**
	 * 修改险别信息
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
	//自己的条件查询方法，暂时没用！！
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
      * 删除多条险别信息
      */
	public void deleteByList(List<GeKind> list) {
		
		super.deleteAll(list);
	}
	/**
	 * 删除一条险别信息
	 */
	public void deleteById(String kindCode, String riskCode) {
		//用主键封装险种代码与险别代码
		GeKindId geKindID = new GeKindId();
		geKindID.setKindCode(kindCode);
		geKindID.setRiskCode(riskCode);
		super.deleteByPK(GeKind.class,geKindID);
	}
	
	/**
	 * 
	 * @param kindCode
	 * @return 返回条数
	 *  校验该险别是否在套餐上面使用
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
			comboFlag="0";//不能删除
		}
		return comboFlag;
	}
	
	/**
	 * 
	 * @param gekind
	 * @return 返回条数
	 * 校验orderno是否重复
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
	 *  添加主险附加险关系
	 */
	public String addKindRelate(GeKindRelate geKindRelate) {
		//0代表已有此险种主险附加险不能重复 不能在插入
		//1代表可以添加
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
	 * 查询险别关系列表
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
	 *   根据险种代码与险别代码查询 险别关系
	 */
	public GeKindRelate viewKindRelate(GeKindRelate geKindRelate) {
			//用主键封装险种代码与险别代码
		GeKindRelateId ID = new GeKindRelateId();
		ID.setKindCode(geKindRelate.getId().getKindCode());
		ID.setRelateKindCode(geKindRelate.getId().getRelateKindCode());
		ID.setRiskCode(geKindRelate.getId().getRiskCode());
		GeKindRelate geKindRela = super.get(GeKindRelate.class,ID);
		return geKindRela;
	}
	/**
	 * 险别关系维护修改
	 * @param geKindRelate
	 * @return
	 */
	public String modifyKindRelate(GeKindRelate geKindRelate) {
		//先去除它自己 在判断是否主键有重复
		super.update(geKindRelate);
	   return "1";
	}
	
	/**
	 * 校验险别关系是否重复
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
	 * 校验是否存在附加险 若不存在则增加附加险
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
	 *  校验是否可以修改险别类型
	 */
	@SuppressWarnings("unchecked")
	public String checkFlag(GeKindRelate geRelate,String flag){
		QueryRule query = QueryRule.getInstance();
		StringBuffer  sb = new StringBuffer();
		sb.append(" riskCode='"+geRelate.getId().getRiskCode()+"' ");
		//若为主险则查询险种险别 附加险则查询关系代码 看看是否关联
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
	 * @return 传入附加险查询主险码
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
	 *  判断该附加险是否有主险关系 
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
				String code = obj[1].toString();  //主险别代码
				temst.add(code);
			}
		 tempMap.put("tempList",temst);
		}
		return tempMap;
	}


	
}
