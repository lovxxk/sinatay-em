<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<#list channelList as li>
<a onclick="window.location.href='${ctx}/web${li.channelPath}/index.jsp'"><div id="${li.channelUrl}"  class="cate ${li.channelUrl} ">
	<div>${li.channelName}</div>
</div></a>
</#list>


<script type="text/javascript">
var linkUrl = window.location.href;
linkUrl = linkUrl.substring(0,linkUrl.lastIndexOf('/'));
linkUrl = linkUrl.substring(linkUrl.lastIndexOf('/') + 1,linkUrl.length);
$("#" + linkUrl).addClass("select");
</script>