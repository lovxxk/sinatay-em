/***
 *判断是否为IE浏览器
 * @returns {Boolean}
 */
function isIE() {
	if(document.all) return true;
	return false;
}

function setInputHint(input,text,type){
	
	if(input.value == ''){
		input.value = text;
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

//定义倒计时对象
var CountDown = function(count,func){
	var se, 
		start = count,
		resend = false;
 	
	function second() {
		start-- ;
		if(start <= 0 )
		{
			resend = true;
			stopclock();
			if(func.stopFn){
				func.stopFn();
			}
		}else{
			if(func.stepFn){
				func.stepFn(start);
			}
		}
    }
	
	function startclock (){ 
		se = setInterval(second, 1000); 
		resend = false;
	}
	
	function stopclock (){
		clearInterval(se);
	}
	
	function reset(){
		stopclock();
		start = count;	
		resend = true;
	}
	
	this.stop = function(){
		stopclock();
	};
	
	this.resetclock = function(){
		reset();
		if(func.resetFn){
			func.resetFn(start);
		}
	};
	
	this.restartclock = function(){
		reset();
		startclock();
		if(func.startFn){
			func.startFn(start);
		}
	};
	
	this.countEnd = function(){
		return resend;
	};
	this.setActive = function(active){
		resend = active;  
	};
};

/***
 * 将javaScript对象转化成JSON串
 */
function toJSON(obj){
	if (obj) {
		switch(obj.constructor){   
		    case Object:
		        var str = "{";   
		        for(var o in obj){ 
		        	var tempstr = arguments.callee(obj[o]); 
		        	if(tempstr!=null&&tempstr!=""){ 
		            str += o + ":" + tempstr +",";   
		            }
		        }   
		        if(str.substr(str.length-1) == ",")   
		            str = str.substr(0,str.length -1);   
		        return str + "}";   
		        break;   
		    case Array:               
		        var str = "[";   
		        for(var o in obj){ 
		        	var tempstr = arguments.callee(obj[o]); 
		        	if(tempstr!=null&&tempstr!=""){ 
		            str += tempstr +",";   
		            }  
		        }   
		        if(str.substr(str.length-1) == ",")   
		            str = str.substr(0,str.length -1);   
		        return str + "]";   
		        break;   
		    case Boolean:   
		        return "\"" + obj.toString() + "\"";   
		        break;   
		    case Date:   
		        return "\"" + obj.toString() + "\"";   
		        break;   
		    case Function:   
		    	return "";
		        break;   
		    case Number:   
		        return "\"" + obj.toString() + "\"";   
		        break;    
		    case String:   
		        return "\"" + obj.toString() + "\"";   
		        break;       
		}
	} else {
		return "";
	}
}

//数组操作

function objExistInArray(array,obj){
	return indexOfArray(array,obj) != -1;
}

function indexOfArray(array,obj){
	for(var i = 0;i<array.length;i++){
        if(array[i] == obj){
           return i;
         }
     }
     return -1;
}

function clear(array){
	array.length = 0;
}

function insert(array,index, obj){
	if(index >= 0){
		array.splice(index, 0, obj);
	}
}
function remove(array,index){
	if(index >= 0){
		array.splice(index, 1);
	}
}
function removeObj(array, obj){
	var index = array.indexOf(obj);
	if(index >= 0){
		remove(array,index);
	}
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


function setInputRule(input){
	if(isIE()){
		var el = input[0];
		if(el){
			el.onkeypress = function(){TextValidate("input",this,event);};
			el.onkeyup = function(){TextValidate("inputup",this,event);};
			el.onpaste = function(){TextValidate("paste",this,event);};
			el.ondrag = function(){TextValidate("drop",this,event);};
			el.ondragenter = function(){TextValidate("drop",this,event);};
			el.ondragover = function(){TextValidate("drop",this,event);};
		}
	}else{
		input.attr({
			'onkeypress':'TextValidate("input",this,event);',
			'onkeyup':'TextValidate("inputup",this,event);',
			'onpaste':'TextValidate("paste",this,event);',
			'ondrag':'TextValidate("drop",this,event);',
			'ondragenter':'TextValidate("drop",this,event);',
			'ondragover':'TextValidate("drop",this,event);'
		});
	}
}
function TextValidate(type,el,event) {
	event = event || window.event;
	var pos = getCursorPos(el);
    var code;
    var character;
    var regText;
    
//    regText = "[\\{,\\},\\\[,\\\],\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\<,\\>,\",\']";
    
    //除了#-（）以外的所有特殊字符（括号为中文括号）
    regText = "[~!@$%^&*_+`=\\[\\]\\{}|;\'\":,\.\/<>?！￥…—、‘’“”；：，。《》（）【】？·]";
    
    if (type == "input" || type == "inputup") {
    	if(window.event){
    		code = window.event.keyCode;    		
    	}else{
    		code = arguments.callee.caller.arguments[0].which;
    	}
    }
    else if (type == "paste") {
    	if(window.clipboardData){
    		code = window.clipboardData.getData('text');
    	}
    }
//    else if (type == "drop") {
//        code = event.dataTransfer.getData('text');
//    }
    var character = String.fromCharCode(code);
    var regInput = new RegExp(regText);
    if (type == "input") {
        if (regInput.test(character)) {
            if (document.all) {
                window.event.returnValue = false;
            }
            else {
                arguments.callee.caller.arguments[0].preventDefault();
            }
        }
    }
    var regAll = new RegExp(regText,'g');
    if( type == "inputup"){
    	if(regAll.test(el.value)){
    		pos--;
    		el.value = el.value.replace(regAll,'');
    	}
    }
    
    if( type == "paste"){
    	if (window.clipboardData){
    		if(txt.test(code)){
    			window.event.returnValue = false;
        	}
    	} else {
    		el.value = el.value.replace(regAll,'');
    	}
    }
    //禁止拖入文本
    if(type == "drop"){
    	event.returnValue = false;
    }
    
    setCursorPos(el,pos);
}

function getCursorPos(obj) 
{
	if(document.selection){
		obj.focus();
		var currentRange = document.selection.createRange();
		
		var workRange=currentRange.duplicate();
		obj.select();
		var allRange=document.selection.createRange();
		var pos=0;
		while(workRange.compareEndPoints("StartToStart",allRange)>0)
		{
			workRange.moveStart("character",-1);
			pos++;
		}
		currentRange.select();
		return pos;
	}else if(window.getSelection()){
		return obj.selectionStart;
	}
}
function setCursorPos(obj,pos)
{
	if(document.selection){
		var rng =obj.createTextRange();
		rng.moveStart('character',pos);
		rng.collapse(true);
		rng.select();
	}else if(window.getSelection()){
		obj.selectionStart = pos;
		obj.selectionEnd = pos;
	}
}

function functionExcute(func,time){
	if(isIe6()){
		var t = 1;
		if(time){
			t = time;
		}
		setTimeout(function(){
			func();
		},t);
	}else{
		func();
	}
}

var OS = function(){
	var userAgent = navigator.userAgent,
	names = {
        ios: 'iOS',
        android: 'Android',
        webos: 'webOS',
        blackberry: 'BlackBerry',
        rimTablet: 'RIMTablet',
        mac: 'MacOS',
        win: 'Windows',
        linux: 'Linux',
        bada: 'Bada',
        other: 'Other'
    },
    prefixes = {
        ios: 'i(?:Pad|Phone|Pod)(?:.*)CPU(?: iPhone)? OS ',
        android: '(Android |HTC_|Silk/)',
        blackberry: 'BlackBerry(?:.*)Version\/',
        rimTablet: 'RIM Tablet OS ',
        webos: '(?:webOS|hpwOS)\/',
        bada: 'Bada\/'
    },
    name,
    version = '',
    i, prefix, match, item, is;
    
	this.is = function(osname){
		if(osname == name){
			return true;
		}else{
			return false;
		}
	};
	
	for (i in prefixes) {
        if (prefixes.hasOwnProperty(i)) {
            prefix = prefixes[i];

            match = userAgent.match(new RegExp('(?:'+prefix+')([^\\s;]+)'));
            if (match) {
                name = names[i];
                break;
            }
        }
    }
	
	if (!name) {
        name = names[(userAgent.toLowerCase().match(/mac|win|linux/) || ['other'])[0]];
    }
	
	this.name = name;
    this.version = version;
    
    this.names = names;
};

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
if(!window.os){
	window.os = new OS();
}

function isIe6(){
	return browser.msie && browser.version == "6.0";
}