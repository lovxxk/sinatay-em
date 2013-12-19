package cn.com.sinosoft.ebusiness.cms.service.spring;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.cms.service.facade.TreeBoxService;
import cn.com.sinosoft.ebusiness.cms.util.CmsChannelFunc;




/**
 * 默认ITreeBox的实现
 *
 */
public class TreeBoxServiceSpringImpl implements TreeBoxService {
	@Autowired
	private CmsChannelFunc cmsChannelFunc;
	
	public void setCmsChannelFunc(CmsChannelFunc cmsChannelFunc) {
		this.cmsChannelFunc = cmsChannelFunc;
	}


	
	/**
	 * 栏目结构，格式如下
	 * <item id='1' text='CMS_Demo'>
	 *		<userdata name='type'>0</userdata>
	 *		<item id='690' text='保险理财'>
	 *			<userdata name='type'>1</userdata>
	 *			<item id='692' text='理财故事'>
	 *				<userdata name='type'>690</userdata>
	 *			</item>
	 *		</item>
	 *	</item>
	 * @return 栏目结构xml
	 */
	public String makeTreeBoxXml() {
		String doc = cmsChannelFunc.channelRelation("0");	
		return doc.toString();
	}
	
}
