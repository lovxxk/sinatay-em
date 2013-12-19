<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
<title></title>
<style type="text/css">
.prompt_popup_wapper{clear:both; width:625px; height:388px; background:url(../images/prompt_popup_bg.jpg) no-repeat;}
.prompt_popup_title{clear:both; height:35px; }
.prompt_popup_title h4{line-height:55px; font-size:14px; color:#FFFFFF; padding-left:45px; font-weight:lighter; letter-spacing:8px;}
.prompt_popup_news1{height:200px; background:url(../images/prompt_popup_img2.jpg) no-repeat 178px 80px; padding:140px 0px 0px 295px; font-size:14px;}
</style>
</head>
<body>
<div style="height:10px"></div>
<div  align="center">

	<div class="prompt_popup_wapper">
		<div class="prompt_popup_title"><h4>系统信息</h4></div>
	    <div class="prompt_popup_news1" style="float: left;height: 60px;margin-top: -30px;">${param.message}
	    	<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<div>
				<table style="float: left;margin-left: -22px;">
					<tr style="float:left;">
						<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'"  onclick="reBack();">返回</td>
					</tr>
				</table>
	    	</div>
	    </div>
	    <c:if test="${not empty param.buttonValue}">
			<div style="padding-left:210px;">
				<c:if test="${param.buttonValue == 'close'}">
					<input type="button" value="关闭" onclick="javascript：window.top.close();">
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

<script type="text/javascript">
function reBack(){
		window.location.href="${ctx}/business/cmpProductManage/riskAndKindManage/findGeKindList.do";
}

</script>

</body>
</html>