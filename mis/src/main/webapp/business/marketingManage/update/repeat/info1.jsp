<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String messages = request.getParameter("message");
  String[] shows = messages.split("@");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<style type="text/css">
body{padding:0px; margin:0px; background-color:#FFFFFF; font-size:12px; font-family:Arial; color:#000000;}
div,p,h1,h2,h3,h4,h5,h6{padding:0px; margin:0px;}
ul,li{padding:0px; margin:0px; list-style-type:none;}


.popss_warpper{width:583px; height:376px;background:url(../../../../global/images/popss_bg.jpg) no-repeat; clear:both;}
.popss_title{clear:both; height:38px; line-height:38px; color:#FFFFFF; padding-left:36px; letter-spacing:8px;}
.popss_news{clear:both; background:url(../../../../global/images/popss_pic.jpg) no-repeat 175px 92px; padding:144px 0px 100px 261px;}
</style>
</head>
<body>
<div style="height:20px"></div>
<div style="padding-left:210px;">
	<div class="popss_warpper">
		<div class="popss_title">系统信息</div>
		<div class="popss_news"><%=shows[0] %>&nbsp;<a style="cursor: hand" onclick="view('<%=shows[1]%>')">点击查看重复活动</a></div>
		<c:if test="${!empty param.buttonValue}">
			<div style="padding-left:210px;">
				<c:if test="${param.buttonValue == 'close'}">
					<input type="button" value="关闭" onclick="closeButton();">
				</c:if>
				<c:if test="${param.buttonValue == 'back'}">
					<input type="button" value="关闭" onclick="javascript：window.top.close();">
				</c:if>
				<c:if test="${param.buttonValue == 'homePage'}">
					<input type="button" value="返回首页" onclick="javascript：window.top.location.href = '${ctx}/index.jsp';">
				</c:if>
			</div>
		</c:if>
	</div>
</div>
</body>
<script type="text/javascript">
	function view(id){
		window.open("${ctx}/marketing/view.do?activityId=" + id,"查询活动","top=100, left=100, width=1100,height=600,toolbar=no,menubar=no,scrollbars=yes");
	}
	function closeButton(){
		window.close();
	}
</script>
</html>