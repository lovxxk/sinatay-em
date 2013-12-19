<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<title>电子商务管理系统-工作流上传文件查询</title>
</head>
<body topmargin="0" leftmargin="0">
	<div class="select_header_top_bg">
		<div class="select_header_top_left1"></div>
		<div class="select_header_top_left2"></div>
		<div class="select_header_top_title">
			<div class="public_fun_title">工作流配置文件维护</div>
		</div>
		<div class="select_header_top_right1"></div>
		<div class="select_header_top_right2"></div>
	</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/workFlow/searchWf.do" target="fraSearchList">
			<table class="table_style" align="center" width="600px">
				<tr>
					<td class="td_head" width="70px" nowrap>
						文件类型：
					</td>
					<td class="td_body" width="220px" >
						<select onchange="change(this.value);" name="geWorkflow.id.filetype">
							<option value="0">任务人员配置文件</option>
							<option value="1">工作流流程配置文件</option>
						</select>
					</td>
					<td class="td_head" width="70px" nowrap>
						业务领域：
					</td>
					<td class="td_body" width="220px" >
							<select  id="sel_1" name="geWorkflow.id.area">
							<option value="">--请选择--</option>
							
							<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
								<option value="${GeCode_businessArea.id.codeCode}">${GeCode_businessArea.codeCName}</option>
							</c:forEach>
							<!-- 
							<c:forEach items="${BusinessArea}" var="area">
								集团不显示 
								<c:if test="${area.id.codeCode!=1}">
									<option value="${area.id.codeCode}">${area.codeCName}</option>
								</c:if>
							</c:forEach>
							-->
						</select>
					</td>
					<td class="td_head" width="70px" nowrap>
						功能类型：
					</td>
					<td class="td_body" width="220px">
						<select id="sel_2" name="geWorkflow.id.funcitontype">
							<option value="">--请选择--</option>
							<c:forEach items="${workFlow}" var="workflow">
								<option value="${workflow.key }">${workflow.value }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="6" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>清空</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
<br/><br/>
</body>
<script type="text/javascript">
	window.onload = function (){
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}

	$(function (){
		change(0);
	});
	
	function change(val){
		if(val==0){
			$("#sel_1").attr("disabled","disabled");
			$("#sel_2").attr("disabled","disabled");
		}else if(val==1){
			document.getElementById("sel_1").disabled = "";
			document.getElementById("sel_2").disabled = "";
			
			//$("#sel_1").removeAttr("disabled");
			//$("#sel_2").removeAttr("disabled");
		}
	}
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
</script>
</html>