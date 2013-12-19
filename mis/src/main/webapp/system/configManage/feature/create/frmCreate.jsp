<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<!-- ��ʾ��ʼ -->
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>frmCreate.jsp</title>
</head>
<body onload="pageValidate();">
<div class="public_fun_title">�½����ܿ���<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
<form action="${ctx}/business/businessManage/feature/create.do" id="frmInput" method="post" target="myFrame">
<table align="center" width="720px">
<tr>
	<td class="td_head" width="200px" nowrap>���ܿ��ر��룺</td>
	<td class="td_body" width="500px" nowrap>
		<input id="functiontId" name="geFunctionSwitch.functiontId" type="text" style="width:170px;" maxlength=32 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>���ܿ���������</td>
	<td class="td_body" nowrap>
		<input id="functionInfo" name="geFunctionSwitch.functionInfo" type="text" style="width:170px;" maxlength=50>
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>���ܿ���״̬��</td>
	<td class="td_body" nowrap>
		<select id="status" name="geFunctionSwitch.status">
			<option value="">--��ѡ��--</option>
			<option value="0">δ��ͨ</option>
			<option value="1">��ͨ</option>
		</select>
	</td>
</tr>
<tr height="60px">
	<td colspan=2>
		<table width="300px" align="center">
			<tr>
				<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>���� </td>
				<td width=5>&nbsp;</td>
				<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
				<td width=300>&nbsp;</td>
			</tr>
		</table>
	</td>
</tr>
</table> 
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//pop��ʾ��Ϣ
	var ids = ['functiontId'];
	var contents = ['Ψһ��ʶ'];
		
    	for ( var i = 0 ; i < 1 ; i++ ){
			$('#' + ids[i]).qtip({ 
				content:contents[i], 
				position: { 
					corner: { 
					tooltip: 'leftMiddle', 
					target: 'rightMiddle'
					} 
				}, 
				 style: { 
				border: { 
					width: 2,
					radius: 2,
					color: '#00739f'
					},
					width:100,
					padding: 10, 
					textAlign: 'left',
					background: '#e0f2ff', 
					tip:true//����������Ƿ����
					//name: 'green' 
				} 
			}); 
    	}
    	
    	var ids1 = ['des'];
    	var contents1 = ['˵����Ϊϵͳ������ӿ��ؿ���<br/>ʹ����Ⱥ�����ܿ���ά����Ա<br/>��������������Ҫ����Ӧ�Ĺ�������Ӧ<br/>ע���������״̬�仯��Ӱ����Ŀ�Ĺ�������'];
    		
        	for ( var i = 0 ; i < 1 ; i++ ){
    			$('#' + ids1[i]).qtip({ 
    				content:contents1[i], 
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
								tip:true,//����������Ƿ����
								padding :10
							}
						});
        	}
//pop��ʾ��Ϣ����
});

function doCreate(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}
function doClear(){
	document.getElementById("frmInput").reset();
}

function pageValidate(){
	tt.vf.req.addName("geFunctionSwitch.functiontId", "geFunctionSwitch.functionInfo", "geFunctionSwitch.status");
	new tt.RV().set(new RegExp("^\\w{1,32}$"), "ֻ��������ĸ�����ֻ��»���,�ҳ���Ϊ1-32").add("geFunctionSwitch.functiontId");
}
</script>
</html>
