function pageChange(pageNo, pageSize,totalPage,totalCount,pageId){
	$("#"+pageId).empty();
	
	var pageNum = 0;
	if(totalPage - pageNo > 1){
		pageNum = pageNo + 1;
	}else{
		pageNum = totalPage;
	}
	
	$("#"+pageId).append("<span style='float:right;'><span style='line-height:30px;'>&nbsp;共" 
			+ totalCount + "条&nbsp;第"+pageNo+"/"+totalPage
			+"页&nbsp;跳转至&nbsp;"+createSelect(totalPage,pageNo)+"</span></span>"); 
	if(pageNo != totalPage){
		$("#"+pageId).append("<span onclick='goPage("+totalPage+");' class='page2_btn_abled'>尾页</span>");
	}else{
		$("#"+pageId).append("<span class='page2_btn_disabled'>尾页</span>");
	}
	if(totalPage - pageNo  > 0){
		$("#"+pageId).append("<span class='page2_btn_abled' onclick='goPage("+ (pageNo + 1)+");'>下一页</span>");
	}else{
		$("#"+pageId).append("<span class='page2_btn_disabled'>下一页</span>");
	}
	if(pageNo - 1 > 0){
		$("#"+pageId).append("<span onclick='goPage("+ (pageNo - 1 )+");' class='page2_btn_abled'>上一页</span>");
	}else{
		$("#"+pageId).append("<span class='page2_btn_disabled'>上一页</span>");
	}
	if(pageNo != 1){
		$("#"+pageId).append("<span onclick='goPage(1);' class='page2_btn_abled'>首页</span>");
	}else{
		$("#"+pageId).append("<span class='page2_btn_disabled'>首页</span>");
	}
	$("#"+pageId).find("span").each(function(){
		if($(this).attr("class") == "page2_btn_abled"){
			$(this).bind({
				mousemove : function() {
					$(this).css("background-color", "#00a66a");
				},
				mouseout:function(){
					$(this).css("background-color", "");
				}
			});
		}
	});
}
function goPage(pageNum){
	if($("#pageNo").val() != pageNum){
	 $("#pageNo").val(pageNum);
	 doSearch();
	}
}

function createSelect(totalPage,pageIndex){
	if(pageIndex == null) {
		return;
	}
	var str = "<select id='allPage' name='allPage' onchange='javascript:goPage(this.selectedIndex + 1)'>"
	for(var i = 1; i <= totalPage; i++){
		if(i == pageIndex){
			str += "<option value='" + i + "' selected >" + i + "</option>";
		}else{
			str += "<option value='" + i + "'>" + i + "</option>";
		}
	}
	str += "</select>";
	return str;	
}

