/*
Content-Type: multipart/related; boundary="_CLOUDGAMER"

--_CLOUDGAMER
Content-Location:blankImage
Content-Transfer-Encoding:base64

R0lGODlhAQABAJEAAAAAAP///////wAAACH5BAEAAAIALAAAAAABAAEAAAICVAEAOw==
*/

var client = function () {
	var engine = {
		ie: 0,
		gecko: 0,
		webkit: 0,
		khtml: 0,
		opera: 0,
		ver: null
	};
	
	var browser = {
		ie: 0,
		firefox: 0,
		konq: 0,
		opera: 0,
		chrome: 0,
		safari: 0,
		
		ver: null
	};
	
	var system = {
		win: false,
		mac: false,
		x11: false,
		
		iphone: false,
		ipod: false,
		nokiaN: false,
		winMobile: false,
		macMobile: false,
		
		wii: false,
		ps: false
	};
	
	var ua = navigator.userAgent;
	
	if (window.opera) {
		engine.ver = browser.ver = window.opera.version();
		engine.opera = browser.opera = parseFloat(engine.ver);
	} else if (/AppleWebKit\/(\S+)/.test(ua)) {
		engine.ver = RegExp["$1"];
		engine.webkit = parseFloat(engine.ver);	
		  
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
		engine.ver = browser.ver = RegExp["$1"];
		engine.khtml = browser.kong = parseFloat(engine.ver);
	} else if (/rv:([^\)]+)\) Gecko\/\d{8}/.test(ua)) {
		engine.ver = RegExp["$1"];
		engine.gecko = parseFloat(engine.ver);
		
		if (/Firefox\/(\S+)/.test(ua)) {
			browser.ver = RegExp["$1"];
			browser.firefox = parseFloat(browser.ver);	
		}
	} else if (/MSIE ([^;]+)/.test(ua)) {
		engine.ver = browser.ver = RegExp["$1"];
		engine.ie = browser.ie = parseFloat(engine.ver);
	}
	
	var platform = navigator.platform;
	
	system.win = platform.indexOf("Win") == 0;
	system.mac = platform.indexOf("Mac") == 0;
	system.x11 = (platform.indexOf("X11") == 0) || (platform.indexOf("Linux")  == 0); 
	
	if (system.win) {
		if (/Win(?:dows)?([^do]{2})\s?(\d+\.\d+)?/.test(ua)) {
			if (RegExp["$1"] == "NT") {
				switch(RegExp["$2"]) {
					case "5.0":
						system.win = "2000";
						break;
					case "5.1":
						system.win = "XP";
						break;
					case "6.0":
						system.win = "Vista";
						break;
					case "6.1":
						system.win = "Win7";
						break;
					defalut: 
						system.win = "NT";
						break;
				}
			} else if (RegExp["$1"] = "9x") {
				system.win = "ME";
			} else  {
				system.win = RegExp["$1"];
			}
		}
	} 
	system.iphone = ua.indexOf("iPhone") > -1;
	system.ipod = ua.indexOf("iPod") > -1;
	system.macMobile = ( system.iphone || system.ipond);
	system.nokiaN = ua.indexOf("NokiaN") > -1;
	system.winMobile = (system.win == "CE");
	system.wii = ua.indexOf("Wii") > -1;
	system.ps = /playstation/i.test(ua);
	
	return { engine: engine, browser:browser, system:system};
} ();
var ImagePreview = function(file, img, options) {
	
	this.file = $$(file);//鏂囦欢瀵硅薄
	this.img = $$(img);//棰勮鍥剧墖瀵硅薄
	
	this._preload = null;//棰勮浇鍥剧墖瀵硅薄
	this._data = "";//鍥惧儚鏁版嵁
	this._upload = null;//remote妯″紡浣跨敤鐨勪笂浼犳枃浠跺璞�
	
	var opt = this._setOptions(options);
	
	this.action = opt.action;
	this.timeout = opt.timeout;
	this.ratio = opt.ratio;
	this.maxWidth = opt.maxWidth;
	this.maxHeight = opt.maxHeight;
	this.onCheck = opt.onCheck;
	this.onShow = opt.onShow;
	this.onErr = opt.onErr;
	
	//璁剧疆鏁版嵁鑾峰彇绋嬪簭
	this._getData = this._getDataFun(opt.mode);
	//璁剧疆棰勮鏄剧ず绋嬪簭
	this._show = opt.mode !== "filter" ? this._simpleShow : this._filterShow;
};
//鏍规嵁娴忚鍣ㄨ幏鍙栨ā寮�
ImagePreview.MODE = $$B.ie7 || $$B.ie8 ? "filter" :
	$$B.firefox ? "domfile" :
	$$B.opera || $$B.chrome || $$B.safari ? "remote" : "simple";
//閫忔槑鍥剧墖
ImagePreview.TRANSPARENT = $$B.ie7 || $$B.ie6 ?
	"mhtml:" + document.scripts[document.scripts.length - 1].getAttribute("src", 4) + "!blankImage" :
	"data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==";
	//alert(document.scripts[document.scripts.length - 1].getAttribute("src", 4) + "!blankImage");

ImagePreview.prototype = {
  //璁剧疆榛樿灞炴�
  _setOptions: function(options) {
    this.options = {//榛樿鍊�
		mode:		ImagePreview.MODE,//棰勮妯″紡
		ratio:		0,//鑷畾涔夋瘮渚�
		maxWidth:	0,//缂╃暐鍥惧搴�
		maxHeight:	0,//缂╃暐鍥鹃珮搴�
		onCheck:	function(){},//棰勮妫�祴鏃舵墽琛�
		onShow:		function(){},//棰勮鍥剧墖鏃舵墽琛�
		onErr:		function(){},//棰勮閿欒鏃舵墽琛�
		//浠ヤ笅鍦╮emote妯″紡鏃舵湁鏁�
		action:		undefined,//璁剧疆action
		timeout:	0//璁剧疆瓒呮椂(0涓轰笉璁剧疆)
    };
    return $$.extend(this.options, options || {});
  },
  //寮�棰勮
  preview: function() {
	if(this.file){
		var fileExt = this.file.value;
		fileExt = fileExt.substring(fileExt.indexOf(".")+1).toLowerCase();
		if(!this.checkIsImage(fileExt)){
			//alert("閫夋嫨鐨勬枃浠朵笉鏄浘鐗囷紝璇烽噸鏂伴�鎷╋紒");
			alert("不是图片格式");
			return;
		}
	}
	if ( this.file && false !== this.options.onCheck()) {
		if (client.browser.firefox > 7) {
			var file = this.file.files[0];
			var reader = new FileReader();  
		    reader.onload = (function(aImg) { return function(e) { aImg.src = e.target.result; }; })(this.img);  
		    reader.readAsDataURL(file); 
		} else {
			this._preview( this._getData() );
		}
		
		
		
	}
  },
  
  //鏍规嵁mode杩斿洖鏁版嵁鑾峰彇绋嬪簭
  _getDataFun: function(mode) {
	switch (mode) {
		case "filter" :
			return this._filterData;
		case "domfile" :
			return this._domfileData;
		case "remote" :
			return this._remoteData;
		case "simple" :
		default :
			return this._simpleData;
	}
  },
  //婊ら暅鏁版嵁鑾峰彇绋嬪簭
  _filterData: function() {
	this.file.select();
	try{
		return document.selection.createRange().text;
	} finally { 
		document.selection.empty(); 
	}
  },
  //domfile鏁版嵁鑾峰彇绋嬪簭
  _domfileData: function() {
	return this.file.files[0].getAsDataURL();
  },
  
  //杩滅▼鏁版嵁鑾峰彇绋嬪簭
  _remoteData: function() {
	this._setUpload();
	this._upload && this._upload.upload();
  },
  //涓�埇鏁版嵁鑾峰彇绋嬪簭
  _simpleData: function() {
	return this.file.value;
  },
  
  //璁剧疆remote妯″紡鐨勪笂浼犳枃浠跺璞�
  _setUpload: function() {
	if ( !this._upload && this.action !== undefined && typeof QuickUpload === "function" ) {
		var oThis = this;
		this._upload = new QuickUpload(this.file, {
			onReady: function(){
				this.action = oThis.action; this.timeout = oThis.timeout;
				var parameter = this.parameter;
				parameter.ratio = oThis.ratio;
				parameter.width = oThis.maxWidth;
				parameter.height = oThis.maxHeight;
			},
			onFinish: function(iframe){
				try{
					oThis._preview( iframe.contentWindow.document.body.innerHTML );
				}catch(e){ oThis._error("remote error"); }
			},
			onTimeout: function(){ oThis._error("timeout error"); }
		});
	}
  },
  
  //棰勮绋嬪簭
  _preview: function(data) {
	//绌哄�鎴栫浉鍚岀殑鍊间笉鎵ц鏄剧ず
	if ( !!data && data !== this._data ) {
		
		this._data = data; this._show();
	}
  },
  
  //璁剧疆涓�埇棰勮浇鍥剧墖瀵硅薄
  _simplePreload: function() {
	if ( !this._preload ) {
		var preload = this._preload = new Image(), oThis = this,
			onload = function(){ oThis._imgShow( oThis._data, this.width, this.height ); };
		this._onload = function(){ this.onload = null; onload.call(this); }
		preload.onload = $$B.ie ? this._onload : onload;
		preload.onerror = function(){ oThis._error(); };
	} else if ( $$B.ie ) {
		this._preload.onload = this._onload;
	}
  },
  //涓�埇鏄剧ず
  _simpleShow: function() {
	this._simplePreload();
	this._preload.src = this._data;
  },
  
  //璁剧疆婊ら暅棰勮浇鍥剧墖瀵硅薄
  _filterPreload: function() {
	if ( !this._preload ) {
		var preload = this._preload = document.createElement("div");
		//闅愯棌骞惰缃护闀�
		$$D.setStyle( preload, {
			width: "1px", height: "1px",
			visibility: "hidden", position: "absolute", left: "-9999px", top: "-9999px",
			filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image')"
		});
		//鎻掑叆body
		var body = document.body; body.insertBefore( preload, body.childNodes[0] );
	}
  },
  //婊ら暅鏄剧ず
  _filterShow: function() {
	this._filterPreload();
	var preload = this._preload,
		data = this._data.replace(/[)'"%]/g, function(s){ return escape(escape(s)); });
	try{
		preload.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = data;
	}catch(e){ this._error("filter error"); return; }
	//璁剧疆婊ら暅骞舵樉绀�
	this.img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + data + "\")";
	this._imgShow( ImagePreview.TRANSPARENT, preload.offsetWidth, preload.offsetHeight );
  },
  
  //鏄剧ず棰勮
  _imgShow: function(src, width, height) {
	var img = this.img, style = img.style,
		ratio = Math.max( 0, this.ratio ) || Math.min( 1,
				Math.max( 0, this.maxWidth ) / width  || 1,
				Math.max( 0, this.maxHeight ) / height || 1
			);
	//璁剧疆棰勮灏哄
	style.width = Math.round( width * ratio ) + "px";
	style.height = Math.round( height * ratio ) + "px";
	//璁剧疆src
	img.src = src;
	this.onShow();
  },
  
  //閿�瘉绋嬪簭
  dispose: function() {
	//閿�瘉涓婁紶鏂囦欢瀵硅薄
	if ( this._upload ) {
		this._upload.dispose(); this._upload = null;
	}
	//閿�瘉棰勮浇鍥剧墖瀵硅薄
	if ( this._preload ) {
		var preload = this._preload, parent = preload.parentNode;
		this._preload = preload.onload = preload.onerror = null;
		parent && parent.removeChild(preload);
	}
	//閿�瘉鐩稿叧瀵硅薄
	this.file = this.img = null;
  },
  //鍑洪敊
  _error: function(err) {
	this.onErr(err);
  },
  
  checkIsImage: function(ext){
    if (!ext.match(/jpg|gif|png|bmp/i)) {
        return false;
    }
    return true;
  }
}