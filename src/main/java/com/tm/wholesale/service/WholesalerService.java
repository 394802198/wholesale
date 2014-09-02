package com.tm.wholesale.service;

import java.util.List;

import javax.xml.ws.ServiceMode;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.WholesalerMapper;
import com.tm.wholesale.model.Wholesaler;

@Service
public class WholesalerService {
	
	private WholesalerMapper wholesalerMapper;
	
	public WholesalerService() {
		
	}
	
	public WholesalerService(WholesalerMapper wholesalerMapper) {
		this.wholesalerMapper = wholesalerMapper;
	}
	
	@Transactional
	public Wholesaler queryWholesaler(Wholesaler wholesaler) {
		List<Wholesaler> wholesalers = this.wholesalerMapper.selectWholesalers(wholesaler);
		return wholesalers != null && wholesalers.size() > 0 ? wholesalers.get(0) : null;
	}
	
	
}
