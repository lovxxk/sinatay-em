$(document).ready(function(){
	$('.member_menu .item').eq(2).addClass('select');
	$('.member_menu .item').eq(2).siblings().removeClass('select');
	
	var name = ['订单号码：','产品名称：','保险金额：','提交日期：','订单状态：'],
		value = ['20130820112','懒人理财宝懒人理财宝','5000000.00','2013/08/20','未支付'];
	
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
//			operation = $('<a class="fix_order" href="#">完善订单</a><div class="pay_action">支&nbsp;&nbsp;&nbsp;付</div><a href="#">删除订单</a>');
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
		//这里面的方法你自己写了
	});
}