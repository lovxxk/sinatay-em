<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>�����������ϵͳ-�ͻ����û��ͽӿڼ��ϵ��Ϣά��</title>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">�ͻ����û��ͽӿڼ��ϵ��Ϣά��</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/interfacePortal/searchInterfaceInfoClientUserIdPage.do" target="fraSearchList">
			<table class="table_style" align="center" width="720px">
				<tr>
					<td class="td_head" width="120" nowrap>
						���״��룺 
					</td>
					<td class="td_body">
						<input type="text" id="transCode" name="interfaceInfo.transCode" maxlength="50" style="width:170px;">
					</td>
					<td class="td_head" width="120" nowrap>
						�������ƣ�
					</td>
					<td class="td_body">
						<input type="text" id="transName" name="interfaceInfo.transName" maxlength="50" style="width:170px;">
					</td>
				</tr>
				<tr>
					<td class="td_head" width="120" nowrap>
						�ͻ����û������� 
					</td>
					<td class="td_body">
						<input type="text" id="id" name="interfaceInfo.clientUser.id" maxlength="50" style="width:170px;">
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