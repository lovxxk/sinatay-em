package cn.com.sinosoft.ebusiness.marketing.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import cn.com.sinosoft.ebusiness.marketing.domain.GeDiscountManage;
import cn.com.sinosoft.ebusiness.marketing.service.facade.GeDiscountManageService;

public class GeDiscountManageServiceImpl extends GenericDaoHibernate<GeDiscountManage, String> implements GeDiscountManageService{

	@Override
	public boolean createGeDiscountManage(GeDiscountManage geDiscountManage) {
		boolean flag = false;
		try {
			super.save(geDiscountManage);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean deleteGeDiscountManage(List<GeDiscountManage> list) {
		boolean flag = false;
		try {
			super.deleteAll(list);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean updateGeDiscountManage(GeDiscountManage geDiscountManage) {
		boolean flag = false;
		try {
			GeDiscountManage update = super.findUnique("discountid", geDiscountManage.getDiscountid());
			List<String> ignorePropertyList = new ArrayList<String>();
			ignorePropertyList.add("discountid");
			if (update.getPictureone() != null && geDiscountManage.getPictureone() == null) {
				ignorePropertyList.add("pictureone");
			}
			if (update.getPicturetwo() != null && geDiscountManage.getPicturetwo() == null) {
				ignorePropertyList.add("picturetwo");
			}
			if (update.getPicturethree() != null && geDiscountManage.getPicturethree() == null) {
				ignorePropertyList.add("picturethree");
			} 
			if (update.getPicturefour() != null && geDiscountManage.getPicturefour() == null) {
				ignorePropertyList.add("picturefour");
			} 
			if (update.getPicturefive() != null && geDiscountManage.getPicturefive() == null) {
				ignorePropertyList.add("picturefive");
			} 
			if (update.getPicturesix() != null && geDiscountManage.getPicturesix() == null) {
				ignorePropertyList.add("picturesix");
			} 
			String[] ignoreProperties = new String[ignorePropertyList.size()];
			BeanUtils.copyProperties(geDiscountManage, update, ignorePropertyList.toArray(ignoreProperties));
			super.update(update);
		} catch (BeansException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public GeDiscountManage findGeDiscountManageByDiscountId(String discountId) {
		return super.get(discountId);
	}
	@Override
	public Page searchGeDiscountManage(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

}
