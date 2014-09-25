(function($){
	
	var _tmpl = $('#combo_list_tmpl')
		, ctx = _tmpl.attr('data-ctx')
		, service_type = _tmpl.attr('data-service_type')
		, address = _tmpl.attr('data-address')
		
	var selected_combo = null
		, view_active = 'columns_view'
			
		, all_materials = []
		, transition_materials = []
	
		, wholesale_materials = []
		, wholesale_original_details = []
		, wholesale_addons_details = []
	
		, enduser_materials = []
		, enduser_original_details = []
		, old_enduser_original_details = []
		, enduser_addons_details = []
		
	function getMaterial(id) {
		var o = null;
		$.each(all_materials, function(){
			if (this.id == id) {
				o = $.extend({}, this);
				return false;
			}
		});
		return o;
	}
	
	function getMaterialById(materials, id) {
		var o = null;
		$.each(materials, function() {
			if (this.id == id) {
				o = $.extend({}, this);
				return false;
			}
		});
		return o;
	} 
	
	function getDetailByIndex(details, index) {
		var o = null;
		$.each(details, function() {
			if (this.index == index) {
				o = this;
				return false;
			}
		});
		return o;
	}
	
	function initDetailsIndex(details, type, detail_type) {
		if (type == 'wholesale') {
			$.each(details, function(i){
				this.index = i;
			});
		} else if (type == 'enduser') {
			if (detail_type == 'original') {
				$.each(details, function(i){
					this.index = i;
				});
			} else if (detail_type == 'addons') {
				$.each(details, function(i){
					this.index = i + 50;
				});
			}
		}
	}
	
	function initEnduserPrice(details) {
		$.each(details, function(){
			this.wholesale_price && (this.enduser_price = this.wholesale_price);
		});
	}
	
	function initTransitionMaterials(details){
		$.each(details, function(){
			if (this.material_category == 'transition'
				|| this.material_category == 'new-connection') {
				transition_materials.push(this);
			}
		});
	}
	
	function initEnduserOriginalDetails(details) {
		old_enduser_original_details = cloneArrayElement(old_enduser_original_details, enduser_original_details);
		enduser_original_details = cloneArrayElement(enduser_original_details, details);
		$.each(old_enduser_original_details, function(){
			this.added && enduser_original_details.push(this);
		});
	}
	
	function cloneArrayElement(newArray, oldArray) {
		newArray.length = 0;
		$.each(oldArray, function(){
			newArray.push($.extend({}, this));
		});
		return newArray;
	}
	
	function filterCombosAvailable(combos) {
		$.each(combos, function(){
			var combo = this;
			combo.available = false;
			$.each(combo.materials, function() {
				if (this.material_category == 'broadband-type') {
					if (this.material_group == service_type) {
						combo.available = true;
						return false;
					}
				}
			});
		});
	}
	
	function filterMaterialsAvailable(materials) {
		$.each(materials, function(){
			this.available = false;
			if (this.suitable.indexOf(service_type) > -1) {
				this.available = true;
			}
		});
	}
	
	function flushComboList() {
		$.get(ctx + '/order/select-combo/combo-loading', function(combos){ //console.log(combos);
			
			filterCombosAvailable(combos);
			
			var o = {
				ctx: ctx
				, service_type: service_type
				, address: address
				, combos: combos
			};
			
			$('#combo-list').html(tmpl('combo_list_tmpl', o));
			$(':radio, :checkbox').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue' });
			
			$.get(ctx + '/order/select-combo/material-loading', function(materials){
				initEnduserPrice(materials); // console.log(materials);
				initTransitionMaterials(materials);
				all_materials = materials; //console.log(getMaterial(766));
				filterMaterialsAvailable(cloneArrayElement(wholesale_materials, materials));
				filterMaterialsAvailable(cloneArrayElement(enduser_materials, materials));
				
				$('input[name="combo"]').on('ifChecked', function(){
					
					var combo_id = this.value; //console.log('combo_id: ' + combo_id);
					$.each(combos, function(){
						if (this.id == combo_id) {
							selected_combo = this; //console.log(selected_combo.materials);
							cloneArrayElement(wholesale_original_details, selected_combo.materials);
							initEnduserOriginalDetails(selected_combo.materials);
							initDetailsIndex(wholesale_original_details, 'wholesale');
							initDetailsIndex(enduser_original_details, 'enduser', 'original');
							return false;
						}
					});
					initEnduserPrice(enduser_original_details);
					loadingViews();
				});
			});
			
		});
	}
	
	flushComboList();
	
	function loadingViews(){
		var o = {
			view_active: view_active
		};
		$('#view-layer').html(tmpl('view_layer_tmpl', o));
		
		$('button[name="views"]').click(function(){
			$('button[name="views"]').removeClass('active');
			$(this).addClass('active');
			
			view_active = this.id;
			
			loadingDetails();
		});
		
		loadingDetails();
	}
	
	function loadingDetails(type) {
		// console.log('loadingDetails running...');
		var o = {
			selected_combo: selected_combo
			, transition_materials: transition_materials
			, wholesale_materials: wholesale_materials
			, wholesale_original_details: wholesale_original_details
			, wholesale_addons_details: wholesale_addons_details
			, enduser_materials: enduser_materials
			, enduser_original_details: enduser_original_details
			, enduser_addons_details: enduser_addons_details
		}; // console.log(o);
		
		if (view_active == 'columns_view') { //console.log(view_active);
			$('#order-for-wholesale-and-enduser').html(tmpl('order_for_wholesale_and_enduser_columns_tmpl', o));
		} else if (view_active == 'tab_view') { //console.log(view_active);
			$('#order-for-wholesale-and-enduser').html(tmpl('order_for_wholesale_and_enduser_tab_tmpl', o));
		}
		
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
		
		//console.log(wholesale_original_details.slice(-1)[0].index);
		
		var wholesale_addons_index = wholesale_addons_details.length > 0 
			? (Number(wholesale_addons_details.slice(-1)[0].index) + 1)
			: (Number(wholesale_original_details.slice(-1)[0].index) + 1);
		var enduser_addons_index = enduser_addons_details.length > 0 
			? (Number(enduser_addons_details.slice(-1)[0].index) + 1)
			: 50;
		//console.log(wholesale_addons_index);
		//console.log(enduser_addons_index);
			
		$('#order_for_wholesale_add_detail').click(function(){
		
			if (wholesale_addons_index >= 50) {
				alert('Items have reached the maximum number.');
				return false;
			}
			var o = { 
				index: wholesale_addons_index
				, materials: wholesale_materials
				, type: 'wholesale'
				, detail_type: 'addons'
				, html: 'select'
				, remove: true 
				, bg: 'info'
			};  //console.log(o);
			
			$('#order_for_wholesale_table > tbody').append(tmpl('order_detail_tmpl', o));
			$('#wholesale_detail' + wholesale_addons_index).selectpicker();
			$('#wholesale_detail' + wholesale_addons_index).change(wholesaleDetailSelect);
			$('#wholesale_detail_delete' + wholesale_addons_index).tooltip();
			$('#wholesale_detail_delete' + wholesale_addons_index).click(wholesaleDetailDelete);
			wholesale_addons_index++;
		});
		
		$('#order_for_enduser_add_detail').click(function(){
			
			var o = { 
				index: enduser_addons_index
				, materials: enduser_materials
				, type: 'enduser'
				, detail_type: 'addons'
				, html: 'select'  
				, remove: true
				, detail: { enduser_price: 0 }
				, bg: 'success'
			}; // console.log(o);
			
			$('#order_for_enduser_table > tbody').append(tmpl('order_detail_tmpl', o));
			$('#enduser_detail_price' + enduser_addons_index).on('input', inputPrice);
			$('#enduser_detail' + enduser_addons_index).selectpicker();
			$('#enduser_detail' + enduser_addons_index).change(enduserDetailSelect);
			$('#enduser_detail_delete' + enduser_addons_index).tooltip();
			$('#enduser_detail_delete' + enduser_addons_index).click(enduserDetailDelete);
			enduser_addons_index++;
		});
		
	}
	
	function wholesaleDetailSelect() {
		var detail = detailSelect(this, 'wholesale'); 
		var detail_type = $(this).attr('data-detail_type'); //console.log(detail_type);
		flushDetails('wholesale', detail_type, 'add', detail);
		flushTotalPrice('wholesale', 'wholesale-order-detail-total');
		
		//console.log('material_type: ' + detail.material_type);
		
		var o = { 
			index: detail.index
			, type: 'enduser'
			, detail_type: 'original'
			, html: 'span'
			, remove: false
			, detail: detail
			, bg: 'info'
		}; //console.log(o);
		
		var exist = flushDetails('enduser', 'original', 'add', detail);
		flushTotalPrice('enduser', 'enduser-order-detail-total');
		
		if (exist) { // console.log(true);
			$('#enduser_detail' + detail.index).text(detail.name);
			$('#enduser_detail_price' + detail.index).val(detail.enduser_price).attr('data-id', detail.id);
		} else { // console.log(false);
			$('#order_for_enduser_table > tbody').append(tmpl('order_detail_tmpl', o));
			$('#enduser_detail_price' + detail.index).on('input', inputPrice);
		}
		
		var o = {
			index: detail.index
			, view_active: view_active
			, type: 'enduser'
			, bg: 'info'
		};
		if (detail.material_type.indexOf('pstn') > -1) {
			o.tr_type = 'pstn'
		} else if (detail.material_category == 'broadband-type') {
			o.tr_type = 'broadband'
		} //console.log(o);
		
		if (view_active == 'columns_view') {
			var tr = $('#enduser_detail' + detail.index).closest('tr');
			tr.next('tr[data-incidental]').remove();
			o.tr_type && tr.after(tmpl('extra_tr_tmpl', o));
		} else if (view_active == 'tab_view') {
			var td = $('#enduser_detail' + detail.index).closest('td').next().next().next();
			td.empty();
			o.tr_type && td.html(tmpl('extra_tr_tmpl', o));
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
		
		var o = {
			index: detail.index
			, view_active: view_active
			, type: 'enduser'
			, bg: 'success'
		};
		if (detail.material_type.indexOf('pstn') > -1) {
			o.tr_type = 'pstn'
		} else if (detail.material_category == 'broadband-type') {
			o.tr_type = 'broadband'
		} //console.log(o);
		
		if (view_active == 'columns_view') {
			var tr = $('#enduser_detail' + detail.index).closest('tr');
			tr.next('tr[data-incidental]').remove();
			o.tr_type && tr.after(tmpl('extra_tr_tmpl', o));
		} else if (view_active == 'tab_view') {
			var td = $('#enduser_detail' + detail.index).closest('td').next().next().next();
			td.empty();
			o.tr_type && td.html(tmpl('extra_tr_tmpl', o));
		}
	}
	
	function enduserDetailDelete(){
		var detail = detailDelete(this, 'enduser'); 
		flushDetails('enduser', 'addons', 'delete', detail);
		flushTotalPrice('enduser', 'enduser-order-detail-total');
	}
	
	function detailSelect(o, type) { //console.log('detailSelect running...' + type);
		var option = $(o).find('option:selected');
		var id = option.attr('data-id');
		var index = $(o).attr('data-index'); //console.log(index);
		var added = $(o).attr('data-added');
		var materials = (type == 'wholesale' ? wholesale_materials : enduser_materials);
		var detail = getMaterialById(materials, id);
		detail.index = index;
		detail.added = Boolean(added);
		$('#' + type + '_detail_price' + index)
			.text('$ ' + detail[type + '_price'].toFixed(2))
			.val(detail[type + '_price'])
			.attr('data-id', detail.id)
			.attr('data-index', index)
			.attr('data-name', detail.name);
		return detail;
	}
	
	function detailDelete(o, type) {
		//console.log('detailDelete running...');
		var index = $(o).attr('data-index'); //console.log(index);
		var detail = { index: index };
		$(o).closest('tr[data-index]').next('tr[data-incidental]').remove();
		$(o).closest('tr[data-index]').remove();
		
		return detail;
	}
	
	function flushTotalPrice(type, id) { //console.log("flushTotalPrice running..." + type);
		var total_before_gst = 0;
		
		if (type == 'wholesale') {
			$.each(wholesale_original_details, function(){
				total_before_gst += this.wholesale_price;
			});
			$.each(wholesale_addons_details, function(){
				total_before_gst += this.wholesale_price;
			});
		} else if (type == 'enduser') {
			$.each(enduser_original_details, function(){
				total_before_gst += this.enduser_price;
			});
			$.each(enduser_addons_details, function(){
				total_before_gst += this.enduser_price;
			});
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
		
		$.each(details, function(i) { //console.log('this.id: ' + this.id + ', detail.id: ' + detail.id + ', this.index: ' + this.index + ', detail.index: ' + detail.index);
			if (this.index == detail.index) {
				details[i] = detail;
				exist = true;
				index = i;
				return false;
			} 
		});
		
		if (action == 'add') {
			if (!exist && detail.name != '') {
				details.push(detail);
			} 
		} else if (action == 'delete') {
			if (exist) details.splice(index, 1);
		}
		console.log(type + '_' + detail_type + '_details:');console.log(details);
		return exist;
	}
	
	function inputPrice(e) {
		var o = $(this);
		var value = this.value;
		if (!isNaN(value) && Number(value) >= 0) {
			var type = o.attr('data-type');
			var detail_type = o.attr('data-detail_type');
			details = null;
			if (type == 'enduser') {
				if (detail_type == 'original') {
					details = enduser_original_details;
				} else if (detail_type == 'addons') {
					details = enduser_addons_details;
				}
			}
			
			var index = o.attr('data-index'); //console.log(id);
			var detail = getDetailByIndex(details, index);
			
			detail.enduser_price = Number(value); //console.log(detail);
			
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
		
		var order = {
			service_type: service_type
			, hardware_post: 0
		};
		var order_details = [];
		
		$.each(enduser_original_details, function(){
			order_details.push({
				name: this.name
				, price: this.wholesale_price
				, price_enduser: this.enduser_price
				, subscribe: this.subscribe
				, material_group: this.material_group
				, material_type: this.material_type
				, material_category: this.material_category
				, type: ''
				, unit: 1
				, data_flow: $('#enduser_detail_data_flow' + this.index).val()
				, number: $('#enduser_detail_number' + this.index).val()
				, is_wholesale: true
				, is_enduser: true
			})
		});
		
		$.each(enduser_addons_details, function(){
			order_details.push({
				name: this.name
				, price_enduser: this.enduser_price
				, subscribe: this.subscribe
				, material_group: this.material_group
				, material_type: this.material_type
				, material_category: this.material_category
				, type: ''
				, unit: 1
				, data_flow: $('#enduser_detail_data_flow' + this.index).val()
				, number: $('#enduser_detail_number' + this.index).val()
				, is_wholesale: false
				, is_enduser: true
			})
		});
		
		//console.log(order_details);
		
		$.each(order_details, function(){ //console.log(this.material_category);
			if (this.material_type == 'transition' 
					|| this.material_type == 'connection-wiring'
					|| this.material_type == 'connection-vdsl') {
				order.broadband_type = this.material_type;
			} else if (this.material_group == 'hardware') {
				order.hardware_post += 1;
			}
		});
		
		order.ods = order_details;
		//console.log(order);
		//console.log(JSON.stringify(order));
		
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