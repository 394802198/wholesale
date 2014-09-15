package com.tm.wholesale.service.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.ManagerMapper;
import com.tm.wholesale.model.Manager;
import com.tm.wholesale.model.Page;

@Service
public class SystemServiceBack {
	
	private ManagerMapper managerMapper;

	@Autowired
	public SystemServiceBack(ManagerMapper managerMapper) {
		this.managerMapper = managerMapper;
	}
	
	/**
	 * BEGIN Manager
	 */
	
	public Manager queryManager(Manager manager) {
		List<Manager> managers = this.managerMapper.selectManagers(manager);
		return managers != null && managers.size() > 0 ? managers.get(0) : null;
	}
	
	public List<Manager> queryManagers(Manager m){
		return this.managerMapper.selectManagers(m);
	}
	
	public Page<Manager> queryManagerByPage(Page<Manager> page){
		page.setResults(this.managerMapper.selectManagersByPage(page));
		page.setTotalRecord(this.managerMapper.selectManagersSum(page));
		return page;
	}
	
	@Transactional
	public void editManager(Manager m){
		this.managerMapper.updateManager(m);
	}
	
	@Transactional
	public void removeManagerById(int id){
		this.managerMapper.deleteManagerById(id);
	}
	
	@Transactional
	public void createManager(Manager m){
		this.managerMapper.insertManager(m);
	}
	
	/**
	 * END Manager
	 */
	
}
