(function($){  
	
	$.sinosoft = $.sinosoft || {version: '@VERSION'};
	
	var tool = tool = $.sinosoft.select = {
		conf: {
			anim:true,
			animTime:200,
			disable : false,
			options:[
			         
			],
			valName: 'val',
			onSelect:function(){},
			css:{
				input:'selector',
				text:'text',
				list:'list',
				item:'list-item',
				last_item:'last',
				hover:'hover',
				disable:'disabled'
			},
			onSelect:function(){}
		} 		
	};
	
	var border = ['border-top-width','border-top-color','border-top-style',
	              'border-left-width','border-left-color','border-left-style',
	              'border-bottom-width','border-bottom-color','border-bottom-style',
	              'border-right-width','border-right-color','border-right-style'
	              ],
		padding = ['padding-left','padding-top','padding-right','padding-bottom'],
		margin = ['margin-left','margin-top','margin-right','margin-bottom'],
		font = ['font-size','font-family','font-variant','font-weight','font-style'];
	
	
	function getUppercase(str){
		var index = 0;
		while((index = str.indexOf('-')) != -1){
			var char = str.charAt(index+1);
			
			var _str1 = str.substring(0,index);
			var _str2 = str.substr(index+2);
			str = _str1 + char.toUpperCase() + _str2;
		}
		return str;
	}
	
	function getCss(style,src,obj){
		for ( var i = 0; i < style.length; i++) {
			obj[getUppercase(style[i])] = src.css(style[i]);
		}
	}
	
	function buildOptions(list,items,css){
		var txt = '<div></div>';

		for(var i = 0; i < items.length ; i++){
			var item = $(txt).addClass(css.item);
			item.text(items[i].name);
			item.attr(tool.conf.valName,items[i].value);
			
			if(i == items.length - 1){
				item.addClass(css.last_item);
			}
			list.append(item);
		}
	}
	
	function getNum(data,unit){
		if(!unit){
			unit = 'px';
		}
		return parseInt(data.replace(unit,''));
	}
	
	function cloneOuterStyle(src,dst){
		var width = getNum(src.css('width')) +getNum(src.css('padding-left'))+getNum(src.css('padding-right'))+getNum(src.css('border-left-width'))+getNum(src.css('border-right-width'));
		var style = {
				width:width,
				float:src.css('float'),
				display:src.css('display')
		};
		getCss(margin,src,style);
		dst.css(style);
	}
	
	function cloneInnerStyle(src,dst){
		var style = {
				height:src.css('height')
		};
		getCss(border,src,style);
		getCss(padding,src,style);
		dst.css(style);
	}
	
	function cloneTextStyle(src,dst){
		
		var style = {
				lineHeight:src.css('height')
		};
		getCss(font,src,style);
		dst.css(style);
	}
	
	function calcListPosition(src,dst){
		var top = getNum(src.css('height'))+getNum(src.css('padding-top'))+getNum(src.css('padding-bottom'))+getNum(src.css('border-top-width'))+getNum(src.css('border-bottom-width')),
			width = getNum(src.css('width')) +getNum(src.css('padding-left'))+getNum(src.css('padding-right'));
		if(width < 80){
			width = 80;
		}
		dst.css({
			top:top-1,
			minWidth:width,
			width:width
		});
	}
	
	function JSelect(input ,conf){
		var self = this,  
			css = conf.css, 
			options = conf.options,
			root = $("<div><div><div></div><b></b></div><div></div></div>").data("jSelect", self),
			value,
			open = false,
			disable = conf.disable,
			border_temp;
		
		// create range	 
		input.before(root);	
		var text = root.addClass(css.input).find('>div').eq(0),
			text_input = text.find('div'),
			text_icon = text.find('b'),
			list = root.find('>div').eq(1);
		
		if(disable){
			root.addClass(css.disable);
		}
		
		text.addClass(css.text);
		
		text.css('padding-right','17px');
		
		list.addClass(css.list);
		
		buildOptions(list,options,css);
		
		function openSelect(){
			calcListPosition(text,list);
			var height = getNum(text.css('height'));
			text.css({
				height:height+1,
				borderBottomWidth:0
			});
			text.addClass(css.hover);
			
			function openEnd(){
				open = true;
			}
			
			if(conf.anim){
				list.show(conf.animTime,function(){
					openEnd();
				});
			}else{
				list.show();
				openEnd();
			}
		}
		
		function closeSelect(){
			
			function closeEnd(){
				var height = getNum(text.css('height'));
				text.css({
					height:height-1,
					borderBottomWidth:border_temp
				});
				text.removeClass(css.hover);
				open = false;
			}
			
			if(conf.anim){
				list.hide(conf.animTime,function(){
					closeEnd();
				});
			}else{
				list.hide();
				closeEnd();
			}
		}
		
		
		function select(item){
			select_by_value(item.text(),item.attr(conf.valName));
		}
		
		function select_by_option(option){
			select_by_value(option.name,option.value);
		}
		
		function select_by_value(name,value){
			text_input.text(name);
			input.val(value);
			input.trigger("change");
			if(conf.onSelect){
				conf.onSelect(self);
			}
		}
		
		root.click(function(){
			if(!open && !disable){
				openSelect();
			}
		});
		
		list.find('.'+css.item).click(function(){
			select($(this));
		});
		
		$('body').click(function(){
			if(open){
				closeSelect();
			}
		});
		
		
		cloneOuterStyle(input,root);
		cloneInnerStyle(input,text);
		cloneTextStyle(input,text_input);
		border_temp = text.css('border-bottom-width');
		input.hide();
		
		root.css('z-index',1000-input.index('body input'));
		
//		$(window).load(function(){
//			cloneOuterStyle(input,root);
//			cloneInnerStyle(input,text);
//			cloneTextStyle(input,text_input);
//			border_temp = text.css('border-bottom-width');
//			input.hide();
//		});
		
		$.extend(self, {  
			getValue: function() {
				return input.val();	
			},
			getName: function(){
				return text_input.text();
			},
			setValue: function(val, e) {
				for(var i = 0 ; i < conf.options.length ; i++){
					if(val == conf.options[i].value){
						select_by_option(conf.options[i]);
					}
				}
			}, 			  
			getOpen: function() {
				return open;
			},
			getConf: function() {
				return conf;	
			},
			getInput: function() {
				return input;	
			},
			update: function(update_options) {
				conf.options = update_options;
				list.empty();
				buildOptions(list,update_options,css);
				
				list.find('.'+css.item).click(function(){
					select($(this));
				});
			},
			clear: function(){
				text_input.text('');
				input.val('');
			},
			disable:function(){
				root.addClass(css.disable);
				disable = true;
			},
			enable:function(){
				root.removeClass(css.disable);
				disable = false;
			},
			setLayout:function(zIndex){
				root.css('z-index',zIndex);
			}
		});
	}
	
	// jQuery plugin implementation
	$.fn.jSelect = function(conf) {

		// already installed
		if (this.data("jSelect")) { return this; } 
		// extend configuration with globals
		conf = $.extend(true, {}, tool.conf, conf);		
		
		var els;
		
		this.each(function() {				
			var el = new JSelect($(this), $.extend(true, {}, conf));		 
			var input = el.getInput().data("jSelect", el);
			els = els ? els.add(input) : input;	
		});		
		
		return els ? els : this; 
	};	
})(jQuery);


/**
 * 日期控件年份
 * @param num 数值（1-100）
 * @param flag 1：从当前年份往前数num年，2：从当前年份往后数num年。
 * @returns year_opts[] 返回对象格式：[{name:'1945',value:'1945'},{name:'1946',value:'1946'}...]
 */
function getYearOfnum(num, flag){
	var nowYear = new Date().getFullYear();
	var year_opts = [];
	if(flag == 1){
		var oldYear = nowYear-num;
		for(var i=oldYear;i<=nowYear;i++){
			year_opts.push({name:i,value:i});
		}
	}else if(flag == 2){
		var oldYear = nowYear+num;
		for(var i=nowYear;i<=oldYear;i++){
			year_opts.push({name:i,value:i});
		}
	}
	return year_opts;
}

/**
 * 日期范围控制年份
 * @param min 最小日期
 * @param max 最大日期
 * @param flag 
 * @returns {Array}
 */
function getYearOfscope(min, max, flag){
//	console.log("getYearOfnum()...");
//	console.log("min: "+min+", max: "+max);
	var minY,maxY;
	if(min != null && min != '' && min.indexOf("-") != -1){
		minY = min.split("-")[0];
	}
	if(max != null && max != '' && max.indexOf("-") != -1){
		maxY = max.split("-")[0];
	}
	var minDate = new Date();
	var maxDate = new Date();
	minDate.setFullYear(minY);
	maxDate.setFullYear(maxY);
	var minYear = minDate.getFullYear();
	var maxYear = maxDate.getFullYear();
//	console.log("minDate: "+minDate+", maxDate: "+maxDate);
//	console.log("minYear: "+minYear+", maxYear: "+maxYear);
	
	var year_opts = [];
	for(var i=minYear;i<=maxYear;i++){
		year_opts.push({name:i,value:i});
	}
	return year_opts;
}