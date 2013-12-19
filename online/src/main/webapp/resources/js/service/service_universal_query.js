var totalNum;
var perNum = 15;
var selectorItem = ".input_row.item";
$(document).ready(function(){
	
	//点击特效
	$('.click_btn').on('mousedown',function(){
		$(this).css({
			'top':'1px',
			'left':'1px'
		});
	}).on('mouseup',function(){
		$(this).css({
			'top':'0',
			'left':'0'
		});
	});
	
	$('#interest_start,#interest_end').click(function(){
		WdatePicker({
			startDate:'%y-%M-%d',
			dateFmt:'yyyy-MM-dd'
		});
	});
	
	$('#product_select').jSelect({
		options:[],
		onSelect:function(){
		}
	});
	
	$('.click_btn.query_interest').click(function(){
		var riskName = $('#product_select').val();
		var startDate = $('#interest_start').val();
		var endDate = $('#interest_end').val();
		$.ajax({
			type : "POST",
			url:$('#ctx').val()+'/universalJson/queryUniversal.do',
			async:false,
			dataType : 'json',
			data: {'riskName':riskName,'startDate':startDate,'endDate':endDate},
			error:function(){
			},
			success:function(data){
				$('.input_row.item').remove();
				for(var i = 0;i<data.listUniversalRate.length;i++){
					$('.input_area').append('<div class="input_row item"><div class="input_col name"><p class="main_name">'+data.listUniversalRate[i].riskName+'</p><p class="main_info"></p></div><div class="input_col date">'+data.listUniversalRate[i].culDate.substr(0,10)+'</div><div class="input_col day_interest">'+data.listUniversalRate[i].dateRate+'%</div><div class="input_col year_interest">'+data.listUniversalRate[i].yearRate+'%</div></div>');
				}
				$('.interest_info .main_name').each(function(){
					if(getTextWidth($(this).text(),$(this)) > $(this).width()){
						$(this).addClass('multi');
					}
				});
				initPage();
			}
		});
	});
	initRiskNameSelector();
	
	$('.interest_info .main_name').each(function(){
		if(getTextWidth($(this).text(),$(this)) > $(this).width()){
			$(this).addClass('multi');
		}
	});
	
	initPage();
	//前一页点击事件
	$('.page.prev_page').click(function(){
		clickPage($('.page.page_num.now').prev().find('a'));
	});
	
	//后一页点击事件
	$('.page.next_page').click(function(){
		clickPage($('.page.page_num.now').next().find('a'));
	});
});

function initRiskNameSelector(){
	var riskNameOpts = [];
	$.ajax({
		type : "POST",
		async : false,
		url : $('#ctx').val()+"/universalJson/initRiskName.do",
		dataType : 'json',
		data : '{}',
		success : function(data) {
			riskNameOpts.push({'name':'','value':''});
			for(var i = 0; i < data.listRiskName.length; i++){
				var opt={};
				opt['name'] = data.listRiskName[i];
				opt['value'] = data.listRiskName[i];
				riskNameOpts.push(opt);
			}
			$('#product_select').data('jSelect').update(riskNameOpts);
		},
		error: function(data) {
			a_alert('网络错误');
		}
	});	
}

function initPage(){
	$('.page.page_num').remove();
	//前台缓存，初始化页面及各函数
	var list = $(selectorItem);
	totalNum = parseInt((list.length - 1) / perNum) + 1;
	if (list.length > perNum) {
		showPage(1);
	}
	for ( var i = 0; i < totalNum; i++) {
		$('<div class="page page_num"><a>' + (i + 1) + '</a></div>')
				.insertBefore($('.page.next_page'))
	}
	$('.page.page_num:first').addClass('now');
	$('.page.prev_page').hide();
	if(totalNum < 2){
		$('.page.next_page').hide();
	}else{
		$('.page.next_page').show();
	}
	$('.page.page_num:gt(4)').hide();
	
	//页码点击事件
	$('.page.page_num a').click(function() {
		clickPage($(this));
	});
}
//pageNum从1开始
function showPage(pageNum){
	$(selectorItem).hide();
	$(selectorItem+':lt(' + (pageNum * perNum) + ')').show();
	$(selectorItem+':lt(' + ((pageNum - 1) * perNum) + ')').hide();
	
	//重适应长度
	$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
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