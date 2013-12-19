package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeFlowConfigRelate;

public interface GeFlowConfigRelateService {

	public abstract List<GeFlowConfigRelate> findGePageConfigByFlowCode(String flowCode);

}
