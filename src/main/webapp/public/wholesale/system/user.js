(function($){
	
	var btn = $('#actionBtn')
		, ctx = btn.attr('data-ctx')
		, action = btn.attr('data-action')
		, heading = $('#heading')
		, id = btn.attr('data-id')
		
	$('.selectpicker').selectpicker();
		
	if (action == 'create') {
		
		heading.text('Create User');
		btn.text('Create');
		
		btn.click(function(e){
			e.preventDefault();
			
			var wholesaler = {
				name: $('#name').val()
				, login_name: $('#login_name').val()
				, login_password: $('#login_password').val()
				, cellphone: $('#cellphone').val()
				, email: $('#email').val()
				, role: $('#role').find('option:selected').val()
				, memo: $('#memo').val()
				, status: $('#status').find('option:selected').val()
			};
			
			var l = Ladda.create(this); l.start();
			$.post(ctx + '/system/user/create', wholesaler, function(json){
				if (!$.jsonValidation(json, 'right')) window.location.href = ctx + json.url;
			}, 'json').always(function () { l.stop(); });
		});
		
	} else if (action == 'edit') {
		
		heading.text('Edit User');
		btn.text('Edit');
		
		$.get(ctx + '/system/user/edit/' + id + '/query', function(w){
			$('#name').val(w.name);
			$('#login_name').val(w.login_name);
			$('#login_password').val(w.login_password);
			$('#cellphone').val(w.cellphone);
			$('#email').val(w.email);
			$('#role').selectpicker('val', w.role);
			$('#memo').val(w.memo);
			$('#status').selectpicker('val', w.status);
		});
		
		
		btn.click(function(e){
			e.preventDefault();
			
			var wholesaler = {
				id: id
				, name: $('#name').val()
				, login_name: $('#login_name').val()
				, login_password: $('#login_password').val()
				, cellphone: $('#cellphone').val()
				, email: $('#email').val()
				, role: $('#role').find('option:selected').val()
				, memo: $('#memo').val()
				, status: $('#status').find('option:selected').val()
			};
			
			var l = Ladda.create(this); l.start();
			$.post(ctx + '/system/user/edit', wholesaler, function(json){
				if (!$.jsonValidation(json, 'right')) window.location.href = ctx + json.url;
			}, 'json').always(function () { l.stop(); });
		});
	}
	
})(jQuery);