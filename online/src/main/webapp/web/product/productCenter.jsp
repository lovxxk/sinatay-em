<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp ">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ʒѡ��</li>
			</ul>
		</div>
		<div class="product_main">
			<div class="left_dynamic">
				<jsp:include page="/web/product/common/product_map.jsp"></jsp:include>
				<jsp:include page="/web/product/common/product_commend.jsp"></jsp:include>
				<jsp:include page="/web/product/common/product_rank_left.jsp"></jsp:include>
				<div class="display"></div>
			</div>
			<div class="right_product">
				<div class="center_top">
					<div class="product_display">
						<a class="active" href="${ctx }/productsDisplay/onlineShop.do"><img src="${ctx }/resources/image/product/product_center_show1.jpg"></a>
						<a href="${ctx }/sale/toQuote.do?eid=G120130902152808046"><img src="${ctx }/resources/image/product/product_center_show2.jpg"></a>
						<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034"><img src="${ctx }/resources/image/product/product_center_show3.jpg"></a>
						<div class="display_nav">
							<div class="active"></div>
							<div></div>
							<div></div>
						</div>
					</div>
					<div class="product_quote">
						<div class="quote_tab">
							<div class="tab_item select">��ͨ����</div>
						</div>
						<div class="quote_main">
							<div class="quote_body traffic">
								<div class="input_field selector">
									<label class="input_label">��ͨ���ͣ�</label>
									<ul class="input_select">
										<li class="select" tag="type"><div>�ɻ�</div></li>
										<li tag="type"><div>��</div></li>
										<li tag="type"><div>����</div></li>
										<li tag="type"><div>�ִ�</div></li>
										<li tag="type"><div>�Լݳ�</div></li>
									</ul>
								</div>
								<div class="input_field bottom selector">
									<label class="input_label">�����ڼ䣺</label>
									<ul class="input_select">
										<li class="select" tag="day"><div>1����</div></li>
										<li tag="day"><div>6����</div></li>
										<li tag="day"><div>1��</div></li>
									</ul>
								</div>
								<div class="action">
									<input type="hidden" id="type" name="type" value="0" />
									<input type="hidden" id="day" name="day" value="0" />
									<button class="quote_btn click_btn">��������</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="sub_cate_name">������Ʒ</div>
				<div class="product_area product_list">
					<div class="product_list_main page1">
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=0&day=0"><img src="${ctx}/resources/image/product/product_center_icon1.jpg"/></a>
								<div class="product_info">
									<div class="product_title accident"><a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=0&day=0">������ݡ�˽�ҳ�����ר���ռƻ�����׼�棩</a></div>
									<div class="base_info"><span>���䣺18-50��</span><span>�����ڼ䣺30��</span><span>�������ڣ�5�ꡢ10��</span></div>
									<div class="product_instro">�г�һ��ر����գ�100000��˽�ҳ����Ĺ�ͬѡ�񣬽�ֹĿǰ���ۼ��ṩ1000�ڵı��ϡ�</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li>˽�ҳ�������ʣ�<span>100��</span></li>
										<li>��ͨ����������ʣ�<span>57.5��</span></li>
										<li>�������/�˲У�<span>7.5��</span></li>
										<li>һ����ʱ���</li>
										<li>�������汣�Ͻ�</li>
										<li></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">1127.5</span><span class="p2">Ԫ/����</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=0&day=0">�˽�����</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=1&day=0"><img src="${ctx}/resources/image/product/product_center_icon2.jpg"/></a>
								<div class="product_info">
									<div class="product_title accident"><a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=1&day=0">������ݡ�˽�ҳ�����ר���ռƻ��������棩</a></div>
									<div class="base_info"><span>���䣺18-50��</span><span>�����ڼ䣺30��</span><span>�������ڣ�5�ꡢ10��</span></div>
									<div class="product_instro">�г�һ��ر����գ�˽�ҳ�������߱��ϸߴ�200��100000��˽�ҳ����Ĺ�ͬѡ�񣬽�ֹĿǰ���ۼ��ṩ1000�ڵı��ϡ�</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li>˽�ҳ�������ʣ�<span>200��</span></li>
										<li>��ͨ����������ʣ�<span>115��</span></li>
										<li>һ����ʱ���</li>
										<li>�������/�˲У�<span>15��</span></li>
										<li>�������汣�Ͻ�</li>
										<li></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">2255</span><span class="p2">Ԫ/����</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=1&day=0">�˽�����</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034&type=0&day=0"><img src="${ctx}/resources/image/product/product_center_icon3.jpg"/></a>
								<div class="product_info">
									<div class="product_title finance"><a href="${ctx }/sale/toQuote.do?eid=G120130902152647034&type=0&day=0">������Ʊ���<span>�Ƚ��Զ���Ͷ����ѡ��</span></a></div>
									<div class="base_info"><span>���䣺18-64��</span><span>�����ȣ�500Ԫ��</span><span>������߹����19��</span></div>
									<div class="product_instro">�ȴ���������ߣ��ȹ���������С����͹�����500Ԫ���޳�ʼ�۷ѣ���������ѣ�������ȡ����ݣ�����������������ѡ��</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li>�����ڣ�<span>6����</span></li>
										<li>�����ڣ�<span>9����</span></li>
										<li>�����ڣ�<span>1��</span></li>
										<li>Ԥ�����棺<span>5.02%</span></li>
										<li>Ԥ�����棺<span>5.12%</span></li>
										<li>Ԥ�����棺<span>5.3%</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">500</span><span class="p2">Ԫ��</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152647034&type=0&day=0">�˽�����</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=0&day=0"><img src="${ctx}/resources/image/product/product_center_icon4.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=0&day=0">���������˺�����</a></div>
									<div class="base_info"><span>���䣺18-80��</span><span>��Ч���ڣ�������ѡ��</span></div>
									<div class="product_instro">�㻹�����ŵ���20Ԫ60�򺽿�����������̩����������7��100��ֻ��Ҫ7ԪǮ���бȽϲ��в��죬�Ͻ����ְɣ�</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">�����ڼ䣺<span> 1���� �� 6���� �� 1��</span></li>
										<li>���<span>50��100��</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">5</span><span class="p2">Ԫ��</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=0&day=0">�˽�����</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=1&day=0"><img src="${ctx}/resources/image/product/product_center_icon5.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=1&day=0">��������˺�����</a></div>
									<div class="base_info"><span>���䣺18-80��</span><span>��Ч���ڣ�������ѡ��</span></div>
									<div class="product_instro">�������𳵡��������������ڱ��Ϸ�Χ��������С��ȼ١�̽�ס����°࣬��һ�ݱ��ϣ���һ�ݰ��ģ�</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">�����ڼ䣺<span>1���� �� 6���� �� 1��</span></li>
										<li>���<span>10��50��</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">1.4</span><span class="p2">Ԫ��</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=1&day=0">�˽�����</a></div>
								</div>
							</div>
						</div>	
					</div>
					<div class="product_list_main page2">
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=4&day=0"><img src="${ctx}/resources/image/product/product_center_icon6.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=4&day=0">���ó������˺�����</a></div>
									<div class="base_info"><span>���䣺18-80��</span><span>��Ч���ڣ�������ѡ��</span></div>
									<div class="product_instro">���Ρ��ȼ١��Լ��Ρ�̽�ס����°࣬����ѡ�񣬼۸���������ֳ��У���ȫ��һ��</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">�����ڼ䣺<span>1���� �� 6���� �� 1��</span></li>
										<li>���<span>10��50��</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">12</span><span class="p2">Ԫ��</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=4&day=0">�˽�����</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=2&day=0"><img src="${ctx}/resources/image/product/product_center_icon7.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=2&day=0">�������������˺�����</a></div>
									<div class="base_info"><span>���䣺18-80��</span><span>��Ч���ڣ�������ѡ��</span></div>
									<div class="product_instro">�㻹�ڿ�������վ������һ��2Ԫ������2�� ˫������ķ��ޡ����ºͳ��ؼ�̽����ĸ��Т˳��Ů�ǣ��Ͻ�������ѡ��ɣ�</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">�����ڼ䣺<span> 1���� �� 6���� �� 1��</span></li>
										<li>���<span>10��50��</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">5</span><span class="p2">Ԫ��</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=2&day=0">�˽�����</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=3&day=0"><img src="${ctx}/resources/image/product/product_center_icon8.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=3&day=0">�ִ������˺�����</a></div>
									<div class="base_info"><span>���䣺18-80��</span><span>��Ч���ڣ�������ѡ��</span></div>
									<div class="product_instro">���Ƿ����ڼƻ�������Ǹ�ta��һ��̩̹��˵�����֮�ã�����󺣣���ԡ���磬���ܹ����쳾������ַ��飡���Ϸ��գ���̩������</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">�����ڼ䣺<span>1���� �� 6���� �� 1��</span></li>
										<li>���<span>10��50��</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">3</span><span class="p2">Ԫ��</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=3&day=0">�˽�����</a></div>
								</div>
							</div>
						</div>
					</div>
					<div class="page_index">
						<div class="page prev_page" title="1"><a>��һҳ</a></div>
						<div class="page page_num now" title="1"><a>1</a></div>
						<div class="page page_num" title="2"><a>2</a></div>
						<div class="page next_page" title="2"><a>��һҳ</a></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>