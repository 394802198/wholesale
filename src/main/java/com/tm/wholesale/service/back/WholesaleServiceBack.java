package com.tm.wholesale.service.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.ComboWholesalerMapper;
import com.tm.wholesale.mapper.MaterialWholesalerMapper;
import com.tm.wholesale.mapper.WholesalerMapper;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Wholesaler;

@Service
public class WholesaleServiceBack {
	
	private WholesalerMapper wholesalerMapper;
	private ComboWholesalerMapper comboWholesalerMapper;
	private MaterialWholesalerMapper materialWholesalerMapper;

	@Autowired
	public WholesaleServiceBack(WholesalerMapper wholesalerMapper,
			ComboWholesalerMapper comboWholesalerMapper,
			MaterialWholesalerMapper materialWholesalerMapper) {
		this.wholesalerMapper = wholesalerMapper;
		this.comboWholesalerMapper = comboWholesalerMapper;
		this.materialWholesalerMapper = materialWholesalerMapper;
	}
	
	/**
	 * BEGIN Wholesaler
	 */
	
	public Wholesaler queryWholesaler(Wholesaler w) {
		List<Wholesaler> ws = this.wholesalerMapper.selectWholesalers(w);
		return ws != null && ws.size() > 0 ? ws.get(0) : null;
	}
	
	public List<Wholesaler> queryWholesalers(Wholesaler w){
		return this.wholesalerMapper.selectWholesalers(w);
	}
	
	public Page<Wholesaler> queryWholesalerByPage(Page<Wholesaler> page){
		page.setResults(this.wholesalerMapper.selectWholesalersByPage(page));
		page.setTotalRecord(this.wholesalerMapper.selectWholesalersSum(page));
		return page;
	}
	
	@Transactional
	public void editWholesaler(Wholesaler w){
		this.wholesalerMapper.updateWholesaler(w);
	}
	
	@Transactional
	public void removeWholesalerById(int id){
		Wholesaler wQuery = new Wholesaler();
		wQuery.getParams().put("id", id);
		wQuery = this.wholesalerMapper.selectWholesalers(wQuery).get(0);
		this.materialWholesalerMapper.deleteMaterialWholesalerByCompanyId(wQuery.getCompany_id());
		this.comboWholesalerMapper.deleteComboWholesalerByCompanyId(wQuery.getCompany_id());
		this.wholesalerMapper.deleteWholesalerById(id);
	}
	
	@Transactional
	public void createWholesaler(Wholesaler w){
		this.wholesalerMapper.insertWholesaler(w);
	}
	
	/**
	 * END Wholesaler
	 */
	
}
