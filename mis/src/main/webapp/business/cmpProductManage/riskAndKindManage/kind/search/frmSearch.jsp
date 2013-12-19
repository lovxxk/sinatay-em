<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>险别维护</title>
		<style type="text/css">
		#operatorTable td {
				vertical-align:middle;
				text-align:center;
				width:82px;
		}
		
		input,select {
			width:170px;
		}
	</style>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">
		险别维护<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
	</div>
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/business/cmpProductManage/riskAndKindManage/findKindList.do" target="fraSearchList">
			<table class="table_style" style="width:528px">
				<tr>
					<td class="td_head" width="80px" nowrap>
						险别代码：
					</td>
					<td class="td_body" >
						<input id="kindCode" type="text" name="geKind.id.kindCode"  maxlength="25">
					</td>
					<td class="td_head" width="100px" nowrap>
						险别中文名称：
					</td>
					<td class="td_body" >
						<input id="kindCName" type="text" name="geKind.kindCName"  maxlength="12">
					</td>
				</tr>
				<tr height="60px;" align="center">
					<td colspan="4" >
						<table id="operatorTable">
							<tr>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
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
</body>
<script type="text/javascript">
	function doSearch(){
		$("#enterpriseNameId").attr("value",$.trim($("#enterpriseNameId").val()));
		$("#organizeCodeId").attr("value",$.trim($("#organizeCodeId").val())); 
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		$("#kindCode").val(jQuery.trim($("#kindCode").val()));
		$("#kindCName").val(jQuery.trim($("#kindCName").val()));
		document.getElementById("frmInput").submit();
	}
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	$(function(){
		doSearch();
		
		
		var ids = ['des'];
		// <img src="'+contextRootPath+'/global/images/form_success.gif" />
		var contents = ['说明：查询险别。<br/>使用人群：车险产品管理配置人员。<br/>配置条件：险别配合险种使用。<br/>注意事项：'];
	    	for ( var i = 0 ; i < 10 ; i++ ){
				$('#' + ids[i]).qtip({ 
					content:contents[i], 
					position: { 
						corner: { 
						tooltip: 'topMiddle',
						target: 'bottomMiddle'
						} ,
						adjust: { 
							screen: true 
							}
					}, 
					 style: {
					border: { 
						width: 1,
						radius: 1,
						color: '#00739f'
						},
						width:350,
						textAlign: 'left',
						background: '#e0f2ff', 
						tip:true,//控制左侧尖角是否出现
						padding :10
					}
				});
	    	}
	});
</script>
</html>
