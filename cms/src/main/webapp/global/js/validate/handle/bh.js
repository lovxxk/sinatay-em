/**
 * baseHandler
 */
tt.bh = tt.C.ext({
	setV:function(v)
	{
		this.v = v;
		return this;
	},
	setE:function(e)
	{
		this.e = e;
		return this;
	},
	setF:function(f)
	{
		this.f = f;
		return this;
	},
	setVal:function(val)
	{
		this.val = val;
		return this;
	},
	setIndex:function(index)
	{
		this.index = index;
		return this;
	},
	needHandle:function()
	{
		if (this.e.style.display == 'none' || this.e.disabled)//对于不可见的元素,不处理
        {
            return false;
        }
        return true;
	}
});