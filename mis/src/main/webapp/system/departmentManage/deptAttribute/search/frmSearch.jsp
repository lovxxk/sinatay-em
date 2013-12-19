<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<title>电子商务管理系统-查询在线报案信息信息</title>
</head>
<body topmargin="0" leftmargin="0">
<form id="frmInput" name="frmInput" method="post" action="${ctx}/system/deptAttribute/queryDeptAttributeByDisPage.do" target="fraSearchList">
	<div class="table_content">
			<table align="left" width="300px">
				<tr valign="middle">
					<td class="td_head" nowrap>
						属性值：
					</td>
					<td class="td_body" width="18%" >
						<input name="geDeptAttribute.attrValue"  style="width:170px;">
						<input name="geDeptAttribute.geDepartment.deptid" type="hidden" value="${param.deptid }"  style="width:170px;">
					</td>
					<td colspan="8" align="right">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="10">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
	</div>
</form>
</body>
<script type="text/javascript">
	function doSearch(){
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	window.onload=function(){
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	};
	function doClear(){
		document.getElementById("authorityDepartmentManager").value = "--全部--";
		document.getElementById("authorityid").value = "";
		document.getElementById("frmInput").reset();
	}
	function deptAuthIdQuery(){
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_B_CREP_M&type=2&receivedObj='+$("#authorityid").val()+'&receivedObjName='+$("#authorityDepartmentManager").val(),
				'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
	}
</script>
</html>