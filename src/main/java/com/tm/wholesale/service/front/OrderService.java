package com.tm.wholesale.service.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.ComboWholesalerMapper;
import com.tm.wholesale.mapper.MaterialWholesalerMapper;
import com.tm.wholesale.model.ComboWholesaler;
import com.tm.wholesale.model.MaterialWholesaler;

@Service
public class OrderService {
	
	private ComboWholesalerMapper comboWholesalerMapper;
	private MaterialWholesalerMapper materialWholesalerMapper;

	@Autowired
	public OrderService(ComboWholesalerMapper comboWholesalerMapper, MaterialWholesalerMapper materialWholesalerMapper) {
		this.comboWholesalerMapper = comboWholesalerMapper;
		this.materialWholesalerMapper = materialWholesalerMapper;
	}
	
	public OrderService(){}
	
	/*@Transactional
	public List<ComboWholesaler> queryComboWholesalers(ComboWholesaler cw) {
		return this.comboWholesalerMapper.selectComboWholesalers(cw);
	}
	
	@Transactional
	public List<MaterialWholesaler> queryMaterialWholesalers(MaterialWholesaler mw) {
		return this.materialWholesalerMapper.selectMaterialWholesalers(mw);
	}*/

}
