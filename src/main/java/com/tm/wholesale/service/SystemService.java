package com.tm.wholesale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.ManagerMapper;
import com.tm.wholesale.mapper.WholesalerMapper;
import com.tm.wholesale.model.Manager;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Wholesaler;

@Service
public class SystemService {
	
	private WholesalerMapper wholesalerMapper;
	private ManagerMapper managerMapper;

	@Autowired
	public SystemService(ManagerMapper managerMapper,
			WholesalerMapper wholesalerMapper) {
		this.managerMapper = managerMapper;
		this.wholesalerMapper = wholesalerMapper;
	}
	
	@Transactional
	public Manager queryManager(Manager manager) {
		List<Manager> managers = this.managerMapper.selectManagers(manager);
		return managers != null && managers.size() > 0 ? managers.get(0) : null;
	}
	
	/**
	 * BEGIN Wholesaler
	 */
	
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
