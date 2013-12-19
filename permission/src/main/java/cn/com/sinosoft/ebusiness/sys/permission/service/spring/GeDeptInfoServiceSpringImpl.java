package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptInfo;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptInfoService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

public class GeDeptInfoServiceSpringImpl extends GenericDaoHibernate<GeDeptInfo, String>
			implements GeDeptInfoService{

	@Override
	public boolean save(GeDeptInfo geDeptInfo) {
		try {
			super.save(geDeptInfo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public GeDeptInfo findGeDeptInfoByPk(String pk) {
		return super.findUnique("attrID", pk);
	}

	@Override
	public List<GeDeptInfo> findAllGeDeptInfo(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public Page findAllGeDeptInfoByDisPage(QueryRule queryRule, int pageNo,
			int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
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
	public boolean updateGeDeptInfo(GeDeptInfo geDeptInfo) {
		boolean flag = false;
		try {
			GeDeptInfo update = super.findUnique("attrID", geDeptInfo.getAttrID());
			BeanUtils.copyProperties(geDeptInfo, update,new String[]{"attrID"});
			super.update(update);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean isExitId(String id) {
		return super.exists(id);
		
	}

}
