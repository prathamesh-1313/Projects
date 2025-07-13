package com.smart.Controllers;

import java.security.Principal;
import java.time.chrono.IsoChronology;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.query.sqm.mutation.internal.temptable.UpdateExecutionDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.Models.Rating;
import com.smart.Models.Store;
import com.smart.Models.User;
import com.smart.Repositories.StoreRepository;
import com.smart.Repositories.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@ModelAttribute
	public void addCommonData(Principal principal, Model model) {
		String username = principal.getName();

		User user = userRepository.getUserByEmail(username);

		model.addAttribute("user", user);
	}

	@RequestMapping("/index")
	public String userdash(Model model) {

		List<Store> allStores = storeRepository.findAll();
		model.addAttribute("allStores", allStores);
		
		
		return "User/Userdash";
	}
	
	
	@RequestMapping("/rating")
	public String ratinginfo(@ModelAttribute Rating rating,Principal principal) {
		
		System.err.println("rating is : " +rating);
		
		User user = userRepository.getUserByEmail(principal.getName());
		
	
		
		
		
		Store getStoreById = storeRepository.getById(rating.getId());
		rating.setId(UUID.randomUUID().toString());
		
		System.out.println("store data are : " + getStoreById.toString());
		
		rating.setUser(user);
		
		getStoreById.getRatings().add(rating);
		
		rating.setStore(getStoreById);

		storeRepository.save(getStoreById);
		
		System.err.println("Store ratings are saved");
		
		
		return "redirect:/user/index";
	}
	
	@RequestMapping("/profile")
	public String profile(Principal principal,Model model) {
		
		User user = userRepository.getUserByEmail(principal.getName());
		
		model.addAttribute("user", user);
		
		return "User/Profile";
	}
	
	
	@RequestMapping("/updateProfile/{uid}")
	public String update(@PathVariable("uid") String uid, Principal principal, Model model) {
		
		
	    User user=userRepository.getById(uid);
	    
	    
	    model.addAttribute("user", user);
		
		return "User/UpdateProfile";
	}
	
	
	@RequestMapping("/updateInfo")
	public String updateInfo(@ModelAttribute User user,Principal principal) {
		
		User oldUser = userRepository.getUserByEmail(principal.getName());
		
		oldUser.setUname(user.getUname());
		oldUser.setUemail(user.getUemail());
		oldUser.setUaddress(user.getUaddress());
		
		userRepository.save(oldUser);
		
		
		return "redirect:/user/index";
	}
	
	

}
