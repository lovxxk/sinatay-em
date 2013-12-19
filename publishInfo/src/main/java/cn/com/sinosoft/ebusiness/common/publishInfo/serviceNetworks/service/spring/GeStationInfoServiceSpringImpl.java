package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.spring;

import java.util.List;
import java.util.Random;

import javax.naming.CommunicationException;

import org.springframework.beans.BeanUtils;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationInfo;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade.GeStationInfoService;


public class GeStationInfoServiceSpringImpl
		extends GenericDaoHibernate<GeStationInfo, String> implements GeStationInfoService{

	@Override
	public boolean save(GeStationInfo geStationInfo) {
		try {
			super.save(geStationInfo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public GeStationInfo findGeStationInfoByPk(String pk) {
		return super.findUnique("obid", pk);
	}
	
	@Override
	public List<GeStationInfo> findAllGeStationInfo(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public Page findAllGeStationInfoByDisPage(QueryRule queryRule, int pageNo,
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
	public boolean updateGeStationInfo(GeStationInfo geStationInfo){
		boolean flag = false;
		try {
			GeStationInfo update = super.findUnique("obid", geStationInfo.getObid());
			BeanUtils.copyProperties(geStationInfo, update,new String[]{"obid"});
			super.update(update);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public String getCode() {
		Random r = new Random();
		int random = Math.abs(r.nextInt()) % (int) (Math.pow(10, 4));
		int length = (random + "").length();
		String result = random * ((int) Math.pow(10, 4 - length)) + "";
		return result;
	}
}
