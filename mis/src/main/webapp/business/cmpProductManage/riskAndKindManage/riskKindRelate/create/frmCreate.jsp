<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>frmCreate.jsp</title>
</head>
<body onload="pageValidate();">
<div class="public_fun_title">
	新建险种险别主附险关系
</div>
<form action="${ctx}/business/cmpProductManage/riskAndKindManage/createCode.do" id="frmInput" method="post" target="myFrame">
<table align="center" width="720px"  id="productTable">
	<tr >
		<td class="td_head" width="" nowrap>险种代码：</td>
		<td class="td_body">
			<input id="riskCodeV" type="text" name="geKindRelate.id.riskCode" style="width:170px;" maxlength=80 ondblclick="alertNationality();" readonly="readonly" >(双击域选择)
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>主险险别代码：</td>
		<td class="td_body" >
			<input type="text" name="geKindRelate.id.kindCode" id="kindCodeMain" style="width:170px;" ondblclick="alertKindCodeMain();" readonly="readonly" maxlength="6">(双击域选择)
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>附加险代码：</td>
		<td class="td_body" >
			<input type="text" name="geKindRelate.id.relateKindCode"  id="kindCodeAd" style="width:170px;" ondblclick="alertKindCodeAddition();" readonly="readonly" maxlength="6">(双击域选择)
		</td>
	</tr>
	<tr>
	<td class="td_head"  nowrap>业务领域：</td>
		<td class="td_body" >
			<select name="geKindRelate.businessArea" style="width:170px;">
			    <option value="">--请选择--</option>
			  <c:forEach var="bussinessArea" items="${list}"> 
			     <option  value="${bussinessArea.id.codeCode}">${bussinessArea.codeCName}</option>
			  </c:forEach>
			 </select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>标识：</td>
		<td class="td_body" >
			<input type="text" name="geKindRelate.flag"  style="width:170px;" maxlength="3">
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
		<div style="padding-right: 150px">
			<table width=200 align="center">
			<tr>
				<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建 </td>
				<td width=5>&nbsp;</td>
				<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
			</tr>
			</table>
			</div>
		</td>
	</tr>
 </table> 
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
//------------------- 开始处理验证 ----------------------------------------
function pageValidate(){
	tt.vf.req.add("geKindRelate.id.riskCode","geKindRelate.id.relateKindCode","geKindRelate.id.kindCode"); 
}

//清空输入
function doClear(){
	document.getElementById("frmInput").reset();
}
//处理险种代码双击域
function alertNationality(){
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/kind/create/searchRiscode/index.jsp","险种代码" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
//处理险别主险代码双击域
function alertKindCodeMain(){
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/riskKindRelate/create/searchKindCode/index.jsp?validInd=1","险种代码" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
//处理险别附加险代码双击域
function alertKindCodeAddition(){
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/riskKindRelate/create/searchKindCode/index.jsp?validInd=0","险种代码" ,"top=100, left=100, width=900,height=600,toolbar=no");
}

function doCreate(){
	if(tt.validate()){
		$("#frmInput").submit();
	}
}

</script>
</html>