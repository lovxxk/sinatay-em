<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
	<link href="/mis/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/mis/global/js/jquery-1.6.2.min.js"></script>

	<link type="text/css" rel="stylesheet" href="/mis/global/js/validate/css/validate.css" />
	<script type="text/javascript" src="/mis/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
</head>


<body>

<div style="height:50px"></div>
		<table align="center" style="margin-bottom:30px;">
		<c:forEach var="productWebFlowElement" items="${list}">
			<tr>
				<td class="td_head" width="50%" >${productWebFlowElement[1]}:</td>
				<td class="td_body" width="50%" >
					<c:if test="${productWebFlowElement[3] ==0}">
						<s:set name="has" value="1"/>
					</c:if>
					${productWebFlowElement[3] ==1? '已编辑':''}
				</td>
			</tr>
		</c:forEach>
		
		</table>

	<table align="center" style="margin-bottom:30px;">
	<tr>
		<td id="SubmitButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" nowrap onclick="doit()">确认保存</td>
		<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.close();" nowrap >关闭</td>
	</tr>
	</table>
	<input type="hidden" id="elementCode" name="elementCode" value="${param.elementCode}" />
	<input type="hidden" id="coreProductCode" name="coreProductCode" value="${param.coreProductCode}" />
</body>

<script type="text/javascript">
var count=0;

function nothing(){}
function doit(){
	if("1"=="${has}"){
		//alert("请先确保全部都已编辑再点击确认!");
		//return;
	}
	
	if(count==1)
		return;
	
	count = 1;
	$.ajax({
		url : "${ctx}/productManage/saveDetailOverview.do",
		data : {
			"coreProductCode" : "${coreProductCode}"
		}, 
		type : 'POST',
		dataType : 'text',
		error : function() {
			alert("程序出现异常,请稍后操作!");
			count =0;
		},
		success : function(data) {
			if (data == "suc") {
				alert("确认保存并提交审核！");
				parent.window.close();
				window.parent.opener.parent.frames[0].doSearch();
			}else if(data == "edit") {  
				alert("请先确保全部都已编辑再点击确认!");
				count =0;
				return;
			}else {
				alert("程序出现异常！");
				count =0;
			} 
		}
	});
	
}

</script>
</html>