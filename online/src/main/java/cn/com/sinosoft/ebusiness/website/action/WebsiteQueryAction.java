package cn.com.sinosoft.ebusiness.website.action;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.network.domain.Network;
import cn.com.sinosoft.businessModule.network.service.facade.NetworkService;

public class WebsiteQueryAction extends Struts2Action {
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description:
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-24
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */

	private static final long serialVersionUID = 1814033969962451503L;
	/**
	 * ���������
	 */
	@Autowired
	private NetworkService networkService;
	/**
	 * ʡ��
	 */
	private String province;
	/**
	 * ��
	 */
	private String city;
	/**
	 * ʡ�б�
	 */
	private List<String> listProvince;
	/**
	 * ���б�
	 */
	private List<String> listCity;
	/**
	 * ��������
	 */
	private String webName;
	/**
	 * �����Ϣ
	 */

	private List<Network> networkInfos = new ArrayList<Network>();

	public String initProvince() {
		listProvince=networkService.findUniqueProvince();
		return SUCCESS;
	}

	public String initCity() {
		listCity=networkService.findUniqueCity(province);
		return SUCCESS;
	}

	/**
	 * ��ѯ������Ϣ
	 * 
	 * @return
	 */
	public String queryWebsite() {
		QueryRule queryRule = QueryRule.getInstance();
		if (province != null && !"".equals(province)) {
			queryRule.addEqual("province", province);
		}
		if (city != null && !"".equals(city)) {
			queryRule.addEqual("city", city);
		}
		if (webName != null && !"".equals(webName)) {
			queryRule.addLike("manageName", ("%" + webName + "%"));
		}
		networkInfos = networkService.findNetworkByQueryRule(queryRule);
		return SUCCESS;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getListProvince() {
		return listProvince;
	}

	public void setListProvince(List<String> listProvince) {
		this.listProvince = listProvince;
	}

	public List<String> getListCity() {
		return listCity;
	}

	public void setListCity(List<String> listCity) {
		this.listCity = listCity;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public List<Network> getNetworkInfos() {
		return networkInfos;
	}

	public void setNetworkInfos(List<Network> networkInfos) {
		this.networkInfos = networkInfos;
	}

}
