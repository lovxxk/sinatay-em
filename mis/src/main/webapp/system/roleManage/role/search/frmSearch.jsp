<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>�����������ϵͳ-��ɫ��ѯ</title>
</head>
<body topmargin="0" leftmargin="0">
<div class="public_fun_title">��ɫά��</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/system/roleManage/queryGeRoleByDisPage.do" target="fraSearchList">
			<input type="hidden" id="sortName" name="sortName"/>
		<input type="hidden" id="sortId" name="sortId"/>
			<table align="center" width="700px">
				<tr>
					<td class="td_head td_head_center" width="120px" nowrap>
						��ɫ��ţ�
					</td>
					<td class="td_body">
						<input type="text" id="roleid" name="roleid" style="width:170px;" maxlength="25">
					</td>
					<td class="td_head td_head_center" width="120px" nowrap>
						��ɫ���ƣ�
					</td>
					<td class="td_body">
						<input type="text" id="rolename" name="rolename" style="width:170px;" maxlength="25">
					</td>
				</tr>
				<tr>
				<td class="td_head td_head_center" width="120px" nowrap>
						����������
					</td>
					<td class="td_body" colspan="3">
						<div>
							<div style="width:180px;float:left;">
								<input type="text" id="authorityDepartmentManager" value="--ȫ��--" style="width:170px;" readonly>
								<input type="hidden" id="authorityid" name="authorityid" value=""/>
							</div>
							<div style="float:left;">
								<input style="width:100px;" onclick="deptAuthIdQuery();" type="button" value="ѡ�����Ȩ�ޡ�" />
							</div>
						</div>
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="4" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>��ѯ</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>���</td>
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
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("sortName").value = "";
		document.getElementById("sortId").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("authorityDepartmentManager").value = "--ȫ��--";
		document.getElementById("authorityid").value = "";
		document.getElementById("frmInput").reset();
	}
	function deptAuthIdQuery(){
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_S_ROLE_M&type=2',
				'����' ,'top=100, left=500, width=400,height=500,toolbar=no');
	}
	$(document).ready(function(){
		$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
		var ids = ['des'];
		    	var contents = ['˵��:��ѯ���޸Ļ�ɾ����ɫ<br/>'
		    	                + 'ʹ����Ⱥ:��ɫ������Ա<br/>'
		    	                + '��������:<br/>'
		    	                + 'ע������:����ʱ��ɫ����Ҫ����<br/>'];
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
										tip:true,//����������Ƿ����
										padding :10
									}
								});
		        	}
		});
</script>
</html>
