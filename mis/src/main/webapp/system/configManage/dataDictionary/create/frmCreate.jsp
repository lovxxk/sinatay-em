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
<title>�½������ֵ�</title>
</head>
<body onload="pageValidate();">
<div class="public_fun_title" >�½������ֵ�<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
<form action="${ctx}/business/businessManage/dataDictionary/create.do" id="frmInput" method="post" target="myFrame">
<table class="table_style" align="center" width="720px" id="productTable">
<tr>
	<td class="td_head"  width="200px" nowrap>�������ͣ�</td>
	<td class="td_body" width="500px">
		<input id="codeType" name="geCode.id.codeType" type="text" style="width:170px;" maxlength=150  value="" readonly>
		<input type="button" style="width:80px" onclick="getGeCodeType();" value="��ȡ���͡�"/>
	</td>
</tr>
<tr>
	<td class="td_head"  nowrap>���룺</td>
	<td class="td_body"  >
		<input id="codeCode" name="geCode.id.codeCode" type="text" style="width:170px;" maxlength=150 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>��ʾ��ţ�</td>
	<td class="td_body" >
		<input id="orderNo" name="geCode.orderNo" type="text" style="width:170px;" maxlength=6 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>������������</td>
	<td class="td_body" >
		<input id="codeCName" name="geCode.codeCName" type="text" style="width:170px;" maxlength=300 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>����Ӣ������</td>
	<td class="td_body" >
		<input id="codeEName" name="geCode.codeEName" type="text" style="width:170px;" maxlength=300 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>���뷱������</td>
	<td class="td_body" >
		<input id="codeTName" name="geCode.codeTName" type="text" style="width:170px;" maxlength=300 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>�Ƿ���Ч��</td>
	<td class="td_body" >
		<select id="validInd" name="geCode.validInd" style="width:100px;">
				<option value="1">��Ч</option>
				<option value="0">��Ч</option>
			</select>
	</td>
</tr>
<tr>
	<td colspan=2>
		<table width=300 align="center">
		<tr>
			<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'"   onclick="doCreate();" nowrap>���� </td>
			<td width=5>&nbsp;</td>
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'"  onclick="doClear();" nowrap>����</td>
			<td width=300>&nbsp;</td>
		</tr>
		</table>
	</td>
</tr>
</table> 
<input type="hidden" name="geCode.validInd" value="1"/>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
<input type="hidden" id="codeTypeBack" value="">
</body>
<script type="text/javascript">

$(document).ready(function(){
	//pop��ʾ��Ϣ
	var ids = ['codeCode'];
	var contents = ['�����ֵ���ֵ������ֵ���Ͳ���������ֵ�ظ�'];
		
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
    	var contents1 = ['˵��������ĳ�����ֵ������µ��ֵ�����<br/>ʹ����Ⱥ�������ֵ�ά����Ա<br/>�����������������������ֵ�������ֵ����ͱ��������е������ֵ�����<br/>ע�������ʾ�����ͬ���������ֵ��б�չʾʱ�������ֶ�'];
    		
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

function doCreate(){
	if(tt.validate()){
		document.getElementById("codeType").value = document.getElementById("codeTypeBack").value;
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function getGeCodeType(){
	window.open("${ctx}/system/configManage/dataDictionary/create/selectType/index.jsp","��ѯ��Ʒ" ,"top=100, left=100, width=900,height=600,toolbar=no");		
}

function pageValidate(){
	tt.vf.req.addName("geCode.id.codeType","geCode.id.codeCode", "geCode.codeCName");
	tt.vf.int.addName("geCode.orderNo");
}
</script>
</html>
