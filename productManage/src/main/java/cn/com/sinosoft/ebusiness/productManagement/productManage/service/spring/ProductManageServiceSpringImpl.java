package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.exception.BusinessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeAddresseeConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProInsuredOccupation;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductApplicantConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductBeneficiaryConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductConfigMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductEmergencyConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductExtraInfo;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInformBook;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInsuredConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductPolicyDisplayConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductProDept;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeWebFlowPageElement;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeWebFlowPageElementService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.ProductManageService;

/**
 * ��Ʒ����
 *  
 * @since 2011-8-18
 */
public class ProductManageServiceSpringImpl extends GenericDaoHibernate<GeProductMain, String> implements ProductManageService{
	
	@Autowired
	private GeWebFlowPageElementService geWebFlowPageElementService;
	
	public void createProduct(GeProductMain geProductMain) {
		try{
			super.save(geProductMain);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ݲ�Ʒ�����ѯ��Ʒ
	 * @param coreProductCode
	 * @return
	 */
	public GeProductMain findProductMainByCoreProductCode(String coreProductCode){
		return super.get(coreProductCode);
	}
	
	public List<GeProductMain> findProductMainWithEidList(GeProductMain geProductMain,String sqlForward,String deptId){
		
		StringBuffer sb =new StringBuffer();
		sb.append("select distinct direc.eid,            ");
		sb.append("       productmain.coreproductcode,   ");
		sb.append("       productmain.productname        ");
		sb.append("from   ge_directory direc,ge_product_main productmain,ge_product_prodept  saledept ");
		sb.append("where  productmain.coreproductcode = saledept.coreproductcode  ");
if(sqlForward.equals("1")){//����� ������ҳ����ѡ���˵���
		String tempdeptId = splitDept(deptId);
		sb.append("and exists                                                                                  ");
		sb.append("(select * from  ge_department department                                                    "); 
		sb.append("where department.deptid=saledept.deptid and "+tempdeptId+"                                  ");
		sb.append(")                                                                                           ");
}else if(sqlForward.equals("2")){//����� ������ҳ����   δѡ�����
		sb.append("and exists                           ");
		sb.append("(select * from  ge_department department                       ");
		sb.append("where department.deptid=saledept.deptid start with department.parentid=? connect by prior department.deptid=department.parentid  ");
		sb.append(")");
}if(sqlForward.equals("3")){//�ӽ��
		sb.append("and exists                                                                                  "); 
		sb.append("(select * from  ge_department department                                                    ");
		sb.append("where department.deptid=saledept.deptid and department.deptid=?                             ");
		sb.append(")                                                                                           ");
}
		sb.append("and direc.coreproductcode=productmain.coreproductcode                                       ");
		sb.append("and direc.isproductshelf='01'                                                               ");
		sb.append("and direc.isnetsale='01'                                                                    ");
		//��ȡҳ��Ĳ�ѯ����
		StringBuilder condition  = new StringBuilder();
		String businessArea = geProductMain.getBusinessArea();//ҵ������
		String coreProductCode = geProductMain.getCoreProductCode();//��Ʒ����
		String coreProductSimpleName = geProductMain.getCoreProductSimpleName();//��Ʒ����
		
		if(businessArea!=null&&!businessArea.equals("")){
			condition.append(" and productmain.businessarea in("+businessArea+")   "); 
		}
		if(coreProductCode!=null&&!coreProductCode.equals("")){
			condition.append(" and productmain.coreproductcode = '"+coreProductCode+"' ");
		}
		if(coreProductSimpleName!=null&&!coreProductSimpleName.equals("")){
			condition.append(" and productmain.coreproductsimplename like '%"+coreProductSimpleName+"%'");
		}
		if(condition.toString()!=null&&!condition.toString().equals("")){
			sb.append(condition.toString());
		}
		
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		if(sqlForward.equals("2")||sqlForward.equals("3")){
			sqlQuery.setString(0, deptId);
		}
		List list = sqlQuery.list();
		List<GeProductMain> geProductMainList = new ArrayList<GeProductMain>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				GeProductMain geProductMainTemp = new GeProductMain();
				Object[] objs = (Object[])list.get(i);
				geProductMainTemp.setEid(objs[0]==null?null:(String)objs[0]);//��Ʒ��EID
				geProductMainTemp.setCoreProductCode(objs[1]==null?null:(String)objs[1]);//��Ʒ����
				geProductMainTemp.setProductName(objs[2]==null?null:(String)objs[2]);//��Ʒ����
				geProductMainList.add(geProductMainTemp);
			}
		}
		return geProductMainList;
	}
	
	
	/**
	 * �������۵�������ѯ�ǳ��������ǻ�Ҫ������ƷĿ¼�е�eid
	 * 
	 * �ڶ�������Ϊ�Ƿ�Ϊ����㣬��Ϊ��������ӽ�����ǵ�SQL�ǲ�ͬ��
	 * 
	 * @return
	 */
	public Page findProductMainBySaleDept(GeProductMain geProductMain,String sqlForward ,int pageNo,int pageSize,String deptId){
		long start=0l;//
		long end = 0l;
		long totalSize=0l;//�ܼ�¼��
		totalSize = findProductMainWithEidCount(geProductMain,sqlForward,deptId);
		start = (pageNo-1)*pageSize+1;
		end =   pageNo*pageSize;
		List list =findProductMainWithEidList(geProductMain,sqlForward,start,end,deptId);
		if(list!=null&&list.size()>0){
			return new Page(start,totalSize,pageSize,list);
		}
		return null;
	}
	
	public List<GeProductMain> findProductMainWithEidList(GeProductMain geProductMain,String sqlForward,long start,long end,String deptId){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from                                                                                      ");
		sb.append("(select A.*,rownum rw from                                                                         ");
		sb.append("(select                                                                                            ");

		sb.append("direc.eid,                                                                                         ");
		sb.append("productmain.coreproductcode,                                                                       ");
		sb.append("productmain.productname,                                                                           ");
		sb.append("productmain.coreproductsimplename,                                                                 ");
		sb.append("productmain.businessarea,                                                                          ");
		sb.append("productmain.productstatus,                                                                         ");
		sb.append("productmain.productflow,                                                                           ");
		sb.append("productmain.operatorid,                                                                            ");
		sb.append("productmain.updatedate                                                                             ");

		sb.append("from ge_directory direc,ge_product_main productmain,ge_product_prodept  saledept                   ");
		sb.append("where   ");
		sb.append("productmain.coreproductcode = saledept.coreproductcode                                             ");
if(sqlForward.equals("1")){//1.����� ��������Ϊ in('deptId')
		String tempdeptId = splitDept(deptId);
		sb.append("and exists                                                                                  ");
		sb.append("(select * from  ge_department department                                                    "); 
//		sb.append("where department.deptid=saledept.deptid and department.deptid in("+deptId+")                         ");
		sb.append("where department.deptid=saledept.deptid and "+tempdeptId+"                         ");
		sb.append(")                                                                                           ");
}else if(sqlForward.equals("2")){//  2.������parentId = id  
		sb.append("and exists                                                                                  ");
		sb.append("(select * from  ge_department department                                                    "); 
		sb.append("where department.deptid=saledept.deptid start with department.parentid=? connect by prior department.deptid=department.parentid   ");
		sb.append(")                                                                                           ");
}else if(sqlForward.equals("3")){//3.�ӽ���departId=
		sb.append("and exists                                                                                  "); 
		sb.append("(select * from  ge_department department                                                    ");
		sb.append("where department.deptid=saledept.deptid and department.deptid=?                             ");
		sb.append(")                                                                                           ");
}
		sb.append("and direc.coreproductcode=productmain.coreproductcode                                              ");//
		sb.append("and direc.isproductshelf='01'                                                                      ");//�ϼ�
		sb.append("and direc.isnetsale='01'                                                                           ");//����
		

//		sb.append("and productmain.businessarea='3'                                                                   ");
//		sb.append("and productmain.coreproductcode = ?                                                                ");
//		sb.append("and productmain.coreproductsimplename like '%%'                                                    ");
		//��ȡҳ��Ĳ�ѯ����
		StringBuilder condition  = new StringBuilder();
		String businessArea = geProductMain.getBusinessArea();//ҵ������
		String coreProductCode = geProductMain.getCoreProductCode();//��Ʒ����
		String coreProductSimpleName = geProductMain.getCoreProductSimpleName();//��Ʒ����
		
		if(businessArea!=null&&!businessArea.equals("")){
			condition.append(" and productmain.businessarea in("+businessArea+")   "); 
		}
		if(coreProductCode!=null&&!coreProductCode.equals("")){
			condition.append(" and productmain.coreproductcode = '"+coreProductCode+"' ");
		}
		if(coreProductSimpleName!=null&&!coreProductSimpleName.equals("")){
			condition.append(" and productmain.coreproductsimplename like '%"+coreProductSimpleName+"%'");
		}
		if(condition.toString()!=null&&!condition.toString().equals("")){
			sb.append(condition.toString());
		}
		
		sb.append(")A                                                                                                 ");
		sb.append("where rownum<=?)                                                                                   ");
		sb.append("where rw>=?                                                                                        ");
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		if(sqlForward.equals("1")){
			
			sqlQuery.setLong(0, end);//С�ڵ�ҳ��
			sqlQuery.setLong(1, start);//���ڵ�ҳ��
			
		}else{
			
			sqlQuery.setString(0, deptId);//����
			sqlQuery.setLong(1, end);//С�ڵ�ҳ��
			sqlQuery.setLong(2, start);//���ڵ�ҳ��
		}
		
		List list = sqlQuery.list();
		List<GeProductMain> geProductMainList = new ArrayList<GeProductMain>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				GeProductMain geProductMainTemp = new GeProductMain();
				Object[] objs = (Object[])list.get(i);
				geProductMainTemp.setEid(objs[0]==null?null:(String)objs[0]);//��Ʒ��EID
				geProductMainTemp.setCoreProductCode(objs[1]==null?null:(String)objs[1]);//��Ʒ����
				geProductMainTemp.setProductName(objs[2]==null?null:(String)objs[2]);//��Ʒ����
				geProductMainTemp.setCoreProductSimpleName(objs[3]==null?null:(String)objs[3]);//��Ʒ���
				geProductMainTemp.setBusinessArea(objs[4]==null?null:(String)objs[4]);//��Ʒҵ������
				geProductMainTemp.setProductStatus(objs[5]==null?null:(String)objs[5]);//��Ʒ״̬
				geProductMainTemp.setProductFlow(objs[6]==null?null:(String)objs[6]);//��Ʒ��������
				geProductMainTemp.setOperatorID(objs[7]==null?null:(String)objs[7]);//������ID
				geProductMainTemp.setUpdateDate(objs[8]==null?null:(Date)objs[8]);//��Ʒ��������
				geProductMainList.add(geProductMainTemp);
			}
		}
		return geProductMainList;
	}
	//
	private long findProductMainWithEidCount(GeProductMain geProductMain,String  sqlForward,String deptId){
		/*
		 * ��������õ�SQL,����ǿ���PL/SQL��ִ�е�SQL
		select * from ge_directory direc,ge_product_main productmain,ge_product_prodept  saledept
		where 
		productmain.coreproductcode = saledept.coreproductcode
		and exists 
		(select * from  ge_department department 
		where department.deptid=saledept.deptid start with department.parentid=? connect by prior department.deptid=department.parentid
		)

		and direc.coreproductcode=productmain.coreproductcode
		and direc.isproductshelf='01'
		and direc.isnetsale='01'

		and productmain.businessarea=?
		and productmain.coreproductcode = ?
		and productmain.coreproductsimplename like '%%'
		*/
		
		/*
		 *�ӽ�����õ�SQL 
		select * from ge_directory direc,ge_product_main productmain,ge_product_prodept  saledept
		where 
		productmain.coreproductcode = saledept.coreproductcode
		and exists 
		(select * from  ge_department department 
		where department.deptid=saledept.deptid start with department.deptid=? 
		)

		and direc.coreproductcode=productmain.coreproductcode
		and direc.isproductshelf='01'
		and direc.isnetsale='01'

		and productmain.businessarea=?
		and productmain.coreproductcode = ?
		and productmain.coreproductsimplename like '%%'
		*/
		StringBuilder sb  = new StringBuilder();
		sb.append("select count(*) from ge_directory direc,ge_product_main productmain,ge_product_prodept  saledept   ");
		sb.append("where                                                                                       ");
		sb.append("productmain.coreproductcode = saledept.coreproductcode                                      ");
if(sqlForward.equals("1")){//1.����� ��������Ϊ in('deptId')
		String tempdeptId = splitDept(deptId);
		sb.append("and exists                                                                                  ");
		sb.append("(select * from  ge_department department                                                    "); 
		//sb.append("where department.deptid=saledept.deptid and department.deptid in("+deptId+")                         ");
		sb.append("where department.deptid=saledept.deptid and "+tempdeptId+"                         ");
		
		sb.append(")                                                                                           ");
}else if(sqlForward.equals("2")){//  2.������parentId = id  
		sb.append("and exists                                                                                  ");
		sb.append("(select * from  ge_department department                                                    "); 
		sb.append("where department.deptid=saledept.deptid start with department.parentid=? connect by prior department.deptid=department.parentid   ");
		sb.append(")                                                                                           ");
}else if(sqlForward.equals("3")){//3.�ӽ���departId=
		sb.append("and exists                                                                                  "); 
		sb.append("(select * from  ge_department department                                                    ");
		sb.append("where department.deptid=saledept.deptid and department.deptid=?                             ");
		sb.append(")                                                                                           ");
}
		sb.append("and direc.coreproductcode=productmain.coreproductcode                                       ");
		sb.append("and direc.isproductshelf='01'                                                               ");//�ϼ�
		sb.append("and direc.isnetsale='01'                                                                    ");//����

		
		//��ȡҳ��Ĳ�ѯ����
		StringBuilder condition  = new StringBuilder();
		String businessArea = geProductMain.getBusinessArea();//ҵ������
		String coreProductCode = geProductMain.getCoreProductCode();//��Ʒ����
		String coreProductSimpleName = geProductMain.getCoreProductSimpleName();//��Ʒ����
		
		if(businessArea!=null&&!businessArea.equals("")){
			condition.append(" and productmain.businessarea in("+businessArea+")   "); 
		}
		if(coreProductCode!=null&&!coreProductCode.equals("")){
			condition.append(" and productmain.coreproductcode = '"+coreProductCode+"' ");
		}
		if(coreProductSimpleName!=null&&!coreProductSimpleName.equals("")){
			condition.append(" and productmain.coreproductsimplename like '%"+coreProductSimpleName+"%'");
		}
		if(condition.toString()!=null&&!condition.toString().equals("")){
			sb.append(condition.toString());
		}
		System.out.println("------------------------");
		System.out.println("------------------------");
		System.out.println("------------------------");
		System.out.println(sb.toString());
		System.out.println("------------------------");
		System.out.println("------------------------");
		System.out.println("------------------------");
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		if(!sqlForward.equals("1")){
			sqlQuery.setString(0,deptId);
		}
		List list = sqlQuery.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				return ((BigDecimal)list.get(i)).longValue();
			}
		}
		
		return 0l;
	}
	
	//����1000ʱƴSQL
	private String splitDept(String deptId){
		
		String[] authorityIdAllTemp = deptId.split(",");//�Ǵ�''��
		String[] authorityIdAll = new String[authorityIdAllTemp.length];
		
		
		if(authorityIdAllTemp.length>=1000){
			//��'2','4' ת�� 2,4
			for(int i=0;i<authorityIdAllTemp.length;i++){
				String temp = authorityIdAllTemp[i];
				authorityIdAll[i] = temp.substring(1, temp.length()-1);
			}
			
			
			
			String sql="(department.DEPTID in";
			if(authorityIdAll!=null && authorityIdAll.length > 0){
				int authorityIdSize = authorityIdAll.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						sql+="or department.deptid in";
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
			return sql;
		}else{
			return "department.deptid in("+deptId+")   ";
		}
	}
	
	public Page searchProduct(QueryRule queryRule, int pageNo, int pageSize) {
		try{
			return super.find(queryRule, pageNo, pageSize);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteProduct(String coreProductCode) throws Exception {
		try{
			GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
			String productStatus = geProductMain.getProductStatus();
			if(!StringUtils.equals("04", productStatus) && !StringUtils.equals("05", productStatus)) {
				super.delete(geProductMain);
			} else {
				throw new BusinessException();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public GeProductMain searchProductByProductCode(String coreProductCode) {
		try{
			return super.get(coreProductCode);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ���̶�������
	public void configProductSaleFlow(String coreProductCode,String productFlow) {
		GeProductConfigMain geProductConfigMain = null;
		GeProductMain geProductMain = null;
		try{
			geProductMain = this.searchProductByProductCode(coreProductCode);
			geProductMain.setProductFlow(productFlow);
			
			if(geProductMain.getGeProductConfigMains().size() > 0){
				geProductConfigMain = this.initProductConfigMain(geProductMain.getGeProductConfigMains().get(0),productFlow);
			} else {
				geProductConfigMain = new GeProductConfigMain();
				geProductConfigMain = this.initProductConfigMain(geProductConfigMain,productFlow);
				geProductConfigMain.setGeProductMain(geProductMain);
				geProductMain.getGeProductConfigMains().add(geProductConfigMain);
			}
			
			super.save(geProductConfigMain);
			super.update(geProductMain);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isConfigProductSaleFlow(String coreProductCode) {
		GeProductMain main = this.searchProductByProductCode(coreProductCode);
		if(main.getProductStatus().equals("03") || main.getProductStatus().equals("02")){
			return true;
		}else{
			return false; 
		}
	}

	public void configProductDetail(GeProductMain geProductMain) {
		try{
			geProductMain.setUpdateDate(new java.sql.Timestamp(System.currentTimeMillis()));
			super.clear();
			super.update(geProductMain);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void configLegalNotices(String coreProductCode,String legalNotices) {
		try{
			GeProductExtraInfo geProductExtraInfo = null;
			GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);		// ��ѯ��Ʒ��Ϣ
			List<GeProductExtraInfo> geProductExtraInfos = geProductMain.getGeProductExtraInfos();	// ������Ϣ��
			if(geProductExtraInfos.size() > 0) {
				geProductExtraInfo = geProductExtraInfos.get(0);
				geProductExtraInfo.setLegalNotices(legalNotices);		// �������÷�������
				super.update(geProductExtraInfo);		// ���¶�����Ϣ��
			} else {
				geProductExtraInfo = new GeProductExtraInfo();
				geProductExtraInfo.setGeProductMain(geProductMain);
				geProductExtraInfo.setLegalNotices(legalNotices);		// �������÷�������
				geProductExtraInfos.add(geProductExtraInfo);
				super.save(geProductExtraInfo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void configInformBook(String coreProductCode,List<GeProductInformBook> geProductInformBookList) {
		try{
			deleteAllInformBooks(coreProductCode);
			saveALLInformBooks(coreProductCode,geProductInformBookList);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// ��������
	public void configPolicy(String coreProductCode,GeProductApplicantConfig geProductApplicantConfig,
			List<GeProductInsuredConfig> geProductInsuredConfigList,GeProductBeneficiaryConfig geProductBeneficiaryConfig,GeProductEmergencyConfig geProductEmergencyConfig,GeAddresseeConfig geAddresseeConfig,GeProductPolicyDisplayConfig geProductPolicyDisplayConfig) {
		try {
			GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
			
			if(geProductApplicantConfig!=null){
				deleteProductApplicantConfigByProductCode(coreProductCode);
			}
			if(geProductInsuredConfigList!=null){
				deleteProductInsuredConfigByProductCode(coreProductCode);
			}
			if(geProductBeneficiaryConfig != null){
				deleteProductBeneficiaryConfigByProductCode(coreProductCode);
			}
			if(geProductEmergencyConfig != null){
				deleteProductEmergencyConfigByProductCode(coreProductCode);
			}
			if(geAddresseeConfig != null){
				deleteProductAddresseeConfigByProductCode(coreProductCode);
			}
			if(geProductPolicyDisplayConfig != null){
				deleteProductPolicyDisplayConfigByProductCode(coreProductCode);
			}
			
			if(geProductApplicantConfig!=null){
				geProductApplicantConfig.setGeProductMain(geProductMain);	// Ͷ����
				super.save(geProductApplicantConfig);
			}
			
			if(geProductInsuredConfigList!=null){
				for(GeProductInsuredConfig geProductInsuredConfig : geProductInsuredConfigList){
					geProductInsuredConfig.setGeProductMain(geProductMain);		// ������
					List<GeProInsuredOccupation>  proInsuredOccupationList = geProductInsuredConfig.getGeProInsuredOccupations();
					for (GeProInsuredOccupation  proInsuredOccupation:proInsuredOccupationList) {
						proInsuredOccupation.setGeProductInsuredConfig(geProductInsuredConfig);
					}
						
					super.save(geProductInsuredConfig);
				}
			}
			
			if(geProductBeneficiaryConfig != null){
				geProductBeneficiaryConfig.setGeProductMain(geProductMain);		// ������
				super.save(geProductBeneficiaryConfig);
			}
			if(geProductEmergencyConfig != null) {
				geProductEmergencyConfig.setGeProductMain(geProductMain);		// ������ϵ��
				super.save(geProductEmergencyConfig);
			}
			if(geAddresseeConfig != null) {
				geAddresseeConfig.setGeProductMain(geProductMain);		// �ռ���
				super.save(geAddresseeConfig);
			}
			if(geProductPolicyDisplayConfig != null){	//����չʾ������
				geProductPolicyDisplayConfig.setGeProductMain(geProductMain);
				super.save(geProductPolicyDisplayConfig);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void configProposalNotices(String coreProductCode,String proposalNotices) {
		try{
			GeProductExtraInfo geProductExtraInfo = null;
			GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);		// ��ѯ��Ʒ��Ϣ
			List<GeProductExtraInfo> geProductExtraInfos = geProductMain.getGeProductExtraInfos();	// ������Ϣ��
			if(geProductExtraInfos.size() > 0) {
				geProductExtraInfo = geProductExtraInfos.get(0);
				geProductExtraInfo.setProposalNotices(proposalNotices);
				super.update(geProductExtraInfo);		// ���¶�����Ϣ��
			} else {
				geProductExtraInfo = new GeProductExtraInfo();
				geProductExtraInfo.setProposalNotices(proposalNotices);	// ����Ͷ������
				geProductExtraInfo.setGeProductMain(geProductMain);
				super.save(geProductExtraInfo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void configProductMainConfig(GeProductConfigMain geProductConfigMain) {
		try{
			super.update(geProductConfigMain);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String updateProductStatus(String coreProductCode,String productStatus) {
		String result_status ="";
		
		try {
			GeProductMain geProductMain = super.get(coreProductCode);
			geProductMain.setUpdateDate(new Date());
			if ("03".equals(productStatus)) {
				QueryRule queryRule = QueryRule.getInstance(); 
				queryRule.addEqual("geProductMain.coreProductCode", coreProductCode);
				List<GeWebFlowPageElement>  geWebFlowPageElementList = geWebFlowPageElementService.findGeWebFlowPageElementByQueryRule(queryRule);
				boolean isAllDetail = true;
				for (GeWebFlowPageElement geWebFlowPageElement:geWebFlowPageElementList) {
					String code = geWebFlowPageElement.getGePageElementConfig().getElementCode();
					
					if ("0".equals(geWebFlowPageElement.getStatus())) {
						isAllDetail = false;
						break;
					}
				}
				if (isAllDetail) {
					result_status = "03";
					geProductMain.setProductStatus(productStatus);
				}
				
			} else {
				geProductMain.setProductStatus(productStatus);
			}
			
			geProductMain.setUpdateDate(new Date());
			super.update(geProductMain);
			
			return result_status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String searchProductStatus(String coreProductCode) {
		String productStatus = "";
		try{
			GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
			productStatus = geProductMain.getProductStatus();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productStatus; 
	}

	public GeProductApplicantConfig searchProductApplicantConfig(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		if(geProductMain.getGeProductApplicantConfigs().size()>0){
			return geProductMain.getGeProductApplicantConfigs().get(0);
		}else{
			return new GeProductApplicantConfig();
		}
	}
	
	public GeProductPolicyDisplayConfig searchProductPolicyDisplayConfig(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		if(geProductMain.getGeProductPolicyDisplayConfigs().size()>0){
			return geProductMain.getGeProductPolicyDisplayConfigs().get(0);
		}else{
			return new GeProductPolicyDisplayConfig();
		}
	}
	
	public List<GeProductInsuredConfig> searchProductInsuredConfig(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		if(geProductMain.getGeProductInsuredConfigs().size()>0){
			return geProductMain.getGeProductInsuredConfigs();
		}else{
			return new ArrayList<GeProductInsuredConfig>();
		}
	}

	public GeProductBeneficiaryConfig searchProductBeneficiaryConfig(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		if(geProductMain.getGeProductBeneficiaryConfigs().size()>0){
			return geProductMain.getGeProductBeneficiaryConfigs().get(0);
		}else{
			return new GeProductBeneficiaryConfig();
		}
//		QueryRule queryRule = QueryRule.getInstance();
//		queryRule.addEqual("geProductMain.coreProductCode", coreProductCode);
//		List<GeProductBeneficiaryConfig> geProductBeneficiaryConfigList = super.find(GeProductBeneficiaryConfig.class,queryRule);
//		if(geProductBeneficiaryConfigList.size()==0){
//			return new GeProductBeneficiaryConfig();
//		}else{
//			return geProductBeneficiaryConfigList.get(0);
//		}
	}

	public List<GeProductInsuredConfig> createGeProductInsuredConfig(int count) {
		List<GeProductInsuredConfig> geProductInsuredConfigList = new ArrayList<GeProductInsuredConfig>(count);
		for(int i=0;i<count;i++){
			geProductInsuredConfigList.add(new GeProductInsuredConfig());
		}
		return geProductInsuredConfigList;
	}

	public String searchProductSaleFlow(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		return geProductMain.getProductFlow();
	}

	public GeProductConfigMain initProductConfigMain(GeProductConfigMain geProductConfigMain,String productFlow) {
//		GeProductConfigMain geProductConfigMain = new GeProductConfigMain();
		geProductConfigMain.setProductInfo("1");// ��Ʒ������Ϣ
		if(productFlow.indexOf("1") != -1) {		// �����ʾ�
			geProductConfigMain.setQuestion("1");
		} else {
			geProductConfigMain.setQuestion("0");
		}
		if(productFlow.indexOf("2") != -1) {		// ��������
			geProductConfigMain.setCalculation("1");
		} else {
			geProductConfigMain.setCalculation("0");
		}
		if(productFlow.indexOf("3") != -1) {		// ��������
			geProductConfigMain.setLegalNotices("1");
		} else {
			geProductConfigMain.setLegalNotices("0");
		}
		if(productFlow.indexOf("4") != -1) {		// Ͷ����֪
			geProductConfigMain.setInform("1");
		} else {
			geProductConfigMain.setInform("0");
		}
		if(productFlow.indexOf("5") != -1) {		// Ͷ������
			geProductConfigMain.setProposalNotices("1");
		} else {
			geProductConfigMain.setProposalNotices("0");
		}
		if(productFlow.indexOf("6") != -1) {		// Ͷ������
			geProductConfigMain.setPolicy("1");
		} else {
			geProductConfigMain.setPolicy("0");
		}
		return geProductConfigMain;
	}

	public GeProductExtraInfo searchLegalNotices(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		return geProductMain.getGeProductExtraInfos().get(0);
	}

	public List<GeProductInformBook> searchInformBook(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		return geProductMain.getGeProductInformBooks();
	}

	public GeProductExtraInfo searchProposalNotices(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		return geProductMain.getGeProductExtraInfos().get(0);
	}

	public void deleteAllInformBooks(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		List<GeProductInformBook> geProductInformBooks = geProductMain.getGeProductInformBooks();
		
		if(geProductInformBooks.size()>0){
			for(Iterator it = geProductInformBooks.iterator();it.hasNext();){
				GeProductInformBook informBook = (GeProductInformBook)it.next();
				it.remove();
				informBook.setGeProductMain(null);
				super.delete(informBook);
			}
		}		
	}

	public void saveALLInformBooks(String coreProductCode,List<GeProductInformBook> geProductInformBookList) {
		try{
			GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
			for(GeProductInformBook informBook : geProductInformBookList) {
				informBook.setGeProductMain(geProductMain);
				super.save(informBook);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void saveDept(String coreProductCode, String deptIds) {
		deleteAllDept(coreProductCode);
		GeProductMain geProductMain = null;
		List<GeProductProDept> geProductProDeptList = new ArrayList<GeProductProDept>();
		String[] deptId;
		try{
			geProductMain = this.searchProductByProductCode(coreProductCode);
			if(StringUtils.isNotBlank(deptIds)) {
				deptId = deptIds.split(",");
				for(String id : deptId) {
					GeProductProDept geProductProDept = new GeProductProDept();
					geProductProDept.setDeptID(id);
					geProductProDept.setGeProductMain(geProductMain);
					geProductProDeptList.add(geProductProDept);
				}
				geProductMain.setGeProductProDepts(geProductProDeptList);
			}
			super.save(geProductMain);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteAllDept(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		List<GeProductProDept> geProductProDepts = geProductMain.getGeProductProDepts();
		
		if(geProductProDepts.size()>0){
			for(Iterator it = geProductProDepts.iterator();it.hasNext();){
				GeProductProDept productDept = (GeProductProDept)it.next();
				it.remove();
				productDept.setGeProductMain(null);
				super.delete(productDept);
			}
		}		
	}

	public void deleteProductApplicantConfigByProductCode(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		GeProductApplicantConfig geProductApplicantConfig = null;
		if(geProductMain.getGeProductApplicantConfigs().size()>0){
			geProductApplicantConfig = geProductMain.getGeProductApplicantConfigs().get(0);
			geProductMain.getGeProductApplicantConfigs().remove(0);
			geProductApplicantConfig.setGeProductMain(null);
			super.delete(geProductApplicantConfig);
		}
	}
	
	public void deleteProductPolicyDisplayConfigByProductCode(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		GeProductPolicyDisplayConfig geProductPolicyDisplayConfig = null;
		if(geProductMain.getGeProductPolicyDisplayConfigs().size()>0){
			geProductPolicyDisplayConfig = geProductMain.getGeProductPolicyDisplayConfigs().get(0);
			geProductMain.getGeProductPolicyDisplayConfigs().remove(0);
			geProductPolicyDisplayConfig.setGeProductMain(null);
			super.delete(geProductPolicyDisplayConfig);
		}
	}

	public void deleteProductBeneficiaryConfigByProductCode(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		GeProductBeneficiaryConfig geProductBeneficiaryConfig = null;
		
		if(geProductMain.getGeProductBeneficiaryConfigs().size()>0){
			geProductBeneficiaryConfig = geProductMain.getGeProductBeneficiaryConfigs().get(0);
			geProductMain.getGeProductBeneficiaryConfigs().remove(0);
			geProductBeneficiaryConfig.setGeProductMain(null);
			super.delete(geProductBeneficiaryConfig);
		}
	}

	public void deleteProductInsuredConfigByProductCode(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		
		List<GeProductInsuredConfig> GeProductInsuredConfigsList = geProductMain.getGeProductInsuredConfigs();
		if(GeProductInsuredConfigsList.size()>0){
			for(Iterator it = GeProductInsuredConfigsList.iterator();it.hasNext();){
				GeProductInsuredConfig geProductInsuredConfig = (GeProductInsuredConfig)it.next();
				it.remove();
				geProductInsuredConfig.setGeProductMain(null);
				super.delete(geProductInsuredConfig);
			}
		}
	}

	// ��ѯ������ϵ��
	public GeProductEmergencyConfig searchProductEmergencyConfig(
			String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		GeProductEmergencyConfig geProductEmergencyConfig = null;
		if(geProductMain.getGeProductEmergencyConfigs().size()>0){
			geProductEmergencyConfig = geProductMain.getGeProductEmergencyConfigs().get(0);
		}else{
			geProductEmergencyConfig = new GeProductEmergencyConfig();
		}
		return geProductEmergencyConfig;
	}
	
	// ��ѯ�ռ���
	public GeAddresseeConfig searchProductAddresseeConfig(
			String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		GeAddresseeConfig geAddresseeConfig = null;
		if(geProductMain.getGeAddresseeConfigs().size()>0){
			geAddresseeConfig = geProductMain.getGeAddresseeConfigs().get(0);
		}else{
			geAddresseeConfig = new GeAddresseeConfig();
		}
		return geAddresseeConfig;
	}
	
	// ɾ��������ϵ��
	public void deleteProductEmergencyConfigByProductCode(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		GeProductEmergencyConfig geProductEmergencyConfig = null;
		
		if(geProductMain.getGeProductEmergencyConfigs().size()>0){
			geProductEmergencyConfig = geProductMain.getGeProductEmergencyConfigs().get(0);
			geProductMain.getGeProductEmergencyConfigs().remove(0);
			geProductEmergencyConfig.setGeProductMain(null);
			super.delete(geProductEmergencyConfig);
		}
	}
	
	// ɾ���ռ���
	public void deleteProductAddresseeConfigByProductCode(String coreProductCode) {
		GeProductMain geProductMain = this.searchProductByProductCode(coreProductCode);
		GeAddresseeConfig geAddresseeConfig = null;
		if(geProductMain.getGeAddresseeConfigs().size()>0){
			geAddresseeConfig = geProductMain.getGeAddresseeConfigs().get(0);
			geProductMain.getGeAddresseeConfigs().remove(0);
			geAddresseeConfig.setGeProductMain(null);
			super.delete(geAddresseeConfig);
		}
		
	}
	
	/**
	 * ���ݲ�Ʒ������ѯ��Ʒ
	 * @param queryRule
	 * @return
	 */
	public ArrayList<GeProductMain> searchProductMains(QueryRule queryRule) {
		try{
			ArrayList<GeProductMain> geProductMainList = new ArrayList<GeProductMain>();
			List<GeProductMain> queryList = super.find(queryRule);
			for (int i = 0; i < queryList.size(); i++) {
				geProductMainList.add(queryList.get(i));
			}
			return geProductMainList;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String LifeProductSynchronous(String coreProductCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public String LifeCoreProductSynchronous(String riskCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public String FinancialProductSynchronous(String coreProductCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updatePro(GeProductMain entity) {
		// TODO Auto-generated method stub
		entity.setUpdateDate(new Date());
		super.update(entity);
	}

	public void savePro(GeProductMain entity) {
		// TODO Auto-generated method stub
		super.save(entity);	
	}

	public void saveEntity(Object entity) {
		// TODO Auto-generated method stub
		super.save(entity);	
	}

	public List findEntitys(Class classes,QueryRule query) {
		// TODO Auto-generated method stub
		return super.find(classes,query);
	}




}


