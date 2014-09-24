(function($){
	
	var ctx = $('#review_order_info_tmpl').attr('data-ctx')
	
	$(':radio, :checkbox').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue' });
	$('.selectpicker').selectpicker();
	
	$.get(ctx + '/order/review-order/loading', function(order){ //console.log(order);
		var details = order.ods;
		order.oneoff_details = [];
		order.monthly_details = [];
		$.each(details, function(){
			if (this.subscribe == 'one-off') {
				order.oneoff_details.push(this);
			} else if (this.subscribe == 'monthly') {
				order.monthly_details.push(this);
			} else {
				order.monthly_details.push(this);
			}
		});
		order.ctx = ctx;
		$('#review-order-info').html(tmpl('review_order_info_tmpl', order));
	});
	
})(jQuery);