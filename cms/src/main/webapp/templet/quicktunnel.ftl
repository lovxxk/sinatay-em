<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="index_area">
	<div class="area_title">${channel.channelName}</div>
	<div class="area_main">
		<#list channelList as li>
		<#if li_index == 0>
		<div class="service_channel query">
			<div class="channel_name">${li.channelName}</div>
			<ul class="channel_list">
			<#if li.child>
			<#list li.childList as liShow1>
				<li><a href="${liShow1.channelUrl}">&gt;&nbsp;${liShow1.channelName}</a></li>
			</#list>
			</ul>
			</#if>
		</div>
		<#elseif li_index == 1>
		<div class="service_channel transact">
			<div class="channel_name">${li.channelName}</div>
			<ul class="channel_list">
			<#if li.child>
			<#list li.childList as liShow2>
				<li><a href="${liShow2.channelUrl}">&gt;&nbsp;${liShow2.channelName}</a></li>
			</#list>
			</ul>
			</#if>
		</div>
		<#else>	
		<div class="service_channel last claims">
			<div class="channel_name">${li.channelName}</div>
			<ul class="channel_list">
			<#if li.child>
			<#list li.childList as liShow3>
				<li><a href="${liShow3.channelUrl}">&gt;&nbsp;${liShow3.channelName}</a></li>
			</#list>
			</ul>
			</#if>
		</div>
		</#if>
	</#list>
	</div>
</div>
	

