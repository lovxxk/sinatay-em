tt.text = tt.bh.ext({
	h:function()
	{
		if (this.needHandle()) {
//			var s = tt.getI18S("<span id='{0}' class='{1}'>{2}</span>", [this.e[tt.Conf.proNameOfMsgId], tt.Conf.errCls, this.v.getI18(this.f.label)]);
//			var msgId = this.f.getMsgId(this.e);
//			if (msgId) {
//				tt.getById(msgId).innerHTML = s;
//			}else{
//				tt.insertAfter(this.e, s);
//			}
//			tt.addCls(this.e, tt.Conf.errInputCls);
			
			var isSelect = this.e.tagName == "SELECT";
			var isCheckbox = this.e.tagName == "INPUT" && this.e.type == 'checkbox';
			var isRadio = this.e.tagName == "INPUT" && this.e.type == 'radio';
			
			var span = document.createElement("span");
			span.id = this.e[tt.Conf.proNameOfMsgId];
			span.className = tt.Conf.errCls;
			span.innerHTML = this.v.getI18(this.f.label);
			
			var msgId = this.f.getMsgId(this.e);
			if (msgId) {
				tt.getById(msgId).appendChild(span);
			} else {
				if ((isCheckbox || isRadio)) {
					tt.moveToPos(span, this.f.es[this.f.es.length - 1]);
				} else {
					tt.moveToPos(span, this.e);
				}
				this.e.parentNode.insertBefore(span, this.e);
				tt.vf.msgs.push({"msg":span,"ele":this.e});
			}
			tt.addCls(this.e, tt.Conf.errInputCls);
			
		}
	}
});