<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet"
	href="${ctx}/global/css/misBasic.css">
<script language="javascript" type="text/javascript"
	src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet"
	href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript"
	src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>电子商务管理系统-网点信息维护</title>

<style type="text/css">
#operatorTable td {
	vertical-align: middle;
	text-align: center;
	width: 82px;
}

input,select {
	width: 170px;
}
</style>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">
		服务网点维护<span id="des" style="cursor: pointer; font-size: 15px;"><img
			style="vertical-align: middle; color: #E9E7E8"
			src="${ctx}/global/images/help.png" /></span>
	</div>
	<form id="frmInput" name="frmInput" method="post"
		action="${ctx}/serviceNetwork/findServiceNetwork2.do"
		target="fraSearchList">
		<table class="table_style" style="width: 528px;">
			<tr>
				<td class="td_head" width="120px" nowrap>省：</td>
				<td class="td_body" width="380px"><select id="province"></select>
					<input id="provinceText" name="network.province" type="text"
					value="" style="display: none; width: 240px;" /></td>

				<td class="td_head" width="120px" nowrap>市：</td>
				<td class="td_body" width="380px"><select id="city"></select> <input
					id="cityText" name="network.city" type="text" value=""
					style="display: none; width: 240px;" /></td>
					
				<td class="td_head " width="100px" nowrap>网点名称：</td>
				<td class="td_body"><input id="manageName" type="text"
					name="network.manageName" maxlength="50"></td>
			</tr>
			<tr height="60px;">
				<td colspan="4" align="center">
					<table id="operatorTable">
						<tr>
							<input type="hidden" name="pageNo" id="pageNo" value="1">
							<input type="hidden" name="pageSize" id="pageSize" value="20">
							<td class="btnBigOnBlur"
								onmouseover="this.className='btnBigOnFocus'"
								onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();"
								nowrap>查询</td>
							<td class="btnBigOnBlur"
								onmouseover="this.className='btnBigOnFocus'"
								onmouseout="this.className='btnBigOnBlur'"
								onclick="javascript:doClear();" nowrap>清空</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		
		initPlaceSelector();
	});

	function doSearch() {
		var provinceText = $('#province').find('option:selected').text();
		var cityText = $('#city').find('option:selected').text();
		if(cityText=='---请选择---'){
			cityText='';
		}
		if(provinceText=='---请选择---'){
			provinceText='';
		}
		$("#manageName").attr("value", $.trim($("#manageName").val()));
		$('#provinceText').val(provinceText);
		$('#cityText').val(cityText);
		
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}

	function doClear() {
		document.getElementById("frmInput").reset();
	}

	$(function() {
		doSearch();

		var ids = [ 'des' ];
		// <img src="'+contextRootPath+'/global/images/form_success.gif" />
		var contents = [ '说明：服务网点维护。<br/>使用人群：<br/>配置条件：<br/>注意事项：' ];
		for ( var i = 0; i < 10; i++) {
			$('#' + ids[i]).qtip({
				content : contents[i],
				position : {
					corner : {
						tooltip : 'topMiddle',
						target : 'bottomMiddle'
					},
					adjust : {
						screen : true
					}
				},
				style : {
					border : {
						width : 1,
						radius : 1,
						color : '#00739f'
					},
					width : 350,
					textAlign : 'left',
					background : '#e0f2ff',
					tip : true,//控制左侧尖角是否出现
					padding : 10
				}
			});
		}
	});
	function initPlaceSelector() {
		$
				.ajax({
					type : "POST",
					url : "${ctx}/serviceNetwork/loadArea.do",
					dataType : 'json',
					data : {
						"province" : ''
					},
					success : function(data) {
						var selectProvince = $('#province');
						selectProvince.append('<option selected="selected">---请选择---</option>');
						if (data != '') {
							$
									.each(
											data,
											function(index, item) {
												
												selectProvince
														.append('<option value="' + item.PLACECODE + '">'
																+ item.PLACENAME
																+ '</option>');
											});
							$
									.ajax({
										type : "POST",
										url : "${ctx}/serviceNetwork/loadArea.do",
										dataType : 'json',
										data : {
											"province" : $("#province").val()
										},
										success : function(data) {
											var selectCity = $('#city');
											selectCity.find('option').remove();
											selectCity.append('<option selected="selected">---请选择---</option>');
											if (data != '') {
												$
														.each(
																data,
																function(index,
																		item) {
																	selectCity
																			.append('<option value="' + item.PLACECODE + '">'
																					+ item.PLACENAME
																					+ '</option>');
																});
											} else {
											}
										},
										error : function(data) {
										}
									});
						} else {
						}
					},
					error : function(data) {
					}
				});

		$("#province")
				.change(
						function() {
							$
									.ajax({
										type : "POST",
										url : "${ctx}/serviceNetwork/loadArea.do",
										dataType : 'json',
										data : {
											"province" : $("#province").val()
										},
										success : function(data) {
											var selectCity = $('#city');
											selectCity.find('option').remove();
											selectCity.append('<option selected="selected">---请选择---</option>');
											if (data != '') {
												$
														.each(
																data,
																function(index,
																		item) {
																	selectCity
																			.append('<option value="' + item.PLACECODE + '">'
																					+ item.PLACENAME
																					+ '</option>');
																});
											} else {
											}
										},
										error : function(data) {
										}
									});
						});
	}
</script>
</html>
