(function($){
	
	var ctx = $('#combo_list_tmpl').attr('data-ctx')
		, combo_list = $('#combo-list')
		, order_for = $('#order_for_wholesale_and_enduser')
		
	var selected_combo = null
		, wholesale_materials = null
		, wholesale_original_details = null
		, wholesale_addons_details = []
		
	function flushComboList() {
		$.get(ctx + '/order/select-combo/combo-loading', function(combos){ //console.log(combos);
			var o = {
				ctx: ctx
				, combos: combos
			};
			combo_list.html(tmpl('combo_list_tmpl', o));
			$(':radio, :checkbox').iCheck({ checkboxClass : 'icheckbox_square-blue', radioClass : 'iradio_square-blue' });
			
			$.get(ctx + '/order/select-combo/material-loading', function(materials){
				wholesale_materials = materials; //console.log(materials);
				$('input[name="combo"]').on('ifChecked', function(){
					var combo_id = this.value; //console.log('combo_id: ' + combo_id);
					for (var i = 0, len = combos.length; i < len; i++) {
						var combo = combos[i]; //console.log('combo.id: ' + combo.id);
						if (combo.id == combo_id) {
							selected_combo = combo; 
							wholesale_original_details = selected_combo.materials.concat();
							break;
						}
					}
					//console.log(selected_combo);
					flushWholesaleDetails();
					flushTotalPrice('wholesale', 'wholesale-order-detail-total');
				});
			});
			
		});
	}
	
	flushComboList();
	
	function flushWholesaleDetails() {
		var o = {
			selected_combo: selected_combo
			, wholesale_original_details: wholesale_original_details
			, wholesale_addons_details: wholesale_addons_details
		};
		//console.log(o);
		order_for.html(tmpl('order_for_wholesale_and_enduser_tmpl', o));
		$('a[data-toggle="tooltip"]').tooltip();
		var order_for_wholesale_table_tbody = $('#order_for_wholesale_table > tbody');
		var index = 0;
		$('#order_for_wholesale_add_detail').click(function(){
			var o = { 
				index: index 
				, wholesale_materials: wholesale_materials
			}; //console.log(o);
			order_for_wholesale_table_tbody.append(tmpl('order_detail_tmpl', o));
			$('a[data-toggle="tooltip"]').tooltip();
			$('#wholesale_detail' + index).selectpicker();
			$('#wholesale_detail' + index).change(function(){
				var option = $(this).find('option:selected');
				var name = option.attr('data-name');
				var price = Number(option.attr('data-wholesale_price')); 
				var index = $(this).attr('data-index');//console.log(price);
				$('#wholesale_detail_price' + index).text('$ ' + price.toFixed(2));
				var addons_detail = {
					index: index
					, name: name
					, wholesale_price: price
				};
				flushWholesaleAaddonsDetails(addons_detail, 'add');
				flushTotalPrice('wholesale', 'wholesale-order-detail-total');
			});
			$('#wholesale_detail_delete' + index).click(function(){
				var index = $(this).attr('data-index');//console.log(price);
				var addons_detail = { index: index };
				$(this).closest('tr[data-index]').remove();
				flushWholesaleAaddonsDetails(addons_detail, 'delete');
				flushTotalPrice('wholesale', 'wholesale-order-detail-total');
			});
			index++;
		});
		
	}
	
	function flushTotalPrice(option, id) {
		var total_before_gst = 0;
		
		if (option == 'wholesale') {
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
		} else if (option == 'enduser') {
			
		}
		
		var o = {
			total_before_gst: total_before_gst
		};
		$('#' + id).html(tmpl('order_detail_total_tmpl', o));
	}
	
	function flushWholesaleAaddonsDetails(addons_detail, action) {
		//console.log('addons_detail:');console.log(addons_detail);
		var exist = false;
		var index = 0;
		if (wholesale_addons_details != null && wholesale_addons_details.length > 0) {
			for (var i = 0, len = wholesale_addons_details.length; i < len; i++) {
				var detail = wholesale_addons_details[i];
				if (detail.index == addons_detail.index) {
					wholesale_addons_details[i] = addons_detail;
					exist = true;
					index = i;
					break;
				}
			}
		}
		if (action == 'add') {
			if (!exist) {
				wholesale_addons_details.push(addons_detail);
			} 
		} else if (action == 'delete') {
			wholesale_addons_details.splice(index, 1);
		}
		//console.log('wholesale_addons_details:');console.log(wholesale_addons_details);
	}

})(jQuery);