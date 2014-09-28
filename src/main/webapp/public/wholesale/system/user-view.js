(function($){
	
	var ctx = $('#user_view_page_tmpl').attr('data-ctx')
	
	function doPage(pageNo) {
		$.get(ctx + '/system/user/view/' + pageNo, function(page){ //console.log(page);
			page.ctx = ctx;
	   		var $div = $('#user-view-page');
	   		$div.html(tmpl('user_view_page_tmpl', page));
	   		$div.find('tfoot a').click(function(){
				doPage($(this).attr('data-pageNo'));
			});
	   		$(':radio, :checkbox').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue' });
			$('#checkbox_users_top').on('ifChecked', function(){
				$('input[name="checkbox_users"]').iCheck('check');
			}).on('ifUnchecked', function(){
				$('input[name="checkbox_users"]').iCheck('uncheck');
			});
	   	});
	}
	doPage(1);
	
})(jQuery);