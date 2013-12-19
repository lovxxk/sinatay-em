<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<script type="text/javascript">
		window.onload=function(){
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		};
	</script>
	<title>电子商务管理系统-个人客户查询</title>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">个人客户维护</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/business/customerManage/personalUser/queryGeUserPersonalByDisPage.do" target="fraSearchList">
			<table align="center" width="720px" border="0">
				<tr>
					<td class="td_head" width="66px" nowrap>账号：</td>
					<td class="td_body">
						<input type="text" id="userAccount" name="geUserPersonal.userAccount" style="width:170px;" maxlength="30">
					</td>
					<td class="td_head" width="66px"  nowrap>证件类型：</td>
					<td class="td_body"  colspan="2">
						<select id="identifyType" name="geUserPersonal.identifyType" >
							<option value="" selected="selected" >全部</option>
							<c:forEach items="${identifyList }" var="identify">
								<option value="${identify.id.codeCode }">${identify.codeCName }</option>
							</c:forEach>
							<option value="01">身份证</option>
						</select>
					</td>
				</tr>
				<tr>
					
					<td class="td_head" width="66px"  nowrap>证件号码：</td>
					<td class="td_body">
						<input type="text" id="identifyNumber" name="geUserPersonal.identifyNumber" style="width:170px;" maxlength="50">
					</td>
					<td class="td_head" width="66px"  nowrap>注册时间：</td>
					<td class="td_body" colspan="2">
						<input name="beginDate"  style="width:170px;" id="beginDate" readonly onclick="WdatePicker({maxDate:'%y-%M-%d'});" class="Wdate">
						到
						<input name="endDate"  style="width:170px;" id="endDate" readonly onclick="WdatePicker({maxDate:'%y-%M-%d'});" class="Wdate">
					</td>
				</tr>
				<tr height="60px">
					<td colspan="5" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur" align="right"  onmouseover="this.className='btnBigOnFocus'" 
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
$(document).ready(function(){
$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
var ids = ['des'];
    	var contents = ['说明:修改、查询或删除个人客户<br/>'
    	                + '使用人群:网站前台用户运维人员<br/>'
    	                + '配置条件:<br/>'
    	                + '注意事项:解绑时必须有该业务下的相应权限进行保单解绑<br/>'];
        	for ( var i = 0 ; i < ids.length ; i++ ){
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
								width:450,
								textAlign: 'left',
								background: '#e0f2ff', 
								tip:true,//控制左侧尖角是否出现
								padding :10
							}
						});
        	}
        	
});
	/**日期控件高度调整*/
	var CDH = new changeDateHeight();
	CDH.dateIds = 'beginDate,endDate';
	CDH.minHeight = '170,30,*,60,0';
	CDH.maxHeight = '220,30,*,60,0';
	CDH.changeHeight();
	
	function doSearch(){
		var userAccount = document.getElementById("userAccount");
		var identifyNumber = document.getElementById("identifyNumber");
		
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		
		userAccount.value = userAccount.value.replace("*","");
		identifyNumber.value = identifyNumber.value.replace("*","");
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
		
</script>
</html>
