package cn.com.sinosoft.ebusiness.cms.service.facade;

import java.util.List;

public interface CMSDistributeService {
	//�����Ŀ����������
	public List getChlDoc(String channelID);
	
	//�����Ŀ�������޸��˵�����
	public List getChlModifyDoc(String channelID);
	
	//������Ŀ������������Ŀ�²�������-�������1
	public String pubChannel(String channelID, List docList);
	
	//������Ŀid������Ŀ,������������-�������2
	public String pubChannel(String channelID);
}
