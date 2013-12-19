<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
	<title>电子商务管理系统-产品目录查询</title>
	<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body topmargin="0" leftmargin="0">
	<div class="select_header_top_bg">
		<div class="select_header_top_left1"></div>
		<div class="select_header_top_left2"></div>
		<div class="select_header_top_title">
			<div class="select_header_top_title_content" style="width:180px;">产品目录维护</div>
		</div>
		<div class="select_header_top_right1"></div>
		<div class="select_header_top_right2"></div>
	</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" action="${ctx}/marketing/findProductDirectory.do" method="post" target="fraSearchList">
			<table class="table_style" align="center" width="98%">
			<tr>
					<td class="td_head td_head_center" width="120" nowrap>电子商务产品ID：</td>
					<td class="td_body"> <input type="text" id="eid" name="geDirectory.eid" style="width:170px;" maxlength="25">
					</td>
					<td class="td_head td_head_center" width="120" nowrap>
						产品代码/险种代码：
					</td>
					<td class="td_body">
						<input type="text" id="coreProductCode" name="geDirectory.coreProductCode" style="width:170px;" maxlength="25">
					</td>
					
				</tr>
				<tr>
					<td class="td_head td_head_center" width="120" nowrap>产品名称：</td>
					<td class="td_body" colspan="3"> <input type="text" id="productName" name="geDirectory.productName" style="width:170px;" maxlength="25">
					</td>
					<td style="display:none;" class="td_head" width="130px" nowrap>产品上下架：</td>
					<td style="display:none;" class="td_body"><select id="isProductShelf" name="geDirectory.isProductShelf" onchange="" style="width: 80px;">
						<option value="01" selected>--上架--</option>
					</select></td>
				</tr>
				<tr height="60px;">
					<td colspan="4" align="right">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="10">
								</td>
								<td class="btnBigOnFocus" onclick="doSearch();" nowrap>查询</td>
								<td class="btnBigOnFocus" align="right" onclick="javascript:$('form')[0].reset();" nowrap>清空</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function doSearch() {
			window.parent.fraToolbar.document.getElementById("idStr").value = "";
			window.parent.fraToolbar.document.getElementById("count").value = "";
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		}
		$(function(){
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		});
	</script>
</body>
</html>
