package cn.com.sinosoft.ebusiness.cms.service.spring;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.cms.service.facade.TreeBoxService;
import cn.com.sinosoft.ebusiness.cms.util.CmsChannelFunc;




/**
 * Ĭ��ITreeBox��ʵ��
 *
 */
public class TreeBoxServiceSpringImpl implements TreeBoxService {
	@Autowired
	private CmsChannelFunc cmsChannelFunc;
	
	public void setCmsChannelFunc(CmsChannelFunc cmsChannelFunc) {
		this.cmsChannelFunc = cmsChannelFunc;
	}


	
	/**
	 * ��Ŀ�ṹ����ʽ����
	 * <item id='1' text='CMS_Demo'>
	 *		<userdata name='type'>0</userdata>
	 *		<item id='690' text='�������'>
	 *			<userdata name='type'>1</userdata>
	 *			<item id='692' text='��ƹ���'>
	 *				<userdata name='type'>690</userdata>
	 *			</item>
	 *		</item>
	 *	</item>
	 * @return ��Ŀ�ṹxml
	 */
	public String makeTreeBoxXml() {
		String doc = cmsChannelFunc.channelRelation("0");	
		return doc.toString();
	}
	
}
