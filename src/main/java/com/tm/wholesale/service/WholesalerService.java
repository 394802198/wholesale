package com.tm.wholesale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.WholesalerMapper;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Wholesaler;

@Service
public class WholesalerService {
	
	private WholesalerMapper wholesalerMapper;
	
	public WholesalerService() {
		
	}
	
	@Autowired
	public WholesalerService(WholesalerMapper wholesalerMapper) {
		this.wholesalerMapper = wholesalerMapper;
	}
	
	@Transactional
	public Wholesaler queryWholesaler(Wholesaler wholesaler) {
		List<Wholesaler> wholesalers = this.wholesalerMapper.selectWholesalers(wholesaler);
		return wholesalers != null && wholesalers.size() > 0 ? wholesalers.get(0) : null;
	}
	
	@Transactional
	public int queryExistWholesaler(Wholesaler wholesaler) {
		return this.wholesalerMapper.selectExistWholesaler(wholesaler);
	}
	
	@Transactional
	public Page<Wholesaler> queryWholesalersByPage(Page<Wholesaler> page){
		page.setResults(this.wholesalerMapper.selectWholesalersByPage(page));
		page.setTotalRecord(this.wholesalerMapper.selectWholesalersSum(page));
		return page;
	} 
	
	@Transactional
	public void createWholesaler(Wholesaler wholesaler) {
		this.wholesalerMapper.insertWholesaler(wholesaler);
	}
	
	@Transactional
	public void editWholesaler(Wholesaler wholesaler) {
		this.wholesalerMapper.updateWholesaler(wholesaler);
	}
}
