package cn.com.sinosoft.ebusiness.common.bizConfig.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.Arrays;
import java.util.List;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeDeptMail;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeDeptMailService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
/**
 *
 */
public class GeDeptMailServiceSpringImpl extends
		GenericDaoHibernate<GeDeptMail, String> implements GeDeptMailService {
	private GeCodeService geCodeService;
	private GeDepartmentService geDepartmentService;
	
   public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}
	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}
/**
    * ����������ѯ��������
    * @param deptID
    * @return
    */
	@LocusTrace(setDesc="����������ѯ��������")
   public GeDeptMail getGeDeptMailByPK(String deptMailID){
	   GeDeptMail geDeptMail = super.get(deptMailID); 
	   if(geDeptMail!=null){
		   if(geDeptMail.getDeptID()!=null&&!geDeptMail.getDeptID().equals("")){
			   GeDepartment geDepartMent = geDepartmentService.findGeDepartmentByPK(geDeptMail.getDeptID());
						int buarea = Integer.parseInt(geDepartMent.getBusinessarea());
						if(geDepartMent!=null){
						   if(Integer.parseInt(geDepartMent.getDeptlevel())>2){
							   switch(buarea) {
								   case 3:
									   geDeptMail.setDepartNmae("��-"+geDepartMent.getDeptname());
									   break;
								   case 2:
									   geDeptMail.setDepartNmae("��-"+geDepartMent.getDeptname());
									   break;
								  
							      case 4:
							    	  geDeptMail.setDepartNmae("����-"+geDepartMent.getDeptname());
								   break;
						      }
						   }else{
							   geDeptMail.setDepartNmae(geDepartMent.getDeptname());
						   }
						}
		   }
		   if(geDeptMail.getFunctionID()!=null&&!"".equals(geDeptMail.getDeptID())){
			   geDeptMail.setSendMailName(geCodeService.findCodeCName(geDeptMail.getFunctionID(), "SendMailType"));
		   }
	   }
	   return geDeptMail;
   }
   /**
    * ���ݲ�ѯ������ѯ��Ӧ�Ļ�������
    * @param geDeptMail
    * @param pageNo
    * @param pageSize
    * @param ind
    * @return
   */
	@LocusTrace(setDesc="���ݲ�ѯ������ѯ��Ӧ�Ļ�������")
   public Page getGeDeptMailList(GeDeptMail geDeptMail,int pageNo,int pageSize,String ind,String authorityid){
	   QueryRule queryRule = QueryRule.getInstance();
	   if(geDeptMail.getFunctionID()!=null&&!"".equals(geDeptMail.getFunctionID())){
		   queryRule.addEqual("functionID", geDeptMail.getFunctionID());
	   }
	   if("1".equals(ind)){
		   if(geDeptMail.getMail()!=null&&!"".equals(geDeptMail.getMail())){
			   queryRule.addLike("mail", '%'+geDeptMail.getMail()+'%');
		   }
	   }
       if(geDeptMail.getDeptID()!=null&&!"".equals(geDeptMail.getDeptID())){
    	   String[] deptIDS = geDeptMail.getDeptID().split(",");
    	   queryRule.addIn("deptID",Arrays.asList(deptIDS));
       }
       queryRule.addAscOrder("deptID");
       queryRule.addDescOrder("createTime");
       List<String> values = Arrays.asList(authorityid.split(","));
		
		if(values.size() >= 1000){
			String[] authorityIdAll = authorityid.split(",");
			String sql="(deptID in";
			if(authorityIdAll!=null && authorityIdAll.length > 0){
				int authorityIdSize = authorityIdAll.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						sql+="or deptID in";
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
			queryRule.addIn("deptID", values);
		}


	   Page page =  super.find(queryRule, pageNo, pageSize);
	   List<GeDeptMail> geDeptMailList = page.getResult();
		for(GeDeptMail tempGeDeptMail:geDeptMailList){
			 if(tempGeDeptMail.getDeptID()!=null&&!tempGeDeptMail.getDeptID().equals("")){
				GeDepartment geDepartMent = geDepartmentService.findGeDepartmentByPK(tempGeDeptMail.getDeptID());
				int buarea = Integer.parseInt(geDepartMent.getBusinessarea());
				if(geDepartMent!=null){
				   if(Integer.parseInt(geDepartMent.getDeptlevel())>2){
					   switch(buarea) {
					   case 3:
						   tempGeDeptMail.setDepartNmae("��-"+geDepartMent.getDeptname());
						   break;
					   case 2:
						   tempGeDeptMail.setDepartNmae("��-"+geDepartMent.getDeptname());
						   break;
					  
				      case 4:
					   tempGeDeptMail.setDepartNmae("����-"+geDepartMent.getDeptname());
					   break;
				    }
				   }else{
					   tempGeDeptMail.setDepartNmae(geDepartMent.getDeptname());
				   }
				}
			}
			if(tempGeDeptMail.getFunctionID()!=null&&!"".equals(tempGeDeptMail.getDeptID())){
				tempGeDeptMail.setSendMailName(geCodeService.findCodeCName(tempGeDeptMail.getFunctionID(), "SendMailType"));
			}
		}
		return page;
   }
   /**
    * ����һ����������
    * @param geDeptMail
    */
	@LocusTrace(setDesc="����һ����������")
   public void addGeDeptMail(GeDeptMail geDeptMail){
	   super.save(geDeptMail);
   }
   /**
    * ���»�������
    * @param geDeptMail
    */
	@LocusTrace(setDesc="���»�������")
   public void updateGeDeptMail(GeDeptMail geDeptMail){
	   super.update(geDeptMail);
   }
   /**
    * ��������ɾ��һ����������
    * @param deptMailID
    */
	@LocusTrace(setDesc="��������ɾ��һ����������")
   public void deleteGeDeptMailByPK(String deptMailID){
	   super.deleteByPK(deptMailID);
   }
   /**
    * ��ѯ��������
    * deptId ----  ������ַ
    * functionID ----------  ����ID
    * */
	@LocusTrace(setDesc="��ѯ��������")
   public List<GeDeptMail> findGeDeptMailByRule(String deptId,String functionId){
	   QueryRule queryRule = QueryRule.getInstance();
	   queryRule.addEqual("deptID", deptId);
	   queryRule.addEqual("functionID", functionId);
	   queryRule.addEqual("validInd", "1");
	   List<GeDeptMail> geDeptMailList = (List<GeDeptMail>) super.find(queryRule);
	   return geDeptMailList;
   }
}
