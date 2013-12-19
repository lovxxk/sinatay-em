package cn.com.sinosoft.ebusiness.cms.service.facade;

import java.util.List;

public interface CMSDistributeService {
	//获得栏目下所有文章
	public List getChlDoc(String channelID);
	
	//获得栏目下所有修改了的文章
	public List getChlModifyDoc(String channelID);
	
	//根据栏目发布，发布栏目下部分文章-发布入口1
	public String pubChannel(String channelID, List docList);
	
	//根据栏目id发布栏目,发布所有内容-发布入口2
	public String pubChannel(String channelID);
}
