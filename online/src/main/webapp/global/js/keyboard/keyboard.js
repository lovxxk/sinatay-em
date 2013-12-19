/*
 * JS Keyboard - 随机生成的软键盘.
 * By yhustc (http://www.yhustc.com)
 * Copyright (c) 2009 yhustc
 * Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
*/

function RandomSort(a,b){
	return Math.random() - 0.5;
}

function getRandomNum()
{
	var numArray=new Array();
	var i;
	for(i=0;i<10;i++)
	  numArray[i]=i;//生成一个数组
	numArray.sort(RandomSort);
	return numArray;
}

function getRandomChar()
{
	var charArray=new Array();
	var i,j;
	for(i=0,j=97;j<123;i++,j++)
	  charArray[i]=j;//生成字母表
	charArray.sort(RandomSort);
	//对字母进行翻译
	for(i=0;i<charArray.length;i++)
		charArray[i] = String.fromCharCode(charArray[i]);
	return charArray;
}

function showKeyboard(inputId)
{
	if($("#"+inputId).attr("readOnly")==false){
		$("#"+inputId).attr("readOnly",true);
	}else if($("#"+inputId).attr("readOnly")==true){
		$("#"+inputId).attr("readOnly",false);
		$("#"+inputId).blur();
	}
	var kb = $('#yh_KeyBoard');
	if (kb.length!=0)
	{
		kb.remove();
		return false;
	}
	
	kb = $('<div id="yh_KeyBoard" class="kbdiv"></div>');
	var i=0;
	var keyboard = '<table class="kbtable"><tr>';
	numArray = getRandomNum();
	charArray = getRandomChar();
	for(i=0;i<10;i++)
	{
		keyboard += '<td class="kbkey">'+numArray[i]+'</td>';
	}
	keyboard += '<td class="kbkey" id="kDelete">←</td>';
	keyboard += "</tr><tr>";
	for(i=0;i<26;i++)
	{
		if (i%11==0 && i>0)
			keyboard += "</tr><tr>";
		keyboard += '<td class="kbkey">'+charArray[i]+'</td>';
	}
	keyboard += '<td id="kbcaps" colspan="2" class="kbcolspan">大小写</td>';
	keyboard += '<td id="kbTab" colspan="3" class="kbcolspan">切换到键盘</td>';
	keyboard += '<td id="kbclose" colspan="2" class="kbcolspan">关闭</td>';
	keyboard += '</tr></table>';
	kb.html(keyboard);
	kb.appendTo('body');

	$("td",kb).mouseover(function() {
		this.className += " kbmouseover";
	}).mouseout(function() {
		this.className = this.className.replace(" kbmouseover","");
	}).click(function() {
		if(this.id == "kbTab") {
			kb.remove();
			$("#"+inputId).removeAttr("readOnly");
			$("#"+inputId).blur();
			return false;
		}
		if(this.id == "kbclose") {
			$("#"+inputId).focus();
			//$("#"+inputId).blur();
			//$("#"+inputId).blur();
			kb.remove();
			$("#"+inputId).removeAttr("readOnly");
			return false;
		}
		if(this.id == "kDelete"){
			var MoveValue = $("#"+inputId).val();
			if(MoveValue.length>0){
				MoveValue = MoveValue.substring(0,MoveValue.length-1);	
				$("#"+inputId).attr("value",MoveValue);
				return false;
			}else{
				return false;
			}	
		}
		else if(this.id == "kbcaps") {
			$.each($(".kbkey",kb),function(i,o) {
				var num = o.innerHTML.charCodeAt(0);
				if(num>96 && num<123)
					o.innerHTML = o.innerHTML.toUpperCase();
				else if(num>64 && num<91)
					o.innerHTML = o.innerHTML.toLowerCase();
			});

			return false;
		}

		$("#"+inputId).attr("value",$("#"+inputId).val()+this.innerHTML);
	});
	
	var offset = $("#"+inputId).offset();
	var left = offset.left;
	var height = $("#"+inputId).height();
	var top = offset.top+height+8;
	kb.css({"left": left+"px", "top": top+"px"});

	return false;
}