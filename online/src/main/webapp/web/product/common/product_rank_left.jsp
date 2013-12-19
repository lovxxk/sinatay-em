<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="product_rank dynamic_frame">
	<p class="dynamic_title">产品销售排行</p>
	<div class="rank_list">
		<ul class="dynamic_list" id="life_topProduct">
<!-- 			<li class="last"> -->
<!-- 				<div class="rank"> -->
<!-- 					<div class="rank_img home"><a href="#"></a></div> -->
<!-- 					<p class="rank_title"><a href="#">产品名称产品名称产 品名称产品的名</a></p> -->
<!-- 				</div> -->
<!-- 			</li> -->
		</ul>
	</div>
</div>
<script>
$(document).ready(function(){
	$.ajax({
		type: "POST",
		url: "${ctx}/productsDisplay/lift_HotProduct.do",
		dataType: 'json',
		success: function(data){
			if(data != ''){
				$.each(data, function(index,item){
// 					console.log(item.EID+", "+item.COREPRODUCTCODE+", "+item.PRODUCTNAME+", "+item.NUB);
					var url = contextRootPath+"/sale/toQuote.do?eid="+item.EID;
					var html = "<li><div class=\"rank\"><div class=\"rank_img\"><a href=\""+url+"\"><img src=\""+contextRootPath+"/upload/images/"+item.COREPRODUCTCODE+"/"+item.COREPRODUCTCODE+"_detail_main.jpg\"></a></div><p class=\"rank_title\"><a href=\""+url+"\">"+item.PRODUCTNAME+"</a></p></div></li>";
					$(".dynamic_frame #life_topProduct").append(html);
					//插码
					$.each($("#life_topProduct a"), function(i,n){
						$(n).click(function(){
							_ga.push(['_trackEvent', '产品详情', '产品销售排行栏: '+$(this).text()]).send();
						});
					});
				});
			}else{
// 				_result.html("未找到对应职业信息！");
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			$(".dynamic_frame #life_topProduct").html("操作异常，请稍候再试！");
		}
	});
});
</script>