<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>电子商务管理系统-产品目录查询</title>
	<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<style type="text/css">
		#operatorTable td {
				vertical-align:middle;
				text-align:center;
				width:82px;
		}
	</style>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">产品目录维护</div>
	<div class="table_content" style="padding-top:1px;padding-right:50px">
		<form id="frmInput" name="frmInput" action="${ctx}/productDirectory/findProductDirectory.do" method="post" target="fraSearchList">
			<table  align="center" width="494px">
			<tr>
			<td class="td_head" width="125" nowrap>电子商务产品ID：</td>
					<td class="td_body"> <input type="text" id="eid" name="geDirectory.eid" style="width:170px;" maxlength="25">
					</td>
				<td class="td_head" width="94px" nowrap>产品上下架：</td>
					<td class="td_body"><select id="isProductShelf" name="geDirectory.isProductShelf" onchange="" style="width: 80px;">
						<option value="" selected>--请选择--</option>
						<option value="01">上架</option>
						<option value="02">下架</option>
					</select></td>	
				</tr>
				<tr>
					<td class="td_head td_head_center" width="125" nowrap>产品名称：</td>
					<td class="td_body"> <input type="text" id="productName" name="geDirectory.productName" style="width:170px;" maxlength="25">
					</td>
				<td class="td_head" width="94px" nowrap>业务领域：</td>
					<td class="td_body">
						<select id="businessArea" name="geDirectory.businessArea" onchange="" style="width: 80px;">
							<option value="" selected>--请选择--</option>
						</select>
					</td>
					

				</tr>
				<tr>
					
					<td class="td_head td_head_center" width="125" nowrap>
						产品代码/险种代码：
					</td>
					<td class="td_body" colspan="3">
						<input type="text" id="coreProductCode" name="geDirectory.coreProductCode" style="width:170px;" maxlength="25">
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="4" align="center">
						<table id="operatorTable">
							<tr>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="10">
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:$('form')[0].reset();" nowrap>清空</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		window.onload = function (){
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		}
	
		function doSearch() {
			window.parent.fraToolbar.document.getElementById("idStr").value = "";
			window.parent.fraToolbar.document.getElementById("count").value = "";
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		}
		$(function(){
			$.ajax({
				   cache :false,
				   type: "POST",
				   url: '${ctx}/productDirectory/findBusinessArea.do',
				   data: { perStr : ""},
				   dataType:"json",
				   success: function(data){
					   $("#businessArea").empty();
					   $("#businessArea").append("<option value=''>--请选择--</option>");
				    	for(var i = 0; i < data.length; i++){
				    		var baObj = data[i];
				    		$("#businessArea").append("<option value='" + baObj.code + "'>" + baObj.name + "</option>");
				    	}
					},
				   error:function(XMLHttpRequest, textStatus, errorThrown){
					   
				   }
			});
		});
		$(document).ready(function(){
			$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
			var ids = ['des'];
			    	var contents = ['说明:查询、修改或删除产品目录<br/>'
			    	                + '使用人群:产品配置人员<br/>'
			    	                + '配置条件:需要先创建产品<br/>'
			    	                + '注意事项:网销的产品目录需要通过查询网销产品进行绑定<br/>'];
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
	</script>
</body>
</html>
