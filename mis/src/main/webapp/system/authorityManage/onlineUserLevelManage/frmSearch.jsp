<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<!-- ��ʾ��ʼ -->
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>

<title>�����������ϵͳ-�û���ѯ</title>
</head>
<body topmargin="0" leftmargin="0">
<div class="public_fun_title">ǰ̨���˵ȼ�Ȩ��ά��<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/authorityManage/searchGePersonalLevel.do" target="fraSearchList">
			<table class="table_style" align="center" width="730px">
				<tr>
					<td class="td_head" width="120px" nowrap>
						�û��ȼ���
					</td>
					<td class="td_body">
						<input type="text" id="codeCode" name="geCode.id.codeCode" style="width:170px;" maxlength="25">
					</td>
					<td class="td_head" width="120px" nowrap>
						�ȼ�������
					</td>
					<td class="td_body"  colspan="5">
						<input type="text" id="codeCName" name="geCode.codeCName" style="width:170px;" maxlength="25">
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
$(document).ready(function(){
	//pop��ʾ��Ϣ
    	var ids = ['des'];
    	var contents = ['˵����ά��ǰ̨�����û��ȼ�Ȩ��<br/>ʹ����Ⱥ��Ȩ�޹�����Ա<br/>����������<br/>ע������û��ȼ�ֻ�ܽ��о�ȷ��ѯ'];
        	for ( var i = 0 ; i < 1 ; i++ ){
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
//pop��ʾ��Ϣ����
});
function doSearch(){
	document.getElementById("frmInput").submit();
}

function doClear(){
	document.getElementById("frmInput").reset();
}
window.onload=function(){
	document.getElementById("pageNo").value = 1;
	document.getElementById("frmInput").submit();
};
</script>
</html>
