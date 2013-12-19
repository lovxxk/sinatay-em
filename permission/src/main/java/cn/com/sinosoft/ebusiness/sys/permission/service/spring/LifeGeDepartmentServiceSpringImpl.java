package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessResourceFailureException;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartmentDto;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.LifeGeDepartmentService;

public class LifeGeDepartmentServiceSpringImpl extends
		GenericDaoHibernate<String, String> implements LifeGeDepartmentService {
	/**
	 * 根据机构代码（父机构编码）查询出机构名称、 机构编码、是否为末级机构（Y/N)
	 * @param deptId	机构代码
	 * @return
	 */
	@Override
	public GeDepartmentDto[] getLifeDeptInfo(String deptId) {
		// TODO Auto-generated method stub
		GeDepartmentDto[] ge;
		try {
			String sql = "Select b.deptid,  b.deptname,  decode((Select Count(*)  From ge_department a  Where  a.parentid = b.deptid), 0,"+
			      "'N', 'Y') As nextcode  From ge_department b Where b.parentid =  ?" +
			      " Order By 1";
			Session session = super.getSession();
			List geDeptList  = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, deptId).list();
			ge = new GeDepartmentDto[geDeptList.size()];
			for(int i=0;i<geDeptList.size()&&geDeptList!=null;i++){
				Map map =(Map) geDeptList.get(i);
				GeDepartmentDto geDepartmentDto = new GeDepartmentDto();
				geDepartmentDto.setDEPTID((String)map.get("DEPTID"));
				geDepartmentDto.setDEPTNAME((String)map.get("DEPTNAME"));
				geDepartmentDto.setNEXTCODE((String)map.get("NEXTCODE"));
				ge[i] = geDepartmentDto;
			}
		} catch (DataAccessResourceFailureException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return ge;
	}

}
