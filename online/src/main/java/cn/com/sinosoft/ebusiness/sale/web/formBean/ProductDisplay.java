package cn.com.sinosoft.ebusiness.sale.web.formBean;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeInfo;

public class ProductDisplay {
	
	private GeDirectoryAttributeInfo directoryAttributeInfo;
	
	private List<GeDirectory> productDirectoryList;

	public GeDirectoryAttributeInfo getDirectoryAttributeInfo() {
		return directoryAttributeInfo;
	}

	public void setDirectoryAttributeInfo(
			GeDirectoryAttributeInfo directoryAttributeInfo) {
		this.directoryAttributeInfo = directoryAttributeInfo;
	}

	public List<GeDirectory> getProductDirectoryList() {
		return productDirectoryList;
	}

	public void setProductDirectoryList(List<GeDirectory> productDirectoryList) {
		this.productDirectoryList = productDirectoryList;
	}
	
	
}
