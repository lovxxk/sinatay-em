<%@ page language="java" contentType="text/html;charset=GBK"
	isELIgnored="false"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet"
	href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet"
	href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript"
	src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript"
	src="${ctx}/global/js/jquery-1.6.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet"
	href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript"
	src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>新建服务网点</title>
<style type="text/css">
span.talentErrMsg {
	padding-left: 17px;
}
</style>
</head>
<body>
	<div class="public_fun_title">
		新建服务网点<span id="des" style="cursor: pointer; font-size: 15px;"><img
			style="vertical-align: middle; color: #E9E7E8"
			src="${ctx}/global/images/help.png" /></span>
	</div>

	<form action="${ctx}/serviceNetwork/addServiceNetwork.do"
		id="networkForm" method="post" target="myFrame">
		<table class="table_style" align="center" id="networkTable">
			<tr>
				<td height=10>&nbsp;</td>
			</tr>

			<tr>
				<td class="td_head" width="120px" nowrap>省：</td>
				<td class="td_body" width="380px"><select id="province"></select>
					<input id="provinceText" name="network.province" type="text"
					value="" style="display: none; width: 240px;" /></td>

			</tr>
			<tr>
				<td class="td_head" width="120px" nowrap>市：</td>
				<td class="td_body" width="380px"><select id="city"></select> <input
					id="cityText" name="network.city" type="text" value=""
					style="display: none; width: 240px;" /></td>
			</tr>
			<tr>
				<td class="td_head" width="120px" nowrap>管理机构编码：</td>
				<td class="td_body" width="380px"><input id="networkManagecom"
					name="network.manageCom" type="text" value="" style="width: 240px;"
					maxlength=100 /></td>
			</tr>
			<tr>
				<td class="td_head" width="120px" nowrap>管理机构名称：</td>
				<td class="td_body" width="380px"><input id="networkManageName"
					name="network.manageName" type="text" value=""
					style="width: 240px;" maxlength=100 /></td>
			</tr>
			<tr>
				<td class="td_head" width="120px" nowrap>优先级：</td>
				<td class="td_body" width="380px"><input id="networkSort"
					name="network.sort" type="text" value="" style="width: 240px;"
					maxlength=100 /></td>
			</tr>

			<tr>
				<td class="td_head" width="120px" nowrap>地址：</td>
				<td class="td_body" width="380px"><input id="networkAddress"
					name="network.address" type="text" value="" style="width: 240px;"
					maxlength=200 /></td>
			</tr>
			<tr>
				<td class="td_head" width="120px" nowrap>电话：</td>
				<td class="td_body" width="380px"><input id="networkPhone"
					name="network.phone" type="text" value="" style="width: 240px;"
					maxlength=200 /></td>
			</tr>

			<tr>
				<td class="td_head" width="120px" nowrap>服务类型：</td>
				<td class="td_body" width="380px"><select name="network.type"
					id="networkType" class="input_text">
						<option value="1" style="width: 120px;" maxlength=380>1-客服服务中心</option>
				</select></td>
			</tr>
			<tr>
				<td class="td_head" width="120px" nowrap>服务星级：</td>
				<td class="td_body" width="380px"><select name="network.grade"
					style="width: 115px;" maxlength=380 id="networkGrade"
					class="input_text">
						<option value="1">一星</option>
						<option value="2">二星</option>
						<option value="3">三星</option>
						<option value="4">四星</option>
						<option value="5">五星</option>
				</select></td>
			</tr>
			<tr>
				<td class="td_head" width="120px" nowrap>工作时间：</td>
				<td class="td_body" width="380px"><input id="networkWorktime"
					name="network.workTime" type="text" value="" style="width: 240px;"
					maxlength=200 /></td>
			</tr>
			<tr>
				<td class="td_head" width="120px" nowrap>纬度：</td>
				<td class="td_body" width="380px"><input id="networkLongItude"
					name="network.longItude" type="text" value="" style="width: 240px;"
					maxlength=200 /></td>
			</tr>
			<tr>
				<td class="td_head" width="120px" nowrap>经度：</td>
				<td class="td_body" width="380px"><input id="networkLatItude"
					name="network.latItude" type="text" value="" style="width: 240px;"
					maxlength=200 /></td>
			</tr>

			<tr height=25>
				<td></td>
			</tr>
			<tr>
				<td colspan=4>
					<table width=64 align="center">
						<tr>
							<td class="btnBigOnBlur"
								onmouseover="this.className='btnBigOnFocus'"
								onmouseout="this.className='btnBigOnBlur'"
								onclick="networkAddSubmit()" nowrap>创建</td>
							<td class="btnBigOnBlur"
								onmouseover="this.className='btnBigOnFocus'"
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();"
								nowrap>重置</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号\
		new tt.LV().set(0, 32).add("network.province");
		new tt.LV().set(0, 32).add("network.city");
		new tt.LV().set(0, 100).add("network.manageName");
		new tt.LV().set(0, 100).add("network.manageCom");
		new tt.LV().set(0, 100).add("network.sort");
		//	tt.vf.num.add("riskCode","riskType");"^\\d+$"
		tt.vf.req.add("network.province");
		tt.vf.req.add("network.city");
		tt.vf.req.add("network.manageName");
		tt.vf.req.add("network.manageCom");
		tt.vf.req.add("network.sort");
		var sortVal = new tt.RV().set(new RegExp("^\\d+$"), "对不起，只能输入非负整数");
		sortVal.add("network.sort");
	
		var manageComVal = new tt.RV().set(new RegExp("^\\d+$"), "对不起，只能输入非负整数");
		manageComVal.add("network.manageCom");
		var ids = [ 'des' ];
		// <img src="'+contextRootPath+'/global/images/form_success.gif" />
		var contents = [ '说明：新建服务网点。<br/>使用人群：<br/>配置条件：<br/>注意事项：' ];
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
					width : 300,
					textAlign : 'left',
					background : '#e0f2ff',
					tip : true,//控制左侧尖角是否出现
					padding : 10
				}
			});
		}
		initPlaceSelector();
	})
	function networkAddSubmit() {
		var provinceText = $('#province').find('option:selected').text();
		var cityText = $('#city').find('option:selected').text();
		$('#provinceText').val(provinceText);
		$('#cityText').val(cityText);
		if (tt.validate()) {
			document.getElementById("networkForm").submit();
		}
	}
	//清空输入
	function doClear() {
		document.getElementById("networkForm").reset();
	}

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