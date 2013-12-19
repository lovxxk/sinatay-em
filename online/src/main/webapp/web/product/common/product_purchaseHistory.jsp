<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script src="${ctx }/global/js/myPaginationV5.0/js/jquery.myPagination5.0.js" type="text/javascript"></script>
<script src="${ctx }/global/js/myPaginationV5.0/js/msgbox/msgbox.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	 $("#demo1").myPagination({
		 currPage:1, 
		 pageCount:"pageCount", 
		 ajax:{on:true, 
			 url:"${ctx}/saleAjax/saleRecords.do",
			 pageCountId:"pageCount",
			 param:{productCode:"${productCode}"},
			 onClick:function(page){
				 //刷新订单列表
				 $(".saleAjax").remove();
			 },
			 callback:function(data){
				 if(_isUndefined(data.saleRecords))
					 return;
				 for(var i=0;i<data.saleRecords.length;i++){
					 var item = $("#historyRow").clone(true);
					 //作标记,以便刷新清除列表
					 $(item).addClass("saleAjax");
					 ///产品名称
					 $(item).find(".productName").text(data.saleRecords[i].productName);
					 //价格
					 $(item).find(".orderAmount").text(data.saleRecords[i].orderAmount);
					 //份数
					 $(item).find(".productNumber").text(data.saleRecords[i].productNumber);
					 //购买时间
					 var payTime =""; 
					 if(data.saleRecords[i].payTime && data.saleRecords[i].payTime != '' && data.saleRecords[i].payTime != 'null')
						 payTime = data.saleRecords[i].payTime.substr(0,10);
					 if(!_isUndefined(data.saleRecords[i].payTime))
					 	$(item).find(".payTime").text(payTime);
					 else
						$(item).find(".payTime").text(payTime);
					 $(item).removeAttr("id").show();
					 $("#historyRow").before(item);
				 }

			 }}
	 });
});
</script>
<link rel="stylesheet" type="text/css" href="${ctx }/global/js/myPaginationV5.0/js/msgbox/msgbox.css" />
<div class="detail_main">
	<div class="purchaseHistory">
		<div class="text_row label">
			<div class="text_col no">买家</div>
			<div class="text_col name">产品名称</div>
			<div class="text_col price">价格</div>
			<div class="text_col quota">份数</div>
			<div class="text_col time">购买时间</div>
		</div>
		<idv id="purchaseHistoryList"></idv>
			<div id="historyRow" class="text_row" style="display: none;">
				<div class="text_col no">*****<span>(匿名)</span></div>
				<div class="text_col name productName"></div>
				<div class="text_col price">￥<span class="orderAmount"></span></div>
				<div class="text_col quota productNumber"></div>
				<div class="text_col time payTime"></div>
			</div>
			<div id="demo1">
			<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
	</div>
</div>
<div id="purchaseHistory_bottom"></div>	