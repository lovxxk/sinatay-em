$(document).ready(function(){
	$('.member_menu .item').eq(2).addClass('select');
	$('.member_menu .item').eq(2).siblings().removeClass('select');
	
	var name = ['�������룺','��Ʒ���ƣ�','���ս�','�ύ���ڣ�','����״̬��'],
		value = ['20130820112','������Ʊ�������Ʊ�','5000000.00','2013/08/20','δ֧��'];
	
	$('.order_item').click(function(){
		$('.order_item').removeClass('select');
		$(this).addClass('select');
		addClose();
	});
	
//	$('.add_order .action_icon').click(function(){
//		window.location.href = ${ctx}/productsDisplay/onlineShop.do?parentAttrID=&topNum=3&attrID=ROOT;
//		$('.order_item').removeClass('select');
//		
//		var policy = $('<div/>').addClass('order_item').addClass('select');
//		var size = $('.order_item').length;
//		for(var i=0 ; i< name.length ; i++){
//			var policy_row = $('<div/>').addClass('order_row'),
//				policy_name = $('<div/>').addClass('name').text(name[i]),
//				policy_value = $('<div/>').addClass('value').text(value[i]+size);
//			
//			if(i == name.length - 1){
//				policy_row.addClass('last');
//			}
//			
//			policy_row.append(policy_name).append(policy_value);
//			
//			policy.append(policy_row);
//		}
//		
//		var policy_bottom = $('<div/>').addClass('order_bottom').addClass('operation'),
//			operation = $('<a class="fix_order" href="#">���ƶ���</a><div class="pay_action">֧&nbsp;&nbsp;&nbsp;��</div><a href="#">ɾ������</a>');
//			
//		policy_bottom.append(operation);
//		policy.append(policy_bottom);
//		
//		var close = $('<div/>').addClass('close');
//		
//		policy.append(close);
//		
//		$('.order_list').prepend(policy);
//		
//		$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
//	});
});

function addClose(){
	$('.order_list .select .close').click(function(){
		//������ķ������Լ�д��
	});
}