<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
    <script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>电子商务管理系统-机构邮箱配置查询</title>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">
	机构邮箱维护<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
	</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/system/configManage/emailConfig/findDeptMail.do" target="fraSearchList">
			<table class="table_style" align="center" width="800px">
				<tr>
					<td class="td_head" width="108px" nowrap>
						机构邮箱名称：
					</td>
					<td class="td_body" width="125px">
						<input id="mail" width="120" name="geDeptMail.mail" type="text" maxlength=200 onchange="trimRe(this);" class=required>
					</td>
					<td class="td_head" width="80px"  nowrap>
	                                                      适用功能：
	                 </td>
	                  <td class="td_body" width="105px">
	                     <select id="functionID" name="geDeptMail.functionID" style="width:90px">
                             <option value="">请选择</option>
                             <s:iterator value="#request.sendMailTypeList" var="code">
                                <option value="<s:property value="#code.id.codeCode"/>"><s:property value="#code.codeCName"/></option>
                             </s:iterator>
	                     </select>
                      </td>
					<td class="td_head td_head_center" width="80px" nowrap>
   					  所属机构：
   				    </td>
   				    <td class="td_body">
      					<div>
      						<div style="width:190px;float:left;">
      							<input type="text" id="authorityDepartmentManager" value="--全部--" style="width:100px;" readonly>
      							<input style="width:80px;" onclick="deptAuthIdQuery();" type="button" value="选择机构权限" />
      							<input type="hidden" id="authorityid" name="geDeptMail.deptID" value=""/>
      						</div>
      					</div>
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
	
		var ids = ['des'];
		// <img src="'+contextRootPath+'/global/images/form_success.gif" />
		var contents = ['说明：机构邮箱查询。<br/>使用人群：系统配置人员 <br/>配置条件：用于维护各个业务部门的客服邮箱 <br/>注意事项：邮箱或手机可用。'];
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
	function deptAuthIdQuery(){
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_S_ECON_M&type=2&receivedObj='+$("#authorityid").val()+'&receivedObjName='+$("#authorityDepartmentManager").val(),
				'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
	}
	function trimRe(field){
		field.value = field.value.replace(/(^\s*)|(\s*$)/g, ""); 
	}
</script>
</html>
