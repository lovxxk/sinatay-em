package cn.com.sinosoft.ebusiness.mis.business.businessManage.serviceNetwork.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.util.Date;
import java.util.List;

import cn.com.sinosoft.businessModule.network.domain.Network;
import cn.com.sinosoft.businessModule.network.service.facade.NetworkService;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.util.encode.JsonBinder;

public class ServiceNetworkAction extends Struts2Action {

	private static final long serialVersionUID = 1L;

	private Page page;
	private NetworkService networkService;
	private Network network;
	private List<Network> networkList;
	private List<String> listProvince;
	private String flag;// 标志
	private String message;// 信息

	public String addServiceNetwork() {
		try {
			// 设置创造时间、更新时间
			network.setCreateTime(new Date());
			network.setUpdateTime(new Date());
			GeOperator geOperator = (GeOperator) super.getSession()
					.getAttribute("geOperator");
			// 设置修改者
			network.setOperator(geOperator.getOperatorname());
			flag = networkService.addServiceNetwork(network);
		
			if ("1".equals(flag)) {
				flag = "1";
				message = "服务网点新建成功!";
			} else if ("2".equals(flag)) {
				flag = "0";
				message = "已存在该服务网点，新建失败！";
			}
		} catch (Exception e) {
			flag = "0";
			message = "服务网点创建失败";
		}
		return SUCCESS;
	}

	public String findServiceNetwork() {

		listProvince = networkService.findUniqueProvince();
		return SUCCESS;
	}

	public String findServiceNetwork2() {
		pageNo = (pageNo == 0 ? 1 : pageNo);
		pageSize = (pageSize == 0 ? 20 : pageSize);

		page = networkService.getNetworkPage(network, pageNo, pageSize);

		return SUCCESS;
	}

	public String viewServiceNetwork() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", network.getSerialNo());
		network = networkService.findNetworkByQueryRule(queryRule).get(0);

		return SUCCESS;
	}

	public String prepareUpdateServiceNetwork() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", network.getSerialNo());
		network = networkService.findNetworkByQueryRule(queryRule).get(0);

		return SUCCESS;
	}

	public String updateServiceNetwork() {
		try {
			networkService.updateNetwork(network);
			flag = "1";
			message = "服务网点更新成功";
		} catch (Exception e) {
			flag = "0";
			message = "服务网点更新失败";
		}
		return SUCCESS;
	}

	public String deleteServiceNetwork() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", network.getSerialNo());
		if (networkService.findNetworkByQueryRule(queryRule).size() == 0) {
			flag = "0";
			message = "删除失败";
		} else {
			networkService.deleteNetwork(network);
			flag = "1";
			message = "删除成功";
		}
		return SUCCESS;
	}

	/**
	 * 查询省市
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String loadArea() {
		String province = super.getRequest().getParameter("province");
		List networks = networkService.findArea(province);
		super.render(JsonBinder.buildNonNullBinder().toJson(networks),
				"application/json;charset=GBK");
		return NONE;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<String> getListProvince() {
		return listProvince;
	}

	public void setListProvince(List<String> listProvince) {
		this.listProvince = listProvince;
	}

	public NetworkService getNetworkService() {
		return networkService;
	}

	public void setNetworkService(NetworkService networkService) {
		this.networkService = networkService;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public List<Network> getNetworkList() {

		return networkList;
	}

	public void setNetworkList(List<Network> networkList) {

		this.networkList = networkList;
	}



}
