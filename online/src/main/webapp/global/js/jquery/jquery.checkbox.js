


(function($) {  
	
	var skinFloder = 'checkbox_skin',
		jsPath = '';
	
	var skinList = [
			{name:"default",charset:"utf-8"},
			{name:"biggreen",charset:"utf-8"},
			{name:"lightblue",charset:"utf-8"},
			{name:"sinatay",charset:"utf-8"},
			{name:"orangebox",charset:"utf-8"}
	];
	
	var cssName = {
			uncheck_backCssName: 'checkbox_uncheck',
		    check_backCssName: 'checkbox_check',
		    checkedCssName: 'checked',
		    uncheckedCssName: 'unchecked',
		    checkHoverCssName: 'check_hover'
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
		var href = skinFloder+'/'+name;
		
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
		for(var i=0 ; i < skinList.length ; i++){
			if(skinList[i].name == skinName ){
				return skinList[i];
			}
		}
		return undefined;
	}
	
	jsPath = getJsPath('jquery.checkbox.js');
	
	$.fn.jCheckBox= function(setting) {
		var config = $.extend({
		    valueTo:$(document.body),
		    enable: true,
		    useCss: false,
		    label:'',
		    property:'',
		    skin:'sinatay',
		    onCheckChanged:null,
		    onBeforeChange:function(name,isCheck){return true;},
		    onChange:null
		}, setting);
		
		//加入皮肤样式
		var skin = getSkin(config.skin);
		if(skin){
			addstylesheet(skin.name+'/checkbox.css',skin.charset,jsPath);
		}
		
		var ischecked = false;
		var isenable = config.enable;
		
		config.renderTo = $(this);
		config.valueTo = (typeof config.valueTo == 'string' ? $(config.valueTo) : config.valueTo);
		
		config.renderTo.addClass('jcheckbox');
		config.valueTo.attr("value",ischecked);
		
		var checkBox = $('<div><div></div></div>');
		
		/**
		 * boxCheck([val])
		 * 	val:设置checkbox是否选中
		 * 
		 * 	如果未带参数，则返回当前选中状态
		 */
		var boxCheck = function(check){
			
			if((typeof check == 'undefined')){
				return ischecked;
			}
			if(!check){
				checkBox.removeClass().addClass(cssName.uncheck_backCssName);
				checked.removeClass().addClass(cssName.uncheckedCssName);
					
				check = false;
			}else{
				checkBox.removeClass().addClass(cssName.check_backCssName);
				checked.removeClass().addClass(cssName.checkedCssName);
				
				check = true;
			}
			config.valueTo.attr("value",check);
			ischecked = check;
			
			if(config.onChange){
				config.onChange(config.label,ischecked);
			}
		};
		
		/**
		 * enable([val])
		 * 	val:设置checkbox是否激活
		 * 
		 * 	如果未带参数，则返回当前激活状态
		 */
		var enable = function(enable){
			if((typeof enable == 'undefined')){
				return isenable;
			}
			
			isenable = enable;
			
			if(isenable){
				checked.css({	
					'cursor': 'pointer'
				});
			}else{
				checked.css({	
					'cursor': 'default'
				});
			}
		};
		
		
		var setBackground = function(style){
			checkBox.css('background',style);
		};
		
		checkBox.removeClass().addClass(cssName.uncheck_backCssName);
		
		checkBox.appendTo(config.renderTo);		
						
		var checked = checkBox.find('div:eq(0)');
		
		checked.removeClass().addClass(cssName.uncheckedCssName);
		
		checkBox.bind('click',function(e){
			
			if(!isenable){
				return;
			}
			
			if(config.onBeforeChange){
				if(!config.onBeforeChange(config.label,ischecked)){
					return;
				}
			}
			boxCheck(!ischecked);
			
			if(config.onCheckChanged){
				config.onCheckChanged(config.label,ischecked);
			}
		});
		
		checkBox.bind('mouseover',function(e){
			
			if(!isenable){
				return;
			}
			
			checkBox.removeClass().addClass(cssName.check_backCssName);
		});
		
		checkBox.bind('mouseleave',function(e){
			
			if(!isenable){
				return;
			}
			
			if(!ischecked)
			{
				checkBox.removeClass().addClass(cssName.uncheck_backCssName);
			}
		});
		
		return {
			check:boxCheck,
			enable:enable,
			setBackground:setBackground,
			getLabel:function(){return config.label;},
			getProperty:function(){return config.property;}
		};
	};
})(jQuery);
