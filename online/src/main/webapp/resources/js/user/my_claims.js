var totalNum;
$(document).ready(function(){
	$('.member_menu .item').eq(3).addClass('select');
	$('.member_menu .item').eq(3).siblings().removeClass('select');
	
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
	
	$('.claims_item .claims_status').each(function(){
		var index = $(this).find('.status').index($(this).find('.status.current'));
		if(index == 2){
			$(this).next().show();
		}
	});
	
	//ǰ̨���棬��ʼ��ҳ�漰������
	var listClaim = $('.claims_item');
	totalNum = parseInt((listClaim.length - 1) / 5) + 1;
	if (listClaim.length > 5) {
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
	
	//�鿴�����¼����
	$('.claims_btn.click_btn').click(function(){
		var claim = $(this).parent();
		$('#claimNumber').val(claim.find('.claimNumber span').html());
		$('#insuredName').val(claim.find('.insuredName span').html());
		$('#accDate').val(claim.find('.accDate span').html());
		
		//alert($('#fm').attr('action'));
		$('#fm').submit();
		//alert(a);
	});
	
});

//pageNum��1��ʼ
function showPage(pageNum){
	$('.claims_item').hide();
	$('.claims_item:lt(' + (pageNum * 5) + ')').show();
	$('.claims_item:lt(' + ((pageNum - 1) * 5) + ')').hide();
	
	//����Ӧ����
	$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
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