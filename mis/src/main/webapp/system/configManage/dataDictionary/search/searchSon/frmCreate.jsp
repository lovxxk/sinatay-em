<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<!-- 提示框开始 -->
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>新建数据字典</title>
<%
String codeType=request.getParameter("geCodeType.codeType");
String codeTypeDesc=request.getParameter("geCodeType.codeTypeCDesc");
%>
</head>
<body onload="pageValidate();">
<div class="public_fun_title" >新建数据字典<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
<form action="${ctx}/business/businessManage/dataDictionary/createInType.do" id="frmInput" method="post" target="myFrame">
<table class="table_style" align="center" width="600px" id="productTable">
<tr>
	<td class="td_head"  width="200px" nowrap>代码类型：</td>
	<td class="td_body" width="400px">
		<input id="codeType" name="geCode.id.codeType" type="text" style="width:170px;" maxlength=150  value="<%=codeTypeDesc %>" readonly>
	</td>
</tr>
<tr>
	<td class="td_head"  nowrap>代码：</td>
	<td class="td_body"  >
		<input id="codeCode" name="geCode.id.codeCode" type="text" style="width:170px;" maxlength=150 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>显示序号：</td>
	<td class="td_body" >
		<input id="orderNo" name="geCode.orderNo" type="text" style="width:170px;" maxlength=6 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>代码中文名：</td>
	<td class="td_body" >
		<input id="codeCName" name="geCode.codeCName" type="text" style="width:170px;" maxlength=300 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>代码英文名：</td>
	<td class="td_body" >
		<input id="codeEName" name="geCode.codeEName" type="text" style="width:170px;" maxlength=300 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>代码繁体名：</td>
	<td class="td_body" >
		<input id="codeTName" name="geCode.codeTName" type="text" style="width:170px;" maxlength=300 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>是否有效：</td>
	<td class="td_body" >
		<select id="validInd" name="geCode.validInd" style="width:100px;">
			<option value="1">有效</option>
			<option value="0">无效</option>
		</select>
	</td>
</tr>
<tr><td colspan=2 height="10"></td></tr>
<tr>
	<td colspan=2>
		<table width=300 align="center">
		<tr>
		<acc:showView source="ROLE_S_DDIC_I">
			<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'"   onclick="doCreate();" nowrap>创建 </td>
		</acc:showView>
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'"  onclick="doClear();" nowrap>重置</td>
			
			<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>关闭</td>
		</tr>
		</table>
	</td>
</tr>
</table> 
<!-- <input type="hidden" name="geCode.validInd" value="1"/> -->
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
<input type="hidden" id="codeTypeBack" value="<%=codeType%>">
</body>
<script type="text/javascript">
document.getElementById("codeType").value = GetQueryString("geCodeType.codeTypeCDesc");
$(document).ready(function(){
	//pop提示信息
	var ids = ['codeCode'];
	var contents = ['数据字典码值，该码值类型不与其它码值重复'];
		
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
					tip:true//控制左侧尖角是否出现
					//name: 'green' 
				} 
			}); 
    	}
    	
    	var ids1 = ['des'];
    	var contents1 = ['说明：增加某数据字典类型下的字典数据<br/>使用人群：数据字典维护人员<br/>配置条件：待创建的数据字典的数据字典类型必须是已有的数据字典类型<br/>注意事项：显示序号是同类型数据字典列表展示时的排序字段'];
    		
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
								tip:true,//控制左侧尖角是否出现
								padding :10
							}
						});
    			
        	}
//pop提示信息结束
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

function pageValidate(){
	tt.vf.req.addName("geCode.id.codeType","geCode.id.codeCode", "geCode.codeCName");
	tt.vf.int.addName("geCode.orderNo");
}
function doClose(){
	window.close();
}
</script>
</html>
