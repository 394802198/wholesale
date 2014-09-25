(function($){
	
	var _tmpl = $('#information_content_tmpl')
		, ctx = _tmpl.attr('data-ctx')
		, address = _tmpl.attr('data-address')
	
	$.get(ctx + '/order/information/loading', function(order){ console.log(order);
		
		order.ctx = ctx;
		order.address = address;
		$('#information-content').html(tmpl('information_content_tmpl', order));
		$(':radio, :checkbox').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue' });
		$('.selectpicker').selectpicker();
		
		var cal = $('#sandbox-container div')
			, preferred_connection_date = null
			, input_connection_date = $('input[name="preferred_connection_date"]')
		
		cal.datepicker({
		    format: "dd/mm/yyyy",
		    startDate: '+7d',
		    daysOfWeekDisabled: "0,6",
		    todayHighlight: true,
		}).on('changeDate', function(e){
			input_connection_date.iCheck('uncheck');
			preferred_connection_date = e.format();
		});
		
		input_connection_date.on('ifChecked', function(){
			cal.datepicker('update', null);
			preferred_connection_date = null;
		});
		
		
		$('#continue').click(function(){
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
				, preferred_connection_date_str: preferred_connection_date
				
				, transition_porting_number: $('#transition_porting_number').val()
				, transition_provider_name: $('#transition_provider_name').val()
				, transition_account_holder_name: $('#transition_account_holder_name').val()
				, transition_account_number: $('#transition_account_number').val()
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
			
		});
		
	});
	
})(jQuery);