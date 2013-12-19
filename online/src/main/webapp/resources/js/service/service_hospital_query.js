var totalNum;
$(document).ready(function(){
	
	$('.nav_menu .nav_item').eq(2).addClass('select');
	$('.nav_menu .nav_item').eq(2).siblings().removeClass('select');
	
	//点击特效
	$('.click_btn').mousedown(function(){
		$(this).css({
			'top':'1px',
			'left':'1px'
		});
	}).mouseup(function(){
		$(this).css({
			'top':'0',
			'left':'0'
		});
	});
	
	
	$('.query_form .query').click(function(){
		queryFunction();
	});
	$('#query_province').jSelect({
		options:[],
		onSelect:function(){
			initCity();
			$("#query_city").data('jSelect').setValue('');
		}
	});
	$('#query_city').jSelect({
		options:[],
		onSelect:function(){
		}
	});
	initProvince();

	$('.page.prev_page').hide();
	$('.page.next_page').hide();
	
	//前一页点击事件
	$('.page.prev_page').click(function(){
		clickPage($('.page.page_num.now').prev().find('a'));
	});
	
	//后一页点击事件
	$('.page.next_page').click(function(){
		clickPage($('.page.page_num.now').next().find('a'));
	});
});

function initProvince(){
	var provinceOpts = [];
	$.ajax({
		type : "POST",
		async : false,
		url : $('#ctx').val()+"/hosJson/initProvince.do",
		dataType : 'json',
		data : '{}',
		success : function(data) {
			provinceOpts.push({'name':'','value':''});
			for(var i = 0; i < data.listProvince.length; i++){
				var opt={};
				opt['name'] = data.listProvince[i];
				opt['value'] = data.listProvince[i];
				provinceOpts.push(opt);
			}
			$('#query_province').data('jSelect').update(provinceOpts);
		},
		error: function(data) {
			a_alert('网络错误');
		}
	});	
}

function initCity(){
	var cityOpts = [];
	var province = $('#query_province').val();
	$.ajax({
		type : "POST",
		async : false,
		url : $('#ctx').val()+"/hosJson/initCity.do",
		dataType : 'json',
		data : {'province':province},
		success : function(data) {
			cityOpts.push({'name':'','value':''});
			for(var i = 0; i < data.listCity.length; i++){
				var opt={};
				opt['name'] = data.listCity[i];
				opt['value'] = data.listCity[i];
				cityOpts.push(opt);
			}
			$('#query_city').data('jSelect').update(cityOpts);
		},
		error: function(data) {
			a_alert('网络错误');
		}
	});	
}

function queryFunction(){
	var province = $('#query_province').val();
	var city = $('#query_city').val();
	var hosName = $('#hospital_name').val();
	$.ajax({
		type : "POST",
		async : false,
		url : $('#ctx').val()+"/hosJson/queryHospital.do",
		dataType : 'json',
		data : {'province':province,'city':city,'hosName':hosName},
		success : function(data) {
			$('.hospital_table tr').remove();
			for(var i = 0; i < data.listHospitalManage.length; i++){
				var tr = '<tr><td class="no">'+(i+1)+'</td><td class="hosName">'+data.listHospitalManage[i].hosName+'</td><td class="hosAddr">'+data.listHospitalManage[i].hosAddr+'</td></tr>';
				
				$('.hospital_table').append(tr);
			}
			totalNum = parseInt((data.listHospitalManage.length - 1) / 10) + 1;
			showPage(1);
			initPage();
		},
		error: function(data) {
			a_alert('网络错误');
		}
	});	
}

function initPage(){
	$('.page.prev_page').hide();
	$('.page.next_page').hide();
	$('.page.page_num').remove();
	for ( var i = 0; i < totalNum; i++) {
		$('<div class="page page_num"><a>' + (i + 1) + '</a></div>')
				.insertBefore($('.page.next_page'))
	}
	$('.page.page_num:first').addClass('now');
	if(totalNum > 1){
		$('.page.next_page').show();
	}
	$('.page.page_num:gt(4)').hide();
	//绑定页码点击事件
	$('.page.page_num a').click(function() {
		clickPage($(this));
	});
}
//pageNum从1开始
function showPage(pageNum){
	$('.hospital_table tr').hide();
	$('.hospital_table tr:lt(' + (pageNum * 10) + ')').show();
	$('.hospital_table tr:lt(' + ((pageNum - 1) * 10) + ')').hide();
}

//点击页码事件
function clickPage(now){
	var nowNum = parseInt(now.html());
	//控制前后跳转的显示
	if(nowNum == 1){
		$('.page.prev_page').hide();
	}else{
		$('.page.prev_page').show();
	}
	if(nowNum == totalNum){
		$('.page.next_page').hide();
	}else{
		$('.page.next_page').show();
	}
	
	//可显示的页码
	if(totalNum > 5){
		if(nowNum < 3){
			$('.page.page_num:gt(4)').hide();
			$('.page.page_num:lt(4)').show();
		}else if(nowNum > (totalNum - 2)){
			//alert('??');
			$('.page.page_num:lt(' + (totalNum - 5) + ')').hide();
			$('.page.page_num:gt(' + (totalNum - 5) + ')').show();
		}else{
			//alert(nowNum);
			$('.page.page_num').show();
			$('.page.page_num:lt(' + (nowNum - 3) + ')').hide();
			$('.page.page_num:gt(' + (nowNum + 1) + ')').hide();
			//$('.page.page_num:gt(' + (nowNum - 4) + ')').show();
			//$('.page.page_num:lt(' +  + '):gt(' + (nowNum + 2) + ')').show();
		}
	}
	
	$('.page.page_num').removeClass('now');
	now.parent().addClass('now');
	showPage(nowNum);
}