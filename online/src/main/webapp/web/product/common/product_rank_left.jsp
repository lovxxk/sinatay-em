<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="product_rank dynamic_frame">
	<p class="dynamic_title">��Ʒ��������</p>
	<div class="rank_list">
		<ul class="dynamic_list" id="life_topProduct">
<!-- 			<li class="last"> -->
<!-- 				<div class="rank"> -->
<!-- 					<div class="rank_img home"><a href="#"></a></div> -->
<!-- 					<p class="rank_title"><a href="#">��Ʒ���Ʋ�Ʒ���Ʋ� Ʒ���Ʋ�Ʒ����</a></p> -->
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
					//����
					$.each($("#life_topProduct a"), function(i,n){
						$(n).click(function(){
							_ga.push(['_trackEvent', '��Ʒ����', '��Ʒ����������: '+$(this).text()]).send();
						});
					});
				});
			}else{
// 				_result.html("δ�ҵ���Ӧְҵ��Ϣ��");
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			$(".dynamic_frame #life_topProduct").html("�����쳣�����Ժ����ԣ�");
		}
	});
});
</script>