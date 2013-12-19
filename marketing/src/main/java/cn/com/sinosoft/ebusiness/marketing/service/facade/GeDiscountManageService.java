package cn.com.sinosoft.ebusiness.marketing.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.marketing.domain.GeDiscountManage;

public interface GeDiscountManageService {
	/**增加产品折扣活动*/
	public boolean createGeDiscountManage(GeDiscountManage geDiscountManage);
	/**删除产品折扣活动*/
	public boolean deleteGeDiscountManage(List<GeDiscountManage> list);
	/**编辑产品折扣活动*/
	public boolean updateGeDiscountManage(GeDiscountManage geDiscountManage);
	/**查看产品折扣活动*/
	public Page searchGeDiscountManage(QueryRule queryRule, int pageNo, int pageSize);
	public abstract GeDiscountManage findGeDiscountManageByDiscountId(String discountId);
}
