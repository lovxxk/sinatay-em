package cn.com.sinosoft.ebusiness.marketing.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.marketing.domain.GeDiscountManage;

public interface GeDiscountManageService {
	/**���Ӳ�Ʒ�ۿۻ*/
	public boolean createGeDiscountManage(GeDiscountManage geDiscountManage);
	/**ɾ����Ʒ�ۿۻ*/
	public boolean deleteGeDiscountManage(List<GeDiscountManage> list);
	/**�༭��Ʒ�ۿۻ*/
	public boolean updateGeDiscountManage(GeDiscountManage geDiscountManage);
	/**�鿴��Ʒ�ۿۻ*/
	public Page searchGeDiscountManage(QueryRule queryRule, int pageNo, int pageSize);
	public abstract GeDiscountManage findGeDiscountManageByDiscountId(String discountId);
}
