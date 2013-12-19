//pageNum��1��ʼ
function showPage(pageNum){
	$('.notices_item').hide();
	$('.notices_item:lt(' + (pageNum * 6) + ')').show();
	$('.notices_item:lt(' + ((pageNum - 1) * 6) + ')').hide();
	
	//����Ӧ����
	$('.noticeInfoList').css('height',$('.noticeInfoList').height());
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


//У���Ƿ��ܹ�����
function check(noticeNo){
	$.ajax({
		type : "post",
		url : contextRootPath+"/email/check.do",
		data : "noticeNo=" + noticeNo,
		dataType : 'json',
		success : function(data, textStatus) {
			if(data.flag=='0'){
				Sinosoft.alert({
					contentStr : '����ʧ�ܣ�',
					subContentStr : 'ϵͳ�������Ժ�����......',
					okStr : 'ȷ��'
				});
			}else{
				//��������ļ���
				window.location.href=contextRootPath+'/email/downloadNotice.do?url='+data.url;
			
			}
			
		},
		error : function(backData) {
			a_alert("�����쳣��");
		}
	});
	
	
}
//���������֪ͨ�� �������
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
					contentStr : '����ʧ�ܣ�',
					subContentStr : data.desc,
					okStr : 'ȷ��'
				});
			}else{
				//��������ļ���
				window.location.href=contextRootPath+'/email/downloadNotice.do?url='+data.url;
			
			}
			
		},
		error : function(backData) {
			a_alert("�����쳣��");
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
	//ǰ̨���棬��ʼ��ҳ�漰������
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
	
	//ҳ�����¼�
	$('.page.page_num a').click(function() {
		clickPage($(this));
	});
	
	//ǰһҳ����¼�
	$('.page.prev_page').click(function(){
		clickPage($('.page.page_num.now').prev().find('a'));
	});
	
	//��һҳ����¼�
	$('.page.next_page').click(function(){
		clickPage($('.page.page_num.now').next().find('a'));
	});
});