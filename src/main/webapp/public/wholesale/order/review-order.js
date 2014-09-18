(function($){
	
	var ctx = $('#review_order_info_tmpl').attr('data-ctx')
	
	$(':radio, :checkbox').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue' });
	$('.selectpicker').selectpicker();
	
	$.get(ctx + '/order/review-order/loading', function(order){ //console.log(order);
		if (order != null && order.ods != null && order.ods.length > 0) {
			var details = order.ods;
			order.oneoff_details = [];
			order.monthly_details = [];
			for (var i = 0, len = details.length; i < len; i++) {
				var detail = details[i];
				if (detail.subscribe == 'one-off') {
					order.oneoff_details.push(detail);
				} else if (detail.subscribe == 'monthly') {
					order.monthly_details.push(detail);
				} else {
					order.monthly_details.push(detail);
				}
			}
		}
		order.ctx = ctx;
		$('#review-order-info').html(tmpl('review_order_info_tmpl', order));
	});

	
	/*$('#continue').click(function(){
		var order = {
			customer_type: $('input[name="customer_type"]:checked').val()
			, company_name: $('#company_name').val()
			, trade_name: $('#trade_name').val()
			, title: $('#title').val()
			, first_name: $('#first_name').val()
			, last_name: $('#last_name').val()
			, email: $('#email').val()
			, mobile: $('#mobile').val()
			, phone: $('#phone').val()
			, preferred_connection_date: preferred_connection_date
		};
		
		console.log(JSON.stringify(order));
		
		var url = ctx + '/order/fill-information/submit';
		var l = Ladda.create(this); l.start();
		$.ajax({
			type: 'post'
			, contentType:'application/json;charset=UTF-8'         
	   		, url: url
		   	, data: JSON.stringify(order)
		   	, dataType: 'json'
		   	, success: function(json){  console.log(json.model);
				if (!$.jsonValidation(json, 'right')) { 
					window.location.href = ctx + json.url;
				} 
		   	}
		}).always(function(){ l.stop(); });	
	});*/
	
})(jQuery);