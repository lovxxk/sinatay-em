<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript">
		window.onload=function(){
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		};
	</script>
	<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>	
	<title>�����������ϵͳ-��������ѯ</title>
</head> 
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">������ά��</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/business/customerManage/blacklist/queryBlackListByDisPage.do" target="fraSearchList">
			<table align="center" width="632px">
				<tr>
					<td class="td_head" width="52px" nowrap>
						������
					</td>
					<td class="td_body">
						<input type="text" id="userName" name="geBlackList.userName" style="width:170px;" maxlength="25">
					</td>
					<td class="td_head" width="80px"  nowrap>
						֤�����ͣ�
					</td>
					<td class="td_body">
						<select id="identifyType" name="geBlackList.identifyType" style="width:80px">
							<option value="">ȫ��</option>
							<c:forEach items="${idTypeList}" var="GeCode_idType" step="1" varStatus="status">
								<option value="${GeCode_idType.id.codeCode}">${GeCode_idType.codeCName}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_head" width="80px"  nowrap>
						֤�����룺
					</td>
					<td class="td_body" colspan="3">
						<input type="text" id="identifyNumber" name="geBlackList.identifyNumber" style="width:170px;" maxlength="25">
					</td>
				</tr>
				<tr height="60px">
					<td colspan="6" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>��ѯ</td>
								<td class="btnBigOnBlur" align="right"  onmouseover="this.className='btnBigOnFocus'" 
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
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	$(document).ready(function(){
		$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
		var ids = ['des'];
		    	var contents = ['˵��:��ѯ���޸Ļ�ɾ���Ѽ��뵽�������е��û�<br/>'
		    	                + 'ʹ����Ⱥ:������¼��͹�����Ա<br/>'
		    	                + '��������:<br/>'
		    	                + 'ע������:���º�����ԭ��Ҫ��������������Ժ���Աά��<br/>'];
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
