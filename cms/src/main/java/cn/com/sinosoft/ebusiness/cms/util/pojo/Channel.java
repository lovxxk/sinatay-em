package cn.com.sinosoft.ebusiness.cms.util.pojo;

import java.util.List;

public class Channel {
	private String channelName;//��Ŀ����
	private String channelUrl;//��ĿURL
	private String channelPath;//��Ŀ����URL
	private String parentPath;//����Ŀ����URL
	private String parentName;//����Ŀ����
	private String parentUrl;
	private String rootChannelName;//һ����Ŀ����
	private String rootChannelUrl;//һ����Ŀ����URL
	private String rootUrl;
	private boolean child;//�Ƿ�������Ŀ
	private List childList;//����Ŀ������Ŀ
	private String type;//1��ͨ 2���� 3����
	
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
