(function($){
	
	var ctx = $('#order_query_page_tmpl').attr('data-ctx')
	
	$(':radio, :checkbox').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue' });
	$('.selectpicker').selectpicker();
	
	function doPage(pageNo) {
		$.get(ctx + '/order/query/' + pageNo, function(page){ console.log(page);
			page.ctx = ctx;
			$('#order-query-page').html(tmpl('order_query_page_tmpl', page));
			
		});
	}
	
	doPage(1);
	
})(jQuery);