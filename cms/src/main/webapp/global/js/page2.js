function pageChange(pageNo, pageSize,totalPage,totalCount,pageId){
	$("#"+pageId).empty();
	
	var pageNum = 0;
	if(totalPage - pageNo > 1){
		pageNum = pageNo + 1;
	}else{
		pageNum = totalPage;
	}
	
	$("#"+pageId).append("<span style='float:right;'><span style='line-height:30px;'>&nbsp;��" 
			+ totalCount + "��&nbsp;��"+pageNo+"/"+totalPage
			+"ҳ&nbsp;��ת��&nbsp;"+createSelect(totalPage,pageNo)+"</span></span>"); 
	if(pageNo != totalPage){
		$("#"+pageId).append("<span onclick='goPage("+totalPage+");' class='page2_btn_abled'>βҳ</span>");
	}else{
		$("#"+pageId).append("<span class='page2_btn_disabled'>βҳ</span>");
	}
	if(totalPage - pageNo  > 0){
		$("#"+pageId).append("<span class='page2_btn_abled' onclick='goPage("+ (pageNo + 1)+");'>��һҳ</span>");
	}else{
		$("#"+pageId).append("<span class='page2_btn_disabled'>��һҳ</span>");
	}
	if(pageNo - 1 > 0){
		$("#"+pageId).append("<span onclick='goPage("+ (pageNo - 1 )+");' class='page2_btn_abled'>��һҳ</span>");
	}else{
		$("#"+pageId).append("<span class='page2_btn_disabled'>��һҳ</span>");
	}
	if(pageNo != 1){
		$("#"+pageId).append("<span onclick='goPage(1);' class='page2_btn_abled'>��ҳ</span>");
	}else{
		$("#"+pageId).append("<span class='page2_btn_disabled'>��ҳ</span>");
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

