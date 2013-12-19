<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%@include file="/web/user/member/common/dataDemo.jsp" %>
<script type="text/javascript">
	 $(function(){
		 //分页
		 console.log("${orderSerialNo}, ${status}");
		 $("#demo1").myPagination({
			 currPage:1, 
			 pageCount:"pageCount", 
			 ajax:{on:true, 
				 url:"${ctx}/orderAjax/ordersAjax.do?orderSerialNo=${orderSerialNo}&status=${status}",
				 pageCountId:"pageCount",
				 onClick:function(page){
					 //刷新订单列表
					 $(".orderAjax").remove();
					 //页面上移
					 window.location.href="#title"; 
				 },
				 callback:function(data){
					 if(_isUndefined(data.orders))
						 return;
					 for(var i=0;i<data.orders.length;i++){
						 var item = $("#item").clone(true);
						 //第一个订单 设置被选中
						 if(i==0)
							 $(item).addClass("select");
						 //作标记,以便刷新清除列表
						 $(item).addClass("orderAjax");
						 $(item).find(".applicationNumber").val(data.orders[i].insurancePolicy.applicationNumber);
						 $(item).find(".serialNo").val(data.orders[i].serialNo);
						 //订单号
						 $(item).find(".orderSerialNumber").attr("title",data.orders[i].orderSerialNumber).text(data.orders[i].orderSerialNumber);
						 //主险名称
						 if(!_isUndefined(data.orders[i].productName))
						 	$(item).find(".productName").attr("title",data.orders[i].productName).text(data.orders[i].productName.substring(0,10));
						 //保费
						 $(item).find(".orderAmount").text("￥"+data.orders[i].orderAmount);
						 //生效日期
						 if(!_isUndefined(data.orders[i].insurancePolicy.inceptionDate)){
							 var lastIndex = data.orders[i].insurancePolicy.inceptionDate.indexOf('T');
							 var inceptionDate = data.orders[i].insurancePolicy.inceptionDate.substring(0,lastIndex);
							 $(item).find(".inceptionDate").text(inceptionDate);
						 }
						 //订单状态
						 var orderStatus="其他";
						 switch(data.orders[i].orderStatus){
						 	case 10:orderStatus="已录入核保信息";break;
						 	case 82:orderStatus="未支付";break;
						 	case 81:orderStatus="支付成功";break;
						 	case 80:orderStatus="支付失败";break;
						 }
						 $(item).find(".orderStatus").text(orderStatus);
						 //查看详情
						 if(data.orders[i].orderStatus==81)
						 	$(item).find(".operation_a").attr("href","${ctx }/order/orderDetail.do?id="+data.orders[i].serialNo);
						 else
							 $(item).find(".operation_a").remove();
						 //完善订单
						 if(data.orders[i].orderStatus==10||data.orders[i].orderStatus==82)
							 	$(item).find(".operation_b").attr("href",
							 			"javascript:perfectOrder('"+data.orders[i].insurancePolicy.quoteNo+"','"+data.orders[i].insurancePolicy.productCode+"','"+data.orders[i].insurancePolicy.serialNo+"','"+data.orders[i].insurancePolicy.applicationNumber+"','"+data.orders[i].insurancePolicy.policyStatus+"');");
						 else
							 $(item).find(".operation_b").remove();
						 //支付
						 if(data.orders[i].orderStatus==82||data.orders[i].orderStatus==80){
							 $(item).find(".pay_action").show();
// 						 	$(item).find(".pay_action_a").attr("href","${ctx }/payment/toPayment.do?id="+data.orders[i].serialNo);
							 $(item).find(".pay_action_a").attr("onclick","toPayConfirmInfo('"+data.orders[i].insurancePolicy.productCode+"','"+data.orders[i].insurancePolicy.serialNo+"')");
						 }else{
							 $(item).find(".pay_action").hide();
							$(item).find(".pay_action_a").remove();
						 }
						 $(item).removeAttr("id").show();
						 $("#addOrder").before(item);
					 }
				 }}
		 });
	 });

	//删除订单
	function deleteOrder(close) {
		var $that = $(close);
		console.log($that);
		console.log($that.siblings(".serialNo"));
		//删除的时候弹出确认框 
		Sinosoft.alert({
			contentStr : '点击确定将删除该订单记录，并且不能恢复，是否确认删除？',
			okStr : '确认',
			cancelStr : '取消',
			width : 510,
			okFunc : function() {
				$.ajax({
					type : "POST",
					async : false,
					url : "${ctx}/order/deleteOrder.do",
					dataType : 'text',
					data : {serialNo : $that.siblings(".serialNo").val()},
					success : function(flag) {
						var message = "";
						if ("1" == flag){
							message = "订单删除成功";
							$that.parent(".order_item").remove();
						}
						if ("-1" == flag){
							message = "操作无效";
						}
						if ("0" == flag){
							message = "订单删除失败，只能删除'未支付'与'未核保'的订单信息";
						}
						$(".title font").text(message);
						setTimeout(function() {$(".title font").text("");}, 5000);
					}
				});
			},
			cancelFunc : function() {}
		});
	};
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li class="at"><a href="${ctx}/memberCenter/homePage.do">会员首页</a></li>
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>我的订单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message }</font></p>
					<a name="title"></a>
				</div>
				<div id="orderList" class="order_list">
						<div id="item" class="order_item" style="display: none;">
							<input type="hidden" value="" class="applicationNumber">
							<input type="hidden" value="" class="serialNo">
							<div class="order_row">
								<div class="name">订单号码：</div>
								<div class="value orderSerialNumber"></div>
							</div>
							<div class="order_row">
								<div class="name">主险名称：</div>
								<div class="value productName"></div>
							</div>
							<div class="order_row">
								<div class="name">保费：</div>
								<div class="value orderAmount"></div>
							</div>
							<div class="order_row">
								<div class="name">生效日期：</div>
								<div class="value inceptionDate"></div>
							</div>
							<div class="order_row last">
								<div class="name">订单状态：</div>
								<div class="value orderStatus"></div>
							</div>
							<div class="order_bottom operation">
								<a class="fix_order operation_a" target="_blank">查看详情</a>
								<a class="fix_order operation_b">完善订单</a>
								<div class="pay_action"><a class="pay_action_a">支&nbsp;&nbsp;&nbsp;付</a></div>
							</div>
							<div class="close" onclick="deleteOrder(this);"></div>
						</div>
					<div id="addOrder" class="order_item add_order">
						<div class="order_action">
							<div class="action_icon" onclick="javascript:window.location.href='${ctx}/productsDisplay/onlineShop.do?parentAttrID=&topNum=3&attrID=ROOT'"></div>
							<p>点击添加订单</p>
						</div>
						<div class="order_bottom">
							
						</div>
					</div>
				</div>
				<div id="demo1"></div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>