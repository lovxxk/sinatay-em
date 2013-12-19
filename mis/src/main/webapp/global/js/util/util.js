String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

String.prototype.replaceAll = function(regex, replaceWith, ignoreCase) {  
	 
　	 if (!RegExp.prototype.isPrototypeOf(regex)) {  
 
        return this.replace(new RegExp(regex, (ignoreCase ? "gi": "g")), replaceWith);  
 
    } else {  
 
        return this.replace(regex, replaceWith);  
 
    }  
 
}

var client = function () {
	var browser = {
		ie: 0,
		firefox: 0,
		konq: 0,
		opera: 0,
		chrome: 0,
		safari: 0,
		ver: null
	};
	
	var viewport = {
		width:null,
		height:null
	};
	var ua = navigator.userAgent;
	
	if (window.opera) {
		browser.ver = window.opera.version();
		browser.opera = parseFloat(engine.ver);
	} else if (/AppleWebKit\/(\S+)/.test(ua)) {
		if (/Chrome\/(\S+)/.test(ua)) {
			browser.ver = RegExp["$1"];
		  	browser.chrome = parseFloat(browser.ver);
		} else if (/Version\/(\S+)/.test(ua)) {
		  	browser.ver = RegExp["$1"];
		  	browser.safari = parseFloat[browser.ver];
		} else  {
		  	var safariVersion = 1;
		  	if (engine.webkit < 100) {
		  	 	safariVersion = 1;
		  	}  else if (engine.webkit < 312) {
		  		safariVersion = 1.2;
		  	} else if (engine.webkit < 412) {
		  		safariVersion = 1.3;
		  	} else {
		  		safariVersion = 2;
		  	}
		  	browser.safari = browser.ver = safariVersion;
		}
	} else if (/KHTML\/(\S)/.test(ua) || /Konqueror\/([^;+])/.test(ua)) {
		browser.ver = RegExp["$1"];
		browser.kong = parseFloat(browser.ver);
	} else if (/rv:([^\)]+)\) Gecko\/\d{8}/.test(ua)) {
		if (/Firefox\/(\S+)/.test(ua)) {
			browser.ver = RegExp["$1"];
			browser.firefox = parseFloat(browser.ver);	
		}
	} else if (/MSIE ([^;]+)/.test(ua)) {
		browser.ver = RegExp["$1"];
		browser.ie = parseFloat(browser.ver);
	}
	
	//浏览器视口大小
	if (browser.ie > 0) {
		
		if (document.compatMode == "BackCompat") {
			viewport.width = document.body.clientWidth;
			viewport.height = document.body.clientHeight;
		} else {
			viewport.width = document.documentElement.clientWidth;
			viewport.height = document.documentElement.clientHeight;
		}
	} else if (browser.firefox >　0) {
		viewport.width = document.body.clientWidth;
		viewport.height = document.body.clientHeight;
	} else {
		viewport.width = document.documentElement.clientWidth;
		viewport.height = document.documentElement.clientHeight;
	}
	
	return { browser:browser, viewport:viewport};
}();