<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="selected_job" id="newJobType">${policy.appnt.jobType}</div>
<!-- 隐藏点击值 -->

<div id="infoType" style="display:none;">0</div>
<div class="selected_job_hidden" style="display:none;" id="jobTypeHidden">${policy.appnt.jobType}</div>

<div class="select_panel">
	<div class="left_icon"></div>
	<div class="right_area">
		<div class="fixed_info">
			<div class="job_item" val="3010202" title="公务员">公务员</div>
			<div class="job_item" val="8000001" title="自由职业人员">自由职业人员</div>
			<div class="job_item" val="1050104" title="企业负责人">企业负责人</div>
			<div class="job_item" val="4010201" title="销售人员">销售人员</div>
			<div class="job_item" val="4071203" title="家庭主妇">家庭主妇</div>
			<div class="job_item" val="1050102" title="企事业单位管理人员">企事业单位管理人员</div>
			<div class="job_item" val="4019903" title="个体经营户">个体经营户</div>
			<div class="job_item" val="8000002" title="退休人员">退休人员</div>
			<div class="job_item" val="3010101" title="企事业单位工作人员">企事业单位工作人员</div>
			<div class="job_item" val="5010101" title="农民">农民</div>
			<div class="job_item" val="2099907" title="学生">学生</div>
		</div>
		<div class="search_info">
			<div class="search_job">
				<div class="search_box">
					<label class="search_label" for="search_job">请输入查询信息</label>
					<input class="search" id="keyword" name="keyword" type="text"/>
					<div class="search_icon"></div>
				</div>
				<div class="search_btn click_btn" id="search_btn">查询</div>
			</div>
			<div id="search_result"></div>
		</div>
	</div>
	<div class="select_close"></div>
</div>

<script>
$('.selected_job').click(function() {
	$('.select_panel').show();
});

$('.select_panel .select_close').click(function(){
	$('.select_panel').hide();
});

$('.select_job .job_item').click(function(){
	$('.select_job .selected_job').text($(this).text());
	$("#appntJobCode").attr('value',$(this).attr('val'));
	$('.select_panel').hide();
});

$('.search_job .search_box').click(function(){
	$('.search_job .search').focus();
	$('.search_job .search_label').css("display","none");
});
$('.search_job .search').blur(function(){
	var tag = $(this).parent().parent().parent().parent().parent().parent().attr('tag');
	var _search = $("#"+tag+" #keyword").val();
// 	console.log(tag+" blur: "+_search);
	if(_search == '' || _search == undefined){
		$('.search_job .search_label').css("display","inline");
	}
});

$('.search_info #search_btn').click(function(){
	//var tag = $(this).parent().parent().parent().parent().parent().attr('tag');
// 	console.log("tag: "+tag);
	var _search = $("#keyword").val();
	var _result = $("#search_result");
	console.log(_search);
	if(_search != '' && _search != undefined){
		$.ajax({
			type: "POST",
			url: "${ctx}/sale/obtainOccupation.do",
			dataType: 'json',
			data:{"keyword":_search},
			success: function(data){
// 				console.log(data.length+", "+data);
				if(data != ''){
					_result.html('');
					$.each(data, function(index,item){
// 						console.log($.trim(item.OCCUPATIONCODE)+", "+$.trim(item.OCCUPATIONNAME));
						if($.trim(item.OCCUPATIONNAME).length >= 10){
							_result.append($("<div class=\"job_item\" val=\""+$.trim(item.OCCUPATIONCODE)+"\" title=\""+$.trim(item.OCCUPATIONNAME)+"\" onclick=\"onSelect(this)\">"+$.trim(item.OCCUPATIONNAME).substring(0,10)+"...</div>"));
						}else{
							_result.append($("<div class=\"job_item\" val=\""+$.trim(item.OCCUPATIONCODE)+"\" title=\""+$.trim(item.OCCUPATIONNAME)+"\" onclick=\"onSelect(this)\">"+$.trim(item.OCCUPATIONNAME)+"</div>"));
						}
					});
				}else{
					_result.html("未找到对应职业信息！");
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				_result.html("操作异常，请稍候再试！");
			}
		});
	}else{
		_result.html("请输入查询信息！");
	}
});

function onSelect(obj){
	$('.select_job .selected_job').text(obj.title);
	appntJobCode
	$('#appntJobCode').val(obj.attributes['val'].nodeValue);
	$('.select_panel').hide();
}

function reset(obj){
// 	console.log("111"+obj.parentNode.attributes['tag'].nodeValue);
// 	$("#"+obj.parentNode.attributes['tag'].nodeValue+" #keyword").val('');
// 	$("#"+obj.parentNode.attributes['tag'].nodeValue+" .search_job .search_label").css("display","inline");
// 	$("#"+obj.parentNode.attributes['tag'].nodeValue+" #search_result").html('');
}

</script>
<style>
.select_job{
	float:left;
	position: relative;
}

.select_job .select_panel{
	position:absolute;
	top:6px;
	left:120px;
	display: none;
}

.select_job .select_close{
	width:30px;
	height:30px;
	background: url(../resources/image/policy/job_close.png) no-repeat;
	cursor: pointer;
	position: absolute;
	top:0;
	right:0;
}


.select_job .left_icon{
	width:7px;
	height:7px;
	float: left;
	margin-top: 11px;
	background: url(../resources/image/policy/select_icon.png) no-repeat;
}

.select_job .right_area{
	background: #fff;
	border:1px solid #c9c9c9;
	margin-left: 6px;
}

.select_job .right_area .fixed_info{
	margin-left:14px;
	margin-right:30px;
	
	padding:17px 10px 10px;
	width:455px;
	overflow: hidden;
	border-bottom:1px solid #c9c9c9;
}

.select_job .right_area .job_item{
	width:151px;
	float:left;
	line-height:30px;
	text-decoration: underline;
	text-align: left;
	cursor: pointer;
	color:#3f67bf;
}
.select_job .right_area .search_info{
	overflow: hidden;
	padding:14px 10px 25px;
	margin-left:14px;
	margin-right:30px;
	width:455px;
}

.select_job .right_area .search_info .search_job{
	height:48px;
	position: relative;
	overflow: hidden;
}

.select_job .right_area .search_info .search_box{
	width: 345px;
	height: 30px;
	float: left;
	position: relative;
	zoom: 1;
	background: #c3c3c3;
	border-radius: 3px 0 0 3px;
	margin-top:8px;
	margin-left:29px;
}

.select_job .right_area .search_info label{
	position: absolute;
	left: 28px;
	top: 7px;
	z-index: 1;
	color: #BBB;
}

.select_job .right_area .search_info input{
	font-size: 12px;
	position: absolute;
	left: 1px;
	top: 1px;
	width: 311px;
	height: 24px;
	line-height: 18px;
	padding: 2px;
	padding-left:30px;
	background: #FFF;
	vertical-align: middle;
	border: 0;
	border-radius: 2px 0 0 2px;
	color:#000;
}
.select_job .right_area .search_info .search_icon{
	width:15px;
	height:16px;
	position: absolute;
	background: url(../resources/image/search_icon.png) no-repeat center;
	top: 8px;
	left: 10px;
}

.select_job .right_area .search_info .search_btn{
	float: left;
	width: 65px;
	height: 20px;
	cursor: pointer;
	border: 0;
	font-size: 12px;
	margin-top:12px;
	color: #FFF;
	text-align: center;
	font-family:"Microsoft Yahei";
	background: url(../resources/image/policy/job_search_btn.png) no-repeat;
	position: relative;
}
#search_result{
	max-height:300px;
	overflow-Y:auto;
}
</style>