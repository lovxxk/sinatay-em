<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>��̩����-�ͷ�����-��������</title>
	<meta name="description" content="Ϊ���ṩ����Ͷ������������񡢲�ѯ�������������������ϲ�������ݡ���ʡ���ı���ʱ�䣡" />
	<meta name="keywords" content="Ͷ������,�������,��ѯ����,��������,�������,Ͷ������" />
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
   var url = location.search; //��ȡurl��"?"������ִ�
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
				<li class="at">��������</li>
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
						<p>��������</p>
					</div>
					<!-- �������⵼����start -->
					<div class="problem_cate">
					<jsp:include page="../qa_nav.jsp"></jsp:include>
					</div>
					<!-- �������⵼����end -->
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