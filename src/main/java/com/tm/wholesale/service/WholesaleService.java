package com.tm.wholesale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.WholesalerMapper;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Wholesaler;

@Service
public class WholesaleService {
	
	private WholesalerMapper wholesalerMapper;

	@Autowired
	public WholesaleService(WholesalerMapper wholesalerMapper) {
		this.wholesalerMapper = wholesalerMapper;
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
