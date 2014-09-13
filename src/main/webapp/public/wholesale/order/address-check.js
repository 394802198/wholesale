(function($){
	
	var ctx = $('#address_check_result_tmpl').attr('data-ctx')
	
	$('#goCheck').click(function(e){
		e.preventDefault();
		var address = $('#address').val();
		address = $.trim(address.replace(/[\/]/g,' ').replace(/[\\]/g,' ')); //console.log(address);
		if (address != '') {
			var l = Ladda.create(this); l.start();
			$.get(ctx + '/order/check-address/' + address, function(broadband){ //console.log(broadband);
				broadband.ctx = ctx;
				$('#address-check-result').html(tmpl('address_check_result_tmpl', broadband));
				if (broadband.adsl_available) { //alert('adsl');
					$('#adsl').click(function(){
						window.location.href = ctx + '/order/select-combo/adsl';
					});
				}
				if (broadband.vdsl_available) {
					$('#vdsl').click(function(){ //alert('vdsl');
						window.location.href = ctx + '/order/select-combo/vdsl';
					});
				}
				if (broadband.ufb_available) {
					$('#ufb').click(function(){ //alert('ufb');
						window.location.href = ctx + '/order/select-combo/ufb';
					});
				}
		   	}).always(function(){ l.stop(); });
		} else {
			alert('Please enter a real address.');
		}
	});
	
})(jQuery);