(function($){

	/**
	 * BEGIN ORDER DETAIL TAB
	 */
	$.getOrderDetail = function() {
		
		var data = {
			'id':o_id
		};
		
		$.get(ctx+'/management/provision/order/detail', data, function(json){
			
			/**
			 * BEGIN LOAD DATA TO TEMPLATE
			 */
			json.ctx = ctx;
	   		var $table = $('#order_detail');
			$table.html(tmpl('order_detail_table_tmpl', json));
			$table.find('tfoot a').click(function(){
				$.getOrderDetail($(this).attr('data-pageNo'));
			});
			
			$('.input-group.date').datepicker({
			    format: "yyyy-mm-dd",
			    autoclose: true,
			    todayHighlight: true
			});
			
			$('.selectpicker').selectpicker();
			
			// Order disconnected Datepicker
			var order_disconnected_input = $('input[name="order_disconnected_date"]').attr('data-val');
			$('#order_disconnected_datepicker').datepicker({
			    format: "yyyy-mm-dd",
			    autoclose: true,
			    todayHighlight: true
			    
			}).datepicker('setDate', order_disconnected_input || new Date());
			
			/**
			 * END LOAD DATA TO TEMPLATE
			 */
			
			
			/**
			 * BEGIN CUSTOMER DETAIL AREA
			 */
			$('a[data-name="update_order_customer_detail_btn"]').click(function(){
				var l = Ladda.create(this); l.start();
				$('#updateCustomerDetailModal').modal('show');
			});
			
			$('#update_customer_detail_modal_btn').click(function(){
				var customer_title = $('select[name="order_customer_title"]').val();
				var customer_type = $('select[name="order_customer_type"]').val();
				var company_name = $('input[name="order_customer_company_name"]').val();
				var trade_name = $('input[name="order_customer_trade_name"]').val();
				var title = $('select[name="order_customer_title"]').val();
				var first_name = $('input[name="order_customer_first_name"]').val();
				var last_name = $('input[name="order_customer_last_name"]').val();
				var phone = $('input[name="order_customer_phone"]').val();
				var mobile = $('input[name="order_customer_mobile"]').val();
				var email = $('input[name="order_customer_email"]').val();
				var address = $('input[name="order_customer_address"]').val();
				
				var data = {
					'id':o_id,
					'customer_title':customer_title,
					'customer_type':customer_type,
					'title':title,
					'first_name':first_name,
					'last_name':last_name,
					'phone':phone,
					'mobile':mobile,
					'email':email,
					'address':address
				};
				if(customer_type=='business'){
					data.company_name = company_name;
					data.trade_name = trade_name;
				} else {
					data.company_name = '';
					data.trade_name = '';
				}
				
				$.post(ctx+'/management/provision/order/customer-detail/update', data, function(json){
			   		$.jsonValidation(json, 'right');
				}, "json");
			});
			
			$('#updateCustomerDetailModal').on('hidden.bs.modal', function (e) {
				$.getOrderDetail();
			});
			/**
			 * END CUSTOMER DETAIL AREA
			 */
			
			
			/**
			 * BEGIN ORDER DETAIL AREA
			 */
			$('a[data-name="update_order_status_btn"]').click(function(){
				var l = Ladda.create(this); l.start();
				$('#updateOrderStatusModal').modal('show');
			});
			$('#update_order_status_modal_btn').click(function(){
				var status = $('select[name="order_status"]').val();
				var disconnected_date_str = $('input[name="order_disconnected_date"]').val();
				
				var data = {
					'id':o_id,
					'status':status,
					'disconnected_date_str':disconnected_date_str
				};
				
				console.log(data);
				
				$.post(ctx+'/management/provision/order/status/update', data, function(json){
			   		$.jsonValidation(json, 'right');
				}, "json");
			});
			$('#updateOrderStatusModal').on('hidden.bs.modal', function (e) {
				$.getOrderDetail();
			});
			/**
			 * END ORDER DETAIL AREA
			 */
			
			
		}, "json");
	}
	/**
	 * END ORDER DETAIL TAB
	 */
	
	$.getOrderDetail();
	
})(jQuery);