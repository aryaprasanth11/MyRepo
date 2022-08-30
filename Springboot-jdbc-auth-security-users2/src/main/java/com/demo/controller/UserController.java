package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.UseRegistration;

@Controller
public class UserController {
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("registration","user",new UseRegistration());
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView processRegister(@ModelAttribute("user") UseRegistration userRegistrationObject) {
		
		 
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
		authorities.add(new SimpleGrantedAuthority(userRegistrationObject.getUserrole()));
		User user=new User(userRegistrationObject.getUsername(),userRegistrationObject.getPassword(),authorities);
		jdbcUserDetailsManager.createUser(user);
		
		return new ModelAndView("redirect:/welcome");
		
	}

}
