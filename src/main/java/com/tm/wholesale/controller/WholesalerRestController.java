package com.tm.wholesale.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tm.wholesale.model.JSONBean;
import com.tm.wholesale.model.Manager;
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.WholesalerService;
import com.tm.wholesale.validation.ManagerLoginValidatedMark;
import com.tm.wholesale.validation.WholesalerLoginValidatedMark;

@RestController
public class WholesalerRestController {
	
	private WholesalerService wholesalerService;
	
	@Autowired
	public WholesalerRestController(WholesalerService wholesalerService) {
		this.wholesalerService = wholesalerService;
	}
	
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public JSONBean<Wholesaler> doSignin(
			@Validated(WholesalerLoginValidatedMark.class) Wholesaler wholesaler, BindingResult result,
			HttpSession session) {
		
		JSONBean<Wholesaler> json = new JSONBean<Wholesaler>();
		json.setModel(wholesaler);

		if (result.hasErrors()) {
			json.setJSONErrorMap(result);
			return json;
		}
		
		wholesaler.getParams().put("login_name", wholesaler.getLogin_name());
		wholesaler.getParams().put("login_password", wholesaler.getLogin_password());
		
		Wholesaler wholesalerSession = this.wholesalerService.queryWholesaler(wholesaler);

		if (wholesalerSession == null) {
			json.getErrorMap().put("alert-error", "Incorrect account or password");
			return json;
		}
		
		session.setAttribute("wholesalerSession", wholesalerSession);
		
		String url = "/index/redirect";
		json.setUrl(url);

		return json;
	}
}
