package com.edutecno.SitioProtegidoConCredenciales.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
	final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("thymeleaf/login");
		return modelAndView;
	}

	@GetMapping("/")
	public RedirectView init() {
		System.out.println(auth);
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			if (auth.getDetails() != null)
				System.out.println(auth.getDetails().getClass());
			if (auth.getDetails() instanceof UserDetails) {
				System.out.println("UserDetails");
			} else {
				System.out.println("!UserDetails");
			}
		}
		return new RedirectView("/login");
	}

}
