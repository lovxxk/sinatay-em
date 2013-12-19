package cn.com.sinosoft.ebusiness.common.party.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ntp.TimeStamp;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import cn.com.sinosoft.ebusiness.common.party.domain.DistributionStatusDto;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterContact;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterGoods;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterInfo;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterOrder;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterSerialNumber;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterService;
import cn.com.sinosoft.ebusiness.common.party.service.facade.ThirdParterService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.PartyCommonException;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.portalModule.interfacePortal.webService.service.WebService;

/**
 * 第三方公司合作伙伴的整体处理
 */
public class ThirdParterServiceSpringImpl extends
		GenericDaoHibernate<GeThirdParterInfo,String> implements ThirdParterService {
	
	private static final Log logger = LogFactory.getLog(ThirdParterServiceSpringImpl.class);
	/**门户通接口服务类*/
	private WebService webService;
	private GeDepartmentService geDepartmentService;
	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	/**
	 * 
	 *  GE_ThirdParter_Info 维护
	 *  第三方合作伙伴信息
	 * 
	 * 
	 */
	
	/**
	 * 保存第三方合作伙伴信息-GE_ThirdParter_Info 
	 * @param geThirdParterInfo
	 */
	public void addGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo){
		geThirdParterInfo.setCreateDate(new TimeStamp(new Date()).getDate());
		super.save(geThirdParterInfo);
		//加上这个是要求将 子表的uuid赋值
		List<GeThirdParterContact> list = geThirdParterInfo.getGeThirdParterContacts();
		if(list!=null&&list.size()>0){
			for(int i=0,n=list.size();i<n;i++){
				list.get(i).setGeThirdParterInfo(geThirdParterInfo);
			}
		}
	}
	/**
	 * 查询第三方合作伙伴信息 按主键来查询
	 * @param thirdParterID
	 * @return
	 */
	public GeThirdParterInfo findGeThirdParterInfoByThirdParterID(String thirdParterID){
		//thirdParterID为主键
		GeThirdParterInfo geThirdParterInfo = super.get(GeThirdParterInfo.class, thirdParterID);
		return geThirdParterInfo;
	}
	/**
	 * 查询第三方合作伙伴信息 分页查询
	 * @return
	 */
	public Page findGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo,int pageNo,int pageSize){
		QueryRule queryRule = QueryRule.getInstance();
		
		if(geThirdParterInfo!=null){
			//thirdPartterName
			if(geThirdParterInfo.getThirdParterName()!=null&&!"".equals(geThirdParterInfo.getThirdParterName())){
				queryRule.addLike("thirdParterName", "%"+geThirdParterInfo.getThirdParterName()+"%");
			}
			//Telephone
			if(geThirdParterInfo.getCompanyPhone()!=null&&!"".equals(geThirdParterInfo.getCompanyPhone())){
				queryRule.addEqual("companyPhone", geThirdParterInfo.getCompanyPhone());
			}
			//Address
			if(geThirdParterInfo.getAddress()!=null&&!"".equals(geThirdParterInfo.getAddress())){
				queryRule.addLike("address", "%"+geThirdParterInfo.getAddress()+"%");
			}
			//Email
			if(geThirdParterInfo.getEmail()!=null&&!"".equals(geThirdParterInfo.getEmail())){
				queryRule.addEqual("email", geThirdParterInfo.getEmail());
			}
			//URL
			if(geThirdParterInfo.getUrl()!=null&&!"".equals(geThirdParterInfo.getUrl())){
				queryRule.addLike("url", "%"+geThirdParterInfo.getUrl()+"%");
			}
			//BusinessArea
			if(geThirdParterInfo.getBusinessArea()!=null&&!"".equals(geThirdParterInfo.getBusinessArea())){
				queryRule.addLike("businessArea", "%"+geThirdParterInfo.getBusinessArea()+"%");
			}
			//companyType
			if(geThirdParterInfo.getCompanyType()!=null&&!"".equals(geThirdParterInfo.getCompanyType())){
				queryRule.addEqual("companyType", geThirdParterInfo.getCompanyType());
			}
			queryRule.addDescOrder("createDate");
			//deptID
			if(geThirdParterInfo.getDeptID()!=null&&!"".equals(geThirdParterInfo.getDeptID())){
				if(geThirdParterInfo.getDeptName()!=null&&!"".equals(geThirdParterInfo.getDeptName())&&geThirdParterInfo.getDeptName().equals("isParentFlag")){//借用字段判断是否为父结点
					if(geThirdParterInfo.getDeptID().equals("1")||geThirdParterInfo.getDeptID().equals("2")||geThirdParterInfo.getDeptID().equals("3")||geThirdParterInfo.getDeptID().equals("4")){
						StringBuilder sb  = new StringBuilder();
						sb.append(" exists(  ");
						sb.append("select * from ge_department g where g.deptid = this_.deptid start with g.businessarea='"+geThirdParterInfo.getDeptID()+"' connect by prior  g.deptid=g.parentid  ");
						sb.append(")");
						queryRule.addSql(sb.toString());
					}else{
						StringBuilder sb  = new StringBuilder();
						sb.append(" exists(  ");
						sb.append("select * from ge_department g where g.deptid = this_.deptid start with g.parentid='"+geThirdParterInfo.getDeptID()+"' connect by prior  g.deptid=g.parentid  ");
						sb.append(")");
						queryRule.addSql(sb.toString());
					}
					
				}else{
					queryRule.addEqual("deptID", geThirdParterInfo.getDeptID());
				}
			}
		}
		return super.find(queryRule, pageNo, pageSize);
	}
	
	public Page findGeThirdParterInfoByDeptManayBydeptId(GeThirdParterInfo geThirdParterInfo,int pageNo,int pageSize){
		QueryRule queryRule = QueryRule.getInstance();
		if(geThirdParterInfo!=null){
			boolean isConditionFlag = false;
			//thirdPartterName
			if(geThirdParterInfo.getThirdParterName()!=null&&!"".equals(geThirdParterInfo.getThirdParterName())){
				isConditionFlag = true;
				queryRule.addLike("thirdParterName", "%"+geThirdParterInfo.getThirdParterName()+"%");
			}
			//Telephone
			if(geThirdParterInfo.getCompanyPhone()!=null&&!"".equals(geThirdParterInfo.getCompanyPhone())){
				isConditionFlag = true;
				queryRule.addEqual("companyPhone", geThirdParterInfo.getCompanyPhone());
			}
			//Address
			if(geThirdParterInfo.getAddress()!=null&&!"".equals(geThirdParterInfo.getAddress())){
				isConditionFlag = true;
				queryRule.addLike("address", "%"+geThirdParterInfo.getAddress()+"%");
			}
			//Email
			if(geThirdParterInfo.getEmail()!=null&&!"".equals(geThirdParterInfo.getEmail())){
				isConditionFlag = true;
				queryRule.addEqual("email", geThirdParterInfo.getEmail());
			}
			//URL
			if(geThirdParterInfo.getUrl()!=null&&!"".equals(geThirdParterInfo.getUrl())){
				isConditionFlag = true;
				queryRule.addLike("url", "%"+geThirdParterInfo.getUrl()+"%");
			}
			//BusinessArea
			/*if(geThirdParterInfo.getBusinessArea()!=null&&!"".equals(geThirdParterInfo.getBusinessArea())){
				isConditionFlag = true;
				queryRule.addLike("businessArea", "%"+geThirdParterInfo.getBusinessArea()+"%");
			}*/
			//companyType
			if(geThirdParterInfo.getCompanyType()!=null&&!"".equals(geThirdParterInfo.getCompanyType())){
				isConditionFlag = true;
				queryRule.addEqual("companyType", geThirdParterInfo.getCompanyType());
			}
			//deptID
			if(geThirdParterInfo.getDeptID()!=null&&!"".equals(geThirdParterInfo.getDeptID())){
				String deptId = geThirdParterInfo.getDeptID();
					if(geThirdParterInfo.getBusinessArea()!=null&&!"".equals(geThirdParterInfo.getBusinessArea())){
						isConditionFlag = true;
						deptId+=","+geThirdParterInfo.getBusinessArea();
					}
				getSql( queryRule, deptId);
			}
			queryRule.addDescOrder("createDate");
		}
		return super.find(queryRule, pageNo, pageSize);
	}
	
	private void getSql(QueryRule queryRule,String authorityid){
		String[] authorityIdAll = authorityid.split(",");
		if(authorityIdAll.length >= 1000){
			String sql="(this_.DEPTID in";
			if(authorityIdAll!=null && authorityIdAll.length > 0){
				int authorityIdSize = authorityIdAll.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						sql+="or this_.deptid in";
					}
					int loopNum = 1000;
					if(j == authorityIdNumber-1){
						loopNum = authorityIdSize - (authorityIdNumber-1)*1000;
					}
					for (int i = 0; i < loopNum; i++) {
						String childDeptId =(String)authorityIdAll[1000*j+i];
						if(i == 0){
							nextParentIdString += "'"+childDeptId+"'";
						}else{
							nextParentIdString += ",'"+childDeptId+"'";
						}
					}
					sql += "("+nextParentIdString+")";
				}
				sql+=")";
			}
			queryRule.addSql(sql);
		}else{
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<authorityIdAll.length;i++){
				sb.append("'");
				sb.append(""+authorityIdAll[i]+"");
				sb.append("'");
				sb.append(",");
			}
			queryRule.addSql(" this_.DEPTID in("+sb.toString().subSequence(0, sb.toString().length()-1)+")");
		}
	}
	/**
	 * 按条件来查询,自己组装条件
	 * @return
	 */
	public Page findGeThirdParterInfoByCondition(QueryRule queryRule,int pageNo,int pageSize){
		return super.find(queryRule, pageNo, pageSize);

	}
	/**
	 * 查询第三方合作伙伴信息 
	 * 不进行分页查询分页查询
	 * @return
	 */
	public List<GeThirdParterInfo> findGethiGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo){
		QueryRule queryRule = QueryRule.getInstance();
		if(geThirdParterInfo!=null){
			if(geThirdParterInfo.getCompanyType()!=null&&!geThirdParterInfo.getCompanyType().equals("")){
				queryRule.addEqual("companyType", geThirdParterInfo.getCompanyType());
			}
			if(geThirdParterInfo.getBusinessArea()!=null&&!geThirdParterInfo.getBusinessArea().equals("")){
				queryRule.addEqual("", geThirdParterInfo.getBusinessArea());
			}
		}
		return super.find(queryRule);
	}
	
	
	/**
	 * 查询第三方合作伙伴  查询全部
	 * @return
	 */
	public List<GeThirdParterInfo> findGeThirdParterInfoAll(){
		QueryRule queryRule = QueryRule.getInstance();
		return super.find(GeThirdParterInfo.class,queryRule);
	}
	/**
	 * 查询第三方合作伙伴  查询全部 通过机构权限来查询
	 * @return
	 */
	public List<GeThirdParterInfo> findGeThirdParterInfoByAuthorityId(String authorityId){
		
		//
		QueryRule queryRule = QueryRule.getInstance();
		return super.find(GeThirdParterInfo.class,queryRule);
	}
	
	/**
	 * 更新 第三方合作伙伴信息-GE_ThirdParter_Info
	 * 更新联系人
	 * @param geThirdParterInfo
	 */
	public void updateGeThirdParterInfoAndGeThirdParterContact(GeThirdParterInfo geThirdParterInfo) throws Exception{
		if(geThirdParterInfo!=null&&geThirdParterInfo.getThirdParterID()!=null&&!"".equals(geThirdParterInfo.getThirdParterID())){
			String deleteSql = "delete GeThirdParterContact as geThirdParterContact where geThirdParterContact.geThirdParterInfo.thirdParterID=:thirdParterID";
			Query   query2 = getSession().createQuery(deleteSql);
			query2.setParameter("thirdParterID", geThirdParterInfo.getThirdParterID()); 
			query2.executeUpdate();
			super.save(geThirdParterInfo);
		}else{

			System.out.println("thirdParterID的值不能为空");
			throw PartyCommonException.newInstanceCode("000");
		}
	}
	/**
	 * 第三方合作伙伴 按主键 删除
	 * @param geThirdParterInfo
	 */
	public boolean deleteGeThirdParterInfo(String thirdParterID){
		
		//先看这个供应商下是否有商品，若有商品则提示他先删除产品 ，若全部产品都删除完了 ，才可以删除公司
	  GeThirdParterInfo	geThirdParterInfo = super.get(thirdParterID);
	  if(geThirdParterInfo.getGeThirdParterServices()!=null&&geThirdParterInfo.getGeThirdParterServices().size()>0){
		return false;  
	  }else{
		  super.deleteByPK(thirdParterID);
		return true;
	  }
		
		
	}
	/**
	 * GE_ThirdParter_Contact 维护 
	 * 第三方联系人信息
	 */
	
	/**
	 * 保存第三方联系人
	 */
	public void addGeThirdParterContact(GeThirdParterContact geThirdParterContact){
		super.save(geThirdParterContact);
	}
	/**
	 * 保存第三方联系人---批量保存
	 */
	public void addGeThirdParterContactList(List<GeThirdParterContact> geThirdParterContactList){
		super.saveAll(geThirdParterContactList);
	}
	/**
	 * 查询第三方联系人 通过主键查询
	 */
	public GeThirdParterContact findGeThirdParterContactByContactID(String contactID){
		//contactID为主键
		GeThirdParterContact geThirdParterContact = super.get(GeThirdParterContact.class, contactID);
		return geThirdParterContact;
	}
	public List<GeThirdParterContact> findGeThirdParterContactList(GeThirdParterContact geThirdParterContact){
		QueryRule queryRule = QueryRule.getInstance();
		if(geThirdParterContact!=null){
			
			//thirdPartterName
//			if(geThirdParterInfo.getThirdParterName()!=null&&!"".equals(geThirdParterInfo.getThirdParterName())){
//				queryRule.addLike("thirdParterName", geThirdParterInfo.getThirdParterName());
//			}
		}
		return null;
	}
	/**
	 * 删除第三方联系人 按主键删除
	 * 删除多条的时候 contactID按","间隔 
	 */
	public void deleteGeThirdParterContactByContactID(String contactID){
//		super.deleteByPK(GeThirdParterContact.class,contactID);
		List<GeThirdParterContact> geThirdParterContactList = new ArrayList<GeThirdParterContact>(); 
		if(contactID!=null&&!"".equals(contactID)){
			String[] contactIDs = contactID.split(",");
			for(int i=0;i<contactIDs.length;i++){
				GeThirdParterContact geThirdParterContact = new GeThirdParterContact();
				geThirdParterContact.setContactID(contactIDs[i]);
				geThirdParterContactList.add(geThirdParterContact);
			}
			super.deleteAll(geThirdParterContactList);
		}
		
	}
	/**
	 * 保存第三方服务产品
	 * @param geThirdParterService
	 */
	public void addGeThirdParterService(GeThirdParterService geThirdParterService){
		if(geThirdParterService!=null
			&&geThirdParterService.getGeThirdParterInfo()!=null
			&&geThirdParterService.getGeThirdParterInfo().getThirdParterID()!=null
			&&!"".equals(geThirdParterService.getGeThirdParterInfo().getThirdParterID())){
			//
			GeThirdParterInfo geThirdParterInfoTemp =super.get(GeThirdParterInfo.class, geThirdParterService.getGeThirdParterInfo().getThirdParterID());
			geThirdParterService.setGeThirdParterInfo(geThirdParterInfoTemp);
			geThirdParterService.setCreateDate(new Date());
			geThirdParterService.setItemPicture(null);
			super.save(geThirdParterService);
			try {
				//扩展名
				String attrPictureName = geThirdParterService.getItemID()+FileUtils.getFileNameExt(geThirdParterService.getAttrPictureFileName());
				System.out.println("******************************************attrPictureName="+attrPictureName);
				System.out.println("******************************************ThirdParterServiceSpringImpl.java类的addGeThirdParterService方法");
				System.out.println("#####################="+geThirdParterService.getImageAbsolutePath()+java.io.File.separator+attrPictureName);
				File attrPictureForMisFile = new File(geThirdParterService.getImageAbsolutePath()+ java.io.File.separator+ attrPictureName);//往mis上传图片
				//File attrPictureNameForOnline = new File(geThirdParterService.getImageOnlineAbsolutePath()+ attrPictureName);//往online上传图片
				
				FileUtils.write(FileUtils.readBytes(geThirdParterService.getAttrPicture()), attrPictureForMisFile);
				//FileUtils.write(FileUtils.readBytes(geThirdParterService.getAttrPicture()), attrPictureNameForOnline);//往online上传文件
				geThirdParterService.setPictureUrl(geThirdParterService.getImagePath()+attrPictureName);
			} catch (IOException e) {
				//第三方商品服务的时候，在上传第三方商品图片的时候出现文件读写异常
				e.printStackTrace();
				throw PartyCommonException.newInstanceCode("004");//000 表示thirdParterID的值不能为空
			} catch (Exception e) {
				e.printStackTrace();
			}//往mis上传文件
			
		}else{
			throw PartyCommonException.newInstanceCode("000");//000 表示thirdParterID的值不能为空
		}
		
	}
	/**
	 * 查询第三方服务产品
	 * 分页查询
	 * @param geThirdParterService
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeThirdParterService(GeThirdParterService geThirdParterService,int pageNo,int pageSize){
		QueryRule queryRule = QueryRule.getInstance();
		if(geThirdParterService.getItemName()!=null&&!"".equals(geThirdParterService.getItemName())){//按项目名称查询
			queryRule.addLike("itemName", "%"+geThirdParterService.getItemName()+"%");
		}
		if(geThirdParterService.getGeThirdParterInfo()!=null
				&&geThirdParterService.getGeThirdParterInfo().getThirdParterID()!=null
				&&!(geThirdParterService.getGeThirdParterInfo().getThirdParterID()).equals("all")){
			queryRule.addEqual("geThirdParterInfo", geThirdParterService.getGeThirdParterInfo());
		}
		queryRule.addDescOrder("createDate");
		Page page = super.find(GeThirdParterService.class, queryRule, pageNo, pageSize);
		if(page!=null&&page.getResult()!=null&&page.getResult().size()>0){
			List<GeThirdParterService> geThirdParterServiceList = page.getResult();
			for (GeThirdParterService geThirdParterServiceTemp : geThirdParterServiceList) {
				if(geThirdParterServiceTemp.getTotalNumber()!=null&&!geThirdParterServiceTemp.getTotalNumber().equals("")){
					String send = "0";
					if(geThirdParterServiceTemp.getSend()!=null&&!geThirdParterServiceTemp.getSend().equals("")){
						send = geThirdParterServiceTemp.getSend();
					}
					String surplus =(Integer.parseInt(geThirdParterServiceTemp.getTotalNumber())-Integer.parseInt(send))+"";
					geThirdParterServiceTemp.setSurplus(surplus);//剩余量
				}
			}
		}
		return page ; 
	}
	/**
	 * 查询第三方服务产品
	 * 不分页并且按条件查询
	 * @param geThirdParterService
	 * @return
	 */
	public List<GeThirdParterService> findGeThirdParterService(GeThirdParterService geThirdParterService){
		QueryRule queryRule = QueryRule.getInstance();
		if(geThirdParterService!=null){
			if(geThirdParterService.getGeThirdParterInfo()!=null
				&&geThirdParterService.getGeThirdParterInfo().getThirdParterID()!=null
				&&!geThirdParterService.getGeThirdParterInfo().getThirdParterID().equals("")){
				queryRule.addEqual("geThirdParterInfo", geThirdParterService.getGeThirdParterInfo());
			}
		}
		return super.find(GeThirdParterService.class, queryRule);
	}
	/**
	 * 查询第三方服务产品
	 * 接主键查询
	 */
	public GeThirdParterService findGeThirdParterServiceByItemID(String itemID){
		return super.get(GeThirdParterService.class, itemID);
	}
	
	//查询第三方产品 
	public GeThirdParterService findGeThirdParterServiceByItemIDForView(String itemID){
		GeThirdParterService geThirdParterServiceTemp =  super.get(GeThirdParterService.class, itemID);
		if(geThirdParterServiceTemp.getTotalNumber()!=null&&!geThirdParterServiceTemp.getTotalNumber().equals("")){
			String send = "0";
			if(geThirdParterServiceTemp.getSend()!=null&&!geThirdParterServiceTemp.getSend().equals("")){
				send = geThirdParterServiceTemp.getSend();
			}
			String surplus =(Integer.parseInt(geThirdParterServiceTemp.getTotalNumber())-Integer.parseInt(send))+"";
			geThirdParterServiceTemp.setSurplus(surplus);//剩余量
			//
			//各个分公司的汇总
			List<GeThirdParterSerialNumber> geThirdParterSerialNumberList = geThirdParterServiceTemp.getGeThirdParterSerialNumbers();
			Map<String,List<GeThirdParterSerialNumber>> map = new HashMap<String, List<GeThirdParterSerialNumber>>();
			if(geThirdParterSerialNumberList!=null&&geThirdParterSerialNumberList.size()>0){//根据地域来分
				for (GeThirdParterSerialNumber geThirdParterSerialNumber : geThirdParterSerialNumberList) {
					if(geThirdParterSerialNumber.getValidInd().equals("1")){//有效
						GeDepartment geDepartment = geDepartmentService.findGeDepartmentByPK(geThirdParterSerialNumber.getProposalArea());
						geThirdParterSerialNumber.setProposalAreaName(getBusinessName(geDepartment)+geDepartment.getDeptname());
						if(map.get(geThirdParterSerialNumber.getProposalArea())==null){
							List<GeThirdParterSerialNumber> geThirdParterSerialNumberTempList = new ArrayList<GeThirdParterSerialNumber>();
							geThirdParterSerialNumberTempList.add(geThirdParterSerialNumber);
							map.put(geThirdParterSerialNumber.getProposalArea(), geThirdParterSerialNumberTempList);
						}else{
							List<GeThirdParterSerialNumber> geThirdParterSerialNumberTempList = map.get(geThirdParterSerialNumber.getProposalArea());
							geThirdParterSerialNumberTempList.add(geThirdParterSerialNumber);
						}
					}
				}
			}
			geThirdParterServiceTemp.setMap(map);
		}
		
		return geThirdParterServiceTemp; 
	}
	
	private String getBusinessName(GeDepartment geDepartment){
		String businessAreaName=""; //业务领域的名字
		String businessArea = geDepartment.getBusinessarea();
		if(businessArea.equals("2")){
			businessAreaName="寿";
		}
		if(businessArea.equals("3")){
			businessAreaName="财";
		}
		if(businessArea.equals("4")){
			businessAreaName="养老";
		}
		return businessAreaName;
	}
	
	
	public Page findGeThirdParterServiceByDefaultPermession(HttpSession session ,GeThirdParterService geThirdParterService,int pageNo,int pageSize){
		Page page = null;
		
		//先得拿出默认公司的ID没有分页
		Map map = (Map) session.getAttribute("permission");
		String authorityid = (String) map.get("ROLE_B_PTPI");//第三方最外层的权限名字
		if(geThirdParterService.getGeThirdParterInfo().getBusinessArea()!=null&&!geThirdParterService.getGeThirdParterInfo().getBusinessArea().equals("")){
			authorityid+=","+geThirdParterService.getGeThirdParterInfo().getBusinessArea();
		}
		List<String> values = Arrays.asList(authorityid.split(","));
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if(values.size() >= 1000){
			String sql1 = authorityid.substring(0,authorityid.indexOf(values.get(values.size()/2+1)));
			sql1 = sql1.substring(0,sql1.length()-1);
			String sql2 = authorityid.substring(authorityid.indexOf(values.get(values.size()/2+1)));
			String sql3 = " (DEPTID in ('" + sql1.replaceAll(",", "','") + "') or DEPTID in ('" + sql2.replaceAll(",", "','") + "')) ";
			queryRule.addSql(sql3);
		}else{
			queryRule.addIn("deptID", values);
		}
		queryRule.addDescOrder("createDate");
		List<GeThirdParterInfo> geThirdParterInfoList =   super.find(queryRule);//得到了默认的机构
		
		
		
		if(geThirdParterInfoList!=null&&geThirdParterInfoList.size()>0){
			List<String> valuesTemp = new ArrayList<String>();
			String authorityidTemp  ="";
			for (GeThirdParterInfo geThirdParterInfoTemp : geThirdParterInfoList) {
				valuesTemp.add(geThirdParterInfoTemp.getThirdParterID());
				authorityidTemp = authorityidTemp+geThirdParterInfoTemp.getThirdParterID()+",";
			}
			authorityidTemp = authorityidTemp.substring(0, authorityidTemp.length()-1);
			QueryRule queryRuleTemp = QueryRule.getInstance();
			if(valuesTemp.size()>= 1000){
				
				String sql1Temp = authorityidTemp.substring(0,authorityidTemp.indexOf(valuesTemp.get(valuesTemp.size()/2+1)));
				sql1Temp = sql1Temp.substring(0,sql1Temp.length()-1);
				String sql2Temp = authorityidTemp.substring(authorityidTemp.indexOf(valuesTemp.get(valuesTemp.size()/2+1)));
				String sql3Temp = " (THIRDPARTERID in ('" + sql1Temp.replaceAll(",", "','") + "') or THIRDPARTERID in ('" + sql2Temp.replaceAll(",", "','") + "')) ";
				queryRuleTemp.addSql(sql3Temp);
			}else{
				queryRuleTemp.addIn("geThirdParterInfo.thirdParterID", valuesTemp);
			}
			if(geThirdParterService.getItemName()!=null&&!"".equals(geThirdParterService.getItemName())){//按项目名称查询
				queryRuleTemp.addLike("itemName", "%"+geThirdParterService.getItemName()+"%");
			}
			queryRuleTemp.addDescOrder("createDate");
			page = super.find(GeThirdParterService.class, queryRuleTemp, pageNo, pageSize);
			if(page!=null&&page.getResult()!=null&&page.getResult().size()>0){
				List<GeThirdParterService> geThirdParterServiceList = page.getResult();
				for (GeThirdParterService geThirdParterServiceTemp : geThirdParterServiceList) {
					if(geThirdParterServiceTemp.getTotalNumber()!=null&&!geThirdParterServiceTemp.getTotalNumber().equals("")){
						String send = "0";
						if(geThirdParterServiceTemp.getSend()!=null&&!geThirdParterServiceTemp.getSend().equals("")){
							send = geThirdParterServiceTemp.getSend();
						}
						String surplus =(Integer.parseInt(geThirdParterServiceTemp.getTotalNumber())-Integer.parseInt(send))+"";
						geThirdParterServiceTemp.setSurplus(surplus);//剩余量
					}
				}
			}
		}
		return page;
	}
	/**
	 * 修改第三方产品
	 * @param geThirdParterService
	 */
	public void updateGeThirdParterService(GeThirdParterService geThirdParterService){
		GeThirdParterService geThirdParterServiceTemp = super.get(GeThirdParterService.class, geThirdParterService.getItemID());
		geThirdParterServiceTemp.setGeThirdParterInfo(geThirdParterService.getGeThirdParterInfo());//公司名称
		geThirdParterServiceTemp.setItemName(geThirdParterService.getItemName());//商品名称
		geThirdParterServiceTemp.setItemContent(geThirdParterService.getItemContent());//商品简介
		//geThirdParterServiceTemp.setItemPicture(geThirdParterService.getItemPicture());//商品图片
		geThirdParterServiceTemp.setPictureUrl(geThirdParterService.getPictureUrl());//商品图片
		geThirdParterServiceTemp.setTotalNumber(geThirdParterService.getTotalNumber());//商品数量
		
		try {
			
			if(geThirdParterService.getAttrPictureFileName()!=null){
				//扩展名
				String attrPictureName = geThirdParterServiceTemp.getItemID()+FileUtils.getFileNameExt(geThirdParterService.getAttrPictureFileName());
				File attrPictureForMisFile = new File(geThirdParterService.getImageAbsolutePath() +java.io.File.separator+ attrPictureName);//往mis上传图片
				System.out.println("*********************************************updateGeThirdParterService上传的文件名字:"+geThirdParterService.getImageAbsolutePath()+ java.io.File.separator+attrPictureName);
				FileUtils.write(FileUtils.readBytes(geThirdParterService.getAttrPicture()), attrPictureForMisFile);
				//FileUtils.write(FileUtils.readBytes(geThirdParterService.getAttrPicture()), attrPictureNameForOnline);//往online上传文件
				geThirdParterServiceTemp.setPictureUrl(geThirdParterService.getImagePath()+attrPictureName);
			}
		} catch (IOException e) {
			//第三方商品服务的时候，在上传第三方商品图片的时候出现文件读写异常ThirdParterServiceSpringImpl类的updateGeThirdParterService方法
			throw PartyCommonException.newInstanceCode("005");//
		} catch (Exception e) {
			//第三方商品服务的时候，在上传第三方商品图片的时候出现文件读写异常ThirdParterServiceSpringImpl类的updateGeThirdParterService方法
			throw PartyCommonException.newInstanceCode("005");//
		}
		super.update(geThirdParterServiceTemp);
	}
	/**
	 *删除第三方产品  这个是第一版的代码 ，以 GeAddServiceActivity  和 GeActivitiesRule  为准 
	 *按主键删除 ，删除多条时候以","分隔
	 */
	//public java.util.Map<String, List<GeThirdParterService>> deleteGeThirdPartterServiceByItemID(String itemID){  用于批量时使用的
	public boolean deleteGeThirdPartterServiceByItemID(String itemID){  
		java.util.Map<String, List<GeThirdParterService>> map = new HashMap<String, List<GeThirdParterService>>();//返回的结果
		
		if(itemID!=null&&!"".equals(itemID)){
			String[] itemIDs = itemID.split(",");
			for(int i=0;i<itemIDs.length;i++){
				
				GeThirdParterService geThirdParterService = new GeThirdParterService();
				geThirdParterService.setItemID(itemIDs[i]);
				
				GeThirdParterSerialNumber geThirdParterSerialNumber = new GeThirdParterSerialNumber();
				geThirdParterSerialNumber.setGeThirdParterService(geThirdParterService);
				
				List list = queryGeThirdParterSerialNumber(geThirdParterSerialNumber);
				//如果
				if(list!=null&&list.size()>0){
					//那么该产品已经产用于增值服务了,即不能删除
//					以上注掉的代码是用于批量时的使用的
//					if(map.get("notDelete")==null){
//						
//						List<GeThirdParterService> notDelGeThirdParterServiceList  = new ArrayList<GeThirdParterService>();
//						notDelGeThirdParterServiceList.add(findGeThirdParterServiceByItemID(itemIDs[i]));
//						map.put("notDelete", notDelGeThirdParterServiceList);
//						
//					}else{
//						map.get("notDelete").add(findGeThirdParterServiceByItemID(itemIDs[i]));
//					}
					return false;
				}else{//要以删除
//					以上注掉的代码是用于批量时的使用的
//					if(map.get("delete")==null){//可以删除的
//						List<GeThirdParterService> delGeThirdParterServiceList  = new ArrayList<GeThirdParterService>();
//						delGeThirdParterServiceList.add(findGeThirdParterServiceByItemID(itemIDs[i]));
//						map.put("delete", delGeThirdParterServiceList);
//					}else{
//						map.get("delete").add(findGeThirdParterServiceByItemID(itemIDs[i]));
//					}
//					//geThirdParterServiceList.add(geThirdParterService);
					super.deleteByPK(GeThirdParterService.class, itemIDs[i]);
					return true;
				}
			}// end for
		}// end if(itemID!=null&&!"".equals(itemID)){
		//return map;
		return true;
	}
	
	/**
	 * 保存第三方产品/服务流水信息表-GE_ThirdParter_SerialNumber
	 */
	public void addGeThirdParterSerialNumber(GeThirdParterSerialNumber geThirdParterSerialNumber){
		super.save(geThirdParterSerialNumber);
	}
	/**
	 * 根据投保单号去查商品 
	 * @return
	 */
	public List<GeThirdParterGoods> findGeThirdParterGoods(List<String> proposalNoList){
		List<GeThirdParterGoods> geThirdParterGoodsList = null;
		
		if(proposalNoList!=null&&proposalNoList.size()>0){
			QueryRule queryRule  = QueryRule.getInstance();
			queryRule.addIn("proposalNo", proposalNoList);
			//
			geThirdParterGoodsList = new ArrayList<GeThirdParterGoods>();
			List<GeThirdParterSerialNumber> geThirdParterSerialNumberList =  super.find(GeThirdParterSerialNumber.class, queryRule);
			if(geThirdParterSerialNumberList!=null&&geThirdParterSerialNumberList.size()>0){
				for(GeThirdParterSerialNumber geThirdParterSerialNumberTemp:geThirdParterSerialNumberList){
					GeThirdParterGoods geThirdParterGoods = new GeThirdParterGoods();
					geThirdParterGoods.setProposalNo(geThirdParterSerialNumberTemp.getProposalNo());
					geThirdParterGoods.setItemName(geThirdParterSerialNumberTemp.getGeThirdParterService().getItemName());
					geThirdParterGoods.setItemAccount(geThirdParterSerialNumberTemp.getCount());
					geThirdParterGoodsList.add(geThirdParterGoods);
				}
			}
			
		}
		return geThirdParterGoodsList;
	}
	
	public List  queryGeThirdParterSerialNumber(GeThirdParterSerialNumber geThirdParterSerialNumber){
		List<GeThirdParterService> geThirdParterServices = new ArrayList<GeThirdParterService>();
		//去增值服务活动去查询
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct  addservice.activityId,                                         ");
	    sb.append("       addservice.status,                                                       ");   
	    sb.append("       addservice.validInd,                                                     ");
	    sb.append("       activitiesRule.itemID                                                    ");
	    sb.append("from   GeAddServiceActivity addservice,                                         ");
	    sb.append("       GeActivitiesRule activitiesRule                                          ");
	    sb.append("where  activitiesRule.itemID=?                                                  ");
	    sb.append("and    addservice.activityId = activitiesRule.geAddServiceActivity.activityId   ");
	    //sb.append("and    addservice.validInd = '0'                                                ");//状态为无效的
	    Query query = getSession().createQuery(sb.toString());
	    query.setString(0, geThirdParterSerialNumber.getGeThirdParterService().getItemID());
	    List list = query.list();
		return list;
	}
	/**
	 * 查询要赠送的商品，并且进行分页
	 * @param geThirdParterSerialNumber
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeThirdParterSerialNumber(boolean isDefaultPermission,HttpSession session,GeThirdParterSerialNumber geThirdParterSerialNumber,int pageNo,int pageSize){
		QueryRule queryRule = QueryRule.getInstance();
		if(geThirdParterSerialNumber!=null){
			if(geThirdParterSerialNumber.getProposalNo()!=null
				&&!geThirdParterSerialNumber.getProposalNo().equals("")){
				queryRule.addEqual("proposalNo", geThirdParterSerialNumber.getProposalNo());
			}
			if(geThirdParterSerialNumber.getUserID()!=null
				&&!geThirdParterSerialNumber.getUserID().equals("")){
				queryRule.addEqual("userID", geThirdParterSerialNumber.getUserID());
			}
			
			if(isDefaultPermission){//默认的机构权限
				Map map = (Map) session.getAttribute("permission");
				String authorityid = (String) map.get("ROLE_B_TPSG_M");//第三方最外层的权限名字
				
				List<String> values = Arrays.asList(authorityid.split(","));
				if(values.size() >= 1000){
					String sql1 = authorityid.substring(0,authorityid.indexOf(values.get(values.size()/2+1)));
					sql1 = sql1.substring(0,sql1.length()-1);
					String sql2 = authorityid.substring(authorityid.indexOf(values.get(values.size()/2+1)));
					String sql3 = " (this_.proposalArea in ('" + sql1.replaceAll(",", "','") + "') or this_.proposalArea in ('" + sql2.replaceAll(",", "','") + "')) ";
					queryRule.addSql(sql3);
				}else{
					queryRule.addIn("proposalArea", values);
				}
			}else{//非默认机构权限,有指定的 
				queryRule.addEqual("proposalArea", geThirdParterSerialNumber.getProposalArea());
			}
		}
		Page page =    super.find(GeThirdParterSerialNumber.class, queryRule, pageNo, pageSize);
		if(page!=null&&page.getResult()!=null&&page.getResult().size()>0){
			List<GeThirdParterSerialNumber> geThirdParterSerialNumberList = page.getResult();
			for (GeThirdParterSerialNumber geThirdParterSerialNumberTemp : geThirdParterSerialNumberList) {
				if(geThirdParterSerialNumberTemp.getProposalArea()!=null&&!geThirdParterSerialNumberTemp.getProposalArea().equals("")){
					GeDepartment geDepartment =  geDepartmentService.findGeDepartmentByPK(geThirdParterSerialNumberTemp.getProposalArea());
					geThirdParterSerialNumberTemp.setProposalAreaName(geDepartment.getDeptname());//投保地区
				}
			}
			return page;
		}else{
			return null;
		}
		//对投保地区进行翻译
		//return super.find(queryRule, pageNo, pageNo);
	}
	/**
	 * 更新状态，将无效改为有效
	 * @param proposalNos
	 */
	public void updateGeThirdParterSerialNumberValidInd(String searialNo){
		if(searialNo!=null&&!searialNo.equals("")){
			String[]searialNos= searialNo.split(",");
			if(searialNos!=null&&searialNos.length>0){
				for(int i=0;i<searialNos.length;i++){
					GeThirdParterSerialNumber geThirdParterSerialNumber = super.get(GeThirdParterSerialNumber.class, searialNos[i]);
					geThirdParterSerialNumber.setValidInd("1");//改为有效
					
					//商品发送数
					GeThirdParterService geThirdParterService =   geThirdParterSerialNumber.getGeThirdParterService();
					if(geThirdParterSerialNumber.getGeThirdParterService().getSend()!=null&&!geThirdParterSerialNumber.getGeThirdParterService().equals("")){//
						geThirdParterSerialNumber.getGeThirdParterService().setSend((Integer.parseInt(geThirdParterService.getSend())+1)+"");
					}else{
						geThirdParterSerialNumber.getGeThirdParterService().setSend("1");
					}
					super.update(geThirdParterSerialNumber);
				}
			}
		}
	}
	/**
	 * 更新状态，将有效改为无效
	 * @param proposalNos
	 */
	public void updateGeThirdParterSerialNumberInValidInd(String searialNo){
		if(searialNo!=null&&!searialNo.equals("")){
			String[]searialNos= searialNo.split(",");
			if(searialNos!=null&&searialNos.length>0){
				for(int i=0;i<searialNos.length;i++){
					GeThirdParterSerialNumber geThirdParterSerialNumber = super.get(GeThirdParterSerialNumber.class, searialNos[i]);
					geThirdParterSerialNumber.setValidInd("0");//改为有效
					
					if(geThirdParterSerialNumber.getGeThirdParterService().getSend()!=null&&!geThirdParterSerialNumber.getGeThirdParterService().getSend().equals("")){
						String send = geThirdParterSerialNumber.getGeThirdParterService().getSend();
						geThirdParterSerialNumber.getGeThirdParterService().setSend((Integer.parseInt(send)-1)+"");
					}
					super.update(geThirdParterSerialNumber);
				}//end
			}
		}
	}
	
	/**
	 * 第三方订单关系表-GE_ThirdParter_Order
	 * parm orderNo
	 * parm waybillNo
	 * parm thirdParterNo
	 * @param geThirdParterOrder
	 */
	public void addGeThirdParterOrder(GeThirdParterOrder geThirdParterOrder){
		
		if(geThirdParterOrder!=null
			&&geThirdParterOrder.getOrderNo()!=null
			&&!"".equals(geThirdParterOrder.getOrderNo())
			&&geThirdParterOrder.getWayBillNo()!=null
			&&!"".equals(geThirdParterOrder.getWayBillNo())
			&&geThirdParterOrder.getGeThirdParterInfo()!=null
			&&geThirdParterOrder.getGeThirdParterInfo().getThirdParterID()!=null
			&&!"".equals(geThirdParterOrder.getGeThirdParterInfo().getThirdParterID())){
			super.save(geThirdParterOrder); 
		}else{
			throw PartyCommonException.newInstanceCode("001");//订单号第三方公司ID:thirdParterID;运单号:WayBillNo;订单号OrderNo都为必录项
		}
	}
	/**
	 * 通过订单号来查询
	 * @param orderNo
	 * @return
	 */
	public GeThirdParterOrder findGeThirdParterOrderByOrderNo(String orderNo){
		if(orderNo!=null&&!orderNo.equals("")){
//			QueryRule queryRule = QueryRule.getInstance();
//			queryRule.addEqual("orderNo", orderNo);
//			return super.find(GeThirdParterOrder.class, queryRule);
			return super.get(GeThirdParterOrder.class, orderNo);
		}else{
			throw PartyCommonException.newInstanceCode("002");//002 订单号orderNo不能为空
		}
	}
	/**
	 * 更改第三方订单表信息
	 * @param geThirdParterOrder
	 */
	public void updateGeThirdParterOrder(GeThirdParterOrder geThirdParterOrder){
		super.update(geThirdParterOrder);
	}
	/**
	 * 根据订单号查询物流信息
	 * @param 订单号 -bookNo
	 * @return
	 */
	public DistributionStatusDto getDistributionStatus(String bookNo) {
		DistributionStatusDto distributionStatusDto = null ;
//		GetNsDistributionStatusInfo  entityInfo44 = new GetNsDistributionStatusInfo();
//		NetSalesServiceStub.NsDistributionStatusInfoRequest  nsDistributionStatusInfoRequest =new NetSalesServiceStub.NsDistributionStatusInfoRequest(); 
//		nsDistributionStatusInfoRequest.setOrderNo(bookNo);
//		entityInfo44.setNsDistributionStatusInfoRequest(nsDistributionStatusInfoRequest);
//		NetSalesServiceStub.GetNsDistributionStatusInfoResponse response = null;
//		try {
//			response = (NetSalesServiceStub.GetNsDistributionStatusInfoResponse)webService.sendADBObject(nsDistributionStatusInfoRequest, "setNsDistributionStatusInfoRequest", "EC06004");
//		} catch (Exception e) {
//			throw PartyCommonException.newInstanceMsg("物流查看错误", e);
//		}
//		NetSalesServiceStub.NsDistributionStatusInfoResponse rnReturn = response.get_return();
//		if(rnReturn!=null&&"0".equals(rnReturn.getTXInsuranceResponse().getTransResult().getResultCode())){
//			distributionStatusDto = new DistributionStatusDto();
//			NetSalesServiceStub.DistributionStatus coreDistbutionStatus = rnReturn.getDistributionStatus();
//			distributionStatusDto.setAddressee(coreDistbutionStatus.getAddressee());
//			distributionStatusDto.setBookNo(coreDistbutionStatus.getOrderNo());
//			distributionStatusDto.setPostAddress(coreDistbutionStatus.getPostAddress());
//			distributionStatusDto.setPostArea(coreDistbutionStatus.getPostArea());
//			distributionStatusDto.setPostCode(coreDistbutionStatus.getPostCode());
//			distributionStatusDto.setState(coreDistbutionStatus.getState());
//		}
		return distributionStatusDto;
	}

	//查询赠送产品
	public GeThirdParterSerialNumber findGeThirdParterSerialNumberByNo(String searialNo) {
		return super.get(GeThirdParterSerialNumber.class, searialNo);
	}

	@Override
	public List<GeThirdParterService> findGeThirdParterServiceByDeptId(String deptId, int pageNo,
			int pageSize,GeThirdParterService geThirdParterService) {
		//拿deptId去查看他是否有子结点，若没有子结点，那么按正常的查询，若没有子结点，说明他是父结点走父结点的查询方法
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("parentid", deptId);
		List<GeDepartment> geDepartmentList= geDepartmentService.findAllDepartments(queryRule);

		if(geDepartmentList!=null&&geDepartmentList.size()>0){//有子结点   说明他是父结点的查询
			
			return findGeThirdParterServiceByParentNode(deptId,pageNo,pageSize,true,geThirdParterService);//true代表父结点
		}else{//没有子结点了的查询  说明他已经是子节点了
			return findGeThirdParterServiceByParentNode(deptId,pageNo,pageSize,false,geThirdParterService);//true代表父结点
		}
	}
	
	//记录
	public Long findGeThirdParterServiceByDeptIdCount(String deptId,GeThirdParterService geThirdParterService) {
		//拿deptId去查看他是否有子结点，若没有子结点，那么按正常的查询，若有子结点，说明他是父结点走父结点的查询方法
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("parentid", deptId);
		List<GeDepartment> geDepartmentList= geDepartmentService.findAllDepartments(queryRule);
		if(geDepartmentList!=null&&geDepartmentList.size()>0){//有子结点   说明他是父结点的查询
			return findGeThirdParterServiceByDeptIdNodeCount(deptId,true,geThirdParterService);//代表父结点
		}else{//没有子结点了的查询  说明他已经是子节点了
			return findGeThirdParterServiceByDeptIdNodeCount(deptId,false,geThirdParterService);//false代表子结点
		}
	}
	//通过父结点来查询所有的第三方产品 
	private  long findGeThirdParterServiceByDeptIdNodeCount(String deptId,boolean isParentFlag,GeThirdParterService geThirdParter){
		
		//还是换成自定义的SQL
		StringBuilder sb = new StringBuilder();
		sb.append("select     count(*)                                   ");
		sb.append("from       ge_thirdparter_info info,                  ");
		sb.append("           ge_thirdparter_service thirdService        ");
		sb.append("where      exists                                     ");
		if(isParentFlag){//父ID结点用的 递归出它下的所有城市
			sb.append("(select    *                                          ");
			sb.append(" from      ge_department m                            ");
			sb.append(" where     m.deptid = info.deptid                     ");
			sb.append(" start     with m.businessarea=?                          ");//需要赋的参数
			sb.append(" connect   by prior                                   ");
			sb.append(" m.deptid= m.parentid )                               ");
			sb.append(" and       info.thirdparterid=thirdService.Thirdparterid ");
		}else{//子结点
			sb.append("(select    *                                          ");
			sb.append(" from      ge_department m                            ");
			sb.append(" where     m.deptid = info.deptid                     ");
			sb.append(" and       m.deptid in (?,"+geThirdParter.getGeThirdParterInfo().getBusinessArea()+") )                               ");
			sb.append(" and       info.thirdparterid=thirdService.Thirdparterid     ");
		}
		if(geThirdParter.getItemName()!=null&&!geThirdParter.getItemName().equals("")){
			sb.append("      and thirdService.itemName like  '%"+geThirdParter.getItemName()+"%' ");
		}
		if(geThirdParter.getGeThirdParterInfo().getThirdParterID()!=null&&!geThirdParter.getGeThirdParterInfo().getThirdParterID().equals("")){
			sb.append("      and info.thirdParterID= '"+geThirdParter.getGeThirdParterInfo().getThirdParterID()+"' ");
		}
		sb.append(" order by thirdService.createdate desc                                                      ");
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		sqlQuery.setString(0, deptId);
		List list= sqlQuery.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				BigDecimal bigDecimal = (BigDecimal)list.get(i);
				int count = bigDecimal.intValue();
				return Long.parseLong(String.valueOf(count));
			}
		}
		return 0l;
	}
	public static void main(String[] args) {
		System.out.println(Long.parseLong(String.valueOf(4)));
	}
	//
	private List<GeThirdParterService> findGeThirdParterServiceByParentNode(String deptId,int pageNo,int pageSize,boolean isParentFlag,GeThirdParterService geThirdParter){
		int start = (pageNo-1)*pageSize+1;
		int end =   pageNo*pageSize;
		List<GeThirdParterService> geThirdParterServiceList = new ArrayList<GeThirdParterService>();//返回的结果集
		
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		sb.append("SELECT * FROM                                                                     "); 
		sb.append("(                                                                                 ");
		sb.append("   select A.*, ROWNUM RN                                                          "); 
		sb.append("   from                                                                           ");
		sb.append("   (                                                                              ");
		sb.append("      select thirdService.Itemid as itemid,                                       ");
		sb.append("             thirdService.Itemname as itemName,                                          ");
		sb.append("             info.thirdpartername as thirdpartername,                                    ");
		sb.append("		        thirdService.Totalnumber as Totalnumber,                                    ");
		sb.append("             thirdService.Send as Send,                                                  ");
		sb.append("             thirdService.Createdate as Createdate                                       ");
		sb.append("      from    ge_thirdparter_info info ,ge_thirdparter_service thirdService              "); 
		sb.append("      where exists                                                                       ");
if(isParentFlag){
		sb.append("      (select * from ge_department m where m.deptid = info.deptid start with m.businessarea=? connect by prior m.deptid= m.parentid ) ");	
}else{
		sb.append("      (select * from ge_department m where m.deptid = info.deptid and m.deptid in (?,"+geThirdParter.getGeThirdParterInfo().getBusinessArea()+") )     ");	
}
		if(geThirdParter.getItemName()!=null&&!geThirdParter.getItemName().equals("")){
			sb.append("      and thirdService.itemName like  '%"+geThirdParter.getItemName()+"%' ");
		}
		if(geThirdParter.getGeThirdParterInfo().getThirdParterID()!=null&&!geThirdParter.getGeThirdParterInfo().getThirdParterID().equals("")){
			sb.append("      and info.thirdParterID= '"+geThirdParter.getGeThirdParterInfo().getThirdParterID()+"' ");
		}
		sb.append("      and info.thirdparterid=thirdService.Thirdparterid ");
		sb.append(" order by thirdService.createdate desc                                                      ");
		sb.append("   ) A                                                                              ");
		sb.append("   WHERE ROWNUM <= ? ");
		sb.append(")                                                                                   ");
		sb.append("WHERE RN >= ?                                                                       ");
		
		SQLQuery sqlQuery =   getSession().createSQLQuery(sb.toString());;
		sqlQuery.setString(0, deptId);
		sqlQuery.setInteger(1, end);
		sqlQuery.setInteger(2, start);
		List list = sqlQuery.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[])list.get(i);
				GeThirdParterService geThirdParterService = new GeThirdParterService();
				geThirdParterService.setItemID((String)objs[0]);//商品ID
				geThirdParterService.setItemName((String)objs[1]);//商品名称
				GeThirdParterInfo geThirdParterInfo = new GeThirdParterInfo();
				geThirdParterInfo.setThirdParterName((String)objs[2]);//公司名称
				geThirdParterService.setGeThirdParterInfo(geThirdParterInfo);//公司名称
				geThirdParterService.setTotalNumber((String)objs[3]);//商品数量
				geThirdParterService.setSend((String)objs[4]);//商品已送出
				String totalNumber  = (String)objs[3];
				String send = (String)objs[4];
				geThirdParterService.setSurplus(setSurplus(totalNumber,send));//商品库存量
				geThirdParterService.setCreateDate((Date)objs[5]);//商品创建日期
				geThirdParterServiceList.add(geThirdParterService);
			}
		}
		return geThirdParterServiceList;
	}
	
	private  String setSurplus(String totalNumber,String send){
		String resut = "";
		if(totalNumber!=null&&!totalNumber.equals("")){
			if(send!=null&&!send.equals("")){
				
			}else{
				send = "0";
			}
			resut = String.valueOf((Integer.parseInt(totalNumber)-Integer.parseInt(send)));
		}
		return resut;
	}
}
