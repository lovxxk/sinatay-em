package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;


import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptAttribute;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptAttributeService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

public class GeDeptAttributeServiceSpringImpl extends GenericDaoHibernate<GeDeptAttribute, String>
			implements GeDeptAttributeService{

	@Override
	public boolean save(GeDeptAttribute geDeptAttribute) {
		try {
			super.save(geDeptAttribute);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Page findAllDeptAttributeByDisPage(QueryRule queryRule, int pageNo,
			int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	@Override
	public GeDeptAttribute findGeDeptAttributeByPk(String pk) {
		return super.findUnique("deptattrid", pk);
	}

	@Override
	public List<GeDeptAttribute> findAllGeDeptAttribute(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public boolean delete(String pk) {
		try {
			super.deleteByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateGeDeptAttribute(GeDeptAttribute geDeptAttribute) {
		boolean flag = false;
		try {
			GeDeptAttribute update = super.findUnique("deptattrid", geDeptAttribute.getDeptattrid());
			BeanUtils.copyProperties(geDeptAttribute, update,new String[]{"deptattrid"});
			super.update(update);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 根据deptId 查询车险配置信息
	 * 
	 * @param deptId
	 * @return
	 */
	@Override
	public Map<String, String> findAllDeptAttribute(String deptId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geDepartment.deptid", deptId);
		List<GeDeptAttribute> list = (List<GeDeptAttribute>) super.find(queryRule);
		Map<String ,String> returnMap=new HashMap<String, String>();
		if(list!=null&&list.size()>0){
		for (int i = 0; i < list.size(); i++) {
			GeDeptAttribute geDeptAttribute=list.get(i);
			returnMap.put(geDeptAttribute.getGeDeptInfo().getAttrID(), geDeptAttribute.getAttrValue());
		}
		}
		return returnMap;
	}

	@Override
	public List<GeDeptAttribute> findGeDeptAttributeById(String deptId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geDepartment.deptid", deptId);
		List<GeDeptAttribute> list = (List<GeDeptAttribute>) super.find(queryRule);
		return list;
	}
	/**
	 * 根据deptId,attrId 查询车险配置信息
	 * @param deptId,attrId
	 * @return
	 */
	public Object findGeDeptAttributeByDeAtId(String deptId,String attrId,Object obj){
		String[] attrValue=null;
		Object object =null;
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geDepartment.deptid", deptId);
		queryRule.addEqual("geDeptInfo.attrID", attrId);
		List<GeDeptAttribute> list =findAllGeDeptAttribute(queryRule);
		if(list!=null&&list.size()>0){
			if(list.get(0).getAttrValue()!=null){
			attrValue= list.get(0).getAttrValue().split("-");
			if(attrValue!=null&&attrValue.length>0){
			if(attrValue[0].equals("0")){
				object=null;
			}else if(attrValue[0].equals("1")){
				object = "";
			}else if(attrValue[0].equals("2")){//返回数据库中数据
				object = attrValue[1];
			}else if(attrValue[0].equals("3")){
				object= obj;
			}else {
				object =null;
			}
		    }else{
		    	object="";
		    }
			}
		}else{
			object = obj;
		}
		return object;
	}

}
