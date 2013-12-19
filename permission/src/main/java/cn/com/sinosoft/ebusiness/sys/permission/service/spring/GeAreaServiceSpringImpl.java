package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAreaAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeAreaService;

public class GeAreaServiceSpringImpl extends GenericDaoHibernate<GeAreaAuthority, String>  implements GeAreaService {

	@Override
	public List<GeAreaService> getGeAeraByGlevel() {
		return null;
	}

	@Override
	public Object[] findGeAreas(String pgid,String glevel){
		//String sql = "select t.GID,t.GNAME from GE_AREA t where t.PGID = ? and t.GLEVEL = ? order by nlssort(t.GNAME,'NLS_SORT=SCHINESE_PINYIN_M')";
		String sql = "select t.GID,t.GNAME from GE_AREA t where t.PGID = ? and t.GLEVEL = ? order by t.GID";
		return super.findBySql(sql, pgid,glevel).toArray();
	}
	
	@Override
	@LocusTrace(setDesc="根据查询条件查询区域")
	public List findGeAreas(QueryRule queryRule) {
		// TODO Auto-generated method stub
		return super.find(queryRule);
	}

	@Override
	@LocusTrace(setDesc="根据父节点查询区域子节点")
	public List getChildList(String pgid) {
		Session session = super.getSession();
		//String querySql1 = "select t.GID from GE_AREA t where t.PGID = ? order by nlssort(t.GNAME,'NLS_SORT=SCHINESE_PINYIN_M')";
		String querySql1 = "select t.GID from GE_AREA t where t.PGID = ? order by t.GID";
		List list  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, pgid).list();
		session.flush();
		if(list == null){
			return new ArrayList();
		}else{
			return list;	
		}
	}

	@Override
	@LocusTrace(setDesc="根据业务领域查询出机构中已使用的到的区域")
	public List getBussAreas(String buss) {
		Session session = super.getSession();
		String querySql1 = "select t.gid from GE_DEPARTMENT t where t.BUSINESSAREA = ? and t.gid is not null";
		List list  =  super.findBySql(querySql1, buss);
		if(list == null){
			return new ArrayList();
		}else{
			return list;	
		}
	}

	@Override
	@LocusTrace(setDesc="保存区域对象")
	public boolean save(GeAreaAuthority geAreaAuthority) {
		boolean flag = false;
		try {
			super.save(geAreaAuthority);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="更新区域对象")
	public boolean updates(GeAreaAuthority geAreaAuthority) {
		try{
			super.update(geAreaAuthority);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	@Override
	@LocusTrace(setDesc="删除区域对象")
	public boolean delete(GeAreaAuthority geAreaAuthority) {
		boolean flag = false;
		try {
			super.delete(geAreaAuthority);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="通过主键id查询区域对象")
	public GeAreaAuthority findGeAuthorityByPK(String pk) {
		try {
			return super.findUnique("gid", pk);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@LocusTrace(setDesc="通过主键id查询是否存在此区域对象")
	public boolean exists(String pk){
		return super.exists(GeAreaAuthority.class, pk);
	}

	@Override
	@LocusTrace(setDesc="判断区域是否被机构所属")
	public boolean isUsedDep(String gid) {
		boolean flag = false;
		Session session = super.getSession();
		String querySql1 = "select * from GE_DEPARTMENT t where t.gid = ?";
		List list  =  super.findBySql(querySql1,gid);
		if(list.size()!=0){
			flag = true;
		}
		return flag;
	}
	
	@Override
	public GeAreaAuthority findGeAuthorityByChildId(String childId){
		String sql = "select d.gid,d.gname,d.pgid,d.type,d.glevel,d.note from ge_area d where d.gid = ( select t.pgid from ge_area t where t.gid = ? )";
		List list = super.findBySql(sql, childId);
		if (list == null || list.size() == 0) {
			return null;
		}
		Object[] strs =  (Object[])list.get(0);
		GeAreaAuthority geArea = new GeAreaAuthority();
		geArea.setGid((String)strs[0]);
		geArea.setGname((String)strs[1]);
		geArea.setPgid((String)strs[2]);
		geArea.setType((String)strs[3]);
		geArea.setGlevel((String)strs[4]);
		geArea.setNote((String)strs[5]);
		return geArea;
	}
}
