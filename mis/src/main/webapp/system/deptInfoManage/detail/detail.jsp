<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>详细信息</title>
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			详细信息
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<table class="table_Show" align="center" width="450px" border="0" >
	<tr><td height="25px"></td></tr>
	<tr>
		<td class="td_head" >属性编码：</td>
		<td class="td_body">
			${geDeptInfo.attrID}
		</td>
	</tr>
	<tr>
		<td class="td_head">属性名称：</td>
		<td class="td_body">${geDeptInfo.attrName }</td>
	</tr>
	<tr>
		<td class="td_head">
			属性描述
		</td>
		<td class="td_body">
		  <textarea  rows="5" cols="30" disabled>${geDeptInfo.attrDescription}</textarea>
		</td>
	</tr>
	<tr height="10px"><td colspan="2">&nbsp;</td></tr> 
	   	<tr height="80px">
   		<td  colspan="2" align="center">
	   		<table width=82 align="center">			
				<tr>
					<acc:showView source="ROLE_DEPT_INFO_U">
					<td align=right class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>编辑</td>
					</acc:showView>
					<acc:showView source="ROLE_DEPT_INFO_D">
					<td align=right class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>删除</td>
					</acc:showView>
					<td align=right class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>关闭</td>
				</tr>
			</table>
   		</td>
   	</tr>
</table>
	<form action="" id="myform" target="myframe">
		<input type="hidden" value="${geDeptInfo.attrID}" name="geDeptInfo.attrID">
	</form>
	<iframe name="myframe" style="display:none;"></iframe>
</div>
</body>
<script type="text/javascript">
function doEdit(){
	location.href="${ctx}/system/deptInfoManage/queryDeptInfoForUpdate.do?geDeptInfo.attrID=" +'${geDeptInfo.attrID}';
}
function doDelete(){
	if(confirm("确定删除该机构属性吗？")){
		$("#myform")[0].action =  "${ctx}/system/deptInfoManage/deleteDeptInfo.do";
		$("#myform")[0].submit();
	}
}
</script>
</html>