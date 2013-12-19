<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
		<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<script type="text/javascript">
		window.onload=function(){
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		};
	</script>
	<title>�����������ϵͳ-��ƷͼƬά��</title>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">��ƷͼƬά��</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/business/cmpProductManage/productPictureManage/queryGeProductPictureByDisPage.do" target="fraSearchList">
			<table align="center" width="372px">
				<tr>
					<td class="td_head" width="52px" nowrap>
						���ƣ�
					</td>
					<td class="td_body">
						<input type="text" id="picturename" name="geProductPictureDetail.picturename" style="width:170px;" maxlength="25">
					</td>
					<td class="td_head" width="80px"nowrap>
						�Ƿ����ã�
					</td>
					<td class="td_body">
						<select id="appleflag" name="geProductPictureDetail.flag" style="width:80px;">
							<option  value="">--ȫ��--</option>
							<option value="1" selected="selected">��</option>
							<option value="0">��</option>
						</select>
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
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	$(document).ready(function(){
		$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
		var ids = ['des'];
		    	var contents = ['˵��:��ѯ���޸Ļ�ɾ�������Ĳ�ƷͼƬ<br/>'
		    	                + 'ʹ����Ⱥ:��Ʒ������Ա<br/>'
		    	                + '��������:<br/>'
		    	                + 'ע������:����ʱ�ϴ�ͼƬʱע��ͼƬ�ĸ�ʽ���С<br/>'];
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
