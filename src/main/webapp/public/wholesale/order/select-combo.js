(function($){
	
	var ctx = $('#combo_list_tmpl').attr('data-ctx')
		, combo_list = $('#combo-list')
		, order_for = $('#order_for_wholesale_and_enduser')
		
	var selected_combo = null
	
		, wholesale_materials = null
		, wholesale_original_details = null
		, wholesale_addons_details = []
	
		, enduser_materials = null
		, enduser_original_details = null
		, old_enduser_original_details = null
		, enduser_addons_details = []
		
	function flushComboList() {
		$.get(ctx + '/order/select-combo/combo-loading', function(combos){ //console.log(combos);
			var o = {
				ctx: ctx
				, combos: combos
			};
			combo_list.html(tmpl('combo_list_tmpl', o));
			$(':radio, :checkbox').iCheck({ checkboxClass : 'icheckbox_square-blue', radioClass : 'iradio_square-blue' });
			
			
			$.get(ctx + '/order/select-combo/material-loading', function(materials){
				initEnduserPrice(materials);
				wholesale_materials = materials.concat(); 
				enduser_materials = materials.concat(); //console.log(materials);
				
				$('input[name="combo"]').on('ifChecked', function(){
					var combo_id = this.value; //console.log('combo_id: ' + combo_id);
					for (var i = 0, len = combos.length; i < len; i++) {
						var combo = combos[i]; //console.log('combo.id: ' + combo.id);
						if (combo.id == combo_id) {
							selected_combo = combo; 
							wholesale_original_details = selected_combo.materials.concat();
							old_enduser_original_details = enduser_original_details && enduser_original_details.concat();
							enduser_original_details = selected_combo.materials.concat();
							if (old_enduser_original_details != null && old_enduser_original_details.length > 0) {
								for (var key in old_enduser_original_details) {
									old_enduser_original_details[key].index 
										&& (enduser_original_details[enduser_original_details.length] = old_enduser_original_details[key]);
								}
							}
							break;
						}
					}
					//console.log('enduser_original_details:');console.log(enduser_original_details);
					//console.log(selected_combo);
					initEnduserPrice(enduser_original_details);
					/*console.log('wholesale_original_details:');console.log(wholesale_original_details);
					console.log('enduser_original_details:');console.log(enduser_original_details);*/
					loadingDetails();
				});
			});
			
		});
	}
	
	flushComboList();
	
	function loadingDetails(type) {
		// console.log('loadingDetails running...');
		var o = {
			selected_combo: selected_combo
			, wholesale_materials: wholesale_materials
			, wholesale_original_details: wholesale_original_details
			, wholesale_addons_details: wholesale_addons_details
			, enduser_materials: enduser_materials
			, enduser_original_details: enduser_original_details
			, enduser_addons_details: enduser_addons_details
		}; // console.log(o);
		order_for.html(tmpl('order_for_wholesale_and_enduser_tmpl', o));
		$('#continue').click(continueOrder); //
		
		$('a[data-toggle="tooltip"]').tooltip();
		$('.selectpicker').selectpicker();
		$('input[data-enduser_detail_price]').on('input', inputPrice);
		$('select[data-wholesale_detail_select]').change(wholesaleDetailSelect);
		$('a[data-wholesale_detail_delete]').click(wholesaleDetailDelete);
		$('select[data-enduser_detail_select]').change(enduserDetailSelect);
		$('a[data-enduser_detail_delete]').click(enduserDetailDelete);
		flushTotalPrice('wholesale', 'wholesale-order-detail-total');
		flushTotalPrice('enduser', 'enduser-order-detail-total');

		var wholesale_index = wholesale_addons_details.length > 0 
			? (Number(wholesale_addons_details[wholesale_addons_details.length - 1].index) + 1)
			: 0;
		var enduser_index = enduser_addons_details.length > 0 
			? (Number(enduser_addons_details[enduser_addons_details.length - 1].index) + 1)
			: 50;
			
		$('#order_for_wholesale_add_detail').click(function(){
			if (wholesale_index == 49) {
				alert('Items have reached the maximum number.');
				return false;
			}
			var o = { 
				index: wholesale_index
				, materials: wholesale_materials
				, type: 'wholesale'
				, detail_type: 'addons'
				, html: 'select'
				, remove: true 
				, bg: 'info'
			}; // console.log(o);
			
			$('#order_for_wholesale_table > tbody').append(tmpl('order_detail_tmpl', o));
			$('#wholesale_detail' + wholesale_index).selectpicker();
			$('#wholesale_detail' + wholesale_index).change(wholesaleDetailSelect);
			$('#wholesale_detail_delete' + wholesale_index).tooltip();
			$('#wholesale_detail_delete' + wholesale_index).click(wholesaleDetailDelete);
			wholesale_index++;
		});
		
		$('#order_for_enduser_add_detail').click(function(){
			var o = { 
				index: enduser_index
				, materials: enduser_materials
				, type: 'enduser'
				, detail_type: 'addons'
				, html: 'select'  
				, remove: true
				, detail: { enduser_price: 0 }
				, bg: 'success'
			}; // console.log(o);
			$('#order_for_enduser_table > tbody').append(tmpl('order_detail_tmpl', o));
			$('#enduser_detail_price' + enduser_index).on('input', inputPrice);
			$('#enduser_detail' + enduser_index).selectpicker();
			$('#enduser_detail' + enduser_index).change(enduserDetailSelect);
			$('#enduser_detail_delete' + enduser_index).tooltip();
			$('#enduser_detail_delete' + enduser_index).click(enduserDetailDelete);
			enduser_index++;
		});
		
	}
	
	function wholesaleDetailSelect() {
		var detail = detailSelect(this, 'wholesale'); 
		flushDetails('wholesale', 'addons', 'add', detail);
		flushTotalPrice('wholesale', 'wholesale-order-detail-total');
		
		var o = { 
			index: detail.index
			, type: 'enduser'
			, detail_type: 'original'
			, html: 'span'
			, remove: false
			, detail: detail
			, bg: 'info'
		}; 
		
		var exist = flushDetails('enduser', 'original', 'add', detail);
		flushTotalPrice('enduser', 'enduser-order-detail-total');
		
		if (exist) {
			$('#enduser_detail' + detail.index).text(detail.name);
			$('#enduser_detail_price' + detail.index).val(detail.enduser_price);
		} else {
			$('#order_for_enduser_table > tbody').append(tmpl('order_detail_tmpl', o));
			$('#enduser_detail_price' + detail.index).on('input', inputPrice);
		}
	}
	
	function wholesaleDetailDelete(){
		var detail = detailDelete(this, 'wholesale'); 
		flushDetails('wholesale', 'addons', 'delete', detail);
		flushTotalPrice('wholesale', 'wholesale-order-detail-total');
		
		detail = detailDelete($('#enduser_detail' + detail.index), 'enduser');
		flushDetails('enduser', 'original', 'delete', detail);
		flushTotalPrice('enduser', 'enduser-order-detail-total');
	}
	
	function enduserDetailSelect(){
		var detail = detailSelect(this, 'enduser'); 
		flushDetails('enduser', 'addons', 'add', detail);
		flushTotalPrice('enduser', 'enduser-order-detail-total');
	}
	
	function enduserDetailDelete(){
		var detail = detailDelete(this, 'enduser'); 
		flushDetails('enduser', 'addons', 'delete', detail);
		flushTotalPrice('enduser', 'enduser-order-detail-total');
	}
	
	function detailSelect(o, type) {
		//console.log('detailSelect running...' + type);
		var option = $(o).find('option:selected');
		var name = option.attr('data-name');
		var price = Number(option.attr('data-price')); //console.log(price);
		var index = $(o).attr('data-index'); //console.log(index);
		$('#' + type + '_detail_price' + index)
			.text('$ ' + price.toFixed(2))
			.val(price)
			.attr('data-index', index)
			.attr('data-name', name);
		var detail = { 
			index: index
			, name: name
			, wholesale_price: price
			, enduser_price: price
		};
		return detail;
	}
	
	function detailDelete(o, type) {
		//console.log('detailDelete running...');
		var index = $(o).attr('data-index'); //console.log(index);
		var detail = { index: index };
		$(o).closest('tr[data-index]').remove();
		return detail;
	}
	
	function flushTotalPrice(type, id) {
		//console.log("flushTotalPrice running..." + type);
		var total_before_gst = 0;
		
		if (type == 'wholesale') {
			if (wholesale_original_details != null && wholesale_original_details.length > 0) {
				for (var i = 0, len = wholesale_original_details.length; i < len; i++) {
					var detail = wholesale_original_details[i];
					total_before_gst += detail.wholesale_price;
				}
			}
			if (wholesale_addons_details != null && wholesale_addons_details.length > 0) {
				for (var i = 0, len = wholesale_addons_details.length; i < len; i++) {
					var detail = wholesale_addons_details[i];
					total_before_gst += detail.wholesale_price;
				}
			}
		} else if (type == 'enduser') {
			if (enduser_original_details != null && enduser_original_details.length > 0) {
				for (var i = 0, len = enduser_original_details.length; i < len; i++) {
					var detail = enduser_original_details[i];
					total_before_gst += detail.enduser_price;
				}
			}
			if (enduser_addons_details != null && enduser_addons_details.length > 0) {
				for (var i = 0, len = enduser_addons_details.length; i < len; i++) {
					var detail = enduser_addons_details[i];
					total_before_gst += detail.enduser_price;
				}
			}
		}
		
		var o = {
			total_before_gst: total_before_gst
		};
		$('#' + id).html(tmpl('order_detail_total_tmpl', o));
	}

	function flushDetails(type, detail_type, action, detail) {
		//console.log('flushDetails running...' + type + '...' + detail_type + '...' + action);
		//console.log('detail:');console.log(detail);
		
		var details = null
			, exist = false
			, index = 0
		
		if (type == 'wholesale') {
			if (detail_type == 'original') {
				details = wholesale_original_details;
			} else if (detail_type == 'addons') {
				details = wholesale_addons_details;
			}
		} else if (type == 'enduser') {
			if (detail_type == 'original') {
				details = enduser_original_details;
			} else if (detail_type == 'addons') {
				details = enduser_addons_details;
			}
		}
		
		//console.log('before_' + type + '_' + detail_type + '_details:');console.log(details);
		
		if (details != null && details.length > 0) {
			for (var i = 0, len = details.length; i < len; i++) {
				var temp_detail = details[i];
				
				if (temp_detail.index) {
					//console.log('temp_detail.index: ' + temp_detail.index + "..." + detail.index);
					if (temp_detail.index == detail.index) {
						details[i] = detail;
						exist = true;
						index = i;
						break;
					}
				} else if (temp_detail.id) {
					//console.log('temp_detail.id: ' + temp_detail.id + "..." + detail.id);
					if (temp_detail.id == detail.id) {
						details[i] = detail;
						exist = true;
						index = i;
						break;
					}
				}
			}
		}
		
		if (action == 'add') {
			if (!exist && detail.name != '') {
				details.push(detail);
			} 
		} else if (action == 'delete') {
			if (exist) details.splice(index, 1);
		}
		// console.log(type + '_' + detail_type + '_details:');console.log(details);
		return exist;
	}
	
	function initEnduserPrice(details) {
		if (details != null && details.length > 0) {
			for (var i = 0, len = details.length; i < len; i++) {
				var detail = details[i];
				detail.wholesale_price && (detail.enduser_price = detail.wholesale_price);
			}
		}
	}
	
	function inputPrice(e) {
		var o = $(this);
		var value = this.value;
		if (!isNaN(value) && Number(value) >= 0) {
			var type = o.attr('data-type');
			var detail_type = o.attr('data-detail_type');
			var detail = {
				id: o.attr('data-id')
				, index: o.attr('data-index')
				, name: o.attr('data-name')
				, wholesale_price: Number(o.attr('data-wholesale_price'))
				, enduser_price: Number(value)
			}; // console.log(detail);
			flushDetails(type, detail_type, 'add', detail);
			flushTotalPrice('enduser', 'enduser-order-detail-total');
		} else {
			//alert('Please enter a price greater than 0.');
			return false;
		}
	} 
	
	function continueOrder(){
		
		//console.log(enduser_original_details);
		//console.log(enduser_addons_details);
		
		var order = {};
		var order_details = [];
		
		if (enduser_original_details != null && enduser_original_details.length > 0) {
			for (var i = 0, len = enduser_original_details.length; i < len; i++) {
				var detail = enduser_original_details[i];
				order_details.push({
					name: detail.name
					, price: detail.wholesale_price
					, price_enduser: detail.enduser_price
					, subscribe: 'monthly'
					, material_type: ''
					, type: ''
					, unit: 1
					, is_wholesale: true
					, is_enduser: true
				});
			}
		}
		
		if (enduser_addons_details != null && enduser_addons_details.length > 0) {
			for (var i = 0, len = enduser_addons_details.length; i < len; i++) {
				var detail = enduser_addons_details[i];
				order_details.push({
					name: detail.name
					, price_enduser: detail.enduser_price
					, subscribe: 'monthly'
					, material_type: ''
					, type: ''
					, unit: 1
					, is_wholesale: false
					, is_enduser: true
				});
			}
		}
		
		order.ods = order_details;
		
		console.log(JSON.stringify(order));
		
		var url = ctx + '/order/select-combo/submit';
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
	}

})(jQuery);