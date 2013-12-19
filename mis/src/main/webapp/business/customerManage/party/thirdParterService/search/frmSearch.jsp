<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>电子商务管理系统-第三方合作伙伴</title>
	<style type="text/css">
		#operatorTable tr {
				vertical-align:middle;
				text-align:center;
				width:82px;
			
		}
	</style>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">
	第三方产品维护<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
	</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/party/findGeThirdParterService.do" target="fraSearchList">
			<input type="hidden" name="nameCount" value="<s:property value="nameCount"/>">
			<table class="table_style" width="530px">
				<tr>
					<td class="td_head td_head_center" width="80px"  nowrap>
						商品名称：
					</td>
					<td class="td_body" width="125px">
						<input type="text" name="geThirdParterService.itemName" style="width:120px;" >
					</td>
					<td class="td_head td_head_center" width="80px" nowrap>
						所在公司：
					</td>
					<td class="td_body">
					<input type="text" name="thirdParterName" style="width:120px;" readonly="readonly">
					<input type="hidden"  name="geThirdParterService.geThirdParterInfo.thirdParterID" >
					<input type="button" value="选择公司" style="width:60px" onclick="alertThirdService();">
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="4" align="center">
						<table id="operatorTable" >
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
	window.onload = function(){
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
		doSearch();
		
		var ids = ['des'];
		// <img src="'+contextRootPath+'/global/images/form_success.gif" />
		var contents = ['说明：查询第三方 。<br/>使用人群：配置第三方人员。<br/>配置条件：<br/>注意事项：'];
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
	//弹出一棵树
	//弹出一个商品的提示框
	function alertThirdService(){
		window.open(contextRootPath+"/business/customerManage/party/thirdParterService/create/selectThirdParterInfo/index.jsp","查询供应商" ,"top=100, left=100, toolbar=no,resizable=yes");
	}
</script>
</html>
