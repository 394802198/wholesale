package com.tm.wholesale.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tm.wholesale.model.Broadband;
import com.tm.wholesale.model.Combo;
import com.tm.wholesale.model.Order;
import com.tm.wholesale.util.broadband.BroadbandCapability;

@RestController
public class OrderRestController {

	public OrderRestController() {}
	
	@RequestMapping("/order/check-address/{address}")
	public Broadband checkAddress(@PathVariable("address") String address,
			HttpSession session) {
		System.out.println("address: " + address);
		Order orderSession = new Order();
		session.setAttribute("orderSession", orderSession);
		orderSession.setAddress(address);
		Broadband broadband = this.returnBroadband(address);
		broadband.setAddress(address);
		orderSession.setBroadband(broadband);
		return broadband;
	}
	
	private Broadband returnBroadband(String address) {
		String message = "";
		try {
			message = BroadbandCapability.getCapabilityResultByAddress(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Broadband broadband = new Broadband();
		String services_available = "";
		if (message.contains("> 10")) {
			broadband.setAdsl_available(true);
			services_available += "ADSL";
		} 
		if (message.contains("> 20")) {
			broadband.setVdsl_available(true);
			services_available += "VDSL";
		} 
		if (message.contains("Business fibre available") || message.contains("Network capability:<\\/h4><ul><li>UFB fibre up to 100 Mbps")) {
			broadband.setUfb_available(true);
			services_available += "UFB";
		} 
		broadband.setScheduled(message.substring(message.lastIndexOf(",") + 1));
		broadband.setServices_available(services_available);
	
		return broadband;
	}
	
	@RequestMapping("/order/select-combo/combo-loading")
	public List<Combo> orderComboLoading(HttpSession session) {
		return null;
	}

}
