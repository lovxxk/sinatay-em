tt.SCV = tt.NRV.ext({
	init:function(){
		this._super();
		this.needAddNumV = false;
	},
	v:function(s, j, es, f)
	{
		var isSelect = es[j].tagName == "SELECT";
		var isCheckbox = es[j].tagName == "INPUT" && es[j].type == 'checkbox';
		var isRadio = es[j].tagName == "INPUT" && es[j].type == 'radio';
		
//		if ((isCheckbox || isRadio) &&  (j < es.length - 1)){
//			return true;
//		}
		
		var count = tt.getSelectedCount(j, es);
		var r = this._super(count+"");
		if(!this.exp){
			v = count - this.max;
			if (v > 0) {  //选 多了
				this.i18n = tt.i18SelectCountMin;
				this.i18ps[0] = this.max;
				this.i18ps[1] = v;
			} else if ((v = this.min - count) > 0){ //少选了
				this.i18n = tt.i18SelectCount;
				if (this.min == 1){
					this.i18n = tt.i18SelectCount_1;
				}
				
				this.i18ps[0] = this.min;
				this.i18ps[1] = v;
			}
		} else {
			this.i18n = tt.i18SelectCountExp;
			this.i18ps[0] = this.exp;
		}
		return r;
	}
});