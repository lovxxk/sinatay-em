<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
		<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
		<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<title>�ձ��ϵά��</title>
</head>
<body topmargin="0" leftmargin="0">
    <div class="public_fun_title">
		�ձ��ϵά��
	</div>
	<div class="table_content">
	<form id="frmInput" name="frmInput" action="${ctx}/business/cmpProductManage/riskAndKindManage/queryKindRelate.do" method="post" target="fraSearchList">
		<table class="table_style" align="center" width="98%">
			<tr>
				<td class="td_head" width="10%" nowrap>
					�����ձ���룺
				</td>
				<td class="td_body" width="15%" >
					<input type="text" name="geKindRelate.id.kindCode" id="kindCode" style="width:170px;" maxlength="25">
				</td>
				<td class="td_head" width="10%" nowrap>
					�����մ��룺
				</td>
				<td class="td_body" width="15%" >
					<input type="text"  name="geKindRelate.id.relateKindCode" id="relateCode" style="width:170px;" maxlength="25">
				</td>
			<tr height="60px;">
				<td colspan="8" align="center">
					<table>
						<tr>
							<td nowrap>
								<input type="hidden" name="pageNo" id="pageNo" value="1">
								<input type="hidden" name="pageSize" id="pageSize" value="20">
							</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSearch();" nowrap>��ѯ</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>���</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	function doSearch(){
		$("#kindCode").val(jQuery.trim($("#kindCode").val()));
		$("#relateCode").val(jQuery.trim($("#relateCode").val()));
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}

	$(function(){
		doSearch();
	});

</script>
</body>
</html>
