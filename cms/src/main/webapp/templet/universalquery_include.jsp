<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="interest_title">${channel.channelName}</div>
						<div class="interest_main">
						<#list documentList as li>
						<#if li_index == 0>
							<div class="interest_name">
							<#if li.docName?length gt 28>
							${li.docName?substring(0,28)}...
							<#else>
							${li.docName}
							</#if>
							</div>
							<#if li.docContent?length gt 85>
							${li.docContent?substring(0,85)}...
							<#else>
							${li.docContent}
							</#if>
							<p class="view_detail">
								<a href="${ctx}/web${channel.channelPath}/index.jsp">²é¿´ÏêÇé&gt;&gt;</a>
							</p>
						</#if>
						</#list>
</div>