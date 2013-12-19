<%@ page language="java" contentType="text/html;charset=GBK" isELIgnored="false"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js" charset="utf-8"></script>
 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>新建定点医院</title>
<style type="text/css">
span.talentErrMsg{
	padding-left:17px;
}
</style>
</head>
<body>
<div class="public_fun_title">新建定点医院<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span></div>

	<form action="${ctx}/hospital/addHospital.do" id="hospitalForm" method="post" target="myFrame">
	<table class="table_style" align="center"  id="hospitalTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>

    <tr>
      <td class="td_head" width="120px" nowrap>省：</td>
      <td class="td_body" width="380px">
      	<select id="province"></select>
        <input id="provinceText" name="hospital.province" type="text" value="" style="display:none;width:240px;"/>
      </td>
   </tr>
	<tr>
		<td class="td_head" width="120px" nowrap>市：</td>
		<td class="td_body" width="380px" >
			<select id="city"></select>
            <input id="cityText" name="hospital.city" type="text" value="" style="display:none;width:240px;"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>医院名称：</td>
		<td class="td_body" width="380px" >
           <input id="hospitalName" name="hospital.hosName" type="text" value="" style="width:240px;" maxlength=100/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>医院地址：</td>
		<td class="td_body" width="380px" >
           <input id="hospitalAddress" name="hospital.hosAddr" type="text" value="" style="width:240px;" maxlength=200 />
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=4>
			<table width=64 align="center">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="hospitalAddSubmit()"  nowrap>
                                                              创建
                </td>
                <td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号\
	new tt.LV().set(0,32).add("hospital.province");
	new tt.LV().set(0,32).add("hospital.city");
	new tt.LV().set(0,100).add("hospital.hosName");
	new tt.LV().set(0,200).add("hospital.hosAddr");
//	tt.vf.num.add("riskCode","riskType");"^\\d+$"
	tt.vf.req.add("hospital.province");
	tt.vf.req.add("hospital.city");
	tt.vf.req.add("hospital.hosName");
	tt.vf.req.add("hospital.hosAddr");
	//testCode.add("geRisk.riskType");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "只能输入A-Z,a-z,0-9,且长度为1-30").add("geDirectory.eid");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "只能输入A-Z,a-z,0-9,且长度为1-30").add("geDirectory.productQuoteCode");

	var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['说明：新建定点医院。<br/>使用人群：<br/>配置条件：<br/>注意事项：'];
    	for ( var i = 0 ; i < 10 ; i++ ){
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
					width:300,
					textAlign: 'left',
					background: '#e0f2ff', 
					tip:true,//控制左侧尖角是否出现
					padding :10
				}
			});
    	}
	initPlaceSelector();
})
function hospitalAddSubmit(){
	var provinceText = $('#province').find('option:selected').text();
	var cityText = $('#city').find('option:selected').text();
	$('#provinceText').val(provinceText);
	$('#cityText').val(cityText);
	if(tt.validate()){
		document.getElementById("hospitalForm").submit();
	}
}
//清空输入
function doClear(){
	document.getElementById("hospitalForm").reset();
}

function initPlaceSelector(){
	$.ajax({
		type: "POST",
		url: "${ctx}/hospital/loadArea.do",
		dataType: 'json',
		data:{"province":''},
		success: function(data){
			var selectProvince = $('#province');
			
			if(data != ''){
				$.each(data, function(index,item){
					selectProvince.append('<option value="' + item.PLACECODE + '">' + item.PLACENAME + '</option>');
				});
				 $.ajax({
						type: "POST",
						url: "${ctx}/hospital/loadArea.do",
						dataType: 'json',
						data:{"province":$("#province").val()},
						success: function(data){
							var selectCity = $('#city');
							 selectCity.find('option').remove();
							if(data != ''){
								$.each(data, function(index,item){
									selectCity.append('<option value="' + item.PLACECODE + '">' + item.PLACENAME + '</option>');
								});
							}else{
							}
						},
						error:function(data){
						}
					});
			}else{
			}
		},
		error:function(data){
		}
	});
	
 	$("#province").change(function(){
		 $.ajax({
			type: "POST",
			url: "${ctx}/hospital/loadArea.do",
			dataType: 'json',
			data:{"province":$("#province").val()},
			success: function(data){
				var selectCity = $('#city');
				 selectCity.find('option').remove();
				if(data != ''){
					$.each(data, function(index,item){
						selectCity.append('<option value="' + item.PLACECODE + '">' + item.PLACENAME + '</option>');
					});
				}else{
				}
			},
			error:function(data){
			}
		});
	});
}
</script>

	
</html>