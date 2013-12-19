package cn.com.sinosoft.ebusiness.cms.util.pojo;

import java.util.List;

public class Channel {
	private String channelName;//栏目名称
	private String channelUrl;//栏目URL
	private String channelPath;//栏目整体URL
	private String parentPath;//父栏目整体URL
	private String parentName;//父栏目名称
	private String parentUrl;
	private String rootChannelName;//一级栏目名称
	private String rootChannelUrl;//一级栏目整体URL
	private String rootUrl;
	private boolean child;//是否有子栏目
	private List childList;//该栏目的子栏目
	private String type;//1普通 2链接 3附件
	
	public String getParentUrl() {
		return parentUrl;
	}
	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
	}
	public String getRootUrl() {
		return rootUrl;
	}
	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	public boolean isChild() {
		return child;
	}
	public void setChild(boolean child) {
		this.child = child;
	}
	public List getChildList() {
		return childList;
	}
	public void setChildList(List childList) {
		this.childList = childList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRootChannelName() {
		return rootChannelName;
	}
	public void setRootChannelName(String rootChannelName) {
		this.rootChannelName = rootChannelName;
	}
	public String getRootChannelUrl() {
		return rootChannelUrl;
	}
	public void setRootChannelUrl(String rootChannelUrl) {
		this.rootChannelUrl = rootChannelUrl;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	public String getChannelPath() {
		return channelPath;
	}
	public void setChannelPath(String channelPath) {
		this.channelPath = channelPath;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
}
