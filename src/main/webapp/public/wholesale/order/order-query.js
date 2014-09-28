(function($){
	
	var ctx = $('#order_query_page_tmpl').attr('data-ctx')
	
	$(':radio, :checkbox').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue' });
	$('.selectpicker').selectpicker();
	
	function doPage(pageNo) {
		$.get(ctx + '/order/query/' + pageNo, function(page){ console.log(page);
			page.ctx = ctx;
			$('#order-query-page').html(tmpl('order_query_page_tmpl', page));
			$(':radio, :checkbox').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue' });
			$('#checkbox_orders_top').on('ifChecked', function(){
				$('input[name="checkbox_orders"]').iCheck('check');
			}).on('ifUnchecked', function(){
				$('input[name="checkbox_orders"]').iCheck('uncheck');
			});
		});
	}
	
	doPage(1);
	
})(jQuery);