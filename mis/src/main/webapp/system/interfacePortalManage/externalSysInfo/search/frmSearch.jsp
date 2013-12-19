<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<title>�����������ϵͳ-�ⲿϵͳ��Ϣά��</title>
<script type="text/javascript">
$(document).ready(function(){
	//�������ֵ��ȡ�ⲿϵͳ����
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"ExternalSysType"},
		error : function() {
		},
		success : function(data) {
			$("#externalSysType").empty();
			$("#externalSysType").append("<option value='' selected='selected'>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				$("#externalSysType").append("<option value='" + data[i][0]+ "'>" + data[i][1] + "</option>");
			}
		}
	});
});
</script>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">�ⲿϵͳ��Ϣά��</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/interfacePortal/searchExternalSysInfoPage.do" target="fraSearchList">
			<table class="table_style" align="center" width="720px">
				<tr>
					<td class="td_head" width="120" nowrap>
						�ⲿϵͳ������ 
					</td>
					<td class="td_body">
						<input type="text" id="externalSysId" name="externalSysInfo.externalSysId" maxlength="50" style="width:170px;">
					</td>
					<td class="td_head" width="120" nowrap>
						�ⲿϵͳ���ƣ�
					</td>
					<td class="td_body">
						<input type="text" id="externalSysName" name="externalSysInfo.externalSysName" maxlength="50" style="width:170px;">
					</td>
				</tr>
				<tr>
					<td class="td_head" width="120" nowrap>
						�ⲿϵͳ���ͣ� 
					</td>
					<td class="td_body">
						<select id="externalSysType" name="externalSysInfo.externalSysType" style="width:170px;">
						</select>
					</td>
				</tr>				
				<tr height="60px;">
					<td colspan="8" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>��ѯ</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" align="right" onclick="doClear()" nowrap>���</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
		</form>
	</div>
</body>
<script type="text/javascript">
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