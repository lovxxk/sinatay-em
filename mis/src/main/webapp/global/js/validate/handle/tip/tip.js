tt.tip = tt.bh.ext({
	h:function()
	{
		var tipMsg = this.v.getTip(this.e,this.f,this.v,this.val);
		if (this.needHandle() && tt.vf.showTip && tipMsg) {
//			var s = tt.getI18S("<span id='{0}' class='{1}'>{2}</span>", [this.e[tt.Conf.proNameOfMsgId], tt.Conf.tipCls, tipMsg]);
//			var msgId = this.f.getMsgId(this.e);
//			if (msgId) {
//				tt.getById(msgId).innerHTML = s;
//			}else{
//				tt.insertAfter(this.e, s);
//			}
			var isSelect = this.e.tagName == "SELECT";
			var isCheckbox = this.e.tagName == "INPUT" && this.e.type == 'checkbox';
			var isRadio = this.e.tagName == "INPUT" && this.e.type == 'radio';
			
			var span = document.createElement("span");
			span.id = this.e[tt.Conf.proNameOfMsgId];
			span.className = tt.Conf.tipCls;
			span.innerHTML = tipMsg;
			
			var msgId = this.f.getMsgId(this.e);
			if (msgId) {
				//var s = tt.getI18S("<span id='{0}' class='{1}'>{2}</span>", [this.e[tt.Conf.proNameOfMsgId], tt.Conf.tipCls, tipMsg]);
				tt.getById(msgId).appendChild(span);
			}else{
				
				if ((isCheckbox || isRadio)) {
					tt.moveToPos(span, this.f.es[this.f.es.length - 1]);
				} else {
					tt.moveToPos(span, this.e);
				}
				this.e.parentNode.insertBefore(span, this.e);
				tt.vf.msgs.push({"msg":span, "ele":this.e});
			}
		}
	}
});