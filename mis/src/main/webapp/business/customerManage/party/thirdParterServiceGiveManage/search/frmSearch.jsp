<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>电子商务管理系统-商品赠送管理</title>
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
	商品赠送维护<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
	</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/party/findGeThirdParterSerialNumber.do" target="fraSearchList">
		<table class="table_style" align="center" width="800px">
				<tr>
					<td class="td_head" width="80px" nowrap>
						投保单号：
					</td>
					<td class="td_body" width="125px" >
						<input type="text" id="proposalNo" name="geThirdParterSerialNumber.proposalNo" style="width:120px;" maxlength="25">
					</td>
					<td class="td_head" width="60px"  nowrap>
						用户ID：
					</td>
					<td class="td_body"  width="125px">
						<input type="text" id="userId" name="geThirdParterSerialNumber.userID" style="width:120px;" maxlength="25">
					</td>
					
					<td class="td_head" width="80px"  nowrap>
						投保地区：
					</td>
					<td class="td_body" >
						<input type="text" id="authorityDepartmentManager" style="width:120px;" maxlength="25" readonly="readonly">
						<input type="hidden" id="authorityid" name="geThirdParterSerialNumber.proposalArea"  maxlength="25" readonly="readonly" >
						<input type="button" value="选择投保地区" style="width:75px" onclick="alterTree();">
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="4" align="right">
						<table id="operatorTable">
							<tr>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
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
		document.getElementsByName("geThirdParterSerialNumber.proposalNo")[0].value=jQuery.trim($("#proposalNo").val());
		document.getElementsByName("geThirdParterSerialNumber.userID")[0].value = jQuery.trim($("#userId").val());
		
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	
		var ids = ['des'];
		// <img src="'+contextRootPath+'/global/images/form_success.gif" />
		var contents = ['说明：查询商品赠送。 <br/>使用人群：配置第三方人员。 <br/>配置条件：<br/>注意事项：'];
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
		//trim   
		document.getElementsByName("geThirdParterSerialNumber.proposalNo")[0].value=jQuery.trim($("#proposalNo").val());
		document.getElementsByName("geThirdParterSerialNumber.userID")[0].value = jQuery.trim($("#userId").val());
		
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	
	//弹出一棵树
	function alterTree(){
		var authorityid = document.getElementById("authorityid").value;
		var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?checkType=2&authorityid=ROLE_B_TPSG_M&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
				'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
	}
</script>
</html>
