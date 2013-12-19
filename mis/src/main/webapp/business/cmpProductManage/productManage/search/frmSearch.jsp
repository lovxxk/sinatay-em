<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>电子商务后台管理系统-非车险产品管理-查询产品</title>
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>	
	<style type="text/css">
		input,select {
			width:170px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			var coreProductCode = "${param.coreProductCode}";
			if (coreProductCode != "") {
				$("#coreProductCode").val(coreProductCode);
				$('form:first').submit();
			};
		});
		
		$(function(){
			$.ajax({
				   async: false,
				   cache :false,
				   type: "POST",
				   url: '${ctx}/productManage/findDataFromDic.do',
				   data: {
						"codeType":"BusinessArea",
						"menuCode":"ROLE_B_CRPR"
					},//传入参数
				   dataType:"json",
				   success: function(data){
					   $("#businessArea").html();
					   var appendHTML = "<option value=''>--请选择--</option>";
				    	for(var i = 0; i < data.mapList.length; i++){
				    		var baObj = data.mapList[i];
				    		 appendHTML += "<option value='" + baObj.codeCode + "'>" + baObj.codeName + "</option>";
				    	}
				    	$("#businessArea").append(appendHTML);
					},
				   error:function(XMLHttpRequest, textStatus, errorThrown){
					   
				   }
			});
		});
		

	</script>
</head>
<body topmargin="0" leftmargin="0">
		<!-- 判断权限 -->
		<acc:showView source="ROLE_B_CRPR_H">
			<c:set var="canDo_audit" value="yes"></c:set>
		</acc:showView>
		<acc:showView source="ROLE_B_CRPR_F">
			<c:set var="canDo_publish" value="yes"></c:set>
		</acc:showView>
		
	<div class="public_fun_title">产品维护</div> 
	<div class="table_content">
		<form id="frmInput" name="frmInput" action="${ctx}/productManage/searchAllProduct.do" method="post" target="fraSearchList">

			<table class="table_style" align="center" width="500px">
				<tr>
					<td class="td_head" width="80" nowrap>
						产品代码：   
					</td>
					<td class="td_body">
						<input type="text" id="coreProductCode" name="geProductMain.coreProductCode" maxlength="25">
					</td>
					<td class="td_head" width="80" nowrap>
						产品名称：
					</td>
					<td class="td_body">
						<input type="text" id="productName" name="geProductMain.productName" maxlength="25">
					</td>
				</tr>
				<tr>
					<td class="td_head" width="80" nowrap>
						业务领域：
					</td>
					<td class="td_body">
						<select id="businessArea"  name="geProductMain.businessArea">
						</select>
					</td>
					<td class="td_head" width="80" nowrap>
						状态：   
					</td>
					<td class="td_body">
						<select id="productStatus" name="geProductMain.productStatus">
							<acc:showView source="ROLE_ALL">
								<option value="all">--请选择--</option>
							</acc:showView>
							<acc:showView source="ROLE_CREATE">
								<option value="01">已创建</option>
							</acc:showView>
							<acc:showView source="ROLE_FLOW">
								<option value="02">已定制流程</option>
							</acc:showView>
							<acc:showView source="ROLE_DE">
								<option value="03">已详细定义</option>
							</acc:showView>
							<acc:showView source="ROLE_PU">
								<option value="04">已审核</option>
							</acc:showView>
							<acc:showView source="ROLE_AUDIT">
								<option value="05">已发布</option>
							</acc:showView>
						</select>
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
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" align="right" onclick="javascript:$('form')[0].reset();" nowrap>清空</td>
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
	if($("#productStatus").val()=='03' || $("#productStatus").val()=='04'){
		document.frmInput.action = "${ctx}/productManage/searchFromWorkFlow.do?type=workflow";
	}else{
		document.frmInput.action = "${ctx}/productManage/searchAllProduct.do";
	}
	document.frmInput.submit();
}

//从首页点进来的，如果有审核权限，加载已详细定义数据，如果有发布权限，加载已审核数据
$(document).ready(function(){

	var fm = "${param.fm}";
	if("welcome" == fm){
		if("${canDo_publish}"=="yes" && "${canDo_audit}"==""){
			$("#productStatus").val("04");
			document.frmInput.action = "${ctx}/productManage/searchFromWorkFlow.do?type=workflow";
			document.getElementById("frmInput").submit();
		}else if("${canDo_publish}"=="yes" && "${canDo_audit}"=="yes"){
			$("#productStatus").val("03");
			document.frmInput.action = "${ctx}/productManage/searchFromWorkFlow.do?type=workflow";
			document.getElementById("frmInput").submit();
		}else if("${canDo_publish}"=="" && "${canDo_audit}"=="yes"){
			$("#productStatus").val("03");
			document.frmInput.action = "${ctx}/productManage/searchFromWorkFlow.do?type=workflow";
			document.getElementById("frmInput").submit();
		}
	}
		});
		
$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
var ids = ['des'];
    	var contents = ['说明:查询、修改或删除非车险产品<br/>'
    	                + '使用人群:产品配置人员<br/>'
    	                + '配置条件:需要先配置险种责任<br/>'
    	                + '注意事项:更新时严格按流程配置<br/>'];
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
</script>
</html>
