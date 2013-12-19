
tt.Conf = {
	ver: '2.1.0',
	
	errorStyle : 'text',
	tipStyle : 'tip',
	
	clearOtherError : true, // 当验证某一元素时，是否隐藏其它字段的错误提示。true 隐藏其它字段的错误提示
	validateOn : ['keyup', 'focus', 'blur', 'change'], // 触发验证的事件类型。 
	
	clrSpace : true,  //是否清空输入值两端的空格
	
	proNameOfMsgId : 'ttTalentMsgId', //  tt.Conf.proNameOfMsgId
	proNameOfReqStarId : 'ttTalentReqStarId', //  tt.Conf.proNameOfReqStarId
	
	eventId : 'talentEventId', // tt.Conf.eventId
	
	
	errCls: "talentErrMsg",     //错误提示信息的样式
	tipCls: "talentTipMsg",     //错误提示信息的样式
	errInputCls: "talentErrInput",  //验证不通过时，输入框的样式
	reqStarCls: "talentReqStar"  // 必须输入项后面紧跟着一个星号的样式
};
