//pageNum从1开始
function showPage(pageNum){
	$('.notices_item').hide();
	$('.notices_item:lt(' + (pageNum * 6) + ')').show();
	$('.notices_item:lt(' + ((pageNum - 1) * 6) + ')').hide();
	
	//重适应长度
	$('.noticeInfoList').css('height',$('.noticeInfoList').height());
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


//校验是否能够下载
function check(noticeNo){
	$.ajax({
		type : "post",
		url : contextRootPath+"/email/check.do",
		data : "noticeNo=" + noticeNo,
		dataType : 'json',
		success : function(data, textStatus) {
			if(data.flag=='0'){
				Sinosoft.alert({
					contentStr : '下载失败！',
					subContentStr : '系统错误！请稍候再试......',
					okStr : '确定'
				});
			}else{
				//获得下载文件流
				window.location.href=contextRootPath+'/email/downloadNotice.do?url='+data.url;
			
			}
			
		},
		error : function(backData) {
			a_alert("网络异常！");
		}
	});
	
	
}
//万能险年度通知书 特殊服务
function checkW(policyNo){
	var startDate=$("#startDate").text();
	var endDate=$("#endDate").text();
	$.ajax({
		type : "post",
		url : contextRootPath+"/email/check.do",
		data : "policyNo=" + policyNo +"&startDate=" + startDate + "&endDate=" + endDate,
		dataType : 'json',
		success : function(data, textStatus) {
			if(data.flag=='0'){
				Sinosoft.alert({
					contentStr : '下载失败！',
					subContentStr : data.desc,
					okStr : '确定'
				});
			}else{
				//获得下载文件流
				window.location.href=contextRootPath+'/email/downloadNotice.do?url='+data.url;
			
			}
			
		},
		error : function(backData) {
			a_alert("网络异常！");
		}
	});
	
	
}

$(document).ready(function() {
			
	$('.content').click(function() {
		if ($(this).hasClass('tip')) {
			return;
		}
		$(this).addClass('select');
		$(this).parent().siblings().children().removeClass('select');
	});	
	//前台缓存，初始化页面及各函数
	var edorsItem = $('.notices_item');
	totalNum = parseInt((edorsItem.length - 1) / 6) + 1;
	
	if (edorsItem.length > 6) {
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
		$('.page.page_num:first').hide();
	}
	$('.page.page_num:gt(4)').hide();
	
	//页码点击事件
	$('.page.page_num a').click(function() {
		clickPage($(this));
	});
	
	//前一页点击事件
	$('.page.prev_page').click(function(){
		clickPage($('.page.page_num.now').prev().find('a'));
	});
	
	//后一页点击事件
	$('.page.next_page').click(function(){
		clickPage($('.page.page_num.now').next().find('a'));
	});
});