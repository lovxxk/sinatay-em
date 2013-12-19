function setInputHint(input,text,type){
	
	if(input.value == ''){
		input.value = text;
		input.style.color='#ABABAB'; 
	}
	input.onblur = function(){
		if(this.value=='')
		{
			this.value=text;
			this.style.color='#ABABAB'; 
			if(type == 'password'){
				this.type='text';
			}
		}
	};
	input.onfocus = function(){
		this.style.color='#232323';
		if(this.value==text)
		{
			this.value='';
			
			if(type == 'password'){
				this.type='password';
			}
		}
	};
}

function DayNumOfMonth(Year,Month)
{
    var d = new Date(Year,Month,0);
    return d.getDate();
}
/**
 * 获取文字在指定容器中的宽度
 * 
 * str 待计算宽度文字
 * srcEl 目标容器
 * 
 * @returns 文字宽度
 * 
 * added：汪晔
 */
function getTextWidth(str,srcEl){
	var text_span = $('#text_width_aclc');
	if(text_span.length == 0){
		$('body').append($('<span id="text_width_aclc" style="visibility:hidden;"></span>'));
	}
	text_span = $('#text_width_aclc');
	var font_attr = ['font-size','font-weight','font-family','font-style'];
	for(var i=0 ; i< font_attr.length ; i++){
		text_span.css(font_attr[i],srcEl.css(font_attr[i]));
	}
	
	text_span.text(str);
	
	return text_span.width();
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
    	return unescape(r[2]);
    }
    return null;
}

/***
 *  String 截取空字符
 */
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

//针对旧版浏览器没有console.log方法导致报错的处理
(function(host) {
	var hasConsole = function(){
		return window.console && window.console.log;
	};
	
	var console;
	if(!hasConsole()){
		console = {
			log:function(obj){
				//emptyFn 仅仅为了使调用console.log方法不报错，如果有需要，可以加上alert方法
				//alert(obj);
			}
		};
		window.console = console;
	}
})(window);

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

function cleanWhitespace(oEelement)
{
		for(var i=0;i<oEelement.childNodes.length;i++){
  		var node=oEelement.childNodes[i];
	  	if(node.nodeType==3 && !/\S/.test(node.nodeValue)){
		  	node.parentNode.removeChild(node);
		}
  	}
}

/*
* 日期比较，参数1格式“yyyy-MM-dd”，参数2可选，默认为当前日期。
* 返回值为相差天数，若为当天，返回0
*/
function dateCompare(dateCompare1, dateCompare2) {
	try {
		if (dateCompare1) {
			var year = dateCompare1.substr(0, 4);
			var month = dateCompare1.substr(5, 2);
			var day = dateCompare1.substr(8, 2);

			dateCompare1 = new Date(year, month - 1, day);

			if (!dateCompare2) {
				dateCompare2 = new Date();
			} else {
				year = dateCompare2.substr(0, 4);
				month = dateCompare2.substr(5, 2);
				day = dateCompare2.substr(8, 2);

				dateCompare2 = new Date(year, month - 1, day);
			}

			var diff = dateCompare1.getTime() - dateCompare2.getTime();
			var days = diff / (1000 * 60 * 60 * 24);
			
			if ((days > 0 && days < 1) || (days * -1 > 0 && days * -1 < 1)) {
				days = 0;
			}

			return Math.floor(days);
		}
	} catch (e) {
//		alert(e + ":" + e.description);
	}
	return '';
}


//写cookies
function setCookie(name,value)
{
	var Days = 30;
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/";
}
//读取cookies
function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	
	if(arr=document.cookie.match(reg)){ 
		return unescape(arr[2]);
	}
	else {
		return null;
	}
}
//删除cookies
function delCookie(name)
{
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null){ 
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}
}

var Browser = function(){
	var userAgent = navigator.userAgent.toLowerCase();
	this.version = (userAgent.match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [0,'0'])[1];
	this.safari = /webkit/.test( userAgent ) && /safari/.test( userAgent ) && !/chrome/.test( userAgent );
	this.chrome = /webkit/.test( userAgent ) && /chrome/.test( userAgent );
	this.opera = /opera/.test( userAgent );
	this.msie = /msie/.test( userAgent ) && !/opera/.test( userAgent );
	this.mozilla = /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent );
};

if(!window.browser){
	window.browser = new Browser();
}

function isIe6(){
	return browser.msie && browser.version == "6.0";
}