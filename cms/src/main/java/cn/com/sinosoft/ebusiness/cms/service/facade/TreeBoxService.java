package cn.com.sinosoft.ebusiness.cms.service.facade;
/**
 * 栏目树接口，包含了栏目树类的所有操作过程
 */
public interface TreeBoxService {
	
	/**
	 * 获得构建cms栏目树时需要的dhtmltree控件要求的 xml
	 * @return xml
	 */
	public String makeTreeBoxXml();
	
}
