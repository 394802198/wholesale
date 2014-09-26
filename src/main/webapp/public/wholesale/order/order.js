(function($){
	
	var _tmpl = $('#order_information_tmpl')
		, ctx = _tmpl.attr('data-ctx')
		, orderid = _tmpl.attr('data-orderid')
		
	$.get(ctx + '/order/edit/' + orderid + '/query', function(order) {
		order.ctx = ctx;
		$('#order-information').html(tmpl('order_information_tmpl', order));
	});
		
})(jQuery);