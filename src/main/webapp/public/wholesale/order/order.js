(function($){
	
	var _tmpl = $('#order_information_tmpl')
		, ctx = _tmpl.attr('data-ctx')
		, orderid = _tmpl.attr('data-orderid')
		
	$.get(ctx + '/order/edit/' + orderid + '/query', function(order) { //console.log(order);
		order.oneoff_details = [];
		order.monthly_details = [];
		$.each(order.ods, function(){
			if (this.subscribe == 'one-off') {
				order.oneoff_details.push(this);
			} else if (this.subscribe == 'monthly') {
				order.monthly_details.push(this);
			} else {
				order.monthly_details.push(this);
			}
		});
		order.ctx = ctx;
		$('#order-information').html(tmpl('order_information_tmpl', order));
	});
	
	$.get(ctx + '/order/edit/orderlog/loading/' + orderid, function(ols){
		var o = {
			ctx: ctx
			, ols: ols
		};
		$('#order-log').html(tmpl('order_log_tmpl', o));
	});
		
})(jQuery);