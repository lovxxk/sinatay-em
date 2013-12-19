<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>电子商务后台管理系统-非车险产品管理-新建产品</title>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>	
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
<script type="text/javascript">
function doClear(){
	$(':input','#frmInput')   
	 .not(':button, :submit, :reset, :hidden')   
	 .val('')   
	 //.removeAttr('checked')
	 .removeAttr('selected');
}

$(document).ready(function(){
	//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
	tt.vf.req.add("businessAreaSelect","geProductMain.coreProductCode", "geProductMain.productName", "geProductMain.coreProductSimpleName");
	new tt.RV().set(new RegExp("^[A-Za-z0-9]{1,10}$"), "只能输入A-Z,a-z,0-9,且长度为1-10").add("geProductMain.coreProductCode");
	
	$("#submit").click(function(){
		if(tt.validate()==false){
			return;
		}else{
			$("#frmInput").submit();
		}
	});
	
	$("#coreProductCode").blur(function(){
		var coreProductCode = $("#coreProductCode").val();
		$.ajax({
			url : "${ctx}/productManage/isHaveGeProduct.do",
			data : {
				"coreProductCode" : coreProductCode
			},//传入参数
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#coreProductCode").val("");
					$("#coreProductCodeContent").remove();
					$("#coreProductCode").parent().append("<span id='coreProductCodeContent'><span class='talentErrMsg'>产品代码已经存在!</span></span>")
				} else {
					$("#coreProductCodeContent").remove();
				}
			}
		});
	});
	$("#coreProductCode").focus(function(){
		$("#coreProductCodeContent").remove();
	});
	$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
	var ids = ['des'];
	    	var contents = ['说明:进行非车险产品的创建与配置<br/>'
	    	                + '使用人群:产品配置人员<br/>'
	    	                + '配置条件:需要先配置险种责任<br/>'
	    	                + '注意事项:严格按流程配置<br/>'];
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
function businessAreaSelectChange(obj) {
	$("#businessArea").val($("#businessAreaSelect").val());
}
function productFmSubmit(){
	if(tt.validate()){
	   document.getElementById("frmInput").submit();
	}
}
function productFmReset(){
	document.getElementById("frmInput").reset();
}
$(function (){
	var addResult = "${addResult}";
	if (addResult == "success") {
		alert("产品创建成功！");
		parent.document.location.href = "${ctx}/productManage/toSearchProduct.do?coreProductCode=${coreProductCode}";
	} else if (addResult == "failure") {
		alert("产品添加失败！");
	}	
});
</script>
</head>
<body>
<div class="public_fun_title">新建产品</div>
<div class="stpess_navigation">
	<ul>
    	<li class="stpli_a">
        	<span class="stpli_spanleft">1</span>
            <span class="stpli_spanright1">新建产品</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">2</span>
            <span class="stpli_spanright1">定制流程</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">3</span>
            <span class="stpli_spanright1">详细定义</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">4</span>
            <span class="stpli_spanright2">审核</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">5</span>
            <span class="stpli_spanright2">发布</span>
        </li>
    </ul>
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/productManage/createProduct.do" target="myFrame">
<input type="hidden" name="geProductMain.createDate" value="<%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10) %>">
<input type="hidden" name="geProductMain.productStatus" value="01">
<table align="center" style="width:550px;" id="productTable" >
	
	<tr>
		<td class="td_head" width="200px" nowrap>产品代码：</td>
		<td class="td_body" width="350px">
			<input type="text" id="coreProductCode" name="geProductMain.coreProductCode" value="" maxlength="50" />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>产品全称：</td>
		<td class="td_body">
			<input type="text" id="geProductMain.productName" name="geProductMain.productName" maxlength="50" value="" />
		</td>
	</tr>
	
	<tr>
		<td class="td_head" width="200px" nowrap>产品简称：</td>
		<td class="td_body">
			<input type="text" id="geProductMain.coreProductSimpleName" name="geProductMain.coreProductSimpleName" value="" maxlength="30" />
		</td>
	</tr>
	
	<tr>
		<td class="td_head" width="200px" nowrap>业务领域：</td>
		<td class="td_body">
			<input type="hidden" id="businessArea" name="geProductMain.businessArea" maxlength="30" value="" style="width: 170px;"/>
			<select id="businessAreaSelect" name="businessAreaSelect" onchange="businessAreaSelectChange(this)" style="width: 170px;">
				<option value="" selected>--请选择--</option>
					<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
						<!--过滤集团-->
						<c:if test="${GeCode_businessArea.id.codeCode!='1'}">
							<option value="${GeCode_businessArea.id.codeCode}">${GeCode_businessArea.codeCName}</option>
						</c:if>
					</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table align="center">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="productFmSubmit();" nowrap>创建</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="productFmReset();" nowrap>重置</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
</html>

