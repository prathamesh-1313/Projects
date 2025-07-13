package com.smart.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.Models.User;
import com.smart.Repositories.UserRepository;

import jakarta.validation.Valid;

@Controller
public class HomeController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String home() {
		
		return "Home";
	}
	
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	
	@RequestMapping("/signin")
	public String signin() {
		
		return "signin";
	}
	
	
	@RequestMapping("/registerinfo")
	public String registerInfo(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("nameErr", result.getFieldError("uname"));
			model.addAttribute("emailErr", result.getFieldError("uemail"));
			model.addAttribute("passErr", result.getFieldError("upassword"));
			model.addAttribute("roleErr", result.getFieldError("urole"));
			return "signup";
		}
		
		if(user.getUrole().equals("Admin")) {
			user.setUrole("ROLE_ADMIN");
		}
		
		
		if(user.getUrole().equals("User")) {
			user.setUrole("ROLE_USER");
		}
		
		if(user.getUrole().equals("Store")) {
			user.setUrole("ROLE_OWNER");
		}
		
		
		
		user.setUid(UUID.randomUUID().toString());
		
		user.setUpassword(passwordEncoder.encode(user.getUpassword()));
		
		
		userRepository.save(user);
		
		return "redirect:/user/signin";
	}
	
}
