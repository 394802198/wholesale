package com.tm.wholesale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WholesalerController {

	@RequestMapping(value = { "", "/sign-in" })
	public String signin(Model model) {
		model.addAttribute("title", "Sign in - Wholesale Portal");
		return "wholesale/sign-in";
	}
	
	@RequestMapping("/index/redirect")
	public String indexRedirect(RedirectAttributes attr) {
		attr.addFlashAttribute("success", "Welcome to Wholesale Portal System of Total Moblie Solution.");
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("title", "Home - Wholesale Portal");
		return "wholesale/index";
	}
}
