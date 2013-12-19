<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>��̩����-�ͷ�����-${channel.channelName}</title>
	<meta name="description" content="Ϊ���ṩ����Ͷ������������񡢲�ѯ�������������������ϲ�������ݡ���ʡ���ı���ʱ�䣡" />
	<meta name="keywords" content="Ͷ������,�������,��ѯ����,��������,�������,Ͷ������" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx }/global/css/global.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx }/resources/css/frame.css" rel="stylesheet" type="text/css"/>
		
		<script src="${ctx }/global/js/jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
		<script src="${ctx }/global/js/common/main.js" type="text/javascript"></script>
		<link href="${ctx }/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<script src="${ctx }/resources/js/frame.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		
		<link href="${ctx }/resources/css/service/service_download.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/service/service_download.js" type="text/javascript"></script>
		
		<link href="${ctx }/global/css/page.css" rel="stylesheet" type="text/css"/>
		
		<script type="text/javascript">
			$(window).load(function(){
				//��ҳЧ��
				$('#universalquery_content_article_bottom').find('span').each(function(){
					$(this).addClass("disabled");
				});
				
				goPage(1);
			});
			
			//--------------��̬����		
			<#assign pageSize = 10 >//ÿҳ����
			
			pageSize = ${pageSize};//ÿҳ����
			totalCount = ${documentList?size};
			pageCount = Math.ceil(totalCount/pageSize) ;//��ҳ��
//--------------
			function goPage(currentPage) {
				$('#universalquery_content_article_bottom').find('a').show();
				$('#universalquery_content_article_bottom').find('span').each(function(){
						$(this).addClass("disabled");
					});
				
				$('#c_' + currentPage).addClass('current');
				$('#c_' + currentPage).removeClass('disabled');			
				$('#p_' + currentPage).hide();

				
				var from = currentPage*pageSize - pageSize;
				var end = currentPage*pageSize;
				$('.universalquery_content_center_list').hide();
				for(var i=from; i<end; i++) {
					$('#s_' + i).show();
				}				
			}
		</script>
	</head>
<body>
<!-- ҳͷ ��ʼ-->
<jsp:include page="/header.jsp"></jsp:include>
<!-- ҳͷ���� -->

<!-- �м����ݿ�ʼ -->
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">${channel.channelName}</li>
			</ul>
		</div>
		<div class="service_main">
			<jsp:include page="/web/service/leftDynamic.jsp"></jsp:include>
			<!-- �Ҳ������ݿ�ʼ -->
			<div class="right_content">
				<div class="service_head">
					<img src="${ctx}/resources/image/service/service_center_head.jpg">
				</div>
				<div class="service_area">
					<div class="title">
						<p>${channel.channelName}</p>
					</div>
					<div class="download_list">
					<#list documentList as li>
						<#if li_index  == 0>
						<h4>${channel.channelName}����</h4>
						</#if>
						<div class="universalquery_content_center_list"  id='s_${li_index}'>
						<ul>
								<li><span class="block"></span><a href="${li.docID}.jsp">${li.docName}</a><span class="date">[���·���ʱ�䣺${li.pubTime}]</span></li>						
						</ul>
						</div>
					</#list>
					</div>
					<div id="universalquery_content_article_bottom" class="badoo">
					<#list documentList as doc>
						<#if doc_index % pageSize == 0>
						<a id='p_${doc_index/pageSize + 1}' href="#" onclick="goPage(${doc_index/pageSize + 1})">${doc_index/pageSize + 1}</a>
						<span id="c_${doc_index/pageSize + 1}" title="${doc_index/pageSize + 1}">${doc_index/pageSize + 1}</span>
						</#if>
					</#list>
					</div>
					
				</div>
			</div>
			<!-- �Ҳ������ݽ��� -->
		</div>
	</div>
</div>

<!-- �м��������ݽ��� -->

<!-- ҳβ��ʼ -->
<jsp:include page="/footer.jsp"></jsp:include>
<!-- ҳβ���� -->


</body>
</html>