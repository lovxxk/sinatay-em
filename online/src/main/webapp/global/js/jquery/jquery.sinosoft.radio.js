(function($){  
	
	$.sinosoft = $.sinosoft || {version: '@VERSION'};
	
	var tool = $.sinosoft.radio = {
			conf: {
				name:'',//单选框名称，分组使用
				enable:true, //是否启用
				enableGroup:true, //是否启用组
				check:true, //是否初始选中
				opts:[], //标签文字数组，用于选框组初始化
				css:{
					radio:'radio_main',
					label:'radio_label',
					checked:'checked',
					disable:'disabled'
				},
				skin:'st_quote',
				onSelect:function(){}
			},
			name:'jRadio',
			skinFloder : 'radio_skin',
			jsPath : getJsPath('jquery.sinosoft.radio.js'),
			skinList : [
				{name:"st_normal",charset:"gbk"},
				{name:"st_quote",charset:"gbk"}
			]
	};
	
	function getJsPath(name){
		var path = '';
		var js=document.getElementsByTagName("script");
		for(var i=js.length;i>0;i--){
			if(js[i-1].src.indexOf(name)>-1){
				path=js[i-1].src.substring(0,js[i-1].src.lastIndexOf("/")+1);
			}
		}
		return path;
	}
	
	function addstylesheet(name,charset,path){
		var head = document.getElementsByTagName('HEAD');
			links = head[0].getElementsByTagName('link');
		var link = document.createElement("link");
		var href = tool.skinFloder+'/'+name;
		
		for(var i=0 ; i < links.length ; i++){
			if(links[i].href.indexOf(href) != -1){
				return;
			}
		}
		
		link.href = path+href;
		link.rel = "stylesheet";
		link.type = "text/css";
		link.charset = charset;
		head[0].appendChild(link);
	}
	
	function getSkin(skinName){
		for(var i=0 ; i < tool.skinList.length ; i++){
			if(tool.skinList[i].name == skinName ){
				return tool.skinList[i];
			}
		}
		return undefined;
	}
	
	function JRadio(el ,conf){
		var enable = conf.enable,
			checked = conf.checked,
			self = this,
			css = conf.css,
			root = $("<div><div></div></div>").data($.sinosoft.radio.name, self);
		
		var skin = getSkin(conf.skin);
		
		if(skin){
			addstylesheet(skin.name+'/radio.css',skin.charset,tool.jsPath);
		}
		
		var label = root.addClass(css.radio).addClass(skin.name).find('div');
		
		label.addClass(css.label);
		
		label.text(conf.option.label);
		
		el.append(root);
		
		firstInit();
		
		root.click(function(){
			if(enable){
				radioCheck();
			}
		});
		
		function firstInit(){
			if(conf.option.selected){
				root.addClass(css.checked);
				checked = true;
			}
			if(!enable){
				root.addClass(css.disable);
			}
		}
		
		function radioCheck(){
			if(!root.hasClass(css.checked)){
				
				for(var i = 0 ; i<$.sinosoft.radio[conf.name].length ; i++ ){
					$.sinosoft.radio[conf.name][i].uncheck();
				}
				
				checked = true;
				root.addClass(css.checked);
				
				if(conf.onSelect){
					conf.onSelect(self);
				}
				
			}
		}
		
		function radioUnCheck(){
			root.removeClass(css.checked);
			checked = false;
		}
		
		$.extend(self, {  
			enable: function(en){
				if((typeof en == 'undefined')){
					return enable;
				}
				
				enable = en;
				
				if(en){
					root.removeClass(css.disable);
				}else{
					root.addClass(css.disable);
				}
				
			},
			check: function(){
				radioCheck();
			},
			uncheck: function(){
				radioUnCheck();
			},
			checked: function(){
				return checked;
			},
			getLabel:function(){
				return conf.option.label;
			},
			getValue: function(){
				return conf.option.value;
			}
		});
	}
	// jQuery plugin implementation
	$.fn.jRadio = function(conf) {
		// already installed
		if (this.data(tool.name)) { return this; } 
		// extend configuration with globals
		conf = $.extend(true, {}, tool.conf, conf);		
		
		this.each(function() {		
			var index = $(this).index();
			
			if(conf.name){
				if(!$.sinosoft.radio[conf.name]){
					$.sinosoft.radio[conf.name] = [];
				}
			}
			
			for(var i = 0 ; i < conf.opts.length ; i++){
				var el = new JRadio($(this), $.extend(true, {}, conf,{option:conf.opts[i]}));
				$.sinosoft.radio[conf.name].push(el);
			}
			
			$(this).data(tool.name,{
				radios:$.sinosoft.radio[conf.name],
				val: function(value){
					if((typeof value == 'undefined')){
						for(var i = 0 ; i<this.radios.length ; i++ ){
							if(this.radios[i].checked()){
								return this.radios[i].getValue();
							}
						}
						
						return 'unselected';
					}
					for(var i = 0 ; i<this.radios.length ; i++ ){
						if(this.radios[i].getValue() == value){
							this.radios[i].check();
						}
					}
				},
				enableAll: function(en){
					for(var i = 0 ; i<this.radios.length ; i++ ){
						this.radios[i].enable(en);
					}
				}
			});
		});		
	};
})(jQuery);