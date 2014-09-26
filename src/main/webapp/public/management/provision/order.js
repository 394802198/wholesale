(function($){

	/**
	 * BEGIN ORDER INFO TAB
	 */
	$.getOrderInfo = function() {
		
		var data = {
			'id':o_id
		};
		
		$.get(ctx+'/management/provision/order/info', data, function(json){
			
			/**
			 * BEGIN LOAD DATA TO TEMPLATE
			 */
			json.ctx = ctx;
	   		var $table = $('#order_info');
			$table.html(tmpl('order_info_table_tmpl', json));
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
			
			// Order rfs Datepicker
			var order_rfs_input = $('input[name="order_rfs_date"]').attr('data-val');
			$('#order_rfs_datepicker').datepicker({
			    format: "yyyy-mm-dd",
			    autoclose: true,
			    todayHighlight: true
			    
			}).datepicker('setDate', order_rfs_input || new Date());
			
			// Order inservice Datepicker
			var order_inservice_input = $('input[name="order_inservice_date"]').attr('data-val');
			$('#order_inservice_datepicker').datepicker({
			    format: "yyyy-mm-dd",
			    autoclose: true,
			    todayHighlight: true
			    
			}).datepicker('setDate', order_inservice_input || new Date());
			
			// Order next invoice Datepicker
			var order_next_invoice_input = $('input[name="order_next_invoice_create_date"]').attr('data-val');
			$('#order_next_invoice_create_datepicker').datepicker({
			    format: "yyyy-mm-dd",
			    autoclose: true,
			    todayHighlight: true
			    
			}).datepicker('setDate', order_next_invoice_input || new Date());
			
			/**
			 * END LOAD DATA TO TEMPLATE
			 */
			
			/**
			 * BEGIN PREPARE FOR USE
			 */
			var LaddaThis;
			var isRefresh;
			/**
			 * END PREPARE FOR USE
			 */
			
			
			/**
			 * BEGIN ORDER CUSTOMER INFO AREA
			 */
			$('a[data-name="update_order_customer_info_btn"]').click(function(){
				var l = Ladda.create(this); l.start();
				LaddaThis = l;
				$('#updateCustomerInfoModal').modal('show');
			});
			
			$('#update_customer_info_modal_btn').click(function(){
				var customer_title = $('select[name="order_customer_title"]').val();
				var customer_type = $('select[name="order_customer_type"]').val();
				var company_name = $('input[name="order_customer_company_name"]').val();
				var trade_name = $('input[name="order_customer_trade_name"]').val();
				var title = $('select[name="order_customer_title"]').val();
				var first_name = $('#order_customer_first_name').val();
				var last_name = $('#order_customer_last_name').val();
				var phone = $('#order_customer_phone').val();
				var mobile = $('#order_customer_mobile').val();
				var email = $('#order_customer_email').val();
				var address = $('#order_customer_address').val();
				
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
				
				$.post(ctx+'/management/provision/order/customer-info/update', data, function(json){
					isRefresh = !$.jsonValidation(json, 'right');
				}, "json");
			});
			
			$('#updateCustomerInfoModal').on('hidden.bs.modal', function (e) {
				
				if(isRefresh){
					$.getOrderInfo();
				} else {
					LaddaThis.stop();
				}
			});
			/**
			 * END ORDER CUSTOMER INFO AREA
			 */
			
			
			/**
			 * BEGIN ORDER STATUS AREA
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
				
				$.post(ctx+'/management/provision/order/status/update', data, function(json){
			   		$.jsonValidation(json, 'right');
				}, "json");
			});
			$('#updateOrderStatusModal').on('hidden.bs.modal', function (e) {
				$.getOrderInfo();
			});
			/**
			 * END ORDER STATUS AREA
			 */
			 
			/**
			 * BEGIN PPPOE INFO AREA
			 */
			$('a[data-name="update_order_pppoe_btn"]').click(function(){
				var l = Ladda.create(this); l.start();
				LaddaThis = l;
				$('#updateOrderPPPoEModal').modal('show');
			});
			$('#update_order_pppoe_modal_btn').click(function(){
				var pppoe_loginname = $('#pppoe_loginname').val();
				var pppoe_password = $('#pppoe_password').val();
				
				var data = {
					'id':o_id,
					'pppoe_loginname':pppoe_loginname,
					'pppoe_password':pppoe_password
				};
				
				$.post(ctx+'/management/provision/order/pppoe/update', data, function(json){
					isRefresh = !$.jsonValidation(json, 'right');
				}, "json");
			});
			$('#updateOrderPPPoEModal').on('hidden.bs.modal', function (e) {
				
				if(isRefresh){
					$.getOrderInfo();
				} else {
					LaddaThis.stop();
				}
			});
			/**
			 * END PPPOE INFO AREA
			 */
			 
			/**
			 * BEGIN ORDER SV/CVLAN AREA
			 */
			$('a[data-name="update_order_svcvlan_btn"]').click(function(){
				var l = Ladda.create(this); l.start();
				LaddaThis = l;
				$('#updateOrderSVCVLanModal').modal('show');
			});
			$('#update_order_svcvlan_modal_btn').click(function(){
				var svlan = $('#svlan').val();
				var cvlan = $('#cvlan').val();
				var rfs_date_str = $('input[name="order_rfs_date"]').val();
				
				var data = {
					'id':o_id,
					'svlan':svlan,
					'cvlan':cvlan,
					'rfs_date_str':rfs_date_str
				};
				
				$.post(ctx+'/management/provision/order/svcvlan/update', data, function(json){
					isRefresh = !$.jsonValidation(json, 'right');
				}, "json");
			});
			$('#updateOrderSVCVLanModal').on('hidden.bs.modal', function (e) {
				
				if(isRefresh){
					$.getOrderInfo();
				} else {
					LaddaThis.stop();
				}
			});
			/**
			 * END ORDER SV/CVLAN AREA
			 */
			 
			/**
			 * BEGIN ORDER SERVICE GIVING AREA
			 */
			$('a[data-name="update_order_inservice_btn"]').click(function(){
				var l = Ladda.create(this); l.start();
				LaddaThis = l;
				$('#updateOrderInServiceModal').modal('show');
			});
			$('#update_order_in_service_modal_btn').click(function(){
				var inservice_date_str = $('input[name="order_inservice_date"]').val();
				
				var data = {
					'id':o_id,
					'inservice_date_str':inservice_date_str
				};
				
				$.post(ctx+'/management/provision/order/service-giving/update', data, function(json){
					isRefresh = !$.jsonValidation(json, 'right');
				}, "json");
			});
			$('#updateOrderInServiceModal').on('hidden.bs.modal', function (e) {
				
				if(isRefresh){
					$.getOrderInfo();
				} else {
					LaddaThis.stop();
				}
			});
			/**
			 * END ORDER SERVICE GIVING AREA
			 */
			 
			/**
			 * BEGIN ORDER BROADBAND ASID AREA
			 */
			$('a[data-name="order_brodband_asid_btn"]').click(function(){
				var l = Ladda.create(this); l.start();
				LaddaThis = l;
				$('#updateOrderBroadbandASIDModal').modal('show');
			});
			$('#update_order_broadband_asid_modal_btn').click(function(){
				var broadband_asid = $('#order_broadband_asid').val();
				
				var data = {
					'id':o_id,
					'broadband_asid':broadband_asid
				};
				
				$.post(ctx+'/management/provision/order/broadband-asid/update', data, function(json){
					isRefresh = !$.jsonValidation(json, 'right');
				}, "json");
			});
			$('#updateOrderBroadbandASIDModal').on('hidden.bs.modal', function (e) {
				
				if(isRefresh){
					$.getOrderInfo();
				} else {
					LaddaThis.stop();
				}
			});
			/**
			 * END ORDER BROADBAND ASID AREA
			 */
			
			
		}, "json");
	}
	/**
	 * END ORDER INFO TAB
	 */
	
	$.getOrderInfo();
	
})(jQuery);