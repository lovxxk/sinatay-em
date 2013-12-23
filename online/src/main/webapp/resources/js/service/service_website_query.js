var totalNum;
$(document).ready(function(){
	
	$('.nav_menu .nav_item').eq(2).addClass('select');
	$('.nav_menu .nav_item').eq(2).siblings().removeClass('select');
	
	//�����Ч
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
	
	//ǰһҳ����¼�
	$('.page.prev_page').click(function(){
		clickPage($('.page.page_num.now').prev().find('a'));
	});
	
	//��һҳ����¼�
	$('.page.next_page').click(function(){
		clickPage($('.page.page_num.now').next().find('a'));
	});
});

function initProvince(){
	var provinceOpts = [];
	$.ajax({
		type : "POST",
		async : false,
		url : $('#ctx').val()+"/webJson/initProvince.do",
		dataType : 'json',
		success : function(data) {
			provinceOpts.push({'m':'','value':''});
			for(var i = 0; i < data.listProvince.length; i++){
				var opt={};
				opt['name'] = data.listProvince[i];
				opt['value'] = data.listProvince[i];
				provinceOpts.push(opt);
			}
			$('#query_province').data('jSelect').update(provinceOpts);
		},
		error: function(data) {
			a_alert('�������');
		}
	});	
}

function initCity(){
	var cityOpts = [];
	var province = $('#query_province').val();
	$.ajax({
		type : "POST",
		async : false,
		url : $('#ctx').val()+"/webJson/initCity.do",
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
			a_alert('�������');
		}
	});	
}

function queryFunction(){
	var province = $('#query_province').val();
	var city = $('#query_city').val();
	var webName = $('#website_name').val();
	$.ajax({
		type : "POST",
		async : false,
		url : $('#ctx').val()+"/webJson/queryWebsite.do",
		dataType : 'json',
		data : {'province':province,'city':city,'webName':webName},
		success : function(data) {
			$('.website_table tr').remove();
			for(var i = 0; i < data.networkInfos.length; i++){
				//�ж��Ƿ�Ϊ��
				if(data.networkInfos[i].manageName==null){
					data.networkInfos[i].manageName="";
				}
				if(data.networkInfos[i].address==null){
					data.networkInfos[i].address="";
				}
//				if(data.networkInfos[i].phone==null){
//					data.networkInfos[i].phone="";
//				}
				var tr = '<tr><td class="no">'+(i+1)+'</td><td class="webName">'+data.networkInfos[i].manageName + '</td><td class="webAddr">'+data.networkInfos[i].address+'</td></tr>';
				$('.website_table').append(tr);
			}
			totalNum = parseInt((data.networkInfos.length - 1) / 10) + 1;
			showPage(1);
			initPage();
		},
		error: function(data) {
			a_alert('�������');
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
	if(totalNum == 1 ){
		$('.page.page_num:first').hide();
	}
	if(totalNum > 1){
		$('.page.next_page').show();
	}
	$('.page.page_num:gt(4)').hide();
	//��ҳ�����¼�
	$('.page.page_num a').click(function() {
		clickPage($(this));
	});
}
//pageNum��1��ʼ
function showPage(pageNum){
	$('.website_table tr').hide();
	$('.website_table tr:lt(' + (pageNum * 10) + ')').show();
	$('.website_table tr:lt(' + ((pageNum - 1) * 10) + ')').hide();
}

//���ҳ���¼�
function clickPage(now){
	var nowNum = parseInt(now.html());
	//����ǰ����ת����ʾ
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
	
	//����ʾ��ҳ��
	if(totalNum > 5){
		if(nowNum < 3){
			$('.page.page_num:gt(4)').hide();
			$('.page.page_num:lt(4)').show();
		}else if(nowNum > (totalNum - 2)){
			$('.page.page_num:gt(' + (totalNum - 5) + ')').show();
		}else{
			$('.page.page_num').show();
			$('.page.page_num:lt(' + (nowNum - 3) + ')').hide();
			$('.page.page_num:gt(' + (nowNum + 1) + ')').hide();
		}
	}
	
	$('.page.page_num').removeClass('now');
	now.parent().addClass('now');
	showPage(nowNum);
}