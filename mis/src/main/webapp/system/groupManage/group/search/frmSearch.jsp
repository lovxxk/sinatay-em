<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>电子商务管理系统-用户组查询</title>
</head>
<body topmargin="0" leftmargin="0">
<div class="public_fun_title">用户组维护</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/system/groupManage/queryGeGroupByDisPage.do" target="fraSearchList">
			<input type="hidden" id="sortName" name="sortName"/>
		<input type="hidden" id="sortId" name="sortId"/>
			<table align="center" width="700px">
				<tr>
					<td class="td_head" width="120px" nowrap>
						用户组编号：
					</td>
					<td class="td_body">
						<input type="text" id="groupId" name="geGroup.groupid" style="width:170px;" maxlength="20">
					</td>
					<td class="td_head" width="94px" nowrap>
						用户组名称：
					</td>
					<td class="td_body" >
						<input type="text" id="groupName" name="geGroup.groupname" style="width:170px;" maxlength="15">
					</td>
					
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>
						用户组类型：
					</td>
					<td class="td_body" >
						<select id="groupTypeId" name="geGroup.grouptypeid" >
							<option value="">--请选择--</option>
							<c:forEach items="${geGrouptypeList}" var="grouptype" varStatus="status">
							<option value="${grouptype.grouptypeid}"  ${geGroup.grouptypeid==grouptype.grouptypeid?"selected":""}>${grouptype.grouptypename}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_head" width="94px" nowrap>
						创建机构：
					</td>
					<td class="td_body" colspan="5">
						<div>
							<div style="width:180px;float:left;">
								<input type="text" id="authorityDepartmentManager" value="--全部--" style="width:170px;" readonly>
								<input type="hidden" id="authorityid" name="authorityid" value=""/>
							</div>
							<div style="float:left;">
								<input style="width:100px;" onclick="deptAuthIdQuery();" type="button" value="选择机构权限…" />
							</div>
						</div>
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="6" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
								<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
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
    doSearch();
	function doSearch(){
		//window.parent.fraToolbar.document.getElementById("idStr").value = "";
		//window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("sortName").value = "";
		document.getElementById("sortId").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
		document.getElementById("authorityDepartmentManager").value = "--全部--";
		document.getElementById("authorityid").value = "";
	}
	function deptAuthIdQuery(){
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_S_GROUP_M_S&type=2',
				'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
	}
	$(document).ready(function(){
		$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
		var ids = ['des'];
		    	var contents = ['说明:查询、修改或删除用户组<br/>'
		    	                + '使用人群:用户组管理人员<br/>'
		    	                + '配置条件:要先配置角色（如果已有需要的角色则不用）<br/>'
		    	                + '注意事项:用户组中只有加入了角色才能具有相应的功能权限<br/>'];
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

</script>
</html>
