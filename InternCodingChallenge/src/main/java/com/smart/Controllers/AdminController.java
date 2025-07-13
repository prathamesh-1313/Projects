package com.smart.Controllers;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.Models.Store;
import com.smart.Models.User;
import com.smart.Repositories.StoreRepository;
import com.smart.Repositories.UserRepository;
import com.smart.helper.Message;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@ModelAttribute
	public void addCommonData(Principal principal, Model model) {
		String username = principal.getName();

		User user = userRepository.getUserByEmail(username);

		model.addAttribute("user", user);
	}
	
	
	@RequestMapping("/index")
	public String adminDash() {
		
		return "Admin/AdminDash";
	}
	
	
	
	
	@RequestMapping("/viewusers")
	public String viewUsers(Model model) {
		
		
		List<User>  allUsers = userRepository.findAll();
		
		model.addAttribute("allUsers", allUsers);
		
		return "Admin/AllUsers";
	}
	
	
	
	@RequestMapping("/viewstores")
	public String viewStores(Model model) {
		
		List<Store> allStores = storeRepository.findAll();
		
		model.addAttribute("allStores", allStores);
		
		return "Admin/AllStores";
	}
	
	
	
	@RequestMapping("/delUser/{uid}")
	public String delUser(@PathVariable("uid") String uid) {
		
		
		userRepository.deleteById(uid);
		
		return "redirect:/admin/viewusers";
	}
	
	
	
	@RequestMapping("/delStore/{id}")
	public String delStore(@PathVariable("id") String id) {
		
		storeRepository.deleteById(id);
		
		return "redirect:/admin/viewstores";
	}
	
	
	@RequestMapping("/profile")
	public String profile(Principal principal,Model model) {
		
		User admin = userRepository.getUserByEmail(principal.getName());
		
		model.addAttribute("admin", admin);
		
		return "Admin/Profile";
	}
	
	
	@RequestMapping("/addstore")
	public String addStore(Model model) {
		
		model.addAttribute("store", new Store());
		
		return "Admin/AddStore";
	}
	
	
	@RequestMapping("/addStoreInfo")
	public String addStoreInfo(@Valid @ModelAttribute Store store, BindingResult result, Model model, Principal principal){
		
	
		
		if(result.hasErrors()) {
          
			
			model.addAttribute("nameErr", result.getFieldError("name"));
			model.addAttribute("emailErr", result.getFieldError("email"));
			model.addAttribute("addressErr", result.getFieldError("address"));
			return "Admin/AddStore";
		}
		
		
	     store.setId(UUID.randomUUID().toString());
	     
	     User admin = userRepository.getUserByEmail(principal.getName());
	     
	     store.setOwner(admin);
	     
	     admin.getStore().add(store);
		 
	     userRepository.save(admin);
	     
	     model.addAttribute("storeMsg", new Message("Store Registered Successfully!!","alert-success"));
		
		
		return "StoreOwner/AddStore";
	}
	
	
	
	
}
