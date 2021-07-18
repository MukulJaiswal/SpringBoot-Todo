package com.springboot.application.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

//	LoginService loginService = new LoginService(); //This is Tight Coupling

//	@Autowired
//	LoginService loginService;

	/**
	 * @GetMapping("/") //We can also used GetMapping here and now it mostly used as
	 * it is a modern annotation.
	 * 
	 * @GetMapping and @RequestMapping is exactly similar.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap map) {
		map.put("name", getLoggedInUserName());
		return "welcome";
	}

	public String getLoggedInUserName() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
}
