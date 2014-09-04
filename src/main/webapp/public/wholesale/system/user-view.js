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
			
			$('#checkbox_users_top').click(function(){
				var b = $(this).prop("checked");
				var input = $('input[name="checkbox_users"]');
				if (b) input.prop("checked", true);
				else input.prop("checked", false);
			});
	   	});
	}
	doPage(1);
	
})(jQuery);