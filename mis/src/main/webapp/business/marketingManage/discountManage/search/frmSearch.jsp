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
	<style type="text/css">
		#operatorTable tr {
				vertical-align:middle;
				text-align:center;
				width:82px;
			
		}
	</style>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">打折活动维护</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/marketing/findGeDiscountManage.do" target="fraSearchList">
			<table class="table_style" align="center" width="98%">
				<tr>
					<td class="td_head td_head_center" width="120" nowrap>活动代码：</td>
					<td class="td_body"> <input type="text" id="discountid" name="geDiscountManage.discountid" style="width:170px;" maxlength="25">
					</td>
					<td class="td_head td_head_center" width="120" nowrap>
						电子商务产品EId：
					</td>
					<td class="td_body">
						<input type="text" id="eid" name="geDiscountManage.eid" style="width:170px;" maxlength="25">
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="4" align="center">
						<table id="operatorTable">
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>清空</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
<script type="text/javascript">

	/**日期控件高度调整*/
// 	var CDH = new changeDateHeight();
// 	CDH.dateIds = 'FSubmitTime,TSubmitTime';
// 	CDH.changeHeight();
	
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("eid").value = "";
		document.getElementById("discountid").value = "";
		document.getElementById("frmInput").reset();
	}
	$(function(){
		doSearch();
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	});
</script>
</html>