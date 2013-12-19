function arrow(obj) {
	clearAllPraBC();
	var childs = obj.parentNode.childNodes;
	var className = obj.className;
	var show;
	
	if (className == "leftPanel_title_up" || className == "leftPanel_title_up2") {
		obj.className = "leftPanel_title_down2";
		show = "none";
	}
	if (className == "leftPanel_title_down" || className == "leftPanel_title_down2") {
		obj.className = "leftPanel_title_up2";
		show = "block";
	}
	for ( var i = 0; i < childs.length; i++) {
		className = childs[i].className;
		if (childs[i].className == "leftPanel_list") {
			childs[i].style.display = show;
		}
	}
}
//点击子节点触发样式变化
function changeLi(obj,url) {
	clearAllLiBC();
	obj.style.backgroundColor = "#BFBFBF";
	obj.style.color = "#6666CC";
	obj.style.fontWeight = "bold";
	clearAllPraBC();
	var childs = obj.parentNode.parentNode.parentNode.childNodes;
	for(var i = 0; i < childs.length; i++){
		if (childs[i].nodeType != 3) {//过滤空白
			childs[i].className = "leftPanel_title_up2";
			break;
		}
	}
	window.parent.fraMAIN.location.href = url;
}
//清除所有父标题背景
function clearAllPraBC() {
	var obj = document.getElementsByTagName("div");
	for(var i = 0; i < obj.length; i++){
		if (obj[i].className == "leftPanel_title_up2") {
			obj[i].className = "leftPanel_title_up";
		}
		if (obj[i].className == "leftPanel_title_down2") {
			obj[i].className = "leftPanel_title_down";
		}
	}
}
//清除所有子标题背景
function clearAllLiBC() {
	var obj = document.getElementsByTagName("li");
	for ( var i = 0; i < obj.length; i++) {
		obj[i].style.backgroundColor = "";
		obj[i].style.color = "";
		obj[i].style.fontWeight = "normal";
	}
}