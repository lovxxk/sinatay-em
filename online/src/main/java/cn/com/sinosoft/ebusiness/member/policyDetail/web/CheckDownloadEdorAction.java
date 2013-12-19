package cn.com.sinosoft.ebusiness.member.policyDetail.web;

import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.online.biz.dcenter.web.FileMngAction;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.DownloadEdorCreater;

public class CheckDownloadEdorAction extends Struts2Action {
	private static final long serialVersionUID = 1596753888219378366L;
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description: ��ⱨ����Ϣ�� 1 ���ķ��ر����Ƿ���ϵͳ�쳣
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-16
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */

	// ��ȫ�����
	private String acceptNo;
	@Autowired
	private DownloadEdorCreater downloadEdorCreater;//��ȫ��������
	@Autowired
	private WSClientHelper wsclientHelper;//�ӿڸ�����
	private String flag;//���ĳɹ���־
	private String desc;//��������
	private String url;//����·��

	public String check() {
		try {
			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String, String> result;
			// ���ձ�����Ϣ
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("acceptNo", acceptNo);
			docRequest = downloadEdorCreater.createXml(map);
			sRequest = wsclientHelper.doc2String(docRequest);

//			String s = wsclientHelper.getEndPoint();
//			wsclientHelper.setEndPoint("http://10.20.3.63:7001/DXTranInterfaceServlet");//����
			
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
			result = downloadEdorCreater.Xml2Object(docResponse);
			flag = result.get("flag");
			desc = result.get("desc");
			if ("1".equals(flag)) {
				url = FileMngAction.encryption(result.get("url").replaceFirst(
						"pdf", ""));
			}
//			wsclientHelper.setEndPoint(s);//����
		} catch (Exception e) {
			desc = e.getMessage();
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getAcceptNo() {
		return acceptNo;
	}

	public void setAcceptNo(String acceptNo) {
		this.acceptNo = acceptNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
