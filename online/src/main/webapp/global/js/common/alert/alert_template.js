function loadSubscribe(){//����ҳ�����ݷ���-��Ҫ��������css����ҳ����ʽ
	var success = $('<div class="subscribe">'
			+ '<div class="success"></div>'
			+ '<div class="main_content"><p class="main_txt">���ĳɹ�</p><p class="sub_txt">��л��������̩���յĵ��Ӻ�������</p></div>'
			+ '</div>');
	return success;
}

var alert = new Sinosoft.InteractiveDialog({
	titleStr:'������ʾ����',//������Ϸ�����-Ĭ��Ϊ��
	layout : loadSubscribe(),//���������ҳ��
	okStr:'ȷ��',//ȷ����ť��ʾ����
	cancelStr:'ȡ��',//ȡ����ť��ʾ����
	width:510,//�Զ��������-�������������
	okBtnShow:false,//�Ƿ���ʾȷ����ť
	cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
	closeIconShow:true,//�Ƿ���ʾ�ر�ͼ��
	okFunc:function(){
		//���ȷ����ťִ�з���
		//������falseʱ������ֹ���ر�
	},
	cancelFunc:function(){
		//���ȡ����ťִ�з���
		//����ر�ͼ��ִ�з���
		//������falseʱ������ֹ���ر�
	}
});
alert.open();//���Զ���ҳ����ʾ��
alert.close();//�ر��Զ���ҳ����ʾ��

Sinosoft.alert({
	titleStr:'������ʾ����',//������Ϸ�����-Ĭ��Ϊ��
	contentStr: '������ı�����������ı�����Ϣ',//����ʾ��Ϣ
	subContentStr:'���Ļ�����Ϣ��������',//����ʾ��Ϣ-���Ϊ�գ�����Ϣ������
	width:510,//�Զ��������-�������������
	okStr: 'ȷ��',//ȷ����ť��ʾ����
	cancelStr: 'ȡ��',//ȡ����ť��ʾ����
	okBtnShow:false,//�Ƿ���ʾȷ����ť
	cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
	closeIconShow:true,//�Ƿ���ʾ�ر�ͼ��
	okFunc:function(){
		//���ȷ����ťִ�з���
		//������falseʱ������ֹ���ر�
	},
	cancelFunc:function(){
		//���ȡ����ťִ�з���
		//����ر�ͼ��ִ�з���
		//������falseʱ������ֹ���ر�
	}
});


var test;
var loading = new Sinosoft.LoadingDialog({
	contentStr: '��ȴ��˱����',
	titleStr:'test',
	okStr:'',
	noCancel: true,
	closeFunc:function(){
		
	},
	waitFunc:function(){
		return test;
	}
});
loading.open();

setTimeout(function(){//���ֹرշ���
//	loading.close(); //�ֶ�����close����
	test = true;//��waitFunc���������ñ�־����
}, 2000);