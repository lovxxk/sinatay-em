<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>信泰保险-客服中心-常见问题</title>
	<meta name="description" content="为您提供网上投保服务、理赔服务、查询服务、在线自助服务，网上操作更便捷、节省您的宝贵时间！" />
	<meta name="keywords" content="投保服务,理赔服务,查询服务,自助服务,服务帮助,投保流程" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<%@ include file="/global/ui/taglibs_head.jsp"%>

		<link href="${ctx}/global/css/global.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/frame.css" rel="stylesheet" type="text/css"/>
		
		<script src="${ctx}/global/js/jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/main.js" type="text/javascript"></script>
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/frame.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_problem.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_problem.js" type="text/javascript"></script>
	<script type="text/javascript">
function GetRequest() {
   var url = location.search; //获取url中"?"符后的字串
   var theRequest = new Object();
   if (url.indexOf("?") != -1) {
      var str = url.substr(1);
      strs = str.split("&");
      for(var i = 0; i < strs.length; i ++) {
         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
      }
   }
   return theRequest;
}


			$(window).load(function(){
			var Request = new Object();
			Request = GetRequest();
			var id;
			id = Request['docindex'];
			$('#'+id).click();
			
			});
	</script>
	</head>
<body>
<!-- 页头 开始-->
<jsp:include page="/header.jsp"></jsp:include>
<!-- 页头结束 -->

<!-- 中间内容开始 -->
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">常见问题</li>
			</ul>
		</div>
		<div class="service_main">
			<jsp:include page="/web/service/leftDynamic.jsp"></jsp:include>
			<!-- 右侧主内容开始 -->
			<div class="right_content">
				<div class="service_head">
					<img src="${ctx}/resources/image/service/service_center_head.jpg">
				</div>
				<div class="service_area">
					<div class="title">
						<p>常见问题</p>
					</div>
					<!-- 常见问题导航栏start -->
					<div class="problem_cate">
					<jsp:include page="../qa_nav.jsp"></jsp:include>
					</div>
					<!-- 常见问题导航栏end -->
					<div class="problem_main">
						<div class="problem_list">
						<#list documentList as li>
							<div class="problem">
								<div class="question" id="${li_index+1}"><span></span>${li.docName}</div>
								<div class="answer">${li.docContent}</div>
							</div>
						</#list>
						</div>
					</div>
				</div>
			</div>
			<!-- 右侧主内容结束 -->
		</div>
	</div>
</div>

<!-- 中间内容内容结束 -->

<!-- 页尾开始 -->
<jsp:include page="/footer.jsp"></jsp:include>
<!-- 页尾结束 -->


</body>
</html>