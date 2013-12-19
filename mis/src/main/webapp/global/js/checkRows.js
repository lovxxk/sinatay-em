function checkAll(obj){
	var checkArray = document.getElementsByName("checkChild");
	var tr_selected;
	for(var i = 0; i < checkArray.length; i++){
		tr_selected = document.getElementById("tr_" + i);
		if(obj.checked){
			tr_selected.className = "search_tr_selected";
		}else{
			if(i%2 == 0){
				tr_selected.className = "";
			}else{
				tr_selected.className = "search_tr_ou";
			}
		}
		checkArray[i].checked = obj.checked;
	}
}
//��ѡĳһ��
function checkSingleRow(){
	var idStr = "";
	var count = 0;
	var checkArray = document.getElementsByName("checkChild");
	var tr_selected;
	var value_checked;
	for (var i = 0; i < checkArray.length; i++){
		tr_selected = document.getElementById("tr_" + i);
		if(checkArray[i].checked){
			tr_selected.className = "search_tr_selected";
			value_checked = checkArray[i].value;
			if(idStr == ""){
				idStr = value_checked;
			}else {
				idStr = idStr + "," + value_checked;
			}
			count++;
		}else{
			if(i%2 == 0){
				tr_selected.className = "";
			}else{
				tr_selected.className = "search_tr_ou";
			}
		}
	}
	window.parent.frames[1].document.getElementById("idStr").value = idStr;
	window.parent.frames[1].document.getElementById("count").value = count;
}

