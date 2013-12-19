<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>	
<title>frmCreate.jsp</title>
</head>
<body>
<div class="public_fun_title">�½��û�������</div>
<div style="height:60px">&nbsp;</div>
<form action="${ctx}/system/groupManage/createType.do" id="frmInput" method="post" target="myFrame">
<table class="table_style" align="center" width="650px" id="productTable">
<tr>
	<td class="td_head" style="height:30px;" nowrap>�û����������ƣ�</td>
	<td class="td_body">
		<input id="grouptypeName" name="geGrouptype.grouptypename" type="text" style="width:170px;" class=required maxlength=15>
		<span id="grouptypeName_msg"></span>
	</td>
</tr>

<tr>
	<td class="td_head"  nowrap>�û�������������</td>
	<td class="td_body" >
		<textarea  id="grouptypeDesc" name="geGrouptype.grouptypedesc" style="width:400px;" rows="5" ></textarea>
	</td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td><span id="grouptypeDesc_msg"></span></td>
</tr>

<tr height=25><td></td></tr> 
<tr>
	<td colspan=2>
		<table width=164 align="center">
		<tr>
			<td id="createButton" align=right  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>���� </td>
			<td width=5>&nbsp;</td>
			<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
		</tr>
		</table>
	</td>
</tr>
</table> 
<input type="hidden" id="deptid" name="geGrouptype.grouptypedeptid" value="${geOperator.deptid}">
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
function doCreate(){
	if(tt.validate()){		
		$("#frmInput").submit();
	}	
}
function doClear(){
	document.getElementById("frmInput").reset();
}
$(document).ready(function(){
	$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
	var ids = ['des'];
	    	var contents = ['˵��:���û�����еķ���<br/>'
	    	                + 'ʹ����Ⱥ:�û������͹�����Ա<br/>'
	    	                + '��������:<br/>'
	    	                + 'ע������:������������������������<br/>'];
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
<script type="text/javascript">

	//������ʾ����
	var groupTypeName =new tt.Field("","","grouptypeName").setMsgId("grouptypeName_msg");
	var groupTypeDesc =new tt.Field("","","grouptypeDesc").setMsgId("grouptypeDesc_msg");
	new tt.LV().set(0, 100).add(groupTypeDesc); 
	//�ǿ���֤
	tt.vf.req.add(groupTypeName);
</script>
</html>
