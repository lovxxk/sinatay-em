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
	<title>电子商务管理系统-产品图片维护</title>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">产品图片维护</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/business/cmpProductManage/productPictureManage/queryGeProductPictureByDisPage.do" target="fraSearchList">
			<table align="center" width="372px">
				<tr>
					<td class="td_head" width="52px" nowrap>
						名称：
					</td>
					<td class="td_body">
						<input type="text" id="picturename" name="geProductPictureDetail.picturename" style="width:170px;" maxlength="25">
					</td>
					<td class="td_head" width="80px"nowrap>
						是否启用：
					</td>
					<td class="td_body">
						<select id="appleflag" name="geProductPictureDetail.flag" style="width:80px;">
							<option  value="">--全部--</option>
							<option value="1" selected="selected">是</option>
							<option value="0">否</option>
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
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur" align="right"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>清空</td>
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
		    	var contents = ['说明:查询、修改或删除创建的产品图片<br/>'
		    	                + '使用人群:产品配置人员<br/>'
		    	                + '配置条件:<br/>'
		    	                + '注意事项:更新时上传图片时注意图片的格式与大小<br/>'];
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
</html>
