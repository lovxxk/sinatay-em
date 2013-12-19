<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="selected_job" id="selected_job">选择职业</div>
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
$('.select_panel .select_close').click(function(){
	$('.select_panel').hide();
});

$('.select_job .job_item').click(function(){
	$('.select_job .selected_job').text($(this).text());
	$('.select_job').parent().find("input[type='hidden']").attr('value',$(this).attr('val'));
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
	var tag = $(this).parent().parent().parent().parent().parent().attr('tag');
// 	console.log("tag: "+tag);
	var _search = $("#"+tag+" #keyword").val();
	var _result = $("#"+tag+" #search_result");
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
	$('.select_job').parent().find("input[type='hidden']").attr('value',obj.attributes['val'].nodeValue);
	$('.select_panel').hide();
}

function reset(obj){
// 	console.log("111"+obj.parentNode.attributes['tag'].nodeValue);
// 	$("#"+obj.parentNode.attributes['tag'].nodeValue+" #keyword").val('');
// 	$("#"+obj.parentNode.attributes['tag'].nodeValue+" .search_job .search_label").css("display","inline");
// 	$("#"+obj.parentNode.attributes['tag'].nodeValue+" #search_result").html('');
}

</script>